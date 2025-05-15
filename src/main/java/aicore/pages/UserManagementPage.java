package aicore.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import aicore.utils.UserManagementPageUtils;

public class UserManagementPage {

	private Page page;
	private static final String ADD_MEMBER_XPATH = "[data-testid='AddIcon']";
	private static final String ADD_MEMBER_TYPE_XPATH = "//label[text()='Type']/parent::div";
	private static final String ADD_MEMBER_TYPE_NATIVE_XPATH = "//li[text()='NATIVE']";
	private static final String ADD_MEMBER_USERID_XPATH = "//label[text()='User Id']";
	private static final String ADD_MEMBER_NAME_XPATH = "//label[text()='Name']/following-sibling::div/input";
	private static final String ADD_MEMBER_EMAIL_XPATH = "//label[text()='Email']/following-sibling::div/input";
	private static final String ADD_MEMBER_PHONE_NUMBER_XPATH = "//label[text()='Phone Number']/following-sibling::div/input";
	private static final String ADD_MEMBER_EXTENSION_XPATH = "//label[text()='Extension']/following-sibling::div/input";
	private static final String ADD_MEMBER_TYPE_SAVE_XPATH = "//span[text()='Save']";
	private static final String ADD_MEMBER_PASSWORD_XPATH = "//input[@type='password']";
	private static final String ADD_MEMBER_TOAST_MESSAGE_XPATH = "//div[text()='Successfully added user']";
	private static final String EDIT_ICON_XPATH = "//p[text()='Name1']/ancestor::td/following-sibling::td//*[name()='svg'][@data-testid='EditIcon']";
	private static final String MODEL_DROPDOWN_XPATH = "//div[text()='None']";
	private static final String TOKEN_VALUE_XPATH = "//li[text()='{dropdown_value}']";
	private static final String MAX_TOKEN_VALUE_XPATH = "//label[text()='Max Tokens']";
	private static final String FREQUENCY_DROPDOWN_XPATH = "//label[text()='Frequency']//parent::div//div";
	private static final String WEEKELY_VALUE_XPATH = "//li[text()='{dropdown_option}']";
	private static final String MODEL_LIMIT_XPATH = "//p[text()='Name1']/ancestor::td/following-sibling::td[text()='{limitValue}']";
	private static final String SEARCH_BUTTON_XPATH = "[placeholder=\"Search Users\"]";
	private static final String SELECT_ALL_BUTTON_XPATH = "//label[@class='MuiFormControlLabel-root MuiFormControlLabel-labelPlacementEnd css-1o4vkg1-MuiFormControlLabel-root']/parent::th//input";
	private static final String DELETE_MEMBER_TOAST_MESSAGE_XPATH = "//div[text()='Successfully deleted users']";
	private static final String DELETE_SELECTED_BUTTON_XPATH = "//span[text()='Delete Selected']";
	private static final String SEARCH_ICON_XPATH = "[data-testid=\"SearchIcon\"]";
	private static final String TOAST_MESSAGE_CLOSE_XPATH = "[data-testid='CloseIcon']";
	private static final String NATIVE_USERNAME_XPATH = "//label[text()='Username']/following-sibling::div//input";
	private static final String NATIVE_PASSWORD_XPATH = "//input[contains(@class, 'MuiInputBase-input') and contains(@class, 'MuiOutlinedInput-input') and @type='password']";
	private static final String LOGIN_WITH_NATIVE_XPATH = "//button[@type='submit' and contains(@class, 'MuiButton-containedPrimary')]";
	private static final String DISPLAYED_NAME_XPATH = "//legend[span[text()='Name']]/ancestor::div[contains(@class, 'MuiInputBase-root')]//input";
	private static final String DISPLAYED_USER_ID_XPATH = "//input[contains(@value, 'TestId')]";
	private static final String DISPLAYED_USERNAME_XPATH = "//label[text()='Username']/following-sibling::p";
	private static final String DISPLAYED_EMAIL_XPATH = "//legend[span[text()='Email']]/ancestor::div[contains(@class, 'MuiInputBase-root')]//input";

	public UserManagementPage(Page page) {
		this.page = page;
	}

	public void checkAddMemberButton() {
		UserManagementPageUtils.checkAddMemberButton(page);
	}

	public void checkAddUserButton() {
		UserManagementPageUtils.checkAddUserButton(page);
	}

	public void clickAddUserButton() {
		UserManagementPageUtils.clickAddUserButton(page);
	}

	public void clickTypeDropdown() {
		UserManagementPageUtils.clickTypeDropdown(page);
	}

	public void clickNativeDropdownValue() {
		UserManagementPageUtils.clickNativeDropdownValue(page);
	}

	public void fillUserId(String UserId) throws InterruptedException {
		UserManagementPageUtils.fillUserId(page, UserId);
	}

	public void fillName(String Name) throws InterruptedException {
		UserManagementPageUtils.fillName(page, Name);
	}

	public void fillEmail(String Email) {
		UserManagementPageUtils.fillEmail(page, Email);
	}

	public void fillPhoneNumber(String Number) {
		UserManagementPageUtils.fillPhoneNumber(page, Number);
	}

	public void fillExtension(String Extension) {
		UserManagementPageUtils.fillExtension(page, Extension);
	}

	public void clickSaveButton() {
		UserManagementPageUtils.clickSaveButton(page);
	}

	public String userCreationToastMessage() {
		return UserManagementPageUtils.userCreationToastMessage(page);
	}

	public void closeToastMessage() {
		UserManagementPageUtils.closeToastMessage(page);
	}

	public void clickOnEditUser() {
		UserManagementPageUtils.clickOnEditUser(page);
	}

	public void clickModelUserDropdown() {
		UserManagementPageUtils.clickModelUserDropdown(page);
	}

	public void selectTokenValueDropdown(String dropdownOption) {
		UserManagementPageUtils.selectTokenValueDropdown(page, dropdownOption);
	}

	public void fillMaxTokensValue(String value) {
		UserManagementPageUtils.fillMaxTokensValue(page, value);
	}

	public void clickFrequencyDropdown() {
		UserManagementPageUtils.clickFrequencyDropdown(page);
	}

	public void selectWeeklyValueDropdown(String dropdownValue) {
		UserManagementPageUtils.selectWeeklyValueDropdown(page, dropdownValue);
	}

	public String getModelLimitValue(String limit) {
		return UserManagementPageUtils.getModelLimitValue(page, limit);
	}

	public void checkSearchButton() {
		UserManagementPageUtils.checkSearchButton(page);
	}

	public void searchUser() {
		UserManagementPageUtils.searchUser(page);
	}

	public void clickSelectAllButton() {
		UserManagementPageUtils.clickSelectAllButton(page);
	}

	public void clickDeleteSelectedButton() {
		UserManagementPageUtils.clickDeleteSelectedButton(page);
	}

	public String userDeletionToastMessage() {
		return UserManagementPageUtils.userDeletionToastMessage(page);
	}

	public void clickOnAccessKeyValue() {
		UserManagementPageUtils.clickOnAccessKeyValue(page);

	}

	public void updateAccessKeyValue(String newValue) {
		UserManagementPageUtils.updateAccessKeyValue(page, newValue);
	}

	public void clickSaveButtonOFAdfs() {
		UserManagementPageUtils.clickSaveButtonOFAdfs(page);
	}

	public String getToastMessage(String expectedMessage) {
		return UserManagementPageUtils.getToastMessage(page, expectedMessage);
	}

	public void waitForToastMessageToDisappear(String expectedMessage) {
		UserManagementPageUtils.waitForToastMessageToDisappear(page, expectedMessage);
	}

	public void clickOnAuthenticationDropdown() {
		UserManagementPageUtils.clickOnAuthenticationDropdown(page);
	}

	public void searchAndSelectOption(String optionText) {
		UserManagementPageUtils.searchAndSelectOption(page, optionText);
	}

	public void loginAsUser(String username, String password) {
		page.fill(NATIVE_USERNAME_XPATH, username);
		page.fill(NATIVE_PASSWORD_XPATH, password);
		page.click(LOGIN_WITH_NATIVE_XPATH);
	}

//	public String getDisplayedName() {
//
//		return page.locator(DISPLAYED_NAME_XPATH).textContent().trim();
//
//	}

	public String getDisplayedName() throws InterruptedException {
		Locator nameInput = page.locator("//label[contains(text(), 'Name')]/following::input[1]");
		return nameInput.getAttribute("value");
	}

	public String getDisplayedId() throws InterruptedException {
		Locator userIdInput = page.locator("//label[contains(text(), 'User Id')]/following::input[1]");
		return userIdInput.getAttribute("value");
	}

//	public String getDisplayedEmail() {
//
//		return page.locator(DISPLAYED_EMAIL_XPATH).textContent().trim();
//
//	}

	public String getDisplayedEmail() throws InterruptedException {
		Locator emailInput = page.locator("//label[contains(text(), 'Email')]/following::input[1]");
		return emailInput.getAttribute("value");
	}

	public void fillPassword(String password) {
		page.locator(ADD_MEMBER_PASSWORD_XPATH).isVisible();
		page.locator(ADD_MEMBER_PASSWORD_XPATH).fill(password);
	}

}
