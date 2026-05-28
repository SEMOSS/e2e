package aicore.unit.app.codeApp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;

import aicore.pages.home.MainMenuUtils;
import aicore.pages.model.AddModelFormUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.AddFunctionPageUtils;
import aicore.utils.AgentBuilderAppsUtils;
import aicore.utils.CatlogAccessPageUtility;
import aicore.utils.CommonUtils;
import aicore.utils.TestResourceTrackerHelper;
import aicore.utils.TestResources;
import aicore.utils.UploadCatalogUtils;
import aicore.utils.annotations.PWPage;
import aicore.utils.page.app.AppPageUtils;
import aicore.utils.page.app.CreateAppPopupUtils;
import aicore.utils.page.app.DragAndDropBlocksPageUtils;
import aicore.utils.page.model.ModelPageUtils;

public class BookmarkCodeAppTests extends AbstractPlaywrightTestBase {
	private String testAppName = "";
	
	@BeforeEach
	void setup(@PWPage Page page) {
		String timestamp = CommonUtils.getTimeStampName();
		testAppName = "Code app " + timestamp; // catalog name
		
		loginNativeAdmin(page);
		// create app
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenAppLibrary(page);
		AppPageUtils.clickOnCreateNewAppButton(page);
		CreateAppPopupUtils.clickOnGetStartedButton(page, "Develop in code");
		CreateAppPopupUtils.enterAppName(page, testAppName);
		CreateAppPopupUtils.enterAppDescription(page, "Created by automation script");
		CreateAppPopupUtils.enterTags(page, "MCP");
		CreateAppPopupUtils.clickOnCreateButton(page);
		String fetchName = CreateAppPopupUtils.userFetchAppName(page);
        Assertions.assertFalse(fetchName.isEmpty(), "Fetched App Name is Empty");
	}	
	
	@AfterEach
	void tearDown(@PWPage Page page) {
		CommonUtils.navigateAndDeleteApp(page, testAppName);
		logout(page);
	}
	
	@Test
	void testVerifyBookmarkAndUnbookmarkTheCodeApp(@PWPage Page page) {
		String expectedSuccessToastMessageBookmarked = "App bookmarked";
		String expectedSuccessToastMessageUnbookmarked = "App unbookmarked";
		
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenAppLibrary(page);
		AppPageUtils.searchApp(page, testAppName, null);
		DragAndDropBlocksPageUtils.clickBookmarkIcon(page, testAppName);
		{ /// bookmarked toast message
			String actualMessage = AddFunctionPageUtils.verifySuccessToastMessage(page, expectedSuccessToastMessageBookmarked);
			Assertions.assertEquals(expectedSuccessToastMessageBookmarked, actualMessage,
					"Toaster is not matching with expected");
			AddFunctionPageUtils.closeToastMessage(page);
		}
		DragAndDropBlocksPageUtils.clickOnBookmarkedAppTab(page);
		boolean isBookmarked = DragAndDropBlocksPageUtils.isBookmarkAppDisplayedInBookmarkSection(page, testAppName);
		Assertions.assertTrue(isBookmarked, "Bookmarked section does not contain the expected app");
		DragAndDropBlocksPageUtils.clickOnUnbookmarkforApp(page, testAppName);
		{ // un-bookmarked toast message
			String actualMessage = AddFunctionPageUtils.verifySuccessToastMessage(page, expectedSuccessToastMessageUnbookmarked);
			Assertions.assertEquals(expectedSuccessToastMessageUnbookmarked, actualMessage, "Toaster is not matching with expected");
			AddFunctionPageUtils.closeToastMessage(page);
		}
		isBookmarked = DragAndDropBlocksPageUtils.isBookmarkAppDisplayedInBookmarkSection(page, testAppName);
		Assertions.assertFalse(isBookmarked, "Bookmarked section contains the expected app");
		DragAndDropBlocksPageUtils.clickOnMyAppsTab(page);
	}
	
	@Test
	void testBookmarkAndVerifyToastMessageAndPresenceOnAppHomePage(@PWPage Page page) {
//		MainMenuUtils.openMainMenu(page);
//		MainMenuUtils.clickOnOpenAppLibrary(page);
//		AppPageUtils.searchApp(page, testAppName, null);
		UploadCatalogUtils.seeFileTabIsOpenByDefault(page);
		CreateAppPopupUtils.userClickOnBreadcrumbLink(page, testAppName, null);
		UploadCatalogUtils.clickOnBookmarkAppIcon(page);
		String toastMessage = "Project bookmarked";
		String actualMessage = CatlogAccessPageUtility.getToastMessage(page, toastMessage);
		Assertions.assertEquals(toastMessage, actualMessage, "Toaster is not matching with expected");
	}
}
