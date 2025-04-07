package aicore.steps;

import org.junit.jupiter.api.Assertions;

import aicore.hooks.SetupHooks;
import aicore.pages.HomePage;
import aicore.pages.SettingPage;
import aicore.pages.UserManagementPage;
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

	@Given("User logs as Admin user in AI CORE application")
	public void user_logs_as_admin_user_in_ai_core_application() {
		homePage.checkOnOpenSetting();
	}

	@And("User navigates to the settings")
	public void user_navigates_to_the_settings() {
		homePage.clickOnOpenSetting();
	}

	@And("User enables admin mode")
	public void user_enables_admin_mode() {
		userpage.checkAdminButton();
		userpage.clickAdminButton();
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

}