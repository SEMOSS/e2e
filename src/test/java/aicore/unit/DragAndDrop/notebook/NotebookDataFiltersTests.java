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
import aicore.utils.page.app.BlockSettingsUtils;
import aicore.utils.page.app.DragAndDropBlocksPageUtils;
import aicore.utils.page.app.NotebookPageUtils;
import aicore.utils.page.app.TemplateCreationUtils;
import aicore.utils.TestResources;

public class NotebookDataFiltersTests extends AbstractPlaywrightTestBase {
	private static final Logger logger = LogManager.getLogger(NotebookDBOperationsTests.class);
    SoftAssertions softAssert = new SoftAssertions();
	private static final String CATALOG_TYPE = "Database";
	private String appName = "";
	private String frameID = "";
	private static final String TEST_DATABASE_NAME = "TestDatabase"; 

	@BeforeEach
	void setup(@PWPage Page page) {
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

	    logger.info("BEFORE ALL: clickOnNotebooksOption");

		
		NotebookPageUtils.clickOnNotebooksOption(page);
		NotebookPageUtils.clickOnCreateNewNotebook(page);
		NotebookPageUtils.enterQueryName(page, "Test");
		NotebookPageUtils.clickOnQuerySubmitButton(page);
		NotebookPageUtils.mouseHoverOnNotebookHiddenOptions(page);
		NotebookPageUtils.clickOnHiddenNotebookOption(page, "Import Data");
	    NotebookPageUtils.selectHiddenOptionDropdown(page, "Query Builder");
	    NotebookPageUtils.selectDatabaseFromDropdown(page, TEST_DATABASE_NAME);

		List<String> expectedFieldColumns = Arrays.asList("Age", "BMI", "BloodPressure", "DIABETES_UNIQUE_ROW_IDFK",
				"DiabetesPedigreeFunction", "End_Date", "Glucose", "Insulin", "Milestone", "Outcome", "Pregnancies",
				"SkinThickness", "Start_Date", "Task_Group", "Task_Name", "Tooltip");
		List<String> actualFieldColumns = NotebookPageUtils.checkColumnNamesOnUI(page);

		
		softAssert.assertThat(actualFieldColumns)
	      .as("columns are not matching")
	      .isEqualTo(expectedFieldColumns);

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

		Assertions.assertTrue(NotebookPageUtils.isColumnUniqueByHeader(page, "DIABETES_UNIQUE_ROW_ID"),
				"DIABETES_UNIQUE_ROW_ID have duplicate values");

		String jsonFrameId = NotebookPageUtils.validateJsonFieldValue(page, frameID);
		String cleanedActualFrameId = jsonFrameId.replaceAll("^\"|\"$", "");
		Assertions.assertEquals(frameID, cleanedActualFrameId, "Frame Id not matching");
		NotebookPageUtils.validateJsonFieldValue(page, "PY");

		DragAndDropBlocksPageUtils.clickOnSaveAppButton(page);
	}

	@AfterEach
	void tearDown(@PWPage Page page) {
		logger.info("AFTER ALL: Deleting App and Catalog");
		CommonUtils.navigateAndDeleteApp(page, appName);
		CommonUtils.navigateAndDeleteCatalog(page, CATALOG_TYPE,  TEST_DATABASE_NAME );
		logout(page);
		softAssert.assertAll();
	}

	private void openNotebookAndStartDataFilter(Page page, String filterOption) {
		NotebookPageUtils.verifyNotebookIsPresentInList(page, "Test");
		NotebookPageUtils.clickOnNotebook(page, "Test");
		
		NotebookPageUtils.mouseHoverOnNotebookHiddenOptionsOnCurrentQueryBuilderCell(page);
		NotebookPageUtils.clickDataFiltersOnCurrentCell(page);
		
		NotebookPageUtils.selectDataFiltersOptionDropdown(page, filterOption);

		BlockSettingsUtils.selectFrame(page, frameID);
	}

	private void addNestedRule(Page page, boolean isNestedRule, String column, String value) {
		NotebookPageUtils.clickOnRuleButton(page, isNestedRule ? "Add Nested Rule" : "Add Rule");
		NotebookPageUtils.getDefaultOperator(page, "AND");
		NotebookPageUtils.selectColumnFromDropdown(page, column);
		//NotebookPageUtils.selectOperatorFromDropdown(page, "Equals");
		NotebookPageUtils.enterValueInInput(page, value);
	}

	private void runFilterAndAssert(Page page, List<String> columns, List<String> values, String operator) {
		NotebookPageUtils.deleteFirstCell(page);
		NotebookPageUtils.clickOnRunCellButton(page);

		Assertions.assertTrue(NotebookPageUtils.isFilteredDataCorrectForColumns(page, columns, values, operator),
				"Filtered data does not match for columns: " + columns + " with values: " + values
						+ " using operator: " + operator);
	}

	@Test
	@ResourceUploadLock(TestResources.TEST_DATABASE_ZIP)
	@DisplayName("TC01_Verify Unfilter Data in the app")
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

		Assertions.assertTrue(NotebookPageUtils.isColumnUniqueByHeader(page, "DIABETES_UNIQUE_ROW_ID"),
				"DIABETES_UNIQUE_ROW_ID have duplicate values");
	}

	
	@Test
	@ResourceUploadLock(TestResources.TEST_DATABASE_ZIP)
	@DisplayName("TC02_Verify filter Data in the app")
	void testVerifyFilterDataInTheApp(@PWPage Page page) {
		openNotebookAndStartDataFilter(page, "Filter Data");
		NotebookPageUtils.clickOnRuleButton(page, "Add Rule");
		NotebookPageUtils.selectColumnFromDropdown(page, "Age");
		
		
		//NotebookPageUtils.selectOperatorFromDropdown(page, "Equals");
		
		NotebookPageUtils.enterValueInInput(page, "30");
		NotebookPageUtils.deleteFirstCell(page);
		NotebookPageUtils.clickOnRunCellButton(page);

		Assertions.assertTrue(NotebookPageUtils.isFilteredDataCorrect(page, "Age", "30"),
				"Filtered data is not correct for column: Age with value: 30");
	}

	@Test
	@ResourceUploadLock(TestResources.TEST_DATABASE_ZIP)
	@DisplayName("TC03_Verify filter Data in the app with AND operator")
	void testVerifyFilterDataWithAndOperator(@PWPage Page page) {
		openNotebookAndStartDataFilter(page, "Filter Data");
		NotebookPageUtils.clickOnRuleButton(page, "Add Rule");
		NotebookPageUtils.selectColumnFromDropdown(page, "Age");
		//NotebookPageUtils.selectOperatorFromDropdown(page, "Equals");
		NotebookPageUtils.enterValueInInput(page, "30");

		addNestedRule(page, false, "BloodPressure", "92");

		runFilterAndAssert(page, Arrays.asList("Age", "BloodPressure"), Arrays.asList("30", "92"), "AND");
	}

	@Test
	@ResourceUploadLock(TestResources.TEST_DATABASE_ZIP)
	@DisplayName("TC04_Verify filter data with OR operator")
	void testVerifyFilterDataWithOrOperator(@PWPage Page page) {
		openNotebookAndStartDataFilter(page, "Filter Data");
		NotebookPageUtils.clickOnRuleButton(page, "Add Rule");
		NotebookPageUtils.selectColumnFromDropdown(page, "Age");
		//NotebookPageUtils.selectOperatorFromDropdown(page, "Equals");
		NotebookPageUtils.enterValueInInput(page, "30");

		NotebookPageUtils.clickOnRuleButton(page, "Add Rule");
		NotebookPageUtils.getDefaultOperator(page, "AND");
		
		NotebookPageUtils.changeOperatorTo(page, "OR");
		NotebookPageUtils.selectColumnFromDropdown(page, "BloodPressure");
		//NotebookPageUtils.selectOperatorFromDropdown(page, "Equals");
		NotebookPageUtils.enterValueInInput(page, "92");

		runFilterAndAssert(page, Arrays.asList("Age", "BloodPressure"), Arrays.asList("30", "92"), "OR");
	}

	@Test
	@ResourceUploadLock(TestResources.TEST_DATABASE_ZIP)
	@DisplayName("TC05_Verify Nested Rule filter data with AND operator")
	void testVerifyNestedRuleFilterDataWithAndOperator(@PWPage Page page) {
		openNotebookAndStartDataFilter(page, "Filter Data");
		NotebookPageUtils.clickOnRuleButton(page, "Add Rule");
		NotebookPageUtils.selectColumnFromDropdown(page, "Age");
		//NotebookPageUtils.selectOperatorFromDropdown(page, "Equals");
		NotebookPageUtils.enterValueInInput(page, "30");

		addNestedRule(page, true, "BloodPressure", "92");
		addNestedRule(page, false, "BMI", "37.6");

		runFilterAndAssert(page, Arrays.asList("Age", "BloodPressure", "BMI"), Arrays.asList("30", "92", "37.6"),
				"AND");
	}

	@Test
	@ResourceUploadLock(TestResources.TEST_DATABASE_ZIP)
	@DisplayName("TC06_Verify Nested Rule within Nested rule filter data")
	void testVerifyNestedRuleWithinNestedRuleFilterData(@PWPage Page page) {
		openNotebookAndStartDataFilter(page, "Filter Data");
		NotebookPageUtils.clickOnRuleButton(page, "Add Rule");
		NotebookPageUtils.selectColumnFromDropdown(page, "Age");
		//NotebookPageUtils.selectOperatorFromDropdown(page, "Equals");
		NotebookPageUtils.enterValueInInput(page, "30");

		addNestedRule(page, true, "BloodPressure", "92");
		addNestedRule(page, true, "BMI", "37.6");
		addNestedRule(page, false, "Glucose", "110");

		runFilterAndAssert(page, Arrays.asList("Age", "BloodPressure", "BMI", "Glucose"),
				Arrays.asList("30", "92", "37.6", "110"), "AND");
	}
}