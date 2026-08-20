package aicore.unit.DragAndDrop.charts;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.microsoft.playwright.Page;
import aicore.pages.home.HomePageUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.AddDatabasePageUtils;
import aicore.utils.CommonUtils;
import aicore.utils.DatabaseTestUtils;
import aicore.utils.annotations.PWPage;
import aicore.utils.page.app.AppPageUtils;
import aicore.utils.page.app.BlockSettingsUtils;
import aicore.utils.page.app.CreateAppPopupUtils;
import aicore.utils.page.app.DragAndDropBlocksPageUtils;
import aicore.utils.page.app.NotebookPageUtils;
import aicore.utils.page.app.TemplateCreationUtils;

public class CreateDragAndDropBarChart extends AbstractPlaywrightTestBase {
	private String frameId;
	private String X_AxisValues = "Show Axis Title=true, Set Axis Title=Bar chart, Edit Axis Title Font Size=12, Axis Gap=25, Show XAxis Labels=true, Edit Label Font Size=12, Rotate X-Axis Values=36, Show XAxis Line Ticks=true, Show / Hide X-Axis Zoom=true";	
	private String Y_AxisValues = "Show Axis Title=true, Set Axis Title=Bar chart, Edit Axis Title Font Size=12, Axis Gap=25, Show YAxis Labels=true, Edit Label Font Size=12, Rotate Y-Axis Values=36, Show XAxis Line Ticks=true, Show / Hide Y-Axis Zoom=true";
	private String ValueLabelValues = "Position=top, Rotate Label=90, Select Alignment=center, Select Font=serif, Select Font Size =8, Select Font Weight=bold, Select Colour=black";
	private String BarStyleValues = "Bar Width= 13, Select Colour= blue";
	private String ResizingValues = "Height=250, Width=350";
	private String ChartTitleValues = "Show Title=true, Title Name=Bar Graph, Select Alignment=left, Text Size= 14, Select Font Weight=bold, Select Font Family=Calibri, Select Colour=black";
	private String appName;

	@BeforeEach
	void setup(@PWPage Page page) {
		loginNativeAdmin(page);
		appName = TemplateCreationUtils.createDragAndDropApp(page, "Drag and Drop");
		verifyAppCreated(page);
		verifyWelcomePage(page);
		DragAndDropBlocksPageUtils.clickOnBlocksOption(page);

		String databaseId = DatabaseTestUtils.uploadDatabaseZip(
		        page,
		        "TestDatabase",
		        "Database/TestDatabase.zip");

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
		NotebookPageUtils.selectHiddenOptionDropdown(page, "From Data Catalog");
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
		NotebookPageUtils.enterDataLimit(page, "20");
		NotebookPageUtils.clickOnRunCellButton(page);
		frameId = fetchFrameId(null);
		DragAndDropBlocksPageUtils.selectPage(page, "page-1");
		DragAndDropBlocksPageUtils.clickOnBlocksOption(page);
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
	
	private void verifyAppCreated(Page page) {
	    String appName = CreateAppPopupUtils.userFetchAppName(page);
	    Assertions.assertFalse(appName.isEmpty(), "Fetched App Name is Empty");
	}
	
	private void verifyCatalogTitle(Page page, String expectedDatabaseName) {
	    Assertions.assertTrue(
	            AddDatabasePageUtils.verifyDatabaseTitle(page, expectedDatabaseName),
	            "Database title is not visible");
	}
	
	private void verifyFieldsColumnNames(Page page, String... expectedColumns) {
	    List<String> actualColumns = NotebookPageUtils.checkColumnNamesOnUI(page);

	    Assertions.assertEquals(
	            Arrays.asList(expectedColumns),
	            actualColumns,
	            "Columns are not matching");
	}
	
	private String fetchFrameId(Page page) {
	    String frameID = NotebookPageUtils.getFrameID(page);

	    Assertions.assertNotNull(frameID, "Frame ID was not fetched");
	    Assertions.assertFalse(frameID.isBlank(), "Frame ID is empty");

	    return frameID;
	}
	
	private void dragColumnToField(Page page, String columnName, String fieldName) {

	    BlockSettingsUtils.dragColumnToTargetField(page, columnName, fieldName);

	    Assertions.assertTrue(
	            BlockSettingsUtils.verifyColumnDroppedInCorrectField(page, columnName, fieldName),
	            String.format("Column '%s' was not dropped into field '%s'.",
	                    columnName, fieldName));
	}
	
	private void verifyConditional(Page page, boolean... values) {

	    for (boolean expected : values) {

	        DragAndDropBlocksPageUtils.applyConditional(page, String.valueOf(expected));

	        boolean isVisible = DragAndDropBlocksPageUtils.isBarChartVisible(page);

	        if (expected) {
	            Assertions.assertTrue(
	                    isVisible,
	                    "Chart should be visible when conditional = true");
	        } else {
	            Assertions.assertFalse(
	                    isVisible,
	                    "Chart should NOT be visible when conditional = false");
	        }
	    }
	}
	
	
	private void verifyColorPalette(Page page, String action) {

	    switch (action.toLowerCase()) {

	        case "add color":
	            Assertions.assertTrue(
	                    DragAndDropBlocksPageUtils.performAddColor(page),
	                    "Failed to add color from palette.");
	            break;

	        case "change color":
	            DragAndDropBlocksPageUtils.performCheckColor(page);
	            break;

	        default:
	            Assertions.fail("Invalid color palette action: " + action);
	    }
	}
	
	
	private void verifyToolMatchesBaseline(Page page, String toolName, String chartName) {
	    String chartFolder = chartName.replaceAll("\\s+", "").toLowerCase();
	    String toolFolder = toolName.replaceAll("\\s+", "").toLowerCase();

	    String basePath = "screenshots/" + chartFolder + "/" + toolFolder + "/";
	    String actualImagePath = basePath + "actualChart.png";
	    String expectedImagePath = basePath + "expectedChart.png";
	    String diffImagePath = basePath + "diffChart.png";

	    DragAndDropBlocksPageUtils.closeBlocksOption(page);
	    DragAndDropBlocksPageUtils.takeChartScreenshot(page, actualImagePath, toolName);
	    try {
	    Assertions.assertTrue(
	            CommonUtils.compareImages(actualImagePath, expectedImagePath, diffImagePath),
	            "Images do not match for Tool: " + toolName + " under Chart: " + chartName);
	    
	    
	    } catch (Exception e) {
	        Assertions.fail("Image comparison failed: " + e.getMessage(), e);
	    }
	}
	
	private void dragAndSelectBlock(Page page, String blockName) {
	    DragAndDropBlocksPageUtils.mouseHoverOnBlock(page, blockName);
	    DragAndDropBlocksPageUtils.blockDropPosition(page, blockName);
	    DragAndDropBlocksPageUtils.clickOnDroppedBlock(page, blockName);
	}
	@Test
    public void DragAndDropDataBarChart_test(@PWPage Page page) {
		
		dragAndSelectBlock(page, "Bar Chart");
		BlockSettingsUtils.clickOnBlockSettingsOption(page);
		BlockSettingsUtils.clickOnDataTab(page);
		BlockSettingsUtils.selectFrame(page, frameId);
		dragColumnToField(page, "AGE", "Select X Axis");
		dragColumnToField(page, "GLUCOSE", "Select Y Axis");
		DragAndDropBlocksPageUtils.clickOnToolTab(page);
		DragAndDropBlocksPageUtils.clickOnToolOption(page, "Conditional");
		verifyConditional(page, false, true);
		DragAndDropBlocksPageUtils.clickOnToolOption(page, "Color Palette");
		verifyColorPalette(page, "Add Color");
		verifyColorPalette(page, "Change Color");		
		verifyToolMatchesBaseline(page, "Color_Palette_Tool", "Bar Chart");
		DragAndDropBlocksPageUtils.clickOnDroppedBlock(page, "Bar Chart");
		DragAndDropBlocksPageUtils.clickOnToolTab(page);
		DragAndDropBlocksPageUtils.clickOnEditXAxisOption(page, "X Axis");
		DragAndDropBlocksPageUtils.updateToolSettings(page, "X Axis", X_AxisValues);
		verifyToolMatchesBaseline(page, "Edit_XAxis_Tool", "Bar Chart");
		DragAndDropBlocksPageUtils.clickOnDroppedBlock(page, "Bar Chart");
		DragAndDropBlocksPageUtils.clickOnToolTab(page);
		DragAndDropBlocksPageUtils.clickOnEditXAxisOption(page, "Y Axis");
		DragAndDropBlocksPageUtils.updateToolSettings(page, "Y Axis", Y_AxisValues);
		verifyToolMatchesBaseline(page, "Edit_YAxis_Tool", "Bar Chart");
		DragAndDropBlocksPageUtils.clickOnToolOption(page, "Value Label");
		DragAndDropBlocksPageUtils.turnOnValueLabelToggle(page);
		DragAndDropBlocksPageUtils.updateValueLabelSettings(page, ValueLabelValues);
		verifyToolMatchesBaseline(page, "Value_Labels_Tool", "Bar Chart");
		DragAndDropBlocksPageUtils.clickOnToolOption(page, "Bar Style");
		DragAndDropBlocksPageUtils.updateBarStyle(page, BarStyleValues);
		verifyToolMatchesBaseline(page, "BarStyleValues", "Bar Chart");		
		DragAndDropBlocksPageUtils.clickOnToolOption(page, "Chart Title");
		DragAndDropBlocksPageUtils.updateToolSettings(page, "Chart_Title_Tool", ChartTitleValues);
		verifyToolMatchesBaseline(page, "Resizing_Tool", "Bar Chart");		
		DragAndDropBlocksPageUtils.clickOnToolOption(page, "Resizing");
		DragAndDropBlocksPageUtils.updateToolSettings(page, "Resizing", ResizingValues);
		verifyToolMatchesBaseline(page, "Resizing_Tool", "Bar Chart");
		DragAndDropBlocksPageUtils.clickOnLegendOptionAndTurnOnTheToggle(page);
		verifyToolMatchesBaseline(page, "Legend_Tool", "Bar Chart");
	}

}
