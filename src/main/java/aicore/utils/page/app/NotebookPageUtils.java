package aicore.utils.page.app;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.microsoft.playwright.Keyboard;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;

import aicore.utils.AICorePageUtils;
import aicore.utils.CommonUtils;

public class NotebookPageUtils {

	private static final String NOTEBOOK_OPTION_XPATH = "//div[contains(@class,'flexlayout__border_button')][@title='Notebooks']";
	private static final String CREATE_NEW_NOTEBOOK_DATA_TESTID = "AddIcon";
	private static final String CODE_ENTER_TEXTAREA = ".view-line";
	private static final String QUERY_CODE_RUN_OUTPUT_XPATH = "//pre[text()='{codeOutput}']";
	private static final String IMPORT_DATA_OPTIONS_XPATH = "//li[@value='{optionName}']";
	private static final String SELECT_DATABASE_DROPDOWN_XPATH = "//label[text()='Select Database']/following-sibling::div//div[@role='combobox']";
	private static final String SELECT_ALL_COLUMNS_XPATH = "(//tbody//tr)[1]//input[@type='checkbox']";
	private static final String LIST_OF_COLUMN_NAMES_XPATH = "//table[contains(@class, 'MuiTable-root')]//tbody//tr[position()>1]//td[2]";
	private static final String IMPORT_BUTTON_XPATH = "//span[text()='Import']";
	private static final String FRAME_CSS = "input[value*='FRAME_']";
	private static final String DELETE_CELL_DATA_TESTID = "DeleteIcon";
	private static final String OUTPUT_TABLE = "//table";
	private static final String JSON_BODY_FIELD_VALUE_XPATH = "//div[contains(@class,'string-value MuiBox-root')]//span[text()='{fieldValue}']";
	private static final String SELECT_TYPE_DROPDOWN_XPATH = "//div[div[text()='Python']]";
	private static final String SELECT_TYPE_LISTBOX_XPATH = "//li[text()='{type}']";
	private static final String TOTAL_COUNT_OF_ROWS_XPATH = "(//span[contains(text(),'This is a preview of ingested data')])[1]";
	private static final String DEFAULT_LANGUAGE_XPATH = "//*[@value='py']";
	private static final String OUTPUT_XPATH = "//pre[text()='{Output}']";
	private static final String PYTHON_OUTPUT_XPATH = "//div[contains(@class,'data-type-label')]/..";
	private static final String NOTEBOOK_NAME_XPATH = "//p[text()='Notebook']/..//following::div//p[text()='{notebookName}']";
	private static final String QUERY_OUTPUT_COLUMN_XPATH = "//tr[contains(@class,'MuiTableRow-root')]//th[text()='{queryLocator}']";
	private static final String QUERY_OUTPUT_FIELD_XPATH = "//tr[contains(@class,'MuiTableRow-root')]//td[text()='{valueLocator}']";
	private static final String QUERY_CODE_RUN_NULL_OUTPUT_XPATH = "//tbody//td[contains(text(),'There was an issue generating a preview.')]";
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
	private static final String FILTER_SELECT_DATABASE_BLOCK_XPATH = "//div[@title ='Select Database']";
	private static final String QUERY_XPATH = "(//div[contains(@class,'view-line')]//div[contains(@class,'view-line')]//span//span)[1]";
	private static final String ADD_VALUE_IN_FIELD_XPATH = "(//div[contains(@role,'dialog')]//div//div[contains(@data-block,'input--')]//label[contains(text(),'{fieldName}')]//..//div//input)[2]";
	private static final String PROGRESS_BAR_IN_FIELD_XPATH = "(//label[contains(text(),'UNIQUE_ROW_ID')]/../div//div//span)[1]";
	private static final String LOADING_ICON_XPATH = "(//span[@role=\"progressbar\"]/../p[contains(text(), \"Loading\")])[2]";
	private static final String PROGRESS_BAR_READ_IN_FIELD_XPATH = "(//label[contains(text(),'Select Unique ID')]/../div//div//span)[1]";
	private static final String READ_RECORD_XPATH = "//p[contains(text(),'[DIABETES_UNIQUE_ROW_ID] : {uniqueId}')]";
//	private static final String UNIQUE_ROW_ID_FIELD_XPATH = "//button[@title='Open']//*[@data-testid='ArrowDropDownIcon']";
	private static final String NOTEBOOK_SEARCH_XPATH = "//p[contains(text(),'Notebook')]//..//..//div//div//input[@placeholder='Search']";
	private static final String DELETE_DIALOG_BOX_XPATH = "(//div[@role='dialog']//div//p)[1]";
	private static final String DELETE_DIALOG_BOX_DELETE_BUTTON_XPATH = "(//div[@role='dialog']//div//button//span[text()='Delete'])[2]";
	private static final String NOTEBOOK_MENU_BUTTON_XPATH = "//p[text()='{NOTEBOOK_NAME}']/../div//div//button";
	private static final String NOTEBOOK_MENU_DUPLICATE_BUTTON_XPATH = "//li[@value='Duplicate']";
	private static final String NOTEBOOK_MENU_DELETE_BUTTON_XPATH = "//li[@value='Delete']";
	private static final String NOTEBOOK_LIST_XPATH = "//li//p[text()='{NotebookName}']";
	private static final String UNIQUE_ROW_ID_FIELD_XPATH = "//label[text()='{label}']/parent::div//input[@aria-autocomplete='list']";
	private static final String TRANSFORMATION_OPTIONS_XPATH = "//li[@value='{optionName}']";
	private static final String TRANSFORMATION_TIMESTAMP_INCLUDE_CHECKBOX_XPATH = "//p[text()='Include time']";
	private static final String NOTEBOOK_MOUSE_HOVER_ABOVE_THE_CELL_XPATH = "//div[contains(@class,'MuiPaper-elevation MuiPaper-rounded')]//div[@title='Database Not Editable']";

	public static void clickOnNotebooksOption(Page page) {
		page.locator(NOTEBOOK_OPTION_XPATH).click();
	}

	public static void clickOnCreateNewNotebook(Page page) {
		page.getByTestId(CREATE_NEW_NOTEBOOK_DATA_TESTID).click();
	}

	public static void enterQueryName(Page page, String queryName) {
		Locator queryTextbox = page.getByRole(AriaRole.TEXTBOX);
		AICorePageUtils.waitFor(queryTextbox);
		queryTextbox.fill(queryName);
	}

	public static void clickOnQueryName(Page page, String queryName) {
		Locator queryTextbox = page.locator(NOTEBOOK_NAME_XPATH.replace("{notebookName}", queryName));
		AICorePageUtils.waitFor(queryTextbox);
		queryTextbox.click();
	}

	public static void clickOnRunCellButtonDatabase(Page page) {
		Locator block = page.locator(FILTER_SELECT_DATABASE_BLOCK_XPATH);
		if (block.isVisible()) {
			block.hover();
		}
		Locator runCellButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Run cell"));
		AICorePageUtils.waitFor(runCellButton);
		runCellButton.click();
		Locator checkCircle = page.getByTestId("CheckCircleIcon");
		AICorePageUtils.waitFor(checkCircle);
		checkCircle.isVisible();
	}

	public static void checkDatabaseQueryOutput(Page page) {
		Locator outputResult = page.locator(QUERY_CODE_RUN_NULL_OUTPUT_XPATH);
		AICorePageUtils.waitFor(outputResult);
		if (!outputResult.isVisible()) {
			throw new AssertionError("There was an issue generating a preview, output is not visible");
		}
	}

	public static void checkDatabaseOutput(Page page) {
		Locator databaseblock = page.getByTestId("CheckCircleIcon");
		AICorePageUtils.waitFor(databaseblock);
		if (!databaseblock.isVisible()) {
			throw new AssertionError("Database command not executed successfully");
		}
	}

	public static void addValueInField(Page page, String fieldName, String value) {
		Locator appFieldLocator = page.locator(ADD_VALUE_IN_FIELD_XPATH.replace("{fieldName}", fieldName));
		AICorePageUtils.waitFor(appFieldLocator);
		appFieldLocator.scrollIntoViewIfNeeded();
		if (!appFieldLocator.isVisible()) {
			throw new AssertionError(fieldName + " field is not visible");
		} else {
			appFieldLocator.fill(value);
		}
	}

	public static void selectValueFromDropdown(Page page, String value, String fieldName) {
		Locator appFieldLocator = page.locator(UNIQUE_ROW_ID_FIELD_XPATH.replace("{label}", fieldName)).nth(2);
		AICorePageUtils.waitFor(appFieldLocator);
		appFieldLocator.click();
		appFieldLocator.fill(value);
		appFieldLocator.press("ArrowDown");
		appFieldLocator.press("Enter");
	}

	public static void selectValueFromReadAppDropdown(Page page, String value, String fieldName) {
		Locator progressBar = page.locator(PROGRESS_BAR_READ_IN_FIELD_XPATH);
		page.waitForCondition(progressBar::isHidden, new Page.WaitForConditionOptions().setTimeout(15000));
		Locator appFieldLocator = page.locator(UNIQUE_ROW_ID_FIELD_XPATH).nth(1);
		AICorePageUtils.waitFor(appFieldLocator);
		appFieldLocator.scrollIntoViewIfNeeded();
		if (!appFieldLocator.isVisible()) {
			throw new AssertionError(fieldName + " field is not visible");
		} else {
			appFieldLocator.click(new Locator.ClickOptions().setForce(true));
			page.locator(DATA_LIST_ITEM_SELECTOR_XPATH.replace("{value}", value))
					.click(new Locator.ClickOptions().setForce(true));
		}

	}

	public static void checkRecordWithUniqueId(Page page, String uniqueId) {
		Locator loadingIndicator = page.locator(LOADING_ICON_XPATH);
		page.waitForCondition(loadingIndicator::isHidden, new Page.WaitForConditionOptions().setTimeout(10000));
		Locator recordLocator = page.locator(READ_RECORD_XPATH.replace("{uniqueId}", uniqueId));
		AICorePageUtils.waitFor(recordLocator);
		if (!recordLocator.isVisible()) {
			throw new AssertionError("Record with Unique ID '" + uniqueId + "' is not visible");
		}
	}

	public static void clickOnRecordButton(Page page, String buttonName) {
		Locator addRecordButton = page.getByRole(AriaRole.BUTTON,
				new Page.GetByRoleOptions().setName(buttonName + " Record"));
		AICorePageUtils.waitFor(addRecordButton);
		addRecordButton.scrollIntoViewIfNeeded();
		if (!addRecordButton.isVisible()) {
			throw new AssertionError(buttonName + " Record button is not visible/available");
		} else {
			addRecordButton.click();
		}
	}

	public static void modifySqlQuery(Page page, String newQuery) {
		Locator QueryTextArea = page.locator(QUERY_XPATH);
		QueryTextArea.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(10000));
		if (QueryTextArea.isVisible()) {
			QueryTextArea.click();
			String textContent = QueryTextArea.textContent();
			QueryTextArea.press("ArrowDown");

			// Press backspace until the text content is empty
			while (textContent != null && !textContent.isEmpty()) {
				QueryTextArea.press("Backspace");
				// Small delay to let the DOM update
				page.waitForTimeout(50);
				textContent = QueryTextArea.textContent();
			}
			page.keyboard().type(newQuery, new Keyboard.TypeOptions().setDelay(200));
		} else {
			throw new RuntimeException("Query Text Area is not visible");
		}
	}

	public static void checkSuccessMessage(Page page, String successMessage) {
		Locator successMessageLocator = page.getByText("true");
		AICorePageUtils.waitFor(successMessageLocator);
		if (!successMessageLocator.isVisible()
				|| !successMessageLocator.textContent().trim().equalsIgnoreCase(successMessage)) {
			throw new AssertionError("Success message is not visible");
		}
	}

	public static void checkNotebookPresence(Page page, String notebookName) {
		Locator notebookLocator = page.locator(NOTEBOOK_LIST_XPATH.replace("{NotebookName}", notebookName));
		AICorePageUtils.waitFor(notebookLocator);
		if (!notebookLocator.isVisible()) {
			throw new AssertionError("Notebook '" + notebookName + "' is not present in the notebook list");
		}
	}

	public static void SearchForNotebook(Page page, String notebookName) {
		Locator searchLocator = page.locator(NOTEBOOK_SEARCH_XPATH);
		AICorePageUtils.waitFor(searchLocator);
		searchLocator.fill(notebookName);
		Locator notebookLocator = page.getByText(notebookName);
		AICorePageUtils.waitFor(notebookLocator);
		if (!notebookLocator.isVisible()) {
			throw new AssertionError(
					"Notebook '" + notebookName + "' is not present in the notebook search result list");
		}
	}

	public static void duplicateNotebook(Page page, String notebookName) {
		Locator notebookLocator = page.locator(NOTEBOOK_LIST_XPATH.replace("{NotebookName}", notebookName));
		AICorePageUtils.waitFor(notebookLocator);
		notebookLocator.hover();
		Locator NotebookMenuButton = page.locator(NOTEBOOK_MENU_BUTTON_XPATH.replace("{NOTEBOOK_NAME}", notebookName));
		NotebookMenuButton.click();
		Locator notebookDuplicateButton = page.locator(NOTEBOOK_MENU_DUPLICATE_BUTTON_XPATH);
		AICorePageUtils.waitFor(notebookDuplicateButton);
		if (!notebookDuplicateButton.isVisible()) {
			throw new AssertionError("Duplicate button is not present in the notebook Menu list");
		}
		notebookDuplicateButton.click();
	}

	public static void deleteNotebook(Page page, String notebookName) {
		Locator notebookLocator = page.locator(NOTEBOOK_LIST_XPATH.replace("{NotebookName}", notebookName));
		AICorePageUtils.waitFor(notebookLocator);
		notebookLocator.hover();
		Locator notebookMenuButton = page.locator(NOTEBOOK_MENU_BUTTON_XPATH.replace("{NOTEBOOK_NAME}", notebookName));
		notebookMenuButton.click();
		Locator notebookDeleteButton = page.locator(NOTEBOOK_MENU_DELETE_BUTTON_XPATH);
		AICorePageUtils.waitFor(notebookDeleteButton);
		if (!notebookDeleteButton.isVisible()) {
			throw new AssertionError("Delete button is not present in the notebook Menu list");
		}
		notebookDeleteButton.click();
		// check for confirmation dialog and confirm deletion
		Locator deleteDialogBoxMessage = page.locator(DELETE_DIALOG_BOX_XPATH);
		String dialogBoxText = deleteDialogBoxMessage.textContent().trim();
		if (dialogBoxText.isEmpty() || !dialogBoxText.contains(notebookName)) {
			throw new AssertionError("Delete confirmation dialog box message is not as expected");
		}
		Locator deleteButton = page.locator(DELETE_DIALOG_BOX_DELETE_BUTTON_XPATH);
		AICorePageUtils.waitFor(deleteButton);
		if (!deleteButton.isVisible()) {
			throw new AssertionError("Delete button is not present in the delete confirmation dialog box");
		}
		deleteButton.click();
	}

	public static void clickOnQuerySubmitButton(Page page) {
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();
	}

	public static void enterCodeInQuery(Page page, String code) {
		code = code.replace("\\n", "\n");
		Locator cell = page.locator(CODE_ENTER_TEXTAREA).first();
		cell.scrollIntoViewIfNeeded();
		cell.click(new Locator.ClickOptions().setForce(true));
		for (int i = 0; i < code.length(); i++) {
			char c = code.charAt(i);
			if (c == '\n') {
				page.keyboard().press("Enter");
			} else {
				page.keyboard().type(String.valueOf(c));
			}
		}
	}

	public static void clickOnRunAllButton(Page page) {
		page.getByTestId("ArrowDownwardIcon").click();
	}

	public static String getCodeOutput(Page page, String codeOutput) {
		Locator outputResult = page.locator(QUERY_CODE_RUN_OUTPUT_XPATH.replace("{codeOutput}", codeOutput));
		AICorePageUtils.waitFor(outputResult);
		return outputResult.textContent().trim();
	}

	public static void mouseHoverOnNotebookHiddenOptions(Page page) {
		if (!page.getByTestId("data-key-pair").isVisible()) {
			Locator cell = page.locator(CODE_ENTER_TEXTAREA).first();
			AICorePageUtils.waitFor(cell);
			CommonUtils.moveMouseToCenterWithMargin(page, cell, 60, 20);
		} else {
			page.setViewportSize(1350, 650);
			Locator dataKeyPair = page.getByTestId("data-key-pairtype").nth(0);
			dataKeyPair.scrollIntoViewIfNeeded();
			AICorePageUtils.waitFor(dataKeyPair);
			dataKeyPair.hover();
			CommonUtils.moveMouseToCenterWithMargin(page, dataKeyPair, 65, 30);
		}
	}

	public static void clickOnHiddenNotebookOption(Page page, String optionName) {
		Locator option = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(optionName));
		AICorePageUtils.waitFor(option);
		option.click();
	}

	public static void selectHiddenOptionDropdown(Page page, String optionName) {
		page.locator(IMPORT_DATA_OPTIONS_XPATH.replace("{optionName}", optionName)).click();
	}

	public static void selectDatabaseFromDropdown(Page page, String databaseName) {
		Locator selectDatabaseDropdown = page.locator(SELECT_DATABASE_DROPDOWN_XPATH);
		AICorePageUtils.waitFor(selectDatabaseDropdown);
		selectDatabaseDropdown.click();
		page.waitForTimeout(300);
		page.getByText(databaseName).click();
	}
	public static void clickOnImportDropdown(Page page) {
		Locator selectDatabaseDropdown = page.locator(SELECT_DATABASE_DROPDOWN_XPATH);
		AICorePageUtils.waitFor(selectDatabaseDropdown);
		page.waitForTimeout(2000); // waiting for columns to map with view
		selectDatabaseDropdown.click();
	}

	public static void selectAllColumns(Page page) {
		page.locator(SELECT_ALL_COLUMNS_XPATH).click();
	}

	public static void clickOnImportButton(Page page) {
		Locator importButtonLocator = page.locator(IMPORT_BUTTON_XPATH);
		importButtonLocator.scrollIntoViewIfNeeded();
		AICorePageUtils.waitFor(importButtonLocator);
		importButtonLocator.click();
	}

	public static void deleteFirstCell(Page page) {
		Locator deleteIcon = page.getByTestId(DELETE_CELL_DATA_TESTID).first();
		AICorePageUtils.waitFor(deleteIcon);
		deleteIcon.click();
	}

	public static void selectDatabaseType(Page page, String databaseName) {
		page.getByTitle("Select Database").isVisible();
		page.locator("//*[@title=\"Select Database\"]//*[@data-testid=\"KeyboardArrowDownIcon\"]").click(new Locator.ClickOptions().setForce(true));
		page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(databaseName)).click();
	}

	public static void clickOnRunCellButton(Page page) {
		Locator block = page.locator(FILTER_SELECT_FRAME_BLOCK_XPATH);
		if (block.isVisible()) {
			block.hover();
		}
		Locator runCellButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Run cell")).last();
		AICorePageUtils.waitFor(runCellButton);
		runCellButton.click();
		Locator checkCircle = page.getByTestId("CheckCircleIcon");
		AICorePageUtils.waitFor(checkCircle);
		checkCircle.isVisible();
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
		Locator tableHeader = page.locator(OUTPUT_TABLE).last().locator("th");
		Locator table = page.locator(OUTPUT_TABLE);
		AICorePageUtils.waitFor(table);
		return tableHeader.allTextContents();
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
		Locator headers = page.locator(OUTPUT_TABLE).last().locator("th");
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
		Locator rows = page.locator(OUTPUT_TABLE).last().locator("//tbody//tr");
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

	public static void writeQuery(Page page, String query) {
		Locator editor = page.locator(".monaco-editor").first();
		editor.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		editor.click();
		Locator inputField = page.locator(CODE_ENTER_TEXTAREA).first();
		inputField.focus();
		page.keyboard().press("Control+A");
		page.keyboard().press("Backspace");
		inputField.pressSequentially(query);
	}

	public static boolean validateQuery(Page page, String queryLocator, String value) {
		boolean isColumnVisible = page.locator(QUERY_OUTPUT_COLUMN_XPATH.replace("{queryLocator}", queryLocator))
				.isVisible();
		boolean isFieldVisible = page.locator(QUERY_OUTPUT_FIELD_XPATH.replace("{queryLocator}", value)).isVisible();
		if (!isFieldVisible && !isColumnVisible) {
			throw new AssertionError(
					"Column with header " + queryLocator + " and value " + value + " is not visible in the output");
		}
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

	public static void enterDataLimit(Page page, String dataLimit) {
		Locator dataLimitField = page.getByLabel("Data Limit");
		AICorePageUtils.waitFor(dataLimitField);
		dataLimitField.fill(dataLimit);
	}

	public static void clickOnRunAllCellButton(Page page) {
		Locator runAllCell = page.getByTitle("Run all cells");
		runAllCell.click();
		page.waitForLoadState(LoadState.LOAD);
		page.getByTestId("data-key-pair").last().isVisible();
		page.waitForTimeout(1000);
	}

	public static void selectTransformationOptionDropdown(Page page, String optionName) {
		page.locator(TRANSFORMATION_OPTIONS_XPATH.replace("{optionName}", optionName)).click();
	}

	public static void selectColumnForTransformation(Page page, String columnName) {
		Locator columnDropdown = page.getByTitle("Open");
		AICorePageUtils.waitFor(columnDropdown);
		columnDropdown.click();
		page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(columnName).setExact(true)).click();
	}

	public static boolean isColumnDataInUppercase(Page page, String columnName) {
		String columnNameText = page.locator(OUTPUT_TABLE).last().locator("//th").textContent().trim();
		if (columnNameText.equals(columnName)) {
			Locator value = page.locator(OUTPUT_TABLE).last().locator("//tbody//tr/td");
			int rowCount = value.count();
			for (int i = 0; i < rowCount; i++) {
				String cellText = value.nth(i).textContent().trim();
				if (!cellText.equals(cellText.toUpperCase())) {
					return false; // if any cell text not in uppercase return falsecellText
				}
			}
		}
		return true;
	}

	public static void enterColumnName(Page page, String columnName) {
		Locator columnTextbox = page.getByLabel("Column Name");
		AICorePageUtils.waitFor(columnTextbox);
		columnTextbox.fill(columnName);
	}

	public static List<String> getColumnValues(Page page, String columnName) {
		List<String> headers = page.locator(OUTPUT_TABLE).last().locator("//th").allInnerTexts();
		int columnIndex = -1;
		for (int i = 0; i < headers.size(); i++) {
			if (headers.get(i).trim().equalsIgnoreCase(columnName.trim())) {
				columnIndex = i + 1;
				break;
			}
		}
		if (columnIndex == -1) {
			throw new RuntimeException("Column not found: " + columnName);
		}
		return page.locator(OUTPUT_TABLE).last().locator("//tbody//tr/td[" + columnIndex + "]").allInnerTexts();
	}

	public static void clickOnIncludeTimeCheckbox(Page page) {
		Locator checkbox = page.locator(TRANSFORMATION_TIMESTAMP_INCLUDE_CHECKBOX_XPATH);
		AICorePageUtils.waitFor(checkbox);
		checkbox.click();
	}

	public static void mouseHoverAboveNotebookHiddenOptions(Page page) {
		page.getByTestId("data-key-pair").isVisible();
		Locator cell = page.locator(NOTEBOOK_MOUSE_HOVER_ABOVE_THE_CELL_XPATH);
		CommonUtils.moveMouseToCenterWithMargin(page, cell, -70, 20);
	}

	public static void mouseHoverOnBlankCell(Page page) {
		Locator cell = page.locator(CODE_ENTER_TEXTAREA).first();
		AICorePageUtils.waitFor(cell);
		CommonUtils.moveMouseToCenterWithMargin(page, cell, 60, 20);
	}

}
