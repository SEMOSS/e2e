package aicore.unit.DragAndDrop;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import aicore.pages.home.MainMenuUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.CommonUtils;
import aicore.utils.annotations.PWPage;
import aicore.utils.page.app.AppPageUtils;
import aicore.utils.page.app.BlockSettingsUtils;
import aicore.utils.page.app.DragAndDropBlocksPageUtils;
import aicore.utils.page.app.NotebookPageUtils;
import aicore.utils.page.app.TemplateCreationUtils;

public class BlocksTests extends AbstractPlaywrightTestBase {
	private static final Logger logger = LogManager.getLogger(BlocksTests.class);
    SoftAssertions softAssert = new SoftAssertions();
	private String appName = "";
	private String blockText = "";

	@BeforeEach
	void setup(@PWPage Page page) {
		loginAdmin(page);
		logger.info("BEFORE ALL: creating App");
		appName = TemplateCreationUtils.createDragAndDropApp(page, "Drag and Drop");

		Assertions.assertTrue(DragAndDropBlocksPageUtils.verifyPage1IsVisible(page), "Page is not visible");
		Assertions.assertTrue(DragAndDropBlocksPageUtils.verifyWelcomeTextboxIsVisible(page),
				"Welcome text box not visible");
		Assertions.assertEquals("Welcome to the UI Builder! Drag and drop blocks to use in your app.",
				DragAndDropBlocksPageUtils.verifyWelcomeText(page), "Mismatch between the expected and actual message");

		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenAppLibrary(page);
		AppPageUtils.searchApp(page, appName, "");
		AppPageUtils.clickOnAppCard(page, appName, "");

		DragAndDropBlocksPageUtils.clickOnEditButton(page);

		BlockSettingsUtils.clickOnBlockSettingsOption(page);
		
		DragAndDropBlocksPageUtils.clickOnBlocksOption(page);
		
		//BlockSettingsUtils.closeBlockSettings(page);

	}

	@AfterEach
	void tearDown(@PWPage Page page) {
		logger.info("After ALL: creating App");
		CommonUtils.navigateAndDeleteApp(page, appName);
		logout(page);
	}

	@Test
	@DisplayName("TC01_Drag and Drop Heading 1 block")
	void testDragAndDropHeading1Block(@PWPage Page page) {
		DragAndDropBlocksPageUtils.mouseHoverOnBlock(page, "Text (h1)");
		DragAndDropBlocksPageUtils.blockDropPosition(page, "Text (h1)");

		String actualHeadingBlockTextMessage = DragAndDropBlocksPageUtils.verifyHeadingBlockTextMessage(page);
		Assertions.assertEquals("Hello world", actualHeadingBlockTextMessage,
				"Mismatch between the expected and actual heading block text");
	}

	
	static Stream<Arguments> textSectionBlocks() {
		return Stream.of(
				Arguments.of("Link", "https://playwright.dev/", "Test App Link", "Bold, Italic",
						"Bold, Italic, Underlined", "Times New Roman", "#ff5733", "Center"),
				Arguments.of("Text (h1)", "", "Heading 1 block", "Italic, Underlined", "Bold, Italic, Underlined",
						"Times New Roman", "#ff6666", "Center"),
				Arguments.of("Text (h2)", "", "Heading 2 block", "Italic, Underlined", "Bold, Italic, Underlined",
						"Arial", "#ff9900", "Left"),
				Arguments.of("Text (h3)", "", "Heading 3 block", "Italic, Underlined", "Bold, Italic, Underlined",
						"Roboto", "#ffcc00", "Right"),
				Arguments.of("Text (h4)", "", "Heading 4 block", "Italic, Underlined", "Bold, Italic, Underlined",
						"Times New Roman", "#ff5733", "Justify"),
				Arguments.of("Text (h5)", "", "Heading 5 block", "Italic, Underlined", "Bold, Italic, Underlined",
						"Helvetica", "#ff007f", "Center"),
				Arguments.of("Text (h6)", "", "Heading 6 block", "Italic, Underlined", "Bold, Italic, Underlined",
						"Georgia", "#ff5733", "Center"),
				Arguments.of("Text", "", "Text block", "Bold, Italic, Underlined", "Bold, Italic, Underlined",
						"Arial", "#ffcc00", "Right"),
				Arguments.of("Markdown", "", "**Markdown** block", "Bold, Italic", "Bold, Italic", "Times New Roman",
						"#ffcc00", "Left"));
	}

	@ParameterizedTest(name = "Drag and Drop Text section {0} block")
	@DisplayName("TC02_Drag and Drop Text section block - styling and destination")
	void testDragAndDropTextSectionBlock(String blockName, String destination, String text, String styles,
			String expectedStyles, String font, String hexColor, String textAlignment, @PWPage Page page) {

		DragAndDropBlocksPageUtils.mouseHoverOnBlock(page, blockName);
		DragAndDropBlocksPageUtils.blockDropPosition(page, blockName);
		DragAndDropBlocksPageUtils.clickOnDroppedBlock(page, blockName);
		BlockSettingsUtils.clickOnBlockSettingsOption(page);

		if (!destination.trim().isEmpty()) {
			BlockSettingsUtils.enterDestination(page, destination);
		}

		if ("Markdown".equals(blockName)) {
			BlockSettingsUtils.enterMarkdown(page, text);
		} else {
			BlockSettingsUtils.enterText(page, text);
		}

		BlockSettingsUtils.userSelectsTheAppearanceTab(page);
		BlockSettingsUtils.selectTextStyle(page, styles);
		BlockSettingsUtils.selectTextFont(page, font);
		BlockSettingsUtils.selectTextColor(page, hexColor.trim());
		BlockSettingsUtils.selectTextAlign(page, textAlignment);
		DragAndDropBlocksPageUtils.clickOnSaveAppButton(page);

		String expectedBlockText = "Markdown".equals(blockName) ? text.replaceAll("\\*\\*(.*?)\\*\\*", "$1") : text;
		blockText = expectedBlockText;

		String actualText = DragAndDropBlocksPageUtils.getBlockText(page, blockName, blockText);
		Assertions.assertEquals(blockText, actualText, "Mismatch between the expected and actual text");

		Locator textBlockLocator = DragAndDropBlocksPageUtils.textSectionDragAndDroppedBlockLocator(page, blockName,
				blockText);
		List<String> appliedTextStyles = Arrays.asList(expectedStyles.split(", "));
		List<String> actualAppliedTextStyles = CommonUtils.getAppliedStyles(textBlockLocator);
		Assertions.assertEquals(appliedTextStyles, actualAppliedTextStyles,
				"Mismatch between the expected and actual text styles");

		String actualTextFont = DragAndDropBlocksPageUtils.getBlockTextFont(page, blockName, blockText);
		Assertions.assertEquals(font, actualTextFont, "Mismatch between the expected and actual text font");

		String actualTextColor = DragAndDropBlocksPageUtils.getBlockTextColor(page, blockName, blockText);
		String expectedTextColor = "rgb(" + CommonUtils.hexToRGBConversion(hexColor) + ")";
		Assertions.assertEquals(expectedTextColor, actualTextColor,
				"Mismatch between the expected and actual text color");

		String actualTextAlign = DragAndDropBlocksPageUtils.getBlockTextAlign(page, blockName, blockText);
		Assertions.assertEquals(textAlignment.toLowerCase(), actualTextAlign,
				"Mismatch between the expected and actual text align");

		if (!destination.trim().isEmpty()) {
			DragAndDropBlocksPageUtils.clickOnLink(page, blockText);
			String actualUrl = DragAndDropBlocksPageUtils.getDestinationUrl(page, destination);
			Assertions.assertEquals(destination, actualUrl, "Mismatch between the expected and actual link destination");
			DragAndDropBlocksPageUtils.navigateToPreviosPage(page);
		}
	}

	@Test
	@DisplayName("TC03_Drag and Drop Text section Logs block")
	void testDragAndDropLogsBlock(@PWPage Page page) {
		NotebookPageUtils.clickOnNotebooksOption(page);
		NotebookPageUtils.clickOnCreateNewNotebook(page);
		NotebookPageUtils.enterQueryName(page, "Test query");
		NotebookPageUtils.clickOnQuerySubmitButton(page);
		NotebookPageUtils.enterCodeInQuery(page, "print(\"Hello word\")");
		NotebookPageUtils.clickOnRunCellButton(page);

		String actualCodeOutput = NotebookPageUtils.getCodeOutput(page, "Hello word");
		Assertions.assertEquals("Hello word", actualCodeOutput, "Mismatch between the expected and actual code output");

		DragAndDropBlocksPageUtils.clickOnBlocksOption(page);
		DragAndDropBlocksPageUtils.selectPage(page, "page-1");
		DragAndDropBlocksPageUtils.mouseHoverOnBlock(page, "Logs");
		DragAndDropBlocksPageUtils.blockDropPosition(page, "Logs");
		DragAndDropBlocksPageUtils.clickOnDroppedBlock(page, "Logs");
		BlockSettingsUtils.clickOnBlockSettingsOption(page);
		BlockSettingsUtils.selectQueryFromList(page, "Test query");
		DragAndDropBlocksPageUtils.clickOnSaveAppButton(page);

		String actualLogsText = DragAndDropBlocksPageUtils.getBlockText(page, "Logs", "Hello word");
		Assertions.assertEquals("Hello word", actualLogsText, "Mismatch between the expected and actual Logs text");
	}
}