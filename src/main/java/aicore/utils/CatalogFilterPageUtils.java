package aicore.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CatalogFilterPageUtils {

	private static final String SELECT_FILTER_VALUE_XPATH = "//h6[text()='{filterCategory}']/ancestor::li/following-sibling::div//p[text()='{filterValue}']";
	private static final String CATALOG_NAME = "{CatalogName}";

	public static void searchFilterValue(Page page, String filterValue) {
		page.getByPlaceholder("Search by...").fill(filterValue);
	}

	public static void selectFilterValue(Page page, String filterCategory, String filterValue) {
		Locator filterValueLocator = page.locator(SELECT_FILTER_VALUE_XPATH.replace("{filterCategory}", filterCategory)
				.replace("{filterValue}", filterValue));
		filterValueLocator.waitFor();
		filterValueLocator.click();
	}

	public static boolean verifyCatalogIsVisibleOnCatalogPage(Page page, String catalogName) {
		boolean isFunctionVisible = page.getByText(CATALOG_NAME.replace("{CatalogName}", catalogName)).isVisible();
		return isFunctionVisible;
	}
}
