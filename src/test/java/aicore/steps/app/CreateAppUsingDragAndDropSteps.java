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
import io.cucumber.datatable.DataTable;
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

	// @When("User clicks on Notebook")
	// public void user_clicks_on_notebook() {
	// openAppLibraryPage.clickOnNotebooksOption();
	// }
	//
	// @And("User clicks on Create new notebook")
	// public void user_clicks_on_create_new_notebook() {
	// openAppLibraryPage.clickOnCreateNewNotebook();
	// }
	//
	// @And("User enters New Query name as {string}")
	// public void user_enters_new_query_name_as(String queryName) {
	// openAppLibraryPage.enterQueryName(queryName);
	// }
	//
	// @When("User clicks on query Submit button")
	// public void user_clicks_on_query_submit_button() {
	// openAppLibraryPage.clickOnQuerySubmitButton();
	// }
	//
	// @When("User enters code as {string}")
	// public void user_enters_code_as(String code) {
	// openAppLibraryPage.enterCodeInQuery(code);
	// }
	//
	// @When("User clicks on Run this cell and below icon")
	// public void user_clicks_on_run_this_cell_and_below_icon() {
	// openAppLibraryPage.clickOnRunAllButton();
	// }
	//
	// @Then("User can see code output as {string}")
	// public void user_can_see_code_output_as(String expectedCodeOutput) {
	// String actualOutput = openAppLibraryPage.getCodeOutput(expectedCodeOutput);
	// Assertions.assertEquals(expectedCodeOutput, actualOutput,
	// "Mismatch between the expected and actual code output");
	// }

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

	// @And("User mouse hover below the existing cell")
	// public void user_mouse_hover_below_the_existing_cell() {
	// openAppLibraryPage.mouseHoverOnNotebookHiddenOptions();
	// }
	//
	// @And("User selects {string} from the hidden options")
	// public void user_selects_from_the_hidden_options(String optionName) {
	// openAppLibraryPage.clickOnHiddenNotebookOption(optionName);
	// }
	//
	// @And("User selects {string} from the data import options")
	// public void user_selects_from_the_data_import_options(String optionName) {
	// openAppLibraryPage.selectDataImportOption(optionName);
	// }
	//
	// @And("User selects {string} from the dropdown list")
	// public void user_selects_from_the_dropdown_list(String databaseName) {
	// openAppLibraryPage.selectDatabaseFromDropdown(databaseName);
	// }
	//
	// @And("User selects all columns from database")
	// public void user_selects_all_columns_from_database() {
	// openAppLibraryPage.selectAllColumns();
	// }
	//
	// @And("User clicks on data Import button")
	// public void user_clicks_on_data_import_button() {
	// openAppLibraryPage.clickOnImportButton();
	// }
	//
	// @And("User deletes the previous cell")
	// public void user_deletes_the_previous_cell() {
	// openAppLibraryPage.deleteFirstCell();
	// }
	//
	// @When("User clicks on Run cell button")
	// public void user_clicks_on_run_cell_button() throws InterruptedException {
	// openAppLibraryPage.clickOnRunCellButton();
	// }
	//
	// @And("User fetch the frame id")
	// public void user_fetch_the_frame_id() {
	// frameID = openAppLibraryPage.getFrameID();
	// }

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

	// @Then("User can see {string} columns under the fields column")
	// public void user_can_see_columns_under_the_fields_column(String columnNames)
	// {
	// List<String> expectedColumns = Arrays.asList(columnNames.split(", "));
	// List<String> uiColumns = openAppLibraryPage.checkColumnNamesOnUI();
	// Assertions.assertEquals(expectedColumns, uiColumns, "columns are not
	// matching");
	// }
	//
	// @Then("User can see header names as {string}")
	// public void user_can_see_header_names_as(String headerNames) {
	// List<String> expectedHeaderNames = Arrays.asList(headerNames.split(", "));
	// List<String> actualHeaderNames =
	// openAppLibraryPage.getNotebookOutputTableHeader();
	// Assertions.assertEquals(expectedHeaderNames, actualHeaderNames, "Headers are
	// not matching");
	// }
	//
	// @Then("User can see total {string} rows")
	// public void user_can_see_total_rows(String rowsCount) {
	// int actualRowsCount = openAppLibraryPage.getTotalRowsFromPreviewCaption();
	// int expectedRowsCount = Integer.parseInt(rowsCount);
	// Assertions.assertEquals(expectedRowsCount, actualRowsCount, "Rows count are
	// not correct");
	// }
	//
	// @Then("User can see the {string} column have unique values")
	// public void user_can_see_the_column_have_unique_values(String headerName) {
	// boolean isColumnUnique =
	// openAppLibraryPage.isColumnUniqueByHeader(headerName);
	// Assertions.assertTrue(isColumnUnique, headerName + " have duplicate values");
	// }
	//
	// @Then("User can see name as frame id in JSON")
	// public void user_can_see_name_as_frame_id_in_json() {
	// String jsonFrameId = openAppLibraryPage.validateJsonFieldValue(frameID);
	// String cleanedActualFrameId = jsonFrameId.replaceAll("^\"|\"$", "");
	// Assertions.assertEquals(frameID, cleanedActualFrameId, "Frame Id not
	// matching");
	// }
	//
	// @When("User selects type as {string}")
	// public void user_selects_type_as(String type) {
	// openAppLibraryPage.selectTypeFromDropdown(type);
	// }
	//
	// @Then("User can see type as {string} for {string} in JSON")
	// public void user_can_see_type_as_for_in_json(String typeFieldValue, String
	// type) {
	// openAppLibraryPage.validateJsonFieldValue(typeFieldValue);
	// }
	//
	// @And("User Sees Python as the default language")
	// public void user_sees_python_as_the_default_language() {
	// openAppLibraryPage.checkPythonAsDefault();
	// }
	//
	// @And("User changes the language to {string}")
	// public void user_changes_the_language_to(String language) {
	// openAppLibraryPage.changeToLanguage(language);
	// }
	//
	// @Then("User hovers and clicks on the cell")
	// public void user_hovers_and_clicks_on_the_cell() {
	// openAppLibraryPage.mouseHoverOnNotebookHiddenOptions();
	// openAppLibraryPage.hoverAndClickOnCell();
	// }
	//
	// @Then("User can see Pixel output as {string}")
	// public void user_can_see_pixel_output_as(String Output) {
	// openAppLibraryPage.getPixelOutput(Output);
	//
	// }
	//
	// @Then("User can see Python output as {string}")
	// public void user_can_see_python_output_as(String Output) {
	// openAppLibraryPage.getPythonOutput(Output);
	// }
	@Then("User clicks on the Sync icon")
	public void user_clicks_on_the_sync_icon() {
		blocksPage.clickOnSyncChangesButton();
	}

	@Then("User remove the {string} column from the Data Grid")
	public void user_remove_the_column_from_the_data_grid(String columnName) {
		blocksPage.removeColumnFromDataGrid(columnName);
	}

	@Then("User can see the Data Grid column names as {string}")
	public void user_can_see_the_data_grid_column_names_as(String columnNames) {
		List<String> expectedColumns = Arrays.asList(columnNames.split(", "));
		List<String> uiColumns = blocksPage.checkDataGridColumnNamesOnUI();
		Assertions.assertEquals(expectedColumns, uiColumns, "Data Grid columns are not matching");
	}

	@Then("User should not see the {string} column in the Data Grid")
	public void user_should_not_see_the_column_in_the_data_grid(String columnName) {
		List<String> uiColumns = blocksPage.checkDataGridColumnNamesOnUI();
		Assertions.assertFalse(uiColumns.contains(columnName),
				"Data Grid still contains the removed column " + columnName);
	}

	@Then("User validates pagination for the following rows per page options")
	public void user_validates_pagination_for_the_following_rows_per_page_options(DataTable dataTable) {
		List<String> rowsPerPageOptions = dataTable.asList(String.class);
		blocksPage.validatePaginationForRowsPerPageOptions(rowsPerPageOptions);
	}
}