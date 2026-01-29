package aicore.steps;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;

import aicore.hooks.SetupHooks;
import aicore.pages.AddFunctionToCatalogPage;
import aicore.pages.HomePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddFunctionSteps {
	private HomePage homePage;
	private String timestamp;
	private AddFunctionToCatalogPage addFunctionToCatalogPage;

	public AddFunctionSteps() {
		homePage = new HomePage(SetupHooks.getPage());
		timestamp = SetupHooks.getTimestamp();
		addFunctionToCatalogPage = new AddFunctionToCatalogPage(SetupHooks.getPage(), timestamp);
	}

	@Given("User clicks on Open Function")
	public void user_navigates_to_open_function() {
		homePage.clickOnOpenFunction();
	}

	@When("User clicks on Add Function")
	public void user_clicks_on_add_function() {
		addFunctionToCatalogPage.clickOnAddFunctionButton();
	}

	@Then("User selects function {string}")
	public void user_selects_function(String functionType) {
		addFunctionToCatalogPage.selectFunction(functionType);
	}

	@Then("User can see function creation form with following sections with fields:")
	public void user_can_see_function_creation_form_with_following_sections_with_fields(DataTable dataTable) {
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> row : rows) {
			String sectionName = row.get("SECTION_NAME");
			String fieldsValue = row.get("FIELDS");
			if (sectionName == null || sectionName.trim().isEmpty() || fieldsValue == null
					|| fieldsValue.trim().isEmpty()) {
				continue;
			}
			String[] fields = fieldsValue.split(", ");
			for (String field : fields) {
				boolean isFieldVisible = addFunctionToCatalogPage.fieldUnderSection(sectionName, field);
				Assertions.assertTrue(isFieldVisible, field + " is not visible under " + sectionName + " section");
			}
		}
	}

	@Then("User can see function creation form with following mandatory fields")
	public void user_can_see_function_creation_form_with_following_mandatory_fields(DataTable dataTable) {
		String singleCell = dataTable.cells().get(0).get(0);
		String[] fields = singleCell.split(", ");
		for (String field : fields) {
			boolean isFieldMandatory = addFunctionToCatalogPage.isFieldMandatory(field);
			Assertions.assertTrue(isFieldMandatory, field + " is not mandatory field");
		}
	}

	@When("User fills the function creation form with:")
	public void user_fills_the_function_creation_form_with(DataTable dataTable) {
		String singleCell = dataTable.cells().get(0).get(0);
		String[] fields = singleCell.split(", ");
		for (String field : fields) {
			if (!field.contains("=")) {
				continue;
			}
			String[] keyValue = field.split("=", 2);
			String fieldName = keyValue[0].trim();
			String fieldValue = keyValue[1].trim();
			addFunctionToCatalogPage.fillModelCreationForm(fieldName, fieldValue);
		}
	}

	@Then("User can see {string} button becomes enabled to create function")
	public void user_can_see_button_becomes_enabled_to_create_function(String string) {
		boolean isButtonEnabled = addFunctionToCatalogPage.validateConnectButtonEnabled();
		Assertions.assertTrue(isButtonEnabled, "'Connect' button is not enabled");
	}

	@When("User clicks on Connect button to create function")
	public void user_clicks_on_connect_button_to_create_function() {
		addFunctionToCatalogPage.clickOnConnectButton();
	}

	@And("User sees astrisk mark on the required fields {string}")
	public void user_sees_astrisk_mark_on_the_required_fields(String requiredFields) {
		String[] fields = requiredFields.split(",");
		for (String field : fields) {
			boolean isFieldMandatory = addFunctionToCatalogPage.isFieldMandatory(field);
			Assertions.assertTrue(isFieldMandatory, field + " is not mandatory field");
		}
	}

	@And("User enters Catalog name {string}")
	public void user_enters_catalog_name(String catalogName) {
		addFunctionToCatalogPage.enterCatalogName(catalogName);
	}

	@And("User enters Url as {string}")
	public void user_enters_url_as(String url) {
		addFunctionToCatalogPage.enterUrl(url);
	}

	@And("User selects HTTP method as {string}")
	public void user_selects_http_method_as(String httpMethod) {
		addFunctionToCatalogPage.selectHttpMethod(httpMethod);
	}

	@And("User selects Post body message as {string}")
	public void user_selects_post_body_message_as(String postBodyMessage) {
		addFunctionToCatalogPage.selectPostBodyMessage(postBodyMessage);
	}

	@And("User enters Headers as {string}")
	public void user_enters_headers_as(String headers) {
		addFunctionToCatalogPage.enterHeaders(headers);
	}

	@And("User enters Function parameters as {string}")
	public void user_enters_function_parameters_as(String functionParameters) {
		addFunctionToCatalogPage.enterFunctionParameters(functionParameters);
	}

	@And("User enters Function required parameters as {string}")
	public void user_enters_function_required_parameters_as(String functionRequiredParameters) {
		addFunctionToCatalogPage.enterFunctionRequiredParameters(functionRequiredParameters);
	}

	@And("User enters Function name as {string}")
	public void user_enters_function_name_as(String functionName) {
		addFunctionToCatalogPage.enterFunctionName(functionName);
	}

	@And("User enters Function description as {string}")
	public void user_enters_function_description_as(String functionDescription) {
		addFunctionToCatalogPage.enterFunctionDescription(functionDescription);
	}

	@Then("User sees the Create function button is disabled")
	public void user_sees_the_create_function_button_disabled() {
		boolean isButtonDisabled = addFunctionToCatalogPage.verifyCreateFunctionButtonDisabled();
		Assertions.assertTrue(isButtonDisabled, "Create Function button is not disabled");
	}

	@Then("User sees Connect button")
	public void user_sees_connect_button() {
		addFunctionToCatalogPage.checkCreateFunctionButton();
	}

	@Then("User sees success toast message {string}")
	public void user_sees_success_toast_message(String toastMessage) {
		String actualMessage = addFunctionToCatalogPage.verifySuccessToastMessage();
		Assertions.assertEquals(toastMessage, actualMessage, "Toaster is not matching with expected");
		addFunctionToCatalogPage.closeToastMessage();
	}

	@Then("User sees the function name {string} in the function catalog")
	public void user_sees_the_function_name_in_the_catalog(String functionName) {
		String expectedFunctionName = addFunctionToCatalogPage.verifyFunctionNameInCatalog(functionName);
		functionName = functionName.replace("{Timestamp}", " " + timestamp);
		Assertions.assertEquals(functionName, expectedFunctionName, "Function is not present in the function catalog");
	}

	@Then("User clicks on the function name {string} in the function catalog")
	public void user_clicks_on_the_function_name_in_the_function_catalog(String functionName) {
		functionName = functionName.replace("{Timestamp}", " " + timestamp);
		addFunctionToCatalogPage.clickOnFunctionNameInCatalog(functionName);
	}

	@Then("User clicks on Access Control Tab")
	public void user_clicks_on_access_control_tab() {
		addFunctionToCatalogPage.clickOnAccessControl();
	}

	@Then("User clicks on Delete button")
	public void user_clicks_on_delete_button() {
		addFunctionToCatalogPage.clickOnDeleteButton();
		addFunctionToCatalogPage.clickOnDeleteConfirmationButton();
	}

	@Then("User sees deleted function success toast message {string}")
	public void user_sees_deleted_function_success_toast_message(String toastMessage) {
		String expectedMessage = addFunctionToCatalogPage.verifyDeleteToastMessage();
		String actualMessage = toastMessage;
		Assertions.assertEquals(actualMessage, expectedMessage, "Delete Message is not matching with expected");
	}

	@Then("User redirects to the missing input field")
	public void user_redirects_to_the_missing_input_field() {
		boolean missingFieldFlag = addFunctionToCatalogPage.verifyMissingInputField();
		Assertions.assertTrue(missingFieldFlag, "missing input field is not highlighted/redirected");
	}
//	@When("User clicks Make Discoverable button")
//	public void user_clicks_make_discoverable_button() {
//		addFunctionToCatalogPage.clickOnMakeDiscoverableButton();
//	}

	@When("User clicks Make {string} Discoverable button")
	public void user_clicks_make_discoverable_button(String catalogName) {
		addFunctionToCatalogPage.clickOnMakeDiscoverableButton(catalogName);
	}

	@And("User clicks on Discoverable Functions button")
	public void user_clicks_on_discoverable_Functions_button() {
		addFunctionToCatalogPage.clickOnDiscoverableFunctionsbutton();
	}

	@Given("User searches the {string} in the function Catalog searchbox")
	public void user_searches_the_in_the_function_catalog_searchbox(String catalogName) {
		addFunctionToCatalogPage.searchFunctionCatalog(catalogName);
	}

	@Given("User selects the {string} from the function catalog")
	public void user_selects_the_from_the_function_catalog(String catalogName) {
		addFunctionToCatalogPage.selectFunctionFromSearchOptions(catalogName);
	}

	@And("User checks if {string} catalog created and Deletes the {string}")
	public void user_deletes_the_if_already_created(String catalog, String catalogName) {
		addFunctionToCatalogPage.deleteCatalog(catalog, catalogName);
	}
}