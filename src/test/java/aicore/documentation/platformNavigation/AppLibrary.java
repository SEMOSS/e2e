package aicore.documentation.platformNavigation;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import aicore.base.GenericSetupUtils;
import aicore.documentation.DocumentationConstants;
import aicore.utils.AppLibraryPageUtils;
import aicore.utils.ConfigUtils;
import aicore.utils.HomePageUtils;
import aicore.utils.page.app.AppPageUtils;
import aicore.utils.page.app.CreateAppPopupUtils;

/**
 * create documentation pictures for Platform Naviation > App Library
 */
public class AppLibrary {

	/**
	 * Test util to create quick test app
	 * 
	 * @param page
	 * @return
	 */
	public static String createTestApp(Page page) {
		String name = "Test";
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String timestamp = sdf.format(ts);
		String appName = CreateAppPopupUtils.enterAppName(page, name, timestamp);
		CreateAppPopupUtils.clickOnCreateButton(page);
		return appName;
	}

	public static void main(String[] args) throws IOException {
		Page page = DocumentationUtils.setupPlaywright(false);

		String adminUser = ConfigUtils.getValue("admin_username");
		String adminPassword = ConfigUtils.getValue("admin_password");

		String cookie = GenericSetupUtils.login(page, adminUser, adminPassword);

		// get image for create new app button
		HomePageUtils.openMainMenu(page);
		HomePageUtils.clickOnOpenAppLibrary(page);
		DocumentationUtils.focusOnElement(page, AppPageUtils.CREATE_NEW_APP_DATA_TEST_ID);
		DocumentationUtils.screenshot(page, DocumentationConstants.CREATE_NEW_APP_BUTTON_IMAGE);

		// get image for app options
		AppPageUtils.clickOnCreateNewAppButton(page);
		DocumentationUtils.screenshot(page, DocumentationConstants.CREATE_NEW_APP_OPTIONS_IMAGE);

		// get image for browse templates
		// Scroll the element to the top of the viewport
		Locator element = DocumentationUtils.focusOnElement(page, AppLibraryPageUtils.BROWSE_TEMPLATES_XPATH);
		DocumentationUtils.moveElementToTopOfScreen(element);
		DocumentationUtils.focusOnElement(page, AppLibraryPageUtils.BROWSE_TEMPLATES_XPATH);
		DocumentationUtils.screenshot(page, DocumentationConstants.BROWSE_TEMPLATES_IMAGE);

		// get image for new app popup
		HomePageUtils.clickOnOpenAppLibrary(page);
		AppPageUtils.clickOnCreateNewAppButton(page);
		CreateAppPopupUtils.clickOnGetStartedButton(page, "Drag and Drop");
		// wait for popup to load
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DocumentationUtils.screenshot(page, DocumentationConstants.NEW_APP_POPUP_IMAGE);

		// get image for preview app
		String appName = createTestApp(page);
		AppLibraryPageUtils.verifyPage1IsVisible(page);
		element = DocumentationUtils.focusOnDataTestId(page, AppLibraryPageUtils.PREVIEW_APP_BUTTON_DATA_TEST_ID);
		DocumentationUtils.zoomPage(page, 150);
		DocumentationUtils.moveElementToTopOfScreen(element);
		element.hover();
		DocumentationUtils.screenshot(page, DocumentationConstants.PREVIEW_APP_IMAGE);
		DocumentationUtils.removeElementFocus(element);

		// get image for share app
		element = DocumentationUtils.focusOnDataTestId(page, AppLibraryPageUtils.SHARE_APP_BUTTON_DATA_TEST_ID);
		DocumentationUtils.zoomPage(page, 150);
		DocumentationUtils.moveElementToTopOfScreen(element);
		element.hover();
		DocumentationUtils.screenshot(page, DocumentationConstants.SHARE_APP_IMAGE);
		DocumentationUtils.removeElementFocus(element);

		// get image for save app
		element = DocumentationUtils.focusOnDataTestId(page, AppLibraryPageUtils.SAVE_APP_BUTTON_DATA_TEST_ID);
		DocumentationUtils.zoomPage(page, 150);
		DocumentationUtils.moveElementToTopOfScreen(element);
		element.hover();
		DocumentationUtils.screenshot(page, DocumentationConstants.SAVE_APP_IMAGE);
		DocumentationUtils.removeElementFocus(element);

		// get image for show app
		element = DocumentationUtils.focusOnElement(page, AppLibraryPageUtils.SHOW_BUTTON_XPATH);
		DocumentationUtils.zoomPage(page, 150);
		DocumentationUtils.moveElementToTopOfScreen(element);
		element.hover();
		DocumentationUtils.screenshot(page, DocumentationConstants.SHOW_APP_IMAGE);
		DocumentationUtils.removeElementFocus(element);

		// get image for app settings
		element = DocumentationUtils.focusOnDataTestId(page, AppLibraryPageUtils.APP_SETTINGS_DATA_TEST_ID);
		DocumentationUtils.zoomPage(page, 150);
		DocumentationUtils.moveElementToTopOfScreen(element);
		element.hover();
		DocumentationUtils.screenshot(page, DocumentationConstants.APP_SETTINGS_IMAGE);
		DocumentationUtils.removeElementFocus(element);
		AppLibraryPageUtils.clickOnAppSettingsOption(page);
		DocumentationUtils.zoomPage(page, 100);

		// get image for user permission settings for app
		element = DocumentationUtils.focusOnDataTestId(page, AppLibraryPageUtils.PERMISSION_SETTINGS_DATA_TEST_ID);
		element = AppLibraryPageUtils.clickOnAppSettingsOption(page);
		DocumentationUtils.formatElementFocus(element);
		element = AppLibraryPageUtils.clickOnPermissionSettingsOption(page);
		DocumentationUtils.formatElementFocus(element);
		DocumentationUtils.screenshot(page, DocumentationConstants.APP_ACCESS_PERMISSIONS_IMAGE);
		DocumentationUtils.removeElementFocus(element);

		// get image for app search
		GenericSetupUtils.navigateToHomePage(page);
		HomePageUtils.clickOnOpenAppLibrary(page);
		element = DocumentationUtils.focusOnElement(page, AppPageUtils.APP_SEARCH_TEXTBOX_XPATH);
		DocumentationUtils.screenshot(page, DocumentationConstants.SEARCH_APP_LIBRARY_IMAGE);
		DocumentationUtils.removeElementFocus(element);

		page.close();
		System.exit(0);
	}
}
