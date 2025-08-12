package aicore.steps.app;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;

import aicore.hooks.SetupHooks;
import aicore.pages.app.NotebookPage;
import aicore.utils.CommonUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class NotebookCreationAndExecutionSteps {

	private NotebookPage notebookPage;
	protected String timestamp;
	protected static String frameID;

	public NotebookCreationAndExecutionSteps() {
		timestamp = CommonUtils.getTimeStampName();
		notebookPage = new NotebookPage(SetupHooks.getPage(), timestamp);
	}

	@When("User clicks on Notebook")
	public void user_clicks_on_notebook() {
		notebookPage.clickOnNotebooksOption();
	}

	@And("User clicks on Create new notebook")
	public void user_clicks_on_create_new_notebook() {
		notebookPage.clickOnCreateNewNotebook();
	}

	@And("User enters New Query name as {string}")
	public void user_enters_new_query_name_as(String queryName) {
		notebookPage.enterQueryName(queryName);
	}

	@When("User clicks on query Submit button")
	public void user_clicks_on_query_submit_button() {
		notebookPage.clickOnQuerySubmitButton();
	}

	@When("User enters code as {string}")
	public void user_enters_code_as(String code) {
		notebookPage.enterCodeInQuery(code);
	}

	@When("User clicks on Run this cell and below icon")
	public void user_clicks_on_run_this_cell_and_below_icon() {
		notebookPage.clickOnRunAllButton();
	}

	@Then("User can see code output as {string}")
	public void user_can_see_code_output_as(String expectedCodeOutput) {
		String actualOutput = notebookPage.getCodeOutput(expectedCodeOutput);
		Assertions.assertEquals(expectedCodeOutput, actualOutput,
				"Mismatch between the expected and actual code output");
	}

	@And("User mouse hover below the existing cell")
	public void user_mouse_hover_below_the_existing_cell() {
		notebookPage.mouseHoverOnNotebookHiddenOptions();
	}

	@And("User selects {string} from the hidden options")
	public void user_selects_from_the_hidden_options(String optionName) {
		notebookPage.clickOnHiddenNotebookOption(optionName);
	}

	@And("User selects {string} from the data import options")
	public void user_selects_from_the_data_import_options(String optionName) {
		notebookPage.selectHiddenOptionDropdown(optionName);
	}

	@And("User selects {string} from the dropdown list")
	public void user_selects_from_the_dropdown_list(String databaseName) {
		notebookPage.selectDatabaseFromDropdown(databaseName);
	}

	@And("User selects all columns from database")
	public void user_selects_all_columns_from_database() {
		notebookPage.selectAllColumns();
	}

	@And("User clicks on data Import button")
	public void user_clicks_on_data_import_button() {
		notebookPage.clickOnImportButton();
	}

	@And("User deletes the previous cell")
	public void user_deletes_the_previous_cell() {
		notebookPage.deleteFirstCell();
	}

	@And("User selects {string} database from the dropdown")
	public void user_selects_database_from_the_dropdown(String Database) {
		notebookPage.selectDatabaseType(Database);
	}

	@When("User clicks on Run cell button")
	public void user_clicks_on_run_cell_button() throws InterruptedException {
		notebookPage.clickOnRunCellButton();
	}

	@And("User fetch the frame id")
	public void user_fetch_the_frame_id() {
		frameID = notebookPage.getFrameID();
	}

	@Then("User can see {string} columns under the fields column")
	public void user_can_see_columns_under_the_fields_column(String columnNames) {
		List<String> expectedColumns = Arrays.asList(columnNames.split(", "));
		List<String> uiColumns = notebookPage.checkColumnNamesOnUI();
		Assertions.assertEquals(expectedColumns, uiColumns, "columns are not matching");
	}

	@Then("User can see header names as {string}")
	public void user_can_see_header_names_as(String headerNames) {
		List<String> expectedHeaderNames = Arrays.asList(headerNames.split(", "));
		List<String> actualHeaderNames = notebookPage.getNotebookOutputTableHeader();
		Assertions.assertEquals(expectedHeaderNames, actualHeaderNames, "Headers are not matching");
	}

	@Then("User can see total {string} rows")
	public void user_can_see_total_rows(String rowsCount) {
		int actualRowsCount = notebookPage.getTotalRowsFromPreviewCaption();
		int expectedRowsCount = Integer.parseInt(rowsCount);
		Assertions.assertEquals(expectedRowsCount, actualRowsCount, "Rows count are not correct");
	}

	@Then("User can see the {string} column have unique values")
	public void user_can_see_the_column_have_unique_values(String headerName) {
		boolean isColumnUnique = notebookPage.isColumnUniqueByHeader(headerName);
		Assertions.assertTrue(isColumnUnique, headerName + " have duplicate values");
	}

	@Then("User can see name as frame id in JSON")
	public void user_can_see_name_as_frame_id_in_json() {
		String jsonFrameId = notebookPage.validateJsonFieldValue(frameID);
		String cleanedActualFrameId = jsonFrameId.replaceAll("^\"|\"$", "");
		Assertions.assertEquals(frameID, cleanedActualFrameId, "Frame Id not matching");
	}

	@When("User selects type as {string}")
	public void user_selects_type_as(String type) {
		notebookPage.selectTypeFromDropdown(type);
	}

	@Then("User can see type as {string} for {string} in JSON")
	public void user_can_see_type_as_for_in_json(String typeFieldValue, String type) {
		notebookPage.validateJsonFieldValue(typeFieldValue);
	}

	@And("User Sees Python as the default language")
	public void user_sees_python_as_the_default_language() {
		notebookPage.checkPythonAsDefault();
	}

	@And("User changes the language to {string}")
	public void user_changes_the_language_to(String language) {
		notebookPage.changeToLanguage(language);
	}

	@Then("User hovers and clicks on the cell")
	public void user_hovers_and_clicks_on_the_cell() {
		notebookPage.mouseHoverOnNotebookHiddenOptions();
		notebookPage.hoverAndClickOnCell();
	}

	@Then("User can see Pixel output as {string}")
	public void user_can_see_pixel_output_as(String Output) {
		notebookPage.getPixelOutput(Output);
	}

	@Then("User can see Python output as {string}")
	public void user_can_see_python_output_as(String Output) {
		notebookPage.getPythonOutput(Output);
	}

	@Given("User Sees the Notebook {string} in the notebook list")
	public void user_Sees_The_Notebook_In_The_Notebook_List(String notebookName) {
		notebookPage.verifyNotebookIsPresentInList(notebookName);
	}

	@When("User clicks on the Notebook {string}")
	public void user_Clicks_On_The_Notebook(String notebookName) {
		notebookPage.clickOnNotebook(notebookName);
	}

	@And("User selects {string} from the data filter options")
	public void user_selects_from_the_data_filters_options(String optionName) {
		notebookPage.selectHiddenOptionDropdown(optionName);
	}

	@When("User writes the query {string}")
	public void user_writes_the_query(String query) {
		notebookPage.writeQuery(query);
	}

	@Then("User sees the output of the executed query where Age is {string} and Bloodpressure is {string}")
	public void user_sees_the_output_of_the_executed_query_where_age_is_and_bloodpressure_is(String age, String bp) {
		boolean flag = notebookPage.validateQuery(age, bp);
		Assertions.assertTrue(flag, "age and bp fields is not visible");
	}

	@And("User clicks on the {string} option")
	public void user_clicks_on_the_option(String buttonName) {
		notebookPage.clickOnRuleButton(buttonName);
	}

	@And("User selects {string} from the column dropdown")
	public void user_selects_from_the_column_dropdown(String columnName) {
		notebookPage.selectColumnFromDropdown(columnName);
	}

	@And("User selects {string} from the operator dropdown")
	public void user_selects_from_the_operator_dropdown(String operator) {
		notebookPage.selectOperatorFromDropdown(operator);
	}

	@And("User enters {string} from the value input")
	public void user_enters_from_the_value_input(String value) {
		notebookPage.enterValueInInput(value);
	}

	@Then("User can see the filtered data with {string} equals {string}")
	public void user_can_see_the_filtered_data_with_column_equals_value(String column, String value) {
		boolean isFilteredDataCorrect = notebookPage.isFilteredDataCorrect(column, value);
		Assertions.assertTrue(isFilteredDataCorrect,
				"Filtered data is not correct for column: " + column + " with value: " + value);
	}

	@Then("User changes the operator to {string}")
	public void user_changes_the_operator_to(String operator) {
		notebookPage.changeOperatorTo(operator);
	}

	@Then("User sees {string} operator by default between the rules")
	public void User_sees_operator_by_default_between_the_rules(String operator) {

		notebookPage.getDefaultOperator(operator);
	}

	@Then("User can see the filtered data with {string} operator for columns {string} and values {string}")
	public void user_can_see_filtered_data_with_operator(String operator, String columnsCsv, String valuesCsv) {
		List<String> columns = Arrays.stream(columnsCsv.split(",")).map(String::trim).collect(Collectors.toList());
		List<String> values = Arrays.stream(valuesCsv.split(",")).map(String::trim).collect(Collectors.toList());
		boolean result;
		result = notebookPage.isFilteredDataCorrectForColumns(columns, values, operator);
		Assertions.assertTrue(result, "Filtered data does not match for columns: " + columns + " with values: " + values
				+ " using operator: " + operator);
	}

}
