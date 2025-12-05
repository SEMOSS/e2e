package aicore.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;

import aicore.hooks.SetupHooks;
import aicore.pages.CatalogPage;
import aicore.pages.ChangeAccessPopUpPage;
import aicore.pages.EmbedDocumentPage;
import aicore.pages.HomePage;
import aicore.pages.OpenVectorPage;
import aicore.pages.ViewUsagePage;
import aicore.utils.CommonUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddVectorDatabaseSteps extends AbstractAddCatalogBase {

	private HomePage homePage;
	private OpenVectorPage vectorPage;
	protected static String timestamp;
	private EmbedDocumentPage embedDocumentPage;
	private ViewUsagePage viewUsagePage;
	private ChangeAccessPopUpPage chnageAccessPopUpPage;
	private CatalogPage catalogPage;

	public AddVectorDatabaseSteps() {
		homePage = new HomePage(SetupHooks.getPage());
		timestamp = AddModelSteps.timestamp;
		vectorPage = new OpenVectorPage(SetupHooks.getPage(), timestamp);
		embedDocumentPage = new EmbedDocumentPage(SetupHooks.getPage());
		viewUsagePage = new ViewUsagePage(SetupHooks.getPage());
		chnageAccessPopUpPage = new ChangeAccessPopUpPage(SetupHooks.getPage());
		catalogPage = new CatalogPage(SetupHooks.getPage());
	}

	@Given("User clicks on Open Vector")
	public void user_clicks_on_open_vector() {
		homePage.clickOnOpenVector();
	}

	@When("User clicks on Add Vector button")
	public void user_clicks_on_add_vector_button() {
		vectorPage.clickOnAddVectorButton();
	}

	@And("User selects {string} connection")
	public void user_selects_connection(String connectionName) {
		vectorPage.selectConnections(connectionName);
	}

	@And("User enters vector database Catalog name as {string}")
	public void user_enters_vector_database_catalog_name_as(String catalogName) {
		vectorPage.enterVectorCatalogName(catalogName + timestamp);
	}

	@And("User selects {string} from Embedder field")
	public void user_selects_from_embedder_field(String modelName) {
//		if (modelName.equals("TextEmbeddings BAAI-Large-En-V1.5")) {
//			vectorPage.selectModelfromEmbedderDropdown(modelName);
//		} else {
//			vectorPage.selectModelfromEmbedderDropdown(modelName + AddModelSteps.timestamp);
//		}
		switch (modelName) {
		case "TextEmbeddings BAAI-Large-En-V1.5":
		case "Model":
			vectorPage.selectModelfromEmbedderDropdown(modelName);
			break;
		default:
			vectorPage.selectModelfromEmbedderDropdown(modelName + AddModelSteps.timestamp);
		}
	}

	@And("User selects {string} from Chunking Strategy field")
	public void user_selects_from_chunking_strategy_field(String strategyName) {
		vectorPage.selectStrategyfromChunkingStrategyDropdown(strategyName);
	}

	@And("User enters value of Content Length as {string}")
	public void user_enters_value_of_content_length_as(String contentLength) {
		vectorPage.enterContentLength(contentLength);
	}

	@And("User enters value of Content Overlap as {string}")
	public void user_enters_value_of_content_overlap_as(String contentOverlap) {
		vectorPage.enterContentOverlap(contentOverlap);
	}

	@And("User enters value of Host Name")
	public void User_enters_value_of_host_name() {
		vectorPage.enterHostName();

	}

	@And("User enters value of API Key")
	public void sUer_enters_value_of_api_key() {
		vectorPage.enterApiKey();

	}

	@And("User enters value of Namespace as {string}")
	public void User_enters_value_of_namespace_as_(String nameSpace) {
		vectorPage.enterNameSpace(nameSpace);

	}

	@And("User clicks on Create Vector button")
	public void user_clicks_on_create_vector_button() {
		vectorPage.clickOnCreateVectorButton();
	}

	@Then("User can see vector database created success toast message as {string}")
	public void User_can_see_vector_database_created_success_toast_message_as(String expectedToastMessage) {
		String actualMessage = vectorPage.verifyToastMessage();
		Assertions.assertEquals(expectedToastMessage, actualMessage, "catalog is not created successfully");
	}

	@Then("User can see the Vector title as {string}")
	public void user_can_see_the_vector_title_as(String vectorTitle) {
		vectorPage.verifyVectorTitle(vectorTitle + timestamp);

	}

	@Then("User can see vector catalog name in {string} field as {string} in SMSS properties")
	public void user_can_see_vector_catalog_name_in_field_as_in_smss_properties(String field, String name) {
		String fullText = vectorPage.verifyNameFiledInSMSS();
		String actualName = CommonUtils.splitTrimValue(fullText, field);
		String expectedName = name + timestamp;
		assertEquals(actualName, expectedName, "Name is not matching");
	}

	@And("User can see embedder engine name in {string} field as {string} in SMSS properties")
	public void user_can_see_embedder_engine_name_in_field_as_in_smss_properties(String field,
			String embedderEngineName) {
		String fullText = vectorPage.verifyEmbedderEngineNameInSMSS();
		String actualEmbedderEngineName = CommonUtils.splitTrimValue(fullText, field);
		String expectedEmbedderEngineName = embedderEngineName + AddModelSteps.timestamp;
		assertEquals(actualEmbedderEngineName, expectedEmbedderEngineName, "Embedder Engine Name is not matching");
	}

	@And("User can see content length in {string} field as {string} in SMSS properties")
	public void user_can_see_content_length_in_field_as_in_smss_properties(String field, String expectedContentLength) {
		String fullText = vectorPage.verifyContentLengthInSMSS();
		String actualContentLength = CommonUtils.splitTrimValue(fullText, field);
		assertEquals(actualContentLength, expectedContentLength, "Content length is not matching");
	}

	@And("User can see content overlap in {string} field as {string} in SMSS properties")
	public void user_can_see_content_overlap_in_field_as_in_smss_properties(String field,
			String expectedContentOverlapValue) {
		String fullText = vectorPage.verifyContentOverlapInSMSS();
		String actualContentOverlapValue = CommonUtils.splitTrimValue(fullText, field);
		assertEquals(actualContentOverlapValue, expectedContentOverlapValue, "Content overlap value is not matching");
	}

	@And("User can see chunking strategy in {string} field as {string} in SMSS properties")
	public void user_can_see_chunking_strategy_in_field_as_in_smss_properties(String field, String chunkingStrategy) {
		String fullText = vectorPage.verifyChunkingStrategyInSMSS();
		String actualChunkingStrategy = CommonUtils.splitTrimValue(fullText, field);

		String expectedChunkingStrategy = null;

		switch (chunkingStrategy) {
		case "Token":
			expectedChunkingStrategy = "ALL";
			break;
		default:
			expectedChunkingStrategy = chunkingStrategy.trim().toUpperCase();
			expectedChunkingStrategy = expectedChunkingStrategy.replace(" ", "_");
			break;
		}
		assertEquals(actualChunkingStrategy, expectedChunkingStrategy, "Chunking strategy is not matching");
	}

	@Then("User clicks on the created Vector card name as {string}")
	public void user_clicks_on_the_created_vector_card_name_as(String catalogName) {
		vectorPage.addedVectorCard(catalogName + timestamp);
	}

	@Then("User clicks on Access Control")
	public void user_clicks_on_access_control() {
		vectorPage.clickOnAccessControl();
	}

	@Then("User clicks on delete icon")
	public void user_clicks_on_delete_icon() {
		vectorPage.clickOnDeleteButton();
		vectorPage.confirmationPopUp();
	}

	@Then("User sees deleted Vector success toast message {string}")
	public void user_sees_deleted_Vector_success_toast_message(String toastMessage) {
		String actualMessage = vectorPage.verifyToastMessage();
		Assertions.assertEquals(toastMessage, actualMessage, "catalog is deleted successfully");
	}

	@And("User clicks on Usage tab for Vector DB")
	public void user_clicks_on_usage() {
		viewUsagePage.clickOnUsageTab();
	}

	@Then("User sees an example of {string} with example code for Vector DB")
	public void user_sees_an_example_of_with_example_code(String example) {
		viewUsagePage.verifyExample(example);
	}

	@Then("User click on the Change Access button")
	public void user_click_on_the_change_access_button() {
		embedDocumentPage.clickOnAccessControlButton();
	}

	@Then("User should see the {string} popup with following options:")
	public void user_should_see_the_popup_with_following_options(String expectedTitle,
			io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
		Assertions.assertTrue(chnageAccessPopUpPage.isPopupVisible(), expectedTitle + " popup is not visible");
		for (String option : dataTable.asList()) {
			Assertions.assertTrue(chnageAccessPopUpPage.isOptionVisible(option),
					option + " is not visible in Change Access popup");
		}
	}

	@Then("User selects {string} access")
	public void user_selects_access(String accessType) {
		chnageAccessPopUpPage.selectAccessType(accessType);
	}

	@Then("User types a comment as {string}")
	public void user_types_a_comment_as(String comment) {
		chnageAccessPopUpPage.enterComment(comment);
	}

	@Then("User clicks on Request button")
	public void user_clicks_on_request_button() {
		chnageAccessPopUpPage.clickOnRequestButton();
	}

	@Then("User should successfully request access given the Vector is requestable with a toast message as {string}")
	public void user_should_successfully_request_access_given_the_vector_is_requestable_with_a_toast_message_as(
			String expectedMessage) {
		boolean toastVisible = chnageAccessPopUpPage.isRequestSuccessToastVisible();
		Assertions.assertTrue(toastVisible, "Expected toast message to be visible: " + expectedMessage);
	}

	@And("User clicks on Discoverable Vectors button")
	public void user_clicks_on_discoverable_vectors_button() {
		vectorPage.clickOnDiscoverableVectorsButton();
	}

	@Then("User searches the {string} in the Vector Catalog searchbox")
	public void user_searches_the_in_the_vector_catalog_searchbox(String catalogName) {
		catalogPage.searchCatalog(catalogName + timestamp);
	}

	@Then("User selects the {string} from the Vector catalog")
	public void user_selects_the_from_the_vector_catalog(String catalogName) {
		catalogPage.selectCatalogFromSearchOptions(catalogName + timestamp);
	}

	@Then("User should see Search bar to filter vector options")
	public void user_should_see_search_bar_to_filter_vector_options() {
		validateSearchBar(vectorPage);
	}

	@And("User should see the following vector options with icons on the page")
	public void user_should_see_the_following_vector_options_with_icons_on_the_page(DataTable dataTable) {
		final String GROUP_NAME = "GROUP";
		final String VECTOR_OPTION_NAMES = "VECTOR_OPTIONS";
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
		validateOptionsWithIcon(GROUP_NAME, VECTOR_OPTION_NAMES, rows, vectorPage);
	}

	@Then("User sees and copies the vector id")
	public void user_copies_the_vector_id() {
		vectorPage.verifyCurrentUrlContainsVectorId();
		vectorPage.copyVectorId();
	}

	@And("User sees a description for this Vector")
	public void user_sees_a_description_for_this_vector() {
		vectorPage.verifyVectorDescription();
	}

	@And("User sees Tags {string} that have been added to the Vector")
	public void user_sees_tags_that_have_been_added_to_the_vector(String tagName) {
		vectorPage.verifyVectorTags(tagName);
	}

	@Then("User sees Updated By as {string} and Updated At as current date")
	public void User_sees_Updated_By_as_and_Updated_At_as_current_date(String role) {
		vectorPage.verifyUpdatedBy(role);
		vectorPage.verifyUpdatedAt();
	}

	@And("User sees the Change Access button")
	public void user_sees_the_change_access_button() {
		vectorPage.verifyChangeAccessButton();
	}

	@Then("User click on the Request Access button")
	public void user_click_on_the_request_access_button() {
		embedDocumentPage.clickOnRequestAccessButton();
	}
}
