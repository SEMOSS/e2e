package aicore.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public abstract class AbstractAddCatalogPageBase {

	protected Page page;

	private static final String SECTION_NAME_XPATH = "//div[text()='{sectionName}']";
	private static final String DATABASE_OPTIONS_UNDER_SECTION_XPATH = "//div[text()='{sectionName}']/following-sibling::div//p[text()='{optionName}']";
	private static final String ICONS_XPATH = "//p[text()='{optionName}']/parent::div//img";

	public boolean verifySectionIsVisible(String sectionName) {
		boolean isSectionVisible = page.isVisible(SECTION_NAME_XPATH.replace("{sectionName}", sectionName));
		return isSectionVisible;
	}

	public boolean VerifyDatabaseOptionIsVisible(String sectionName, String databaseOptionName) {
		boolean isOptionVisible = page.isVisible(DATABASE_OPTIONS_UNDER_SECTION_XPATH
				.replace("{sectionName}", sectionName).replace("{optionName}", databaseOptionName));
		return isOptionVisible;
	}

	public Locator getIconByLabel(String optionName) {
		return page.locator(ICONS_XPATH.replace("{optionName}", optionName));
	}

	public boolean isIconVisible(String optionName) {
		return page.locator(ICONS_XPATH.replace("{optionName}", optionName)).isVisible();
	}

	public boolean isSearchBarPresent() {
		return page.getByPlaceholder("Search").isVisible();
	}

}
