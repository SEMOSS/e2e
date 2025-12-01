package aicore.pages.app;

import java.util.List;

import com.microsoft.playwright.Page;

import aicore.utils.page.app.NotebookPageUtils;

public class NotebookPage {
	private Page page;
	private String timestamp;

	public NotebookPage(Page page, String timestamp) {
		this.page = page;
		this.timestamp = timestamp;
	}

	public void clickOnNotebooksOption() {
		NotebookPageUtils.clickOnNotebooksOption(page);
	}

	public void clickOnCreateNewNotebook() {
		NotebookPageUtils.clickOnCreateNewNotebook(page);
	}

	public void enterQueryName(String queryName) {
		NotebookPageUtils.enterQueryName(page, queryName);
	}

	public void clickOnQueryName(String queryName) {
		NotebookPageUtils.clickOnQueryName(page, queryName);
	}

	public void clickOnRunCellButtonDatabase() {
		NotebookPageUtils.clickOnRunCellButtonDatabase(page);
	}

	public void checkDatabaseQueryOutput() {
		NotebookPageUtils.checkDatabaseQueryOutput(page);
	}

	public void checkDatabaseOutput() {
		NotebookPageUtils.checkDatabaseOutput(page);
	}

	public void modifySqlQuery(String newQuery) {
		NotebookPageUtils.modifySqlQuery(page, newQuery);
	}

	public void addValueInField(String fieldName, String value) {
		NotebookPageUtils.addValueInField(page, fieldName, value);
	}

	public void selectValueFromDropdown(String value, String fieldName) {
		NotebookPageUtils.selectValueFromDropdown(page, value, fieldName);
	}

	public void selectValueFromReadAppDropdown(String value, String fieldName) {
		NotebookPageUtils.selectValueFromReadAppDropdown(page, value, fieldName);
	}

	public void checkRecordWithUniqueId(String uniqueId) {
		NotebookPageUtils.checkRecordWithUniqueId(page, uniqueId);
	}

	public void clickOnRecordButton(String buttonName) {
		NotebookPageUtils.clickOnRecordButton(page, buttonName);
	}

	public void checkSuccessMessage(String successMessage) {
		NotebookPageUtils.checkSuccessMessage(page, successMessage);
	}

	public void checkNotebookPresence(String notebookName) {
		NotebookPageUtils.checkNotebookPresence(page, notebookName);
	}

	public void SearchForNotebook(String notebookName) {
		NotebookPageUtils.SearchForNotebook(page, notebookName);
	}

	public void duplicateNotebook(String notebookName) {
		NotebookPageUtils.duplicateNotebook(page, notebookName);
	}

	public void deleteNotebook(String notebookName) {
		NotebookPageUtils.deleteNotebook(page, notebookName);
	}

	public void clickOnQuerySubmitButton() {
		NotebookPageUtils.clickOnQuerySubmitButton(page);
	}

	public void enterCodeInQuery(String code) {
		NotebookPageUtils.enterCodeInQuery(page, code);
	}

	public void clickOnRunAllButton() {
		NotebookPageUtils.clickOnRunAllButton(page);
	}

	public String getCodeOutput(String codeOutput) {
		return NotebookPageUtils.getCodeOutput(page, codeOutput);
	}

	public void mouseHoverOnNotebookHiddenOptions() {
		NotebookPageUtils.mouseHoverOnNotebookHiddenOptions(page);
	}

	public void clickOnHiddenNotebookOption(String optionName) {
		NotebookPageUtils.clickOnHiddenNotebookOption(page, optionName);
	}

	public void selectHiddenOptionDropdown(String optionName) {
		NotebookPageUtils.selectHiddenOptionDropdown(page, optionName);
	}

	public void selectDatabaseFromDropdown(String databaseName) {
		NotebookPageUtils.selectDatabaseFromDropdown(page, databaseName);
	}

	public void selectAllColumns() {
		NotebookPageUtils.selectAllColumns(page);
	}

	public void clickOnImportButton() {
		NotebookPageUtils.clickOnImportButton(page);
	}

	public void deleteFirstCell() {
		NotebookPageUtils.deleteFirstCell(page);
	}

	public void selectDatabaseType(String databaseName) {
		NotebookPageUtils.selectDatabaseType(page, databaseName);
	}

	public void clickOnRunCellButton() {
		NotebookPageUtils.clickOnRunCellButton(page);
	}

	public String getFrameID() {
		return NotebookPageUtils.getFrameID(page);
	}

	public List<String> checkColumnNamesOnUI() {
		return NotebookPageUtils.checkColumnNamesOnUI(page);
	}

	public List<String> getNotebookOutputTableHeader() {
		return NotebookPageUtils.getNotebookOutputTableHeader(page);
	}

	public int getTotalRowsFromPreviewCaption() {
		return NotebookPageUtils.getTotalRowsFromPreviewCaption(page);
	}

	public boolean isColumnUniqueByHeader(String headerName) {
		return NotebookPageUtils.isColumnUniqueByHeader(page, headerName);
	}

	public String validateJsonFieldValue(String frameId) {
		return NotebookPageUtils.validateJsonFieldValue(page, frameId);
	}

	public void selectTypeFromDropdown(String type) {
		NotebookPageUtils.selectTypeFromDropdown(page, type);
	}

	public void hoverAndClickOnCell() {
		NotebookPageUtils.hoverAndClickOnCell(page);
	}

	public void checkPythonAsDefault() {
		NotebookPageUtils.checkPythonAsDefaultLanguage(page);
	}

	public void changeToLanguage(String language) {
		NotebookPageUtils.changeToLanguage(page, language);
	}

	public void getPixelOutput(String output) {
		NotebookPageUtils.getPixelOutput(page, output);
	}

	public void getPythonOutput(String output) {
		NotebookPageUtils.getPythonOutput(page, output);
	}

	public void clickOnNotebook(String notebookName) {
		NotebookPageUtils.clickOnNotebook(page, notebookName);
	}

	public void verifyNotebookIsPresentInList(String notebookName) {
		NotebookPageUtils.verifyNotebookIsPresentInList(page, notebookName);
	}

	public void writeQuery(String query) {
		NotebookPageUtils.writeQuery(page, query);
	}

	public boolean validateQuery(String age, String bp) {
		return NotebookPageUtils.validateQuery(page, age, bp);
	}

	public void clickOnRuleButton(String buttonName) {
		NotebookPageUtils.clickOnRuleButton(page, buttonName);
	}

	public void selectColumnFromDropdown(String columnName) {
		NotebookPageUtils.selectColumnFromDropdown(page, columnName);
	}

	public void selectOperatorFromDropdown(String operator) {
		NotebookPageUtils.selectOperatorFromDropdown(page, operator);
	}

	public void enterValueInInput(String value) {
		NotebookPageUtils.enterValueInInput(page, value);
	}

	public boolean isFilteredDataCorrect(String column, String value) {
		return NotebookPageUtils.isFilteredDataCorrect(page, column, value);
	}

	public void changeOperatorTo(String operator) {
		NotebookPageUtils.changeOperatorTo(page, operator);
	}

	public void getDefaultOperator(String operator) {
		NotebookPageUtils.getDefaultOperator(page, operator);
	}

	public boolean isFilteredDataCorrectForColumns(List<String> columns, List<String> values, String operator) {
		return NotebookPageUtils.isFilteredDataCorrectForColumns(page, columns, values, operator);
	}

	public void enterDataLimit(String dataLimit) {
		NotebookPageUtils.enterDataLimit(page, dataLimit);
	}

	public void clickOnRunAllCellButton() {
		NotebookPageUtils.clickOnRunAllCellButton(page);
	}

	public void selectTransformationOptionDropdown(String optionName) {
		NotebookPageUtils.selectTransformationOptionDropdown(page, optionName);
	}

	public void enterColumnName(String columnName) {
		NotebookPageUtils.enterColumnName(page, columnName);
	}

	public List<String> getColumnValues(String columnName) {
		return NotebookPageUtils.getColumnValues(page, columnName);
	}

	public void clickOnIncludeTimeCheckbox() {
		NotebookPageUtils.clickOnIncludeTimeCheckbox(page);
	}
}
