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
import aicore.utils.page.app.DragAndDropBlocksPageUtils;
import aicore.utils.page.app.NotebookPageUtils;
import aicore.utils.page.app.TemplateCreationUtils;

public class NotebookOperationTests extends AbstractPlaywrightTestBase {

	private String appName = "";

	@BeforeEach
	void setup(@PWPage Page page) {
		loginAdmin(page);
		appName = TemplateCreationUtils.createDragAndDropApp(page, "Drag and Drop");
	}

	@AfterEach
	void tearDown(@PWPage Page page) {
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

		Assertions.assertTrue(DragAndDropBlocksPageUtils.verifyPage1IsVisible(page), "Page is not visible");
		Assertions.assertTrue(DragAndDropBlocksPageUtils.verifyWelcomeTextboxIsVisible(page),
				"Welcome text box not visible");
		Assertions.assertEquals("Welcome to the UI Builder! Drag and drop blocks to use in your app.",
				DragAndDropBlocksPageUtils.verifyWelcomeText(page), "Mismatch between the expected and actual message");
	}

	@Test
	@DisplayName("TC01_Verify creation and duplication of new Notebook")
	void testCreationAndDuplicationOfNewNotebook(@PWPage Page page) {
		navigateToAppAndOpenEditor(page);

		NotebookPageUtils.clickOnNotebooksOption(page);
		NotebookPageUtils.clickOnCreateNewNotebook(page);
		NotebookPageUtils.enterQueryName(page, "Test query");
		NotebookPageUtils.clickOnQuerySubmitButton(page);
		NotebookPageUtils.checkNotebookPresence(page, "Test query");

		NotebookPageUtils.duplicateNotebook(page, "Test query");
		NotebookPageUtils.checkNotebookPresence(page, "Test query copy");
	}

	@Test
	@DisplayName("TC02_Verify deletion of new Notebook")
	void testDeletionOfNewNotebook(@PWPage Page page) {
		navigateToAppAndOpenEditor(page);

		NotebookPageUtils.clickOnNotebooksOption(page);
		NotebookPageUtils.clickOnCreateNewNotebook(page);
		NotebookPageUtils.enterQueryName(page, "Test query");
		NotebookPageUtils.clickOnQuerySubmitButton(page);
		NotebookPageUtils.checkNotebookPresence(page, "Test query");

		NotebookPageUtils.deleteNotebook(page, "Test query");
	}

	@Test
	@DisplayName("TC03_Verify Search functionality of new Notebook")
	void testSearchFunctionalityOfNewNotebook(@PWPage Page page) {
		navigateToAppAndOpenEditor(page);

		NotebookPageUtils.clickOnNotebooksOption(page);
		NotebookPageUtils.clickOnCreateNewNotebook(page);
		NotebookPageUtils.enterQueryName(page, "Test query");
		NotebookPageUtils.clickOnQuerySubmitButton(page);
		NotebookPageUtils.checkNotebookPresence(page, "Test query");

		NotebookPageUtils.duplicateNotebook(page, "Test query");
		NotebookPageUtils.checkNotebookPresence(page, "Test query copy");

		NotebookPageUtils.SearchForNotebook(page, "Test query copy");
		NotebookPageUtils.checkNotebookPresence(page, "Test query copy");
	}
}

