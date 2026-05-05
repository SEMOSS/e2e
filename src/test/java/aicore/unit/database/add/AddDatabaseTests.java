package aicore.unit.database.add;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import aicore.pages.database.AddDatabaseFormUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.utils.AICoreAllureLabels;
import aicore.utils.AbstractE2ETest;
import aicore.utils.AddCatalogPageBaseUtils;
import aicore.utils.AddDatabasePageUtils;
import aicore.utils.CatlogAccessPageUtility;
import aicore.utils.CommonUtils;
import aicore.utils.DatabaseTestUtils;
import aicore.utils.TestResourceTrackerHelper;
import aicore.utils.TestResources;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Epic(AICoreAllureLabels.DATABASE_EPIC)
@Feature(AICoreAllureLabels.ADD_DATABASE_FEATURE)
public class AddDatabaseTests extends AbstractE2ETest {

	@BeforeEach
	void setUp() {
		login(page, UserType.NATIVE);
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenDatabase(page);
		AddDatabaseFormUtils.clickAddDatabaseButton(page);
		assertTrue(AddCatalogPageBaseUtils.isSearchBarPresent(page));
	}
	
    @DisplayName("Add H2 Database")
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
	public void testAddZip() throws IOException {
		// delete zip db before upload
		String dbName = "TestDatabase";

		String fileName = TestResources.TEST_DATABASE_ZIP;
		String dbID = DatabaseTestUtils.uploadDatabaseZip(page, dbName, fileName);

		// delete db
		CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_DATABASE, dbID);
	}

}
