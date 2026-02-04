package aicore.steps;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;

import com.microsoft.playwright.Page;

import aicore.hooks.SetupHooks;
import aicore.pages.AddDatabasePage;
import aicore.pages.HomePage;
import aicore.pages.ViewCatalogPage;
import aicore.pages.ViewUsagePage;
import aicore.utils.AICorePageUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddDatabaseSteps extends AbstractAddCatalogBase {

	private AddDatabasePage addDatabaseToCatalogPage;
	private HomePage homePage;
	private ViewUsagePage viewUsagePage;
	private ViewCatalogPage viewCatalogPage;
	private static String timestamp;

	public AddDatabaseSteps() {
		homePage = new HomePage(SetupHooks.getPage());
		addDatabaseToCatalogPage = new AddDatabasePage(SetupHooks.getPage());
		viewUsagePage = new ViewUsagePage(SetupHooks.getPage());
		viewCatalogPage = new ViewCatalogPage(SetupHooks.getPage());
		timestamp = SetupHooks.getTimestamp();
	}

	@Given("User opens Main Menu")
	public void user_opens_main_menu() {
		homePage.openMainMenu();
	}

	@Given("User closes Main Menu")
	public void user_closes_main_menu() {
		homePage.closeMainMenu();
	}

	@Given("User clicks on Open Database")
	public void user_clicks_to_open_database() {
		homePage.clickOnOpenDatabase();
	}

	@When("User clicks on Add Database")
	public void user_clicks_on_add_database() {
		addDatabaseToCatalogPage.clickOnAddDatabaseButton();
	}

	@And("User enters {string} as Catalog Name")
	public void user_enters_catalog_name(String catalogName) {
		addDatabaseToCatalogPage.enterCatalogName(catalogName + timestamp);
	}

	@And("User Upload {string} as Host Name")
	public void user_upload_as_host_name(String fileName) {
		addDatabaseToCatalogPage.enterHostName(fileName);
	}

	@And("User clear the Port Number")
	public void user_clear_the_port_number() {
		addDatabaseToCatalogPage.clearPortNumber();
	}

	@And("User Upload {string} as Schema Name")
	public void user_upload_as_schema_name(String schemaName) {
		addDatabaseToCatalogPage.enterSchemaName(schemaName);
	}

	@And("User add {string} as Username")
	public void user_add_as_username(String userName) {
		addDatabaseToCatalogPage.addUserName(userName);
	}

	@And("User add {string} as JDBC URL for {string} database")
	public void user_add_as_jdbc_url_for_database(String jdbcUrl, String dbType) {
		addDatabaseToCatalogPage.addJDBCUrl(jdbcUrl, dbType);
	}

	@When("User clicks on Apply button")
	public void user_clicks_on_apply_button() {
		addDatabaseToCatalogPage.clickOnApplyButton();
	}

	@And("User clicks on Import database button")
	public void user_clicks_on_import_database_button() {
		addDatabaseToCatalogPage.clickOnImportDatabaseButton();
	}

	@Then("User selects database {string} from connection types")
	public void user_selects_database_from_connection_types(String dbType) {
		addDatabaseToCatalogPage.selectDatabaseFromConnectionTypes(dbType);
	}

	@Then("User can see form sections with fields:")
	public void user_can_see_form_sections_with_fields(DataTable DBTable) {
		List<Map<String, String>> rows = DBTable.asMaps(String.class, String.class);
		for (Map<String, String> row : rows) {
			String sectionName = row.get("SECTION_NAME");
			String[] fields = row.get("FIELDS").split(", ");
			for (String field : fields) {
				boolean isFieldVisible = addDatabaseToCatalogPage.verifyFieldUnderSection(sectionName, field);
				Assertions.assertTrue(isFieldVisible, field + " is not visible under " + sectionName + " section");
			}
		}
	}

	@Then("User can see database mandatory fields")
	public void user_can_see_database_mandatory_fields(DataTable DBTable) {
		String singleCell = DBTable.cells().get(0).get(0);
		String[] fields = singleCell.split(", ");
		for (String field : fields) {
			boolean isFieldMandatory = addDatabaseToCatalogPage.isDBFieldMandatory(field);
			Assertions.assertTrue(isFieldMandatory, field + " is not mandatory field");
		}
	}

	@Then("User can see the database title as {string}")
	public void User_can_see_the_database_title_as(String dbName) {
		boolean isTitleVisible = addDatabaseToCatalogPage.verifyDatabaseTitle(dbName + timestamp);
		Assertions.assertTrue(isTitleVisible, "Database title is not visible");
	}

	@Given("User clicks on the database name {string} in  database catalog")
	public void User_clicks_on_the_database_name_in_database_catalog(String dbName) {
		addDatabaseToCatalogPage.clickOnDatabaseNameInCatalog(dbName + timestamp);
	}

	@Given("User sees the database name {string} in database catalog")
	public void User_sees_the_database_name_in_database_catalog(String dbName) {
		String databaseNameInCatalog = addDatabaseToCatalogPage.verifyDatabaseNameInCatalog(dbName + timestamp);
		boolean databaseNameFlag = databaseNameInCatalog.contains(dbName + timestamp);
		Assertions.assertTrue(databaseNameFlag, "Database name is not visible in the database catalog");
	}

	@Then("User selects database {string}")
	public void user_selects_database(String dbType) {
		addDatabaseToCatalogPage.selectDatabaseType(dbType);
	}

	@And("User clicks on Next button for Create Database")
	public void user_clicks_on_next_button_for_create_database() {
		addDatabaseToCatalogPage.clickOnNextButtonForCreateDatabase();
	}

	@And("User sees the database name {string} in the database catalog")
	public void user_sees_the_database_name_in_the_database_catalog(String dbName) {
		String databaseNameInCatalog = addDatabaseToCatalogPage.verifyDatabaseNameInCatalog(dbName);
		boolean databaseNameFlag = databaseNameInCatalog.contains(dbName);
		Assertions.assertTrue(databaseNameFlag, "Database name is not visible in the database catalog");
	}

	@And("User clicks on the database name {string} in the database catalog")
	public void user_clicks_the_database_name_in_the_database_catalog(String dbName) {
		addDatabaseToCatalogPage.clickOnDatabaseNameInCatalog(dbName);
	}

	@When("User clicks on Usage tab for Database")
	public void user_clicks_on_usage_tab_for_database() {
		viewUsagePage.clickOnUsageTab();
	}

	@Then("User sees an example of {string} with example code for Database")
	public void user_sees_an_example_of_with_example_code_for_database(String usageType) {
		viewUsagePage.verifyExample(usageType);
	}

	@Then("User should see Search bar to filter database options")
	public void user_should_see_search_bar_to_filter_database_options() {
		validateSearchBar(addDatabaseToCatalogPage);
	}

	@Then("User should see the following {string} options with icons on the page")
	public void user_should_see_the_following_options_with_icons_on_the_page(String catalog, DataTable dataTable) {
		final String GROUP_NAME = "GROUP";
		final String DATABASE_OPTION_NAMES = "DATABASE_OPTIONS";
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
		validateOptionsWithIcon(catalog, GROUP_NAME, DATABASE_OPTION_NAMES, rows, addDatabaseToCatalogPage);
	}

	@When("User clicks on Copy ID option of {string} database")
	public void user_clicks_on_copy_id_option_of_database(String dbName) {
		addDatabaseToCatalogPage.clickOnCopyID(dbName);
	}

	@Then("User can see a copy success toast message as {string}")
	public void user_can_see_a_copy_success_toast_message_as(String expectedToastMessage) {
		String actualToastMessage = addDatabaseToCatalogPage.verifyCopyIdSuccessToastMessage(expectedToastMessage);
		Assertions.assertEquals(expectedToastMessage, actualToastMessage, "Toast message is incorrect");
	}

	@Then("User sees the database name as {string}")
	public void user_sees_the_database_name_as(String catalogName) {
		boolean flag = viewCatalogPage.verifyCatalogName(catalogName);
		Assertions.assertTrue(flag, "database name is not visible");
	}

	@Then("User clicks on {string} in the database catalog")
	public void user_clicks_on_in_the_database_catalog(String databaseName) {
		addDatabaseToCatalogPage.clickDatabase(databaseName);
	}

	@Then("User can see {string} Database ID")
	public void user_can_see_database_id(String catalogID) {
		boolean flag = viewCatalogPage.verifyCatalogID(catalogID);
		Assertions.assertTrue(flag, "database ID is not visible");
	}

	@Then("User clicks on copy icon of Database ID")
	public void user_clicks_on_copy_icon_of_database_id() {
		boolean flag = viewCatalogPage.checkCopyIcon();
		viewCatalogPage.clickCopyIcon();
		Assertions.assertTrue(flag, "copy icon is not visible");
	}

	@Then("User can see {string} as database description")
	public void user_can_see_as_database_description(String databaseDescription) {
		boolean flag = viewCatalogPage.verifyCatalogDescription(databaseDescription);
		Assertions.assertTrue(flag, "database description is not visible");
	}

	@Then("User clicks on Export button that creates a Zip of DB when clicked")
	public void user_clicks_on_Export_button_that_creates_a_Zip_of_DB_when_clicked() throws Exception {
		Path downloadedPath = addDatabaseToCatalogPage.clickOnExportButton();
		Assertions.assertNotNull(downloadedPath, "Download was not triggered by Export button!");
	}

	@Then("User sees an Edit button that opens a pop-up to edit")
	public void user_sees_an_edit_button_that_opens_a_pop_up_to_edit() {
		addDatabaseToCatalogPage.clickOnEditButton();
	}

	@Given("User searches the {string} in the database Catalog searchbox")
	public void user_searches_the_in_the_database_catalog_searchbox(String catalogName) {
		addDatabaseToCatalogPage.searchDatabaseCatalog(catalogName);
	}

	@Given("User selects the {string} from the database catalog")
	public void user_selects_the_from_the_database_catalog(String catalogName) {
		addDatabaseToCatalogPage.selectDatabaseFromSearchOptions(catalogName);
	}

	@And("User clicks on apply database button")
	public void user_clicks_on_apply_database_button() {
		addDatabaseToCatalogPage.clickApplyDatabaseButton();
	}

	@When("User clicks on MetaData tab")
	public void user_clicks_on_metadata_tab() {
		// TODO this should be user clicks on Metadata tab
		// if you fix this you need to update in all the places it is used in cucumber
		// files
		addDatabaseToCatalogPage.clickOnMetaDataTab();
	}

	@When("User clicks on Usage tab")
	public void user_clicks_on_usage_tab() {
		Page page = SetupHooks.getPage();
		AICorePageUtils.clickOnTabButton(page, "Usage");
	}

	@Then("User sees the table in the metadata tab")
	public void user_sees_the_table_in_the_metadata_tab() {
		addDatabaseToCatalogPage.verifyMetaData();
	}

	// View Database Type on Connect To database page
	@Then("User searches {string} types and verifies visibility under respective sections")
	public void user_searches_types_and_verifies_visibility_under_respective_sections(String catalog,
			DataTable dataTable) {
		final String SECTION_NAME = "EXPECTED_SECTION";
		final String OPTION_NAME = "DATABASE_TYPE";
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> row : rows) {
			String section = row.get(SECTION_NAME);
			String[] dbTypes = row.get(OPTION_NAME).split(",\\s*");
			addDatabaseToCatalogPage.clickOnSection(catalog, section);
			for (String dbType : dbTypes) {
				addDatabaseToCatalogPage.searchDatabaseType(section, dbType);
				boolean isVisible = addDatabaseToCatalogPage.verifyOptionIsVisible(catalog, section, dbType);
				Assertions.assertTrue(isVisible,
						"Database type '" + dbType + "' was not found under section '" + section + "'");
			}
		}
	}

	@And("User clicks on Refresh button")
	public void user_clicks_on_refresh_button() {
		addDatabaseToCatalogPage.clickOnRefreshButton();
	}

	@And("User selects the {string} from the dropdown")
	public void user_selects_the_from_the_dropdown(String dbName) {
		addDatabaseToCatalogPage.selectDatabaseFromDropdown(dbName);
	}

	@When("User clicks on Query tab")
	public void user_clicks_on_query_tab() {
		addDatabaseToCatalogPage.clickOnQueryTab();
	}

	@When("User enters the query {string}")
	public void user_enters_the_query(String query) {
		addDatabaseToCatalogPage.enterQuery(query);
	}

	@Then("User sees {string} columns in the query response table")
	public void user_sees_columns_in_the_query_response_table(String headerNames) {
		List<String> expectedHeaderNames = Arrays.asList(headerNames.split(", "));
		List<String> actualHeaderNames = addDatabaseToCatalogPage.getQueryResponseTableHeader();
		Assertions.assertEquals(expectedHeaderNames, actualHeaderNames, "Headers are not matching");
	}

	@Then("User can see query field is empty")
	public void user_can_see_query_field_is_empty() {
		addDatabaseToCatalogPage.verifyQueryFieldIsEmpty();
	}

	@Then("User can see all data columns are collapsed")
	public void user_can_see_all_data_columns_are_collapsed() {
		addDatabaseToCatalogPage.verifyAllColumnsAreCollapsed();
	}

	@Then("User can see button name changed to {string} button")
	public void user_can_see_button_name_changed_to_button(String buttonName) {
		boolean isButtonVisible = addDatabaseToCatalogPage.verifyButtonNameChanged(buttonName);
		Assertions.assertTrue(isButtonVisible, "Button name is not changed to " + buttonName);
	}

	@When("User clicks on {string} arrow")
	public void user_clicks_on_arrow(String arrowName) {
		addDatabaseToCatalogPage.clickOnExpandTableArrow(arrowName);
	}

	@Then("User can see {string} columns displayed under data columns section")
	public void user_can_see_columns_displayed_under_data_columns_section(String columnNames) {
		List<String> expectedColumnNames = Arrays.asList(columnNames.split(", "));
		List<String> actualColumnNames = addDatabaseToCatalogPage.getDataColumns();
		Assertions.assertEquals(expectedColumnNames, actualColumnNames, "Columns are not matching");
	}

	@When("User searches the {string} column in data columns searchbox")
	public void user_searches_the_column_in_data_columns_searchbox(String columnName) {
		addDatabaseToCatalogPage.searchDataColumn(columnName);
	}

	@Then("User can see only {string} column in the list")
	public void user_can_see_only_column_in_the_list(String columnName) {
		boolean isColumnVisible = addDatabaseToCatalogPage.verifySearchedDataColumn(columnName);
		Assertions.assertTrue(isColumnVisible, "Searched " + columnName + " column is not visible in the list");
	}

	@When("User clicks on Refresh database structure icon")
	public void user_clicks_on_refresh_database_structure_icon() {
		addDatabaseToCatalogPage.clickOnRefreshButtonForDataColumns();
	}

	@Then("User can see {string} tile")
	public void user_can_see_tile(String text) {
		boolean isTileVisible = addDatabaseToCatalogPage.verifyRefreshingTileForDataColumns(text);
		Assertions.assertTrue(isTileVisible, "Tile is not visible");
	}

	@And("User sees the Save button is {string}")
	public void user_sees_the_save_button_is(String state) {
		boolean isDisabled = state.equalsIgnoreCase("disabled");
		boolean isSaveButtonDisabled = addDatabaseToCatalogPage.isSaveButtonDisabled();
		Assertions.assertEquals(isDisabled, isSaveButtonDisabled, 
			"Save button state is not " + state);
	}
	
	@And("User clicks on Save button of Metadata tab")
	public void user_clicks_on_save_button_of_metadata_tab() {
		addDatabaseToCatalogPage.clickOnSaveButtonOfMetadataTab();
	}	

	@Then("User sees Database Catalog page")
	public void user_sees_database_catalog_page() {
		addDatabaseToCatalogPage.verifyDatabaseCatalogPage();	
	}	

	@And("User click on Connect button")
	public void user_click_on_connect_button() {
		addDatabaseToCatalogPage.clickOnConnectButton();
	}

	@And("User clicks on Save button for database")
	public void user_clicks_on_save_button_for_database() {
		addDatabaseToCatalogPage.clickOnSaveButton();
	}

}
