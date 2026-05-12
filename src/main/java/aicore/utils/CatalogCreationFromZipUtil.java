package aicore.utils;

import java.nio.file.FileSystems;
import java.nio.file.Paths;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

import aicore.pages.base.AbstractBasePage;
import aicore.pages.home.MainMenuUtils;
import io.qameta.allure.Step;

public class CatalogCreationFromZipUtil extends AbstractBasePage {
	private static final String CATALOG_MENU_BUTTON_DATA_TESTID = "sidebar-{catalogName}-btn";
	private static final String ADD_CATALOG_BUTTON_DATA_TESTID = "engineIndex-add-{catalog}-btn";
	private static final String ADD_FILE_XPATH = "//input[@type='file']";
	private static final String ADD_FILE_NAME_XPATH = "//*[normalize-space()='{fileName}']";
	private static final String UPLOAD_FILE_BUTTON_XPATH = "//button[contains(@data-testid,'upload-submit-button')]";
	private static final String ZIP_UPLOAD_ICON_XPATH = "//button[contains(@data-testid,'-upload-file-button')]";

	public static void openCatalog(Page page, String catalogName) {
		Locator locator = page.getByTestId(CATALOG_MENU_BUTTON_DATA_TESTID.replace("{catalogName}", catalogName));
		MainMenuUtils.clickOnLocatorAndCLoseMainMenu(page, locator);
	}

	public static void clickOnAddCatalogButton(Page page, String catalogName) {
		Locator locator = page.getByTestId(ADD_CATALOG_BUTTON_DATA_TESTID.replace("{catalog}", catalogName));
		locator.click();
	}

	public static void selectAddCatalogOption(Page page, String option) {
		page.locator(ZIP_UPLOAD_ICON_XPATH).isVisible();
		page.locator(ZIP_UPLOAD_ICON_XPATH).click();
	}

	public static void clickOnFileUploadIcon(Page page) {
		Locator icon = page.locator("//button[contains(@data-testid,'upload-file-button')]");
		AICorePageUtils.waitFor(icon);
		icon.click();
	}

	@Step("Upload File: {fileName}")
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
		page.locator(UPLOAD_FILE_BUTTON_XPATH).isVisible();
		page.locator(UPLOAD_FILE_BUTTON_XPATH).click();
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

	// alternative to CodeAppPAgeUtils.userCanSeeFolder
	public static boolean userSeesItemInFilesList(Page page, String itemIdentifier) {
		Locator listItem = page.getByTitle(itemIdentifier);
		return listItem.isVisible();
	}

	// alternative to CodeAppPAgeUtils.userSelectTheFolder
	public static void userClicksOnItemInFilesList(Page page, String itemIdentifier) {
		Locator listItem = page.getByTitle(itemIdentifier);
		waitAndClick(listItem);
	}

	public static void clickOnFileUploadIconForAPP(Page page) {
		page.getByTestId("createAppSection-upload-btn").click();
		Locator selectFile = page
				.locator("//button[@type=\"button\"]//*[name()='svg'][contains(@class,'lucide-upload')]");
		AICorePageUtils.waitFor(selectFile);
		selectFile.click();
	}
}