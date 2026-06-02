package aicore.unit.settings.admin;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.microsoft.playwright.Page;

import aicore.pages.home.MainMenuUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.TestTags;
import aicore.utils.annotations.PWPage;
import aicore.utils.settings.JobPageUtils;
import aicore.utils.settings.SettingsPageUtils;

public class JobPageLoadingTests extends AbstractPlaywrightTestBase {

	@BeforeEach
	public void setup(@PWPage Page page) throws IOException {
		loginNativeAdmin(page);
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenSettings(page);
		SettingsPageUtils.clickOnAdminButton(page);
		JobPageUtils.clickOnJobTile(page);
	}
	
	@AfterEach
	void tearDown(@PWPage Page page) {
		logout(page);
	}

	@Test
	@Tag(TestTags.FE_BUG)
	void testLoadPage(@PWPage Page page) {
		String title = "Jobs";
		String actualTitle = JobPageUtils.verifyTitleOfJobPage(page, title);
		Assertions.assertEquals(actualTitle, title, "Job page title mismatch");

		String subtitle = "Manage and schedule cron jobs for the platform";
		String actualSubtitle = JobPageUtils.verifySubtitleOfJobPage(page, subtitle);
		Assertions.assertEquals(actualSubtitle, subtitle, "Job page subtitle mismatch");

		// FE BUG, SEARCH BOX DOES NOT APPEAR IN JOBS PAGE - https://github.com/SEMOSS/semoss-ui/issues/3280
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
	@ValueSource(strings = { "Active Jobs", "Runs (24h)", "Success rate (24h)", "Failures (24h)"})
	void testPageStatusTiles(String tile, @PWPage Page page) {
		boolean isTileVisible = JobPageUtils.verifyStatusTilesVisibleOnJobPage(page, tile);
		Assertions.assertTrue(isTileVisible, "Tile not visible: " + tile);
	}

	@ParameterizedTest
	@ValueSource(strings = { "All", "Active", "Inactive" })
	void testPageStatusTabs(String tab, @PWPage Page page) {
		boolean isTabVisible = JobPageUtils.verifyTabsVisibleOnJobPage(page, tab);
		Assertions.assertTrue(isTabVisible, "Tab not visible: " + tab);
	}

	@ParameterizedTest
	@ValueSource(strings = { "Pause", "Resume", "Add" })
	void testViewButtons(String btn, @PWPage Page page) {
		boolean isButtonVisible = JobPageUtils.verifyButtonsVisibleOnJobPage(page, btn);
		Assertions.assertTrue(isButtonVisible, "Button not visible: " + btn);
	}

	@ParameterizedTest
	@ValueSource(strings = { "Pause", "Resume"/*, "Delete"*/ })
	// Note: skipping the test for the disabled "Delete" button, locator resolves to
	// too many other "delete" buttons, causing errors
	void testViewDisabledButtons(String btn, @PWPage Page page) {
		boolean isButtonDisabled = JobPageUtils.verifyButtonsDisabledOnJobPage(page, btn);
		Assertions.assertTrue(isButtonDisabled, "Button is not disabled: " + btn);
	}

	@ParameterizedTest
	@ValueSource(strings = { "Name", "Frequency", "Time Zone", "Tags", "Last Run", "Modified By", "Actions" })
	void testJobTable(String column, @PWPage Page page) {
		boolean isColumnVisible = JobPageUtils.verifyJobTableColumns(page, column);
		Assertions.assertTrue(isColumnVisible, "Column not visible: " + column);
	}

	@ParameterizedTest
	@ValueSource(strings = { "Name", "Run Date", "Duration" })
	void testJobHistoryTable(String column, @PWPage Page page) {
		JobPageUtils.clickOnTab(page, "History");
		boolean isColumnVisible = JobPageUtils.verifyHistoryTableColumns(page, column);
		Assertions.assertTrue(isColumnVisible, "Column not visible: " + column);
	}

}
