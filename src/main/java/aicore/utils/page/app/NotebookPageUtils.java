package aicore.utils.page.app;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

import aicore.utils.AICorePageUtils;
import aicore.utils.CommonUtils;

public class NotebookPageUtils {

	private static final String NOTEBOOK_OPTION_XPATH = "//div[@class='flexlayout__border_button_content' and text()='Notebooks']";
	private static final String CREATE_NEW_NOTEBOOK_DATA_TESTID = "NoteAddOutlinedIcon";
	private static final String QUERY_SUBMIT_BUTTON_XPATH = "//span[text()='Submit']";
	private static final String NOTEBOOK_QUERY_ID_LABEL = "Id";
	private static final String CODE_ENTER_TEXTAREA = ".monaco-editor textarea.inputarea";
	private static final String QUERY_CODE_RUN_OUTPUT_XPATH = "//div[contains(@id,'notebook-cell-actions')]/child::div/span[text()='{codeOutput}']";
	private static final String IMPORT_DATA_OPTIONS_XPATH = "//li[@value='{optionName}']";
	private static final String SELECT_DATABASE_DROPDOWN_XPATH = "//label[text()='Select Database']/following-sibling::div//div[@role='combobox']";
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
	private static final String CHECK_DEFAULT_OPERATOR_XPATH = "(//div[text()='{operator}'])";
	private static final String CHANGE_DEFAULT_OPERATOR_XPATH = "(//li[text()='{operator}'])";
	private static final String COLOUMN_SELECTOR_XPATH = "(//div[@title='Select Header'])";
	private static final String COLOUMN_INPUT_SELECTOR_XPATH = "(//div[@title='Select Header']//input)";
	private static final String COLOUMN_OPTION_SELECTOR_XPATH = "(//li[@data-value='{columnName}'])";
	private static final String OPERATOR_SELECTOR_XPATH = "(//div[@title='select operator'])";
	private static final String DATA_SELECTOR_XPATH = "(//label[text()='Select Data']/..)";
	private static final String DATA_LIST_ITEM_SELECTOR_XPATH = "(//li[text()='{value}'])";
	private static final String DATA_SPAN_SELECTOR_XPATH = "(//label[text()='Select Data']/..//div//div[@role='button']//span)";
	private static final String RULE_BUTTON_XPATH = "//span[contains(normalize-space(),'{buttonName}')]";
	private static final String FILTER_SELECT_FRAME_BLOCK_XPATH = "//input[@placeholder='Select Frame']";

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
		Locator deleteIcon = page.getByTestId(DELETE_CELL_DATA_TESTID).first();
		AICorePageUtils.waitFor(deleteIcon);
		deleteIcon.click();
	}

	public static void selectDatabaseType(Page page, String databaseName) {
		page.getByTitle("Select Database").click();
		page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(databaseName)).click();
	}

	public static void clickOnRunCellButton(Page page) {
		Locator block = page.locator(FILTER_SELECT_FRAME_BLOCK_XPATH);
		if (block.isVisible()) {
			block.hover();
		}
		Locator runCellButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Run cell"));
		runCellButton.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		runCellButton.click();
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
		page.locator(QUERY_INPUT_FIELD_XPATH)
				.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		page.locator(QUERY_INPUT_FIELD_XPATH).clear();
		page.locator(QUERY_INPUT_FIELD_XPATH).fill(query);
		return true;
	}

	public static boolean validateQuery(Page page, String age, String bp) {
		page.locator(QUERY_OUTPUT_FIELD_XPATH.replace("{Age}", age)).isVisible();
		page.locator(QUERY_OUTPUT_FIELD1_XPATH.replace("{BP}", bp)).isVisible();
		return true;
	}

	public static boolean isFilteredDataCorrect(Page page, String columnName, String expectedValue) {
		Locator headerRow = page.locator("table tr").first();
		List<String> headers = headerRow.locator("th,td").allInnerTexts();
		int colIndex = -1;
		for (int i = 0; i < headers.size(); i++) {
			if (headers.get(i).trim().equalsIgnoreCase(columnName)) {
				colIndex = i;
				break;
			}
		}
		if (colIndex == -1) {
			throw new AssertionError(columnName + " column not found");
		}

		List<Locator> dataRows = page.locator("table tr").all().subList(1, page.locator("table tr").count());

		for (Locator row : dataRows) {
			List<String> cells = row.locator("td").allInnerTexts();
			if (cells.size() > colIndex) {
				String cellValue = cells.get(colIndex).trim();
				if (!cellValue.equals(expectedValue)) {
					return false;
				}
			}
		}
		return true;
	}

	public static void enterValueInInput(Page page, String value) {
		Locator dataValue = page.locator(DATA_SELECTOR_XPATH);
		int count = dataValue.count();
		int lastVisibleIndex = 0;

		for (int i = 1; i <= count; i++) {

			boolean isVisible = page.locator(DATA_SPAN_SELECTOR_XPATH + "[" + i + "]").isVisible();
			if (isVisible) {
				lastVisibleIndex = i;
			} else {
				break;
			}
		}
		int nextIndex = lastVisibleIndex + 1;
		if (nextIndex <= count) {
			Locator nextDatavalue = page.locator(DATA_SELECTOR_XPATH + "[" + nextIndex + "]");
			nextDatavalue.click();
			page.locator(DATA_LIST_ITEM_SELECTOR_XPATH.replace("{value}", value)).click();
		}
	}

	public static void selectOperatorFromDropdown(Page page, String operator) {
		Locator operators = page.locator(OPERATOR_SELECTOR_XPATH);
		int count = operators.count();

		for (int i = 0; i <= count; i++) {
			Locator Operator = operators.nth(i);
			Locator input = page.locator(OPERATOR_SELECTOR_XPATH + "//input");

			for (int j = 0; j < input.count(); j++) {
				String value = input.nth(j).inputValue();
				if (value == null || value.trim().isEmpty()) {
					Operator.click();
					page.locator(COLOUMN_OPTION_SELECTOR_XPATH.replace("{columnName}", operator)).click();
				}
			}
		}
	}

	public static void selectColumnFromDropdown(Page page, String columnName) {
		Locator Headers = page.locator(COLOUMN_SELECTOR_XPATH);
		int count = Headers.count();
		for (int i = 0; i < count; i++) {
			Locator header = page.locator(COLOUMN_SELECTOR_XPATH + "[" + (i + 1) + "]");
			Locator input = page.locator(COLOUMN_INPUT_SELECTOR_XPATH + "[" + (i + 1) + "]");

			for (int j = 0; j < input.count(); j++) {
				String value = input.nth(j).inputValue();
				if (value == null || value.trim().isEmpty()) {
					header.click();
					page.locator(COLOUMN_OPTION_SELECTOR_XPATH.replace("{columnName}", columnName)).click();

				}
			}
		}
	}

	public static void clickOnRuleButton(Page page, String buttonName) {
		page.locator(RULE_BUTTON_XPATH.replace("{buttonName}", buttonName)).first().isVisible();
		page.locator(RULE_BUTTON_XPATH.replace("{buttonName}", buttonName)).first().click();
	}

	public static void changeOperatorTo(Page page, String operator) {
		page.locator("//div[text()='AND']").click();
		page.locator(CHANGE_DEFAULT_OPERATOR_XPATH.replace("{operator}", operator)).click();
	}

	public static void getDefaultOperator(Page page, String operator) {
		Locator andDropdowns = page.locator(CHECK_DEFAULT_OPERATOR_XPATH.replace("{operator}", operator));
		int count = andDropdowns.count();

		for (int i = 0; i < count; i++) {
			Locator dropdown = page
					.locator(CHECK_DEFAULT_OPERATOR_XPATH.replace("{operator}", operator) + "[" + (i + 1) + "]");
			if (!dropdown.isVisible()) {
				throw new AssertionError("AND operator dropdown at index " + i + " is not visible");
			}
		}
	}

	public static boolean isFilteredDataCorrectForColumns(Page page, List<String> columns, List<String> values,
			String operator) {
		Locator headerRow = page.locator("table tr").first();
		List<String> headers = headerRow.locator("th,td").allInnerTexts();
		List<Integer> colIndices = new ArrayList<>();

		for (String col : columns) {
			int idx = -1;
			for (int i = 0; i < headers.size(); i++) {
				if (headers.get(i).trim().equalsIgnoreCase(col.trim())) {
					idx = i;
					break;
				}
			}
			if (idx == -1) {
				throw new AssertionError(col + " column not found");
			}
			colIndices.add(idx);
		}

		List<Locator> allRows = page.locator("table tr").all();
		List<Locator> dataRows = allRows.subList(1, allRows.size());

		if ("AND".equalsIgnoreCase(operator)) {
			// Every row must match all column-value pairs
			for (Locator row : dataRows) {
				List<String> cells = row.locator("td").allInnerTexts();
				boolean allMatch = true;
				for (int i = 0; i < colIndices.size(); i++) {
					int colIdx = colIndices.get(i);
					if (cells.size() <= colIdx || !cells.get(colIdx).trim().equals(values.get(i).trim())) {
						allMatch = false;
						break;
					}
				}
				if (!allMatch) {
					return false; // As soon as one row doesn't match, return false
				}
			}
			return true; // All rows matched
		} else if ("OR".equalsIgnoreCase(operator)) {
			// Every row must match at least one column-value pair
			for (Locator row : dataRows) {
				List<String> cells = row.locator("td").allInnerTexts();
				boolean anyMatch = false;
				for (int i = 0; i < colIndices.size(); i++) {
					int colIdx = colIndices.get(i);
					if (cells.size() > colIdx && cells.get(colIdx).trim().equals(values.get(i).trim())) {
						anyMatch = true;
						break;
					}
				}
				if (!anyMatch) {
					return false; // As soon as one row doesn't match any, return false
				}
			}
			return true; // All rows matched at least one
		} else {
			throw new IllegalArgumentException("Unsupported operator: " + operator);
		}
	}
}
