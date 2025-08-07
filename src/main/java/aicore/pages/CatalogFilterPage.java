package aicore.pages;

import com.microsoft.playwright.Page;

import aicore.utils.CatalogFilterPageUtils;

public class CatalogFilterPage {
	protected Page page;

	public CatalogFilterPage(Page page) {
		this.page = page;
	}

	public void searchFilterValue(String filterValue) {
		CatalogFilterPageUtils.searchFilterValue(page, filterValue);
	}

	public void selectFilterValue(String filterCategory, String filterValue) {
		CatalogFilterPageUtils.selectFilterValue(page, filterCategory, filterValue);
	}

	public boolean verifyCatalogIsVisibleOnCatalogPage(String catalogName) {
		return CatalogFilterPageUtils.verifyCatalogIsVisibleOnCatalogPage(page, catalogName);
	}
}
