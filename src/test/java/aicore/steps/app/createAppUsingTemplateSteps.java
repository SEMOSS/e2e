package aicore.steps.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import aicore.framework.UrlUtils;
import aicore.hooks.SetupHooks;
import aicore.pages.app.AppTemplatePage;
import aicore.pages.app.DragAndDropBlocksPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class createAppUsingTemplateSteps {
	private AppTemplatePage appTemplatePage;
	private DragAndDropBlocksPage blocksPage;

	public createAppUsingTemplateSteps() {
		appTemplatePage = new AppTemplatePage(SetupHooks.getPage());
		blocksPage = new DragAndDropBlocksPage(SetupHooks.getPage());
	}

	@Then("User selects {string} from Template List")
	public void user_selects_template_from_list(String templateName) {
		appTemplatePage.selectTemplateFromList(templateName);
	}

	@Then("User sees the title {string}")
	public void user_can_see_page_with_the_title(String title) {
		appTemplatePage.verifyPageWithTitle(title);
	}

	@And("User sees description as {string}")
	public void user_sees_description_as(String description) {
		appTemplatePage.verifyDescription(description);
	}

	@And("User sees input field with With label {string}")
	public void user_sees_input_field_with_label(String label) {
		appTemplatePage.verifyInputFieldWithLabel(label);
	}

	@And("User clicks on description block")
	public void user_clicks_on_description_block() {
		appTemplatePage.clickOnQuestionBlock();
	}

	@And("User adds description as {string}")
	public void user_adds_description_as(String description) {
		appTemplatePage.addDescription(description);
	}

	@And("User sees submit button")
	public void user_sees_submit_button() {
		appTemplatePage.verifySubmitButton();
	}

	@When("User clicks on Preview app button")
	public void user_clicks_on_preview_app_button() {
		appTemplatePage.clickPreviewButton();
	}

	@Then("User sees input field with With label {string} in Preview App")
	public void User_sees_input_field_with_With_label_in_Preview_App(String label) {
		appTemplatePage.verifyInputFieldWithLabelInPreview(label);
	}

	@When("User close the Preview app window")
	public void user_close_the_preview_app_window() {
		appTemplatePage.closePreviewWindow();
	}

	@Then("User sees description as {string} in Preview App")
	public void User_sees_description_as_in_Preview_App(String description) {
		appTemplatePage.verifyDescriptionInPreview(description);
	}

	@When("User clicks on Close Preview button")
	public void User_clicks_on_Close_Preview_button() {
		appTemplatePage.clickClosePreviewButton();
	}

	@Then("User sees submit button in Preview App")
	public void User_sees_submit_button_in_Preview_App() {
		appTemplatePage.verifySubmitButtonInPreview();
	}

	@Then("User sees the title {string} in Preview App")
	public void User_sees_the_title_in_Preview_App(String title) {
		appTemplatePage.verifyPageWithTitleInPreview(title);
	}

	@Then("User sees the title as {string}")
	public void user_sees_the_title_as(String titleText) {
		appTemplatePage.verifyAppPageTitle(titleText);
	}

	@Then("User sees title of the block as {string}")
	public void user_sees_title_of_the_block_as(String title) {
		appTemplatePage.verifyPageWithtitleText(title);
	}

	@Then("User change title {string} with {string}")
	public void user_change_the_title_as(String oldTitle, String newTitle) {
		appTemplatePage.changeAppPageTitle(oldTitle, newTitle);
	}

	@And("User clicks on the title as {string}")
	public void user_clicks_on_the_title_as(String title) {
		appTemplatePage.clickOnAppPageTitle(title);
	}

	@Then("User sees the sub title as {string}")
	public void user_sees_the_sub_title_as(String title) {
		appTemplatePage.verifyAppPageSubTitle(title);
	}

	@Then("User views description as {string}")
	public void user_views_description_as(String description) {
		appTemplatePage.verifyDescriptionBelowTitle(description);
	}

	@Then("User sees the hyperlink with text {string} should point to the url {string}")
	public void user_sees_the_hyperlink_with_text_should_point_to_the_url(String text, String url) {
		appTemplatePage.verifyHyperlink(text, url);
		String currentUrl = appTemplatePage.getCurrentUrl();
		String actualRelativePath = UrlUtils.extractRelativePath(currentUrl);
		assertEquals(url, actualRelativePath, "URL mismatch!");
	}

	@Then("User navigates to back page")
	public void user_navigates_to_back_page() {
		appTemplatePage.getBackPage();
	}

	@Then("User views description for the block with title {string} as {string}")
	public void user_views_description_for_the_block_with_title_as(String blockTitle, String description) {
		appTemplatePage.verifyDescriptionBelowTitleOfBlock(blockTitle, description);
	}

	@Then("User clicks on the hyperlink with text {string}  with title {string} should point to {string}")
	public void user_clicks_on_the_hyperlink_with_text_with_title_should_point_to(String text, String blockTitle,
			String url) {
		appTemplatePage.verifyHyperlinkText(text, blockTitle, url);
		String currentUrl = appTemplatePage.getCurrentUrl();
		String actualRelativePath = UrlUtils.extractRelativePath(currentUrl);
		assertEquals(url, actualRelativePath, "URL mismatch!");
	}

	@Then("User clicks on hyperlink text {string}")
	public void user_clicks_on_hyperlink_text(String text) {
		appTemplatePage.clickOnHyperlinkText(text);
	}

	@Then("User filles the destination URL as {string}")
	public void user_filles_the_destination_url_as(String url) {
		appTemplatePage.fillDestinationUrl(url);
	}

	@Then("User clicks on Save button of the app")
	public void user_clicks_on_save_button_of_the_app() {
		appTemplatePage.clickSaveButtonOfTheApp();
	}

	@Then("User sees the URL as {string}")
	public void user_sees_the_url_as(String expectedUrl) {
		String actualUrl = appTemplatePage.getCurrentUrl();
		assertEquals(expectedUrl, actualUrl, "Expected URL does not match the current page URL.");
	}

	@And("User see the {string}")
	public void user_see_Page1(String expectedText) {
		String actualText = appTemplatePage.userSeePage1();
		assertEquals(actualText, expectedText, "Expected and Actual Text is do not match");
	}

	@And("User see the {string} block")
	public void user_see_template_page_title(String expectedblock) {
		String actualBlock = appTemplatePage.userSeeTeamplatePageTitle();
		assertEquals(actualBlock, expectedblock, "Expected and Actual Block is do not match");
	}

	@And("User see the {string} hyperlink")
	public void user_see_hyperlink(String hrefValue) {
		boolean isVisible = appTemplatePage.userSeeTheHyperlink(hrefValue);
		assertTrue(isVisible, "Hyperlink is not visible to the user");

	}

	@Then("User click on the {string} hyperlink should point to {string}")
	public void user_click_on_the_hyperlink_should_point_to(String text, String expectedUrl) {
		appTemplatePage.verifyHyperlink(text, expectedUrl);
		String currentUrl = appTemplatePage.getCurrentUrl();
		String actualRelativePath = UrlUtils.extractRelativePath(currentUrl);
		assertTrue(actualRelativePath.matches(expectedUrl),
				"URL mismatch!\nExpected pattern: " + expectedUrl + "\nActual URL: " + actualRelativePath);
	}

	@And("User drags the {string} block and drops it on the {string}")
	public void user_drop_chart_on_page(String blockName, String titleOfPage) {
		blocksPage.mouseHoverOnBlock(blockName);
		boolean isVisiable = appTemplatePage.dropChartOnPage(titleOfPage);
		assertTrue(isVisiable,
				"Expected: Chart should be visible on the Page after drag-and-drop. " + "But it was not found.");
	}

	@And("User see the {string} title after clicking resources hyperlink")
	public void user_see_resource_title(String expectedTitle) {
		String actualTitle = appTemplatePage.userSeeResourceTitle();
		assertEquals(expectedTitle, actualTitle, "Page title does not match.");
	}

	@And("User see the {string} title after clicking about hyperlink")
	public void user_see_about_title(String expectedTitle) {
		String actualTitle = appTemplatePage.userSeeAboutTitle();
		assertEquals(expectedTitle, actualTitle, "Page title does not match.");
	}

	// Variable Guide App
	@And("User can see the {string} block")
	public void user_see_variable_guide_blocks_Title(String blockTitle) {
		String actualBlockTitle = appTemplatePage.userSeeVariableGuideBlocksTitle(blockTitle);
		assertEquals(blockTitle, actualBlockTitle, "Variable Guide Block title does not match.");
	}

	@And("User can click on the {string} block and see the Fonts Style and Size")
	public void user_see_font_style_and_size_block(String block) {
		boolean isVisiable = appTemplatePage.userSeeTheFontStyleAndSizeBlock(block);
		assertTrue(isVisiable, "Expected: After clicking on block '" + block
				+ "', the Font Style and Size options should be visible, but they were not found.");
	}

	@When("User selects {string} font style")
	public void user_selects_font_style(String fontName) {
		appTemplatePage.selectFontStyle(fontName);
	}

	@And("User changes font size to {string}")
	public void user_changes_font_size(String size) {
		appTemplatePage.changeFontSize(size);
	}

	@Then("The font style should be {string} and size should be {string}")
	public void the_font_style_should_be_and_size_should_be(String expectedFont, String expectedSize) {
		String actualFont = appTemplatePage.getSelectedFont();
		String actualSize = appTemplatePage.getFontSize();
		assertEquals(expectedFont, actualFont, "Font style does not match!");
		assertEquals(expectedSize, actualSize, "Font size does not match!");
	}

	@When("User clicks on the Submit Block")
	public void user_clicks_on_the_submit_block() {
		appTemplatePage.clickOSubmitBlock();
	}

	@And("User clicks on the Response Block")
	public void user_clicks_on_the_response_block() {
		appTemplatePage.clickOnResponseBlock();
	}

	// nlp teamplate
	@And("User select the {string} from notebook")
	public void user_select_the_from_notebook(String notebookName) {
		appTemplatePage.selectNotebookFromlist(notebookName);
	}

	@And("User select the {string} model for {string}")
	public void user_select_the_model_for_nlp_query(String modelName, String notebookName) {
		appTemplatePage.selectModelForNLPTemplate(modelName, notebookName);
	}

	@And("User click on Fetch Data")
	public void user_click_on_fetch_data_button() {
		appTemplatePage.clickOnFetchDataButton();
	}

	@When("User enter the query for people {string} the age {string}")
	public void user_enter_the_query_for_people_condition_the_age(String condition, String age) {
		String query = "people " + condition + " the age " + age;
		appTemplatePage.enterQueryForNLPTemplate(query);
	}

	@Then("Results should contain only people with age {string} {string}")
	public void results_should_contain_only_people_with_age(String condition, String number) {

		int num = Integer.parseInt(number);

		boolean result = appTemplatePage.validateAges(condition, num);

		assertTrue(result, "Validation failed. Some records do not satisfy the condition: " + condition + " " + number);
	}

	@Then("User sees the description as {string}")
	public void user_sees_the_description_as(String descriptionText) {
		appTemplatePage.verifyAppPageDescription(descriptionText);
	}

	@Then("User checks {string} button is enabled")
	public void user_checks_button_is_enabled(String buttonText) {
		boolean isEnabled = appTemplatePage.isButtonEnabled(buttonText);
		assertTrue(isEnabled, "Expected: Button '" + buttonText + "' should be enabled, but it is not.");
	}

	@Then("User checks the created models are visible in the list")
	public void user_checks_the_created_models_are_visible_in_the_list() {
		boolean areModelsVisible = appTemplatePage.verifyCreatedModelsInList();
		assertTrue(areModelsVisible," Created models are not visible.");
	}
}
