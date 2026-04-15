package aicore.pages.home;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import aicore.utils.AICorePageUtils;

public class MainMenuUtils {
	// menu
	private static final String SEMOSS_OPEN_MEN_XPATH = "//button//*[name()='svg'][contains(@class,'lucide-panel-left')]";
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
		// check if menu is open
		Locator isMenuOpen = page.locator(SEMOSS_OPEN_MEN_XPATH);
		page.waitForTimeout(800);
		if (isMenuOpen.isVisible()) {
			isMenuOpen.dblclick();
		}
		Locator mainMenu = page.getByTestId(SEMOSS_MENU_DATA_TESTID);
		Locator appMenu = page.locator(APP_MENU_XPATH);
		if (appMenu.isVisible()) {
			AICorePageUtils.waitFor(appMenu);
			appMenu.click();
		} else {
			AICorePageUtils.waitFor(mainMenu);
			mainMenu.click();
		}
	}

	public static void closeMainMenu(Page page) {
		Locator menuOpen = page.locator(SEMOSS_OPEN_MEN_XPATH);
		if (menuOpen.isVisible()) {
			menuOpen.dblclick();
		}
	}

	public static void clickOnHome(Page page) {
		Locator locator = page.locator(HOME_MENU_BUTTON_DATA_TESTID);
		locator.click();
		page.waitForTimeout(1000);// wait for home page to load and sync then close menu
		MainMenuUtils.closeMainMenu(page);
	}

	public static void clickOnOpenFunction(Page page) {
		Locator locator = page.getByTestId(FUNCTION_MENU_BUTTON_DATA_TEST_ID);
		locator.click();
		MainMenuUtils.closeMainMenu(page);
	}

	public static void clickOnOpenVector(Page page) {
		Locator locator = page.getByTestId(VECTOR_MENU_BUTTON_DATA_TEST_ID);
		locator.click();
		MainMenuUtils.closeMainMenu(page);
	}

	public static void clickOnGuardrail(Page page) {
		page.getByTestId(GUARDRAIL_MENU_BUTTON_DATA_TEST_ID).click();
		MainMenuUtils.closeMainMenu(page);
	}

	public static void clickOnOpenAppLibrary(Page page) {
		Locator locator = page.getByTestId(APP_MENU_BUTTON_DATA_TEST_ID);
		locator.click();
		MainMenuUtils.closeMainMenu(page);
	}

	public static void clickOnOpenModel(Page page) {
		Locator locator = page.getByTestId(MODEL_MENU_BUTTON_DATA_TEST_ID);
		locator.click();
		MainMenuUtils.closeMainMenu(page);
	}

	public static void clickOnOpenStorage(Page page) {
		Locator locator = page.getByTestId(STORAGE_MENU_BUTTON_DATA_TEST_ID);
		locator.click();
		MainMenuUtils.closeMainMenu(page);
	}

	public static void clickOnOpenSettings(Page page) {
		Locator locator = page.getByTestId(SETTINGS_MENU_BUTTON_DATA_TEST_ID);
		locator.click();
		MainMenuUtils.closeMainMenu(page);
	}

	public static void clickOnOpenDatabase(Page page) {
		Locator locator = page.getByTestId(DATABASE_MENU_BUTTON_DATA_TEST_ID);
		locator.click();
		MainMenuUtils.closeMainMenu(page);
	}

	public static void clickOnUserAccountButton(Page page) {
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
	}

	public static void logout(Page page) {
		Locator isMenuOpen = page.locator(SEMOSS_OPEN_MEN_XPATH);
		if (isMenuOpen.isVisible()) {
			isMenuOpen.dblclick();
		}
		Locator mainMenu = page.getByTestId(SEMOSS_MENU_DATA_TESTID);
		Locator appMenu = page.locator(APP_MENU_XPATH);
		if (appMenu.isVisible()) {
			AICorePageUtils.waitFor(appMenu);
			appMenu.click();
		} else {
			AICorePageUtils.waitFor(mainMenu);
			mainMenu.click();
		}
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Logout")).click();
		page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Welcome!")).click();
	}
}
