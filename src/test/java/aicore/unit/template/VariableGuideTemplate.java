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

public class VariableGuideTemplate extends AbstractPlaywrightTestBase {

	
	private static final String ARIAL = "Arial";
	private static final String ARIAL_SIZE = "48";

	private static final String ROBOTO = "Roboto";
	private static final String ROBOTO_SIZE = "38";

	private static final String TIMES_NEW_ROMAN = "Times New Roman";
	private static final String TIMES_NEW_ROMAN_SIZE = "28";

	private static final String GEORGIA = "Georgia";
	private static final String GEORGIA_SIZE = "18";
	
	private static final String DESCRIPTION =
		    "This is an app used to help you understand the usage of our variables within our drag and drop app  builder";
	
	@BeforeEach
	void setup(@PWPage Page page) {
		loginNativeAdmin(page);
	}	
	@AfterEach
	void tearDown(@PWPage Page page) {
	    logout(page);
	}
	

	private void verifyFontAndSize(Page page, String expectedFont, String expectedSize) {
	    Assertions.assertEquals(
	            expectedFont,
	            AppTemplatePageUtils.getSelectedFont(page),
	            "Font style does not match!");

	    Assertions.assertEquals(
	            expectedSize,
	            AppTemplatePageUtils.getFontSize(page),
	            "Font size does not match!");
	}
	
	private void verifyVariableBlock(Page page, String blockName) {
	    Assertions.assertEquals(
	            blockName,
	            AppTemplatePageUtils.userSeeVariableGuideBlocksTitle(page, blockName),
	            "Variable Guide Block title does not match.");

	    Assertions.assertTrue(
	            AppTemplatePageUtils.userSeeTheFontStyleAndSizeBlock(page, blockName),
	            "Expected: After clicking on block '" + blockName
	                    + "', the Font Style and Size options should be visible, but they were not found.");
	}
	

	@Test
    public void VariableGuideTemplate_test(@PWPage Page page) {
		
		
		
		TemplateCreationUtils.createAppFromTemplate(page, "Variables Guide");	
		String fetchName = CreateAppPopupUtils.userFetchAppName(page);
		Assertions.assertFalse(fetchName.isEmpty(), "Fetched App Name is Empty");
		
		
		Assertions.assertEquals(
		        "page-1",
		        AppTemplatePageUtils.userSeePage1(page),
		        "Expected and Actual Text do not match");
		
		
		Assertions.assertEquals(
		        "Variables Example",
		        AppTemplatePageUtils.userSeeTeamplatePageTitle(page),
		        "Expected and Actual Block do not match");
		
		AppTemplatePageUtils.verifyDescriptionBelowTitle(DESCRIPTION, page);

	
		verifyVariableBlock(page, "Engine Variables");
		AppTemplatePageUtils.selectFontStyle(page, ARIAL);
		AppTemplatePageUtils.changeFontSize(page, ARIAL_SIZE);
		verifyFontAndSize(page, ARIAL, ARIAL_SIZE);

		verifyVariableBlock(page, "Data Structure Variables");
		AppTemplatePageUtils.selectFontStyle(page, ROBOTO);
		AppTemplatePageUtils.changeFontSize(page, ROBOTO_SIZE);
		verifyFontAndSize(page, ROBOTO, ROBOTO_SIZE);
		
		verifyVariableBlock(page, "Block Variables");
		AppTemplatePageUtils.selectFontStyle(page, TIMES_NEW_ROMAN);
		AppTemplatePageUtils.changeFontSize(page, TIMES_NEW_ROMAN_SIZE);
		verifyFontAndSize(page, TIMES_NEW_ROMAN, TIMES_NEW_ROMAN_SIZE);
		
	
		verifyVariableBlock(page, "Notebook Variables");
		AppTemplatePageUtils.selectFontStyle(page, GEORGIA);
		AppTemplatePageUtils.changeFontSize(page, GEORGIA_SIZE);
		verifyFontAndSize(page, GEORGIA, GEORGIA_SIZE);
	
		DragAndDropBlocksPageUtils.clickOnBlocksOption(page);
		
		DragAndDropBlocksPageUtils.mouseHoverOnBlock(page, "Area Chart");

		Assertions.assertTrue(
		        AppTemplatePageUtils.dropChartOnPage(page, "Variables Example"),
		        "Expected: Chart should be visible on the page after drag-and-drop, but it was not found.");
	
		
		DragAndDropBlocksPageUtils.clickOnSaveAppButton(page);

		
	}
	
	

}
