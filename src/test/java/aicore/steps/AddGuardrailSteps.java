package aicore.steps;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Assertions;

import aicore.hooks.SetupHooks;
import aicore.pages.GuardrailPage;
import aicore.pages.HomePage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddGuardrailSteps {
	private HomePage homePage;
	private GuardrailPage guardrailPage;
	protected static String timestamp;
	private Path downloadedZip;

	public AddGuardrailSteps() {
		timestamp = SetupHooks.getTimestamp();
		this.guardrailPage = new GuardrailPage(SetupHooks.getPage());
		this.homePage = new HomePage(SetupHooks.getPage());
	}

	@When("User clicks on Guardrail")
	public void user_clicks_on_guardrail() {
		homePage.clickOnGuardrail();
	}

	@When("User clicks on Add Guardrail button")
	public void user_clicks_on_add_guardrail() {
		guardrailPage.clickOnAddGuardrailButton();
	}

	@When("User enters guardrail Catalog Name as {string}")
	public void user_enters_guardrail_catalog_name_as(String catalogName) {
		guardrailPage.enterCatalogName(catalogName + timestamp);
	}

	@When("User enters NER Labels as {string} and presses Enter")
	public void user_enters_ner_labels_as_and_presses_Enter(String label) {
		guardrailPage.enterNerLabels(label);
	}

	@When("User enters Default Threshold as {string}")
	public void user_enters_default_threshold_as(String threshold) {
		guardrailPage.enterDefaultThreshold(threshold);
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

}
