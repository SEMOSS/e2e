package aicore.unit.database;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import aicore.hooks.SetupHooks;
import aicore.pages.home.MainMenuUtils;
import aicore.utils.AbstractE2ETest;
import aicore.utils.CommonUtils;
import aicore.utils.DatabaseTestUtils;
import aicore.utils.TestResources;
import aicore.utils.page.app.AppPageUtils;

public class AllDatabaseSortTests extends AbstractE2ETest {
	private static String TEST_DB_ID = null;
	private static String DIABETES_DB_ID = null;

	@BeforeAll
	public static void setupAddDB() throws IOException {
		login(page, UserType.NATIVE);
		
		// add 2 zip db
		TEST_DB_ID = DatabaseTestUtils.uploadDatabaseZip(page, TestResources.TEST_DATABASE_NAME, TestResources.TEST_DATABASE_ZIP);
		DIABETES_DB_ID = DatabaseTestUtils.uploadDatabaseZip(page, TestResources.DIABETES_DATABASE_NAME, TestResources.DIABETES_DATABASE_ZIP);
	}
	
	@BeforeEach
	public void setup() throws IOException {
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenDatabase(page);
	}
	
	@Test
	void testNameSort() {
		AppPageUtils.clickOnFilterButton(page, "Ascending");
		boolean isSortedInAscendingOrder = AppPageUtils.verifySortedInAscendingOrder(page);
		Assertions.assertTrue(isSortedInAscendingOrder, "Database are not sorted in ascending order");

		AppPageUtils.clickOnFilterButton(page, "Descending");
		boolean isSortedInDescendingOrder = AppPageUtils.verifySortedInDescendingOrder(page);
		Assertions.assertTrue(isSortedInDescendingOrder, "Database are not sorted in descending order");
	}
	
	@Test
	void testDateCreatedSort() {
		AppPageUtils.selectSortByOption(page, "Date Created");
		AppPageUtils.clickOnFilterButton(page, "Ascending");
		boolean isSortedByDateCreatedAsc = AppPageUtils.verifySortedByDateCreated(SetupHooks.getPage(), true);
		Assertions.assertTrue(isSortedByDateCreatedAsc, "Database are not sorted by date created in ascending order");
		
		AppPageUtils.clickOnFilterButton(page, "Descending");
		boolean isSortedByDateCreatedDesc = AppPageUtils.verifySortedByDateCreated(SetupHooks.getPage(), false);
		Assertions.assertTrue(isSortedByDateCreatedDesc, "Database are not sorted by date created in descending order");
	}
	
	@AfterAll
	static void cleanUp() {
		login(page, UserType.NATIVE);
		boolean deleteDb = CommonUtils.navigateAndDeleteCatalog(page, "Database", TEST_DB_ID);
		assertTrue(deleteDb);

		deleteDb = CommonUtils.navigateAndDeleteCatalog(page, "Database", DIABETES_DB_ID);
		assertTrue(deleteDb);
	}
}
