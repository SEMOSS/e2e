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

	public void clickOnJobTile() {
		JobPageUtils.clickOnJobTile(page);
	}

	public void clickOnAddJobButton() {
		JobPageUtils.clickOnAddJobButton(page);
	}

	public void clickAddButton() {
		JobPageUtils.clickAddButton(page);
	}

	public void clickEditIcon(String jobTitle) {
		JobPageUtils.clickEditIcon(page, jobTitle + " " + timestamp);
	}

	public void clickSaveButton() {
		JobPageUtils.clickSaveButton(page);
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

	public void clickResumeButton() {
		JobPageUtils.clickResumeButton(page);
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

	public String jobDeletionToastMessage() {
		return JobPageUtils.jobDeletionToastMessage(page);
	}

	public boolean isJobStopped(String jobName) {
		return JobPageUtils.isJobStopped(page, jobName + " " + timestamp);
	}

	public boolean isCheckboxSelected(String jobName) {
		return JobPageUtils.isCheckboxSelected(page, jobName + " " + timestamp);
	}

	public boolean isPauseButtonReverted() {
		return JobPageUtils.isPauseButtonReverted(page);
	}

	public boolean isResumeButtonReverted() {
		return JobPageUtils.isResumeButtonReverted(page);
	}

	public void createJob(String name, String value) {
		JobPageUtils.createJob(page, name, value);
	}

	public String verifyJobTitle(String jobTitle) {
		return JobPageUtils.verifyJobTitle(page, jobTitle + " " + timestamp);
	}

	public void verifyAddedTag(String expectedText, String jobTitle) {
		JobPageUtils.verifyAddedTag(page, expectedText, jobTitle + " " + timestamp);
	}

	public void verifyPauseButtonEnabled(String jobTitle) {
		JobPageUtils.verifyPauseButtonEnabled(page, jobTitle);
	}

	public String verifyTitleOfJobPage(String expectedTitle) {
		return JobPageUtils.verifyTitleOfJobPage(page, expectedTitle);
	}

	public String verifySubtitleOfJobPage(String expectedSubtitle) {
		return JobPageUtils.verifySubtitleOfJobPage(page, expectedSubtitle);
	}

	public boolean verifyStatusTilesOnJobPage(String tile) {
		return JobPageUtils.verifyStatusTilesVisibleOnJobPage(page, tile);
	}

	public boolean verifyButtonsVisibleOnJobPage(String buttonName) {
		return JobPageUtils.verifyButtonsVisibleOnJobPage(page, buttonName);
	}

	public boolean verifyButtonsDisabledOnJobPage(String buttonName) {
		return JobPageUtils.verifyButtonsDisabledOnJobPage(page, buttonName);
	}

	public boolean verifyTabsVisibleOnJobPage(String tabName) {
		return JobPageUtils.verifyTabsVisibleOnJobPage(page, tabName);
	}

	public boolean verifySearchBoxVisibleOnJobPage() {
		return JobPageUtils.verifySearchBoxVisibleOnJobPage(page);
	}

	public boolean verifyJobTableColumns(String columnName) {
		return JobPageUtils.verifyJobTableColumns(page, columnName);
	}

	public boolean verifyHistoryTableColumns(String columnName) {
		return JobPageUtils.verifyHistoryTableColumns(page, columnName);
	}

	public boolean verifyNoJobsMessageOnJobPage(String expectedMessage) {
		return JobPageUtils.verifyNoJobsMessageOnJobPage(page, expectedMessage);
	}

	public boolean verifyHistoryTableCollapsed() {
		return JobPageUtils.verifyHistoryTableCollapsed(page);
	}

	public boolean verifySearchBoxVisibleOnHistoryTable() {
		return JobPageUtils.verifySearchBoxVisibleOnHistoryTable(page);
	}

	public boolean verifyNoJobHistoryMessageOnJobPage(String expectedMessage) {
		return JobPageUtils.verifyNoJobHistoryMessageOnJobPage(page, expectedMessage);
	}

	public void expandHistoryTable() {
		JobPageUtils.expandHistoryTable(page);
	}

}
