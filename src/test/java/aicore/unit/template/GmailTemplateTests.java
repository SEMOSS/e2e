package aicore.unit.template;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.microsoft.playwright.Page;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.CommonUtils;
import aicore.utils.annotations.PWPage;
import aicore.utils.page.app.AppTemplatePageUtils;
import aicore.utils.page.app.CreateAppPopupUtils;
import aicore.utils.page.app.TemplateCreationUtils;

public class GmailTemplateTests extends AbstractPlaywrightTestBase {
	
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
	
	private void verifyAppCreated(Page page) {
	    String appName = CreateAppPopupUtils.userFetchAppName(page);
	    Assertions.assertFalse(appName.isEmpty(), "Fetched App Name is Empty");
	}
	
	private void verifyGmailTemplate(Page page) {
	    AppTemplatePageUtils.verifyAppTemplateTitle(TEMPLATE_NAME, page);
	    AppTemplatePageUtils.verifyDialogText(LOGGED_IN_TEXT, page);

	    AppTemplatePageUtils.verifyButtonIsEnabled("Refresh", page);
	    AppTemplatePageUtils.verifyButtonIsEnabled("Compose Mail", page);

	    AppTemplatePageUtils.verifyTabIsVisible("All Mails", page);
	    AppTemplatePageUtils.verifyButtonIsEnabled("Read", page);
	    AppTemplatePageUtils.verifyButtonIsEnabled("Delete", page);

	    AppTemplatePageUtils.verifyTabIsVisible("Unread Mails", page);
	    AppTemplatePageUtils.verifyButtonIsEnabled("Read", page);

	    AppTemplatePageUtils.verifyTabIsVisible("Sent Mails", page);
	}
	
	private static final String TEMPLATE_NAME = "Gmail";
	private static final String LOGGED_IN_TEXT = "Logged in by :";
	
	@Test
    public void GmailTemplate_test(@PWPage Page page) {
		
		appName = TemplateCreationUtils.createAppFromTemplate(page, TEMPLATE_NAME);
		verifyAppCreated(page);		
		AppTemplatePageUtils.verifyAppTemplateTitle(TEMPLATE_NAME, page);
		AppTemplatePageUtils.verifyDialogText(LOGGED_IN_TEXT, page);
	    verifyGmailTemplate(page);
		AppTemplatePageUtils.clickPreviewButton(page);
		AppTemplatePageUtils.verifyAppTemplateTitle(TEMPLATE_NAME, page);
		AppTemplatePageUtils.verifyDialogText(LOGGED_IN_TEXT, page);
	    verifyGmailTemplate(page);
		AppTemplatePageUtils.clickClosePreviewButton(page);


		
	}
    

}
