package aicore.steps;

import java.util.List;
import java.util.Map;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;

import com.microsoft.playwright.Locator;

import aicore.hooks.SetupHooks;
import aicore.hooks.SoftAssertionHooks;
import aicore.pages.HomePage;
import aicore.pages.AddFunctionToCatalogPage;
import aicore.utils.CommonUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ViewAddFunctionOptionsSteps {
	private HomePage homePage;
	private AddFunctionToCatalogPage functionPage;
	private SoftAssertions softAssert;

	public ViewAddFunctionOptionsSteps() {
		this.homePage = new HomePage(SetupHooks.getPage());
		functionPage = new AddFunctionToCatalogPage(SetupHooks.getPage());
		softAssert = SoftAssertionHooks.getSoftAssertions();
	}

	@Given("User navigates to Open Function")
	public void user_navigates_to_open_function() {
		homePage.clickOnOpenFunction();
	}

	@When("User clicks on Add Function button")
	public void user_clicks_on_add_function_button() {
		functionPage.clickOnAddFunctionButton();
	}

	@Then("User should see Search bar to filter options")
	public void user_should_see_search_bar_to_filter_options() {
		boolean isSearchBarVisible = functionPage.isSearchBarPresent();
		Assertions.assertTrue(isSearchBarVisible, "Search bar not visible");
		System.out.println("Search bar is present");
	}

	@Then("User should see the following options on the page")
	public void user_should_see_the_following_options_on_the_page(DataTable dataTable) {
		final String LLM_DATATABLE_GROUP_NAME = "LLM_GROUP";
		final String LLM_FUNCTION_DATATABLE_OPTION_NAME = "LLM_DATABASE_OPTIONS";
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

		for (Map<String, String> row : rows) {
			String sectionName = row.get(LLM_DATATABLE_GROUP_NAME);
			String expectedOptions = row.get(LLM_FUNCTION_DATATABLE_OPTION_NAME);
			boolean isSectionVisible = functionPage.verifySectionIsVisible(sectionName);
			softAssert.assertThat(isSectionVisible).isTrue();

			String[] expectedOptionsArray = expectedOptions.split(", ");
			for (String option : expectedOptionsArray) {
				boolean isModelVisible = functionPage.VerifyDatabaseOptionIsVisible(sectionName, option);
				softAssert.assertThat(isModelVisible).isTrue();
			}
		}
	}

	@Then("User should see the valid \\(non-broken) icon appear before each database option")
	public void user_should_see_the_valid_non_broken_icon_appear_before_each_database_option(DataTable dataTable) {
		for (String optionName : dataTable.asList()) {
			Locator icon = functionPage.getIconByLabel(optionName);
			icon.waitFor();
			boolean isIconVisible = functionPage.isIconVisible(optionName);
			Assertions.assertTrue(isIconVisible, optionName + " icon is not visible");
			String iconUrl = icon.getAttribute("src");
			if (isIconVisible) {
				boolean isIconValid = CommonUtils.isIconValid(iconUrl);
				Assertions.assertTrue(isIconValid, optionName + " icon src is broken: " + iconUrl);
			}
		}
	}
}