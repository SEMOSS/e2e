package aicore.steps;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;

import com.microsoft.playwright.Locator;

import aicore.pages.AbstractAddCatalogPageBase;
import aicore.utils.CommonUtils;

public abstract class AbstractAddCatalogBase {

	protected void validateOptionsWithIcon(final String catalog, final String groupName, final String supportedOptions,
			List<Map<String, String>> rows, AbstractAddCatalogPageBase abstractCatalogPage) {
		for (Map<String, String> row : rows) {
			String sectionName = row.get(groupName);
			String expectedOptions = row.get(supportedOptions);
			// Verify section is visible
			boolean isSectionVisible = abstractCatalogPage.verifySectionIsVisible(catalog, sectionName);
			Assertions.assertTrue(isSectionVisible, sectionName + " section not visible");
			String[] expectedOptionsArray = expectedOptions.split(", ");
			for (String optionName : expectedOptionsArray) {
				// Verify option is visible
				boolean isOptionVisible = abstractCatalogPage.verifyOptionIsVisible(catalog, sectionName, optionName);
				Assertions.assertTrue(isOptionVisible, optionName + " option not visible");
				// Verify icon is visible
				Locator icon = abstractCatalogPage.getIconByLabel(catalog, sectionName, optionName);
				icon.waitFor();
				boolean isIconVisible = abstractCatalogPage.isIconVisible(catalog, sectionName, optionName);
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

	protected void validateSearchBar(AbstractAddCatalogPageBase abstractCatalogPage) {
		boolean isSearchBarVisible = abstractCatalogPage.isSearchBarPresent();
		Assertions.assertTrue(isSearchBarVisible, "Search bar not visible");
	}
}
