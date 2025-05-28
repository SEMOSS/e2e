package aicore.pages;

import java.nio.file.FileSystems;
import java.nio.file.Paths;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class AddFunctionToCatalogPage extends AbstractAddCatalogPageBase {

	private static final String ADD_FUNCTION_BUTTON = "Navigate to import Function";
	private static final String CATALOG_NAME = "importForm-textField-NAME";
	private static final String URL = "importForm-textField-URL";
	private static final String HTTP_METHOD = "importForm-selectField-HTTP_METHOD";
	private static final String POST_BODY_MESSAGE = "importForm-selectField-CONTENT_TYPE";
	private static final String HEADERS = "importForm-textField-HEADERS";
	private static final String FUNCTION_PARAMETERS = "importForm-textField-FUNCTION_PARAMETERS";
	private static final String FUNCTION_REQUIRED_PARAMETERS = "importForm-textField-FUNCTION_REQUIRED_PARAMETERS";
	private static final String FUNCTION_NAME = "importForm-textField-FUNCTION_NAME";
	private static final String FUNCTION_DESCRIPTION = "importForm-textField-FUNCTION_DESCRIPTION";
	private static final String FUNCTION_TYPE = "importForm-selectField-FUNCTION_TYPE";
	private static final String ADD_FILE_XPATH = "//input[@type='file']";
	private static final String ADD_FILE_NAME_XPATH = "//span[@title='{fileName}']";
	private static final String CREATE_FUNCTION_BUTTON = "Create Function";
	private static final String CATALOG_FUNCTION = "{FunctionName}";
	private static final String ACCESS_CONTROL_XPATH = "//button[text()='Access Control']";
	private static final String DELETE_BUTTON_XPATH = "//span[text()='Delete']";
	private static final String CONFIRMATION_POPUP_XPATH = "//div[contains(@class,'MuiDialog-paperWidthSm')]";
	private static final String CONFIRMATION_POPUP_DELETE_BUTTON_XPATH = "//div[contains(@class,'MuiDialog-paperWidthSm')]//div//button[contains(@class,'MuiButton-containedSizeMedium')]";
	private static final String DELETE_TOAST_MESSAGE = "Successfully deleted Function";
	private static final String MAKE_DISCOVERABLE_BUTTON_XPATH = "//span[@title='Make Function discoverable']/child::input[@type='checkbox']";

	private static final String SELECT_FILTER_VALUE_XPATH = "//h6[text()='{filterCategory}']/ancestor::li/following-sibling::div//p[text()='{filterValue}']";
	private static final String DISCOVERABLE_FUNCTIONS_BUTTON_XPATH = "//button[text()='Discoverable Functions']";

	public AddFunctionToCatalogPage(Page page) {
		this.page = page;
	}

	public void clickOnAddFunctionButton() {
		page.getByLabel(ADD_FUNCTION_BUTTON).isVisible();
		page.getByLabel(ADD_FUNCTION_BUTTON).click();
	}

	public void selectFunction(String functionType) {
		page.getByText(functionType).isVisible();
		page.getByText(functionType).click();
	}

	public void enterCatalogName(String catalogName) {
		page.getByTestId(CATALOG_NAME).click();
		page.getByTestId(CATALOG_NAME).fill(catalogName);
	}

	public void enterUrl(String url) {
		page.getByTestId(URL).click();
		page.getByTestId(URL).fill(url);
	}

	public void selectHttpMethod(String httpMethod) {
		page.getByTestId(HTTP_METHOD).isVisible();
		page.getByTestId(HTTP_METHOD).click();
		page.getByTestId(httpMethod).isVisible();
		page.getByTestId(httpMethod).click();
	}

	public void selectPostBodyMessage(String postBodyMessage) {
		page.getByTestId(POST_BODY_MESSAGE).isVisible();
		page.getByTestId(POST_BODY_MESSAGE).click();
		page.getByTestId(postBodyMessage).isVisible();
		page.getByTestId(postBodyMessage).click();
	}

	public void verifyAsteriskMarkOnFields(String fieldLabels) {
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

	public void enterHeaders(String headers) {
		page.getByTestId(HEADERS).click();
		page.getByTestId(HEADERS).fill(headers);
	}

	public void enterFunctionParameters(String functionParameters) {
		page.getByTestId(FUNCTION_PARAMETERS).click();
		page.getByTestId(FUNCTION_PARAMETERS).fill(functionParameters);
	}

	public void enterFunctionName(String functionName) {
		page.getByTestId(FUNCTION_NAME).click();
		page.getByTestId(FUNCTION_NAME).fill(functionName);
	}

	public void enterFunctionDescription(String functionDescription) {
		page.getByTestId(FUNCTION_DESCRIPTION).click();
		page.getByTestId(FUNCTION_DESCRIPTION).fill(functionDescription);
	}

	public void selectFunctionType(String functionType) {
		page.getByTestId(FUNCTION_TYPE).isVisible();
		page.getByTestId(FUNCTION_TYPE).click();
		page.getByTestId(FUNCTION_TYPE).fill(functionType);
	}

	public void enterFunctionRequiredParameters(String functionRequiredParameters) {
		page.getByTestId(FUNCTION_REQUIRED_PARAMETERS).click();
		page.getByTestId(FUNCTION_REQUIRED_PARAMETERS).fill(functionRequiredParameters);
	}

	public void clickOnCreateFunctionButton() {
		page.getByText(CREATE_FUNCTION_BUTTON).isVisible();
		page.getByText(CREATE_FUNCTION_BUTTON).isEnabled();
		page.getByText(CREATE_FUNCTION_BUTTON).click();
	}

	public String enterFilePath(String fileName) {
		String pathSeparator = FileSystems.getDefault().getSeparator();
		Locator fileInput = page.locator(ADD_FILE_XPATH);
		String relativePath = "src" + pathSeparator + "test" + pathSeparator + "resources" + pathSeparator + "data"
				+ pathSeparator;
		if (fileName.contains("/")) {
			fileName.replace("/", pathSeparator);
		}
		fileInput.setInputFiles(Paths.get(relativePath + fileName));
		if (fileName.contains("/")) {
			String[] ActualFileName = fileName.split("/");
			int fileNameIndex = ActualFileName.length - 1;
			Locator uploadedFileName = page
					.locator(ADD_FILE_NAME_XPATH.replace("{fileName}", ActualFileName[fileNameIndex]));
			String uploadedFileNameValue = uploadedFileName.textContent();
			return uploadedFileNameValue;
		} else {
			Locator uploadedFileName = page.locator(ADD_FILE_NAME_XPATH.replace("{fileName}", fileName));
			String uploadedFileNameValue = uploadedFileName.textContent();
			return uploadedFileNameValue;
		}
	}

	public String verifyFunctionNameInCatalog(String functionName) {
		page.getByText(CATALOG_FUNCTION.replace("{FunctionName}", functionName)).isVisible();
		String functionNameInCatalog = page.getByText(CATALOG_FUNCTION.replace("{FunctionName}", functionName))
				.textContent();
		return functionNameInCatalog;
	}

	public boolean verifyFunctionIsVisbileInCatalog(String functionName) {
		boolean isFunctionVisible = page.getByText(CATALOG_FUNCTION.replace("{FunctionName}", functionName))
				.isVisible();
		return isFunctionVisible;
	}

	public void clickOnFunctionNameInCatalog(String functionName) {
		page.getByText(CATALOG_FUNCTION.replace("{FunctionName}", functionName)).click();
	}

	public void clickOnAccessControl() {
		page.locator(ACCESS_CONTROL_XPATH).isVisible();
		page.locator(ACCESS_CONTROL_XPATH).click();
	}

	public void clickOnDeleteButton() {
		page.locator(DELETE_BUTTON_XPATH).isVisible();
		page.locator(DELETE_BUTTON_XPATH).click();
	}

	public void clickOnDeleteConfirmationButton() {
		page.locator(CONFIRMATION_POPUP_XPATH)
				.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		page.locator(CONFIRMATION_POPUP_DELETE_BUTTON_XPATH).isVisible();
		page.locator(CONFIRMATION_POPUP_DELETE_BUTTON_XPATH).click();
	}

	public String verifyDeleteToastMessage() {
		page.getByText(DELETE_TOAST_MESSAGE)
				.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		String toastMessage = page.getByText(DELETE_TOAST_MESSAGE).textContent();
		return toastMessage;
	}

	public void searchFilterValue(String filterValue) {
		page.getByPlaceholder("Search by...").fill(filterValue);
	}

	public void selectFilterValue(String filterCategory, String filterValue) {
		Locator filterValueLocator = page.locator(SELECT_FILTER_VALUE_XPATH.replace("{filterCategory}", filterCategory)
				.replace("{filterValue}", filterValue));
		filterValueLocator.waitFor();
		filterValueLocator.click();
	}

	public void clickOnMakeDiscoverableButton() {
		page.locator(MAKE_DISCOVERABLE_BUTTON_XPATH).isVisible();
		page.locator(MAKE_DISCOVERABLE_BUTTON_XPATH).click();
	}

	public void clickOnDiscoverableFunctionsbutton() {
		page.locator(DISCOVERABLE_FUNCTIONS_BUTTON_XPATH).click();
	}

}
