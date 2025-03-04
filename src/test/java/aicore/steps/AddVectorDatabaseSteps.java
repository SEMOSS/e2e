package aicore.steps;

import org.junit.jupiter.api.Assertions;

import aicore.base.AICoreTestManager;
import aicore.pages.HomePage;
import aicore.pages.OpenVectorPage;
import aicore.utils.CommonUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddVectorDatabaseSteps {

	private HomePage homePage;
	private OpenVectorPage vectorPage;
	private String timestamp;

	public AddVectorDatabaseSteps() {
		homePage = new HomePage(AICoreTestManager.getPage());
		timestamp = AddModelSteps.timestamp;
		vectorPage = new OpenVectorPage(AICoreTestManager.getPage(), AddModelSteps.timestamp);
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
		vectorPage.enterVectorCatalogName(catalogName);
	}

	@And("User selects {string} from Embedder field")
	public void user_selects_from_embedder_field(String modelName) {
		vectorPage.selectModelfromEmbedderDropdown(modelName);
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

	@And("User clicks on Create Vector button")
	public void user_clicks_on_create_vector_button() {
		vectorPage.clickOnCreateVectorButton();
	}

	@Then("User can see vector database created success toast message as {string}")
	public void user_can_see_vector_database_created_success_toast_message_as(String expectedToastMessage) {
		String actualToastMessage = vectorPage.verifyVectorCreatedToastMessage();
		Assertions.assertEquals(actualToastMessage, expectedToastMessage, "Toast message is incorrect");
		vectorPage.waitForVectorToastMessageToDisappear();
	}

	@Then("User can see the Vector title as {string}")
	public void user_can_see_the_vector_title_as(String VectorTitle) {
		String actualVectorTitle = vectorPage.verifyVectorTitle();
		System.out.println("act Title is : " + actualVectorTitle);
		String expectedVectorTitle = VectorTitle + " " + timestamp;
		Assertions.assertEquals(actualVectorTitle, expectedVectorTitle, "Vector Title is not matching with expected");
	}

	@Then("User can see vector catalog name in {string} field as {string} in SMSS properties")
	public void user_can_see_vector_catalog_name_in_field_as_in_smss_properties(String field, String name) {
		String fullText = vectorPage.verifyNameFiledInSMSS();
		String actualName = CommonUtils.splitTrimValue(fullText, field);
		String expectedName = name + " " + timestamp;
		Assertions.assertEquals(actualName, expectedName, "Name is not matching");
	}

	@And("User can see embedder engine name in {string} field as {string} in SMSS properties")
	public void user_can_see_embedder_engine_name_in_field_as_in_smss_properties(String field,
			String embedderEngineName) {
		String fullText = vectorPage.verifyEmbedderEngineNameInSMSS();
		String actualEmbedderEngineName = CommonUtils.splitTrimValue(fullText, field);
		String expectedEmbedderEngineName = embedderEngineName + timestamp;
		Assertions.assertEquals(actualEmbedderEngineName, expectedEmbedderEngineName,
				"Embedder Engine Name is not matching");
	}

	@And("User can see content length in {string} field as {string} in SMSS properties")
	public void user_can_see_content_length_in_field_as_in_smss_properties(String field, String expectedContentLength) {
		String fullText = vectorPage.verifyContentLengthInSMSS();
		String actualContentLength = CommonUtils.splitTrimValue(fullText, field);
		Assertions.assertEquals(actualContentLength, expectedContentLength, "Content length is not matching");
	}

	@And("User can see content overlap in {string} field as {string} in SMSS properties")
	public void user_can_see_content_overlap_in_field_as_in_smss_properties(String field,
			String expectedContentOverlapValue) {
		String fullText = vectorPage.verifyContentOverlapInSMSS();
		String actualContentOverlapValue = CommonUtils.splitTrimValue(fullText, field);
		Assertions.assertEquals(actualContentOverlapValue, expectedContentOverlapValue,
				"Content overlap value is not matching");
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
		Assertions.assertEquals(actualChunkingStrategy, expectedChunkingStrategy, "Chunking strategy is not matching");
	}
}
