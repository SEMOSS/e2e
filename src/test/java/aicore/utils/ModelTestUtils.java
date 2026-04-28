package aicore.utils;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.microsoft.playwright.Page;

import aicore.hooks.SetupHooks;
import aicore.pages.home.HomePageUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.pages.model.EditModelPageUtils;
import aicore.utils.page.model.ModelPageUtils;

public class ModelTestUtils {
    public static String uploadModelZip(Page page, String modelName, String fileName) {
		// delete model before
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenModel(page);
		AddFunctionPageUtils.deleteCatalog(page, "Model", modelName);

		// click on add model
		HomePageUtils.navigateToHomePage(page);
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenModel(page);
		ModelPageUtils.clickAddModelButton(page);
		assertTrue(AddCatalogPageBaseUtils.isSearchBarPresent(page));


		CatalogCreationFromZipUtil.clickOnFileUploadIcon(page);
		CatalogCreationFromZipUtil.uploadFile(page, fileName);
		CatalogCreationFromZipUtil.clickOnUploadButton(page, "Upload");

        // Wait for page to stabilize
		page.waitForLoadState();
		page.waitForTimeout(500); // Small buffer for any animations

		//return null;
		return getModelID(page, modelName);
	}

    public static String getModelID(Page page, String modelName) {
		HomePageUtils.navigateToHomePage(page);
		MainMenuUtils.openMainMenu(SetupHooks.getPage());
		MainMenuUtils.clickOnOpenModel(SetupHooks.getPage());
		EditModelPageUtils.searchModelCatalog(page, modelName);
		EditModelPageUtils.selectModelFromSearchOptions(page, modelName);
		String modelId = CatlogAccessPageUtility.getCatalogAndCopyId(page);
		return modelId;
	}
}
