package aicore.utils.page.app;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;

public class CodeAppPageUtils {

	private static final String FILE_UPLOAD_TESTID = "workspace-Files-image";
	private static final String UNZIP_CHECKBOX_TESTID = "CheckBoxOutlineBlankIcon";
	private static final String PUBLISH_BUTTON_XPATH = "//button//*[name()='svg'][contains(@class,'lucide-cloud-up')]";
	private static final String FOLDER_NAME_XPATH = "//span[normalize-space()='{folderName}']";
	private static final String CREATE_NEW_FOLDER_ICON_XPATH = "//*[name()='svg'][@data-testid='CreateNewFolderOutlinedIcon']";
	private static final String FOLDER_NAME_INPUT_XPATH = "//label[text()='Directory Name']/parent::div//input";
	private static final String CREATE_NEW_FILE_ICON_XPATH = "//*[name()='svg'][@data-testid='NoteAddOutlinedIcon']";
	private static final String SUBFOLDERORFILE_XPATH = "//li[@id='/{parentfolder}/{subfolder}/']";
	private static final String EDIT_FILE_XPATH = "//div[@class='view-lines monaco-mouse-cursor-text']";
	private static final String SEE_THE_FILES_IN_FILES_TAB_XPATH = "//div[normalize-space()='Files']";
	private static final String CLICK_ON_THE_FILES_TAB_XPATH = "//img[@alt='Files']";
	private static final String FILES_REFRESH_OPTION_XPATH = "//button//*[name()='svg'][contains(@class,'refresh-cw')]";
	private static final String FILES_RECOMPILE_REACTOR_XPATH = "//button[@aria-label='Recompile reactors']";
	private static Page newTab;
	private static final String SHARE_APP_LINK_XPATH = "//button[@aria-label='Share App']";
	private static final String COPY_BUTTON_XPATH = "//span[normalize-space()='Copy']";
	private static final String EDIT_TITLE_OF_TITLE_SECOND_TEXT_XPATH = "//div[@class='monaco-scrollable-element editor-scrollable vs']//div[@class='view-line']//span//span[@class='mtk1' and contains(normalize-space(.),'Stock')]";
	private static final String EDIT_TITLE_OF_TITLE_FIRST_TEXT_XPATH = "//div[@class='monaco-scrollable-element editor-scrollable vs']//div[@class='view-line']//span//span[@class='mtk1' and contains(normalize-space(.),'Get')]";
	private static final String CODEAPPBOOKMARK_ICON_DATATESTID = "viewAppPage-bookmark-btn";
	private static final String CODE_APP_SETTINGS_TAB = "workspace-Settings-image";
	private static final String FILE_SECTION_IS_DISABLE_XPATH = "//div[contains(@class,'unselected workspace_layout')]//div//img[@alt='Files']";
	private static final String CREATE_AT_ICON_XPATH = "//button//*[name()='svg'][contains(@class,'lucide-file-plus')]";
	private static final String CLICK_TO_SELECT_OPTION_XPATH = "//label[text()='Action']/parent::div//button";
	private static final String SELECT_OPTION_XPATH = "//select//option[text()='{action}']";
	private static final String FILE_UPLOAD_BUTTON_XPATH = "//button[text()='{buttonName}']";
	private static final String CLICK_ON_CREATE_BUTTON_XPATH = "//button[text()='Create']";
	private static final String FILE_NAME_INPUT_XPATH = "//label[text()='File Name']/parent::div//input";
	private static final String CLICK_ON_THREE_DOT_ICON_OF_FILE_XPATH = "//div//span[text()='{fileName}']/parent::div//*[name()='svg'][contains(@class,'lucide-ellipsis')]";
	private static final String SELECT_THE_OPTION_FROM_THREE_DOT_ICON = "//div[text()='{optionName}']";

	public static void clickOnFileUploadButton(Page page) {
		Locator unselectedFile = page.locator(FILE_SECTION_IS_DISABLE_XPATH);
		if (unselectedFile.isVisible()) {
			page.getByTestId(FILE_UPLOAD_TESTID).click();
		}
	}

	public static void clickOnUnzipCheckbox(Page page) {
		page.getByTestId(UNZIP_CHECKBOX_TESTID).first()
				.click(new com.microsoft.playwright.Locator.ClickOptions().setForce(true));
	}

	public static void clickOnPublishButton(Page page) {
		page.locator(PUBLISH_BUTTON_XPATH).click();
	}

	public static boolean userCanSeeFolder(Page page, String folderName) {
		Locator folderLocator = page.locator(FOLDER_NAME_XPATH.replace("{folderName}", folderName));
		return folderLocator.isVisible();
	}

	public static void clickOnCreateNewFolderIcon(Page page) {
		page.locator(CREATE_NEW_FOLDER_ICON_XPATH).click();
	}

	public static void enterFolderName(Page page, String folderName) {
		page.locator(FOLDER_NAME_INPUT_XPATH).first().fill(folderName);

	}

	public static void clickOnCreateButton(Page page) {
		page.locator(CLICK_ON_CREATE_BUTTON_XPATH).click();
	}

	public static void clickOnCreateNewFileIcon(Page page) {
		page.locator(CREATE_NEW_FILE_ICON_XPATH).click();
	}

	public static void enterFileName(Page page, String fileName) {
		page.locator(FILE_NAME_INPUT_XPATH).first().fill(fileName);
	}

	public static boolean userCanSeeFile(Page page, String fileName) {
		Locator fileLocator = page.locator(FOLDER_NAME_XPATH.replace("{folderName}", fileName));
		return fileLocator.isVisible();
	}

	public static void userSelectTheFolder(Page page, String folderName) {
		page.locator(FOLDER_NAME_XPATH.replace("{folderName}", folderName)).click();
	}

	public static void userCanSeeFolderUnderParentFolder(Page page, String folderName, String parentFolderName) {
		Locator folderLocator = page.locator(
				SUBFOLDERORFILE_XPATH.replace("{parentFolder}", parentFolderName).replace("{subFolder}", folderName));
		folderLocator.isVisible();
	}

	public static void userCanSeeFileUnderParentFolder(Page page, String fileName, String parentFolderName) {
		Locator fileLocator = page.locator(
				SUBFOLDERORFILE_XPATH.replace("{parentFolder}", parentFolderName).replace("{subFolder}", fileName));
		fileLocator.isVisible();
	}

	public static void userSelectTheFile(Page page, String fileName) {
		page.locator(FOLDER_NAME_XPATH.replace("{folderName}", fileName)).click();
	}

	public static void userEditFileWithSomeContentAs(Page page, String content) {
		Locator editFile = page.locator(EDIT_FILE_XPATH).first();
		editFile.click();
		editFile.pressSequentially(content);
	}

	public static void userSaveTheFile(Page page) {
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Save").setExact(true)).last().click();
	}

	public static void seeFileTabIsOpenByDefault(Page page) {
		if (!page.locator(SEE_THE_FILES_IN_FILES_TAB_XPATH).first().isVisible()) {
			page.locator(CLICK_ON_THE_FILES_TAB_XPATH).first().click();
		}
	}

	public static boolean isRefreshFilesOptionVisible(Page page) {
		page.locator(FILES_REFRESH_OPTION_XPATH).isVisible();
		return page.locator(FILES_REFRESH_OPTION_XPATH).isEnabled();
	}

	public static void isRefreshFilesOptionClickable(Page page) {
		page.locator(FILES_REFRESH_OPTION_XPATH).click();
	}

	public static boolean isRecompileRefactorOptionVisible(Page page) {
		page.locator(FILES_RECOMPILE_REACTOR_XPATH).isVisible();
		return page.locator(FILES_RECOMPILE_REACTOR_XPATH).isEnabled();
	}

	public static void isRecompileRefactorOptionClickable(Page page) {
		page.locator(FILES_RECOMPILE_REACTOR_XPATH).click();
	}

	public static void clickOnShareAppLink(Page page) {
		page.locator(SHARE_APP_LINK_XPATH).click();
	}

	public static void clickOnCopyButtonForUrl(Page page) {
		Locator copyButton = page.locator(COPY_BUTTON_XPATH).nth(1);
		copyButton.click();
	}

	public static String getCopiedUrl(Page page) {
		return (String) page.evaluate("() => navigator.clipboard.readText()");
	}

	public static void openNewTab(Page page) {
		newTab = page.context().newPage();
	}

	public static void pasteTheUrlOnNewTab(Page page) {
		String copiedUrl = getCopiedUrl(page);
		if (copiedUrl == null || copiedUrl.isEmpty()) {
			throw new RuntimeException("Clipboard is empty so URL was not copied.");
		}
		newTab.navigate(copiedUrl);
		newTab.waitForLoadState(LoadState.DOMCONTENTLOADED);
		newTab.waitForLoadState(LoadState.NETWORKIDLE);
	}

	public static boolean isAppVisibleOnNewTab(String appName) {
		Locator appLocator = newTab.frameLocator("iframe[data-test^='iframe--']").getByRole(AriaRole.HEADING,
				new FrameLocator.GetByRoleOptions().setName(appName));
		appLocator.waitFor();
		return appLocator.isVisible();
	}

	public static void moveToMainPage(Page page) {
		if (newTab != null && !newTab.isClosed()) {
			newTab.close();
		}
		page.bringToFront();
		page.waitForLoadState(LoadState.DOMCONTENTLOADED);
	}

	public static void userEditFileForTitleAsUpdatedContent(Page page, String content) {
		Locator EditTitle = page.locator(EDIT_TITLE_OF_TITLE_SECOND_TEXT_XPATH);
		EditTitle.dblclick();
		page.keyboard().press("Delete");
		page.keyboard().press("Backspace");
		page.locator(EDIT_TITLE_OF_TITLE_FIRST_TEXT_XPATH).dblclick();
		page.keyboard().press("Delete");
		page.keyboard().type(content);
	}

	public static boolean isAppTitleVisible(Page page, String appName) {
		Locator appTitle = page.frameLocator("//iframe[contains(@data-test,'iframe-')]")
				.locator("//h3[normalize-space()='" + appName + "']");
		return appTitle.isVisible();
	}

	public static void clickOnBookmarkAppIcon(Page page) {
		page.getByTestId(CODEAPPBOOKMARK_ICON_DATATESTID).click();
	}

	public static void clickOnSettingsTab(Page page) {
		page.getByTestId(CODE_APP_SETTINGS_TAB).click();
	}

	public static void clickOnGeneralTab(Page page) {
		page.getByLabel("General").click();
	}

	public static void clickOnAppsTab(Page page) {
		page.getByLabel("Apps").click();
	}

	public static void clickOnCreateAtIconOnFileSection(Page page) {
		page.locator(CREATE_AT_ICON_XPATH).click();
	}

	public static void selectActionAs(Page page, String action) {
		page.locator(CLICK_TO_SELECT_OPTION_XPATH).click();
		// page.locator(SELECT_OPTION_XPATH.replace("{action}", action)).click();
		page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(action)).click();
	}

	public static void clickOnUploadButtonToCreateCodeApp(Page page, String buttonName) {
		page.locator(FILE_UPLOAD_BUTTON_XPATH.replace("{buttonName}", buttonName)).click();
	}

	public static void clickOnThreeDotIcon(Page page, String fileName) {
		page.locator(CLICK_ON_THREE_DOT_ICON_OF_FILE_XPATH.replace("{fileName}", fileName)).click();
	}

	public static void userSelectTheOptionFromThreeDotIcon(Page page, String optionName) {
		page.locator(SELECT_THE_OPTION_FROM_THREE_DOT_ICON.replace("{optionName}", optionName)).click();
	}
}
