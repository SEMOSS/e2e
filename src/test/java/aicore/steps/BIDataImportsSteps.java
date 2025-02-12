package aicore.steps;

import org.junit.jupiter.api.Assertions;
import aicore.base.AICoreTestBase;
import aicore.pages.BISystemAppPage;
import aicore.pages.HomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BIDataImportsSteps {

	private HomePage homePage;
	private BISystemAppPage biApp;

	public BIDataImportsSteps() {
		homePage = new HomePage(AICoreTestBase.page);
		biApp = new BISystemAppPage(AICoreTestBase.page);
	}

	@When("User clicks on System app")
	public void user_clicks_on_system_app() {
		homePage.clickOnSystemApp();
	}

	@And("User clicks on BI")
	public void user_clicks_on_bi() {
		homePage.clickOnBIApp();
	}

	@And("User clicks on Catalog option")
	public void user_clicks_on_catalog_option() {
		biApp.closeWelcomePopup();
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
		biApp.enterDatabaseName(databaseName);
	}

	@And("User upload CSV file and clicks on Next button")
	public void user_upload_csv_file_and_clicks_on_next_button() {
		biApp.uploadCSVFile();
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

	@And("User clicks on Add option")
	public void user_clicks_on_add_option() {
		biApp.clickOnAppOption();
	}

	@And("User search {string} database and select")
	public void user_search_database_and_select(String databaseName){
		biApp.searchDatabaseName(databaseName);
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

	@And("User click on Save button")
	public void user_click_on_save_button() {
		biApp.clickOnWorkspaceSaveButton();
	}

	@And("User fill the all information and clicks on Save button")
	public void user_fill_the_all_information_and_clicks_on_save_button() {
		biApp.enterInsightName();
		biApp.enterInsightsDetail();
	}

	@Then("User can see toast message as {string}")
	public void user_can_see_toast_message_as(String expectedMessage) {
		String actualMessage = biApp.verifySavedInsightSuccessMsg();
		Assertions.assertEquals(actualMessage, expectedMessage, "Insights creation failed");
	}

}
