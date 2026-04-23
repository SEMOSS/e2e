package aicore.pages.home;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import aicore.pages.base.AbstractBasePage;
import aicore.utils.AICorePageUtils;

public class MainMenuUtils extends AbstractBasePage{
	private static final Logger logger = LogManager.getLogger(MainMenuUtils.class);

	// menu
	private static final String SEMOSS_OPEN_MEN_XPATH = "//button//*[name()='svg'][contains(@class,'lucide-panel-left')]";
	private static final String SEMOSS_OPEN_MENU_LABEL = "Open sidebar";
	private static final String SEMOSS_MENU_DATA_TESTID = "MenuRoundedIcon";
	private static final String APP_MENU_XPATH = "//button[@aria-label='Open sidebar']";

	// home option
	private static final String HOME_MENU_BUTTON_DATA_TESTID = "//span[text()='Home']";

	// catalog options
	private static final String APP_MENU_BUTTON_DATA_TEST_ID = "sidebar-Apps-btn";
	private static final String DATABASE_MENU_BUTTON_DATA_TEST_ID = "sidebar-Database-btn";
	private static final String FUNCTION_MENU_BUTTON_DATA_TEST_ID = "sidebar-Function-btn";
	private static final String MODEL_MENU_BUTTON_DATA_TEST_ID = "sidebar-Model-btn";
	private static final String STORAGE_MENU_BUTTON_DATA_TEST_ID = "sidebar-Storage-btn";
	private static final String VECTOR_MENU_BUTTON_DATA_TEST_ID = "sidebar-Vector-btn";
	private static final String GUARDRAIL_MENU_BUTTON_DATA_TEST_ID = "sidebar-Guardrail-btn";

	// settings
	private static final String SETTINGS_MENU_BUTTON_DATA_TEST_ID = "sidebar-settings-btn";
	
	public static void openMainMenu(Page page) {
		logger.info("OPEN MAIN MENU");
		Locator mainMenuButtonLocator = page.getByLabel(SEMOSS_OPEN_MENU_LABEL);
		waitAndClick(mainMenuButtonLocator);
	}

	public static void closeMainMenu(Page page) {
		Locator menuOpen = page.locator(SEMOSS_OPEN_MEN_XPATH);
		if (menuOpen.isVisible()) {
			menuOpen.dblclick();
		}
	}
	
	public static void clickOnHome(Page page) {
		Locator locator = page.locator(HOME_MENU_BUTTON_DATA_TESTID);
		clickOnLocatorAndCLoseMainMenu(page, locator);
	}

	public static void clickOnOpenFunction(Page page) {
		logger.info("Opening Function Page");
		Locator locator = page.getByTestId(FUNCTION_MENU_BUTTON_DATA_TEST_ID);
		clickOnLocatorAndCLoseMainMenu(page, locator);
	}

	public static void clickOnOpenVector(Page page) {
		Locator locator = page.getByTestId(VECTOR_MENU_BUTTON_DATA_TEST_ID);
		clickOnLocatorAndCLoseMainMenu(page, locator);
	}

	public static void clickOnGuardrail(Page page) {
		Locator locator = page.getByTestId(GUARDRAIL_MENU_BUTTON_DATA_TEST_ID);
		clickOnLocatorAndCLoseMainMenu(page, locator);
	}

	public static void clickOnOpenAppLibrary(Page page) {
		Locator locator = page.getByTestId(APP_MENU_BUTTON_DATA_TEST_ID);
		clickOnLocatorAndCLoseMainMenu(page, locator);
	}

	public static void clickOnOpenModel(Page page) {
		Locator locator = page.getByTestId(MODEL_MENU_BUTTON_DATA_TEST_ID);
		clickOnLocatorAndCLoseMainMenu(page, locator);
	}

	public static void clickOnOpenStorage(Page page) {
		Locator locator = page.getByTestId(STORAGE_MENU_BUTTON_DATA_TEST_ID);
		clickOnLocatorAndCLoseMainMenu(page, locator);
	}

	public static void clickOnOpenSettings(Page page) {
		Locator locator = page.getByTestId(SETTINGS_MENU_BUTTON_DATA_TEST_ID);
		clickOnLocatorAndCLoseMainMenu(page, locator);
	}

	public static void clickOnOpenDatabase(Page page) {
		logger.info("CLICK ON OPEN DATABASE");
		Locator locator = page.getByTestId(DATABASE_MENU_BUTTON_DATA_TEST_ID);
		clickOnLocatorAndCLoseMainMenu(page, locator);
	}

	public static void clickOnUserAccountButton(Page page) {
		Locator locator = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login"));
		clickOnLocatorAndCLoseMainMenu(page, locator);
	}
	
	private static void clickOnLocatorAndCLoseMainMenu(Page page, Locator locator) {
		waitAndClick(locator);
		clickAnywhereOnPage(page); // closes side-menu on main page
	}

	public static void logout(Page page) {
		Locator isMenuOpen = page.locator(SEMOSS_OPEN_MEN_XPATH);
		if (isMenuOpen.isVisible()) {
			isMenuOpen.dblclick();
		}
		Locator mainMenu = page.getByTestId(SEMOSS_MENU_DATA_TESTID);
		Locator appMenu = page.locator(APP_MENU_XPATH);
		if (appMenu.isVisible()) {
			waitAndClick(appMenu);
		} else {

			waitAndClick(mainMenu);
		}
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Logout")).click();
		page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Welcome!")).click();
	}
}
