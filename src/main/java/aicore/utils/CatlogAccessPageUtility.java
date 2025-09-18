package aicore.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class CatlogAccessPageUtility {

	private static final String VIEW_OVERVIEW_TAB_XPATH = "//button[contains(@class, 'MuiTab-root') and text()='Overview']";
	private static final String VIEW_USAGE_TAB_XPATH = "//button[contains(@class, 'MuiTab-root') and text()='Usage']";
	private static final String VIEW_SMSS_TAB_XPATH = "//button[contains(@class, 'MuiTab-root') and text()='SMSS']";
	private static final String VIEW_EDIT_SMSS_BUTTON_XPATH = "//span[text()='Edit SMSS']";
	// new database catalog
	private static final String VIEW_ACCESSCONTROL_Text = "Access Control";
	private static final String VIEW_METADATA_TAB_Text = "Metadata";
	private static final String CLICK_ON_SEARCH_ICON_XPATH = "//h6[text()='Permissions']/parent::div/following-sibling::div//*[@data-testid='SearchIcon']";
	private static final String SEARCH_MEMBER_PLACEHOLDER_TEXT = "Search Members";
	private static final String EXPORT_OPTION_TEXT = "//span[text()='Export']";
	private static final String EDITOR_SEE_TOASTER_MESSAGE_XPATH = "//*[name()='svg' and @data-testid='ErrorOutlineIcon']/ancestor::div[contains(@class, 'MuiAlert-root')]//div[contains(@class, 'MuiAlert-message')]";
	private static final String CLICK_ON_CANCEL_BUTTON_XPATH = "//button[@type='button' and .//span[normalize-space(text())='Cancel']]";
	// create app variable declaration
	private static final String CLICK_ON_SETTINGS_XPATH = "//div[contains(@class,'flexlayout__border_button')][@title='Settings']";
	private static final String CLICK_ON_DELETE_BUTTON_XPATH = "//span[text()='Delete']";
	private static final String CLICK_ON_CONFIRMATION_FOR_DELETEMODEL_XPATH = "//div[contains(@class, 'MuiDialogActions-root')]//button[.//span[text()='Delete']]";
	private static final String CLICK_ON_MEMBER_XPATH = "//button[contains(@class, 'MuiTab-root') and contains(text(), 'Member')]";
	private static final String CIICK_ON_PENDING_REQUESTS_XPATH = "//button[contains(@class, 'MuiTab-root') and contains(text(), 'Pending Requests')]";
	private static final String CLICK_ON_DATA_APPS_XPATH = "//button[contains(@class, 'MuiTab-root') and contains(text(), 'Data Apps')]";
	private static final String CLICK_ON_EXPORT_ICON_XAPTH = "//button[@aria-label='Export']//*[name()='svg']";
	private static final String MAKE_PRIAVTE_TOOGLE_ENABLE_XPATH = "//span[contains(@title,'public')]";
	private static final String MAKE_DISCOVRABLE_ENABLE_XAPTH = "//span[contains(@title,'discoverable')]";
	private static final String TOASTER_MEASSAGE_XAPTH = "//div[contains(@class,'MuiSnackbar-root')]//div[contains(@class,'MuiAlert-message')]";
	private static final String SEE_EDIT_OPTION_XPATH = "//span[text()='Edit']";
	private static final String CLICK_ON_COPYICON_DATATESTID = "ContentCopyOutlinedIcon";
	private static final String CATALOG_TYPE_XPATH = "//a[@color='inherit']";
	private static final String DISCOVERABLE_TOGGLE_OPTION_XPATH = "//span[contains(@data-testid,'settingsTiles') and contains(@data-testid,'makeDiscoverable-switch')]//input[@type='checkbox']";
	private static final String PRIVATE_TOOGLE_OPTION_XPATH = "//span[contains(@data-testid,'settingsTiles') and contains(@data-testid,'private-switch')]//input[@type='checkbox']";

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
	}

	// new
	public static boolean canViewMetadata(Page page) {
		return page.getByText(VIEW_METADATA_TAB_Text).isVisible();
	}

	public static void searchUserBasedOnRole(Page page, String role) {
		Locator searchIcon = page.locator(CLICK_ON_SEARCH_ICON_XPATH);
		if (searchIcon.isVisible()) {
			searchIcon.click();
		}
		page.getByPlaceholder(SEARCH_MEMBER_PLACEHOLDER_TEXT).fill(role);
		page.waitForTimeout(1000);
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
		AICorePageUtils.waitFor(settingOption);
//		if (!settingOption.getAttribute("class").contains("flexlayout__border_button--selected")) {
//			settingOption.click();
//		}

		// we have issue with Settings so for now we are commenting if statement
		settingOption.click();
		
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

	public static boolean userCanSeeAndEnablePrivateToggle(Page page) {
		Locator privateToggle = page.locator(MAKE_PRIAVTE_TOOGLE_ENABLE_XPATH);
		return privateToggle.isVisible();
	}

	public static void setToggleStateForPrivate(Page page) {
		Locator toggleCheckboxForPrivate = page.locator(PRIVATE_TOOGLE_OPTION_XPATH);
		toggleCheckboxForPrivate.click();
	}

	public static String getToasterMessage(Page page) {
		// Wait for toaster to appear and return text
		Locator toasterMessage = page.locator(TOASTER_MEASSAGE_XAPTH);
		AICorePageUtils.waitFor(toasterMessage);
		return toasterMessage.textContent();
	}

	public static boolean userCanSeeAndEnableNonDiscoverableToggle(Page page) {
		return page.locator(MAKE_DISCOVRABLE_ENABLE_XAPTH).isVisible();
	}

	public static void setToggleStateForNonDiscovrable(Page page) {
		Locator toggleCheckbox = page.locator(DISCOVERABLE_TOGGLE_OPTION_XPATH);
		toggleCheckbox.click();
	}

	public static boolean canSeeEditOption(Page page) {
		return page.locator(SEE_EDIT_OPTION_XPATH).isEnabled();
	}

	public static boolean canSeeSettingOption(Page page) {
		return page.locator(CLICK_ON_SETTINGS_XPATH).isVisible();
	}

	public static String editorUserSeeToastMessageText(Page page) {
		page.locator(EDITOR_SEE_TOASTER_MESSAGE_XPATH)
				.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		String toasterMessage = page.locator(EDITOR_SEE_TOASTER_MESSAGE_XPATH).innerText();
		page.locator(CLICK_ON_CANCEL_BUTTON_XPATH).click();
		return toasterMessage;
	}

	public static boolean getCatalogAndCopyId(Page page) {
		Locator copyId = page.getByTestId(CLICK_ON_COPYICON_DATATESTID);
		AICorePageUtils.waitFor(copyId);
		copyId.click(new Locator.ClickOptions().setForce(true));
		Locator toastMessage = page.locator("//div[contains(text(),'Successfully copied ID')]");
		AICorePageUtils.waitFor(toastMessage);
		boolean isToastVisible = toastMessage.isVisible();
		String copiedId = (String) page.evaluate("() => navigator.clipboard.readText()");
		String catalogTypeText = page.innerText(CATALOG_TYPE_XPATH);
		String catalogType = catalogTypeText.trim().split("\\s+")[0];
		TestResourceTrackerHelper.getInstance().setCatalogId(catalogType, copiedId);
		return isToastVisible;
	}
}
