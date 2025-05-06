package aicore.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class JobPageUtils {
	
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

	public static void clickOnJobTile(Page page) {
		page.locator(JOBS_TILE_XPATH).isVisible();
		page.locator(JOBS_TILE_XPATH).click();
	}

	public static void clickOnAddJobButton(Page page) {
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add")).isVisible();
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add")).click();
	}

	public static void fillName(Page page, String name) {
		page.locator(NAME_XPATH).isVisible();
		page.locator(NAME_XPATH).fill(name);
	}

	public static void fillPixel(Page page, String value) {
		page.locator(PIXEL_XPATH).isVisible();
		page.locator(PIXEL_XPATH).fill(value);
	}

	public static void clickAddButton(Page page) {
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add")).isVisible();
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add")).click();
	}

	public static String verifyJobTitle(Page page, String jobTitle) {
		Locator actualJobTitle = page.locator(JOB_LIST_XPATH.replace("{jobName}", jobTitle));
		return actualJobTitle.textContent().trim();
	}

	public static void clickEditIcon(Page page, String jobTitle) {
		Locator actualJobTitle = page.locator(JOB_LIST_XPATH.replace("{jobName}", jobTitle));
		String title = actualJobTitle.textContent();
		page.locator(EDIT_ICON_XPATH.replace("{jobName}", title)).isVisible();
		page.locator(EDIT_ICON_XPATH.replace("{jobName}", title)).click();
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

	public static void clickSaveButton(Page page) {
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Save")).isVisible();
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Save")).click();
	}

	public static void verifyAddedTag(Page page, String expectedText, String jobTitle) {
		page.locator(
				ADDED_TAG_XPATH.replace("{jobName}", jobTitle).replace("{textValue}", expectedText))
				.isVisible();
	}

	public static void clickDeleteIcon(Page page, String jobTitle) throws InterruptedException {
		Locator actualJobTitle = page.locator(JOB_LIST_XPATH.replace("{jobName}", jobTitle));
		String title = actualJobTitle.textContent();
		page.locator(DELETE_ICON_XPATH.replace("{jobName}", title)).isVisible();
		page.locator(DELETE_ICON_XPATH.replace("{jobName}", title)).click();
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Delete")).click();
	}

	public static String jobDeletionToastMessage(Page page) {
		String toastMessage = page.textContent(DELETE_JOB_TOAST_MESSAGE_XPATH).trim();
		return toastMessage;
	}

	public static void clickCheckBox(Page page, String jobTitle) {
		Locator actualJobTitle = page.locator(JOB_LIST_XPATH.replace("{jobName}", jobTitle));
		String title = actualJobTitle.textContent();
		page.locator(CHECKBOX_XPATH.replace("{jobName}", title)).isVisible();
		page.locator(CHECKBOX_XPATH.replace("{jobName}", title)).click();
	}

	public static void verifyPauseButtonEnabled(Page page, String jobTitle) {
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Pause")).isEnabled();
	}

}
