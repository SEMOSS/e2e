package aicore.pages;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;

public class JobsPage {
	private Page page;
	private String timestamp;

	private static final String OPEN_SETTINGS_ICON_XPATH = "Settings-icon";
	private static final String PAUSE_BUTTON_XPATH = "//button[@type='button' and normalize-space(.) = 'Pause']";
	private static final String JOB_CHECKBOX_XPATH = "//div[@data-field='name' and normalize-space(text()) = '{JobName}']/ancestor::div[@role='row']//input[@type='checkbox']";
	private static final String JOB_STATUS_CHECK_XPATH = "//div[@role='rowgroup']//div[@role='row'][.//div[@data-field='name' and normalize-space(text())='{JobName}']]//div[@data-field='isActive']";
	private static final String RESUME_BUTTON_XPATH = "//button[@type='button' and normalize-space(.) = 'Resume']";


	public JobsPage(Page page, String timestamp) {
		this.page = page;
		this.timestamp = timestamp;
	}

	public void openSettingsIcon() {
		page.getByTestId(OPEN_SETTINGS_ICON_XPATH).click();
	}

	public void clickOnJobs() {
		page.getByText("Jobs").click();
	}

	public void checkTestJobCheckbox(String jobName) {
		String checkboxXPath = JOB_CHECKBOX_XPATH.replace("{JobName}", jobName);
		Locator checkbox = page.locator(checkboxXPath);
		checkbox.waitFor();

		if (!checkbox.isChecked()) {
			checkbox.check();
		}
	}

	public void clickPauseButton() {
		Locator pauseButton = page.locator(PAUSE_BUTTON_XPATH);
		pauseButton.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		pauseButton.click();
	}

	public void clickResumeButton() {
		Locator pauseButton = page.locator(RESUME_BUTTON_XPATH);
		pauseButton.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		pauseButton.click();
	}

	public boolean isTestJobStopped(String jobName) {
		final String PAUSED = "Paused";
		String xpath = JOB_STATUS_CHECK_XPATH.replace("{JobName}", jobName);
		page.waitForSelector(xpath);
		String status = page.locator(xpath).textContent().trim();
		return status.equals(PAUSED);
	}

	public boolean isTestJobRunning(String jobName) {
		final String ACTIVE = "Active";
		String xpath = JOB_STATUS_CHECK_XPATH.replace("{JobName}", jobName);
		page.waitForSelector(xpath);
		String status = page.locator(xpath).textContent().trim();
		return status.equals(ACTIVE);
	}

	public boolean isCheckboxSelected(String jobName) {
		String xpath = JOB_CHECKBOX_XPATH.replace("{JobName}", jobName);
		page.waitForSelector(xpath);
		return page.locator(xpath).isChecked();
	}

	public boolean isPauseButtonReverted() {
		String xpath = PAUSE_BUTTON_XPATH;
		page.waitForSelector(xpath);
		return (page.locator(xpath).isDisabled());
	}

	public boolean isResumeButtonReverted() {
		String xpath = RESUME_BUTTON_XPATH;
		page.waitForSelector(xpath);
		return (page.locator(xpath).isDisabled());
	}

}
