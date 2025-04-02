package aicore.steps;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;

import aicore.hooks.SetupHooks;
import aicore.pages.HomePage;
import aicore.pages.UserManagementPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserManagementSteps {

	private HomePage homePage;
	private UserManagementPage userpage;

	public UserManagementSteps() {
		this.homePage = new HomePage(SetupHooks.getPage());
		this.userpage = new UserManagementPage(SetupHooks.getPage());
	}

	@Given("User logs as Admin user in AI CORE application")
	public void user_is_on_application_with_admin() throws IOException {
		homePage.checkOnOpenSetting();
	}

	@When("User navigates to the settings")
	public void user_clicks_on_setting_button() throws IOException {
		homePage.clickOnOpenSetting();
	}

	@And("User enables admin mode")
	public void user_is_on_seting_page() throws IOException {
		if (userpage.checkAdminButton()) {
			userpage.clickAdminButton();
		} else {
			System.out.println("Admin button is not visible");
		}
	}

	@And("User clicks Member Settings")
	public void user_select_member_setting_tile() throws IOException {
		if (userpage.checkMemberSettingPageTile()) {
			userpage.clickMemberSettingPageTile();
		} else {
			System.out.println("Admin mode is not ON Or Member Setting card is not visible");
		}
	}

	@And("User should see Admin on")
	public void user_can_see_Admin_on() throws IOException {
		userpage.checkAdminOnButton();
	}

	@Then("User sees the Add User button")
	public void user_can_see_Add_user_button() throws IOException {
		userpage.checkAddMemberButton();
	}

	@And("User clicks on Add User button")
	public void user_click_on_AddUser_button() throws IOException {
		userpage.clickAddUserButton();
	}

	@And("User clicks on Type dropdown")
	public void user_click_on_Type_dropdown() throws IOException {
		userpage.clickTypeDropdown();
	}

	@And("User selects {string} value from Type dropdown")
	public void user_click_on_Native_value_from_dropdown(String dropdown) throws IOException {
		userpage.clickNativeDropdownValue();
	}

	@And("User fills UserId value in UserId field")
	public void user_fills_userId(String UserId) throws IOException, InterruptedException {
		userpage.fillUserId(UserId);
	}

	@And("User fills Username value in Username field")
	public void user_fills_username(String Username) throws IOException, InterruptedException {
		userpage.fillUsername(Username);
	}

	@And("User fills Name value in Name field")
	public void user_fills_name(String Name) throws IOException, InterruptedException {
		userpage.fillName(Name);
	}

	@And("User fills Email value in Email field")
	public void user_fills_email(String Email) throws IOException, InterruptedException {
		userpage.fillEmail(Email);
	}

	@And("User fills Phone Number in Phone Number field")
	public void user_fills_phone_number(String Number) throws IOException, InterruptedException {
		userpage.fillPhoneNumber(Number);
	}

	@And("User fills {string} in Extension field")
	public void user_fills_extension(String Extension) throws IOException, InterruptedException {
		userpage.fillExtension(Extension);
	}

	@And("User clicks on Save Button")
	public void user_clicks_save_button() throws IOException, InterruptedException {
		userpage.clickSaveButton();
	}

	@And("User adds {int} members and can see toast message as {string} for all added members")
	public void user_adds_members_and_can_see_toast_message_as_for_all_added_members(Integer usercount,
			String toastMessage) throws InterruptedException {
		for (int i = 1; i <= usercount; i++) {
			userpage.clickAddUserButton();
			userpage.clickTypeDropdown();
			userpage.clickNativeDropdownValue();
			userpage.fillUserId("UserId" + i);
			userpage.fillUsername("Username" + i);
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

}