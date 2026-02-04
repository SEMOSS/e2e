package aicore.utils;

import com.microsoft.playwright.Page;

public class ViewUsagePageUtils {
     
	private static final String USAGE_TAB_XPATH = "//button[text()='Usage']";
	private static final String USAGE_TAB_EXAMPLE_STRING_XPATH = "(//p[text()='{Usage Example}'])";

	public static void clickOnUsageTab(Page page) {
		page.locator(USAGE_TAB_XPATH).isVisible();
		page.locator(USAGE_TAB_XPATH).click();
	}

	public static void verifyExample(Page page,String example) {
		page.getByText(example, new Page.GetByTextOptions().setExact(true)).isVisible();
		String exampleXpath = USAGE_TAB_EXAMPLE_STRING_XPATH.replace("{Usage Example}", example);
		page.locator(exampleXpath).isVisible();
		String exampleText = page.locator(exampleXpath).textContent();
		if (exampleText == null || exampleText.trim().length() <= 1) {
			throw new AssertionError("Example code is not visible for"+ example);
		}
	}
}
