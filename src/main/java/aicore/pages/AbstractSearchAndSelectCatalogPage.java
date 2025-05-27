package aicore.pages;

import com.microsoft.playwright.Page;

import aicore.utils.AbstarctSearchAndSelectCatalogPageUtils;

public abstract class AbstractSearchAndSelectCatalogPage {
	protected Page page;

	public void selectCatalogFromSearchOptions(String catalogName, String timestamp) {
		AbstarctSearchAndSelectCatalogPageUtils.selectCatalogFromSearchOptions(page, catalogName, timestamp);
	}

	public void searchCatalog(String catalogName, String timestamp) {
		AbstarctSearchAndSelectCatalogPageUtils.searchCatalog(page, catalogName, timestamp);
	}

	public void isOptionVisible(String option) {
		AbstarctSearchAndSelectCatalogPageUtils.isOptionVisible(page, option);
	}
}
