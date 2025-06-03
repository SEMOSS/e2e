package aicore.steps;

import java.util.List;
import java.util.Map;

import org.assertj.core.api.SoftAssertions;

import aicore.hooks.SetupHooks;
import aicore.hooks.SoftAssertionHooks;
import aicore.pages.AddModelPage;
import aicore.utils.CommonUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class AdminSettingsSteps {
	private AddModelPage modelPage;
	private String timestamp;
	private SoftAssertions softAssert;

	public AdminSettingsSteps() {
		timestamp = CommonUtils.getTimeStampName();
		modelPage = new AddModelPage(SetupHooks.getPage(), timestamp);
		softAssert = SoftAssertionHooks.getSoftAssertions();
	}

	@Then("User can view the following settings tile")
	public void user_can_view_the_following_settings_tile(io.cucumber.datatable.DataTable dataTable) {
		final String SETTINGS_TILE_DATATABLE_COL_NAME = "SETTINGS_TILE";
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> row : rows) {
			String tileName = row.get(SETTINGS_TILE_DATATABLE_COL_NAME);
			boolean isTileVisible = modelPage.verifyTileIsVisible(tileName);
			softAssert.assertThat(isTileVisible).isTrue();
		}
	}

	@Given("User searches {string} in Search box")
	public void user_searches_in_search_box(String string) {
		modelPage.clickOnSearchBox(string);
	}

	@Then("User can view the {string} tile")
	public void user_can_view_the_tile(String tileName) {
		boolean isTileVisible = modelPage.verifyTileIsVisible(tileName);
		softAssert.assertThat(isTileVisible).isTrue();
	}
}
