package aicore.unit.template;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.microsoft.playwright.Page;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.annotations.PWPage;
import aicore.utils.page.app.AppTemplatePageUtils;
import aicore.utils.page.app.CreateAppPopupUtils;
import aicore.utils.page.app.DragAndDropBlocksPageUtils;
import aicore.utils.page.app.TemplateCreationUtils;

public class CustomeFrameToVisualizationTemplateTests extends AbstractPlaywrightTestBase {
	
	private static final String EXPECTED_DESCRIPTION =
		    "This is simply an app that shows you how to create a custom pandas frame in notebook.  "
		  + "Use this as inspiration for the cool visualizations you can build off of this.  "
		  + "Ask the LLM to create JSON out of data, manually import database engine data and construct "
		  + "a custom pandas frame off of that data (use imagination on how to interact that pulled data "
		  + "with the LLM).";

	
	private static final String TEMPLATE_NAME = "Custom Frame to Visualization";
	private static final String PAGE_NAME = "page-1";
	private static final String BLOCK_NAME = "Create Pandas Frame Help Guide";
	private static final String CHART_NAME = "Area Chart";
	
	@BeforeEach
	void setup(@PWPage Page page) {
		loginNativeAdmin(page);
	}	
	@AfterEach
	void tearDown(@PWPage Page page) {
	    logout(page);
	}
	
	private void verifyAppCreated(Page page) {
	    String appName = CreateAppPopupUtils.userFetchAppName(page);
	    Assertions.assertFalse(appName.isEmpty(), "Fetched App Name is Empty");
	}

    @Test
    public void customFrameToVisualizationTemplate_test(@PWPage Page page) {
    	
		TemplateCreationUtils.createAppFromTemplate(page, TEMPLATE_NAME);
		verifyAppCreated(page);	
		
		String actualText = AppTemplatePageUtils.userSeePage1(page);
		Assertions.assertEquals(PAGE_NAME, actualText,
		        "Expected and Actual Text do not match");
    	
		Assertions.assertEquals(
				BLOCK_NAME,
		        AppTemplatePageUtils.userSeeTeamplatePageTitle(page),
		        "Expected and Actual Block do not match");
				 
		AppTemplatePageUtils.verifyDescriptionBelowTitle(EXPECTED_DESCRIPTION,page);
		DragAndDropBlocksPageUtils.clickOnBlocksOption(page);	
		DragAndDropBlocksPageUtils.mouseHoverOnBlock(page, CHART_NAME);
		Assertions.assertTrue(
		        AppTemplatePageUtils.dropChartOnPage(page, BLOCK_NAME),
		        "Expected: Chart should be visible on the Page after drag-and-drop.");
		
		DragAndDropBlocksPageUtils.clickOnSaveAppButton(page);
    }

}