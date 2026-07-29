package aicore.unit.DragAndDrop.notebook;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.microsoft.playwright.Page;
import aicore.pages.database.AddDatabaseFormUtils;
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

public class NotebookDataFiltersTests extends AbstractPlaywrightTestBase {

	private static final String APP_NAME = "Test app";
	private static final String CATALOG_TYPE = "Database";
	private static final String CATALOG_NAME = "TestDatabase";

	private String timestamp = "";
	private String frameID = "";

	@BeforeEach
	void setup(@PWPage Page page) {
		timestamp = CommonUtils.getTimeStampName();

		loginAdmin(page);

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
		CreateAppPopupUtils.enterAppDescription(page, "Created by automation script");
		CreateAppPopupUtils.enterTags(page, "Test1, Test2");
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

		NotebookPageUtils.clickOnNotebooksOption(page);
		NotebookPageUtils.clickOnCreateNewNotebook(page);
		NotebookPageUtils.enterQueryName(page, "Test");
		NotebookPageUtils.clickOnQuerySubmitButton(page);
		NotebookPageUtils.mouseHoverOnNotebookHiddenOptions(page);
		NotebookPageUtils.clickOnHiddenNotebookOption(page, "Import Data");
		NotebookPageUtils.selectHiddenOptionDropdown(page, "From Data Catalog");
		NotebookPageUtils.selectDatabaseFromDropdown(page, CATALOG_NAME);

		List<String> expectedFieldColumns = Arrays.asList("Age", "BMI", "BloodPressure", "DIABETES_UNIQUE_ROW_IDFK",
				"DiabetesPedigreeFunction", "End_Date", "Glucose", "Insulin", "Milestone", "Outcome", "Pregnancies",
				"SkinThickness", "Start_Date", "Task_Group", "Task_Name", "Tooltip");
		List<String> actualFieldColumns = NotebookPageUtils.checkColumnNamesOnUI(page);
		Assertions.assertEquals(expectedFieldColumns, actualFieldColumns, "columns are not matching");

		NotebookPageUtils.selectAllColumns(page);
		NotebookPageUtils.clickOnImportButton(page);
		NotebookPageUtils.deleteFirstCell(page);
		NotebookPageUtils.selectTypeFromDropdown(page, "Python");
		NotebookPageUtils.enterDataLimit(page, "20");
		NotebookPageUtils.clickOnRunCellButton(page);
		frameID = NotebookPageUtils.getFrameID(page);

		List<String> expectedHeaderNames = Arrays.asList("Age", "BloodPressure", "BMI", "DIABETES_UNIQUE_ROW_ID",
				"DiabetesPedigreeFunction", "End_Date", "Glucose", "Insulin", "Milestone", "Outcome", "Pregnancies",
				"SkinThickness", "Start_Date", "Task_Group", "Task_Name", "Tooltip");
		List<String> actualHeaderNames = NotebookPageUtils.getNotebookOutputTableHeader(page);
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
		BlockSettingsUtils.closeBlockSettings(page);
	}

	@AfterEach
	void tearDown(@PWPage Page page) {
		CommonUtils.navigateAndDeleteApp(page, APP_NAME + timestamp);
		CommonUtils.navigateAndDeleteCatalog(page, CATALOG_TYPE, CATALOG_NAME);
		logout(page);
	}

	private void openNotebookAndStartDataFilter(Page page, String filterOption) {
		NotebookPageUtils.verifyNotebookIsPresentInList(page, "Test");
		NotebookPageUtils.clickOnNotebook(page, "Test");
		NotebookPageUtils.mouseHoverOnNotebookHiddenOptions(page);
		NotebookPageUtils.clickOnHiddenNotebookOption(page, "Data filters");
		NotebookPageUtils.selectHiddenOptionDropdown(page, filterOption);
		BlockSettingsUtils.selectFrame(page, frameID);
	}

	@Test
	@DisplayName("Verify Unfilter Data in the app")
	void testVerifyUnfilterDataInTheApp(@PWPage Page page) {
		openNotebookAndStartDataFilter(page, "Unfilter Data");
		NotebookPageUtils.deleteFirstCell(page);
		NotebookPageUtils.clickOnRunCellButton(page);

		List<String> expectedHeaderNames = Arrays.asList("Age", "BloodPressure", "BMI", "DIABETES_UNIQUE_ROW_ID",
				"DiabetesPedigreeFunction", "End_Date", "Glucose", "Insulin", "Milestone", "Outcome", "Pregnancies",
				"SkinThickness", "Start_Date", "Task_Group", "Task_Name", "Tooltip");
		List<String> actualHeaderNames = NotebookPageUtils.getNotebookOutputTableHeader(page);
		Assertions.assertEquals(expectedHeaderNames, actualHeaderNames, "Headers are not matching");

		int actualRowsCount = NotebookPageUtils.getTotalRowsFromPreviewCaption(page);
		Assertions.assertEquals(20, actualRowsCount, "Rows count are not correct");

		boolean isColumnUnique = NotebookPageUtils.isColumnUniqueByHeader(page, "DIABETES_UNIQUE_ROW_ID");
		Assertions.assertTrue(isColumnUnique, "DIABETES_UNIQUE_ROW_ID have duplicate values");
	}

	@Test
	@DisplayName("Verify filter Data in the app")
	void testVerifyFilterDataInTheApp(@PWPage Page page) {
		openNotebookAndStartDataFilter(page, "Filter Data");
		NotebookPageUtils.clickOnRuleButton(page, "Add Rule");
		NotebookPageUtils.selectColumnFromDropdown(page, "Age");
		NotebookPageUtils.selectOperatorFromDropdown(page, "Equals");
		NotebookPageUtils.enterValueInInput(page, "30");
		NotebookPageUtils.deleteFirstCell(page);
		NotebookPageUtils.clickOnRunCellButton(page);

		boolean isFilteredDataCorrect = NotebookPageUtils.isFilteredDataCorrect(page, "Age", "30");
		Assertions.assertTrue(isFilteredDataCorrect, "Filtered data is not correct for column: Age with value: 30");
	}

	@Test
	@DisplayName("Verify filter Data in the app with AND operator")
	void testVerifyFilterDataWithAndOperator(@PWPage Page page) {
		openNotebookAndStartDataFilter(page, "Filter Data");
		NotebookPageUtils.clickOnRuleButton(page, "Add Rule");
		NotebookPageUtils.selectColumnFromDropdown(page, "Age");
		NotebookPageUtils.selectOperatorFromDropdown(page, "Equals");
		NotebookPageUtils.enterValueInInput(page, "30");

		NotebookPageUtils.clickOnRuleButton(page, "Add Rule");
		NotebookPageUtils.getDefaultOperator(page, "AND");
		NotebookPageUtils.selectColumnFromDropdown(page, "BloodPressure");
		NotebookPageUtils.selectOperatorFromDropdown(page, "Equals");
		NotebookPageUtils.enterValueInInput(page, "92");

		NotebookPageUtils.deleteFirstCell(page);
		NotebookPageUtils.clickOnRunCellButton(page);

		List<String> columns = Arrays.asList("Age", "BloodPressure");
		List<String> values = Arrays.asList("30", "92");
		boolean result = NotebookPageUtils.isFilteredDataCorrectForColumns(page, columns, values, "AND");
		Assertions.assertTrue(result, "Filtered data does not match for columns: " + columns + " with values: "
				+ values + " using operator: AND");
	}

	@Test
	@DisplayName("Verify filter data with OR operator")
	void testVerifyFilterDataWithOrOperator(@PWPage Page page) {
		openNotebookAndStartDataFilter(page, "Filter Data");
		NotebookPageUtils.clickOnRuleButton(page, "Add Rule");
		NotebookPageUtils.selectColumnFromDropdown(page, "Age");
		NotebookPageUtils.selectOperatorFromDropdown(page, "Equals");
		NotebookPageUtils.enterValueInInput(page, "30");

		NotebookPageUtils.clickOnRuleButton(page, "Add Rule");
		NotebookPageUtils.getDefaultOperator(page, "AND");
		NotebookPageUtils.changeOperatorTo(page, "OR");
		NotebookPageUtils.selectColumnFromDropdown(page, "BloodPressure");
		NotebookPageUtils.selectOperatorFromDropdown(page, "Equals");
		NotebookPageUtils.enterValueInInput(page, "92");

		NotebookPageUtils.deleteFirstCell(page);
		NotebookPageUtils.clickOnRunCellButton(page);

		List<String> columns = Arrays.asList("Age", "BloodPressure");
		List<String> values = Arrays.asList("30", "92");
		boolean result = NotebookPageUtils.isFilteredDataCorrectForColumns(page, columns, values, "OR");
		Assertions.assertTrue(result, "Filtered data does not match for columns: " + columns + " with values: "
				+ values + " using operator: OR");
	}

	@Test
	@DisplayName("Verify Nested Rule filter data with AND operator")
	void testVerifyNestedRuleFilterDataWithAndOperator(@PWPage Page page) {
		openNotebookAndStartDataFilter(page, "Filter Data");
		NotebookPageUtils.clickOnRuleButton(page, "Add Rule");
		NotebookPageUtils.selectColumnFromDropdown(page, "Age");
		NotebookPageUtils.selectOperatorFromDropdown(page, "Equals");
		NotebookPageUtils.enterValueInInput(page, "30");

		NotebookPageUtils.clickOnRuleButton(page, "Add Nested Rule");
		NotebookPageUtils.getDefaultOperator(page, "AND");
		NotebookPageUtils.selectColumnFromDropdown(page, "BloodPressure");
		NotebookPageUtils.selectOperatorFromDropdown(page, "Equals");
		NotebookPageUtils.enterValueInInput(page, "92");

		NotebookPageUtils.clickOnRuleButton(page, "Add Rule");
		NotebookPageUtils.getDefaultOperator(page, "AND");
		NotebookPageUtils.selectColumnFromDropdown(page, "BMI");
		NotebookPageUtils.selectOperatorFromDropdown(page, "Equals");
		NotebookPageUtils.enterValueInInput(page, "37.6");

		NotebookPageUtils.deleteFirstCell(page);
		NotebookPageUtils.clickOnRunCellButton(page);

		List<String> columns = Arrays.asList("Age", "BloodPressure", "BMI");
		List<String> values = Arrays.asList("30", "92", "37.6");
		boolean result = NotebookPageUtils.isFilteredDataCorrectForColumns(page, columns, values, "AND");
		Assertions.assertTrue(result, "Filtered data does not match for columns: " + columns + " with values: "
				+ values + " using operator: AND");
	}

	@Test
	@DisplayName("Verify Nested Rule within Nested rule filter data")
	void testVerifyNestedRuleWithinNestedRuleFilterData(@PWPage Page page) {
		openNotebookAndStartDataFilter(page, "Filter Data");
		NotebookPageUtils.clickOnRuleButton(page, "Add Rule");
		NotebookPageUtils.selectColumnFromDropdown(page, "Age");
		NotebookPageUtils.selectOperatorFromDropdown(page, "Equals");
		NotebookPageUtils.enterValueInInput(page, "30");

		NotebookPageUtils.clickOnRuleButton(page, "Add Nested Rule");
		NotebookPageUtils.getDefaultOperator(page, "AND");
		NotebookPageUtils.selectColumnFromDropdown(page, "BloodPressure");
		NotebookPageUtils.selectOperatorFromDropdown(page, "Equals");
		NotebookPageUtils.enterValueInInput(page, "92");

		NotebookPageUtils.clickOnRuleButton(page, "Add Nested Rule");
		NotebookPageUtils.getDefaultOperator(page, "AND");
		NotebookPageUtils.selectColumnFromDropdown(page, "BMI");
		NotebookPageUtils.selectOperatorFromDropdown(page, "Equals");
		NotebookPageUtils.enterValueInInput(page, "37.6");

		NotebookPageUtils.clickOnRuleButton(page, "Add Rule");
		NotebookPageUtils.getDefaultOperator(page, "AND");
		NotebookPageUtils.selectColumnFromDropdown(page, "Glucose");
		NotebookPageUtils.selectOperatorFromDropdown(page, "Equals");
		NotebookPageUtils.enterValueInInput(page, "110");

		NotebookPageUtils.deleteFirstCell(page);
		NotebookPageUtils.clickOnRunCellButton(page);

		List<String> columns = Arrays.asList("Age", "BloodPressure", "BMI", "Glucose");
		List<String> values = Arrays.asList("30", "92", "37.6", "110");
		boolean result = NotebookPageUtils.isFilteredDataCorrectForColumns(page, columns, values, "AND");
		Assertions.assertTrue(result, "Filtered data does not match for columns: " + columns + " with values: "
				+ values + " using operator: AND");
	}
}

