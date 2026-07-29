package aicore.unit.DragAndDrop.notebook;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;

import aicore.pages.home.HomePageUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.CommonUtils;
import aicore.utils.annotations.PWPage;
import aicore.utils.page.app.AppPageUtils;
import aicore.utils.page.app.CreateAppPopupUtils;
import aicore.utils.page.app.DragAndDropBlocksPageUtils;
import aicore.utils.page.app.NotebookPageUtils;


public class NotebookScriptingTests extends AbstractPlaywrightTestBase {

	private static final String APP_NAME = "Test app";
	private String timestamp = "";

	@BeforeEach
	void setup(@PWPage Page page) {
		timestamp = CommonUtils.getTimeStampName();

		loginAdmin(page);

		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenAppLibrary(page);
		AppPageUtils.clickOnCreateNewAppButton(page);
		CreateAppPopupUtils.clickOnGetStartedButton(page, "Drag and Drop");
		CreateAppPopupUtils.enterAppName(page, APP_NAME + timestamp);
		CreateAppPopupUtils.clickOnCreateButton(page);
		String fetchName = CreateAppPopupUtils.userFetchAppName(page);
		Assertions.assertFalse(fetchName.isEmpty(), "Fetched App Name is Empty");
	}

	@AfterEach
	void tearDown(@PWPage Page page) {
		CommonUtils.navigateAndDeleteApp(page, APP_NAME + timestamp);
		logout(page);
	}

	private void navigateToAppAndOpenEditor(Page page) {
		HomePageUtils.navigateToHomePage(page);
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenAppLibrary(page);
		AppPageUtils.searchApp(page, APP_NAME, timestamp);
		AppPageUtils.clickOnAppCard(page, APP_NAME, timestamp);
		DragAndDropBlocksPageUtils.clickOnEditButton(page);

		boolean isPage1Visible = DragAndDropBlocksPageUtils.verifyPage1IsVisible(page);
		Assertions.assertTrue(isPage1Visible, "Page is not visible");
		boolean isWelcomeTextboxVisible = DragAndDropBlocksPageUtils.verifyWelcomeTextboxIsVisible(page);
		Assertions.assertTrue(isWelcomeTextboxVisible, "Welcome text box not visible");
		String actualWelcomeTextMessage = DragAndDropBlocksPageUtils.verifyWelcomeText(page);
		Assertions.assertEquals("Welcome to the UI Builder! Drag and drop blocks to use in your app.",
				actualWelcomeTextMessage, "Mismatch between the expected and actual message");
	}

	@Test
	@DisplayName("Pixel Output in Notebook")
	void testPixelOutputInNotebook(@PWPage Page page) {
		navigateToAppAndOpenEditor(page);

		NotebookPageUtils.clickOnNotebooksOption(page);
		NotebookPageUtils.clickOnCreateNewNotebook(page);
		NotebookPageUtils.enterQueryName(page, "Test query");
		NotebookPageUtils.clickOnQuerySubmitButton(page);

		NotebookPageUtils.checkPythonAsDefaultLanguage(page);

		NotebookPageUtils.changeToLanguage(page, "Pixel");
		NotebookPageUtils.mouseHoverOnNotebookHiddenOptions(page);
		NotebookPageUtils.hoverAndClickOnCell(page);
		NotebookPageUtils.deleteFirstCell(page);
		NotebookPageUtils.enterCodeInQuery(page, "Hello");
		NotebookPageUtils.clickOnRunAllButton(page);

		NotebookPageUtils.getPixelOutput(page, "Hello");
	}

	@Test
	@DisplayName("Python Output in Notebook")
	void testPythonOutputInNotebook(@PWPage Page page) {
		navigateToAppAndOpenEditor(page);

		NotebookPageUtils.clickOnNotebooksOption(page);
		NotebookPageUtils.clickOnCreateNewNotebook(page);
		NotebookPageUtils.enterQueryName(page, "Test query");
		NotebookPageUtils.clickOnQuerySubmitButton(page);

		NotebookPageUtils.checkPythonAsDefaultLanguage(page);

		NotebookPageUtils.mouseHoverOnNotebookHiddenOptions(page);
		NotebookPageUtils.hoverAndClickOnCell(page);
		NotebookPageUtils.deleteFirstCell(page);
		NotebookPageUtils.enterCodeInQuery(page, "1+1");
		NotebookPageUtils.clickOnRunAllButton(page);

		NotebookPageUtils.getPythonOutput(page, "2");
	}
}

