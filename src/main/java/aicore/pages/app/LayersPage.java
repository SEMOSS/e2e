package aicore.pages.app;

import com.microsoft.playwright.Page;

import aicore.utils.page.app.LayersPageUtils;

public class LayersPage {
	private Page page;

	public LayersPage(Page page) {
		this.page = page;
	}

	public void clickOnTabInLeftPanel(String tabName) {
		LayersPageUtils.clickOnTabInLeftPanel(page, tabName);
	}

	public void clickOnAddNewPageIcon() {
		LayersPageUtils.clickOnAddNewPageIcon(page);
	}

	public boolean isPagePresent(String pageName) {
		return LayersPageUtils.isPagePresent(page, pageName);
	}

	public boolean isUserOnPage(String pageName) {
		return LayersPageUtils.isUserOnPage(page, pageName);
	}

	public void clickOnPageInLeftPane(String pageName) {
		LayersPageUtils.clickOnPageInLeftPane(page, pageName);
	}
}
