package aicore.unit.template;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.microsoft.playwright.Page;
import aicore.framework.UrlUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.annotations.PWPage;
import aicore.utils.page.app.AppTemplatePageUtils;
import aicore.utils.page.app.CreateAppPopupUtils;
import aicore.utils.page.app.DragAndDropBlocksPageUtils;
import aicore.utils.page.app.TemplateCreationUtils;

public class MultiPageTemplate extends AbstractPlaywrightTestBase {
	
	String hyperlinkText = "Go to resources";
	private static final String RESOURCES_URL = "e2e/SemossWeb/packages/client/dist/#/app/.*/view/resources";
	private static final String ABOUT_URL = "e2e/SemossWeb/packages/client/dist/#/app/.*/view/about";
	private static final String LANDING_PAGE = "Landing Page";
	
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
    public void MultiPageTemplate_test(@PWPage Page page){
		
		TemplateCreationUtils.createAppFromTemplate(page, "Multi Page");
		verifyAppCreated(page);		

		String actualText = AppTemplatePageUtils.userSeePage1(page);
		Assertions.assertEquals(
		        "page-1",
		        actualText,
		        "Expected and Actual Text do not match");
		
		String actualBlock = AppTemplatePageUtils.userSeeTeamplatePageTitle(page);

		Assertions.assertEquals(
				LANDING_PAGE,
		        actualBlock,
		        "Expected and Actual Block do not match");
		
		Assertions.assertTrue(
		        AppTemplatePageUtils.userSeeTheHyperlink(page, "Go to resources"),
		        "Hyperlink is not visible to the user");
		
		Assertions.assertTrue(
		        AppTemplatePageUtils.userSeeTheHyperlink(page, "Go to About"),
		        "Hyperlink is not visible to the user");
		
		
		AppTemplatePageUtils.verifyHyperlink(
		        hyperlinkText,
		        RESOURCES_URL,
		        page);

		String actualRelativePath =
		        UrlUtils.extractRelativePath(AppTemplatePageUtils.getCurrentUrl(page));

		Assertions.assertTrue(
		        actualRelativePath.matches(RESOURCES_URL),
		        "URL mismatch!\nExpected pattern: " + RESOURCES_URL
		        + "\nActual URL: " + actualRelativePath);
		
		Assertions.assertEquals(
		        "Resources",
		        AppTemplatePageUtils.userSeeResourceTitle(page),
		        "Page title does not match.");
		
		AppTemplatePageUtils.getBackPage(page);

		
		AppTemplatePageUtils.verifyHyperlink(
		        "Go to About",
		        "SemossWeb/packages/client/dist/#/app/.*/view/about",
		        page);

		String actualPath =
		        UrlUtils.extractRelativePath(AppTemplatePageUtils.getCurrentUrl(page));

		Assertions.assertTrue(
		        actualPath.matches(ABOUT_URL),
		        "URL mismatch!\nExpected pattern: "+ ABOUT_URL
		        + "\nActual URL: " + actualPath);
		
		Assertions.assertEquals(
		        "About",
		        AppTemplatePageUtils.userSeeAboutTitle(page),
		        "Page title does not match.");
		
		AppTemplatePageUtils.getBackPage(page);
		DragAndDropBlocksPageUtils.clickOnBlocksOption(page);
		
		DragAndDropBlocksPageUtils.mouseHoverOnBlock(page, "Area Chart");

		Assertions.assertTrue(
		        AppTemplatePageUtils.dropChartOnPage(page, LANDING_PAGE),
		        "Expected: Chart should be visible on the Page after drag-and-drop. But it was not found.");
		
		DragAndDropBlocksPageUtils.clickOnSaveAppButton(page);


	}
	

}
