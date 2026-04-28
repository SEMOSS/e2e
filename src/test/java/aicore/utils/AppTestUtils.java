package aicore.utils;

import com.microsoft.playwright.Page;

import aicore.hooks.SetupHooks;
import aicore.pages.home.MainMenuUtils;
import aicore.utils.page.app.AppPageUtils;
import aicore.utils.page.app.CreateAppPopupUtils;

public class AppTestUtils {
	
	public static String createApp(Page page, String appName) {
		// create a new app
		MainMenuUtils.openMainMenu(SetupHooks.getPage());
		MainMenuUtils.clickOnOpenAppLibrary(SetupHooks.getPage());
		AppPageUtils.clickOnCreateNewAppButton(page);
		CreateAppPopupUtils.clickOnGetStartedButton(page, "Drag and Drop");
		CreateAppPopupUtils.enterAppName(page, appName);
		CreateAppPopupUtils.enterAppDescription(page, "Created by automation script");
		CreateAppPopupUtils.enterTags(page, "Test1, Test2");
		CreateAppPopupUtils.clickOnCreateButton(page);
		
		// TODO should return appID
		return appName;
	}

}
