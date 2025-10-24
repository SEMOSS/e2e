package aicore.steps;

import static org.junit.Assert.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import aicore.hooks.SetupHooks;
import aicore.pages.BISystemAppPage;
import aicore.pages.HomePage;
import aicore.steps.app.CreateAppUsingDragAndDropSteps;
import aicore.utils.CommonUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BICreateDatabaseAndInsightSteps {
	private static final Logger logger = LogManager.getLogger(BICreateDatabaseAndInsightSteps.class);

	private HomePage homePage;
	private BISystemAppPage biApp;
	protected static String timestamp;

	public BICreateDatabaseAndInsightSteps() {
		homePage = new HomePage(SetupHooks.getPage());
		timestamp = SetupHooks.getTimestamp();
		biApp = new BISystemAppPage(SetupHooks.getPage(), CreateAppUsingDragAndDropSteps.timestamp);
	}

	@And("User clicks on System app")
	public void user_clicks_on_system_app() {
		homePage.clickOnSystemApp();
	}

	@And("User clicks on {string} app tab")
	public void user_clicks_on_tab(String tabName) {
		homePage.clickOnTab(tabName);
	}

	@And("User clicks on BI")
	public void user_clicks_on_bi() {
		homePage.clickOnBIApp();
	}

	@And("User clicks on Welcome popup close option")
	public void user_clicks_on_welcome_popup_close_option() {
		biApp.closeWelcomePopup();
	}

	@And("User clicks on Catalog option")
	public void user_clicks_on_catalog_option() {
		biApp.clickOnCatalogOption();
	}

	@And("User clicks on Add Database button")
	public void user_clicks_on_add_database_button() {
		biApp.clickOnAddDatabaseButton();
	}

	@And("User select the Excel option")
	public void user_select_the_excel_option() {
		biApp.selectExcelOption();
	}

	@When("User enter the database name as {string}")
	public void user_enter_the_database_name_as(String databaseName) {
		biApp.enterDatabaseName(databaseName + ' ' + timestamp);
	}

	@And("User uploads CSV file")
	public void user_uploads_csv_file() {
		biApp.uploadCSVFile();
	}

	@And("User clicks on Next button")
	public void user_clicks_on_next_button() {
		biApp.clickOnNextButton();
	}

	@And("User upload Excel file and clicks on Next button")
	public void user_upload_excel_file_and_clicks_on_next_button() {
		biApp.uploadExcelFile();
		biApp.clickOnNextButton();
	}

	@And("User clicks on Import button")
	public void user_clicks_on_import_button() {
		biApp.clickOnImportButton();
	}

	@Then("User can see database created success toast message as {string}")
	public void user_can_see_database_created_success_toast_message_as(String expectedToastMessage) {
		// TODO toast disappears quickly need a better way to validate
		String actualDBCreatedMessage = biApp.verifyDBCreatedToastMessage();
		Assertions.assertEquals(expectedToastMessage, actualDBCreatedMessage, "Database creation failed");
		logger.info("the success toast is quick skippng for now");
	}

	@And("User clicks on Add option")
	public void user_clicks_on_add_option() {
		biApp.clickOnAppOption();
	}

	@And("User search {string} database and select")
	public void user_search_database_and_select(String createdDatabaseName) {
		biApp.searchDatabaseName(createdDatabaseName + ' ' + timestamp);
	}

	@And("User search {string} created database and select")
	public void user_search_created_database_and_select(String createdDatabaseName) {
		biApp.searchDatabaseName(createdDatabaseName);
	}

	@And("User clicks on Add All button from Available Columns section")
	public void user_clicks_on_add_all_button_from_available_columns_section() {
		biApp.clickOnAddAllOption();
	}

	@And("User clicks on Import button from Selected Columns section")
	public void user_clicks_on_import_button_from_selected_columns_section() {
		biApp.clickOnImportButtonOfSelectedColumns();

	}

	@And("User mouse hover on database frame and click on Visualize this data option")
	public void user_mouse_hover_on_database_frame_and_click_on_Visualize_this_data_option() {
		biApp.mouseHoverOnDatabaseFrame();
		biApp.clickOnVisualizeDataOption();
	}

	@And("User clicks on Save button")
	public void user_clicks_on_save_button() {
		biApp.clickOnWorkspaceSaveButton();
	}

	@And("User enters {string} as the insight name, selects the {string} project, and clicks the Save button")
	public void user_enters_as_the_insight_name_selects_the_project_and_clicks_the_save_button(String insightName,
			String projectName) {
		biApp.enterInsightName(insightName);
		biApp.selectProjectName(projectName);
	}

	@Then("User can see Insight created toast message as {string}")
	public void user_can_see_insight_created_toast_message_as(String expectedMessage) {
		String actualMessage = biApp.verifySavedInsightSuccessMsg();
		assertEquals("Insights creation failed", actualMessage, expectedMessage);
	}

	@And("User clicks on New project button")
	public void user_clicks_on_new_project_button() {
		biApp.clickOnNewProjectButton();
	}

	@And("User selects {string} from the Visualization type options")
	public void user_selects_bar_from_the_visualization_type_options(String visualizationType) {
		biApp.selectVisualizationType(visualizationType);
	}

	@And("User drags the {string} field to the {string}")
	public void user_drags_the_field_to_the_x_axis(String fieldName, String axis) {
		biApp.dragFieldToXAxis(fieldName, axis);
	}

	@And("User clicks on the Tools option")
	public void user_clicks_on_the_tools_option() {
		biApp.clickOnToolsOption();
	}

	@And("User selects {string} from the Tools options")
	public void user_selects_from_the_tools_options(String toolOption) {
		biApp.selectToolOption(toolOption);
	}

	@And("User hovers Add Panel and selects {string}")
	public void user_hovers_add_panel_and_selects_add_chart(String panelType) {
		biApp.hoverAddPanelAndSelectAddChart(panelType);
	}

	@And("User Clicks on Presentation Mode option")
	public void user_clicks_on_presentation_mode_option() {
		biApp.clickOnPresentationModeOption();
	}

	@And("User clicks on Export option from the side menu")
	public void user_clicks_on_export_option_from_the_side_menu() {
		biApp.clickOnSideMenuOption();
	}
}
