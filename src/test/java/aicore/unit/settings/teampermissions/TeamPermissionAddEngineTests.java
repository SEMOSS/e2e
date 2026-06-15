package aicore.unit.settings.teampermissions;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.microsoft.playwright.Page;

import aicore.pages.base.EditMetadataPageUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.CommonUtils;
import aicore.utils.DatabaseTestUtils;
import aicore.utils.FunctionTestUtils;
import aicore.utils.StoragePageUtils;
import aicore.utils.StorageTestUtils;
import aicore.utils.TestResourceTrackerHelper;
import aicore.utils.TestResources;
import aicore.utils.UserManagementPageUtils;
import aicore.utils.VectorTestUtils;
import aicore.utils.annotations.PWPage;
import aicore.utils.annotations.ResourceUploadLock;
import aicore.utils.settings.SettingsPageUtils;
import aicore.utils.settings.TeamPermissionsSettingsUtils;

public class TeamPermissionAddEngineTests extends AbstractPlaywrightTestBase {
	
	private final String cardName = "Team Permissions";
	private String teamName = "";
	
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
	}
	
	@ParameterizedTest
	@ValueSource(strings = { "Author", "Editor", "Read-Only" })
	@ResourceUploadLock(TestResources.TEST_DATABASE_ZIP)
	void testAddEngineForDatabaseUsersRole(String role, @PWPage Page page) {
		// this method will navigate to database and upload it
		String dbID = DatabaseTestUtils.uploadDatabaseZip(page, TestResources.TEST_DATABASE_NAME, TestResources.TEST_DATABASE_ZIP);
		// need to navigate back to settings
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenSettings(page);
		SettingsPageUtils.clickOnAdminButton(page);
		SettingsPageUtils.checkCardVisible(page, cardName);
		SettingsPageUtils.clickOnCard(page, cardName);
		// user sees team
		TeamPermissionsSettingsUtils.searchForTeamNameInSearchBar(page, teamName);
		TeamPermissionsSettingsUtils.userClickOnCreatedTeamName(page, teamName, null);
		TeamPermissionsSettingsUtils.userClickOnAddEngineButton(page, "Add Engine");
		TeamPermissionsSettingsUtils.userSelectEngineFromList(page, TestResources.TEST_DATABASE_NAME, null, "Select Engine", TestResourceTrackerHelper.CATALOG_TYPE_DATABASE);
		TeamPermissionsSettingsUtils.userSelectEngineAccessRole(page, role);
		UserManagementPageUtils.clickSaveButton(page);
		TeamPermissionsSettingsUtils.validateToastMessage(page, "Successfully added engine permission");

		boolean isEnginePresent = TeamPermissionsSettingsUtils.userSeeAddedEngineInTheList(page, TestResources.TEST_DATABASE_NAME, role);
		if (!isEnginePresent) {
			isEnginePresent = TeamPermissionsSettingsUtils.userSeeAddedEngineInTheList(page, TestResources.TEST_DATABASE_NAME, role);
		}
		Assertions.assertTrue(isEnginePresent, "Engine with the specified role is not present in the list.");
		
		
		if (isEnginePresent) {
			TeamPermissionsSettingsUtils.deleteAddedRole(page, TestResources.TEST_DATABASE_NAME, role);
		} 
		CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_DATABASE, dbID);
	}
	
	@ParameterizedTest
	@ValueSource(strings = { "Author", "Editor", "Read-Only" })
	@ResourceUploadLock(TestResources.WEATHER_FUNC_ZIP)
	void testAddEngineForFunctionUsersRoles(String role, @PWPage Page page) {
		FunctionTestUtils.addDefaultZipUploadFunction(page);
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenSettings(page);
		SettingsPageUtils.clickOnAdminButton(page);
		SettingsPageUtils.checkCardVisible(page, cardName);
		SettingsPageUtils.clickOnCard(page, cardName);
		// user sees team
		TeamPermissionsSettingsUtils.searchForTeamNameInSearchBar(page, teamName);
		TeamPermissionsSettingsUtils.userClickOnCreatedTeamName(page, teamName, null);
		TeamPermissionsSettingsUtils.userClickOnAddEngineButton(page, "Add Engine");
		TeamPermissionsSettingsUtils.userSelectEngineFromList(page, TestResources.WEATHER_FUNC_NAME, null,
				"Select Engine", TestResourceTrackerHelper.CATALOG_TYPE_FUNCTION);
		TeamPermissionsSettingsUtils.userSelectEngineAccessRole(page, role);
		UserManagementPageUtils.clickSaveButton(page);
		TeamPermissionsSettingsUtils.validateToastMessage(page, "Successfully added engine permission");

		boolean isEnginePresent = TeamPermissionsSettingsUtils.userSeeAddedEngineInTheList(page,
				TestResources.WEATHER_FUNC_NAME, role);
		if (!isEnginePresent) {
			isEnginePresent = TeamPermissionsSettingsUtils.userSeeAddedEngineInTheList(page,
					TestResources.WEATHER_FUNC_NAME, role);
		}
		Assertions.assertTrue(isEnginePresent, "Engine with the specified role is not present in the list.");

		if (isEnginePresent) {
			TeamPermissionsSettingsUtils.deleteAddedRole(page, TestResources.WEATHER_FUNC_NAME, role);
		}

		FunctionTestUtils.deleteDefaultZipUploadFunction(page);
	}
	
	@ParameterizedTest
	@ValueSource(strings = { "Author", "Editor", "Read-Only" })
	@ResourceUploadLock(TestResources.LOCAL_MINIO_ZIP)
	void testAddEngineForStorageUsersRoles(String role, @PWPage Page page) {
		StorageTestUtils.addDefaultZipUploadStorage(page);
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenSettings(page);
		SettingsPageUtils.clickOnAdminButton(page);
		SettingsPageUtils.checkCardVisible(page, cardName);
		SettingsPageUtils.clickOnCard(page, cardName);
		// user sees team
		TeamPermissionsSettingsUtils.searchForTeamNameInSearchBar(page, teamName);
		TeamPermissionsSettingsUtils.userClickOnCreatedTeamName(page, teamName, null);
		TeamPermissionsSettingsUtils.userClickOnAddEngineButton(page, "Add Engine");
		TeamPermissionsSettingsUtils.userSelectEngineFromList(page, TestResources.LOCAL_MINIO_NAME, null,
				"Select Engine", TestResourceTrackerHelper.CATALOG_TYPE_STORAGE);
		TeamPermissionsSettingsUtils.userSelectEngineAccessRole(page, role);
		UserManagementPageUtils.clickSaveButton(page);
		TeamPermissionsSettingsUtils.validateToastMessage(page, "Successfully added engine permission");

		boolean isEnginePresent = TeamPermissionsSettingsUtils.userSeeAddedEngineInTheList(page,
				TestResources.LOCAL_MINIO_NAME, role);
		if (!isEnginePresent) {
			isEnginePresent = TeamPermissionsSettingsUtils.userSeeAddedEngineInTheList(page,
					TestResources.LOCAL_MINIO_NAME, role);
		}
		Assertions.assertTrue(isEnginePresent, "Engine with the specified role is not present in the list.");

		if (isEnginePresent) {
			TeamPermissionsSettingsUtils.deleteAddedRole(page, TestResources.LOCAL_MINIO_NAME, role);
		}

		StorageTestUtils.deleteDefaultZipUploadStorage(page);
	}

	@ParameterizedTest
	@ValueSource(strings = { "Author", "Editor", "Read-Only" })
	@ResourceUploadLock(TestResources.TEST_VECTOR_ZIP)
	void testAddEngineForVectorUsersRoles(String role, @PWPage Page page) {
		VectorTestUtils.addDefaultZipUploadVector(page);
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenSettings(page);
		SettingsPageUtils.clickOnAdminButton(page);
		SettingsPageUtils.checkCardVisible(page, cardName);
		SettingsPageUtils.clickOnCard(page, cardName);
		// user sees team
		TeamPermissionsSettingsUtils.searchForTeamNameInSearchBar(page, teamName);
		TeamPermissionsSettingsUtils.userClickOnCreatedTeamName(page, teamName, null);
		TeamPermissionsSettingsUtils.userClickOnAddEngineButton(page, "Add Engine");
		TeamPermissionsSettingsUtils.userSelectEngineFromList(page, TestResources.TEST_VECTOR_NAME, null,
				"Select Engine", TestResourceTrackerHelper.CATALOG_TYPE_VECTOR);
		TeamPermissionsSettingsUtils.userSelectEngineAccessRole(page, role);
		UserManagementPageUtils.clickSaveButton(page);
		TeamPermissionsSettingsUtils.validateToastMessage(page, "Successfully added engine permission");

		boolean isEnginePresent = TeamPermissionsSettingsUtils.userSeeAddedEngineInTheList(page,
				TestResources.TEST_VECTOR_NAME, role);
		if (!isEnginePresent) {
			isEnginePresent = TeamPermissionsSettingsUtils.userSeeAddedEngineInTheList(page,
					TestResources.TEST_VECTOR_NAME, role);
		}
		Assertions.assertTrue(isEnginePresent, "Engine with the specified role is not present in the list.");

		if (isEnginePresent) {
			TeamPermissionsSettingsUtils.deleteAddedRole(page, TestResources.TEST_VECTOR_NAME, role);
		}

		VectorTestUtils.deleteDefaultZipUploadVector(page);
	}
	
	@AfterEach
	void tearDown(@PWPage Page page) {
		CommonUtils.navigateAndDeleteTeam(page, teamName);
		logout(page);
	}

}
