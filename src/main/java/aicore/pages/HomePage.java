package aicore.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import aicore.utils.ConfigUtils;
import aicore.utils.HomePageUtils;
import aicore.utils.UrlUtils;

public class HomePage {
	private static final Logger logger = LogManager.getLogger(HomePage.class);

	private Page page;
	private static final String ACCEPT_BUTTON_XPATH = "//span[text()='Accept']";
	private static final String CLOSE_POPUP_BUTTON_XPATH = "//div[@class='css-1bvc4cc']//button";
	private static final String PAGE_TITLE_XPATH = "//a[@class='css-jnxb8i']";
	private static final String SYSTEM_APP_BUTTON_XPATH = "//button[text()='System Apps']";
	private static final String APP_TAB_XPATH = "//button[text()='{tab}']";
	private static final String BI_APP_XPATH = "(//div[@class='css-uncsel']//div//a)[1]";
	private static final String OPEN_APP_LIBRARY_XPATH = "//a[@data-tour='nav-app-library']";
	private static final String OPEN_MODEL_XPATH = "//a[@data-testid='Model-icon']";
	private static final String OPEN_STORAGE_XPATH = "//a[@data-testid='Storage-icon']";
	private static final String OPEN_VECTOR_XPATH = "//a[@data-testid='Vector-icon']";
	private static final String OPEN_FUNCTION_XPATH = "//a[@data-testid='Function-icon']";
	private static final String USER_PROFILE_ICON_XPATH = "//div[normalize-space()='"
			+ ConfigUtils.getValue("applicationName") + "']//button";
	public static final String OPEN_FUNCTIONS_XPATH = "SwitchAccessShortcutOutlinedIcon";	
	public static final String OPEN_DATABASE= "Database-icon";	
	private static final String OPEN_SETTINGS_XPATH = "//*[name()='svg'][@data-testid='Settings-icon']";

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

	public void logOutAsCurrentUser() {
		HomePageUtils.logOutAsCurrentUser(page);
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

}