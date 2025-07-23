package aicore.steps;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;

import aicore.hooks.SetupHooks;
import aicore.pages.AddDatabasePage;
import aicore.pages.HomePage;
import aicore.pages.ViewCatalogPage;
import aicore.pages.ViewUsagePage;
import aicore.utils.CommonUtils;
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
		timestamp = CommonUtils.getTimeStampName();
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
		addDatabaseToCatalogPage.uploadHostFile(fileName);
	}

	@When("User clicks on apply button")
	public void user_clicks_on_apply_button() {
		addDatabaseToCatalogPage.clickOnApplyButton();
	}

	@And("User clicks on apply database button")
	public void user_clicks_on_apply_database_button() {
		addDatabaseToCatalogPage.clickApplyDatabaseButton();
	}

	@Then("User selects database {string} from connection types")
	public void user_selects_database_from_connection_types(String dbType) {
		addDatabaseToCatalogPage.selectDatabaseFromConnectionTypes(dbType);
	}

	@Then("User can see the database title as {string}")
	public void User_can_see_the_database_title_as(String dbName) {
		boolean isTitleVisible = addDatabaseToCatalogPage.verifyDatabaseTitle(dbName + timestamp);
		Assertions.assertTrue(isTitleVisible, "Database title is not visible");
	}

	@Given("User clicks on the database name {string} in  database catalog")
	public void User_clicks_on_the_database_name_in_database_catalog(String dbName) {
		String databaseNameInCatalog = addDatabaseToCatalogPage.verifyDatabaseNameInCatalog(dbName + timestamp);
		boolean databaseNameFlag = databaseNameInCatalog.contains(dbName + timestamp);
		Assertions.assertTrue(databaseNameFlag, "Database name is not visible in the database catalog");
	}

	@Given("User sees the database name {string} in database catalog")
	public void User_sees_the_database_name_in_database_catalog(String dbName) {
		addDatabaseToCatalogPage.clickOnDatabaseNameInCatalog(dbName + timestamp);
	}

	@Then("User selects database {string}")
	public void user_selects_database(String dbType) {
		addDatabaseToCatalogPage.selectDatabaseType(dbType);
	}

	@And("User uploads database file {string}")
	public void user_uploads_database_file(String fileName) {
		String uploadedFileName = addDatabaseToCatalogPage.uploadDatabaseFile(fileName);
		if (fileName.contains("/")) {
			String[] ActualFileName = fileName.split("/");
			int fileNameIndex = ActualFileName.length - 1;
			Assertions.assertEquals(ActualFileName[fileNameIndex], uploadedFileName,
					"Database Document file is not uploaded successfully");
		} else {
			Assertions.assertEquals(fileName, uploadedFileName, "Database Document file is not uploaded successfully");
		}

	}

	@And("User clicks on Create Database button")
	public void user_clicks_on_create_database_button() {
		addDatabaseToCatalogPage.clickOnCreateDatabaseButton();
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

	@And("User clicks on the database name {string} in the database catalog and Copy ID")
	public void user_clicks_the_database_name_in_the_database_catalog_And_Copy_ID(String dbName) {
		//TODO 
//		addDatabaseToCatalogPage.clickOnDatabaseNameInCatalogAndCopyID(dbName);
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

	@And("User should see the following database options with icons on the page")
	public void user_should_see_the_following_database_options_with_icons_on_the_page(DataTable dataTable) {
		final String GROUP_NAME = "GROUP";
		final String DATABASE_OPTION_NAMES = "DATABASE_OPTIONS";
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
		validateOptionsWithIcon(GROUP_NAME, DATABASE_OPTION_NAMES, rows, addDatabaseToCatalogPage);
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

	@When("User clicks on bookmark button of {string} database")
	public void user_clicks_on_bookmark_button_of_database(String dbName) {
		addDatabaseToCatalogPage.clickOnBookmark(dbName);
	}

	@Then("User sees the database name {string} in the Bookmarked section")
	public void user_sees_the_database_name_in_the_bookmarked_section(String catalogName) {
		boolean iscatalogDisplayedUnderBookmarkedSection = addDatabaseToCatalogPage
				.verifyCatalogDisplayedUnderBookmarkedSection(catalogName);
		Assertions.assertTrue(iscatalogDisplayedUnderBookmarkedSection,
				catalogName + " " + "not dispaled under bookmarked section");
	}

	@When("User clicks on bookmark button to unbookmark {string} database")
	public void user_clicks_on_bookmark_button_ot_unbookmark_database(String dbName) {
		addDatabaseToCatalogPage.clickOnUnbookmark(dbName);
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
		addDatabaseToCatalogPage.searchFunctionCatalog(catalogName);
	}

	@Given("User selects the {string} from the database catalog")
	public void user_selects_the_from_the_database_catalog(String catalogName) {
		addDatabaseToCatalogPage.selectFunctionFromSearchOptions(catalogName);
	}

	@When("User clicks on MetaData tab")
	public void user_clicks_on_metadata_tab() {
		// TODO this should be user clicks on Metadata tab
		// if you fix this you need to update in all the places it is used in cucumber
		// files
		addDatabaseToCatalogPage.clickOnMetaDataTab();
	}

	@Then("User sees the table in the metadata tab")
	public void user_sees_the_table_in_the_metadata_tab() {
		addDatabaseToCatalogPage.verifyMetaData();
	}

	// View Database Type on Connect To database page
	@Then("User searches database types and verifies visibility under respective sections")
	public void userSearchesDatabaseTypesAndVerifiesVisibility(DataTable dataTable) {
		final String SECTION_NAME = "EXPECTED_SECTION";
		final String OPTION_NAME = "DATABASE_TYPE";
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> row : rows) {
			String section = row.get(SECTION_NAME);
			String[] dbTypes = row.get(OPTION_NAME).split(",\\s*");
			for (String dbType : dbTypes) {
				addDatabaseToCatalogPage.searchDatabaseType(dbType);
				boolean isVisible = addDatabaseToCatalogPage.verifyOptionIsVisible(section, dbType);
				Assertions.assertTrue(isVisible,
						"Database type '" + dbType + "' was not found under section '" + section + "'");
			}
		}
	}
}
