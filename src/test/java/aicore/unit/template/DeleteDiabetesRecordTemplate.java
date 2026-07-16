package aicore.unit.template;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;

import aicore.pages.database.DataBaseCreationUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.AddDatabasePageUtils;
import aicore.utils.annotations.PWPage;
import aicore.utils.page.app.AppTemplatePageUtils;
import aicore.utils.page.app.CreateAppPopupUtils;
import aicore.utils.page.app.NotebookPageUtils;
import aicore.utils.page.app.TemplateCreationUtils;

public class DeleteDiabetesRecordTemplate extends AbstractPlaywrightTestBase{
	
	
	@BeforeEach
	void setup(@PWPage Page page) {
		loginNativeAdmin(page);
		String uploaded = DataBaseCreationUtils.createTestDatabase(page);
		Assertions.assertEquals(
			    "TestDatabase.zip",
			    uploaded,
			    "Database ZIP wasn't uploaded correctly.");
		AddDatabasePageUtils.clickOnMetadataTab(page);

	}	
	@AfterEach
	void tearDown(@PWPage Page page) {
	    logout(page);
	}
	
	private void verifyAppCreated(Page page) {
	    String appName = CreateAppPopupUtils.userFetchAppName(page);
	    Assertions.assertFalse(appName.isEmpty(), "Fetched App Name is Empty");
	}
	
	private void runNotebook(Page page) {
	    NotebookPageUtils.clickOnNotebooksOption(page);
	    NotebookPageUtils.clickOnQueryName(page, QUERY_NAME);
	    NotebookPageUtils.selectDatabaseType(page, DATABASE_NAME);
	    NotebookPageUtils.clickOnRunCellButtonDatabase(page);
	    NotebookPageUtils.checkDatabaseOutput(page);
	}
	
	private static final String TEMPLATE_NAME = "Delete Diabetes Record";
	private static final String DATABASE_NAME = "TestDatabase";
	private static final String QUERY_NAME = "on-page-load";
	
	
	@Test
	public void DeleteDiabetesRecordTemplate_test (@PWPage Page page) {
		
		TemplateCreationUtils.createAppFromTemplate(page, TEMPLATE_NAME);
		verifyAppCreated(page);		
		runNotebook(page);	
	}

	

	@Test
	public void DeleteDiabetesRecordTemplateExist_test (@PWPage Page page) {
		
		TemplateCreationUtils.createAppFromTemplate(page, "Delete Diabetes Record");
		verifyAppCreated(page);		
		AppTemplatePageUtils.clickPreviewButton(page);
		NotebookPageUtils.selectValueFromDropdown(page, "4", "Select Unique ID");
		NotebookPageUtils.clickOnRecordButton(page, "Delete Record");
		NotebookPageUtils.checkSuccessMessage(page, "true");
		AppTemplatePageUtils.closePreviewWindow(page);
		runNotebook(page);
		NotebookPageUtils.modifySqlQuery(page, "SELECT * from diabetes WHERE DIABETES_UNIQUE_ROW_ID = 4");
		NotebookPageUtils.clickOnRunCellButtonDatabase(page);
		NotebookPageUtils.checkDatabaseQueryOutput(page);	
	}

}
