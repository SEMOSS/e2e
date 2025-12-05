package aicore.steps.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;

import com.microsoft.playwright.Locator;

import aicore.hooks.SetupHooks;
import aicore.pages.HomePage;
import aicore.pages.app.AppPage;
import aicore.pages.app.AppVariablePage;
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
	private int initialChartCount;
	private String copiedId;
	private AppVariablePage appVariablePage;
	private String copiedCatalogName;

	public CreateAppUsingDragAndDropSteps() {
		this.homePage = new HomePage(SetupHooks.getPage());
		timestamp = SetupHooks.getTimestamp();
		appVariablePage = new AppVariablePage(SetupHooks.getPage());
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

	@When("User clicks on Upload button")
	public void user_clicks_on_upload_button() {
		appCreatePopup.clickOnUploadButton();
	}

	@Then("User clicks on Share App button")
	public void user_clicks_on_share_app_button() {
		appCreatePopup.clickOnShareAppButton();
	}

	@And("User clicks on IFrame button")
	public void user_clicks_on_iframe_button() {
		appCreatePopup.clickOnIframeButton();
	}

	@When("User clicks on Access Control button")
	public void user_clicks_on_access_control_button() {
		appPage.clickOnAccessControlButton();
	}

	@Then("User Clicks on close button")
	public void user_clicks_on_close_button() {
		appCreatePopup.clickOnCloseButton();
	}

	@When("User clicks on Get Started button in {string}")
	public void user_clicks_on_get_started_button_in(String appType) {
		appCreatePopup.clickOnGetStartedButton(appType);
	}

	@And("User enters app name as {string}")
	public void user_enters_app_name_as(String appName) {
		appCreatePopup.enterAppName(appName);
	}

	@Then("User can selects {string} on the page")
	public void user_can_selects_on_the_page(String appName) {
		appCreatePopup.selectApp(appName);
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

	@And("User fetch the app name for drag and drop app")
	public void user_fetch_app_name() {
		String fetchName = appCreatePopup.userFetchAppName();
		Assertions.assertFalse(fetchName.isEmpty(), "Fetched App Name is Empty");
	}

	@Then("User can see {string} with the text {string}")
	public void user_can_see_with_the_text(String pageName, String expectedWelcomeTextMessage) {
		boolean isPage1Visible = blocksPage.verifyPage1IsVisible();
		Assertions.assertTrue(isPage1Visible, "Page is not visible");
		boolean isWelcomeTextboxVisible = blocksPage.verifyWelcomeTextboxIsVisible();
		Assertions.assertTrue(isWelcomeTextboxVisible, "Welcome text box not visible");
		String actualWelcomeTextMessage = blocksPage.verifyWelcomeText();
		assertEquals(expectedWelcomeTextMessage, actualWelcomeTextMessage,
				"Mismatch between the expected and actual message");
	}

	@When("User is on Home page")
	public void user_is_on_home_page() {
		blocksPage.navigatesToHomePage();
	}

	@Given("User clicks on Build button")
	public void user_navigates_to_build_button() {
		homePage.clickOnBuildButton();
	}

	@And("User able to see the {string} button")
	public void user_able_to_see_the_button(String buttonName) {
		homePage.verifyBuildPageButtons(buttonName);
	}

	@Then("User able to see the following Buttons:")
	public void user_able_to_see_the_buttons(io.cucumber.datatable.DataTable dataTable) {
		List<String> buttons = dataTable.asList(String.class);
		for (String buttonName : buttons) {
			homePage.verifyBuildPageButton(buttonName);
		}
	}

	@Then("User able to see the following Titles:")
	public void user_able_to_see_the_titles(io.cucumber.datatable.DataTable dataTable) {
		List<String> titles = dataTable.asList(String.class);
		for (String titleName : titles) {
			homePage.verifyTitleIsVisible(titleName);
		}
	}

	@And("User searches {string} app in the app searchbox")
	public void user_searches_app_in_the_app_searchbox(String appName) {
		appPage.searchApp(appName);
	}

	@And("User clicks on {string} app from the My Apps")
	public void user_clicks_on_app_from_the_my_apps(String appName) {
		appPage.clickOnAppCard(appName);
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

	@And("User clicks on the {string} block to select it")
	public void user_clicks_on_block_to_select_it(String blockName) {
		blocksPage.clickOnDroppedBlock(blockName);
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

	@And("User enters {string} in graph TD section")
	public void user_enters_in_graph_td_section(String graphTdContent) {
		blockSettings.enterValueInGraphTD(graphTdContent);
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
		assertEquals(blockText, actualText, "Mismatch between the expected and actual text");
	}

	@Then("User should see the {string} text displayed in {string} styles")
	public void user_should_see_the_text_displayed_in_styles(String blockName, String textStyles) {
		Locator linkLocator = blocksPage.textSectionDragAndDroppedBlockLocator(blockName, blockText);
		List<String> appliedTextStyles = Arrays.asList(textStyles.split(", "));
		List<String> actualAppliedTextStyles = CommonUtils.getAppliedStyles(linkLocator);
		assertEquals(appliedTextStyles, actualAppliedTextStyles,
				"Mismatch between the expected and actual text styles");
	}

	@Then("User should see the {string} text displayed in {string} font")
	public void user_should_see_the_text_displayed_in_font(String blockName, String expectedLinkFont) {
		String actualTextFont = blocksPage.getBlockTextFont(blockName, blockText);
		assertEquals(expectedLinkFont, actualTextFont, "Mismatch between the expected and actual text font");
	}

	@Then("User should see the {string} text displayed in {string} HEX color value")
	public void user_should_see_the_text_displayed_in_hex_color_value(String blockName, String hexColor) {
		String actualTextColor = blocksPage.getBlockTextColor(blockName, blockText);
		String expectedTextColor = CommonUtils.hexToRGBConversion(hexColor);
		assertEquals("rgb(" + expectedTextColor + ")", actualTextColor,
				"Mismatch between the expected and actual text color");
	}

	@Then("User should see the {string} text aligned to the {string}")
	public void user_should_see_the_text_aligned_to_the(String blockName, String textAlign) {
		String actualTextAlign = blocksPage.getBlockTextAlign(blockName, blockText);
		String expectedTextAlign = textAlign.toLowerCase();
		assertEquals(expectedTextAlign, actualTextAlign, "Mismatch between the expected and actual text align");
	}

	@Then("User should be navigated to {string} by clicking on link")
	public void user_should_be_navigated_to_by_clicking_on_link(String destination) {
		if (!destination.trim().isEmpty()) {
			blocksPage.clickOnLink(blockText);
			String actualUrl = blocksPage.getDestinationUrl(destination);
			assertEquals(destination, actualUrl, "Mismatch between the expected and actual link destination");
			blocksPage.navigateToPreviosPage();
		}
	}

	@And("User clicks on Blocks")
	public void user_clicks_on_blocks() {
		blocksPage.clickOnBlocksOption();
	}

	@And("User searches {string} block in the block searchbox")
	public void user_searches_block_in_the_block_searchbox(String blockName) {
		blocksPage.searchBlock(blockName);
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

	@And("User selects the frame from the selected frame dropdown")
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
		final String actualImagePath = "screenshots/" + folderName + "/actualChart.png";
		final String expectedImagePath = "screenshots/" + folderName + "/expectedChart.png";
		final String diffImagePath = "screenshots/" + folderName + "/diffChart.png";
		blocksPage.closeBlocksOption();
		blocksPage.takeChartScreenshot(actualImagePath, chartName);
		boolean imagesMatches = CommonUtils.compareImages(actualImagePath, expectedImagePath, diffImagePath);
		Assertions.assertTrue(imagesMatches, "Images do not match for the " + chartName);
	}

	// duplicate and delete Area Chart
	@And("User click on the area chart on the page to view options")
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

	@And("User click on {string} chart duplicate icon")
	public void user_click_on_chart_Duplicate_Icon(String blockName) {
		initialChartCount = blocksPage.getInitialCount(blockName);
		blocksPage.clickOnDuplicateIcon();
	}

	@Then("Duplicate {string} chart should appear on the page")
	public void duplicate_chart_block_should_appear_on_the_page(String blockName) {
		boolean chartIsAdded = blocksPage.duplicatedChartIsVisiable(initialChartCount, blockName);
		Assertions.assertTrue(chartIsAdded, "Duplicate chart is not added");
	}

	@When("User can {string} delete icon on area chart")
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

	@And("User click on {string} chart delete icon")
	public void user_click_on_chart_Delete_Icon(String blockName) {
		initialChartCount = blocksPage.getInitialCount(blockName);
		blocksPage.clickOnDeleteIcon();
	}

	@And("Duplicate {string} chart should be remove from the page")
	public void duplicate_chart_should_be_remove_from_the_page(String chartName) {
		boolean chartIsRemoved = blocksPage.chartIsRemoved(initialChartCount, chartName);
		Assertions.assertTrue(chartIsRemoved, "Expected :Chart is not removed after Delete");
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

	@And("User clicks on Duplicate Icon {int} times")
	public void user_Click_DuplicateIcon_Multiple_Times(int count) {
		blocksPage.clickOnDuplicateIconMultipleTimes(count);
	}

	@Then("Total {int} Area Chart blocks should be present on the page")
	public void total_Area_Chart_Present_On_Page(int expectedcount) {
		int actual = blocksPage.waitForChartCount(expectedcount);
		assertEquals(expectedcount, actual, "Not matched");
	}

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
		assertEquals(expectedColumns, uiColumns, "Data Grid columns are not matching");
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

	@When("User clicks on more vertical icon of {string} app")
	public void user_clicks_on_more_vertical_icon_of_app(String appName) {
		appPage.clickOnMoreVertIcon(appName + " " + timestamp);
	}

	@When("User clicks on {string} option")
	public void user_clicks_on_option(String optionName) {
		copiedId = appPage.clickOnOption(optionName);
	}

	@Then("User can see {string} toast message after copying the ID.")
	public void user_can_see_toast_message_after_copying_the_id(String expectedToastMessage) {
		String actualToastMessage = appPage.getAppIdCopiedToastMessage();
		assertEquals(expectedToastMessage, actualToastMessage, "Toast message text is incorrect");
	}

	@When("User searches copied id in the app searchbox")
	public void user_searches_copied_id_in_the_app_searchbox() {
		appPage.searchAppId(copiedId);
	}

	@When("User enters cloned app name as {string}")
	public void user_enters_cloned_app_name_as(String appName) {
		appPage.enterCloneAppName(appName);
	}

	@When("User enters cloned app description as {string}")
	public void user_enters_cloned_app_description_as(String appDescription) {
		appPage.enterCloneAppDescription(appDescription);
	}

	@When("User clicks on {string} button of clone app popup")
	public void user_clicks_on_button_of_clone_app_popup(String buttonName) {
		appPage.clickOnButton(buttonName);
	}

	@When("User click on Make Public toggle switch")
	public void user_click_on_make_public_toggle_switch() {
		appPage.MakeAppPublic();
	}

	@Then("User can see {string} app on the page")
	public void user_can_see_app_on_the_page(String appName) {
		boolean isAppDisplayed = appPage.isAppDisplayedOnPage(appName);
		Assertions.assertTrue(isAppDisplayed, "Application is not displayed on page");
	}

	@And("User can see the following details on the app card")
	public void user_can_see_the_following_details_on_the_app_card(DataTable dataTable) {
		List<Map<String, String>> details = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> detail : details) {
			String name = detail.get("DETAIL_NAME");
			String value = detail.get("VALUE");
			boolean isContentVisible = appPage.isContentVisibleOnAppCard(name, value);
			Assertions.assertTrue(isContentVisible, name + " is not displayed on app card");
		}
	}

	@When("User click on {string} confirmation button")
	public void user_click_on_confirmation_button(String buttonName) {
		appPage.clickOnDeleteButton(buttonName);
	}

	@Then("User can not see {string} app on the page")
	public void user_can_not_see_app_on_the_page(String appName) {
		boolean isAppNotDisplayed = appPage.isAppNotDisplayedOnPage(appName);
		Assertions.assertTrue(isAppNotDisplayed, "Application is displayed on page");
	}

	@When("User applies each filter and validate {string} app is visible on the page")
	public void user_applies_each_filter_and_validate_app_is_visible_on_the_page(String appName, DataTable dataTable) {
		final String FILTER_CATEGORY_NAME = "FILTER_CATEGORY";
		final String FILTER_VALUE_NAME = "FILTER_VALUE";
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> row : rows) {
			String filterCategory = row.get(FILTER_CATEGORY_NAME);
			String filterValues = row.get(FILTER_VALUE_NAME);

			String[] filterValuesArray = filterValues.split(", ");
			for (String filterValue : filterValuesArray) {
				appPage.searchFilterValueOnAppPage(filterValue);
				appPage.selectFilterValueOnAppPage(filterCategory, filterValue);
				boolean isappVisible = appPage.isAppDisplayedOnPage(appName);
				Assertions.assertTrue(isappVisible,
						"App is not present in the list for " + " ' " + filterValue + " ' " + " filter value");
				// To de-select selected filter we again call this method
				appPage.selectFilterValueOnAppPage(filterCategory, filterValue);
			}
		}
	}

	@Then("User clicks on app {string} button")
	public void User_clicks_on_button(String buttonName) {
		appPage.clickOnViewDetails(buttonName);
	}

	@And("User get the CatalogName for variable")
	public void user_get_the_catalog_name_for_variable() {
		copiedCatalogName = appVariablePage.getCatalogNameForVariable();
	}

	@When("User clicks on Variable")
	public void user_clicks_on_variable() {
		appVariablePage.clickOnVariablesOption();
	}

	@When("User clicks on Add Variable button")
	public void user_clicks_on_add_variable_button() {
		appVariablePage.clickOnAddVariableButton();
	}

	@When("User enters variable name as {string}")
	public void user_enters_variable_name_as(String variableName) {
		appVariablePage.enterVariableName(variableName);
	}

	@When("User selects variable type as {string}")
	public void user_selects_variable_type_as(String variableType) {
		appVariablePage.selectVariableType(variableType);
	}

	@When("User enters variable value")
	public void user_enters_variable_value() {
		appVariablePage.setVariableValue(copiedCatalogName);
	}

	@When("User enters variable value as {string}")
	public void user_enters_variable_value_as(String variableValue) {
		appVariablePage.enterVariableValue(variableValue);
	}

	@When("User enter variable value as {string}")
	public void user_enter_variable_value_as(String variableValue) {
		appVariablePage.enterVariable(variableValue);
	}

	@When("User clicks on Create Variable button")
	public void user_clicks_on_create_variable_button() {
		appVariablePage.clickOnCreateVariableButton();
	}

	@Then("User sees Toast message of variable creation {string}")
	public void user_sees_toast_message_of_variable_creation(String variableName) {
		appVariablePage.validateSuccessToastMessage(variableName);
	}

	@Then("User sees the variable with name {string} and type {string} in the variable list")
	public void user_sees_the_variable_in_the_variable_list(String variableName, String variableType) {
		appVariablePage.verifyVariablePresentInList(variableName, variableType);
	}
	// bookmark the app

	@When("User clicks on the Bookmark icon for {string} App")
	public void user_click_on_bookmark_icon(String appName) {
		blocksPage.userClickOnBookmarkIcon(appName);
	}

	@When("User clicks on the Unbookmark icon for {string} App")
	public void user_clicks_on_bookmark_icon_to_unbookmark_app(String appName) {
		blocksPage.clickOnUnbookmarkforApp(appName);
	}

	@Then("User see the Bookmarked section")
	public void user_see_bookmark_section() {
		boolean isVisible = blocksPage.userSeeBookMarkSection();
		Assertions.assertTrue(isVisible, "The user is unable to see the Bookmark section after Bookmark the App");
	}

	@And("The app should appear in the bookmarked section")
	public void bookmark_app_see_on_bookmarksection() {
		boolean isappDisplayedUnderBookmarkedSection = blocksPage.bookmarkAppSeeOnTheBookmarkSection();
		Assertions.assertTrue(isappDisplayedUnderBookmarkedSection,
				"Bookmarked section does not contain the expected app");
	}

	@Then("The {string} should be removed from the bookmarked section")
	public void app_should_be_removed_from_bookmarked_section(String appName) {
		boolean isRemoved = blocksPage.isAppRemovedFromBookmarkSection(appName);
		Assertions.assertTrue(isRemoved, "App is still visible under the bookmarked section after removal.");
	}

	@And("If no apps remain bookmarked the {string} section should not be visible")
	public void bookmarked_section_should_not_be_visible(String sectionName) {
		boolean isNotVisible = blocksPage.isBookmarkedSectionNotVisible();
		Assertions.assertTrue(isNotVisible,
				sectionName + " Section is still visible even though no apps are bookmarked.");
	}

	// created app display in all apps section
	@Then("User can see {string} app in the All Apps section")
	public void user_see_the_created_app_in_all_apps_section(String appName) {
		boolean isAppDisplayed = blocksPage.isAppDisplayedInAllAppsSection(appName);
		Assertions.assertTrue(isAppDisplayed, "Created Application is not displayed in All Apps section");
	}

	@And("User click on Discoverable Apps")
	public void user_click_discoverable_app() {
		blocksPage.clickOnDiscovrableApps();
	}

	@And("The newly created {string} should be displayed in the discoverable apps list")
	public void created_app_display_in_discoverable_app(String appName) {
		boolean appDispalyInDiscoverable = blocksPage.createdAppDisplayInDiscoverableApp(appName);
		Assertions.assertTrue(appDispalyInDiscoverable,
				"Created Application is not displayed in Discovrable Apps section");
	}

	@When("User click on System Apps")
	public void user_click_on_system_apps() {
		blocksPage.clickOnSystemApps();
	}

	@Then("User can see {string} app in the System Apps section")
	public void user_see_the_created_app_in_system_apps_section(String appName) {
		boolean isAppDisplayed = blocksPage.isAppDisplayedInSystemAppsSection(appName);
		Assertions.assertTrue(isAppDisplayed, "Created Application is not displayed in System Apps section");
	}

	@And("User clicks on edit variable option")
	public void user_clicks_on_edit_variable_option() {
		appVariablePage.clickOnEditVariableOption();
	}

	@And("User clicks on {string} open menu")
	public void user_clicks_on_variable_open_menu(String variableName) {
		appVariablePage.clickOnVariableOpenMenu(variableName);
	}

	@When("User clicks on Save variable button")
	public void user_clicks_on_save_variable_button() {
		appVariablePage.clickOnSaveVariableButton();
	}

	// bar charts
	@And("User click on the Tools tab")
	public void user_click_on_tool_tab() {
		blocksPage.clickOnToolTab();
	}

	@And("User validates Conditional using {string}")
	public void user_validates_conditional_using(String conditionalValues) {
		String[] values = conditionalValues.split(",");
		for (String val : values) {
			boolean expected = Boolean.parseBoolean(val.trim());
			blocksPage.applyConditional(val.trim());
			boolean isVisible = blocksPage.isBarChartVisible();
			if (expected) {
				Assertions.assertTrue(isVisible, "Chart should be visible when conditional = true");
			} else {
				Assertions.assertFalse(isVisible, "Chart should NOT be visible when conditional = false");
			}
		}
	}

	@And("User validates Color Palette using {string}")
	public void user_validates_color_palette_using(String colorPaletteValues) {
		for (String action : colorPaletteValues.split(",")) {
			action = action.trim().toLowerCase();
			if (action.equals("add color")) {
				boolean isAdded = blocksPage.performAddColor();
				if (!isAdded) {
					throw new AssertionError("Failed to add color from palette.");
				}
			} else if (action.equals("change color")) {
				blocksPage.performCheckColor();
			} else {
				throw new AssertionError("Invalid color palette action: " + action);
			}
		}
	}

	@And("User click on Legend Option and turn on the toggle")
	public void user_click_on_legend_option_and_turn_on_the_toggle() {
		blocksPage.clickOnLegendOptionAndTurnOnTheToggle();
	}

	@And("User click on the Edit {string} option")
	public void user_click_on_the_edit_x_axis_option(String axis) {
		blocksPage.clickOnEditXAxisOption(axis);
	}

	@And("User updates {string} settings using {string}")
	public void user_updates_axis_settings_using(String axis, String AxisSettings) {
		blocksPage.updateAxisSettings(axis, AxisSettings);
	}

	@Then("User can see {string} of {string} same as baseline")
	public void user_can_see_tool_same_as_baseline(String toolName, String chartName) throws Exception {

		String chartFolder = chartName.replaceAll("\\s+", "").toLowerCase();
		String toolFolder = toolName.replaceAll("\\s+", "").toLowerCase();

		String basePath = "screenshots/" + chartFolder + "/" + toolFolder + "/";

		String actualImagePath = basePath + "actualChart.png";
		String expectedImagePath = basePath + "expectedChart.png";
		String diffImagePath = basePath + "diffChart.png";

		blocksPage.closeBlocksOption();
		blocksPage.takeChartScreenshot(actualImagePath, toolName);

		boolean imagesMatch = CommonUtils.compareImages(actualImagePath, expectedImagePath, diffImagePath);

		Assertions.assertTrue(imagesMatch, "Images do not match for Tool: " + toolName + " under Chart: " + chartName);
	}

	@And("User turns on the Value Labels toggle")
	public void user_turns_on_the_value_label_toggle() {
		blocksPage.turnOnValueLabelToggle();
	}

	@And("User updates Value Labels settings using {string}")
	public void user_updates_value_label_settings_using(String valueLabelSettings) {
		blocksPage.updateValueLabelSettings(valueLabelSettings);
	}

	@And("User click on {string} tool option")
	public void user_click_on_tool_option(String toolName) {
		blocksPage.clickOnToolOption(toolName);
	}
	@And("User update Bar Style setting using {string}")
	public void and_user_update_bar_style_setting_using_bar_style_value(String barStyleValue) {
		blocksPage.updateBarStyle(barStyleValue);
	}
}
