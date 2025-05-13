package aicore.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

/**
 * Main AI Core Home page utils
 */
public class AICorePageUtils {
	
	private static final String TOAST_MESSAGE_XPATH = "//div[contains(@class, 'MuiAlert-message') and contains(text(), '{TOAST_MESSAGE}')]";
	
	public static void verifyToastMessage(Page page, String msg) {
		page.locator(TOAST_MESSAGE_XPATH.replace("{TOAST_MESSAGE}", msg))
		.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
	}

	public static String readStringFromClipboard(Page page) {
		return page.evaluate("navigator.clipboard.readText()").toString();
	}

}
