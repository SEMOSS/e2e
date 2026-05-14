package aicore.unit.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;

import aicore.pages.home.MainMenuUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.CommonUtils;
import aicore.utils.ModelTestUtils;
import aicore.utils.TestResourceTrackerHelper;
import aicore.utils.annotations.PWPage;
import aicore.utils.page.app.AppPageUtils;

public class ModelPageFiltersTests extends AbstractPlaywrightTestBase {
    
    private String modelCatalogNameAsc = null;
    private String modelCatalogNameDesc = null;

    @BeforeEach
    public void setup(@PWPage Page page) {
        // login with admin user before tests
		loginNativeAdmin(page);

        // setup models
        String timestamp = CommonUtils.getTimeStampName();
		modelCatalogNameAsc = "Ascending Model" + timestamp;
        modelCatalogNameDesc = "Descending Model" + timestamp;
		String modelType = "OpenAI";
		String modelName = "GPT-5";
		String openAIKey = "Test@1234";

        // add model one
        ModelTestUtils.addModel(page, modelType, modelName, modelCatalogNameAsc, openAIKey);

        // add model two
        ModelTestUtils.addModel(page, modelType, modelName, modelCatalogNameDesc, openAIKey);

        // need to return back to main engine page
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenModel(page);
    }

    @AfterEach
    public void tearDown(@PWPage Page page) {
        loginNativeAdmin(page);
		// Clean up: delete the test model catalog
		assertTrue(CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_MODEL, modelCatalogNameAsc));
		assertTrue(CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_MODEL, modelCatalogNameDesc));
        logout(page);
    }

    @Test    
    public void testNameSort(@PWPage Page page) {
        AppPageUtils.clickOnFilterButton(page, "Ascending");
		boolean isSortedInAscendingOrder = AppPageUtils.verifySortedInAscendingOrder(page);
		Assertions.assertTrue(isSortedInAscendingOrder, "Models are not sorted in ascending order");

		AppPageUtils.clickOnFilterButton(page, "Descending");
		boolean isSortedInDescendingOrder = AppPageUtils.verifySortedInDescendingOrder(page);
		Assertions.assertTrue(isSortedInDescendingOrder, "Models are not sorted in descending order");
    }

    @Test
    public void testDateCreatedSort(@PWPage Page page) {
		AppPageUtils.selectSortByOption(page, "Date Created");
		AppPageUtils.clickOnFilterButton(page, "Ascending");
		boolean isSortedByDateCreatedAsc = AppPageUtils.verifySortedByDateCreated(page, true);
		Assertions.assertTrue(isSortedByDateCreatedAsc, "Models are not sorted by date created in ascending order");
		
		AppPageUtils.clickOnFilterButton(page, "Descending");
		boolean isSortedByDateCreatedDesc = AppPageUtils.verifySortedByDateCreated(page, false);
		Assertions.assertTrue(isSortedByDateCreatedDesc, "Models are not sorted by date created in descending order");
	}
}
