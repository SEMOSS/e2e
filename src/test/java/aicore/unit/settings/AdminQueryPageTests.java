package aicore.unit.settings;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import aicore.hooks.SetupHooks;
import aicore.pages.home.MainMenuUtils;
import aicore.utils.AbstractE2ETest;
import aicore.utils.settings.AdminQueryPageUtils;
import aicore.utils.settings.SettingsPageUtils;

@Tag("REGRESSION")
public class AdminQueryPageTests extends AbstractE2ETest {

	@BeforeAll
	public static void setup() throws IOException {
		login(page, UserType.NATIVE);
	}

	@BeforeEach
	public void openMainMenu() throws IOException {
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenSettings(SetupHooks.getPage());
		SettingsPageUtils.clickOnAdminButton(page);
	}

	@ParameterizedTest(name = "[{index}] DB={0}, Query={1}")
	@MethodSource("queryTestData")
	void testQueries(String databaseName, String query, int expectedColumnCount, String expectedNames) {
		SettingsPageUtils.clickOnCard(page, "Admin Query");
		AdminQueryPageUtils.clickOnSelectDatabase(page);
		AdminQueryPageUtils.selectDatabase(page, databaseName);
		AdminQueryPageUtils.enterQuery(page, query);
		AdminQueryPageUtils.clickOnExecuteQueryButton(page);

		int actualColumnCount = AdminQueryPageUtils.getTableHeaderCount(page);
		List<String> actualHeaderNamesList = AdminQueryPageUtils.getTableHeaderNames(page);
		List<String> expectedHeaderNamesList = Arrays.asList(expectedNames.split(",\\s*"));

		Assertions.assertEquals(expectedColumnCount, actualColumnCount, "Column count is not matching");
		Assertions.assertEquals(expectedHeaderNamesList, actualHeaderNamesList, "Column names are not matching");
	}

	static Stream<Arguments> queryTestData() {
		return Stream.of(
				Arguments.of("Local Master Database", "select * from ENGINECONCEPT", 14,
				"engine, parentsemossname, semossname, parentphysicalname, parentphysicalnameid, physicalname, physicalnameid, parentlocalconceptid, localconceptid, ignore_data, pk, original_type, property_type, additional_type"),
				Arguments.of("security", "select * from ENGINEMETA", 4, "engineid, metakey, metavalue, metaorder"),
				Arguments.of("scheduler", "select * from SMSS_JOB_RECIPES", 11,
						"user_id, job_id, job_name, job_group, cron_expression, cron_timezone, pixel_recipe, pixel_recipe_parameters, job_category, trigger_on_load, ui_state"),
				Arguments.of("themes", "select * from ADMIN_THEME", 4, "id, theme_name, theme_map, is_active"),
				Arguments.of("User Tracking Database", "select * from INSIGHT_OPENS", 4,
						"insightid, userid, opened_on, origin"));
	}

}
