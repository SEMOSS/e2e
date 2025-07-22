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

	public void searchAppId(String appId) {
		AppPageUtils.searchAppId(page, appId);
	}

	public void clickOnAppCard(String appName) {
		AppPageUtils.clickOnAppCard(page, appName, timestamp);
	}

	public void clickOnMoreVertIcon(String appName) {
		AppPageUtils.clickOnMoreVertIcon(page, appName);
	}

	public void clickOnOption(String optionName) {
		AppPageUtils.clickOnOption(page, optionName);
	}

	public String getCopiedId() {
		return AppPageUtils.getCopiedId();
	}

	public String getAppIdCopiedToastMessage() {
		return AppPageUtils.getAppIdCopiedToastMessage(page);
	}

	public void enterCloneAppName(String appName) {
		AppPageUtils.enterCloneAppName(page, appName, timestamp);
	}

	public void enterCloneAppDescription(String appDescription) {
		AppPageUtils.enterCloneAppDescription(page, appDescription);
	}

	public void clickOnButton(String buttonName) {
		AppPageUtils.clickOnButton(page, buttonName);
	}

	public void MakeAppPublic() {
		AppPageUtils.MakeAppPublic(page);
	}

	public boolean isAppDisplayedOnPage(String appName) {
		return AppPageUtils.isAppDisplayedOnPage(page, appName, timestamp);
	}

	public void clickOnDeleteButton(String buttonName) {
		AppPageUtils.clickOnDeleteButton(page, buttonName);
	}

	public boolean isAppNotDisplayedOnPage(String appName) {
		return AppPageUtils.isAppNotDisplayedOnPage(page, appName, timestamp);
	}
}
