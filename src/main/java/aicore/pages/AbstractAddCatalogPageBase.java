package aicore.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import aicore.utils.AddCatalogPageBaseUtils;

public abstract class AbstractAddCatalogPageBase {

	protected Page page;

	public boolean verifySectionIsVisible(String sectionName) {
		return AddCatalogPageBaseUtils.verifySectionIsVisible(page, sectionName);
	}

	public boolean verifyOptionIsVisible(String sectionName, String optionName) {
		return AddCatalogPageBaseUtils.verifyOptionIsVisible(page, sectionName, optionName);
	}

	public Locator getIconByLabel(String sectionName, String optionName) {
		return AddCatalogPageBaseUtils.getIconByLabel(page, sectionName, optionName);
	}

	public boolean isIconVisible(String sectionName, String optionName) {
		return AddCatalogPageBaseUtils.isIconVisible(page, sectionName, optionName);
	}

	public boolean isSearchBarPresent() {
		return AddCatalogPageBaseUtils.isSearchBarPresent(page);
	}

	// View Database Type on Connect To database page
	public void searchDatabaseType(String dbType) {
		AddCatalogPageBaseUtils.searchDatabaseType(page, dbType);
	}

	public boolean isDatabaseTypeVisiable(String dbType, String section) {
		return AddCatalogPageBaseUtils.isDatabaseTypeVisible(page, dbType, section);
	}

}
