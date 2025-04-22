package aicore.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class JobManagementPage {

	private Page page;
	private String timestamp;

	private static final String JOBS_TILE_XPATH = "//span[text()='Jobs']";
	private static final String NAME_XPATH = "//label[text()='Name']/parent::div/div/input";
	private static final String PIXEL_XPATH = "//label[text()='Pixel']/parent::div/div/textarea[@aria-invalid=\"false\"]";
	private static final String JOB_LIST_XPATH = "//div[text()='{jobName}']";
	private static final String EDIT_ICON_XPATH = "//div[contains(text(),'{jobName}')]/following-sibling::div//*[name()='svg'][@data-testid='EditIcon']";
	private static final String EDIT_TAGS_XPATH = "//span[text()='Tags']/ancestor::fieldset/parent::div//input";
	private static final String ADDED_TAG_XPATH = "//div[contains(text(),'{jobName}')]/following-sibling::div//div//span[text()='{textValue}']";
	private static final String DELETE_ICON_XPATH = "//div[contains(text(),'{jobName}')]/following-sibling::div//*[name()='svg'][@data-testid='DeleteIcon']";
	private static final String DELETE_JOB_TOAST_MESSAGE_XPATH = "//div[text()='Successfully deleted UNSCHEDULE_JOB']";
	private static final String CHECKBOX_XPATH = "//div[contains(text(),'{jobName}')]/preceding-sibling::div[@role=\"gridcell\"]";

	public JobManagementPage(Page page, String timestamp) {
		this.page = page;
		this.timestamp = timestamp;

	}

	public void clickOnJobTile() {
		page.locator(JOBS_TILE_XPATH).isVisible();
		page.locator(JOBS_TILE_XPATH).click();
	}

	public void clickOnAddJobButton() {
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add")).isVisible();
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add")).click();
	}

	public void fillName(String value) {
		page.locator(NAME_XPATH).isVisible();
		page.locator(NAME_XPATH).fill(value + " " + timestamp);
	}

	public void fillPixel(String value) {
		page.locator(PIXEL_XPATH).isVisible();
		page.locator(PIXEL_XPATH).fill(value);
	}

	public void clickAddButton() {
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add")).isVisible();
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add")).click();
	}

	public String verifyJobTitle(String jobTitle) {
		Locator actualJobTitle = page.locator(JOB_LIST_XPATH.replace("{jobName}", jobTitle + " " + timestamp));
		return actualJobTitle.textContent().trim();
	}

	public void clickEditIcon(String jobTitle) {
		Locator actualJobTitle = page.locator(JOB_LIST_XPATH.replace("{jobName}", jobTitle + " " + timestamp));
		String title = actualJobTitle.textContent();
		page.locator(EDIT_ICON_XPATH.replace("{jobName}", title)).isVisible();
		page.locator(EDIT_ICON_XPATH.replace("{jobName}", title)).click();
	}

	public void editTags(int value) {
		page.locator(EDIT_TAGS_XPATH).isVisible();
		page.locator(EDIT_TAGS_XPATH).click();
		page.locator(EDIT_TAGS_XPATH).fill(String.valueOf(value));
		page.locator(EDIT_TAGS_XPATH).press("Enter");
	}

	public void editPixel(String value) {
		page.locator(PIXEL_XPATH).isVisible();
		page.locator(PIXEL_XPATH).fill(value);
	}

	public void clickSaveButton() {
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Save")).isVisible();
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Save")).click();
	}

	public void verifyAddedTag(String expectedText, String jobTitle) {
		page.locator(
				ADDED_TAG_XPATH.replace("{jobName}", jobTitle + " " + timestamp).replace("{textValue}", expectedText))
				.isVisible();
	}

	public void clickDeleteIcon(String jobTitle) throws InterruptedException {
		Locator actualJobTitle = page.locator(JOB_LIST_XPATH.replace("{jobName}", jobTitle + " " + timestamp));
		String title = actualJobTitle.textContent();
		page.locator(DELETE_ICON_XPATH.replace("{jobName}", title)).isVisible();
		page.locator(DELETE_ICON_XPATH.replace("{jobName}", title)).click();
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Delete")).click();
	}

	public String jobDeletionToastMessage() {
		String toastMessage = page.textContent(DELETE_JOB_TOAST_MESSAGE_XPATH).trim();
		return toastMessage;
	}

	public void clickCheckBox(String jobTitle) {
		Locator actualJobTitle = page.locator(JOB_LIST_XPATH.replace("{jobName}", jobTitle + " " + timestamp));
		String title = actualJobTitle.textContent();
		page.locator(CHECKBOX_XPATH.replace("{jobName}", title)).isVisible();
		page.locator(CHECKBOX_XPATH.replace("{jobName}", title)).click();
	}

	public void verifyPauseButtonEnabled(String jobTitle) {
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Pause")).isEnabled();
	}

}
