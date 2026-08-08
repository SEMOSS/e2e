package aicore.unit.DragAndDrop.notebook;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;

import aicore.pages.database.AddDatabaseFormUtils;
import aicore.pages.home.HomePageUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.unit.function.AddFunctionFromZipTests;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.AddDatabasePageUtils;
import aicore.utils.AddFunctionPageUtils;
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
	private static final String CATALOG_TYPE = "Database";
	private static final String CATALOG_NAME = "TestDatabase";

	private String appName = "";
	private String frameID = "";

	@BeforeEach
	void setup(@PWPage Page page) {
		logger.info("BEFORE ALL: creating DataBase");

		loginAdmin(page);

		HomePageUtils.navigateToHomePage(page);
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenDatabase(page);
		AddFunctionPageUtils.deleteCatalogIfExists(page, CATALOG_TYPE,  TestResources.TEST_DATABASE_ZIP );
		AddDatabaseFormUtils.clickAddDatabaseButton(page);
		CatalogCreationFromZipUtil.clickOnFileUploadIcon(page);
		
		String uploadedFileName = CatalogCreationFromZipUtil.uploadFile(page, TestResources.TEST_DATABASE_ZIP);
		
		Assertions.assertEquals("TestDatabase.zip", uploadedFileName, "file is not uploaded successfully");
		CatalogCreationFromZipUtil.clickOnUploadButton(page, "Upload");
		CatlogAccessPageUtility.getCatalogAndCopyId(page);
		Assertions.assertTrue(AddDatabasePageUtils.verifyDatabaseTitle(page, TestResources.TEST_DATABASE_ZIP),
				"Database title is not visible");
		CatalogPageUtils.clickOnMetadataTab(page);

		
		logger.info("BEFORE ALL: creating App");

		appName = TemplateCreationUtils.createDragAndDropApp(page, "Drag and Drop");

		Assertions.assertTrue(DragAndDropBlocksPageUtils.verifyPage1IsVisible(page), "Page is not visible");
		Assertions.assertTrue(DragAndDropBlocksPageUtils.verifyWelcomeTextboxIsVisible(page),
				"Welcome text box not visible");
		Assertions.assertEquals("Welcome to the UI Builder! Drag and drop blocks to use in your app.",
				DragAndDropBlocksPageUtils.verifyWelcomeText(page), "Mismatch between the expected and actual message");

		BlockSettingsUtils.closeBlockSettings(page);
	}

	@AfterEach
	void tearDown(@PWPage Page page) {
		logger.info("AFTER ALL: Deleting App and Catalog");
		CommonUtils.navigateAndDeleteApp(page, appName);
		CommonUtils.navigateAndDeleteCatalog(page, CATALOG_TYPE,  TestResources.TEST_DATABASE_ZIP);
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
		NotebookPageUtils.selectHiddenOptionDropdown(page, "From Data Catalog");
		NotebookPageUtils.selectDatabaseFromDropdown(page, TestResources.TEST_DATABASE_ZIP);

		List<String> expectedFieldColumns = Arrays.asList("Age", "BMI", "BloodPressure",
				"DIABETES_UNIQUE_ROW_IDFK", "DiabetesPedigreeFunction", "End_Date", "Glucose", "Insulin", "Milestone",
				"Outcome", "Pregnancies", "SkinThickness", "Start_Date", "Task_Group", "Task_Name", "Tooltip");
		List<String> actualFieldColumns = NotebookPageUtils.checkColumnNamesOnUI(page);
		Assertions.assertEquals(expectedFieldColumns, actualFieldColumns, "columns are not matching");

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
		Assertions.assertEquals(expectedHeaderNames, actualHeaderNames, "Headers are not matching");

		int actualRowsCount = NotebookPageUtils.getTotalRowsFromPreviewCaption(page);
		Assertions.assertEquals(20, actualRowsCount, "Rows count are not correct");

		Assertions.assertTrue(NotebookPageUtils.isColumnUniqueByHeader(page, "DIABETES_UNIQUE_ROW_ID"),
				"DIABETES_UNIQUE_ROW_ID have duplicate values");

		String jsonFrameId = NotebookPageUtils.validateJsonFieldValue(page, frameID);
		String cleanedActualFrameId = jsonFrameId.replaceAll("^\"|\"$", "");
		Assertions.assertEquals(frameID, cleanedActualFrameId, "Frame Id not matching");

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
		NotebookPageUtils.selectHiddenOptionDropdown(page, "Custom Import (SQL)");
		NotebookPageUtils.selectDatabaseType(page, TestResources.TEST_DATABASE_ZIP);
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
