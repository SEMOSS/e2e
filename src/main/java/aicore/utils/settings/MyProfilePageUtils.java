package aicore.utils.settings;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import aicore.utils.AICorePageUtils;

public class MyProfilePageUtils {

	///// SETTING MY PROFILE PAGE
	private static final String MY_PROFILE_SECTION_TITLE_XPATH = "//*[text()='{sectionText}']";
	private static final String PRIVACY_CENTER_XPATH = "//span[text()='Privacy Center']";
	private static final String CANCEL_BUTTON_XPATH = "//button[contains(@class, 'MuiButtonBase-root') and .//span[normalize-space()='Close']]";
	private static final String DELETE_KEY_TOAST_MESSAGE_XPATH = "//div[contains(@class, 'MuiAlert-message')]";


	//TODO check if this or the other works
//	private static final String EDIT_PROFILE_INFORMATION_STATE_XPATH = "//label[text()='{fieldName}']/following::input | //span[text()='{fieldName}']/parent::button";
	private static final String EDIT_PROFILE_INFORMATION_STATE_XPATH = "//div//button//following-sibling::button[text()='{fieldName}'] | //div//button[text()='{fieldName}']";
	private static final String UPDATED_NAME_XPATH = "//span[text()='{name}']";
	private static final String CLICK_ON_CHANGE_PASSWORD_LINK_TEXT = "Change Password";
	private static final String CHANGE_PASSWORD_POP_TITLE_XPATH = "//*[@data-slot='dialog-title']";
	private static final String CLICK_ON_PROFILE_ICON_XPATH = "//ul[@aria-label='user navigation']";
	private static final String ENTER_PASSWORD_XPATH = "//div//span[text()='{fieldName}']/parent::div//div[contains(@class,'relative')]//input[@type='password']";
	private static final String PASSWORDS_DO_NOT_MATCH_ERROR_XPATH = "//span[text()='The passwords do not match']";
	private static final String TEXT_GENERATION_MODEL_DROPDOWN_DATATESTID = "myProfilePage-default-model-select";
	private static final String CODE_GENERATION_MODEL_DROPDOWN_DATATESTID = "myProfilePage-secondary-model-select";
	private static final String MODEL_OPTION_XPATH = "//span[text()='{modelName}']";
	private static final String TOAST_MESSAGE_XPATH = "//li[@data-type='success']";

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

	public static boolean isAccessKeyPopupVisible(Page page) {
		Locator accessKeyPopup = page.locator(".access-key-popup");
		return accessKeyPopup.isVisible();
	}

	public static void clickOnCancelButton(Page page) {
		page.click(CANCEL_BUTTON_XPATH);
	}

	public static String deleteKeyToastMessage(Page page) {
		// TODO toast message should probably use a generic util
		String toastMessage = page.textContent(DELETE_KEY_TOAST_MESSAGE_XPATH).trim();
		return toastMessage;
	}

	public static boolean isFieldEnabled(Page page, String fieldName) {
		Locator fieldState = page.locator(EDIT_PROFILE_INFORMATION_STATE_XPATH.replace("{fieldName}", fieldName))
				.first();
		String disabledAttr = fieldState.getAttribute("disabled");
		if (disabledAttr != null) {
			return false;
		}
		return fieldState.isEnabled();
	}

	public static void updateField(Page page, String fieldName, String fieldValue) {
		Locator fieldLocator = page.locator(EDIT_PROFILE_INFORMATION_STATE_XPATH.replace("{fieldName}", fieldName))
				.first();
		fieldLocator.clear();
		fieldLocator.fill(fieldValue);
	}

	public static boolean getUpdatedInfo(Page page, String name) {
		Locator updatedName = page.locator(UPDATED_NAME_XPATH.replace("{name}", name)).first();
		return updatedName.isVisible();
	}

	public static void clickOnChangePasswordLink(Page page) {
		page.getByText(CLICK_ON_CHANGE_PASSWORD_LINK_TEXT).click();
	}

	public static boolean changePasswordTitle(Page page, String title) {
		Locator titleLocator = page.locator(CHANGE_PASSWORD_POP_TITLE_XPATH);
		return titleLocator.isVisible();
	}

	public static void clickOnProfileIcon(Page page) {
		page.locator(CLICK_ON_PROFILE_ICON_XPATH).click();
	}

	public static void enterThePassword(Page page, String fieldName, String fieldValue) {
		Locator passwordField = page.locator(ENTER_PASSWORD_XPATH.replace("{fieldName}", fieldName)).first();
		passwordField.click();
		passwordField.fill(fieldValue);
	}

	public static String getErrorMessage(Page page, String message) {
		String errorMessage = page.locator(PASSWORDS_DO_NOT_MATCH_ERROR_XPATH).textContent().trim();
		return errorMessage;
	}

	// Model Dropdown Methods

	public static void selectModelFromTextGenerationModelDropdown(Page page, String modelName) {
		page.getByTestId(TEXT_GENERATION_MODEL_DROPDOWN_DATATESTID).click();
		page.waitForSelector(MODEL_OPTION_XPATH.replace("{modelName}", modelName));
		page.click(MODEL_OPTION_XPATH.replace("{modelName}", modelName));
	}

	public static void selectModelFromCodeGenerationModelDropdown(Page page, String modelName) {
		page.getByTestId(CODE_GENERATION_MODEL_DROPDOWN_DATATESTID).click();
		Locator modelOption = page.locator(MODEL_OPTION_XPATH.replace("{modelName}", modelName)).nth(1);
		AICorePageUtils.waitFor(modelOption);
		modelOption.click();
	}

	public static String getToastMessage(Page page) {
		Locator toastLocator = page.locator(TOAST_MESSAGE_XPATH).first();
		return toastLocator.textContent().trim();
	}
}