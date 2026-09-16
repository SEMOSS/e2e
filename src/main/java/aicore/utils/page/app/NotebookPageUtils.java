package aicore.utils.page.app;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Keyboard;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;

import aicore.utils.AICorePageUtils;
import aicore.utils.CommonUtils;
import aicore.utils.waitLayer.Waits;

public class NotebookPageUtils {

	private static final Logger logger = LogManager.getLogger(NotebookPageUtils.class);

	private static final String NOTEBOOK_OPTION_XPATH = "//div[contains(@class,'flexlayout__border_button')][@title='Notebooks']";
	private static final String CODE_ENTER_TEXTAREA = "//div[@class='view-lines monaco-mouse-cursor-text']";
	private static final String QUERY_CODE_RUN_OUTPUT_XPATH = "//pre[text()='{codeOutput}']";
	private static final String IMPORT_DATA_OPTIONS_XPATH = "//div[text()='{optionName}']";

	private static final String SELECT_DATABASE_DROPDOWN_XPATH = "//button[@role='combobox']";
	private static final String SELECT_ALL_COLUMNS_XPATH = "//table[contains(@class,'caption-bottom')]//thead//button[@role='checkbox']";

	private static final String EDIT_IMPORTED_DATA_CELL_BUTTON_XPATH = "//button[text()='Edit']";
	private static final String UPDATE_CELL_BUTTON_XPATH = "//button[@type='submit']";
	private static final String LIST_OF_COLUMN_NAMES_XPATH = "//table[contains(@class,'caption-bottom')]//tbody//tr//td[1]//span";

	private static final String IMPORT_BUTTON_XPATH = "//button[@type='submit' and normalize-space()='Import']";
	private static final String FRAME_CSS = "input[value*='FRAME_']";
	private static final String DELETE_CELL_DATA_XAPTH = "//button[@title='Delete cell']";
	private static final String OUTPUT_TABLE = "//table";
	private static final String JSON_BODY_FIELD_VALUE_XPATH = "//span[contains(@class,'text-destructive') and contains(., '{fieldValue}')]";
	private static final String SELECT_TYPE_DROPDOWN_XPATH = "//button[span[text()='{type}']]";
	private static final String SELECT_TYPE_LISTBOX_XPATH = "//div[@role='option']//span[text()='{type}']";
	
	private static final String TOTAL_COUNT_OF_ROWS_XPATH = "(//span[contains(text(),'This is a preview of ingested data')])[1]";
	
	private static final String DEFAULT_LANGUAGE_XPATH = "//button[@role='combobox' and .//title[text()='Python']]";
	
	private static final String OUTPUT_XPATH = "//pre[text()='{Output}']";
	private static final String PYTHON_OUTPUT_XPATH = "//pre[text()='{codeOutput}']";
	private static final String NOTEBOOK_NAME_XPATH = "//span[text()='Notebook']/following::li//span[text()='{notebookName}']";
	private static final String QUERY_OUTPUT_COLUMN_XPATH = "//th[text()='{queryLocator}']";
	private static final String QUERY_OUTPUT_FIELD_XPATH = "//td[text()='{valueLocator}']";
	private static final String QUERY_CODE_RUN_NULL_OUTPUT_XPATH = "//tbody//td[contains(text(),'There was an issue generating a preview.')]";
	private static final String CHECK_DEFAULT_OPERATOR_XPATH = "(//span[text()='{operator}'])";
	private static final String CHANGE_DEFAULT_OPERATOR_XPATH = "//div[@role='option']//span[text()='{operator}']";
	private static final String COLOUMN_SELECTOR_XPATH = "//button[@role='combobox']//span[text()='Select Header']";
	private static final String COLOUMN_INPUT_SELECTOR_XPATH = "(//div[@title='Select Header']//input)";
	private static final String COLOUMN_OPTION_SELECTOR_XPATH = "//div[@role='option']//span[text()='{operator}']";
	private static final String OPERATOR_SELECTOR_XPATH = ".//button[@role='combobox' and @data-slot='select-trigger']";

	private static final String DATA_SELECTOR_XPATH = "//span[text()='{selectValue}']/parent::label//button";
	private static final String DATA_LIST_ITEM_SELECTOR_XPATH = "(//li[text()='{value}'])";
	private static final String DATA_SPAN_SELECTOR_XPATH = "(//label[text()='Select Data']/..//div//div[@role='button']//span)";
	private static final String RULE_BUTTON_XPATH = "//button[contains(normalize-space(),'{buttonName}')]";
	private static final String FILTER_SELECT_FRAME_BLOCK_XPATH = "//input[@title='Set Frame Variable Name']";
	private static final String FILTER_SELECT_DATABASE_BLOCK_XPATH = "//div[@title ='Select Database']";
	private static final String QUERY_XPATH = "(//div[contains(@class,'view-line')]//div[contains(@class,'view-line')]//span//span)[1]";
	private static final String ADD_VALUE_IN_FIELD_XPATH = "(//div[contains(@role,'dialog')]//div//div[contains(@data-block,'input--')]//label[contains(text(),'{fieldName}')]//..//div//input)[2]";
	private static final String LOADING_ICON_XPATH = "(//span[@role=\"progressbar\"]/../p[contains(text(), \"Loading\")])[2]";
	private static final String PROGRESS_BAR_READ_IN_FIELD_XPATH = "(//label[contains(text(),'Select Unique ID')]/../div//div//span)[1]";
	private static final String READ_RECORD_XPATH = "//p[contains(text(),'[DIABETES_UNIQUE_ROW_ID] : {uniqueId}')]";
	private static final String NOTEBOOK_SEARCH_TEXT = "Search";
	private static final String DELETE_DIALOG_BOX_XPATH = "//p/ancestor::div[@role='dialog']";
	private static final String DELETE_DIALOG_BOX_DELETE_BUTTON_XPATH = "//button[text()='Delete']";
	private static final String NOTEBOOK_MENU_BUTTON_XPATH = "//p[text()='{NOTEBOOK_NAME}']/../div//div//button";
	private static final String NOTEBOOK_MENU_DUPLICATE_BUTTON_XPATH = "//span[text()='{notebookName}']/following::div//button[@title='Duplicate']";
	private static final String NOTEBOOK_MENU_DELETE_BUTTON_XPATH = "//span[normalize-space()='{notebookName}']/following::div//button[@title='Delete']";
	private static final String NOTEBOOK_LIST_XPATH = "//li//span[text()='{NotebookName}']";
	private static final String UNIQUE_ROW_ID_FIELD_XPATH = "//div[@class='relative']//button//*[name()='svg'][contains(@class,'lucide-chevron-down')]";
	private static final String TRANSFORMATION_OPTIONS_XPATH = "//li[@value='{optionName}']";
	private static final String TRANSFORMATION_TIMESTAMP_INCLUDE_CHECKBOX_XPATH = "//p[text()='Include time']";
	private static final String NOTEBOOK_MOUSE_HOVER_ABOVE_THE_CELL_XPATH = "//div[contains(@class,'MuiPaper-elevation MuiPaper-rounded')]//div[@title='Database Not Editable']";
	private static final String DROPDOWN_BUTTON_XPATH = "//label[text()='{dropdownName}']/..//button";
	private static final String RUN_CELL_OPTION_XPATH = "//button[@title='Run cell']";
	private static final String RUN_CELL_LOADING_ICON_XPATH = "//button[@title='Run cell']//*[name()='svg'][@aria-label='Loading']";
	private static final String CELL_XPATH = "//div[contains(@id,'notebook-cell')]";
	private static final String NOTEBOOK_SELECT_HEADER_XPATH = "//div[@role='option']//span[text()='{selectHeader}']";
	private static final String CONFIRM_DUPLICATE_NOTEBOOK_XPATH = "//button[text()='Duplicate']";
	private static final String RUN_CELL_LOADER_XPATH = "//button//*[name()='svg'][contains(@class,'lucide-loader-circle')]";
	private static final String PREVIEW_APP_BUTTON_XPATH = "//span[text()='{buttonName}']/parent::button";
	private static final String CREATE_NEW_NOTEBOOK_TEXT = "Create new notebook";

	private static final String LANGUAGE_TRIGGER_XPATH = "//button[@role='combobox']";

	private static final String NEW_QUERY_DIALOG_XPATH = 
		    "//div[@role='dialog'][.//h2[text()='New Query']][not(@aria-hidden='true')]";

	
	private static final String QUERY_ID_INPUT_XPATH = "//label[text()='Id']/following-sibling::input";
	private static final String QUERY_SUBMIT_BUTTON_XPATH = "//button[text()='Submit']";

	
	public static void clickOnNotebooksOption(Page page) {
		logger.info("Starting: clickOnNotebooksOption");
		Waits.waitForPageLoad(page);
		Locator locator = page.locator(NOTEBOOK_OPTION_XPATH);
		Waits.waitForElementClickable(locator);
		locator.click();
		logger.info("Completed: clickOnNotebooksOption");
	}

	public static void clickOnCreateNewNotebook(Page page) {
		logger.info("Starting: clickOnCreateNewNotebook");
		Waits.waitForPageLoad(page);
		Locator locator = page.getByTitle(CREATE_NEW_NOTEBOOK_TEXT);
		Waits.waitForElementClickable(locator);
		locator.click();
		logger.info("Completed: clickOnCreateNewNotebook");
	}

	public static void enterQueryName(Page page, String queryName) {
	    logger.info("Starting: enterQueryName with value '{}'", queryName);
	    Locator queryTextbox = page.locator("//label[text()='Id']/following::input").first();
	    Waits.waitForElementVisible(queryTextbox);
	    queryTextbox.click(new Locator.ClickOptions().setForce(true));
	    queryTextbox.focus();
	    page.keyboard().type(queryName);
	    logger.info("Entered value: {}", queryTextbox.inputValue());
	    logger.info("Completed: enterQueryName");
	}


	
	public static void clickOnQuerySubmitButton(Page page) {
	    logger.info("Starting: clickOnQuerySubmitButton");
	    Locator submitButton = page.locator(QUERY_SUBMIT_BUTTON_XPATH).first();
	    Waits.waitForElementVisible(submitButton);
	    submitButton.click(new Locator.ClickOptions().setForce(true));
	    //submitButton.click();
	    logger.info("Completed: clickOnQuerySubmitButton");
	}

	
	public static void enterCodeInQuery(Page page, String code) {
	    logger.info("Starting: enterCodeInQuery");
	    code = code.replace("\\n", "\n");
	    Locator cell = page.locator(CODE_ENTER_TEXTAREA).first();
	    Waits.waitForElementVisible(cell);
	    cell.scrollIntoViewIfNeeded();
	    cell.click(new Locator.ClickOptions().setForce(true));
	    page.keyboard().insertText(code);
	    logger.info("Completed: enterCodeInQuery");
	}


	public static void mouseHoverOnNotebookHiddenOptions(Page page) {
		logger.info("Starting: mouseHoverOnNotebookHiddenOptions");
		if (!page.getByTestId("data-key-pair").isVisible()) {
			Locator cell = page.locator(CODE_ENTER_TEXTAREA).first();
			Waits.waitForElementVisible(cell);
			CommonUtils.moveMouseToCenterWithMargin(page, cell, 60, 20);
		} else {
			page.setViewportSize(1350, 650);
			Locator dataKeyPair = page.getByTestId("data-key-pairtype").nth(0);
			dataKeyPair.scrollIntoViewIfNeeded();
			Waits.waitForElementVisible(dataKeyPair);
			dataKeyPair.hover();
			CommonUtils.moveMouseToCenterWithMargin(page, dataKeyPair, 65, 30);
		}
		logger.info("Completed: mouseHoverOnNotebookHiddenOptions");
	}

	public static void clickOnHiddenNotebookOption(Page page, String optionName) {
		logger.info("Starting: clickOnHiddenNotebookOption with optionName '{}'", optionName);
		Locator option = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(optionName));
		Waits.waitForElementClickable(option);
		option.click();
		logger.info("Completed: clickOnHiddenNotebookOption");
	}
	
	public static void clickOnHiddenNotebookOptionForCurrentCell(Page page, String optionName) {
		logger.info("Starting: clickOnHiddenNotebookOption with optionName '{}'", optionName);
		Locator option = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(optionName));
		Waits.waitForElementClickable(option);
		option.click();
		logger.info("Completed: clickOnHiddenNotebookOption");
	}



	public static void selectHiddenOptionDropdown(Page page, String optionName) {
	    logger.info("Starting: selectHiddenOptionDropdown with optionName '{}'", optionName);
	    Locator option = page.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName(optionName));
	    Waits.waitForElementClickable(option);
	    option.click();
	    logger.info("Completed: selectHiddenOptionDropdown");
	}

	public static void selectDatabaseFromDropdown(Page page, String databaseName) {
		logger.info("Starting: selectDatabaseFromDropdown with databaseName '{}'", databaseName);
		Waits.waitForPageLoad(page);		
		Locator selectDatabaseDropdown = page.locator(SELECT_DATABASE_DROPDOWN_XPATH).last();
		Waits.waitForElementClickable(selectDatabaseDropdown);
		selectDatabaseDropdown.click();
		Locator option = page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(databaseName));
		Waits.waitForElementClickable(option);
		option.click();
		logger.info("Completed: selectDatabaseFromDropdown");
	}


	public static void selectAllColumns(Page page) {
		logger.info("Starting: selectAllColumns");
		Locator checkbox = page.locator("button[role='checkbox']").first();
		Waits.waitForElementClickable(checkbox);
		checkbox.click();
		logger.info("Completed: selectAllColumns");
	}
	


	public static void clickOnImportButton(Page page) {
		logger.info("Starting: clickOnImportButton");
		Locator importButtonLocator = page.locator(IMPORT_BUTTON_XPATH);
		importButtonLocator.scrollIntoViewIfNeeded();
		Waits.waitForElementClickable(importButtonLocator);
		importButtonLocator.click();
		logger.info("Completed: clickOnImportButton");
	}
	

	public static void clickOnEditButtonForImportedDataCell(Page page) {
		logger.info("Starting: clickOnEditButtonForImportedDataCell");
		Locator editButton = page.locator(EDIT_IMPORTED_DATA_CELL_BUTTON_XPATH);
		Waits.waitForElementVisible(editButton);
		if (!editButton.isVisible()) {
			logger.error("Edit button for imported data cell is not visible");
			throw new AssertionError("Edit button for imported data cell is not visible");
		}
		editButton.click();
		logger.info("Completed: clickOnEditButtonForImportedDataCell");
	}

	public static void uncheckColumnFromSelectedColumns(Page page, String columnName) {
		logger.info("Starting: uncheckColumnFromSelectedColumns with columnName '{}'", columnName);
		Locator checkbox = page.locator("#checkbox-" + columnName + "-0");
		Waits.waitForElementClickable(checkbox);
		checkbox.click();
		logger.info("Completed: uncheckColumnFromSelectedColumns");
	}
	
	public static void clickOnUpdateCellButton(Page page) {
		logger.info("Starting: clickOnUpdateCellButton");
		Locator updateButton = page.locator(UPDATE_CELL_BUTTON_XPATH);
		Waits.waitForElementVisible(updateButton);
		if (!updateButton.isVisible()) {
			logger.error("Update cell button is not visible");
			throw new AssertionError("Update cell button is not visible");
		}
		updateButton.click();
		logger.info("Completed: clickOnUpdateCellButton");
	}


	public static void deleteFirstCell(Page page) {
	    logger.info("Starting: deleteFirstCell");
	    Locator firstCell = page.locator(CELL_XPATH).first();
	    Waits.waitForElementVisible(firstCell);
	    firstCell.hover();
	    Locator deleteIcon = page.locator(DELETE_CELL_DATA_XAPTH).first();
	    Waits.waitForElementVisible(deleteIcon);
	    deleteIcon.hover();
	    deleteIcon.click(new Locator.ClickOptions().setForce(true));
	    logger.info("Completed: deleteFirstCell");
	}



	public static void selectDatabaseType(Page page, String databaseName) {
	    logger.info("Starting: selectDatabaseType with databaseName '{}'", databaseName);
	    Locator combobox = page.locator("button[role='combobox']").filter(
	        new Locator.FilterOptions().setHasText(databaseName));
	    Waits.waitForElementVisible(combobox);
	    combobox.click();
	    Locator option = page.getByRole(AriaRole.OPTION,
	            new Page.GetByRoleOptions().setName(databaseName)).first();
	    Waits.waitForElementClickable(option);
	    option.click();
	    logger.info("Completed: selectDatabaseType");
	}

	public static void clickOnRunCellButton(Page page) {
		logger.info("Starting: clickOnRunCellButton");
		Locator block = page.locator(FILTER_SELECT_FRAME_BLOCK_XPATH);
		if (block.isVisible()) {
			block.hover();
		}
		Locator runCellButton = page.locator(RUN_CELL_OPTION_XPATH).first();
		Waits.waitForElementClickable(runCellButton);
		runCellButton.click();
		Locator checkCircle = page.locator(RUN_CELL_LOADING_ICON_XPATH);
		Waits.waitForElementAttached(checkCircle);
		page.waitForTimeout(500);
		logger.info("Completed: clickOnRunCellButton");
	}


	public static String getFrameID(Page page) {
	    logger.info("Starting: getFrameID");
	    Locator frameInput = page.locator(
	        "div[class*='border-primary'] input[title='Set frame variable name']");
	    Waits.waitForElementVisible(frameInput);
	    String frameId = frameInput.inputValue().trim();

	    logger.info("Completed: getFrameID - Frame ID: {}", frameId);
	    return frameId;
	}


	public static List<String> getNotebookOutputTableHeader(Page page) {
		logger.info("Starting: getNotebookOutputTableHeader");
		Locator table = page.locator(OUTPUT_TABLE).first();
		Waits.waitForElementVisible(table);
		Locator tableHeader = page.locator(OUTPUT_TABLE).last().locator("th");
		List<String> result = tableHeader.allTextContents();
		logger.info("Completed: getNotebookOutputTableHeader");
		return result;
	}


	public static int getTotalRowsFromPreviewCaption(Page page) {
		logger.info("Starting: getTotalRowsFromPreviewCaption");
		final String compilePattern = "Showing \\d+ of (\\d+)";
		Locator previewCaption = page.locator(TOTAL_COUNT_OF_ROWS_XPATH);
		Waits.waitForElementVisible(previewCaption);
		previewCaption.scrollIntoViewIfNeeded();
		String captionText = previewCaption.textContent();
		Pattern pattern = Pattern.compile(compilePattern);
		Matcher matcher = pattern.matcher(captionText);
		if (matcher.find()) {
			int total = Integer.parseInt(matcher.group(1));
			logger.info("Completed: getTotalRowsFromPreviewCaption");
			return total;
		} else {
			logger.error("Failed to extract total row count from caption: {}", captionText);
			throw new RuntimeException("Failed to extract total row count from caption: " + captionText);
		}
	}

	
	public static boolean isColumnUniqueByHeader(Page page, String headerName) {
		logger.info("Starting: isColumnUniqueByHeader with headerName '{}'", headerName);
		Locator table = page.locator(OUTPUT_TABLE).last();
		Waits.waitForElementVisible(table);
		Locator headers = table.locator("th");
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
			logger.error("Header with label '{}' not found", headerName);
			throw new RuntimeException("Header with label '" + headerName + "' not found");
		}
		Locator rows = table.locator("//tbody//tr");
		int rowCount = rows.count();
		Set<String> uniqueValues = new HashSet<>();
		for (int i = 0; i < rowCount; i++) {
			Locator cell = rows.nth(i).locator("td").nth(targetColumnIndex);
			String cellText = cell.textContent().trim();
			uniqueValues.add(cellText);
		}
		logger.info("Completed: isColumnUniqueByHeader");
		return uniqueValues.size() == rowCount;
	}

	

		public static void selectTypeFromDropdown(Page page, String type) {
		logger.info("Starting: selectTypeFromDropdown with type '{}'", type);
		Locator dropdownArrow = page.locator(SELECT_TYPE_DROPDOWN_XPATH.replace("{type}", type)).first();
		Waits.waitForElementClickable(dropdownArrow);
		dropdownArrow.click();
		Locator listboxOption = page.locator(SELECT_TYPE_LISTBOX_XPATH.replace("{type}", type));
		Waits.waitForElementClickable(listboxOption);
		listboxOption.click();
		logger.info("Completed: selectTypeFromDropdown");
	}
	
	public static String validateJsonFieldValue(Page page, String fieldValue) {
	    logger.info("Starting: validateJsonFieldValue with fieldValue '{}'", fieldValue);
	    Locator jsonNameLocator = page.locator(
	        JSON_BODY_FIELD_VALUE_XPATH.replace("{fieldValue}", fieldValue));
	    Waits.waitForElementVisible(jsonNameLocator);
	    String result = jsonNameLocator.textContent();
	    logger.info("Completed: validateJsonFieldValue. Found value: '{}'", result);
	    return result;
	}



	public static void hoverAndClickOnCell(Page page) {
		logger.info("Starting: hoverAndClickOnCell");
		Locator cellLocator = page.getByTitle("Cell", new Page.GetByTitleOptions().setExact(true));
		Waits.waitForElementVisible(cellLocator);
		cellLocator.hover();
		Waits.waitForElementClickable(cellLocator);
		cellLocator.click();
		logger.info("Completed: hoverAndClickOnCell");
	}

	public static void checkPythonAsDefaultLanguage(Page page) {
	    logger.info("Starting: checkPythonAsDefaultLanguage");
	    Locator defaultLanguage = page.locator(
	        "//button[@role='combobox' and .//*[local-name()='title' and text()='Python']]"
	    );
	    Waits.waitForElementVisible(defaultLanguage);
	    if (!defaultLanguage.isVisible()) {
	        logger.error("Python is not selected as the default language option");
	        throw new AssertionError("Python is not selected as the default language option");
	    }
	    logger.info("Completed: checkPythonAsDefaultLanguage");
	}


		
		public static void changeToLanguage(Page page, String language) {
		    logger.info("Starting: changeToLanguage with language '{}'", language);
		    Locator selectLanguage = page.locator(LANGUAGE_TRIGGER_XPATH);
		    Waits.waitForElementClickable(selectLanguage);
		    selectLanguage.click();
		    Waits.waitForElementVisible(page.getByRole(AriaRole.LISTBOX));
		    Locator languageOption = page.getByRole(AriaRole.LISTBOX)
		        .locator("//*[contains(text(),'" + language + "')]");
		    Waits.waitForElementClickable(languageOption);
		    languageOption.click();
		    logger.info("Completed: changeToLanguage");
		}




		public static void getPixelOutput(Page page, String output) {
		    logger.info("Starting: getPixelOutput with output '{}'", output);
		    	 Locator outputLocator = page.locator(".monaco-editor .view-line")
		        .filter(new Locator.FilterOptions().setHasText(output))
		        .first();
		    Waits.waitForElementVisible(outputLocator);
		    if (!outputLocator.isVisible()) {
		        logger.error("Expected Pixel output: {}, but it was not visible", output);
		        throw new AssertionError("Expected Pixel output: " + output + ", but it was not visible");
		    }
		    logger.info("Completed: getPixelOutput");
		}

	public static void getPythonOutput(Page page, String output) {
		logger.info("Starting: getPythonOutput with output '{}'", output);
		Locator outputLocator = page.locator(PYTHON_OUTPUT_XPATH.replace("{codeOutput}", output));
		Waits.waitForElementVisible(outputLocator);
		String pythonOutput = outputLocator.textContent();
		if (pythonOutput == null || !pythonOutput.contains(output)) {
			logger.error("Expected Python output: {}, but got: {}", output, pythonOutput);
			throw new AssertionError("Expected Python output: " + output + ", but got: " + pythonOutput);
		}
		logger.info("Completed: getPythonOutput");
	}

	public static void clickOnNotebook(Page page, String notebookName) {
		logger.info("Starting: clickOnNotebook with notebookName '{}'", notebookName);
		Locator notebookLocator = page.locator(NOTEBOOK_NAME_XPATH.replace("{notebookName}", notebookName));
		Waits.waitForElementClickable(notebookLocator);
		notebookLocator.click();
		logger.info("Completed: clickOnNotebook");
	}

	public static void verifyNotebookIsPresentInList(Page page, String notebookName) {
		logger.info("Starting: verifyNotebookIsPresentInList with notebookName '{}'", notebookName);
		Locator notebookLocator = page.locator(NOTEBOOK_NAME_XPATH.replace("{notebookName}", notebookName));
		Waits.waitForElementVisible(notebookLocator);
		if (!notebookLocator.isVisible()) {
			logger.error("Notebook '{}' is not present in the list", notebookName);
			throw new AssertionError("Notebook '" + notebookName + "' is not present in the list");
		}
		logger.info("Completed: verifyNotebookIsPresentInList");
	}
	public static void writeQuery(Page page, String query) {
		logger.info("Starting: writeQuery with query '{}'", query);
		Locator editor = page.locator(".monaco-editor").first();
		Waits.waitForElementVisible(editor);
		editor.click();
		Locator inputField = page.locator(CODE_ENTER_TEXTAREA).first();
		Waits.waitForElementVisible(inputField);
		inputField.focus();
		page.keyboard().press("Control+A");
		page.keyboard().press("Backspace");
		inputField.pressSequentially(query);
		logger.info("Completed: writeQuery");
	}

	public static boolean validateQuery(Page page, String queryLocator, String value) {
		logger.info("Starting: validateQuery with queryLocator '{}' and value '{}'", queryLocator, value);
		Locator columnName = page.locator(QUERY_OUTPUT_COLUMN_XPATH.replace("{queryLocator}", queryLocator));
		Waits.waitForElementVisible(columnName);
		Locator fieldLocator = page.locator(QUERY_OUTPUT_FIELD_XPATH.replace("{queryLocator}", value));
		boolean isFieldVisible = fieldLocator.isVisible();
		if (!isFieldVisible && !columnName.isVisible()) {
			logger.error("Column with header {} and value {} is not visible in the output", queryLocator, value);
			throw new AssertionError(
					"Column with header " + queryLocator + " and value " + value + " is not visible in the output");
		}
		logger.info("Completed: validateQuery");
		return true;
	}

	public static boolean isFilteredDataCorrect(Page page, String columnName, String expectedValue) {
		logger.info("Starting: isFilteredDataCorrect with columnName '{}' and expectedValue '{}'", columnName,
				expectedValue);
		Locator headerRow = page.locator("table tr").first();
		Waits.waitForElementVisible(headerRow);
		List<String> headers = headerRow.locator("th,td").allInnerTexts();
		int colIndex = -1;
		for (int i = 0; i < headers.size(); i++) {
			if (headers.get(i).trim().equalsIgnoreCase(columnName)) {
				colIndex = i;
				break;
			}
		}
		if (colIndex == -1) {
			logger.error("{} column not found", columnName);
			throw new AssertionError(columnName + " column not found");
		}
		List<Locator> dataRows = page.locator("table tr").all().subList(1, page.locator("table tr").count());
		for (Locator row : dataRows) {
			List<String> cells = row.locator("td").allInnerTexts();
			if (cells.size() > colIndex) {
				String cellValue = cells.get(colIndex).trim();
				if (!cellValue.equals(expectedValue)) {
					logger.info("Completed: isFilteredDataCorrect - mismatch found");
					return false;
				}
			}
		}
		logger.info("Completed: isFilteredDataCorrect");
		return true;
	}

	
	public static void enterValueInInput(Page page, String value) {
		logger.info("Starting: enterValueInInput with value '{}'", value);
		Locator dataValue = page.locator(DATA_SELECTOR_XPATH.replace("{selectValue}", value));
		Waits.waitForElementClickable(dataValue);
		dataValue.click();
		logger.info("Completed: enterValueInInput");
	}

	// need review
	public static void selectOperatorFromDropdown(Page page, String operator) {
	    logger.info("Starting: selectOperatorFromDropdown with operator '{}'", operator);
	    Locator currentCell = getCurrentCell(page);
	    Locator operators = currentCell.locator(OPERATOR_SELECTOR_XPATH);
	    operators.scrollIntoViewIfNeeded();
	    Waits.waitForElementVisible(operators.first());
	    Locator operatorLocator = operators.nth(1); 
	    operatorLocator.scrollIntoViewIfNeeded();
	    Waits.waitForElementClickable(operatorLocator);
	    operatorLocator.click();
	    Locator option = page.locator(COLOUMN_OPTION_SELECTOR_XPATH.replace("{operator}", operator));
	    option.first().scrollIntoViewIfNeeded();
	    Waits.waitForElementClickable(option);
	    option.click();
	    logger.info("Completed: selectOperatorFromDropdown");
	}

	public static void selectColumnFromDropdown(Page page, String columnName) {
		logger.info("Starting: selectColumnFromDropdown with columnName '{}'", columnName);
		Locator headers = page.locator(COLOUMN_SELECTOR_XPATH);
		Waits.waitForElementClickable(headers);
		headers.click();
		Locator option = page.locator(NOTEBOOK_SELECT_HEADER_XPATH.replace("{selectHeader}", columnName));
		Waits.waitForElementClickable(option);
		option.click();
		logger.info("Completed: selectColumnFromDropdown");
	}

	
	public static void clickOnRuleButton(Page page, String buttonName) {
		logger.info("Starting: clickOnRuleButton with buttonName '{}'", buttonName);
		Locator ruleButton = page.locator(RULE_BUTTON_XPATH.replace("{buttonName}", buttonName)).first();
		Waits.waitForElementClickable(ruleButton);
		ruleButton.click();
		logger.info("Completed: clickOnRuleButton");
	}

	public static void changeOperatorTo(Page page, String operator) {
		logger.info("Starting: changeOperatorTo with operator '{}'", operator);
		Locator andLocator = page.locator("//span[text()='AND']");
		Waits.waitForElementClickable(andLocator);
		andLocator.click();
		Locator newOperator = page.locator(CHANGE_DEFAULT_OPERATOR_XPATH.replace("{operator}", operator));
		Waits.waitForElementClickable(newOperator);
		newOperator.click();
		logger.info("Completed: changeOperatorTo");
	}
	public static void getDefaultOperator(Page page, String operator) {
		logger.info("Starting: getDefaultOperator with operator '{}'", operator);
		Locator andDropdowns = page.locator(CHECK_DEFAULT_OPERATOR_XPATH.replace("{operator}", operator));
		Waits.waitForElementVisible(andDropdowns.first());
		int count = andDropdowns.count();
		for (int i = 0; i < count; i++) {
			Locator dropdown = page
					.locator(CHECK_DEFAULT_OPERATOR_XPATH.replace("{operator}", operator) + "[" + (i + 1) + "]");
			if (!dropdown.isVisible()) {
				logger.error("AND operator dropdown at index {} is not visible", i);
				throw new AssertionError("AND operator dropdown at index " + i + " is not visible");
			}
		}
		logger.info("Completed: getDefaultOperator");
	}

		public static boolean isFilteredDataCorrectForColumns(Page page, List<String> columns, List<String> values,
			String operator) {
		logger.info("Starting: isFilteredDataCorrectForColumns with columns '{}', values '{}', operator '{}'",
				columns, values, operator);
		Locator headerRow = page.locator("table tr").first();
		Waits.waitForElementVisible(headerRow);
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
				logger.error("{} column not found", col);
				throw new AssertionError(col + " column not found");
			}
			colIndices.add(idx);
		}
		List<Locator> allRows = page.locator("table tr").all();
		List<Locator> dataRows = allRows.subList(1, allRows.size());
		boolean result;
		if ("AND".equalsIgnoreCase(operator)) {
			result = true;
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
					result = false;
					break;
				}
			}
		} else if ("OR".equalsIgnoreCase(operator)) {
			result = true;
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
					result = false;
					break;
				}
			}
		} else {
			logger.error("Unsupported operator: {}", operator);
			throw new IllegalArgumentException("Unsupported operator: " + operator);
		}
		logger.info("Completed: isFilteredDataCorrectForColumns");
		return result;
	}

	public static void enterDataLimit(Page page, String dataLimit) {
	    logger.info("Starting: enterDataLimit with dataLimit '{}'", dataLimit);
	    Locator dataLimitField = page.getByPlaceholder("No limit");
	    Waits.waitForElementEnabled(dataLimitField);
	    dataLimitField.fill(dataLimit);
	    logger.info("Completed: enterDataLimit with dataLimit " );
	    }
	
	public static void clickOnRunAllCellButton(Page page) {
		logger.info("Starting: clickOnRunAllCellButton");
		Locator runAllCell = page.getByTitle("Run all cells");
		Waits.waitForElementClickable(runAllCell);
		runAllCell.click();
		page.waitForTimeout(1000);
		logger.info("Completed: clickOnRunAllCellButton");
	}

	public static void selectTransformationOptionDropdown(Page page, String optionName) {
	    logger.info("Starting: selectTransformationOptionDropdown with optionName '{}'", optionName);
	    Locator option = page.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName(optionName));
	    Waits.waitForElementClickable(option);
	    option.click();
	    logger.info("Completed: selectTransformationOptionDropdown");
	}
	
	
	public static void selectColumnForTransformation(Page page, String columnName) {
	    logger.info("Starting: selectColumnFromTableHeader with columnName '{}'", columnName);

	    Locator columnHeader = page.locator(
	            "th[data-slot='table-head']:has-text('" + columnName + "'), " +
	            "th:has-text('" + columnName + "'), " +
	            "[data-slot='table-head']:has-text('" + columnName + "')"
	    ).first();

	    columnHeader.waitFor(new Locator.WaitForOptions()
	            .setState(WaitForSelectorState.VISIBLE)
	            .setTimeout(10000));

	    columnHeader.scrollIntoViewIfNeeded();
	    columnHeader.click();
	    page.waitForTimeout(600);

	    logger.info("Completed: selectColumnFromTableHeader - clicked on '{}'", columnName);
	}

	public static boolean isColumnDataInUppercase(Page page, String columnName) {
		logger.info("Starting: isColumnDataInUppercase with columnName '{}'", columnName);
		Locator table = page.locator(OUTPUT_TABLE).last();
		Waits.waitForElementVisible(table);
		String columnNameText = table.locator("//th").textContent().trim();
		if (columnNameText.equals(columnName)) {
			Locator value = table.locator("//tbody//tr/td");
			int rowCount = value.count();
			for (int i = 0; i < rowCount; i++) {
				String cellText = value.nth(i).textContent().trim();
				if (!cellText.equals(cellText.toUpperCase())) {
					logger.info("Completed: isColumnDataInUppercase - mismatch found");
					return false;
				}
			}
		}
		logger.info("Completed: isColumnDataInUppercase");
		return true;
	}


	public static void enterColumnName(Page page, String columnName) {
	    logger.info("Starting: enterColumnName with columnName '{}'", columnName);
	    Locator columnTextbox = page.locator("label:text-is('Column Name') + input");
	    Waits.waitForElementVisible(columnTextbox);
	    columnTextbox.fill(columnName);
	    logger.info("Completed: enterColumnName");
	}




	public static void selectTransformationValueFromDropdown(Page page, String value, String dropdownName) {
	    logger.info("Starting: selectTransformationValueFromDropdown with value '{}' and dropdownName '{}'", value, dropdownName);    
	    Locator trigger = page.locator("button[role='combobox']")
	            .filter(new Locator.FilterOptions().setHasText(dropdownName))
	            .first();
	    Waits.waitForElementClickable(trigger);
	    trigger.click();
	    Locator option = page.locator("[role='option'], [data-radix-collection-item]")
	            .filter(new Locator.FilterOptions().setHasText(value))
	            .first();
	    Waits.waitForElementVisible(option);
	    option.click();
	    logger.info("Completed: selectTransformationValueFromDropdown");
	}


	public static List<String> getColumnValues(Page page, String columnName) {
		logger.info("Starting: getColumnValues with columnName '{}'", columnName);
		Locator table = page.locator(OUTPUT_TABLE).last();
		Waits.waitForElementVisible(table);
		List<String> headers = table.locator("//th").allInnerTexts();
		int columnIndex = -1;
		for (int i = 0; i < headers.size(); i++) {
			if (headers.get(i).trim().equalsIgnoreCase(columnName.trim())) {
				columnIndex = i + 1;
				break;
			}
		}
		if (columnIndex == -1) {
			logger.error("Column not found: {}", columnName);
			throw new RuntimeException("Column not found: " + columnName);
		}
		List<String> result = table.locator("//tbody//tr/td[" + columnIndex + "]").allInnerTexts();
		logger.info("Completed: getColumnValues");
		return result;
	}


	public static void clickOnIncludeTimeCheckbox(Page page) {
	    logger.info("Starting: clickOnIncludeTimeCheckbox");
	    Locator checkbox = page.getByText("Include time", new Page.GetByTextOptions().setExact(true));
	    Waits.waitForElementClickable(checkbox);
	    checkbox.click();
	    logger.info("Completed: clickOnIncludeTimeCheckbox");
	}
	
	
	public static void selectDataFiltersOptionDropdown(Page page, String optionName) {
	    logger.info("Starting: selectDataFiltersOptionDropdown with optionName '{}'", optionName);
	    page.locator("[role='menu']").first().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(10000));
	    Locator option = page.getByRole(AriaRole.MENUITEM).filter(new Locator.FilterOptions().setHasText(optionName)).first();
	    Waits.waitForElementVisible(option);
	    option.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(10000));
	    option.click();
	    logger.info("Completed: selectDataFiltersOptionDropdown - Selected '{}'", optionName);
	}


	 public static Locator getCurrentCell(Page page) {
	        logger.info("Getting current (active) cell");
	        Locator currentCell = page.locator(
	                "div[class*='border-primary'][class*='border-l-primary'][class*='ring-primary'], " +
	                "div.border-primary.border-l-primary.ring-1"
	        ).first();

	        currentCell.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(10000));
	        logger.info("Current cell is visible");
	        return currentCell;
	    }

	  
	    public static void mouseHoverOnCurrentCell(Page page) {
	        logger.info("Hovering on current cell");
	        Locator currentCell = getCurrentCell(page);
	        currentCell.hover();
	        page.waitForTimeout(500); 
	        logger.info("Hovered successfully on current cell");
	    }
	    
	    public static void mouseHoverOnNotebookHiddenOptionsOnCurrentCell(Page page) {
	        logger.info("Starting: mouseHoverOnNotebookHiddenOptionsOnCurrentCell");
	        Locator currentCell = getCurrentCell(page);  
	        if (!page.getByTestId("data-key-pair").isVisible()) {
	            Locator cellEditor = currentCell.locator(CODE_ENTER_TEXTAREA).first();
	            Waits.waitForElementVisible(cellEditor);
	            CommonUtils.moveMouseToCenterWithMargin(page, cellEditor, 60, 20);
	        } else {
	            page.setViewportSize(1350, 650);
	            Locator dataKeyPair = currentCell.getByTestId("data-key-pairtype").first();
	            dataKeyPair.scrollIntoViewIfNeeded();
	            Waits.waitForElementVisible(dataKeyPair);
	            dataKeyPair.hover();
	            CommonUtils.moveMouseToCenterWithMargin(page, dataKeyPair, 65, 30);
	        }
	        page.waitForTimeout(500);
	        logger.info("Completed: mouseHoverOnNotebookHiddenOptionsOnCurrentCell");
	    }
	    
	    
	    public static void mouseHoverOnNotebookHiddenOptionsOnCurrentQueryBuilderCell(Page page) {
	        logger.info("Starting: mouseHoverOnNotebookHiddenOptionsOnCurrentQueryBuilderCell");
	        Locator currentCell = page.locator(
	            "div[class*='border-primary'][class*='border-l-primary'], " +
	            "div.border-primary.border-l-primary"
	        ).first();

	        Waits.waitForElementVisible(currentCell);
	        currentCell.hover();
	        Locator dataFiltersBtn = page.locator("button[title='Data Filters']").first();
	        Waits.waitForElementVisible(dataFiltersBtn);
	        Waits.waitForElementClickable(dataFiltersBtn);

	        logger.info("Completed: mouseHoverOnNotebookHiddenOptionsOnCurrentQueryBuilderCell");
	    }


	    public static void clickDataFiltersOnCurrentCell(Page page) {
	        logger.info("Starting: Clicking Data Filters on current cell");
	        getCurrentCell(page);
	        Locator btn = page.locator(
	                "button[title*='Data Filter' i], " +
	                "button[title*='Filter' i], " +
	                "button[aria-label*='Filter' i]"
	        ).last();
	        btn.waitFor(new Locator.WaitForOptions()
	                .setState(WaitForSelectorState.VISIBLE)
	                .setTimeout(8000));
	        btn.click();
	        page.waitForTimeout(800);
	        logger.info("Completed: Data Filters clicked successfully");
	    }


	    public static void clickTransformationOnCurrentCell(Page page) {
	        logger.info("Clicking Transformation on current cell");
	        getCurrentCell(page);
	        Locator transformationBtn = page.locator(
	                "button[title*='Transformation' i], " +
	                "button[aria-label*='Transformation' i], " +
	                "[title='Transformation']"
	        ).last();   
	        transformationBtn.waitFor(new Locator.WaitForOptions()
	                .setState(WaitForSelectorState.VISIBLE)
	                .setTimeout(8000));
	        transformationBtn.click();
	        page.waitForTimeout(800);
	        logger.info("Transformation clicked successfully");
	    }

	    

        ////******/////
	    public static void clickOnQueryName(Page page, String queryName) {
			logger.info("Starting: clickOnQueryName with value '{}'", queryName);
			Locator queryTextbox = page.locator(NOTEBOOK_NAME_XPATH.replace("{notebookName}", queryName));
			Waits.waitForElementClickable(queryTextbox);
			queryTextbox.click();
			logger.info("Completed: clickOnQueryName");
		}

		public static void clickOnRunCellButtonDatabase(Page page) {
			logger.info("Starting: clickOnRunCellButtonDatabase");
			Locator block = page.locator(FILTER_SELECT_DATABASE_BLOCK_XPATH);
			if (block.isVisible()) {
				block.hover();
			}
			Locator runCellButton = page.locator(RUN_CELL_OPTION_XPATH).first();
			Waits.waitForElementClickable(runCellButton);
			runCellButton.click();
			Locator checkCircle = page.locator(RUN_CELL_LOADING_ICON_XPATH);
			Waits.waitForElementAttached(checkCircle);
			logger.info("Completed: clickOnRunCellButtonDatabase");
		}

		public static void checkDatabaseQueryOutput(Page page) {
			logger.info("Starting: checkDatabaseQueryOutput");
			Locator outputResult = page.locator(QUERY_CODE_RUN_NULL_OUTPUT_XPATH);
			Waits.waitForElementVisible(outputResult);
			if (!outputResult.isVisible()) {
				logger.error("Database query output is not visible");
				throw new AssertionError("There was an issue generating a preview, output is not visible");
			}
			logger.info("Completed: checkDatabaseQueryOutput");
		}

		public static void checkDatabaseOutput(Page page) {
			logger.info("Starting: checkDatabaseOutput");
			Locator databaseblock = page.locator(RUN_CELL_LOADER_XPATH).last();
			Waits.waitForElementAttached(databaseblock);
			if (!databaseblock.isVisible()) {
				logger.error("Database command not executed successfully");
				throw new AssertionError("Database command not executed successfully");
			}
			logger.info("Completed: checkDatabaseOutput");
		}

		public static void addValueInField(Page page, String fieldName, String value) {
			logger.info("Starting: addValueInField with fieldName '{}' and value '{}'", fieldName, value);
			Locator appFieldLocator = page.locator(ADD_VALUE_IN_FIELD_XPATH.replace("{fieldName}", fieldName));
			Waits.waitForElementVisible(appFieldLocator);
			appFieldLocator.scrollIntoViewIfNeeded();
			if (!appFieldLocator.isVisible()) {
				logger.error("{} field is not visible", fieldName);
				throw new AssertionError(fieldName + " field is not visible");
			} else {
				appFieldLocator.fill(value);
			}
			logger.info("Completed: addValueInField");
		}

		public static void selectValueFromDropdown(Page page, String value, String fieldName) {
			logger.info("Starting: selectValueFromDropdown with value '{}' and fieldName '{}'", value, fieldName);
			Locator appFieldLocator = page.locator(UNIQUE_ROW_ID_FIELD_XPATH).nth(2);
			Waits.waitForElementClickable(appFieldLocator);
			appFieldLocator.click();
			Locator selectValue = page.getByRole(AriaRole.OPTION,
					new Page.GetByRoleOptions().setName(Pattern.compile("^" + value + "$")));
			Waits.waitForElementClickable(selectValue);
			selectValue.click();
			logger.info("Completed: selectValueFromDropdown");
		}

		public static void selectValueFromReadAppDropdown(Page page, String value, String fieldName) {
			logger.info("Starting: selectValueFromReadAppDropdown with value '{}' and fieldName '{}'", value, fieldName);
			Locator progressBar = page.locator(PROGRESS_BAR_READ_IN_FIELD_XPATH);
			Waits.waitForElementHidden(progressBar, 15000);
			Locator appFieldLocator = page.locator(UNIQUE_ROW_ID_FIELD_XPATH).nth(1);
			Waits.waitForElementVisible(appFieldLocator);
			appFieldLocator.scrollIntoViewIfNeeded();
			if (!appFieldLocator.isVisible()) {
				logger.error("{} field is not visible", fieldName);
				throw new AssertionError(fieldName + " field is not visible");
			} else {
				appFieldLocator.click(new Locator.ClickOptions().setForce(true));
				Locator listItem = page.locator(DATA_LIST_ITEM_SELECTOR_XPATH.replace("{value}", value));
				Waits.waitForElementVisible(listItem);
				listItem.click(new Locator.ClickOptions().setForce(true));
			}
			logger.info("Completed: selectValueFromReadAppDropdown");
		}

		public static void checkRecordWithUniqueId(Page page, String uniqueId) {
			logger.info("Starting: checkRecordWithUniqueId with uniqueId '{}'", uniqueId);
			Locator loadingIndicator = page.locator(LOADING_ICON_XPATH);
			Waits.waitForElementHidden(loadingIndicator, 10000);
			Locator recordLocator = page.locator(READ_RECORD_XPATH.replace("{uniqueId}", uniqueId));
			Waits.waitForElementVisible(recordLocator);
			if (!recordLocator.isVisible()) {
				logger.error("Record with Unique ID '{}' is not visible", uniqueId);
				throw new AssertionError("Record with Unique ID '" + uniqueId + "' is not visible");
			}
			logger.info("Completed: checkRecordWithUniqueId");
		}

		public static void clickOnRecordButton(Page page, String buttonName) {
			logger.info("Starting: clickOnRecordButton with buttonName '{}'", buttonName);
			Locator addRecordButton = page.locator(PREVIEW_APP_BUTTON_XPATH.replace("{buttonName}", buttonName)).last();
			Waits.waitForElementVisible(addRecordButton);
			addRecordButton.scrollIntoViewIfNeeded();
			if (!addRecordButton.isVisible()) {
				logger.error("{} Record button is not visible/available", buttonName);
				throw new AssertionError(buttonName + " Record button is not visible/available");
			} else {
				addRecordButton.click();
			}
			logger.info("Completed: clickOnRecordButton");
		}

		public static void modifySqlQuery(Page page, String newQuery) {
			logger.info("Starting: modifySqlQuery with newQuery '{}'", newQuery);
			Locator queryTextArea = page.locator(QUERY_XPATH);
			Waits.waitForElementVisible(queryTextArea);
			if (queryTextArea.isVisible()) {
				queryTextArea.click();
				String textContent = queryTextArea.textContent();
				queryTextArea.press("ArrowDown");
				while (textContent != null && !textContent.isEmpty()) {
					queryTextArea.press("Backspace");
					page.waitForTimeout(50);
					textContent = queryTextArea.textContent();
				}
				page.keyboard().type(newQuery, new Keyboard.TypeOptions().setDelay(200));
			} else {
				logger.error("Query Text Area is not visible");
				throw new RuntimeException("Query Text Area is not visible");
			}
			logger.info("Completed: modifySqlQuery");
		}

		public static void checkSuccessMessage(Page page, String successMessage) {
			logger.info("Starting: checkSuccessMessage with successMessage '{}'", successMessage);
			Locator successMessageLocator = page.getByText("true");
			Waits.waitForElementVisible(successMessageLocator);
			if (!successMessageLocator.isVisible()
					|| !successMessageLocator.textContent().trim().equalsIgnoreCase(successMessage)) {
				logger.error("Success message is not visible");
				throw new AssertionError("Success message is not visible");
			}
			logger.info("Completed: checkSuccessMessage");
		}

		public static void checkNotebookPresence(Page page, String notebookName) {
			logger.info("Starting: checkNotebookPresence with notebookName '{}'", notebookName);
			Locator notebookLocator = page.locator(NOTEBOOK_LIST_XPATH.replace("{NotebookName}", notebookName));
			Waits.waitForElementVisible(notebookLocator);
			if (!notebookLocator.isVisible()) {
				logger.error("Notebook '{}' is not present in the notebook list", notebookName);
				throw new AssertionError("Notebook '" + notebookName + "' is not present in the notebook list");
			}
			logger.info("Completed: checkNotebookPresence");
		}

		public static void SearchForNotebook(Page page, String notebookName) {
			logger.info("Starting: SearchForNotebook with notebookName '{}'", notebookName);
			Locator searchLocator = page.getByPlaceholder(NOTEBOOK_SEARCH_TEXT);
			Waits.waitForElementVisible(searchLocator);
			searchLocator.fill(notebookName);
			Locator notebookLocator = page.getByText(notebookName);
			Waits.waitForElementVisible(notebookLocator);
			if (!notebookLocator.isVisible()) {
				logger.error("Notebook '{}' is not present in the notebook search result list", notebookName);
				throw new AssertionError(
						"Notebook '" + notebookName + "' is not present in the notebook search result list");
			}
			logger.info("Completed: SearchForNotebook");
		}

		public static void duplicateNotebook(Page page, String notebookName) {
			logger.info("Starting: duplicateNotebook with notebookName '{}'", notebookName);
			Locator notebookDuplicateButton = page
					.locator(NOTEBOOK_MENU_DUPLICATE_BUTTON_XPATH.replace("{notebookName}", notebookName));
			Waits.waitForElementVisible(notebookDuplicateButton);
			if (!notebookDuplicateButton.isVisible()) {
				logger.error("Duplicate button is not present in the notebook Menu list");
				throw new AssertionError("Duplicate button is not present in the notebook Menu list");
			}
			notebookDuplicateButton.click();
			Locator confirmDuplicate = page.locator(CONFIRM_DUPLICATE_NOTEBOOK_XPATH);
			Waits.waitForElementClickable(confirmDuplicate);
			confirmDuplicate.click();
			logger.info("Completed: duplicateNotebook");
		}

		public static void deleteNotebook(Page page, String notebookName) {
			logger.info("Starting: deleteNotebook with notebookName '{}'", notebookName);
			Locator notebookDeleteButton = page
					.locator(NOTEBOOK_MENU_DELETE_BUTTON_XPATH.replace("{notebookName}", notebookName)).first();
			Waits.waitForElementVisible(notebookDeleteButton);
			if (!notebookDeleteButton.isVisible()) {
				logger.error("Delete button is not present in the notebook Menu list");
				throw new AssertionError("Delete button is not present in the notebook Menu list");
			}
			notebookDeleteButton.click();
			Locator deleteDialogBoxMessage = page.locator(DELETE_DIALOG_BOX_XPATH);
			Waits.waitForElementVisible(deleteDialogBoxMessage);
			String dialogBoxText = deleteDialogBoxMessage.textContent().trim();
			if (dialogBoxText.isEmpty() || !dialogBoxText.contains(notebookName)) {
				logger.error("Delete confirmation dialog box message is not as expected");
				throw new AssertionError("Delete confirmation dialog box message is not as expected");
			}
			Locator deleteButton = page.locator(DELETE_DIALOG_BOX_DELETE_BUTTON_XPATH);
			Waits.waitForElementVisible(deleteButton);
			if (!deleteButton.isVisible()) {
				logger.error("Delete button is not present in the delete confirmation dialog box");
				throw new AssertionError("Delete button is not present in the delete confirmation dialog box");
			}
			deleteButton.click();
			logger.info("Completed: deleteNotebook");
		}

		public static void runCurrentCell(Page page) {
	        logger.info("Running current cell (this cell and below)");
	        Locator currentCell = getCurrentCell(page);

	        Locator runBtn = currentCell.locator(
	                "button[title='Run this cell and below'], " +
	                "button[title*='Run this cell']"
	        ).first();

	        runBtn.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(8000));
	        runBtn.click();
	        page.waitForTimeout(1000);
	        logger.info("Current cell run triggered");
	    }


	    public static void runCurrentCellAndAbove(Page page) {
	        logger.info("Running current cell + above");
	        Locator currentCell = getCurrentCell(page);

	        Locator runBtn = currentCell.locator(
	                "button[title='Run the cells above and this cell']"
	        ).first();

	        runBtn.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(8000));
	        runBtn.click();
	        page.waitForTimeout(1000);
	        logger.info("Run current cell + above triggered");
	    }


	    public static void duplicateCurrentCell(Page page) {
	        logger.info("Duplicating current cell");
	        Locator currentCell = getCurrentCell(page);

	        Locator btn = currentCell.locator("button[title='Duplicate cell']");
	        btn.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(8000));
	        btn.click();
	        page.waitForTimeout(800);
	        logger.info("Current cell duplicated");
	    }


	    public static void deleteCurrentCell(Page page) {
	        logger.info("Deleting current cell");
	        Locator currentCell = getCurrentCell(page);

	        Locator btn = currentCell.locator("button[title='Delete cell']");
	        btn.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(8000));
	        btn.click();
	        page.waitForTimeout(800);
	        logger.info("Current cell deleted");
	    }


	    public static void renameCurrentCell(Page page) {
	        logger.info("Opening rename for current cell");
	        Locator currentCell = getCurrentCell(page);

	        Locator btn = currentCell.locator("button[title='Rename variable']");
	        btn.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(8000));
	        btn.click();
	        page.waitForTimeout(500);
	        logger.info("Rename opened");
	    }

	    public static void copyCurrentCellVariable(Page page) {
	        logger.info("Copying variable name of current cell");
	        Locator currentCell = getCurrentCell(page);

	        Locator btn = currentCell.locator("button[title^='Copy']");
	        btn.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(8000));
	        btn.click();
	        page.waitForTimeout(500);
	        logger.info("Variable name copied");
	    }

	    public static void clickShowColumnsOnCurrentCell(Page page) {
	        logger.info("Clicking 'Show columns' on current cell");
	        Locator currentCell = getCurrentCell(page);

	        Locator btn = currentCell.locator("button:has-text('Show columns')");
	        btn.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(8000));
	        btn.click();
	        page.waitForTimeout(800);
	        logger.info("'Show columns' clicked");
	    }

	    public static String getCurrentCellVariableName(Page page) {
	        logger.info("Getting variable name of current cell");
	        Locator currentCell = getCurrentCell(page);

	        Locator input = currentCell.locator("input[title='Set frame variable name']");
	        input.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(8000));
	        
	        String value = input.inputValue();
	        logger.info("Current cell variable name = {}", value);
	        return value;
	    }

	    public static String getCurrentCellType(Page page) {
	        logger.info("Getting type of current cell");
	        Locator currentCell = getCurrentCell(page);

	        Locator typeSpan = currentCell.locator("span.uppercase.tracking-wider");
	        typeSpan.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(8000));
	        
	        String type = typeSpan.innerText().trim();
	        logger.info("Current cell type = {}", type);
	        return type;
	    }
	
		public static void mouseHoverAboveNotebookHiddenOptions(Page page) {
			logger.info("Starting: mouseHoverAboveNotebookHiddenOptions");
			Locator dataKeyPair = page.getByTestId("data-key-pair");
			Waits.waitForElementVisible(dataKeyPair);
			Locator cell = page.locator(NOTEBOOK_MOUSE_HOVER_ABOVE_THE_CELL_XPATH);
			Waits.waitForElementVisible(cell);
			CommonUtils.moveMouseToCenterWithMargin(page, cell, -70, 20);
			logger.info("Completed: mouseHoverAboveNotebookHiddenOptions");
		}

		public static void mouseHoverOnBlankCell(Page page) {
			logger.info("Starting: mouseHoverOnBlankCell");
			Locator cell = page.locator(CODE_ENTER_TEXTAREA).first();
			Waits.waitForElementVisible(cell);
			CommonUtils.moveMouseToCenterWithMargin(page, cell, 60, 20);
			logger.info("Completed: mouseHoverOnBlankCell");
		}
		
		
		
		public static void clickOnRunAllButton(Page page) {
			logger.info("Starting: clickOnRunAllButton");
			Locator runAllButton = page.getByTestId("ArrowDownwardIcon");
			Waits.waitForElementClickable(runAllButton);
			runAllButton.click();
			logger.info("Completed: clickOnRunAllButton");
		}

		public static String getCodeOutput(Page page, String codeOutput) {
			logger.info("Starting: getCodeOutput with codeOutput '{}'", codeOutput);
			Locator outputResult = page.locator(QUERY_CODE_RUN_OUTPUT_XPATH.replace("{codeOutput}", codeOutput));
			Waits.waitForElementVisible(outputResult);
			String result = outputResult.textContent().trim();
			logger.info("Completed: getCodeOutput");
			return result;
		}
		
		
		public static List<String> checkColumnNamesOnUI(Page page) {
			logger.info("Starting: checkColumnNamesOnUI");
			Locator columnNames = page.locator(LIST_OF_COLUMN_NAMES_XPATH);
			Waits.waitForElementVisible(columnNames.first());
			List<String> result = columnNames.allTextContents();
			logger.info("Completed: checkColumnNamesOnUI");
			return result;
		}
		
		public static void clickOnImportDropdown(Page page) {
			logger.info("Starting: clickOnImportDropdown");
			Locator selectDatabaseDropdown = page.locator(SELECT_DATABASE_DROPDOWN_XPATH);
			Waits.waitForElementClickable(selectDatabaseDropdown);
			selectDatabaseDropdown.click();
			logger.info("Completed: clickOnImportDropdown");
		}
}
