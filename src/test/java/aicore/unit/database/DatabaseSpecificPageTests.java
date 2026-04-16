package aicore.unit.database;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;

import aicore.base.GenericSetupUtils;
import aicore.framework.AICoreTestConstants;
import aicore.framework.ConfigUtils;
import aicore.pages.database.AddDatabaseFormUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.pages.model.EditModelPageUtils;
import aicore.pages.model.SettingsModelPageUtils;
import aicore.utils.AICorePageUtils;
import aicore.utils.AddCatalogPageBaseUtils;
import aicore.utils.AddDatabaseFileUploadUtils;
import aicore.utils.AddDatabasePageUtils;
import aicore.utils.AddFunctionPageUtils;
import aicore.utils.CatalogCreationFromZipUtil;
import aicore.utils.CatlogAccessPageUtility;
import aicore.utils.CommonUtils;
import aicore.utils.ViewUsagePageUtils;

@Tag("SMOKE")
public class DatabaseSpecificPageTests {

	private static String dbName = null;
	private static String dbID = null;
	private static Page page = null;

	@BeforeAll
	public static void setup() throws IOException {
		GenericSetupUtils.initialize();
		page = GenericSetupUtils.setupPlaywright();
		String nativeUser = ConfigUtils.getValue(AICoreTestConstants.NATIVE_USERNAME);
		String nativePassword = ConfigUtils.getValue(AICoreTestConstants.NATIVE_PASSWORD);
		GenericSetupUtils.login(page, nativeUser, nativePassword);

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

	}

	@Test
	public void testViewDatabaseTags() throws IOException {
		AddCatalogPageBaseUtils.clickEditIcon(page);
		String tagName = "embeddings";
		AddCatalogPageBaseUtils.enterTagName(page, tagName);
		AddCatalogPageBaseUtils.clickOnSubmit(page);
		AddCatalogPageBaseUtils.verifyEditSuccessfullToastMessage(page);
		List<String> tags = EditModelPageUtils.verifyTagNames(page);
		assertTrue(tags.contains(tagName));
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
		AddDatabasePageUtils.selectDatabaseFromDropdown(page, "SHEET1");
		AddDatabasePageUtils.selectDatabaseFromDropdown(page, "SHEET1");
		AddDatabasePageUtils.clickApplyDatabaseButton(page);
		AddDatabasePageUtils.verifyMetaData(page);
		AddDatabasePageUtils.clickOnSaveButton(page);
	}

	@Test
	public void testOverview() throws IOException {
		AddDatabasePageUtils.clickOnOverview(page);
		String catalogDescription = "No Markdown available";
		assertTrue(AddCatalogPageBaseUtils.verifyCatalogDescription(page, catalogDescription));
	}

	@Test
	public void testAccessControl() throws IOException, InterruptedException {
		AddFunctionPageUtils.clickOnAccessControl(page);
		SettingsModelPageUtils.clickOnAddMembersButton(page);
		String role = "Read";
		boolean useDocker = false;
		SettingsModelPageUtils.addMember(page, role, useDocker);
	}

	@Test
	public void testExport() throws IOException, InterruptedException {
		Path path = AddDatabasePageUtils.clickOnExportButton(page);
		assertTrue(path.toFile().exists());
	}

	@Test
	public void testEdit() throws IOException, InterruptedException {
		AddCatalogPageBaseUtils.clickEditIcon(page);
		AddCatalogPageBaseUtils.clickOnClose(page);
	}

	@AfterAll
	public static void tearDown() {
		CommonUtils.navigateAndDeleteCatalog(page, "Database", dbID);
	}

}
