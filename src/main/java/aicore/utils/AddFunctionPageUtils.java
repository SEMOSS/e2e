package aicore.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class AddFunctionPageUtils {

	private static final String ADD_FUNCTION_BUTTON = "Navigate to import Function";
	private static final String CATALOG_NAME = "importForm-NAME-textField";
	private static final String URL = "importForm-URL-textField";
	private static final String HTTP_METHOD = "importForm-HTTP_METHOD-select";
	private static final String POST_BODY_MESSAGE = "importForm-CONTENT_TYPE-select";
	private static final String HEADERS = "importForm-HEADERS-textField";
	private static final String FUNCTION_PARAMETERS = "importForm-FUNCTION_PARAMETERS-textField";
	private static final String FUNCTION_REQUIRED_PARAMETERS = "importForm-FUNCTION_REQUIRED_PARAMETERS-textField";
	private static final String FUNCTION_NAME = "importForm-FUNCTION_NAME-textField";
	private static final String FUNCTION_DESCRIPTION = "importForm-FUNCTION_DESCRIPTION-textField";
	private static final String FUNCTION_TYPE = "importForm-FUNCTION_TYPE-textField";
	private static final String ADD_FILE_XPATH = "//input[@type='file']";
	private static final String ADD_FILE_NAME_XPATH = "//span[@title='{fileName}']";
	private static final String CREATE_FUNCTION_BUTTON = "Create Function";
	private static final String CATALOG_FUNCTION = "{FunctionName}";
	private static final String CATALOG_FUNCTION_XPATH = "//div[contains(@class,'MuiCard-root')]//p[(text()='{FunctionName}')]";
	public static final String OPEN_FUNCTIONS_XPATH = "SwitchAccessShortcutOutlinedIcon";
	private static final String ACCESS_CONTROL_XPATH = "//button[text()='Access Control']";
	private static final String SETTINGS_TAB_XPATH = "//button[text()='Settings']";
	private static final String DELETE_BUTTON_XPATH = "//span[text()='Delete']";
	private static final String CONFIRMATION_POPUP_XPATH = "//div[contains(@class,'MuiDialog-paperWidthSm')]";
	private static final String CONFIRMATION_POPUP_DELETE_BUTTON_XPATH = "//div[contains(@class,'MuiDialog-paperWidthSm')]//div//button[contains(@class,'MuiButton-containedSizeMedium')]";
	private static final String DELETE_TOAST_MESSAGE = "Successfully deleted Function";
	private static final String MAKE_DISCOVERABLE_BUTTON_XPATH = "//span[@title='Make {catalogName} discoverable']";
	private static final String SELECT_FILTER_VALUE_XPATH = "//h6[text()='{filterCategory}']/ancestor::li/following-sibling::div//p[text()='{filterValue}']";
	private static final String DISCOVERABLE_FUNCTIONS_BUTTON_XPATH = "//button[text()='Discoverable Functions']";
	private static final String FUNCTION_CATALOG_SEARCH_TEXTBOX_XPATH = "//input[@placeholder='Search']";
	private static final String SEARCHED_FUNCTION_XPATH = "//p[text()='{catalogName}']";
	private static final String HTTP_METHOD_TYPE_TESTID = "importForm-{type}-item";
	private static final String SEARCH_BAR_XPATH = "//*[@data-testid='engineIndexPage-searchBar-{catalog}']//input";

	public static void clickOnAddFunctionButton(Page page) {
		page.getByLabel(ADD_FUNCTION_BUTTON).isVisible();
		page.getByLabel(ADD_FUNCTION_BUTTON).click();
	}

	public static void selectFunction(Page page, String functionType) {
		page.getByText(functionType).isVisible();
		page.getByText(functionType).click();
	}

	public static void enterCatalogName(Page page, String catalogName, String timestamp) {
		catalogName = catalogName.replace("{Timestamp}", " " + timestamp);
		page.getByTestId(CATALOG_NAME).click();
		page.getByTestId(CATALOG_NAME).fill(catalogName);

	}

	public static void enterUrl(Page page, String url) {
		page.getByTestId(URL).click();
		page.getByTestId(URL).fill(url);
	}

	public static void selectHttpMethod(Page page, String httpMethod) {
		page.getByTestId(HTTP_METHOD).isVisible();
		page.getByTestId(HTTP_METHOD).click();
		page.getByTestId(HTTP_METHOD_TYPE_TESTID.replace("{type}", httpMethod)).isVisible();
		page.getByTestId(HTTP_METHOD_TYPE_TESTID.replace("{type}", httpMethod)).click();
	}

	public static void selectPostBodyMessage(Page page, String postBodyMessage) {
		page.getByTestId(POST_BODY_MESSAGE).isVisible();
		page.getByTestId(POST_BODY_MESSAGE).click();
		page.getByTestId(HTTP_METHOD_TYPE_TESTID.replace("{type}", postBodyMessage)).isVisible();
		page.getByTestId(HTTP_METHOD_TYPE_TESTID.replace("{type}", postBodyMessage)).click();
	}

	public static void verifyAsteriskMarkOnFields(Page page, String fieldLabels) {
		String[] labels = fieldLabels.split(",");
		for (String label : labels) {
			String asteriskSelector = "//label[text()='%s']/span[text()='*']".replace("%s", label.trim());
			if (!page.locator(asteriskSelector).isVisible()
					|| !page.locator(asteriskSelector).textContent().contains("*")) {
				throw new AssertionError(
						"Asterisk mark is not visible or does not contain '*' for the field: " + label.trim());
			}
		}
	}

	public static void enterHeaders(Page page, String headers) {
		page.getByTestId(HEADERS).click();
		page.getByTestId(HEADERS).fill(headers);
	}

	public static void enterFunctionParameters(Page page, String functionParameters) {
		page.getByTestId(FUNCTION_PARAMETERS).click();
		page.getByTestId(FUNCTION_PARAMETERS).fill(functionParameters);
	}

	public static void enterFunctionName(Page page, String functionName) {
		page.getByTestId(FUNCTION_NAME).click();
		page.getByTestId(FUNCTION_NAME).fill(functionName);
	}

	public static void enterFunctionDescription(Page page, String functionDescription) {
		page.getByTestId(FUNCTION_DESCRIPTION).click();
		page.getByTestId(FUNCTION_DESCRIPTION).fill(functionDescription);
	}

	public static void selectFunctionType(Page page, String functionType) {
		page.getByTestId(FUNCTION_TYPE).isVisible();
		page.getByTestId(FUNCTION_TYPE).click();
		page.getByTestId(FUNCTION_TYPE).fill(functionType);
	}

	public static boolean verifyCreateFunctionButtonDisabled(Page page) {
		return page.getByText(CREATE_FUNCTION_BUTTON).isDisabled();
	}

	public static void enterFunctionRequiredParameters(Page page, String functionRequiredParameters) {
		page.getByTestId(FUNCTION_REQUIRED_PARAMETERS).click();
		page.getByTestId(FUNCTION_REQUIRED_PARAMETERS).fill(functionRequiredParameters);
	}

	public static void checkCreateFunctionButton(Page page) {
		page.getByText(CREATE_FUNCTION_BUTTON).isVisible();
	}

	public static void clickOnCreateFunctionButton(Page page) {
		page.getByText(CREATE_FUNCTION_BUTTON).isVisible();
		page.getByText(CREATE_FUNCTION_BUTTON).isEnabled();
		page.getByText(CREATE_FUNCTION_BUTTON).click();
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

	public static void clickOnAccessControl(Page page) {
		page.locator(ACCESS_CONTROL_XPATH).isVisible();
		page.locator(ACCESS_CONTROL_XPATH).click();
	}

	public static void clickOnSettings(Page page) {
		Locator settingsTab = page.locator(SETTINGS_TAB_XPATH);
		AICorePageUtils.waitFor(settingsTab);
		settingsTab.click();
	}

	public static void clickOnDeleteButton(Page page) {
		page.locator(DELETE_BUTTON_XPATH).isVisible();
		page.locator(DELETE_BUTTON_XPATH).click();
	}

	public static void clickOnDeleteConfirmationButton(Page page) {
		page.locator(CONFIRMATION_POPUP_XPATH)
				.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		page.locator(CONFIRMATION_POPUP_DELETE_BUTTON_XPATH).isVisible();
		page.locator(CONFIRMATION_POPUP_DELETE_BUTTON_XPATH).click();
	}

	public static String verifyDeleteToastMessage(Page page) {
		page.getByText(DELETE_TOAST_MESSAGE)
				.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		String toastMessage = page.getByText(DELETE_TOAST_MESSAGE).textContent();
		return toastMessage;
	}

	public static String verifySuccessToastMessage(Page page) {
		Locator alert = page.getByTestId("notification-success-alert");
		return AICorePageUtils.verifySuccessToastMessage(page, alert);
	}

	public static boolean verifyMissingInputField(Page page) {
		Locator missingFieldParent = page.getByTestId(URL).locator("..");
		missingFieldParent.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		String missingFieldClass = missingFieldParent.getAttribute("class");
		return missingFieldClass.contains("Mui-focused");
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

	public static void clickOnMakeDiscoverableButton(Page page, String catalogName) {
		Locator makeDiscoverableButton = page
				.locator(MAKE_DISCOVERABLE_BUTTON_XPATH.replace("{catalogName}", catalogName));
		makeDiscoverableButton.isVisible();
		makeDiscoverableButton.click();
	}

	public static void clickOnDiscoverableFunctionsbutton(Page page) {
		page.locator(DISCOVERABLE_FUNCTIONS_BUTTON_XPATH).click();
	}

	public static void searchFunctionCatalog(Page page, String catalogName) {
		page.waitForSelector(FUNCTION_CATALOG_SEARCH_TEXTBOX_XPATH);
		page.locator(FUNCTION_CATALOG_SEARCH_TEXTBOX_XPATH).click();
		page.locator(FUNCTION_CATALOG_SEARCH_TEXTBOX_XPATH).fill(catalogName);
	}

	public static void selectFunctionFromSearchOptions(Page page, String catalogName) {
		page.locator((SEARCHED_FUNCTION_XPATH.replace("{catalogName}", catalogName))).isVisible();
		page.locator(SEARCHED_FUNCTION_XPATH.replace("{catalogName}", catalogName)).click();
	}

	public static void deleteCatalog(Page page, String catalog, String catalogName) {
		Locator searchBar = page.locator(SEARCH_BAR_XPATH.replace("{catalog}",catalog));
		searchBar.click();
		searchBar.fill(catalog);
		Locator catalogLocator = page.locator((SEARCHED_FUNCTION_XPATH.replace("{catalogName}", catalogName)));
		if(catalogLocator.isVisible()) {
		catalogLocator.click();
		clickOnAccessControl(page);
		clickOnDeleteButton(page);
		clickOnDeleteConfirmationButton(page);
	}
	}

}
