package aicore.unit.function;


import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.microsoft.playwright.Page;

import aicore.pages.function.AddFunctionFormUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.AddFunctionPageUtils;
import aicore.utils.CommonUtils;
import aicore.utils.FunctionTestUtils;
import aicore.utils.TestResources;
import aicore.utils.annotations.PWPage;

public class AddAllFunctionTypesTests extends AbstractPlaywrightTestBase {
	
	@BeforeEach
	void setup(@PWPage Page page) {
		loginNativeAdmin(page);
	}	
	@AfterEach
	void tearDown(@PWPage Page page) {
		logout(page);
	}

	private static Stream<Arguments> provideFormInputsForTestValidateFunctions() {
	    return Stream.of(
	    		Arguments.of( "AWS Image Text Extraction", "General","Function Type, Catalog Name","Credentials","Access Key, Secret Key","Settings","Region, S3 Bucket Engine Id","Function Type, Catalog Name, Access Key, Secret Key, Region, S3 Bucket Engine Id","Function Type=AWS REKOGNITION, Catalog Name=AWS-Image-Text-Extraction, Access Key=Test123, Secret Key=Test@123, Region=Asia, S3 Bucket Engine Id=s3"),
	    		Arguments.of("AWS Polly","General","Function Type, Catalog Name","Credentials","Access Key, Secret Key","Settings","Region","Function Type, Catalog Name, Access Key, Secret Key, Region","Catalog Name=AWS-Polly, Access Key=Test123, Secret Key=Test@123, Region=Asia" ),
	    		Arguments.of("AWS Transcribe","General","Function Type, Catalog Name","Credentials","Access Key, Secret Key","Settings","Region, S3 Bucket Engine Id","Function Type, Catalog Name, Access Key, Secret Key, Region, S3 Bucket Engine Id","Catalog Name=AWS-Transcribe , Access Key=Test123, Secret Key=Test@123, Region=Asia, S3 Bucket Engine Id=s3"),
	    		Arguments.of("AWS Comprehend","General","Function Type, Catalog Name","Credentials","Access Key, Secret Key","Settings","Region","Function Type, Catalog Name, Access Key, Secret Key, Region","Catalog Name=AWS-Comprehend, Access Key=Test123, Secret Key=Test@123, Region=Asia"),
	    		Arguments.of("Azure Document Intelligence","General","Function Type, Catalog Name","Credentials","API Key, URL","        ","   ","Function Type, Catalog Name, URL, API Key","Catalog Name=Azure-Document-Intelligence, URL=https://www.google.com, API Key=Test123"),
	    		Arguments.of("Azure Speech To Text","General","Function Type, Catalog Name","Credentials","Speech Key","Settings","Speech region","Function Type, Catalog Name, Speech Key, Speech region","Catalog Name=Azure-Speech-To-Text, Speech Key=Test@123, Speech region=Asia")
				);
	}
	
	@ParameterizedTest
	@MethodSource("provideFormInputsForTestValidateFunctions")
	void testValidateFunctions(String functionName, String s1Name, String s1Fields, String s2Name, String s2Fields, String s3Name, String s3Fields, String mandatoryFields, String formFields, @PWPage Page page) throws IOException {
		/// set up test parameters
		List<Map<String, String>> fields = List.of(
					Map.of("SECTION_NAME", s1Name, "FIELDS", s1Fields),
					Map.of("SECTION_NAME", s2Name, "FIELDS", s2Fields),
					Map.of("SECTION_NAME", s3Name, "FIELDS", s3Fields));    		  
				
		String timestamp = CommonUtils.getTimeStampName();
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenFunction(page); 
		AddFunctionPageUtils.clickOnAddFunctionButton(page);
		AddFunctionFormUtils.selectFunction(page, functionName);

		FunctionTestUtils.verifyFunctionCreationFormWithSelectFields(page, fields);
		FunctionTestUtils.verifyFunctionCreationFormWithMandatoryFields(page, mandatoryFields);
		FunctionTestUtils.verifyUserFilledFunctionCreationFormFields(page, formFields, timestamp);
		boolean isButtonEnabled = AddFunctionFormUtils.validateConnectButtonEnabled(page);
		Assertions.assertTrue(isButtonEnabled, "'Connect' button is not enabled");
	}
	
	private static Stream<Arguments> provideFormInputsForFunctionsRequiringParameterSelection() {
	    return Stream.of(
	    		Arguments.of("REST", 
						"General",  "Function Type, Catalog Name", 
						"Credentials", "URL, Http Method",
						"Settings", "POST Message Body Type, Http Headers, Function Name (metadata), Function Description (metadata), Function Parameters, Function Required Parameters",
						"Function Type, Catalog Name, URL, Http Method, POST Message Body Type, Function Name (metadata), Function Description (metadata)",
						// start args to populate form
						"TestFunction{Timestamp}", 
						"", // file name not needed
						"https://api.api-ninjas.com/v1/weather", 
						"GET", 
						"json",
						"{\"X-Api-Key\": \"myKey\"}",
						List.of(Map.of("parameterName", "lat", "parameterType", "String", "parameterDescription",
								"The lat of the location"),
								Map.of("parameterName", "lon", "parameterType", "String", "parameterDescription",
										"lon of the location")),
						List.of("lat", "lon"), 
						"WeatherFunction", 
						"a function to call weather based on lat and long"
	    				),
	    		Arguments.of("Local Python Function", 
						"General",  "Function Type, Catalog Name", 
						" ", " ", // no credentials section
						"Settings", "Python File Name, Function Name (metadata), Function Description (metadata), Function Parameters, Function Required Parameters",
						"Function Type, Catalog Name, Python File Name, Function Name (metadata), Function Description (metadata)",
						// start args to populate form
						"TestFunction{Timestamp}", 
						"python_file.txt",
						"", // url not needed
						"", // post method not needed
						"", // httpMethod not needed
						"", // headers not needed
						List.of(Map.of("parameterName", "lat", "parameterType", "String", "parameterDescription",
								"The lat of the location"),
								Map.of("parameterName", "lon", "parameterType", "String", "parameterDescription",
										"lon of the location")),
						List.of("lat", "lon"), 
						"WeatherFunction", 
						"a function to call weather based on lat and long"
	    				)
	    		);
	}
	
	@ParameterizedTest
	@MethodSource("provideFormInputsForFunctionsRequiringParameterSelection")
	void testValidateFunctionsRequiringParameterSelection(String functionType, 
			String s1Name, String s1Fields,
			String s2Name, String s2Fields, 
			String s3Name, String s3Fields, 
			String mandatoryFields,//String formFields,
			String catalogName, 
			String fileName,
			String url, 
			String httpMethod, 
			String postBodyMessage, 
			String headers, 
			List<Map<String, String>> functionParameters, 
			List<String> functionRequiredParameters, 
			String functionName, 
			String functionDescription,
			@PWPage Page page) {
		// consolidate fields into their sections for validation
		List<Map<String, String>> fields = List.of(
				Map.of("SECTION_NAME", s1Name, "FIELDS", s1Fields),
				Map.of("SECTION_NAME", s2Name, "FIELDS", s2Fields),
				Map.of("SECTION_NAME", s3Name, "FIELDS", s3Fields)); 
		
		
		String timestamp = CommonUtils.getTimeStampName();
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenFunction(page); 
		AddFunctionPageUtils.clickOnAddFunctionButton(page);
		AddFunctionFormUtils.selectFunction(page, functionType);
		// verify sections and mandatory fields
		FunctionTestUtils.verifyFunctionCreationFormWithSelectFields(page, fields);
		FunctionTestUtils.verifyFunctionCreationFormWithMandatoryFields(page, mandatoryFields);
		// populate form
		AddFunctionFormUtils.enterCatalogName(page, catalogName, timestamp);
		if (!fileName.isBlank()) AddFunctionFormUtils.enterPythonFileName(page, fileName);
		if (!url.isBlank()) AddFunctionFormUtils.enterUrl(page, url);
		if (!httpMethod.isBlank()) AddFunctionFormUtils.selectHttpMethod(page, httpMethod);
		if (!httpMethod.isBlank()) AddFunctionFormUtils.selectPostBodyMessage(page, postBodyMessage);
		if (!headers.isBlank()) AddFunctionFormUtils.enterHeaders(page, headers);
		AddFunctionFormUtils.enterFunctionName(page, functionName);
		AddFunctionFormUtils.enterFunctionDescription(page, functionDescription);
		AddFunctionFormUtils.enterFunctionParameters(page, functionParameters);
		AddFunctionFormUtils.enterFunctionRequiredParameters(page, functionRequiredParameters);
		// validate connect button
		boolean isButtonEnabled = AddFunctionFormUtils.validateConnectButtonEnabled(page);
		Assertions.assertTrue(isButtonEnabled, "'Connect' button is not enabled");
	}
	
	private static Stream<Arguments> provideFormInputsForTestValidateFunctionsRequiringUpload() {
	    return Stream.of(
	    		Arguments.of("Google Speech To Text",
	    				"General","Function Type, Catalog Name",
	    				"Credentials","Google Bucket Engine Id",
	    				"Settings"," ",
	    				"Function Type, Catalog Name, Google Bucket Engine Id",
	    				"Catalog Name=AWS-Image-Text-Extraction, Google Bucket Engine Id=G10"),
	    		Arguments.of("Google OCR",
	    				"General","Function Type, Catalog Name",
	    				"Credentials","Project Id",
	    				"Settings","Processor Id, Region, Upload Service Account File, Google Bucket Engine Id",
	    				"Function Type, Catalog Name, Project Id, Processor Id, Region, Google Bucket Engine Id",
	    				"Catalog Name=Google-OCR, Project Id=01, Processor Id=1.1, Region=Asia, Google Bucket Engine Id=G10")
	    		);
	}
	
	@ParameterizedTest
	@MethodSource("provideFormInputsForTestValidateFunctionsRequiringUpload")
	void testValidateFunctionsRequiringUpload(String functionName, String s1Name, String s1Fields, String s2Name, String s2Fields, String s3Name, String s3Fields, String mandatoryFields, String formFields, @PWPage Page page) throws IOException {
		/// set up test parameters
		List<Map<String, String>> fields = List.of(
				Map.of("SECTION_NAME", s1Name, "FIELDS", s1Fields),
				Map.of("SECTION_NAME", s2Name, "FIELDS", s2Fields), 
				Map.of("SECTION_NAME", s3Name, "FIELDS", s3Fields));

		String timestamp = CommonUtils.getTimeStampName();
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenFunction(page);
		AddFunctionPageUtils.clickOnAddFunctionButton(page);
		AddFunctionFormUtils.selectFunction(page, functionName);
		FunctionTestUtils.verifyFunctionCreationFormWithSelectFields(page, fields);
		FunctionTestUtils.verifyFunctionCreationFormWithMandatoryFields(page, mandatoryFields);
		FunctionTestUtils.verifyUserFilledFunctionCreationFormFields(page, formFields, timestamp);
		FunctionTestUtils.verifyUploadedFunctionFile(page, TestResources.WEATHER_FUNC_ZIP);
		boolean isButtonEnabled = AddFunctionFormUtils.validateConnectButtonEnabled(page);
		Assertions.assertTrue(isButtonEnabled, "'Connect' button is not enabled");
	}
}
