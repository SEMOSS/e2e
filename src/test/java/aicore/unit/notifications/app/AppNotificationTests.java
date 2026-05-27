package aicore.unit.notifications.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;

import aicore.base.GenericSetupUtils;
import aicore.framework.ConfigUtils;
import aicore.pages.function.FunctionAccessSettingsUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.pages.model.EditModelPageUtils;
import aicore.pages.model.SettingsModelPageUtils;
import aicore.pages.notifications.NotificationsUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.AddFunctionPageUtils;
import aicore.utils.CatlogAccessPageUtility;
import aicore.utils.CommonUtils;
import aicore.utils.RequestAccessPopupUtils;
import aicore.utils.annotations.PWPage;
import aicore.utils.page.app.AppPageUtils;
import aicore.utils.page.app.CreateAppPopupUtils;
import aicore.utils.page.app.DragAndDropBlocksPageUtils;
import aicore.utils.page.model.ModelPageUtils;

public class AppNotificationTests extends AbstractPlaywrightTestBase {
	private static String appName = null;
	String timestamp = CommonUtils.getTimeStampName();

	@BeforeEach
	public void createApp(@PWPage Page page) {
		loginNativeAdmin(page);
		String appType = "Drag and Drop";
		appName = "Testapp";
		// Create app
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenAppLibrary(page);
		AppPageUtils.clickOnCreateNewAppButton(page);
		CreateAppPopupUtils.clickOnGetStartedButton(page, appType);
		CreateAppPopupUtils.enterAppName(page, appName + timestamp);
		CreateAppPopupUtils.clickOnCreateButton(page);
		CreateAppPopupUtils.userFetchAppName(page);
	}

	@Test
	@DisplayName("Validate newly added member in app received notification")
	public void verifyNewlyAddedMemberInAppReceivesNotification(@PWPage Page page) throws InterruptedException {
		String accessProvided = "Editor";
		String accessProvidedByUser = ConfigUtils.getValue("Admin".toUpperCase() + "_USERNAME").split("@")[0];
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
				accessProvided, appName + timestamp, accessProvidedByUser);
		String expectedMessage = String.format("You are added as %s to %s by %s.", accessProvided, appName + timestamp,
				accessProvidedByUser);
		Assertions.assertEquals(expectedMessage, actualUserAddedNotificationMessage,
				"The notification message is not as expected.");
		// Close the notification pane
		NotificationsUtils.closeNotificationPane(page);
		// Logout from newly added member account and login back with admin for cleanup
		logout(page);
		loginNativeAdmin(page);
	}

	@Test
	@DisplayName("Validate Notification message for Accept the request")
	public void testAcceptRequestAndValidateMemberList(@PWPage Page page) {
		CatlogAccessPageUtility.clickOnSettings(page);
		AddFunctionPageUtils.clickOnAccessControl(page);
		FunctionAccessSettingsUtils.clickOnMakeDiscoverableButton(page, appName + timestamp);
		logout(page);
		loginEditor(page);
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenAppLibrary(page);
		DragAndDropBlocksPageUtils.clickOnDiscovrableApps(page);
		AppPageUtils.searchApp(page, appName, timestamp);
		AppPageUtils.clickOnAppCard(page, appName, timestamp);
		EditModelPageUtils.clickOnRequestAccessButtonOfDiscoverableCatalog(page);
		RequestAccessPopupUtils.selectAccessType(page, "author");
		RequestAccessPopupUtils.enterComment(page, "Access Request");
		RequestAccessPopupUtils.clickOnRequestButton(page);
		logout(page);
		loginNativeAdmin(page);
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenAppLibrary(page);
		AppPageUtils.searchApp(page, appName, timestamp);
		AppPageUtils.clickOnAppCard(page, appName, timestamp);
		DragAndDropBlocksPageUtils.clickOnEditButton(page);
		CatlogAccessPageUtility.clickOnSettings(page);
		AddFunctionPageUtils.clickOnAccessControl(page);
		String actualCountWithText = SettingsModelPageUtils.getPendingRequestCountText(page);
		Assertions.assertEquals("1 pending request", actualCountWithText, "Pending request text not correct");
		SettingsModelPageUtils.clickOnPendingRequestsExpandButton(page);
		SettingsModelPageUtils.performActionOnPendingRequest(page, "Approve");
		String actualMessage = ModelPageUtils.modelCreationToastMessage(page, "Successfully approved user permissions");
		Assertions.assertEquals(actualMessage, "Successfully approved user permissions",
				"Approval toast message not correct");
		logout(page);
		loginEditor(page);
		NotificationsUtils.clickOnNotificationBellIcon(page);
		String byUser = ConfigUtils.getValue("Admin".toUpperCase() + "_USERNAME").split("@")[0];
		String actualNotificationMessage = NotificationsUtils
				.validateActionPerformOnRequestAccessNotificationMessage(page, "Author", appName + timestamp, byUser);
		Assertions.assertEquals(actualNotificationMessage, "Your request for Author permission on " + appName
				+ timestamp + " has been approved by " + byUser + ".");
		NotificationsUtils.closeNotificationPane(page);
		logout(page);
		loginNativeAdmin(page);
	}

	@AfterEach
	void tearDownEachTest(@PWPage Page page) {
		CommonUtils.navigateAndDeleteApp(page, appName + timestamp);
		logout(page);
	}

}
