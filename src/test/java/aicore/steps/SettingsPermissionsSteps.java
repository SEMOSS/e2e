package aicore.steps;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;

import aicore.hooks.SetupHooks;
import aicore.pages.SettingsPermissionsPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SettingsPermissionsSteps {
	private SettingsPermissionsPage catalogSettingsPage;

	public SettingsPermissionsSteps() {
		catalogSettingsPage = new SettingsPermissionsPage(SetupHooks.getPage());
	}

	@When("User selects the {string} card")
	public void user_selects_the_card(String string) {
		catalogSettingsPage.selectCard(string);
	}

	@When("User can search {string} in search box")
	public void user_can_search_in_search_box(String string) {
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
			Assertions.assertTrue(isRadioButtonSelected,
					"for " + userType + "user " + expectedRole + " radio button is selected");
		}
	}

	// @When("user can see {string} permission date along with added time")
	// public void user_can_see_permission_date_along_with_added_time(String role) {
	// String expectedTimeWithDate = catalogSettingsPage.getCurrentUtcTime();
	// String actualTimeWithDate = catalogSettingsPage.getUserPermissionDate(role);
	// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd
	// HH:mm:ss");

	// // Parse both strings into LocalDateTime
	// LocalDateTime expected = LocalDateTime.parse(expectedTimeWithDate,
	// formatter);
	// LocalDateTime actual = LocalDateTime.parse(actualTimeWithDate, formatter);

	// // Calculate difference in seconds
	// long secondsDiff = Math.abs(java.time.Duration.between(expected,
	// actual).getSeconds());
	// Assertions.assertTrue(secondsDiff <= 30,
	// "Permission time differs by more than 1 minute (difference = " + secondsDiff
	// + "s)");
	// }

	@Then("user can see permission date along with user added time")
	public void user_can_see_permission_date_along_with_user_added_time(DataTable dataTable) {
		List<String> roles = dataTable.asList(String.class);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		for (String role : roles) {
			String expectedTimeWithDate = catalogSettingsPage.getCurrentUtcTime();
			catalogSettingsPage.searchUserInUserSearchbar(role);
			String actualTimeWithDate = catalogSettingsPage.getUserPermissionDate(role);

			// Parse both strings into LocalDateTime
			LocalDateTime expected = LocalDateTime.parse(expectedTimeWithDate, formatter);
			LocalDateTime actual = LocalDateTime.parse(actualTimeWithDate, formatter);

			// Calculate difference in seconds
			long secondsDiff = Math.abs(java.time.Duration.between(expected, actual).getSeconds());
			Assertions.assertTrue(secondsDiff <= 30, "For role '" + role
					+ "' permission time differs by more than 30 seconds (difference = " + secondsDiff + "s)");
		}

	}

	@When("{string} user changes {string} role to {string}")
	public void user_changes_role_to(String user, String currentRole, String newRole) {
		catalogSettingsPage.changeUserRole(currentRole, newRole);
	}

	@Then("User should see role changed to {string} in members list")
	public void user_should_see_role_changed_to_in_members_list(String role) {
		boolean isRadioButtonSelected = catalogSettingsPage.validateUserPermissions(role);
		Assertions.assertTrue(isRadioButtonSelected, "Role not changed to " + role);
	}

	@When("{string} user deletes {string} role user from members list")
	public void user_deletes_role_user_from_members_list(String user, String role) {
		catalogSettingsPage.deleteUserFromMembersList(role);
	}

	@Then("User should see {string} role user is removed from members list")
	public void user_should_see_user_is_removed_from_members_list(String role) {
		boolean isUserRemoved = catalogSettingsPage.verifyUserIsExists(role);
		Assertions.assertFalse(isUserRemoved, role + " user is not removed from members list");
	}

	@When("User search for {string} user in members search box")
	public void user_search_for_user_in_members_search_box(String userType) {
		catalogSettingsPage.searchUserInUserSearchbar(userType);
	}

	@Then("{string} user cannot change the {string} user role and sees {string} user in members list")
	public void user_cannot_change_the_user_role_and_sees_user_in_members_list(String user, String userType,
			String role) {
		boolean isUserExists = catalogSettingsPage.verifyUserIsExists(role);
		Assertions.assertTrue(isUserExists, role + " user is removed from members list");
	}

	@Then("{string} user cannot delete the {string} user and sees {string} user in members list")
	public void user_cannot_delete_the_user_and_sees_user_in_members_list(String user, String userType, String role) {
		boolean isUserExists = catalogSettingsPage.verifyUserIsExists(role);
		Assertions.assertTrue(isUserExists, role + " user is removed from members list");
	}

	@Then("User can see {string} user {string} icon is disabled")
	public void user_can_see_user_icon_is_disabled(String role, String iconType) {
		boolean isIconDisabled = catalogSettingsPage.isIconDisabled(role, iconType);
		Assertions.assertTrue(isIconDisabled, "for " + role + " user " + iconType + " icon is not disabled");
	}

	@And("{string} user can see {string} option is disable for {string} role")
	public void user_can_see_option_is_disabled_for_role(String currentrole, String option, String newRole) {
		boolean isOptionDisabled = catalogSettingsPage.isOptionDisabled(currentrole, option, newRole);
		Assertions.assertTrue(isOptionDisabled,
				"for " + currentrole + " user " + option + " option is not disabled for role " + newRole);
	}
}