package aicore.steps;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.SoftAssertions;

import aicore.hooks.SetupHooks;
import aicore.hooks.SoftAssertionHooks;
import aicore.pages.AddModelPage;
import aicore.utils.CommonUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ModelSettingsSteps {
	private AddModelPage modelPage;
	private String timestamp;
	private SoftAssertions softAssert;

	public ModelSettingsSteps() {
		timestamp = CommonUtils.getTimeStampName();
		modelPage = new AddModelPage(SetupHooks.getPage(), timestamp);
		softAssert = SoftAssertionHooks.getSoftAssertions();
	}

	@When("User clicks on Model Setting tab")
	public void user_clicks_on_model_setting_tab() {
		modelPage.clickOnSettingsTab();
	}

	@Then("User can see {string} section")
	public void user_can_see_section(String sectionName) {
		boolean isSectionVisible = false;
		switch (sectionName) {
		case "Pending Requests":
			isSectionVisible = modelPage.verifyPendingRequestsSectionIsVisible();
			break;
		case "Permissions":
			isSectionVisible = modelPage.verifyMembersSectionIsVisible();
			break;
		default:
			isSectionVisible = modelPage.verifyMakePublicSectionIsVisible(sectionName);
		}
		softAssert.assertThat(isSectionVisible).isTrue();
	}

	@And("User can see text message in {string} section as {string}")
	public void user_can_see_text_message_in_section_as(String sectionName, String textMessage) {
		String actualTextMessage = null;
		switch (sectionName) {
		case "Private":
			actualTextMessage = modelPage.verifyMakePublicSectionTextMessage();
			break;
		case "Non Discoverable":
			actualTextMessage = modelPage.verifyMakeDiscoverableSectionTextMessage();
			break;
		case "Delete Model":
			actualTextMessage = modelPage.verifyDeleteSectionTextMessage();
			break;
		default:
			System.out.println("Invalid section name: " + sectionName);
		}
		softAssert.assertThat(actualTextMessage).isEqualTo(textMessage);
	}

	@And("User can see toggle button in {string} section")
	public void user_can_see_toggle_button_in_section(String sectionName) {
		boolean isToggleButtonVisible = false;
		switch (sectionName) {
		case "Private":
			isToggleButtonVisible = modelPage.verifyMakePublicToggleButtonIsVisible();
			break;
		case "Non Discoverable":
			isToggleButtonVisible = modelPage.verifyMakeDiscoverableToggleButtonIsVisible();
			break;
		default:
			System.out.println("Invalid section name: " + sectionName);
		}
		softAssert.assertThat(isToggleButtonVisible).isTrue();
	}

	@And("User can see Delete button in {string} section")
	public void user_can_see_delete_button_in_section(String sectionName) {
		boolean isDeleteButtonVisible = false;
		switch (sectionName) {
		case "Delete Model":
			isDeleteButtonVisible = modelPage.verifyDeleteButtonIsVisible();
			break;
		default:
			System.out.println("Invalid section name: " + sectionName);
		}
		softAssert.assertThat(isDeleteButtonVisible).isTrue();
	}

	@And("User can see {string} section contains {string} or {string} text with count")
	public void user_can_see_section_contains_or_text_wit_count(String sectionName, String singleRequesttextMessage,
			String multipleRequesttextMessage) {
		String pendingRequestText = modelPage.verifyPendingRequestsSectionTextMessage();
		String extractedText = pendingRequestText.replaceAll("\\d+", "").trim();
		boolean isValidText = extractedText.equals(singleRequesttextMessage)
				|| extractedText.equals(multipleRequesttextMessage);
		softAssert.assertThat(isValidText).isTrue();
	}

	@And("User can see {string} textbox")
	public void user_can_see_textbox(String searchMembersTextBox) {
		boolean isSearchMembersSearchBoxVisible = modelPage.verifySearchMembersSearchBoxIsVisible();
		softAssert.assertThat(isSearchMembersSearchBoxVisible).isTrue();
	}

	@And("User can see {string} button")
	public void user_can_see_button(String addMembersButton) {
		boolean isAddMembersButtonVisible = modelPage.verifyAddMembersButtonIsVisible();
		softAssert.assertThat(isAddMembersButtonVisible).isTrue();
	}

	@And("User can see {string} selection dropdown")
	public void user_can_see_selection_dropdown(String rowsPerPageDropdown) {
		boolean isRowsPerPageDropdownVisible = modelPage.verifyRowsPerPageDropdownIsVisible();
		softAssert.assertThat(isRowsPerPageDropdownVisible).isTrue();
	}

	@And("User can see {string} dropdown has options {string}")
	public void user_can_see_dropdown_has_options(String dropdownName, String expectedOptions) {
		List<String> actualAllOptions = modelPage.verifyRowsPerPageDropdownOptions();
		List<String> expectedOptionList = Arrays.asList(expectedOptions.split(", "));
		softAssert.assertThat(actualAllOptions).isEqualTo(expectedOptionList);

	}
}
