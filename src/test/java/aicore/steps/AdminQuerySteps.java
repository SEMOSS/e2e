package aicore.steps;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;

import aicore.hooks.SetupHooks;
import aicore.pages.HomePage;
import aicore.pages.SettingPage;
import aicore.pages.SettingsAdminQueryPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class AdminQuerySteps {
	private HomePage homePage;
	private SettingPage settingPage;
	private SettingsAdminQueryPage adminQuery;

	public AdminQuerySteps() {
		homePage = new HomePage(SetupHooks.getPage());
		settingPage = new SettingPage(SetupHooks.getPage());
		adminQuery = new SettingsAdminQueryPage(SetupHooks.getPage());
	}

	@And("User selects {string} from the database dropdown")
	public void user_selects_from_the_database_dropdown(String databaseName) {
		adminQuery.selectDatabase(databaseName);
	}

	@And("User enters {string} in the query textbox")
	public void user_enters_in_the_query_textbox(String query) {
		adminQuery.enterQuery(query);
	}

	@And("User clicks on Run button")
	public void user_clicks_on_run_button() {
		adminQuery.clickOnExecuteQueryButton();
	}

	@Then("User can see table with {string} columns:{string}")
	public void user_can_see_table_with_columns(String columnCount, String expectedNames) {
		int expectedColumnCount = Integer.parseInt(columnCount);
		int actualColumnCount = adminQuery.getTableHeaderCount();
		List<String> actualHeaderNamesList = adminQuery.getTableHeaderNames();
		List<String> expectedHeaderNamesList = Arrays.asList(expectedNames.split(",\\s*"));
		Assertions.assertEquals(expectedColumnCount, actualColumnCount, "Column count is not matching");
		Assertions.assertEquals(actualHeaderNamesList, expectedHeaderNamesList, "Column count is not matching");
	}

	@And("User can see success toast message as {string}")
	public void user_can_see_success_toast_message_as(String expectedToastMessage) {
		String actualToastMessage = adminQuery.verifyQueryExecutedToastMessage();
		System.out.println(actualToastMessage);
		Assertions.assertEquals(expectedToastMessage, actualToastMessage, "Toast message is incorrect");
	}

}
