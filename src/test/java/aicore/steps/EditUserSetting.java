package aicore.steps;

import aicore.hooks.SetupHooks;
import aicore.pages.UserManagementPage;
import aicore.pages.EditUser;
import aicore.pages.HomePage;
import io.cucumber.java.en.And;

public class EditUserSetting {

	private HomePage homePage;
	private UserManagementPage userpage;
	private EditUser edituser;

	public EditUserSetting() {

		this.homePage = new HomePage(SetupHooks.getPage());
		this.userpage = new UserManagementPage(SetupHooks.getPage());
		this.edituser = new EditUser(SetupHooks.getPage());
	}

//	@Given("User logs as Admin user in AI CORE application")
//	public void user_is_on_application_with_admin() throws IOException {
//		homePage.checkSetting();
//	}
//
//	@When("User navigates to the settings")
//	public void user_clicks_on_setting_button() throws IOException {
//		homePage.clickOnSetting();
//	}
//
//	@And("User enables admin mode")
//	public void user_is_on_seting_page() throws IOException {
//		if (userpage.checkAdminButton()) {
//			userpage.clickAdminButton();
//		} else {
//			System.out.println("Admin button is not visible");
//		}
//	}
//
//	@And("User clicks Member Settings")
//	public void user_select_member_setting_tile() throws IOException {
//		if (userpage.checkMemberSettingPageTile()) {
//			userpage.clickMemberSettingPageTile();
//		} else {
//			System.out.println("Admin mode is not ON Or Member Setting card is not visible");
//		}
//	}
//
//	@And("User should see Admin on")
//	public void user_can_see_Admin_on() throws IOException {
//		userpage.checkAdminOnButton();
//	}
//
//	@Then("User sees the Add User button")
//	public void user_can_see_Add_user_button() throws IOException {
//		userpage.checkAddMemberButton();
//	}
//
//	@And("User clicks on Add User button")
//	public void user_click_on_AddUser_button() throws IOException {
//		userpage.clickAddUserButton();
//	}
//
//	@And("User clicks on Type dropdown")
//	public void user_click_on_Type_dropdown() throws IOException {
//		userpage.clickTypeDropdown();
//	}
//
//	@And("User selects {string} value from Type dropdown")
//	public void user_click_on_Native_value_from_dropdown(String dropdown) throws IOException {
//		userpage.clickNativeDropdownValue();
//	}
//
//	@And("User fills UserId value in UserId field")
//	public void user_fills_userId(String UserId) throws IOException, InterruptedException {
//		userpage.fillUserId(UserId);
//	}
//
//	@And("User fills Username value in Username field")
//	public void user_fills_username(String Username) throws IOException, InterruptedException {
//		userpage.fillUsername(Username);
//	}
//
//	@And("User fills Name value in Name field")
//	public void user_fills_name(String Name) throws IOException, InterruptedException {
//		userpage.fillName(Name);
//	}
//
//	@And("User fills Email value in Email field")
//	public void user_fills_email() throws IOException, InterruptedException {
//		userpage.fillEmail();
//	}
//
//	@And("User fills Phone Number in Phone Number field")
//	public void user_fills_phone_number() throws IOException, InterruptedException {
//		userpage.fillPhoneNumber();
//	}
//
//	@And("User fills {string} in Extension field")
//	public void user_fills_extension(String Extension) throws IOException, InterruptedException {
//		userpage.fillExtension(Extension);
//	}
//
//	@And("User clicks on Save Button")
//	public void user_clicks_save_button() throws IOException, InterruptedException {
//		userpage.clickSaveButton();
//	}
//
//	@And("User adds {int} member")
//	public void user_adds_one_member(int usercount) throws InterruptedException {
//		for (int i = 1; i <= usercount; i++) {
//			userpage.clickAddUserButton();
//			userpage.clickTypeDropdown();
//			userpage.clickNativeDropdownValue();
//			userpage.fillUserId("UserId" + i);
//			userpage.fillUsername("Username" + i);
//			userpage.fillName("Name" + i);
//			userpage.fillEmail();
//			userpage.fillPhoneNumber();
//			userpage.clickSaveButton();
//
//		}
//	}
//
//	@Then("User can see toast message as {string}")
//	public void user_can_see_toast_message_as(String toastMessage) {
//		String actualMessage = userpage.userCreationToastMessage();
//		Assertions.assertEquals(actualMessage, toastMessage, "User creation failed");
//	}

	@And("User clicks on Edit icon")
	public void user_clicks_on_edit_icon() throws InterruptedException {
		edituser.editUser();
	}

	@And("User clicks on Model dropdown")
	public void user_clicks_on_model_dropdown() throws InterruptedException {
		edituser.clickModelUserDropdown();
	}

	@And("User selects {string} value in Model dropdown")
	public void user_clicks_on_value(String string) throws InterruptedException {
		edituser.clickTokenValueDropdown();
	}

	@And("User fills {string} value in Max Tokens field")
	public void user_fills_value_in_max_tokens_field(String value) throws InterruptedException {
		edituser.fillMaxTokensValue(value);
	}

	@And("User clicks on Frequency dropdown")
	public void user_clicks_on_frequency_dropdown() throws InterruptedException {
		edituser.clickFrequencyDropdown();
	}

}