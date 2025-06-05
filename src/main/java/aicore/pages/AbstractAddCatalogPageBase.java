package aicore.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import aicore.utils.AbstractAddCatalogPageBaseUtils;

public abstract class AbstractAddCatalogPageBase {

	protected Page page;

	public boolean verifySectionIsVisible(String sectionName) {
		return AbstractAddCatalogPageBaseUtils.verifySectionIsVisible(page, sectionName);
	}

	public boolean verifyOptionIsVisible(String sectionName, String optionName) {
		return AbstractAddCatalogPageBaseUtils.verifyOptionIsVisible(page, sectionName, optionName);
	}

	public Locator getIconByLabel(String sectionName, String optionName) {
		return AbstractAddCatalogPageBaseUtils.getIconByLabel(page, sectionName, optionName);
	}

	public boolean isIconVisible(String sectionName, String optionName) {
		return AbstractAddCatalogPageBaseUtils.isIconVisible(page, sectionName, optionName);
	}

	public boolean isSearchBarPresent() {
		return AbstractAddCatalogPageBaseUtils.isSearchBarPresent(page);
	}

}
