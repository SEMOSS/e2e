package aicore.unit.guardrail.add;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import aicore.pages.home.MainMenuUtils;
import aicore.utils.AbstractE2ETest;
import aicore.utils.CatalogCreationFromZipUtil;
import aicore.utils.CatlogAccessPageUtility;
import aicore.utils.CommonUtils;
import aicore.utils.GuardrailPageUtils;
import aicore.utils.TestResourceTrackerHelper;
import aicore.utils.TestResources;

public class AddGuardrailZipTests extends AbstractE2ETest {
	
	private static String GUARDRAIL_ID = null;

	@BeforeAll
	public static void setup() throws IOException {
		// login with native user before tests
		login(page, UserType.NATIVE);
	}
	
	@Test
	void testGlinerGuardrailZip() {
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnGuardrail(page);
		GuardrailPageUtils.clickOnAddGuardrailButton(page);
		CatalogCreationFromZipUtil.clickOnFileUploadIcon(page);
		String fileName = TestResources.GLINER_ZIP;
		String uploadedFileName = CatalogCreationFromZipUtil.uploadFile(page, fileName);;
		if (fileName.contains("/")) {
			String[] ActualFileName = fileName.split("/");
			int fileNameIndex = ActualFileName.length - 1;
			Assertions.assertEquals(ActualFileName[fileNameIndex], uploadedFileName,
					"file is not uploaded successfully");
		} else {
			Assertions.assertEquals(fileName, uploadedFileName, "file is not uploaded successfully");
		}
		CatalogCreationFromZipUtil.clickOnUploadButton(page, "Upload");
		
		GUARDRAIL_ID = CatlogAccessPageUtility.getCatalogAndCopyId(page);

		// TODO toast message
//		String toastMessage = "Successfully added new guardrail to catalog";
//		ModelPageUtils.modelCreationToastMessage(page, toastMessage);

		String catalogName = "Gliner";
		String actualGuardrailTitle = GuardrailPageUtils.verifyGuardrailTitle(page, catalogName);
		Assertions.assertEquals(catalogName, actualGuardrailTitle, "Guardrail title does not match");


	}
	
	@AfterAll
	static void deleteGuardrail() {
		CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_GUARDRAIL, GUARDRAIL_ID);
	}
}
