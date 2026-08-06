package aicore.unit.template;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;

import aicore.pages.database.DatabaseCreationUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.CommonUtils;
import aicore.utils.annotations.PWPage;
import aicore.utils.page.app.AppTemplatePageUtils;
import aicore.utils.page.app.CreateAppPopupUtils;
import aicore.utils.page.app.NotebookPageUtils;
import aicore.utils.page.app.TemplateCreationUtils;

public class ReadDiabetesRecordTemplate extends AbstractPlaywrightTestBase {
	
	String timestamp = CommonUtils.getTimeStampName();
	String appName = "Test app " + timestamp;	

	@BeforeEach
	void setup(@PWPage Page page) {
		loginNativeAdmin(page);
	    DatabaseCreationUtils.createTestDatabase(page);
		String uploaded = DatabaseCreationUtils.createTestDatabase(page);
		Assertions.assertEquals(
			    "TestDatabase.zip",
			    uploaded,
			    "Database ZIP wasn't uploaded correctly.");
	}	
	@AfterEach
	void tearDown(@PWPage Page page) {
		CommonUtils.navigateAndDeleteApp(page, appName);
	    logout(page);
	}
	
	private void verifyAppCreated(Page page) {
	    String appName = CreateAppPopupUtils.userFetchAppName(page);
	    Assertions.assertFalse(appName.isEmpty(), "Fetched App Name is Empty");
	}
	
	

	@Test
	public void CreateAppUsingReadDiabetesRecordTemplate_test (@PWPage Page page) {
		
		appName = TemplateCreationUtils.createAppFromTemplate(page, "Read Diabetes Record");	
		verifyAppCreated(page);
		NotebookPageUtils.clickOnNotebooksOption(page);
		NotebookPageUtils.clickOnQueryName(page, "on-page-load");
		NotebookPageUtils.selectDatabaseType(page, "TestDatabase");
		NotebookPageUtils.clickOnRunCellButtonDatabase(page);
		NotebookPageUtils.checkDatabaseOutput(page);
	}

	
	
	@Test
	public void CreateAppUsingReadDiabetesRecordTemplateExisting_test (@PWPage Page page) {
		
		appName = TemplateCreationUtils.createAppFromTemplate(page, "Read Diabetes Record");	
		verifyAppCreated(page);
		AppTemplatePageUtils.clickPreviewButton(page);
		NotebookPageUtils.selectValueFromDropdown(page, "4", "Select Unique ID");
		NotebookPageUtils.checkRecordWithUniqueId(page, "4");
		}
	
}
