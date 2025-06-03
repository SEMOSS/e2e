package aicore.steps;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;

import aicore.hooks.SetupHooks;
import aicore.pages.HomePage;
import aicore.pages.SettingPage;
import aicore.pages.UserManagementPage;
import aicore.utils.LastCreatedUser;
import aicore.utils.RestCaller;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserManagementSteps {

	private HomePage homePage;
	private UserManagementPage userpage;
	private SettingPage settingpage;

	public UserManagementSteps() {
		this.homePage = new HomePage(SetupHooks.getPage());
		this.userpage = new UserManagementPage(SetupHooks.getPage());
		this.settingpage = new SettingPage(SetupHooks.getPage());
	}

	@And("User clicks on Open Settings")
	public void user_clicks_on_open_settings() {
		homePage.clickOnOpenSettings();
	}

	@And("User enables admin mode")
	public void user_enables_admin_mode() {
		settingpage.checkAdminButton();
		settingpage.clickOnAdminButton();
	}

	@When("User Management clicks on {string} Card")
	public void user_management_clicks_on_card(String cardName) {
		settingpage.checkCardVisible(cardName);
		settingpage.clickOnCard(cardName);

	}

	@Then("User Management sees the Add User button")
	public void user_sees_the_add_user_button() {
		userpage.checkAddMemberButton();
	}

	@And("User adds {int} members and can see toast message as {string} for all added members")
	public void user_adds_members_and_can_see_toast_message_as_for_all_added_members(Integer usercount,
			String toastMessage) throws InterruptedException {
		for (int i = 1; i <= usercount; i++) {
			userpage.clickAddUserButton();
			userpage.clickTypeDropdown();
			userpage.clickNativeDropdownValue();
			userpage.fillUserId("UserId" + i);
			userpage.fillName("Name" + i);
			userpage.fillEmail("UserEmail" + i + "@testautomation.com");
			if (i <= 9) {
				userpage.fillPhoneNumber("100000000" + i);
			} else if (i > 9 && i < 100) {
				userpage.fillPhoneNumber("10000000" + i);
			} else if (i >= 100) {
				userpage.fillPhoneNumber("1000000" + i);
			}
			userpage.clickSaveButton();
			String actualMessage = userpage.userCreationToastMessage();
			Assertions.assertEquals(actualMessage, toastMessage, "User creation failed");
			userpage.closeToastMessage();
		}
	}

	@And("User adds {int} members using the api")
	public void user_adds_members_using_api(Integer usercount) throws IOException {
		for (int i = 1; i <= usercount; i++) {
			RestCaller.createNewUser("Name" + i);
		}
	}

	@And("User clicks on Edit icon")
	public void user_clicks_on_edit_icon() {
		userpage.clickOnEditUser();
	}

	@And("User clicks on Model dropdown")
	public void user_clicks_on_model_dropdown() {
		userpage.clickModelUserDropdown();
	}

	@Then("User selects {string} value in Model dropdown")
	public void user_selects_value_in_model_dropdown(String dropdownOption) {
		userpage.selectTokenValueDropdown(dropdownOption);
	}

	@And("User fills {string} value in Max Tokens field")
	public void user_fills_value_in_max_tokens_field(String value) {
		userpage.fillMaxTokensValue(value);
	}

	@And("User clicks on Frequency dropdown")
	public void user_clicks_on_frequency_dropdown() {
		userpage.clickFrequencyDropdown();
	}

	@And("User selects {string} value in Frequency dropdown")
	public void user_selects_value_in_frequency_dropdown(String dropdownValue) {
		userpage.selectWeeklyValueDropdown(dropdownValue);
	}

	@And("User clicks on save button")
	public void user_clicks_on_save_button() {
		userpage.clickSaveButton();
	}

	@Then("User sees the new model limit value as {string} on Member Settings page")
	public void user_sees_the_new_model_limit_value_as_on_member_settings_page(String expectedLimitValue)
			throws InterruptedException {
		String actualLimitValue = userpage.getModelLimitValue(expectedLimitValue);
		Assertions.assertEquals(expectedLimitValue, actualLimitValue);
	}

	@And("User sees the search button")
	public void user_sees_the_search_button() {
		userpage.checkSearchButton();
	}

	@And("User searches for the created user")
	public void user_searches_for_the_created_user() {
		userpage.searchUser();
	}

	@And("User clicks on Delete Selected button {int} times")
	public void user_clicks_on_delete_selected_button(int usercount) {
		if (usercount <= 25) {
			userpage.clickSelectAllButton();
			userpage.clickDeleteSelectedButton();
		} else {
			for (int i = 1; i <= (int) Math.ceil((double) usercount / 25); i++) {
				userpage.clickSelectAllButton();
				userpage.clickDeleteSelectedButton();
			}
		}
	}

	@Then("User sees delete toast message as {string}")
	public void user_sees_delete_toast_message_as(String toastMessage) {
		String actualMessage = userpage.userDeletionToastMessage();
		Assertions.assertEquals(actualMessage, toastMessage, "User deletion failed");
	}

	@Given("User adds {int} members with name {string}, userId {string}, password {string}, and email domain {string} and can see toast message as {string} for all added members")
	public void user_adds_members_with_name_user_id_password_and_email_domain_and_can_see_toast_message_as_for_all_added_members(
			Integer userCount, String baseName, String baseUserId, String password, String emailDomain,
			String toastMessage) throws InterruptedException {
		for (int i = 1; i <= userCount; i++) {
			String name = baseName + i;
			String userId = baseUserId + i;
			String email = baseUserId + i + "@" + emailDomain;
			userpage.clickAddUserButton();
			userpage.clickTypeDropdown();
			userpage.clickNativeDropdownValue();
			userpage.fillUserId(userId);
			userpage.fillName(name);
			userpage.fillEmail(email);
			if (i <= 9) {
				userpage.fillPhoneNumber("100000000" + i);
			} else if (i <= 99) {
				userpage.fillPhoneNumber("10000000" + i);
			} else {
				userpage.fillPhoneNumber("1000000" + i);
			}
			userpage.fillPassword(password);
			Thread.sleep(5000);
			userpage.clickSaveButton();

			String actualMessage = userpage.userCreationToastMessage();
			Assertions.assertEquals(toastMessage, actualMessage, "User creation failed for user " + userId);

			// store last created credentials in a static map or variable for later steps
			LastCreatedUser.setUserId(userId);
			LastCreatedUser.setPassword(password);
			LastCreatedUser.setName(name);
			LastCreatedUser.setEmail(email);

		}
	}

	@Given("User logs in with the last generated userId and password")
	public void user_logs_in_with_the_last_generated_user_id_and_password() {
		String username = LastCreatedUser.getUserId();
		String password = LastCreatedUser.getPassword();
		userpage.loginAsUser(username, password);
	}

	@Then("User can see that the displayed Name matches the generated name")
	public void user_can_see_that_the_displayed_name_matches_the_generated_name() throws InterruptedException {
		String generatedName = LastCreatedUser.getName();
		String actualName = userpage.getDisplayedName();
		System.out.println("Expected User name: " + generatedName);
		System.out.println("Actual User name: " + actualName);
		Assertions.assertEquals(generatedName, actualName, "Displayed User name does not match");
	}

	@Then("User can see that the displayed User ID matches the generated userId")
	public void user_can_see_that_the_displayed_user_id_matches_the_generated_user_id() throws InterruptedException {
		String generatedUserId = LastCreatedUser.getUserId();
		String actualUserId = userpage.getDisplayedId();
		System.out.println("Expected User ID: " + generatedUserId);
		System.out.println("Actual User ID: " + actualUserId);
		Assertions.assertEquals(generatedUserId, actualUserId, "Displayed User ID does not match");
	}

	@Then("User can see that the displayed Email matches the generated email")
	public void user_can_see_that_the_displayed_email_matches_the_generated_email() throws InterruptedException {
		String generatedEmail = LastCreatedUser.getEmail();
		String actualEmail = userpage.getDisplayedEmail();
		System.out.println("Expected Email: " + generatedEmail);
		System.out.println("Actual Email: " + actualEmail);
		Assertions.assertEquals(generatedEmail.toLowerCase(), actualEmail.toLowerCase(),
				"Displayed User Email does not match");
	}

	@When("User clicks on 'access_keys_allowed' value")
	public void user_clicks_on_access_keys_allowed_value() {
		userpage.clickOnAccessKeyValue();
	}

	@When("User change value of the key to 'true'")
	public void user_change_value_of_the_key_to_true() throws InterruptedException {
		userpage.updateAccessKeyValue("true");
	}

	@Then("User can see a toast message after updating values of {string} as {string}")
	public void user_can_see_toast_message(String sectionName, String expectedToastMessage) {
		String actualMessage = userpage.getToastMessage(expectedToastMessage);
		Assertions.assertEquals(expectedToastMessage, actualMessage, sectionName + " update failed");
		userpage.waitForToastMessageToDisappear(expectedToastMessage);
	}

	@When("User clicks on Save button of the configuration")
	public void user_clicks_on_save_button_of_the_configuration() {
		userpage.clickSaveButton();
	}

	@When("User clicks on Authentication dropdown")
	public void user_clicks_on_authentication_dropdown() {
		userpage.clickOnAuthenticationDropdown();
	}

	@When("User search {string} and select")
	public void user_search_and_select(String option) {
		userpage.searchAndSelectOption(option);
	}

}