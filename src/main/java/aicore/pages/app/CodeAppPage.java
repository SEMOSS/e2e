package aicore.pages.app;

import com.microsoft.playwright.Page;

import aicore.utils.UploadCatalogUtils;

public class CodeAppPage {
	private Page page;

	public CodeAppPage(Page page) {
		this.page = page;
	}

	public void clickOnFileUploadButton() {
		UploadCatalogUtils.clickOnFileUploadButton(page);
	}

	public void clickOnUnzipCheckbox() {
		UploadCatalogUtils.clickOnUnzipCheckbox(page);
	}

	public void clickOnPublishButton() {
		UploadCatalogUtils.clickOnPublishButton(page);
	}

	public boolean userCanSeeFolder(String folderName) {
		return UploadCatalogUtils.userCanSeeFolder(page, folderName);
	}

	public void clickOnCreateNewFolderIcon() {
		UploadCatalogUtils.clickOnCreateNewFolderIcon(page);
	}

	public void enterFolderName(String folderName) {
		UploadCatalogUtils.enterFolderName(page, folderName);
	}

	public void clickOnCreateButton() {
		UploadCatalogUtils.clickOnCreateButton(page);
	}

	public void clickOnCreateNewFileIcon() {
		UploadCatalogUtils.clickOnCreateNewFileIcon(page);
	}

	public void enterFileName(String fileName) {
		UploadCatalogUtils.enterFileName(page, fileName);
	}

	public boolean userCanSeeFile(String fileName) {
		return UploadCatalogUtils.userCanSeeFile(page, fileName);
	}

	public void userSelectTheFolder(String folderName) {
		UploadCatalogUtils.userSelectTheFolder(page, folderName);
	}

	public void userCanSeeFolderUnderParentFolder(String folderName, String parentFolderName) {
		UploadCatalogUtils.userCanSeeFolderUnderParentFolder(page, folderName, parentFolderName);
	}

	public void userCanSeeFileUnderParentFolder(String fileName, String parentFolderName) {
		UploadCatalogUtils.userCanSeeFileUnderParentFolder(page, fileName, parentFolderName);
	}

	public void userSelectTheFile(String fileName) {
		UploadCatalogUtils.userSelectTheFile(page, fileName);
	}

	public void userEditFileWithSomeContentAs(String content) {
		UploadCatalogUtils.userEditFileWithSomeContentAs(page, content);
	}

	public void userSaveTheFile() {
		UploadCatalogUtils.userSaveTheFile(page);
	}

	public void seeFileTabIsOpenByDefault() {
		UploadCatalogUtils.seeFileTabIsOpenByDefault(page);
	}

	public boolean isRefreshFilesOptionVisible() {
		return UploadCatalogUtils.isRefreshFilesOptionVisible(page);
	}

	public void isRefreshFilesOptionClickable() {
		UploadCatalogUtils.isRefreshFilesOptionClickable(page);
	}

	public boolean isRecompileRefactorOptionVisible() {
		return UploadCatalogUtils.isRecompileRefactorOptionVisible(page);
	}

	public void isRecompileRefactorOptionClickable() {
		UploadCatalogUtils.isRecompileRefactorOptionClickable(page);
	}

	public void clickOnShareAppLink() {
		UploadCatalogUtils.clickOnShareAppLink(page);
	}

	public void clickOnCopyButtonForUrl() {
		UploadCatalogUtils.clickOnCopyButtonForUrl(page);
	}

	public void openNewTab() {
		UploadCatalogUtils.openNewTab(page);
	}

	public void pasteTheUrlOnNewTab() {
		UploadCatalogUtils.pasteTheUrlOnNewTab(page);
	}

	public boolean isAppVisibleOnNewTab(String appName) {
		return UploadCatalogUtils.isAppVisibleOnNewTab(appName);
	}

	public void moveToMainPage() {
		UploadCatalogUtils.moveToMainPage(page);
	}

	public void userEditFileForTitleAsUpdatedContent(String content) {
		UploadCatalogUtils.userEditFileForTitleAsUpdatedContent(page, content);
	}

	public boolean isAppTitleVisible(String appName) {
		return UploadCatalogUtils.isAppTitleVisible(page, appName);
	}

	public void clickOnBookmarkAppIcon() {
		UploadCatalogUtils.clickOnBookmarkAppIcon(page);
	}

	public void clickOnSettingsTab() {
		UploadCatalogUtils.clickOnSettingsTab(page);
	}

	public void clickOnGeneralTab() {
		UploadCatalogUtils.clickOnGeneralTab(page);
	}

	public void clickOnAppsTab() {
		UploadCatalogUtils.clickOnAppsTab(page);
	}

	public void clickOnCreateAtIconOnFileSection() {
		UploadCatalogUtils.clickOnCreateAtIconOnFileSection(page);
	}

	public void selectAction(String action) {
		UploadCatalogUtils.selectAction(page, action);
	}

	public void clickOnUploadButtonToCreateCodeApp(String buttonName) {
		UploadCatalogUtils.clickOnUploadButtonToCreateCodeApp(page, buttonName);
	}

	public void clickOnThreeDotIcon(String fileName) {
		UploadCatalogUtils.clickOnThreeDotIcon(page, fileName);
	}

	public void userSelectTheOptionFromThreeDotIcon(String optionName) {
		UploadCatalogUtils.userSelectTheOptionFromThreeDotIcon(page, optionName);
	}
}
