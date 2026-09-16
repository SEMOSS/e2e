package aicore.unit.DragAndDrop.AppLandingPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.microsoft.playwright.Page;
import aicore.pages.home.HomePageUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.AddFunctionPageUtils;
import aicore.utils.CatlogAccessPageUtility;
import aicore.utils.CommonUtils;
import aicore.utils.annotations.PWPage;
import aicore.utils.page.app.AppPageUtils;
import aicore.utils.page.app.CreateAppPopupUtils;
import aicore.utils.page.app.DragAndDropBlocksPageUtils;
import aicore.utils.page.app.TemplateCreationUtils;

public class AppLandingPage extends AbstractPlaywrightTestBase {
	
	private String appName;
	private String copiedId;
	private String clonedApp = "App clone";
	
	@BeforeEach
	void setup(@PWPage Page page) {
		loginNativeAdmin(page);
		appName = TemplateCreationUtils.createDragAndDropApp(page, "Drag and Drop");
		verifyAppCreated(page);
		openAppLibrary(page);
		AppPageUtils.selectAppCardsView(page, "Grid view");	
	}
	
	
	@AfterEach
	void tearDown(@PWPage Page page) {
		CommonUtils.navigateAndDeleteApp(page, appName);
	    logout(page);
	}
	
	private void verifyAppCardDetails(Page page, String appName) {

		String baseAppName = appName.substring(0, appName.lastIndexOf(" "));
		String timestamp = appName.substring(appName.lastIndexOf(" ") + 1);

		Assertions.assertTrue(
		        AppPageUtils.isContentVisibleOnAppCard(
		                page, "App Name", baseAppName, timestamp),
		        "App Name is not displayed on app card"
		);

		Assertions.assertTrue(
		        AppPageUtils.isContentVisibleOnAppCard(
		                page, "App Description", "Created by automation script", ""),
		        "App Description is not displayed on app card"
		);

		Assertions.assertTrue(
		        AppPageUtils.isContentVisibleOnAppCard(
		                page, "Open App button", "Open", ""),
		        "Open App button is not displayed on app card"
		);

		Assertions.assertTrue(
		        AppPageUtils.isContentVisibleOnAppCard(
		                page, "Info button", "Info", ""),
		        "Info button is not displayed on app card"
		);

		Assertions.assertTrue(
		        AppPageUtils.isContentVisibleOnAppCard(
		                page, "More Vert Icon", "", ""),
		        "More Vert Icon is not displayed on app card"
		);

		Assertions.assertTrue(
		        AppPageUtils.isContentVisibleOnAppCard(
		                page, "Bookmark Icon", "", ""),
		        "Bookmark Icon is not displayed on app card"
		);
	

		}


	
	private void verifyAppCreated(Page page) {
	    String appName = CreateAppPopupUtils.userFetchAppName(page);
	    Assertions.assertFalse(appName.isEmpty(), "Fetched App Name is Empty");
	}
	
	private void openAppLibrary(Page page) {
	    HomePageUtils.navigateToHomePage(page);
	    MainMenuUtils.openMainMenu(page);
	    MainMenuUtils.clickOnOpenAppLibrary(page);
	}
	
	private void verifyAppDisplayed(Page page, String appName) {
	    Assertions.assertTrue(
	            AppPageUtils.isAppDisplayedOnPage(page, appName, ""),
	            "Application '" + appName + "' is not displayed on the page"
	    );
	}
	
	private void copyAppId(Page page, String icon) {
	    copiedId = AppPageUtils.getCopiedId(page, icon);
	}
	
	private void verifyAppIdCopiedToast(Page page, String expectedToastMessage) {
	    String actualToastMessage = AppPageUtils.getAppIdCopiedToastMessage(page);

	    Assertions.assertEquals(
	            expectedToastMessage,
	            actualToastMessage,
	            "Toast message text is incorrect"
	    );
	}
	
	private void clickOnOption(Page page, String optionName) {
	    copiedId = AppPageUtils.clickOnOption(page, optionName);
	}
	
	private void verifyAppDisplayedInBookmarkedSection(Page page, String appName) {
	    Assertions.assertTrue(
	            DragAndDropBlocksPageUtils.isBookmarkAppDisplayedInBookmarkSection(page, appName),
	            "Bookmarked section does not contain the expected app"
	    );
	}
	
	private void verifyAppNotDisplayedInBookmarkedSection(Page page, String appName) {
		Assertions.assertFalse(
		DragAndDropBlocksPageUtils.isBookmarkAppDisplayedInBookmarkSection(page, appName),
		"Bookmarked section contains the expected app"
		);
	}
	
	private void verifyAppDisplayedInMyAppsSection(Page page, String appName) {
		Assertions.assertTrue(
		DragAndDropBlocksPageUtils.isAppDisplayedInAllAppsSection(page, appName),
		"Created Application is not displayed in All Apps section"
		);
	}
	
	private void verifyCreatedAppDisplayedInDiscoverableApps(Page page, String appName) {
		Assertions.assertTrue(
		DragAndDropBlocksPageUtils.createdAppDisplayInDiscoverableApp(page, appName),
		"Created Application is not displayed in Discoverable Apps section"
		);
	}

	@Test
    public void VerifyAppCardDetails_test (@PWPage Page page) {
		
		AppPageUtils.searchApp(page, appName, "");
		verifyAppDisplayed(page, appName);
	    verifyAppCardDetails(page, appName);
		
	}
	
	@Test
    public void UserCopyAppId_test (@PWPage Page page) {
		
		AppPageUtils.searchApp(page, appName, "");
		AppPageUtils.selectAppCardsView(page, "List view");
		copyAppId(page, "Copy app ID");
		verifyAppIdCopiedToast(page, "App ID copied to clipboard");
		openAppLibrary(page);
		AppPageUtils.searchAppId(page, copiedId);
		verifyAppDisplayed(page, appName);		
	}

	@Test
    public void UserCloneApp_test (@PWPage Page page) {
		AppPageUtils.searchApp(page, appName, "");
		AppPageUtils.clickOnMoreVertIcon(page, appName, "");
		clickOnOption(page, "Clone App");
		AppPageUtils.enterCloneAppName(page, clonedApp, "");
		AppPageUtils.enterCloneAppDescription(page, "cloned app");
		AppPageUtils.clickOnButton(page, "Next");
		AppPageUtils.clickOnButton(page, "Clone");
		openAppLibrary(page);
		AppPageUtils.searchApp(page, clonedApp, "");
		verifyAppDisplayed(page, clonedApp);		
	}

	@Test
    public void VerifyBookmarkAndUnbookmark_test (@PWPage Page page) {
		
		AppPageUtils.searchApp(page, appName, "");
		DragAndDropBlocksPageUtils.clickBookmarkIcon(page, appName);
		DragAndDropBlocksPageUtils.clickOnBookmarkedAppTab(page);
		verifyAppDisplayedInBookmarkedSection(page, appName);
		DragAndDropBlocksPageUtils.clickOnUnbookmarkforApp(page, appName);
		verifyAppNotDisplayedInBookmarkedSection(page, appName);
		DragAndDropBlocksPageUtils.clickOnMyAppsTab(page);
	}
		
	@Test
    public void VerifyAppDisplayedInMyApps_test (@PWPage Page page) {
		
		AppPageUtils.searchApp(page, appName, "");
		verifyAppDisplayedInMyAppsSection(page, appName);
	}
		
	@Test
    public void VerifyAppDisplayedUnderDiscoverable_test (@PWPage Page page) {
		
		AppPageUtils.searchApp(page, appName, "");
		verifyAppDisplayed(page, appName);		
		AppPageUtils.clickOnInfoButton(page, "Info");
		AddFunctionPageUtils.clickOnAccessControl(page);
		CatlogAccessPageUtility.setToggleStateForNonDiscovrable(page);
	    logout(page);
	    loginEditor(page);
		openAppLibrary(page);
		DragAndDropBlocksPageUtils.clickOnDiscovrableApps(page);
		AppPageUtils.searchApp(page, appName, "");
		verifyCreatedAppDisplayedInDiscoverableApps(page, appName);
	    logout(page);
		loginNativeAdmin(page);
		openAppLibrary(page);
	}
}
