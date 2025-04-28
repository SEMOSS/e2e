package aicore.steps;

import org.junit.jupiter.api.Assertions;

import aicore.hooks.SetupHooks;
import aicore.pages.ViewStoragePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ViewStorageSteps {

	private ViewStoragePage viewStorage;
	
	public ViewStorageSteps() {
		this.viewStorage = new ViewStoragePage(SetupHooks.getPage());
	}
	
	@Given("User clicks on Open Storage Engine")
	public void user_clicks_on_open_storage_engine() {
		viewStorage.openStorageEngine();
	}
	
	@Given("User has added storages in the system")
	public void user_has_added_storages_in_the_system() {
		Assertions.assertTrue(viewStorage.isStoragePresent(), "No storage is added in the system");
	}
	
	@When("User clicks on 'My Storages' tab")
	public void user_clicks_on_my_storages_tab() {
		viewStorage.clickMyStoragesTab();
	}
	
	@Then("User should see the Storage title as {string}")
	public void user_can_see_the_storage_title_as(String storageTitle) {
		String actualTitle = viewStorage.getStorageTitle(storageTitle);
		String expectedTitle = storageTitle;
		Assertions.assertEquals(actualTitle, expectedTitle, "Storage title is not matching");
	}

	
	@Then("User should see the 'No description available' in the description")
	public void user_should_see_no_description_available() {
		String description = viewStorage.getStorageDescription();
		Assertions.assertEquals("No description available", description, "Description mismatch");
	}
	
	
	@When("User clicks on {string}")
	public void user_clicks_on_storage(String storageName) {
		viewStorage.clickOnStorage(storageName);
	}
	
	@When("User clicks on 'Edit' button")
	public void user_clicks_on_edit_button() {
		viewStorage.clickEditButton();
	}
	
	@When("User enters {string} under 'Tag' and press enter")
	public void user_enters_tag_and_press_enter(String TagName) {
		viewStorage.enterTag(TagName);
	}
	
	@Then("User should see {string} under 'Tag' in the Overview tab")
	public void user_should_see_tag_in_overview_tab(String expectedTag) {
		Assertions.assertTrue(viewStorage.isFilterValueVisibleInOverview(expectedTag), "'"+ expectedTag+"' not visible in Overview tab");
	}

	@When("User clicks on 'Submit' button")
	public void user_clicks_on_submit_button() {
		viewStorage.clickSubmitButton();
	}
	
	@When("User enters {string} under 'Domain' section and press enter")
	public void user_enters_domain_and_press_enter(String domainName) {
		viewStorage.enterDomain(domainName);
	}
	
	@Then("User should see {string} under 'Domain' in the Overview tab")
	public void user_should_see_domain_in_overview_tab(String expectedDomain) {
		Assertions.assertTrue(viewStorage.isFilterValueVisibleInOverview(expectedDomain), "'"+ expectedDomain+"' not visible in Overview tab");
	}
	
	@When("User enters and selects {string} under 'Data classification' section")
	public void user_enters_and_selects_data_classification(String dataClassificationName) {
		viewStorage.enterAndSelectDataClassification(dataClassificationName);
	}
	
	@Then("User should see {string} under 'Data classification' in the Overview tab")
	public void user_should_see_confidential_in_overview_tab(String expectedDataClassificationValue) {
		Assertions.assertTrue(viewStorage.isFilterValueVisibleInOverview(expectedDataClassificationValue), "'"+ expectedDataClassificationValue+"' not visible in Overview tab");	
	}
	
	@When("User enters and selects {string} under 'Data restrictions' section")
	public void user_selects_confidential_allowed_data_restriction(String dataRestrictionValue) {
		viewStorage.enterAndSelectDataRestriction(dataRestrictionValue);
	}
	
	@Then("User should see {string} under 'Data restrictions' in the Overview tab")
	public void user_should_see_confidential_allowed_in_overview_tab(String expectedDataRestrictionValue) {
		Assertions.assertTrue(viewStorage.isFilterValueVisibleInOverview(expectedDataRestrictionValue), "'"+ expectedDataRestrictionValue+"' not visible in Overview tab");
	}
	
	@When("User expands 'Filter By' section")
	public void user_expands_filter_by_section() {
		viewStorage.expandFilterBySection();
	}
	
	@When("User selects {string} under 'Tag' section")
	public void user_selects_tag(String tagName) {
		viewStorage.toggleFilter(tagName);
	}
	
	@When("User unselects {string} under 'Tag' section")
	public void user_unselects_under_tag(String tagName) {
		viewStorage.toggleFilter(tagName);
	}
	
	@When("User selects {string} under 'Domain' section")
	public void user_selects_under_domain(String domainName) {
		viewStorage.toggleFilter(domainName);
	}
	
	@When("User unselects {string} under 'Domain' section")
	public void user_unselects_under_domain(String domainName) {
		viewStorage.toggleFilter(domainName);
	}
	
	@When("User selects {string} under 'Data Classification' section")
	public void user_selects_data_classification(String dataClassificationValue) {
		viewStorage.toggleFilter(dataClassificationValue);
	}
	
	@When("User unselects {string} under 'Data Classification' section")
	public void user_unselects_confidential_under_data_classification(String dataClassificationValue) {
		viewStorage.toggleFilter(dataClassificationValue);
	}
	
	@When("User selects {string} under 'Data Restrictions' section")
	public void user_selects_data_restriction(String dataRestrictionValue) {
		viewStorage.toggleFilter(dataRestrictionValue);
	}
	
	@When("User clicks on 'Discoverable Storages' tab")
	public void user_clicks_on_discoverable_storages_tab() {
		viewStorage.clickDiscoverableStoragesTab();
	}
}
