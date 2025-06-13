package aicore.utils;

import com.microsoft.playwright.Page;

public class SearchAndSelectCatalogPageUtils {
	private static final String STORAGE_CATALOG_SEARCH_TEXTBOX_XPATH = "//input[@placeholder='Search']";
	private static final String SEARCHED_STORAGE_XPATH = "//p[text()='{catalogName}']";

	public static void selectCatalogFromSearchOptions(Page page, String catalogName, String timestamp) {
		page.locator((SEARCHED_STORAGE_XPATH.replace("{catalogName}", catalogName + timestamp))).isVisible();
		page.locator(SEARCHED_STORAGE_XPATH.replace("{catalogName}", catalogName + timestamp)).click();
	}

	public static void searchCatalog(Page page, String catalogName, String timestamp) {
		page.locator(STORAGE_CATALOG_SEARCH_TEXTBOX_XPATH).click();
		page.locator(STORAGE_CATALOG_SEARCH_TEXTBOX_XPATH).fill(catalogName + timestamp);
	}
}