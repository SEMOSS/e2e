package aicore.pages;

import java.nio.file.Paths;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Locator.WaitForOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import aicore.utils.AICorePageUtils;
import aicore.framework.ConfigUtils;

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
	private static final String EXCEL_OPTION_XPATH = "//div[@class='smss-block__text']//span[text()='Excel']";
	private static final String ENTER_DATABASE_NAME_TEXTBOX_XPATH = "//input[@placeholder='Database Name']";
	private static final String UPLOAD_FILE_BUTTON_XPATH = "(//input[@type='file'])[2]";
	private static final String DATABASE_CREATED_TOAST_MESSAGE_XPATH = "//div[@class='smss-alert__content smss-alert__content--closable']";
	private static final String VISUALIZATION_TYPE_OPTION_XPATH = "//span[text()='{type}']";
	private static final String X_AXIS_DROPPABLE_AREA_XPATH = "//span[text()='{axis}']//../../..//ul";
	private static final String FIELD_BUTTON_XPATH = "//div[@title='{fieldName} is a Number']";
	private static final String TOOLS_OPTION_XPATH = "//span[text()='{optionName}']";

	public BISystemAppPage(Page page, String timestamp) {
		this.page = page;
		this.timestamp = timestamp;
	}

	/**
	 * This is the old ui and the behavior is inconsistent so wrapping in try/catch
	 * to handle errors
	 */
	public void closeWelcomePopup() {
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
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add Database")).click();
	}

	public void selectExcelOption() {
		page.click(EXCEL_OPTION_XPATH);
	}

	public void enterDatabaseName(String databaseName) {
		Locator databaseNameTextbox = page.locator(ENTER_DATABASE_NAME_TEXTBOX_XPATH);
		AICorePageUtils.waitFor(databaseNameTextbox);
		databaseNameTextbox.fill(databaseName);
	}

	public void uploadCSVFile() {
		page.setInputFiles(UPLOAD_FILE_BUTTON_XPATH, Paths.get(ConfigUtils.getValue("BIDataImportCSVFile")));
	}

	public void uploadExcelFile() {
		page.setInputFiles(UPLOAD_FILE_BUTTON_XPATH, Paths.get(ConfigUtils.getValue("BIDataImportExcelFile")));
	}

	public void clickOnNextButton() {
		AICorePageUtils.clickOnButton(page, "Next");
	}

	public void clickOnImportButton() {
		AICorePageUtils.clickOnButton(page, "Import");
	}

	public String verifyDBCreatedToastMessage() {
		Locator toast = page.locator(DATABASE_CREATED_TOAST_MESSAGE_XPATH).first();
		toast.isVisible();
	    return toast.textContent().trim();
		
	}

	public void searchDatabaseName(String createdDatabaseName) {
		page.fill(SEARCH_DATABASE_TEXTBOX_XPATH, createdDatabaseName);
		page.click(DATABASE_SEARCH_LIST_XPATH.replace("{DatabaseName}", createdDatabaseName));
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

	public void enterInsightName(String insightName) {
		page.fill(INSIGHT_NAME_TEXTBOX_ID, insightName + timestamp);
	}

	public void selectProjectName(String projectName) {
		page.click(PROJECT_NAME_DROPDOWN_XPATH);
		page.fill(PROJECT_SEARCH_TEXTBOX_XPATH, projectName + " " + timestamp);
		page.waitForSelector(PROJECT_SEARCH_LIST_XPATH);
		page.click(PROJECT_SEARCH_LIST_XPATH);
		page.click(INSIGHT_SAVE_BUTTON_XPATH);
	}

	public String verifySavedInsightSuccessMsg() {
		Locator toast = page.locator(INSIGHT_SAVE_TOAST_MESSAGE_XPATH).last();
		return toast.textContent().trim();
	}

	public void clickOnNewProjectButton() {
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("New project")).click();
	}

	public void selectVisualizationType(String visualizationType) {
		page.locator(VISUALIZATION_TYPE_OPTION_XPATH.replace("{type}", visualizationType)).click();
	}

	public void dragFieldToXAxis(String fieldName, String axis) {
		page.dragAndDrop(FIELD_BUTTON_XPATH.replace("{fieldName}", fieldName), X_AXIS_DROPPABLE_AREA_XPATH.replace("{axis}", axis));
	}

	public void clickOnToolsOption() {
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("TOOLS")).isVisible();
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("TOOLS")).click();
	}

	public void selectToolOption(String toolOption) {
		page.click(TOOLS_OPTION_XPATH.replace("{optionName}", toolOption));
	}

	public void hoverAddPanelAndSelectAddChart(String panelType) {
		page.getByTitle("Add Panel").hover();
		page.getByTitle(panelType).click();
	}

	public void clickOnPresentationModeOption() {
		page.getByTitle("Toggle Presentation").click();
	}

	public void clickOnSideMenuOption() {
		page.getByTitle("Share or Export").click();
	}
}
