package aicore.pages;

import java.util.List;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class SettingsAdminQueryPage {
	private Page page;

	private static final String DATABASE_DROPDOWN_NAME = "Database";
	private static final String DATABASE_DROPDOWN_LIST_OPTIONS_XPATH = "//ul[contains(@class,'MuiList-root MuiList-padding')]//li[text()='{databaseName}']";
	private static final String QUERY_TEXTBOX_XPATH = "//div[contains(@class,'MuiFormControl-root')]//label[text()='Enter query to run on database']";
	private static final String RUN_BUTTON_XPATH = "//button[contains(@class,'MuiButtonBase-root MuiButton-root MuiButton-contained')]//span[text()='Run']";
	private static final String DATABASE_TABLE_HEADER_XPATH = "//table[contains(@class,'MuiTable-root')]//thead//th";
	private static final String QUERY_EXECUTED_TOAST_MESSAGE = "Successfully submitted query";

	private static final String EXECUTE_QUERY_BUTTON_TEXT = "Execute query";

	public SettingsAdminQueryPage(Page page) {
		this.page = page;
	}

	public void selectDatabse(String databaseName) {
		Locator databaseDropdown = page.getByRole(AriaRole.BUTTON,
				new Page.GetByRoleOptions().setName(DATABASE_DROPDOWN_NAME));
		databaseDropdown.isVisible();
		databaseDropdown.click();
		Locator databaseDropdownListOptions = page.getByRole(AriaRole.OPTION,
				new Page.GetByRoleOptions().setName(databaseName));
		databaseDropdownListOptions.click();
	}

	public void enterQuery(String query) {
		page.locator(QUERY_TEXTBOX_XPATH).fill(query);
	}

	public void clickOnExecuteQueryButton() {
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(EXECUTE_QUERY_BUTTON_TEXT)).click();
	}

	public int getTableHeaderCount() {
		return page.locator(DATABASE_TABLE_HEADER_XPATH).count();
	}

	public List<String> getTableHeaderNames() {
		return page.locator(DATABASE_TABLE_HEADER_XPATH).allInnerTexts();
	}

	public String verifyQueryExecutedToastMessage() {
		Locator toastMessage = page.getByRole(AriaRole.ALERT)
				.filter(new Locator.FilterOptions().setHasText(QUERY_EXECUTED_TOAST_MESSAGE));
		toastMessage.isVisible();
		return toastMessage.textContent().trim();
	}
}