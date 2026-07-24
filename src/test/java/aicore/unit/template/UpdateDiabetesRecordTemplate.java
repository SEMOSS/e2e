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

public class UpdateDiabetesRecordTemplate extends AbstractPlaywrightTestBase {
	
	

	
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
	
	private void verifyQueryValue(Page page, String column, String value) {
	    Assertions.assertTrue(
	        NotebookPageUtils.validateQuery(page, column, value),
	        "Query output is not visible");
	}
	private void openUpdateNotebook(Page page) {
	    NotebookPageUtils.clickOnNotebooksOption(page);
	    NotebookPageUtils.clickOnQueryName(page, QUERY_NAME);
	    NotebookPageUtils.selectDatabaseType(page, DATABASE_NAME);
	    NotebookPageUtils.clickOnRunCellButtonDatabase(page);
	    NotebookPageUtils.checkDatabaseOutput(page);
	}
	
	private static final String QUERY_NAME = "update-diabetes-record";
	private static final String DATABASE_NAME = "TestDatabase";

	@Test
	public void UpdateDiabetesRecordTemplate_test (@PWPage Page page) {
		
		
		TemplateCreationUtils.createAppFromTemplate(page, "Update Diabetes Record");	
		verifyAppCreated(page);
		openUpdateNotebook(page);
	}

	
	@Test
	public void UpdateDiabetesRecordTemplateExisting_test (@PWPage Page page) {
		
		String newQuery = "SELECT * from diabetes WHERE ID=16767 AND AGE=35 AND LOCATION='Pune' AND GENDER='Male'";
		TemplateCreationUtils.createAppFromTemplate(page, "Update Diabetes Record");	
		verifyAppCreated(page);
		AppTemplatePageUtils.clickPreviewButton(page);
		NotebookPageUtils.selectValueFromDropdown(page, "4", "UNIQUE_ROW_ID");
		NotebookPageUtils.addValueInField(page, "ID", "16767");
		NotebookPageUtils.addValueInField(page, "AGE", "35");
		NotebookPageUtils.addValueInField(page, "Male", "GENDER");
		NotebookPageUtils.addValueInField(page, "Pune", "LOCATION");
		NotebookPageUtils.clickOnRecordButton(page, "Update");
		NotebookPageUtils.checkSuccessMessage(page, "true");
		AppTemplatePageUtils.closePreviewWindow(page);
		openUpdateNotebook(page);
		NotebookPageUtils.modifySqlQuery(page, newQuery);
		NotebookPageUtils.clickOnRunCellButtonDatabase(page);

		
		verifyQueryValue(page, "AGE", "35");
		verifyQueryValue(page, "LOCATION", "Pune");
		verifyQueryValue(page, "GENDER", "Male");
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
}
