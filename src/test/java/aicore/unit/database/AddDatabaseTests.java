package aicore.unit.database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import aicore.hooks.SetupHooks;
import aicore.pages.database.AddDatabaseFormUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.utils.AbstractE2ETest;
import aicore.utils.AddCatalogPageBaseUtils;
import aicore.utils.AddDatabasePageUtils;
import aicore.utils.AddFunctionPageUtils;
import aicore.utils.CatalogCreationFromZipUtil;
import aicore.utils.CatlogAccessPageUtility;
import aicore.utils.CommonUtils;

public class AddDatabaseTests extends AbstractE2ETest {

	@BeforeEach
	void setUp() {
		login(page, UserType.NATIVE);
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenDatabase(page);
		AddDatabaseFormUtils.clickAddDatabaseButton(page);
		assertTrue(AddCatalogPageBaseUtils.isSearchBarPresent(page));
	}
	
	@Test
	public void testAddH2() throws IOException {
		String timestamp = CommonUtils.getTimeStampName();
		String dbType = "H2";

		// add db options
		String dbName = "h2 test" + timestamp;
		String hostName = "localhost";
		String schemaName = "PUBLIC";
		String userName = "sa";
		String jdbcUrl = "h2";
		AddDatabaseFormUtils.selectDatabaseFromConnectionTypes(page, dbType);
		AddDatabaseFormUtils.enterCatalogName(page, dbName);
		AddDatabaseFormUtils.enterHostName(page, hostName);
		AddDatabaseFormUtils.clearPortNumber(page);
		AddDatabaseFormUtils.enterSchemaName(page, schemaName);
		AddDatabaseFormUtils.enterUserName(page, userName);
		String url = AddDatabaseFormUtils.getJDBCUrl(jdbcUrl, dbType);
		String expectedURL = "jdbc:h2:C:/workspace/e2e/src/test/resources/data/Database/H2";
		assertEquals(expectedURL, url);
		AddDatabaseFormUtils.enterJDBCUrl(page, url);

		// create db
		AddDatabaseFormUtils.clickOnConnectButton(page);
		AddDatabasePageUtils.clickOnEmptyMetaModelButton(page);

		// validation of the db created
		MainMenuUtils.openMainMenu(SetupHooks.getPage());
		MainMenuUtils.clickOnOpenDatabase(SetupHooks.getPage());
		AddDatabasePageUtils.searchDatabaseCatalog(page, dbName);
		AddDatabasePageUtils.clickOnDatabaseNameInCatalog(page, dbName);
		boolean isTitleVisible = AddDatabasePageUtils.verifyDatabaseTitle(page, dbName);
		Assertions.assertTrue(isTitleVisible, "Database title is not visible");
		String dbID = CatlogAccessPageUtility.getCatalogAndCopyId(page);

		// delete db
		CommonUtils.navigateAndDeleteCatalog(page, "Database", dbID);
	}

	@Test
	public void testAddSQLite() throws IOException {
		String timestamp = CommonUtils.getTimeStampName();

		// add db options
		String dbType = "SQLITE";
		String dbName = "SqliteDB test" + timestamp;
		String hostName = "localhost";
		String jdbcUrl = "sqlite.db";
		AddDatabaseFormUtils.selectDatabaseFromConnectionTypes(page, dbType);
		AddDatabaseFormUtils.enterCatalogName(page, dbName);
		AddDatabaseFormUtils.enterHostName(page, hostName);
		AddDatabaseFormUtils.clearPortNumber(page);
		String url = AddDatabaseFormUtils.getJDBCUrl("sqlite", jdbcUrl);
		String expectedURL = "jdbc:sqlite:C:/workspace/e2e/src/test/resources/data/Database/sqlite.db";
		assertEquals(expectedURL, url);
		AddDatabaseFormUtils.enterJDBCUrl(page, url);

		// create db
		AddDatabaseFormUtils.clickOnConnectButton(page);
		AddDatabasePageUtils.clickOnEmptyMetaModelButton(page);

		// validation of the db created
		MainMenuUtils.openMainMenu(SetupHooks.getPage());
		MainMenuUtils.clickOnOpenDatabase(SetupHooks.getPage());
		AddDatabasePageUtils.searchDatabaseCatalog(page, dbName);
		AddDatabasePageUtils.clickOnDatabaseNameInCatalog(page, dbName);
		boolean isTitleVisible = AddDatabasePageUtils.verifyDatabaseTitle(page, dbName);
		Assertions.assertTrue(isTitleVisible, "Database title is not visible");
		String dbID = CatlogAccessPageUtility.getCatalogAndCopyId(page);

		// delete db
		CommonUtils.navigateAndDeleteCatalog(page, "Database", dbID);
	}

	@Test
	public void testAddZip() throws IOException {
		// delete zip db before upload
		String dbName = "TestDatabase";
		MainMenuUtils.openMainMenu(SetupHooks.getPage());
		MainMenuUtils.clickOnOpenDatabase(SetupHooks.getPage());
		AddFunctionPageUtils.deleteCatalog(page, "Database", dbName);

		
		String fileName = "Database/TestDatabase.zip";
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenDatabase(page);
		AddDatabaseFormUtils.clickAddDatabaseButton(page);
		assertTrue(AddCatalogPageBaseUtils.isSearchBarPresent(page));
		
		// add zip db
		CatalogCreationFromZipUtil.clickOnFileUploadIcon(page);
		CatalogCreationFromZipUtil.uploadFile(page, fileName);
		CatalogCreationFromZipUtil.clickOnUploadButton(page, "Upload");

		MainMenuUtils.openMainMenu(SetupHooks.getPage());
		MainMenuUtils.clickOnOpenDatabase(SetupHooks.getPage());
		AddDatabasePageUtils.searchDatabaseCatalog(page, dbName);
		AddDatabasePageUtils.clickOnDatabaseNameInCatalog(page, dbName);
		boolean isTitleVisible = AddDatabasePageUtils.verifyDatabaseTitle(page, dbName);
		Assertions.assertTrue(isTitleVisible, "Database title is not visible");
		String dbID = CatlogAccessPageUtility.getCatalogAndCopyId(page);

		// delete db
		CommonUtils.navigateAndDeleteCatalog(page, "Database", dbID);
	}

}
