package aicore.unit.database;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import aicore.hooks.SetupHooks;
import aicore.pages.database.AddDatabaseFormUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.pages.model.EditModelPageUtils;
import aicore.steps.CatalogFilterSteps;
import aicore.utils.AICorePageUtils;
import aicore.utils.AbstractE2ETest;
import aicore.utils.AddCatalogPageBaseUtils;
import aicore.utils.AddDatabaseFileUploadUtils;
import aicore.utils.AddDatabasePageUtils;
import aicore.utils.CatalogCreationFromZipUtil;
import aicore.utils.CatlogAccessPageUtility;
import aicore.utils.CommonUtils;

public class AllDatabasePageTests extends AbstractE2ETest {
	private static String dbName = null;
	private static String dbID = null;

	@BeforeAll
	public static void setupAddDB() throws IOException {
		login(page, UserType.NATIVE);

		String timestamp = CommonUtils.getTimeStampName();

		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenDatabase(page);

		// add file upload db
		AddDatabaseFormUtils.clickAddDatabaseButton(page);
		String tabName = "file uploads";
		AddDatabaseFileUploadUtils.selectTab(page, tabName);

		String fileType = "Excel";
		dbName = "Excel db" + timestamp;
		String dbType = "h2";
		String metaModelType = "asFlatTable";
		String fileName = "Database/Database.xlsx";

		// db options
		AddDatabaseFileUploadUtils.selectFileType(page, fileType);
		AddDatabaseFileUploadUtils.enterDatabaseName(page, dbName);
		AddDatabaseFileUploadUtils.selectDatabaseType(page, dbType);
		AddDatabaseFileUploadUtils.selectMetamodelType(page, metaModelType);
		CatalogCreationFromZipUtil.uploadFile(page, fileName);
		AddDatabaseFormUtils.clickOnConnectButton(page);

		// validate the db created
		AddDatabaseFileUploadUtils.checkColumnsAreEditable(page);
		AICorePageUtils.clickOnButton(page, "Import");
		boolean isTitleVisible = AddDatabasePageUtils.verifyDatabaseTitle(page, dbName);
		Assertions.assertTrue(isTitleVisible, "Database title is not visible");
		dbID = CatlogAccessPageUtility.getCatalogAndCopyId(page);

		// edit db metadata for filter tests
		AddCatalogPageBaseUtils.clickEditIcon(page);
		// tags
		AddCatalogPageBaseUtils.enterTagName(page, "embeddings");
		AddCatalogPageBaseUtils.enterTagName(page, "Test1");
		// domains
		EditModelPageUtils.enterDomainName(page, "SAP");
		EditModelPageUtils.enterDomainName(page, "AI");
		EditModelPageUtils.selectDataClassificationOption(page, "IP");
		EditModelPageUtils.selectDataClassificationOption(page, "PHI");
		EditModelPageUtils.selectDataRestrictionsOption(page, "IP ALLOWED");
		EditModelPageUtils.selectDataRestrictionsOption(page, "PII ALLOWED");

		AddCatalogPageBaseUtils.clickOnSubmit(page);
	}

	@Test
	void testDatabaseCatalogCard() {
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
	void testFilters(String filterCategory, String filterValue) {
		openCatalogAndValidateSearch();

		List<Map<String, String>> mapList = List
				.of(Map.of("FILTER_CATEGORY", filterCategory, "FILTER_VALUE", filterValue));

		CatalogFilterSteps.validateCatalogFilters(dbName, "FILTER_CATEGORY", "FILTER_VALUE", mapList,
				SetupHooks.getPage());
	}

	private void openCatalogAndValidateSearch() {
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

	@AfterAll
	static void cleanUp() {
		login(page, UserType.NATIVE);
		boolean deleteDb = CommonUtils.navigateAndDeleteCatalog(page, "Database", dbID);
		assertTrue(deleteDb);
	}

}
