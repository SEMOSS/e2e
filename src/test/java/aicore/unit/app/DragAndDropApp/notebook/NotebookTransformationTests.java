package aicore.unit.app.DragAndDropApp.notebook;


import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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


public class NotebookTransformationTests extends AbstractPlaywrightTestBase {

	private static final String APP_NAME = "Test app";
	private static final String CATALOG_TYPE = "Database";
	private static final String CATALOG_NAME = "TestDatabase";

	private String timestamp = "";

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
		NotebookPageUtils.selectHiddenOptionDropdown(page, "Custom Import (SQL)");
		NotebookPageUtils.deleteFirstCell(page);
		NotebookPageUtils.selectDatabaseType(page, CATALOG_NAME);
	}

	@AfterEach
	void tearDown(@PWPage Page page) {
		CommonUtils.navigateAndDeleteApp(page, APP_NAME + timestamp);
		CommonUtils.navigateAndDeleteCatalog(page, CATALOG_TYPE, CATALOG_NAME);
		logout(page);
	}

	
	private void verifyColumnValuesAreTodaysDateWithCurrentTime(Page page, String columnName) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDate today = LocalDate.now();
		List<String> columnValues = NotebookPageUtils.getColumnValues(page, columnName);
		for (String columnValue : columnValues) {
			LocalDateTime actual = LocalDateTime.parse(columnValue.trim(), formatter);
			if (!actual.toLocalDate().equals(today)) {
				throw new AssertionError("Value '" + columnValue + "' does not match today's date: " + today);
			}
			LocalDateTime expected = LocalDateTime.now();
			long diffSeconds = Math.abs(Duration.between(actual, expected).getSeconds());
			Assertions.assertTrue(diffSeconds <= 5, "time differs by more than 10 seconds");
		}
	}

	
	private void verifyColumnValuesAreTodaysDateWithTimestamp(Page page, String columnName, String expectedTimestamp) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		List<String> columnValues = NotebookPageUtils.getColumnValues(page, columnName);
		for (String columnValue : columnValues) {
			String cleaned = columnValue.replace("T", " ");
			LocalDateTime actual = LocalDateTime.parse(cleaned, formatter);
			String expected = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " " + expectedTimestamp;
			Assertions.assertEquals(expected, actual.format(formatter), "timestamp values not matching");
		}
	}

	
	private void verifyColumnValuesGreaterThanOrEqualTo(Page page, String columnName, String minValue) {
		List<String> columnValues = NotebookPageUtils.getColumnValues(page, columnName);
		double min = Double.parseDouble(minValue);
		for (String val : columnValues) {
			String cleaned = val.trim();
			double numericValue;
			try {
				numericValue = Double.parseDouble(cleaned);
			} catch (NumberFormatException e) {
				Assertions.fail("Column value for " + columnName + " is not numeric: " + cleaned);
				return;
			}
			Assertions.assertTrue(numericValue >= min, "Expected " + columnName
					+ " value to be greater than or equal to " + minValue + " but found " + numericValue);
		}
	}

	@Test
	@DisplayName("Validate Timestamp function in Transformation")
	void testValidateTimestampFunctionInTransformation(@PWPage Page page) {
		NotebookPageUtils.writeQuery(page, "SELECT BMI FROM DIABETES LIMIT 20");
		NotebookPageUtils.clickOnRunCellButton(page);
		String frameID = NotebookPageUtils.getFrameID(page);

		NotebookPageUtils.mouseHoverOnNotebookHiddenOptions(page);
		NotebookPageUtils.clickOnHiddenNotebookOption(page, "Transformation");
		NotebookPageUtils.selectTransformationOptionDropdown(page, "Timestamp");
		BlockSettingsUtils.selectFrame(page, frameID);
		NotebookPageUtils.enterColumnName(page, "BMI_IncludeTime");
		NotebookPageUtils.clickOnIncludeTimeCheckbox(page);
		NotebookPageUtils.clickOnRunAllCellButton(page);

		List<String> expectedHeaderNamesIncludeTime = Arrays.asList("BMI", "BMI_IncludeTime");
		List<String> actualHeaderNamesIncludeTime = NotebookPageUtils.getNotebookOutputTableHeader(page);
		Assertions.assertEquals(expectedHeaderNamesIncludeTime, actualHeaderNamesIncludeTime,
				"Headers are not matching");
		verifyColumnValuesAreTodaysDateWithCurrentTime(page, "BMI_IncludeTime");

		NotebookPageUtils.clickOnIncludeTimeCheckbox(page);
		NotebookPageUtils.enterColumnName(page, "BMI_ExcludeTime");
		NotebookPageUtils.clickOnRunAllCellButton(page);

		List<String> expectedHeaderNamesExcludeTime = Arrays.asList("BMI", "BMI_ExcludeTime");
		List<String> actualHeaderNamesExcludeTime = NotebookPageUtils.getNotebookOutputTableHeader(page);
		Assertions.assertEquals(expectedHeaderNamesExcludeTime, actualHeaderNamesExcludeTime,
				"Headers are not matching");
		verifyColumnValuesAreTodaysDateWithTimestamp(page, "BMI_ExcludeTime", "00:00:00");
	}

	@Test
	@DisplayName("Validate Date Difference transformation using UI fields")
	void testValidateDateDifferenceTransformationUsingUiFields(@PWPage Page page) {
		NotebookPageUtils.writeQuery(page, "SELECT START_DATE, END_DATE FROM DIABETES LIMIT 20");
		NotebookPageUtils.clickOnRunCellButton(page);
		String frameID = NotebookPageUtils.getFrameID(page);

		NotebookPageUtils.mouseHoverOnNotebookHiddenOptions(page);
		NotebookPageUtils.clickOnHiddenNotebookOption(page, "Transformation");
		NotebookPageUtils.selectTransformationOptionDropdown(page, "Date Difference");
		BlockSettingsUtils.selectFrame(page, frameID);
		NotebookPageUtils.selectTransformationValueFromDropdown(page, "START_DATE", "Start Date Column");
		NotebookPageUtils.selectTransformationValueFromDropdown(page, "END_DATE", "End Date Column");
		NotebookPageUtils.selectTransformationValueFromDropdown(page, "day", "Unit of Measure");
		NotebookPageUtils.enterColumnName(page, "DATE_DIFF_DAYS");
		NotebookPageUtils.clickOnRunAllCellButton(page);

		List<String> expectedHeaderNames = Arrays.asList("DATE_DIFF_DAYS", "END_DATE", "START_DATE");
		List<String> actualHeaderNames = NotebookPageUtils.getNotebookOutputTableHeader(page);
		Assertions.assertEquals(expectedHeaderNames, actualHeaderNames, "Headers are not matching");

		verifyColumnValuesGreaterThanOrEqualTo(page, "DATE_DIFF_DAYS", "0");
	}
}

