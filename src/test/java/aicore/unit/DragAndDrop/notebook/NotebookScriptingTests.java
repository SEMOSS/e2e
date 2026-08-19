package aicore.unit.DragAndDrop.notebook;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
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
import aicore.utils.page.app.BlockSettingsUtils;
import aicore.utils.page.app.DragAndDropBlocksPageUtils;
import aicore.utils.page.app.NotebookPageUtils;
import aicore.utils.page.app.TemplateCreationUtils;

public class NotebookScriptingTests extends AbstractPlaywrightTestBase {
	private static final Logger logger = LogManager.getLogger(NotebookScriptingTests.class);
    SoftAssertions softAssert = new SoftAssertions();
	private String appName = "";

	@BeforeEach
	void setup(@PWPage Page page) {
		loginAdmin(page);
		logger.info("BEFORE ALL: creating App");
		appName = TemplateCreationUtils.createDragAndDropApp(page, "Drag and Drop");
	}

	@AfterEach
	void tearDown(@PWPage Page page) {
		logger.info("AFTER ALL: Deleting App ");
		CommonUtils.navigateAndDeleteApp(page, appName);
		logout(page);
	}

	private void navigateToAppAndOpenEditor(Page page) {
		HomePageUtils.navigateToHomePage(page);
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenAppLibrary(page);
		AppPageUtils.searchApp(page, appName, "");
		AppPageUtils.clickOnAppCard(page, appName, "");
		DragAndDropBlocksPageUtils.clickOnEditButton(page);
		
		
	    BlockSettingsUtils.closeBlockSettings(page);


		Assertions.assertTrue(DragAndDropBlocksPageUtils.verifyPage1IsVisible(page), "Page is not visible");
		Assertions.assertTrue(DragAndDropBlocksPageUtils.verifyWelcomeTextboxIsVisible(page),
				"Welcome text box not visible");
		Assertions.assertEquals("Welcome to the UI Builder! Drag and drop blocks to use in your app.",
				DragAndDropBlocksPageUtils.verifyWelcomeText(page), "Mismatch between the expected and actual message");
	}
	

	@Test
	@DisplayName("TC01_Pixel Output in Notebook")
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
		NotebookPageUtils.enterCodeInQuery(page, "HelloWorld();");
		
		//NotebookPageUtils.clickOnRunAllButton(page);
		NotebookPageUtils.clickOnRunAllCellButton(page);

		NotebookPageUtils.getPixelOutput(page, "HelloWorld();");
	}

	@Test
	@DisplayName("TC02_Python Output in Notebook")
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
		NotebookPageUtils.clickOnRunAllCellButton(page);

		NotebookPageUtils.getPythonOutput(page, "2");
	}
}



