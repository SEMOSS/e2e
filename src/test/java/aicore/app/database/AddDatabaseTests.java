package aicore.app.database;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;

import aicore.base.GenericSetupUtils;
import aicore.framework.AICoreTestConstants;
import aicore.framework.ConfigUtils;
import aicore.hooks.SetupHooks;
import aicore.pages.database.AddDatabaseFormUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.utils.AddDatabasePageUtils;
import aicore.utils.CatlogAccessPageUtility;
import aicore.utils.CommonUtils;

public class AddDatabaseTests {

	@Test
	public void testAddH2Database() throws IOException {
		GenericSetupUtils.initialize();
		Page page = GenericSetupUtils.setupPlaywright();
		String nativeUser = ConfigUtils.getValue(AICoreTestConstants.NATIVE_USERNAME);
		String nativePassword = ConfigUtils.getValue(AICoreTestConstants.NATIVE_PASSWORD);
		GenericSetupUtils.login(page, nativeUser, nativePassword);
		
		String timestamp = CommonUtils.getTimeStampName();
		String dbType = "H2";
		String dbName = "h2 test" + timestamp;
		String hostName = "localhost";
		String schemaName = "PUBLIC";
		String userName = "sa";
		String jdbcUrl = "h2";

		MainMenuUtils.openMainMenu(SetupHooks.getPage());
		MainMenuUtils.clickOnOpenDatabase(SetupHooks.getPage());
		
		AddDatabaseFormUtils.clickAddDatabaseButton(page);
		AddDatabaseFormUtils.selectDatabaseFromConnectionTypes(page, dbType);
		AddDatabaseFormUtils.enterCatalogName(page, dbName);
		AddDatabaseFormUtils.enterHostName(page, hostName);
		AddDatabaseFormUtils.clearPortNumber(page);
		AddDatabaseFormUtils.enterSchemaName(page, schemaName);
		AddDatabaseFormUtils.enterUserName(page, userName);
		AddDatabaseFormUtils.enterJDBCUrl(page, jdbcUrl, jdbcUrl);
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
}
