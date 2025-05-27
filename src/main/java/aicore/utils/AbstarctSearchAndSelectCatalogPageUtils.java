package aicore.utils;

import com.microsoft.playwright.Page;

public abstract class AbstarctSearchAndSelectCatalogPageUtils {

	private static final String STORAGE_CATALOG_SEARCH_TEXTBOX_XPATH = "//input[@placeholder='Search']";
	private static final String SEARCHED_STORAGE_XPATH = "//p[text()='{catalogName}']";
	private static final String AUTHOR_OPTION_XPATH = "//div[contains(@class,'MuiCardHeader-content')]//div[contains(., 'Author')]/ancestor::div[contains(@class,'MuiCard-root')]//input[@type='radio']";
	private static final String EDITOR_OPTION_XPATH = "//div[contains(@class,'MuiCardHeader-content')]//div[contains(., 'Editor')]/ancestor::div[contains(@class,'MuiCard-root')]//input[@type='radio']";
	private static final String READONLY_OPTION_XPATH = "//div[contains(@class,'MuiCardHeader-content')]//div[contains(., 'Read-Only')]/ancestor::div[contains(@class,'MuiCard-root')]//input[@type='radio']";
	private static final String COMMENT_BOX_XPATH = "//textarea[not(@aria-hidden) and not(@readonly)]";
	private static final String CANCEL_BUTTON_XPATH = "//button[contains(., 'Cancel')]";
	private static final String REQUEST_BUTTON_XPATH = "//button[contains(., 'Request')]";

	public static void selectCatalogFromSearchOptions(Page page, String catalogName, String timestamp) {
		page.locator((SEARCHED_STORAGE_XPATH.replace("{catalogName}", catalogName + timestamp))).isVisible();
		page.locator(SEARCHED_STORAGE_XPATH.replace("{catalogName}", catalogName + timestamp)).click();
	}

	public static void searchCatalog(Page page, String catalogName, String timestamp) {
		page.locator(STORAGE_CATALOG_SEARCH_TEXTBOX_XPATH).click();
		page.locator(STORAGE_CATALOG_SEARCH_TEXTBOX_XPATH).fill(catalogName + timestamp);
	}

	public static boolean isOptionVisible(Page page, String option) {
		return switch (option.toLowerCase()) {
		case "author" -> page.locator(AUTHOR_OPTION_XPATH).isVisible();
		case "editor" -> page.locator(EDITOR_OPTION_XPATH).isVisible();
		case "read-only" -> page.locator(READONLY_OPTION_XPATH).isVisible();
		case "comment box" -> page.locator(COMMENT_BOX_XPATH).isVisible();
		case "cancel button" -> page.locator(CANCEL_BUTTON_XPATH).isVisible();
		case "request button" -> page.locator(REQUEST_BUTTON_XPATH).isVisible();
		default -> false;
		};
	}
}
