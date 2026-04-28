package aicore.unit.function;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import aicore.pages.function.AddFunctionFormUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.utils.AbstractE2ETest;
import aicore.utils.AddFunctionPageUtils;
import aicore.utils.CommonUtils;
import aicore.utils.FunctionTestUtils;

public class AddAllFunctionTypesTests extends AbstractE2ETest {
	
	@BeforeAll
	static void setup() {
		login(page, UserType.NATIVE);
	}
	
	private static Stream<Arguments> provideFormInputsForTestValidateFunctions() {
	    return Stream.of(
	    		Arguments.of( "AWS Image Text Extraction", "General","Function Type, Catalog Name","Credentials","Access Key, Secret Key","Settings","Region, S3 Bucket Engine Id, Function Name (metadata), Function Description (metadata), Function Required Parameters","Function Type, Catalog Name, Access Key, Secret Key, Region, S3 Bucket Engine Id, Function Name (metadata), Function Description (metadata), Function Required Parameters","Function Type=AWS REKOGNITION, Catalog Name=AWS-Image-Text-Extraction, Access Key=Test123, Secret Key=Test@123, Region=Asia, S3 Bucket Engine Id=s3, Function Name (metadata)=Text-Extraction, Function Description (metadata)=Testing, Function Required Parameters=[\"isFilePresentInS3\",\"filePath\"]"),
	    		Arguments.of("AWS Polly","General","Function Type, Catalog Name","Credentials","Access Key, Secret Key","Settings","Region, Function Name (metadata), Function Description (metadata), Function Required Parameters","Function Type, Catalog Name, Access Key, Secret Key, Region, Function Name (metadata), Function Description (metadata), Function Required Parameters","Catalog Name=AWS-Polly, Access Key=Test123, Secret Key=Test@123, Region=Asia, Function Name (metadata)=AWS Polly, Function Description (metadata)=Testing, Function Required Parameters=[\"isFilePresentInS3\",\"filePath\"]" ),
	    		Arguments.of("AWS Transcribe","General","Function Type, Catalog Name","Credentials","Access Key, Secret Key","Settings","Region, S3 Bucket Engine Id, Function Name (metadata), Function Description (metadata), Function Required Parameters","Function Type, Catalog Name, Access Key, Secret Key, Region, S3 Bucket Engine Id, Function Name (metadata), Function Description (metadata), Function Required Parameters","Catalog Name=AWS-Transcribe , Access Key=Test123, Secret Key=Test@123, Region=Asia, S3 Bucket Engine Id=s3, Function Name (metadata)=AWS Transcribe , Function Description (metadata)=Testing, Function Required Parameters=[\"isFilePresentInS3\",\"filePath\"]"),
	    		Arguments.of("AWS Comprehend","General","Function Type, Catalog Name","Credentials","Access Key, Secret Key","Settings","Region, Function Name (metadata), Function Description (metadata), Function Required Parameters","Function Type, Catalog Name, Access Key, Secret Key, Region, Function Name (metadata), Function Description (metadata), Function Required Parameters","Catalog Name=AWS-Comprehend, Access Key=Test123, Secret Key=Test@123, Region=Asia, Function Name (metadata)=AWS Comprehend, Function Description (metadata)=Testing, Function Required Parameters=[\"isFilePresentInS3\",\"filePath\"]"),
	    		Arguments.of("Azure Document Intelligence","General","Function Type, Catalog Name","Credentials","API Key, URL","        ","   ","Function Type, Catalog Name, URL, API Key","Catalog Name=Azure-Document-Intelligence, URL=https://www.google.com, API Key=Test123"),
	    		Arguments.of("Azure Speech To Text","General","Function Type, Catalog Name","Credentials","Speech Key","Settings","Speech region, Function Name (metadata), Function Description (metadata), Function Required Parameters","Function Type, Catalog Name, Speech Key, Speech region, Function Name (metadata), Function Description (metadata), Function Required Parameters","Catalog Name=Azure-Speech-To-Text, Speech Key=Test@123, Speech region=Asia, Function Name (metadata)=Speech-To-Text, Function Description (metadata)=Testing, Function Required Parameters=[\"isFilePresentInS3\",\"filePath\"]"),
	    		Arguments.of("REST","General","Function Type, Catalog Name","Credentials","URL, Http Method","Settings","POST Message Body Type, Http Headers, Function Parameters, Function Name (metadata), Function Description (metadata), Function Required Parameters","Function Type, Catalog Name, URL, Http Method, POST Message Body Type, Function Parameters, Function Name (metadata), Function Description (metadata), Function Required Parameters","Catalog Name=Rest, URL=https://www.google.com, Http Method=HEAD, POST Message Body Type=x-www-form-urlencoded, Http Headers=Host, Function Parameters=myFunctio, Function Name (metadata)=Rest, Function Description (metadata)=Testing, Function Required Parameters=[\"isFilePresentInS3\",\"filePath\"]")
	    		);
	}
	
	@ParameterizedTest
	@MethodSource("provideFormInputsForTestValidateFunctions")
	void testValidateFunctions(String functionName, String s1Name, String s1Fields, String s2Name, String s2Fields, String s3Name, String s3Fields, String mandatoryFields, String formFields) throws IOException {
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
		/// do not need to delete, connect button is never clicked, function not created
	}
	
	
	private static Stream<Arguments> provideFormInputsForTestValidateFunctionsRequiringUpload() {
	    return Stream.of(
	    		Arguments.of("Google Speech To Text","General","Function Type, Catalog Name","Credentials","Google Bucket Engine Id, Function Name (metadata), Function Description (metadata)","Settings","Upload Service Account File, Function Required Parameters","Function Type, Catalog Name, Google Bucket Engine Id, Function Name (metadata), Function Description (metadata), Function Required Parameters","Catalog Name=AWS-Image-Text-Extraction, Function Name (metadata)=Text-Extraction, Google Bucket Engine Id=G10, Function Description (metadata)=Testing, Function Required Parameters=[\"isFilePresentInS3\",\"filePath\"]"),
	    		Arguments.of("Google OCR","General","Function Type, Catalog Name","Credentials","Project Id","Settings","Processor Id, Region, Upload Service Account File, Google Bucket Engine Id, Function Name (metadata), Function Description (metadata), Function Required Parameters","Function Type, Catalog Name, Project Id, Processor Id, Region, Google Bucket Engine Id, Function Name (metadata), Function Description (metadata), Function Required Parameters","Catalog Name=Google-OCR, Project Id=01, Processor Id=1.1, Region=Asia, Function Name (metadata)=Text-Extraction, Google Bucket Engine Id=G10, Function Description (metadata)=Testing, Function Required Parameters=[\"isFilePresentInS3\",\"filePath\"]")
	    		);
	}
	
	@ParameterizedTest
	@MethodSource("provideFormInputsForTestValidateFunctionsRequiringUpload")
	void testValidateFunctionsRequiringUpload(String functionName, String s1Name, String s1Fields, String s2Name, String s2Fields, String s3Name, String s3Fields, String mandatoryFields, String formFields) throws IOException {
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
		FunctionTestUtils.verifyUploadedFunctionFile(page, "Function/weatherFunctionTest.zip");
		boolean isButtonEnabled = AddFunctionFormUtils.validateConnectButtonEnabled(page);
		Assertions.assertTrue(isButtonEnabled, "'Connect' button is not enabled");
		/// do not need to delete, connect button is never clicked, function not created
	}
}
