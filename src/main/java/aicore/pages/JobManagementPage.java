package aicore.pages;

import com.microsoft.playwright.Page;

import aicore.utils.settings.JobPageUtils;

public class JobManagementPage {

	private Page page;
	private String timestamp;

	public JobManagementPage(Page page, String timestamp) {
		this.page = page;
		this.timestamp = timestamp;
	}

	public void clickOnAddJobButton() {
		JobPageUtils.clickOnAddJobButton(page);
	}

	public void clickEditIcon(String jobTitle) {
		JobPageUtils.clickEditIcon(page, jobTitle + " " + timestamp);
	}

	public void clickDeleteIcon(String jobTitle) throws InterruptedException {
		JobPageUtils.clickDeleteIcon(page, jobTitle + " " + timestamp);
	}

	public void clickJobCheckBox(String jobName) {
		JobPageUtils.clickJobCheckBox(page, jobName + " " + timestamp);
	}

	public void clickPauseButton() {
		JobPageUtils.clickPauseButton(page);
	}

	public void clickCheckBox(String jobTitle) {
		JobPageUtils.clickCheckBox(page, jobTitle + " " + timestamp);
	}

	public void fillName(String value) {
		JobPageUtils.fillName(page, value);
	}

	public void fillPixel(String value) {
		JobPageUtils.fillPixel(page, value);
	}

	public void editTags(int value) {
		JobPageUtils.editTags(page, value);
	}

	public void editPixel(String value) {
		JobPageUtils.editPixel(page, value);
	}

	public boolean isJobStopped(String jobName) {
		return JobPageUtils.isJobStopped(page, jobName + " " + timestamp);
	}

	public boolean isCheckboxSelected(String jobName) {
		return JobPageUtils.isCheckboxSelected(page, jobName + " " + timestamp);
	}

	public boolean isResumeButtonReverted() {
		return JobPageUtils.isResumeButtonReverted(page);
	}

	public void createJob(String name, String value) {
		JobPageUtils.createJob(page, name, value);
	}

	public boolean verifyJobTitle(String jobTitle, String sectionName) {
		return JobPageUtils.verifyJobTitle(page, jobTitle + " " + timestamp, sectionName);
	}

	public void verifyAddedTag(String expectedText, String jobTitle) {
		JobPageUtils.verifyAddedTag(page, expectedText, jobTitle + " " + timestamp);
	}

	public String verifyTitleOfJobPage(String expectedTitle) {
		return JobPageUtils.verifyTitleOfJobPage(page, expectedTitle);
	}

	public String verifySubtitleOfJobPage(String expectedSubtitle) {
		return JobPageUtils.verifySubtitleOfJobPage(page, expectedSubtitle);
	}

	public void clickOnRunJobIcon(String jobName) {
		JobPageUtils.clickOnRunJobIcon(page, jobName + " " + timestamp);
	}

}
