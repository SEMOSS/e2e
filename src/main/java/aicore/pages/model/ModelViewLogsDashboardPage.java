package aicore.pages.model;

import java.util.List;

import com.microsoft.playwright.Page;

public class ModelViewLogsDashboardPage {

	private Page page;

	public ModelViewLogsDashboardPage(Page page) {
		this.page = page;
	}

	public void navigateToModelInsightDashboard() {
		ModelViewLogsDashboardPageUtils.navigateToModelInsightDashboard(page);
	}

	public boolean verifyDashboardHeading() {
		return ModelViewLogsDashboardPageUtils.verifyDashboardHeading(page);
	}

	public boolean verifyRefreshButton() {
		return ModelViewLogsDashboardPageUtils.verifyRefreshButton(page);
	}

	public boolean verifyEventHistorySection() {
		return ModelViewLogsDashboardPageUtils.verifyEventHistorySection(page);
	}

	public boolean verifyPromptResponseTimelineSection() {
		return ModelViewLogsDashboardPageUtils.verifyPromptResponseTimelineSection(page);
	}

	public boolean verifyTableExists() {
		return ModelViewLogsDashboardPageUtils.verifyTableExists(page);
	}

	public boolean verifyTableColumnsExist(List<String> columnNames) {
		return ModelViewLogsDashboardPageUtils.verifyTableColumnsExist(page, columnNames);
	}

	public List<String> getTableColumnNames() {
		return ModelViewLogsDashboardPageUtils.getTableColumnNames(page);
	}

	public void clickRefreshButton() {
		ModelViewLogsDashboardPageUtils.clickRefreshButton(page);
	}
}
