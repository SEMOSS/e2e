package aicore.steps;

import aicore.hooks.SetupHooks;
import aicore.pages.HomePage;
import aicore.pages.VectorSettingPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class VectorSettingsSteps {
	private HomePage homePage;
	private VectorSettingPage vectorSettingsPage;

	public VectorSettingsSteps() {
		homePage = new HomePage(SetupHooks.getPage());
		vectorSettingsPage = new VectorSettingPage(SetupHooks.getPage());
	}

	@When("User navigates to Open Setting page")
	public void user_navigates_to_open_setting_page() {
		homePage.clickOnOpenSettings();
	}

	@Then("User sees title as {string}")
	public void user_sees_title_as_vector_settings(String title) {
		vectorSettingsPage.verifyVectorSettingTitle(title);
	}

	@Then("User sees the vector cards")
	public void user_sees_the_vector_cards() {
		vectorSettingsPage.verifyVectorCards();

	}

	@Then("User sees the search bar")
	public void user_sees_the_search_bar() {
		vectorSettingsPage.isSearchBarPresent();
	}

	@Then("User searches for the vector {string}")
	public void user_searches_for_the_vector(String catalogName) {
		vectorSettingsPage.searchForVector(catalogName);
	}

	@Then("User sees the {string} in the searched vector list")
	public void user_sees_the_in_the_searched_vector_list(String catalogName) {
		vectorSettingsPage.verifySearchedVector(catalogName);
	}

	@And("User clicks on created Vector card name as {string}")
	public void user_clicks_on_created_vector_card_name_as(String catalogName) {
		vectorSettingsPage.clickOnVectorCardByName(catalogName);
	}

}
