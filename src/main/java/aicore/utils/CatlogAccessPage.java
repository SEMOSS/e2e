package aicore.utils;

import com.microsoft.playwright.Page;

public class CatlogAccessPage {

	private static final String VIEW_OVERVIEW_TAB_XPATH = "//button[contains(@class, 'MuiTab-root') and text()='Overview']";
	private static final String VIEW_USAGE_TAB_XPATH = "//button[contains(@class, 'MuiTab-root') and text()='Usage']";
	private static final String VIEW_SMSS_TAB_XPATH = "//button[contains(@class, 'MuiTab-root') and text()='SMSS']";
	private static final String VIEW_EDIT_SMSS_BUTTON_XPATH = "//span[text()='Edit SMSS']";
	// private static final String VIEW_ACCESSCONTROL_TAB_XPATH
	// =button[contains(@class, 'MuiTab-root') and text()='Access Control']";
	// new database catalog
	private static final String VIEW_ACCESSCONTROL_Text = "Access Control";
	private static final String VIEW_METADATA_TAB_Text = "Metadata";
	private static final String CLICK_ON_SEARCH_ICON_XPATH = "//div[@class='css-1hk1ec8']//div[@class='css-70qvj9']";
	private static final String SEARCH_MEMBER_PLACEHOLDER_TEXT = "Search Members";
	private static final String EXPORT_OPTION_TEXT = "//span[text()='Export']";

	// create app variable declaration
	private static final String CLICK_ON_SETTINGS_XPATH = "//div[@data-layout-path=\"/border/bottom/tb0\" and contains(@class, \"flexlayout__border_button\")]//div[text()=\"Settings\"]";
	private static final String CLICK_ON_DELETE_BUTTON_XPATH = "//span[text()='Delete']";
	private static final String CLICK_ON_CONFIRMATION_FOR_DELETEMODEL_XPATH = "//div[contains(@class, 'MuiDialogActions-root')]//button[.//span[text()='Delete']]";
	private static final String CLICK_ON_MEMBER_XPATH = "//button[contains(@class, 'MuiTab-root') and contains(text(), 'Member')]";
	private static final String CIICK_ON_PENDING_REQUESTS_XPATH = "//button[contains(@class, 'MuiTab-root') and contains(text(), 'Pending Requests')]";
	private static final String CLICK_ON_DATA_APPS_XPATH = "//button[contains(@class, 'MuiTab-root') and contains(text(), 'Data Apps')]";
	private static final String CLICK_ON_EXPORT_ICON_XAPTH = "//button[@aria-label='Export']//*[name()='svg']";
	private static final String MAKE_PUBLIC_TOOGLE_ENABLE_XPATH = "(//div[contains(., 'Make Public')]//span[contains(@class, 'MuiSwitch-track')])[1]";
	private static final String MAKE_DISCOVRABLE_ENABLE_XAPTH = "(//div[contains(., 'Make Public')]//span[contains(@class, 'MuiSwitch-track')])[2]";

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

	// create app class
	public static void clickOnSettings(Page page) {
		page.locator(CLICK_ON_SETTINGS_XPATH).click();
	}

	public static void userDeleteModel(Page page) {
		page.locator(CLICK_ON_DELETE_BUTTON_XPATH).click();
		page.locator(CLICK_ON_CONFIRMATION_FOR_DELETEMODEL_XPATH).click();
	}

	public static boolean user_Can_See_Member(Page page) {
		return page.locator(CLICK_ON_MEMBER_XPATH).isVisible();
	}

	public static boolean User_Can_See_PendingRequests(Page page) {
		return page.locator(CIICK_ON_PENDING_REQUESTS_XPATH).isVisible();
	}

	public static boolean user_Can_See_DataApps(Page page) {
		return page.locator(CLICK_ON_DATA_APPS_XPATH).isVisible();
	}

	public static boolean user_Can_See_ExportIcon(Page page) {
		return page.locator(CLICK_ON_EXPORT_ICON_XAPTH).isVisible();
	}

	public static void user_Can_See_And_Enable_Make_Public_Toggle(Page page) {
		boolean isEnable = page.locator(MAKE_PUBLIC_TOOGLE_ENABLE_XPATH).isEnabled();

	}
}
