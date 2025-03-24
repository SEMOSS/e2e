package aicore.steps;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Assertions;

import aicore.base.AICoreTestManager;
import aicore.pages.SettingsMyProfile;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SettingsMyProfileSteps {
	private SettingsMyProfile settings;

	public SettingsMyProfileSteps() {
		this.settings = new SettingsMyProfile(AICoreTestManager.getPage());
	}

	@Given("User clicks on Open Settings icon")
	public void user_clicks_on_open_settings_icon() {
		settings.openSettingsIcon();
	}

	@When("User clicks on My Profile")
	public void user_clicks_on_my_profile() {
		settings.clickOnMyProfileCard();
	}

	@Then("User can sees {string} link in the top right")
	public void user_can_sees_link_in_the_top_right(String privacyCenterName) {
		String actualName = settings.verifyPrivacyCenter();
		Assertions.assertEquals(actualName, privacyCenterName, "Pivacy Center name after adding failed");

	}

	@Then("User can sees {string} section")
	public void user_can_sees_section(String sectionName) {
		boolean isVisible = settings.isSectionVisible(sectionName);
		assertTrue("Expected section not found: " + sectionName, isVisible);
	}

}
