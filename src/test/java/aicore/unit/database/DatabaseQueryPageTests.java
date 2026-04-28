package aicore.unit.database;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import aicore.pages.home.MainMenuUtils;
import aicore.utils.AbstractE2ETest;
import aicore.utils.AddDatabasePageUtils;
import aicore.utils.CommonUtils;
import aicore.utils.DatabaseTestUtils;
import aicore.utils.StoragePageUtils;
import aicore.utils.TestResources;

public class DatabaseQueryPageTests extends AbstractE2ETest {
	private static String dbName = null;
	private static String dbID = null;

	@BeforeAll
	public static void setupBeforeAll() throws IOException {
		login(page, UserType.NATIVE);

		String timestamp = CommonUtils.getTimeStampName();
		dbName = "CSV db" + timestamp;
		String fileName = TestResources.DIABETES_CSV;
		String dbType = "h2";
		String metaModelType = "asFlatTable";
		dbID = DatabaseTestUtils.addFlatCsv(page, dbName, fileName, dbType, metaModelType);
	}
	

	@BeforeEach
	public void setup() throws IOException {
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenDatabase(page);
		AddDatabasePageUtils.searchDatabaseCatalog(page, dbName);
		AddDatabasePageUtils.clickOnDatabaseNameInCatalog(page, dbName);
		boolean isTitleVisible = AddDatabasePageUtils.verifyDatabaseTitle(page, dbName);
		Assertions.assertTrue(isTitleVisible, "Database title is not visible");
	}

	@Test
	public void testRunQueryDB() {
		AddDatabasePageUtils.clickOnQueryTab(page);
		String query = "SELECT AGE, BMI from DIABETES";
		AddDatabasePageUtils.enterQuery(page, query);
		AddDatabasePageUtils.clickOnRunQueryButton(page);

		// validate headers
		String headerNames = "AGE, BMI";
		List<String> expectedHeaderNames = Arrays.asList(headerNames.split(", "));
		List<String> actualHeaderNames = AddDatabasePageUtils.getQueryResponseTableHeader(page);
		Assertions.assertEquals(expectedHeaderNames, actualHeaderNames, "Headers are not matching");
	}

	@Test
	public void testViewAllDBColumns() {
		AddDatabasePageUtils.clickOnOverview(page);
		AddDatabasePageUtils.clickOnQueryTab(page);

		String columnNames = "Age, BloodPressure, BMI, DiabetesPedigreeFunction, DIABETES_UNIQUE_ROW_ID, Glucose, Insulin, Outcome, Pregnancies, SkinThickness";
		// validate headers
		List<String> expectedColumnNames = Arrays.asList(columnNames.split(", "));
		List<String> actualColumnNames = AddDatabasePageUtils.getDataColumns(page);
		Assertions.assertEquals(expectedColumnNames, actualColumnNames, "Columns are not matching");
	}

	@Test
	public void testSearchDBColumns() {
		AddDatabasePageUtils.clickOnQueryTab(page);
		String columnName = "BMI";
		AddDatabasePageUtils.searchDataColumn(page, columnName);

		// validate
		boolean isColumnVisible = AddDatabasePageUtils.verifySearchedDataColumn(page, columnName);
		Assertions.assertTrue(isColumnVisible, "Searched " + columnName + " column is not visible in the list");
	}

	@Test
	public void testResetButton() {
		AddDatabasePageUtils.clickOnQueryTab(page);
		String query = "SELECT AGE, BMI from DIABETES";
		AddDatabasePageUtils.enterQuery(page, query);
		AddDatabasePageUtils.clickOnResetButton(page);
		AddDatabasePageUtils.verifyQueryFieldIsEmpty(page);
	}

	@Test
	public void testRefreshDatabaseStructure() {
		AddDatabasePageUtils.clickOnQueryTab(page);
		AddDatabasePageUtils.clickOnRefreshButtonForDataColumns(page);
		String text = "Refreshing database structure";
		boolean isTileVisible = AddDatabasePageUtils.verifyRefreshingTileForDataColumns(page, text);
		Assertions.assertTrue(isTileVisible, "Tile is not visible");
	}

	@Test
	public void testCollapseButtons() {
		AddDatabasePageUtils.clickOnQueryTab(page);

		// collapse
		String collapseButtonName = "Collapse All";
		StoragePageUtils.clickOnButton(page, collapseButtonName);
		assertTrue(AddDatabasePageUtils.verifyAllColumnsAreCollapsed(page));
		assertTrue(AddDatabasePageUtils.verifyButtonNameChanged(page, "Expand All"));

		// expand
		AddDatabasePageUtils.clickOnExpandTableArrow(page, "Expand table");
		assertTrue(AddDatabasePageUtils.verifyButtonNameChanged(page, collapseButtonName));

		// collapse again
		StoragePageUtils.clickOnButton(page, collapseButtonName);
		assertTrue(AddDatabasePageUtils.verifyAllColumnsAreCollapsed(page));
	}

	@AfterAll
	public static void tearDown() {
		CommonUtils.navigateAndDeleteCatalog(page, "Database", dbID);
	}
}