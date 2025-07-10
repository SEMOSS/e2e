package aicore.pages.app;

import com.microsoft.playwright.Page;

import aicore.utils.page.app.CreateAppPopupUtils;

public class CreateAppPopupPage {
	private Page page;
	private String timestamp;

	public CreateAppPopupPage(Page page, String timestamp) {
		this.page = page;
		this.timestamp = timestamp;
	}

	public void clickOnGetStartedButton(String appType) {
		CreateAppPopupUtils.clickOnGetStartedButton(page, appType);
	}

	public void enterAppName(String appName) {
		CreateAppPopupUtils.enterAppName(page, appName, timestamp);
	}

	public void enterAppDescription(String appDescription) {
		CreateAppPopupUtils.enterAppDescription(page, appDescription);
	}

	public void enterTags(String tagName) {
		CreateAppPopupUtils.enterTags(page, tagName);
	}

	public void clickOnCreateButton() {
		CreateAppPopupUtils.clickOnCreateButton(page);
	}
}
