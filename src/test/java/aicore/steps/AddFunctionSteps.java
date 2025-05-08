package aicore.steps;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;

import aicore.hooks.SetupHooks;
import aicore.pages.AddFunctionToCatalogPage;
import aicore.pages.HomePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddFunctionSteps {
	private HomePage homePage;
	private AddFunctionToCatalogPage addFunctionToCatalogPage;

	public AddFunctionSteps() {
		homePage = new HomePage(SetupHooks.getPage());
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

	@When("User searches {string} in the search by box")
	public void user_searches_in_the_search_by_box(String filterValue) {
		addFunctionToCatalogPage.searchFilterValue(filterValue);
	}

	@And("User applies each filter and validate {string} function is visible on the page")
	public void user_applies_each_filter_and_validate_function_is_visible_on_the_page(String functionName,
			DataTable dataTable) throws InterruptedException {
		final String FILTER_CATEGORY_NAME = "FILTER_CATEGORY";
		final String FILTER_VALUE_NAME = "FILTER_VALUE";
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

		for (Map<String, String> row : rows) {
			String filterCategory = row.get(FILTER_CATEGORY_NAME);
			String filterValues = row.get(FILTER_VALUE_NAME);

			String[] filterValuesArray = filterValues.split(", ");
			for (String filterValue : filterValuesArray) {
				addFunctionToCatalogPage.searchFilterValue(filterValue);
				addFunctionToCatalogPage.selectFilterValue(filterCategory, filterValue);
				boolean isFunctionVisible = addFunctionToCatalogPage.verifyFunctionIsVisbileInCatalog(functionName);
				Assertions.assertTrue(isFunctionVisible, "Function is not present in the function catalog for " + " ' "
						+ filterValue + " ' " + " filter value");
			}
		}
	}

	@When("User clicks Make Discoverable button")
	public void user_clicks_make_discoverable_button() {
		addFunctionToCatalogPage.clickOnMakeDiscoverableButton();
	}

	@And("User clicks on Discoverable Functions button")
	public void user_clicks_on_discoverable_functions_button() {
		addFunctionToCatalogPage.clickOnDiscoverableFunctionsbutton();
	}
}