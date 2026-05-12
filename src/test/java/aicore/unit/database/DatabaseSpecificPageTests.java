package aicore.unit.database;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;

import aicore.pages.base.EditMetadataPageUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.pages.model.EditModelPageUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.AddCatalogPageBaseUtils;
import aicore.utils.AddDatabasePageUtils;
import aicore.utils.CommonUtils;
import aicore.utils.DatabaseTestUtils;
import aicore.utils.TestResourceTrackerHelper;
import aicore.utils.TestResources;
import aicore.utils.TestTags;
import aicore.utils.ViewUsagePageUtils;
import aicore.utils.annotations.PWPage;
import aicore.utils.page.model.ModelPageUtils;

@Tag(TestTags.SMOKE)
public class DatabaseSpecificPageTests extends AbstractPlaywrightTestBase {

	private String dbName = null;
	private String dbID = null;

	@BeforeEach
	public void setup(@PWPage Page page) throws IOException {
		loginNativeAdmin(page);
		String timestamp = CommonUtils.getTimeStampName();
		dbName = "CSV db" + timestamp;
		String fileName = TestResources.DIABETES_CSV;
		String dbType = "h2";
		String metaModelType = "asFlatTable";
		dbID = DatabaseTestUtils.addFlatCsv(page, dbName, fileName, dbType, metaModelType);
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenDatabase(page);
		AddDatabasePageUtils.searchDatabaseCatalog(page, dbName);
		AddDatabasePageUtils.clickOnDatabaseNameInCatalog(page, dbName);
	}

	@Test
	public void testUsage(@PWPage Page page) throws IOException {
		ViewUsagePageUtils.clickOnUsageTab(page);
		assertTrue(ViewUsagePageUtils.verifyExample(page, "How to use in Pixel"));
		assertTrue(ViewUsagePageUtils.verifyExample(page, "How to use in Python"));
		assertTrue(ViewUsagePageUtils.verifyExample(page, "How to use with LangChain API"));
		assertTrue(ViewUsagePageUtils.verifyExample(page, "How to use in Java"));
	}

	@Test
	public void testMetadata(@PWPage Page page) throws IOException {
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
	public void testOverview(@PWPage Page page) throws IOException {
		AddDatabasePageUtils.clickOnOverview(page);
		String catalogDescription = "No description available";
		assertTrue(AddCatalogPageBaseUtils.verifyCatalogDescription(page, catalogDescription));
	}

	@Test
	public void testExport(@PWPage Page page) throws IOException, InterruptedException {
		Path path = AddDatabasePageUtils.clickOnExportButton(page);
		assertTrue(path.toFile().exists());
		assertTrue(path.toAbsolutePath().getFileName().toString().contains(dbID));
	}

	/////////////////////// EDIT

	@Test
	public void testEdit(@PWPage Page page) throws IOException, InterruptedException {
		EditMetadataPageUtils.clickEditIcon(page);
		EditMetadataPageUtils.clickOnClose(page);
	}

	@Test
	public void testViewMetadataTags(@PWPage Page page) throws IOException {
		EditMetadataPageUtils.clickEditIcon(page);
		String tagName = "embeddings";
		EditMetadataPageUtils.enterTagName(page, tagName);
		EditMetadataPageUtils.clickOnSubmit(page);
		// TODO fix the toast message check
//		AddCatalogPageBaseUtils.verifyEditSuccessfullToastMessage(page);
		List<String> tags = EditModelPageUtils.verifyTagNames(page);
		assertTrue(tags.contains(tagName));
	}

	@Test
	@DisplayName("Validate the available tool and their input parameter after MCP Generation for database")
	public void testValidateToolsAfterMCPGeneration() throws IOException {
		String toastMessage = "MCP generated";
		AICorePageUtils.clickOnTabButton(page, "MCP Usage");
		EditModelPageUtils.clickOnGenerateMCPButtonFromMCPUsageTab(page);
		String actualMessage = ModelPageUtils.modelCreationToastMessage(page, toastMessage);
		Assertions.assertEquals(actualMessage, toastMessage, "Generate MCP creation failed");
		Map<String, List<String>> toolData = new HashMap<>();
		toolData.put("Get Database Table Structure", Arrays.asList("database"));
		toolData.put("Sql Query Base64", Arrays.asList("database", "limit", "query", "commit"));
		toolData.forEach((toolName, parameters) -> {
			boolean isToolPresent = EditModelPageUtils.verifyToolsInGeneratedMCP(page, toolName);
			Assertions.assertTrue(isToolPresent, "Tool not displayed: " + toolName);
			boolean isParamPresent = EditModelPageUtils.verifyInputParameters(page, toolName, parameters);
			Assertions.assertTrue(isParamPresent, "Parameters not correct for: " + toolName);
		});
	}

	@AfterEach
	public void tearDown(@PWPage Page page) {
		CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_DATABASE, dbID);
		logout(page);
	}

}
