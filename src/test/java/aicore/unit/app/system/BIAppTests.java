package aicore.unit.app.system;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import aicore.hooks.SetupHooks;
import aicore.pages.home.HomePageUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.utils.AbstractE2ETest;
import aicore.utils.CommonUtils;
import aicore.utils.page.app.AppPageUtils;
import aicore.utils.page.app.BISystemAppUtils;
import aicore.utils.page.app.CreateAppPopupUtils;

public class BIAppTests extends AbstractE2ETest {
	private static String appName = "Test app";
	private static String timestamp = CommonUtils.getTimeStampName();


	@Test
	public void test() throws IOException {

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


	}
	
	@AfterAll
	public static void cleanUp() {
		CommonUtils.navigateAndDeleteApp(page, appName + " "+timestamp);
	}

}
