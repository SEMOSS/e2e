package aicore.steps;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;

import aicore.hooks.SetupHooks;
import aicore.pages.AddDatabasePage;
import aicore.pages.HomePage;
import aicore.pages.ViewUsagePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddDatabaseSteps extends AbstractAddCatalogBase {

	private AddDatabasePage addDatabaseToCatalogPage;
	private HomePage homePage;
	private ViewUsagePage viewUsagePage;

	public AddDatabaseSteps() {
		homePage = new HomePage(SetupHooks.getPage());
		addDatabaseToCatalogPage = new AddDatabasePage(SetupHooks.getPage());
		viewUsagePage = new ViewUsagePage(SetupHooks.getPage());
	}

	@Given("User clicks on Open Database")
	public void user_clicks_to_open_database() {
		homePage.clickOnOpenDatabase();
	}

	@When("User clicks on Add Database")
	public void user_clicks_on_add_database() {
		addDatabaseToCatalogPage.clickOnAddDatabaseButton();
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
		Assertions.assertEquals(dbName, databaseNameInCatalog, "Database name is not visible in the database catalog");
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

	@And("User applies each filter and validate {string} database is visible on the page")
	public void user_applies_each_filter_and_validate_database_is_visible_on_the_page(String databaseName,
			DataTable dataTable) {
		final String FILTER_CATEGORY_NAME = "FILTER_CATEGORY";
		final String FILTER_VALUE_NAME = "FILTER_VALUE";
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

		for (Map<String, String> row : rows) {
			String filterCategory = row.get(FILTER_CATEGORY_NAME);
			String filterValues = row.get(FILTER_VALUE_NAME);

			String[] filterValuesArray = filterValues.split(", ");
			for (String filterValue : filterValuesArray) {
				addDatabaseToCatalogPage.searchFilterValue(filterValue);
				addDatabaseToCatalogPage.selectFilterValue(filterCategory, filterValue);
				boolean isDatabaseVisible = addDatabaseToCatalogPage.verifyDatabaseIsVisbileInCatalog(databaseName);
				Assertions.assertTrue(isDatabaseVisible, "Database is not present in the databse catalog for " + " ' "
						+ filterValue + " ' " + " filter value");
				// To de-select selected filter we again call this method
				addDatabaseToCatalogPage.selectFilterValue(filterCategory, filterValue);
			}
		}
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
  @When("User clicks on MetaData tab")
  public void user_clicks_on_metadata_tab() {
      addDatabaseToCatalogPage.clickOnMetaDataTab();
  }

  @Then("User sees the table in the metadata tab")
  public void user_sees_the_table_in_the_metadata_tab() {
      addDatabaseToCatalogPage.verifyMetaData();
  }
}