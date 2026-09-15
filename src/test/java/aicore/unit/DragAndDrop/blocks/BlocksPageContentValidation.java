package aicore.unit.DragAndDrop.blocks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.microsoft.playwright.Page;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.CommonUtils;
import aicore.utils.VectorSettingPageUtils;
import aicore.utils.annotations.PWPage;
import aicore.utils.page.app.CreateAppPopupUtils;
import aicore.utils.page.app.DragAndDropBlocksPageUtils;
import aicore.utils.page.app.TemplateCreationUtils;

public class BlocksPageContentValidation extends AbstractPlaywrightTestBase{
	
	@BeforeEach
	void setup(@PWPage Page page) {
		loginNativeAdmin(page);
	}	
	@AfterEach
	void tearDown(@PWPage Page page) {
		CommonUtils.navigateAndDeleteApp(page, appName);
	    logout(page);
	}
	
	private void verifyAppCreated(Page page) {
	    String appName = CreateAppPopupUtils.userFetchAppName(page);
	    Assertions.assertFalse(appName.isEmpty(), "Fetched App Name is Empty");
	}
	
	private void verifyWelcomePage(Page page) {
	    Assertions.assertTrue(
	            DragAndDropBlocksPageUtils.verifyPage1IsVisible(page),
	            "Page is not visible");

	    Assertions.assertTrue(
	            DragAndDropBlocksPageUtils.verifyWelcomeTextboxIsVisible(page),
	            "Welcome text box is not visible");

	    Assertions.assertEquals(
	            "Welcome to the UI Builder! Drag and drop blocks to use in your app.",
	            DragAndDropBlocksPageUtils.verifyWelcomeText(page),
	            "Mismatch between the expected and actual welcome message");
	}
	
	private void verifyBlockSettingsPanelTitle(Page page) {
	    String expectedTitle = "Add Blocks";
	    String option = "Blocks";

	    String actualTitle = DragAndDropBlocksPageUtils.getBlockSettingsPanelTitle(page, expectedTitle, option);

	    Assertions.assertEquals(
	            expectedTitle,
	            actualTitle,
	            "Mismatch between the expected and actual Block Settings panel title");
	}
	
	private void verifySectionVisible(Page page, String sectionName) {
	    Assertions.assertTrue(
	            DragAndDropBlocksPageUtils.userSeesTheSection(page, sectionName),
	            sectionName + " section is not visible under the Block panel");
	}
	
	private void verifyOptionsUnderSection(Page page, String sectionName, String... optionNames) {
	    for (String optionName : optionNames) {
	        Assertions.assertTrue(
	                DragAndDropBlocksPageUtils.isOptionVisibleUnderSection(page, sectionName, optionName),
	                optionName + " option is not visible under the " + sectionName + " section");
	    }
	}
	
	private String appName =
	        "Test app " + CommonUtils.getTimeStampName();
	
	@Test
    public void BlocksPageContentValidation_test(@PWPage Page page) {
		
		appName = TemplateCreationUtils.createDragAndDropApp(page, "Drag and Drop");
		verifyAppCreated(page);	
	    verifyWelcomePage(page);
	    
		DragAndDropBlocksPageUtils.clickOnBlocksOption(page);
		DragAndDropBlocksPageUtils.clickOnBlockSettingsOption(page);
		verifyBlockSettingsPanelTitle(page);
	    VectorSettingPageUtils.isSearchBarPresent(page);
	    verifySectionVisible(page, "System Blocks");
	    verifySectionVisible(page, "Community Blocks");
	    verifyOptionsUnderSection(
	            page,
	            "Layout",
	            "Accordion",
	            "Container",
	            "Flip Card",
	            "Form",
	            "Iterator",
	            "Modal",
	            "Popover",
	            "Sidebar-Menu",
	            "Tab");

	}

}
