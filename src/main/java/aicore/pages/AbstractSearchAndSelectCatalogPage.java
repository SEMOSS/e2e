package aicore.pages;

import com.microsoft.playwright.Page;

import aicore.utils.SearchAndSelectCatalogPageUtils;

public abstract class AbstractSearchAndSelectCatalogPage {
	protected Page page;

	public void selectCatalogFromSearchOptions(String catalogName, String timestamp) {
		SearchAndSelectCatalogPageUtils.selectCatalogFromSearchOptions(page, catalogName, timestamp);
	}

	public void searchCatalog(String catalogName, String timestamp) {
		SearchAndSelectCatalogPageUtils.searchCatalog(page, catalogName, timestamp);

	}
}