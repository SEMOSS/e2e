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
	
	public void clickOnAccessControlButton() {
		AppPageUtils.clickOnAccessControlButton(page);
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

	public String clickOnOption(String optionName) {
		return AppPageUtils.clickOnOption(page, optionName);
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
	
    public boolean isContentVisibleOnAppCard(String contentName, String contentValue) {
		return AppPageUtils.isContentVisibleOnAppCard(page, contentName, contentValue, timestamp);
	}
	public void clickOnDeleteButton(String buttonName) {
		AppPageUtils.clickOnDeleteButton(page, buttonName);
	}

	public boolean isAppNotDisplayedOnPage(String appName) {
		return AppPageUtils.isAppNotDisplayedOnPage(page, appName, timestamp);
	}

	public void searchFilterValueOnAppPage(String filterValue) {
		AppPageUtils.searchFilterValueOnAppPage(page, filterValue);
	}

	public void selectFilterValueOnAppPage(String filterCategory, String filterValue) {
		AppPageUtils.selectFilterValueOnAppPage(page, filterCategory, filterValue);
	}

	public void clickOnViewDetails(String buttonName) {
		AppPageUtils.clickOnViewDetails(page,buttonName);
	}
}
