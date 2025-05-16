package aicore.steps;

import org.junit.jupiter.api.Assertions;

import aicore.hooks.SetupHooks;
import aicore.pages.AddFunctionToCatalogPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddFunctionSteps {
	private AddFunctionToCatalogPage addFunctionToCatalogPage;

	public AddFunctionSteps() {
		addFunctionToCatalogPage = new AddFunctionToCatalogPage(SetupHooks.getPage());
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

	@Then("User clicks on Create Funtion button")
	public void user_clicks_on_create_Funtion_button() {
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

	@Then("User sees astrisk mark on the required form fields")
	public void user_sees_astrisk_mark_on_the_required_form_fields(io.cucumber.datatable.DataTable dataTable) {

	}
}