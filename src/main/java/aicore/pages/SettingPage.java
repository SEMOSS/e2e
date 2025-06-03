package aicore.pages;

import com.microsoft.playwright.Page;

import aicore.utils.settings.SettingsPageUtils;

public class SettingPage {

	private Page page;

	public SettingPage(Page page) {
		this.page = page;
	}

	public boolean checkAdminButton() {
		return SettingsPageUtils.checkAdminButton(page);
	}

	public void clickOnAdminButton() {
		SettingsPageUtils.clickOnAdminButton(page);
	}

	public void checkCardVisible(String cardName) {
		SettingsPageUtils.checkCardVisible(page, cardName);
	}

	public void clickOnCard(String cardName) {
		SettingsPageUtils.clickOnCard(page, cardName);
	}

	public void checkAddUserButton() {
		SettingsPageUtils.checkAddUserButton(page);

	}

	public void checkAdminOnButton() {
		SettingsPageUtils.checkAdminOnButton(page);
	}

	public int checkCountOfUsers() {
		return SettingsPageUtils.checkCountOfUsers(page);

	}

	public int countOfPages() {
		return SettingsPageUtils.countOfPages(page);
	}

	public void checkForwardButton() {
		SettingsPageUtils.checkForwardButton(page);
	}

	public void checkBackwardButton() {
		SettingsPageUtils.checkBackwardButton(page);
	}

	public int checkRecordsOnPage() {
		return SettingsPageUtils.checkRecordsOnPage(page);
	}

	public void clickNumberOfRows(String rowsPerPageValue) {
		SettingsPageUtils.clickNumberOfRows(page, rowsPerPageValue);
	}
	public void clickOnSearchButton() {
		SettingsPageUtils.clickOnSearchButton(page);
	}

	public void clickOnSearchBox() {
		SettingsPageUtils.clickOnSearchBox(page);
	}

	public void enterUsername(String username) {
		SettingsPageUtils.enterUsername(page, username);
	}

	public String checkUsername(String username) {
		return SettingsPageUtils.checkUsername(page, username);
	}
}

