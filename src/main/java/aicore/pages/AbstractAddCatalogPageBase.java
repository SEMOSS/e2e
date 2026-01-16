package aicore.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import aicore.utils.AddCatalogPageBaseUtils;

public abstract class AbstractAddCatalogPageBase {

	protected Page page;

	public void clickOnSection(String catalog, String section) {
		AddCatalogPageBaseUtils.clickOnSection(page, catalog, section);
	}

	public boolean verifySectionIsVisible(String catalog, String sectionName) {
		return AddCatalogPageBaseUtils.verifySectionIsVisible(page, catalog, sectionName);
	}

	public boolean verifyOptionIsVisible(String catalog, String sectionName, String optionName) {
		return AddCatalogPageBaseUtils.verifyOptionIsVisible(page, catalog, sectionName, optionName);
	}

	public Locator getIconByLabel(String catalog, String sectionName, String optionName) {
		return AddCatalogPageBaseUtils.getIconByLabel(page, catalog, sectionName, optionName);
	}

	public boolean isIconVisible(String catalog, String sectionName, String optionName) {
		return AddCatalogPageBaseUtils.isIconVisible(page, catalog, sectionName, optionName);
	}

	public boolean isSearchBarPresent() {
		return AddCatalogPageBaseUtils.isSearchBarPresent(page);
	}

	// View Database Type on Connect To database page
	public void searchDatabaseType(String section, String dbType) {
		AddCatalogPageBaseUtils.searchDatabaseType(page, section, dbType);
	}
}
