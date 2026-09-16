package aicore.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

public class CatalogFilterPageUtils {

	private static final String SELECT_FILTER_VALUE_XPATH = "filterbox-{FilterValue}-filterBtn";
	private static final String CATALOG_NAME = "{CatalogName}";
	private static final String CATALOG_UNDER_BOOKMARKED_SECTION_XPATH = "//p[text()='Bookmarked']//following-sibling::div[1]//p[contains(text(),'{catalogName}')]";

	public static void searchFilterValue(Page page, String filterValue) {
		page.getByPlaceholder("Search by...").fill(filterValue);
	}

	public static void selectFilterValue(Page page, String filterValue) {
		if (filterValue.contains(" ")) {
			filterValue = filterValue.replace(" ", "-");
		}
		Locator filterValueLocator = page.getByTestId(SELECT_FILTER_VALUE_XPATH.replace("{FilterValue}", filterValue));
		AICorePageUtils.waitFor(filterValueLocator);
		filterValueLocator.click();
		try {
			PlaywrightAssertions.assertThat(filterValueLocator).hasAttribute("aria-pressed", "true");
		} catch (AssertionError e) {
			filterValueLocator.click();
			PlaywrightAssertions.assertThat(filterValueLocator).hasAttribute("aria-pressed", "true");
		}
	}

	public static boolean isFilterValueSelected(Page page, String filterValue) {
		if (filterValue.contains(" ")) {
			filterValue = filterValue.replace(" ", "-");
		}
		Locator filterValueLocator = page.getByTestId(SELECT_FILTER_VALUE_XPATH.replace("{FilterValue}", filterValue));
		return "true".equals(filterValueLocator.getAttribute("aria-pressed"));
	}


	public static boolean verifyCatalogIsVisibleOnCatalogPage(Page page, String catalogName) {
		Locator catalogLocator = page.getByText(CATALOG_NAME.replace("{CatalogName}", catalogName));
		try {
			AICorePageUtils.waitFor(catalogLocator);
		} catch (com.microsoft.playwright.TimeoutError e) {
			return false;
		}
		return catalogLocator.isVisible();
	}

	public static void clickOnBookmark(Page page, String catalogName) {
		page.getByTitle("Bookmark " + catalogName).click();
	}

	public static void clickOnUnbookmark(Page page, String catalogName) {
		page.getByTitle("Unbookmark " + catalogName).click();
	}

	public static boolean verifyCatalogDisplayedUnderBookmarkedSection(Page page, String catalogName) {
		Locator bookmarkedSectio = page
				.locator(CATALOG_UNDER_BOOKMARKED_SECTION_XPATH.replace("{catalogName}", catalogName));
		AICorePageUtils.waitFor(bookmarkedSectio);
		return bookmarkedSectio.isVisible();
	}
}
