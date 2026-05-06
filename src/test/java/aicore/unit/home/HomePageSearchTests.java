package aicore.unit.home;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import aicore.pages.home.HomePageUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.utils.AbstractE2ETest;
import aicore.utils.AppTestUtils;
import aicore.utils.CommonUtils;
import aicore.utils.ModelTestUtils;
import aicore.utils.TestResourceTrackerHelper;
import aicore.utils.page.app.CreateAppPopupUtils;

public class HomePageSearchTests extends AbstractE2ETest  {

	@BeforeEach
	public void setup() throws IOException {
		login(page, UserType.NATIVE);
	}

	@AfterEach
	public void teardown() throws IOException {
		logout(page);
	}

	@Test
	void testApp() {
		String timestamp = CommonUtils.getTimeStampName();
		String appName = "Test app " + timestamp;
		AppTestUtils.createApp(page, appName);

		String appNameActual = CreateAppPopupUtils.userFetchAppName(page);
		assertEquals(appName, appNameActual);
		MainMenuUtils.clickOnHome(page);

		// search
		HomePageUtils.searchCatalog(page, appName);
		HomePageUtils.selectSearchResultFilterOption(page, appName);
		boolean isCardVisible = HomePageUtils.verifySearchResultIsVisible(page, appName);
		Assertions.assertTrue(isCardVisible, "Searched data is not visible in search result list");
		HomePageUtils.selectSearchResultFilterOption(page, appName);
		HomePageUtils.closeSearchPopup(page);

		// clean up
		CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_APP, appName);
	}

	@Test
	void testModel() {
		String timestamp = CommonUtils.getTimeStampName();
		String modelName = "Test app " + timestamp;

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
		CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_APP, modelId);
	}

}
