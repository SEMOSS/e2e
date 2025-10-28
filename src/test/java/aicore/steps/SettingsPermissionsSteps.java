package aicore.steps;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;

import aicore.base.GenericSetupUtils;
import aicore.hooks.SetupHooks;
import aicore.pages.SettingsPermissionsPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SettingsPermissionsSteps {
	private SettingsPermissionsPage catalogSettingsPage;
	public SettingsPermissionsSteps()
	{
	 catalogSettingsPage=new SettingsPermissionsPage(SetupHooks.getPage());
	}
	@When("User selects the {string} card")
	public void user_selects_the_card(String string) {
		catalogSettingsPage.selectCard(string);
	}

	@When("User can serach {string} in search box")
	public void user_can_serach_in_search_box(String string) {
		catalogSettingsPage.searchCatalog(string);
	}

	@When("User clicks on the {string}")
	public void user_clicks_on_the(String string) {
		catalogSettingsPage.clickOnCatalogCard(string);
	}

	@Then("User should see users with following permissions")
	public void user_should_see_users_with_following_permissions(DataTable dataTable) {
		List<Map<String, String>> userRoleData = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> row : userRoleData) {
	        String userType = row.get("USER_TYPE");
	        String expectedRole = row.get("ROLE");
	        catalogSettingsPage.searchUserInUserSearchbar(userType);
	       boolean isRadioButtonSelected = catalogSettingsPage.validateUserPermissions(expectedRole);
		Assertions.assertTrue(isRadioButtonSelected, "for " + userType+"user "+expectedRole+ " radio button is selected");
		}
	}

	@Then("user can see permission date as current date")
	public void user_can_see_permission_date_as_current_date() {
	
	}

	@When("{string} user changes {string} user role to {string}")
	public void user_changes_user_role_to(String string, String string2, String role) {
	 catalogSettingsPage.selectUserRole(role);
	}

	@Then("User should see role changed to {string} in members list")
	public void user_should_see_role_changed_to_in_members_list(String role) {
		 boolean isRadioButtonSelected = catalogSettingsPage.validateUserPermissions(role);
		Assertions.assertTrue(isRadioButtonSelected, "Role not changed to "+role);
	}

	@When("{string} user deletes {string} role user from members list")
	public void user_deletes_role_user_from_members_list(String user, String role) {
	   catalogSettingsPage.deleteUserFromMembersList(role); 
	}

	@Then("User should see {string} role user is removed from members list")
	public void user_should_see_user_is_removed_from_members_list(String role) {
	  boolean isUserRemoved=catalogSettingsPage.verifyUserIsExists(role);
	  Assertions.assertFalse(isUserRemoved, role + " user is not removed from members list");
	}

    @When("User search for {string} user in members search box")
	public void user_search_for_user_in_members_search_box(String userType) {
		catalogSettingsPage.searchUserInUserSearchbar(userType);
	}
    @Then("{string} user cannot change the {string} user role and sees {string} user in members list")
    public void user_cannot_change_the_user_role_and_sees_user_in_members_list(String user, String user1, String role) {
    	 boolean isUserExists=catalogSettingsPage.verifyUserIsExists(role);
   	  Assertions.assertTrue(isUserExists, role + " user is removed from members list");
    }

    @Then("{string} user cannot delete the {string} user and sees {string} user in members list")
    public void user_cannot_delete_the_user_and_sees_user_in_members_list(String user, String user1, String role) {
    	boolean isUserExists=catalogSettingsPage.verifyUserIsExists(role);
     	  Assertions.assertTrue(isUserExists, role + " user is removed from members list");
    }

	@Then("{string} user can see {string} button is disabled")
	public void user_can_see_button_is_disabled(String string, String string2) {
	    
	}

	@Then("User can see {string} user with {string} role in members list")
	public void user_can_see_user_with_role_in_members_list(String string, String string2) {
	    
	}
}
