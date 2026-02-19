package aicore.steps;

import org.junit.jupiter.api.Assertions;

import aicore.hooks.SetupHooks;
import aicore.pages.AddDatabaseFileUploadPage;
import io.cucumber.java.en.And;

public class AddDatabaseFileUploadSteps {

	private AddDatabaseFileUploadPage addDatabaseCSVFileUploadPage;
	private String timestamp;

	public AddDatabaseFileUploadSteps() {
		addDatabaseCSVFileUploadPage = new AddDatabaseFileUploadPage(SetupHooks.getPage());
		timestamp = SetupHooks.getTimestamp();
	}

	@And("User clicks on the {string} tab from options")
	public void user_clicks_on_the_tab_from_options(String tabName) {
		addDatabaseCSVFileUploadPage.selectTab(tabName);
	}

	@And("User selects {string} file type")
	public void user_selects_file_type(String fileType) {
		addDatabaseCSVFileUploadPage.selectFileType(fileType);
	}

	@And("User enters {string} as Database Name")
	public void user_enters_database_name(String dbName) {
		addDatabaseCSVFileUploadPage.enterDatabaseName(dbName + "_" + timestamp);
	}

	@And("User selects {string} as database type")
	public void user_selects_database_type(String dbType) {
		addDatabaseCSVFileUploadPage.selectDatabaseType(dbType);
	}

	@And("User selects {string} as Metamodel type")
	public void user_selects_metamodel_type(String metaModelType) {
		addDatabaseCSVFileUploadPage.selectMetamodelType(metaModelType);
	}

	@And("User sees the colunm names with edit button and delete button")
	public void user_checks_the_columns_are_editable() {
		addDatabaseCSVFileUploadPage.checkColumnsAreEditable();
	}

	@And("User verifies the {string} table name is displayed")
	public void user_verifies_the_table_name_is_displayed(String tableName) {
		boolean tableNameVisible = addDatabaseCSVFileUploadPage.verifyTableName(tableName);
		Assertions.assertTrue(tableNameVisible, "Table name " + tableName + " is not visible");
	}

	@And("User verifies the full screen button is enabled")
	public void user_verifies_the_full_screen_button_is_enabled() {
		addDatabaseCSVFileUploadPage.verifyFullScreenBtn();
	}

	@And("User clicks on the select table button and verifies the table is selected")
	public void user_clicks_on_the_select_table_button_and_verifies_the_table_is_selected() {
		addDatabaseCSVFileUploadPage.verifySelectTableBtn();
	}

	@And("User reset button is enabled")
	public void user_reset_button_is_enabled() {
		addDatabaseCSVFileUploadPage.verifyResetbtn();
	}

	@And("User clicks on create relationship button and creates relationship between {string} and {string} tables")
	public void user_clicks_on_create_relationship_button_and_creates_relationship_between_and_tables(String table1,
			String table2) {
		addDatabaseCSVFileUploadPage.verifyCreateRealtionshipBtn(table1, table2);
	}

	@And("User verifies save button is enabled")
	public void user_verifies_save_button_is_enabled() {
		addDatabaseCSVFileUploadPage.verifySaveBtn();
	}

	@And("User verifies cancel button is enabled")
	public void user_verifies_cancel_button_is_enabled() {
		addDatabaseCSVFileUploadPage.verifyCancelBtn();
	}

	@And("User clicks on Add button for Create Connection")
	public void user_cliks_on_add_button_for_create_connection() {
		addDatabaseCSVFileUploadPage.verifyAddBtnForCreateConnection();
	}

	@And("User clicks on Save button for Create Relationship")
	public void user_clicks_on_save_button_for_create_relationship() {
		addDatabaseCSVFileUploadPage.verifySaveBtnForCreateRelationship();
	}
}