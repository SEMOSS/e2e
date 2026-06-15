package aicore.utils;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.microsoft.playwright.Page;

import aicore.pages.home.MainMenuUtils;

public class VectorTestUtils {
	
private static String catalog = TestResourceTrackerHelper.CATALOG_TYPE_VECTOR;
	
	public static String addDefaultZipUploadVector(Page page) {
		MainMenuUtils.openMainMenu(page);
		CatalogCreationFromZipUtil.openCatalog(page, catalog);
		AddFunctionPageUtils.deleteCatalog(page, catalog, TestResources.TEST_VECTOR_NAME);
		CatalogCreationFromZipUtil.clickOnAddCatalogButton(page, catalog);		
		CatalogCreationFromZipUtil.clickOnFileUploadIcon(page);
		FunctionTestUtils.userUploadsFile(page, TestResources.TEST_VECTOR_ZIP);
		CatalogCreationFromZipUtil.clickOnUploadButton(page, "Upload");
		return CatlogAccessPageUtility.getCatalogAndCopyId(page);
	}

	public static void deleteDefaultZipUploadVector(Page page) {
		assertTrue(CommonUtils.navigateAndDeleteCatalog(page, catalog, TestResources.TEST_VECTOR_NAME));
	}

}
