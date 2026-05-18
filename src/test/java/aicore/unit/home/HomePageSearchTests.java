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
import aicore.utils.DatabaseTestUtils;
import aicore.utils.ModelTestUtils;
import aicore.utils.TestResourceTrackerHelper;
import aicore.utils.TestResources;
import aicore.utils.annotations.PWPage;
import aicore.utils.page.app.CreateAppPopupUtils;

/**
 * Test search page for every catalog type
 */
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

			searchTests(page, appName);
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

			searchTests(page, modelName);
		} finally {
			CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_MODEL, modelName);
		}
	}

	@Test
	void testFunction(@PWPage Page page) {
		String timestamp = CommonUtils.getTimeStampName();
		String functionName = "Test function " + timestamp;

		try {
			String functionId = AddSpecificFunctionPage.addFunction(page, functionName);
			
			searchTests(page, functionId);
		} finally {
			CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_FUNCTION, functionName);
		}
	}

	// TODO
//	@Test
	void testVector(@PWPage Page page) {

	}
	
	// TODO
//	@Test
	void testStorage(@PWPage Page page) {

	}


	@Test
	void testDatabase(@PWPage Page page) {
		String timestamp = CommonUtils.getTimeStampName();
		String dbName = "CSV db" + timestamp;
		String fileName = TestResources.DIABETES_CSV;
		String dbType = "h2";
		String metaModelType = "asFlatTable";
		String dbID = null;
		try {
			dbID = DatabaseTestUtils.addFlatCsv(page, dbName, fileName, dbType, metaModelType);
			searchTests(page, dbID);
		} finally {
			CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_DATABASE, dbID);
		}

	}
	
	private void searchTests(Page page, String catalogName) {
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnHome(page);

		// search
		HomePageUtils.searchCatalog(page, catalogName);
		HomePageUtils.selectSearchResultFilterOption(page, catalogName);
		boolean isCardVisible = HomePageUtils.verifySearchResultIsVisible(page, catalogName);
		Assertions.assertTrue(isCardVisible, "Searched data is not visible in search result list");
		HomePageUtils.selectSearchResultFilterOption(page, catalogName);
		HomePageUtils.closeSearchPopup(page);
	}

}
