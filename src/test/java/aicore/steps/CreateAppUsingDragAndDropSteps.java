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
	private String linkText;

	public CreateAppUsingDragAndDropSteps() {
		this.homePage = new HomePage(SetupHooks.getPage());
		timestamp = CommonUtils.getTimeStampName();
		openAppLibraryPage = new OpenAppLibraryPage(SetupHooks.getPage(), timestamp);
	}

	@Given("User navigates to Open App Library")
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
		assertEquals(expectedWelcomeTextMessage, actualWelcomeTextMessage);
	}

	@When("User navigate back to the Home page")
	public void user_navigate_back_to_the_home_page() {
		openAppLibraryPage.navigateToPreviousPage();
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
		switch (blockName) {
		case "Heading 1":
			openAppLibraryPage.mouseHoverOnHeading1Block();
			break;
		case "Link":
			openAppLibraryPage.mouseHoverOnLinkBlock();
			break;
		default:
			System.out.println("Invalid block name");
		}
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

	@And("User enters {string} as the destination")
	public void user_enters_as_the_destination(String destination) {
		openAppLibraryPage.enterDestination(destination);
	}

	@And("User enters {string} as the text")
	public void user_enters_as_the_text(String linkText) {
		openAppLibraryPage.enterLinkText(linkText);
	}

	@And("User selects the {string} styles")
	public void user_selects_the_styles(String textStyles) {
		openAppLibraryPage.selectTextStyle(textStyles);
	}

	@And("User selects {string} from the font list")
	public void user_selects_from_the_font_list(String fontName) {
		openAppLibraryPage.selectLinkTextFont(fontName);
	}

	@And("User selects {string} as the HEX color value")
	public void user_selects_as_the_rgb_color_values(String hexColor) {
		openAppLibraryPage.selectLinkTextColor(hexColor);
	}

	@And("User selects {string} as the text alignment")
	public void user_selects_as_the_text_alignment(String textAlign) {
		openAppLibraryPage.selectTextAlign(textAlign);
	}

	@And("User clicks on the Save App icon")
	public void user_clicks_on_the_save_app_icon() {
		openAppLibraryPage.clickOnSaveAppButton();
	}

	@Then("User should see {string} as the link name")
	public void user_should_see_as_the_link_name(String text) {
		this.linkText = text;
		String actualLinkText = openAppLibraryPage.getLinkText(linkText);
		Assertions.assertEquals(linkText, actualLinkText, "Mismatch between the expected and actual link text");
	}

	@And("User should see the link name displayed in {string} styles")
	public void user_should_see_the_link_name_displayed_in_styles(String linkStyles) {
		Locator linkLocator = openAppLibraryPage.linkLocator(linkText);
		List<String> appliedLinkStyles = Arrays.asList(linkStyles.split(", "));
		List<String> actualAppliedLinkStyles = CommonUtils.getAppliedStyles(linkLocator);
		Assertions.assertEquals(appliedLinkStyles, actualAppliedLinkStyles,
				"Mismatch between the expected and actual link styles");
	}

	@And("User should see the link name displayed in {string} font")
	public void user_should_see_the_link_name_displayed_in_font(String expectedLinkFont) {
		String actualLinkFont = openAppLibraryPage.getLinkFont(linkText);
		Assertions.assertEquals(expectedLinkFont, actualLinkFont, "Mismatch between the expected and actual link font");
	}

	@And("User should see the link name displayed in {string} HEX color value")
	public void user_should_see_the_link_name_displayed_in_hex_color_value(String hexLinkColor) {
		String actualLinkColor = openAppLibraryPage.getLinkColor(linkText);
		String expectedLinkColor = CommonUtils.hexToRGBConversion(hexLinkColor);
		Assertions.assertEquals("rgb(" + expectedLinkColor + ")", actualLinkColor,
				"Mismatch between the expected and actual link color");
	}

	@And("User should see the link name aligned to the {string}")
	public void user_should_see_the_link_name_aligned_to_the(String linkAlign) {
		String actualLinkAlign = openAppLibraryPage.getLinkTextAlign(linkText);
		String expectedLinkAlign = linkAlign.toLowerCase();
		Assertions.assertEquals(expectedLinkAlign, actualLinkAlign,
				"Mismatch between the expected and actual link align");
	}

	@When("User clicks on the link")
	public void user_clicks_on_the_link() {
		openAppLibraryPage.clickOnLink(linkText);
	}

	@Then("User should be navigated to {string}")
	public void user_should_be_navigated_to(String expectedUrl) {
		String actualUrl = openAppLibraryPage.getDestinationUrl(expectedUrl);
		Assertions.assertEquals(expectedUrl, actualUrl, "Mismatch between the expected and actual link destination");
	}
}
