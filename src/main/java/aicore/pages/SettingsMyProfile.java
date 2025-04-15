package aicore.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class SettingsMyProfile {
	private Page page;
	private String timestamp;
	private static final String OPEN_SETTINGS_ICON_XPATH = "Settings-icon";
	private static final String MY_PROFILE_SECTION_TITLE_XPATH = "//h6[text()='{sectionText}']";
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

	public SettingsMyProfile(Page page, String timestamp) {
		this.page = page;
		this.timestamp = timestamp;
	}

	public void openSettingsIcon() {
		page.getByTestId(OPEN_SETTINGS_ICON_XPATH).click();;
	}

	public void clickOnMyProfileCard() {
		page.getByText("My profile").click();
	}

	public boolean isSectionVisible(String sectionText) {
		page.waitForSelector(MY_PROFILE_SECTION_TITLE_XPATH.replace("{sectionText}", sectionText));
		return page.isVisible(MY_PROFILE_SECTION_TITLE_XPATH.replace("{sectionText}", sectionText));
	}

	public String verifyPrivacyCenter() {
		String actualTextMessage = page.textContent(PRIVACY_CENTER_XPATH);
		return actualTextMessage;
	}

	public void clickNewKeyButton() {
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("New Key")).click();

	}

	public void enterKeyName(String keyName) {
		String uniqueKeyName = keyName + timestamp;
		page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Name")).click();
		page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Name")).fill(uniqueKeyName);
	}

	public void enterDescription(String description) {
		String uniqueDescription = description + timestamp;
		page.click(DESCRIPTION_FIELD_BUTTON_XPATH);
		page.fill(DESCRIPTION_FIELD_BUTTON_XPATH, uniqueDescription);

	}

	public void clickGenerateButton() {
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Generate")).click();
	}

	public boolean isAccessKeyPopupVisible() {
		Locator accessKeyPopup = page.locator(".access-key-popup");
		return accessKeyPopup.isVisible();
	}

	public String copyAccessKey(String KeyName) {
		page.click(KEY_COPY_BUTTON_XPATH.replace("{KeyName}", KeyName));
		return page.evaluate("navigator.clipboard.readText()").toString().trim();
	}

	public String extractExampleSectionContent(String sectionName) {
		page.click(SECTION_ARROW_DOWN_XPATH.replace("{sectionName}", sectionName));
		page.click(SECTION_COPY_BUTTON_XPATH.replace("{sectionName}", sectionName));
		return page.evaluate("navigator.clipboard.readText()").toString().trim();
	}

	public void clickOnCancelButton() {
		page.click(CANCEL_BUTTON_XPATH);
	}

	public void clickOnDeleteIcon(String KeyName) {
		String deleteButtonXpath = DELETE_BUTTON_XPATH.replace("{KeyName}", KeyName + timestamp);
		page.waitForSelector(deleteButtonXpath);
		page.click(deleteButtonXpath);
	}

	public String deleteKeyToastMessage() {
		String toastMessage = page.textContent(DELETE_KEY_TOAST_MESSAGE_XPATH).trim();
		return toastMessage;
	}

	public String getExpectedAccessKeyTitle(String keyName) {
		String expTitle = keyName + timestamp;
		return expTitle;
	}

	public String validateGeneratedKey(String keyName) {
		String generatedKeyName = page.textContent(GENERATED_KEY_XPATH.replace("{keyName}", keyName + timestamp))
				.trim();
		return generatedKeyName;
	}

	public String validateDescriptionName(String description) {
		String generatedDescription = page
				.textContent(GENERATED_DESCRIPTION_XPATH.replace("{description}", description + timestamp)).trim();
		return generatedDescription;
	}

	public String getExpectedDescriptionName(String description) {
		String expDescription = description + timestamp;
		return expDescription;
	}

}
