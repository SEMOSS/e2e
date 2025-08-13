package aicore.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

/**
 * Every Catalog shares same behavior need to move similar utils here
 */
public class CatalogPageUtils {

	public static void clickOnMetadataTab(Page page) {
		// TODO need datatestid
		Locator locator = page.locator("button.MuiTab-root:has-text('Metadata')");
		AICorePageUtils.waitFor(locator);
		locator.isVisible();
		locator.click();
	}
}
