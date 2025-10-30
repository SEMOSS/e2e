package aicore.steps;

import java.util.List;
import java.util.Map;

import aicore.hooks.SetupHooks;
import aicore.pages.AddFunctionToCatalogPage;
import aicore.utils.CommonUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ViewAddFunctionOptionsSteps extends AbstractAddCatalogBase {
	AddFunctionToCatalogPage functionPage;
	protected static String timestamp;

	public ViewAddFunctionOptionsSteps() {
		timestamp = SetupHooks.getTimestamp();
		functionPage = new AddFunctionToCatalogPage(SetupHooks.getPage(), timestamp);
	}

	@When("User clicks on Add Function button")
	public void user_clicks_on_add_function_button() {
		functionPage.clickOnAddFunctionButton();
	}

	@Then("User should see the following function options with valid icons on the page")
	public void user_should_see_the_following_function_options_with_valid_icon_on_the_page(DataTable dataTable) {
		final String GROUP_NAME = "GROUP";
		final String FUNCTION_OPTION_NAMES = "FUNCTION_OPTIONS";
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

		validateOptionsWithIcon(GROUP_NAME, FUNCTION_OPTION_NAMES, rows, functionPage);
	}

	@Then("User should see Search bar to filter function options")
	public void user_should_see_search_bar_to_filter_options() {
		validateSearchBar(functionPage);
	}
}