package aicore.pages.home;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;

import aicore.framework.AICoreTestConstants;
import aicore.framework.ConfigUtils;
import aicore.framework.UrlUtils;
import aicore.utils.AICorePageUtils;
import aicore.utils.CommonUtils;

public class HomePageUtils {

	private static final Logger logger = LogManager.getLogger(HomePageUtils.class);
	private static final String PAGE_TITLE_XPATH = "//h6[text()='" + ConfigUtils.getValue(AICoreTestConstants.APP_NAME)
			+ "']";
	public static final String APP_SEARCH_TEXTBOX_XPATH = "//button[normalize-space()='Search']";
	public static final String SEARCH_TEXTBOX_ON_POPUP_XPATH = "//input[@Placeholder='Search apps, engines, and tools']";
	public static final String SEARCH_RESULT_XPATH = "//span[text()='{catalogName}']";

	// build page options
	private static final String BUILD_PAGE_TITLE_XPATH = "//*[text()='{title}']";
	private static final String BUILD_PAGE_BUTTON = "//div[text()='{cardName}']/parent::div/following-sibling::div//button//span[text()='{buttonName}']";
	private static final String BUILD_PAGE_BROWSER_TEMPLATE_BUTTON_XPATH = "//a[text()='Browse Templates']";

	// system apps
	private static final String SYSTEM_APP_BUTTON_XPATH = "//button[text()='System Apps']";
	private static final String APP_TAB_XPATH = "//button[text()='{tab}']";

	// Create app
	public static final String NAME_TEXTBOX_DATATESTID = "newAppModal-textField-name";
	private static final String CATALOG_NAME_TEXTBOX_DATA_TESTID = "importForm-NAME-textField";
	private static final String CATALOG_NAME_TEXTBOX_NEW_UI_DATA_TESTID = "importForm-Catalog-Name-textField";
	private static final String STORAGE_CATALOG_NAME_TEXTBOX_DATA_TESTID = "storage-form-input-NAME";
	private static final String VECTOR_CATALOG_NAME_TEXTBOX_DATA_TESTID = "vector-form-input-NAME";
	// pop ups
	private static final String ACCEPT_BUTTON_XPATH = "//span[text()='Accept']";
	private static final String CLOSE_POPUP_BUTTON_XPATH = "//div[@class='css-1bvc4cc']//button";

	public static void navigateToHomePage(Page page) {
		String homePage = UrlUtils.getUrl("#");
		navigateToHomePage(page, homePage);
	}
	
	public static void navigateToHomePage(Page page, String homePageUrl) {
		page.navigate(homePageUrl);
		try {
			page.waitForURL(homePageUrl);
		} catch (Throwable t) {
			logger.warn("Waiting for: {}\nCurrent: {}\nContinuing anyway", homePageUrl, page.url());
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
		Locator button = page.locator(SYSTEM_APP_BUTTON_XPATH);
		AICorePageUtils.waitFor(button);
		button.click();
	}

	public static void clickOnTab(Page page, String tabName) {
		page.click(APP_TAB_XPATH.replace("{tab}", tabName));
	}

	public static void clickOnBIApp(Page page) {
		// TODO switch to clicking on app
		String bi = UrlUtils.getBaseFrontendUrl("packages/legacy/dist/#!/");
		page.navigate(bi);
		page.waitForLoadState(LoadState.DOMCONTENTLOADED);
		page.waitForLoadState(LoadState.NETWORKIDLE);
	}

	/**
	 * Click on Build page with options like playground etc
	 * @param page
	 */
	public static void clickOnBuildButton(Page page) {
		Locator btn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Build"));
		AICorePageUtils.waitFor(btn);
		
		String classValue = btn.getAttribute("class");
		if (classValue == null || !classValue.contains("bg-primary")) {
			btn.click();
		} 
	}
	
	/**
	 * Click on build search page
	 * 
	 * @param page
	 */
	public static void clickOnBuildSearchButton(Page page) {
		Locator btn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Build"));
		AICorePageUtils.waitFor(btn);
		String classValue = btn.getAttribute("class");
		if (classValue == null || classValue.contains("bg-primary")) {
			btn.click();
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
		page.getByTestId(NAME_TEXTBOX_DATATESTID).fill(appName);
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

}