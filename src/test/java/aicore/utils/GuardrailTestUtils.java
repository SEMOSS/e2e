package aicore.utils;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.microsoft.playwright.Page;

import aicore.pages.guardrail.AddGuardrailFormUtils;
import aicore.pages.home.MainMenuUtils;

public class GuardrailTestUtils {
private static String catalog = TestResourceTrackerHelper.CATALOG_TYPE_GUARDRAIL;
	
	public static String addDefaultZipUploadGuardrail(Page page) {
		MainMenuUtils.openMainMenu(page);
		CatalogCreationFromZipUtil.openCatalog(page, catalog);
		AddFunctionPageUtils.deleteCatalog(page, catalog, TestResources.GLINER_NAME);
		CatalogCreationFromZipUtil.clickOnAddCatalogButton(page, catalog);		
		CatalogCreationFromZipUtil.clickOnFileUploadIcon(page);
		FunctionTestUtils.userUploadsFile(page, TestResources.GLINER_ZIP);
		CatalogCreationFromZipUtil.clickOnUploadButton(page, "Upload");
		return CatlogAccessPageUtility.getCatalogAndCopyId(page);
	}

	public static void deleteDefaultZipUploadGuardrail(Page page) {
		assertTrue(CommonUtils.navigateAndDeleteCatalog(page, catalog, TestResources.GLINER_NAME));
	}

	public static String createGlinerGuardrail(Page page, String catalogName) {
		String modelNameType = "Gliner";
		String nerLabel = "label";
		String threshold = "1";
		return AddGuardrailFormUtils.createGuardrail(page, modelNameType, catalogName, nerLabel, threshold);
	}
}
