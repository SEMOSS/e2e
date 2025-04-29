package aicore.steps;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Assertions;

import aicore.hooks.SetupHooks;
import aicore.pages.JobsPage;
import aicore.utils.CommonUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class JobsSteps {

	private JobsPage job;
	private String timestamp;

	public JobsSteps() {
		timestamp = CommonUtils.getTimeStampName();
		this.job = new JobsPage(SetupHooks.getPage(), timestamp);
	}

	@Given("User clicks on Open Settings Engine")
	public void user_clicks_on_open_settings_engine() {
		job.openSettingsIcon();
	}

	@Given("User clicks on Jobs Icon")
	public void user_clicks_on_job_icon() {
		job.clickOnJobs();
	}

	@When("User clicks the checkbox of {string}")
	public void user_checks_on_the_checkbox_of_job(String jobName) {
		job.checkTestJobCheckbox(jobName);
	}

	@When("User clicks the checkbox of Paused {string}")
	public void user_checks_on_the_checkbox_of_paused_job(String jobName) {
		job.checkTestJobCheckbox(jobName);
	}

	@When("User clicks the green Pause button")
	public void user_clicks_the_pause_button() {
		job.clickPauseButton();
	}

	@When("User clicks the Resume button")
	public void user_clicks_the_resume_button() {
		job.clickResumeButton();
	}

	@Then("the {string} should stop running")
	public void the_job_should_stop_running(String jobName) {
		boolean isStopped = job.isTestJobStopped(jobName);
		assertTrue("Test Job not stopped: " + jobName, isStopped);
	}

	@Then("the {string} should start running")
	public void the_job_should_start_running(String jobName) {
		boolean isStarted = job.isTestJobRunning(jobName);
		assertTrue("Test Job not started: " + jobName, isStarted);
	}

	@Then("the checkbox of {string} should become unselected")
	public void the_checkbox_is_unselected(String jobName) {
		boolean isSelected = job.isCheckboxSelected(jobName);
		assertFalse("Checkbox is still selected: " + isSelected, isSelected);
	}

	@Then("the green Pause button should revert to its default state")
	public void green_pause_revert_default() {
		boolean isReverted = job.isPauseButtonReverted();
		assertTrue("The button is not reverted: " + isReverted, isReverted);
	}

	@Then("the Resume button should revert to its default state")
	public void resume_button_revert_default() {
		boolean isReverted = job.isResumeButtonReverted();
		assertTrue("The button is not reverted: " + isReverted, isReverted);
	}
}
