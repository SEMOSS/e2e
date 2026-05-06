package aicore.unit.database;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.microsoft.playwright.Page;

import aicore.pages.base.EditMetadataPageUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.pages.model.EditModelPageUtils;
import aicore.steps.CatalogFilterSteps;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.AddDatabasePageUtils;
import aicore.utils.CommonUtils;
import aicore.utils.DatabaseTestUtils;
import aicore.utils.TestResourceTrackerHelper;
import aicore.utils.TestResources;
import aicore.utils.annotations.PWPage;
import aicore.utils.annotations.ResourceUploadLock;

public class AllDatabasePageTests extends AbstractPlaywrightTestBase {
	private String dbName = null;
	private String dbID = null;

	@BeforeEach
	public void setupAddDB(@PWPage Page page) throws IOException {
		loginNativeAdmin(page);
		
		// add zip db
		String fileName = TestResources.TEST_DATABASE_ZIP;
		dbName = "TestDatabase";
//		acquireTestDatabaseZipLock(() -> 
		dbID = DatabaseTestUtils.uploadDatabaseZip(page, dbName, fileName);
//		);
//		dbID = DatabaseTestUtils.getDatabaseID(page, dbName);

		// edit db metadata for filter tests
		EditMetadataPageUtils.clickEditIcon(page);
		// tags
		EditMetadataPageUtils.enterTagName(page, "embeddings");
		EditMetadataPageUtils.enterTagName(page, "Test1");
		// domains
		EditMetadataPageUtils.enterDomainName(page, "SAP");
		EditMetadataPageUtils.enterDomainName(page, "AI");
		EditMetadataPageUtils.selectDataClassificationOption(page, "IP");
		EditMetadataPageUtils.selectDataClassificationOption(page, "PHI");
		EditMetadataPageUtils.selectDataRestrictionsOption(page, "IP ALLOWED");
		EditMetadataPageUtils.selectDataRestrictionsOption(page, "PII ALLOWED");
		EditMetadataPageUtils.clickOnSubmit(page);
	}
	
	@AfterEach
	void tearDown(@PWPage Page page) {
		loginNativeAdmin(page);
//		releaseTestDatabaseZipLock(()->
		assertTrue( CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_DATABASE, dbID));
//		);
		logout(page);
	}

	@Test
	@ResourceUploadLock(TestResources.TEST_DATABASE_ZIP)
	void testCatalogCard(@PWPage Page page) {
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenDatabase(page);
		AddDatabasePageUtils.searchDatabaseCatalog(page, dbName);

		// validate search
		String databaseNameInCatalog = AddDatabasePageUtils.verifyDatabaseNameInCatalog(page, dbName);
		boolean databaseNameFlag = databaseNameInCatalog.contains(dbName);
		Assertions.assertTrue(databaseNameFlag, "Database name is not visible in the database catalog");

		// test id on card
		boolean isIdVisible = EditModelPageUtils.validateIDisDisplayedOnCatalogCard(page, dbID);
		Assertions.assertTrue(isIdVisible, "Catalog ID is not visible on the catalog card");

		// validate tags on card
		String[] tagArray = "embeddings, Test1".split(", ");
		List<String> actualTagList = EditModelPageUtils.verifyTagNamesDisplayedOnCard(page, "Database");
		List<String> expectedTagList = Arrays.asList(tagArray);
		Assertions.assertEquals(expectedTagList, actualTagList);

		assertTrue(EditModelPageUtils.isCreatedDateVisibleOnCard(page));

		//TODO make tests to test the behavior of clicking on these icons
		List<String> icons = List.of("lock", "bookmark", "view logs dashboard", "delete");
		for (String icon : icons) {
			boolean isIconVisible = EditModelPageUtils.isIconVisibleOnCatalogCard(page, icon);
			Assertions.assertTrue(isIconVisible, "Icon '" + icon + "' is not visible on the catalog card");
		}
	}
	
	//////////////// test filters
	@ParameterizedTest(name = "{index} => {0} = {1}")
	@MethodSource("filterData")
	@ResourceUploadLock(TestResources.TEST_DATABASE_ZIP)
	void testFilters(String filterCategory, String filterValue, @PWPage Page page) {
		openCatalogAndValidateSearch(page);

		List<Map<String, String>> mapList = List
				.of(Map.of("FILTER_CATEGORY", filterCategory, "FILTER_VALUE", filterValue));

		CatalogFilterSteps.validateCatalogFilters(dbName, "FILTER_CATEGORY", "FILTER_VALUE", mapList,
				page);
	}

	private void openCatalogAndValidateSearch(@PWPage Page page) {
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenDatabase(page);

		AddDatabasePageUtils.searchDatabaseCatalog(page, dbName);
		String databaseNameInCatalog = AddDatabasePageUtils.verifyDatabaseNameInCatalog(page, dbName);
		Assertions.assertTrue(databaseNameInCatalog.contains(dbName),
				"Database name is not visible in the database catalog");
	}

	private static Stream<Arguments> filterData() {
		return Stream.of(Arguments.of("Tag", "embeddings, Test1"), 
					Arguments.of("Domain", "SAP, AI"),
					Arguments.of("Domain", "AI"),
					Arguments.of("Data Classification", "IP"), 
					Arguments.of("Data Classification", "PII"),
					Arguments.of("Data Restrictions", "IP ALLOWED"), 
					Arguments.of("Data Restrictions", "PII ALLOWED")
		);
	}

}
