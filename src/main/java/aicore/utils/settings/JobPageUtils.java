package aicore.utils.settings;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import aicore.utils.AICorePageUtils;
import aicore.utils.HomePageUtils;

public class JobPageUtils {

	private static final String JOBS_TILE_XPATH = "//span[text()='Jobs']";
	private static final String NAME_XPATH = "//label[text()='Name']/parent::div/div/input";
	private static final String PIXEL_XPATH = "//label[text()='Pixel']/parent::div/div/textarea[@aria-invalid=\"false\"]";
	private static final String JOB_LIST_XPATH = "//div[text()='{jobName}']";
	private static final String EDIT_ICON_XPATH = "//div[contains(text(),'{jobName}')]/following-sibling::div//*[name()='svg'][@data-testid='EditIcon']";
	private static final String EDIT_TAGS_XPATH = "//span[text()='Tags']/ancestor::fieldset/parent::div//input";
	private static final String ADDED_TAG_XPATH = "//div[contains(text(),'{jobName}')]/following-sibling::div//div//span[text()='{textValue}']";
	private static final String DELETE_ICON_XPATH = "//div[contains(text(),'{jobName}')]/following-sibling::div//*[name()='svg'][@data-testid='DeleteIcon']";
	private static final String DELETE_JOB_TOAST_MESSAGE_DATA_TESTID = "notification-success-message";
	private static final String CHECKBOX_XPATH = "//div[contains(text(),'{jobName}')]/preceding-sibling::div[@role=\"gridcell\"]";
	private static final String JOB_CHECKBOX_XPATH = "//div[text()='{jobName}']/parent::div//input[@type='checkbox']";
	private static final String JOB_STATUS_CHECK_XPATH = "//div[@role='rowgroup']//div[@role='row'][.//div[@data-field='name' and normalize-space(text())='{JobName}']]//div[@data-field='isActive']";
	private static final String RESUME_BUTTON_XPATH = "//button[@type='button' and normalize-space(.) = 'Resume']";
	private static final String PAUSE_BUTTON_XPATH = "//button[@type='button' and normalize-space(.) = 'Pause']";
	private static final String JOB_PAGE_SUB_TITLE_XPATH = "//p[text()='{subTitle}']";
	private static final String STATUS_TILES_XPATH = "//div[contains(@class,'MuiBox')]//p[text()='{tileName}']";
	private static final String NO_JOBS_MESSAGE_XPATH = "//div[text()='{message}']";
	private static final String JOBS_TABLE_COULMNS_XPATH = "//div[@role='columnheader']//div[text()='{columnName}']";
	private static final String HISTORY_TABLE_XPATH = "//div[@aria-expanded='false']//div[text()='History']";
	private static final String HISTORY_TABLE_COLUMNS_XPATH = "//table//thead//tr//th[text()='{columnName}']";
	private static final String HISTORY_TABLE_SEARCHBOX_XPATH = "//div[text()='History']/ancestor::h3//following-sibling::div//input[@placeholder='Search']";
	private static final String NO_JOB_HISTORY_MESSAGE_XPATH = "//table//tbody//tr//td[text()='{message}']";

	public static void clickOnJobTile(Page page) {
		page.locator(JOBS_TILE_XPATH).isVisible();
		page.locator(JOBS_TILE_XPATH).click();
	}

	public static void clickOnAddJobButton(Page page) {
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add")).isVisible();
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add")).click();
	}

	public static void clickAddButton(Page page) {
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add")).isVisible();
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add")).click();
	}

	public static void clickEditIcon(Page page, String jobTitle) {
		Locator actualJobTitle = page.locator(JOB_LIST_XPATH.replace("{jobName}", jobTitle));
		String title = actualJobTitle.textContent();
		page.locator(EDIT_ICON_XPATH.replace("{jobName}", title)).isVisible();
		page.locator(EDIT_ICON_XPATH.replace("{jobName}", title)).click();
	}

	public static void clickSaveButton(Page page) {
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Save")).isVisible();
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Save")).click();
	}

	public static void clickDeleteIcon(Page page, String jobTitle) throws InterruptedException {
		Locator actualJobTitle = page.locator(JOB_LIST_XPATH.replace("{jobName}", jobTitle));
		String title = actualJobTitle.textContent();
		page.locator(DELETE_ICON_XPATH.replace("{jobName}", title)).isVisible();
		page.locator(DELETE_ICON_XPATH.replace("{jobName}", title)).click();
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Delete")).click();
	}

	public static void clickJobCheckBox(Page page, String jobName) {
		String checkboxXPath = JOB_CHECKBOX_XPATH.replace("{jobName}", jobName);
		Locator checkbox = page.locator(checkboxXPath);
		AICorePageUtils.waitFor(checkbox);
		checkbox.scrollIntoViewIfNeeded();
		checkbox.click(new Locator.ClickOptions().setForce(true));
	}

	public static void clickPauseButton(Page page) {
		Locator pauseButton = page.locator(PAUSE_BUTTON_XPATH);
		AICorePageUtils.waitFor(pauseButton);
		pauseButton.isEnabled();
		pauseButton.click();
	}

	public static void clickResumeButton(Page page) {
		Locator resumeButton = page.locator(RESUME_BUTTON_XPATH);
		AICorePageUtils.waitFor(resumeButton);
		resumeButton.isEnabled();
		resumeButton.click();
	}

	public static void clickCheckBox(Page page, String jobTitle) {
		Locator actualJobTitle = page.locator(JOB_LIST_XPATH.replace("{jobName}", jobTitle));
		String title = actualJobTitle.textContent();
		page.locator(CHECKBOX_XPATH.replace("{jobName}", title)).isVisible();
		page.locator(CHECKBOX_XPATH.replace("{jobName}", title)).click();
	}

	public static void fillName(Page page, String name) {
		page.locator(NAME_XPATH).isVisible();
		page.locator(NAME_XPATH).fill(name);
	}

	public static void fillPixel(Page page, String value) {
		page.locator(PIXEL_XPATH).isVisible();
		page.locator(PIXEL_XPATH).fill(value);
	}

	public static void editTags(Page page, int value) {
		page.locator(EDIT_TAGS_XPATH).isVisible();
		page.locator(EDIT_TAGS_XPATH).click();
		page.locator(EDIT_TAGS_XPATH).fill(String.valueOf(value));
		page.locator(EDIT_TAGS_XPATH).press("Enter");
	}

	public static void editPixel(Page page, String value) {
		page.locator(PIXEL_XPATH).isVisible();
		page.locator(PIXEL_XPATH).fill(value);
	}

	public static String jobDeletionToastMessage(Page page) {
		return page.getByTestId(DELETE_JOB_TOAST_MESSAGE_DATA_TESTID).textContent().trim();
	}

	public static boolean isJobStopped(Page page, String jobName) {
		final String PAUSED = "Paused";
		String xpath = JOB_STATUS_CHECK_XPATH.replace("{JobName}", jobName);
		page.waitForSelector(xpath);
		String status = page.locator(xpath).textContent().trim();
		return status.equals(PAUSED);
	}

	public static boolean isJobRunning(Page page, String jobName) {
		final String ACTIVE = "Active";
		String xpath = JOB_STATUS_CHECK_XPATH.replace("{JobName}", jobName);
		page.waitForSelector(xpath);
		String status = page.locator(xpath).textContent().trim();
		return status.equals(ACTIVE);
	}

	public static boolean isCheckboxSelected(Page page, String jobName) {
		String xpath = JOB_CHECKBOX_XPATH.replace("{jobName}", jobName);
		page.waitForSelector(xpath);
		return page.locator(xpath).isChecked();
	}

	public static boolean isPauseButtonReverted(Page page) {
		String xpath = PAUSE_BUTTON_XPATH;
		page.waitForSelector(xpath);
		return (page.locator(xpath).isDisabled());
	}

	public static boolean isResumeButtonReverted(Page page) {
		String xpath = RESUME_BUTTON_XPATH;
		page.waitForSelector(xpath);
		return (page.locator(xpath).isDisabled());
	}

	public static void createJob(Page page, String name, String value) {
		HomePageUtils.openMainMenu(page);
		HomePageUtils.clickOnOpenSettings(page);
		if (page.locator("//*[local-name()='svg'][contains(@class,'MuiSvgIcon-colorDisabled')]").isVisible()) {
			page.getByTestId("AdminPanelSettingsOutlinedIcon").click();
		}
		page.locator(JOBS_TILE_XPATH).click();
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add")).click();
		page.locator(NAME_XPATH).fill(name);
		page.locator(PIXEL_XPATH).fill(value);
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add")).click();
	}

	public static String verifyJobTitle(Page page, String jobTitle) {
		Locator actualJobTitle = page.locator(JOB_LIST_XPATH.replace("{jobName}", jobTitle)).first();
		AICorePageUtils.waitFor(actualJobTitle);
		actualJobTitle.scrollIntoViewIfNeeded();
		return actualJobTitle.textContent().trim();
	}

	public static void verifyAddedTag(Page page, String expectedText, String jobTitle) {
		page.locator(ADDED_TAG_XPATH.replace("{jobName}", jobTitle).replace("{textValue}", expectedText)).isVisible();
	}

	public static void verifyPauseButtonEnabled(Page page, String jobTitle) {
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Pause")).isEnabled();
	}

	public static String verifyTitleOfJobPage(Page page, String expectedTitle) {
		return page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName(expectedTitle)).textContent()
				.trim();
	}

	public static String verifySubtitleOfJobPage(Page page, String expectedSubtitle) {
		return page.locator(JOB_PAGE_SUB_TITLE_XPATH.replace("{subTitle}", expectedSubtitle)).textContent().trim();
	}

	public static boolean verifyStatusTilesVisibleOnJobPage(Page page, String tileName) {
		return page.locator(STATUS_TILES_XPATH.replace("{tileName}", tileName)).isVisible();
	}

	public static boolean verifyButtonsVisibleOnJobPage(Page page, String buttonName) {
		return page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(buttonName)).isVisible();
	}

	public static boolean verifyButtonsDisabledOnJobPage(Page page, String buttonName) {
		return page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(buttonName)).isDisabled();
	}

	public static boolean verifyTabsVisibleOnJobPage(Page page, String tabName) {
		return page.getByRole(AriaRole.TAB, new Page.GetByRoleOptions().setName(tabName)).first().isVisible();
	}

	public static boolean verifySearchBoxVisibleOnJobPage(Page page) {
		return page.getByPlaceholder("Search").first().isVisible();
	}

	public static boolean verifyJobTableColumns(Page page, String columnName) {
		return page.locator(JOBS_TABLE_COULMNS_XPATH.replace("{columnName}", columnName)).isVisible();
	}

	public static boolean verifyNoJobsMessageOnJobPage(Page page, String expectedMessage) {
		return page.locator(NO_JOBS_MESSAGE_XPATH.replace("{message}", expectedMessage)).isVisible();
	}

	public static boolean verifyHistoryTableCollapsed(Page page) {
		Locator historyTableLocator = page.locator(HISTORY_TABLE_XPATH);
		AICorePageUtils.waitFor(historyTableLocator);
		historyTableLocator.scrollIntoViewIfNeeded();
		return historyTableLocator.isVisible();
	}

	public static boolean verifyHistoryTableColumns(Page page, String columnName) {
		return page.locator(HISTORY_TABLE_COLUMNS_XPATH.replace("{columnName}", columnName)).isVisible();
	}

	public static boolean verifySearchBoxVisibleOnHistoryTable(Page page) {
		return page.locator(HISTORY_TABLE_SEARCHBOX_XPATH).isVisible();
	}

	public static boolean verifyNoJobHistoryMessageOnJobPage(Page page, String expectedMessage) {
		return page.locator(NO_JOB_HISTORY_MESSAGE_XPATH.replace("{message}", expectedMessage)).isVisible();
	}

	public static void expandHistoryTable(Page page) {
		Locator historyTableLocator = page.locator(HISTORY_TABLE_XPATH);
		AICorePageUtils.waitFor(historyTableLocator);
		historyTableLocator.scrollIntoViewIfNeeded();
		historyTableLocator.click();
	}

}
