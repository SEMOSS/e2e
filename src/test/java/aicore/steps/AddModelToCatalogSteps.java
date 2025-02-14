package aicore.steps;

import org.junit.jupiter.api.Assertions;

import aicore.base.AICoreTestBase;
import aicore.pages.AddModelToCatalogPage;
import aicore.pages.HomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddModelToCatalogSteps {
	private HomePage homePage;
	private AddModelToCatalogPage openModelPage;

	public AddModelToCatalogSteps() {
		this.homePage = new HomePage(AICoreTestBase.page);
		this.openModelPage = new AddModelToCatalogPage(AICoreTestBase.page);
	}

	@Given("User navigated to Open Model")
	public void user_navigate_to_open_model() {
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

	@And("User can see toast message as {string}")
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
		openModelPage.smssTab();
	}

	@Then("User can see NAME as {string} in SMSS properties")
	public void user_can_see_name_as_in_smss_properties(String modelTitle) {
		String fullText = openModelPage.verifyNameInSMSS();
		if (fullText != null && !fullText.isEmpty()) {
			fullText = fullText.replaceAll("\\u00A0", " ");
			// Now split the text on "NAME" and get the second part (after NAME)
			if (fullText.contains("NAME")) {
				String[] parts = fullText.split("NAME\\s+");
				if (parts.length > 1) {
					String actualName = parts[1].trim();
					String expectedNameProperties = openModelPage.getExpectedCatalogTitle(modelTitle);
					Assertions.assertEquals(actualName, expectedNameProperties);
				}
			}
		}
	}

	@Then("User can see VAR_NAME as {string} in SMSS properties")
	public void user_can_see_var_name_as_in_smss_properties(String variableName) {
		String fullText = openModelPage.verifyVarNameInSMSS();
		if (fullText != null && !fullText.isEmpty()) {
			fullText = fullText.replaceAll("\\u00A0", " ");
			// Now split the text on "NAME" and get the second part (after NAME)
			if (fullText.contains("VAR_NAME")) {
				String[] parts = fullText.split("VAR_NAME\\s+");
				if (parts.length > 1) {
					String actualName = parts[1].trim();
					Assertions.assertEquals(actualName, variableName);
				}
			}
		}
	}

}
