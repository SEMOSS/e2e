package aicore.utils;

import com.microsoft.playwright.Page;

public class ViewUsagePageUtils {

	private static final String USAGE_TAB_XPATH = "//button[text()='Usage']";
	private static final String USAGE_TAB_EXAMPLE_STRING_XPATH = "(//p[text()='{Usage Example}'])";

	public static void clickOnUsageTab(Page page) {
		page.locator(USAGE_TAB_XPATH).isVisible();
		page.locator(USAGE_TAB_XPATH).click();
	}

	public static boolean verifyExample(Page page, String example) {
		return page.getByText(example, new Page.GetByTextOptions().setExact(true)).isVisible();
	}
}
