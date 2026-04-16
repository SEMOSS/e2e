package aicore.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Mouse;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.BoundingBox;

import aicore.pages.function.AddFunctionFormUtils;
import aicore.pages.function.FunctionAccessSettingsUtils;

public class AddFunctionPageUtils {
	private static final Logger logger = LogManager.getLogger(AddFunctionPageUtils.class);
	
	private static final String ADD_FUNCTION_BUTTON = "Navigate to import Function";
	private static final String CATALOG_FUNCTION = "{FunctionName}";
	private static final String CATALOG_FUNCTION_XPATH = "//div[contains(@data-testid,'genericEngineCards')]//p[(text()='{FunctionName}')]";
	public static final String OPEN_FUNCTIONS_XPATH = "SwitchAccessShortcutOutlinedIcon";
	private static final String ACCESS_CONTROL_XPATH = "//button[text()='Access Control']";
	private static final String FILE_TAB_XPATH = "//button[text()='Files']";
	private static final String SETTINGS_TAB_XPATH = "//button[text()='Settings']";

	private static final String SELECT_FILTER_VALUE_XPATH = "//h6[text()='{filterCategory}']/ancestor::li/following-sibling::div//p[text()='{filterValue}']";
	private static final String FUNCTION_CATALOG_SEARCH_TEXTBOX_DATA_TESTID = "search-bar";
	private static final String SEARCHED_FUNCTION_XPATH = "//p[text()='{catalogName}']";
	private static final String SEARCHED_CATALOG_DATATESTID = "genericEngineCards-{catalogType}-{catalogName}";

	private static final String SEARCH_BAR_DATATESTID = "search-bar";
	private static final String TOASTER_MESSAGE_XPATH = "//*[text()='{toastMessage}']";
	private static final String DISCOVERABLE_FUNCTIONS_BUTTON_TESTID = "engineIndexPage-Functions-discoverable-switch";

	public static void clickOnAddFunctionButton(Page page) {
		page.getByLabel(ADD_FUNCTION_BUTTON).isVisible();
		page.getByLabel(ADD_FUNCTION_BUTTON).click();
		logger.info("Clicked on Add Function Button");
	}

	public static String verifyFunctionNameInCatalog(Page page, String catalogName, String timestamp) {
		if (catalogName.contains("{Timestamp}")) {
			catalogName = catalogName.replace("{Timestamp}", " " + timestamp);
		}
		page.locator(CATALOG_FUNCTION_XPATH.replace("{FunctionName}", catalogName)).isVisible();
		String functionNameInCatalog = page.locator(CATALOG_FUNCTION_XPATH.replace("{FunctionName}", catalogName))
				.textContent();
		return functionNameInCatalog;

	}

	public static void clickOnFunctionNameInCatalog(Page page, String functionName, String timestamp) {
		if (functionName.contains("{Timestamp}")) {
			functionName = functionName.replace("{Timestamp}", " " + timestamp);
		}
		page.locator(CATALOG_FUNCTION_XPATH.replace("{FunctionName}", functionName)).isVisible();
		page.locator(CATALOG_FUNCTION_XPATH.replace("{FunctionName}", functionName)).click();
	}

	public static void clickOnFileTab(Page page) {
		page.locator(FILE_TAB_XPATH).isVisible();
		page.locator(FILE_TAB_XPATH).click();
	}

	public static void clickOnAccessControl(Page page) {
		Locator btn = page.locator(ACCESS_CONTROL_XPATH);
		if (!btn.isVisible()) {
			AICorePageUtils.waitFor(btn);
		}
		page.locator(ACCESS_CONTROL_XPATH).click();
	}

	public static void clickOnSettings(Page page) {
		Locator settingsTab = page.locator(SETTINGS_TAB_XPATH);
		AICorePageUtils.waitFor(settingsTab);
		settingsTab.click();
	}

	public static String verifySuccessToastMessage(Page page, String toastMessage) {
		Locator alert = page.locator(TOASTER_MESSAGE_XPATH.replace("{toastMessage}", toastMessage)).first();
		alert.scrollIntoViewIfNeeded();
		AICorePageUtils.waitFor(alert);
		return AICorePageUtils.verifySuccessToastMessage(page, alert);
	}

	public static boolean verifyFunctionIsVisibleInCatalog(Page page, String functionName) {
		boolean isFunctionVisible = page.getByText(CATALOG_FUNCTION.replace("{FunctionName}", functionName))
				.isVisible();
		return isFunctionVisible;
	}

	public static void searchFilterValue(Page page, String filterValue) {
		page.getByPlaceholder("Search by...").fill(filterValue);
	}

	public static void selectFilterValue(Page page, String filterCategory, String filterValue) {
		Locator filterValueLocator = page.locator(SELECT_FILTER_VALUE_XPATH.replace("{filterCategory}", filterCategory)
				.replace("{filterValue}", filterValue));
		filterValueLocator.waitFor();
		filterValueLocator.click();
	}

	public static void clickOnDiscoverableFunctionsButton(Page page) {
		page.getByTestId(DISCOVERABLE_FUNCTIONS_BUTTON_TESTID).click();
	}

	public static void searchFunctionCatalog(Page page, String catalogName) {
		Locator searchbox = page.getByTestId(FUNCTION_CATALOG_SEARCH_TEXTBOX_DATA_TESTID);
		AICorePageUtils.waitFor(searchbox);
		searchbox.click();
		searchbox.fill(catalogName);
	}

	public static void selectFunctionFromSearchOptions(Page page, String catalogName) {
		page.locator(SEARCHED_FUNCTION_XPATH.replace("{catalogName}", catalogName)).isVisible();
		page.locator(SEARCHED_FUNCTION_XPATH.replace("{catalogName}", catalogName)).click();
	}

	public static void deleteCatalog(Page page, String catalog, String catalogName) {
		//TODO duplicate code in CommonUtils.navigateAndDeleteCatalog!!!!!!
		Locator searchBar = page.getByTestId(SEARCH_BAR_DATATESTID);
		searchBar.click();
		searchBar.fill(catalogName);
		Locator catalogLocator = page.getByTestId(SEARCHED_CATALOG_DATATESTID
				.replace("{catalogType}", catalog.toUpperCase()).replace("{catalogName}", catalogName));
		if (catalogLocator.isVisible()) {
			catalogLocator.first().waitFor();
			catalogLocator.first().click();
			clickOnAccessControl(page);
			FunctionAccessSettingsUtils.clickOnDeleteButton(page);
			FunctionAccessSettingsUtils.clickOnDeleteConfirmationButton(page);
		}
	}

	public static void closeToastMessage(Page page) {
		// AICorePageUtils.closeToastMessage(page);
		Locator toast = page.locator("//li[@data-sonner-toast]").first();
		toast.waitFor();
		BoundingBox box = toast.boundingBox();
		double startX = box.x + 2;
		double startY = box.y + box.height / 2;
		page.mouse().move(startX, startY);
		page.mouse().down();
		page.mouse().move(startX + box.width + 300, startY, new Mouse.MoveOptions().setSteps(25));
		page.mouse().up();
	}

	public static boolean fieldUnderSection(Page page, String section, String field) {
		return AddFunctionFormUtils.fieldUnderSection(page, section, field);
	}

	public static boolean isFieldMandatory(Page page, String field) {
		return AddFunctionFormUtils.isFieldMandatory(page, field);
	}
	
	public static void fillFunctionCreationForm(Page page, String fieldName, String fieldValue, String timestamp) {
		AddFunctionFormUtils.fillFunctionCreationForm(page, fieldName, fieldValue, timestamp);
	}
}