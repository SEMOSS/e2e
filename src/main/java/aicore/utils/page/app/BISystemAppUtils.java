package aicore.utils.page.app;

import java.nio.file.Paths;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import aicore.framework.AICoreTestConstants;
import aicore.framework.ConfigUtils;
import aicore.utils.AICorePageUtils;

public class BISystemAppUtils {

	private static final String WELCOME_POPUP_CLOSE_XPATH = "//div[@class='smss-overlay__wrapper__header__times']";
	
	// app options
	private static final String APP_OPTION_ID = "#home-nav__item--new-insight";
	
	// catalog options
	private static final String CATALOG_OPTION_ID = "#home-nav__item--catalog";
	private static final String ENTER_DATABASE_NAME_TEXTBOX_XPATH = "//input[@placeholder='Database Name']";
	private static final String UPLOAD_FILE_BUTTON_XPATH = "(//input[@type='file'])[2]";
	private static final String DATABASE_CREATED_TOAST_MESSAGE_XPATH = "//div[@class='smss-alert__content smss-alert__content--closable']";
	private static final String SELECT_STARTING_POINT_TEXT_XPATH = "//span[text()='Select a Starting Point']";


	// insight options
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


	
	public static void closeWelcomePopup(Page page) {
		// accepting browser cookies
		try {
			page.getByText("Accept").click();
		} catch (Exception e) {
		}

		// welcome popup
		try {
			boolean popUp = page.locator(WELCOME_POPUP_CLOSE_XPATH).isVisible();
			if (popUp) {
				page.click(WELCOME_POPUP_CLOSE_XPATH);
			}
		} catch (Exception e) {
			// no popup
		}
	}
	
	public static void clickOnCatalogOption(Page page) {
		page.click(CATALOG_OPTION_ID);
	}
	
	public static void clickOnAddDatabaseButton(Page page) {
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add Database")).click();
	}
	
	public static void enterDatabaseName(Page page, String databaseName, String timestamp) {
		String createdDatabaseName = databaseName + ' ' + timestamp;
		Locator databaseNameTextbox = page.locator(ENTER_DATABASE_NAME_TEXTBOX_XPATH);
		AICorePageUtils.waitFor(databaseNameTextbox);
		databaseNameTextbox.fill(createdDatabaseName);
	}
	
	public static void uploadCSVFile(Page page) {
		page.setInputFiles(UPLOAD_FILE_BUTTON_XPATH,
				Paths.get(ConfigUtils.getValue(AICoreTestConstants.BI_IMPORT_CSV_FILE)));
	}
	
	public static String verifyDBCreatedToastMessage(Page page) {
		Locator toast = page.locator(DATABASE_CREATED_TOAST_MESSAGE_XPATH).first();
		toast.isVisible();
		return toast.textContent().trim();
	}
	
	public static void clickOnAppOption(Page page) {
		page.click(APP_OPTION_ID);
		Locator startingPointText = page.locator(SELECT_STARTING_POINT_TEXT_XPATH);
		AICorePageUtils.waitFor(startingPointText);
		startingPointText.hover();
	}
	
	public static void searchDatabaseName(Page page, String databaseName, String timestamp) {
		String createdDatabaseName = databaseName + ' ' + timestamp;
		page.fill(SEARCH_DATABASE_TEXTBOX_XPATH, createdDatabaseName);
		page.click(DATABASE_SEARCH_LIST_XPATH.replace("{DatabaseName}", createdDatabaseName));
	}
	
	public static void clickOnAddAllOption(Page page) {
		page.click(ADD_ALL_BUTTON_XPATH);
	}
	
	public static void clickOnImportButtonOfSelectedColumns(Page page) {
		page.click(IMPORT_ALL_BUTTON_XPATHS);
	}
	
	public static void mouseHoverOnDatabaseFrame(Page page) {
		page.hover(DATABASE_FRAME_XPATH);
	}
	
	public static void clickOnVisualizeDataOption(Page page) {
		page.click(VISUALIZE_THIS_DATA_OPTION_XPATH);
		page.waitForTimeout(300);
	}
	
	public static void clickOnWorkspaceSaveButton(Page page) {
		page.click(SAVE_WORKSPACE_BUTTON_XPATH);
	}
	
	// createdInsightName does not have " " whitespace separator between the name and timestamp,
	// unlike appNameTesting (in CreateAppPopupUtils.java) and createdDatabaseName (in this file)
	public static void enterInsightName(Page page, String insightName, String timestamp) {
		String createdInsightName = insightName + timestamp;
		page.fill(INSIGHT_NAME_TEXTBOX_ID, createdInsightName);
	}
	
	public static void selectProjectName(Page page, String projectName, String timestamp) {
		page.click(PROJECT_NAME_DROPDOWN_XPATH);
		page.fill(PROJECT_SEARCH_TEXTBOX_XPATH, projectName + " " + timestamp);
		page.waitForSelector(PROJECT_SEARCH_LIST_XPATH);
		page.click(PROJECT_SEARCH_LIST_XPATH);
		page.click(INSIGHT_SAVE_BUTTON_XPATH);
	}
	
	public static String verifySavedInsightSuccessMsg(Page page) {
		Locator toast = page.locator(INSIGHT_SAVE_TOAST_MESSAGE_XPATH).last();
		return toast.textContent().trim();
	}
	
	
}
