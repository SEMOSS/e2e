package aicore.pages.function;

import java.util.List;
import java.util.Map;

import com.microsoft.playwright.Page;

import aicore.pages.home.MainMenuUtils;
import aicore.utils.AddFunctionPageUtils;
import aicore.utils.CatalogCreationFromZipUtil;
import aicore.utils.CatlogAccessPageUtility;
import aicore.utils.CommonUtils;
import aicore.utils.TestResources;

public class AddSpecificFunctionPage {

	/**
	 * Adds a REST function with default values for quick tests
	 * @param page
	 * @param catalogName
	 */
	public static String addFunction(Page page, String catalogName) {
		String timestamp = CommonUtils.getTimeStampName();
		String url = "https://api.api-ninjas.com/v1/weather"; 
		String httpMethod = "GET"; 
		String postBodyMessage = "json";
		String headers = "{\"X-Api-Key\": \"myKey\"}";
		List<Map<String, String>> functionParameters = List.of(Map.of("parameterName", "lat", "parameterType", "String", "parameterDescription",
				"The lat of the location"),
				Map.of("parameterName", "lon", "parameterType", "String", "parameterDescription",
						"lon of the location"));
		List<String> functionRequiredParameters = List.of("lat", "lon");
		String functionName = "testFunction"; 
		String functionDescription = "a function to call weather based on lat and long";
		return addParameterizedFunction(page,
				TestResources.FUNC_REST, 
				catalogName,
				timestamp,
				functionName,  
				null,
				url, 
				httpMethod, 
				postBodyMessage, 
				headers,
				functionDescription,
				functionParameters, 
				functionRequiredParameters);
	}
	
	/**
	 * used for aws and azure functions - does not need parameter selection
	 * 
	 * @param page
	 * @param functionType - EX: TestResources.FUNC_AWS_IMG_TXT_EXTRACTION
	 * @param timestamp
	 * @param formFields - ex: "Catalog Name=Azure-Speech-To-Text, Speech Key=Test@123, Speech region=Asia"
	 */
	public static String addNormalInputFunction(Page page, String functionType, String timestamp, String formFields) {
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenFunction(page); 
		AddFunctionPageUtils.clickOnAddFunctionButton(page);
		AddFunctionFormUtils.selectFunction(page, functionType);
		fillFunctionCreationForm(page, formFields, timestamp);
		AddFunctionFormUtils.checkCreateFunctionButton(page);
		AddFunctionFormUtils.clickOnConnectButton(page);
		return CatlogAccessPageUtility.getCatalogAndCopyId(page);
	}
	
	/**
	 * used for REST and Python functions - require parameters to be selected, not required field in form
	 * 
	 * @param page
	 * @param functionType - EX: TestResources.FUNC_REST
	 * @param timestamp
	 * @param functionName
	 * @param fileName - only needed for Local Python function
	 * @param url - only needed for REST function
	 * @param httpMethod - only needed for REST function
	 * @param postBodyMessage - only needed for REST function
	 * @param headers - only needed for REST function
	 * @param functionDescription
	 * @param functionParameters - EX: 
	  			List.of(
	  				Map.of("parameterName", "lat", "parameterType", "String", "parameterDescription", "The lat of the location"),
					Map.of("parameterName", "lon", "parameterType", "String", "parameterDescription", "lon of the location"))
			* Needs all three keys in map
	 * @param functionRequiredParameters - Ex: List.of("lat", "lon")
	 */
	public static String addParameterizedFunction(Page page, 
			String functionType, 
			String catalogName,
			String timestamp,
			String functionName,  
			String fileName,
			String url, 
			String httpMethod, 
			String postBodyMessage, 
			String headers,
			String functionDescription,
			List<Map<String, String>> functionParameters, 
			List<String> functionRequiredParameters) {
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenFunction(page); 
		AddFunctionPageUtils.clickOnAddFunctionButton(page);
		AddFunctionFormUtils.selectFunction(page, functionType);
		
		// populate form
		AddFunctionFormUtils.enterCatalogName(page, functionName, timestamp);
		if (fileName != null && !fileName.isBlank()) AddFunctionFormUtils.enterPythonFileName(page, fileName);
		if (url != null && !url.isBlank()) AddFunctionFormUtils.enterUrl(page, url);
		if (httpMethod != null && !httpMethod.isBlank()) AddFunctionFormUtils.selectHttpMethod(page, httpMethod);
		if (postBodyMessage != null && !postBodyMessage.isBlank()) AddFunctionFormUtils.selectPostBodyMessage(page, postBodyMessage);
		if (headers != null && !headers.isBlank()) AddFunctionFormUtils.enterHeaders(page, headers);
		AddFunctionFormUtils.enterFunctionName(page, functionName);
		AddFunctionFormUtils.enterFunctionDescription(page, functionDescription);
		AddFunctionFormUtils.enterFunctionParameters(page, functionParameters);
		AddFunctionFormUtils.enterFunctionRequiredParameters(page, functionRequiredParameters);
		AddFunctionFormUtils.checkCreateFunctionButton(page);
		AddFunctionFormUtils.clickOnConnectButton(page);
		return CatlogAccessPageUtility.getCatalogAndCopyId(page);
	}
	
	/**
	 * Used for Google speech-to-text and OCR functions
	 * 
	 * @param page
	 * @param functionType - EX: TestResources.FUNC_GOOGLE_OCR
	 * @param timestamp
	 * @param formFields - EX: "Catalog Name=Google-OCR, Project Id=01, Processor Id=1.1, Region=Asia, Google Bucket Engine Id=G10"
	 * @param fileUploadResource - needs to be the resource the test is locked on
	 */
	public static String addUploadFunction(Page page, String functionType, String timestamp, String formFields, String fileUploadResource) {
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenFunction(page);
		AddFunctionPageUtils.clickOnAddFunctionButton(page);
		AddFunctionFormUtils.selectFunction(page, functionType);
		fillFunctionCreationForm(page, formFields, timestamp);
		CatalogCreationFromZipUtil.uploadFile(page, fileUploadResource);
		AddFunctionFormUtils.checkCreateFunctionButton(page);
		AddFunctionFormUtils.clickOnConnectButton(page);
		return CatlogAccessPageUtility.getCatalogAndCopyId(page);
	}
	
	/**
	 * 
	 * 
	 * @param page
	 * @param formFields - ex: "Catalog Name=Azure-Speech-To-Text, Speech Key=Test@123, Speech region=Asia"
	 * @param timestamp
	 */
	public static void fillFunctionCreationForm(Page page, String formFields, String timestamp) {
		String[] fields = formFields.split(", ");
		for (String field : fields) {
			if (!field.contains("=")) {
				continue;
			}
			String[] keyValue = field.split("=", 2);
			String fieldName = keyValue[0].trim();
			String fieldValue = keyValue[1].trim();
			AddFunctionPageUtils.fillFunctionCreationForm(page, fieldName, fieldValue, timestamp);
		}
	}
	
}
