package aicore.steps;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;

import aicore.hooks.SetupHooks;
import aicore.pages.AddDatabaseToCatalogPage;
import aicore.pages.HomePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddDatabaseSteps {
	private AddDatabaseToCatalogPage addDatabaseToCatalogPage;
	private HomePage homePage;

	public AddDatabaseSteps() {
		homePage = new HomePage(SetupHooks.getPage());
		addDatabaseToCatalogPage = new AddDatabaseToCatalogPage(SetupHooks.getPage());
	}

	@Given("User navigates to Open Database")
	public void user_navigates_to_open_database() {
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
}