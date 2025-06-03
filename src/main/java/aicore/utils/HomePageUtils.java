package aicore.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class HomePageUtils {

	private static final Logger logger = LogManager.getLogger(HomePageUtils.class);

	private static final String PAGE_TITLE_XPATH = "//a[@class='css-jnxb8i']";

	// side bar options
	private static final String OPEN_APP_LIBRARY_XPATH = "//a[@data-tour='nav-app-library']";
	private static final String OPEN_MODEL_XPATH = "//a[@data-testid='Model-icon']";
	private static final String OPEN_STORAGE_XPATH = "//a[@data-testid='Storage-icon']";
	private static final String OPEN_VECTOR_XPATH = "//a[@data-testid='Vector-icon']";
	private static final String OPEN_FUNCTION_DATA_TEST_ID_VALUE = "Function-icon";
	private static final String USER_PROFILE_ICON_XPATH = "//div[normalize-space()='"
			+ ConfigUtils.getValue("applicationName") + "']//button";
	public static final String OPEN_DATABASE = "Database-icon";
	private static final String OPEN_SETTINGS_XPATH = "//*[name()='svg'][@data-testid='Settings-icon']";

	// system apps
	private static final String SYSTEM_APP_BUTTON_XPATH = "//button[text()='System Apps']";
	private static final String APP_TAB_XPATH = "//button[text()='{tab}']";
	private static final String BI_APP_XPATH = "(//div[@class='css-uncsel']//div//a)[1]";

	// pop ups
	private static final String ACCEPT_BUTTON_XPATH = "//span[text()='Accept']";
	private static final String CLOSE_POPUP_BUTTON_XPATH = "//div[@class='css-1bvc4cc']//button";

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
		Locator locator = page.getByTestId(OPEN_FUNCTION_DATA_TEST_ID_VALUE);
		locator.click();
	}

	public static void clickOnBIApp(Page page) {
		page.click(BI_APP_XPATH);
	}

	public static void clickOnOpenModel(Page page) {
		page.click(OPEN_MODEL_XPATH);
	}

	public static void clickOnOpenStorage(Page page) {
		page.click(OPEN_STORAGE_XPATH);
	}

	public static void clickOnOpenVector(Page page) {
		page.click(OPEN_VECTOR_XPATH);
	}

	public static void clickOnOpenAppLibrary(Page page) {
		page.click(OPEN_APP_LIBRARY_XPATH);
	}

	public static void logout(Page page) {
		page.click(USER_PROFILE_ICON_XPATH);
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Logout")).click();
	}

	public static void clickOnOpenSettings(Page page) {
		page.locator(OPEN_SETTINGS_XPATH).click();
	}

	public static void checkOnOpenSetting(Page page) {
		page.locator(OPEN_SETTINGS_XPATH).isVisible();
	}

	public static void navigateToHomePage(Page page) {
		String homePage = UrlUtils.getUrl("#");
		page.navigate(homePage);
		try {
			page.waitForURL(homePage);
		} catch (Throwable t) {
			logger.warn("Waiting for: {}\nCurrent: {}\nContinuing anyway", homePage, page.url());
		}
	}

	public static void clickOnOpenDatabase(Page page) {
		page.getByTestId(OPEN_DATABASE).isVisible();
		page.getByTestId(OPEN_DATABASE).click();
	}
}
