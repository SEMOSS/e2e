package aicore.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class AbstractAddCatalogPageBaseUtils {
	private static final String SECTION_NAME_XPATH = "//div[text()='{sectionName}']";
	private static final String OPTIONS_UNDER_SECTION_XPATH = "//div[text()='{sectionName}']/following-sibling::div//p[text()='{optionName}']";
	private static final String ICONS_XPATH = "//div[text()='{sectionName}']/following-sibling::div//p[text()='{optionName}']/parent::div//img";

	public static boolean verifySectionIsVisible(Page page, String sectionName) {
		boolean isSectionVisible = page.isVisible(SECTION_NAME_XPATH.replace("{sectionName}", sectionName));
		return isSectionVisible;
	}

	public static boolean verifyOptionIsVisible(Page page, String sectionName, String optionName) {
		boolean isOptionVisible = page.isVisible(
				OPTIONS_UNDER_SECTION_XPATH.replace("{sectionName}", sectionName).replace("{optionName}", optionName));
		return isOptionVisible;
	}

	public static Locator getIconByLabel(Page page, String sectionName, String optionName) {
		return page.locator(ICONS_XPATH.replace("{sectionName}", sectionName).replace("{optionName}", optionName));
	}

	public static boolean isIconVisible(Page page, String sectionName, String optionName) {
		return page.locator(ICONS_XPATH.replace("{sectionName}", sectionName).replace("{optionName}", optionName))
				.isVisible();
	}

	public static boolean isSearchBarPresent(Page page) {
		return page.getByPlaceholder("Search").isVisible();
	}

}
