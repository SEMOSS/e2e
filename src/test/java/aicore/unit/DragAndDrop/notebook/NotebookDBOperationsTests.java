package aicore.unit.DragAndDrop.notebook;

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
import aicore.utils.AddFunctionPageUtils;
import aicore.utils.CatalogCreationFromZipUtil;
import aicore.utils.CatalogPageUtils;
import aicore.utils.CatlogAccessPageUtility;
import aicore.utils.CommonUtils;
import aicore.utils.annotations.PWPage;
import aicore.utils.page.app.AppPageUtils;
import aicore.utils.page.app.BlockSettingsUtils;
import aicore.utils.page.app.CreateAppPopupUtils;
import aicore.utils.page.app.DragAndDropBlocksPageUtils;
import aicore.utils.page.app.NotebookPageUtils;


public class NotebookDBOperationsTests extends AbstractPlaywrightTestBase {

	private static final String APP_NAME = "Test app";
	private static final String CATALOG_TYPE = "Database";
	private static final String CATALOG_NAME = "TestDatabase";

	private String timestamp = "";
	private String frameID = "";

	@BeforeEach
	void setup(@PWPage Page page) {
		timestamp = CommonUtils.getTimeStampName();

		loginAdmin(page);

		HomePageUtils.navigateToHomePage(page);
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenDatabase(page);
		AddFunctionPageUtils.deleteCatalog(page, CATALOG_TYPE, CATALOG_NAME);
		AddDatabaseFormUtils.clickAddDatabaseButton(page);
		CatalogCreationFromZipUtil.clickOnFileUploadIcon(page);
		String uploadedFileName = CatalogCreationFromZipUtil.uploadFile(page, "Database/TestDatabase.zip");
		Assertions.assertEquals("TestDatabase.zip", uploadedFileName, "file is not uploaded successfully");
		CatalogCreationFromZipUtil.clickOnUploadButton(page, "Upload");
		CatlogAccessPageUtility.getCatalogAndCopyId(page);
		boolean isTitleVisible = AddDatabasePageUtils.verifyDatabaseTitle(page, CATALOG_NAME);
		Assertions.assertTrue(isTitleVisible, "Database title is not visible");
		CatalogPageUtils.clickOnMetadataTab(page);

		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenAppLibrary(page);
		AppPageUtils.clickOnCreateNewAppButton(page);
		CreateAppPopupUtils.clickOnGetStartedButton(page, "Drag and Drop");
		CreateAppPopupUtils.enterAppName(page, APP_NAME + timestamp);
		CreateAppPopupUtils.clickOnCreateButton(page);
		String fetchName = CreateAppPopupUtils.userFetchAppName(page);
		Assertions.assertFalse(fetchName.isEmpty(), "Fetched App Name is Empty");

		boolean isPage1Visible = DragAndDropBlocksPageUtils.verifyPage1IsVisible(page);
		Assertions.assertTrue(isPage1Visible, "Page is not visible");
		boolean isWelcomeTextboxVisible = DragAndDropBlocksPageUtils.verifyWelcomeTextboxIsVisible(page);
		Assertions.assertTrue(isWelcomeTextboxVisible, "Welcome text box not visible");
		String actualWelcomeTextMessage = DragAndDropBlocksPageUtils.verifyWelcomeText(page);
		Assertions.assertEquals("Welcome to the UI Builder! Drag and drop blocks to use in your app.",
				actualWelcomeTextMessage, "Mismatch between the expected and actual message");

		BlockSettingsUtils.closeBlockSettings(page);
	}

	@AfterEach
	void tearDown(@PWPage Page page) {
		CommonUtils.navigateAndDeleteApp(page, APP_NAME + timestamp);
		CommonUtils.navigateAndDeleteCatalog(page, CATALOG_TYPE, CATALOG_NAME);
		logout(page);
	}

	
	private void navigateToAppAndOpenNotebookTab(Page page) {
		HomePageUtils.navigateToHomePage(page);
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenAppLibrary(page);
		AppPageUtils.searchApp(page, APP_NAME, timestamp);
		AppPageUtils.clickOnAppCard(page, APP_NAME, timestamp);
		DragAndDropBlocksPageUtils.clickOnEditButton(page);
		DragAndDropBlocksPageUtils.clickOnBlocksOption(page);
		NotebookPageUtils.clickOnNotebooksOption(page);
	}

	@Test
	@DisplayName("Validate import db query functionality")
	void testValidateImportDbQueryFunctionality(@PWPage Page page) {
		navigateToAppAndOpenNotebookTab(page);

		NotebookPageUtils.clickOnCreateNewNotebook(page);
		NotebookPageUtils.enterQueryName(page, "Test");
		NotebookPageUtils.clickOnQuerySubmitButton(page);
		NotebookPageUtils.mouseHoverOnNotebookHiddenOptions(page);
		NotebookPageUtils.clickOnHiddenNotebookOption(page, "Import Data");
		NotebookPageUtils.selectHiddenOptionDropdown(page, "Custom Import (SQL)");
		NotebookPageUtils.selectDatabaseType(page, CATALOG_NAME);
		NotebookPageUtils.deleteFirstCell(page);
		NotebookPageUtils.writeQuery(page, "SELECT * FROM DIABETES where Age = 50 AND BloodPressure = 90");
		NotebookPageUtils.clickOnRunCellButton(page);

		boolean ageMatches = NotebookPageUtils.validateQuery(page, "AGE", "50");
		Assertions.assertTrue(ageMatches, "Query output is not visible");
		boolean bloodPressureMatches = NotebookPageUtils.validateQuery(page, "BLOODPRESSURE", "90");
		Assertions.assertTrue(bloodPressureMatches, "Query output is not visible");
	}

	@Test
	@DisplayName("Import Data")
	void testImportData(@PWPage Page page) {
		navigateToAppAndOpenNotebookTab(page);
		importDataFromCatalogAndRun(page);
	}

	@Test
	@DisplayName("Import Data - Edit button")
	void testImportDataEditButton(@PWPage Page page) {
		navigateToAppAndOpenNotebookTab(page);
		importDataFromCatalogAndRun(page);

		NotebookPageUtils.clickOnEditButtonForImportedDataCell(page);
		NotebookPageUtils.uncheckColumnFromSelectedColumns(page, "Age");
		NotebookPageUtils.clickOnUpdateCellButton(page);
		NotebookPageUtils.clickOnRunCellButton(page);

		java.util.List<String> expectedHeaderNamesAfterEdit = java.util.Arrays.asList("BloodPressure", "BMI",
				"DIABETES_UNIQUE_ROW_ID", "DiabetesPedigreeFunction", "End_Date", "Glucose", "Insulin", "Milestone",
				"Outcome", "Pregnancies", "SkinThickness", "Start_Date", "Task_Group", "Task_Name", "Tooltip");
		java.util.List<String> actualHeaderNamesAfterEdit = NotebookPageUtils.getNotebookOutputTableHeader(page);
		Assertions.assertEquals(expectedHeaderNamesAfterEdit, actualHeaderNamesAfterEdit, "Headers are not matching");
	}

	
	private void importDataFromCatalogAndRun(Page page) {
		NotebookPageUtils.clickOnCreateNewNotebook(page);
		NotebookPageUtils.enterQueryName(page, "Test");
		NotebookPageUtils.clickOnQuerySubmitButton(page);
		NotebookPageUtils.mouseHoverOnNotebookHiddenOptions(page);
		NotebookPageUtils.clickOnHiddenNotebookOption(page, "Import Data");
		NotebookPageUtils.selectHiddenOptionDropdown(page, "From Data Catalog");
		NotebookPageUtils.selectDatabaseFromDropdown(page, CATALOG_NAME);

		java.util.List<String> expectedFieldColumns = java.util.Arrays.asList("Age", "BMI", "BloodPressure",
				"DIABETES_UNIQUE_ROW_IDFK", "DiabetesPedigreeFunction", "End_Date", "Glucose", "Insulin", "Milestone",
				"Outcome", "Pregnancies", "SkinThickness", "Start_Date", "Task_Group", "Task_Name", "Tooltip");
		java.util.List<String> actualFieldColumns = NotebookPageUtils.checkColumnNamesOnUI(page);
		Assertions.assertEquals(expectedFieldColumns, actualFieldColumns, "columns are not matching");

		NotebookPageUtils.selectAllColumns(page);
		NotebookPageUtils.clickOnImportButton(page);
		NotebookPageUtils.deleteFirstCell(page);
		NotebookPageUtils.selectTypeFromDropdown(page, "Python");
		NotebookPageUtils.enterDataLimit(page, "20");
		NotebookPageUtils.clickOnRunCellButton(page);
		frameID = NotebookPageUtils.getFrameID(page);

		java.util.List<String> expectedHeaderNames = java.util.Arrays.asList("Age", "BloodPressure", "BMI",
				"DIABETES_UNIQUE_ROW_ID", "DiabetesPedigreeFunction", "End_Date", "Glucose", "Insulin", "Milestone",
				"Outcome", "Pregnancies", "SkinThickness", "Start_Date", "Task_Group", "Task_Name", "Tooltip");
		java.util.List<String> actualHeaderNames = NotebookPageUtils.getNotebookOutputTableHeader(page);
		Assertions.assertEquals(expectedHeaderNames, actualHeaderNames, "Headers are not matching");

		int actualRowsCount = NotebookPageUtils.getTotalRowsFromPreviewCaption(page);
		Assertions.assertEquals(20, actualRowsCount, "Rows count are not correct");

		boolean isColumnUnique = NotebookPageUtils.isColumnUniqueByHeader(page, "DIABETES_UNIQUE_ROW_ID");
		Assertions.assertTrue(isColumnUnique, "DIABETES_UNIQUE_ROW_ID have duplicate values");

		String jsonFrameId = NotebookPageUtils.validateJsonFieldValue(page, frameID);
		String cleanedActualFrameId = jsonFrameId.replaceAll("^\"|\"$", "");
		Assertions.assertEquals(frameID, cleanedActualFrameId, "Frame Id not matching");

		NotebookPageUtils.validateJsonFieldValue(page, "PY");

		DragAndDropBlocksPageUtils.clickOnSaveAppButton(page);
	}
}
