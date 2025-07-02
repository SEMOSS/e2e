package aicore.steps;

import org.junit.jupiter.api.Assertions;

import com.microsoft.playwright.Page;

import aicore.base.GenericSetupUtils;
import aicore.hooks.SetupHooks;
import aicore.pages.AddModelPage;
import aicore.pages.CatlogPermissionsPage;
import aicore.pages.HomePage;
import aicore.pages.LoginPage;
import aicore.utils.CommonUtils;
import aicore.utils.ConfigUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.messages.types.Exception;

public class CatlogAccessStep {

	private HomePage homePage;
	protected static String timestamp;
	private CatlogPermissionsPage catlogpermission;
	private AddModelPage openModelPage;

	public CatlogAccessStep() {
		new LoginPage(SetupHooks.getPage());
		this.homePage = new HomePage(SetupHooks.getPage());
		timestamp = CommonUtils.getTimeStampName();
		this.openModelPage = new AddModelPage(SetupHooks.getPage(), timestamp);
		this.catlogpermission = new CatlogPermissionsPage(SetupHooks.getPage());

	}

	@Then("{string} user can {string} Overview")
	public void user_can_view_overview(String role, String action) {
		// Check visibility of the Overview tab for different roles
		boolean canSeeOverview = catlogpermission.canViewOverview();
		if (action.equalsIgnoreCase("view")) {
			// Assert that the user can see the Overview tab if action is "see"
			Assertions.assertTrue(canSeeOverview, role + " user cannot view the Overview tab");
		} else if (action.equalsIgnoreCase("not view")) {
			// Assert that the user cannot see the Overview tab if action is "not see"
			Assertions.assertTrue(canSeeOverview, role + " user should not view the Overview tab");
		} else {
			Assertions.fail("Invalid action: " + action);
		}
	}

	@Then("{string} user can {string} Usage")
	public void user_can_usage(String role, String action) {
		boolean canSeeUsage = catlogpermission.canViewUsage();
		if (action.equalsIgnoreCase("view")) {
			Assertions.assertTrue(canSeeUsage, role + " user cannot view the Usage tab");
		} else if (action.equalsIgnoreCase("not view")) {
			Assertions.assertFalse(canSeeUsage, role + " user should not view the Usage tab");
		} else {
			Assertions.fail("Invalid action: " + action);
		}
	}

	@Then("{string} user can {string} SMSS Details")
	public void user_can_smss_details(String role, String action) {
		boolean canSeeSMSS = catlogpermission.canViewSMSSDetails();
		if (action.equalsIgnoreCase("view")) {
			Assertions.assertTrue(canSeeSMSS, role + " user cannot view the SMSS tab");
		} else if (action.equalsIgnoreCase("not view")) {
			Assertions.assertFalse(canSeeSMSS, role + " user should not view the SMSS tab");
		} else {
			Assertions.fail("Invalid action: " + action);
		}
	}

	@Then("{string} user can {string} Edit SMSS")
	public void user_can_edit_smss(String role, String action) {
		boolean canSeeEditSMSS = catlogpermission.canViewEditSMSS();
		if (action.equalsIgnoreCase("view")) {
			Assertions.assertTrue(canSeeEditSMSS, role + " user cannot view the Edit SMSS button");
		} else if (action.equalsIgnoreCase("not view")) {
			assertFalse(canSeeEditSMSS, role + " user should not view the Edit SMSS button");
		} else {
			Assertions.fail("Invalid action: " + action);
		}
	}

	@Then("{string} user can {string} Access Control")
	public void user_can_Access_Control(String role, String action) {
		boolean canSeeSettings = catlogpermission.canViewAccessControl();
		if (action.equalsIgnoreCase("view")) {
			Assertions.assertTrue(canSeeSettings, role + " user cannot view the Settings tab");
		} else if (action.equalsIgnoreCase("not view")) {
			Assertions.assertFalse(canSeeSettings, role + " user should not view the Settings tab");
		} else {
			Assertions.fail("Invalid action: " + action);
		}
	}

	@Then("User clicks on Add Member button")
	public void user_clicks_on_add_member_button() {
		openModelPage.clickOnAddMembersButton();
	}

	@Then("User adds one user and assigns them as {string}")
	public void user_adds_one_user_and_assigns_them_as(String role) throws InterruptedException {
		openModelPage.addMember(role, GenericSetupUtils.useDocker());
	}

	@Then("User logs out from the application")
	public void user_logs_out_from_the_application() {
		homePage.logout();
	}

	@Given("User login as {string}")
	public void user_login_as(String role) throws Exception {
		String username = ConfigUtils.getValue(role.toLowerCase() + "_username");
		String password = ConfigUtils.getValue(role.toLowerCase() + "_password");

		if (username == null || password == null) {
			throw new Exception("Login credentials not found for role: " + role);
		}

		Page page = SetupHooks.getPage();
		GenericSetupUtils.login(page, username, password);
	}

	@Given("{string} user clicks on Access Control")
	public void user_clicks_on_access_control(String role) {
		openModelPage.clickOnAccessControl();

	}

	@Given("{string} user clicks on Settings")
	public void user_clicks_on_settings(String role) {
		openModelPage.clickOnAccessControl();
	}

	@Then("{string} user {string} Delete Model")
	public void userDeleteModel(String userRole, String expectedOutcome) {
		// Perform delete action
		openModelPage.clickOnDeleteButton();
		if ("can".equalsIgnoreCase(expectedOutcome)) {
			// Verify only success message appears
			Assertions.assertTrue(openModelPage.isDeleteSuccessful(),
					userRole + " should be able to delete the model, but permission error appeared.");
			System.out.println(userRole + " successfully deleted the model.");
		} else if ("can not".equalsIgnoreCase(expectedOutcome)) {
			// Verify only permission error appears
			Assertions.assertTrue(openModelPage.isPermissionErrorDisplayed(),
					userRole + " should not be able to delete the model, but success message appeared.");
			System.out.println(userRole + " does not have permission to delete the model.");
		} else {
			throw new IllegalArgumentException("Invalid expected outcome: " + expectedOutcome);
		}
	}

	@Then("{string} user {string} see Member Setting")
	public void user_see_member_setting(String userRole, String expectedOutcome) {
		boolean isButtonVisible = openModelPage.isAddMemberButtonVisible();
		if ("can".equalsIgnoreCase(expectedOutcome)) {
			// Assert that the button is visible
			Assertions.assertTrue(isButtonVisible,
					userRole + " should be able to see the Add Member button, but it is not visible.");
			System.out.println(userRole + " can see the Add Member button.");
		} else if ("can not".equalsIgnoreCase(expectedOutcome)) {
			// Assert that the button is NOT visible
			Assertions.assertFalse(isButtonVisible,
					userRole + " should NOT be able to see the Add Member button, but it is visible.");
			System.out.println(userRole + " cannot see the Add Member button.");
		} else {
			throw new IllegalArgumentException("Invalid expected outcome: " + expectedOutcome);
		}
	}

	@Given("User deletes the {string} user")
	public void user_deletes_the_user(String role) {
		openModelPage.deleteAddedMember(role);
	}

	// new
	@Then("{string} user can {string} Metadata")
	public void user_can_metadata(String role, String action) {
		boolean canSeeMetada = catlogpermission.canViewMetadata();
		if (action.equalsIgnoreCase("view")) {
			Assertions.assertTrue(canSeeMetada, role + " user cannot view the Metadata tab");
		} else if (action.equalsIgnoreCase("not view")) {
			Assertions.assertFalse(canSeeMetada, role + " user should not view the Metadata tab");
		} else {
			Assertions.fail("Invalid action: " + action);
		}
	}

	@And("User Search {string} user from Access Control")
	public void search_the_user_basedonrole(String role) throws InterruptedException {
		catlogpermission.searchUserBasedOnRole(role);
		Thread.sleep(2000);
	}

	@Then("{string} user can {string} export button")
	public void user_can_See_ExportButton(String role, String action) {
		boolean viewExport = catlogpermission.canViewExportOption();
		if (action.equalsIgnoreCase("view")) {
			Assertions.assertTrue(viewExport, role + " user view the Export button");
		} else if (action.equalsIgnoreCase("not view")) {
			Assertions.assertFalse(viewExport, role + " user should not view the Export button");
		} else {
			Assertions.fail("Invalid action: " + action);
		}
	}

	// Create App Steps
	@And("User click on Settings")
	public void user_Click_OnSettings() {
		catlogpermission.clickOnSettings();
	}

	@And("User clicks on Delete Model")
	public void user_Delete_Model() {
		catlogpermission.userDeleteModel();
	}

	@Then("{string} user can {string} Member")
	public void user_Can_See_Member(String role, String action) {
		boolean viewMember = catlogpermission.userCanSeeMember();
		if (action.equalsIgnoreCase("view")) {
			Assertions.assertTrue(viewMember, role + " user view the Member Option");
		} else if (action.equalsIgnoreCase("not view")) {
			Assertions.assertFalse(viewMember, role + " user should not view the Member Option");
		} else {
			Assertions.fail("Invalid action: " + action);
		}

	}

	@And("{string} user can {string} Pending Requests")
	public void user_Can_See_PendingRequests(String role, String action) {
		boolean viewPendingRequests = catlogpermission.userCanSeePendingRequests();
		if (action.equalsIgnoreCase("view")) {
			Assertions.assertTrue(viewPendingRequests, role + " user view the Pending Requests Option");
		} else if (action.equalsIgnoreCase("not view")) {
			Assertions.assertFalse(viewPendingRequests, role + " user should not view the Pending Requests Option");
		} else {
			Assertions.fail("Invalid action: " + action);
		}
	}

	@And("{string} user can {string} Data Apps")
	public void user_Can_See_DataApps(String role, String action) {
		boolean viewDataApps = catlogpermission.userCanSeeDataApps();
		if (action.equalsIgnoreCase("view")) {
			Assertions.assertTrue(viewDataApps, role + " user view the Data Apps Option");
		} else if (action.equalsIgnoreCase("not view")) {
			Assertions.assertFalse(viewDataApps, role + " user should not view the Data Apps Option");
		} else {
			Assertions.fail("Invalid action: " + action);
		}
	}

	@And("{string} user can {string} Export Icon")
	public void user_Can_See_ExportIcon(String role, String action) {
		boolean viewExportIcon = catlogpermission.userCanSeeExportIcon();
		if (action.equalsIgnoreCase("view")) {
			Assertions.assertTrue(viewExportIcon, role + " user view the Data Apps Option");
		} else if (action.equalsIgnoreCase("not view")) {
			Assertions.assertFalse(viewExportIcon, role + " user should not view the Data Apps Option");
		} else {
			Assertions.fail("Invalid action: " + action);
		}
	}

	@Then("{string} user Make Public toggle should be {string}")
	public void user_Can_See_MakePulic_Toggle_Enable(String role, String action) {

	}
}
