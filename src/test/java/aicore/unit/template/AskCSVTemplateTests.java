package aicore.unit.template;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.microsoft.playwright.Page;
import aicore.pages.home.HomePageUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.annotations.PWPage;
import aicore.utils.page.app.AppPageUtils;
import aicore.utils.page.app.AppTemplatePageUtils;
import aicore.utils.page.app.CreateAppPopupUtils;
import aicore.utils.page.app.DragAndDropBlocksPageUtils;

public class AskCSVTemplateTests extends AbstractPlaywrightTestBase {
	
	
	@BeforeEach
	void setup(@PWPage Page page) {
		loginNativeAdmin(page);
	}	
	@AfterEach
	void tearDown(@PWPage Page page) {
	    logout(page);
	}
	
	@Test
	public void askCSVtemplate_test (@PWPage Page page) {
	
	
		HomePageUtils.navigateToHomePage(page);
		MainMenuUtils.openMainMenu(page);	
		MainMenuUtils.clickOnOpenAppLibrary(page);
		AppPageUtils.clickOnCreateNewAppButton(page);	
		AppTemplatePageUtils.selectTemplateFromList("Ask CSV", page);
		CreateAppPopupUtils.enterAppName(page, "Test app");				
		CreateAppPopupUtils.enterAppDescription(page, "Created by automation script");		
		CreateAppPopupUtils.enterTags(page, "Test1, Test2");
		CreateAppPopupUtils.clickOnCreateButton(page );	
		AppTemplatePageUtils.verifyPageWithTitle("CSV Query", page);
		AppTemplatePageUtils.clickOnQuestionBlock(page, "title");
		AppTemplatePageUtils.changeAppPageTitle("CSV Query", "CSV Query - Edited", page);
		AppTemplatePageUtils.clickOnQuestionBlock(page, "question");
		AppTemplatePageUtils.addDescription("What is the total sales?", page);
		AppTemplatePageUtils.verifyDescription("Upload a csv file and ask a question", page);
		AppTemplatePageUtils.clickOnQuestionBlock(page, "description");
		AppTemplatePageUtils.changeAppPageTitle("Upload a csv file and ask a question", "Upload a csv file and ask a question - Edited", page);
		AppTemplatePageUtils.verifySubmitButton(page);
		DragAndDropBlocksPageUtils.clickOnSaveAppButton(page);		
		AppTemplatePageUtils.clickPreviewButton(page);
		AppTemplatePageUtils.verifyPageWithTitleInPreview("CSV Query - Edited", page);
		AppTemplatePageUtils.clickClosePreviewButton(page);
	}

}


