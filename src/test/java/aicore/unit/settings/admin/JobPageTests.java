package aicore.unit.settings.admin;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;

import aicore.pages.home.MainMenuUtils;
import aicore.utils.AbstractE2ETest;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.CommonUtils;
import aicore.utils.TestTags;
import aicore.utils.annotations.PWPage;
import aicore.utils.settings.JobPageUtils;
import aicore.utils.settings.SettingsPageUtils;
//TODO this is buggy and needs to be fixed

@Tag(TestTags.FE_BUG) //https://github.com/SEMOSS/semoss-ui/issues/3281
public class JobPageTests extends AbstractPlaywrightTestBase {

	private String jobName = null;
	
	@BeforeEach
	public void setup(@PWPage Page page) throws IOException {
		loginNativeAdmin(page);
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenSettings(page);
		SettingsPageUtils.clickOnAdminButton(page);
		JobPageUtils.clickOnJobTile(page);

		// add job
		JobPageUtils.clickOnAddJobButton(page);
		jobName = "test job " + CommonUtils.getTimeStampName();
		JobPageUtils.fillName(page, jobName);

		JobPageUtils.fillPixel(page, "1+2;");
		JobPageUtils.clickAddButton(page);

		String sectionName = "All";
		boolean isJobVisible = JobPageUtils.verifyJobTitle(page, jobName, sectionName);
		Assertions.assertTrue(isJobVisible, "Job title not visible: " + jobName);
	}
	
	@AfterEach
	void tearDown(@PWPage Page page) {
		JobPageUtils.clickDeleteIcon(page, jobName);
		JobPageUtils.verifyPauseButtonEnabled(page, jobName);

		String actualMessage = JobPageUtils.jobDeletionToastMessage(page);
		Assertions.assertEquals(actualMessage, "Successfully deleted all selected jobs", "Job deletion failed");
		logout(page);
	}
	

//	@BeforeAll
//	public void addJob() throws IOException {
//		login(page, UserType.NATIVE);
//		MainMenuUtils.openMainMenu(page);
//		MainMenuUtils.clickOnOpenSettings(page);
//		SettingsPageUtils.clickOnAdminButton(page);
//		JobPageUtils.clickOnJobTile(page);
//
//		// add job
//		JobPageUtils.clickOnAddJobButton(page);
//		jobName = "test job " + CommonUtils.getTimeStampName();
//		JobPageUtils.fillName(page, jobName);
//
//		JobPageUtils.fillPixel(page, "1+2;");
//		JobPageUtils.clickAddButton(page);
//
//		String sectionName = "All";
//		boolean isJobVisible = JobPageUtils.verifyJobTitle(page, jobName, sectionName);
//		Assertions.assertTrue(isJobVisible, "Job title not visible: " + jobName);
//	}

	@Test
	void testEditJob(@PWPage Page page) {
		// add tags
		JobPageUtils.clickEditIcon(page, jobName);
		JobPageUtils.editTags(page, 2);
		JobPageUtils.editPixel(page, "2+3;");
		JobPageUtils.clickSaveButton(page);

		// validate tags
		JobPageUtils.verifyAddedTag(page, "2", jobName);
	}
	
	@Test
	void testRunJob(@PWPage Page page) {
		JobPageUtils.clickCheckBox(page, jobName);
	}
	
	@Test
	void testPauseJob(@PWPage Page page) {
		// start
		JobPageUtils.clickCheckBox(page, jobName);
		
		// pause
		JobPageUtils.clickPauseButton(page);
		boolean isStopped = JobPageUtils.isJobStopped(page, jobName);
		assertTrue(isStopped, "Test Job not stopped: " + jobName);
		
		boolean isSelected = JobPageUtils.isCheckboxSelected(page, jobName);
		assertFalse(isSelected, "Checkbox is still selected: " + isSelected);
		
		boolean isReverted = JobPageUtils.isPauseButtonReverted(page);
		assertTrue(isReverted, "The button is not reverted: " + isReverted);
		
		// resume
		JobPageUtils.clickJobCheckBox(page, jobName);
		JobPageUtils.clickResumeButton(page);
		JobPageUtils.verifyPauseButtonEnabled(page, jobName);
		isSelected = JobPageUtils.isCheckboxSelected(page, jobName);
		assertFalse(isSelected, "Checkbox is still selected: " + isSelected);
		isReverted = JobPageUtils.isResumeButtonReverted(page);
		assertTrue(isReverted, "The button is not reverted: " + isReverted);


	}

//	@AfterAll
//	void deleteJob() {
//		JobPageUtils.clickDeleteIcon(page, jobName);
//		JobPageUtils.verifyPauseButtonEnabled(page, jobName);
//
//		String actualMessage = JobPageUtils.jobDeletionToastMessage(page);
//		Assertions.assertEquals(actualMessage, "Successfully deleted all selected jobs", "Job deletion failed");
//	}

}
