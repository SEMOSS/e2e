package aicore.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.microsoft.playwright.options.WaitUntilState;

import aicore.framework.AICoreTestConstants;
import aicore.framework.ConfigUtils;
import io.qameta.allure.Allure;

/**
 * Main AI Core Home page utils
 */
public class AICorePageUtils {
	private static final String TOAST_CLOSE_XPATH = "//div[@data-testid='notification-success-alert']//button[@aria-label='Close']";

	public static String verifySuccessToastMessage(Page page, Locator locator) {
		locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(20000));
		String toastMessage = locator.textContent().trim();
		return toastMessage;
	}

	public static void closeToastMessage(Page page) {
		Locator closeToaster = page.locator(TOAST_CLOSE_XPATH);
		closeToaster.click();
		closeToaster.first().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.DETACHED));
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
		AICorePageUtils.waitFor(buttonLocator);
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
		Double timeout = Double.parseDouble(ConfigUtils.getValue(AICoreTestConstants.TIMEOUT));
		locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(timeout));
	}

	public static void refreshPage(Page page) {
		page.reload(new Page.ReloadOptions().setWaitUntil(WaitUntilState.NETWORKIDLE));
	}
	
	public static void saveScreenshotAtStep(Page page, String stepName) {
		Allure.step(stepName, () -> {
			try {
				Path dir = Paths.get("test-output", "screenshots");
				Files.createDirectories(dir);

				Path file = dir.resolve("login-" + System.currentTimeMillis() + ".png");

				page.screenshot(new Page.ScreenshotOptions().setPath(file).setFullPage(true));

				try (InputStream is = Files.newInputStream(file)) {
					Allure.addAttachment("Saved screenshot", "image/png", is, ".png");
				}

			} catch (IOException e) {
				throw new RuntimeException("Failed to save screenshot", e);
			}
		});
	}
}
