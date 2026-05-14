package aicore.pages.notifications;

import com.microsoft.playwright.Page;

public class NotificationsUtils {
	public static final String NOTIFICATION_BELL_ICON_XPATH_ = "//button[@aria-label='Notifications']";
	public static final String USER_ADDED_NOTIFICATION_MESSAGE_FOR_USER_XPATH = "//div[@data-testid='notification-item'][.//span[normalize-space()='{accessProvided}'] and .//span[normalize-space()='{catalogName}'] and .//span[normalize-space()='{accessProvidedByUser}User']] //div[span[contains(text(),'You are added as')]]";
	public static final String NOTIFICATION_PANE_CLOSE_XPATH = "//h2[text()='Notifications']/parent::div/following-sibling::button[span[text()='Close']]";
	public static final String USER_ADDED_NOTIFICATION_MESSAGE_FOR_OWNER_XPATH = "//div[@data-testid='notification-item'][.//span[normalize-space()='{userName} User'] and .//span[normalize-space()='{accessProvided}'] and .//span[normalize-space()='{catalogName}']] //div[span[contains(text(),'has been added as')]]";
	public static final String USER_ADDED_NOTIFICATION_MESSAGE_FOR_OTHER_OWNER_XPATH = "//div[@data-testid='notification-item'][.//span[normalize-space()=\"{userName} User's\"] and .//span[normalize-space()='{accessProvided}'] and .//span[normalize-space()='{catalogName}'] and .//span[normalize-space()='{accessProvidedByUser}User']]//div[span[contains(text(),'has been added as')]]";

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

	public static String validateUserAddedNotificationMessageForOwner(Page page, String userName, String accessProvided,
			String catalogName) {
		String actualMessage = page
				.locator(USER_ADDED_NOTIFICATION_MESSAGE_FOR_OWNER_XPATH.replace("{userName}", userName)
						.replace("{accessProvided}", accessProvided).replace("{catalogName}", catalogName))
				.first().textContent().trim();

		return actualMessage;
	}

	public static String validateUserAddedNotificationMessageForOtherOwner(Page page, String userName, String role,
			String catalogName, String addedBy) {
		String actualMessage = page.locator(USER_ADDED_NOTIFICATION_MESSAGE_FOR_OTHER_OWNER_XPATH
				.replace("{userName}", userName).replace("{accessProvided}", role).replace("{catalogName}", catalogName)
				.replace("{accessProvidedByUser}", addedBy)).first().textContent().trim();
		return actualMessage;
	}

	public static void closeNotificationPane(Page page) {
		page.locator(NOTIFICATION_PANE_CLOSE_XPATH).click();
	}
}
