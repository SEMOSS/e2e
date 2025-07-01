package aicore.utils;

import com.microsoft.playwright.Page;

public class CatlogAccessPage {

	private static final String VIEW_OVERVIEW_TAB_XPATH = "//button[contains(@class, 'MuiTab-root') and text()='Overview']";
	private static final String VIEW_USAGE_TAB_XPATH = "//button[contains(@class, 'MuiTab-root') and text()='Usage']";
	private static final String VIEW_SMSS_TAB_XPATH = "//button[contains(@class, 'MuiTab-root') and text()='SMSS']";
	private static final String VIEW_EDIT_SMSS_BUTTON_XPATH = "//span[text()='Edit SMSS']";
	// private static final String VIEW_ACCESSCONTROL_TAB_XPATH =
	// "//button[contains(@class, 'MuiTab-root') and text()='Access Control']";
	// new
	private static final String VIEW_ACCESSCONTROL_Text = "Access Control";
	private static final String VIEW_METADATA_TAB_Text = "Metadata";
	private static final String CLICK_ON_SEARCH_ICON_XPATH = "//div[@class='css-1hk1ec8']//div[@class='css-70qvj9']";
	private static final String SEARCH_MEMBER_PLACEHOLDER_TEXT = "Search Members";
	private static final String EXPORT_OPTION_TEXT = "//span[text()='Export']";

	public static boolean canViewOverview(Page page) {
		return page.isVisible(VIEW_OVERVIEW_TAB_XPATH);
	}

	public static boolean canViewUsage(Page page) {
		return page.isVisible(VIEW_USAGE_TAB_XPATH);
	}

	public static boolean canViewSMSSDetails(Page page) {
		return page.isVisible(VIEW_SMSS_TAB_XPATH);
	}

	public static boolean canViewEditSMSS(Page page) {
		return page.isVisible(VIEW_EDIT_SMSS_BUTTON_XPATH);
	}

	public static boolean canViewAccessControl(Page page) {
		return page.getByText(VIEW_ACCESSCONTROL_Text).isVisible();
		// return page.isVisible(VIEW_ACCESSCONTROL_TAB_XPATH);
	}

	// new
	public static boolean canViewMetadata(Page page) {
		return page.getByText(VIEW_METADATA_TAB_Text).isVisible();
	}

	public static void searchUserBasedOnRole(Page page, String role) {
		page.click(CLICK_ON_SEARCH_ICON_XPATH);
		page.getByPlaceholder(SEARCH_MEMBER_PLACEHOLDER_TEXT).fill(role);

	}

	public static boolean canViewExportOption(Page page) {
		page.waitForSelector(EXPORT_OPTION_TEXT);
		return page.locator(EXPORT_OPTION_TEXT).isVisible();

	}

}
