package aicore.unit.function;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;

import aicore.hooks.SetupHooks;
import aicore.pages.function.AddFunctionFormUtils;
import aicore.pages.function.GeneralFunctionPage;
import aicore.pages.home.MainMenuUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.AddFunctionPageUtils;
import aicore.utils.CatalogCreationFromZipUtil;
import aicore.utils.CatlogAccessPageUtility;
import aicore.utils.CommonUtils;
import aicore.utils.FunctionTestUtils;
import aicore.utils.TestResourceTrackerHelper;
import aicore.utils.TestResources;
import aicore.utils.annotations.PWPage;
import aicore.utils.annotations.ResourceUploadLock;
import aicore.utils.page.app.AppPageUtils;

public class FunctionPageFiltersTests extends AbstractPlaywrightTestBase {
	private static final Logger logger = LogManager.getLogger(FunctionPageFiltersTests.class);

	String testFunctionName = "";

	@BeforeEach
	void setup(@PWPage Page page) {
		logger.info("Setup: creating functions");
		loginNativeAdmin(page);

		String timestamp = CommonUtils.getTimeStampName();
		testFunctionName = "TestFunction " + timestamp;
		String url = "https://api.api-ninjas.com/v1/weather";
		String httpMethod = "GET";
		String postBodyMessage = "json";
		String functionParameters = "[{\"parameterName\":\"lat\",\"parameterType\":\"String\",\"parameterDescription\":\"The lat of the location\"},{\"parameterName\":\"lon\",\"parameterType\":\"String\",\"parameterDescription\":\"lon of the location\"}]";
		String functionRequiredParameters = "[\"lat\", \"lon\"]";
		String functionName = "Weatherfunction";
		String functionDescription = "a function to call weather based on lat and long";
		// check for and delete functions to be tested
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenFunction(page);
		GeneralFunctionPage.deleteFunctionIfExists(page, testFunctionName);
		GeneralFunctionPage.deleteFunctionIfExists(page, TestResources.WEATHER_FUNC_NAME);
		// add REST function
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenFunction(page);
		AddFunctionPageUtils.clickOnAddFunctionButton(page);
		AddFunctionFormUtils.selectFunction(page, "REST");
		AddFunctionFormUtils.enterCatalogName(page, testFunctionName, timestamp);
		AddFunctionFormUtils.enterUrl(page, url);
		AddFunctionFormUtils.selectHttpMethod(page, httpMethod);
		AddFunctionFormUtils.selectPostBodyMessage(page, postBodyMessage);
		AddFunctionFormUtils.enterFunctionParameters(page, functionParameters);
		AddFunctionFormUtils.enterFunctionRequiredParameters(page, functionRequiredParameters);
		AddFunctionFormUtils.enterFunctionName(page, functionName);
		AddFunctionFormUtils.enterFunctionDescription(page, functionDescription);
		AddFunctionFormUtils.checkCreateFunctionButton(page);
		AddFunctionFormUtils.clickOnConnectButton(page);
		CatlogAccessPageUtility.getCatalogAndCopyId(page);
		// upload zip function
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenFunction(page);
		AddFunctionPageUtils.clickOnAddFunctionButton(page);
		CatalogCreationFromZipUtil.clickOnFileUploadIcon(page);
		FunctionTestUtils.userUploadsFile(page, TestResources.WEATHER_FUNC_ZIP);
		CatalogCreationFromZipUtil.clickOnUploadButton(page, "Upload");
		CatlogAccessPageUtility.getCatalogAndCopyId(page);
	}

	@AfterEach
	void tearDown(@PWPage Page page) {
		logger.info("Tear Down: deleting functions");
		// delete catalogs
		CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_FUNCTION, testFunctionName);
		CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_FUNCTION,
				TestResources.WEATHER_FUNC_NAME);

		logout(page);
	}

	@Test
	@ResourceUploadLock(TestResources.WEATHER_FUNC_ZIP)
	void testValidateFunctionFilter(@PWPage Page page) {
		// toggle asc/desc sort filter
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenFunction(page);
		AppPageUtils.clickOnFilterButton(page, "Ascending");
		boolean isSortedInAscendingOrder = AppPageUtils.verifySortedInAscendingOrder(page);
		Assertions.assertTrue(isSortedInAscendingOrder, "Not sorted in ascending order");
		AppPageUtils.clickOnFilterButton(page, "Descending");
		boolean isSortedInDescendingOrder = AppPageUtils.verifySortedInDescendingOrder(page);
		Assertions.assertTrue(isSortedInDescendingOrder, "Not sorted in descending order");
		// sort by dat created and toggle sort order filter
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenFunction(page);
		AppPageUtils.selectSortByOption(page, "Date Created");
		AppPageUtils.clickOnFilterButton(page, "Ascending");
		isSortedInAscendingOrder = AppPageUtils.verifySortedInAscendingOrder(page);
		Assertions.assertTrue(isSortedInAscendingOrder, "Not sorted in ascending order");
		AppPageUtils.clickOnFilterButton(page, "Descending");
		isSortedInDescendingOrder = AppPageUtils.verifySortedInDescendingOrder(page);
		Assertions.assertTrue(isSortedInDescendingOrder, "Not sorted in descending order");
	}
}
