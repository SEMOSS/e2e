package aicore.pages;

import java.nio.file.FileSystems;
import java.nio.file.Paths;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class AddFunctionToCatalogPage {

	private Page page;
	private static final String ADD_FUNCTION_BUTTON = "Navigate to import Function";
	private static final String ADD_FILE_XPATH = "//input[@type='file']";
	private static final String ADD_FILE_NAME_XPATH = "//span[@title='{fileName}']";
	private static final String CREATE_FUNCTION_BUTTON = "Create Function";
	private static final String CATALOG_FUNCTION = "{FunctionName}";
	private static final String ACCESS_CONTROL_XPATH = "//button[text()='Access Control']";
	private static final String DELETE_BUTTON_XPATH = "//span[text()='Delete']";
	private static final String CONFIRMATION_POPUP_XPATH = "//div[contains(@class,'MuiDialog-paperWidthSm')]";
	private static final String CONFIRMATION_POPUP_DELETE_BUTTON_XPATH = "//div[contains(@class,'MuiDialog-paperWidthSm')]//div//button[contains(@class,'MuiButton-containedSizeMedium')]";
	private static final String DELETE_TOAST_MESSAGE = "Successfully deleted Function";
	private static final String FUNCTION_SECTION_NAME_XPATH = "//div[text()='{sectionName}']";
	private static final String DATABASE_OPTIONS_UNDER_SECTION_XPATH = "//div[text()='{sectionName}']/following-sibling::div//p[text()='{optionName}']";
	private static final String ICONS_XPATH = "//p[text()='{optionName}']/parent::div//img";

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

	public boolean isSearchBarPresent() {
		return page.getByPlaceholder("Search").isVisible();
	}

	public boolean verifySectionIsVisible(String sectionName) {
		boolean isSectionVisible = page.isVisible(FUNCTION_SECTION_NAME_XPATH.replace("{sectionName}", sectionName));
		return isSectionVisible;
	}

	public boolean VerifyDatabaseOptionIsVisible(String sectionName, String databaseOptionName) {
		boolean isOptionVisible = page.isVisible(DATABASE_OPTIONS_UNDER_SECTION_XPATH
				.replace("{sectionName}", sectionName).replace("{optionName}", databaseOptionName));
		return isOptionVisible;
	}

	public Locator getIconByLabel(String optionName) {
		return page.locator(ICONS_XPATH.replace("{optionName}", optionName));
	}

	public boolean isIconVisible(String optionName) {
		return page.locator(ICONS_XPATH.replace("{optionName}", optionName)).isVisible();
	}

}
