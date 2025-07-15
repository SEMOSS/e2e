package aicore.steps.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;

import com.microsoft.playwright.Locator;

import aicore.hooks.SetupHooks;
import aicore.pages.HomePage;
import aicore.pages.app.AppPage;
import aicore.pages.app.BlockSettingsPage;
import aicore.pages.app.CreateAppPopupPage;
import aicore.pages.app.DragAndDropBlocksPage;
import aicore.utils.CommonUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreateAppUsingDragAndDropSteps {
	private HomePage homePage;
	private DragAndDropBlocksPage blocksPage;
	private AppPage appPage;
	private CreateAppPopupPage appCreatePopup;
	private BlockSettingsPage blockSettings;
	public static String timestamp;
	private String blockText;
	private static ThreadLocal<Integer> initialCount = new ThreadLocal<>();

	public CreateAppUsingDragAndDropSteps() {
		this.homePage = new HomePage(SetupHooks.getPage());
		timestamp = CommonUtils.getTimeStampName();
		blocksPage = new DragAndDropBlocksPage(SetupHooks.getPage());
		appPage = new AppPage(SetupHooks.getPage(), timestamp);
		appCreatePopup = new CreateAppPopupPage(SetupHooks.getPage(), timestamp);
		blockSettings = new BlockSettingsPage(SetupHooks.getPage());
	}

	@Given("User clicks on Open App Library")
	public void user_navigates_to_open_app_library() {
		homePage.clickOnOpenAppLibrary();
	}

	@When("User clicks on Create New App button")
	public void user_clicks_on_create_new_app_button() {
		appPage.clickOnCreateNewAppButton();
	}

	@When("User clicks on Get Started button in {string}")
	public void user_clicks_on_get_started_button_in(String appType) {
		appCreatePopup.clickOnGetStartedButton(appType);
	}

	@And("User enters app name as {string}")
	public void user_enters_app_name_as(String appName) {
		appCreatePopup.enterAppName(appName);
	}

	@And("User enters description as {string}")
	public void user_enters_description_as(String appDescription) {
		appCreatePopup.enterAppDescription(appDescription);
	}

	@And("User enters tags {string} and presses Enter")
	public void user_enters_tags_and_presses_enter(String tags) {
		String[] tagsArray = tags.split(", ");
		for (String tag : tagsArray) {
			appCreatePopup.enterTags(tag);
		}
	}

	@And("User clicks on Create button")
	public void user_clicks_on_create_button() {
		appCreatePopup.clickOnCreateButton();
	}

	@Then("User can see {string} with the text {string}")
	public void user_can_see_with_the_text(String pageName, String expectedWelcomeTextMessage) {
		boolean isPage1Visible = blocksPage.verifyPage1IsVisible();
		assertTrue(isPage1Visible);
		boolean isWelcomeTextboxVisible = blocksPage.verifyWelcomeTextboxIsVisible();
		assertTrue(isWelcomeTextboxVisible);
		String actualWelcomeTextMessage = blocksPage.verifyWelcomeText();
		Assertions.assertEquals(expectedWelcomeTextMessage, actualWelcomeTextMessage,
				"Mismatch between the expected and actual message");
	}

	@When("User is on Home page")
	public void user_is_on_home_page() {
		blocksPage.navigatesToHomePage();
	}

	@And("User searches {string} app in the app searchbox")
	public void user_searches_app_in_the_app_searchbox(String appName) {
		appPage.searchApp(appName);
	}

	@And("User clicks on {string} app from the My Apps")
	public void user_clicks_on_app_from_the_my_apps(String appName) {
		appPage.clickOnSearchedApp(appName);
	}

	@And("User clicks on app Edit button")
	public void user_clicks_on_app_edit_button() {
		blocksPage.clickOnEditButton();
	}

	@And("User clicks on Blocks if it is not selected by default")
	public void user_clicks_on_blocks_if_it_is_not_selected_by_default() {
		blocksPage.clickOnBlocksOption();
	}

	@When("User drags the {string} block and drops it on the page")
	public void user_drags_the_block_and_drops_it_on_the_page(String blockName) {
		blocksPage.mouseHoverOnBlock(blockName);
		blocksPage.blockDropPosition();
	}

	@Then("User can see {string} on the page")
	public void user_can_see_on_the_page(String expectedHeadingBlockTextMessage) {
		String actualHeadingBlockTextMessage = blocksPage.verifyHeadingBlockTextMessage();
		assertEquals(expectedHeadingBlockTextMessage, actualHeadingBlockTextMessage);
	}

	@And("User clicks on the Block Settings option")
	public void user_clicks_on_the_block_settings_option() {
		blockSettings.clickOnBlockSettingsOption();
	}

	@And("User selects the Appearance tab")
	public void user_selects_the_Appearance_tab() {
		blockSettings.userSelectsTheAppearanceTab();
	}

	@And("User enters {string} as the destination")
	public void user_enters_as_the_destination(String destination) {
		if (!destination.trim().isEmpty()) {
			blockSettings.enterDestination(destination);
		}
	}

	@And("User enters {string} text as {string}")
	public void user_enters_text_as(String blockName, String blockText) {
		switch (blockName) {
		case "Markdown":
			blockSettings.enterMarkdown(blockText);
			break;
		default:
			blockSettings.enterText(blockText);
			break;
		}
	}

	@And("User selects the {string} styles")
	public void user_selects_the_styles(String textStyles) {
		blockSettings.selectTextStyle(textStyles);
	}

	@And("User selects {string} from the font list")
	public void user_selects_from_the_font_list(String fontName) {
		blockSettings.selectTextFont(fontName);
	}

	@And("User selects {string} as the HEX color value")
	public void user_selects_as_the_rgb_color_values(String hexColor) {
		blockSettings.selectTextColor(hexColor.trim());
	}

	@And("User selects {string} as the text alignment")
	public void user_selects_as_the_text_alignment(String textAlign) {
		blockSettings.selectTextAlign(textAlign);
	}

	@And("User clicks on the Save App icon")
	public void user_clicks_on_the_save_app_icon() {
		blocksPage.clickOnSaveAppButton();
	}

	@Then("User should see the {string} text as {string}")
	public void user_should_see_the_text_as(String blockName, String text) {
		String BOLD_MARKDOWN_PATTERN = "\\*\\*(.*?)\\*\\*";
		String BOLD_REPLACEMENT = "$1";
		switch (blockName) {
		case "Markdown":
			this.blockText = text.replaceAll(BOLD_MARKDOWN_PATTERN, BOLD_REPLACEMENT);
			break;
		default:
			this.blockText = text;
			break;
		}
		String actualText = blocksPage.getBlockText(blockName, blockText);
		Assertions.assertEquals(blockText, actualText, "Mismatch between the expected and actual text");
	}

	@Then("User should see the {string} text displayed in {string} styles")
	public void user_should_see_the_text_displayed_in_styles(String blockName, String textStyles) {
		Locator linkLocator = blocksPage.textSectionDragAndDroppedBlockLocator(blockName, blockText);
		List<String> appliedTextStyles = Arrays.asList(textStyles.split(", "));
		List<String> actualAppliedTextStyles = CommonUtils.getAppliedStyles(linkLocator);
		Assertions.assertEquals(appliedTextStyles, actualAppliedTextStyles,
				"Mismatch between the expected and actual text styles");
	}

	@Then("User should see the {string} text displayed in {string} font")
	public void user_should_see_the_text_displayed_in_font(String blockName, String expectedLinkFont) {
		String actualTextFont = blocksPage.getBlockTextFont(blockName, blockText);
		Assertions.assertEquals(expectedLinkFont, actualTextFont, "Mismatch between the expected and actual text font");
	}

	@Then("User should see the {string} text displayed in {string} HEX color value")
	public void user_should_see_the_text_displayed_in_hex_color_value(String blockName, String hexColor) {
		String actualTextColor = blocksPage.getBlockTextColor(blockName, blockText);
		String expectedTextColor = CommonUtils.hexToRGBConversion(hexColor);
		Assertions.assertEquals("rgb(" + expectedTextColor + ")", actualTextColor,
				"Mismatch between the expected and actual text color");
	}

	@Then("User should see the {string} text aligned to the {string}")
	public void user_should_see_the_text_aligned_to_the(String blockName, String textAlign) {
		String actualTextAlign = blocksPage.getBlockTextAlign(blockName, blockText);
		String expectedTextAlign = textAlign.toLowerCase();
		Assertions.assertEquals(expectedTextAlign, actualTextAlign,
				"Mismatch between the expected and actual text align");
	}

	@Then("User should be navigated to {string} by clicking on link")
	public void user_should_be_navigated_to_by_clicking_on_link(String destination) {
		if (!destination.trim().isEmpty()) {
			blocksPage.clickOnLink(blockText);
			String actualUrl = blocksPage.getDestinationUrl(destination);
			Assertions.assertEquals(destination, actualUrl,
					"Mismatch between the expected and actual link destination");
			blocksPage.navigateToPreviosPage();
		}
	}

	@And("User clicks on Blocks")
	public void user_clicks_on_blocks() {
		blocksPage.clickOnBlocksOption();
	}

	@And("User clicks on {string} page")
	public void user_clicks_on_page(String pageName) {
		blocksPage.selectPage(pageName);
	}

	@And("User selects {string} from the Query dropdown")
	public void user_selects_from_the_query_dropdown(String queryName) {
		blockSettings.selectQueryFromList(queryName);
	}

	@And("User clicks on Data tab")
	public void user_clicks_on_data_tab() {
		blockSettings.clickOnDataTab();
	}

	@And("User selects the frame from the Selected Frame dropdown")
	public void user_selects_the_frame_from_the_selected_frame_dropdown() {
		blockSettings.selectFrame(NotebookCreationAndExecutionSteps.frameID);
	}

	@When("User drag and drop the {string} columns to {string} fields")
	public void user_drag_and_drop_the_columns_to_fields(String columnNames, String fieldNames) {
		String[] columnNameList = columnNames.split(", ");
		String[] fielsNameList = fieldNames.split(", ");
		for (int i = 0; i < columnNameList.length; i++) {
			String columnName = columnNameList[i].trim();
			String fieldName = fielsNameList[i].trim();
			blockSettings.dragColumnToTargetField(columnName, fieldName);
			boolean isColumnDroppedCorrectly = blockSettings.verifyColumnDroppedInCorrectField(columnName, fieldName);
			Assertions.assertTrue(isColumnDroppedCorrectly, columnName + " column dropped in wrong field " + fieldName);
		}
	}

	@Then("User can see {string} chart same as baseline chart")
	public void user_can_see_chart_same_as_baseline_chart(String chartName) throws Exception {
		String removeSpace = chartName.replace(" ", "");
		String folderName = Character.toLowerCase(removeSpace.charAt(0)) + removeSpace.substring(1);
		final String actualImagePath = "src/test/resources/data/screenshots/" + folderName + "/actualChart.png";
		final String expectedImagePath = "src/test/resources/data/screenshots/" + folderName + "/expectedChart.png";
		final String diffImagePath = "src/test/resources/data/screenshots/" + folderName + "/diffChart.png";
		blocksPage.takeChartScreenshot(actualImagePath);
		boolean imagesMatches = CommonUtils.compareImages(actualImagePath, expectedImagePath, diffImagePath);
		Assertions.assertTrue(imagesMatches, "Images do not match for the " + chartName);
	}

	// duplicate and delete Area Chart
	@And("User Click on the area chart on the page to view options")
	public void user_Click_On_AreaChart_To_View_Options() {
		blocksPage.clickOnAreaChartTOViewOptions();
	}

	@When("User can {string} Duplicate icon on area chart")
	public void user_can_see_Duplicate_Icon(String action) {
		boolean canSeeDuplicateIcon = blocksPage.canSeeDuplicateIcon();
		if (action.equalsIgnoreCase("view")) {
			Assertions.assertTrue(canSeeDuplicateIcon, " user cannot view the Duplicate Icon");
		} else if (action.equalsIgnoreCase("not view")) {
			Assertions.assertFalse(canSeeDuplicateIcon, " user should not view the Duplicate Icon");
		} else {
			Assertions.fail("Invalid action: " + action);
		}
	}

	@And("User Click on Duplicate Icon")
	public void user_Click_On_Duplicate_Icon() {
		int count = blocksPage.getInitialCount();
		initialCount.set(count);
		blocksPage.clickOnDuplicateIcon();
	}

	@And("Another Area Chart block should appear on the page")
	public void duplicated_Area_Chart_Is_Visiable() {
		int prevCount = initialCount.get();
		boolean chartIsAdded = blocksPage.duplicatedChartIsVisiable(prevCount);
		Assertions.assertTrue(chartIsAdded, "Expected : New Area Chart is added after Duplicateion");
	}

	@When("User can {string} Delete icon on area chart")
	public void user_can_see_Delete_Icon(String action) {
		boolean canSeeDelete = blocksPage.canSeeDeleteIcon();
		if (action.equalsIgnoreCase("view")) {
			Assertions.assertTrue(canSeeDelete, " user cannot view the Delete Icon");
		} else if (action.equalsIgnoreCase("not view")) {
			Assertions.assertFalse(canSeeDelete, " user should not view the Delete Icon");
		} else {
			Assertions.fail("Invalid action: " + action);
		}
	}

	@And("User Click on Delete Icon")
	public void user_Click_On_Delete_Icon() {
		int count = blocksPage.getInitialCount();
		initialCount.set(count);
		blocksPage.clickOnDeleteIcon();
	}

	@And("Area Chart should be Remove from the page")
	public void area_Chart_Is_Removed() {
		int prevCount = initialCount.get();
		boolean chartIsRemoved = blocksPage.areaChartIsRemoved(prevCount);
		Assertions.assertTrue(chartIsRemoved, "Expected : Area Chart is not removed after Delete");
	}

	@And("User hovers over the Duplicate icon")
	public void user_Hover_On_Duplicate_Icon() {
		blocksPage.hoverOnDuplicateIcon();
	}

	@Then("Tooltip with text {string} should appear")
	public void check_Tooltiop_Message_Of_Duplicate(String expected) {
		boolean actual = blocksPage.checkTooltipMessageOfDuplicate(expected);
		Assertions.assertTrue(actual, "Hover Message is Not Match with expedted: ");
	}

	@And("User hovers over the Delete icon")
	public void user_Hover_On_Delete_Icon() {
		blocksPage.hoverOnDeleteIcon();
	}

	@And("Tooltip with text {string} should display")
	public void check_Tooltiop_Message_Of_Delete(String expected) {
		boolean actual = blocksPage.checkTooltipMessageOfDelete(expected);
		Assertions.assertTrue(actual, "Hover Message is Not Match with expedted: ");
	}

	@And("User Clicks on Duplicate Icon {int} times")
	public void user_Click_DuplicateIcon_Multiple_Times(int count) {
		blocksPage.clickOnDuplicateIconMultipleTimes(count);
	}

	@Then("Total {int} Area Chart blocks should be present on the page")
	public void total_Area_Chart_Present_On_Page(int expectedcount) {
		int actual = blocksPage.countcheck();
		Assertions.assertEquals(expectedcount, actual, "Not matched");
	}

}