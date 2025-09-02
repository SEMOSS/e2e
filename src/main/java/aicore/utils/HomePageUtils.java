package aicore.utils;

import aicore.framework.ConfigUtils;
import aicore.framework.UrlUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;

public class HomePageUtils {

	private static final Logger logger = LogManager.getLogger(HomePageUtils.class);

	private static final String PAGE_TITLE_XPATH = "//h6[text()='" + ConfigUtils.getValue("applicationName") + "']";
	public static final String APP_SEARCH_TEXTBOX_XPATH = "//input[contains(@class,'MuiInputBase-input MuiOutlinedInput-input ') and @placeholder='Search']";
	// menu options
	private static final String BUILD_BUTTON_XPATH = "//button[@value='build']";
	private static final String BUILD_PAGE_TITLE_XPATH = "//*[text()='{title}']";
	private static final String BUILD_PAGE_BUTTON = "new-app-{Button}-btn";
	private static final String BUILD_PAGE_BROWSER_TEMPLATE_BUTTON_XPATH = "//button//span[text()='Browse Templates']";
	private static final String BUILD_PAGE_POPUP_XPATH = "//div[@role='presentation']//div[@role='presentation']";
	private static final String BUILD_PAGE_POPUP_CLOSE_XPATH = "//button//span[text()='Cancel']";
	private static final String SEMOSS_MENU_DATA_TESID = "MenuRoundedIcon";
//	private static final String SEMOSS_OPEN_MEN_DATA_XPATH = "//a[@aria-label='Go Home']/parent::div//*[@data-testid='CloseIcon']";
	private static final String SEMOSS_OPEN_MEN_DATA_TESTID = "MenuOpenRoundedIcon";
	private static final String APP_MENU_BUTTON_XPATH = "//div[@aria-label='Apps']";
	private static final String DATABASE_MENU_BUTTON_XPATH = "//div[@aria-label='Database']";
	private static final String FUNCTION_MENU_BUTTON_XPATH = "//div[@aria-label='Function']";
	private static final String MODEL_MENU_BUTTON_XPATH = "//div[@aria-label='Model']";
	private static final String STORAGE_MENU_BUTTON_XPATH = "//div[@aria-label='Storage']";
	private static final String VECTOR_MENU_BUTTON_XPATH = "//div[@aria-label='Vector']";
	private static final String USER_PROFILE_ICON_XPATH = "//div[normalize-space()='"
			+ ConfigUtils.getValue("applicationName") + "']//button";
	private static final String SETTINGS_MENU_BUTTON_DATA_TESTID = "SettingsIcon";
	private static final String USER_PROFILE_ICON_DATA_TESTID = "PersonIcon";

	// system apps
	private static final String SYSTEM_APP_BUTTON_XPATH = "//button[text()='System Apps']";
	private static final String APP_TAB_XPATH = "//button[text()='{tab}']";
	private static final String BI_APP_XPATH = "(//div[@class='css-uncsel']//div//a)[1]";

	// pop ups
	private static final String ACCEPT_BUTTON_XPATH = "//span[text()='Accept']";
	private static final String CLOSE_POPUP_BUTTON_XPATH = "//div[@class='css-1bvc4cc']//button";

	public static void navigateToHomePage(Page page) {
		String homePage = UrlUtils.getUrl("#");
		page.navigate(homePage);
		try {
			page.waitForURL(homePage);
		} catch (Throwable t) {
			logger.warn("Waiting for: {}\nCurrent: {}\nContinuing anyway", homePage, page.url());
		}
	}

	public static void openMainMenu(Page page) {
		// check if menu is open
//		Locator isMenuOpen = page.locator(SEMOSS_OPEN_MEN_DATA_XPATH);
		Locator isMenuOpen = page.getByTestId(SEMOSS_OPEN_MEN_DATA_TESTID);
		page.waitForTimeout(300);
		if (isMenuOpen.isVisible()) {
//			isMenuOpen.click();
			isMenuOpen.dblclick();
		}
		Locator locator = page.getByTestId(SEMOSS_MENU_DATA_TESID);
		AICorePageUtils.waitFor(locator);
		locator.click();
	}

	public static void closeMainMenu(Page page) {
//		Locator menuOpen = page.locator(SEMOSS_OPEN_MEN_DATA_XPATH);
		Locator menuOpen = page.getByTestId(SEMOSS_OPEN_MEN_DATA_TESTID);
		if (menuOpen.isVisible()) {
//			menuOpen.click();
			menuOpen.dblclick();
		}
	}

	public static void closeInfoPopup(Page page) {
		page.click(ACCEPT_BUTTON_XPATH);
		page.click(CLOSE_POPUP_BUTTON_XPATH);
	}

	public static String getPageTitle(Page page) {
		String actTitle = page.textContent(PAGE_TITLE_XPATH).trim();
		return actTitle;
	}

	public static void clickOnSystemApp(Page page) {
		page.click(SYSTEM_APP_BUTTON_XPATH);
	}

	public static void clickOnTab(Page page, String tabName) {
		page.click(APP_TAB_XPATH.replace("{tab}", tabName));
	}

	public static void clickOnOpenFunction(Page page) {
		// TODO change back to id once fe is fixed
//		Locator locator = page.getByTestId(OPEN_FUNCTION_DATA_TEST_ID_VALUE);
		Locator locator = page.locator(FUNCTION_MENU_BUTTON_XPATH);
		locator.click();
		HomePageUtils.closeMainMenu(page);
	}

	public static void clickOnBIApp(Page page) {
		page.click(BI_APP_XPATH);
	}

	public static void clickOnOpenModel(Page page) {
		page.click(MODEL_MENU_BUTTON_XPATH);
		HomePageUtils.closeMainMenu(page);
	}

	public static void clickOnOpenStorage(Page page) {
		page.click(STORAGE_MENU_BUTTON_XPATH);
		HomePageUtils.closeMainMenu(page);
	}

	public static void clickOnOpenVector(Page page) {
		page.click(VECTOR_MENU_BUTTON_XPATH);
		HomePageUtils.closeMainMenu(page);
	}

	public static void clickOnOpenAppLibrary(Page page) {
		Locator locator = page.locator(APP_MENU_BUTTON_XPATH);
		locator.click();
		HomePageUtils.closeMainMenu(page);
	}
	
	public static void clickOnBuildButton(Page page) {
		Locator BuildButton = page.locator(BUILD_BUTTON_XPATH);
		if (!BuildButton.isVisible()) {
			throw new RuntimeException("Build button is not visible");
		} else {
			BuildButton.click();
		}
	}
	public static void verifyBuildPageButton(Page page, String buttonName) {
		String BUILD_PAGE_BUTTON_XPATH = BUILD_PAGE_BUTTON.replace("{Button}", buttonName);
		Locator button = page.getByTestId(BUILD_PAGE_BUTTON_XPATH);
		if (!button.isVisible()) {
			throw new RuntimeException("Get Started button for " + buttonName + " is not visible");
		}else{
			button.click();
			if (buttonName.equalsIgnoreCase("drag") || buttonName.equalsIgnoreCase("code")) {
				if (!page.locator(BUILD_PAGE_POPUP_XPATH).isVisible()) {
					throw new RuntimeException("POP-Up is not showing after clicking on " + buttonName);
				} else {
					page.locator(BUILD_PAGE_POPUP_CLOSE_XPATH).click();
				}
			} else {
				String currentUrl = page.url();
				page.waitForLoadState(LoadState.DOMCONTENTLOADED);
				if (!currentUrl.contains("prompt")) {
					throw new RuntimeException("Browser Prompt page is not opened");
				} else {
					page.goBack();
				}
			}

		}
	}

	public static void verifyBuildPageButtons(Page page, String buttonName) {
		Locator button = page.locator(BUILD_PAGE_BROWSER_TEMPLATE_BUTTON_XPATH);
		if (!button.isVisible()) {
			throw new RuntimeException("Browser Template Button is not visible");
		}else{
			button.click();
			String currentUrl = page.url();
			page.waitForLoadState(LoadState.LOAD);
			if (!currentUrl.contains("template")) {
   				 throw new RuntimeException("Browser Template page is not opened");
			} else {
    			page.goBack(); 
				}
		}
	}
	public static void verifyTitleIsVisible(Page page, String titleName) {
		Locator title = page.locator(BUILD_PAGE_TITLE_XPATH.replace("{title}", titleName));
		if (!title.isVisible()) {
			throw new RuntimeException(" the title with name " + titleName + " is not visible");
		}
	}

	public static void logout(Page page) {
//		Locator isMenuOpen = page.locator(SEMOSS_OPEN_MEN_DATA_XPATH);
		Locator isMenuOpen = page.getByTestId(SEMOSS_OPEN_MEN_DATA_TESTID);
		if (isMenuOpen.isVisible()) {
//			isMenuOpen.click();
			isMenuOpen.dblclick();
		}
		Locator locator = page.getByTestId(SEMOSS_MENU_DATA_TESID);
		AICorePageUtils.waitFor(locator);
		locator.click();
		page.getByTestId("AccountCircleRoundedIcon").click();
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Logout")).click();

		page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Welcome!")).click();
	}

	public static void clickOnOpenSettings(Page page) {
		page.getByTestId(SETTINGS_MENU_BUTTON_DATA_TESTID).click();
		HomePageUtils.closeMainMenu(page);
	}

	public static void checkOnOpenSetting(Page page) {
		page.getByTestId(SETTINGS_MENU_BUTTON_DATA_TESTID).isVisible();
	}

	public static void clickOnOpenDatabase(Page page) {
		Locator locator = page.locator(DATABASE_MENU_BUTTON_XPATH);
		locator.click();
		HomePageUtils.closeMainMenu(page);
	}

	public static void searchApp(Page page, String appName, String timestamp) {
		page.locator(APP_SEARCH_TEXTBOX_XPATH).fill(appName + " " + timestamp);
		page.getByLabel("Search").click();
		page.getByLabel("Search").fill(appName + " " + timestamp);
	}

	public static void clickOnSearchedApp(Page page, String appName, String timestamp) {
		// new search box
		Locator listbox = page.locator("ul.MuiAutocomplete-listbox");
		AICorePageUtils.waitFor(listbox);
		String expectedText = appName + " " + timestamp;
		Locator button = listbox.getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName(expectedText));
		AICorePageUtils.waitFor(button);
		Locator anchor = page.locator("//span[text()='" + expectedText + "']/ancestor::a");
		CommonUtils.removeTargetAttribute(anchor);
		button.click();
	}
}
