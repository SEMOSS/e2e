package aicore.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class HomePageUtils {

	private static final Logger logger = LogManager.getLogger(HomePageUtils.class);

	private static final String PAGE_TITLE_XPATH = "//a[@class='css-jnxb8i']";
	public static final String APP_SEARCH_TEXTBOX_XPATH = "//input[contains(@class,'MuiInputBase-input MuiOutlinedInput-input ') and @placeholder='Search']";
	// menu options
	private static final String SEMOSS_MENU_DATA_TESID = "MenuRoundedIcon";
	private static final String SEMOSS_OPEN_MEN_DATA_TESID = "MenuOpenRoundedIcon";
	public static final String APP_MENU_BUTTON_LABEL = "div.MuiButtonBase-root:has-text('Apps')";
	public static final String DATABASE_MENU_BUTTON_LABEL = "div.MuiButtonBase-root:has-text('Database')";

	// TODO this changed for now need to use data test id
	public static final String FUNCTION_MENU_BUTTON_LABEL = "div.MuiButtonBase-root:has-text('Function')";
	private static final String OPEN_FUNCTION_DATA_TEST_ID_VALUE = "Function-icon";

	public static final String MODEL_MENU_BUTTON_LABEL = "div.MuiButtonBase-root:has-text('Model')";
	private static final String OPEN_MODEL_XPATH = "//a[@data-testid='Model-icon']";

	public static final String STORAGE_MENU_BUTTON_LABEL = "div.MuiButtonBase-root:has-text('Storage')";
	private static final String OPEN_STORAGE_XPATH = "//a[@data-testid='Storage-icon']";

	public static final String VECTOR_MENU_BUTTON_LABEL = "div.MuiButtonBase-root:has-text('Vector')";

	private static final String OPEN_VECTOR_XPATH = "//a[@data-testid='TokenRoundedIcon']";

	private static final String USER_PROFILE_ICON_XPATH = "//div[normalize-space()='"
			+ ConfigUtils.getValue("applicationName") + "']//button";
	private static final String OPEN_SETTINGS_XPATH = "//*[name()='svg'][@data-testid='SettingsIcon']";

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
		// previously searched app remains visible when the user returns to the homepage
		// This is a issue and will be removed once resolved.
		page.reload();
	}

	public static void openMainMenu(Page page) {
		// check if menu is open
		Locator menuOpen = page.getByTestId(SEMOSS_OPEN_MEN_DATA_TESID);
		if (!menuOpen.isVisible()) {
			Locator locator = page.getByTestId(SEMOSS_MENU_DATA_TESID);
			locator.click();

			menuOpen.click();
		}
	}

	public static void closeMainMenu(Page page) {
		Locator menuOpen = page.getByTestId(SEMOSS_OPEN_MEN_DATA_TESID);
		if (menuOpen.isVisible()) {
			menuOpen.click();
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
		Locator locator = page.locator(FUNCTION_MENU_BUTTON_LABEL);
		locator.click();
		HomePageUtils.closeMainMenu(page);
	}

	public static void clickOnBIApp(Page page) {
		page.click(BI_APP_XPATH);
	}

	public static void clickOnOpenModel(Page page) {
		page.click(MODEL_MENU_BUTTON_LABEL);
		HomePageUtils.closeMainMenu(page);
	}

	public static void clickOnOpenStorage(Page page) {
		page.click(STORAGE_MENU_BUTTON_LABEL);
		HomePageUtils.closeMainMenu(page);
	}

	public static void clickOnOpenVector(Page page) {
		page.click(VECTOR_MENU_BUTTON_LABEL);
		HomePageUtils.closeMainMenu(page);
	}

	public static void clickOnOpenAppLibrary(Page page) {
		Locator locator = page.locator(APP_MENU_BUTTON_LABEL);
		locator.click();
		HomePageUtils.closeMainMenu(page);
	}

	public static void logout(Page page) {

		Locator menuOpen = page.getByTestId("MenuOpenRoundedIcon");

		if (!menuOpen.isVisible()) {
			Locator locator = page.getByTestId("MenuRoundedIcon");
			locator.click();
			menuOpen.click();
		}
		page.getByTestId("PersonIcon").click();
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Logout")).click();

		page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Welcome!")).click();
	}

	public static void clickOnOpenSettings(Page page) {
		page.locator(OPEN_SETTINGS_XPATH).click();
	}

	public static void checkOnOpenSetting(Page page) {
		page.locator(OPEN_SETTINGS_XPATH).isVisible();
	}

	public static void clickOnOpenDatabase(Page page) {
		Locator locator = page.locator(DATABASE_MENU_BUTTON_LABEL);
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
