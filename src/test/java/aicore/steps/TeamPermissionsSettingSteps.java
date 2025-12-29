package aicore.steps;

import org.junit.jupiter.api.Assertions;

import aicore.hooks.SetupHooks;
import aicore.pages.TeamPermissionsSettingsPage;
import aicore.pages.app.CreateAppPopupPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TeamPermissionsSettingSteps {

	private TeamPermissionsSettingsPage teamPermissionsSettings;
	private String timestamp;
	private CreateAppPopupPage createAppPopupPage;

	public TeamPermissionsSettingSteps() {
		timestamp = SetupHooks.getTimestamp();
		teamPermissionsSettings = new TeamPermissionsSettingsPage(SetupHooks.getPage(), timestamp);
		createAppPopupPage = new CreateAppPopupPage(SetupHooks.getPage(), timestamp);
	}

	@When("User selects type as {string} from Type dropdown")
	public void user_selects_type_as_from_type_dropdown(String type) {
		teamPermissionsSettings.selectTypeFromDropdown(type);
	}

	@When("User fills {string} in Name field of Add Team form")
	public void user_fills_in_name_field_of_add_team_form(String string) {
		teamPermissionsSettings.fillTeamName(string + " " + timestamp);
	}

	@When("User fills Description as {string} in Description field of Add Team form")
	public void user_fills_description_as_in_description_field_of_add_team_form(String description) {
		teamPermissionsSettings.enterDescription(description + " " + timestamp);
	}

	@When("User clicks on {string} button in Add Team form")
	public void user_clicks_on_button_in_add_team_form(String button) {
		teamPermissionsSettings.clickOnAddButton(button);
	}

	@When("User clicks on {string} button in Add Team Page")
	public void user_clicks_on_button_in_add_team_page(String button) {
		teamPermissionsSettings.clickOnAddTeamButton(button);
	}

	@When("User selects {string} member from the list")
	public void user_selects_member_from_the_list(String member) {
		teamPermissionsSettings.selectMemberFromList(member);
	}

	@When("User sees {string} card in the Add Member form")
	public void user_sees_card_in_the_add_member_form(String member) {
		teamPermissionsSettings.checkMemberCard(member);
	}

	@When("User clicks on {string} button in Add Member form")
	public void user_clicks_on_button_in_add_member_form(String button) {
		teamPermissionsSettings.clickOnSaveMemberButton(button);
	}

	@Then("User sees the message {string} is displayed")
	public void user_sees_the_message_is_displayed(String expectedMessage) {
		teamPermissionsSettings.validateToastMessage(expectedMessage);
	}

	@Then("User can see the new member {string} added in the team member list")
	public void user_can_see_the_new_member_added_in_the_team_member_list(String member) {
		boolean isMemberPresent = teamPermissionsSettings.checkMemberInList(member);
		Assertions.assertTrue(isMemberPresent, "Member is not present in the team member list.");
		// teamPermissionsSettings.checkMemberInList(member);
	}

	@Then("User can see team name as {string} in the list")
	public void user_can_see_team_name_as_in_the_list(String name) {
		String actualName = teamPermissionsSettings.verifyName(name);
		String expectedName = name + " " + timestamp;
		Assertions.assertEquals(expectedName, actualName);
	}

	@Then("User can see description as {string} in the list")
	public void user_can_see_description_as_in_the_list(String description) {
		String actualDescription = teamPermissionsSettings.validateDescription(description);
		String expectedDesc = description + " " + timestamp;
		Assertions.assertEquals(expectedDesc, actualDescription);
	}

	// add engine to all catalog with different roles
	@And("User clicks on the team name {string} in the list")
	public void user_clicks_on_the_team_name_in_the_list(String teamName) {
		teamPermissionsSettings.userClickOnCreatedTeamName(teamName, timestamp);
	}

	@And("User clicks on {string} button in Team Permission page")
	public void user_clicks_on_button_in_team_permission_page(String addcatalogName) {
		teamPermissionsSettings.userClickOnAddEngineButton(addcatalogName);
	}

	@And("User select the {string} in the {string} field of Add Engine form the {string}")
	public void user_select_the_in_the_engine_field_of_add_engine_form(String catalogName, String selectCatalog,
			String catlogType) {
		teamPermissionsSettings.userSelectEngineFromList(catalogName, timestamp, selectCatalog, catlogType);
	}

	@And("User select the {string} in the {string} field of Add App form")
	public void user_select_the_in_the_app_field_of_add_app_form(String catalogName, String selectCatalog) {
		teamPermissionsSettings.userSelectAppFromList(catalogName, selectCatalog);
	}

	@And("User select the engine access as {string}")
	public void user_select_the_engine_access_as(String role) {
		teamPermissionsSettings.userSelectEngineAccessRole(role);
	}

	@And("User see the added {string} in the engine list with access as {string}")
	public void user_sees_the_with_role_added_in_the_list(String catalogName, String role) {
		boolean isEnginePresent = teamPermissionsSettings.userSeeAddedEngineInTheList(catalogName + timestamp, role);
		Assertions.assertTrue(isEnginePresent, "Engine with the specified role is not present in the list.");
	}

	// delete team memeber
	@When("User clicks on {string} icon on the {string} card")
	public void user_clicks_on_icon_for_the_member_in_the(String icon, String member) {
		teamPermissionsSettings.userClickOnDeleteIcon(icon, member);
	}

	@And("User clicks on {string} button in the confirmation modal")
	public void user_clicks_on_button_in_the_confirmation_modal(String button) {
		teamPermissionsSettings.userClickOnDeleteConfirmButton(button);
	}

	@And("User should not see the {string} card in the team member list")
	public void user_should_not_see_the_card_in_the_team_member_list(String member) {
		boolean isMemberPresent = teamPermissionsSettings.checkMemberInList(member);
		Assertions.assertFalse(isMemberPresent, "Member is still present in the team member list.");
	}

	@And("User selects multiple members {string} and {string} from the team list")
	public void user_selects_multiple_members_and_from_the_team_list(String member1, String member2) {
		teamPermissionsSettings.selectMultipleMembersFromList(member1, member2);
	}

	@And("User click on checkbox to select all member")
	public void user_select_all_added_member() {
		teamPermissionsSettings.userSelectAllMember();
	}

	@And("User Click on {string} Option")
	public void user_click_on_option(String string) {
		teamPermissionsSettings.userClickOnOption(string);
	}

	@And("User Search the member name as {string}")
	public void user_search_the_member_name_as(String member) {
		teamPermissionsSettings.userSearchMemberName(member);
	}

	@And("User deletes the added role")
	public void User_deletes_the_added_role() {
		teamPermissionsSettings.clickOnDeleteButton();
	}

	@And("User adds {string} from the member list")
	public void user_adds_from_the_member_list(String member) {
		teamPermissionsSettings.addmultipleMembers(member);
	}

	@And("User verifies pagination is working correctly")
	public void user_verifies_pagination_is_working_correctly() {
		boolean isPaginationWorking = teamPermissionsSettings.verifyPagination();
		Assertions.assertTrue(isPaginationWorking, "Pagination is not working correctly.");
	}

	@And("User adds multiple engines to the team")
	public void user_adds_multiple_engines_to_the_team() {
		teamPermissionsSettings.addmultipleEngines();
	}

	@And("User creates {string} {string} apps with app name {string}, description {string}, and tags {string}")
	public void user_creates_apps_with_app_name_description_and_tags(String appCount, String appType, String appName,
			String appDescription, String appTags) {
		for (int i = 1; i <= Integer.parseInt(appCount); i++) {
			String appNameWithTimestamp = appName + System.currentTimeMillis();
			createAppPopupPage.createMultipleApps(appType, appNameWithTimestamp, appDescription, appTags);
		}
	}

	@And("User adds multiple projects to the team")
	public void user_adds_multiple_projects_to_the_team() {
		teamPermissionsSettings.addMultipleProjects();
	}

	@And("User Delete the created Apps")
	public void user_deletes_all_the_created_apps() {
		createAppPopupPage.deleteCreatedApps();
	}
}
