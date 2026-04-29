package aicore.unit.app.DragAndDropApp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import aicore.pages.database.AddDatabaseFormUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.utils.AbstractE2ETest;
import aicore.utils.AddDatabasePageUtils;
import aicore.utils.AddFunctionPageUtils;
import aicore.utils.CatalogCreationFromZipUtil;
import aicore.utils.CatalogPageUtils;
import aicore.utils.CatlogAccessPageUtility;
import aicore.utils.CommonUtils;
import aicore.utils.page.app.AppPageUtils;
import aicore.utils.page.app.CreateAppPopupUtils;
import aicore.utils.page.app.DragAndDropBlocksPageUtils;
import aicore.utils.page.app.NotebookPageUtils;

public class DragAndDropExportTests extends AbstractE2ETest {

	private static String appType = "Drag and Drop";
	private static String appName = "Test app";
	private static String catalog = "Database";
	private static String catalogName = "TestDatabase";
	private String frameID;
	String timestamp = CommonUtils.getTimeStampName();

	@BeforeAll
	static void setup() {
		login(page, UserType.NATIVE);
	}

	@BeforeEach
	void setupForCreateDatabaseAndAPP() throws Exception {
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenDatabase(page);
		// Check database is present if not create new one
		AddFunctionPageUtils.deleteCatalog(page, catalog, catalogName);
		AddDatabaseFormUtils.clickAddDatabaseButton(page);
		CatalogCreationFromZipUtil.clickOnFileUploadIcon(page);
		CatalogCreationFromZipUtil.uploadFile(page, "Database/TestDatabase.zip");
		CatalogCreationFromZipUtil.clickOnUploadButton(page, "Upload");
		CatlogAccessPageUtility.getCatalogAndCopyId(page);
		AddDatabasePageUtils.verifyDatabaseTitle(page, catalogName);
		CatalogPageUtils.clickOnMetadataTab(page);
		AddDatabasePageUtils.clickOnRefreshButton(page);
		AddDatabasePageUtils.clickApplyDatabaseButton(page);
		AddDatabasePageUtils.verifyMetaData(page);
		AddDatabasePageUtils.clickOnSaveButton(page);

		// Click on Create New App button - using appPage
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenAppLibrary(page);
		AppPageUtils.clickOnCreateNewAppButton(page);
		CreateAppPopupUtils.clickOnGetStartedButton(page, appType);
		CreateAppPopupUtils.enterAppName(page, appName + timestamp);
		CreateAppPopupUtils.clickOnCreateButton(page);
		CreateAppPopupUtils.userFetchAppName(page);
		// Verify page is loaded
		DragAndDropBlocksPageUtils.verifyPage1IsVisible(page);
	}

	@AfterEach
	void tearDownEachTest() {
		CommonUtils.navigateAndDeleteApp(page, appName);
		CommonUtils.navigateAndDeleteCatalog(page, catalog, catalogName);
	}

	@Test
	@DisplayName("Validate Export Data functionality")
	public void testExportDataFunctionality() throws Exception {
		List<String> fields = Arrays.asList("AGE", "BLOODPRESSURE", "BMI", "DIABETESPEDIGREEFUNCTION",
				"DIABETES_UNIQUE_ROW_IDFK", "END_DATE", "GLUCOSE", "INSULIN", "MILESTONE", "OUTCOME", "PREGNANCIES",
				"SKINTHICKNESS", "START_DATE", "TASK_GROUP", "TASK_NAME", "TOOLTIP");
		DragAndDropBlocksPageUtils.clickOnBlockSettingsOption(page);
		DragAndDropBlocksPageUtils.clickOnBlocksOption(page);
		NotebookPageUtils.clickOnNotebooksOption(page);
		NotebookPageUtils.clickOnCreateNewNotebook(page);
		NotebookPageUtils.enterQueryName(page, "Test");
		NotebookPageUtils.clickOnQuerySubmitButton(page);
		NotebookPageUtils.mouseHoverOnNotebookHiddenOptions(page);
		NotebookPageUtils.clickOnHiddenNotebookOption(page, "Import Data");
		NotebookPageUtils.selectHiddenOptionDropdown(page, "From Data Catalog");
		NotebookPageUtils.selectDatabaseFromDropdown(page, catalogName);
		List<String> uiColumns = NotebookPageUtils.checkColumnNamesOnUI(page);
		Assertions.assertEquals(fields, uiColumns, "columns are not matching");
		NotebookPageUtils.selectAllColumns(page);
		NotebookPageUtils.clickOnImportButton(page);
		NotebookPageUtils.deleteFirstCell(page);
		NotebookPageUtils.selectTypeFromDropdown(page, "Python");
		NotebookPageUtils.enterDataLimit(page, "20");
		NotebookPageUtils.clickOnRunCellButton(page);
		frameID = NotebookPageUtils.getFrameID(page);
		NotebookPageUtils.getNotebookOutputTableHeader(page);
		DragAndDropBlocksPageUtils.clickOnExportOption(page);
		boolean isExportDataSectionVisible = DragAndDropBlocksPageUtils.isExportDataSectionVisible(page);
		Assertions.assertTrue(isExportDataSectionVisible, "Export Data section is not visible");
		DragAndDropBlocksPageUtils.selectFrameForExportData(page, frameID);
		DragAndDropBlocksPageUtils.selectFileTypeAs(page, "csv");
		DragAndDropBlocksPageUtils.clickOnExportOption(page, "Create Export Button");
		boolean isFileExported = DragAndDropBlocksPageUtils.isExportedFileVisibleInNotebookSection(page, frameID,
				"csv");
		Assertions.assertTrue(isFileExported, "Exported file is not visible in Notebook section");

	}

	@Test
	@DisplayName("Validate Direct Export Data functionality")
	void testDirectExportFunctionality() throws Exception {
		String expectedToastMessage = "Exporting data from ";
		List<String> fields = Arrays.asList("AGE", "BLOODPRESSURE", "BMI", "DIABETESPEDIGREEFUNCTION",
				"DIABETES_UNIQUE_ROW_IDFK", "END_DATE", "GLUCOSE", "INSULIN", "MILESTONE", "OUTCOME", "PREGNANCIES",
				"SKINTHICKNESS", "START_DATE", "TASK_GROUP", "TASK_NAME", "TOOLTIP");
		DragAndDropBlocksPageUtils.clickOnBlockSettingsOption(page);
		DragAndDropBlocksPageUtils.clickOnBlocksOption(page);
		NotebookPageUtils.clickOnNotebooksOption(page);
		NotebookPageUtils.clickOnCreateNewNotebook(page);
		NotebookPageUtils.enterQueryName(page, "Test");
		NotebookPageUtils.clickOnQuerySubmitButton(page);
		NotebookPageUtils.mouseHoverOnNotebookHiddenOptions(page);
		NotebookPageUtils.clickOnHiddenNotebookOption(page, "Import Data");
		NotebookPageUtils.selectHiddenOptionDropdown(page, "From Data Catalog");
		NotebookPageUtils.selectDatabaseFromDropdown(page, catalogName);
		List<String> uiColumns = NotebookPageUtils.checkColumnNamesOnUI(page);
		Assertions.assertEquals(fields, uiColumns, "columns are not matching");
		NotebookPageUtils.selectAllColumns(page);
		NotebookPageUtils.clickOnImportButton(page);
		NotebookPageUtils.deleteFirstCell(page);
		NotebookPageUtils.selectTypeFromDropdown(page, "Python");
		NotebookPageUtils.enterDataLimit(page, "20");
		NotebookPageUtils.clickOnRunCellButton(page);
		frameID = NotebookPageUtils.getFrameID(page);
		NotebookPageUtils.getNotebookOutputTableHeader(page);
		DragAndDropBlocksPageUtils.clickOnExportOption(page);
		boolean isExportDataSectionVisible = DragAndDropBlocksPageUtils.isExportDataSectionVisible(page);
		Assertions.assertTrue(isExportDataSectionVisible, "Export Data section is not visible");
		DragAndDropBlocksPageUtils.selectFrameForExportData(page, frameID);
		DragAndDropBlocksPageUtils.selectFileTypeAs(page, "tsv");
		DragAndDropBlocksPageUtils.setDirectExportToggle(page, "Turn ON");
		DragAndDropBlocksPageUtils.clickOnExportOption(page, "Export Data");
		// verify toaster message
		String actualToastMessage = DragAndDropBlocksPageUtils.getExportDataSuccessToastMessage(page);
		assertEquals(expectedToastMessage + frameID + "...", actualToastMessage,
				"Export data success toast message is incorrect");
		// Verify file is download in download folder
		boolean isValid = DragAndDropBlocksPageUtils.isDownloadedFileVisible(page, "tsv");
		Assertions.assertTrue(isValid, "Expected file format: " + "tsv" + " but got different file");
		// verify file display in notebook section
		boolean isExportedFileVisible = DragAndDropBlocksPageUtils.isExportedFileVisibleInNotebookSection(page, frameID,
				"tsv");
		Assertions.assertTrue(isExportedFileVisible, "Exported file is not visible in Notebook section");
	}
}