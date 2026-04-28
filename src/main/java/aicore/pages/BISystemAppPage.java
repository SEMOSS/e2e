package aicore.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import aicore.utils.AICorePageUtils;
import aicore.utils.page.app.BISystemAppUtils;

public class BISystemAppPage {

	private Page page;

	// App option

	// insight option
	private static final String INSIGHTS_OPTION_ID = "#home-nav__item--insight";
	
	// catalog option
	private static final String VISUALIZATION_TYPE_OPTION_XPATH = "//span[text()='{type}']";
	private static final String X_AXIS_DROPPABLE_AREA_XPATH = "//span[text()='{axis}']//../../..//ul";
	private static final String FIELD_BUTTON_XPATH = "//div[@title='{fieldName} is a Number']";
	private static final String TOOLS_OPTION_XPATH = "//span[text()='{optionName}']";
	private static final String CHART_TABLE_XPATH = "//div[@role='grid']";

	public BISystemAppPage(Page page) {
		this.page = page;
	}

	/**
	 * This is the old ui and the behavior is inconsistent so wrapping in try/catch
	 * to handle errors
	 */
	public void closeWelcomePopup() {
		BISystemAppUtils.closeWelcomePopup(page);
	}

	public void clickOnAppOption() {
		BISystemAppUtils.clickOnAppOption(page);
	}

	public void clickOnInsightsOption() {
		page.click(INSIGHTS_OPTION_ID);
	}

	public void clickOnCatalogOption() {
		BISystemAppUtils.clickOnCatalogOption(page);
	}

	public void clickOnAddDatabaseButton() {
		BISystemAppUtils.clickOnAddDatabaseButton(page);
	}

	public void selectExcelOption() {
		BISystemAppUtils.selectExcelOption(page);
	}

	public void enterDatabaseName(String databaseName) {
		BISystemAppUtils.enterDatabaseName(page, databaseName);
	}

	public void uploadCSVFile() {
		BISystemAppUtils.uploadCSVFile(page);
	}

	public void uploadExcelFile() {
		BISystemAppUtils.uploadExcelFile(page);
	}

	public void clickOnNextButton() {
		AICorePageUtils.clickOnButton(page, "Next");
	}

	public void clickOnImportButton() {
		AICorePageUtils.clickOnButton(page, "Import");
	}

	public String verifyDBCreatedToastMessage() {
		return BISystemAppUtils.verifyDBCreatedToastMessage(page);
	}

	public void searchDatabaseName(String databaseName) {
		BISystemAppUtils.searchDatabaseName(page, databaseName);
	}

	public void clickOnAddAllOption() {
		BISystemAppUtils.clickOnAddAllOption(page);
	}

	public void clickOnImportButtonOfSelectedColumns() {
		BISystemAppUtils.clickOnImportButtonOfSelectedColumns(page);
	}

	public void mouseHoverOnDatabaseFrame() {
		BISystemAppUtils.mouseHoverOnDatabaseFrame(page);
	}

	public void clickOnVisualizeDataOption() {
		BISystemAppUtils.clickOnVisualizeDataOption(page);
	}

	public void clickOnWorkspaceSaveButton() {
		BISystemAppUtils.clickOnWorkspaceSaveButton(page);
	}

	public void enterInsightName(String insightName) {
		BISystemAppUtils.enterInsightName(page, insightName);
	}

	public void selectProjectName(String projectName) {
		BISystemAppUtils.selectProjectName(page, projectName);
	}

	public String verifySavedInsightSuccessMsg() {
		return BISystemAppUtils.verifySavedInsightSuccessMsg(page);
	}

	public void clickOnNewProjectButton() {
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("New project")).click();
	}

	public void selectVisualizationType(String visualizationType) {
		page.locator(VISUALIZATION_TYPE_OPTION_XPATH.replace("{type}", visualizationType)).click();
	}

	public void dragFieldToXAxis(String fieldName, String axis) {
		page.dragAndDrop(FIELD_BUTTON_XPATH.replace("{fieldName}", fieldName.toUpperCase()),
				X_AXIS_DROPPABLE_AREA_XPATH.replace("{axis}", axis));
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
		page.locator(CHART_TABLE_XPATH).hover();
	}

	public void clickOnPresentationModeOption() {
		page.getByTitle("Toggle Presentation").click();
	}

	public void clickOnSideMenuOption() {
		page.getByTitle("Share or Export").click();
	}
}
