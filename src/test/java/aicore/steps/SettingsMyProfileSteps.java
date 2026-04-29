package aicore.steps;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;

import aicore.hooks.SetupHooks;
import aicore.pages.SettingsMyProfile;
import aicore.utils.settings.PersonalAccessTokenUtils;
import aicore.utils.settings.MyProfilePageUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SettingsMyProfileSteps {
	private SettingsMyProfile settings;
	private String timestamp;

	public SettingsMyProfileSteps() {
		timestamp = SetupHooks.getTimestamp();
		this.settings = new SettingsMyProfile(SetupHooks.getPage(), timestamp);
	}

	@When("User clicks on My Profile")
	public void user_clicks_on_my_profile() {
		MyProfilePageUtils.clickOnMyProfileCard(SetupHooks.getPage());
	}

	@Then("User can see {string} link in the top right")
	public void user_can_see_link_in_the_top_right(String privacyCenterName) {
		String actualName = settings.verifyPrivacyCenter();
		Assertions.assertEquals(actualName, privacyCenterName, "Pivacy Center name after adding failed");

	}

	@Then("User can see {string} section on profile page")
	public void user_can_see_section(String sectionName) {
		boolean isVisible =  MyProfilePageUtils.isSectionVisible(SetupHooks.getPage(), sectionName);
		assertTrue(isVisible, "Expected section not found: " + sectionName);
	}

	@When("User clicks on New Key button")
	public void user_clicks_on_new_key_button() {
		PersonalAccessTokenUtils.clickNewKeyButton(SetupHooks.getPage());
	}

	@Then("User clicks on Generate button")
	public void user_clicks_on_generate_button() {
		PersonalAccessTokenUtils.clickGenerateButton(SetupHooks.getPage());
	}


	@Then("User clicks on Cancel button")
	public void user_clicks_on_cancel_button() {
		settings.clickOnCancelButton();
	}

	@Then("User can sees the Toastmessage as {string}")
	public void user_can_sees_the_toastmessage_as(String toastMessage) {
		String actualMessage = settings.deleteKeyToastMessage();
		Assertions.assertEquals(actualMessage, toastMessage, "Key Deletion failed");
	}

	@Then("User can see the following field with their state:")
	public void user_can_see_the_following_field_with_their_state(DataTable dataTable) {
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> row : rows) {
			String fieldName = row.get("FIELD NAME");
			String fieldState = row.get("FIELD STATE");
			boolean expectedState = "Enable".equalsIgnoreCase(fieldState);
			boolean actualState = settings.isFieldEnabled(fieldName);
			Assertions.assertEquals(actualState, expectedState, "Field state mismatch for field: '" + fieldName + "'");
		}
	}

	@Then("User update {string} field with {string}")
	public void user_update_field_with(String fieldName, String fieldValue) {
		settings.updateField(fieldName, fieldValue + timestamp);
	}

	@Then("User can see the the updated name as {string}")
	public void user_can_see_the_the_updated_name_as(String updatedName) {
		boolean updatedNameVisible = settings.getUpdatedInfo(updatedName + timestamp);
		Assertions.assertTrue(updatedNameVisible, "Updated name is not visible");
	}

	@And("User clicks on Change Password link")
	public void user_clicks_on_change_password_link() {
		settings.clickOnChangePasswordLink();
	}

	@And("User can sees the {string} title")
	public void user_can_sees_the_title(String title) {
		boolean titleVisible = settings.changePasswordTitle(title);
		Assertions.assertTrue(titleVisible, "Change Password title is not visible");
	}

	@And("User clicks on Profile Icon")
	public void user_clicks_on_profile_icon() {
		settings.clickOnProfileIcon();
	}

	@When("User enter the {string} as {string}")
	public void user_enter_the_password_as(String fieldName, String fieldValue) {
		settings.enterThePassword(fieldName, fieldValue);
	}

	@And("User can see the error as {string}")
	public void user_can_see_the_error_as(String errorMessage) {
		String actualError = settings.getErrorMessage(errorMessage);
		Assertions.assertEquals(actualError, errorMessage, "Error message mismatch");
	}

}