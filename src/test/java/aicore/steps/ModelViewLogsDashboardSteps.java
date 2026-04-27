package aicore.steps;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.SoftAssertions;

import aicore.hooks.SetupHooks;
import aicore.hooks.SoftAssertionHooks;
import aicore.pages.model.ModelViewLogsDashboardPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ModelViewLogsDashboardSteps {
	private ModelViewLogsDashboardPage dashboardPage;
	private SoftAssertions softAssert;

	public ModelViewLogsDashboardSteps() {
		dashboardPage = new ModelViewLogsDashboardPage(SetupHooks.getPage());
		softAssert = SoftAssertionHooks.getSoftAssertions();
	}

	@When("User navigates to the Model Insight Dashboard")
	public void user_navigates_to_model_insight_dashboard() {
		dashboardPage.navigateToModelInsightDashboard();
	}

	@Then("User should see the heading 'Model Insight Dashboard'")
	public void user_should_see_heading_model_insight_dashboard() {
		boolean isHeadingVisible = dashboardPage.verifyDashboardHeading();
		softAssert.assertThat(isHeadingVisible)
			.as("Model Insight Dashboard heading should be visible")
			.isTrue();
	}

	@And("User should see the 'Refresh' button")
	public void user_should_see_refresh_button() {
		boolean isRefreshButtonVisible = dashboardPage.verifyRefreshButton();
		softAssert.assertThat(isRefreshButtonVisible)
			.as("Refresh button should be visible")
			.isTrue();
	}

	@And("User should see the 'Event History' section")
	public void user_should_see_event_history_section() {
		boolean isEventHistorySectionVisible = dashboardPage.verifyEventHistorySection();
		softAssert.assertThat(isEventHistorySectionVisible)
			.as("Event History section should be visible")
			.isTrue();
	}

	@And("User should see the 'Prompt & Response Timeline' section")
	public void user_should_see_prompt_response_timeline_section() {
		boolean isTimelineSectionVisible = dashboardPage.verifyPromptResponseTimelineSection();
		softAssert.assertThat(isTimelineSectionVisible)
			.as("Prompt & Response Timeline section should be visible")
			.isTrue();
	}

	@And("User should see the table with columns {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, and {string}")
	public void user_should_see_table_with_columns(String col1, String col2, String col3, String col4, String col5, 
			String col6, String col7, String col8, String col9, String col10) {
		List<String> expectedColumns = Arrays.asList(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10);
		
		// Verify table exists
		boolean isTableVisible = dashboardPage.verifyTableExists();
		softAssert.assertThat(isTableVisible)
			.as("Table should be visible on the dashboard")
			.isTrue();
		
		// Verify all columns exist
		boolean areAllColumnsPresent = dashboardPage.verifyTableColumnsExist(expectedColumns);
		softAssert.assertThat(areAllColumnsPresent)
			.as("All expected columns should be present in the table")
			.isTrue();
	}
}
