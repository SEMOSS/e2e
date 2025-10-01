package aicore.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

/**
 * Main AI Core Home page utils
 */
public class AICorePageUtils {

	public static String verifySuccessToastMessage(Page page, Locator locator) {
		locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(20000));
		String toastMessage = locator.textContent().trim();
		return toastMessage;
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
		buttonLocator.click();
	}

	public static void clickOnTabButton(Page page, String buttonLabel) {
		Locator buttonLocator = page.getByRole(AriaRole.TAB,
				new Page.GetByRoleOptions().setName(buttonLabel).setExact(true));
		buttonLocator.scrollIntoViewIfNeeded();
		buttonLocator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		buttonLocator.click();
	}

	public static void waitFor(Locator locator) {
		locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
	}
}
