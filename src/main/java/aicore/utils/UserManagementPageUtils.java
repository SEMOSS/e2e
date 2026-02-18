package aicore.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

import aicore.framework.ConfigUtils;

public class UserManagementPageUtils {

	private static final String ADD_MEMBER_XPATH = "[data-testid='AddIcon']";
	private static final String ADD_MEMBER_TYPE_XPATH = "//label[text()='Type']/parent::div";
	private static final String ADD_MEMBER_TYPE_NATIVE_XPATH = "//li[text()='NATIVE']";
	private static final String ADD_MEMBER_USERID_XPATH = "//label[text()='User Id']";
	private static final String ADD_MEMBER_NAME_XPATH = "//label[text()='Name']/following-sibling::div/input";
	private static final String ADD_MEMBER_EMAIL_XPATH = "//label[text()='Email']/following-sibling::div/input";
	private static final String ADD_MEMBER_PHONE_NUMBER_XPATH = "//label[text()='Phone Number']/following-sibling::div/input";
	private static final String ADD_MEMBER_EXTENSION_XPATH = "//label[text()='Extension']/following-sibling::div/input";
	private static final String ADD_MEMBER_TYPE_SAVE_XPATH = "//span[text()='Save']";
	private static final String ADD_MEMBER_TOAST_MESSAGE_XPATH = "//div[text()='Successfully added user']";
	private static final String EDIT_ICON_XPATH = "//p[text()='Name1']/ancestor::td/following-sibling::td//*[name()='svg'][@data-testid='EditIcon']";
	private static final String MODEL_DROPDOWN_XPATH = "//div[text()='None']";
	private static final String TOKEN_VALUE_XPATH = "//li[text()='{dropdown_value}']";
	private static final String MAX_TOKEN_VALUE_XPATH = "//label[text()='Max Tokens']";
	private static final String FREQUENCY_DROPDOWN_XPATH = "//label[text()='Frequency']//parent::div//div";
	private static final String WEEKELY_VALUE_XPATH = "//li[text()='{dropdown_option}']";
	private static final String MODEL_LIMIT_XPATH = "//p[text()='Name1']/ancestor::td/following-sibling::td[text()='{limitValue}']";
	private static final String SEARCH_BUTTON_XPATH = "[placeholder=\"Search Users\"]";
	private static final String SELECT_ALL_BUTTON_XPATH = "//label[@id='userTable-checkbox-selectAll']";// "//th//label//span//input[@type='checkbox']";
	private static final String DELETE_MEMBER_TOAST_MESSAGE_XPATH = "//div[text()='Successfully deleted users']";
	private static final String DELETE_SELECTED_BUTTON_XPATH = "//span[text()='Delete Selected']";
	private static final String SEARCH_ICON_XPATH = "//div[@id='home__content']//*[@data-testid='SearchIcon']";
	private static final String SEARCH_BAR_XPATH = "//div[@role=\"region\"]//input[@placeholder='Search']";
	private static final String TOAST_MESSAGE_CLOSE_XPATH = "[data-testid='CloseIcon']";
	private static final String CONFIGERATION_KEY_VALUE_XPATH = "//input[@value='access_keys_allowed']/../../following-sibling::div//input";
	private static final String SAVE_BUTTON_ADFS_XPATH = "//button[.//span[text()='Save']]";
	private static final String ADFS_TOAST_MESSAGE_XPATH = "//div[text()='{message}']";

	public static void checkAddMemberButton(Page page) {
		page.locator(ADD_MEMBER_XPATH).isVisible();
	}

	public static void checkAddUserButton(Page page) {
		page.locator(ADD_MEMBER_XPATH).isVisible();
	}

	public static void clickAddUserButton(Page page) {
		page.locator(ADD_MEMBER_XPATH).click();
	}

	public static void clickTypeDropdown(Page page) {
		page.locator(ADD_MEMBER_TYPE_XPATH).isVisible();
		page.locator(ADD_MEMBER_TYPE_XPATH).click();
	}

	public static void clickNativeDropdownValue(Page page) {
		page.locator(ADD_MEMBER_TYPE_NATIVE_XPATH).isVisible();
		page.locator(ADD_MEMBER_TYPE_NATIVE_XPATH).click();
	}

	public static void fillUserId(Page page, String UserId) throws InterruptedException {
		page.locator(ADD_MEMBER_USERID_XPATH).isVisible();
		page.locator(ADD_MEMBER_USERID_XPATH).fill(UserId);
	}

	public static void fillName(Page page, String Name) throws InterruptedException {
		page.locator(ADD_MEMBER_NAME_XPATH).isVisible();
		page.locator(ADD_MEMBER_NAME_XPATH).fill(Name);
	}

	public static void fillEmail(Page page, String Email) {
		page.locator(ADD_MEMBER_EMAIL_XPATH).isVisible();
		page.locator(ADD_MEMBER_EMAIL_XPATH).fill(Email);
	}

	public static void fillPhoneNumber(Page page, String Number) {
		page.locator(ADD_MEMBER_PHONE_NUMBER_XPATH).isVisible();
		page.locator(ADD_MEMBER_PHONE_NUMBER_XPATH).fill(Number);
	}

	public static void fillExtension(Page page, String Extension) {
		page.locator(ADD_MEMBER_EXTENSION_XPATH).isVisible();
		page.locator(ADD_MEMBER_EXTENSION_XPATH).fill(Extension);
	}

	public static void clickSaveButton(Page page) {
		page.setViewportSize(1350, 650);
		Locator saveButton = page.locator(ADD_MEMBER_TYPE_SAVE_XPATH);
		saveButton.scrollIntoViewIfNeeded();
		saveButton.hover();
		saveButton.click(new Locator.ClickOptions().setForce(true));
	}

	public static String userCreationToastMessage(Page page) {
		// page.locator(ADD_MEMBER_TOAST_MESSAGE_XPATH)
		// .waitFor(new
		// Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		// page.locator(ADD_MEMBER_TOAST_MESSAGE_XPATH).textContent().trim();
		Locator toasterMessage = page.getByTestId("notification-success-alert");
		AICorePageUtils.waitFor(toasterMessage);
		String toastMessage = toasterMessage.textContent().trim();
		return toastMessage;
	}

	public static void closeToastMessage(Page page) {
		page.locator(TOAST_MESSAGE_CLOSE_XPATH).isVisible();
		page.locator(TOAST_MESSAGE_CLOSE_XPATH).click();
	}

	public static void clickOnEditUser(Page page) {
		Locator editIcon = page.locator(EDIT_ICON_XPATH);
		AICorePageUtils.waitFor(editIcon);
		page.locator(EDIT_ICON_XPATH).click();
	}

	public static void clickModelUserDropdown(Page page) {
		page.locator(MODEL_DROPDOWN_XPATH).isVisible();
		page.locator(MODEL_DROPDOWN_XPATH).click();
	}

	public static void selectTokenValueDropdown(Page page, String dropdownOption) {
		page.locator(TOKEN_VALUE_XPATH.replace("{dropdown_value}", dropdownOption)).isVisible();
		page.locator(TOKEN_VALUE_XPATH.replace("{dropdown_value}", dropdownOption)).click();
	}

	public static void fillMaxTokensValue(Page page, String value) {
		page.locator(MAX_TOKEN_VALUE_XPATH).isVisible();
		page.locator(MAX_TOKEN_VALUE_XPATH).fill(value);
	}

	public static void clickFrequencyDropdown(Page page) {
		page.locator(FREQUENCY_DROPDOWN_XPATH).first().isVisible();
		page.locator(FREQUENCY_DROPDOWN_XPATH).first().click();

	}

	public static void selectWeeklyValueDropdown(Page page, String dropdownValue) {
		page.locator(WEEKELY_VALUE_XPATH.replace("{dropdown_option}", dropdownValue)).isVisible();
		page.locator(WEEKELY_VALUE_XPATH.replace("{dropdown_option}", dropdownValue)).click();
	}

	public static String getModelLimitValue(Page page, String limit) {
		page.locator(MODEL_LIMIT_XPATH.replace("{limitValue}", limit)).isVisible();
		return page.locator(MODEL_LIMIT_XPATH.replace("{limitValue}", limit)).textContent();
	}

	public static void checkSearchButton(Page page) {
		page.isVisible(SEARCH_ICON_XPATH);
		page.click(SEARCH_ICON_XPATH);
	}

	public static void searchUser(Page page) {
		String Base = ConfigUtils.getValue("baseUrl");
		if (Base.contains("8080")) {
			String responseURL = Base + "Monolith/api/auth/admin/user/getAllUsers?filterWord=UserId&offset=0&limit=0";
			page.waitForResponse(responseURL, () -> {
				// Triggers the response
				page.fill(SEARCH_BUTTON_XPATH, "UserId");
				page.waitForTimeout(1000);
			});
		} else {
			page.fill(SEARCH_BUTTON_XPATH, "UserId");
			page.waitForTimeout(1000);
		}
	}

	public static void clickSelectAllButton(Page page) {
		Locator checkbox = page.locator(SELECT_ALL_BUTTON_XPATH);
		AICorePageUtils.waitFor(checkbox);
		checkbox.scrollIntoViewIfNeeded();
		checkbox.click();
		page.waitForTimeout(2000);
	}

	public static void clickDeleteSelectedButton(Page page) {
		page.isVisible(DELETE_SELECTED_BUTTON_XPATH);
		page.click(DELETE_SELECTED_BUTTON_XPATH);
	}

	public static String userDeletionToastMessage(Page page) {
		String toastMessage = page.textContent(DELETE_MEMBER_TOAST_MESSAGE_XPATH).trim();
		return toastMessage;
	}

	public static void clickOnAccessKeyValue(Page page) {
		page.locator(CONFIGERATION_KEY_VALUE_XPATH).click();

	}

	public static void updateAccessKeyValue(Page page, String newValue) {
		Locator valueInput = page.locator(CONFIGERATION_KEY_VALUE_XPATH);
		valueInput.fill("");
		valueInput.fill(newValue);
	}

	public static void clickSaveButtonOFAdfs(Page page) {
		page.locator(SAVE_BUTTON_ADFS_XPATH).click();
	}

	public static String getToastMessage(Page page, String expectedMessage) {
		Locator toast = page.locator(ADFS_TOAST_MESSAGE_XPATH.replace("{message}", expectedMessage));
		AICorePageUtils.waitFor(toast);
		return toast.textContent().trim();
	}

	public static void waitForToastMessageToDisappear(Page page, String expectedMessage) {
		page.getByRole(AriaRole.ALERT).filter(new Locator.FilterOptions().setHasText(expectedMessage))
				.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
	}

	public static void clickOnAuthenticationDropdown(Page page) {
		page.getByTestId("KeyboardArrowDownIcon").click();
	}

	public static void searchAndSelectOption(Page page, String optionText) {
//		// Fill the search box
//		Locator searchInput = page.locator(SEARCH_BAR_XPATH);
//		searchInput.fill("");
//		searchInput.fill(optionText);

		// Click on the result button
		Locator resultButton = page.locator("//button[.//span[text()='" + optionText + "']]");
		AICorePageUtils.waitFor(resultButton);
		resultButton.click();
	}

}
