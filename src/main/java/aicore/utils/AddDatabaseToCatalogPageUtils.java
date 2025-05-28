package aicore.utils;

import java.nio.file.FileSystems;
import java.nio.file.Paths;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

public class AddDatabaseToCatalogPageUtils {

	private static final String ADD_DATABASE_BUTTON = "Navigate to import Database";
	private static final String ADD_FILE_XPATH = "//input[@type='file']";
	private static final String ADD_FILE_NAME_XPATH = "//span[@title='{fileName}']";
	private static final String CREATE_DATABASE_BUTTON = "Create database";
	private static final String VERTICAL_OPTIONS_DATA_TEST_ID = "MoreVertIcon";
	private static final String COPY_ID_OPTION_TEXT = "Copy";
	private static final String SELECT_FILTER_VALUE_XPATH = "//h6[text()='{filterCategory}']/ancestor::li/following-sibling::div//p[text()='{filterValue}']";
	private static final String BOOKMARK_ICON_DATA_TEST_ID = "BookmarkBorderIcon";
	private static final String UNBOOKMARK_ICON_DATA_TEST_ID = "BookmarkIcon";
	private static final String CATALOG_UNDER_BOOKMARKED_SECTION_XPATH = "//h6[text()='Bookmarked']/following-sibling::div[1]//p[text()='{catalogName}']";

	public static void clickAddDatabaseButton(Page page) {
		page.getByLabel(ADD_DATABASE_BUTTON).isVisible();
		page.getByLabel(ADD_DATABASE_BUTTON).click();
	}

	public static void selectDatabaseType(Page page, String dbType) {
		page.getByText(dbType).isVisible();
		page.getByText(dbType).click();
	}

	public static String uploadDatabaseFile(Page page, String fileName) {
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

	public static void clickCreateDatabaseButton(Page page) {
		page.getByText(CREATE_DATABASE_BUTTON).isVisible();
		page.getByText(CREATE_DATABASE_BUTTON).click();
	}

	public static String verifyDatabaseNameInCatalog(Page page, String dbName) {
		page.getByText(dbName).isVisible();
		String databaseNameInCatalog = page.getByText(dbName).textContent();
		return databaseNameInCatalog;
	}

	public static boolean verifyDatabaseIsVisbileInCatalog(Page page, String dbName) {
		boolean isFunctionVisible = page.getByText(dbName).isVisible();
		return isFunctionVisible;
	}

	public static void clickOnDatabaseNameInCatalog(Page page, String dbName) {
		page.getByText(dbName).click();
	}

	public static void clickOnCopyID(Page page) {
		page.getByTestId(VERTICAL_OPTIONS_DATA_TEST_ID).click();
		page.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName(COPY_ID_OPTION_TEXT)).click();
	}

	public static String verifyCopyIdSuccessToastMessage(Page page, String toastMessage) {
		Locator toastAlert = page.getByRole(AriaRole.ALERT)
				.filter(new Locator.FilterOptions().setHasText(toastMessage));
		toastAlert.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		return toastAlert.textContent().trim();
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

	public static void clickOnBookmark(Page page) {
		page.getByTestId(BOOKMARK_ICON_DATA_TEST_ID).isVisible();
		page.getByTestId(BOOKMARK_ICON_DATA_TEST_ID).click();
	}

	public static void clickOnUnbookmark(Page page) {
		page.getByTestId(UNBOOKMARK_ICON_DATA_TEST_ID).first().isVisible();
		page.getByTestId(UNBOOKMARK_ICON_DATA_TEST_ID).first().click();
	}

	public static boolean verifyCatalogDisplayedUnderBookmarkedSection(Page page, String catalogName) {
		return page.locator(CATALOG_UNDER_BOOKMARKED_SECTION_XPATH.replace("{catalogName}", catalogName)).isVisible();
	}

}