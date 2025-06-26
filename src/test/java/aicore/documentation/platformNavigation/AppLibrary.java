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

/**
 * create documentation pictures for Platform Naviation > App Library
 */
public class AppLibrary {
	
	/**
	 * Test util to create quick test app
	 * @param page
	 * @return
	 */
	public static String createTestApp(Page page) {
		String name = "Test";
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String timestamp = sdf.format(ts);
		String appName = AppLibraryPageUtils.enterAppName(page, name, timestamp);
		AppLibraryPageUtils.clickOnCreateButton(page);
		return appName;
	}

	public static void main(String[] args) throws IOException {
		Page page = DocumentationUtils.setupPlaywright(!true);
		
		String adminUser = ConfigUtils.getValue("admin_username");
		String adminPassword = ConfigUtils.getValue("admin_password");

		String cookie = GenericSetupUtils.login(page, adminUser, adminPassword);
		
		// get image for create new app button
		HomePageUtils.clickOnOpenAppLibrary(page);
		DocumentationUtils.focusOnElement(page, AppLibraryPageUtils.CREATE_NEW_APP_BUTTON_XPATH);

        // get image for app options
		AppLibraryPageUtils.clickOnCreateNewAppButton(page);
		DocumentationUtils.screenshot(page, DocumentationConstants.CREATE_NEW_APP_OPTIONS_IMAGE);

        // get image for browse templates
        // Scroll the element to the top of the viewport
		Locator element = DocumentationUtils.focusOnElement(page, AppLibraryPageUtils.BROWSE_TEMPLATES_XPATH);
		DocumentationUtils.moveElementToTopOfScreen(element);
		DocumentationUtils.focusOnElement(page, AppLibraryPageUtils.BROWSE_TEMPLATES_XPATH);
		DocumentationUtils.screenshot(page, DocumentationConstants.BROWSE_TEMPLATES_IMAGE);

		// get image for new app popup
		HomePageUtils.clickOnOpenAppLibrary(page);
		AppLibraryPageUtils.clickOnCreateNewAppButton(page);
		AppLibraryPageUtils.clickOnGetStartedButtonInDragAndDrop(page);
		DocumentationUtils.screenshot(page, DocumentationConstants.NEW_APP_POPUP_IMAGE);

		// get image for preview app
		String appName = createTestApp(page);
		AppLibraryPageUtils.verifyPage1IsVisible(page);
		element = DocumentationUtils.focusOnDataTestId(page, AppLibraryPageUtils.PREVIEW_APP_BUTTON_DATA_TEST_ID);
		DocumentationUtils.moveElementToTopOfScreen(element);
		DocumentationUtils.screenshot(page, DocumentationConstants.PREVIEW_APP_IMAGE);

        page.close();
        System.exit(0);
	}
}
