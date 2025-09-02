package aicore.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.jupiter.api.Assertions;

import aicore.hooks.SetupHooks;
import aicore.pages.TeamPermissionsSettingsPage;
import aicore.utils.CommonUtils;

public class TeamPermissionsSettingSteps {

    private TeamPermissionsSettingsPage teamPermissionsSettings;
    private String timestamp;

    public TeamPermissionsSettingSteps() {
        timestamp = CommonUtils.getTimeStampName();
        teamPermissionsSettings = new TeamPermissionsSettingsPage(SetupHooks.getPage(), timestamp);
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

}
