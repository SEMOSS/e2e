package aicore.unit.DragAndDrop;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;

import aicore.pages.model.SettingsModelPageUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.CatlogAccessPageUtility;
import aicore.utils.CommonUtils;
import aicore.utils.annotations.PWPage;
import aicore.utils.page.app.DragAndDropBlocksPageUtils;
import aicore.utils.page.app.TemplateCreationUtils;

public class DragAndDropCommitTests extends AbstractPlaywrightTestBase {

	private String appName = "";

	@BeforeEach
	void setup(@PWPage Page page) {
		loginAdmin(page);
		appName = TemplateCreationUtils.createDragAndDropApp(page, "Drag and Drop");

		Assertions.assertTrue(DragAndDropBlocksPageUtils.verifyPage1IsVisible(page), "Page is not visible");
		Assertions.assertTrue(DragAndDropBlocksPageUtils.verifyWelcomeTextboxIsVisible(page),
				"Welcome text box not visible");
		Assertions.assertEquals("Welcome to the UI Builder! Drag and drop blocks to use in your app.",
				DragAndDropBlocksPageUtils.verifyWelcomeText(page), "Mismatch between the expected and actual message");

		DragAndDropBlocksPageUtils.clickOnBlockSettingsOption(page);
	}

	@AfterEach
	void tearDown(@PWPage Page page) {
		CommonUtils.navigateAndDeleteApp(page, appName);
		logout(page);
	}

	@Test
	@DisplayName("TC01_Setting page - Commit Tab - validate commit tab functionality")
	void testCommitTabFunctionality(@PWPage Page page) {
		CatlogAccessPageUtility.clickOnSettings(page);

		Assertions.assertTrue(CatlogAccessPageUtility.canSeeCommitsTab(page), "Commits tab is not visible");

		CatlogAccessPageUtility.clickOnCommitsTab(page);

		Assertions.assertTrue(CatlogAccessPageUtility.isCommitHistoryTitleVisible(page, "Commit History"),
				"Expected title 'Commit History' is not visible");

		Assertions.assertTrue(
				CatlogAccessPageUtility.getCommitMessage(page, "Initial creation of project"),
				"Expected commit message 'Initial creation of project' is not visible in Commits section");

		DragAndDropBlocksPageUtils.clickOnBlocksOption(page);
		DragAndDropBlocksPageUtils.selectPage(page, "page-1");
		DragAndDropBlocksPageUtils.mouseHoverOnBlock(page, "Area Chart");
		DragAndDropBlocksPageUtils.blockDropPosition(page, "Area Chart");
		DragAndDropBlocksPageUtils.clickOnSaveAppButton(page);

		SettingsModelPageUtils.pageReload(page);

		DragAndDropBlocksPageUtils.selectPage(page, "AppSettings");
		CatlogAccessPageUtility.clickOnCommitsTab(page);

		Assertions.assertTrue(CatlogAccessPageUtility.getCommitMessage(page, "Commited on"),
				"Expected commit message 'Commited on' is not visible in Commits section");
	}
}

