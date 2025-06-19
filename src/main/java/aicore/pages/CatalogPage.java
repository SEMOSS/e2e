package aicore.pages;

import com.microsoft.playwright.Page;

public class CatalogPage extends AbstractSearchAndSelectCatalogPage {
	public CatalogPage(Page page) {
		this.page = page;
	}
}
