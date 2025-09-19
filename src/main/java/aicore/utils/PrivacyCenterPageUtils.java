package aicore.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class PrivacyCenterPageUtils {
	private static final String PRIVACY_CENTER_BUTTON_DATA_TESTID = "settingsLayout-privacy-btn";
	private static final String CLOSE_ICON_DATA_TESTID = "CloseIcon";
	private static final String PRIVACY_POPUP_XPATH = "//div[@role='dialog']";
	private static final String POPUP_TITLE_XPATH = "//h6[text()='{title}']";
	private static final String CLOSE_BUTTON_XPATH = "//span[text()='Close']";

	public static void clickOnPrivacyCenterButton(Page page) {
		page.getByTestId(PRIVACY_CENTER_BUTTON_DATA_TESTID).click();
	}

	public static boolean isPrivacyPopupVisible(Page page) {
		return page.locator(PRIVACY_POPUP_XPATH).isVisible();
	}

	public static void waitForPopupClose(Page page) {
		page.locator(PRIVACY_POPUP_XPATH).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
	}

	public static void clickOnCloseIcon(Page page) {
		page.getByTestId(CLOSE_ICON_DATA_TESTID).click();
	}

	public static boolean isElemnetVisible(Page page, String element, String title) {
		Locator elementLocator;
		switch (element) {
		case "Popup":
			elementLocator = page.locator(PRIVACY_POPUP_XPATH);
			break;
		case "Title":
			elementLocator = page.locator(POPUP_TITLE_XPATH.replace("{title}", title));
			break;
		case "Close Icon":
			elementLocator = page.getByTestId(CLOSE_ICON_DATA_TESTID);
			break;
		case "Close button":
			elementLocator = page.locator(CLOSE_BUTTON_XPATH);
			break;
		default:
			throw new IllegalArgumentException("Unknown element: " + element);
		}
		AICorePageUtils.waitFor(elementLocator);
		return elementLocator.isVisible();
	}
}
