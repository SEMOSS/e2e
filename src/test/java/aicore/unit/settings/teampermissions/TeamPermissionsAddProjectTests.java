package aicore.unit.settings.teampermissions;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.microsoft.playwright.Page;

import aicore.pages.home.MainMenuUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.AppTestUtils;
import aicore.utils.CommonUtils;
import aicore.utils.DatabaseTestUtils;
import aicore.utils.StoragePageUtils;
import aicore.utils.TestResourceTrackerHelper;
import aicore.utils.TestResources;
import aicore.utils.UserManagementPageUtils;
import aicore.utils.annotations.PWPage;
import aicore.utils.annotations.ResourceUploadLock;
import aicore.utils.settings.SettingsPageUtils;
import aicore.utils.settings.TeamPermissionsSettingsUtils;

public class TeamPermissionsAddProjectTests extends AbstractPlaywrightTestBase {
	
	private final String cardName = "Team Permissions";
	private String teamName = "";
	private String appName = "";
	
	@BeforeEach
	public void setup(@PWPage Page page) throws IOException {
		String timestamp = CommonUtils.getTimeStampName();
		
		loginNativeAdmin(page);
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenSettings(page);
		SettingsPageUtils.clickOnAdminButton(page);
		
		SettingsPageUtils.checkCardVisible(page, cardName);
		SettingsPageUtils.clickOnCard(page, cardName);
		StoragePageUtils.clickOnButton(page, "Add Team");
		TeamPermissionsSettingsUtils.selectTypeFromDropdown(page, "Custom");
		teamName = "Test Team " + timestamp;
		TeamPermissionsSettingsUtils.fillTeamName(page, teamName);
		TeamPermissionsSettingsUtils.enterDescription(page, "Test Description " + timestamp);
		TeamPermissionsSettingsUtils.clickOnAddButton(page, "Add");
		String fetchName = TeamPermissionsSettingsUtils.fetchTeamName(page);
		Assertions.assertFalse(fetchName.isEmpty(), "Fetched Team Name is Empty");
		
		appName = "test app name " + timestamp;
		AppTestUtils.createApp(page, appName);
	}
	
	@AfterEach
	void tearDown(@PWPage Page page) {
		AppTestUtils.deleteApp(page, appName);
		CommonUtils.navigateAndDeleteTeam(page, teamName);
		logout(page);
	}
	
	@ParameterizedTest
	@ValueSource(strings = { "Author", "Editor", "Read-Only" })
	void testAddProjectForDifferentUserRoles(String role, @PWPage Page page) {
		// need to navigate back to settings
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenSettings(page);
		SettingsPageUtils.clickOnAdminButton(page);
		SettingsPageUtils.checkCardVisible(page, cardName);
		SettingsPageUtils.clickOnCard(page, cardName);
		// user sees team
		TeamPermissionsSettingsUtils.searchForTeamNameInSearchBar(page, teamName);
		TeamPermissionsSettingsUtils.userClickOnCreatedTeamName(page, teamName, null);
		TeamPermissionsSettingsUtils.userClickOnAddEngineButton(page, "Add Apps");
		TeamPermissionsSettingsUtils.userSelectAppFromList(page, appName, "Select App");
		TeamPermissionsSettingsUtils.userSelectEngineAccessRole(page, role);
		UserManagementPageUtils.clickSaveButton(page);
		TeamPermissionsSettingsUtils.validateToastMessage(page, "Successfully added app permissions");
		assertTrue(TeamPermissionsSettingsUtils.userSeeAddedAppInTheList(page, appName, role));

	}
}
