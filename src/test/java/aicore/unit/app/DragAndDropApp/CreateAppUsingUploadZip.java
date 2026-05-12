package aicore.unit.app.DragAndDropApp;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import aicore.pages.home.MainMenuUtils;
import aicore.utils.AbstractE2ETest;
import aicore.utils.CatalogCreationFromZipUtil;
import aicore.utils.TestResources;
import aicore.utils.page.app.AppPageUtils;

public class CreateAppUsingUploadZip extends AbstractE2ETest {

	@BeforeAll
	static void setup() {
		login(page, UserType.NATIVE);
	}

	@Test
	@DisplayName("Create App Using Upload Zip")
	public void testExportDataFunctionality() {
		String fileName = TestResources.APP_FILE;
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenAppLibrary(page);
		AppPageUtils.clickOnCreateNewAppButton(page);
		CatalogCreationFromZipUtil.clickOnFileUploadIconForAPP(page);
		CatalogCreationFromZipUtil.uploadFile(page, fileName);
		// CatalogCreationFromZipUtil.clickOnUploadButton(page, "Upload");

	}

}
