package aicore.unit.template;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;

import aicore.pages.database.DatabaseCreationUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.CommonUtils;
import aicore.utils.TestResourceTrackerHelper;
import aicore.utils.annotations.PWPage;
import aicore.utils.page.app.AppTemplatePageUtils;
import aicore.utils.page.app.BlockSettingsUtils;
import aicore.utils.page.app.CreateAppPopupUtils;
import aicore.utils.page.app.NotebookPageUtils;
import aicore.utils.page.app.TemplateCreationUtils;


public class CreateDiabetesRecordTemplate extends AbstractPlaywrightTestBase {
	
	String appName = "Default Name Test App";
	
	private static final String TEMPLATE_NAME = "Create Diabetes Record";
	private static final String FILE_NAME = "TestDatabase.zip";
	private static final String DATABASE_NAME = "TestDatabase";
	private static final String QUERY_NAME = "insert-diabetes-record";
	
	@BeforeEach
	void setup(@PWPage Page page) {
		loginNativeAdmin(page);
		String uploaded = DatabaseCreationUtils.createTestDatabase(page);
		Assertions.assertEquals(
			    FILE_NAME,
			    uploaded,
			    "Database ZIP wasn't uploaded correctly.");
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
	
	private void verifyHeaders(Page page, String... expectedHeaders) {
	    Assertions.assertEquals(
	            List.of(expectedHeaders),
	            NotebookPageUtils.getNotebookOutputTableHeader(page),
	            "Headers are not matching");
	}
	
	private void verifyAppCreated(Page page) {
	    String name = CreateAppPopupUtils.userFetchAppName(page);
	    Assertions.assertFalse(name.isEmpty(), "Fetched App Name is Empty");
	    Assertions.assertEquals(appName, name);
	}
	
	private void verifyQueryValue(Page page, String column, String value) {
	    Assertions.assertTrue(
	        NotebookPageUtils.validateQuery(page, column, value),
	        "Query output is not visible");
	}
	
	@Test
	public void CreateDiabetesRecordTemplate_test (@PWPage Page page) {
		
		appName = TemplateCreationUtils.createAppFromTemplate(page, TEMPLATE_NAME);

		verifyAppCreated(page);
		BlockSettingsUtils.closeBlockSettings(page);
		NotebookPageUtils.clickOnNotebooksOption(page);
		NotebookPageUtils.clickOnQueryName(page, QUERY_NAME);
		NotebookPageUtils.selectDatabaseFromDropdown(page, DATABASE_NAME);
		NotebookPageUtils.clickOnRunCellButton(page);
		verifyHeaders(
		        page,
		        "AGE",
		        "BLOODPRESSURE",
		        "BMI",
		        "DIABETES_UNIQUE_ROW_ID",
		        "DIABETESPEDIGREEFUNCTION",
		        "END_DATE",
		        "GLUCOSE",
		        "INSULIN",
		        "MILESTONE",
		        "OUTCOME",
		        "PREGNANCIES",
		        "SKINTHICKNESS",
		        "START_DATE",
		        "TASK_GROUP",
		        "TASK_NAME",
		        "TOOLTIP");
	}
	

	@Test
	public void ValidateupdatedDiabetesRecord_test (@PWPage Page page) {
		
		appName = TemplateCreationUtils.createAppFromTemplate(page, TEMPLATE_NAME);

		verifyAppCreated(page);
		AppTemplatePageUtils.clickPreviewButton(page);
		NotebookPageUtils.addValueInField(page, "ID", "1234");
		NotebookPageUtils.addValueInField(page, "AGE", "23");
		NotebookPageUtils.addValueInField(page, "GENDER", "Male");
		NotebookPageUtils.addValueInField(page, "LOCATION", "Delhi");
        System.out.println("Before Clicking add record");
		NotebookPageUtils.clickOnRecordButton(page, "Add record");
        System.out.println("after Clicking add record");
		NotebookPageUtils.checkSuccessMessage(page, "true");

	}
	
	
	@Test
	public void CreateDiabetesRecordTemplateQueryValidation_test (@PWPage Page page) {
		String query= "SELECT * from diabetes WHERE ID=16767 AND AGE=35 AND LOCATION='Pune' AND GENDER='Male'";
		
		appName = TemplateCreationUtils.createAppFromTemplate(page, TEMPLATE_NAME);

		verifyAppCreated(page);
		AppTemplatePageUtils.clickPreviewButton(page);
		NotebookPageUtils.addValueInField(page, "ID", "16767");
		NotebookPageUtils.addValueInField(page, "AGE", "35");
		NotebookPageUtils.addValueInField(page, "GENDER", "Male");
		NotebookPageUtils.addValueInField(page, "LOCATION", "Pune");
		NotebookPageUtils.clickOnRecordButton(page, "Add record");
		NotebookPageUtils.checkSuccessMessage(page, "true");
		AppTemplatePageUtils.closePreviewWindow(page);
		NotebookPageUtils.clickOnNotebooksOption(page);
		NotebookPageUtils.clickOnQueryName(page, QUERY_NAME);
		NotebookPageUtils.selectDatabaseType(page, DATABASE_NAME);
		NotebookPageUtils.clickOnRunCellButtonDatabase(page);
		NotebookPageUtils.checkDatabaseOutput(page);
		NotebookPageUtils.modifySqlQuery(page, query);
		NotebookPageUtils.clickOnRunCellButton(page);
		
		verifyQueryValue(page, "AGE", "35");
		verifyQueryValue(page, "LOCATION", "Pune");
		verifyQueryValue(page, "GENDER", "Male");
	}

}
	    
