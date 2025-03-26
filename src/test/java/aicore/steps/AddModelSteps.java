package aicore.steps;

import aicore.hooks.SetupHooks;
import org.junit.jupiter.api.Assertions;

import aicore.pages.AddModelToCatalogPage;
import aicore.pages.HomePage;
import aicore.utils.CommonUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddModelSteps {
	private HomePage homePage;
	private AddModelToCatalogPage openModelPage;
	protected static String timestamp;

	public AddModelSteps() {
		this.homePage = new HomePage(SetupHooks.getPage());
		timestamp = CommonUtils.getTimeStampName();
		this.openModelPage = new AddModelToCatalogPage(SetupHooks.getPage(), timestamp);
	}

	@Given("User navigates to Open Model")
	public void user_navigates_to_open_model() {
		homePage.clickOnOpenModel();
	}

	@When("User clicks on Add Model")
	public void user_clicks_on_add_model() {
		openModelPage.addModelButton();
	}

	@When("User selects {string}")
	public void user_selects(String aiModelName) {
		openModelPage.selectOpenAi(aiModelName);
	}

	@And("User enters Catalog name as {string}")
	public void user_enters_Catalog_name_as(String CatalogName) {
		openModelPage.enterCatalogName(CatalogName);
	}

	@When("User enters open AI Key as {string}")
	public void user_enters_open_ai_key_as(String openAIKey) {
		openModelPage.enterOpenAIKey(openAIKey);
	}

	@When("User enters var name as {string}")
	public void user_enters_var_name_as(String varName) {
		openModelPage.enterVariableName(varName);
	}

	@When("User clicks on Create Model button")
	public void user_clicks_on_create_model_button() throws InterruptedException {
		openModelPage.createModel();
	}

	@And("User can see a toast message as {string}")
	public void user_can_see_toast_message_as(String toastMessage) {
		String actualMessage = openModelPage.modelCreationToastMessage();
		Assertions.assertEquals(actualMessage, toastMessage, "Model creation failed");
	}

	@Then("User Can see the Model title as {string}")
	public void user_can_see_the_model_title_as(String modelTitle) {
		String actualModelTitle = openModelPage.verifyModelTitle();
		String expModelTitle = openModelPage.getExpectedCatalogTitle(modelTitle);
		Assertions.assertEquals(actualModelTitle, expModelTitle);
	}

	@Then("User clicks on SMSS")
	public void user_clicks_on_smss() {
		openModelPage.clickOnSMSSTab();
	}

	@Then("User can see name in {string} field as {string} in SMSS properties")
	public void user_can_see_name_in_field_as_in_smss_properties(String field, String nameFiledValue) {
		String fullText = openModelPage.verifyNameInSMSS();
		String actualModelName = CommonUtils.splitTrimValue(fullText, field);
		String expectedNameProperties = openModelPage.getExpectedCatalogTitle(nameFiledValue);
		Assertions.assertEquals(actualModelName, expectedNameProperties, "Model name is not matching");
	}

	@Then("User can see var name in {string} field as {string} in SMSS properties")
	public void user_can_see_var_name_in_field_as_in_smss_properties(String field, String nameFiledValue) {
		String fullText = openModelPage.verifyVarNameInSMSS();
		String actualVarName = CommonUtils.splitTrimValue(fullText, field);
		Assertions.assertEquals(actualVarName, nameFiledValue, "Var name is not matching");
	}

	@When("User clicks on Edit button")
	public void user_clicks_on_edit_button() {
		openModelPage.clickOnEditButton();
	}

	@When("User enters tag as {string} in Edit Model Details and press enter")
	public void user_enters_tag_as_in_edit_model_details_and_press_enter(String tagName) {
		openModelPage.enterTagName(tagName);
	}

	@When("User clicks on Submit button")
	public void user_clicks_on_submit_button() {
		openModelPage.clickOnSubmit();
	}

	@Then("User can see {string} tag added")
	public void user_can_see_tag_added(String tagNameAfterAdding) {
		String actualTagName = openModelPage.verifyTagNameafteradding();
		Assertions.assertEquals(actualTagName, tagNameAfterAdding, "Tag name after adding failed");
	}

//Edit SMSS

	@And("User clicks on Edit SMSS button")
	public void user_clicks_on_edit_smss_button() {
		openModelPage.clickOnEditSMSSButton();
	}

	@And("User can edit the value of {string} field as {string}")
	public void user_can_edit_the_value_of_field_as(String fieldName, String newValue) {
		openModelPage.editSMSSFieldValues(fieldName, newValue);
	}

	@And("User clicks on Update SMSS button")
	public void user_clicks_on_update_smss_button() {
		openModelPage.clickOnUpdateSMSSButton();
	}

	@And("User refresh the page")
	public void user_refresh_the_page() {
		openModelPage.pageReload();
	}

	@Then("User can see updated value in {string} field as {string}")
	public void user_can_see_updated_value_in_field_as(String field, String newValue) {
		String fullText = null;
		switch (field) {
		case "VAR_NAME":
			fullText = openModelPage.verifyVarNameInSMSS();
			break;
		case "KEEP_CONVERSATION_HISTORY":
			fullText = openModelPage.verifyKeepConversationHistoryValueInSMSS(field);
		default:
			System.out.println("Invalid field name " + field);
		}
		String actualVarName = CommonUtils.splitTrimValue(fullText, field);
		assertEquals(actualVarName, newValue);
	}
}
