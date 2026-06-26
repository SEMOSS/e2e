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

public class AskLLMTemplateTests extends AbstractPlaywrightTestBase {
	
	String timestamp = CommonUtils.getTimeStampName();
	String appName = "Test app" + timestamp;

	@BeforeEach
	void setup(@PWPage Page page) {
		loginNativeAdmin(page);
	}	
	@AfterEach
	void tearDown(@PWPage Page page) {
	    logout(page);
	}

	@Test
	public void askLLMtemplate_test (@PWPage Page page) {
		
		HomePageUtils.navigateToHomePage(page);
		MainMenuUtils.openMainMenu(page);	
		MainMenuUtils.clickOnOpenAppLibrary(page);
		AppPageUtils.clickOnCreateNewAppButton(page);	
		AppTemplatePageUtils.selectTemplateFromList("Ask LLM", page);
		CreateAppPopupUtils.enterAppName(page, appName);				
		CreateAppPopupUtils.enterAppDescription(page, "Created by automation script");		
		CreateAppPopupUtils.enterTags(page, "Test1, Test2");
		CreateAppPopupUtils.clickOnCreateButton(page);
		String fetchName = CreateAppPopupUtils.userFetchAppName(page);
		Assertions.assertFalse(fetchName.isEmpty(), "Fetched App Name is Empty");		
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
		AppPageUtils.searchApp(page, appName, null);		
		AppPageUtils.clickOnAppCard(page, appName,"");

	}

}
