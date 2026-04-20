package aicore.unit.database;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import aicore.hooks.SetupHooks;
import aicore.pages.database.AddDatabaseFormUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.pages.model.EditModelPageUtils;
import aicore.pages.model.SettingsModelPageUtils;
import aicore.utils.AICorePageUtils;
import aicore.utils.AbstractE2ETest;
import aicore.utils.AddDatabaseFileUploadUtils;
import aicore.utils.AddDatabasePageUtils;
import aicore.utils.AddFunctionPageUtils;
import aicore.utils.CatalogCreationFromZipUtil;
import aicore.utils.CatlogAccessPageUtility;
import aicore.utils.CommonUtils;

public class DatabaseAccessControlPageTests extends AbstractE2ETest {

	private static String dbName = null;
	private static String dbID = null;

	@BeforeAll
	public static void setupBeforeAll() throws IOException {
		login(page, UserType.NATIVE);
		String timestamp = CommonUtils.getTimeStampName();

		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenDatabase(page);

		// add file upload db
		AddDatabaseFormUtils.clickAddDatabaseButton(page);
		String tabName = "file uploads";
		AddDatabaseFileUploadUtils.selectTab(page, tabName);

		String fileType = "Excel";
		dbName = "Excel db" + timestamp;
		String dbType = "h2";
		String metaModelType = "asFlatTable";
		String fileName = "Database/Database.xlsx";

		// db options
		AddDatabaseFileUploadUtils.selectFileType(page, fileType);
		AddDatabaseFileUploadUtils.enterDatabaseName(page, dbName);
		AddDatabaseFileUploadUtils.selectDatabaseType(page, dbType);
		AddDatabaseFileUploadUtils.selectMetamodelType(page, metaModelType);
		CatalogCreationFromZipUtil.uploadFile(page, fileName);
		AddDatabaseFormUtils.clickOnConnectButton(page);

		// validate the db created
		AddDatabaseFileUploadUtils.checkColumnsAreEditable(page);
		AICorePageUtils.clickOnButton(page, "Import");
		boolean isTitleVisible = AddDatabasePageUtils.verifyDatabaseTitle(page, dbName);
		Assertions.assertTrue(isTitleVisible, "Database title is not visible");
		dbID = CatlogAccessPageUtility.getCatalogAndCopyId(page);
	}
	
	@BeforeEach
	public void setup() throws IOException {
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenDatabase(page);
		AddDatabasePageUtils.searchDatabaseCatalog(page, dbName);
		AddDatabasePageUtils.clickOnDatabaseNameInCatalog(page, dbName);
	}
	
	@Test
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

		MainMenuUtils.openMainMenu(SetupHooks.getPage());
		MainMenuUtils.clickOnOpenDatabase(SetupHooks.getPage());
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
		CommonUtils.navigateAndDeleteCatalog(page, "Database", dbID);
	}
}
