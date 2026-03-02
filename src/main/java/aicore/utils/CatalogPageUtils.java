package aicore.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

/**
 * Every Catalog shares same behavior need to move similar utils here
 */
public class CatalogPageUtils {

	public static void clickOnMetadataTab(Page page) {
		Locator locator = page.getByTestId("engineLayout-Metadata-tab");
		AICorePageUtils.waitFor(locator);
		locator.isVisible();
		locator.click();
	}
}
