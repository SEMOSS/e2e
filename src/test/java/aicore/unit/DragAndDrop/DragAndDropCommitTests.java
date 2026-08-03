package aicore.unit.DragAndDrop;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;

import aicore.pages.home.HomePageUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.pages.model.SettingsModelPageUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.CatlogAccessPageUtility;
import aicore.utils.CommonUtils;
import aicore.utils.annotations.PWPage;
import aicore.utils.page.app.AppPageUtils;
import aicore.utils.page.app.CreateAppPopupUtils;
import aicore.utils.page.app.DragAndDropBlocksPageUtils;


public class DragAndDropCommitTests extends AbstractPlaywrightTestBase {

	private static final String APP_NAME = "Test app";

	private String timestamp = "";

	@BeforeEach
	void setup(@PWPage Page page) {
		timestamp = CommonUtils.getTimeStampName();

		loginAdmin(page);

		MainMenuUtils.openMainMenu(page);
		HomePageUtils.navigateToHomePage(page);
		MainMenuUtils.clickOnOpenAppLibrary(page);
		AppPageUtils.clickOnCreateNewAppButton(page);
		CreateAppPopupUtils.clickOnGetStartedButton(page, "Drag and Drop");
		CreateAppPopupUtils.enterAppName(page, APP_NAME + timestamp);
		CreateAppPopupUtils.clickOnCreateButton(page);
		String fetchName = CreateAppPopupUtils.userFetchAppName(page);
		Assertions.assertFalse(fetchName.isEmpty(), "Fetched App Name is Empty");

		boolean isPage1Visible = DragAndDropBlocksPageUtils.verifyPage1IsVisible(page);
		Assertions.assertTrue(isPage1Visible, "Page is not visible");
		boolean isWelcomeTextboxVisible = DragAndDropBlocksPageUtils.verifyWelcomeTextboxIsVisible(page);
		Assertions.assertTrue(isWelcomeTextboxVisible, "Welcome text box not visible");
		String actualWelcomeTextMessage = DragAndDropBlocksPageUtils.verifyWelcomeText(page);
		Assertions.assertEquals("Welcome to the UI Builder! Drag and drop blocks to use in your app.",
				actualWelcomeTextMessage, "Mismatch between the expected and actual message");

		DragAndDropBlocksPageUtils.clickOnBlockSettingsOption(page);
	}

	@AfterEach
	void tearDown(@PWPage Page page) {
		CommonUtils.navigateAndDeleteApp(page, APP_NAME + timestamp);
		logout(page);
	}

	@Test
	@DisplayName("Setting page - Commit Tab - validate commit tab functionality")
	void testCommitTabFunctionality(@PWPage Page page) {
		CatlogAccessPageUtility.clickOnSettings(page);

		boolean canSeeCommitsTab = CatlogAccessPageUtility.canSeeCommitsTab(page);
		Assertions.assertTrue(canSeeCommitsTab, "Commits tab is not visible");

		CatlogAccessPageUtility.clickOnCommitsTab(page);

		boolean isTitleVisible = CatlogAccessPageUtility.isCommitHistoryTitleVisible(page, "Commit History");
		Assertions.assertTrue(isTitleVisible, "Expected title 'Commit History' is not visible");

		boolean isInitialCommitMessageVisible = CatlogAccessPageUtility.getCommitMessage(page,
				"Initial creation of project");
		Assertions.assertTrue(isInitialCommitMessageVisible,
				"Expected commit message 'Initial creation of project' is not visible in Commits section");

		DragAndDropBlocksPageUtils.clickOnBlocksOption(page);
		DragAndDropBlocksPageUtils.selectPage(page, "page-1");
		DragAndDropBlocksPageUtils.mouseHoverOnBlock(page, "Area Chart");
		DragAndDropBlocksPageUtils.blockDropPosition(page, "Area Chart");
		DragAndDropBlocksPageUtils.clickOnSaveAppButton(page);

		SettingsModelPageUtils.pageReload(page);

		DragAndDropBlocksPageUtils.selectPage(page, "AppSettings");
		CatlogAccessPageUtility.clickOnCommitsTab(page);

		boolean isCommitedOnMessageVisible = CatlogAccessPageUtility.getCommitMessage(page, "Commited on");
		Assertions.assertTrue(isCommitedOnMessageVisible,
				"Expected commit message 'Commited on' is not visible in Commits section");
	}
}

