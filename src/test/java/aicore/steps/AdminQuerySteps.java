package aicore.steps;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;

import aicore.hooks.SetupHooks;
import aicore.pages.AddModelPage;
import aicore.pages.HomePage;
import aicore.pages.JobManagementPage;
import aicore.pages.SettingPage;
import aicore.pages.SettingsAdminQueryPage;
import aicore.utils.CommonUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AdminQuerySteps {
	private HomePage homePage;
	private SettingPage settingPage;
	private SettingsAdminQueryPage adminQuery;
	private AddModelPage modelPage;
	private JobManagementPage jobPage;
	private String timestamp;

	public AdminQuerySteps() {
		homePage = new HomePage(SetupHooks.getPage());
		settingPage = new SettingPage(SetupHooks.getPage());
		adminQuery = new SettingsAdminQueryPage(SetupHooks.getPage());
		timestamp = SetupHooks.getTimestamp();
		modelPage = new AddModelPage(SetupHooks.getPage(), timestamp);
		jobPage = new JobManagementPage(SetupHooks.getPage(), timestamp);
	}

	@And("User clicks on Database dropdown")
	public void user_clicks_on_database_dropdown() {
		adminQuery.clickOnSelectDatabase();
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
		Assertions.assertEquals(expectedToastMessage, actualToastMessage, "Toast message is incorrect");
		adminQuery.closeQueryExecutedToastMessage();
	}

	@When("User enters {string} in the Max Rows to Collected textbox")
	public void user_enters_in_the_max_rows_to_collected_textbox(String count) {
		adminQuery.enterRowCount(count);
	}

	@Then("User can see table with {string} rows")
	public void user_can_see_table_with_rows(String rowCount) {
		int expectedRowsCount = Integer.parseInt(rowCount);
		int actualRowsCount = adminQuery.totalCountOfRows();
		Assertions.assertEquals(expectedRowsCount, actualRowsCount, "Row count is not matching");
	}

	@Given("User created {string} models with the model {string}, catalog name {string}, OpenAI key {string}, and variable name {string}")
	public void user_created_models_with_the_catalog_name_open_ai_key_and_variable_name(String modelCount,
			String modelName, String catalogName, String openAIKey, String variableName) {
		for (int i = 1; i <= Integer.parseInt(modelCount); i++) {
			String catalogNameWithTimestamp = catalogName + System.currentTimeMillis();
			modelPage.createModel(modelName, catalogNameWithTimestamp, openAIKey, variableName);
		}
	}

	@And("User Delete the created Model")
	public void user_delete_created_model() {
		modelPage.deleteCreatedModel();
	}

	@Given("User created {string} jobs with the job name {string}, Pixel {string}")
	public void user_created_jobs_with_the_job_name_pixel(String jobCount, String jobName, String pixel) {
		for (int i = 1; i <= Integer.parseInt(jobCount); i++) {
			String jobNameWithTimestamp = jobName + System.currentTimeMillis();
			jobPage.createJob(jobNameWithTimestamp, pixel);
		}
	}
}
