package aicore.unit.DragAndDrop.charts;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.microsoft.playwright.Page;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import aicore.pages.home.HomePageUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.AddDatabasePageUtils;
import aicore.utils.CommonUtils;
import aicore.utils.DatabaseTestUtils;
import aicore.utils.TestResources;
import aicore.utils.annotations.PWPage;
import aicore.utils.annotations.ResourceUploadLock;
import aicore.utils.page.app.AppPageUtils;
import aicore.utils.page.app.BlockSettingsUtils;
import aicore.utils.page.app.CreateAppPopupUtils;
import aicore.utils.page.app.DragAndDropBlocksPageUtils;
import aicore.utils.page.app.NotebookPageUtils;
import aicore.utils.page.app.TemplateCreationUtils;

public class CreateAppUsingDragAndDropCharts extends AbstractPlaywrightTestBase{
	private String appName;
	private int initialChartCount;
	
	
	
	@BeforeEach
	void setup(@PWPage Page page) {
		loginNativeAdmin(page);
		appName = TemplateCreationUtils.createDragAndDropApp(page, "Drag and Drop");
		verifyAppCreated(page);
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
	
	private void verifyChartMatchesBaseline(Page page, String chartName) {
	    try {
	        String removeSpace = chartName.replace(" ", "");
	        String folderName = Character.toLowerCase(removeSpace.charAt(0))
	                + removeSpace.substring(1);

	        String basePath = "screenshots/" + folderName + "/";
	        String actualImagePath = basePath + "actualChart.png";
	        String expectedImagePath = basePath + "expectedChart.png";
	        String diffImagePath = basePath + "diffChart.png";

	        DragAndDropBlocksPageUtils.closeBlocksOption(page);
	        DragAndDropBlocksPageUtils.takeChartScreenshot(page, actualImagePath, chartName);

	        Assertions.assertTrue(
	                CommonUtils.compareImages(actualImagePath, expectedImagePath, diffImagePath),
	                "Images do not match for chart: " + chartName);

	    } catch (Exception e) {
	        Assertions.fail("Image comparison failed: " + e.getMessage(), e);
	    }
	}
	
	
	private void verifyCatalogTitle(Page page, String expectedDatabaseName) {
	    Assertions.assertTrue(
	            AddDatabasePageUtils.verifyDatabaseTitle(page, expectedDatabaseName),
	            "Database title is not visible");
	}
	
	private void verifyColumnsUnderFields(Page page, String columnNames) {

	    List<String> expectedColumns = Arrays.asList(columnNames.split(",\\s*"));

	    List<String> actualColumns = NotebookPageUtils.checkColumnNamesOnUI(page);

	    Assertions.assertEquals(
	            expectedColumns,
	            actualColumns,
	            "Columns under the fields panel do not match.");
	}
	

	private String fetchFrameId(Page page) {
	    String frameID = NotebookPageUtils.getFrameID(page);
	    Assertions.assertNotNull(frameID, "Frame ID was not fetched");
	    Assertions.assertFalse(frameID.isBlank(), "Frame ID is empty");
	    return frameID;
	}
	
	private void dragColumnsToFields(
			Page page,
	        String columnNames,
	        String fieldNames) {

	    String[] columnNameList = columnNames.split(",\\s*");
	    String[] fieldNameList = fieldNames.split(",\\s*");

	    Assertions.assertEquals(
	            columnNameList.length,
	            fieldNameList.length,
	            "Number of columns and fields must match.");

	    for (int i = 0; i < columnNameList.length; i++) {

	        String columnName = columnNameList[i].trim();
	        String fieldName = fieldNameList[i].trim();

	        BlockSettingsUtils.dragColumnToTargetField(page, columnName, fieldName);

	        Assertions.assertTrue(
	        		BlockSettingsUtils.verifyColumnDroppedInCorrectField(page, columnName, fieldName),
	                String.format(
	                        "Column '%s' was not dropped into field '%s'.",
	                        columnName,
	                        fieldName));
	    }
	}
	
	private void verifyDuplicateRemoved(Page page, String blockName) {

	    boolean chartIsRemoved =
	            DragAndDropBlocksPageUtils.chartIsRemoved(
	                    page,
	                    initialChartCount,
	                    blockName);

	    Assertions.assertTrue(
	            chartIsRemoved,
	            "Duplicate '" + blockName + "' chart was not removed.");
	}
	
	private void verifyDuplicateChart(Page page, String blockName) {
	    Assertions.assertTrue(
	            DragAndDropBlocksPageUtils.duplicatedChartIsVisiable(
	                    page,
	                    initialChartCount,
	                    blockName),
	            "Duplicate chart is not added");
	}
	private void duplicateChart(Page page, String blockName) {

	    initialChartCount =
	            DragAndDropBlocksPageUtils.getInitialcount(page, blockName);

	    DragAndDropBlocksPageUtils.clickOnDuplicateIcon(page);
	}

	private void createChart(
	        Page page,
	        String frameId,
	        String blockName,
	        String columnNames,
	        String fieldNames) {

	    DragAndDropBlocksPageUtils.mouseHoverOnBlock(page, blockName);
	    DragAndDropBlocksPageUtils.blockDropPosition(page, blockName);

	    DragAndDropBlocksPageUtils.clickOnDroppedBlock(page, blockName);

		BlockSettingsUtils.clickOnBlockSettingsOption(page);
		BlockSettingsUtils.clickOnDataTab(page);

	    BlockSettingsUtils.selectFrame(page, frameId);


	    dragColumnsToFields(page, columnNames, fieldNames);

	    verifyChartMatchesBaseline(page, blockName);
	    
	    DragAndDropBlocksPageUtils.clickOnDroppedBlock(page, blockName);

	    duplicateChart(page, blockName);
	    verifyDuplicateChart(page, blockName);

	    DragAndDropBlocksPageUtils.clickOnDeleteIcon(page);

	    verifyDuplicateRemoved(page, blockName);	    

	    DragAndDropBlocksPageUtils.clickOnSaveAppButton(page);
	}

	private static Stream<Arguments> chartData() {
	    return Stream.of(

	        Arguments.of(
	                "Scatter Plot",
	                "AGE, BLOODPRESSURE, BMI, GLUCOSE",
	                "Select Label, Select X Axis, Select Y Axis, Select Tooltip"),

	        Arguments.of(
	                "Line Chart",
	                "AGE, BLOODPRESSURE, BMI",
	                "Select X Axis, Select Y Axis, Select Tooltip"),

	        Arguments.of(
	                "Bar Chart",
	                "AGE, BLOODPRESSURE",
	                "Select X Axis, Select Y Axis"),

	        Arguments.of(
	                "Bar Chart - Stacked",
	                "AGE, BMI, GLUCOSE, INSULIN",
	                "Select X Axis, Select Y Axis, Select Category, Select Tooltip"),

	        Arguments.of(
	                "Pie Chart",
	                "AGE, BLOODPRESSURE",
	                "Select Label, Select Value"),

	        Arguments.of(
	                "Gantt Chart",
	                "TASK_NAME, START_DATE, END_DATE, TASK_GROUP, MILESTONE, TOOLTIP",
	                "Select Task, Select Start Date, Select End Date, Select Task Group, Select MileStone, Select Tooltip"),

	        Arguments.of(
	                "Dendrogram Chart",
	                "AGE, BLOODPRESSURE",
	                "Select Dimensions, Select Facet"),

	        Arguments.of(
	                "World Map Chart",
	                "DIABETES_UNIQUE_ROW_ID, AGE, BMI, SKINTHICKNESS, TOOLTIP",
	                "Select Label, Select Latitude, Select Longitude, Select Size, Select Tooltip")
	    );
	}
	@ParameterizedTest
	@ResourceUploadLock(TestResources.TEST_DATABASE_ZIP)
	@MethodSource("chartData")
    public void DragAndDropDataCharts_test( String blockName,String columnNames,String fieldNames, @PWPage Page page) {

		
		String databaseId = DatabaseTestUtils.uploadDatabaseZip(
		        page,
		        "TestDatabase",
		         TestResources.TEST_DATABASE_ZIP);

		Assertions.assertNotNull(databaseId);
		Assertions.assertFalse(databaseId.isBlank());
		
		
		verifyCatalogTitle(page, "TestDatabase");
		AddDatabasePageUtils.clickOnMetadataTab(page);
        HomePageUtils.navigateToHomePage(page);
        MainMenuUtils.openMainMenu(page);
        MainMenuUtils.clickOnOpenAppLibrary(page);
		AppPageUtils.searchApp(page, appName, "");
		AppPageUtils.clickOnAppCard(page, appName, "");
		DragAndDropBlocksPageUtils.clickOnEditButton(page);
		DragAndDropBlocksPageUtils.clickOnBlocksOption(page);
		NotebookPageUtils.clickOnNotebooksOption(page);
		NotebookPageUtils.clickOnCreateNewNotebook(page);
		NotebookPageUtils.enterQueryName(page, "Test");
		NotebookPageUtils.clickOnQuerySubmitButton(page);
		NotebookPageUtils.mouseHoverOnNotebookHiddenOptions(page);
		NotebookPageUtils.clickOnHiddenNotebookOption(page, "Import Data");
		NotebookPageUtils.selectHiddenOptionDropdown(page, "Query Builder");
		NotebookPageUtils.selectDatabaseFromDropdown(page, "TestDatabase");
		verifyColumnsUnderFields(page,
		        "Age, BloodPressure, BMI, DiabetesPedigreeFunction, DIABETES_UNIQUE_ROW_ID, End_Date, Glucose, Insulin, Milestone, Outcome, Pregnancies, SkinThickness, Start_Date, Task_Group, Task_Name, Tooltip");
		
		NotebookPageUtils.selectAllColumns(page);
		NotebookPageUtils.deleteFirstCell(page);
		NotebookPageUtils.enterDataLimit(page, "20");
		NotebookPageUtils.clickOnRunCellButton(page);
		String frameId = fetchFrameId(page);
		DragAndDropBlocksPageUtils.clickOnBlocksOption(page);
		DragAndDropBlocksPageUtils.selectPage(page, "page-1");

	    createChart(
	            page,
	            frameId,
	            blockName,
	            columnNames,
	            fieldNames);	
	}
	
	@Test
    public void DragAndDropDataMermaidChart_test(@PWPage Page page) {
		String BlockName = "Mermaid Chart";
		
        HomePageUtils.navigateToHomePage(page);
        MainMenuUtils.openMainMenu(page);
        MainMenuUtils.clickOnOpenAppLibrary(page);
		AppPageUtils.searchApp(page, appName, "");
		AppPageUtils.clickOnAppCard(page, appName, "");
		DragAndDropBlocksPageUtils.clickOnEditButton(page);
		DragAndDropBlocksPageUtils.clickOnBlocksOption(page);
	    DragAndDropBlocksPageUtils.mouseHoverOnBlock(page, BlockName);
	    DragAndDropBlocksPageUtils.blockDropPosition(page, BlockName);
		DragAndDropBlocksPageUtils.clickOnDroppedBlock(page, BlockName);
		BlockSettingsUtils.clickOnBlockSettingsOption(page);
		BlockSettingsUtils.enterValueInGraphTD(page, "A-->D");
		DragAndDropBlocksPageUtils.clickOnSaveAppButton(page);
		verifyChartMatchesBaseline(page, "Mermaid Chart"); 
	}

}


