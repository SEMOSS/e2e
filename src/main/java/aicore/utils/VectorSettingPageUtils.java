package aicore.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class VectorSettingPageUtils {

	private static final String CATALOG_SEARCHBOX_PLACEHOLDER = "Search";
	private static final String VECTOR_CARDS_XPATH = "//div[contains(@class,'MuiGrid-root MuiGrid-container')]";
	private static final String VECTOR_NAME_XPATH = "//p[contains(text(),'{vectorName}')]";

	public static void verifyVectorSettingTitle(Page page, String title) {
		Locator titleLocator = page.locator("h4:has-text('" + title + "')");
		titleLocator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		if (!titleLocator.isVisible()) {
			throw new AssertionError("Vector settings title '" + title + "' is not visible on the page.");
		}
	}

	public static void verifyVectorCards(Page page) {
		int vectorCardCount = page.locator(VECTOR_CARDS_XPATH).count();
		if (vectorCardCount < 1) {
			throw new AssertionError(
					"Expected vector count should be more than or equal to 1 vector card, but found 0.");
		}
	}

	public static void isSearchBarPresent(Page page) {
		if (!page.getByPlaceholder(CATALOG_SEARCHBOX_PLACEHOLDER).isVisible()) {
			throw new AssertionError("Search bar is not visible on the page.");
		}
	}

	public static void searchForVector(Page page, String vectorName) {
		page.getByPlaceholder(CATALOG_SEARCHBOX_PLACEHOLDER).click();
		page.getByPlaceholder(CATALOG_SEARCHBOX_PLACEHOLDER).fill(vectorName);
		page.getByPlaceholder(CATALOG_SEARCHBOX_PLACEHOLDER).press("Enter");
	}

	public static void verifySearchedVector(Page page, String vectorName) {
		Locator vectorLocator = page.locator(VECTOR_NAME_XPATH.replace("{vectorName}", vectorName));
		vectorLocator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		if (!vectorLocator.isVisible()) {
			throw new AssertionError("Searched vector '" + vectorName + "' is not visible on the page.");
		}
	}

	public static void clickOnVectorCardByName(Page page, String catalogName) {
		page.locator(VECTOR_NAME_XPATH.replace("{vectorName}", catalogName)).click();
	}
}
