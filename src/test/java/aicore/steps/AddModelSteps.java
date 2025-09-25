package aicore.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;

import aicore.hooks.SetupHooks;
import aicore.pages.AddModelPage;
import aicore.pages.HomePage;
import aicore.pages.ViewCatalogPage;
import aicore.utils.CommonUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddModelSteps {
	private HomePage homePage;
	private AddModelPage openModelPage;
	protected static String timestamp;
	private String expectedCatalogId;
	private ViewCatalogPage viewCatalogPage;

	public AddModelSteps() {
		this.homePage = new HomePage(SetupHooks.getPage());
		timestamp = CommonUtils.getTimeStampName();
		this.openModelPage = new AddModelPage(SetupHooks.getPage(), timestamp);
		viewCatalogPage = new ViewCatalogPage(SetupHooks.getPage());

	}

	@Given("User clicks on Open Model")
	public void user_navigates_to_open_model() {
		homePage.clickOnOpenModel();
	}

	@When("User clicks on Add Model")
	public void user_clicks_on_add_model() {
		openModelPage.clickAddModelButton();
	}

	@When("User selects {string}")
	public void user_selects(String aiModelName) {
		openModelPage.selectModel(aiModelName);
	}

	@And("User enters Catalog name as {string}")
	public void user_enters_Catalog_name_as(String catalogName) {
		openModelPage.enterCatalogName(catalogName);
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
		openModelPage.clickOnCreateModelButton();
	}

	@Given("User uploads a file {string}")
	public void user_uploads_a_file(String fileName) {
		String uploadedFileName = openModelPage.enterFilePath(fileName);
		if (fileName.contains("/")) {
			String[] ActualFileName = fileName.split("/");
			int fileNameIndex = ActualFileName.length - 1;
			Assertions.assertEquals(ActualFileName[fileNameIndex], uploadedFileName,
					"Document is not uploaded successfully");
		} else {
			Assertions.assertEquals(fileName, uploadedFileName, "Document is not uploaded successfully");
		}
	}

	@And("User can see a toast message as {string}")
	public void user_can_a_see_toast_message_as(String toastMessage) {
		String actualMessage = openModelPage.modelCreationToastMessage();
		Assertions.assertEquals(actualMessage, toastMessage, "Model creation failed");
		openModelPage.waitForModelCreationToastMessageDisappear();
	}

	@Then("User Can see the Model title as {string}")
	public void user_can_see_the_model_title_as(String modelTitle) {
		String actualModelTitle = openModelPage.verifyModelTitle(modelTitle);
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
		viewCatalogPage.clickEditIcon();
	}

	@When("User searches the {string} in the model catalog searchbox")
	public void user_searches_the_in_the_model_catalog_searchbox(String modelName) {
		openModelPage.searchModelCatalog(modelName + timestamp);
	}

	@Then("User should see the {string} on the model catalog page")
	public void user_should_see_the_on_the_model_catalog_page(String modelName) throws InterruptedException {
		openModelPage.searchModelCatalog(modelName + timestamp);
		boolean isModelDisplayed = openModelPage.verifyModelIsDisplayedOnCatalogPage(modelName + timestamp);
		Assertions.assertTrue(isModelDisplayed);
	}

	@And("User selects the {string} from the model catalog")
	public void user_selects_the_from_the_model_catalog(String modelName) throws InterruptedException {
		openModelPage.selectModelFromSearchOptions(modelName + timestamp);
	}

	@And("User enters the details as {string}")
	public void user_enters_the_details_as(String detailsText) {
		openModelPage.enterDetails(detailsText);
	}

	@And("User enters the description as {string}")
	public void user_enters_the_description_as(String descriptionText) {
		openModelPage.enterDescription(descriptionText);
	}

	@And("User add tags {string} and presses Enter")
	public void user_add_tags_and_presses_enter(String tags) {
		String[] tagsArray = tags.split(", ");
		for (String tag : tagsArray) {
			viewCatalogPage.enterTagName(tag);
		}
	}

	@And("User enters the Domains as {string}")
	public void user_enters_the_domains_as(String domainNames) {
		String[] allDomainNames = domainNames.split(", ");
		for (String domainName : allDomainNames) {
			openModelPage.enterDomainName(domainName);
		}
	}

	@And("User selects {string} from the Data Classification dropdown")
	public void user_selects_from_the_data_classification_dropdown(String dataClassificationOptions) {
		String[] options = dataClassificationOptions.split(", ");
		for (String option : options) {
			openModelPage.selectDataClassificationOption(option);
		}
	}

	@And("User selects {string} from the Data Restrictions dropdown")
	public void user_selects_from_the_data_restrictions_dropdown(String dataRestictionOptions) {
		String[] options = dataRestictionOptions.split(", ");
		for (String option : options) {
			openModelPage.selectDataRestrictionsOption(option);
		}
	}

	@Then("User can see a edit success toast message as {string}")
	public void user_can_see_a_edit_success_toast_message_as(String expectedToastMessage) {
		String actualToastMessage = viewCatalogPage.verifyEditSuccessfullToastMessage();
		Assertions.assertEquals(actualToastMessage, expectedToastMessage);
		viewCatalogPage.waitForEditSuccessToastMessageToDisappear();
	}

	@And("User should see description as {string} on the page")
	public void user_should_see_description_as_on_the_page(String expectedDescriptionText) {
		String actualDescriptionText = openModelPage.verifyDescriptionText();
		Assertions.assertEquals(actualDescriptionText, expectedDescriptionText);
	}

	@And("User should see {string} on the page")
	public void user_should_see_on_the_page(String expectedTags) {
		String[] tagArray = expectedTags.split(", ");
		List<String> actualTagList = openModelPage.verifyTagNames();
		List<String> expectedTagList = Arrays.asList(tagArray).subList(0, Math.min(2, tagArray.length));
		Assertions.assertEquals(actualTagList, expectedTagList);
	}

	@And("User should see {string} in the overview Details section")
	public void user_should_see_in_the_overview_details_section(String expectedDetailsText) {
		String actualDetailsText = openModelPage.verifyDetailsTextUnderOverview();
		Assertions.assertEquals(actualDetailsText, expectedDetailsText);
	}

	@And("User should see {string} in the overview Tag section")
	public void user_should_see_in_the_overview_tag_section(String expectedTags) {
		String[] tagArray = expectedTags.split(", ");
		List<String> expectedTagList = Arrays.asList(tagArray);
		List<String> actualTagList = openModelPage.verifyTagNamesUnderOverview();
		Assertions.assertEquals(actualTagList, expectedTagList);
	}

	@And("User should see {string} in the overview Domain section")
	public void user_should_see_in_the_overview_domain_section(String expectedDomains) {
		String[] domainArray = expectedDomains.split(", ");
		List<String> expectedDomainList = Arrays.asList(domainArray);
		List<String> actualDomainList = openModelPage.verifyDomainValuesUnderOverview();
		Assertions.assertEquals(actualDomainList, expectedDomainList);
	}

	@And("User should see {string} in the overview Data classification section")
	public void user_should_see_in_the_overview_data_classification_section(String expectedDataClassificationOptions) {
		String[] dataClassificationOptionsArray = expectedDataClassificationOptions.split(", ");
		List<String> expectedDataClassificationOptionsList = Arrays.asList(dataClassificationOptionsArray);
		List<String> actualDataClassificationOptionsList = openModelPage.verifyDataClassificationOptionsUnderOverview();
		Assertions.assertEquals(actualDataClassificationOptionsList, expectedDataClassificationOptionsList);
	}

	@And("User should see {string} in the overview Data restrictions section")
	public void user_should_see_in_the_overview_data_restrictions_section(String expectedDataRestrictionOptions) {
		String[] dataRestrictionOptionsArray = expectedDataRestrictionOptions.split(", ");
		List<String> expectedDataRestrictionOptionsList = Arrays.asList(dataRestrictionOptionsArray);
		List<String> actualDataRestrictionOptionsList = openModelPage.verifyDataRestrictionOptionsUnderOverview();
		Assertions.assertEquals(actualDataRestrictionOptionsList, expectedDataRestrictionOptionsList);
	}

	@When("User clicks on Submit button")
	public void user_clicks_on_submit_button() {
		viewCatalogPage.clickOnSubmit();
	}

	@When("User clicks on Close button")
	public void user_clicks_on_close_button() {
		viewCatalogPage.clickOnClose();
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
			break;
		default:
			System.out.println("Invalid field name " + field);
		}
		String actualVarName = CommonUtils.splitTrimValue(fullText, field);
		assertEquals(actualVarName, newValue);
	}

	// Usage

	@When("User copies the model catalog ID below the title using copy icon")
	public void user_copies_the_model_catalog_id_below_the_title_using_copy_icon() {
		expectedCatalogId = openModelPage.copyModelID();
	}

	@When("User copies code contents and validate model catalog Id occurences in sections:")
	public void user_copies_code_contents_and_validate_model_catalog_id_occurences_in_sections(DataTable table) {

		final String MODEL_USAGE_COMMANDS_SECTION_NAME = "SECTIONS";
		final String EXPECTED_MODEL_ID_COUNT = "EXPECTED_MODEL_ID_COUNT";

		List<Map<String, String>> rows = table.asMaps(String.class, String.class);

		for (Map<String, String> row : rows) {
			String sectionName = row.get(MODEL_USAGE_COMMANDS_SECTION_NAME);
			int expectedCount = Integer.parseInt(row.get(EXPECTED_MODEL_ID_COUNT));
			String copiedSectionContents = openModelPage.getFullSectionCodeByHeading(sectionName);
			int countIdOccurances = CommonUtils.countIdOccurances(copiedSectionContents, expectedCatalogId);
			Assertions.assertEquals(expectedCount, countIdOccurances,
					"Model id not match for the section '" + sectionName + "'");
		}
	}

	@Then("User clicks on the created Model card name as {string}")
	public void user_clicks_on_the_created_Model_card_name_as(String modelName) {
		openModelPage.addedModelCard(modelName);
	}

	@Then("User sees deleted Model success toast message {string}")
	public void user_sees_deleted_Model_success_toast_message(String toastMessage) {
		String expectedMessage = openModelPage.verifyDeleteToastMessage();
		String actualMessage = toastMessage;
		Assertions.assertEquals(actualMessage, expectedMessage, "Delete Message is not matching with expected");
	}

	@And("User clicks on Discoverable Models button")
	public void user_clicks_on_discoverable_models_button() {
		openModelPage.clickOnDiscoverableModelsButton();
	}

	@And("User searches {string} in the model catalog searchbox")
	public void user_searches_text_embeddings_baa_large_en_v1_5_in_the_model_catalog_searchbox(String modelName) {
		openModelPage.searchModelCatalog(modelName);
	}

	@And("User selects {string} from the model catalog")
	public void user_selects_text_embeddings_baa_large_en_v1_5_from_the_model_catalog(String modelName) {
		openModelPage.selectModelFromSearchOptions(modelName);
	}

	@And("User click on Created Model")
	public void user_click_on_created_model() {
		openModelPage.userClickOnCreatedModel();
	}

}
