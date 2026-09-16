package aicore.unit.DragAndDrop.notebook;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;

import aicore.pages.database.AddDatabaseFormUtils;
import aicore.pages.home.HomePageUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.AddDatabasePageUtils;
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
	private static final Logger logger = LogManager.getLogger(NotebookQueryUppercaseTests.class);
    SoftAssertions softAssert = new SoftAssertions();
	private static final String CATALOG_TYPE = "Database";
	private static final String TEST_DATABASE_NAME = "TestDatabase"; 

	private String appName = "";

	@BeforeEach
	void setup(@PWPage Page page) {
	    logger.info("BEFORE ALL: creating DataBase");
	    loginAdmin(page);
	    HomePageUtils.navigateToHomePage(page);
	    MainMenuUtils.openMainMenu(page);
	    MainMenuUtils.clickOnOpenDatabase(page);
	    AddDatabaseFormUtils.clickAddDatabaseButton(page);
	    CatalogCreationFromZipUtil.clickOnFileUploadIcon(page);
	    String uploadedFileName = CatalogCreationFromZipUtil.uploadFile(page, TestResources.TEST_DATABASE_ZIP);

	  
	    softAssert.assertThat(uploadedFileName)
	            .as("File name should not be null")
	            .isNotNull();
	    softAssert.assertThat(uploadedFileName)
	            .as("Should be a zip file")
	            .contains(".zip");

	    CatalogCreationFromZipUtil.clickOnUploadButton_New(page);
	    CatlogAccessPageUtility.getCatalogAndCopyId(page);

	    softAssert.assertThat(AddDatabasePageUtils.verifyDatabaseTitle(page, TEST_DATABASE_NAME))
	          .as("Database title is not visible")
	          .isTrue();

	    CatalogPageUtils.clickOnMetadataTab(page);

	    
	    logger.info("BEFORE ALL: creating App");
	    appName = TemplateCreationUtils.createDragAndDropApp(page, "Drag and Drop");

	    softAssert.assertThat(DragAndDropBlocksPageUtils.verifyPage1IsVisible(page))
	            .as("Page is not visible")
	            .isTrue();
	    softAssert.assertThat(DragAndDropBlocksPageUtils.verifyWelcomeTextboxIsVisible(page))
	            .as("Welcome text box not visible")
	            .isTrue();

	    softAssert.assertThat(DragAndDropBlocksPageUtils.verifyWelcomeText(page))
	          .as("Mismatch between the expected and actual message")
	          .isEqualTo("Welcome to the UI Builder! Drag and drop blocks to use in your app.");
	    
	    BlockSettingsUtils.closeBlockSettings(page);
	    
	    
	    
	    logger.info("BEFORE ALL: clickOnNotebooksOption");
		NotebookPageUtils.clickOnNotebooksOption(page);
		NotebookPageUtils.clickOnCreateNewNotebook(page);
		NotebookPageUtils.enterQueryName(page, "Test");
		NotebookPageUtils.clickOnQuerySubmitButton(page);
		NotebookPageUtils.mouseHoverOnNotebookHiddenOptions(page);
		NotebookPageUtils.clickOnHiddenNotebookOption(page, "Import Data");
		NotebookPageUtils.selectHiddenOptionDropdown(page, "Custom Query");
		NotebookPageUtils.deleteFirstCell(page);
		NotebookPageUtils.selectDatabaseType(page, TEST_DATABASE_NAME);
	}

	@AfterEach
	void tearDown(@PWPage Page page) {
		logger.info("AFTER ALL: Deleting App and Catalog");
		CommonUtils.navigateAndDeleteApp(page, appName);
		CommonUtils.navigateAndDeleteCatalog(page, CATALOG_TYPE,  TEST_DATABASE_NAME );	
		logout(page);
	}
	

	@Test
	@ResourceUploadLock(TestResources.TEST_DATABASE_ZIP)
	@DisplayName("TC01_Validate Uppercase function in Transformation")
	void testValidateUppercaseFunctionInTransformation(@PWPage Page page) {
		
		NotebookPageUtils.writeQuery(page, "SELECT TASK_GROUP FROM DIABETES LIMIT 20");
		NotebookPageUtils.clickOnRunCellButton(page);
		String frameID = NotebookPageUtils.getFrameID(page);

		NotebookPageUtils.mouseHoverOnNotebookHiddenOptionsOnCurrentCell(page);
		NotebookPageUtils.clickTransformationOnCurrentCell(page);

		
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