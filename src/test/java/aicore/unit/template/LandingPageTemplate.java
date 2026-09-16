package aicore.unit.template;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.microsoft.playwright.Page;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.annotations.PWPage;
import aicore.utils.page.app.AppTemplatePageUtils;
import aicore.utils.page.app.BlockSettingsUtils;
import aicore.utils.page.app.CreateAppPopupUtils;
import aicore.utils.page.app.TemplateCreationUtils;

public class LandingPageTemplate extends AbstractPlaywrightTestBase {
	
	private static final String EXPECTED_DESCRIPTION =
	        "Drag and drop your content below to start populating your page.  "
	      + "Add images, text, and links to customize your landing page and make it your own.  "
	      + "Whether you are setting up a portfolio, a business page, or a personal blog, "
	      + "this is the first step to creating something unique and engaging.  "
	      + "Make your vision come to life!";

	
	private static final String RESOURCES_DESCRIPTION = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse varius enim in eros elementum tristique. Duis cursus, mi quis viverra ornare, eros dolor interdum nulla, ut commodo diam libero vitae erat. Aenean faucibus nibh et justo cursus id rutrum lorem imperdiet. Nunc ut sem vitae risus tristique posuere.";			 
	
	private static final String RESOURCE_DESCRIPTION =
		    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse varius enim in eros elementum tristique.";
	
	private static final String APP_URL =
	        "SemossWeb/packages/client/dist";

	private static final String LOGIN_URL =
	        "https://workshop.cfg.deloitte.com/cfg-ai-demo/SemossWeb/packages/client/dist/#/login";
	
	private static final String TEMPLATE_NAME = "Landing Page";
	
	@BeforeEach
	void setup(@PWPage Page page) {
		loginNativeAdmin(page);
	}	
	@AfterEach
	void tearDown(@PWPage Page page) {
	    logout(page);
	}
	
	private void verifyResource(Page page, String resourceName) {
	    AppTemplatePageUtils.verifyPageWithtitleText(resourceName, page);
	    AppTemplatePageUtils.verifyDescriptionBelowTitleOfBlock(
	            resourceName,
	            RESOURCE_DESCRIPTION,
	            page);
	    AppTemplatePageUtils.verifyHyperlinkText(
	            "Navigate",
	            resourceName,
	            APP_URL,
	            page);
	    AppTemplatePageUtils.getBackPage(page);
	}
	
	private void verifyAppCreated(Page page) {
	    String appName = CreateAppPopupUtils.userFetchAppName(page);
	    Assertions.assertFalse(appName.isEmpty(), "Fetched App Name is Empty");
	}
	
	@Test
    public void LandingPageTemplate_test(@PWPage Page page){
		
		TemplateCreationUtils.createAppFromTemplate(page, TEMPLATE_NAME);
		
		verifyAppCreated(page);		

		
		AppTemplatePageUtils.verifyPageWithtitleText("Landing Page Title", page);	
		AppTemplatePageUtils.verifyDescriptionBelowTitle(EXPECTED_DESCRIPTION, page);
		AppTemplatePageUtils.verifyHyperlink("Explore", APP_URL, page);
		AppTemplatePageUtils.getBackPage(page);
		
		AppTemplatePageUtils.verifyPageWithtitleText("Resources", page);
		AppTemplatePageUtils.verifyDescriptionBelowTitle(RESOURCES_DESCRIPTION, page);

		verifyResource(page, "Resource 1");
		verifyResource(page, "Resource 2");
		verifyResource(page, "Resource 3");
		verifyResource(page, "Resource 4");
		verifyResource(page, "Resource 5");
		verifyResource(page, "Resource 6");
		
		AppTemplatePageUtils.clickOnHyperlinkText("Explore", page);
		BlockSettingsUtils.clickOnBlockSettingsOption(page);
		AppTemplatePageUtils.fillDestinationUrl(LOGIN_URL, page);
		AppTemplatePageUtils.clickSaveButtonOfTheApp(page);
		BlockSettingsUtils.clickOnBlockSettingsOption(page);
		AppTemplatePageUtils.clickOnHyperlinkText("Explore", page);
		Assertions.assertEquals(
				LOGIN_URL,
			    AppTemplatePageUtils.getCurrentUrl(page),
			    "Expected URL does not match the current page URL."
			);
		
		page.goBack();       // To Logout successfully
	}

		

}

