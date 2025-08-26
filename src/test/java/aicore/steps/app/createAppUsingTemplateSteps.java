package aicore.steps.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import aicore.hooks.SetupHooks;
import aicore.pages.app.AppTemplatePage;
import aicore.pages.app.DragAndDropBlocksPage;
import aicore.utils.UrlUtils;
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
		appTemplatePage.verifyPageWithtitleText(titleText);
	}

	@Then("User sees title of the block as {string}")
	public void user_sees_title_of_the_block_as(String title) {
		appTemplatePage.verifyAppPageTitle(title);
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

	@Then("User sees the hyperlink with text {string} should point to {string}")
	public void user_sees_the_hyperlink_with_text_should_point_to(String text, String url) {
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
	public void user_see_landing_page_block(String expectedblock) {
		String actualBlock = appTemplatePage.userSeeLandingPageBlock();
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

	@And("User drags the {string} block and drops it on the landing page")
	public void user_drop_chart_on_landing_page(String blockName) {
		blocksPage.mouseHoverOnBlock(blockName);
		boolean isVisiable = appTemplatePage.dropChartOnLandingPage();
		assertTrue(isVisiable, "Expected: Chart should be visible on the Landing Page after drag-and-drop. "
				+ "But it was not found.");
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
}
