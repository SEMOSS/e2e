package aicore.steps;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;

import com.microsoft.playwright.Page;

import aicore.base.GenericSetupUtils;
import aicore.framework.ConfigUtils;
import aicore.hooks.SetupHooks;
import aicore.pages.AddDatabasePage;
import aicore.pages.AddModelPage;
import aicore.pages.CatlogPermissionsPage;
import aicore.pages.HomePage;
import aicore.pages.LoginPage;
import aicore.utils.CommonUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class CatlogAccessStep {

	private HomePage homePage;
	protected static String timestamp;
	private CatlogPermissionsPage catlogpermission;
	private AddModelPage openModelPage;
	private AddDatabasePage addDatabaseToCatalogPage;

	public CatlogAccessStep() {
		new LoginPage(SetupHooks.getPage());
		this.homePage = new HomePage(SetupHooks.getPage());
		timestamp = CommonUtils.getTimeStampName();
		this.openModelPage = new AddModelPage(SetupHooks.getPage(), timestamp);
		this.catlogpermission = new CatlogPermissionsPage(SetupHooks.getPage());
		this.addDatabaseToCatalogPage = new AddDatabasePage(SetupHooks.getPage());
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

	@Then("{string} user {string} Delete Catalog")
	public void user_Delete_Model(String userRole, String expectedOutcome) {
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

	@Then("{string} user clicks on delete button and see the permission error toast message")
	public void userClicksDeleteAndVerifiesToast(String userRole) {
		openModelPage.clickOnDeleteButton();
		String toastText = catlogpermission.editorUserSeeToastMessageText();
		Assertions.assertTrue(toastText.contains(
				"user does not have permissions to delete the engine. User must be the owner to perform this function."),
				userRole + "Expected toast message not found. Actual message: " + toastText);
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

	@Then("{string} user can {string} Settings")
	public void user_can_See_Settings(String role, String action) {
		boolean canSeeSettings = catlogpermission.canSeeSettingOtion();
		if (action.equalsIgnoreCase("view")) {
			Assertions.assertTrue(canSeeSettings, role + " user cannot view the Settings option");
		} else if (action.equalsIgnoreCase("not view")) {
			Assertions.assertFalse(canSeeSettings, role + " user should not view the Settings option");
		} else {
			Assertions.fail("Invalid action: " + action);
		}
	}

	// Create database
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
	public void search_the_user_basedonrole(String role) {
		catlogpermission.searchUserBasedOnRole(role);
	}

	@Then("{string} user can {string} export button")
	public void user_can_See_ExportButton(String role, String action) {
		boolean viewExport = catlogpermission.canViewExportOption();
		if (action.equalsIgnoreCase("view")) {
			Assertions.assertTrue(viewExport, role + " user can not view the Export button");
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

	@Then("{string} user can {string} Members")
	public void user_Can_See_Member(String role, String action) {
		boolean viewMember = catlogpermission.userCanSeeMember();
		if (action.equalsIgnoreCase("view")) {
			Assertions.assertTrue(viewMember, role + " user can not view the Member Option");
		} else if (action.equalsIgnoreCase("not view")) {
			Assertions.assertFalse(viewMember, role + " user should not view the Member Option");
		} else {
			Assertions.fail("Invalid action: " + action);
		}

	}

	@And("{string} user can {string} General")
	public void user_Can_See_PendingRequests(String role, String action) {
		boolean viewPendingRequests = catlogpermission.userCanSeeGeneral();
		if (action.equalsIgnoreCase("view")) {
			Assertions.assertTrue(viewPendingRequests, role + " user can not view the Pending Requests Option");
		} else if (action.equalsIgnoreCase("not view")) {
			Assertions.assertFalse(viewPendingRequests, role + " user should not view the Pending Requests Option");
		} else {
			Assertions.fail("Invalid action: " + action);
		}
	}

	@And("{string} user can {string} Apps")
	public void user_Can_See_DataApps(String role, String action) {
		boolean viewDataApps = catlogpermission.userCanSeeApps();
		if (action.equalsIgnoreCase("view")) {
			Assertions.assertTrue(viewDataApps, role + " user can not view the Data Apps Option");
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
			Assertions.assertTrue(viewExportIcon, role + " user can not view the Data Apps Option");
		} else if (action.equalsIgnoreCase("not view")) {
			Assertions.assertFalse(viewExportIcon, role + " user should not view the Data Apps Option");
		} else {
			Assertions.fail("Invalid action: " + action);
		}
	}

	@Then("{string} user can see private toggle button as {string}")
	public void user_Can_See_Private_Toggle_Enable(String role, String action) {
		boolean viewMakePrivateToogle = catlogpermission.userCanSeeAndEnablePrivateToggle();
		if (action.equalsIgnoreCase("Enable")) {
			Assertions.assertTrue(viewMakePrivateToogle,
					role + " user can not see the Make see Private Toggle is Enabled");
		} else if (action.equalsIgnoreCase("Disable")) {
			Assertions.assertFalse(viewMakePrivateToogle, role + " user should not Enable Make see Private Toggle");
		} else {
			Assertions.fail("Invalid action: " + action);
		}
	}

	@And("User turn ON the Private option")
	public void user_TurnON_Private() {
		catlogpermission.setToggleStateForPrivate();
	}

	@And("{string} user can see toaster message is {string}")
	public void verify_ToasterMessage_Of_Toggle_Option(String role, String expectedPattern) {
		String actualMessage = catlogpermission.getToasterMessage();
		assertTrue(actualMessage.toLowerCase().matches(expectedPattern.toLowerCase()), role
				+ "User can turn ON the Toogle - Expected pattern: " + expectedPattern + ", but got: " + actualMessage);
	}

	@And("User turn OFF the Private option")
	public void author_TurnOFF_Private() {
		catlogpermission.setToggleStateForPrivate();
	}

	@Then("{string} user can see Non-Discoverable toggle button as {string}")
	public void user_Can_See_Non_Discoverable_Toggle_Enable(String role, String action) {
		boolean viewNonDiscovrableToogle = catlogpermission.userCanSeeAndEnableNonDiscovrableToggle();
		if (action.equalsIgnoreCase("Enable")) {
			Assertions.assertTrue(viewNonDiscovrableToogle,
					role + " user can't see the Make see Non- Discovrable Toggle is Enabled");
		} else if (action.equalsIgnoreCase("Disable")) {
			Assertions.assertFalse(viewNonDiscovrableToogle,
					role + " user should not Enable Make see Non- Discovrable Toggle");
		} else {
			Assertions.fail("Invalid action: " + action);
		}
	}

	@And("User turn ON the Non Discoverable option")
	public void user_TurnOn_NonDiscoverable() {
		catlogpermission.setToggleStateForNonDiscovrable();
	}

	@And("User turn OFF the Non Discoverable option")
	public void user_TurnOFF_NonDiscoverable() {
		catlogpermission.setToggleStateForNonDiscovrable();
	}

	@Then("{string} user can {string} Delete catalog option")
	public void user_Can_See_Delete_Model_Option(String role, String action) {
		boolean viewDeleteCatalogOption = catlogpermission.userCanSeeDeleteCatalogOption();
		if (action.equalsIgnoreCase("view")) {
			Assertions.assertTrue(viewDeleteCatalogOption, role + " user not view the Delete Model Option");
		} else if (action.equalsIgnoreCase("not view")) {
			Assertions.assertFalse(viewDeleteCatalogOption, role + " user should not view Delete Model Option");
		} else {
			Assertions.fail("Invalid action: " + action);
		}
	}

	@And("{string} user Edit option should be {string}")
	public void user_Can_See_EditOptionIcon(String role, String action) {
		boolean editOption = catlogpermission.canSeeEditOtion();
		if (action.equalsIgnoreCase("Enable")) {
			Assertions.assertTrue(editOption, role + " user can not view the Edit Option");
		} else if (action.equalsIgnoreCase("Disable")) {
			Assertions.assertFalse(editOption, role + " user should not view the Edit Option");
		} else {
			Assertions.fail("Invalid action: " + action);
		}
	}

	@And("User clicks On Copy Catalog ID")
	public void user_clicks_on_Copy_Catalog_ID() {
		catlogpermission.getCatalogAndCopyId();
	}

	@Then("User can see the Catalog title as {string}")
	public void User_can_see_the_Catalog_title_as(String dbName) {
		boolean isTitleVisible = addDatabaseToCatalogPage.verifyDatabaseTitle(dbName);
		Assertions.assertTrue(isTitleVisible, "Database title is not visible");
	}

	// As per New UI of setting page
	@And("User Click on Members setting option")
	public void userClickOnMemberSettingOption() {
		catlogpermission.clickOnMemberSettingOption();
	}

	@And("User Click on General setting option")
	public void userClickOnGeneralSettingOption() {
		catlogpermission.clickOnGeneralSettingOption();
	}

	@And("Editor user not able to Delete Catalog")
	public void editorUserNotAbleToDeleteCatalog() {
		openModelPage.clickOnDeleteButton();
		String toastMessage = catlogpermission.editorUserSeeToastMessageText();
		String expectedPart = "does not exist or user does not have permissions to delete the project. "
				+ "User must be the owner to perform this function.";
		Assertions.assertTrue(toastMessage.contains(expectedPart),
				"Expected toast message to contain: " + expectedPart + " but got: " + toastMessage);
	}

	@And("{string} user can {string} private toggle button")
	public void user_can_see_private_toggle_button(String role, String action) {
		boolean viewMakePrivateToogle = catlogpermission.userCanSeeAndEnablePrivateToggle();
		if (action.equalsIgnoreCase("see")) {
			Assertions.assertTrue(viewMakePrivateToogle, role + " user can not see the Make see Private Toggle");
		} else if (action.equalsIgnoreCase("not see")) {
			Assertions.assertFalse(viewMakePrivateToogle, role + " user should not see Make see Private Toggle");
		} else {
			Assertions.fail("Invalid action: " + action);
		}
	}

	@And("{string} user can {string} Non-Discoverable toggle button")
	public void user_can_see_non_discoverable_toggle_button(String role, String action) {
		boolean viewNonDiscovrableToogle = catlogpermission.userCanSeeAndEnableNonDiscovrableToggle();
		if (action.equalsIgnoreCase("see")) {
			Assertions.assertTrue(viewNonDiscovrableToogle,
					role + " user can not see the Make see Non- Discovrable Toggle");
		} else if (action.equalsIgnoreCase("not see")) {
			Assertions.assertFalse(viewNonDiscovrableToogle,
					role + " user should not see Make see Non- Discovrable Toggle");
		} else {
			Assertions.fail("Invalid action: " + action);
		}
	}
}
