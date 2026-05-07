package aicore.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;

import aicore.base.GenericSetupUtils;
import aicore.hooks.SetupHooks;
import aicore.pages.AddModelPage;
import aicore.pages.ViewCatalogPage;
import aicore.pages.base.EditMetadataPageUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.pages.model.AddModelFormUtils;
import aicore.utils.CommonUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddModelSteps {
	private AddModelPage openModelPage;
	protected static String timestamp;
	private String expectedCatalogId;
	private ViewCatalogPage viewCatalogPage;

	public AddModelSteps() {
		timestamp = SetupHooks.getTimestamp();
		this.openModelPage = new AddModelPage(SetupHooks.getPage(), timestamp);
		viewCatalogPage = new ViewCatalogPage(SetupHooks.getPage());
	}

	@Given("User clicks on Open Model")
	public void user_navigates_to_open_model() {
		MainMenuUtils.clickOnOpenModel(SetupHooks.getPage());
	}

	@When("User clicks on Add Model")
	public void user_clicks_on_add_model() {
		openModelPage.clickAddModelButton();
	}

	@When("User clicks on the {string} tab")
	public void user_clicks_on_the_tab(String tabName) {
		openModelPage.clickOnGroupTab(tabName);
	}

	@And("User add {string} models with details {string} {string} {string} {string} {string}")
	public void user_add_models_with_details(String index, String modelType, String modelName, String catalogName,
			String apiKey, String tags) {
		int modelCount = Integer.parseInt(index);
		for (int i = 0; i < modelCount; i++) {
			openModelPage.selectModelType(modelType);
			AddModelFormUtils.selectModel(SetupHooks.getPage(), modelName);
			openModelPage.enterCatalogName(catalogName + "" + (i + 1));
			openModelPage.enterOpenAIKey(apiKey);
			openModelPage.clickOnCreateModelButton();
			EditMetadataPageUtils.clickEditIcon(SetupHooks.getPage());
			String[] tagsArray = tags.split(", ");
			for (String tag : tagsArray) {
				EditMetadataPageUtils.enterTagName(SetupHooks.getPage(), tag);
			}
			EditMetadataPageUtils.clickOnSubmit(SetupHooks.getPage());
			if (i < modelCount - 1) {
				MainMenuUtils.openMainMenu(SetupHooks.getPage());
				MainMenuUtils.clickOnOpenModel(SetupHooks.getPage());
				openModelPage.clickAddModelButton();
			}
		}
	}

	@And("User selects {string} type")
	public void user_selects_type(String model) {
		openModelPage.selectModelType(model);
	}

	@Then("User can see following form sections with fields:")
	public void user_can_see_following_form_sections_with_fields(DataTable dataTable) {
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> row : rows) {
			String sectionName = row.get("SECTION_NAME");
			String[] fields = row.get("FIELDS").split(", ");
			for (String field : fields) {
				boolean isFieldVisible = openModelPage.fieldUnderSection(sectionName, field);
				Assertions.assertTrue(isFieldVisible, field + " is not visible under " + sectionName + " section");
			}
		}
	}

	@Then("User can see following fields are mandatory fields")
	public void user_can_see_following_fields_are_mandatory_fields(DataTable dataTable) {
		String singleCell = dataTable.cells().get(0).get(0);
		String[] fields = singleCell.split(", ");
		for (String field : fields) {
			boolean isFieldMandatory = openModelPage.isFieldMandatory(field);
			Assertions.assertTrue(isFieldMandatory, field + " is not mandatory field");
		}

	}

	@When("User fills the model creation form with:")
	public void user_fills_the_model_creation_form_with(DataTable dataTable) {
		String singleCell = dataTable.cells().get(0).get(0);
		String[] fields = singleCell.split(", ");
		for (String field : fields) {
			if (!field.contains("=")) {
				continue;
			}
			String[] keyValue = field.split("=", 2);
			String fieldName = keyValue[0].trim();
			String fieldValue = keyValue[1].trim();
			openModelPage.fillModelCreationForm(fieldName, fieldValue);
		}
	}

	@Then("User can see {string} button becomes enabled")
	public void user_can_see_button_becomes_enabled(String buttonName) {
		boolean isButtonEnabled = openModelPage.validateConnectButtonEnabled();
		Assertions.assertTrue(isButtonEnabled, "'Connect' button is not enabled");
	}

	@And("User enters the following details in the model configuration")
	public void user_enters_value_as_field_name(DataTable dataTable) {
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> row : rows) {
			String fieldName = row.get("fieldName");
			String fieldValue = row.get("fieldValue");
			openModelPage.fillModelCreationForm(fieldName, fieldValue);
		}
	}

	@Then("User clicks on model {string} button")
	public void user_clicks_on_model_button(String buttonName) {
		openModelPage.clickOnCreateModelButton(buttonName);
	}

	@When("User selects {string}")
	public void user_selects(String aiModelName) {
		AddModelFormUtils.selectModel(SetupHooks.getPage(), aiModelName);
	}

	@And("User enters Catalog Name as {string}")
	public void user_enters_Catalog_name_as(String catalogName) {
		openModelPage.enterCatalogName(catalogName);
	}

	@When("User enters Open AI Key as {string}")
	public void user_enters_open_ai_key_as(String openAIKey) {
		openModelPage.enterOpenAIKey(openAIKey);
	}

	@When("User clicks on Create Model button")
	public void user_clicks_on_create_model_button() throws InterruptedException {
		openModelPage.clickOnCreateModelButton();
	}

	@And("User can see a toast message as {string}")
	public void user_can_a_see_toast_message_as(String toastMessage) {
		String actualMessage = openModelPage.modelCreationToastMessage(toastMessage);
		Assertions.assertEquals(actualMessage, toastMessage, "Model creation failed");
		// openModelPage.closeModelCreationToastMessage();
	}

	@Then("User can see the Model title as {string}")
	public void user_can_see_the_model_title_as(String modelTitle) {
		String actualModelTitle = openModelPage.verifyModelTitle(modelTitle);
		String expModelTitle = openModelPage.getExpectedCatalogTitle(modelTitle);
		Assertions.assertEquals(actualModelTitle, expModelTitle);
	}

	@Then("User clicks on SMSS")
	public void user_clicks_on_smss() {
		openModelPage.clickOnSMSSTab();
	}

	@And("User clicks on Chat button")
	public void user_Clicks_On_Chat_Button() {
		openModelPage.clickOnChatTab();
	}

	@Then("User should see the Chat section for Model with title {string}")
	public void user_Should_See_The_Chat_Section_For_Model_with_title(String title) {
		openModelPage.verifyChatSectionDisplayed(title);
	}

	@And("User should see the Model ID and Model Name displayed in Model information section")
	public void user_Should_See_The_Model_ID_And_Model_Name_Displayed_In_Model_Information_Section() {
		openModelPage.verifyModelIDAndNameDisplayed();
	}

	@And("User should see the Temperature value displayed as {string} in Model information section by default")
	public void user_Should_See_The_Temperature_Value_Displayed_As_In_Model_Information_Section_By_Default(
			String tempValue) {
		openModelPage.verifyTemperatureValue(tempValue);
	}

	@And("User should see the Max Tokens value displayed as {string} in Model information section by default")
	public void user_Should_See_The_Max_Tokens_Value_Displayed_As_In_Model_Information_Section_By_Default(
			String maxTokens) {
		openModelPage.verifyMaxTokensValue(maxTokens);
	}

	@And("User should see the input textbox with placeholder as {string}")
	public void user_Should_See_The_Input_Textbox_With_Placeholder_As(String placeholder) {
		openModelPage.verifyInputTextboxPlaceholder(placeholder);
	}

	@And("User should see the send button get active on entering text {string} in the input textbox")
	public void user_Should_See_The_Send_Button_Get_Active_On_Entering_Text_In_The_Input_Textbox(String inputText) {
		openModelPage.verifyAndActivateSendButton(inputText);
	}

	@When("User click on send button to submit the query")
	public void user_Click_On_Send_Button_To_Submit_The_Query() {
		openModelPage.clickOnSendButton();
	}

	@When("User click on clear all button")
	public void user_Click_On_Clear_All_Button() {
		openModelPage.clickOnClearAllButton();
	}

	@Then("User should see the chat window is cleared of previous conversation")
	public void user_Should_See_The_Chat_Window_Is_Cleared_Of_Previous_Conversation() {
		openModelPage.verifyChatWindowCleared();
	}

	@Then("User should see the loader indicating that the response is being generated for the query")
	public void user_Should_See_The_Loader_Indicating_That_The_Response_Is_Being_Generated_For_The_Query() {
		openModelPage.verifyLoaderDisplayed();
	}

	@And("User should see the response generated for the query in the chat window")
	public void user_Should_See_The_Response_Generated_For_The_Query_In_The_Chat_Window() {
		openModelPage.verifyResponseGeneratedInChatWindow();
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
		EditMetadataPageUtils.clickEditIcon(SetupHooks.getPage());
	}

	@When("User searches the {string} in the model catalog searchbox")
	public void user_searches_the_in_the_model_catalog_searchbox(String modelName) {
		if (!modelName.contains("Perplexity")) {
			modelName = modelName + timestamp;
		}
		openModelPage.searchModelCatalog(modelName);
	}

	@Then("User should see the {string} on the model catalog page")
	public void user_should_see_the_on_the_model_catalog_page(String modelName) throws InterruptedException {
		boolean isModelDisplayed = openModelPage.verifyModelIsDisplayedOnCatalogPage(modelName + timestamp);
		Assertions.assertTrue(isModelDisplayed, "Model is not displayed");
	}

	@And("User selects the {string} from the model catalog")
	public void user_selects_the_from_the_model_catalog(String modelName) throws InterruptedException {
		if (!modelName.contains("Perplexity")) {
			modelName = modelName + timestamp;
		}
		openModelPage.selectModelFromSearchOptions(modelName);
	}

	@And("User enters the details as {string}")
	public void user_enters_the_details_as(String detailsText) {
		EditMetadataPageUtils.enterDetails(SetupHooks.getPage(), detailsText);
	}

	@And("User enters the description as {string}")
	public void user_enters_the_description_as(String descriptionText) {
		EditMetadataPageUtils.enterDescription(SetupHooks.getPage(), descriptionText);
	}

	@And("User add Tags {string} and presses Enter")
	public void user_add_tags_and_presses_enter(String tags) {
		String[] tagsArray = tags.split(", ");
		for (String tag : tagsArray) {
			EditMetadataPageUtils.enterTagName(SetupHooks.getPage(), tag);
		}
	}

	@And("User enters the Domains as {string}")
	public void user_enters_the_domains_as(String domainNames) {
		String[] allDomainNames = domainNames.split(", ");
		for (String domainName : allDomainNames) {
			EditMetadataPageUtils.enterDomainName(SetupHooks.getPage(), domainName);
		}
	}

	@And("User selects {string} from the Data Classification dropdown")
	public void user_selects_from_the_data_classification_dropdown(String dataClassificationOptions) {
		String[] options = dataClassificationOptions.split(", ");
		for (String option : options) {
			EditMetadataPageUtils.selectDataClassificationOption(SetupHooks.getPage(), option);
		}
	}

	@And("User selects {string} from the Data Restrictions dropdown")
	public void user_selects_from_the_data_restrictions_dropdown(String dataRestictionOptions) {
		String[] options = dataRestictionOptions.split(", ");
		for (String option : options) {
			EditMetadataPageUtils.selectDataRestrictionsOption(SetupHooks.getPage(), option);
		}
	}

	@Then("User can see a edit success toast message as {string}")
	public void user_can_see_a_edit_success_toast_message_as(String expectedToastMessage) {
		String actualToastMessage = viewCatalogPage.verifyEditSuccessfullToastMessage();
		Assertions.assertEquals(actualToastMessage, expectedToastMessage);
		// viewCatalogPage.waitForEditSuccessToastMessageToDisappear();
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
		List<String> expectedTagList = Arrays.asList(tagArray);
		Assertions.assertEquals(expectedTagList, actualTagList);
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
		EditMetadataPageUtils.clickOnSubmit(SetupHooks.getPage());
	}

	@When("User clicks on Close button")
	public void user_clicks_on_close_button() {
		EditMetadataPageUtils.clickOnClose(SetupHooks.getPage());
	}

	// Edit SMSS

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

	// Create all Model Types
	@And("User enter Init Script as {string}")
	public void user_enter_init_script_as(String initScript) {
		openModelPage.enterInitScript(initScript);
	}

	@And("User enter GCP Region as {string}")
	public void user_enter_gcp_region_as(String gcpRegion) {
		openModelPage.enterGCPRegion(gcpRegion);
	}

	@And("User enter the Endpoint as {string}")
	public void user_enter_the_endpoint_as(String endpoint) {
		openModelPage.enterEndpoint(endpoint);
	}

	@Then("User can see following fields in SMSS Properties")
	public void user_can_see_following_fields_in_smss_properties(DataTable table) {
		String singleCell = table.cells().get(0).get(0);
		String[] fields = singleCell.split(", ");
		for (String field : fields) {
			if (!field.contains("=")) {
				continue;
			}
			String[] keyValue = field.split("=", 2);
			String fieldName = keyValue[0].trim();
			String expectedValue = keyValue[1].trim();
			String fullText = openModelPage.getAllFieldsInSMSSProperties(fieldName);
			if (fullText == null || fullText.trim().isEmpty()) {
				Assertions.fail("No text found for field: " + fieldName);
			}
			// Normalize spacing and remove non-breaking spaces
			fullText = fullText.replace("\u00A0", " ").trim().replaceAll("\\s+", " ");
			String actualValue;
			// Remove fieldName prefix if present
			if (fullText.toUpperCase().startsWith(fieldName.toUpperCase())) {
				int firstSpaceIndex = fullText.indexOf(' ');
				if (firstSpaceIndex != -1 && firstSpaceIndex + 1 < fullText.length()) {
					actualValue = fullText.substring(firstSpaceIndex + 1).trim();
				} else {
					actualValue = "";
				}
			} else {
				actualValue = fullText;
			}
			// For NAME field ignore trailing digits
			if (fieldName.equalsIgnoreCase("NAME")) {
				actualValue = actualValue.replaceAll("\\d+$", "");
			}
			// Field-specific validation logic
			switch (fieldName) {
			// case "ENDPOINT":
			// Assertions.assertEquals(expectedValue, fullText, "Field validation failed for
			// '" + fieldName + "'");
			// break;
			case "INIT_MODEL_ENGINE":
				Assertions.assertTrue(actualValue.contains(expectedValue), "Field validation failed for '" + fieldName
						+ "' ==> expected partial text: <" + expectedValue + "> but was: <" + actualValue + ">");
				break;
			default:
				Assertions.assertEquals(expectedValue, actualValue, "Field validation failed for '" + fieldName + "'");
				break;
			}
		}
	}

	@And("User enter the Deployment Name as {string}")
	public void user_enter_the_deployment_name_as(String deploymentName) {
		openModelPage.enterDeploymentName(deploymentName);
	}

	@And("User enter the tag as {string}")
	public void user_enter_the_tag_as(String tagName) {
		EditMetadataPageUtils.enterTagName(SetupHooks.getPage(), tagName);
	}

	@And("User enter the Version as {string}")
	public void user_enter_the_version_as(String version) {
		openModelPage.enterVersion(version);
	}

	@And("User select Chat Type as {string}")
	public void user_select_chat_type_as(String option) {
		openModelPage.selectChatOption(option);
	}

	@And("User select the Keep Conversation History as {string}")
	public void user_select_the_keep_conversation_history_as(String option) {
		openModelPage.selectKeepConversationHistoryOption(option);
	}

	@And("User select Record Questions and Responses as {string}")
	public void user_select_record_questions_and_responses_as(String option) {
		openModelPage.selectRecordQuestionsAndResponsesOption(option);
	}

	@And("User enter the Max Tokens as {string}")
	public void user_enter_the_max_tokens_as(String maxTokens) {
		openModelPage.enterMaxTokens(maxTokens);
	}

	@And("User enter the Max Input Tokens {string}")
	public void user_enter_the_max_input_tokens_as(String maxInputTokens) {
		openModelPage.enterMaxInputTokens(maxInputTokens);
	}

	@And("User select Type as {string}")
	public void user_select_type_as(String type) {
		openModelPage.selectTypeForModel(type);
	}

	@And("User enter model name as {string}")
	public void user_enter_model_name_as(String modelName) {
		openModelPage.enterModelName(modelName);
	}

	@And("User select the model as {string}")
	public void user_select_the_model_as(String model) {
		openModelPage.selectModelOption(model);
	}

	@And("User enter aws Region as {string}")
	public void user_enter_aws_region_as(String awsRegion) {
		openModelPage.enterAWSRegion(awsRegion);
	}

	@And("User enter AWS Access key as {string}")
	public void user_enter_aws_access_key_as(String awsAccessKey) {
		openModelPage.enterAWSAccessKey(awsAccessKey);
	}

	@And("User enter AWS Secret key as {string}")
	public void user_enter_aws_secreate_key_as(String awsSecreateKey) {
		openModelPage.enterAWSSecretKey(awsSecreateKey);
	}

	@And("User click on Create {string} button")
	public void user_click_on_create_button(String buttonName) {
		openModelPage.clickOnCreateButton(buttonName);
	}

	@And("User select the zip icon option to upload file for {string}")
	public void user_select_the_zip_icon_option_to_upload_file_for(String option) {
		openModelPage.selectAddModelOption(option);
	}

	@And("User click on Upload button for {string}")
	public void user_click_on_upload_button_for(String buttonName) {
		openModelPage.clickOnUploadButton(buttonName);
	}

	@When("User mouse hover on Lock icon displayed on catalog card")
	public void user_mouse_hover_on_lock_icon_displayed_on_catalog_card() {
		openModelPage.mouseHoverOnEngineAccessStatusIcon();
	}

	@Then("User can see engine access status as {string} on the tooltip")
	public void user_can_see_engine_access_status_as_on_the_tooltip(String expectedStatus) {
		String actualStatus = openModelPage.getEngineAccessStatusTooltipText(expectedStatus);
		Assertions.assertEquals(expectedStatus, actualStatus, "Incorrect status");
	}

	@When("User clicks on make {string} public button")
	public void user_clicks_on_make_public_button(String catalogName) {
		openModelPage.clickOnMakeCatalogPublicButton(catalogName);
	}

	@Then("User should see the catalog ID on the catalog card")
	public void User_should_see_the_catalog_ID_on_the_catalog_card() {
		boolean isIdVisible = openModelPage.validateIDisDisplayedOnCatalogCard();
		Assertions.assertTrue(isIdVisible, "Catalog ID is not visible on the catalog card");
	}

	@Then("User should see the tags {string} on the {string} catalog card")
	public void User_should_see_the_tags_on_the_catalog_card(String expectedTags, String catalogName) {
		String[] tagArray = expectedTags.split(", ");
		List<String> actualTagList = openModelPage.verifyTagNamesDisplayedOnCard(catalogName);
		List<String> expectedTagList = Arrays.asList(tagArray);
		Assertions.assertEquals(expectedTagList, actualTagList);
	}

	@Then("User should see the catalog created date on the catalog card")
	public void User_should_see_the_catalog_created_date_on_the_catalog_card() {
		boolean isCreatedDateVisible = openModelPage.isCreatedDateVisibleOnCard();
		Assertions.assertTrue(isCreatedDateVisible, "Catalog created date is not visible on the catalog card");
	}

	@Then("User should see the following icons on the catalog card")
	public void User_should_see_the_following_icons_on_the_catalog_card(DataTable dataTable) {
		List<String> icons = dataTable.asList(String.class);
		for (String icon : icons) {
			boolean isIconVisible = openModelPage.isIconVisibleOnCatalogCard(icon);
			Assertions.assertTrue(isIconVisible, "Icon '" + icon + "' is not visible on the catalog card");
		}
	}

	@When("User get the catalog ID")
	public void User_get_the_catalog_ID() {
		String id = openModelPage.getCatalogID();
		Assertions.assertNotNull(id, "Catalog ID should not be null");
	}

	@And("User clicks on {string} option from catalog card options")
	public void user_clicks_on_option_from_catalog_card_options(String option) {
		openModelPage.clickOnCatalogCardOption(option);
	}

	@And("User should see a delete confirmation pop-up with message {string}")
	public void user_should_see_a_delete_confirmation_pop_up_with_message(String expectedMessage) {
		String actualMessage = openModelPage.getDeleteConfirmationMessage();
		Assertions.assertEquals(expectedMessage, actualMessage, "Incorrect confirmation message");
	}

	@And("User should see the Engine name as {string} on the delete confirmation pop-up for {string} catalog")
	public void user_should_see_the_engine_name_as_on_the_delete_confirmation_pop_up(String expectedEngineName,
			String catalogType) {
		String actualEngineName = openModelPage.getDeleteConfirmationEngineName();
		boolean needsTimestamp;
		switch (catalogType.toLowerCase()) {
		case "database":
		case "function":
			needsTimestamp = false;
			break;
		default:
			needsTimestamp = true;
		}
		if (needsTimestamp) {
			Assertions.assertEquals(expectedEngineName + timestamp, actualEngineName, "Incorrect engine name");
		} else {
			Assertions.assertEquals(expectedEngineName, actualEngineName, "Incorrect engine name");
		}
	}

	@And("User should see the Engine ID on the delete confirmation pop-up")
	public void user_should_see_the_engine_id_on_the_delete_confirmation_pop_up() {
		Boolean isEngineIdVisible = openModelPage.isEngineIdVisibleOnDeleteConfirmation();
		Assertions.assertTrue(isEngineIdVisible, "Engine ID is not visible on the delete confirmation pop-up");
	}

	@And("User sees the {string} button on the delete confirmation pop-up")
	public void user_sees_the_button_on_the_delete_confirmation_pop_up(String expectedButton) {
		boolean isButtonVisible = openModelPage.isButtonVisibleOnDeleteConfirmation(expectedButton);
		Assertions.assertTrue(isButtonVisible,
				"Button '" + expectedButton + "' is not visible on the delete confirmation pop-up");
	}

	@And("User can see a toast message as {string} engine for {string} catalog")
	public void then_user_can_see_a_toast_message_as_catalog(String expectedToastMessage, String catalogType) {
		String actualMessage = openModelPage.verifyDeleteToastMessage();
		boolean needsTimestamp;
		switch (catalogType.toLowerCase()) {
		case "database":
		case "function":
			needsTimestamp = false;
			break;
		default:
			needsTimestamp = true;
		}
		if (needsTimestamp) {
			Assertions.assertEquals(expectedToastMessage + timestamp, actualMessage, "Delete message doesn't match");
		} else {
			Assertions.assertEquals(expectedToastMessage, actualMessage, "Delete message doesn't match");
		}
	}

	@Then("User should see {string} on Pending Requests section")
	public void User_should_see_on_Pending_Requests_section(String expectedText) {
		String actualCountWithText = openModelPage.getpendingRequestCountText();
		Assertions.assertEquals(expectedText, actualCountWithText, "Pending request text not correct");
	}

	@When("User clicks on the pending request expand button")
	public void User_clicks_on_the_pending_request_expand_button() {
		openModelPage.clickOnPendingRequestsExpandButton();
	}

	@When("User clicks on {string} option in the Actions column")
	public void User_clicks_on_option_in_the_Actions_column(String action) {
		openModelPage.performActionOnPendingRequest(action);
	}

	@Then("User should see the {string} user in the Members list with {string} permission")
	public void User_should_see_the_user_in_the_Members_list_with_permission(String role, String permissionGranted) {
		boolean isUserDisplayed = openModelPage.isUserDisplayedInListAfterRequestAction(role, permissionGranted,
				GenericSetupUtils.useDocker());
		Assertions.assertTrue(isUserDisplayed,
				"User is not displayed in the members list with the provided permission");
	}

	@Then("User should not see the {string} user in the Members list with {string} permission")
	public void User_should_not_see_the_user_in_the_Members_list_with_permission(String role,
			String permissionGranted) {
		boolean isUserDisplayed = openModelPage.isUserDisplayedInListAfterRequestAction(role, permissionGranted,
				GenericSetupUtils.useDocker());
		Assertions.assertFalse(isUserDisplayed, "User is displayed in the members list with the requested permission");
	}

	@When("User change the requested access role to {string} role")
	public void User_change_the_requested_access_role_to_role(String newRole) {
		openModelPage.changeRequestedAccessRole(newRole);
	}

	@And("User clicks on Generate MCP button")
	public void user_clicks_on_generate_mcp_button() {
		openModelPage.clickOnGenerateMCPButtonFromMCPUsageTab();
	}

	@And("User can see the Avialble Tools as {string}")
	public void user_can_see_the_available_tools_as(String expectedTool) {
		boolean isToolPresent = openModelPage.verifyToolsInGeneratedMCP(expectedTool);
		Assertions.assertTrue(isToolPresent, "Tool are not displayed as expected in generated MCP");
	}

	@And("User can see the below Input parameters under the View parameter")
	public void user_can_see_the_below_input_parameters_under_the_view_parameter(String toolName, DataTable dataTable) {
		String param = dataTable.cell(0, 0);
		List<String> expectedParameters = Arrays.asList(param.split(", "));
		boolean isInputParameterPresent = openModelPage.verifyInputParameters(toolName, expectedParameters);
		Assertions.assertTrue(isInputParameterPresent,
				"Input parameters are not displayed as expected in generated MCP ");
	}

}