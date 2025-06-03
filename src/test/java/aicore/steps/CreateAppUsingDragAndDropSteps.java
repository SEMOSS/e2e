package aicore.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;

import com.microsoft.playwright.Locator;

import aicore.hooks.SetupHooks;
import aicore.pages.HomePage;
import aicore.pages.OpenAppLibraryPage;
import aicore.utils.CommonUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreateAppUsingDragAndDropSteps {
	private HomePage homePage;
	private OpenAppLibraryPage openAppLibraryPage;
	protected static String timestamp;
	private String blockText;

	public CreateAppUsingDragAndDropSteps() {
		this.homePage = new HomePage(SetupHooks.getPage());
		timestamp = CommonUtils.getTimeStampName();
		openAppLibraryPage = new OpenAppLibraryPage(SetupHooks.getPage(), timestamp);
	}

	@Given("User clicks on Open App Library")
	public void user_navigates_to_open_app_library() {
		homePage.clickOnOpenAppLibrary();
	}

	@When("User clicks on Create New App button")
	public void user_clicks_on_create_new_app_button() {
		openAppLibraryPage.clickOnCreateNewAppButton();
	}

	@And("User clicks on Get Started button in Drag and Drop")
	public void user_clicks_on_get_started_button_in_drag_and_drop() {
		openAppLibraryPage.clickOnGetStartedButtonInDragAndDrop();
	}

	@And("User enters app name as {string}")
	public void user_enters_app_name_as(String appName) {
		openAppLibraryPage.enterAppName(appName);
	}

	@And("User enters description as {string}")
	public void user_enters_description_as(String appDescription) {
		openAppLibraryPage.enterAppDescription(appDescription);
	}

	@And("User enters tags {string} and presses Enter")
	public void user_enters_tags_and_presses_enter(String tags) {
		String[] tagsArray = tags.split(", ");
		for (String tag : tagsArray) {
			openAppLibraryPage.enterTags(tag);
		}
	}

	@And("User clicks on Create button")
	public void user_clicks_on_create_button() {
		openAppLibraryPage.clickOnCreateButton();
	}

	@Then("User can see {string} with the text {string}")
	public void user_can_see_with_the_text(String pageName, String expectedWelcomeTextMessage) {
		boolean isPage1Visible = openAppLibraryPage.verifyPage1IsVisible();
		assertTrue(isPage1Visible);
		boolean isWelcomeTextboxVisible = openAppLibraryPage.verifyWelcomeTextboxIsVisible();
		assertTrue(isWelcomeTextboxVisible);
		String actualWelcomeTextMessage = openAppLibraryPage.verifyWelcomeText();
		Assertions.assertEquals(expectedWelcomeTextMessage, actualWelcomeTextMessage,
				"Mismatch between the expected and actual message");
	}

//	@When("User navigates to the Home page from the App Edit page")
//	public void user_navigates_to_the_home_page_from_the_app_edit_page() {
//		openAppLibraryPage.navigateToHomePageFromAppEditPage();
//	}

	@When("User navigates to Home page")
	public void user_navigate_to_home_page() {
		openAppLibraryPage.navigatesToHomePage();
	}

	@And("User searches {string} app in the app searchbox")
	public void user_searches_app_in_the_app_searchbox(String appName) {
		openAppLibraryPage.searchApp(appName);
	}

	@And("User clicks on {string} app from the My Apps")
	public void user_clicks_on_app_from_the_my_apps(String appName) {
		openAppLibraryPage.clickOnSearchedApp(appName);
	}

	@And("User clicks on app Edit button")
	public void user_clicks_on_app_edit_button() {
		openAppLibraryPage.clickOnEditButton();
	}

	@And("User clicks on Blocks if it is not selected by default")
	public void user_clicks_on_blocks_if_it_is_not_selected_by_default() {
		openAppLibraryPage.clickOnBlocksOption();
	}

	@When("User drags the {string} block and drops it on the page")
	public void user_drags_the_block_and_drops_it_on_the_page(String blockName) {
		openAppLibraryPage.mouseHoverOnTextSectionBlock(blockName);
		openAppLibraryPage.blockDropPosition();
	}

	@Then("User can see {string} on the page")
	public void user_can_see_on_the_page(String expectedHeadingBlockTextMessage) {
		String actualHeadingBlockTextMessage = openAppLibraryPage.verifyHeadingBlockTextMessage();
		assertEquals(expectedHeadingBlockTextMessage, actualHeadingBlockTextMessage);
	}

	@And("User clicks on the Block Settings option")
	public void user_clicks_on_the_block_settings_option() {
		openAppLibraryPage.clickOnBlockSettingsOption();
	}
	
	@And("User selects the Appearance tab")
	public void user_selects_the_Appearance_tab() {
		openAppLibraryPage.userSelectsTheAppearanceTab();
	}

	@And("User enters {string} as the destination")
	public void user_enters_as_the_destination(String destination) {
		if (!destination.trim().isEmpty()) {
			openAppLibraryPage.enterDestination(destination);
		}
	}

	@And("User enters {string} text as {string}")
	public void user_enters_text_as(String blockName, String blockText) {
		switch (blockName) {
		case "Markdown":
			openAppLibraryPage.enterMarkdown(blockText);
			break;
		default:
			openAppLibraryPage.enterText(blockText);
			break;
		}
	}

	@And("User selects the {string} styles")
	public void user_selects_the_styles(String textStyles) {
		openAppLibraryPage.selectTextStyle(textStyles);
	}

	@And("User selects {string} from the font list")
	public void user_selects_from_the_font_list(String fontName) {
		openAppLibraryPage.selectTextFont(fontName);
	}

	@And("User selects {string} as the HEX color value")
	public void user_selects_as_the_rgb_color_values(String hexColor) {
		openAppLibraryPage.selectTextColor(hexColor.trim());
	}

	@And("User selects {string} as the text alignment")
	public void user_selects_as_the_text_alignment(String textAlign) {
		openAppLibraryPage.selectTextAlign(textAlign);
	}

	@And("User clicks on the Save App icon")
	public void user_clicks_on_the_save_app_icon() {
		openAppLibraryPage.clickOnSaveAppButton();
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
		String actualText = openAppLibraryPage.getBlockText(blockName, blockText);
		Assertions.assertEquals(blockText, actualText, "Mismatch between the expected and actual text");
	}

	@Then("User should see the {string} text displayed in {string} styles")
	public void user_should_see_the_text_displayed_in_styles(String blockName, String textStyles) {
		Locator linkLocator = openAppLibraryPage.textSectionDragAndDroppedBlockLocator(blockName, blockText);
		List<String> appliedTextStyles = Arrays.asList(textStyles.split(", "));
		List<String> actualAppliedTextStyles = CommonUtils.getAppliedStyles(linkLocator);
		Assertions.assertEquals(appliedTextStyles, actualAppliedTextStyles,
				"Mismatch between the expected and actual text styles");
	}

	@Then("User should see the {string} text displayed in {string} font")
	public void user_should_see_the_text_displayed_in_font(String blockName, String expectedLinkFont) {
		String actualTextFont = openAppLibraryPage.getBlockTextFont(blockName, blockText);
		Assertions.assertEquals(expectedLinkFont, actualTextFont, "Mismatch between the expected and actual text font");
	}

	@Then("User should see the {string} text displayed in {string} HEX color value")
	public void user_should_see_the_text_displayed_in_hex_color_value(String blockName, String hexColor) {
		String actualTextColor = openAppLibraryPage.getBlockTextColor(blockName, blockText);
		String expectedTextColor = CommonUtils.hexToRGBConversion(hexColor);
		Assertions.assertEquals("rgb(" + expectedTextColor + ")", actualTextColor,
				"Mismatch between the expected and actual text color");
	}

	@Then("User should see the {string} text aligned to the {string}")
	public void user_should_see_the_text_aligned_to_the(String blockName, String textAlign) {
		String actualTextAlign = openAppLibraryPage.getBlockTextAlign(blockName, blockText);
		String expectedTextAlign = textAlign.toLowerCase();
		Assertions.assertEquals(expectedTextAlign, actualTextAlign,
				"Mismatch between the expected and actual text align");
	}

	@Then("User should be navigated to {string} by clicking on link")
	public void user_should_be_navigated_to_by_clicking_on_link(String destination) {
		if (!destination.trim().isEmpty()) {
			openAppLibraryPage.clickOnLink(blockText);
			String actualUrl = openAppLibraryPage.getDestinationUrl(destination);
			Assertions.assertEquals(destination, actualUrl,
					"Mismatch between the expected and actual link destination");
			openAppLibraryPage.navigateToPreviosPage();
		}
	}

	@When("User clicks on Notebook")
	public void user_clicks_on_notebook() {
		openAppLibraryPage.clickOnNotebooksOption();
	}

	@And("User clicks on Create new notebook")
	public void user_clicks_on_create_new_notebook() {
		openAppLibraryPage.clickOnCreateNewNotebook();
	}

	@And("User enters New Query name as {string}")
	public void user_enters_new_query_name_as(String queryName) {
		openAppLibraryPage.enterQueryName(queryName);
	}

	@When("User clicks on query Submit button")
	public void user_clicks_on_query_submit_button() {
		openAppLibraryPage.clickOnQuerySubmitButton();
	}

	@When("User enters code as {string}")
	public void user_enters_code_as(String code) {
		openAppLibraryPage.enterCodeInQuery(code);
	}

	@When("User clicks on Run this cell and below icon")
	public void user_clicks_on_run_this_cell_and_below_icon() {
		openAppLibraryPage.clickOnRunAllButton();
	}

	@Then("User can see code output as {string}")
	public void user_can_see_code_output_as(String expectedCodeOutput) {
		String actualOutput = openAppLibraryPage.getCodeOutput(expectedCodeOutput);
		Assertions.assertEquals(expectedCodeOutput, actualOutput,
				"Mismatch between the expected and actual code output");
	}

	@And("User clicks on Blocks")
	public void user_clicks_on_blocks() {
		openAppLibraryPage.clickOnBlocksOption();
	}

	@And("User clicks on {string} page")
	public void user_clicks_on_page(String pageName) {
		openAppLibraryPage.selectPage(pageName);
	}

	@And("User selects {string} from the Query dropdown")
	public void user_selects_from_the_query_dropdown(String queryName) {
		openAppLibraryPage.selectQueryFromList(queryName);
	}
}