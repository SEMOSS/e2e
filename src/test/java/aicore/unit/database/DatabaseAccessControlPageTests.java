package aicore.unit.database;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import aicore.pages.home.MainMenuUtils;
import aicore.pages.model.EditModelPageUtils;
import aicore.pages.model.SettingsModelPageUtils;
import aicore.utils.AbstractE2ETest;
import aicore.utils.AddDatabasePageUtils;
import aicore.utils.AddFunctionPageUtils;
import aicore.utils.CommonUtils;
import aicore.utils.DatabaseTestUtils;
import aicore.utils.TestResourceTrackerHelper;
import aicore.utils.TestResources;
import aicore.utils.TestTags;

public class DatabaseAccessControlPageTests extends AbstractE2ETest {

	private static String dbName = null;
	private static String dbID = null;

	@BeforeAll
	public static void setupBeforeAll() throws IOException {
		login(page, UserType.NATIVE);

		// add db
		String fileName = TestResources.TEST_DATABASE_ZIP;
		dbName = "TestDatabase";
		dbID = DatabaseTestUtils.uploadDatabaseZip(page, dbName, fileName);
	}
	
	@BeforeEach
	public void setup() throws IOException {
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenDatabase(page);
		AddDatabasePageUtils.searchDatabaseCatalog(page, dbName);
		AddDatabasePageUtils.clickOnDatabaseNameInCatalog(page, dbName);
	}
	
	@Test
	@Tag(TestTags.FE_BUG)
	public void testAccessControl() throws IOException, InterruptedException {
		AddFunctionPageUtils.clickOnAccessControl(page);
		SettingsModelPageUtils.clickOnAddMembersButton(page);
		String role = "Read";
		boolean useDocker = false;
		SettingsModelPageUtils.addMember(page, role, useDocker);
	}
	
	@Test
	void testLockDatabase() {
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenDatabase(page);
		AddDatabasePageUtils.searchDatabaseCatalog(page, dbName);
		
		// validate search
		String databaseNameInCatalog = AddDatabasePageUtils.verifyDatabaseNameInCatalog(page, dbName);
		boolean databaseNameFlag = databaseNameInCatalog.contains(dbName);
		Assertions.assertTrue(databaseNameFlag, "Database name is not visible in the database catalog");
		
		// check status
		EditModelPageUtils.mouseHoverOnEngineAccessStatusIcon(page);
		String actualStatus = EditModelPageUtils.getEngineAccessStatusTooltipText(page, "Private");
		Assertions.assertEquals("Private", actualStatus, "Incorrect status");
		
		// make db public
		AddDatabasePageUtils.clickOnDatabaseNameInCatalog(page, dbName);
		AddFunctionPageUtils.clickOnAccessControl(page);
		SettingsModelPageUtils.clickOnMakeCatalogPublicButton(page, dbName);

		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenDatabase(page);
		AddDatabasePageUtils.searchDatabaseCatalog(page, dbName);
		
		// validate search
		databaseNameInCatalog = AddDatabasePageUtils.verifyDatabaseNameInCatalog(page, dbName);
		databaseNameFlag = databaseNameInCatalog.contains(dbName);
		Assertions.assertTrue(databaseNameFlag, "Database name is not visible in the database catalog");

		// check status
		EditModelPageUtils.mouseHoverOnEngineAccessStatusIcon(page);
		actualStatus =  EditModelPageUtils.getEngineAccessStatusTooltipText(page, "Global");
		Assertions.assertEquals("Global", actualStatus, "Incorrect status");

	}
	
	@AfterAll
	public static void tearDown() {
		CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_DATABASE, dbID);
	}
}
