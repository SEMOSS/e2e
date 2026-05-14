package aicore.unit.home;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;

import aicore.pages.function.AddSpecificFunctionPage;
import aicore.pages.home.HomePageUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.AppTestUtils;
import aicore.utils.CommonUtils;
import aicore.utils.ModelTestUtils;
import aicore.utils.TestResourceTrackerHelper;
import aicore.utils.annotations.PWPage;
import aicore.utils.page.app.CreateAppPopupUtils;

public class HomePageSearchTests extends AbstractPlaywrightTestBase {

	@BeforeEach
	void setup(@PWPage Page page) {
		loginNativeAdmin(page);
	}

	@AfterEach
	void tearDown(@PWPage Page page) {
		logout(page);
	}

	@Test
	void testApp(@PWPage Page page) {
		String timestamp = CommonUtils.getTimeStampName();
		String appName = "Test app " + timestamp;
		try {
			AppTestUtils.createApp(page, appName);

			String appNameActual = CreateAppPopupUtils.userFetchAppName(page);
			assertEquals(appName, appNameActual);
			MainMenuUtils.openMainMenu(page);
			MainMenuUtils.clickOnHome(page);

			// search
			HomePageUtils.searchCatalog(page, appName);
			HomePageUtils.selectSearchResultFilterOption(page, appName);
			boolean isCardVisible = HomePageUtils.verifySearchResultIsVisible(page, appName);
			Assertions.assertTrue(isCardVisible, "Searched data is not visible in search result list");
			HomePageUtils.selectSearchResultFilterOption(page, appName);
			HomePageUtils.closeSearchPopup(page);
		} finally {
			// clean up
			CommonUtils.navigateAndDeleteApp(page, appName);
		}

	}

	@Test
	void testModel(@PWPage Page page) {
		String timestamp = CommonUtils.getTimeStampName();
		String modelName = "Test model " + timestamp;

		try {

			String modelId = ModelTestUtils.addModel(page, modelName);

			MainMenuUtils.openMainMenu(page);
			MainMenuUtils.clickOnHome(page);

			// search
			HomePageUtils.searchCatalog(page, modelName);
			HomePageUtils.selectSearchResultFilterOption(page, modelName);
			boolean isCardVisible = HomePageUtils.verifySearchResultIsVisible(page, modelName);
			Assertions.assertTrue(isCardVisible, "Searched data is not visible in search result list");
			HomePageUtils.selectSearchResultFilterOption(page, modelName);
			HomePageUtils.closeSearchPopup(page);
		} finally {
			CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_APP, modelName);
		}
	}

	@Test
	void testFunction(@PWPage Page page) {
		String timestamp = CommonUtils.getTimeStampName();
		String functionName = "Test function " + timestamp;

		try {
			String functionId = AddSpecificFunctionPage.addFunction(page, functionName);

			MainMenuUtils.openMainMenu(page);
			MainMenuUtils.clickOnHome(page);

			// search
			HomePageUtils.searchCatalog(page, functionName);
			HomePageUtils.selectSearchResultFilterOption(page, functionName);
			boolean isCardVisible = HomePageUtils.verifySearchResultIsVisible(page, functionName);
			Assertions.assertTrue(isCardVisible, "Searched data is not visible in search result list");
			HomePageUtils.selectSearchResultFilterOption(page, functionName);
			HomePageUtils.closeSearchPopup(page);
		} finally {
			CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_FUNCTION, functionName);
		}
	}

}
