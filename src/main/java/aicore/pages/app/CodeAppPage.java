package aicore.pages.app;

import com.microsoft.playwright.Page;

import aicore.utils.page.app.CodeAppPageUtils;

public class CodeAppPage {
	private Page page;

	public CodeAppPage(Page page) {
		this.page = page;
	}

	public void clickOnFileUploadButton() {
		CodeAppPageUtils.clickOnFileUploadButton(page);
	}

	public void clickOnUnzipCheckbox() {
		CodeAppPageUtils.clickOnUnzipCheckbox(page);
	}

	public void clickOnPublishButton() {
		CodeAppPageUtils.clickOnPublishButton(page);
	}

	public boolean userCanSeeFolder(String folderName) {
		return CodeAppPageUtils.userCanSeeFolder(page, folderName);
	}

	public void clickOnCreateNewFolderIcon() {
		CodeAppPageUtils.clickOnCreateNewFolderIcon(page);
	}

	public void enterFolderName(String folderName) {
		CodeAppPageUtils.enterFolderName(page, folderName);
	}

	public void clickOnCreateButton() {
		CodeAppPageUtils.clickOnCreateButton(page);
	}

	public void clickOnCreateNewFileIcon() {
		CodeAppPageUtils.clickOnCreateNewFileIcon(page);
	}

	public void enterFileName(String fileName) {
		CodeAppPageUtils.enterFileName(page, fileName);
	}

	public boolean userCanSeeFile(String fileName) {
		return CodeAppPageUtils.userCanSeeFile(page, fileName);
	}

	public void userSelectTheFolder(String folderName) {
		CodeAppPageUtils.userSelectTheFolder(page, folderName);
	}

	public void userCanSeeFolderUnderParentFolder(String folderName, String parentFolderName) {
		CodeAppPageUtils.userCanSeeFolderUnderParentFolder(page, folderName, parentFolderName);
	}

	public void userCanSeeFileUnderParentFolder(String fileName, String parentFolderName) {
		CodeAppPageUtils.userCanSeeFileUnderParentFolder(page, fileName, parentFolderName);
	}

	public void userSelectTheFile(String fileName) {
		CodeAppPageUtils.userSelectTheFile(page, fileName);
	}

	public void userEditFileWithSomeContentAs(String content) {
		CodeAppPageUtils.userEditFileWithSomeContentAs(page, content);
	}

	public void userSaveTheFile() {
		CodeAppPageUtils.userSaveTheFile(page);
	}

	public void seeFileTabIsOpenByDefault() {
		CodeAppPageUtils.seeFileTabIsOpenByDefault(page);
	}

	public boolean isRefreshFilesOptionVisible() {
		return CodeAppPageUtils.isRefreshFilesOptionVisible(page);
	}

	public void isRefreshFilesOptionClickable() {
		CodeAppPageUtils.isRefreshFilesOptionClickable(page);
	}

	public boolean isRecompileRefactorOptionVisible() {
		return CodeAppPageUtils.isRecompileRefactorOptionVisible(page);
	}

	public void isRecompileRefactorOptionClickable() {
		CodeAppPageUtils.isRecompileRefactorOptionClickable(page);
	}

	public void clickOnShareAppLink() {
		CodeAppPageUtils.clickOnShareAppLink(page);
	}

	public void clickOnCopyButtonForUrl() {
		CodeAppPageUtils.clickOnCopyButtonForUrl(page);
	}

	public void openNewTab() {
		CodeAppPageUtils.openNewTab(page);
	}

	public void pasteTheUrlOnNewTab() {
		CodeAppPageUtils.pasteTheUrlOnNewTab(page);
	}

	public boolean isAppVisibleOnNewTab(String appName) {
		return CodeAppPageUtils.isAppVisibleOnNewTab(appName);
	}

	public void moveToMainPage() {
		CodeAppPageUtils.moveToMainPage(page);
	}

}
