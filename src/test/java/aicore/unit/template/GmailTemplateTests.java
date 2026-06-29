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

public class GmailTemplateTests extends AbstractPlaywrightTestBase {
	
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
    public void GmailTemplate_test(@PWPage Page page) {
		
		HomePageUtils.navigateToHomePage(page);
		MainMenuUtils.openMainMenu(page);	
		MainMenuUtils.clickOnOpenAppLibrary(page);
		AppPageUtils.clickOnCreateNewAppButton(page);	
		AppTemplatePageUtils.selectTemplateFromList("Gmail", page);
		CreateAppPopupUtils.enterAppName(page, appName);				
		CreateAppPopupUtils.enterAppDescription(page, "Created by automation script");		
		CreateAppPopupUtils.enterTags(page, "Test1, Test2");
		CreateAppPopupUtils.clickOnCreateButton(page);
		
		String fetchName = CreateAppPopupUtils.userFetchAppName(page);
		Assertions.assertFalse(fetchName.isEmpty(), "Fetched App Name is Empty");
		
		AppTemplatePageUtils.verifyAppTemplateTitle("Gmail", page);
		AppTemplatePageUtils.verifyDialogText("Logged in by :", page);
		AppTemplatePageUtils.verifyButtonIsEnabled("Refresh", page);
		AppTemplatePageUtils.verifyButtonIsEnabled("Compose Mail", page);	
		AppTemplatePageUtils.verifyTabIsVisible("All Mails", page);
		AppTemplatePageUtils.verifyButtonIsEnabled("Read", page);
		AppTemplatePageUtils.verifyButtonIsEnabled("Delete", page);
		AppTemplatePageUtils.verifyTabIsVisible("Unread Mails", page);
		AppTemplatePageUtils.verifyButtonIsEnabled("Read", page);
		AppTemplatePageUtils.verifyTabIsVisible("Sent Mails", page);
		AppTemplatePageUtils.clickPreviewButton(page);
		AppTemplatePageUtils.verifyAppTemplateTitle("Gmail", page);
		AppTemplatePageUtils.verifyDialogText("Logged in by :", page);
		AppTemplatePageUtils.verifyButtonIsEnabled("Refresh", page);
		AppTemplatePageUtils.verifyButtonIsEnabled("Compose Mail", page);
		AppTemplatePageUtils.verifyTabIsVisible("All Mails", page);
		AppTemplatePageUtils.verifyButtonIsEnabled("Read", page);
		AppTemplatePageUtils.verifyButtonIsEnabled("Delete", page);
		AppTemplatePageUtils.verifyTabIsVisible("Unread Mails", page);
		AppTemplatePageUtils.verifyButtonIsEnabled("Read", page);
		AppTemplatePageUtils.verifyTabIsVisible("Sent Mails", page);
		AppTemplatePageUtils.clickClosePreviewButton(page);


		
	}
    

}
