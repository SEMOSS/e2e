package aicore.steps;

import org.junit.jupiter.api.Assertions;

import aicore.hooks.SetupHooks;
import aicore.pages.JobManagementPage;
import aicore.utils.CommonUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class JobManagementSteps {
	private JobManagementPage jobManagementPage;
	protected static String timestamp;

	public JobManagementSteps() {
		timestamp = CommonUtils.getTimeStampName();
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
		jobManagementPage.fillName(string);
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

}
