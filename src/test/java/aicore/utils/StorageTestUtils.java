package aicore.utils;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.microsoft.playwright.Page;

import aicore.pages.home.MainMenuUtils;

public class StorageTestUtils {
	
	private static String catalog = TestResourceTrackerHelper.CATALOG_TYPE_STORAGE;
	
	public static String addDefaultZipUploadStorage(Page page) {
		MainMenuUtils.openMainMenu(page);
		CatalogCreationFromZipUtil.openCatalog(page, catalog);
		AddFunctionPageUtils.deleteCatalog(page, catalog, TestResources.LOCAL_MINIO_NAME);
		CatalogCreationFromZipUtil.clickOnAddCatalogButton(page, catalog);		
		CatalogCreationFromZipUtil.clickOnFileUploadIcon(page);
		FunctionTestUtils.userUploadsFile(page, TestResources.LOCAL_MINIO_ZIP);
		CatalogCreationFromZipUtil.clickOnUploadButton(page, "Upload");
		return CatlogAccessPageUtility.getCatalogAndCopyId(page);
	}

	public static void deleteDefaultZipUploadStorage(Page page) {
		assertTrue(CommonUtils.navigateAndDeleteCatalog(page, catalog, TestResources.LOCAL_MINIO_NAME));
	}
}
