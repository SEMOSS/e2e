package aicore.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import aicore.hooks.SetupHooks;
import aicore.pages.EmbedDocumentPage;
import aicore.pages.HomePage;
import aicore.pages.OpenVectorPage;
import aicore.pages.ViewUsagePage;
import aicore.utils.CommonUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddVectorDatabaseSteps {

	private HomePage homePage;
	private OpenVectorPage vectorPage;
	private String timestamp;
	private EmbedDocumentPage embedDocumentPage;
	private ViewUsagePage viewUsagePage;

	public AddVectorDatabaseSteps() {
		homePage = new HomePage(SetupHooks.getPage());
		timestamp = AddModelSteps.timestamp.substring(0, 5);
		vectorPage = new OpenVectorPage(SetupHooks.getPage(), timestamp);
		embedDocumentPage = new EmbedDocumentPage(SetupHooks.getPage());
		viewUsagePage = new ViewUsagePage(SetupHooks.getPage());
	}

	@Given("User clicks on Open Vector engine")
	public void user_clicks_on_open_vector_engine() {
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
		if (modelName.equals("TextEmbeddings BAAI-Large-En-V1.5")) {
			vectorPage.selectModelfromEmbedderDropdown(modelName);
		} else {
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
		vectorPage.verifyToastMessage(expectedToastMessage);
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
		System.out.println(expectedName);
		System.out.println(actualName);

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
		vectorPage.verifyToastMessage(toastMessage);
	}

	@And("User clicks on Usage tab for Vector DB")
	public void user_clicks_on_usage() {
		viewUsagePage.clickOnUsageTab();
	}

	@Then("User sees an example of {string} with example code for Vector DB")
	public void user_sees_an_example_of_with_example_code(String example) {
		viewUsagePage.verifyExample(example);
	}

}
