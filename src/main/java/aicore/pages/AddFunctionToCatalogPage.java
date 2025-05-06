package aicore.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class AddFunctionToCatalogPage {
	private Page page;
	private static final String ADD_FUNCTION_BUTTON = "Navigate to import Function";
	private static final String FUNCTION_SECTION_NAME_XPATH = "//div[text()='{sectionName}']";
	private static final String DATABASE_OPTIONS_UNDER_SECTION_XPATH = "//div[text()='{sectionName}']/following-sibling::div//p[text()='{optionName}']";
	private static final String ICONS_XPATH = "//p[text()='{optionName}']/parent::div//img";

	public AddFunctionToCatalogPage(Page page) {
		this.page = page;
	}

	public void clickOnAddFunctionButton() {
		page.getByLabel(ADD_FUNCTION_BUTTON).isVisible();
		page.getByLabel(ADD_FUNCTION_BUTTON).click();
	}

	public boolean isSearchBarPresent() {
		return page.getByPlaceholder("Search").isVisible();
	}

	public boolean verifySectionIsVisible(String sectionName) {
		boolean isSectionVisible = page.isVisible(FUNCTION_SECTION_NAME_XPATH.replace("{sectionName}", sectionName));
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
}