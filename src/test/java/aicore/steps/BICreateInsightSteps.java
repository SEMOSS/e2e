package aicore.steps;

import static org.junit.Assert.assertEquals;

import aicore.base.AICoreTestBase;
import aicore.pages.BISystemAppPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class BICreateInsightSteps {

	private BISystemAppPage biApp;

	public BICreateInsightSteps() {
		biApp = new BISystemAppPage(AICoreTestBase.page, BICreateDatabaseSteps.timestamp);
	}

	@And("User clicks on Add option")
	public void user_clicks_on_add_option() {
		biApp.clickOnAppOption();
	}

	@And("User search {string} database and select")
	public void user_search_database_and_select(String createdDatabaseName) {
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

	@And("User fill the all information and clicks on Save button")
	public void user_fill_the_all_information_and_clicks_on_save_button() {
		biApp.enterInsightName();
		biApp.enterInsightsDetail();
	}

	@Then("User can see Insight created toast message as {string}")
	public void user_can_see_insight_created_toast_message_as(String expectedMessage) {
		String actualMessage = biApp.verifySavedInsightSuccessMsg();
		assertEquals(actualMessage, expectedMessage, "Insights creation failed");
	}

}
