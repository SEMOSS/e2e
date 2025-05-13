package aicore.steps;

import aicore.hooks.SetupHooks;
import aicore.pages.HomePage;
import aicore.pages.ViewFunctionPage;
import io.cucumber.java.en.Then;

public class ViewFunctionSteps {
	private HomePage homePage;
	private ViewFunctionPage viewFunction;

	public ViewFunctionSteps() {
		homePage = new HomePage(SetupHooks.getPage());
		viewFunction = new ViewFunctionPage(SetupHooks.getPage());
	}

	@Then("User can see {string} as function name")
	public void user_can_see_as_function_name(String functionName) {
		viewFunction.verifyFunctionName(functionName);
	}

	@Then("User can see function ID")
	public void user_can_see_function_id() {
		viewFunction.verifyFunctionID();
	}

	@Then("User can see {string} as function description")
	public void user_can_see_as_function_description(String functionDescription) {
		viewFunction.verifyFunctionDescription(functionDescription);
	}

	@Then("User can see {string} as Date last updated")
	public void user_can_see_as_date_last_updated(String dateLastUpdated) {
		viewFunction.verifyDateLastUpdated(dateLastUpdated);
	}

	@Then("User can see {string} Markup with Function overview in Overview tab at the bottom of the page.")
	public void user_can_see_markup_with_function_overview_in_overview_tab_at_the_bottom_of_the_page(
			String markupFunction) {
		viewFunction.verifyMarkupFunction(markupFunction);
	}

	@Then("User sees {string} button")
	public void user_sees_button(String changeAccessButton) {
		viewFunction.verifyChangeAccessButton(changeAccessButton);
	}

	@Then("User selects {string} tab")
	public void user_selects_tab(String usageTab) {
		viewFunction.clickUsageTab(usageTab);
	}

	@Then("User can see {string} usage instructions section")
	public void user_can_see_usage_instructions_section(String usageInstructionsSection) {
		viewFunction.verifyUsageInstructionsSection(usageInstructionsSection);
	}

}