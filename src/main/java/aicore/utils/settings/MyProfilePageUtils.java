package aicore.utils.settings;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class MyProfilePageUtils {

	///// SETTING MY PROFILE PAGE
	private static final String MY_PROFILE_SECTION_TITLE_XPATH = "//*[text()='{sectionText}']";
	private static final String PRIVACY_CENTER_XPATH = "//span[text()='Privacy Center']";
	private static final String DESCRIPTION_FIELD_BUTTON_XPATH = "//label[text()='Description']/following-sibling::div//input";
	private static final String KEY_COPY_BUTTON_XPATH = "//div[h6[text()='{KeyName}']]//button[contains(@class, 'MuiButton-outlined') and .//span[text()='Copy']]";
	private static final String SECTION_ARROW_DOWN_XPATH = "//div[h6[text()='{sectionName}']]//*[name()='svg']";
	private static final String SECTION_COPY_BUTTON_XPATH = "//div[h6[text()='{sectionName}']]/following-sibling::div//span[text()='Copy']";
	private static final String CANCEL_BUTTON_XPATH = "//button[contains(@class, 'MuiButtonBase-root') and .//span[normalize-space()='Close']]";
	private static final String DELETE_BUTTON_XPATH = "//td[text()='{KeyName}']/following-sibling::td//button[@title='Delete']";
	private static final String DELETE_KEY_TOAST_MESSAGE_XPATH = "//div[contains(@class, 'MuiAlert-message')]";
	private static final String GENERATED_KEY_XPATH = "//td[contains(text(),'{keyName}')]";
	private static final String GENERATED_DESCRIPTION_XPATH = "//td[text()='{description}']";

	public static void clickOnMyProfileCard(Page page) {
		page.getByText("My profile").click();
	}

	public static boolean isSectionVisible(Page page, String sectionText) {
		page.waitForSelector(MY_PROFILE_SECTION_TITLE_XPATH.replace("{sectionText}", sectionText));
		return page.isVisible(MY_PROFILE_SECTION_TITLE_XPATH.replace("{sectionText}", sectionText));
	}

	public static String verifyPrivacyCenter(Page page) {
		String actualTextMessage = page.textContent(PRIVACY_CENTER_XPATH);
		return actualTextMessage;
	}

	public static void clickNewKeyButton(Page page) {
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("New Key")).click();

	}

	public static void enterKeyName(Page page, String keyName, String timestamp) {
		String uniqueKeyName = keyName + timestamp;
		page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Name")).click();
		page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Name")).fill(uniqueKeyName);
	}

	public static void enterDescription(Page page, String description, String timestamp) {
		String uniqueDescription = description + timestamp;
		page.click(DESCRIPTION_FIELD_BUTTON_XPATH);
		page.fill(DESCRIPTION_FIELD_BUTTON_XPATH, uniqueDescription);
	}

	public static void clickGenerateButton(Page page) {
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Generate")).click();
	}

	public static boolean isAccessKeyPopupVisible(Page page) {
		Locator accessKeyPopup = page.locator(".access-key-popup");
		return accessKeyPopup.isVisible();
	}

	public static String copyAccessKey(Page page, String KeyName) {
		page.click(KEY_COPY_BUTTON_XPATH.replace("{KeyName}", KeyName));
		return page.evaluate("navigator.clipboard.readText()").toString().trim();
	}

	public static String extractExampleSectionContent(Page page, String sectionName) {
		page.click(SECTION_ARROW_DOWN_XPATH.replace("{sectionName}", sectionName));
		page.click(SECTION_COPY_BUTTON_XPATH.replace("{sectionName}", sectionName));
		return page.evaluate("navigator.clipboard.readText()").toString().trim();
	}

	public static void clickOnCancelButton(Page page) {
		page.click(CANCEL_BUTTON_XPATH);
	}

	public static void clickOnDeleteIcon(Page page, String KeyName, String timestamp) {
		String deleteButtonXpath = DELETE_BUTTON_XPATH.replace("{KeyName}", KeyName + timestamp);
		page.waitForSelector(deleteButtonXpath);
		page.click(deleteButtonXpath);
	}

	public static String deleteKeyToastMessage(Page page) {
		String toastMessage = page.textContent(DELETE_KEY_TOAST_MESSAGE_XPATH).trim();
		return toastMessage;
	}

	public static String getExpectedAccessKeyTitle(Page page, String keyName, String timestamp) {
		String expTitle = keyName + timestamp;
		return expTitle;
	}

	public static String validateGeneratedKey(Page page, String keyName, String timestamp) {
		String generatedKeyName = page.textContent(GENERATED_KEY_XPATH.replace("{keyName}", keyName + timestamp))
				.trim();
		return generatedKeyName;
	}

	public static String validateDescriptionName(Page page, String description, String timestamp) {
		String generatedDescription = page
				.textContent(GENERATED_DESCRIPTION_XPATH.replace("{description}", description + timestamp)).trim();
		return generatedDescription;
	}

	public static String getExpectedDescriptionName(Page page, String description, String timestamp) {
		String expDescription = description + timestamp;
		return expDescription;
	}
}
