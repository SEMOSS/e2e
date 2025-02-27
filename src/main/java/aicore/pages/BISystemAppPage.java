package aicore.pages;

import java.nio.file.Paths;

import com.microsoft.playwright.Locator.WaitForOptions;
import com.microsoft.playwright.Page;

import aicore.utils.ConfigUtils;

public class BISystemAppPage {

	private Page page;
	private String timestamp;

	private static final String WELCOME_POPUP_CLOSE_XPATH = "//div[@class='smss-overlay__wrapper__header__times']";

	// App option
	private static final String APP_OPTION_ID = "#home-nav__item--new-insight";

	// insight option
	private static final String INSIGHTS_OPTION_ID = "#home-nav__item--insight";
	private static final String SEARCH_DATABASE_TEXTBOX_XPATH = "//input[@id='smss-search__input' and @placeholder='Search...']";
	private static final String DATABASE_SEARCH_LIST_XPATH = "//span[text()='{DatabaseName}']";
	private static final String ADD_ALL_BUTTON_XPATH = "//button[@title='Add All']";
	private static final String IMPORT_ALL_BUTTON_XPATHS = "//button[@class='smss-btn--outline smss-btn']//span[normalize-space(text())='Import']";
	private static final String DATABASE_FRAME_XPATH = "#pipeline__component__box";
	private static final String VISUALIZE_THIS_DATA_OPTION_XPATH = "//button[@title='Visualize this data']";
	private static final String SAVE_WORKSPACE_BUTTON_XPATH = "//button[@title='Save Workspace']";
	private static final String INSIGHT_NAME_TEXTBOX_ID = "#smss-typeahead__input";
	private static final String PROJECT_NAME_DROPDOWN_XPATH = "//div[@class='smss-dropdown__toggle__text']//div[@class='app-list-dropdown__item__text']";
	private static final String PROJECT_SEARCH_TEXTBOX_XPATH = "//input[@id='smss-search__input' and @placeholder='Search']";
	private static final String PROJECT_SEARCH_LIST_XPATH = "//div[@class='smss-dropdown__list__option__label']";
	private static final String INSIGHT_SAVE_BUTTON_XPATH = "//div[@class='smss-action workspace-save__action']//span[normalize-space(text())='Save']";
	private static final String INSIGHT_SAVE_TOAST_MESSAGE_XPATH = "//div[@class='smss-alert__content smss-alert__content--closable']";

	// catalog option
	private static final String CATALOG_OPTION_ID = "#home-nav__item--catalog";
	private static final String ADD_DATABASE_BUTTON_ID = "#catalog__semoss-tour-import";
	private static final String EXCEL_OPTION_XPATH = "//div[@class='smss-block__text']//span[text()='Excel']";
	private static final String ENTER_DATABASE_NAME_TEXTBOX_XPATH = "//input[@placeholder='Database Name']";
	private static final String UPLOAD_FILE_BUTTON_XPATH = "(//input[@type='file'])[2]";
	private static final String UPLOAD_FILE_PAGE_NEXT_BUTTON_XPATH = "//button[@class='smss-right smss-btn']//span[normalize-space(text())='Next']";
	private static final String IMPORT_BUTTON_XPATH = "//button[@class='smss-right smss-btn']//span[normalize-space(text())='Import']";
	private static final String DATABASE_CREATED_TOAST_MESSAGE_XPATH = "//div[@class='smss-alert__content smss-alert__content--closable']";

	public BISystemAppPage(Page page, String timestamp) {
		this.page = page;
		this.timestamp = timestamp;
	}

	public void closeWelcomePopup() {
		page.click(WELCOME_POPUP_CLOSE_XPATH);
	}

	public void clickOnAppOption() {
		page.click(APP_OPTION_ID);
	}

	public void clickOnInsightsOption() {
		page.click(INSIGHTS_OPTION_ID);
	}

	public void clickOnCatalogOption() {
		page.click(CATALOG_OPTION_ID);
	}

	public void clickOnAddDatabaseButton() {
		page.click(ADD_DATABASE_BUTTON_ID);
	}

	public void selectExcelOption() {
		page.click(EXCEL_OPTION_XPATH);
	}

	public void enterDatabaseName(String databaseName) {
		page.fill(ENTER_DATABASE_NAME_TEXTBOX_XPATH, databaseName + " " + timestamp);
	}

	public void uploadCSVFile() {
		page.setInputFiles(UPLOAD_FILE_BUTTON_XPATH, Paths.get(ConfigUtils.getValue("BIDataImportCSVFile")));
	}

	public void uploadExcelFile() {
		page.setInputFiles(UPLOAD_FILE_BUTTON_XPATH, Paths.get(ConfigUtils.getValue("BIDataImportExcelFile")));
	}

	public void clickOnNextButton() {
		page.click(UPLOAD_FILE_PAGE_NEXT_BUTTON_XPATH);
	}

	public void clickOnImportButton() {
		page.click(IMPORT_BUTTON_XPATH);
		page.waitForTimeout(3000);
	}

	public String verifyDBCreatedToastMessage() {
		String dbSuccessToastMessage = page.textContent(DATABASE_CREATED_TOAST_MESSAGE_XPATH).trim();
		return dbSuccessToastMessage;
	}

	public void searchDatabaseName(String createdDatabaseName) {
		page.fill(SEARCH_DATABASE_TEXTBOX_XPATH, createdDatabaseName + " " + timestamp);
		page.click(DATABASE_SEARCH_LIST_XPATH.replace("{DatabaseName}", createdDatabaseName + " " + timestamp));
	}

	public void clickOnAddAllOption() {
		page.click(ADD_ALL_BUTTON_XPATH);
	}

	public void clickOnImportButtonOfSelectedColumns() {
		page.click(IMPORT_ALL_BUTTON_XPATHS);
	}

	public void mouseHoverOnDatabaseFrame() {
		page.hover(DATABASE_FRAME_XPATH);
	}

	public void clickOnVisualizeDataOption() {
		page.click(VISUALIZE_THIS_DATA_OPTION_XPATH);
	}

	public void clickOnWorkspaceSaveButton() {
		page.click(SAVE_WORKSPACE_BUTTON_XPATH);
	}

	public void enterInsightName() {
		page.fill(INSIGHT_NAME_TEXTBOX_ID, "Test(Automation) " + timestamp);
	}

	public void enterInsightsDetail() {
		page.click(PROJECT_NAME_DROPDOWN_XPATH);
		page.fill(PROJECT_SEARCH_TEXTBOX_XPATH, "Hanumant-Used for Automation");
		page.waitForSelector(PROJECT_SEARCH_LIST_XPATH);
		page.click(PROJECT_SEARCH_LIST_XPATH);
		page.click(INSIGHT_SAVE_BUTTON_XPATH);
	}

	public String verifySavedInsightSuccessMsg() {
		page.locator(INSIGHT_SAVE_TOAST_MESSAGE_XPATH).waitFor(new WaitForOptions().setTimeout(10000));
		String successMessage = page.textContent(INSIGHT_SAVE_TOAST_MESSAGE_XPATH).trim();
		return successMessage;
	}

}
