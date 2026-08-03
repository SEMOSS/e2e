package aicore.unit.app.codeApp;

import aicore.pages.home.MainMenuUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.page.app.AppPageUtils;
import aicore.utils.page.app.CreateAppPopupUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;
import aicore.utils.AddFunctionPageUtils;
import aicore.utils.CatalogCreationFromZipUtil;
import aicore.utils.CommonUtils;
import aicore.utils.UploadCatalogUtils;
import aicore.utils.annotations.PWPage;


public class CodeAppFilesTests extends AbstractPlaywrightTestBase{
	
	private String testAppName = "";

	@BeforeEach
	void setup(@PWPage Page page) {
		String timestamp = CommonUtils.getTimeStampName();
		testAppName = "Code app " + timestamp;

		loginNativeAdmin(page);

		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenAppLibrary(page);
		AppPageUtils.clickOnCreateNewAppButton(page);
		CreateAppPopupUtils.clickOnGetStartedButton(page, "Develop in code");
		CreateAppPopupUtils.enterAppName(page, testAppName);
		CreateAppPopupUtils.enterAppDescription(page, "Created by automation script");
		CreateAppPopupUtils.enterTags(page, "MCP");
		CreateAppPopupUtils.clickOnCreateButton(page);
		String fetchName = CreateAppPopupUtils.userFetchAppName(page);
		Assertions.assertFalse(fetchName.isEmpty(), "Fetched App Name is Empty");
	}

	@AfterEach
	void tearDown(@PWPage Page page) {
		CommonUtils.navigateAndDeleteApp(page, testAppName);
		logout(page);
	}


	@Test
	void testUploadFileAndPublishCodeApp(@PWPage Page page) {
		String fileToUpload = "Playground/mcp.zip";

		UploadCatalogUtils.clickOnFileUploadButton(page);
		UploadCatalogUtils.clickOnCreateAtIconOnFileSection(page);
		UploadCatalogUtils.selectAction(page, "Upload Files");

		String uploadedFileName = CatalogCreationFromZipUtil.uploadFile(page, fileToUpload);
		String[] fileParts = fileToUpload.split("/");
		String expectedFileName = fileParts[fileParts.length - 1];
		Assertions.assertEquals(expectedFileName, uploadedFileName, "file is not uploaded successfully");

		UploadCatalogUtils.clickOnUploadButtonToCreateCodeApp(page, "Upload");

		boolean isFolderVisible = UploadCatalogUtils.userCanSeeFolder(page, "mcp.zip");
		Assertions.assertTrue(isFolderVisible, "mcp.zip folder is not visible in the Files section");

		UploadCatalogUtils.clickOnPublishButton(page);

		String expectedToast = "Successfully compiled and published";
		String actualToast = AddFunctionPageUtils.verifySuccessToastMessage(page, expectedToast);
		Assertions.assertEquals(expectedToast, actualToast, "Toaster is not matching with expected");
		AddFunctionPageUtils.closeToastMessage(page);
	}

	
	@Test
	void testCreateDirectoryInFilesSection(@PWPage Page page) {
		UploadCatalogUtils.clickOnFileUploadButton(page);
		UploadCatalogUtils.clickOnCreateAtIconOnFileSection(page);
		UploadCatalogUtils.selectAction(page, "New Directory");
		UploadCatalogUtils.enterFolderName(page, "TestFolder");
		UploadCatalogUtils.clickOnCreateButton(page);

		boolean isFolderVisible = UploadCatalogUtils.userCanSeeFolder(page, "TestFolder");
		Assertions.assertTrue(isFolderVisible, "TestFolder folder is not visible in the Files section");
	}

	
	@Test
	void testCreateFileInFilesSection(@PWPage Page page) {
		UploadCatalogUtils.clickOnFileUploadButton(page);
		UploadCatalogUtils.clickOnCreateAtIconOnFileSection(page);
		UploadCatalogUtils.selectAction(page, "New File");
		UploadCatalogUtils.enterFileName(page, "TestFile");
		UploadCatalogUtils.clickOnCreateButton(page);

		boolean isFileVisible = UploadCatalogUtils.userCanSeeFile(page, "TestFile");
		Assertions.assertTrue(isFileVisible, "TestFile file is not visible in the Files section");

		UploadCatalogUtils.userSelectTheFile(page, "TestFile");
		UploadCatalogUtils.userEditFileWithSomeContentAs(page, "dummydata");
		UploadCatalogUtils.userSaveTheFile(page);
	}

	
	@Test
	void testCreateFileAndFolderUnderCreatedDirectory(@PWPage Page page) {
		UploadCatalogUtils.clickOnFileUploadButton(page);
		UploadCatalogUtils.clickOnCreateAtIconOnFileSection(page);
		UploadCatalogUtils.selectAction(page, "New Directory");
		UploadCatalogUtils.enterFolderName(page, "TestFolder");
		UploadCatalogUtils.clickOnCreateButton(page);

		boolean isFolderVisible = UploadCatalogUtils.userCanSeeFolder(page, "TestFolder");
		Assertions.assertTrue(isFolderVisible, "TestFolder folder is not visible in the Files section");

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

		boolean isVisible = UploadCatalogUtils.isRefreshFilesOptionVisible(page);
		Assertions.assertTrue(isVisible, "Refresh files option is not visible");

		UploadCatalogUtils.isRefreshFilesOptionClickable(page);
	}

	
	@Test
	void testPublishCodeAppAndVerifyAccessUsingSharedUrl(@PWPage Page page) {
		String fileToUpload = "Playground/mcp.zip";

		UploadCatalogUtils.clickOnFileUploadButton(page);
		UploadCatalogUtils.clickOnCreateAtIconOnFileSection(page);
		UploadCatalogUtils.selectAction(page, "Upload Files");

		String uploadedFileName = CatalogCreationFromZipUtil.uploadFile(page, fileToUpload);
		Assertions.assertEquals("mcp.zip", uploadedFileName, "file is not uploaded successfully");

		UploadCatalogUtils.clickOnUploadButtonToCreateCodeApp(page, "Upload");

		boolean isFolderVisible = UploadCatalogUtils.userCanSeeFolder(page, "mcp.zip");
		Assertions.assertTrue(isFolderVisible, "mcp.zip folder is not visible in the Files section");

		UploadCatalogUtils.clickOnPublishButton(page);

		String expectedPublishToast = "Successfully compiled and published";
		String actualPublishToast = AddFunctionPageUtils.verifySuccessToastMessage(page, expectedPublishToast);
		Assertions.assertEquals(expectedPublishToast, actualPublishToast, "Toaster is not matching with expected");
		AddFunctionPageUtils.closeToastMessage(page);

		UploadCatalogUtils.clickOnShareAppLink(page);
		UploadCatalogUtils.clickOnCopyButtonForUrl(page);

		String expectedCopyToast = "Successfully copied to clipboard";
		String actualCopyToast = AddFunctionPageUtils.verifySuccessToastMessage(page, expectedCopyToast);
		Assertions.assertEquals(expectedCopyToast, actualCopyToast, "Toaster is not matching with expected");
		AddFunctionPageUtils.closeToastMessage(page);

		UploadCatalogUtils.openNewTab(page);
		UploadCatalogUtils.pasteTheUrlOnNewTab(page);

		boolean isAppVisible = UploadCatalogUtils.isAppVisibleOnNewTab(testAppName);
		Assertions.assertTrue(isAppVisible, "The app is not visible on the new tab page");

		UploadCatalogUtils.moveToMainPage(page);
	}

	@Test
	void testEditUploadedFileAndVerifyChangesInSharedUrlLink(@PWPage Page page) {
		String fileToUpload = "Playground/mcp.zip";

		UploadCatalogUtils.clickOnFileUploadButton(page);
		UploadCatalogUtils.clickOnCreateAtIconOnFileSection(page);
		UploadCatalogUtils.selectAction(page, "Upload Files");

		String uploadedFileName = CatalogCreationFromZipUtil.uploadFile(page, fileToUpload);
		Assertions.assertEquals("mcp.zip", uploadedFileName, "file is not uploaded successfully");

		UploadCatalogUtils.clickOnUploadButtonToCreateCodeApp(page, "Upload");

		boolean isZipVisible = UploadCatalogUtils.userCanSeeFolder(page, "mcp.zip");
		Assertions.assertTrue(isZipVisible, "mcp.zip folder is not visible in the Files section");

		UploadCatalogUtils.clickOnThreeDotIcon(page, "mcp.zip");
		UploadCatalogUtils.userSelectTheOptionFromThreeDotIcon(page, "Unzip");

		boolean isPyVisible = UploadCatalogUtils.userCanSeeFolder(page, "py");
		Assertions.assertTrue(isPyVisible, "py folder is not visible in the Files section");

		UploadCatalogUtils.userSelectTheFolder(page, "portals");
		UploadCatalogUtils.userSelectTheFile(page, "index.html");
		UploadCatalogUtils.userEditFileForTitleAsUpdatedContent(page, "Get New Stock Updated");
		UploadCatalogUtils.userSaveTheFile(page);

		UploadCatalogUtils.clickOnPublishButton(page);

		String expectedPublishToast = "Successfully compiled and published";
		String actualPublishToast = AddFunctionPageUtils.verifySuccessToastMessage(page, expectedPublishToast);
		Assertions.assertEquals(expectedPublishToast, actualPublishToast, "Toaster is not matching with expected");
		AddFunctionPageUtils.closeToastMessage(page);

		UploadCatalogUtils.clickOnShareAppLink(page);
		UploadCatalogUtils.clickOnCopyButtonForUrl(page);

		
		String expectedCopyToast = "Copied to clipboard";
		String actualCopyToast = AddFunctionPageUtils.verifySuccessToastMessage(page, expectedCopyToast);
		Assertions.assertEquals(expectedCopyToast, actualCopyToast, "Toaster is not matching with expected");
		AddFunctionPageUtils.closeToastMessage(page);

		UploadCatalogUtils.openNewTab(page);
		UploadCatalogUtils.pasteTheUrlOnNewTab(page);

		boolean isTitleVisible = UploadCatalogUtils.isAppTitleVisible(page, "Get New Stock Updated");
		Assertions.assertTrue(isTitleVisible, "The updated title is not visible on the new tab page");

		UploadCatalogUtils.moveToMainPage(page);
	}

}
