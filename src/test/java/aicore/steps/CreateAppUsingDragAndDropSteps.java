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
	private String frameID;

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

	@When("User clicks on Get Started button in {string}")
	public void user_clicks_on_get_started_button_in(String appType) {
		openAppLibraryPage.clickOnGetStartedButtonInDragAndDrop(appType);
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

	// @When("User navigates to the Home page from the App Edit page")
	// public void user_navigates_to_the_home_page_from_the_app_edit_page() {
	// openAppLibraryPage.navigateToHomePageFromAppEditPage();
	// }

	@When("User is on Home page")
	public void user_is_on_home_page() {
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
		openAppLibraryPage.mouseHoverOnBlock(blockName);
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

	@And("User mouse hover below the existing cell")
	public void user_mouse_hover_below_the_existing_cell() {
		openAppLibraryPage.mouseHoverOnNotebookHiddenOptions();
	}

	@And("User selects {string} from the hidden options")
	public void user_selects_from_the_hidden_options(String optionName) {
		openAppLibraryPage.clickOnHiddenNotebookOption(optionName);
	}

	@And("User selects {string} from the data import options")
	public void user_selects_from_the_data_import_options(String optionName) {
		openAppLibraryPage.selectDataImportOption(optionName);
	}

	@And("User selects {string} from the dropdown list")
	public void user_selects_from_the_dropdown_list(String databaseName) {
		openAppLibraryPage.selectDatabaseFromDropdown(databaseName);
	}

	@And("User selects all columns from database")
	public void user_selects_all_columns_from_database() {
		openAppLibraryPage.selectAllColumns();
	}

	@And("User clicks on data Import button")
	public void user_clicks_on_data_import_button() {
		openAppLibraryPage.clickOnImportButton();
	}

	@And("User deletes the previous cell")
	public void user_deletes_the_previous_cell() {
		openAppLibraryPage.deleteFirstCell();
	}

	@When("User clicks on Run cell button")
	public void user_clicks_on_run_cell_button() throws InterruptedException {
		openAppLibraryPage.clickOnRunCellButton();
	}

	@And("User fetch the frame id")
	public void user_fetch_the_frame_id() {
		frameID = openAppLibraryPage.getFrameID();
	}

	@And("User clicks on Data tab")
	public void user_clicks_on_data_tab() {
		openAppLibraryPage.clickOnDataTab();
	}

	@And("User selects the frame from the Selected Frame dropdown")
	public void user_selects_the_frame_from_the_selected_frame_dropdown() {
		openAppLibraryPage.selectFrame(frameID);
	}

	@When("User drag and drop the {string} columns to {string} fields")
	public void user_drag_and_drop_the_columns_to_fields(String columnNames, String fieldNames) {
		String[] columnNameList = columnNames.split(", ");
		String[] fielsNameList = fieldNames.split(", ");
		for (int i = 0; i < columnNameList.length; i++) {
			String columnName = columnNameList[i].trim();
			String fieldName = fielsNameList[i].trim();
			openAppLibraryPage.dragColumnToTargetField(columnName, fieldName);
			boolean isColumnDroppedCorrectly = openAppLibraryPage.verifyColumnDroppedInCorrectField(columnName,
					fieldName);
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
		openAppLibraryPage.takeChartScreenshot(actualImagePath);
		boolean imagesMatches = CommonUtils.compareImages(actualImagePath, expectedImagePath, diffImagePath);
		Assertions.assertTrue(imagesMatches, "Images do not match for the " + chartName);
	}

	@Then("User can see {string} columns under the fields column")
	public void user_can_see_columns_under_the_fields_column(String columnNames) {
		List<String> expectedColumns = Arrays.asList(columnNames.split(", "));
		List<String> uiColumns = openAppLibraryPage.checkColumnNamesOnUI();
		Assertions.assertEquals(expectedColumns, uiColumns, "columns are not matching");
	}

	@Then("User can see header names as {string}")
	public void user_can_see_header_names_as(String headerNames) {
		List<String> expectedHeaderNames = Arrays.asList(headerNames.split(", "));
		List<String> actualHeaderNames = openAppLibraryPage.getNotebookOutputTableHeader();
		Assertions.assertEquals(expectedHeaderNames, actualHeaderNames, "Headers are not matching");
	}

	@Then("User can see total {string} rows")
	public void user_can_see_total_rows(String rowsCount) {
		int actualRowsCount = openAppLibraryPage.getTotalRowsFromPreviewCaption();
		int expectedRowsCount = Integer.parseInt(rowsCount);
		Assertions.assertEquals(expectedRowsCount, actualRowsCount, "Rows count are not correct");
	}

	@Then("User can see the {string} column have unique values")
	public void user_can_see_the_column_have_unique_values(String headerName) {
		boolean isColumnUnique = openAppLibraryPage.isColumnUniqueByHeader(headerName);
		Assertions.assertTrue(isColumnUnique, headerName + " have duplicate values");
	}

	@Then("User can see name as frame id in JSON")
	public void user_can_see_name_as_frame_id_in_json() {
		String jsonFrameId = openAppLibraryPage.validateJsonFieldValue(frameID);
		String cleanedActualFrameId = jsonFrameId.replaceAll("^\"|\"$", "");
		Assertions.assertEquals(frameID, cleanedActualFrameId, "Frame Id not matching");
	}

	@Then("User can see type as {string} for {string} in JSON")
	public void user_can_see_type_as_for_in_json(String typeFieldValue, String type) {
		openAppLibraryPage.selectTypeFromDropdown(type);
		openAppLibraryPage.validateJsonFieldValue(typeFieldValue);
	}

	@And("User Sees Python as the default language")
	public void user_sees_python_as_the_default_language() {
		openAppLibraryPage.checkPythonAsDefault();
	}

	@And("User changes the language to {string}")
	public void user_changes_the_language_to(String language) {
		openAppLibraryPage.changeToLanguage(language);
	}

	@Then("User hovers and clicks on the cell")
	public void user_hovers_and_clicks_on_the_cell() {
		openAppLibraryPage.mouseHoverOnNotebookHiddenOptions();
		openAppLibraryPage.hoverAndClickOnCell();
	}

	@Then("User can see Pixel output as {string}")
	public void user_can_see_pixel_output_as(String Output) {
		openAppLibraryPage.getPixelOutput(Output);

	}

	@Then("User can see Python output as {string}")
	public void user_can_see_python_output_as(String Output) {
		openAppLibraryPage.getPythonOutput(Output);
	}
}