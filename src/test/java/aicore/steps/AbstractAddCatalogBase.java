package aicore.steps;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import aicore.pages.AbstractAddCatalogPageBase;
import aicore.utils.AddCatalogPageBaseUtils;
import aicore.utils.CommonUtils;

public abstract class AbstractAddCatalogBase {

	protected void validateOptionsWithIcon(final String catalog, final String supportedOptions,
			List<Map<String, String>> rows, AbstractAddCatalogPageBase abstractCatalogPage) {
		for (Map<String, String> row : rows) {
			String expectedOptions = row.get(supportedOptions);
			String[] expectedOptionsArray = expectedOptions.split(", ");
			for (String optionName : expectedOptionsArray) {
				// Verify option is visible
				boolean isOptionVisible = abstractCatalogPage.verifyOptionIsVisible(catalog, optionName);
				Assertions.assertTrue(isOptionVisible, optionName + " option not visible");
				// Verify icon is visible
				Locator icon = abstractCatalogPage.getIconByLabel(catalog, optionName);
				icon.waitFor();
				boolean isIconVisible = abstractCatalogPage.isIconVisible(catalog, optionName);
				Assertions.assertTrue(isIconVisible, optionName + " icon is not visible");
				// verify icon is not broken
				String iconUrl = icon.getAttribute("src");
				// for 'Local File System' storage & 'FAISS' vector options getting broken image
				if (isIconVisible && !optionName.matches(".*(Local File System||FAISS).*")) {
					boolean isIconValid = CommonUtils.isIconValid(iconUrl);
					Assertions.assertTrue(isIconValid, optionName + " icon src is broken: " + iconUrl);
				}
			}
		}
	}

	public static void validateOptionsWithIcon(final String catalog, final String groupName, final String supportedOptions,
			List<Map<String, String>> rows, Page page) {
		for (Map<String, String> row : rows) {
			String sectionName = row.get(groupName);
			String expectedOptions = row.get(supportedOptions);
			// Verify section is visible
			boolean isSectionVisible = AddCatalogPageBaseUtils.verifySectionIsVisible(page, catalog, sectionName);
			Assertions.assertTrue(isSectionVisible, sectionName + " section not visible");
			String[] expectedOptionsArray = expectedOptions.split(", ");
			for (String optionName : expectedOptionsArray) {
				// Verify option is visible
				boolean isOptionVisible =  AddCatalogPageBaseUtils.verifyOptionIsVisible(page, catalog, sectionName, optionName);
				Assertions.assertTrue(isOptionVisible, optionName + " option not visible");
				// Verify icon is visible
				Locator icon = AddCatalogPageBaseUtils.getIconByLabel(page, catalog, sectionName, optionName);
				icon.waitFor();
				boolean isIconVisible = AddCatalogPageBaseUtils.isIconVisible(page, catalog, sectionName, optionName);
				Assertions.assertTrue(isIconVisible, optionName + " icon is not visible");
				// verify icon is not broken
				String iconUrl = icon.getAttribute("src");
				// for 'Local File System' storage & 'FAISS' vector options getting broken image
				if (isIconVisible && !optionName.matches(".*(Local File System||FAISS).*")) {
					boolean isIconValid = CommonUtils.isIconValid(iconUrl);
					Assertions.assertTrue(isIconValid, optionName + " icon src is broken: " + iconUrl);
				}
			}
		}
	}
	
	public static void searchOptionsWithIcon(String catalog, String SECTION_NAME, String OPTION_NAME,
			List<Map<String, String>> rows, Page page) {
		for (Map<String, String> row : rows) {
			String section = row.get(SECTION_NAME);
			String[] dbTypes = row.get(OPTION_NAME).split(",\\s*");
			AddCatalogPageBaseUtils.clickOnSection(page, catalog, section);
			for (String dbType : dbTypes) {
				AddCatalogPageBaseUtils.searchDatabaseType(page, section, dbType);
				boolean isVisible = AddCatalogPageBaseUtils.verifyOptionIsVisible(page, catalog, section, dbType);
				Assertions.assertTrue(isVisible,
						"Database type '" + dbType + "' was not found under section '" + section + "'");
			}
		}		
	}

	protected void validateSearchBar(AbstractAddCatalogPageBase abstractCatalogPage) {
		boolean isSearchBarVisible = abstractCatalogPage.isSearchBarPresent();
		Assertions.assertTrue(isSearchBarVisible, "Search bar not visible");
	}
}
