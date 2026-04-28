package aicore.unit.database;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import aicore.pages.home.MainMenuUtils;
import aicore.pages.model.EditModelPageUtils;
import aicore.utils.AbstractE2ETest;
import aicore.utils.AddCatalogPageBaseUtils;
import aicore.utils.AddDatabasePageUtils;
import aicore.utils.CommonUtils;
import aicore.utils.DatabaseTestUtils;
import aicore.utils.TestResources;
import aicore.utils.TestTags;
import aicore.utils.ViewUsagePageUtils;

@Tag(TestTags.SMOKE)
public class DatabaseSpecificPageTests extends AbstractE2ETest {

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
	}


	@Test
	public void testUsage() throws IOException {
		ViewUsagePageUtils.clickOnUsageTab(page);
		assertTrue(ViewUsagePageUtils.verifyExample(page, "How to use in Pixel"));
		assertTrue(ViewUsagePageUtils.verifyExample(page, "How to use in Python"));
		assertTrue(ViewUsagePageUtils.verifyExample(page, "How to use with LangChain API"));
		assertTrue(ViewUsagePageUtils.verifyExample(page, "How to use in Java"));
	}

	@Test
	public void testMetadata() throws IOException {
		AddDatabasePageUtils.clickOnMetadataTab(page);
		AddDatabasePageUtils.clickOnRefreshButton(page);
		// TODO need to try to edit and sync the metadata tab to see a bigger change
		AddDatabasePageUtils.selectDatabaseFromDropdown(page, "DIABETES");
		AddDatabasePageUtils.selectDatabaseFromDropdown(page, "DIABETES");
		AddDatabasePageUtils.clickApplyDatabaseButton(page);
		AddDatabasePageUtils.verifyMetaData(page);
		AddDatabasePageUtils.clickOnSaveButton(page);
	}

	@Test
	public void testOverview() throws IOException {
		AddDatabasePageUtils.clickOnOverview(page);
		String catalogDescription = "No description available";
		assertTrue(AddCatalogPageBaseUtils.verifyCatalogDescription(page, catalogDescription));
	}

	@Test
	public void testExport() throws IOException, InterruptedException {
		Path path = AddDatabasePageUtils.clickOnExportButton(page);
		assertTrue(path.toFile().exists());
	}
	
	/////////////////////// EDIT

	@Test
	public void testEdit() throws IOException, InterruptedException {
		AddCatalogPageBaseUtils.clickEditIcon(page);
		AddCatalogPageBaseUtils.clickOnClose(page);
	}
	
	@Test
	public void testViewDatabaseTags() throws IOException {
		AddCatalogPageBaseUtils.clickEditIcon(page);
		String tagName = "embeddings";
		AddCatalogPageBaseUtils.enterTagName(page, tagName);
		AddCatalogPageBaseUtils.clickOnSubmit(page);
		//TODO fix the toast message check
//		AddCatalogPageBaseUtils.verifyEditSuccessfullToastMessage(page);
		List<String> tags = EditModelPageUtils.verifyTagNames(page);
		assertTrue(tags.contains(tagName));
	}

	@AfterAll
	public static void tearDown() {
		CommonUtils.navigateAndDeleteCatalog(page, "Database", dbID);
	}

}
