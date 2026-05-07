package aicore.unit.model;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import aicore.base.GenericSetupUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.pages.model.AddModelFormUtils;
import aicore.pages.model.SettingsModelPageUtils;
import aicore.pages.notifications.NotificationsUtils;
import aicore.utils.AbstractE2ETest;
import aicore.utils.CommonUtils;
import aicore.utils.TestResourceTrackerHelper;
import aicore.utils.page.model.ModelPageUtils;

public class NotificationsTests extends AbstractE2ETest {
	private static String catalogName = null;

	@BeforeAll
	public static void setup() throws IOException {
		// login with native user before tests
		login(page, UserType.ADMIN);

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
	public void verifyNewlyAddedMemberReceivesNotification() throws InterruptedException {
		String accessProvided = "Editor";
		String accessProvidedByUser = "Admin";
		// Open access control tab and add a new member
		SettingsModelPageUtils.clickOnAccessControl(page);
		SettingsModelPageUtils.clickOnAddMembersButton(page);
		SettingsModelPageUtils.addMember(page, accessProvided, GenericSetupUtils.useDocker());
		// Logout and login with the newly added member
		logout(page);
		login(page, UserType.EDITOR);
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
		login(page, UserType.ADMIN);
	}

	@AfterAll
	public static void teardown() {
		// Clean up: delete the test model catalog
		if (catalogName != null && page != null) {
			try {
				CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_MODEL, catalogName);
			} catch (Exception e) {
				System.err.println("Failed to delete model catalog: " + e.getMessage());
			}
		}
	}
}
