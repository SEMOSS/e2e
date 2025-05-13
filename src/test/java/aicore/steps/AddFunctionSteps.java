package aicore.steps;

import org.junit.jupiter.api.Assertions;

import aicore.hooks.SetupHooks;
import aicore.pages.AddFunctionToCatalogPage;
import aicore.pages.HomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddFunctionSteps {
	private HomePage homePage;
	private AddFunctionToCatalogPage addFunctionToCatalogPage;

	public AddFunctionSteps() {
		homePage = new HomePage(SetupHooks.getPage());
		addFunctionToCatalogPage = new AddFunctionToCatalogPage(SetupHooks.getPage());
	}

	@Given("User navigates to Open Function")
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

	@Then("User sees the function name {string} in the function catalog")
	public void user_sees_the_function_name_in_the_catalog(String functionName) {
		String addedFunction = addFunctionToCatalogPage.verifyFunctionNameInCatalog(functionName);
		Assertions.assertEquals(functionName, addedFunction, "Function is not present in the function catalog");
	}

	@Then("User clicks on the function name {string} in the function catalog")
	public void user_clicks_on_the_function_name_in_the_function_catalog(String functionName) {
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

}