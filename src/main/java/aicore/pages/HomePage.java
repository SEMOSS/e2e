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

}