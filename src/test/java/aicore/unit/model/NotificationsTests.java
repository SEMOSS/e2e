package aicore.unit.model;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;

import aicore.base.GenericSetupUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.pages.model.AddModelFormUtils;
import aicore.pages.model.SettingsModelPageUtils;
import aicore.pages.notifications.NotificationsUtils;
import aicore.utils.AbstractE2ETest;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.CommonUtils;
import aicore.utils.TestResourceTrackerHelper;
import aicore.utils.annotations.PWPage;
import aicore.utils.page.model.ModelPageUtils;

public class NotificationsTests extends AbstractPlaywrightTestBase {
	private static String catalogName = null;

	@BeforeEach
	public void setup(@PWPage Page page) throws IOException {
		// login with native user before tests
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
		String accessProvidedByUser = "Admin";
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
		String actualUserAddedNotificationMessage = NotificationsUtils.validateUserAddedNotificationMessage(page,
				accessProvided, catalogName, accessProvidedByUser);
		String expectedMessage = String.format("You are added as %s to %s by %s.", accessProvided, catalogName,
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
