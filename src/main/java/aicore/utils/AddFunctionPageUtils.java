package aicore.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class AddFunctionPageUtils {

	private static final String ADD_FUNCTION_BUTTON = "Navigate to import Function";
	private static final String CATALOG_NAME_DATA_TESTID = "function-form-input-NAME";
	private static final String URL_DATA_TESTID = "function-form-input-URL";
	private static final String HTTP_METHOD_DATA_TESTID = "function-form-input-HTTP_METHOD";
	private static final String POST_BODY_MESSAGE_DATA_TESTID = "function-form-input-CONTENT_TYPE";
	private static final String HEADERS_DATA_TESTID = "function-form-input-HEADERS";
	private static final String FUNCTION_PARAMETERS_DATA_TESTID = "function-form-input-FUNCTION_PARAMETERS";
	private static final String FUNCTION_REQUIRED_PARAMETERS_DATA_TESTID = "function-form-input-FUNCTION_REQUIRED_PARAMETERS";
	private static final String FUNCTION_NAME_DATA_TESTID = "function-form-input-FUNCTION_NAME";
	private static final String FUNCTION_DESCRIPTION_DATA_TESTID = "function-form-input-FUNCTION_DESCRIPTION";
	private static final String FUNCTION_TYPE_DATA_TESTID = "function-form-input-FUNCTION_TYPE";
	private static final String FIELDS_UNDER_SECTION_XPATH = "//h6[text()='{section}']/parent::div/following-sibling::div//div[@data-testid='function-form-input-{fieldName}']";
	private static final String MANDATORY_FIELDS_XPATH = "//div[@data-testid='function-form-input-{fieldName}']//span[text()='*']";
	private static final String FIELDS_DATA_TESTID = "function-form-input-{fieldName}";
	private static final String INPUT_FIELDS_XPATH = "//div[@data-testid='function-form-input-{fieldName}']//input[@type='text'] | .//textarea";
	private static final String DROPDOWN_FIELDS_XPATH = "//div[@data-testid='function-form-input-{fieldName}']//*[@role='button' or @aria-haspopup='listbox']";
	private static final String PASSWORD_FIELDS_XPATH = "//div[@data-testid='function-form-input-{fieldName}']//input[@type='password'] | .//textarea";
	private static final String SELECT_DROPDOWN_VALUE_XPATH = "//li[normalize-space()='{fieldValue}']";
	private static final String CONNECT_BUTTON_DATA_TESTID = "function-form-submit";
	private static final String CATALOG_FUNCTION = "{FunctionName}";
	private static final String CATALOG_FUNCTION_XPATH = "//div[contains(@data-testid,'genericEngineCards-FUNCTION')]//p[(text()='{FunctionName}')]";
	public static final String OPEN_FUNCTIONS_XPATH = "SwitchAccessShortcutOutlinedIcon";
	private static final String ACCESS_CONTROL_XPATH = "//button[text()='Access Control']";
	private static final String SETTINGS_TAB_XPATH = "//button[text()='Settings']";
	private static final String DELETE_BUTTON_XPATH = "//button[contains(@data-testid,'-delete-btn')]";
	private static final String CONFIRMATION_POPUP_XPATH = "//div[@data-slot='dialog-content']";
	private static final String CONFIRMATION_POPUP_DELETE_BUTTON_XPATH = "//button[contains(@data-testid,'confirmDelete-btn')]";
	private static final String DELETE_TOAST_MESSAGE = "Successfully deleted Function";
	private static final String MAKE_DISCOVERABLE_BUTTON_DATATESTID = "settingsTiles-{catalogName}-makeDiscoverable-switch";
	private static final String SELECT_FILTER_VALUE_XPATH = "//h6[text()='{filterCategory}']/ancestor::li/following-sibling::div//p[text()='{filterValue}']";
	private static final String DISCOVERABLE_FUNCTIONS_BUTTON_XPATH = "//button[text()='Discoverable Functions']";
	private static final String FUNCTION_CATALOG_SEARCH_TEXTBOX_DATA_TESTID = "Search";
	private static final String SEARCHED_FUNCTION_DATATESTID = "genericEngineCards-DATABASE-{catalogName}";
	private static final String SEARCHED_CATALOG_DATATESTID = "genericEngineCards-{catalogType}-{catalogName}";
	private static final String HTTP_METHOD_TYPE_TESTID = "function-form-option-HTTP_METHOD-{method}";
	private static final String POST_MESSAGE_BODY_TYPE_TESTID = "function-form-option-CONTENT_TYPE-json";
	private static final String SEARCH_BAR_DATATESTID = "search-bar";
	private static final String TOASTER_MESSAGE_XPATH = "//div[text()='{toastMessage}']";
	private static final String DISCOVERABLE_FUNCTIONS_BUTTON_TESTID = "engineIndexPage-Functions-discoverable-switch";

	public static void clickOnAddFunctionButton(Page page) {
		page.getByLabel(ADD_FUNCTION_BUTTON).isVisible();
		page.getByLabel(ADD_FUNCTION_BUTTON).click();
	}

	public static void selectFunction(Page page, String functionType) {
		page.getByText(functionType).first().isVisible();
		page.getByText(functionType).first().click();
	}

	private static String getFieldNameForTestId(String field) {
		String fieldNamesForDataTestid = switch (field) {
		case "Catalog Name" -> "Name";
		case "Function Name (metadata)" -> "Function Name";
		case "Function Description (metadata)" -> "Function Description";
		case "S3 Bucket Engine Id" -> "S3BucketEngineId";
		case "Google Bucket Engine Id" -> "Google Bucket EngineId";
		case "Upload Service Account File" -> "File";
		case "POST Message Body Type" -> "Content Type";
		case "Http Headers" -> "Headers";
		default -> field;
		};
		return fieldNamesForDataTestid.replace(" ", "_").toUpperCase();
	}

	public static boolean fieldUnderSection(Page page, String section, String field) {
		String fieldName = getFieldNameForTestId(field);
		Locator fieldLocator = page
				.locator(FIELDS_UNDER_SECTION_XPATH.replace("{section}", section).replace("{fieldName}", fieldName));
		fieldLocator.scrollIntoViewIfNeeded();
		return fieldLocator.isVisible();
	}

	public static boolean isFieldMandatory(Page page, String field) {
		String fieldName = getFieldNameForTestId(field);
		Locator fieldLocator = page.locator(MANDATORY_FIELDS_XPATH.replace("{fieldName}", fieldName));
		fieldLocator.first().scrollIntoViewIfNeeded();
		return fieldLocator.first().isVisible();
	}

	public static void fillFunctionCreationForm(Page page, String field, String fieldValue, String timestamp) {
		String fieldName = getFieldNameForTestId(field);
		Locator fieldContainer = page.getByTestId(FIELDS_DATA_TESTID.replace("{fieldName}", fieldName));
		fieldContainer.scrollIntoViewIfNeeded();
		Locator dropdownField = page.locator(DROPDOWN_FIELDS_XPATH.replace("{fieldName}", fieldName));
		Locator inputField = page.locator(INPUT_FIELDS_XPATH.replace("{fieldName}", fieldName));
		Locator passwordField = page.locator(PASSWORD_FIELDS_XPATH.replace("{fieldName}", fieldName));
		if (dropdownField.count() > 0) {
			dropdownField.first().click();
			Locator dropdownOption = page.locator(SELECT_DROPDOWN_VALUE_XPATH.replace("{fieldValue}", fieldValue));
			dropdownOption.click();
		} else if (passwordField.count() > 0) {
			passwordField.first().fill(fieldValue);
		} else {
			if (field.contains("Catalog Name")) {
				fieldValue = fieldValue + timestamp;
			}
			inputField.first().fill(fieldValue);
		}
	}

	public static void enterCatalogName(Page page, String catalogName, String timestamp) {
		catalogName = catalogName.replace("{Timestamp}", " " + timestamp);
		page.getByTestId(CATALOG_NAME_DATA_TESTID).click();
		page.getByTestId(CATALOG_NAME_DATA_TESTID).fill(catalogName);
	}

	public static void enterUrl(Page page, String url) {
		page.getByTestId(URL_DATA_TESTID).click();
		page.getByTestId(URL_DATA_TESTID).fill(url);
	}

	public static void selectHttpMethod(Page page, String httpMethod) {
		page.getByTestId(HTTP_METHOD_DATA_TESTID).isVisible();
		page.getByTestId(HTTP_METHOD_DATA_TESTID).click();
		page.getByTestId(HTTP_METHOD_TYPE_TESTID.replace("{method}", httpMethod)).isVisible();
		page.getByTestId(HTTP_METHOD_TYPE_TESTID.replace("{method}", httpMethod)).click();
	}

	public static void selectPostBodyMessage(Page page, String postBodyMessage) {
		page.getByTestId(POST_BODY_MESSAGE_DATA_TESTID).isVisible();
		page.getByTestId(POST_BODY_MESSAGE_DATA_TESTID).click();
		page.getByTestId(POST_MESSAGE_BODY_TYPE_TESTID.replace("{type}", postBodyMessage)).isVisible();
		page.getByTestId(POST_MESSAGE_BODY_TYPE_TESTID.replace("{type}", postBodyMessage)).click();
	}

	public static void enterHeaders(Page page, String headers) {
		page.getByTestId(HEADERS_DATA_TESTID).click();
		page.getByTestId(HEADERS_DATA_TESTID).fill(headers);
	}

	public static void enterFunctionParameters(Page page, String functionParameters) {
		page.getByTestId(FUNCTION_PARAMETERS_DATA_TESTID).click();
		page.getByTestId(FUNCTION_PARAMETERS_DATA_TESTID).fill(functionParameters);
	}

	public static void enterFunctionName(Page page, String functionName) {
		page.getByTestId(FUNCTION_NAME_DATA_TESTID).click();
		page.getByTestId(FUNCTION_NAME_DATA_TESTID).fill(functionName);
	}

	public static void enterFunctionDescription(Page page, String functionDescription) {
		page.getByTestId(FUNCTION_DESCRIPTION_DATA_TESTID).click();
		page.getByTestId(FUNCTION_DESCRIPTION_DATA_TESTID).fill(functionDescription);
	}

	public static void selectFunctionType(Page page, String functionType) {
		page.getByTestId(FUNCTION_TYPE_DATA_TESTID).isVisible();
		page.getByTestId(FUNCTION_TYPE_DATA_TESTID).click();
		page.getByTestId(FUNCTION_TYPE_DATA_TESTID).fill(functionType);
	}

	public static boolean verifyCreateFunctionButtonDisabled(Page page) {
		return page.getByTestId(CONNECT_BUTTON_DATA_TESTID).isDisabled();
	}

	public static void enterFunctionRequiredParameters(Page page, String functionRequiredParameters) {
		page.getByTestId(FUNCTION_REQUIRED_PARAMETERS_DATA_TESTID).click();
		page.getByTestId(FUNCTION_REQUIRED_PARAMETERS_DATA_TESTID).fill(functionRequiredParameters);
	}

	public static void checkCreateFunctionButton(Page page) {
		page.getByTestId(CONNECT_BUTTON_DATA_TESTID).isVisible();
	}

	public static boolean validateConnectButtonEnabled(Page page) {
		Locator connectButton = page.getByTestId(CONNECT_BUTTON_DATA_TESTID);
		connectButton.scrollIntoViewIfNeeded();
		return connectButton.isEnabled();
	}

	public static void clickOnConnectButton(Page page) {
		Locator connectButton = page.getByTestId(CONNECT_BUTTON_DATA_TESTID);
		connectButton.scrollIntoViewIfNeeded();
		connectButton.click();
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

	public static String verifySuccessToastMessage(Page page, String toastMessage) {
		Locator alert = page.locator(TOASTER_MESSAGE_XPATH.replace("{toastMessage}", toastMessage));
		alert.scrollIntoViewIfNeeded();
		AICorePageUtils.waitFor(alert);
		return AICorePageUtils.verifySuccessToastMessage(page, alert);
	}

	public static boolean verifyMissingInputField(Page page) {
		Locator missingFieldParent = page.getByTestId(URL_DATA_TESTID).locator("..");
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
				.getByTestId(MAKE_DISCOVERABLE_BUTTON_DATATESTID.replace("{catalogName}", catalogName));
		makeDiscoverableButton.isVisible();
		makeDiscoverableButton.click();
	}

	public static void clickOnDiscoverableFunctionsbutton(Page page) {
		page.getByTestId(DISCOVERABLE_FUNCTIONS_BUTTON_TESTID).click();
	}

	public static void searchFunctionCatalog(Page page, String catalogName) {
		Locator searchbox = page.getByTestId(FUNCTION_CATALOG_SEARCH_TEXTBOX_DATA_TESTID);
		AICorePageUtils.waitFor(searchbox);
		searchbox.click();
		searchbox.fill(catalogName);
	}

	public static void selectFunctionFromSearchOptions(Page page, String catalogName) {
		page.getByTestId(SEARCHED_FUNCTION_DATATESTID.replace("{catalogName}", catalogName)).isVisible();
		page.getByTestId(SEARCHED_FUNCTION_DATATESTID.replace("{catalogName}", catalogName)).click();
	}

	public static void deleteCatalog(Page page, String catalog, String catalogName) {
		Locator searchBar = page.getByTestId(SEARCH_BAR_DATATESTID);
		searchBar.click();
		searchBar.fill(catalogName);
		Locator catalogLocator = page.getByTestId(SEARCHED_CATALOG_DATATESTID
				.replace("{catalogType}", catalog.toUpperCase()).replace("{catalogName}", catalogName));
		if (catalogLocator.isVisible()) {
			catalogLocator.first().waitFor();
			catalogLocator.first().click();
			clickOnAccessControl(page);
			clickOnDeleteButton(page);
			clickOnDeleteConfirmationButton(page);
		}
	}

	public static void closeToastMessage(Page page) {
		AICorePageUtils.closeToastMessage(page);
	}

}
