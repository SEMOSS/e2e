package aicore.pages;

import java.util.List;

import com.microsoft.playwright.Page;

import aicore.utils.SettingsPageUtils;

public class SettingsAdminQueryPage {
	private Page page;

	public SettingsAdminQueryPage(Page page) {
		this.page = page;
	}

	public void selectDatabase(String databaseName) {
		SettingsPageUtils.selectDatabase(page, databaseName);
	}

	public void enterQuery(String query) {
		SettingsPageUtils.enterQuery(page, query);
	}

	public void clickOnExecuteQueryButton() {
		SettingsPageUtils.clickOnExecuteQueryButton(page);
	}

	public int getTableHeaderCount() {
		return SettingsPageUtils.getTableHeaderCount(page);
	}

	public List<String> getTableHeaderNames() {
		return SettingsPageUtils.getTableHeaderNames(page);
	}

	public String verifyQueryExecutedToastMessage() {
		return SettingsPageUtils.verifyQueryExecutedToastMessage(page);
	}
}