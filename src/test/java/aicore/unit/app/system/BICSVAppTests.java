package aicore.unit.app.system;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import aicore.pages.home.HomePageUtils;
import aicore.utils.AICorePageUtils;
import aicore.utils.AbstractE2ETest;
import aicore.utils.AppTestUtils;
import aicore.utils.CommonUtils;
import aicore.utils.DatabaseTestUtils;
import aicore.utils.page.app.BISystemAppUtils;

public class BICSVAppTests extends AbstractE2ETest {
	private static String timestamp = CommonUtils.getTimeStampName();
	private static String appName = "Test app " + timestamp;
	private static String databaseNameCSV = "DB created from CSV " + timestamp;
	private static String databaseCSV_ID = null;
	private static String insightName = "Test Automation " + timestamp;

	@BeforeAll
	public static void createApp() {
		login(page, UserType.NATIVE);
		HomePageUtils.navigateToHomePage(page);
		AppTestUtils.createApp(page, appName);
	}
	
	@Test
	public void createDatabaseAndInsightFromCSV_test() throws IOException {
		// add database from csv file
		BISystemAppUtils.navigateToBIApp(page);
		
		BISystemAppUtils.clickOnCatalogOption(page);
		BISystemAppUtils.clickOnAddDatabaseButton(page);
		BISystemAppUtils.enterDatabaseName(page, databaseNameCSV);
		BISystemAppUtils.uploadCSVFile(page);
		AICorePageUtils.clickOnButton(page, "Next");
		AICorePageUtils.clickOnButton(page, "Import");
		// TODO toast disappears quickly need a better way to validate
//		String expectedDBToastMessage = "Success";
//		String actualDBToastMessage = BISystemAppUtils.verifyDBCreatedToastMessage(page);
//		assertEquals(expectedDBToastMessage, actualDBToastMessage, "Database creation failed");
//		logger.info("the success toast is quick skipping for now");
		
		// get the database id from to delete resource after
		databaseCSV_ID = DatabaseTestUtils.getDatabaseID(page, databaseNameCSV);
		BISystemAppUtils.navigateToBIApp(page);
		
		// create an insight from the newly created database
		BISystemAppUtils.clickOnAppOption(page);
		BISystemAppUtils.searchDatabaseName(page, databaseNameCSV);
		BISystemAppUtils.clickOnAddAllOption(page);
		BISystemAppUtils.clickOnImportButtonOfSelectedColumns(page);
		BISystemAppUtils.mouseHoverOnDatabaseFrame(page);
		BISystemAppUtils.clickOnVisualizeDataOption(page);
		BISystemAppUtils.clickOnWorkspaceSaveButton(page);
		BISystemAppUtils.enterInsightName(page, insightName);
		BISystemAppUtils.selectProjectName(page, appName);

		// TODO toast message timing
//		String expectedInsightToastMessage = "Successfully saved insight(s)";
//		String actualInsightToastMessage = BISystemAppUtils.verifySavedInsightSuccessMsg(page);
//		assertEquals(actualInsightToastMessage, expectedInsightToastMessage, "Insights creation failed");
	}
	
	@AfterAll
	public static void cleanUp() {
		CommonUtils.navigateAndDeleteApp(page, appName);
		CommonUtils.navigateAndDeleteCatalog(page, "Database", databaseCSV_ID);
	}

}
