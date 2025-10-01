package aicore.steps;

import org.junit.jupiter.api.Assertions;

import aicore.hooks.SetupHooks;
import aicore.pages.AddFunctionToCatalogPage;
import aicore.pages.HomePage;
import aicore.utils.CommonUtils;
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
		timestamp = CommonUtils.getTimeStampName();
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

	@Then("User uploads function file {string}")
	public void user_uploads_funtion_file(String fileName) {
		String uploadedFileName = addFunctionToCatalogPage.enterFilePath(fileName);
		if (fileName.contains("/")) {
			String[] ActualFileName = fileName.split("/");
			int fileNameIndex = ActualFileName.length - 1;
			Assertions.assertEquals(ActualFileName[fileNameIndex], uploadedFileName,
					"function Document file is not uploaded successfully");
		} else {
			Assertions.assertEquals(fileName, uploadedFileName, "function Document file is not uploaded successfully");
		}
	}

	@Then("User clicks on Create Function button")
	public void user_clicks_on_create_Function_button() {
		addFunctionToCatalogPage.clickOnCreateFunctionButton();
	}

	@And("User sees astrisk mark on the required fields {string}")
	public void user_sees_astrisk_mark_on_the_required_fields(String requiredFields) {
		addFunctionToCatalogPage.verifyAsteriskMarkOnFields(requiredFields);
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

	@Then("User sees Create Function button")
	public void user_sees_create_Funtion_button() {
		addFunctionToCatalogPage.checkCreateFunctionButton();
	}

	@Then("User sees success toast message {string}")
	public void user_sees_success_toast_message(String toastMessage) {
		String actualMessage = addFunctionToCatalogPage.verifySuccessToastMessage();
		Assertions.assertEquals(toastMessage, actualMessage, "catalog is not created successfully");
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
}