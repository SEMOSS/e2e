package aicore.pages.base;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import aicore.utils.AICorePageUtils;

public class AbstractBasePage {

	protected static void clickAnywhereOnPage(Page page) {
		page.locator("body").click(); /// click anywhere on page to close menu side-bar
	}
	
	protected static void waitAndClick(Locator locator) {
		AICorePageUtils.waitFor(locator);
		locator.click();
	}
	
	protected static void waitAndFill(Locator locator, String content) {
		AICorePageUtils.waitFor(locator);
		locator.fill(content);
	}
}
