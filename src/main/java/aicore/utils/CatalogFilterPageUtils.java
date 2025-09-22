package aicore.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CatalogFilterPageUtils {

	private static final String SELECT_FILTER_VALUE_XPATH = "//h6[text()='{filterCategory}']/ancestor::li/following-sibling::div//p[text()='{filterValue}']";
	private static final String CATALOG_NAME = "{CatalogName}";
	private static final String BOOKMARK_ICON_XPATH = "//button[contains(@title, '{catalogName}')]/*[name()='svg']";
	private static final String CATALOG_UNDER_BOOKMARKED_SECTION_XPATH = "//h6[text()='Bookmarked']/following-sibling::div[1]//p[contains(text(),'{catalogName}')]";

	public static void searchFilterValue(Page page, String filterValue) {
		page.getByPlaceholder("Search by...").fill(filterValue);
	}

	public static void selectFilterValue(Page page, String filterCategory, String filterValue) {
		Locator filterValueLocator = page.locator(SELECT_FILTER_VALUE_XPATH.replace("{filterCategory}", filterCategory)
				.replace("{filterValue}", filterValue));
		AICorePageUtils.waitFor(filterValueLocator);
		filterValueLocator.click();
	}

	public static boolean verifyCatalogIsVisibleOnCatalogPage(Page page, String catalogName) {
		Locator catalogLocator = page.getByText(CATALOG_NAME.replace("{CatalogName}", catalogName));
		AICorePageUtils.waitFor(catalogLocator);
		return catalogLocator.isVisible();
	}

	public static void clickOnBookmark(Page page, String catalogName) {
		page.locator(BOOKMARK_ICON_XPATH.replace("{catalogName}", catalogName)).click();
	}

	public static void clickOnUnbookmark(Page page, String catalogName) {
		page.locator(BOOKMARK_ICON_XPATH.replace("{catalogName}", catalogName)).first().click();
	}

	public static boolean verifyCatalogDisplayedUnderBookmarkedSection(Page page, String catalogName) {
		Locator bookmarkedSectio = page
				.locator(CATALOG_UNDER_BOOKMARKED_SECTION_XPATH.replace("{catalogName}", catalogName));
		AICorePageUtils.waitFor(bookmarkedSectio);
		return bookmarkedSectio.isVisible();
	}
}
