package aicore.unit.DragAndDrop.charts;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.microsoft.playwright.Page;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.AddDatabasePageUtils;
import aicore.utils.CommonUtils;
import aicore.utils.DatabaseTestUtils;
import aicore.utils.TestResources;
import aicore.utils.annotations.PWPage;
import aicore.utils.annotations.ResourceUploadLock;
import aicore.utils.page.app.BlockSettingsUtils;
import aicore.utils.page.app.CreateAppPopupUtils;
import aicore.utils.page.app.DragAndDropBlocksPageUtils;
import aicore.utils.page.app.NotebookPageUtils;
import aicore.utils.page.app.TemplateCreationUtils;
public class DragAndDropDataGrid extends AbstractPlaywrightTestBase{
	
	private String appName;

	@BeforeEach
	@ResourceUploadLock(TestResources.TEST_DATABASE_ZIP)
	void setup(@PWPage Page page) {
		loginNativeAdmin(page);
		String databaseId = DatabaseTestUtils.uploadDatabaseZip(
		        page,
		        "TestDatabase",
		        TestResources.TEST_DATABASE_ZIP);

		Assertions.assertNotNull(databaseId);
		Assertions.assertFalse(databaseId.isBlank());
		
		verifyCatalogTitle(page, "TestDatabase");
		AddDatabasePageUtils.clickOnMetadataTab(page);
		appName = TemplateCreationUtils.createDragAndDropApp(page, "Drag and Drop");
		verifyAppCreated(page);
		verifyWelcomePage(page);
		DragAndDropBlocksPageUtils.clickOnBlocksOption(page);
		NotebookPageUtils.clickOnNotebooksOption(page);
		NotebookPageUtils.clickOnCreateNewNotebook(page);
		NotebookPageUtils.enterQueryName(page, "Test");
		NotebookPageUtils.clickOnQuerySubmitButton(page);
		NotebookPageUtils.mouseHoverOnNotebookHiddenOptions(page);

		NotebookPageUtils.clickOnHiddenNotebookOption(page, "Import Data");
		NotebookPageUtils.selectHiddenOptionDropdown(page, "Query Builder");
		NotebookPageUtils.selectDatabaseFromDropdown(page, "TestDatabase");

		verifyFieldsColumnNames(
		        page,
		        "Age",
		        "BloodPressure",
		        "BMI",
		        "DiabetesPedigreeFunction",
		        "DIABETES_UNIQUE_ROW_ID",
		        "End_Date",
		        "Glucose",
		        "Insulin",
		        "Milestone",
		        "Outcome",
		        "Pregnancies",
		        "SkinThickness",
		        "Start_Date",
		        "Task_Group",
		        "Task_Name",
		        "Tooltip");
		NotebookPageUtils.selectAllColumns(page);
		NotebookPageUtils.clickOnImportButton(page);
		NotebookPageUtils.deleteFirstCell(page);
		NotebookPageUtils.clickOnRunCellButton(page);
		fetchFrameId(page);	

	}	

	@AfterEach
	void tearDown(@PWPage Page page) {
		CommonUtils.navigateAndDeleteApp(page, appName);
	    logout(page);
	}
	
	private void verifyWelcomePage(Page page) {
	    Assertions.assertTrue(
	            DragAndDropBlocksPageUtils.verifyPage1IsVisible(page),
	            "Page is not visible");

	    Assertions.assertTrue(
	            DragAndDropBlocksPageUtils.verifyWelcomeTextboxIsVisible(page),
	            "Welcome text box is not visible");

	    Assertions.assertEquals(
	            "Welcome to the UI Builder! Drag and drop blocks to use in your app.",
	            DragAndDropBlocksPageUtils.verifyWelcomeText(page),
	            "Mismatch between the expected and actual welcome message");
	}
	
	
	private void verifyFieldsColumnNames(Page page, String... expectedColumns) {
	    List<String> actualColumns = NotebookPageUtils.checkColumnNamesOnUI(page);

	    Assertions.assertEquals(
	            Arrays.asList(expectedColumns),
	            actualColumns,
	            "Columns are not matching");
	}
	private void verifyAppCreated(Page page) {
	    String appName = CreateAppPopupUtils.userFetchAppName(page);
	    Assertions.assertFalse(appName.isEmpty(), "Fetched App Name is Empty");
	}

	private void verifyCatalogTitle(Page page, String expectedDatabaseName) {
	    Assertions.assertTrue(
	            AddDatabasePageUtils.verifyDatabaseTitle(page, expectedDatabaseName),
	            "Database title is not visible");
	}
	
	private String fetchFrameId(Page page) {
	    String frameID = NotebookPageUtils.getFrameID(page);

	    Assertions.assertNotNull(frameID, "Frame ID was not fetched");
	    Assertions.assertFalse(frameID.isBlank(), "Frame ID is empty");

	    return frameID;
	}
	
	private void verifyDataGridColumnNames(Page page, String... expectedColumns) {
	    Assertions.assertEquals(
	            Arrays.asList(expectedColumns),
	            DragAndDropBlocksPageUtils.checkDataGridColumnNamesOnUI(page),
	            "Data Grid columns are not matching");
	}
	
	private void verifyColumnNotPresent(Page page, String columnName) {
	    List<String> uiColumns = DragAndDropBlocksPageUtils.checkDataGridColumnNamesOnUI(page);

	    Assertions.assertFalse(
	            uiColumns.contains(columnName),
	            "Data Grid still contains the removed column " + columnName);
	}
	
	private void verifyPaginationOptions(Page page, String... rowsPerPageOptions) {
	    DragAndDropBlocksPageUtils.validatePaginationForRowsPerPageOptions(
	            page,
	            Arrays.asList(rowsPerPageOptions));
	}
	private void configureBlock(Page page, String pageName, String blockName) {
	    DragAndDropBlocksPageUtils.selectPage(page, pageName);
	    DragAndDropBlocksPageUtils.clickOnBlocksOption(page);
	    DragAndDropBlocksPageUtils.mouseHoverOnBlock(page, blockName);
	    DragAndDropBlocksPageUtils.blockDropPosition(page, blockName);
	    DragAndDropBlocksPageUtils.clickOnDroppedBlock(page, blockName);
	    BlockSettingsUtils.clickOnBlockSettingsOption(page);
	    BlockSettingsUtils.clickOnDataTab(page);
	    fetchFrameId(page);
	    DragAndDropBlocksPageUtils.clickOnSyncChangesButton(page);
	}
	
	
	

	@Test
    public void validateDataGridPagination_test(@PWPage Page page) {
		
		configureBlock(page, "page-1", "Data Grid");
		verifyDataGridColumnNames(
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
		DragAndDropBlocksPageUtils.removeColumnFromDataGrid(page, "AGE");
		DragAndDropBlocksPageUtils.clickOnSyncChangesButton(page);
		verifyColumnNotPresent(page, "AGE");   
	}
	
	@Test
    public void DragAndDropDataGridvalidatePagnation_test(@PWPage Page page) {
		
		configureBlock(page, "page-1", "Data Grid");
	    fetchFrameId(page);
		verifyPaginationOptions(
		        page,
		        "10",
		        "50",
		        "100");
	}
}
