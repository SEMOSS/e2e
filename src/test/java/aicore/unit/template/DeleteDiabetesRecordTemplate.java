package aicore.unit.template;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;

import aicore.pages.database.DatabaseCreationUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.AddDatabasePageUtils;
import aicore.utils.CommonUtils;
import aicore.utils.TestResourceTrackerHelper;
import aicore.utils.annotations.PWPage;
import aicore.utils.page.app.AppTemplatePageUtils;
import aicore.utils.page.app.CreateAppPopupUtils;
import aicore.utils.page.app.NotebookPageUtils;
import aicore.utils.page.app.TemplateCreationUtils;

public class DeleteDiabetesRecordTemplate extends AbstractPlaywrightTestBase{
	
	String appName = "Default Name Test App";
	
	private static final String TEMPLATE_NAME = "Delete Diabetes Record";
	private static final String DATABASE_NAME = "TestDatabase";
	private static final String QUERY_NAME = "on-page-load";
	private static final String FILE_NAME = "TestDatabase.zip";
	
	@BeforeEach
	void setup(@PWPage Page page) {
		loginNativeAdmin(page);
		String uploaded = DatabaseCreationUtils.createTestDatabase(page);
		Assertions.assertEquals(
			    FILE_NAME,
			    uploaded,
			    "Database ZIP wasn't uploaded correctly.");
		AddDatabasePageUtils.clickOnMetadataTab(page);
		
		appName = TemplateCreationUtils.createAppFromTemplate(page, TEMPLATE_NAME);
		verifyAppCreated(page);

	}	
	@AfterEach
	void tearDown(@PWPage Page page) {
		CommonUtils.navigateAndDeleteApp(page, appName);
		CommonUtils.navigateAndDeleteCatalog(
		        page,
		        TestResourceTrackerHelper.CATALOG_TYPE_DATABASE,
		        DATABASE_NAME
		    );
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
	
	
	@Test
	public void DeleteDiabetesRecordTemplate_test (@PWPage Page page) {
		
		runNotebook(page);	
	}

	

	@Test
	public void DeleteDiabetesRecordTemplateExist_test (@PWPage Page page) {
		
		AppTemplatePageUtils.clickPreviewButton(page);
		AppTemplatePageUtils.clickSelectUniqueIDDropdownButton(page);
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
