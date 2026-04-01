package aicore.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;

import aicore.framework.ConfigUtils;
import aicore.framework.UrlUtils;

public class HomePageUtils {

	private static final Logger logger = LogManager.getLogger(HomePageUtils.class);
	private static final String PAGE_TITLE_XPATH = "//h6[text()='" + ConfigUtils.getValue("applicationName") + "']";
	public static final String APP_SEARCH_TEXTBOX_XPATH = "//button[normalize-space()='Search']";
	public static final String SEARCH_TEXTBOX_ON_POPUP_XPATH = "//input[@Placeholder='Search']";
	public static final String SEARCH_RESULT_XPATH = "//span[text()='{catalogName}']";
	// menu options
	private static final String BUILD_BUTTON_XPATH = "//button[@value='build']";
	private static final String BUILD_PAGE_TITLE_XPATH = "//*[text()='{title}']";
	private static final String BUILD_PAGE_BUTTON = "//div[text()='{cardName}']/parent::div/following-sibling::div//button//span[text()='{buttonName}']";
	private static final String BUILD_PAGE_BROWSER_TEMPLATE_BUTTON_XPATH = "//a[text()='Browse Templates']";
	private static final String BUILD_PAGE_POPUP_XPATH = "//div[@role='presentation']//div[@role='presentation']";
	private static final String BUILD_PAGE_POPUP_CLOSE_XPATH = "//button//span[text()='Cancel']";
	private static final String SEMOSS_MENU_DATA_TESTID = "MenuRoundedIcon";
	private static final String APP_MENU_XPATH = "//button[@aria-label='Open sidebar']";
	private static final String SEMOSS_OPEN_MEN_XPATH = "//button//*[name()='svg'][contains(@class,'lucide-panel-left')]";
	private static final String APP_MENU_BUTTON_DATA_TEST_ID = "sidebar-Apps-btn";
	private static final String DATABASE_MENU_BUTTON_DATA_TEST_ID = "sidebar-Database-btn";
	private static final String FUNCTION_MENU_BUTTON_DATA_TEST_ID = "sidebar-Function-btn";
	private static final String MODEL_MENU_BUTTON_DATA_TEST_ID = "sidebar-Model-btn";
	private static final String STORAGE_MENU_BUTTON_DATA_TEST_ID = "sidebar-Storage-btn";
	private static final String VECTOR_MENU_BUTTON_DATA_TEST_ID = "sidebar-Vector-btn";
	private static final String GUARDRAIL_MENU_BUTTON_DATA_TEST_ID = "sidebar-Guardrail-btn";
	private static final String SETTINGS_MENU_BUTTON_DATA_TEST_ID = "sidebar-settings-btn";
	// TODO change to data_test_id
	private static final String HOME_MENU_BUTTON_DATA_TESTID = "//span[text()='Home']";
	// system apps
	private static final String SYSTEM_APP_BUTTON_XPATH = "//button[text()='System Apps']";
	private static final String APP_TAB_XPATH = "//button[text()='{tab}']";
	private static final String BI_APP_XPATH = "(//div[@class='css-uncsel']//div//a)[1]";
	// Create app
	private static final String APP_NAME_TEXTBOX_XPATH = "//label[text()='Name']";
	private static final String CATALOG_NAME_TEXTBOX_DATA_TESTID = "importForm-NAME-textField";
	private static final String CATALOG_NAME_TEXTBOX_NEW_UI_DATA_TESTID = "importForm-Catalog-Name-textField";
	private static final String STORAGE_CATALOG_NAME_TEXTBOX_DATA_TESTID = "storage-form-input-NAME";
	private static final String VECTOR_CATALOG_NAME_TEXTBOX_DATA_TESTID = "vector-form-input-NAME";
	// pop ups
	private static final String ACCEPT_BUTTON_XPATH = "//span[text()='Accept']";
	private static final String CLOSE_POPUP_BUTTON_XPATH = "//div[@class='css-1bvc4cc']//button";
	private static final String PROFILE_ICON_XPATH = "//div[@aria-label='Login']";

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
		Locator locator = page.getByTestId(FUNCTION_MENU_BUTTON_DATA_TEST_ID);
		locator.click();
		HomePageUtils.closeMainMenu(page);
	}

	public static void clickOnBIApp(Page page) {
		String useDocker = ConfigUtils.getValue("use_docker");
		if (useDocker.equals("true")) {
			page.click(BI_APP_XPATH);
		} else {
			String bi = UrlUtils.getBaseFrontendUrl("packages/legacy/dist/#!/");
			page.navigate(bi);
			page.waitForLoadState(LoadState.DOMCONTENTLOADED);
			page.waitForLoadState(LoadState.NETWORKIDLE);
		}
	}

	public static void clickOnOpenModel(Page page) {
		Locator locator = page.getByTestId(MODEL_MENU_BUTTON_DATA_TEST_ID);
		locator.click();
		HomePageUtils.closeMainMenu(page);
	}

	public static void clickOnOpenStorage(Page page) {
		Locator locator = page.getByTestId(STORAGE_MENU_BUTTON_DATA_TEST_ID);
		locator.click();
		HomePageUtils.closeMainMenu(page);
	}

	public static void clickOnOpenVector(Page page) {
		Locator locator = page.getByTestId(VECTOR_MENU_BUTTON_DATA_TEST_ID);
		locator.click();
		;
		HomePageUtils.closeMainMenu(page);
	}

	public static void clickOnGuardrail(Page page) {
		page.getByTestId(GUARDRAIL_MENU_BUTTON_DATA_TEST_ID).click();
		HomePageUtils.closeMainMenu(page);
	}

	public static void clickOnOpenAppLibrary(Page page) {
		Locator locator = page.getByTestId(APP_MENU_BUTTON_DATA_TEST_ID);
		locator.click();
		HomePageUtils.closeMainMenu(page);
	}

	public static void clickOnBuildButton(Page page) {
		Locator BuildButton = page.locator(BUILD_BUTTON_XPATH);
		AICorePageUtils.waitFor(BuildButton);
		if (!BuildButton.isVisible()) {
			throw new RuntimeException("Build button is not visible");
		} else {
			BuildButton.click();
		}
	}

	public static boolean verifyBuildPageButtons(Page page, String sectionName, String buttonName) {
		String BUILD_PAGE_BUTTON_XPATH = BUILD_PAGE_BUTTON.replace("{cardName}", sectionName).replace("{buttonName}",
				buttonName);
		Locator button = page.locator(BUILD_PAGE_BUTTON_XPATH);
		button.scrollIntoViewIfNeeded();
		AICorePageUtils.waitFor(button);
		return button.isVisible();
	}

	public static void verifyBuildPageButton(Page page, String buttonName) {
		Locator button = page.locator(BUILD_PAGE_BROWSER_TEMPLATE_BUTTON_XPATH);
		if (!button.isVisible()) {
			throw new RuntimeException("Browser Template Button is not visible");
		} else {
			button.click();
//			String currentUrl = page.url();
			page.waitForLoadState(LoadState.LOAD);
//			if (!currentUrl.contains("template")) {
//				throw new RuntimeException("Browser Template page is not opened");
//			} else {
			page.goBack();
//			}
		}
	}

	public static void verifyTitleIsVisible(Page page, String titleName) {
		Locator title = page.locator(BUILD_PAGE_TITLE_XPATH.replace("{title}", titleName));
		if (!title.isVisible()) {
			throw new RuntimeException(" the title with name " + titleName + " is not visible");
		}
	}

	public static void clickOnHome(Page page) {
		Locator locator = page.locator(HOME_MENU_BUTTON_DATA_TESTID);
		locator.click();
		page.waitForTimeout(1000);// wait for home page to load and sync then close menu
		HomePageUtils.closeMainMenu(page);
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
		page.locator(PROFILE_ICON_XPATH).click();
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Logout")).click();
		page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Welcome!")).click();
	}

	public static void clickOnOpenSettings(Page page) {
		Locator locator = page.getByTestId(SETTINGS_MENU_BUTTON_DATA_TEST_ID);
		locator.click();
		HomePageUtils.closeMainMenu(page);
	}

	public static void clickOnOpenDatabase(Page page) {
		Locator locator = page.getByTestId(DATABASE_MENU_BUTTON_DATA_TEST_ID);
		locator.click();
		HomePageUtils.closeMainMenu(page);
	}

	public static void searchCatalog(Page page, String searchData) {
		Locator locator = page.locator(APP_SEARCH_TEXTBOX_XPATH);
		locator.click();
		Locator search = page.locator(SEARCH_TEXTBOX_ON_POPUP_XPATH);
		AICorePageUtils.waitFor(search);
		search.fill(searchData);
	}

	public static void selectSearchResultFilterOption(Page page, String optionName) {
		Locator option = page.getByRole(AriaRole.BUTTON,
				new Page.GetByRoleOptions().setName(optionName).setExact(true));
		AICorePageUtils.waitFor(option);
		option.click();
	}

	public static void closeSearchPopup(Page page) {
		page.locator("//span[text()='Close']").click();
	}

	public static boolean verifySearchResultIsVisible(Page page, String catalogName) {
		Locator searchCard = page.locator(SEARCH_RESULT_XPATH.replace("{catalogName}", catalogName));
		AICorePageUtils.waitFor(searchCard);
		return searchCard.isVisible();
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

	// Created below 2 method to provide name without timestamp to verify home
	// search functionality
	public static void enterAppNameToCreateApp(Page page, String appName) {
		page.locator(APP_NAME_TEXTBOX_XPATH).fill(appName);
	}

	public static void enterCatalogNameToCreateCatalog(Page page, String catalogName) {
		if (page.getByTestId(CATALOG_NAME_TEXTBOX_NEW_UI_DATA_TESTID).isVisible()) {
			page.getByTestId(CATALOG_NAME_TEXTBOX_NEW_UI_DATA_TESTID).fill(catalogName);
		} else if (page.getByTestId(STORAGE_CATALOG_NAME_TEXTBOX_DATA_TESTID).isVisible()) {
			page.getByTestId(STORAGE_CATALOG_NAME_TEXTBOX_DATA_TESTID).fill(catalogName);
		} else if (page.getByTestId(VECTOR_CATALOG_NAME_TEXTBOX_DATA_TESTID).isVisible()) {
			page.getByTestId(VECTOR_CATALOG_NAME_TEXTBOX_DATA_TESTID).fill(catalogName);
		} else {
			page.getByTestId(CATALOG_NAME_TEXTBOX_DATA_TESTID).fill(catalogName);
		}
	}

	public static void clickOnUserAccountButton(Page page) {
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
	}
}