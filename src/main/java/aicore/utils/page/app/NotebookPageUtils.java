package aicore.utils.page.app;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

import aicore.utils.CommonUtils;

public class NotebookPageUtils {

	private static final String NOTEBOOK_OPTION_XPATH = "//div[@class='flexlayout__border_button_content' and text()='Notebooks']";
	private static final String CREATE_NEW_NOTEBOOK_DATA_TESTID = "NoteAddOutlinedIcon";
	private static final String QUERY_SUBMIT_BUTTON_XPATH = "//span[text()='Submit']";
	private static final String NOTEBOOK_QUERY_ID_LABEL = "Id";
	private static final String CODE_ENTER_TEXTAREA = ".monaco-editor textarea.inputarea";
	private static final String QUERY_CODE_RUN_OUTPUT_XPATH = "//div[contains(@id,'notebook-cell-actions')]/child::div/span[text()='{codeOutput}']";
	private static final String IMPORT_DATA_OPTIONS_XPATH = "//li[@value='{optionName}']";
	private static final String SELECT_DATABASE_DROPDOWN_XPATH = "//label[text()='Select Database']/following-sibling::div//div[@role='button']";
	private static final String SELECT_ALL_COLUMNS_XPATH = "(//tbody//tr)[1]//input[@type='checkbox']";
	private static final String LIST_OF_COLUMN_NAMES_XPATH = "//table[contains(@class, 'MuiTable-root')]//tbody//tr[position()>1]//td[2]";
	private static final String IMPORT_BUTTON_XPATH = "//span[text()='Import']";
	private static final String FRAME_CSS = "input[value*='FRAME_']";
	private static final String DELETE_CELL_DATA_TESTID = "DeleteIcon";
	private static final String OUTPUT_TABLE_HEADER_XPATH = "//table//th";
	private static final String OUTPUT_TABLE_ROW_XPATH = "//table//tbody//tr";
	private static final String JSON_BODY_FIELD_VALUE_XPATH = "//div[contains(@class,'string-value MuiBox-root')]//span[text()='{fieldValue}']";
	private static final String SELECT_TYPE_DROPDOWN_XPATH = "//div[div[text()='Python']]";
	private static final String SELECT_TYPE_LISTBOX_XPATH = "//li[text()='{type}']";
	private static final String TOTAL_COUNT_OF_ROWS_XPATH = "(//span[contains(text(),'This is a preview of ingested data')])[1]";
	private static final String DEFAULT_LANGUAGE_XPATH = "//*[@value='py']";
	private static final String OUTPUT_XPATH = "//pre[text()='{Output}']";
	private static final String PYTHON_OUTPUT_XPATH = "//div[contains(@class,'data-type-label')]/..";
	private static final String NOTEBOOK_NAME_XPATH = "//p[text()='{notebookName}']";
	private static final String QUERY_INPUT_FIELD_XPATH = "[data-mode-id='sql']>div>div>textarea";
	private static final String QUERY_OUTPUT_FIELD_XPATH = "//tr[@class=\"MuiTableRow-root css-5lw8r7-MuiTableRow-root\"]//td[text()='{Age}']";
	private static final String QUERY_OUTPUT_FIELD1_XPATH = "//tr[@class=\"MuiTableRow-root css-5lw8r7-MuiTableRow-root\"]//td[text()='{BP}']";

	public static void clickOnNotebooksOption(Page page) {
		page.locator(NOTEBOOK_OPTION_XPATH).click();
	}

	public static void clickOnCreateNewNotebook(Page page) {
		page.getByTestId(CREATE_NEW_NOTEBOOK_DATA_TESTID).click();
	}

	public static void enterQueryName(Page page, String queryName) {
		page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName(NOTEBOOK_QUERY_ID_LABEL)).fill(queryName);
	}

	public static void clickOnQuerySubmitButton(Page page) {
		page.locator(QUERY_SUBMIT_BUTTON_XPATH).click();
	}

	public static void enterCodeInQuery(Page page, String code) {
		page.locator(CODE_ENTER_TEXTAREA).fill(code);
	}

	public static void clickOnRunAllButton(Page page) {
		page.getByTestId("ArrowDownwardIcon").click();
	}

	public static String getCodeOutput(Page page, String codeOutput) {
		Locator outputResult = page.locator(QUERY_CODE_RUN_OUTPUT_XPATH.replace("{codeOutput}", codeOutput));
		outputResult.waitFor(new Locator.WaitForOptions().setTimeout(10000));
		return outputResult.textContent().trim();
	}

	public static void mouseHoverOnNotebookHiddenOptions(Page page) {
		if (!page.getByTestId("data-key-pair").isVisible()) {
			Locator hiddenOptions = page.locator(CODE_ENTER_TEXTAREA);
			CommonUtils.moveMouseToCenterWithMargin(page, hiddenOptions, 80, 10);
		} else {
			Locator dataKeyPair = page.getByTestId("data-key-pair");
			dataKeyPair.scrollIntoViewIfNeeded();
			CommonUtils.moveMouseToCenterWithMargin(page, dataKeyPair, 60, 10);
		}
	}

	public static void clickOnHiddenNotebookOption(Page page, String optionName) {
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(optionName)).click();
	}

	public static void selectHiddenOptionDropdown(Page page, String optionName) {
		page.locator(IMPORT_DATA_OPTIONS_XPATH.replace("{optionName}", optionName)).click();
	}

	public static void selectDatabaseFromDropdown(Page page, String databaseName) {
		page.locator(SELECT_DATABASE_DROPDOWN_XPATH).click();
		page.waitForTimeout(300);
		page.getByText(databaseName).click();
	}

	public static void selectAllColumns(Page page) {
		page.locator(SELECT_ALL_COLUMNS_XPATH).click();
	}

	public static void clickOnImportButton(Page page) {
		page.locator(IMPORT_BUTTON_XPATH).scrollIntoViewIfNeeded();
		page.locator(IMPORT_BUTTON_XPATH).click();
	}

	public static void deleteFirstCell(Page page) {
		page.getByTestId(DELETE_CELL_DATA_TESTID).first().click();
	}

	public static void clickOnRunCellButton(Page page) {
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Run cell")).isVisible();
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Run cell")).click();
		page.getByTestId("CheckCircleIcon")
				.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
	}

	public static String getFrameID(Page page) {
		page.locator(FRAME_CSS).isVisible();
		return page.locator(FRAME_CSS).inputValue().trim();
	}

	public static List<String> checkColumnNamesOnUI(Page page) {
		Locator columnNames = page.locator(LIST_OF_COLUMN_NAMES_XPATH);
		return columnNames.allTextContents();
	}

	public static List<String> getNotebookOutputTableHeader(Page page) {
		return page.locator(OUTPUT_TABLE_HEADER_XPATH).allTextContents();
	}

	public static int getTotalRowsFromPreviewCaption(Page page) {
		final String compilePattern = "Showing \\d+ of (\\d+)";
		Locator previewCaption = page.locator(TOTAL_COUNT_OF_ROWS_XPATH);
		previewCaption.scrollIntoViewIfNeeded();
		String captionText = previewCaption.textContent();
		Pattern pattern = Pattern.compile(compilePattern);
		Matcher matcher = pattern.matcher(captionText);
		if (matcher.find()) {
			return Integer.parseInt(matcher.group(1)); // Extracts the second number
		} else {
			throw new RuntimeException("Failed to extract total row count from caption: " + captionText);
		}
	}

	public static boolean isColumnUniqueByHeader(Page page, String headerName) {
		Locator headers = page.locator(OUTPUT_TABLE_HEADER_XPATH);
		int columnCount = headers.count();
		int targetColumnIndex = -1;
		for (int i = 0; i < columnCount; i++) {
			String text = headers.nth(i).textContent().trim();
			if (text.equalsIgnoreCase(headerName)) {
				targetColumnIndex = i;
				break;
			}
		}
		if (targetColumnIndex == -1) {
			throw new RuntimeException("Header with label '" + headerName + "' not found");
		}
		Locator rows = page.locator(OUTPUT_TABLE_ROW_XPATH);
		int rowCount = rows.count();
		Set<String> uniqueValues = new HashSet<>();
		for (int i = 0; i < rowCount; i++) {
			Locator cell = rows.nth(i).locator("td").nth(targetColumnIndex);
			String cellText = cell.textContent().trim();
			uniqueValues.add(cellText);
		}
		return uniqueValues.size() == rowCount;
	}

	public static String validateJsonFieldValue(Page page, String fieldValue) {
		Locator jsonNameLocator = page.locator(JSON_BODY_FIELD_VALUE_XPATH.replace("{fieldValue}", fieldValue));
		jsonNameLocator.isVisible();
		return jsonNameLocator.textContent();
	}

	public static void selectTypeFromDropdown(Page page, String type) {
		Locator dropdownArrow = page.locator(SELECT_TYPE_DROPDOWN_XPATH.replace("{type}", type)).first();
		dropdownArrow.isVisible();
		dropdownArrow.click();
		page.locator(SELECT_TYPE_LISTBOX_XPATH.replace("{type}", type)).click();
	}

	public static void hoverAndClickOnCell(Page page) {
		page.getByTitle("Cell", new Page.GetByTitleOptions().setExact(true)).hover();
		page.getByTitle("Cell", new Page.GetByTitleOptions().setExact(true)).click();
	}

	public static void checkPythonAsDefaultLanguage(Page page) {
		page.locator(DEFAULT_LANGUAGE_XPATH).isVisible();
		if (!page.locator(DEFAULT_LANGUAGE_XPATH).isVisible()) {
			throw new AssertionError("Python is not selected as the default language option");
		}
	}

	public static void changeToLanguage(Page page, String language) {
		page.getByTitle("Select Language").click();
		page.getByRole(AriaRole.LISTBOX).getByTitle(language).click();

	}

	public static void getPixelOutput(Page page, String output) {
		page.locator(OUTPUT_XPATH.replace("{Output}", output)).isVisible();
	}

	public static void getPythonOutput(Page page, String output) {
		String pythonOutput = page.locator(PYTHON_OUTPUT_XPATH).textContent();
		if (pythonOutput == null || !pythonOutput.contains(output)) {
			throw new AssertionError("Expected Python output: " + output + ", but got: " + pythonOutput);
		}
	}

	public static void clickOnNotebook(Page page, String notebookName) {
		page.locator(NOTEBOOK_NAME_XPATH.replace("{notebookName}", notebookName)).click();
	}

	public static void verifyNotebookIsPresentInList(Page page, String notebookName) {
		Locator notebookLocator = page.locator(NOTEBOOK_NAME_XPATH.replace("{notebookName}", notebookName));
		if (!notebookLocator.isVisible()) {
			throw new AssertionError("Notebook '" + notebookName + "' is not present in the list");
		}
	}

	public static boolean writeQuery(Page page, String query) {
		page.locator(QUERY_INPUT_FIELD_XPATH).isVisible();
		page.locator(QUERY_INPUT_FIELD_XPATH).clear();
		page.locator(QUERY_INPUT_FIELD_XPATH).fill(query);
		return true;
	}

	public static boolean validateQuery(Page page, String age, String bp) {
		page.locator(QUERY_OUTPUT_FIELD_XPATH.replace("{Age}", age)).isVisible();
		page.locator(QUERY_OUTPUT_FIELD1_XPATH.replace("{BP}", bp)).isVisible();
		return true;
	}
}
