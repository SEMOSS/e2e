package aicore.unit.app.system;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import aicore.hooks.SetupHooks;
import aicore.pages.home.HomePageUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.steps.BICreateDatabaseAndInsightSteps;
import aicore.utils.AICorePageUtils;
import aicore.utils.AbstractE2ETest;
import aicore.utils.CommonUtils;
import aicore.utils.page.app.AppPageUtils;
import aicore.utils.page.app.BISystemAppUtils;
import aicore.utils.page.app.CreateAppPopupUtils;

public class BIAppTests extends AbstractE2ETest {
	private static String appName = "Test app";
	private static String databaseNameCSV = "DB created from CSV";
	private static String databaseNameExcel = "DB created from Excel";
	private static String insightName = "Test Automation";
	private static String timestamp = CommonUtils.getTimeStampName();
	
	private static String expectedDBToastMessage = "Success";
	private static String expectedInsightToastMessage = "Successfully saved insight(s)";

	private static final Logger logger = LogManager.getLogger(BIAppTests.class);


	@Test
	public void createDatabaseAndInsightFromCSV_test() throws IOException {

		login(page, UserType.NATIVE);
		HomePageUtils.navigateToHomePage(page);
		
		// create a new app
		MainMenuUtils.openMainMenu(SetupHooks.getPage());
		MainMenuUtils.clickOnOpenAppLibrary(SetupHooks.getPage());
		AppPageUtils.clickOnCreateNewAppButton(page);
		CreateAppPopupUtils.clickOnGetStartedButton(page, "Drag and Drop");
		CreateAppPopupUtils.enterAppName(page, appName, timestamp);
		CreateAppPopupUtils.enterAppDescription(page, "Created by automation script");
		CreateAppPopupUtils.enterTags(page, "Test1, Test2");
		CreateAppPopupUtils.clickOnCreateButton(page);
		
		// add database from csv file
		HomePageUtils.navigateToHomePage(page);
		MainMenuUtils.openMainMenu(SetupHooks.getPage());
		MainMenuUtils.clickOnOpenAppLibrary(SetupHooks.getPage());
		HomePageUtils.clickOnSystemApp(page);
		HomePageUtils.clickOnBIApp(page);
		BISystemAppUtils.closeWelcomePopup(page);
		BISystemAppUtils.clickOnCatalogOption(page);
		BISystemAppUtils.clickOnAddDatabaseButton(page);
		BISystemAppUtils.enterDatabaseName(page, databaseNameCSV, timestamp);
		BISystemAppUtils.uploadCSVFile(page);
		AICorePageUtils.clickOnButton(page, "Next");
		AICorePageUtils.clickOnButton(page, "Import");
		
		// TODO toast disappears quickly need a better way to validate
		String actualDBToastMessage = BISystemAppUtils.verifyDBCreatedToastMessage(page);
		assertEquals(expectedDBToastMessage, actualDBToastMessage, "Database creation failed");
		logger.info("the success toast is quick skipping for now");
		
		// create an insight from the newly created database
		BISystemAppUtils.clickOnAppOption(page);
		BISystemAppUtils.searchDatabaseName(page, databaseNameCSV, timestamp);
		BISystemAppUtils.clickOnAddAllOption(page);
		BISystemAppUtils.clickOnImportButtonOfSelectedColumns(page);
		BISystemAppUtils.mouseHoverOnDatabaseFrame(page);
		BISystemAppUtils.clickOnVisualizeDataOption(page);
		BISystemAppUtils.clickOnWorkspaceSaveButton(page);
		BISystemAppUtils.enterInsightName(page, insightName, timestamp);
		BISystemAppUtils.selectProjectName(page, appName, timestamp);
		
		String actualInsightToastMessage = BISystemAppUtils.verifySavedInsightSuccessMsg(page);
		assertEquals(actualInsightToastMessage, expectedInsightToastMessage, "Insights creation failed");
		
	}
	
	@Test
	public void createDatabaseAndInsightFromExcel_test() throws IOException {
		
		login(page, UserType.NATIVE);
		HomePageUtils.navigateToHomePage(page);
		
		// create a new app
		MainMenuUtils.openMainMenu(SetupHooks.getPage());
		MainMenuUtils.clickOnOpenAppLibrary(SetupHooks.getPage());
		AppPageUtils.clickOnCreateNewAppButton(page);
		CreateAppPopupUtils.clickOnGetStartedButton(page, "Drag and Drop");
		CreateAppPopupUtils.enterAppName(page, appName, timestamp);
		CreateAppPopupUtils.enterAppDescription(page, "Created by automation script");
		CreateAppPopupUtils.enterTags(page, "Test1, Test2");
		CreateAppPopupUtils.clickOnCreateButton(page);
		
		// add database from csv file
		HomePageUtils.navigateToHomePage(page);
		MainMenuUtils.openMainMenu(SetupHooks.getPage());
		MainMenuUtils.clickOnOpenAppLibrary(SetupHooks.getPage());
		HomePageUtils.clickOnSystemApp(page);
		HomePageUtils.clickOnBIApp(page);
		BISystemAppUtils.closeWelcomePopup(page);
		BISystemAppUtils.clickOnCatalogOption(page);
		BISystemAppUtils.clickOnAddDatabaseButton(page);
		
		BISystemAppUtils.selectExcelOption(page);
		BISystemAppUtils.enterDatabaseName(page, databaseNameExcel, timestamp);
		BISystemAppUtils.uploadExcelFile(page);
		AICorePageUtils.clickOnButton(page, "Next");
		AICorePageUtils.clickOnButton(page, "Import");
		
		// TODO toast disappears quickly need a better way to validate
//		String actualDBToastMessage = BISystemAppUtils.verifyDBCreatedToastMessage(page);
//		assertEquals(expectedDBToastMessage, actualDBToastMessage, "Database creation failed");
//		logger.info("the success toast is quick skipping for now");
		
		// create an insight from the newly created database
		BISystemAppUtils.clickOnAppOption(page);
		BISystemAppUtils.searchDatabaseName(page, databaseNameExcel, timestamp);
		BISystemAppUtils.clickOnAddAllOption(page);
		BISystemAppUtils.clickOnImportButtonOfSelectedColumns(page);
		BISystemAppUtils.mouseHoverOnDatabaseFrame(page);
		BISystemAppUtils.clickOnVisualizeDataOption(page);
		BISystemAppUtils.clickOnWorkspaceSaveButton(page);
		BISystemAppUtils.enterInsightName(page, insightName, timestamp);
		BISystemAppUtils.selectProjectName(page, appName, timestamp);
		
		String actualInsightToastMessage = BISystemAppUtils.verifySavedInsightSuccessMsg(page);
		assertEquals(actualInsightToastMessage, expectedInsightToastMessage, "Insights creation failed");
				
	}
	
	
	@AfterEach
	public static void cleanUp() {
		CommonUtils.navigateAndDeleteApp(page, appName + " " + timestamp);
	}

}
