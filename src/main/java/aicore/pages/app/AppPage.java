package aicore.pages.app;

import com.microsoft.playwright.Page;

import aicore.utils.page.app.AppPageUtils;

public class AppPage {
	private Page page;
	private String timestamp;

	public AppPage(Page page, String timestamp) {
		this.page = page;
		this.timestamp = timestamp;
	}

	public void clickOnCreateNewAppButton() {
		AppPageUtils.clickOnCreateNewAppButton(page);
	}

	public void searchApp(String appName) {
		AppPageUtils.searchApp(page, appName, timestamp);
	}

	public void clickOnAppCard(String appName) {
		AppPageUtils.clickOnAppCard(page, appName, timestamp);
	}
}
