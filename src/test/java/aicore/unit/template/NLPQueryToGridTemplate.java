package aicore.unit.template;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;

import aicore.pages.database.DatabaseCreationUtils;
import aicore.pages.home.HomePageUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.pages.model.EditModelPageUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.AddDatabasePageUtils;
import aicore.utils.CatalogCreationFromZipUtil;
import aicore.utils.CommonUtils;
import aicore.utils.DatabaseTestUtils;
import aicore.utils.TestResourceTrackerHelper;
import aicore.utils.annotations.PWPage;
import aicore.utils.page.app.AppTemplatePageUtils;
import aicore.utils.page.app.CreateAppPopupUtils;
import aicore.utils.page.app.DragAndDropBlocksPageUtils;
import aicore.utils.page.app.NotebookPageUtils;
import aicore.utils.page.app.TemplateCreationUtils;
import aicore.utils.page.model.ModelPageUtils;

public class NLPQueryToGridTemplate extends AbstractPlaywrightTestBase {
	
	String appName = "Default Name Test App";

	private static final String TEMPLATE_NAME = "NLP Query To Grid";
	private static final String DATABASE_NAME = "TestDatabase";
	private static final String FILE_NAME = "TestDatabase.zip";
	private static final String MODEL_NAME = "Llama3-70B-Instruct";

	@BeforeEach
	void setup(@PWPage Page page) {
		loginNativeAdmin(page);
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
	
	public static void deleteTestModel(Page page) {
	    CommonUtils.navigateAndDeleteCatalog(
	        page,
	        TestResourceTrackerHelper.CATALOG_TYPE_MODEL,
	        MODEL_NAME
	    );
	}
	@Test
	public void NLPQueryToGridTemplate_test (@PWPage Page page) {
		
		String databaseId = DatabaseTestUtils.uploadDatabaseZip(
		        page,
		        "TestDatabase",
		        "Database/TestDatabase.zip");

		Assertions.assertNotNull(databaseId);
		Assertions.assertFalse(databaseId.isBlank());		
		HomePageUtils.navigateToHomePage(page);
        MainMenuUtils.openMainMenu(page);
        MainMenuUtils.clickOnOpenDatabase(page);

		
//		Assertions.assertTrue(
//			    AddDatabasePageUtils.verifyDatabaseTitle(page, DATABASE_NAME),
//			    "Database title is not visible"
//			);
		
        HomePageUtils.navigateToHomePage(page);
        MainMenuUtils.openMainMenu(page);
        MainMenuUtils.clickOnOpenModel(page);
        
        if(EditModelPageUtils.checkIfModelIsDisplayedOnCatalogPage(page, MODEL_NAME)) {
        	deleteTestModel(page);
        }
        
		ModelPageUtils.clickAddModelButton(page);
		CatalogCreationFromZipUtil.clickOnFileUploadIcon(page);
        
        CatalogCreationFromZipUtil.uploadFile(page, "Model/ModelZIP.zip");

		CatalogCreationFromZipUtil.clickOnUploadButton(page, "Upload");

		Assertions.assertTrue(
			    AddDatabasePageUtils.verifyDatabaseTitle(page, MODEL_NAME),
			    "Database title '" + DATABASE_NAME + "' is not visible");
		
		
		
		appName = TemplateCreationUtils.createAppFromTemplate(page, TEMPLATE_NAME);
		verifyAppCreated(page);
		
		Assertions.assertEquals(
			    "page-1",
			    AppTemplatePageUtils.userSeePage1(page),
			    "Expected and actual text do not match"
			);
		
		
		Assertions.assertEquals(
			    "Natural Language Query to Grid",
			    AppTemplatePageUtils.userSeeTeamplatePageTitle(page),
			    "Expected and actual block title do not match"
			);
		
		
		AppTemplatePageUtils.verifyDescriptionBelowTitle("Ask your query on the diabetes dataset", page);
		NotebookPageUtils.clickOnNotebooksOption(page);
		AppTemplatePageUtils.selectNotebookFromlist(page, "nlp-query");
		AppTemplatePageUtils.selectModelForNLPTemplate(page, MODEL_NAME, "nlp-query-1");

		NotebookPageUtils.clickOnRunAllCellButton(page);

		DragAndDropBlocksPageUtils.selectPage(page, "page-1");

        System.out.println("Dialogs before opening preview = "
        	    + page.locator("div[role='dialog']").count());
        
		AppTemplatePageUtils.clickPreviewButton(page);
		AppTemplatePageUtils.enterQueryForNLPTemplate(page, "people over the age 50");


		AppTemplatePageUtils.clickOnFetchDataButton(page);
		Assertions.assertTrue(
			    AppTemplatePageUtils.validateAges(page, "above", 50),
			    "Validation failed. Some records do not satisfy the condition: over 50"
			);
		

		AppTemplatePageUtils.enterQueryForNLPTemplate(page, "people below the age 50");

		AppTemplatePageUtils.clickOnFetchDataButton(page);
		Assertions.assertTrue(
			    AppTemplatePageUtils.validateAges(page, "below", 50),
			    "Validation failed. Some records do not satisfy the condition: over 50"
			);
//		AppTemplatePageUtils.closePreviewWindow(page);
		// there's no dedicated "close preview" button --- clicking outside the preview popup
		// will close it
		AppTemplatePageUtils.clickOutsideThePreviewPopup(page);

		DragAndDropBlocksPageUtils.clickOnSaveAppButton(page);
		
		deleteTestModel(page);
		CommonUtils.navigateAndDeleteCatalog(
		        page,
		        TestResourceTrackerHelper.CATALOG_TYPE_DATABASE,
		        DATABASE_NAME
		    );
	}

}
