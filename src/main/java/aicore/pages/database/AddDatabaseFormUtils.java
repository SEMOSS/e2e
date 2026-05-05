package aicore.pages.database;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

import aicore.utils.AICorePageUtils;

public class AddDatabaseFormUtils {
	private static final Logger logger = LogManager.getLogger(AddDatabaseFormUtils.class);

	private static final String ADD_DATABASE_BUTTON = "Navigate to import Database";
	private static final String DATABASE_TYPE_CSS_TAG = "{DATABASE_TYPE_TAG}";
	private static final String DATABASE_CONNECTION_XPATH = "database-card-" + DATABASE_TYPE_CSS_TAG;
	private static final String CATALOG_NAME_XPATH = "//input[@data-testid='database-form-input-NAME']";
	private static final String HOST_NAME_XPATH = "//input[@data-testid='database-form-input-hostname']";
	private static final String PORT_NUMBER_XPATH = "//input[@data-testid='database-form-input-port']";
	private static final String SCHEMA_NAME_XPATH = "//input[@data-testid='database-form-input-schema']";
	private static final String JDBC_URL_XPATH = "//input[@data-testid='database-form-input-CONNECTION_URL']";
	private static final String USER_NAME_XPATH = "//input[@data-testid='database-form-input-USERNAME']";
	private static final String CONNECT_BUTTON_DATA_TESTID = "database-form-connect-button";

	private static final String MANDATORY_FIELD_XPATH = "//div//label[text()='{fieldName}']//span";

	public static void clickAddDatabaseButton(Page page) {
		logger.info("CLICK ON ADD DATABASE BUTTON");
		page.getByLabel(ADD_DATABASE_BUTTON).isVisible();
		page.getByLabel(ADD_DATABASE_BUTTON).click();
	}

	public static void selectDatabaseFromConnectionTypes(Page page, String dbType) {
		logger.info("SELECT " + dbType + " AS DATABASE TYPE");
		Locator option = page.getByTestId(DATABASE_CONNECTION_XPATH.replace(DATABASE_TYPE_CSS_TAG, dbType));
		if (!option.isVisible()) {
			throw new AssertionError("Database connection type '" + dbType + "' is not visible.");
		}
		option.click();
	}

	public static void enterCatalogName(Page page, String catalogName) {
		logger.info("ENTER CATALOG NAME: " + catalogName);
		Locator catalogNameInput = page.locator(CATALOG_NAME_XPATH);
		if (!catalogNameInput.isVisible() && !catalogNameInput.isEnabled()) {
			throw new AssertionError("Catalog name input field is not visible.");
		}
		catalogNameInput.fill(catalogName);
	}

	public static void enterHostName(Page page, String hostName) {
		logger.info("ENTER HOSTNAME: " + hostName);
		Locator hostNameInput = page.locator(HOST_NAME_XPATH);
		hostNameInput.scrollIntoViewIfNeeded();
		hostNameInput.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		if (!hostNameInput.isVisible() || !hostNameInput.isEnabled()) {
			throw new AssertionError("Host name input field is not visible or enabled.");
		}
		hostNameInput.fill(hostName);
	}

	public static void clearPortNumber(Page page) {
		logger.info("CLEAR PORT NUMBER");
		Locator portNumberInput = page.locator(PORT_NUMBER_XPATH);
		portNumberInput.scrollIntoViewIfNeeded();
		portNumberInput.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		if (!portNumberInput.isVisible() || !portNumberInput.isEnabled()) {
			throw new AssertionError("Port number input field is not visible or enabled.");
		}
		portNumberInput.fill("");
	}

	public static void enterSchemaName(Page page, String schemaName) {
		logger.info("ENTER SCHEMA NAME: " + schemaName);
		Locator schemaNameInput = page.locator(SCHEMA_NAME_XPATH);
		schemaNameInput.scrollIntoViewIfNeeded();
		schemaNameInput.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		if (!schemaNameInput.isVisible() || !schemaNameInput.isEnabled()) {
			throw new AssertionError("Schema name input field is not visible or enabled.");
		}
		schemaNameInput.fill(schemaName);
	}
	
	public static String getJDBCUrl(String dbType, String jdbcUrl) {
		String jdbcUrlPrefix = "jdbc:" + dbType + ":";
		String workspaceRoot = System.getProperty("user.dir");
		Path dbPath = Paths.get(workspaceRoot, "src", "test", "resources", "data", "Database", jdbcUrl);
		String dbAbsolutePath = dbPath.toAbsolutePath().toString().replace("\\", "/");
		String jdbcUrlInput = jdbcUrlPrefix + dbAbsolutePath;
		return jdbcUrlInput;
	}

	public static void enterJDBCUrl(Page page, String jdbcUrl) {
		logger.info("ENTER JDBC URL: " + jdbcUrl);
		Locator jdbcUrlLocator = page.locator(JDBC_URL_XPATH);
		if (!jdbcUrlLocator.isVisible() || !jdbcUrlLocator.isEnabled()) {
			throw new AssertionError("JDBC URL input field is not visible or enabled.");
		}
		jdbcUrlLocator.fill(jdbcUrl);
	}

	public static void enterUserName(Page page, String userName) {
		logger.info("ENTER USERNAMEL: " + userName);
		Locator userNameInput = page.locator(USER_NAME_XPATH);
		if (!userNameInput.isVisible() && !userNameInput.isEnabled()) {
			throw new AssertionError("User name input field is not visible.");
		}
		userNameInput.fill(userName);
	}

	public static void clickOnConnectButton(Page page) {
		logger.info("CLICK ON CONNECT BUTTON");
		Locator connectButton = page.getByTestId(CONNECT_BUTTON_DATA_TESTID);
		AICorePageUtils.waitFor(connectButton);
		connectButton.click();
	}

	public static boolean isDBFieldMandatory(Page page, String fieldName) {
		Locator mandatoryField = page.locator(MANDATORY_FIELD_XPATH.replace("{fieldName}", fieldName));
		if (!mandatoryField.textContent().contains("*")) {
			throw new AssertionError(
					"Database connection type '" + fieldName + "' is not showing with * symbol of required field.");
		}
		return mandatoryField.isVisible();
	}

}
