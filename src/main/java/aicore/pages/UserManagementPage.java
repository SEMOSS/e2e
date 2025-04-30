package aicore.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

import aicore.utils.UserManagementPageUtils;

public class UserManagementPage {

	private Page page;

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

}
