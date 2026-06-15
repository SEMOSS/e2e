package aicore.unit.settings.teampermissions;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.microsoft.playwright.Page;

import aicore.pages.home.MainMenuUtils;
import aicore.pages.model.EditModelPageUtils;
import aicore.pages.model.SettingsModelPageUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.AddDatabasePageUtils;
import aicore.utils.AddFunctionPageUtils;
import aicore.utils.AppTestUtils;
import aicore.utils.CommonUtils;
import aicore.utils.DatabaseTestUtils;
import aicore.utils.FunctionTestUtils;
import aicore.utils.GuardrailTestUtils;
import aicore.utils.ModelTestUtils;
import aicore.utils.SearchAndSelectCatalogPageUtils;
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

public class TeamPermissionsTests extends AbstractPlaywrightTestBase {
	
	private final String role = "Editor";
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
	
	@Test
	@ResourceUploadLock(TestResources.TEST_DATABASE_ZIP)
	void testVerifyTeamDisplayedInCatalogSectionAfterAddingDatabaseEngine(@PWPage Page page) {
		// Add Database zip
		String dbID = DatabaseTestUtils.uploadDatabaseZip(page, TestResources.TEST_DATABASE_NAME, TestResources.TEST_DATABASE_ZIP);
		// need to navigate back to settings
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenSettings(page);
		SettingsPageUtils.clickOnAdminButton(page);
		SettingsPageUtils.checkCardVisible(page, cardName);
		SettingsPageUtils.clickOnCard(page, cardName);
		// user sees team and adds DB
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
		// navigate to DB to verify team permissions
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenDatabase(page);
		AddDatabasePageUtils.searchDatabaseCatalog(page, TestResources.TEST_DATABASE_NAME);
		AddDatabasePageUtils.clickOnDatabaseNameInCatalog(page, TestResources.TEST_DATABASE_NAME);
		AddFunctionPageUtils.clickOnAccessControl(page);
		assertTrue(TeamPermissionsSettingsUtils.checkTeamWithAccess(page, teamName, role));
		assertTrue(TeamPermissionsSettingsUtils.isEngineAndCatalogTimeMatching(page, teamName));
		CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_DATABASE, dbID);
	}
	
	@Test
	@ResourceUploadLock(TestResources.LLAMA3_70B_INSTRUCT_ZIP)
	void testVerifyTeamDisplayedInCatalogSectionAfterAddingModelEngine(@PWPage Page page) {
		// Add Database zip
		String dbID = ModelTestUtils.addDefaultZipUploadModel(page);
		// need to navigate back to settings
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenSettings(page);
		SettingsPageUtils.clickOnAdminButton(page);
		SettingsPageUtils.checkCardVisible(page, cardName);
		SettingsPageUtils.clickOnCard(page, cardName);
		// user sees team and adds DB
		TeamPermissionsSettingsUtils.searchForTeamNameInSearchBar(page, teamName);
		TeamPermissionsSettingsUtils.userClickOnCreatedTeamName(page, teamName, null);
		TeamPermissionsSettingsUtils.userClickOnAddEngineButton(page, "Add Engine");
		TeamPermissionsSettingsUtils.userSelectEngineFromList(page, TestResources.LLAMA3_70B_INSTRUCT_NAME, null, "Select Engine", TestResourceTrackerHelper.CATALOG_TYPE_MODEL);
		TeamPermissionsSettingsUtils.userSelectEngineAccessRole(page, role);
		UserManagementPageUtils.clickSaveButton(page);
		TeamPermissionsSettingsUtils.validateToastMessage(page, "Successfully added engine permission");
		boolean isEnginePresent = TeamPermissionsSettingsUtils.userSeeAddedEngineInTheList(page, TestResources.LLAMA3_70B_INSTRUCT_NAME, role);
		if (!isEnginePresent) {
			isEnginePresent = TeamPermissionsSettingsUtils.userSeeAddedEngineInTheList(page, TestResources.LLAMA3_70B_INSTRUCT_NAME, role);
		}
		Assertions.assertTrue(isEnginePresent, "Engine with the specified role is not present in the list.");
		// navigate to Model to verify team permissions
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenModel(page);
		EditModelPageUtils.searchModelCatalog(page, TestResources.LLAMA3_70B_INSTRUCT_NAME);
		EditModelPageUtils.selectModelFromSearchOptions(page, TestResources.LLAMA3_70B_INSTRUCT_NAME);
		SettingsModelPageUtils.clickOnAccessControl(page);
		assertTrue(TeamPermissionsSettingsUtils.checkTeamWithAccess(page, teamName, role));
		assertTrue(TeamPermissionsSettingsUtils.isEngineAndCatalogTimeMatching(page, teamName));
		ModelTestUtils.deleteDefaultZipUploadModel(page);
	}
	
	@Test
	@ResourceUploadLock(TestResources.TEST_VECTOR_ZIP)
	void testVerifyTeamDisplayedInCatalogSectionAfterAddingVectorEngine(@PWPage Page page) {
		VectorTestUtils.addDefaultZipUploadVector(page);
		// need to navigate back to settings
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenSettings(page);
		SettingsPageUtils.clickOnAdminButton(page);
		SettingsPageUtils.checkCardVisible(page, cardName);
		SettingsPageUtils.clickOnCard(page, cardName);
		// user sees team and adds DB
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
		// navigate to Model to verify team permissions
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenVector(page);
		SearchAndSelectCatalogPageUtils.searchCatalog(page, TestResources.TEST_VECTOR_NAME);
		SearchAndSelectCatalogPageUtils.selectCatalogFromSearchOptions(page, TestResources.TEST_VECTOR_NAME);
		SettingsModelPageUtils.clickOnAccessControl(page);
		assertTrue(TeamPermissionsSettingsUtils.checkTeamWithAccess(page, teamName, role));
		assertTrue(TeamPermissionsSettingsUtils.isEngineAndCatalogTimeMatching(page, teamName));
		VectorTestUtils.deleteDefaultZipUploadVector(page);
	}
	
	@Test
	@ResourceUploadLock(TestResources.LOCAL_MINIO_ZIP)
	void testVerifyTeamDisplayedInCatalogSectionAfterAddingStorageEngine(@PWPage Page page) {
		StorageTestUtils.addDefaultZipUploadStorage(page);
		// need to navigate back to settings
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenSettings(page);
		SettingsPageUtils.clickOnAdminButton(page);
		SettingsPageUtils.checkCardVisible(page, cardName);
		SettingsPageUtils.clickOnCard(page, cardName);
		// user sees team and adds DB
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
		// navigate to Model to verify team permissions
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenStorage(page);
		SearchAndSelectCatalogPageUtils.searchCatalog(page, TestResources.LOCAL_MINIO_NAME);
		SearchAndSelectCatalogPageUtils.selectCatalogFromSearchOptions(page, TestResources.LOCAL_MINIO_NAME);
		SettingsModelPageUtils.clickOnAccessControl(page);
		assertTrue(TeamPermissionsSettingsUtils.checkTeamWithAccess(page, teamName, role));
		assertTrue(TeamPermissionsSettingsUtils.isEngineAndCatalogTimeMatching(page, teamName));
		StorageTestUtils.deleteDefaultZipUploadStorage(page);
	}
	
	@Test
	@ResourceUploadLock(TestResources.WEATHER_FUNC_ZIP)
	void testVerifyTeamDisplayedInCatalogSectionAfterAddingFunctionEngine(@PWPage Page page) {
		FunctionTestUtils.addDefaultZipUploadFunction(page);
		// need to navigate back to settings
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenSettings(page);
		SettingsPageUtils.clickOnAdminButton(page);
		SettingsPageUtils.checkCardVisible(page, cardName);
		SettingsPageUtils.clickOnCard(page, cardName);
		// user sees team and adds DB
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
		// navigate to Model to verify team permissions
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenFunction(page);
		SearchAndSelectCatalogPageUtils.searchCatalog(page, TestResources.WEATHER_FUNC_NAME);
		SearchAndSelectCatalogPageUtils.selectCatalogFromSearchOptions(page, TestResources.WEATHER_FUNC_NAME);
		SettingsModelPageUtils.clickOnAccessControl(page);
		assertTrue(TeamPermissionsSettingsUtils.checkTeamWithAccess(page, teamName, role));
		assertTrue(TeamPermissionsSettingsUtils.isEngineAndCatalogTimeMatching(page, teamName));
		FunctionTestUtils.deleteDefaultZipUploadFunction(page);
	}
	
	@Test
	@ResourceUploadLock(TestResources.GLINER_ZIP)
	void testVerifyTeamDisplayedInCatalogSectionAfterAddingGuardrailEngine(@PWPage Page page) {
		GuardrailTestUtils.addDefaultZipUploadGuardrail(page);
		// need to navigate back to settings
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenSettings(page);
		SettingsPageUtils.clickOnAdminButton(page);
		SettingsPageUtils.checkCardVisible(page, cardName);
		SettingsPageUtils.clickOnCard(page, cardName);
		// user sees team and adds DB
		TeamPermissionsSettingsUtils.searchForTeamNameInSearchBar(page, teamName);
		TeamPermissionsSettingsUtils.userClickOnCreatedTeamName(page, teamName, null);
		TeamPermissionsSettingsUtils.userClickOnAddEngineButton(page, "Add Engine");
		TeamPermissionsSettingsUtils.userSelectEngineFromList(page, TestResources.GLINER_NAME, null,
				"Select Engine", TestResourceTrackerHelper.CATALOG_TYPE_GUARDRAIL);
		TeamPermissionsSettingsUtils.userSelectEngineAccessRole(page, role);
		UserManagementPageUtils.clickSaveButton(page);
		TeamPermissionsSettingsUtils.validateToastMessage(page, "Successfully added engine permission");
		boolean isEnginePresent = TeamPermissionsSettingsUtils.userSeeAddedEngineInTheList(page,
				TestResources.GLINER_NAME, role);
		if (!isEnginePresent) {
			isEnginePresent = TeamPermissionsSettingsUtils.userSeeAddedEngineInTheList(page,
					TestResources.GLINER_NAME, role);
		}
		Assertions.assertTrue(isEnginePresent, "Engine with the specified role is not present in the list.");
		// navigate to Model to verify team permissions
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnGuardrail(page);
		SearchAndSelectCatalogPageUtils.searchCatalog(page, TestResources.GLINER_NAME);
		SearchAndSelectCatalogPageUtils.selectCatalogFromSearchOptions(page, TestResources.GLINER_NAME);
		SettingsModelPageUtils.clickOnAccessControl(page);
		assertTrue(TeamPermissionsSettingsUtils.checkTeamWithAccess(page, teamName, role));
		assertTrue(TeamPermissionsSettingsUtils.isEngineAndCatalogTimeMatching(page, teamName));
		GuardrailTestUtils.deleteDefaultZipUploadGuardrail(page);
	}
	
	
	@AfterEach
	void tearDown(@PWPage Page page) {
		CommonUtils.navigateAndDeleteTeam(page, teamName);
		logout(page);
	}
}
