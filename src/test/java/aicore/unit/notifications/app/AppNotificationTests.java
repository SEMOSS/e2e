package aicore.unit.notifications.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;

import aicore.base.GenericSetupUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.pages.model.SettingsModelPageUtils;
import aicore.pages.notifications.NotificationsUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.CatlogAccessPageUtility;
import aicore.utils.CommonUtils;
import aicore.utils.annotations.PWPage;
import aicore.utils.page.app.AppPageUtils;
import aicore.utils.page.app.CreateAppPopupUtils;

public class AppNotificationTests extends AbstractPlaywrightTestBase {
	private static String appName = null;

	@BeforeEach
	public void createApp(@PWPage Page page) {
		loginNativeAdmin(page);
		String timestamp = CommonUtils.getTimeStampName();
		String appType = "Drag and Drop";
		appName = "Test app" + timestamp;
		// Create app
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenAppLibrary(page);
		AppPageUtils.clickOnCreateNewAppButton(page);
		CreateAppPopupUtils.clickOnGetStartedButton(page, appType);
		CreateAppPopupUtils.enterAppName(page, appName);
		CreateAppPopupUtils.clickOnCreateButton(page);
		CreateAppPopupUtils.userFetchAppName(page);
	}

	@Test
	@DisplayName("Validate newly added member in app received notification")
	public void verifyNewlyAddedMemberInAppReceivesNotification(@PWPage Page page) throws InterruptedException {
		String accessProvided = "Editor";
		String accessProvidedByUser = "Admin";
		// Open access control tab and add a new member
		CatlogAccessPageUtility.clickOnSettings(page);
		SettingsModelPageUtils.clickOnAccessControl(page);
		SettingsModelPageUtils.clickOnAddMembersButton(page);
		SettingsModelPageUtils.addMember(page, accessProvided, GenericSetupUtils.useDocker());
		// Logout and login with the newly added member
		logout(page);
		loginEditor(page);
		// Validate the notification message for the newly added member
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenAppLibrary(page);
		NotificationsUtils.clickOnNotificationBellIcon(page);
		String actualUserAddedNotificationMessage = NotificationsUtils.validateUserAddedNotificationMessageForUser(page,
				accessProvided, appName, accessProvidedByUser);
		String expectedMessage = String.format("You are added as %s to %s by %s.", accessProvided, appName,
				accessProvidedByUser + "User");
		Assertions.assertEquals(expectedMessage, actualUserAddedNotificationMessage,
				"The notification message is not as expected.");
		// Close the notification pane
		NotificationsUtils.closeNotificationPane(page);
		// Logout from newly added member account and login back with admin for cleanup
		logout(page);
		loginNativeAdmin(page);
	}

	@AfterEach
	void tearDownEachTest(@PWPage Page page) {
		CommonUtils.navigateAndDeleteApp(page, appName);
	}

}
