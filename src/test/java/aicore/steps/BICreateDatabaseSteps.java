package aicore.steps;

import org.junit.jupiter.api.Assertions;

import aicore.base.AICoreTestBase;
import aicore.pages.BISystemAppPage;
import aicore.pages.HomePage;
import aicore.utils.CommonUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BICreateDatabaseSteps {

	private HomePage homePage;
	private BISystemAppPage biApp;
	protected static String timestamp;

	public BICreateDatabaseSteps() {
		homePage = new HomePage(AICoreTestBase.page);
		timestamp = CommonUtils.getTimeStampName();
		biApp = new BISystemAppPage(AICoreTestBase.page, timestamp);
	}

	@When("User clicks on System app")
	public void user_clicks_on_system_app() {
		homePage.clickOnSystemApp();
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

	@Then("User can see database created success toast message as {string}")
	public void user_can_see_database_created_success_toast_message_as(String expectedToastMessage) {
		String actualDBCreatedMessage = biApp.verifyDBCreatedToastMessage();
		Assertions.assertEquals(actualDBCreatedMessage, expectedToastMessage, "Database creation failed");
	}
}
