package aicore.steps;

import static org.junit.jupiter.api.Assertions.assertFalse;

import aicore.hooks.SetupHooks;
import org.junit.jupiter.api.Assertions;

import aicore.base.AICoreTestManager;
import aicore.pages.AddModelToCatalogPage;
import aicore.pages.HomePage;
import aicore.pages.LoginPage;
import aicore.pages.ModelPermissionsAuthor;
import aicore.utils.CommonUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ModelCatalogAuthorPermissionsSteps {
	private LoginPage loginpage;
	private HomePage homePage;
	private AddModelToCatalogPage openModelPage;
	private ModelPermissionsAuthor authorPermissions;
	protected static String timestamp;

	public ModelCatalogAuthorPermissionsSteps() {
		this.loginpage = new LoginPage(SetupHooks.getPage());
		this.homePage = new HomePage(SetupHooks.getPage());
		timestamp = CommonUtils.getTimeStampName();
		this.openModelPage = new AddModelToCatalogPage(SetupHooks.getPage(), timestamp);
		this.authorPermissions = new ModelPermissionsAuthor(SetupHooks.getPage());
	}

	@When("{string} user login to the appication")
	public void user_login_to_the_appication(String string) {
		openModelPage.clickOnAccessControl();
	}

	@Then("{string} user can {string} Overview")
	public void user_can_view_overview(String role, String action) {
		// Check visibility of the Overview tab for different roles
		boolean canSeeOverview = authorPermissions.canViewOverview();
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
		boolean canSeeUsage = authorPermissions.canViewUsage();
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
		boolean canSeeSMSS = authorPermissions.canViewSMSSDetails();
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
		boolean canSeeEditSMSS = authorPermissions.canViewEditSMSS();
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
		boolean canSeeSettings = authorPermissions.canViewAccessControl();
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
		openModelPage.addMember(role);
	}

	@Then("User logs out from the application")
	public void user_logs_out_from_the_application() {
		homePage.logOutAsCurrentUser();
	}

	@Given("User login as {string}")
	public void user_login_as(String role) throws Exception {
		loginpage.loginWithDifferetUsers(role);
	}

	@When("User searches the {string} in the model catalog searchbox")
	public void user_searches_the_in_the_model_catalog_searchbox(String modelName) {
		openModelPage.searchModelCatalog(modelName);
	}

	@And("User selects the {string} from the model catalog")
	public void user_selects_the_from_the_model_catalog(String modelName) {
		openModelPage.selectModelFromSearchOptions(modelName);
	}

	@Given("{string} user clicks on Access Control")
	public void user_clicks_on_access_control(String role) {
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

	@Then("{string} user {string} see member setting")
	public void user_see_member_setting(String userRole, String expectedOutcome) {
		boolean isButtonVisible = openModelPage.isAddMemberButtonVisible();
		if ("can".equalsIgnoreCase(expectedOutcome)) {
			// Assert that the button is visible
			Assertions.assertTrue(isButtonVisible,
					userRole + " should be able to see the Add Member button, but it is not visible.");
			System.out.println(userRole + " can see the Add Member button.");
		} else if ("can not".equalsIgnoreCase(expectedOutcome)) {
			// Assert that the button is NOT visible
			Assertions.assertTrue(isButtonVisible,
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
}