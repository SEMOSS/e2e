package aicore.unit.DragAndDrop;

import com.microsoft.playwright.Page;
import aicore.pages.app.settings.AppAccessControlPageUtils;
import aicore.pages.home.HomePageUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.AddFunctionPageUtils;
import aicore.utils.CatlogAccessPageUtility;
import aicore.utils.CommonUtils;
import aicore.utils.annotations.PWPage;
import aicore.utils.page.app.AppPageUtils;
import aicore.utils.page.app.CreateAppPopupUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class VerifyCreatedByMeFilterTests extends AbstractPlaywrightTestBase {

	private static final String ADMIN_APP_NAME = "Test app";
	private static final String EDITOR_APP_NAME = "App created by editor";

	private String timestamp = "";

	@BeforeEach
	void setup() {
		timestamp = CommonUtils.getTimeStampName();
	}

	@AfterEach
	void tearDown(@PWPage Page page) {
		CommonUtils.navigateAndDeleteApp(page, ADMIN_APP_NAME + timestamp);
		CommonUtils.navigateAndDeleteApp(page, EDITOR_APP_NAME + timestamp);
		logout(page);
	}

	@Test
	@DisplayName("Validate Created by me filter on app landing page")
	void testCreatedByMeFilterOnAppLandingPage(@PWPage Page page) {
		loginAdmin(page);

		HomePageUtils.navigateToHomePage(page);
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenAppLibrary(page);
		AppPageUtils.clickOnCreateNewAppButton(page);
		CreateAppPopupUtils.clickOnGetStartedButton(page, "Drag and Drop");
		CreateAppPopupUtils.enterAppName(page, ADMIN_APP_NAME + timestamp);
		CreateAppPopupUtils.clickOnCreateButton(page);
		String adminAppFetchName = CreateAppPopupUtils.userFetchAppName(page);
		Assertions.assertFalse(adminAppFetchName.isEmpty(), "Fetched App Name is Empty");

		logout(page);
		loginEditor(page);

		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenAppLibrary(page);
		AppPageUtils.clickOnCreateNewAppButton(page);
		CreateAppPopupUtils.clickOnGetStartedButton(page, "Drag and Drop");
		CreateAppPopupUtils.enterAppName(page, EDITOR_APP_NAME + timestamp);
		CreateAppPopupUtils.clickOnCreateButton(page);

		CatlogAccessPageUtility.clickOnSettings(page);
		AddFunctionPageUtils.clickOnAccessControl(page);
		AppAccessControlPageUtils.makeAppPublic(page, EDITOR_APP_NAME + timestamp);

		logout(page);
		loginAdmin(page);

		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenAppLibrary(page);
		AppPageUtils.searchApp(page, EDITOR_APP_NAME, timestamp);

		boolean isEditorAppDisplayed = AppPageUtils.isAppDisplayedOnPage(page, EDITOR_APP_NAME, timestamp);
		Assertions.assertTrue(isEditorAppDisplayed, "Application is not displayed on page");

		AppPageUtils.clickOnCreatedByMeToggleSwitch(page);

		boolean isEditorAppNotDisplayed = AppPageUtils.isAppNotDisplayedOnPage(page, EDITOR_APP_NAME, timestamp);
		Assertions.assertTrue(isEditorAppNotDisplayed, "Application is displayed on page");
	}
}
