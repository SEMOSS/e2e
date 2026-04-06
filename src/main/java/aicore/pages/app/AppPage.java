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

	public void clickOnDiscoverableAppsButton() {
		AppPageUtils.clickOnDiscoverableAppsButton(page);
	}

	public void searchApp(String appName) {
		AppPageUtils.searchApp(page, appName, timestamp);
	}

	public void selectAppCardsView(String view) {
		AppPageUtils.selectAppCardsView(page, view);
	}

	public void searchAppId(String appId) {
		AppPageUtils.searchAppId(page, appId);
	}

	public void clickOnAppCard(String appName) {
		AppPageUtils.clickOnAppCard(page, appName, timestamp);
	}

	public void clickOnMoreVertIcon(String appName) {
		AppPageUtils.clickOnMoreVertIcon(page, appName, timestamp);
	}

	public String clickOnOption(String optionName) {
		return AppPageUtils.clickOnOption(page, optionName);
	}

	public String getCopiedId(String icon) {
		return AppPageUtils.getCopiedId(page, icon);
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
		AppPageUtils.makeAppPublic(page);
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

	public void clickOnFilterOption() {
		AppPageUtils.clickOnFilterOption(page);
	}

	public void searchFilterValueOnAppPage(String filterValue) {
		AppPageUtils.searchFilterValueOnAppPage(page, filterValue);
	}

	public void selectFilterValueOnAppPage(String filterCategory, String filterValue) {
		AppPageUtils.selectFilterValueOnAppPage(page, filterCategory, filterValue);
	}

	public void clickOnInfoButton(String buttonName) {
		AppPageUtils.clickOnInfoButton(page, buttonName);
	}

	public void clickOnMakeDiscoverableButtoninSettings(String appName) {
		AppPageUtils.clickOnMakeDiscoverableButtoninSettings(page, appName);
	}

	public boolean verifyAppsSortedInAscendingOrder() {
		return AppPageUtils.verifyAppsSortedInAscendingOrder(page);
	}

	public void clickOnFilterButton(String filterName) {
		 AppPageUtils.clickOnFilterButton(page, filterName);
	}

	public boolean verifyAppsSortedInDescendingOrder() {
		return AppPageUtils.verifyAppsSortedInDescendingOrder(page);
	}

	public boolean verifyAppsSortedByDateLastEdited() {
		return AppPageUtils.verifyAppsSortedByDateLastEdited(page);
	}	

	public void selectSortByOption(String optionName) {
		AppPageUtils.selectSortByOption(page, optionName);
	}
}
