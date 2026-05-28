package aicore.unit.notifications.model;

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
import aicore.pages.model.AddModelFormUtils;
import aicore.pages.model.EditModelPageUtils;
import aicore.pages.model.SettingsModelPageUtils;
import aicore.pages.notifications.NotificationsUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.AddFunctionPageUtils;
import aicore.utils.CommonUtils;
import aicore.utils.RequestAccessPopupUtils;
import aicore.utils.TestResourceTrackerHelper;
import aicore.utils.annotations.PWPage;
import aicore.utils.page.model.ModelPageUtils;

public class NotificationsTests extends AbstractPlaywrightTestBase {
	private static String catalogName = null;

	@BeforeEach
	public void createModelCatalog(@PWPage Page page) {
		loginNativeAdmin(page);

		String timestamp = CommonUtils.getTimeStampName();
		catalogName = "Model" + timestamp;
		String modelType = "OpenAI";
		String modelName = "GPT-4.1";
		String openAIKey = "Test@1234";

		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenModel(page);
		// model form options
		ModelPageUtils.clickAddModelButton(page);
		AddModelFormUtils.selectModelType(page, modelType);
		AddModelFormUtils.selectModel(page, modelName);
		AddModelFormUtils.enterCatalogName(page, catalogName);
		AddModelFormUtils.enterOpenAIKey(page, openAIKey);
		AddModelFormUtils.clickOnCreateModelButton(page);
	}

	@Test
	@DisplayName("Validate newly added member received notification")
	public void verifyNewlyAddedMemberReceivesNotification(@PWPage Page page) throws InterruptedException {
		String accessProvided = "Editor";
		String byUser = ConfigUtils.getValue("Admin".toUpperCase() + "_USERNAME").split("@")[0];
		// Open access control tab and add a new member
		SettingsModelPageUtils.clickOnAccessControl(page);
		SettingsModelPageUtils.clickOnAddMembersButton(page);
		SettingsModelPageUtils.addMember(page, accessProvided, GenericSetupUtils.useDocker());
		// Logout and login with the newly added member
		logout(page);
		loginEditor(page);
		// Validate the notification message for the newly added member
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenModel(page);
		NotificationsUtils.clickOnNotificationBellIcon(page);
		String actualUserAddedNotificationMessage = NotificationsUtils.validateUserAddedNotificationMessageForUser(page,
				accessProvided, catalogName, byUser);
		String expectedMessage = String.format("You are added as %s to %s by %s.", accessProvided, catalogName, byUser);
		Assertions.assertEquals(expectedMessage, actualUserAddedNotificationMessage,
				"The notification message is not as expected.");
		// Close the notification pane
		NotificationsUtils.closeNotificationPane(page);
		// Logout from newly added member account and login back with admin for cleanup
		logout(page);
		loginNativeAdmin(page);
	}

	@Test
	@DisplayName("Validate along with the newly added user, all owner users receive a notification")
	public void verifyNewUserAndAllOwnersReceivesNotification(@PWPage Page page) throws InterruptedException {
		// Open access control tab
		SettingsModelPageUtils.clickOnAccessControl(page);
		SettingsModelPageUtils.clickOnAddMembersButton(page);
		// Add a new member as Author
		SettingsModelPageUtils.addMember(page, "Author", GenericSetupUtils.useDocker());
		// Validate the notification message for the owner who added the new member
		NotificationsUtils.clickOnNotificationBellIcon(page);
		String actualUserAddedNotificationMessageForOwner = NotificationsUtils
				.validateUserAddedNotificationMessageForOwner(page, "Author", catalogName);
		String expectedUserAddedNotificationMessageForOwner = String.format("has been added as %s to %s by you.",
				"Author", catalogName);
		Assertions.assertTrue(
				actualUserAddedNotificationMessageForOwner.contains(expectedUserAddedNotificationMessageForOwner),
				"The notification message is not as expected.");
		// Close the notification pane
		NotificationsUtils.closeNotificationPane(page);
		// Now add another member as Editor
		SettingsModelPageUtils.clickOnAddMembersButton(page);
		SettingsModelPageUtils.addMember(page, "Editor", GenericSetupUtils.useDocker());
		// Logout and login with the newly added member
		logout(page);
		loginAuthor(page);
		// Validate notification message for the first newly added member who is Author
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenModel(page);
		NotificationsUtils.clickOnNotificationBellIcon(page);
		String byUser = ConfigUtils.getValue("Admin".toUpperCase() + "_USERNAME").split("@")[0];
		String actualUserAddedNotificationMessageForOtherOwner = NotificationsUtils
				.validateUserAddedNotificationMessageForOtherOwner(page, "Editor", catalogName, byUser);
		String expectedUserAddedNotificationMessageForOtherOwner = String.format("has been added as %s to %s by %s.",
				"Editor", catalogName, byUser);
		Assertions.assertTrue(
				actualUserAddedNotificationMessageForOtherOwner
						.contains(expectedUserAddedNotificationMessageForOtherOwner),
				"The notification message is not as expected.");
		// Close the notification pane
		NotificationsUtils.closeNotificationPane(page);
		// Logout from newly added member account and login back with Admin for cleanup
		logout(page);
		loginNativeAdmin(page);
	}

	@Test
	@DisplayName("Validate along with the newly added user, can perform read, unread and clear all notifications operation")

	public void verifyNewUserAndAllOwnersReceivesNotificationOperation(@PWPage Page page) throws InterruptedException {
		// Open access control tab
		SettingsModelPageUtils.clickOnAccessControl(page);
		SettingsModelPageUtils.clickOnAddMembersButton(page);
		// Add a new member as Author
		SettingsModelPageUtils.addMember(page, "Author", GenericSetupUtils.useDocker());
		// Validate the notification message for the owner who added the new member
		NotificationsUtils.clickOnNotificationBellIcon(page);
		String actualUserAddedNotificationMessageForOwner = NotificationsUtils
				.validateUserAddedNotificationMessageForOtherOwner(page, "Author", "Author", catalogName);
		String expectedUserAddedNotificationMessageForOwner = String.format("%s has been added as %s to %s by you.",
				"Author User", "Author", catalogName);
		Assertions.assertEquals(expectedUserAddedNotificationMessageForOwner,
				actualUserAddedNotificationMessageForOwner, "The notification message is not as expected.");
		// click on the notification message to move it to unread to read section and
		// Unread section is blank
		NotificationsUtils.clickOnUnreadTab(page);
		NotificationsUtils.clickOnNotificationMessage(page, actualUserAddedNotificationMessageForOwner);
		boolean isUnreadSectionBlank = NotificationsUtils.isUnreadSectionBlank(page);
		Assertions.assertTrue(isUnreadSectionBlank,
				"The Unread section is not blank after clicking on the notification message.");
		// switching to read section and validate the notification message is present
		// there
		NotificationsUtils.clickOnReadTab(page);
		boolean isNotificationMessagePresentInReadSection = NotificationsUtils
				.isNotificationMessagePresentInReadSection(page, "You are added as");
		Assertions.assertTrue(isNotificationMessagePresentInReadSection,
				"The notification message is not present in the Read section after clicking on the notification message.");
		// Clear all notifications
		NotificationsUtils.clickOnClearAllButton(page);
		boolean isReadSectionBlank = NotificationsUtils.isReadSectionBlank(page);
		// Check all notifications are cleared and read section is blank
		Assertions.assertTrue(isReadSectionBlank, "The Read section is not blank after clearing all notifications.");
		// Close the notification pane
		NotificationsUtils.closeNotificationPane(page);
		// Now add another member as Editor
		SettingsModelPageUtils.clickOnAddMembersButton(page);
		SettingsModelPageUtils.addMember(page, "Editor", GenericSetupUtils.useDocker());
		// Logout and login with the newly added member
		logout(page);
		loginAuthor(page);
		// Validate notification message for the first newly added member who is Author
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenModel(page);
		NotificationsUtils.clickOnNotificationBellIcon(page);
		String actualUserAddedNotificationMessageForOtherOwner = NotificationsUtils
				.validateUserAddedNotificationMessageForOtherOwner(page, "Editor", catalogName, "Admin");
		String expectedUserAddedNotificationMessageForOtherOwner = String
				.format("has been added as %s to %s by %sUser.", "Editor", catalogName, "Admin");
		Assertions.assertEquals(expectedUserAddedNotificationMessageForOtherOwner,
				actualUserAddedNotificationMessageForOtherOwner, "The notification message is not as expected.");
		// Close the notification pane
		NotificationsUtils.closeNotificationPane(page);
		// Logout from newly added member account and login back with Admin for cleanup
		logout(page);
		loginNativeAdmin(page);
	}

	@Test
	@DisplayName("Validate Notification message for Accept the request")
	public void testAcceptRequestAndValidateMemberList(@PWPage Page page) {
		AddFunctionPageUtils.clickOnAccessControl(page);
		FunctionAccessSettingsUtils.clickOnMakeDiscoverableButton(page, "Model");
		logout(page);
		loginEditor(page);
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenModel(page);
		SettingsModelPageUtils.clickOnDiscoverableModelsButton(page);
		EditModelPageUtils.searchModelCatalog(page, catalogName);
		EditModelPageUtils.selectModelFromSearchOptions(page, catalogName);
		EditModelPageUtils.clickOnRequestAccessButtonOfDiscoverableCatalog(page);
		RequestAccessPopupUtils.selectAccessType(page, "author");
		RequestAccessPopupUtils.enterComment(page, "Access Request");
		RequestAccessPopupUtils.clickOnRequestButton(page);
		logout(page);
		loginAdmin(page);
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenModel(page);
		EditModelPageUtils.searchModelCatalog(page, catalogName);
		EditModelPageUtils.selectModelFromSearchOptions(page, catalogName);
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
		String actualNotificationMessage = NotificationsUtils.validateActionPerformOnRequestAccessNotificationMessage(
				page, "Author", catalogName, "approved", byUser);
		Assertions.assertEquals(actualNotificationMessage,
				"Your request for Author permission on " + catalogName + " has been approved by " + byUser + ".");
		NotificationsUtils.closeNotificationPane(page);
		logout(page);
		loginAdmin(page);
	}

	@Test
	@DisplayName("Validate notification message for request denied")
	public void verifyAccessRequestRejectedNotification(@PWPage Page page) {
		// Make catalog discoverable
		AddFunctionPageUtils.clickOnAccessControl(page);
		FunctionAccessSettingsUtils.clickOnMakeDiscoverableButton(page, "Model");
		logout(page);
		loginEditor(page);
		// Request for access
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenModel(page);
		SettingsModelPageUtils.clickOnDiscoverableModelsButton(page);
		EditModelPageUtils.searchModelCatalog(page, catalogName);
		EditModelPageUtils.selectModelFromSearchOptions(page, catalogName);
		EditModelPageUtils.clickOnRequestAccessButtonOfDiscoverableCatalog(page);
		RequestAccessPopupUtils.selectAccessType(page, "author");
		RequestAccessPopupUtils.enterComment(page, "Access Request");
		RequestAccessPopupUtils.clickOnRequestButton(page);
		logout(page);
		loginAdmin(page);
		// Perform action on access request
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenModel(page);
		EditModelPageUtils.searchModelCatalog(page, catalogName);
		EditModelPageUtils.selectModelFromSearchOptions(page, catalogName);
		AddFunctionPageUtils.clickOnAccessControl(page);
		String actualCountWithText = SettingsModelPageUtils.getPendingRequestCountText(page);
		Assertions.assertEquals("1 pending request", actualCountWithText, "Pending request text not correct");
		SettingsModelPageUtils.clickOnPendingRequestsExpandButton(page);
		SettingsModelPageUtils.performActionOnPendingRequest(page, "Reject");
		String actualMessage = ModelPageUtils.modelCreationToastMessage(page, "Successfully denied user permissions");
		Assertions.assertEquals(actualMessage, "Successfully denied user permissions", "Toast message not correct");
		logout(page);
		loginEditor(page);
		// validate request denied notification message
		NotificationsUtils.clickOnNotificationBellIcon(page);
		String byUser = ConfigUtils.getValue("Admin".toUpperCase() + "_USERNAME").split("@")[0];
		String actualNotificationMessage = NotificationsUtils
				.validateActionPerformOnRequestAccessNotificationMessage(page, "Author", catalogName, "denied", byUser);
		Assertions.assertEquals(actualNotificationMessage,
				"Your request for Author permission on " + catalogName + " has been denied by " + byUser + ".");
		NotificationsUtils.closeNotificationPane(page);
		logout(page);
		loginAdmin(page);
	}

	@AfterEach
	public void teardown(@PWPage Page page) {
		// Clean up: delete the test model catalog
		if (catalogName != null && page != null) {
			try {
				CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_MODEL, catalogName);
			} catch (Exception e) {
				System.err.println("Failed to delete model catalog: " + e.getMessage());
			}
		}
		logout(page);
	}

}
