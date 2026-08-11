package aicore.unit.template;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.microsoft.playwright.Page;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.CommonUtils;
import aicore.utils.annotations.PWPage;
import aicore.utils.page.app.AppTemplatePageUtils;
import aicore.utils.page.app.DragAndDropBlocksPageUtils;
import aicore.utils.page.app.TemplateCreationUtils;

public class AskCSVTemplateTests extends AbstractPlaywrightTestBase {
	
	String timestamp = CommonUtils.getTimeStampName();
	String appName = "Test app " + timestamp;

	@BeforeEach
	void setup(@PWPage Page page) {
		loginNativeAdmin(page);
	}	
	@AfterEach
	void tearDown(@PWPage Page page) {
		CommonUtils.navigateAndDeleteApp(page, appName);
	    logout(page);
	}
	
	@Test
	public void askCSVtemplate_test (@PWPage Page page) {
	
	
		appName = TemplateCreationUtils.createAppFromTemplate(page, "Ask CSV");
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


