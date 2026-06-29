package aicore.unit.template;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;

import aicore.pages.home.HomePageUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.CommonUtils;
import aicore.utils.annotations.PWPage;
import aicore.utils.page.app.AppPageUtils;
import aicore.utils.page.app.AppTemplatePageUtils;
import aicore.utils.page.app.CreateAppPopupUtils;
import aicore.utils.page.app.DragAndDropBlocksPageUtils;

public class CustomeFrameToVisualizationTemplateTests extends AbstractPlaywrightTestBase {
	
	String timestamp = CommonUtils.getTimeStampName();
	String appName = "Custome Frame to Visualization App using Template" + timestamp;
	
	String expectedDescription = """
		    This is simply an app that shows you how to create a custom pandas frame in notebook.
		    Use this as inspiration for the cool visualizations you can build off of this.
		    Ask the LLM to create JSON out of data, manually import database engine data and
		    construct a custom pandas frame off of that data (use imagination on how to interact
		    that pulled data with the LLM).
		    """.replace("\n", " ").trim();

	
	@BeforeEach
	void setup(@PWPage Page page) {
		loginNativeAdmin(page);
	}	
	@AfterEach
	void tearDown(@PWPage Page page) {
	    logout(page);
	}

    @Test
    public void customFrameToVisualizationTemplate_test(@PWPage Page page) {
    	
		HomePageUtils.navigateToHomePage(page);
		MainMenuUtils.openMainMenu(page);	
		MainMenuUtils.clickOnOpenAppLibrary(page);
		AppPageUtils.clickOnCreateNewAppButton(page);	
		AppTemplatePageUtils.selectTemplateFromList("Custom Frame to Visualization", page);
		CreateAppPopupUtils.enterAppName(page, appName);				
		CreateAppPopupUtils.enterAppDescription(page, "Test to add description");		
		CreateAppPopupUtils.enterTags(page, "Test1, Test2");
		CreateAppPopupUtils.clickOnCreateButton(page);
		
		String fetchName = CreateAppPopupUtils.userFetchAppName(page);
		Assertions.assertFalse(fetchName.isEmpty(), "Fetched App Name is Empty");
		
		String actualText = AppTemplatePageUtils.userSeePage1(page);
		Assertions.assertEquals("page-1", actualText,
		        "Expected and Actual Text do not match");
    	
		Assertions.assertEquals(
		        "Create Pandas Frame Help Guide",
		        AppTemplatePageUtils.userSeeTeamplatePageTitle(page),
		        "Expected and Actual Block do not match");
		
		AppTemplatePageUtils.verifyDescriptionBelowTitle(expectedDescription,page);
		
		DragAndDropBlocksPageUtils.clickOnBlocksOption(page);	
		
		DragAndDropBlocksPageUtils.mouseHoverOnBlock(page, "Area Chart");

		Assertions.assertTrue(
		        AppTemplatePageUtils.dropChartOnPage(page, "Create Pandas Frame Help Guide"),
		        "Expected: Chart should be visible on the Page after drag-and-drop.");
		
		DragAndDropBlocksPageUtils.clickOnSaveAppButton(page);
    }

}