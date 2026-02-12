package aicore.utils;

import java.nio.file.FileSystems;
import java.nio.file.Paths;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class CatalogCreationFromZipUtil {
	private static final String DATABASE_MENU_BUTTON_XPATH = "//div[@aria-label='Database']";
	private static final String FUNCTION_MENU_BUTTON_XPATH = "//div[@aria-label='Function']";
	private static final String MODEL_MENU_BUTTON_XPATH = "//div[@aria-label='Model']";
	private static final String STORAGE_MENU_BUTTON_XPATH = "//div[@aria-label='Storage']";
	private static final String VECTOR_MENU_BUTTON_XPATH = "//div[@aria-label='Vector']";
	private static final String ADD_CATALOG_BUTTON_DATA_TESTID = "engineIndex-add-{catalog}-btn";
	private static final String ADD_FILE_XPATH = "//input[@type='file']";
	private static final String ADD_FILE_NAME_XPATH = "//span[normalize-space()='{fileName}']";
	private static final String CREATE_CATALOG_BUTTON_DATA_TESTID = "importForm-submit-btn";
	private static final String UPLOAD_FILE_BUTTON_XPATH = "//span[normalize-space()='Upload']";

	public static void openCatalog(Page page, String catalogName) {
		Locator locator = null;
		switch (catalogName) {
		case "Model":
			locator = page.locator(MODEL_MENU_BUTTON_XPATH);
			break;
		case "Database":
			locator = page.locator(DATABASE_MENU_BUTTON_XPATH);
			break;
		case "Function":
			locator = page.locator(FUNCTION_MENU_BUTTON_XPATH);
			break;
		case "Storage":
			locator = page.locator(STORAGE_MENU_BUTTON_XPATH);
			break;
		case "Vector":
			locator = page.locator(VECTOR_MENU_BUTTON_XPATH);
			break;
		default:
			throw new IllegalArgumentException("Invalid block name: " + catalogName);
		}
		locator.click();
		HomePageUtils.closeMainMenu(page);
	}

	public static void clickOnAddCatalogButton(Page page, String catalogName) {
		Locator locator = page.getByTestId(ADD_CATALOG_BUTTON_DATA_TESTID.replace("{catalog}", catalogName));
		locator.click();
	}

	public static void selectAddCatalogOption(Page page, String option) {
		page.getByText(option).isVisible();
		page.getByText(option).click();
	}

	public static void clickOnFileUploadIcon(Page page) {
		Locator icon = page.locator("//button[contains(@data-testid,'upload-file-button')]");
		AICorePageUtils.waitFor(icon);
		icon.click();
	}

	public static String uploadFile(Page page, String fileName) {
		String pathSeparator = FileSystems.getDefault().getSeparator();
		Locator fileInput = page.locator(ADD_FILE_XPATH);
		String relativePath = "src" + pathSeparator + "test" + pathSeparator + "resources" + pathSeparator + "data"
				+ pathSeparator;
		if (fileName.contains("/")) {
			fileName.replace("/", pathSeparator);
		}
		if (fileInput.count() > 1) {
			int count = fileInput.count();
			for (int i = 0; i < count; i++) {
				Locator input = fileInput.nth(i);
				input.setInputFiles(Paths.get(relativePath + fileName));
			}
		} else {
			fileInput.setInputFiles(Paths.get(relativePath + fileName));
		}

		if (fileName.contains("/")) {
			String[] ActualFileName = fileName.split("/");
			int fileNameIndex = ActualFileName.length - 1;
			Locator uploadedFileName = page
					.locator(ADD_FILE_NAME_XPATH.replace("{fileName}", ActualFileName[fileNameIndex]));
			String uploadedFileNameValue = uploadedFileName.first().textContent();
			return uploadedFileNameValue;
		} else {
			Locator uploadedFileName = page.locator(ADD_FILE_NAME_XPATH.replace("{fileName}", fileName));
			String uploadedFileNameValue = uploadedFileName.first().textContent();
			return uploadedFileNameValue;
		}
	}

	public static void clickOnCreateCatalogButton(Page page) {
		page.getByTestId(CREATE_CATALOG_BUTTON_DATA_TESTID).isVisible();
		page.getByTestId(CREATE_CATALOG_BUTTON_DATA_TESTID).click();
	}

	public static void clickOnUploadButton(Page page, String label) {
		Locator buttonLocator = page.locator(UPLOAD_FILE_BUTTON_XPATH);
		buttonLocator.scrollIntoViewIfNeeded();
		buttonLocator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		buttonLocator.click();
		Locator loadingSpinner = page.locator("//span[@role='progressbar']").first();
		if (loadingSpinner.isVisible()) {
			loadingSpinner
					.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN).setTimeout(120000));
		}
	}
}
