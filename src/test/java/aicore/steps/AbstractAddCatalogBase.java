package aicore.steps;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;

import com.microsoft.playwright.Locator;

import aicore.pages.AbstractAddCatalogPageBase;
import aicore.utils.CommonUtils;

public abstract class AbstractAddCatalogBase {

	protected abstract boolean isSearchBarPresent();

	protected void validateOptionsWithIcon(final String groupName, final String supportedOptions, List<Map<String, String>> rows,
			AbstractAddCatalogPageBase functionPage) {
		for (Map<String, String> row : rows) {
			String sectionName = row.get(groupName);
			String expectedOptions = row.get(supportedOptions);
			// Verify section is visible
			boolean isSectionVisible = functionPage.verifySectionIsVisible(sectionName);
			Assertions.assertTrue(isSectionVisible, sectionName + "section not visible");

			String[] expectedOptionsArray = expectedOptions.split(", ");
			for (String optionName : expectedOptionsArray) {
				// Verify option is visible
				boolean isOptionVisible = functionPage.VerifyDatabaseOptionIsVisible(sectionName, optionName);
				Assertions.assertTrue(isOptionVisible, optionName + "option not visible");
				// Verify icon is visible
				Locator icon = functionPage.getIconByLabel(optionName);
				icon.waitFor();
				boolean isIconVisible = functionPage.isIconVisible(optionName);
				Assertions.assertTrue(isIconVisible, optionName + " icon is not visible");
				// verify icon is not broken
				String iconUrl = icon.getAttribute("src");
				// for 'Local File System' storage option getting broken image
				if (isIconVisible && !optionName.contains("Local File System")) {
					boolean isIconValid = CommonUtils.isIconValid(iconUrl);
					Assertions.assertTrue(isIconValid, optionName + " icon src is broken: " + iconUrl);
				}
			}
		}
	}

	protected void validateSearchBar() {
		boolean isSearchBarVisible = isSearchBarPresent();
		Assertions.assertTrue(isSearchBarVisible, "Search bar not visible");
		// System.out.println("Search bar is present");
	}

}
