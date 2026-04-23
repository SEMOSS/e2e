package aicore.unit.function;

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
import aicore.utils.CatlogAccessPageUtility;
import aicore.utils.CommonUtils;
import aicore.utils.FunctionTestUtils;
import aicore.utils.TestResourceTrackerHelper;
import aicore.utils.AbstractE2ETest.UserType;

public class AddFunctionTests extends AbstractE2ETest {
	
	@BeforeAll
	static void setup() {
		login(page, UserType.NATIVE);
	}
	
	private static Stream<Arguments> provideFormInputsForTestValidateFunctions() {
	    return Stream.of(
	    		Arguments.of("REST","TestFunction{Timestamp}","https://api.api-ninjas.com/v1/weather","GET","json","{\"X-Api-Key\": \"myKey\"}", "[{\"parameterName\":\"lat\",\"parameterType\":\"String\",\"parameterDescription\":\"The lat of the location\"},{\"parameterName\":\"lon\",\"parameterType\":\"String\",\"parameterDescription\":\"lon of the location\"}]","[\"lat\", \"lon\"]","WeatherFunction","a function to call weather based on lat and long","catalog_name","Create function","Successfully added function database to catalog","Function Type,Catalog Name,URL,Http Method,POST Message Body Type,Function Parameters,Function Required Parameters,Function Name (metadata),Function Description (metadata)")
	    		);
	}
	
	@ParameterizedTest
	@MethodSource("provideFormInputsForTestValidateFunctions")
	void testCreateFunctionWithAllRequiredFields(String functionType, String catalogName, String url, String httpMethod, String postBodyMessage, String headers, String functionParameters, String functionRequiredParameters, String functionName, String functionDescription, String functionTitle, String createFunction, String toastMessage, String requiredFields) {		
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
		//TODO fails to delete catalog but doesn't throw error - needs to investigate
		CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_FUNCTION, functionName);
	}
	
	private static Stream<Arguments> provideIncompleteInputsForTestValidateFunctions() {
	    return Stream.of(
	    		Arguments.of("REST","TestFunction{Timestamp}","GET","json","{\"X-Api-Key\": \"myKey\"}", "[{\"parameterName\":\"lat\",\"parameterType\":\"String\",\"parameterDescription\":\"The lat of the location\"},{\"parameterName\":\"lon\",\"parameterType\":\"String\",\"parameterDescription\":\"lon of the location\"}]","[\"lat\", \"lon\"]","WeatherFunction","a function to call weather based on lat and long","catalog_name","Create function","Successfully added function database to catalog","Function Type,Catalog Name,URL,Http Method,POST Message Body Type,Function Parameters,Function Required Parameters,Function Name (metadata),Function Description (metadata)")
	    		);
	}
	
	@ParameterizedTest
	@MethodSource("provideIncompleteInputsForTestValidateFunctions")
	void testCreateFunctionWithMissingFields(String functionType, String catalogName, String httpMethod, String postBodyMessage, String headers, String functionParameters, String functionRequiredParameters, String functionName, String functionDescription, String functionTitle, String createFunction, String toastMessage, String requiredFields) {		
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
		AddFunctionFormUtils.enterFunctionParameters(page, functionParameters);
		AddFunctionFormUtils.enterFunctionRequiredParameters(page, functionRequiredParameters);
		AddFunctionFormUtils.enterFunctionName(page, functionName);
		AddFunctionFormUtils.enterFunctionDescription(page, functionDescription);

		boolean isButtonEnabled = AddFunctionFormUtils.validateConnectButtonEnabled(page);
		Assertions.assertFalse(isButtonEnabled, "'Connect' button is enabled");
	}
}
