package aicore.pages;

import com.microsoft.playwright.Page;

import aicore.utils.SearchAndSelectCatalogPageUtils;

public abstract class AbstractSearchAndSelectCatalogPage {
	protected Page page;

	public void selectCatalogFromSearchOptions(String catalogName) {
		SearchAndSelectCatalogPageUtils.selectCatalogFromSearchOptions(page, catalogName);
	}

	public void searchCatalog(String catalogName) {
		SearchAndSelectCatalogPageUtils.searchCatalog(page, catalogName);

	}
}
