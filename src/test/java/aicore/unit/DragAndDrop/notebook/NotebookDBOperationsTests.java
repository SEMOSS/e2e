package aicore.unit.DragAndDrop.notebook;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;

import aicore.pages.database.AddDatabaseFormUtils;
import aicore.pages.home.HomePageUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.AddDatabasePageUtils;
import aicore.utils.CatalogCreationFromZipUtil;
import aicore.utils.CatalogPageUtils;
import aicore.utils.CatlogAccessPageUtility;
import aicore.utils.CommonUtils;
import aicore.utils.annotations.PWPage;
import aicore.utils.annotations.ResourceUploadLock;
import aicore.utils.page.app.AppPageUtils;
import aicore.utils.page.app.BlockSettingsUtils;
import aicore.utils.page.app.DragAndDropBlocksPageUtils;
import aicore.utils.page.app.NotebookPageUtils;
import aicore.utils.page.app.TemplateCreationUtils;
import aicore.utils.TestResources;

public class NotebookDBOperationsTests extends AbstractPlaywrightTestBase {
	private static final Logger logger = LogManager.getLogger(NotebookDBOperationsTests.class);
    SoftAssertions softAssert = new SoftAssertions();
	private static final String CATALOG_TYPE = "Database";
	private String appName = "";
	private String frameID = "";
	private static final String TEST_DATABASE_NAME = "TestDatabase"; 
	
	@BeforeEach
	void setup(@PWPage Page page) {
	    SoftAssertions softAssert = new SoftAssertions();

	    logger.info("BEFORE ALL: creating DataBase");
	    loginAdmin(page);
	    HomePageUtils.navigateToHomePage(page);
	    MainMenuUtils.openMainMenu(page);
	    MainMenuUtils.clickOnOpenDatabase(page);
	    AddDatabaseFormUtils.clickAddDatabaseButton(page);
	    CatalogCreationFromZipUtil.clickOnFileUploadIcon(page);
	    String uploadedFileName = CatalogCreationFromZipUtil.uploadFile(page, TestResources.TEST_DATABASE_ZIP);

	  
	    softAssert.assertThat(uploadedFileName)
	            .as("File name should not be null")
	            .isNotNull();
	    softAssert.assertThat(uploadedFileName)
	            .as("Should be a zip file")
	            .contains(".zip");

	    CatalogCreationFromZipUtil.clickOnUploadButton_New(page);
	    CatlogAccessPageUtility.getCatalogAndCopyId(page);

	   softAssert.assertThat(AddDatabasePageUtils.verifyDatabaseTitle(page, TEST_DATABASE_NAME))
	          .as("Database title is not visible")
	          .isTrue();

	    CatalogPageUtils.clickOnMetadataTab(page);

	    logger.info("BEFORE ALL: creating App");
	    appName = TemplateCreationUtils.createDragAndDropApp(page, "Drag and Drop");

	    softAssert.assertThat(DragAndDropBlocksPageUtils.verifyPage1IsVisible(page))
	            .as("Page is not visible")
	            .isTrue();
	    softAssert.assertThat(DragAndDropBlocksPageUtils.verifyWelcomeTextboxIsVisible(page))
	            .as("Welcome text box not visible")
	            .isTrue();

	   softAssert.assertThat(DragAndDropBlocksPageUtils.verifyWelcomeText(page))
	          .as("Mismatch between the expected and actual message")
	          .isEqualTo("Welcome to the UI Builder! Drag and drop blocks to use in your app.");

	    BlockSettingsUtils.closeBlockSettings(page);

	}

	

	@AfterEach
	void tearDown(@PWPage Page page) {
		logger.info("AFTER ALL: Deleting App and Catalog");
		CommonUtils.navigateAndDeleteApp(page, appName);
		CommonUtils.navigateAndDeleteCatalog(page, CATALOG_TYPE,  TEST_DATABASE_NAME );
		logout(page);
	}

	
	private void navigateToAppAndOpenNotebookTab(Page page) {
		HomePageUtils.navigateToHomePage(page);
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenAppLibrary(page);
		AppPageUtils.searchApp(page, appName, "");
		AppPageUtils.clickOnAppCard(page, appName, "");
		DragAndDropBlocksPageUtils.clickOnEditButton(page);
		DragAndDropBlocksPageUtils.clickOnBlocksOption(page);
		NotebookPageUtils.clickOnNotebooksOption(page);
	}
	
	
	private void importDataFromCatalogAndRun(Page page) {
	    NotebookPageUtils.clickOnCreateNewNotebook(page);
	    NotebookPageUtils.enterQueryName(page, "Test");
	    NotebookPageUtils.clickOnQuerySubmitButton(page);
	    NotebookPageUtils.mouseHoverOnNotebookHiddenOptions(page);
	    NotebookPageUtils.clickOnHiddenNotebookOption(page, "Import Data");
	    
	    NotebookPageUtils.selectHiddenOptionDropdown(page, "Query Builder");
	    
	    NotebookPageUtils.selectDatabaseFromDropdown(page, TEST_DATABASE_NAME);
	    
	    NotebookPageUtils.selectAllColumns(page);
	    NotebookPageUtils.clickOnImportButton(page);
	    
	    NotebookPageUtils.deleteFirstCell(page);
	    NotebookPageUtils.selectTypeFromDropdown(page, "Python");
	    NotebookPageUtils.enterDataLimit(page, "20");
	    NotebookPageUtils.clickOnRunCellButton(page);
	    frameID = NotebookPageUtils.getFrameID(page);
	    
	    
	    List<String> expectedHeaderNames = Arrays.asList("Age", "BloodPressure", "BMI",
	            "DIABETES_UNIQUE_ROW_ID", "DiabetesPedigreeFunction", "End_Date", "Glucose", "Insulin", "Milestone",
	            "Outcome", "Pregnancies", "SkinThickness", "Start_Date", "Task_Group", "Task_Name", "Tooltip");
	    
	    List<String> actualHeaderNames = NotebookPageUtils.getNotebookOutputTableHeader(page);
	    
	    softAssert.assertThat(actualHeaderNames)
	            .as("Headers are not matching")
	            .isEqualTo(expectedHeaderNames);
	    int actualRowsCount = NotebookPageUtils.getTotalRowsFromPreviewCaption(page);
	    
	    softAssert.assertThat(actualRowsCount)
	            .as("Rows count are not correct")
	            .isEqualTo(20);
	    boolean isUnique = NotebookPageUtils.isColumnUniqueByHeader(page, "DIABETES_UNIQUE_ROW_ID");
	    
	    softAssert.assertThat(isUnique)
	            .as("DIABETES_UNIQUE_ROW_ID have duplicate values")
	            .isTrue();	    
	    String jsonFrameId = NotebookPageUtils.validateJsonFieldValue(page, frameID);
	    String cleanedActualFrameId = jsonFrameId.replaceAll("^\"|\"$", "");
	    
	    softAssert.assertThat(cleanedActualFrameId)
	            .as("Frame Id not matching")
	            .isEqualTo(frameID);
	    
	    NotebookPageUtils.validateJsonFieldValue(page, "PY");
	    	    
	    DragAndDropBlocksPageUtils.clickOnSaveAppButton(page);
	}

	
	@Test
	@ResourceUploadLock(TestResources.TEST_DATABASE_ZIP)
	@DisplayName("TC01_Validate import db query functionality")
	void testValidateImportDbQueryFunctionality(@PWPage Page page) {
		
		navigateToAppAndOpenNotebookTab(page);

		NotebookPageUtils.clickOnCreateNewNotebook(page);
		NotebookPageUtils.enterQueryName(page, "Test");
		NotebookPageUtils.clickOnQuerySubmitButton(page);
		NotebookPageUtils.mouseHoverOnNotebookHiddenOptions(page);
		NotebookPageUtils.clickOnHiddenNotebookOption(page, "Import Data");
		NotebookPageUtils.selectHiddenOptionDropdown(page, "Custom Query");
		NotebookPageUtils.selectDatabaseType(page, TEST_DATABASE_NAME );
		NotebookPageUtils.deleteFirstCell(page);
		NotebookPageUtils.writeQuery(page, "SELECT * FROM DIABETES where Age = 50 AND BloodPressure = 90");
		NotebookPageUtils.clickOnRunCellButton(page);

		Assertions.assertTrue(NotebookPageUtils.validateQuery(page, "AGE", "50"), "Query output is not visible");
		Assertions.assertTrue(NotebookPageUtils.validateQuery(page, "BLOODPRESSURE", "90"), "Query output is not visible");
	}
	

	@Test
	@ResourceUploadLock(TestResources.TEST_DATABASE_ZIP)
	@DisplayName("TC02_Import Data")
	void testImportData(@PWPage Page page) {
		navigateToAppAndOpenNotebookTab(page);
		importDataFromCatalogAndRun(page);
	}

	@Test
	@ResourceUploadLock(TestResources.TEST_DATABASE_ZIP)
	@DisplayName("TC03_Import Data - Edit button")
	void testImportDataEditButton(@PWPage Page page) {
		navigateToAppAndOpenNotebookTab(page);
		importDataFromCatalogAndRun(page);

		NotebookPageUtils.clickOnEditButtonForImportedDataCell(page);
		NotebookPageUtils.uncheckColumnFromSelectedColumns(page, "Age");
		NotebookPageUtils.clickOnUpdateCellButton(page);
		NotebookPageUtils.clickOnRunCellButton(page);

		List<String> expectedHeaderNamesAfterEdit = Arrays.asList("BloodPressure", "BMI",
				"DIABETES_UNIQUE_ROW_ID", "DiabetesPedigreeFunction", "End_Date", "Glucose", "Insulin", "Milestone",
				"Outcome", "Pregnancies", "SkinThickness", "Start_Date", "Task_Group", "Task_Name", "Tooltip");
		List<String> actualHeaderNamesAfterEdit = NotebookPageUtils.getNotebookOutputTableHeader(page);
		Assertions.assertEquals(expectedHeaderNamesAfterEdit, actualHeaderNamesAfterEdit, "Headers are not matching");
	}

	
	
}
