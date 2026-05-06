package aicore.unit.database;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;

import aicore.pages.home.MainMenuUtils;
import aicore.pages.model.EditModelPageUtils;
import aicore.pages.model.SettingsModelPageUtils;
import aicore.utils.AbstractDatabaseTestBase;
import aicore.utils.AddDatabasePageUtils;
import aicore.utils.AddFunctionPageUtils;
import aicore.utils.CommonUtils;
import aicore.utils.DatabaseTestUtils;
import aicore.utils.TestResourceTrackerHelper;
import aicore.utils.TestResources;
import aicore.utils.TestTags;
import aicore.utils.annotations.PWPage;

public class DatabaseAccessControlPageTests extends AbstractDatabaseTestBase {

	private String dbName = null;
	private String dbID = null;

	@BeforeEach
	public void setup(@PWPage Page page) throws IOException {
		loginNativeAdmin(page);

		// add db
		String fileName = TestResources.TEST_DATABASE_ZIP;
		dbName = "TestDatabase";
		acquireTestDatabaseZipLock(()->DatabaseTestUtils.uploadDatabaseZip(page, dbName, fileName));
		dbID = DatabaseTestUtils.getDatabaseID(page, dbName);
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenDatabase(page);
		AddDatabasePageUtils.searchDatabaseCatalog(page, dbName);
		AddDatabasePageUtils.clickOnDatabaseNameInCatalog(page, dbName);
	}
	
	@Test
	@Tag(TestTags.FE_BUG)
	public void testAccessControl(@PWPage Page page) throws IOException, InterruptedException {
		AddFunctionPageUtils.clickOnAccessControl(page);
		SettingsModelPageUtils.clickOnAddMembersButton(page);
		String role = "Read";
		boolean useDocker = false;
		SettingsModelPageUtils.addMember(page, role, useDocker);
	}
	
	@Test
	void testLockDatabase(@PWPage Page page) {
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
	
	@AfterEach
	public void tearDown(@PWPage Page page) {
		releaseTestDatabaseZipLock(()-> CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_DATABASE, dbID));
		logout(page);
	}
}
