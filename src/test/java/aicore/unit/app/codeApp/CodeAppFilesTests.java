
package aicore.unit.app.codeApp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;

import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.AddFunctionPageUtils;
import aicore.utils.CatalogCreationFromZipUtil;
import aicore.utils.CommonUtils;
import aicore.utils.TestResources;
import aicore.utils.UploadCatalogUtils;
import aicore.utils.annotations.PWPage;
import aicore.utils.annotations.ResourceUploadLock;
import aicore.utils.page.app.TemplateCreationUtils;

public class CodeAppFilesTests extends AbstractPlaywrightTestBase {

	private String testAppName = "";

	@BeforeEach
	void setup(@PWPage Page page) {
		loginNativeAdmin(page);
		testAppName = TemplateCreationUtils.createCodeApp(page);
	}

	@AfterEach
	void tearDown(@PWPage Page page) {
		CommonUtils.navigateAndDeleteApp(page, testAppName);
		logout(page);
	}

	private void openFilesCreateMenu(Page page) {
		UploadCatalogUtils.clickOnFileUploadButton(page);
		UploadCatalogUtils.clickOnCreateAtIconOnFileSection(page);
	}

	private String uploadZipFile(Page page, String fileToUpload) {
		openFilesCreateMenu(page);
		UploadCatalogUtils.selectAction(page, "Upload Files");

		String uploadedFileName = CatalogCreationFromZipUtil.uploadFile(page, fileToUpload);
		String expectedFileName = fileToUpload.substring(fileToUpload.lastIndexOf('/') + 1);
		Assertions.assertEquals(expectedFileName, uploadedFileName, "file is not uploaded successfully");

		UploadCatalogUtils.clickOnUploadButtonToCreateCodeApp(page, "Upload");

		Assertions.assertTrue(UploadCatalogUtils.userCanSeeFolder(page, expectedFileName),
				expectedFileName + " folder is not visible in the Files section");

		return expectedFileName;
	}

	private void assertToastMessage(Page page, String expectedToast) {
		String actualToast = AddFunctionPageUtils.verifySuccessToastMessage(page, expectedToast);
		Assertions.assertEquals(expectedToast, actualToast, "Toaster is not matching with expected");
		AddFunctionPageUtils.closeToastMessage(page);
	}

	private void publishAndAssertToast(Page page, String expectedToast) {
		UploadCatalogUtils.clickOnPublishButton(page);
		assertToastMessage(page, expectedToast);
	}

	@Test
	@ResourceUploadLock(TestResources.MCP_ZIP)
	void testUploadFileAndPublishCodeApp(@PWPage Page page) {
		uploadZipFile(page, TestResources.MCP_ZIP);
		publishAndAssertToast(page, "Successfully compiled and published");
	}

	@Test
	void testCreateDirectoryInFilesSection(@PWPage Page page) {
		openFilesCreateMenu(page);
		UploadCatalogUtils.selectAction(page, "New Directory");
		UploadCatalogUtils.enterFolderName(page, "TestFolder");
		UploadCatalogUtils.clickOnCreateButton(page);

		Assertions.assertTrue(UploadCatalogUtils.userCanSeeFolder(page, "TestFolder"),
				"TestFolder folder is not visible in the Files section");
	}

	@Test
	void testCreateFileInFilesSection(@PWPage Page page) {
		openFilesCreateMenu(page);
		UploadCatalogUtils.selectAction(page, "New File");
		UploadCatalogUtils.enterFileName(page, "TestFile");
		UploadCatalogUtils.clickOnCreateButton(page);

		Assertions.assertTrue(UploadCatalogUtils.userCanSeeFile(page, "TestFile"),
				"TestFile file is not visible in the Files section");

		UploadCatalogUtils.userSelectTheFile(page, "TestFile");
		UploadCatalogUtils.userEditFileWithSomeContentAs(page, "dummydata");
		UploadCatalogUtils.userSaveTheFile(page);
	}

	@Test
	void testCreateFileAndFolderUnderCreatedDirectory(@PWPage Page page) {
		openFilesCreateMenu(page);
		UploadCatalogUtils.selectAction(page, "New Directory");
		UploadCatalogUtils.enterFolderName(page, "TestFolder");
		UploadCatalogUtils.clickOnCreateButton(page);

		Assertions.assertTrue(UploadCatalogUtils.userCanSeeFolder(page, "TestFolder"),
				"TestFolder folder is not visible in the Files section");

		UploadCatalogUtils.userSelectTheFolder(page, "TestFolder");

		UploadCatalogUtils.clickOnCreateAtIconOnFileSection(page);
		UploadCatalogUtils.selectAction(page, "New Directory");
		UploadCatalogUtils.enterFolderName(page, "SubFolder");
		UploadCatalogUtils.clickOnCreateButton(page);
		UploadCatalogUtils.userCanSeeFolderUnderParentFolder(page, "SubFolder", "TestFolder");

		UploadCatalogUtils.clickOnCreateAtIconOnFileSection(page);
		UploadCatalogUtils.selectAction(page, "New File");
		UploadCatalogUtils.enterFileName(page, "SubFile");
		UploadCatalogUtils.clickOnCreateButton(page);
		UploadCatalogUtils.userCanSeeFileUnderParentFolder(page, "SubFile", "TestFolder");
	}

	@Test
	void testRefreshFilesOptionIsEnabledAndClickable(@PWPage Page page) {
		UploadCatalogUtils.clickOnFileUploadButton(page);

		Assertions.assertTrue(UploadCatalogUtils.isRefreshFilesOptionVisible(page),
				"Refresh files option is not visible");

		UploadCatalogUtils.isRefreshFilesOptionClickable(page);
	}

	@Test
	@ResourceUploadLock(TestResources.MCP_ZIP)
	void testPublishCodeAppAndVerifyAccessUsingSharedUrl(@PWPage Page page) {
		uploadZipFile(page, TestResources.MCP_ZIP);
		publishAndAssertToast(page, "Successfully compiled and published");

		UploadCatalogUtils.clickOnShareAppLink(page);
		UploadCatalogUtils.clickOnCopyButtonForUrl(page);
		assertToastMessage(page, "Successfully copied to clipboard");

		UploadCatalogUtils.openNewTab(page);
		UploadCatalogUtils.pasteTheUrlOnNewTab(page);

		Assertions.assertTrue(UploadCatalogUtils.isAppVisibleOnNewTab(testAppName),
				"The app is not visible on the new tab page");

		UploadCatalogUtils.moveToMainPage(page);
	}

	@Test
	@ResourceUploadLock(TestResources.MCP_ZIP)
	void testEditUploadedFileAndVerifyChangesInSharedUrlLink(@PWPage Page page) {
		uploadZipFile(page, TestResources.MCP_ZIP);

		UploadCatalogUtils.clickOnThreeDotIcon(page, "mcp.zip");
		UploadCatalogUtils.userSelectTheOptionFromThreeDotIcon(page, "Unzip");

		Assertions.assertTrue(UploadCatalogUtils.userCanSeeFolder(page, "py"),
				"py folder is not visible in the Files section");

		UploadCatalogUtils.userSelectTheFolder(page, "portals");
		UploadCatalogUtils.userSelectTheFile(page, "index.html");
		UploadCatalogUtils.userEditFileForTitleAsUpdatedContent(page, "Get New Stock Updated");
		UploadCatalogUtils.userSaveTheFile(page);

		publishAndAssertToast(page, "Successfully compiled and published");

		UploadCatalogUtils.clickOnShareAppLink(page);
		UploadCatalogUtils.clickOnCopyButtonForUrl(page);
		assertToastMessage(page, "Copied to clipboard");

		UploadCatalogUtils.openNewTab(page);
		UploadCatalogUtils.pasteTheUrlOnNewTab(page);

		Assertions.assertTrue(UploadCatalogUtils.isAppTitleVisible(page, "Get New Stock Updated"),
				"The updated title is not visible on the new tab page");

		UploadCatalogUtils.moveToMainPage(page);
	}
}