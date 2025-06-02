package aicore.steps;

import java.util.List;
import java.util.Map;

import org.assertj.core.api.SoftAssertions;

import aicore.hooks.SetupHooks;
import aicore.hooks.SoftAssertionHooks;
import aicore.pages.AddModelToCatalogPage;
import aicore.utils.CommonUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;

public class AvailableModelsUnderGroupsSteps {
	private AddModelToCatalogPage modelPage;
	private String timestamp;
	private SoftAssertions softAssert;

	public AvailableModelsUnderGroupsSteps() {
		timestamp = CommonUtils.getTimeStampName();
		modelPage = new AddModelToCatalogPage(SetupHooks.getPage(), timestamp);
		softAssert = SoftAssertionHooks.getSoftAssertions();
	}

	@Then("User can view the following model options under their group")
	public void user_can_view_the_following_model_options_under_their__group(DataTable dataTable) {
		final String GROUP_NAME = "GROUP";
		final String MODEL_OPTIONS_NAME = "MODEL_OPTIONS";
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> row : rows) {
			String group = row.get(GROUP_NAME);
			String expectedModelOptions = row.get(MODEL_OPTIONS_NAME);
			modelPage.clickOnOptionsGroupTab(group);
			String[] expectedModelOptionsArray = expectedModelOptions.split(", ");
			for (String modelOption : expectedModelOptionsArray) {
				boolean isModelOptionVisible = modelPage.verifyModelOptionIsVisible(modelOption);
				softAssert.assertThat(isModelOptionVisible)
						.withFailMessage(modelOption + " " + "model option is not visible").isTrue();
			}
		}

	}
}
