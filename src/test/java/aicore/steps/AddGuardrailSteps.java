package aicore.steps;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Assertions;

import aicore.hooks.SetupHooks;
import aicore.pages.GuardrailPage;
import aicore.pages.guardrail.AddGuardrailFormUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.utils.GuardrailPageUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddGuardrailSteps {
	private GuardrailPage guardrailPage;
	protected static String timestamp;
	private Path downloadedZip;

	public AddGuardrailSteps() {
		timestamp = SetupHooks.getTimestamp();
		this.guardrailPage = new GuardrailPage(SetupHooks.getPage());
	}

	@When("User clicks on Guardrail")
	public void user_clicks_on_guardrail() {
		MainMenuUtils.clickOnGuardrail(SetupHooks.getPage());
	}

	@When("User clicks on Add Guardrail button")
	public void user_clicks_on_add_guardrail() {
		GuardrailPageUtils.clickOnAddGuardrailButton(SetupHooks.getPage());
	}

	@When("User enters guardrail Catalog Name as {string}")
	public void user_enters_guardrail_catalog_name_as(String catalogName) {
		guardrailPage.enterCatalogName(catalogName + timestamp);
	}

	@Given("User created {string} guardrail with the {string} guardrail, catalog name {string}, NER Labels {string}, Default Threshold {string}")
	public void user_created_models_with_the_catalog_name_open_ai_key_and_variable_name(String guardrailCount, String guardrailType,
			 String catalogName, String nerLabels, String defaultThreshold) {
		for (int i = 1; i <= Integer.parseInt(guardrailCount); i++) {
			String catalogNameWithTimestamp = catalogName + System.currentTimeMillis();
			guardrailPage.createGuardrail(guardrailType, catalogNameWithTimestamp, nerLabels, defaultThreshold);
		}
	}

	@When("User enters NER Labels as {string} and presses Enter")
	public void user_enters_ner_labels_as_and_presses_Enter(String label) {
		AddGuardrailFormUtils.enterNerLabels(SetupHooks.getPage(), label);
	}

	@When("User enters Default Threshold as {string}")
	public void user_enters_default_threshold_as(String threshold) {
		AddGuardrailFormUtils.enterDefaultThreshold(SetupHooks.getPage(), threshold);
	}

	@Then("User can see the Guardrail Catalog title as {string}")
	public void user_can_see_the_guardrail_catalog_title_as(String guardrailTitle) {
		String actualGuardrailTitle = guardrailPage.verifyGuardrailTitle(guardrailTitle);
		String expGuardrailTitle = guardrailTitle + timestamp;
		Assertions.assertEquals(expGuardrailTitle, actualGuardrailTitle, "Guardrail title does not match");
	}

	@When("User clicks on Export button")
	public void user_clicks_on_export_button() {
		downloadedZip = guardrailPage.downloadCatalog();
	}

	@Then("User sees catalog zip file downloaded")
	public void user_sees_catalog_zip_file_downloaded() {
		Assertions.assertNotNull(downloadedZip, "file download path is null");
		Assertions.assertTrue(Files.exists(downloadedZip), "file was not downloaded");
		Assertions.assertTrue(downloadedZip.toString().endsWith(".zip"), "Downloaded file is not a ZIP");
	}

	@Then("User sees downloaded zip file name contains {string}")
	public void user_sees_downloaded_zip_file_name_contains(String expectedText) {
		String fileName = downloadedZip.getFileName().toString();
		Assertions.assertTrue(fileName.toLowerCase().contains(expectedText.toLowerCase()),
				"File name does not contain expected text");
	}

	@Then("User sees export success toast message as {string}")
	public void user_sees_export_success_toast_message_as(String expectedToastMessage) {
		String actualToastMessage = guardrailPage.verifyToastMessage(expectedToastMessage);
		Assertions.assertEquals(actualToastMessage, expectedToastMessage,
				"toast message is not matching with expected");
	}

	@Then("User enters {string} in the search box")
	public void user_enters_in_the_search_box(String searchText) {
		guardrailPage.searchGuardrailCatalog(searchText + timestamp);
	}

	@Then("User should see the Guardrail Catalog title as {string} in search results")
	public void user_should_see_the_guardrail_catalog_title_as_in_search_results(String guardrailTitle) {
		boolean isGuardrailVisible = guardrailPage.verifySearchedGuardrailCatalogTitle(guardrailTitle + timestamp);
		Assertions.assertTrue(isGuardrailVisible, "Guardrail title is not visible in search results");
	}

	@Then("User searches the {string} in the guardrail Catalog searchbox")
	public void user_searches_the_in_the_guardrail_Catalog_searchbox(String catalogName) {
		guardrailPage.searchGuardrailCatalog(catalogName + timestamp);
	}

	@Then("User selects the {string} from the guardrail catalog")
	public void user_selects_the_from_the_guardrail_catalog(String guardrailTitle) {
		guardrailPage.selectTheGuardrailCatalog(guardrailTitle + timestamp);
	}

}