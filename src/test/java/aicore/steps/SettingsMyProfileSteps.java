package aicore.steps;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Assertions;

import aicore.hooks.SetupHooks;
import aicore.pages.SettingsMyProfile;
import aicore.utils.CommonUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SettingsMyProfileSteps {
	private SettingsMyProfile settings;
	private String timestamp;

	public SettingsMyProfileSteps() {
		timestamp = CommonUtils.getTimeStampName();
		this.settings = new SettingsMyProfile(SetupHooks.getPage(), timestamp);
	}

	@Given("User clicks on Open Settings icon")
	public void user_clicks_on_open_settings_icon() {
		settings.openSettingsIcon();
	}

	@When("User clicks on My Profile")
	public void user_clicks_on_my_profile() {
		settings.clickOnMyProfileCard();
	}

	@Then("User can see {string} link in the top right")
	public void user_can_see_link_in_the_top_right(String privacyCenterName) {
		String actualName = settings.verifyPrivacyCenter();
		Assertions.assertEquals(actualName, privacyCenterName, "Pivacy Center name after adding failed");

	}

	@Then("User can see {string} section on profile page")
	public void user_can_see_section(String sectionName) {
		boolean isVisible = settings.isSectionVisible(sectionName);
		assertTrue("Expected section not found: " + sectionName, isVisible);
	}

	@When("User clicks on New Key button")
	public void user_clicks_on_new_key_button() {
		settings.clickNewKeyButton();
	}

	@Then("User fills Name as {string} in Name field")
	public void user_fills_name_as_in_name_field(String newKey) {
		settings.enterKeyName(newKey);
	}

	@Then("User fills Description as {string} in Description field")
	public void user_fills_description_as_in_description_field(String description) {
		settings.enterDescription(description);
	}

	@Then("User clicks on Generate button")
	public void user_clicks_on_generate_button() {
		settings.clickGenerateButton();
	}

	@Then("User clicks on Close button")
	public void user_clicks_on_close_button() {
		settings.clickOnCancelButton();
	}

	@Then("User can see generated key name as {string}")
	public void user_can_see_generated_key_name_as(String keyName) {
		String actualKeyName = settings.validateGeneratedKey(keyName);
		String expKeyTitle = settings.getExpectedAccessKeyTitle(keyName);
		Assertions.assertEquals(actualKeyName, expKeyTitle);
	}

	@Then("User can see generated key description as {string}")
	public void user_can_see_generated_key_description_as(String description) {
		String actualDescription = settings.validateDescriptionName(description);
		String expDescription = settings.getExpectedDescriptionName(description);
		Assertions.assertEquals(actualDescription, expDescription);
	}
}
