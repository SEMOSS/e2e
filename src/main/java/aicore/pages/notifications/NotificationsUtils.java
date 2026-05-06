package aicore.pages.notifications;

import com.microsoft.playwright.Page;

public class NotificationsUtils {
	public static final String NOTIFICATION_BELL_ICON_XPATH_ = "//button[@aria-label='Notifications']";
	public static final String USER_ADDED_NOTIFICATION_MESSAGE_XPATH = "//div[@data-testid='notification-item'][.//span[normalize-space()='{accessProvided}'] and .//span[normalize-space()='{catalogName}'] and .//span[normalize-space()='{accessProvidedByUser}User']] //div[span[contains(text(),'You are added as')]]";
	public static final String NOTIFICATION_PANE_CLOSE_XPATH = "//h2[text()='Notifications']/parent::div/following-sibling::button[span[text()='Close']]";

	public static void clickOnNotificationBellIcon(Page page) {
		page.locator(NOTIFICATION_BELL_ICON_XPATH_).click();
	}

	public static String validateUserAddedNotificationMessage(Page page, String accessProvided, String catalogName,
			String accessProvidedByUser) {
		String actualMessage = page
				.locator(USER_ADDED_NOTIFICATION_MESSAGE_XPATH.replace("{accessProvided}", accessProvided)
						.replace("{catalogName}", catalogName).replace("{accessProvidedByUser}", accessProvidedByUser))
				.first().textContent().trim();

		return actualMessage;
	}

	public static void closeNotificationPane(Page page) {
		page.locator(NOTIFICATION_PANE_CLOSE_XPATH).click();
	}
}
