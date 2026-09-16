package aicore.unit.DragAndDrop;

import com.microsoft.playwright.Page;
import aicore.pages.app.settings.AppAccessControlPageUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.AddFunctionPageUtils;
import aicore.utils.CatlogAccessPageUtility;
import aicore.utils.CommonUtils;
import aicore.utils.annotations.PWPage;
import aicore.utils.page.app.AppPageUtils;
import aicore.utils.page.app.TemplateCreationUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class VerifyCreatedByMeFilterTests extends AbstractPlaywrightTestBase {

	private String adminAppName = "";
	private String editorAppName = "";

	@AfterEach
	void tearDown(@PWPage Page page) {
		CommonUtils.navigateAndDeleteApp(page, adminAppName);
		CommonUtils.navigateAndDeleteApp(page, editorAppName);
		logout(page);
	}

	@Test
	@DisplayName("TC01_Validate Created by me filter on app landing page")
	void testCreatedByMeFilterOnAppLandingPage(@PWPage Page page) {
		loginAdmin(page);
		adminAppName = TemplateCreationUtils.createDragAndDropApp(page, "Drag and Drop");

		logout(page);
		loginEditor(page);
		editorAppName = TemplateCreationUtils.createDragAndDropApp(page, "Drag and Drop");

		CatlogAccessPageUtility.clickOnSettings(page);
		AddFunctionPageUtils.clickOnAccessControl(page);
		AppAccessControlPageUtils.makeAppPublic(page, editorAppName);

		logout(page);
		loginAdmin(page);

		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenAppLibrary(page);
		AppPageUtils.searchApp(page, editorAppName, "");

		Assertions.assertTrue(AppPageUtils.isAppDisplayedOnPage(page, editorAppName, ""),
				"Application is not displayed on page");

		AppPageUtils.clickOnCreatedByMeToggleSwitch(page);

		Assertions.assertTrue(AppPageUtils.isAppNotDisplayedOnPage(page, editorAppName, ""),
				"Application is displayed on page");
	}
}