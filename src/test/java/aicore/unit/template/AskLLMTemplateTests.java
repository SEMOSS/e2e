package aicore.unit.template;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
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
import aicore.utils.page.app.TemplateCreationUtils;

public class AskLLMTemplateTests extends AbstractPlaywrightTestBase {

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
	public void askLLMtemplate_test (@PWPage Page page) {
		
		String appName = TemplateCreationUtils.createAppFromTemplate(page, "Ask LLM");
		verifyAppCreated(page);		
		AppTemplatePageUtils.verifyPageWithTitle("Ask LLM", page);
		AppTemplatePageUtils.verifyDescription("Ask an LLM a question", page);
		AppTemplatePageUtils.verifyInputFieldWithLabel("question", page);
		AppTemplatePageUtils.verifySubmitButton(page);
		AppTemplatePageUtils.clickPreviewButton(page);
		AppTemplatePageUtils.verifyPageWithTitleInPreview("Ask LLM", page);
		AppTemplatePageUtils.verifyDescriptionInPreview("Ask an LLM a question", page);
		AppTemplatePageUtils.verifyInputFieldWithLabelInPreview("Question", page);
		AppTemplatePageUtils.verifySubmitButtonInPreview(page);
		AppTemplatePageUtils.clickClosePreviewButton(page);
		HomePageUtils.navigateToHomePage(page);
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenAppLibrary(page);
		AppPageUtils.searchApp(page, appName, "");		
		AppPageUtils.clickOnAppCard(page, appName,"");

	}

}
