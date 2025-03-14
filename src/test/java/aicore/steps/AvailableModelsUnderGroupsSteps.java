package aicore.steps;

import java.util.List;
import java.util.Map;

import org.assertj.core.api.SoftAssertions;

import aicore.base.AICoreTestManager;
import aicore.hooks.SoftAssertionHooks;
import aicore.pages.AddModelToCatalogPage;
import aicore.utils.CommonUtils;
import io.cucumber.java.en.Then;

public class AvailableModelsUnderGroupsSteps {
	private AddModelToCatalogPage modelPage;
	private String timestamp;
	private SoftAssertions softAssert;

	public AvailableModelsUnderGroupsSteps() {
		timestamp = CommonUtils.getTimeStampName();
		modelPage = new AddModelToCatalogPage(AICoreTestManager.getPage(), timestamp);
		softAssert = SoftAssertionHooks.getSoftAssertions();
	}

	@Then("User can view the following models grouping")
	public void user_can_view_the_following_models_grouping(io.cucumber.datatable.DataTable dataTable) {
		final String LLM_GROUP_DATATABLE_COL_NAME = "LLM_GROUP";
		final String LLM_MODELS_DATATABLE_COL_NAME = "LLM_MODELS";
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

		for (Map<String, String> row : rows) {
			String group = row.get(LLM_GROUP_DATATABLE_COL_NAME);
			String expectedModels = row.get(LLM_MODELS_DATATABLE_COL_NAME);

			boolean isGroupVisible = modelPage.verifyGroupIsVisible(group);
			softAssert.assertThat(isGroupVisible).isTrue();

			String[] expectedModelArray = expectedModels.split(", ");
			for (String model : expectedModelArray) {
				boolean isModelVisible = modelPage.VerifyModelIsVisible(group, model);
				softAssert.assertThat(isModelVisible).isTrue();
			}
		}

	}
}
