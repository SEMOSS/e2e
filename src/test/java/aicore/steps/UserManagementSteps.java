package aicore.steps;

import org.junit.jupiter.api.Assertions;

import aicore.hooks.SetupHooks;
import aicore.pages.HomePage;
import aicore.pages.UserManagementPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class UserManagementSteps {

	private HomePage homePage;
	private UserManagementPage userpage;

	public UserManagementSteps() {
		this.homePage = new HomePage(SetupHooks.getPage());
		this.userpage = new UserManagementPage(SetupHooks.getPage());
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
		if (userpage.checkAdminButton()) {
			userpage.clickAdminButton();
		} else {
			System.out.println("Admin button is not visible");
		}
	}

	@And("User clicks Member Settings")
	public void user_clicks_member_settings() {
		if (userpage.checkMemberSettingPageTile()) {
			userpage.clickMemberSettingPageTile();
		} else {
			System.out.println("Admin mode is not ON Or Member Setting card is not visible");
		}
	}

	@Then("User sees the Add User button")
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