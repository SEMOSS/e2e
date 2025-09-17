package aicore.steps;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;

import aicore.hooks.SetupHooks;
import aicore.pages.CatalogFilterPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CatalogFilterSteps {
	private CatalogFilterPage filterPage;

	public CatalogFilterSteps() {
		filterPage = new CatalogFilterPage(SetupHooks.getPage());
	}

	@And("User applies each filter and validate {string} catalog is visible on the {string} catalog page")
	public void user_applies_each_filter_and_validate_catalog_is_visible_on_the_page(String catalogName,
			String engineName, DataTable dataTable) {
		switch (engineName) {
		case "model":
			catalogName = catalogName + AddModelSteps.timestamp;
			break;
		case "storage":
			catalogName = catalogName + AddStorageSteps.timestamp;
			break;
		case "vector":
			catalogName = catalogName + AddVectorDatabaseSteps.timestamp;
			break;
		}
		final String FILTER_CATEGORY_NAME = "FILTER_CATEGORY";
		final String FILTER_VALUE_NAME = "FILTER_VALUE";
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
		validateCatalogFilters(catalogName, FILTER_CATEGORY_NAME, FILTER_VALUE_NAME, rows, filterPage);
	}

	@When("User clicks on bookmark button of {string} catalog")
	public void user_clicks_on_bookmark_button_of_catalog(String catalogName) {
		filterPage.clickOnBookmark(catalogName);
	}

	@Then("User sees the catalog name {string} in the Bookmarked section")
	public void user_sees_the_catalog_name_in_the_bookmarked_section(String catalogName) {
		boolean iscatalogDisplayedUnderBookmarkedSection = filterPage
				.verifyCatalogDisplayedUnderBookmarkedSection(catalogName);
		Assertions.assertTrue(iscatalogDisplayedUnderBookmarkedSection,
				catalogName + " " + "not dispaled under bookmarked section");
	}

	@When("User clicks on bookmark button to unbookmark {string} catalog")
	public void user_clicks_on_bookmark_button_ot_unbookmark_catalog(String catalogName) {
		filterPage.clickOnUnbookmark(catalogName);
	}

	private void validateCatalogFilters(final String catalogName, final String filterCategoryName,
			final String filterValueName, List<Map<String, String>> rows, CatalogFilterPage catalogFilterPage) {
		for (Map<String, String> row : rows) {
			String filterCategory = row.get(filterCategoryName);
			String filterValues = row.get(filterValueName);

			String[] filterValuesArray = filterValues.split(", ");
			for (String filterValue : filterValuesArray) {
				catalogFilterPage.searchFilterValue(filterValue);
				catalogFilterPage.selectFilterValue(filterCategory, filterValue);
				boolean isCatalogVisible = catalogFilterPage.verifyCatalogIsVisibleOnCatalogPage(catalogName);
				Assertions.assertTrue(isCatalogVisible,
						"Catalog is not present for " + "'" + filterValue + "'" + " filter value");
				// To de-select selected filter we again call this method
				catalogFilterPage.selectFilterValue(filterCategory, filterValue);
			}
		}
	}

}
