package aicore.unit.settings.admin;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import aicore.pages.home.MainMenuUtils;
import aicore.utils.AbstractE2ETest;
import aicore.utils.settings.JobPageUtils;
import aicore.utils.settings.SettingsPageUtils;

public class JobPageLoadingTests extends AbstractE2ETest {

	@BeforeAll
	public void addJob() throws IOException {
		login(page, UserType.NATIVE);
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenSettings(page);
		SettingsPageUtils.clickOnAdminButton(page);
		JobPageUtils.clickOnJobTile(page);
	}

	@Test
	void testLoadPage() {
		String title = "Jobs";
		String actualTitle = JobPageUtils.verifyTitleOfJobPage(page, title);
		Assertions.assertEquals(actualTitle, title, "Job page title mismatch");

		String subtitle = "Search by job name or filter using job tags";
		String actualSubtitle = JobPageUtils.verifySubtitleOfJobPage(page, subtitle);
		Assertions.assertEquals(actualSubtitle, subtitle, "Job page subtitle mismatch");

		boolean isSearchboxVisible = JobPageUtils.verifySearchBoxVisibleOnJobPage(page);
		Assertions.assertTrue(isSearchboxVisible, "Search box not visible on Jobs page");

		String message = "No jobs found";
		boolean isMessageVisible = JobPageUtils.verifyNoJobsMessageOnJobPage(page, message);
		Assertions.assertTrue(isMessageVisible, "No jobs message not visible: " + message);

		boolean isSearchBoxVisible = JobPageUtils.verifySearchBoxVisibleOnHistoryTable(page);
		Assertions.assertTrue(isSearchBoxVisible, "Search box not visible on History table");

		message = "No job history, please try again.";
		isMessageVisible = JobPageUtils.verifyNoJobHistoryMessageOnJobPage(page, message);
		Assertions.assertTrue(isMessageVisible, "No job history message not visible: " + message);
	}

	@ParameterizedTest
	@ValueSource(strings = { "Active Jobs", "Inactive Jobs", "Failed Jobs" })
	void testPageStatusTiles(String tile) {
		boolean isTileVisible = JobPageUtils.verifyStatusTilesVisibleOnJobPage(page, tile);
		Assertions.assertTrue(isTileVisible, "Tile not visible: " + tile);
	}

	@ParameterizedTest
	@ValueSource(strings = { "All", "Active", "Inactive" })
	void testPageStatusTabs(String tab) {
		boolean isTabVisible = JobPageUtils.verifyTabsVisibleOnJobPage(page, tab);
		Assertions.assertTrue(isTabVisible, "Tab not visible: " + tab);
	}

	@ParameterizedTest
	@ValueSource(strings = { "Pause", "Resume", "Add" })
	void testViewButtons(String btn) {
		boolean isButtonVisible = JobPageUtils.verifyButtonsVisibleOnJobPage(page, btn);
		Assertions.assertTrue(isButtonVisible, "Button not visible: " + btn);
	}

	@ParameterizedTest
	@ValueSource(strings = { "Pause", "Resume", "Add" })
	void testViewDisabledButtons(String btn) {
		boolean isButtonDisabled = JobPageUtils.verifyButtonsDisabledOnJobPage(page, btn);
		Assertions.assertTrue(isButtonDisabled, "Button is not disabled: " + btn);
	}

	@ParameterizedTest
	@ValueSource(strings = { "Name", "Frequency", "Time Zone", "Tags", "Last Run", "Status", "Modified By", "Actions" })
	void testJobTable(String column) {
		boolean isColumnVisible = JobPageUtils.verifyJobTableColumns(page, column);
		Assertions.assertTrue(isColumnVisible, "Column not visible: " + column);
	}

	@ParameterizedTest
	@ValueSource(strings = { "Name", "Run Date", "Time", "Status" })
	void testJobHistoryTable(String column) {
		boolean isColumnVisible = JobPageUtils.verifyHistoryTableColumns(page, column);
		Assertions.assertTrue(isColumnVisible, "Column not visible: " + column);
	}

}
