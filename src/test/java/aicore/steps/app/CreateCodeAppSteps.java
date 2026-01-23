package aicore.steps.app;

import org.junit.jupiter.api.Assertions;

import aicore.hooks.SetupHooks;
import aicore.pages.app.CodeAppPage;
import io.cucumber.java.en.And;

public class CreateCodeAppSteps {

	private CodeAppPage codeAppPage;

	public CreateCodeAppSteps() {
		this.codeAppPage = new CodeAppPage(SetupHooks.getPage());
	}

	@And("User clicks on the file icon in the left panel")
	public void user_Clicks_On_The_File_Icon_In_The_Left_Panel() {
		codeAppPage.clickOnFileUploadButton();
	}

	@And("User selects the unzip checkbox")
	public void user_Selects_The_Unzip_Checkbox() {
		codeAppPage.clickOnUnzipCheckbox();
	}

	@And("User clicks on the publish icon to publish the code app")
	public void user_Clicks_On_The_Publish_Icon_To_Publish_The_Code_App() {
		codeAppPage.clickOnPublishButton();
	}

	@And("User can see the {string} folder in the Files section")
	public void user_can_see_the_folder(String folderName) {
		codeAppPage.userCanSeeFolder(folderName);
		boolean isFolderVisible = codeAppPage.userCanSeeFolder(folderName);
		Assertions.assertTrue(isFolderVisible, "Default folder is not visible");
	}

	@And("User clicks on Create New Folder icon")
	public void user_Clicks_On_Create_New_Folder_Icon() {
		codeAppPage.clickOnCreateNewFolderIcon();
	}

	@And("User enter the folder name as {string}")
	public void user_Enter_The_Folder_Name_As(String folderName) {
		codeAppPage.enterFolderName(folderName);
	}

	@And("User click on Create button")
	public void click_On_Create_Button() {
		codeAppPage.clickOnCreateButton();
	}

	@And("User clicks on Create New File icon")
	public void user_Clicks_On_Create_New_File_Icon() {
		codeAppPage.clickOnCreateNewFileIcon();
	}

	@And("User enter the file name as {string}")
	public void user_Enter_The_File_Name_As(String fileName) {
		codeAppPage.enterFileName(fileName);
	}

	@And("User can see the {string} file in the Files section")
	public void user_can_see_the_file(String fileName) {
		codeAppPage.userCanSeeFile(fileName);
		boolean isFileVisible = codeAppPage.userCanSeeFile(fileName);
		Assertions.assertTrue(isFileVisible, "Default file is not visible");
	}

	@And("User clicks on the {string} folder in the Files section")
	public void user_Clicks_On_The_Folder_In_The_Files_Section(String folderName) {
		codeAppPage.userCanSeeFolder(folderName);
		codeAppPage.userSelectTheFolder(folderName);
	}

	@And("User can see the {string} folder under {string} in the Files section")
	public void user_Can_See_The_Folder_Under_Folder(String folderName, String parentFolderName) {
		codeAppPage.userCanSeeFolderUnderParentFolder(folderName, parentFolderName);
	}

	@And("User can see the {string} file under {string} in the Files section")
	public void user_Can_See_The_File_Under_Folder(String fileName, String parentFolderName) {
		codeAppPage.userCanSeeFileUnderParentFolder(fileName, parentFolderName);
	}

	@And("User click on the created {string} file")
	public void user_Click_On_The_Created_File(String fileName) {
		codeAppPage.userCanSeeFile(fileName);
		codeAppPage.userSelectTheFile(fileName);
	}

	@And("User Edit File with some content as {string}")
	public void user_Edit_File_With_Some_Content_As(String content) {
		codeAppPage.userEditFileWithSomeContentAs(content);
	}

	@And("User Save the file")
	public void user_Save_The_File() {
		codeAppPage.userSaveTheFile();
	}

	@And("The Files section should be open by default")
	public void the_Files_Section_Should_Be_Open_By_Default() {
		codeAppPage.seeFileTabIsOpenByDefault();
	}

	@And("The Refresh Files option should be visible")
	public void the_Refresh_Files_Option_Should_Be_Visible() {
		boolean isenabled = codeAppPage.isRefreshFilesOptionVisible();
		Assertions.assertTrue(isenabled, "Refresh files option is not visible");
	}

	@And("The Refresh Files option should be clickable")
	public void the_Refresh_Files_Option_Should_Be_Clickable() {
		codeAppPage.isRefreshFilesOptionClickable();
	}

	@And("The Recompile Refactor option should be visible")
	public void the_Recompile_Refactor_Option_Should_Be_Visible() {
		boolean isenabled = codeAppPage.isRecompileRefactorOptionVisible();
		Assertions.assertTrue(isenabled, "Recompile Refactor option is not visible");
	}

	@And("User click on the  Recompile Refactor option")
	public void user_Click_On_The_Recompile_Refactor_Option() {
		codeAppPage.isRecompileRefactorOptionClickable();
	}

	@And("User click on Share App link")
	public void user_click_on_share_app_link() {
		codeAppPage.clickOnShareAppLink();
	}

	@And("User open the new tab")
	public void user_open_the_new_tab() {
		codeAppPage.openNewTab();
	}

	@And("User click on Copy button for Url")
	public void user_click_on_copy_button_for_url() {
		codeAppPage.clickOnCopyButtonForUrl();
	}

	@And("User paste the URl on new tab")
	public void user_paste_the_url_on_new_tab() {
		codeAppPage.pasteTheUrlOnNewTab();
	}

	@And("User able to see the {string} title on the new tab page")
	public void user_able_to_see_the_on_the_new_tab_page(String appName) {
		boolean isAppVisible = codeAppPage.isAppVisibleOnNewTab(appName);
		Assertions.assertTrue(isAppVisible, "The app is not visible on the new tab page");
	}

	@And("User move to main page")
	public void userMoveToMainPage() {
		codeAppPage.moveToMainPage();
	}

	@And("User edit file for change title as {string}")
	public void user_Edit_File_For_Change_Title_As(String content) {
		codeAppPage.userEditFileForTitleAsUpdatedContent(content);
	}

	@And("User able to see the {string} title on the page")
	public void user_able_to_see_the_title_on_the_page(String appName) {
		boolean isAppVisible = codeAppPage.isAppTitleVisible(appName);
		Assertions.assertTrue(isAppVisible, "The app Title is not visible on the page");
	}

	@And("User click on the Bookmark App icon")
	public void user_click_on_the_bookmark_app_icon() {
		codeAppPage.clickOnBookmarkAppIcon();
	}

}