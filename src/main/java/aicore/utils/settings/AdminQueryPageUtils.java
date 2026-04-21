package aicore.utils.settings;

import java.util.List;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class AdminQueryPageUtils {

	///// SETTING ADMIN QUERY PAGE
	private static final String DATABASE_DROPDOWN_XPATH = "//label[text()='Database']/parent::div//button";
	private static final String QUERY_TEXTBOX_PLACEHOLDER_TEXT = "SELECT * FROM engine";
	private static final String QUERY_EXECUTED_TOAST_MESSAGE = "Successfully submitted query";
	private static final String TOAST_MESSAGE_ClOSE_DATA_XPATH = "//button[@aria-label='Close']";
	private static final String EXECUTE_QUERY_BUTTON_TEXT = "Run";
	private static final String DATABASE_TABLE_HEADER_XPATH = "//table[contains(@class,'MuiTable-root')]//thead//th";
	private static final String TOTAL_TABLE_ROWS_XPATH = "//table/tbody/tr";
	private static final String MAX_ROW_COUNT_XPATH = "//input[@type='number']";

	public static void clickOnSelectDatabase(Page page) {
		Locator databaseDropdown = page.locator(DATABASE_DROPDOWN_XPATH);
		databaseDropdown.isVisible();
		databaseDropdown.click();
	}

	public static void selectDatabase(Page page, String databaseName) {
		Locator databaseDropdownListOptions = page.getByRole(AriaRole.OPTION,
				new Page.GetByRoleOptions().setName(databaseName));
		databaseDropdownListOptions.click();
	}

	public static void enterQuery(Page page, String query) {
		// page.locator(QUERY_TEXTBOX_PLACEHOLDER_TEXT).fill(query);
		Locator textbox = page.getByPlaceholder(QUERY_TEXTBOX_PLACEHOLDER_TEXT);
		textbox.fill(query);

	}

	public static void clickOnExecuteQueryButton(Page page) {
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(EXECUTE_QUERY_BUTTON_TEXT)).click();
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(EXECUTE_QUERY_BUTTON_TEXT)).click();
	}

	public static int getTableHeaderCount(Page page) {
		//TODO page is missing data-testid
		Locator headers = page.locator("thead th");
		return headers.count();
	}

	public static List<String> getTableHeaderNames(Page page) {
		//TODO page is missing data-testid
		Locator headers = page.locator("thead th");
		return headers.allInnerTexts();
	}

	public static String verifyQueryExecutedToastMessage(Page page) {
		Locator toastMessage = page.getByRole(AriaRole.ALERT)
				.filter(new Locator.FilterOptions().setHasText(QUERY_EXECUTED_TOAST_MESSAGE));
		toastMessage.isVisible();
		return toastMessage.textContent().trim();
	}

	public static void closeQueryExecutedToastMessage(Page page) {
		page.locator(TOAST_MESSAGE_ClOSE_DATA_XPATH).click();
	}

	public static void enterRowCount(Page page, String count) {
		Locator countTextbox = page.locator(MAX_ROW_COUNT_XPATH);
		countTextbox.fill(count);
	}

	public static int totalCountOfRows(Page page) {
		Locator tableRows = page.locator(TOTAL_TABLE_ROWS_XPATH);
		return tableRows.count();
	}
}
