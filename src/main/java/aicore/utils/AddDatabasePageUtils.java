package aicore.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Download;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

public class AddDatabasePageUtils {
	private static final Logger logger = LogManager.getLogger(AddDatabasePageUtils.class);
	private static final String ADD_DATABASE_BUTTON = "Navigate to import Database";
	private static final String NEXT_BUTTON_FOR_CREATE_DATABASE_XPATH = "//button[@data-testid='database-form-submit']";
	private static final String METADATA_TABLE_XPATH = "//div[contains(@class,'react-flow__node-metamodel')]";
	private static final String VERTICAL_OPTIONS_XPATH = "//button[contains(@title, '{catalogName}')]/following-sibling::button/*[name()='svg']";
	private static final String COPY_ID_OPTION_TEXT = "copy";
	private static final String SELECT_FILTER_VALUE_XPATH = "//h6[text()='{filterCategory}']/ancestor::li/following-sibling::div//p[text()='{filterValue}']";
	private static final String BOOKMARK_ICON_XPATH = "//button[contains(@title, '{catalogName}')]/*[name()='svg']";
	private static final String UNBOOKMARK_ICON_DATA_TEST_ID = "BookmarkIcon";
	private static final String CATALOG_UNDER_BOOKMARKED_SECTION_XPATH = "//h6[text()='Bookmarked']/following-sibling::div[1]//p[text()='{catalogName}']";
	private static final String EDIT_BTN_XPATH = "EditRoundedIcon";
	private static final String TAGS_XPATH = "//span[text()='Tag']/ancestor::fieldset/parent::div//input";
	private static final String SUBMIT_BTN_XPATH = "//span[text()='Submit']";
	private static final String EMBEDDED_TOAST_MESSAGE_XPATH = "//div[text()='{ToastMessage}']";
	private static final String EXPORT_BTN_XPATH = "Export";
	private static final String EDIT_POPUP_XPATH = "//div[contains(text(),\"Edit\")]";
	private static final String DATABASE_CATALOG_SEARCH_TEXTBOX_XPATH = "//label//following-sibling::div//input";
	private static final String SEARCHED_DATABASE_XPATH = "//p[text()='{catalogName}']";
	private static final String DATABASE_ID_XPATH = "//button[@aria-label=\"copy Database ID\"]/parent::span";
	private static final String DATABASE_DESCRIPTION_XPATH = "//h6[text()='{DatabaseDescription}']";
	private static final String DATABASE_NAME_XPATH = "//p[text()='{DatabaseName}']";
	private static final String HOST_NAME_XPATH = "//div[@data-testid='database-form-input-hostname']//div//input";
	private static final String CATALOG_NAME_XPATH = "//div[@data-testid='database-form-input-NAME']//div//input";
	private static final String PORT_NUMBER_XPATH = "//div[@data-testid='database-form-input-port']//div//input";
	private static final String SCHEMA_NAME_XPATH = "//div[@data-testid='database-form-input-schema']//div//input";
	private static final String JDBC_URL_XPATH = "//div[@data-testid='database-form-input-CONNECTION_URL']//div//input";
	private static final String USER_NAME_XPATH = "//div[@data-testid='database-form-input-USERNAME']//div//input";
	private static final String APPLY_BUTTON_XPATH = "model-upload-submit-button";
	private static final String APPLY_DATABASE_BUTTON_XPATH = "//span[text()='Apply']";
	private static final String IMPORT_DATABASE_BUTTON_XPATH = "//span[text()='Import']";
	private static final String DB_CATALOG_XPATH = "//p[text()='{dbName}']";
	private static final String DATABASE_CONNECTION_XPATH = "[data-testid='database-card-undefined']";
	private static final String COPY_ID_XPATH = "//span[text()='{message}']";
	private static final String SELECT_ALL_DATABASE_XPATH = "//span[text()='(Select searched items)']";
	private static final String MANDATORY_FIELD_XPATH = "//div//label[text()='{fieldName}']//span";
	private static final String FORM_SECTION_XPATH = "//h6[text()='{sectionName}']";
	private static final String ADVANCED_SECTION_XPATH = "(//button[@data-testid='database-form-advanced-toggle']//*)[1]";
	private static final String SECTION_FIELD_XPATH = "../following-sibling::div//label[text()='{fieldName}']";
	private static final String QUERY_TAB_DATA_TESTID = "engineLayout-Query-tab";
	private static final String QUERY_ENTER_TEXTAREA_XPATH = ".monaco-editor .native-edit-context";
	private static final String OUTPUT_TABLE = "//table";

	public static void clickAddDatabaseButton(Page page) {
		page.getByLabel(ADD_DATABASE_BUTTON).isVisible();
		page.getByLabel(ADD_DATABASE_BUTTON).click();
	}

	public static void selectDatabaseType(Page page, String dbType) {
		page.getByText(dbType).isVisible();
		page.getByText(dbType).click();
	}

	public static void selectDatabaseFromConnectionTypes(Page page, String dbType) {
		Locator option = page.locator(DATABASE_CONNECTION_XPATH).filter(new Locator.FilterOptions().setHasText(dbType));
		if (!option.isVisible()) {
			throw new AssertionError("Database connection type '" + dbType + "' is not visible.");
		}
		option.click();
	}

	public static boolean isDBFieldMandatory(Page page, String fieldName) {
		Locator mandatoryField = page.locator(MANDATORY_FIELD_XPATH.replace("{fieldName}", fieldName));
		if (!mandatoryField.textContent().contains("*")) {
			throw new AssertionError(
					"Database connection type '" + fieldName + "' is not showing with * symbol of required field.");
		}
		return mandatoryField.isVisible();
	}

	public static boolean verifyFieldUnderSection(Page page, String sectionName, String fieldName) {
		Locator sectionLocator = page.locator(FORM_SECTION_XPATH.replace("{sectionName}", sectionName));
		if (sectionName.toLowerCase().equals("advanced settings")) {
			if (fieldName.toLowerCase().equals("not available")) {
				return true;
			}
			Locator dropdownLocator = page.locator(ADVANCED_SECTION_XPATH);
			AICorePageUtils.waitFor(dropdownLocator);
			String attributeVal = dropdownLocator.getAttribute("data-testid");
			dropdownLocator.scrollIntoViewIfNeeded();
			if (!sectionLocator.isVisible()) {
				throw new AssertionError("Advanced Settings dropdown is not visible.");
			}
			if (attributeVal.equals("ExpandMoreIcon")) {

				dropdownLocator.click();
			}

		}

		if (!sectionLocator.isVisible()) {
			throw new AssertionError("Section '" + sectionName + "' is not visible.");
		}
		Locator fieldLocator = sectionLocator.locator(SECTION_FIELD_XPATH.replace("{fieldName}", fieldName));
		return fieldLocator.isVisible();

	}

	public static void clickOnApplyButton(Page page) {
		Locator applyButton = page.getByTestId(APPLY_BUTTON_XPATH);
		applyButton.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		if (!applyButton.isVisible() || !applyButton.isEnabled()) {
			throw new AssertionError("Apply button is not visible or enabled.");
		}
		applyButton.click();
	}

	public static void clickApplyDatabaseButton(Page page) {
		Locator applyDatabaseButton = page.locator(APPLY_DATABASE_BUTTON_XPATH);
		applyDatabaseButton.scrollIntoViewIfNeeded();
		applyDatabaseButton.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		if (!applyDatabaseButton.isVisible() || !applyDatabaseButton.isEnabled()) {
			throw new AssertionError("Apply button is not visible or enabled.");
		}
		applyDatabaseButton.click();
	}

	public static void clickOnImportDatabaseButton(Page page) {
		Locator importDatabaseButton = page.locator(IMPORT_DATABASE_BUTTON_XPATH);
		importDatabaseButton.scrollIntoViewIfNeeded();
		importDatabaseButton.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		if (!importDatabaseButton.isVisible() || !importDatabaseButton.isEnabled()) {
			throw new AssertionError("Import database button is not visible or enabled.");
		}
		importDatabaseButton.click();
	}

	public static void enterHostName(Page page, String hostName) {
		Locator hostNameInput = page.locator(HOST_NAME_XPATH);
		hostNameInput.scrollIntoViewIfNeeded();
		hostNameInput.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		if (!hostNameInput.isVisible() || !hostNameInput.isEnabled()) {
			throw new AssertionError("Host name input field is not visible or enabled.");
		}
		hostNameInput.fill(hostName);
	}

	public static void clearPortNumber(Page page) {
		Locator portNumberInput = page.locator(PORT_NUMBER_XPATH);
		portNumberInput.scrollIntoViewIfNeeded();
		portNumberInput.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		if (!portNumberInput.isVisible() || !portNumberInput.isEnabled()) {
			throw new AssertionError("Port number input field is not visible or enabled.");
		}
		portNumberInput.fill("");
	}

	public static void enterSchemaName(Page page, String schemaName) {
		Locator schemaNameInput = page.locator(SCHEMA_NAME_XPATH);
		schemaNameInput.scrollIntoViewIfNeeded();
		schemaNameInput.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		if (!schemaNameInput.isVisible() || !schemaNameInput.isEnabled()) {
			throw new AssertionError("Schema name input field is not visible or enabled.");
		}
		schemaNameInput.fill(schemaName);
	}

	public static void enterJDBCUrl(Page page, String jdbcUrl, String dbType) {
		Locator jdbcUrlInput = page.locator(JDBC_URL_XPATH);
		String jdbcUrlPrefix = "jdbc:" + dbType + ":";
		String workspaceRoot = System.getProperty("user.dir");
		Path dbPath = Paths.get(workspaceRoot, "src", "test", "resources", "data", "Database", jdbcUrl);
		String dbAbsolutePath = dbPath.toAbsolutePath().toString().replace("\\", "/");

		if (!jdbcUrlInput.isVisible() || !jdbcUrlInput.isEnabled()) {
			throw new AssertionError("JDBC URL input field is not visible or enabled.");
		}
		jdbcUrlInput.fill(jdbcUrlPrefix + dbAbsolutePath);
	}

	public static void enterUserName(Page page, String userName) {
		Locator userNameInput = page.locator(USER_NAME_XPATH);
		if (!userNameInput.isVisible() && !userNameInput.isEnabled()) {
			throw new AssertionError("User name input field is not visible.");
		}
		userNameInput.fill(userName);
	}

	public static void enterCatalogName(Page page, String catalogName) {
		Locator catalogNameInput = page.locator(CATALOG_NAME_XPATH);
		if (!catalogNameInput.isVisible() && !catalogNameInput.isEnabled()) {
			throw new AssertionError("Catalog name input field is not visible.");
		}
		catalogNameInput.fill(catalogName);
	}

	public static boolean verifyDatabaseTitle(Page page, String dbName) {
		Locator actualDatabaseTitle = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName(dbName));
		actualDatabaseTitle.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		return actualDatabaseTitle.isVisible();
	}

	public static void clickOnNextButtonForCreateDatabase(Page page) {
		Locator nextButtonForCreateDatabase = page.locator(NEXT_BUTTON_FOR_CREATE_DATABASE_XPATH);
		if (!nextButtonForCreateDatabase.isVisible() && !nextButtonForCreateDatabase.isEnabled()) {
			throw new AssertionError("Next button on Create database is not visible/Active.");
		}
		nextButtonForCreateDatabase.click();
	}

	public static String verifyDatabaseNameInCatalog(Page page, String dbName) {
		Locator databaseName = page.locator(DB_CATALOG_XPATH.replace("{dbName}", dbName));
		AICorePageUtils.waitFor(databaseName);
		// databaseName.waitFor(new
		// Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		if (!databaseName.isVisible()) {
			throw new AssertionError("Database name '" + dbName + "' is not visible in the catalog.");
		}
		return databaseName.textContent();

	}

	public static void clickOnDatabaseNameInCatalog(Page page, String dbName) {
		page.getByText(dbName).click();
	}

	public static void clickOnMetadataTab(Page page) {
		CatalogPageUtils.clickOnMetadataTab(page);
	}

	public static void clickOnUsageTab(Page page) {
		AICorePageUtils.clickOnTabButton(page, "Usage");
	}

	public static void verifyMetaData(Page page) {
		Locator metadataTable = page.locator(METADATA_TABLE_XPATH);
		AICorePageUtils.waitFor(metadataTable);
		int tableCount = metadataTable.count();
		if (tableCount > 0) {
			for (int i = 0; i < tableCount; i++) {
				String metaDataContent = metadataTable.nth(i).textContent();
				if (!metadataTable.nth(i).isVisible() || metaDataContent == null || metaDataContent.length() <= 1) {
					throw new AssertionError("Metadata table is not present or does not contain valid content.");
				}
			}
		} else {
			throw new AssertionError("Metadata table is not present.");
		}
	}

	public static boolean verifyDatabaseIsVisibleInCatalog(Page page, String dbName) {
		boolean isDatabaseVisible = page.getByText(dbName).isVisible();
		return isDatabaseVisible;
	}

	public static void clickOnCopyID(Page page, String catalogName) {
		page.waitForTimeout(300);
		page.locator(VERTICAL_OPTIONS_XPATH.replace("{catalogName}", catalogName)).click();
		page.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName(COPY_ID_OPTION_TEXT)).click();
	}

	public static String verifyCopyIdSuccessToastMessage(Page page, String toastMessage) {
		Locator toastAlert = page.locator(COPY_ID_XPATH.replace("{message}", toastMessage));
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

	public static void verifyDatabaseName(Page page, String databaseName) {
		page.locator(DATABASE_NAME_XPATH.replace("{DbName}", databaseName)).isVisible();
	}

	public static void verifyDatabaseID(Page page) {
		page.getByText(DATABASE_ID_XPATH).isVisible();
	}

	public static void verifyDatabaseDescription(Page page, String databaseDescription) {
		page.getByText(DATABASE_DESCRIPTION_XPATH.replace("{DatabaseDescription}", databaseDescription)).isVisible();
	}

	public static Path clickOnExportButton(Page page) throws IOException {
		page.getByText(EXPORT_BTN_XPATH).isVisible();
		Download download = page.waitForDownload(() -> {
			page.getByText(EXPORT_BTN_XPATH).click();
		});
		Path downloadPath = download.path();
		if (isZipFile(downloadPath)) {
			logger.info("The downloaded file is a ZIP.");
		} else {
			logger.warn("The downloaded file is NOT a ZIP.");
		}
		return downloadPath;
	}

	public static boolean isZipFile(Path file) throws IOException {
		try (InputStream is = Files.newInputStream(file)) {
			byte[] signature = new byte[4];
			is.read(signature);
			return (signature[0] == 0x50 && signature[1] == 0x4B
					&& (signature[2] == 0x03 || signature[2] == 0x05 || signature[2] == 0x07)
					&& (signature[3] == 0x04 || signature[3] == 0x06 || signature[3] == 0x08));
		}
	}

	public static void clickOnEditButton(Page page) {
		page.getByTestId(EDIT_BTN_XPATH).isVisible();
		page.getByTestId(EDIT_BTN_XPATH).click();
		page.locator(EDIT_POPUP_XPATH).isVisible();

	}

	public static void searchDatabaseCatalog(Page page, String catalogName) {
//		page.waitForSelector(DATABASE_CATALOG_SEARCH_TEXTBOX_XPATH);
//		page.locator(DATABASE_CATALOG_SEARCH_TEXTBOX_XPATH).click();
//		page.locator(DATABASE_CATALOG_SEARCH_TEXTBOX_XPATH).fill(catalogName);
		Locator searchcatalog = page.locator(DATABASE_CATALOG_SEARCH_TEXTBOX_XPATH);
		AICorePageUtils.waitFor(searchcatalog);
		searchcatalog.click();
		searchcatalog.fill(catalogName);

	}

	public static void selectDatabaseFromSearchOptions(Page page, String catalogName) {
		page.locator((SEARCHED_DATABASE_XPATH.replace("{catalogName}", catalogName))).isVisible();
		page.locator(SEARCHED_DATABASE_XPATH.replace("{catalogName}", catalogName)).click();
	}

	public static void clickDatabase(Page page, String databaseName) {
		Locator locator = page.locator(DATABASE_NAME_XPATH.replace("{DatabaseName}", databaseName));
		locator.isVisible();
		locator.click();
	}

	public static void clickOnRefreshButton(Page page) {
		page.getByText("Refresh Data").isVisible();
		page.getByText("Refresh Data").click();
	}

	public static void selectDatabaseFromDropdown(Page page, String dbName) {
		page.locator(SELECT_ALL_DATABASE_XPATH).isVisible();
		page.locator(SELECT_ALL_DATABASE_XPATH).click();
	}

	public static void clickOnQueryTab(Page page) {
		page.getByTestId(QUERY_TAB_DATA_TESTID).isVisible();
		page.getByTestId(QUERY_TAB_DATA_TESTID).click();
	}

	public static void enterQuery(Page page, String query) {
		Locator cell = page.locator(QUERY_ENTER_TEXTAREA_XPATH).first();
		cell.scrollIntoViewIfNeeded();
		cell.click(new Locator.ClickOptions().setForce(true));
		cell.pressSequentially(query);
	}

	public static List<String> getQueryResponseTableHeader(Page page) {
		return page.locator(OUTPUT_TABLE).last().locator("th").allTextContents();
	}

	public static void verifyQueryFieldIsEmpty(Page page) {
		Locator cell = page.locator(QUERY_ENTER_TEXTAREA_XPATH).first();
		String queryText = cell.textContent();
		if (queryText != null && !queryText.trim().isEmpty()) {
			throw new AssertionError("Query field is not empty.");
		}
	}
}