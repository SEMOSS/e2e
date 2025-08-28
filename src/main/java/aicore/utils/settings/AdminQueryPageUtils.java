package aicore.utils.settings;

import java.util.List;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class AdminQueryPageUtils {

	///// SETTING ADMIN QUERY PAGE
	private static final String DATABASE_DROPDOWN_NAME = "Database";
	private static final String QUERY_TEXTBOX_XPATH = "//div[contains(@class,'MuiFormControl-root')]//label[text()='Enter query to run on database']";
	private static final String QUERY_EXECUTED_TOAST_MESSAGE = "Successfully submitted query";
	private static final String TOAST_MESSAGE_ClOSE_DATA_TESTID = "CloseIcon";
	private static final String EXECUTE_QUERY_BUTTON_TEXT = "Run";
	private static final String DATABASE_TABLE_HEADER_XPATH = "//table[contains(@class,'MuiTable-root')]//thead//th";
	private static final String MAX_ROWS_COUNT_TEXTBOX_LABEL = "Max # Rows to Collect";
	private static final String TOTAL_TABLE_ROWS_XPATH = "//table/tbody/tr";

	public static void clickOnSelectDatabase(Page page) {
		Locator databaseDropdown = page.locator("label:has-text('" + DATABASE_DROPDOWN_NAME + "')")
				.locator("xpath=following-sibling::div//div");
		databaseDropdown.isVisible();
		databaseDropdown.click();
	}

	public static void selectDatabase(Page page, String databaseName) {
		Locator databaseDropdownListOptions = page.getByRole(AriaRole.OPTION,
				new Page.GetByRoleOptions().setName(databaseName));
		databaseDropdownListOptions.click();
	}

	public static void enterQuery(Page page, String query) {
		page.locator(QUERY_TEXTBOX_XPATH).fill(query);
	}

	public static void clickOnExecuteQueryButton(Page page) {
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(EXECUTE_QUERY_BUTTON_TEXT)).click();
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(EXECUTE_QUERY_BUTTON_TEXT)).click();
	}

	public static int getTableHeaderCount(Page page) {
		return page.locator(DATABASE_TABLE_HEADER_XPATH).count();
	}

	public static List<String> getTableHeaderNames(Page page) {
		return page.locator(DATABASE_TABLE_HEADER_XPATH).allInnerTexts();
	}

	public static String verifyQueryExecutedToastMessage(Page page) {
		Locator toastMessage = page.getByRole(AriaRole.ALERT)
				.filter(new Locator.FilterOptions().setHasText(QUERY_EXECUTED_TOAST_MESSAGE));
		toastMessage.isVisible();
		return toastMessage.textContent().trim();
	}

	public static void closeQueryExecutedToastMessage(Page page) {
		page.getByTestId(TOAST_MESSAGE_ClOSE_DATA_TESTID).click();
	}

	public static void enterRowCount(Page page, String count) {
		Locator countTextbox = page.getByLabel(MAX_ROWS_COUNT_TEXTBOX_LABEL);
		countTextbox.fill(count);
	}

	public static int totalCountOfRows(Page page) {
		Locator tableRows = page.locator(TOTAL_TABLE_ROWS_XPATH);
		return tableRows.count();
	}
}
