package aicore.pages;

import com.microsoft.playwright.Page;

import aicore.pages.home.HomePageUtils;

public class HomePage {

	private Page page;

	public HomePage(Page page) {
		this.page = page;
	}

	public void closeInfoPopup() {
		HomePageUtils.closeInfoPopup(page);
	}

	public String getPageTitle() {
		return HomePageUtils.getPageTitle(page);
	}

	public void clickOnSystemApp() {
		HomePageUtils.clickOnSystemApp(page);
	}

	public void clickOnTab(String tabName) {
		HomePageUtils.clickOnTab(page, tabName);
	}

	public void clickOnBIApp() {
		HomePageUtils.clickOnBIApp(page);
	}

	public void verifyBuildPageButton(String buttonName) {
		HomePageUtils.verifyBuildPageButton(page, buttonName);
	}

	public boolean verifyBuildPageButtons(String sectionName, String buttonName) {
		return HomePageUtils.verifyBuildPageButtons(page, sectionName, buttonName);
	}

	public void verifyTitleIsVisible(String titleName) {
		HomePageUtils.verifyTitleIsVisible(page, titleName);
	}

	public void navigateToHomePage() {
		HomePageUtils.navigateToHomePage(page);
	}

	public void searchCatalog(String searchData) {
		HomePageUtils.searchCatalog(page, searchData);
	}

	public void selectSearchResultFilterOption(String optionName) {
		HomePageUtils.selectSearchResultFilterOption(page, optionName);
	}

	public void closeSearchPopup() {
		HomePageUtils.closeSearchPopup(page);
	}

	public boolean verifySearchResultIsVisible(String searchResult) {
		return HomePageUtils.verifySearchResultIsVisible(page, searchResult);
	}

	public void enterAppNameToCreateApp(String appName) {
		HomePageUtils.enterAppNameToCreateApp(page, appName);
	}

	public void enterCatalogNameToCreateCatalog(String catalogName) {
		HomePageUtils.enterCatalogNameToCreateCatalog(page, catalogName);
	}
}