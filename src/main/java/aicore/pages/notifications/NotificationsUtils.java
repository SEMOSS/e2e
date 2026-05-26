package aicore.pages.notifications;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;

public class NotificationsUtils {
	public static final String NOTIFICATION_BELL_ICON_XPATH_ = "//button[@aria-label='Notifications']";
	public static final String NOTIFICATION_XPATH = "//div[@data-testid='notification-item']//div[span[contains(text(),'{NotificationMessage}')]]";
	public static final String NO_NOTIFICATION_MESSAGE_XPATH = "//div//p[text()='No notifications yet.']";
	public static final String READ_SECTION_XPATH = "//div[span[contains(text(),'{notificationMessage}')]]";
	public static final String NOTIFICATION_UNREAD_SECTION_XPATH = "//button[text()='Unread']";
	public static final String NOTIFICATION_READ_SECTION_XPATH = "//button[text()='Read']";
	public static final String NOTIFICATION_CLEAR_ALL_SECTION_XPATH = "//button[text()='Clear All']";
	public static final String USER_ADDED_NOTIFICATION_MESSAGE_FOR_USER_XPATH = "//div[@data-testid='notification-item'][.//span[normalize-space()='{accessProvided}'] and .//span[normalize-space()='{catalogName}'] and .//span[normalize-space()='{accessProvidedByUser}']] //div[span[contains(text(),'You are added as')]]";
	public static final String NOTIFICATION_PANE_CLOSE_XPATH = "//h2[text()='Notifications']/parent::div/following-sibling::button[span[text()='Close']]";
	public static final String USER_ADDED_NOTIFICATION_MESSAGE_FOR_OWNER_XPATH = "//div[@data-testid='notification-item'][.//span[normalize-space()='{accessProvided}'] and .//span[normalize-space()='{catalogName}']] //div[span[contains(text(),'has been added as')]]";
	public static final String USER_ADDED_NOTIFICATION_MESSAGE_FOR_OTHER_OWNER_XPATH = "//div[@data-testid='notification-item'][.//span[normalize-space()='{accessProvided}'] and .//span[normalize-space()='{catalogName}'] and .//span[normalize-space()='{accessProvidedByUser}']]//div[span[contains(text(),'has been added as')]]";
	public static final String REQUEST_ACTION_NOTIFICATION_XPATH = "//div[@data-testid='notification-item']//div[@class='text-sm'][.//span[normalize-space()='Your request for'] and .//span[normalize-space()='{accessType}'] and .//span[normalize-space()='permission on'] and .//span[normalize-space()='{catalogName}'] and .//span[normalize-space()='has been approved by'] and .//span[contains(text(),'{userName}')]]";

	public static void clickOnNotificationBellIcon(Page page) {
		page.locator(NOTIFICATION_BELL_ICON_XPATH_).click();
	}

	public static String validateUserAddedNotificationMessageForUser(Page page, String accessProvided,
			String catalogName, String accessProvidedByUser) {
		String actualMessage = page
				.locator(USER_ADDED_NOTIFICATION_MESSAGE_FOR_USER_XPATH.replace("{accessProvided}", accessProvided)
						.replace("{catalogName}", catalogName).replace("{accessProvidedByUser}", accessProvidedByUser))
				.first().textContent().trim();

		return actualMessage;
	}

	public static String validateUserAddedNotificationMessageForOwner(Page page, String accessProvided,
			String catalogName) {
		String actualMessage = page.locator(USER_ADDED_NOTIFICATION_MESSAGE_FOR_OWNER_XPATH
				.replace("{accessProvided}", accessProvided).replace("{catalogName}", catalogName)).first()
				.textContent().trim();

		return actualMessage;
	}

	public static String validateUserAddedNotificationMessageForOtherOwner(Page page, String role, String catalogName,
			String addedBy) {
		String actualMessage = page
				.locator(USER_ADDED_NOTIFICATION_MESSAGE_FOR_OTHER_OWNER_XPATH.replace("{accessProvided}", role)
						.replace("{catalogName}", catalogName).replace("{accessProvidedByUser}", addedBy))
				.first().textContent().trim();
		return actualMessage;
	}

	public static void closeNotificationPane(Page page) {
		page.locator(NOTIFICATION_PANE_CLOSE_XPATH).click();
	}

	public static void clickOnNotificationMessage(Page page, String NotificationMessage) {
		Locator Notification = page
				.locator(NOTIFICATION_XPATH.replace("{NotificationMessage}", NotificationMessage)).first();
		Notification.isVisible();
		Notification.click();
	}

	public static void clickOnUnreadTab(Page page) {
		page.locator(NOTIFICATION_UNREAD_SECTION_XPATH).click();
	}

	public static void clickOnReadTab(Page page) {
		page.locator(NOTIFICATION_READ_SECTION_XPATH).click();
	}

	public static void clickOnClearAllButton(Page page) {
		page.locator(NOTIFICATION_CLEAR_ALL_SECTION_XPATH).click();
	}

	public static boolean isNotificationMessagePresentInReadSection(Page page, String notificationMessage) {
		page.waitForTimeout(1500); // Wait for the notification message to appear in the Read section
		return page.locator(READ_SECTION_XPATH.replace("{notificationMessage}", notificationMessage)).isVisible();
	}

	public static boolean isUnreadSectionBlank(Page page) {
		return page.locator(NO_NOTIFICATION_MESSAGE_XPATH).isVisible();
	}

	public static boolean isReadSectionBlank(Page page) {
		return page.locator(NO_NOTIFICATION_MESSAGE_XPATH).isVisible();
	}

	public static String validateActionPerformOnRequestAccessNotificationMessage(Page page, String accessType,
			String catalogName, String byUser) {
		String actualMessage = page.locator(REQUEST_ACTION_NOTIFICATION_XPATH.replace("{accessType}", accessType)
				.replace("{catalogName}", catalogName).replace("{userName}", byUser)).textContent().trim();
		return actualMessage;
	}
}