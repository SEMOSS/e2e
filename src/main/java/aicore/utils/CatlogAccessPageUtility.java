package aicore.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CatlogAccessPageUtility {

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
	private static final String CLICK_ON_SETTINGS_XPATH = "//div[@data-layout-path='/border/bottom/tb0']";
	private static final String CLICK_ON_DELETE_BUTTON_XPATH = "//span[text()='Delete']";
	private static final String CLICK_ON_CONFIRMATION_FOR_DELETEMODEL_XPATH = "//div[contains(@class, 'MuiDialogActions-root')]//button[.//span[text()='Delete']]";
	private static final String CLICK_ON_MEMBER_XPATH = "//button[contains(@class, 'MuiTab-root') and contains(text(), 'Member')]";
	private static final String CIICK_ON_PENDING_REQUESTS_XPATH = "//button[contains(@class, 'MuiTab-root') and contains(text(), 'Pending Requests')]";
	private static final String CLICK_ON_DATA_APPS_XPATH = "//button[contains(@class, 'MuiTab-root') and contains(text(), 'Data Apps')]";
	private static final String CLICK_ON_EXPORT_ICON_XAPTH = "//button[@aria-label='Export']//*[name()='svg']";
	private static final String MAKE_PUBLIC_TOOGLE_ENABLE_XPATH = "(//div[contains(., 'Make Public')]//span[contains(@class, 'MuiSwitch-track')])[1]";
	private static final String MAKE_DISCOVRABLE_ENABLE_XAPTH = "(//div[contains(., 'Make Public')]//span[contains(@class, 'MuiSwitch-track')])[2]";
	private static final String TOASTER_MEASSAGE_XAPTH = "//div[contains(@class,'MuiSnackbar-root')]//div[contains(@class,'MuiAlert-message')]";
	private static final String ClICK_ON_MAKE_PUBLIC_TOGGLE_XAPTH = "(//div[contains(., 'Make Public')]//span[contains(@class, 'css-1mt6xn3-MuiButtonBase-root-MuiSwitch-switchBase')])[1]";
	private static final String CLICK_ON_MAKE_DISCOVRABLE_XPATH = "(//div[contains(., 'Make Public')]//span[contains(@class, 'css-1mt6xn3-MuiButtonBase-root-MuiSwitch-switchBase')])[2]";
	private static final String SEE_EDIT_OPTION_XPATH = "//span[text()='Edit']";

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
		// page.locator(CLICK_ON_SETTINGS_XPATH).click();
		// As per New UI
		Locator settingOption = page.locator(CLICK_ON_SETTINGS_XPATH);
		if (!settingOption.getAttribute("class").contains("flexlayout__border_button--selected")) {
			settingOption.click();
		}
	}

	public static boolean userCanSeeDeleteModel(Page page) {
		return page.locator(CLICK_ON_DELETE_BUTTON_XPATH).isVisible();
	}

	public static boolean userCanSeeMember(Page page) {
		return page.locator(CLICK_ON_MEMBER_XPATH).isVisible();
	}

	public static boolean UserCanSeePendingRequests(Page page) {
		return page.locator(CIICK_ON_PENDING_REQUESTS_XPATH).isVisible();
	}

	public static boolean userCanSeeDataApps(Page page) {
		return page.locator(CLICK_ON_DATA_APPS_XPATH).isVisible();
	}

	public static boolean userCanSeeExportIcon(Page page) {
		return page.locator(CLICK_ON_EXPORT_ICON_XAPTH).isVisible();
	}

	public static boolean userCanSeeAndEnableMakePublicToggle(Page page) {
		return page.locator(MAKE_PUBLIC_TOOGLE_ENABLE_XPATH).isVisible();
	}

	public static boolean setToggleStateForMakePublic(Page page, boolean expectedState) {
		Locator toggleCheckbox = page.locator("input.MuiSwitch-input[type='checkbox']").nth(0);
		// Get current state: if 'checked' attribute exists
		boolean isOn = toggleCheckbox.getAttribute("checked") != null;
		if (expectedState != isOn) {
			toggleCheckbox.click();
			for (int i = 0; i < 10; i++) {
				page.waitForTimeout(300);
				boolean updatedIsOn = toggleCheckbox.getAttribute("checked") != null;
				if (updatedIsOn == expectedState) {
					return true;
				}
			}
			return false; // toggle state didn't update
		}
		return true; // already in correct state
	}

	public static String getToasterMessage(Page page) {
		// Wait for toaster to appear and return text
		Locator toasterMessage = page.locator(TOASTER_MEASSAGE_XAPTH);
		toasterMessage.waitFor(new Locator.WaitForOptions().setTimeout(3000));
		return toasterMessage.textContent();
	}

	public static boolean user_Can_See_And_Enable_Make_Discoverable_Toggle(Page page) {
		return page.locator(MAKE_DISCOVRABLE_ENABLE_XAPTH).isVisible();
	}

	public static boolean setToggleStateForMakeDiscovrable(Page page, boolean expectedState) {
		Locator toggleCheckbox = page.locator("input.MuiSwitch-input[type='checkbox']").nth(1);
		// Get current state: if 'checked' attribute exists
		boolean isOn = toggleCheckbox.getAttribute("checked") != null;
		if (expectedState != isOn) {
			toggleCheckbox.click();
			for (int i = 0; i < 10; i++) {
				page.waitForTimeout(300);
				boolean updatedIsOn = toggleCheckbox.getAttribute("checked") != null;
				if (updatedIsOn == expectedState) {
					return true;
				}
			}
			return false; // toggle state didn't update
		}
		return true; // already in correct state
	}

	public static boolean canSeeEditOption(Page page) {
		return page.locator(SEE_EDIT_OPTION_XPATH).isEnabled();
	}

	public static boolean canSeeSettingOption(Page page) {
		return page.locator(CLICK_ON_SETTINGS_XPATH).isVisible();
	}
}
