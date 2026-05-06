package aicore.pages.home;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import aicore.pages.base.AbstractBasePage;
import io.qameta.allure.Step;

public class MainMenuUtils extends AbstractBasePage {
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

	@Step("Open Main Menu")
	public static void openMainMenu(Page page) {
		logger.info("Open Main Menu");
		Locator mainMenuButtonLocator = page.getByLabel(SEMOSS_OPEN_MENU_LABEL);
		waitAndClick(mainMenuButtonLocator);
	}

	@Step("Close Main Menu")
	public static void closeMainMenu(Page page) {
		logger.info("Close Main Menu");
		Locator menuOpen = page.locator(SEMOSS_OPEN_MEN_XPATH);
		if (menuOpen.isVisible()) {
			menuOpen.dblclick();
		}
	}

	@Step("Click on Home")
	public static void clickOnHome(Page page) {
		logger.info("Click on Home");
		Locator locator = page.locator(HOME_MENU_BUTTON_DATA_TESTID);
		clickOnLocatorAndCLoseMainMenu(page, locator);
	}

	@Step("Click on Open Function Library")
	public static void clickOnOpenFunction(Page page) {
		logger.info("Opening Function Page");
		Locator locator = page.getByTestId(FUNCTION_MENU_BUTTON_DATA_TEST_ID);
		clickOnLocatorAndCLoseMainMenu(page, locator);
	}

	@Step("Click on Open Vector Library")
	public static void clickOnOpenVector(Page page) {
		logger.info("Opening Vector Page");
		Locator locator = page.getByTestId(VECTOR_MENU_BUTTON_DATA_TEST_ID);
		clickOnLocatorAndCLoseMainMenu(page, locator);
	}

	@Step("Click on Open Guardrail Library")
	public static void clickOnGuardrail(Page page) {
		logger.info("Opening Guardrail Page");
		Locator locator = page.getByTestId(GUARDRAIL_MENU_BUTTON_DATA_TEST_ID);
		clickOnLocatorAndCLoseMainMenu(page, locator);
	}

	@Step("Click on Open App Library")
	public static void clickOnOpenAppLibrary(Page page) {
		logger.info("Opening App Page");
		Locator locator = page.getByTestId(APP_MENU_BUTTON_DATA_TEST_ID);
		clickOnLocatorAndCLoseMainMenu(page, locator);
	}

	@Step("Click on Open App Library")
	public static void clickOnOpenModel(Page page) {
		logger.info("Opening Model Page");
		Locator locator = page.getByTestId(MODEL_MENU_BUTTON_DATA_TEST_ID);
		clickOnLocatorAndCLoseMainMenu(page, locator);
	}

	@Step("Click on Open Storage Library")
	public static void clickOnOpenStorage(Page page) {
		logger.info("Opening Storage Page");
		Locator locator = page.getByTestId(STORAGE_MENU_BUTTON_DATA_TEST_ID);
		clickOnLocatorAndCLoseMainMenu(page, locator);
	}

	@Step("Click on Open Settings")
	public static void clickOnOpenSettings(Page page) {
		logger.info("Opening Settings Page");
		Locator locator = page.getByTestId(SETTINGS_MENU_BUTTON_DATA_TEST_ID);
		clickOnLocatorAndCLoseMainMenu(page, locator);
	}

	@Step("Click on Open Database Library")
	public static void clickOnOpenDatabase(Page page) {
		logger.info("Click on Open Database");
		Locator locator = page.getByTestId(DATABASE_MENU_BUTTON_DATA_TEST_ID);
		clickOnLocatorAndCLoseMainMenu(page, locator);
	}

	@Step("Click on User Account button")
	public static void clickOnUserAccountButton(Page page) {
		logger.info("Opening User Account Button");
		Locator locator = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login"));
		clickOnLocatorAndCLoseMainMenu(page, locator);
	}

	public static void clickOnLocatorAndCLoseMainMenu(Page page, Locator locator) {
		waitAndClick(locator);
		clickAnywhereOnPage(page); // closes side-menu on main page
	}

	@Step("Logging out")
	public static void logout(Page page) {
		logger.info("Logging out...");
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
		// page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Welcome!")).click();
	}
}
