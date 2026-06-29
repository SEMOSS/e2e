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
import aicore.utils.page.app.BlockSettingsUtils;
import aicore.utils.page.app.CreateAppPopupUtils;

public class LandingPageTemplate extends AbstractPlaywrightTestBase {
	
	String timestamp = CommonUtils.getTimeStampName();
	String appName = "Test app" + timestamp;
	
	String expectedDescription =
	        "Drag and drop your content below to start populating your page.  "
	      + "Add images, text, and links to customize your landing page and make it your own.  "
	      + "Whether you are setting up a portfolio, a business page, or a personal blog, "
	      + "this is the first step to creating something unique and engaging.  "
	      + "Make your vision come to life!";

	
	String resourcesDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse varius enim in eros elementum tristique. Duis cursus, mi quis viverra ornare, eros dolor interdum nulla, ut commodo diam libero vitae erat. Aenean faucibus nibh et justo cursus id rutrum lorem imperdiet. Nunc ut sem vitae risus tristique posuere.";			 
	
	private static final String RESOURCE_DESCRIPTION =
		    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse varius enim in eros elementum tristique.";
	
	
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
	            "SemossWeb/packages/client/dist",
	            page);
	    AppTemplatePageUtils.getBackPage(page);
	}
	
	@Test
    public void LandingPageTemplate_test(@PWPage Page page){
		
		HomePageUtils.navigateToHomePage(page);
		MainMenuUtils.openMainMenu(page);	
		MainMenuUtils.clickOnOpenAppLibrary(page);
		AppPageUtils.clickOnCreateNewAppButton(page);	
		AppTemplatePageUtils.selectTemplateFromList("Landing Page", page);
		CreateAppPopupUtils.enterAppName(page, appName);				
		CreateAppPopupUtils.enterAppDescription(page, "Created by automation script");		
		CreateAppPopupUtils.enterTags(page, "Test1, Test2");
		CreateAppPopupUtils.clickOnCreateButton(page);		
		String fetchName = CreateAppPopupUtils.userFetchAppName(page);
		Assertions.assertFalse(fetchName.isEmpty(), "Fetched App Name is Empty");
		
		AppTemplatePageUtils.verifyPageWithtitleText("Landing Page Title", page);	
		AppTemplatePageUtils.verifyDescriptionBelowTitle(expectedDescription, page);
		AppTemplatePageUtils.verifyHyperlink("Explore", "SemossWeb/packages/client/dist", page);
		AppTemplatePageUtils.getBackPage(page);
		
		AppTemplatePageUtils.verifyPageWithtitleText("Resources", page);
		AppTemplatePageUtils.verifyDescriptionBelowTitle(resourcesDescription, page);

		verifyResource(page, "Resource 1");
		verifyResource(page, "Resource 2");
		verifyResource(page, "Resource 3");
		verifyResource(page, "Resource 4");
		verifyResource(page, "Resource 5");
		verifyResource(page, "Resource 6");
		
		AppTemplatePageUtils.clickOnHyperlinkText("Explore", page);
		BlockSettingsUtils.clickOnBlockSettingsOption(page);
		AppTemplatePageUtils.fillDestinationUrl("https://workshop.cfg.deloitte.com/cfg-ai-demo/SemossWeb/packages/client/dist/#/login", page);
		AppTemplatePageUtils.clickSaveButtonOfTheApp(page);
		BlockSettingsUtils.clickOnBlockSettingsOption(page);
		AppTemplatePageUtils.clickOnHyperlinkText("Explore", page);
		Assertions.assertEquals(
			    "https://workshop.cfg.deloitte.com/cfg-ai-demo/SemossWeb/packages/client/dist/#/login",
			    AppTemplatePageUtils.getCurrentUrl(page),
			    "Expected URL does not match the current page URL."
			);
		
		page.goBack();       // To Logout successfully
	}

		

}

