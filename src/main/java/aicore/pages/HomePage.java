package aicore.pages;

import com.microsoft.playwright.Page;

import aicore.utils.HomePageUtils;

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

	public void clickOnOpenFunction() {
		HomePageUtils.clickOnOpenFunction(page);
	}

	public void clickOnBIApp() {
		HomePageUtils.clickOnBIApp(page);
	}

	public void clickOnOpenModel() {
		HomePageUtils.clickOnOpenModel(page);
	}

	public void clickOnOpenStorage() {
		HomePageUtils.clickOnOpenStorage(page);
	}

	public void clickOnOpenVector() {
		HomePageUtils.clickOnOpenVector(page);
	}

	public void clickOnOpenAppLibrary() {
		HomePageUtils.clickOnOpenAppLibrary(page);
	}

	public void clickOnGuardrail() {
		HomePageUtils.clickOnGuardrail(page);
	}

	public void clickOnBuildButton() {
		HomePageUtils.clickOnBuildButton(page);
	}

	public void verifyBuildPageButton(String buttonName) {
		HomePageUtils.verifyBuildPageButton(page, buttonName);
	}

	public void verifyBuildPageButtons(String buttonName) {
		HomePageUtils.verifyBuildPageButtons(page, buttonName);
	}

	public void verifyTitleIsVisible(String titleName) {
		HomePageUtils.verifyTitleIsVisible(page, titleName);
	}

	public void logout() {
		HomePageUtils.logout(page);
	}

	public void clickOnOpenSettings() {
		HomePageUtils.clickOnOpenSettings(page);
	}

	public void checkOnOpenSetting() {
		HomePageUtils.checkOnOpenSetting(page);
	}

	public void navigateToHomePage() {
		HomePageUtils.navigateToHomePage(page);
	}

	public void clickOnOpenDatabase() {
		HomePageUtils.clickOnOpenDatabase(page);
	}

	public void openMainMenu() {
		HomePageUtils.openMainMenu(page);
	}

	public void closeMainMenu() {
		HomePageUtils.closeMainMenu(page);
	}

	public void clickOnHome() {
		HomePageUtils.clickOnHome(page);
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