package aicore.steps;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;

import aicore.hooks.SetupHooks;
import aicore.pages.model.ModelViewLogsDashboardPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ModelViewLogsDashboardSteps {
	private ModelViewLogsDashboardPage dashboardPage;

	public ModelViewLogsDashboardSteps() {
		dashboardPage = new ModelViewLogsDashboardPage(SetupHooks.getPage());
	}

	@When("User navigates to the Model Insight Dashboard")
	public void user_navigates_to_model_insight_dashboard() {
		dashboardPage.navigateToModelInsightDashboard();
	}

	@Then("User should see the heading 'Model Insight Dashboard'")
	public void user_should_see_heading_model_insight_dashboard() {
		boolean isHeadingVisible = dashboardPage.verifyDashboardHeading();
		Assertions.assertThat(isHeadingVisible).as("Model Insight Dashboard heading should be visible").isTrue();
	}

	@And("User should see the 'Refresh' button")
	public void user_should_see_refresh_button() {
		boolean isRefreshButtonVisible = dashboardPage.verifyRefreshButton();
		Assertions.assertThat(isRefreshButtonVisible).as("Refresh button should be visible").isTrue();
	}

	@And("User should see the 'Event History' section")
	public void user_should_see_event_history_section() {
		boolean isEventHistorySectionVisible = dashboardPage.verifyEventHistorySection();
		Assertions.assertThat(isEventHistorySectionVisible).as("Event History section should be visible").isTrue();
	}

	@And("User should see the 'Prompt & Response Timeline' section")
	public void user_should_see_prompt_response_timeline_section() {
		boolean isTimelineSectionVisible = dashboardPage.verifyPromptResponseTimelineSection();
		Assertions.assertThat(isTimelineSectionVisible).as("Prompt & Response Timeline section should be visible")
				.isTrue();
	}

	@And("User should see the table with columns {string}")
	public void user_should_see_table_with_columns(String columns) {
		List<String> expectedColumns = Arrays.asList(columns.split("\\s*,\\s*"));

		// Verify table exists
		boolean isTableVisible = dashboardPage.verifyTableExists();
		Assertions.assertThat(isTableVisible).as("Table should be visible on the dashboard").isTrue();

		// Verify all columns exist
		boolean areAllColumnsPresent = dashboardPage.verifyTableColumnsExist(expectedColumns);
		Assertions.assertThat(areAllColumnsPresent).as("All expected columns should be present in the table").isTrue();
	}
}
