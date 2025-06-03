package aicore.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import aicore.utils.LoginPageUtils;
import aicore.utils.UserManagementPageUtils;

public class UserManagementPage {

	private Page page;
	private static final String ADD_MEMBER_PASSWORD_XPATH = "//input[@type='password']";

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
		LoginPageUtils.enterUsernameAndPassword(page, username, password);
		LoginPageUtils.clickLoginButton(page);
	}

	public String getDisplayedName() throws InterruptedException {
		Locator nameInput = page.locator("//label[contains(text(), 'Name')]/following::input[1]");
		return nameInput.getAttribute("value");
	}

	public String getDisplayedId() throws InterruptedException {
		Locator userIdInput = page.locator("//label[contains(text(), 'User Id')]/following::input[1]");
		return userIdInput.getAttribute("value");
	}


	public String getDisplayedEmail() throws InterruptedException {
		Locator emailInput = page.locator("//label[contains(text(), 'Email')]/following::input[1]");
		return emailInput.getAttribute("value");
	}

	public void fillPassword(String password) {
		page.locator(ADD_MEMBER_PASSWORD_XPATH).isVisible();
		page.locator(ADD_MEMBER_PASSWORD_XPATH).fill(password);
	}

}
