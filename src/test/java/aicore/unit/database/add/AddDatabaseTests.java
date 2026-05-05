package aicore.unit.database.add;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;

import aicore.pages.database.AddDatabaseFormUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.utils.AICoreAllureLabels;
import aicore.utils.AbstractE2ETest;
import aicore.utils.AddCatalogPageBaseUtils;
import aicore.utils.AddDatabasePageUtils;
import aicore.utils.CatlogAccessPageUtility;
import aicore.utils.CommonUtils;
import aicore.utils.DatabaseTestUtils;
import aicore.utils.PWPage;
import aicore.utils.TestResourceTrackerHelper;
import aicore.utils.TestResources;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Epic(AICoreAllureLabels.DATABASE_EPIC)
@Feature(AICoreAllureLabels.ADD_DATABASE_FEATURE)
public class AddDatabaseTests extends AbstractE2ETest {

	@BeforeEach
	void setup(@PWPage Page page) {
		loginNativeAdmin(page);
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenDatabase(page);
		AddDatabaseFormUtils.clickAddDatabaseButton(page);
		assertTrue(AddCatalogPageBaseUtils.isSearchBarPresent(page));
	}	
	
	@AfterEach
	void tearDown(@PWPage Page page) {
		logout(page);
	}
	
    @DisplayName("Add H2 Database")
	@Test
	public void testAddH2(@PWPage Page page) throws IOException {
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
		assertTrue(url.startsWith("jdbc:h2"));
		assertTrue(url.endsWith("e2e/src/test/resources/data/Database/H2"));
		AddDatabaseFormUtils.enterJDBCUrl(page, url);

		// create db
		AddDatabaseFormUtils.clickOnConnectButton(page);
		AddDatabasePageUtils.clickOnEmptyMetaModelButton(page);

		// validation of the db created
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenDatabase(page);
		AddDatabasePageUtils.searchDatabaseCatalog(page, dbName);
		AddDatabasePageUtils.clickOnDatabaseNameInCatalog(page, dbName);
		boolean isTitleVisible = AddDatabasePageUtils.verifyDatabaseTitle(page, dbName);
		Assertions.assertTrue(isTitleVisible, "Database title is not visible");
		String dbID = CatlogAccessPageUtility.getCatalogAndCopyId(page);

		// delete db
		CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_DATABASE, dbID);
	}

    @DisplayName("Add SQLite Database")
	@Test
	public void testAddSQLite(@PWPage Page page) throws IOException {
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
		assertTrue(url.startsWith("jdbc:sqlite"));
		assertTrue(url.endsWith("e2e/src/test/resources/data/Database/sqlite.db"));
		AddDatabaseFormUtils.enterJDBCUrl(page, url);

		// create db
		AddDatabaseFormUtils.clickOnConnectButton(page);
		AddDatabasePageUtils.clickOnEmptyMetaModelButton(page);

		// validation of the db created
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenDatabase(page);
		AddDatabasePageUtils.searchDatabaseCatalog(page, dbName);
		AddDatabasePageUtils.clickOnDatabaseNameInCatalog(page, dbName);
		boolean isTitleVisible = AddDatabasePageUtils.verifyDatabaseTitle(page, dbName);
		Assertions.assertTrue(isTitleVisible, "Database title is not visible");
		String dbID = CatlogAccessPageUtility.getCatalogAndCopyId(page);

		// delete db
		CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_DATABASE, dbID);
	}

    @DisplayName("Add Shared Zip Database")
	@Test
	public void testAddZip(@PWPage Page page) throws IOException {
		// delete zip db before upload
		String dbName = "TestDatabase";

		String fileName = TestResources.TEST_DATABASE_ZIP;
		acquireTestDatabaseZipLock(()-> DatabaseTestUtils.uploadDatabaseZip(page, dbName, fileName));
		String dbID = DatabaseTestUtils.getDatabaseID(page, dbName);

		// delete db
		releaseTestDatabaseZipLock(() ->CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_DATABASE, dbID));
	}

}
