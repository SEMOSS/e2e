package aicore.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

/**
 * Main AI Core Home page utils
 */
public class AICorePageUtils {

	private static final String TOAST_MESSAGE_XPATH = "//div[contains(@class, 'MuiAlert-message') and contains(text(), '{TOAST_MESSAGE}')]";
	private static final String NEXT_BUTTON_XPATH = "//button[span[normalize-space(text())='Next']]";

	public static Locator verifyToastMessage(Page page, String msg) {
		Locator locator = page.locator(TOAST_MESSAGE_XPATH.replace("{TOAST_MESSAGE}", msg));
		locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
		return locator;
	}

	public static String readStringFromClipboard(Page page) {
		String clipboardText = null;
		Boolean isClipboardSupported = (Boolean) page.evaluate("typeof navigator.clipboard !== 'undefined'");
		if (isClipboardSupported) {
			clipboardText = (String) page.evaluate("navigator.clipboard.readText()");
			System.out.println("Clipboard contains: " + clipboardText);
		} else {
			System.out.println("Clipboard API is not supported in this context.");
		}
		return clipboardText;
	}

	public static void clickOnButton(Page page, String buttonLabel) {
		Locator buttonLocator = page.getByRole(AriaRole.BUTTON,
				new Page.GetByRoleOptions().setName(buttonLabel).setExact(true));
		buttonLocator.scrollIntoViewIfNeeded();
		buttonLocator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
//		page.locator(NEXT_BUTTON_XPATH).click();
		buttonLocator.click();
	}
	
	public static void waitFor(Locator locator) {
		if (!locator.isVisible()) {
			locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		}
	}


}
