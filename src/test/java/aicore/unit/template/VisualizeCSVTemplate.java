package aicore.unit.template;

import org.junit.jupiter.api.AfterEach;
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
import aicore.utils.page.app.DragAndDropBlocksPageUtils;
import aicore.utils.page.app.TemplateCreationUtils;

public class VisualizeCSVTemplate extends AbstractPlaywrightTestBase {
	
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
	
	private static final String TITLE = "Visualize data from upload";
	private static final String TITLE_EDITED = "Visualize data from upload - Edited";
	private static final String SUBTITLE = "This app allows you to upload a CSV file shows data from it in our dynamic data grid";


	@Test
    public void VisualizeCSVTemplate_test(@PWPage Page page){
		
		appName = TemplateCreationUtils.createAppFromTemplate(page, "Visualize CSV");
		AppTemplatePageUtils.verifyAppPageTitle(TITLE, page);
		AppTemplatePageUtils.verifyAppPageSubTitle(SUBTITLE, page);
		DragAndDropBlocksPageUtils.clickOnSaveAppButton(page);
        HomePageUtils.navigateToHomePage(page);
        MainMenuUtils.openMainMenu(page);
        MainMenuUtils.clickOnOpenAppLibrary(page);
		AppPageUtils.searchApp(page, appName, "");
		AppPageUtils.clickOnAppCard(page, appName, "");
		AppTemplatePageUtils.verifyAppPageTitle(TITLE, page);
		DragAndDropBlocksPageUtils.clickOnEditButton(page);
		AppTemplatePageUtils.clickOnAppPageTitle(TITLE, page);
		BlockSettingsUtils.clickOnBlockSettingsOption(page);
		AppTemplatePageUtils.changeAppPageTitle(TITLE, TITLE_EDITED, page);
		DragAndDropBlocksPageUtils.clickOnSaveAppButton(page);
		AppTemplatePageUtils.verifyAppPageTitle(TITLE_EDITED, page);
	}
}
