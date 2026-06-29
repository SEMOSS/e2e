package aicore.unit.template;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;

import aicore.framework.UrlUtils;
import aicore.pages.home.HomePageUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.CommonUtils;
import aicore.utils.annotations.PWPage;
import aicore.utils.page.app.AppPageUtils;
import aicore.utils.page.app.AppTemplatePageUtils;
import aicore.utils.page.app.CreateAppPopupUtils;
import aicore.utils.page.app.DragAndDropBlocksPageUtils;

public class MultiPageTemplate extends AbstractPlaywrightTestBase {
	
	
	String timestamp = CommonUtils.getTimeStampName();
	String appName = "Test app" + timestamp;
	
	String hyperlinkText = "Go to resources";
	String expectedRelativeUrl = "e2e/SemossWeb/packages/client/dist/#/app/.*/view/resources";
	
	@BeforeEach
	void setup(@PWPage Page page) {
		loginNativeAdmin(page);
	}	
	@AfterEach
	void tearDown(@PWPage Page page) {
	    logout(page);
	}
	
	@Test
    public void MultiPageTemplate_test(@PWPage Page page){
		
		HomePageUtils.navigateToHomePage(page);
		MainMenuUtils.openMainMenu(page);	
		MainMenuUtils.clickOnOpenAppLibrary(page);
		AppPageUtils.clickOnCreateNewAppButton(page);	
		AppTemplatePageUtils.selectTemplateFromList("Multi Page", page);
		CreateAppPopupUtils.enterAppName(page, appName);				
		CreateAppPopupUtils.enterAppDescription(page, "Created by automation script");		
		CreateAppPopupUtils.enterTags(page, "Test1, Test2");
		CreateAppPopupUtils.clickOnCreateButton(page);		
		String fetchName = CreateAppPopupUtils.userFetchAppName(page);
		Assertions.assertFalse(fetchName.isEmpty(), "Fetched App Name is Empty");
		
		String actualText = AppTemplatePageUtils.userSeePage1(page);
		Assertions.assertEquals(
		        "page-1",
		        actualText,
		        "Expected and Actual Text do not match");
		
		String actualBlock = AppTemplatePageUtils.userSeeTeamplatePageTitle(page);

		Assertions.assertEquals(
		        "Landing Page",
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
		        expectedRelativeUrl,
		        page);

		String actualRelativePath =
		        UrlUtils.extractRelativePath(AppTemplatePageUtils.getCurrentUrl(page));

		Assertions.assertTrue(
		        actualRelativePath.matches(expectedRelativeUrl),
		        "URL mismatch!\nExpected pattern: " + expectedRelativeUrl
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
		        actualPath.matches("e2e/SemossWeb/packages/client/dist/#/app/.*/view/about"),
		        "URL mismatch!\nExpected pattern: e2e/SemossWeb/packages/client/dist/#/app/.*/view/about"
		        + "\nActual URL: " + actualPath);
		
		Assertions.assertEquals(
		        "About",
		        AppTemplatePageUtils.userSeeAboutTitle(page),
		        "Page title does not match.");
		
		AppTemplatePageUtils.getBackPage(page);
		DragAndDropBlocksPageUtils.clickOnBlocksOption(page);
		
		DragAndDropBlocksPageUtils.mouseHoverOnBlock(page, "Area Chart");

		Assertions.assertTrue(
		        AppTemplatePageUtils.dropChartOnPage(page, "landing page"),
		        "Expected: Chart should be visible on the Page after drag-and-drop. But it was not found.");
		
		DragAndDropBlocksPageUtils.clickOnSaveAppButton(page);


	}
	

}
