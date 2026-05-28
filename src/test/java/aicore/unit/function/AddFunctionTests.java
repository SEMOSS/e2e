package aicore.unit.function;

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
import aicore.utils.CatlogAccessPageUtility;
import aicore.utils.CommonUtils;
import aicore.utils.FunctionTestUtils;
import aicore.utils.TestResourceTrackerHelper;
import aicore.utils.annotations.PWPage;

public class AddFunctionTests extends AbstractPlaywrightTestBase {
	
	@BeforeEach
	void setup(@PWPage Page page) {
		loginNativeAdmin(page);
	}	
	@AfterEach
	void goHome(@PWPage Page page) {
		logout(page);
	}
	
	private static Stream<Arguments> provideFormInputsForTestValidateFunctions() {
	    return Stream.of(
				Arguments.of("REST", "TestFunction{Timestamp}", "https://api.api-ninjas.com/v1/weather", "GET", "json",
						"{\"X-Api-Key\": \"myKey\"}",
						List.of(Map.of("parameterName", "lat", "parameterType", "String", "parameterDescription",
								"The lat of the location"),
								Map.of("parameterName", "lon", "parameterType", "String", "parameterDescription",
										"lon of the location")),
						List.of("lat", "lon"), "WeatherFunction", "a function to call weather based on lat and long",
						"catalog_name", "Create function", "Successfully added function database to catalog",
						"Function Type,Catalog Name,URL,Http Method,POST Message Body Type,Function Name (metadata),Function Description (metadata)")	    		);
	}
	
	@ParameterizedTest
	@MethodSource("provideFormInputsForTestValidateFunctions")
	void testCreateFunctionWithAllRequiredFields(String functionType, String catalogName, String url, String httpMethod, String postBodyMessage, String headers, List<Map<String, String>> functionParameters, List<String> functionRequiredParameters, String functionName, String functionDescription, String functionTitle, String createFunction, String toastMessage, String requiredFields, @PWPage Page page) {		
		String timestamp = CommonUtils.getTimeStampName();
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenFunction(page); 
		AddFunctionPageUtils.clickOnAddFunctionButton(page);
		AddFunctionFormUtils.selectFunction(page, functionType);
		FunctionTestUtils.verifyAsteriskOnRequiredFields(page, requiredFields);
		AddFunctionFormUtils.enterCatalogName(page, catalogName, timestamp);
		AddFunctionFormUtils.enterUrl(page, url);
		AddFunctionFormUtils.selectHttpMethod(page, httpMethod);
		AddFunctionFormUtils.selectPostBodyMessage(page, postBodyMessage);
		AddFunctionFormUtils.enterHeaders(page, headers);
		AddFunctionFormUtils.enterFunctionParameters(page, functionParameters);
		AddFunctionFormUtils.enterFunctionRequiredParameters(page, functionRequiredParameters);
		AddFunctionFormUtils.enterFunctionName(page, functionName);
		AddFunctionFormUtils.enterFunctionDescription(page, functionDescription);
		AddFunctionFormUtils.checkCreateFunctionButton(page);
		AddFunctionFormUtils.clickOnConnectButton(page);
		CatlogAccessPageUtility.getCatalogAndCopyId(page);
		FunctionTestUtils.verifyUserSeesSuccessToastMessage(page, toastMessage);
		// delete db
		CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_FUNCTION, catalogName.replace("{Timestamp}", " " + timestamp));
	}
	
	private static Stream<Arguments> provideIncompleteInputsForTestValidateFunctions() {
	    return Stream.of(
				Arguments.of("REST", "TestFunction{Timestamp}", "GET", "json", "{\"X-Api-Key\": \"myKey\"}",
						List.of(Map.of("parameterName", "lat", "parameterType", "String", "parameterDescription",
								"The lat of the location"),
								Map.of("parameterName", "lon", "parameterType", "String", "parameterDescription",
										"lon of the location")),
						List.of("lat", "lon"), "WeatherFunction", "a function to call weather based on lat and long",
						"catalog_name", "Create function", "Successfully added function database to catalog",
						"Function Type,Catalog Name,URL,Http Method,POST Message Body Type,Function Name (metadata),Function Description (metadata)")	    		);
	}
	
	@ParameterizedTest	@MethodSource("provideIncompleteInputsForTestValidateFunctions")
	void testCreateFunctionWithMissingFields(String functionType, String catalogName, String httpMethod, String postBodyMessage, String headers, List<Map<String, String>> functionParameters, List<String> functionRequiredParameters, String functionName, String functionDescription, String functionTitle, String createFunction, String toastMessage, String requiredFields, @PWPage Page page) {		
		String timestamp = CommonUtils.getTimeStampName();
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenFunction(page); 
		AddFunctionPageUtils.clickOnAddFunctionButton(page);
		AddFunctionFormUtils.selectFunction(page, functionType);
		FunctionTestUtils.verifyAsteriskOnRequiredFields(page, requiredFields);
		AddFunctionFormUtils.enterCatalogName(page, catalogName, timestamp);
		AddFunctionFormUtils.selectHttpMethod(page, httpMethod);
		AddFunctionFormUtils.selectPostBodyMessage(page, postBodyMessage);
		AddFunctionFormUtils.enterHeaders(page, headers);
		AddFunctionFormUtils.enterFunctionName(page, functionName);
		AddFunctionFormUtils.enterFunctionDescription(page, functionDescription);
		AddFunctionFormUtils.enterFunctionParameters(page, functionParameters);
		AddFunctionFormUtils.enterFunctionRequiredParameters(page, functionRequiredParameters);

		boolean isButtonEnabled = AddFunctionFormUtils.validateConnectButtonEnabled(page);
		Assertions.assertFalse(isButtonEnabled, "'Connect' button is enabled");
	}
}
