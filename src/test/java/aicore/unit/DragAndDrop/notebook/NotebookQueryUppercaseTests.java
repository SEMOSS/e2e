package aicore.unit.DragAndDrop.notebook;

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
import aicore.utils.annotations.ResourceUploadLock;
import aicore.utils.page.app.BlockSettingsUtils;
import aicore.utils.page.app.DragAndDropBlocksPageUtils;
import aicore.utils.page.app.NotebookPageUtils;
import aicore.utils.page.app.TemplateCreationUtils;
import aicore.utils.TestResources;

public class NotebookQueryUppercaseTests extends AbstractPlaywrightTestBase {

	private static final String CATALOG_TYPE = "Database";
	private static final String CATALOG_NAME = "TestDatabase";

	private String appName = "";

	@BeforeEach
	@ResourceUploadLock(TestResources.TEST_DATABASE_ZIP)
	void setup(@PWPage Page page) {
		loginAdmin(page);

		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenDatabase(page);
		AddFunctionPageUtils.deleteCatalog(page, CATALOG_TYPE, CATALOG_NAME);
		AddDatabaseFormUtils.clickAddDatabaseButton(page);
		CatalogCreationFromZipUtil.clickOnFileUploadIcon(page);
		String uploadedFileName = CatalogCreationFromZipUtil.uploadFile(page, TestResources.TEST_DATABASE_ZIP);
		Assertions.assertEquals("TestDatabase.zip", uploadedFileName, "file is not uploaded successfully");
		CatalogCreationFromZipUtil.clickOnUploadButton(page, "Upload");
		CatlogAccessPageUtility.getCatalogAndCopyId(page);
		Assertions.assertTrue(AddDatabasePageUtils.verifyDatabaseTitle(page, CATALOG_NAME),
				"Database title is not visible");
		CatalogPageUtils.clickOnMetadataTab(page);

		appName = TemplateCreationUtils.createDragAndDropApp(page, "Drag and Drop");

		Assertions.assertTrue(DragAndDropBlocksPageUtils.verifyPage1IsVisible(page), "Page is not visible");
		Assertions.assertTrue(DragAndDropBlocksPageUtils.verifyWelcomeTextboxIsVisible(page),
				"Welcome text box not visible");
		Assertions.assertEquals("Welcome to the UI Builder! Drag and drop blocks to use in your app.",
				DragAndDropBlocksPageUtils.verifyWelcomeText(page), "Mismatch between the expected and actual message");

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
	@ResourceUploadLock(TestResources.TEST_DATABASE_ZIP)
	void tearDown(@PWPage Page page) {
		CommonUtils.navigateAndDeleteApp(page, appName);
		CommonUtils.navigateAndDeleteCatalog(page, CATALOG_TYPE, CATALOG_NAME);
		logout(page);
	}
	

	@Test
	@DisplayName("TC02_Validate Uppercase function in Transformation")
	void testValidateUppercaseFunctionInTransformation(@PWPage Page page) {
		NotebookPageUtils.writeQuery(page, "SELECT TASK_GROUP FROM DIABETES LIMIT 20");
		NotebookPageUtils.clickOnRunCellButton(page);
		String frameID = NotebookPageUtils.getFrameID(page);

		NotebookPageUtils.mouseHoverOnNotebookHiddenOptions(page);
		NotebookPageUtils.clickOnHiddenNotebookOption(page, "Transformation");
		NotebookPageUtils.selectTransformationOptionDropdown(page, "Uppercase");
		BlockSettingsUtils.selectFrame(page, frameID);
		NotebookPageUtils.selectColumnForTransformation(page, "TASK_GROUP");
		NotebookPageUtils.clickOnRunAllCellButton(page);

		List<String> expectedHeaderNames = Arrays.asList("TASK_GROUP");
		List<String> actualHeaderNames = NotebookPageUtils.getNotebookOutputTableHeader(page);
		Assertions.assertEquals(expectedHeaderNames, actualHeaderNames, "Headers are not matching");

		Assertions.assertTrue(NotebookPageUtils.isColumnDataInUppercase(page, "TASK_GROUP"),
				"Column data is not in uppercase format for column: TASK_GROUP");
	}
}