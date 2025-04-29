package aicore.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

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

	public void fillName(String value) {
		JobPageUtils.fillName(page, value + " " + timestamp);
	}

	public void fillPixel(String value) {
		JobPageUtils.fillPixel(page, value);
	}

	public void clickAddButton() {
		JobPageUtils.clickAddButton(page);
	}

	public String verifyJobTitle(String jobTitle) {
		return JobPageUtils.verifyJobTitle(page, jobTitle + " " + timestamp);
	}

	public void clickEditIcon(String jobTitle) {
		JobPageUtils.clickEditIcon(page,  jobTitle + " " + timestamp);
	}

	public void editTags(int value) {
		JobPageUtils.editTags(page, value);
	}

	public void editPixel(String value) {
		JobPageUtils.editPixel(page, value);
	}

	public void clickSaveButton() {
		JobPageUtils.clickSaveButton(page);
	}

	public void verifyAddedTag(String expectedText, String jobTitle) {
		JobPageUtils.verifyAddedTag(page, expectedText, jobTitle + " " + timestamp);
	}

	public void clickDeleteIcon(String jobTitle) throws InterruptedException {
		JobPageUtils.clickDeleteIcon(page, jobTitle + " " + timestamp);
	}

	public String jobDeletionToastMessage() {
		return JobPageUtils.jobDeletionToastMessage(page);
	}

	public void clickCheckBox(String jobTitle) {
		JobPageUtils.clickCheckBox(page, jobTitle + " " + timestamp);
	}

	public void verifyPauseButtonEnabled(String jobTitle) {
		JobPageUtils.verifyPauseButtonEnabled(page, jobTitle);
	}

}
