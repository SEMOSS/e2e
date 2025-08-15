package aicore.pages;

import java.util.List;

import com.microsoft.playwright.Page;

import aicore.utils.settings.AdminQueryPageUtils;

public class SettingsAdminQueryPage {
	private Page page;

	public SettingsAdminQueryPage(Page page) {
		this.page = page;
	}

	public void selectDatabase(String databaseName) {
		AdminQueryPageUtils.selectDatabase(page, databaseName);
	}

	public void clickOnSelectDatabase() {
		AdminQueryPageUtils.clickOnSelectDatabase(page);
	}

	public void enterQuery(String query) {
		AdminQueryPageUtils.enterQuery(page, query);
	}

	public void clickOnExecuteQueryButton() {
		AdminQueryPageUtils.clickOnExecuteQueryButton(page);
	}

	public int getTableHeaderCount() {
		return AdminQueryPageUtils.getTableHeaderCount(page);
	}

	public List<String> getTableHeaderNames() {
		return AdminQueryPageUtils.getTableHeaderNames(page);
	}

	public String verifyQueryExecutedToastMessage() {
		return AdminQueryPageUtils.verifyQueryExecutedToastMessage(page);
	}

	public void enterRowCount(String count) {
		AdminQueryPageUtils.enterRowCount(page, count);
	}

	public int totalCountOfRows() {
		return AdminQueryPageUtils.totalCountOfRows(page);
	}

}