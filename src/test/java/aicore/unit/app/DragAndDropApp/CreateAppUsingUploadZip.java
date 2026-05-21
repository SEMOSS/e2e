package aicore.unit.app.DragAndDropApp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;

import aicore.pages.home.MainMenuUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.CatalogCreationFromZipUtil;
import aicore.utils.CommonUtils;
import aicore.utils.TestResources;
import aicore.utils.annotations.PWPage;
import aicore.utils.page.app.AppPageUtils;
import aicore.utils.page.app.CreateAppPopupUtils;
import aicore.utils.page.app.DragAndDropBlocksPageUtils;

public class CreateAppUsingUploadZip extends AbstractPlaywrightTestBase {

	@BeforeAll
	void setup(@PWPage Page page) {
		loginNativeAdmin(page);
	}

	@Test
	@DisplayName("Create App Using Upload Zip")
	public void testExportDataFunctionality(@PWPage Page page) {
		String fileName = TestResources.APP_FILE;
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenAppLibrary(page);
		AppPageUtils.clickOnCreateNewAppButton(page);
		CatalogCreationFromZipUtil.clickOnFileUploadIconForAPP(page);
		CatalogCreationFromZipUtil.uploadFile(page, fileName);
		CatalogCreationFromZipUtil.clickOnNextButton(page);
		CatalogCreationFromZipUtil.clickOnNextButton(page);
		CatalogCreationFromZipUtil.clickOnUploadButton(page);
		boolean isPage1Visible = DragAndDropBlocksPageUtils.verifyPage1IsVisible(page);
		Assertions.assertTrue(isPage1Visible, "Page is not visible so App creation failed");
		CreateAppPopupUtils.userFetchAppName(page);
		CommonUtils.navigateAndDeleteApp(page, "CreateDiabetesRecord");
	}

}
