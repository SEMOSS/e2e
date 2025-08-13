package aicore.utils;

import com.microsoft.playwright.Page;

public class SearchAndSelectCatalogPageUtils {
	private static final String STORAGE_CATALOG_SEARCH_TEXTBOX_XPATH = "//input[@placeholder='Search']";
	private static final String SEARCHED_STORAGE_XPATH = "//p[text()='{catalogName}']";

	public static void selectCatalogFromSearchOptions(Page page, String catalogName) {
		page.locator((SEARCHED_STORAGE_XPATH.replace("{catalogName}", catalogName))).isVisible();
		page.locator(SEARCHED_STORAGE_XPATH.replace("{catalogName}", catalogName)).click();
	}

	public static void searchCatalog(Page page, String catalogName) {
		page.getByLabel("Search").click();
		page.getByLabel("Search").fill(catalogName);
	}
}
