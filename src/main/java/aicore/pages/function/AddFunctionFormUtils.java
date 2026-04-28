package aicore.pages.function;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;


public class AddFunctionFormUtils {
	
	private static final String CATALOG_NAME_DATA_TESTID = "function-form-input-NAME";
	private static final String HTTP_METHOD_DATA_TESTID = "function-form-input-HTTP_METHOD";
	private static final String POST_BODY_MESSAGE_DATA_TESTID = "function-form-input-CONTENT_TYPE";
	private static final String HEADERS_DATA_TESTID = "function-form-input-HEADERS";
	private static final String FUNCTION_PARAMETERS_DATA_TESTID = "function-form-input-FUNCTION_PARAMETERS";
	private static final String FUNCTION_REQUIRED_PARAMETERS_DATA_TESTID = "function-form-input-FUNCTION_REQUIRED_PARAMETERS";
	private static final String FUNCTION_NAME_DATA_TESTID = "function-form-input-FUNCTION_NAME";
	private static final String FUNCTION_DESCRIPTION_DATA_TESTID = "function-form-input-FUNCTION_DESCRIPTION";
	private static final String FUNCTION_TYPE_DATA_TESTID = "function-form-input-FUNCTION_TYPE";
	private static final String HTTP_METHOD_TYPE_TESTID = "function-form-option-HTTP_METHOD-{method}";
	private static final String POST_MESSAGE_BODY_TYPE_TESTID = "function-form-option-CONTENT_TYPE-json";
	private static final String URL_DATA_TESTID = "function-form-input-URL";
	private static final String CONNECT_BUTTON_DATA_TESTID = "function-form-submit";

	private static final String FIELDS_UNDER_SECTION_XPATH = "//div[//h4[normalize-space()='{section}']]/following-sibling::div//*[@data-testid='function-form-input-{fieldName}']";
	private static final String FILE_FIELD_UNDER_SECTION_XPATH = "//div[//h4[normalize-space()='Settings']]/following-sibling::div[@data-testid='function-form-field-{fieldName}']";
	private static final String MANDATORY_FIELDS_XPATH = "//label[text()='{fieldName}']//span[text()='*']";
	private static final String FIELDS_DATA_TESTID = "function-form-input-{fieldName}";
	private static final String DROPDOWN_FIELDS_XPATH = "//button[@data-testid='function-form-input-{fieldName}']";
	
	public static void selectFunction(Page page, String functionType) {
		page.getByText(functionType).first().isVisible();
		page.getByText(functionType).first().click();
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
		String fieldNameForTestId = getFieldNameForTestId(field);
		Locator fileFieldLocator = page.locator(FILE_FIELD_UNDER_SECTION_XPATH.replace("{section}", section)
				.replace("{fieldName}", fieldNameForTestId));
		Locator fieldLocator = page.locator(
				FIELDS_UNDER_SECTION_XPATH.replace("{section}", section).replace("{fieldName}", fieldNameForTestId));
		if (fieldLocator.isVisible()) {
			fieldLocator.scrollIntoViewIfNeeded();
			return fieldLocator.isVisible();
		}
		if (fileFieldLocator.isVisible()) {
			fileFieldLocator.scrollIntoViewIfNeeded();
			return fileFieldLocator.isVisible();
		}
		return false;
	}

	public static boolean isFieldMandatory(Page page, String field) {
		Locator mandatoryField = page.locator(MANDATORY_FIELDS_XPATH.replace("{fieldName}", field));
		if (mandatoryField.textContent().contains("*")) {
			return true;
		}
		return false;
	}

	public static void fillFunctionCreationForm(Page page, String fieldName, String fieldValue, String timestamp) {
		String fieldNameForTestId = getFieldNameForTestId(fieldName);
		Locator field = page.getByTestId(FIELDS_DATA_TESTID.replace("{fieldName}", fieldNameForTestId));
		field.scrollIntoViewIfNeeded();
		Locator dropdownField = page.locator(DROPDOWN_FIELDS_XPATH.replace("{fieldName}", fieldNameForTestId));
		if (dropdownField.count() > 0) {
			dropdownField.first().click();
			page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(fieldValue)).click();
		} else {
			if (fieldName.contains("Catalog Name")) {
				fieldValue = fieldValue + timestamp;
			}
			field.first().fill(fieldValue);
		}
	}
	
	public static boolean verifyMissingInputField(Page page) {
		Locator missingFieldParent = page.getByTestId(URL_DATA_TESTID).locator("..");
		missingFieldParent.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		String missingFieldClass = missingFieldParent.getAttribute("class");
		return missingFieldClass.contains("Mui-focused");
	}
}
