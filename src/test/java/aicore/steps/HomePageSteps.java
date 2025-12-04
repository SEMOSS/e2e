package aicore.steps;

import org.junit.jupiter.api.Assertions;

import aicore.hooks.SetupHooks;
import aicore.pages.HomePage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HomePageSteps {

	private HomePage homePage;

	public HomePageSteps() {
		this.homePage = new HomePage(SetupHooks.getPage());
	}

	@When("User clicks on Home")
	public void user_clicks_on_home() {
		homePage.clickOnHome();
	}

	@When("User search the {string} in the home search box")
	public void user_search_the_in_the_home_search_box(String searchData) {
		homePage.searchCatalog(searchData);
	}

	@When("User clicks on the {string} option to filter the results")
	public void user_clicks_on_the_option_to_filter_the_results(String optionName) {
		homePage.selectSearchResultFilterOption(optionName);
	}

	@Then("User can see {string} in the {string} filter results")
	public void user_can_see_in_the_filter_results(String searchResult, String optionName) {
		boolean isCardVisible = homePage.verifySearchResultIsVisible(searchResult);
		Assertions.assertTrue(isCardVisible, "Searched data is not visible in search result list");
	}

	@When("User clicks on the {string} option to unfilter the results")
	public void user_clicks_on_the_option_to_unfilter_the_results(String optionName) {
		homePage.selectSearchResultFilterOption(optionName);
	}

	@Then("User close the search popup")
	public void user_close_the_search_popup() {
		homePage.closeSearchPopup();
	}

	@When("User enters {string} as app name")
	public void user_enters_as_app_name(String appName) {
		homePage.enterAppNameToCreateApp(appName);
	}

	@When("User enters {string} as Catalog name")
	public void user_enters_as_catalog_name(String catalogName) {
		homePage.enterCatalogNameToCreateCatalog(catalogName);
	}

}
