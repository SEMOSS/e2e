package aicore.steps;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;

import aicore.hooks.SetupHooks;
import aicore.pages.SettingsMyProfile;
import aicore.utils.CommonUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SettingsMyProfileSteps {
	private SettingsMyProfile settings;
	private String copiedAccessKey;
	private String copiedSecretKey;
	private String timestamp;

	public SettingsMyProfileSteps() {
		timestamp = CommonUtils.getTimeStampName();
		this.settings = new SettingsMyProfile(SetupHooks.getPage(), timestamp);
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
	public void user_can_see_section(String sectionName) throws InterruptedException {
		boolean isVisible = settings.isSectionVisible(sectionName);
		assertTrue("Expected section not found: " + sectionName, isVisible);
		Thread.sleep(5000);
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

	@When("User copies the {string} using copy icon and validate its alphanumeric")
	public void user_copies_the_using_copy_icon_and_validate_its_alphanumeric(String KeyName) {
		String copiedKey = settings.copyAccessKey(KeyName);
		// Validate key format
		Assertions.assertTrue(copiedKey.matches("^[a-zA-Z0-9-]+$"),
				"Copied Key contains non-alphanumeric characters: " + copiedKey);
		// Store the key in the appropriate variable
		if (KeyName.equalsIgnoreCase("Access Key")) {
			copiedAccessKey = copiedKey;
		} else if (KeyName.equalsIgnoreCase("Secret Key")) {
			copiedSecretKey = copiedKey;
		} else {
			Assertions.fail("Unknown key type: " + KeyName);
		}
	}

	@Then("User copies contents using copy icon from example section and validate count of Access Key and Secret Key occurences in sections:")
	public void user_copies_contents_using_copy_icon_from_example_section_and_validate_count_of_access_key_and_secret_key_occurences_in_sections(
			io.cucumber.datatable.DataTable dataTable) {
		final String SECTIONS_COLUMN = "SECTIONS";
		final String EXPECTED_ACCESS_KEY_COUNT = "ACCESS KEY COUNT";
		final String EXPECTED_SECRET_KEY_COUNT = "SECRET KEY COUNT";

		Assertions.assertNotNull(copiedAccessKey, "Access Key is not set, check previous step");
		Assertions.assertNotNull(copiedSecretKey, "Secret Key is not set, check previous step");

		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> row : rows) {
			String sectionName = row.get(SECTIONS_COLUMN);
			int expectedAccessKeyCount = Integer.parseInt(row.get(EXPECTED_ACCESS_KEY_COUNT));
			int expectedSecretKeyCount = Integer.parseInt(row.get(EXPECTED_SECRET_KEY_COUNT));
			// Copy the section contents
			String copiedSectionContents = settings.extractExampleSectionContent(sectionName);
			// Count occurrences of Access Key and Secret Key
			int actualAccessKeyCount = CommonUtils.countIdOccurances(copiedSectionContents, copiedAccessKey);
			System.out.println(actualAccessKeyCount);
			int actualSecretKeyCount = CommonUtils.countIdOccurances(copiedSectionContents, copiedSecretKey);
//		System.out.println(actualSecretKeyCount);
			// Validate the counts
			Assertions.assertEquals(expectedAccessKeyCount, actualAccessKeyCount,
					"Access Key count mismatch in section: '" + sectionName + "'");
			Assertions.assertEquals(expectedSecretKeyCount, actualSecretKeyCount,
					"Secret Key count mismatch in section: '" + sectionName + "'");
		}
	}

	@Then("User clicks on Cancel button")
	public void user_clicks_on_cancel_button() {
		settings.clickOnCancelButton();
	}

	@Then("User clicks on delete icon of the generated {string}")
	public void user_clicks_on_delete_icon_of_the_generated(String KeyName) {
		settings.clickOnDeleteIcon(KeyName);
	}

	@Then("User can sees the Toastmessage as {string}")
	public void user_can_sees_the_toastmessage_as(String toastMessage) {
		String actualMessage = settings.deleteKeyToastMessage();
		Assertions.assertEquals(actualMessage, toastMessage, "Key Deletion failed");
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
