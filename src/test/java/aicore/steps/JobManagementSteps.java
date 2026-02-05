package aicore.steps;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Assertions;

import aicore.hooks.SetupHooks;
import aicore.pages.JobManagementPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class JobManagementSteps {
	private JobManagementPage jobManagementPage;
	protected static String timestamp;

	public JobManagementSteps() {
		timestamp = SetupHooks.getTimestamp();
		jobManagementPage = new JobManagementPage(SetupHooks.getPage(), timestamp);
	}

	@And("User clicks on Jobs")
	public void user_clicks_on_jobs() {
		jobManagementPage.clickOnJobTile();
	}

	@And("User clicks on Add Job button")
	public void user_clicks_on_add_job_button() {
		jobManagementPage.clickOnAddJobButton();
	}

	@And("User fills {string} in Name field")
	public void user_fills_in_name_field(String string) {
		jobManagementPage.fillName(string + " " + timestamp);
	}

	@And("User fills {string} in Pixel field")
	public void user_fills_in_pixel_field(String value) {
		jobManagementPage.fillPixel(value);
	}

	@And("User clicks Add button")
	public void user_clicks_add_button() {
		jobManagementPage.clickAddButton();
	}

	@Then("User can see {string} in the list")
	public void user_can_see_in_the_list(String jobTitle) {
		String actualJobTitle = jobManagementPage.verifyJobTitle(jobTitle);
		String expectedJobTitle = jobTitle + " " + timestamp;
		Assertions.assertEquals(expectedJobTitle, actualJobTitle);
	}

	@When("User clicks on Edit Icon for added {string}")
	public void user_clicks_on_edit_icon_for_added(String jobTitle) {
		jobManagementPage.clickEditIcon(jobTitle);
	}

	@And("User edit Tags as {int}")
	public void user_edit_tags_as(int value) {
		jobManagementPage.editTags(value);
	}

	@And("User edit Pixels as {string}")
	public void user_edit_pixels_as(String string) {
		jobManagementPage.editPixel(string);

	}

	@And("User clicks Save button")
	public void user_clicks_save_button() {
		jobManagementPage.clickSaveButton();
	}

	@Then("User can see {string} value as Tag for edited {string}")
	public void user_can_see_value_as_tag_for_edited(String string, String string2) {
		jobManagementPage.verifyAddedTag(string, string2);

	}

	@When("User clicks on Delete Icon for added {string}")
	public void user_clicks_on_delete_icon_for_added(String jobTitle) throws InterruptedException {
		jobManagementPage.clickDeleteIcon(jobTitle);
	}

	@Then("User sees delete job toast message as {string}")
	public void user_sees_delete_job_toast_message_as(String toastMessage) {
		String actualMessage = jobManagementPage.jobDeletionToastMessage();
		Assertions.assertEquals(actualMessage, toastMessage, "Job deletion failed");
	}

	@When("User selects the checkbox next to {string}")
	public void user_selects_the_checkbox_next_to(String jobTitle) {
		jobManagementPage.clickCheckBox(jobTitle);
	}

	@Then("{string} will start running and Pause button will be enabled")
	public void will_start_running_and_pause_button_will_be_enabled(String jobTitle) {
		jobManagementPage.verifyPauseButtonEnabled(jobTitle);
	}

	@When("User clicks the checkbox of {string}")
	public void user_checks_on_the_checkbox_of_job(String jobName) {
		jobManagementPage.clickJobCheckBox(jobName);
	}

	@When("User clicks the green Pause button")
	public void user_clicks_the_pause_button() {
		jobManagementPage.clickPauseButton();
	}

	@Then("the {string} should stop running")
	public void the_job_should_stop_running(String jobName) {
		boolean isStopped = jobManagementPage.isJobStopped(jobName);
		assertTrue(isStopped, "Test Job not stopped: " + jobName);
	}

	@Then("the checkbox of {string} should become unselected")
	public void the_checkbox_is_unselected(String jobName) {
		boolean isSelected = jobManagementPage.isCheckboxSelected(jobName);
		assertFalse(isSelected, "Checkbox is still selected: " + isSelected);
	}

	@Then("the green Pause button should revert to its default state")
	public void green_pause_revert_default() {
		boolean isReverted = jobManagementPage.isPauseButtonReverted();
		assertTrue(isReverted, "The button is not reverted: " + isReverted);
	}

	@When("User clicks the Resume button")
	public void user_clicks_the_resume_button() {
		jobManagementPage.clickResumeButton();
	}

	@Then("the Resume button should revert to its default state")
	public void resume_button_revert_default() {
		boolean isReverted = jobManagementPage.isResumeButtonReverted();
		assertTrue(isReverted, "The button is not reverted: " + isReverted);
	}

	@Then("User sees Jobs page title as {string}")
	public void user_sees_jobs_page_title_as(String title) {
		String actualTitle = jobManagementPage.verifyTitleOfJobPage(title);
		Assertions.assertEquals(actualTitle, title, "Job page title mismatch");
	}

	@Then("User sees Jobs page subtitle as {string}")
	public void user_sees_jobs_page_subtitle_as(String subtitle) {
		String actualSubtitle = jobManagementPage.verifySubtitleOfJobPage(subtitle);
		Assertions.assertEquals(actualSubtitle, subtitle, "Job page subtitle mismatch");
	}

	@Then("User sees below status tiles on Jobs page")
	public void user_sees_below_status_tiles_on_jobs_page(DataTable dataTable) {
		List<String> tiles = dataTable.asList();
		for (String tile : tiles) {
			boolean isTileVisible = jobManagementPage.verifyStatusTilesOnJobPage(tile);
			Assertions.assertTrue(isTileVisible, "Tile not visible: " + tile);
		}
	}

	@Then("User sees below tabs on Jobs page")
	public void user_sees_below_tabs_on_jobs_page(DataTable dataTable) {
		List<String> tabs = dataTable.asList();
		for (String tab : tabs) {
			boolean isTabVisible = jobManagementPage.verifyTabsVisibleOnJobPage(tab);
			Assertions.assertTrue(isTabVisible, "Tab not visible: " + tab);
		}
	}

	@Then("User sees below buttons on Jobs page")
	public void user_sees_below_buttons_on_jobs_page(DataTable dataTable) {
		List<String> buttons = dataTable.asList();
		for (String button : buttons) {
			boolean isButtonVisible = jobManagementPage.verifyButtonsVisibleOnJobPage(button);
			Assertions.assertTrue(isButtonVisible, "Button not visible: " + button);
		}
	}

	@Then("User sees below buttons are disabled on Jobs page")
	public void user_sees_below_buttons_are_disabled_on_jobs_page(DataTable dataTable) {
		List<String> buttons = dataTable.asList();
		for (String button : buttons) {
			boolean isButtonDisabled = jobManagementPage.verifyButtonsDisabledOnJobPage(button);
			Assertions.assertTrue(isButtonDisabled, "Button is not disabled: " + button);
		}
	}

	@Then("User sees Search box on Jobs page")
	public void user_sees_search_box_on_jobs_page() {
		boolean isSearchboxVisible = jobManagementPage.verifySearchBoxVisibleOnJobPage();
		Assertions.assertTrue(isSearchboxVisible, "Search box not visible on Jobs page");
	}

	@Then("User sees Jobs table with below columns")
	public void user_sees_jobs_table_with_below_columns(DataTable dataTable) {
		List<String> columns = dataTable.asList();
		for (String column : columns) {
			boolean isColumnVisible = jobManagementPage.verifyJobTableColumns(column);
			Assertions.assertTrue(isColumnVisible, "Column not visible: " + column);
		}
	}

	@Then("User sees {string} message when there are no jobs present")
	public void user_sees_message_when_there_are_no_jobs_present(String message) {
		boolean isMessageVisible = jobManagementPage.verifyNoJobsMessageOnJobPage(message);
		Assertions.assertTrue(isMessageVisible, "No jobs message not visible: " + message);
	}

	@Then("User sees History table is by default collapsed")
	public void user_sees_history_table_is_by_default_collapsed() {
		boolean isHistoryTableCollapsed = jobManagementPage.verifyHistoryTableCollapsed();
		Assertions.assertTrue(isHistoryTableCollapsed, "History table is not collapsed");
	}

	@When("User expands History table")
	public void user_expands_history_table() {
		jobManagementPage.expandHistoryTable();
	}

	@Then("User sees History table with below columns")
	public void user_sees_history_table_with_below_columns(DataTable dataTable) {
		List<String> columns = dataTable.asList();
		for (String column : columns) {
			boolean isColumnVisible = jobManagementPage.verifyHistoryTableColumns(column);
			Assertions.assertTrue(isColumnVisible, "Column not visible: " + column);
		}
	}

	@Then("User sees search box on History table")
	public void user_sees_search_box_on_history_table() {
		boolean isSearchBoxVisible = jobManagementPage.verifySearchBoxVisibleOnHistoryTable();
		Assertions.assertTrue(isSearchBoxVisible, "Search box not visible on History table");
	}

	@Then("User sees {string} message when there is no job history present")
	public void user_sees_message_when_there_is_no_job_history_present(String message) {
		boolean isMessageVisible = jobManagementPage.verifyNoJobHistoryMessageOnJobPage(message);
		Assertions.assertTrue(isMessageVisible, "No job history message not visible: " + message);
	}

}
