package aicore.unit.app.system;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import aicore.hooks.SetupHooks;
import aicore.pages.home.HomePageUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.utils.AICorePageUtils;
import aicore.utils.AbstractE2ETest;
import aicore.utils.AppTestUtils;
import aicore.utils.CommonUtils;
import aicore.utils.page.app.BISystemAppUtils;

public class BIAppTests extends AbstractE2ETest {
	private static String timestamp = CommonUtils.getTimeStampName();
	private static String appName = "Test app " + timestamp;
	private static String databaseNameCSV = "DB created from CSV " + timestamp;
	private static String databaseNameExcel = "DB created from Excel" + timestamp;
	private static String insightName = "Test Automation " + timestamp;


	private static final Logger logger = LogManager.getLogger(BIAppTests.class);
	
	@BeforeAll
	public static void createApp() {
		login(page, UserType.NATIVE);
		HomePageUtils.navigateToHomePage(page);
		AppTestUtils.createApp(page, appName);
	}

	@Test
	public void createDatabaseAndInsightFromCSV_test() throws IOException {
		// add database from csv file
		HomePageUtils.navigateToHomePage(page);
		MainMenuUtils.openMainMenu(SetupHooks.getPage());
		MainMenuUtils.clickOnOpenAppLibrary(SetupHooks.getPage());
		HomePageUtils.clickOnSystemApp(page);
		HomePageUtils.clickOnBIApp(page);
		BISystemAppUtils.closeWelcomePopup(page);
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
		
		// TODO need to get the database id from to delete resource after
		
		// create an insight from the newly created database
		BISystemAppUtils.clickOnAppOption(page);
		BISystemAppUtils.searchDatabaseName(page, databaseNameCSV);
		BISystemAppUtils.clickOnAddAllOption(page);
		BISystemAppUtils.clickOnImportButtonOfSelectedColumns(page);
		BISystemAppUtils.mouseHoverOnDatabaseFrame(page);
		BISystemAppUtils.clickOnVisualizeDataOption(page);
		BISystemAppUtils.clickOnWorkspaceSaveButton(page);
		BISystemAppUtils.enterInsightName(page, insightName);
		BISystemAppUtils.selectProjectName(page, appName, timestamp);

		String expectedInsightToastMessage = "Successfully saved insight(s)";
		String actualInsightToastMessage = BISystemAppUtils.verifySavedInsightSuccessMsg(page);
		assertEquals(actualInsightToastMessage, expectedInsightToastMessage, "Insights creation failed");
	}

	@Test
	public void createDatabaseAndInsightFromExcel_test() throws IOException {
		// add database from excel file
		HomePageUtils.navigateToHomePage(page);
		MainMenuUtils.openMainMenu(SetupHooks.getPage());
		MainMenuUtils.clickOnOpenAppLibrary(SetupHooks.getPage());
		HomePageUtils.clickOnSystemApp(page);
		HomePageUtils.clickOnBIApp(page);
		BISystemAppUtils.closeWelcomePopup(page);
		BISystemAppUtils.clickOnCatalogOption(page);
		BISystemAppUtils.clickOnAddDatabaseButton(page);

		BISystemAppUtils.selectExcelOption(page);
		BISystemAppUtils.enterDatabaseName(page, databaseNameExcel);
		BISystemAppUtils.uploadExcelFile(page);
		AICorePageUtils.clickOnButton(page, "Next");
		AICorePageUtils.clickOnButton(page, "Import");
		// TODO toast disappears quickly need a better way to validate
//		String actualDBToastMessage = BISystemAppUtils.verifyDBCreatedToastMessage(page);
//		assertEquals(expectedDBToastMessage, actualDBToastMessage, "Database creation failed");
//		logger.info("the success toast is quick skipping for now");
		
		// TODO need to get the database id from to delete resource after


		// create an insight from the newly created database
		BISystemAppUtils.clickOnAppOption(page);
		BISystemAppUtils.searchDatabaseName(page, databaseNameExcel);
		BISystemAppUtils.clickOnAddAllOption(page);
		BISystemAppUtils.clickOnImportButtonOfSelectedColumns(page);
		BISystemAppUtils.mouseHoverOnDatabaseFrame(page);
		BISystemAppUtils.clickOnVisualizeDataOption(page);
		BISystemAppUtils.clickOnWorkspaceSaveButton(page);
		BISystemAppUtils.enterInsightName(page, insightName);
		BISystemAppUtils.selectProjectName(page, appName, timestamp);

		String expectedInsightToastMessage = "Successfully saved insight(s)";
		String actualInsightToastMessage = BISystemAppUtils.verifySavedInsightSuccessMsg(page);
		assertEquals(actualInsightToastMessage, expectedInsightToastMessage, "Insights creation failed");

	}

	@AfterAll
	public static void cleanUp() {
		CommonUtils.navigateAndDeleteApp(page, appName);
	}

}
