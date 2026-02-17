package aicore.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

import aicore.framework.ConfigUtils;

public class CatlogAccessPageUtility {
	private static final Logger logger = LogManager.getLogger(CatlogAccessPageUtility.class);
	private static final String VIEW_OVERVIEW_TAB_XPATH = "engineLayout-Overview-tab";
	private static final String VIEW_USAGE_TAB_XPATH = "engineLayout-Usage-tab";
	private static final String VIEW_SMSS_TAB_XPATH = "engineLayout-SMSS-tab";
	private static final String VIEW_EDIT_SMSS_BUTTON_XPATH = "//*[@data-test-id='updateSMSS-updateSNSS-btn']";
	// new database catalog
	private static final String VIEW_ACCESSCONTROL_Text = "Access Control";
	private static final String VIEW_METADATA_TAB_Text = "Metadata";
	private static final String CLICK_ON_SEARCH_ICON_XPATH = "//h4[text()='Permissions']/parent::div/following-sibling::div//*[@data-testid=\"membersTable-searchIcon\"]";
	private static final String SEARCH_MEMBER_PLACEHOLDER_TEXT = "Search Members";
	private static final String EXPORT_OPTION_TEXT = "//button[text()='Export']";
	private static final String EDITOR_SEE_TOASTER_MESSAGE_DATATESTID = "notification-error-alert";
	private static final String CLICK_ON_CANCEL_BUTTON_XPATH = "//button[@type='button' and .//span[normalize-space(text())='Cancel']]";
	// create app variable declaration
	private static final String CLICK_ON_SETTINGS_DATATESTID = "workspace-Settings-image";
	private static final String CLICK_ON_DELETE_BUTTON_XPATH = "//button[text()='Delete']";
	private static final String CLICK_ON_CONFIRMATION_FOR_DELETEMODEL_XPATH = "//div[contains(@class, 'MuiDialogActions-root')]//button[.//span[text()='Delete']]";
	private static final String CLICK_ON_MEMBER_XPATH = "//span[contains(@class, 'MuiTypography-root') and contains(text(), 'Member')]";
	private static final String CIICK_ON_GENERAL_XPATH = "//span[contains(@class, 'MuiTypography-root') and contains(text(), 'General')]";
	private static final String CLICK_ON_DATA_APPS_XPATH = "//span[contains(@class, 'MuiTypography-root') and contains(text(), 'Apps')]";
	private static final String CLICK_ON_EXPORT_ICON_XAPTH = "//button[@aria-label='Export']//*[name()='svg']";
	private static final String MAKE_PRIAVTE_TOOGLE_ENABLE_XPATH = "//button[contains(@title,'public')]";
	private static final String MAKE_DISCOVRABLE_ENABLE_XAPTH = "//button[contains(@title,'discoverable')]";
	private static final String TOASTER_MEASSAGE_XAPTH = "//div[contains(@class,'MuiSnackbar-root')]//div[contains(@class,'MuiAlert-message')]";
	private static final String SEE_EDIT_OPTION_XPATH = "//span[normalize-space(text())='Edit']/ancestor::a[1]";
	private static final String CLICK_ON_COPYICON_DATATESTID = "ContentCopyOutlinedIcon";
	private static final String CATALOG_TYPE_XPATH = "//a[@data-slot='breadcrumb-link']";
	private static final String DISCOVERABLE_TOGGLE_OPTION_XPATH = "//button[contains(@data-testid,'settingsTiles') and contains(@data-testid,'makeDiscoverable-switch')]";
	private static final String PRIVATE_TOOGLE_OPTION_XPATH = "//button[contains(@data-testid,'settingsTiles') and contains(@data-testid,'private-switch')]";
	private static final String ADD_MEMBER_XPATH = "//input[@placeholder='Search users' and @type='text' and @role='combobox']";
	private static final String APP_SETTING_OPTION_XPATH = "//span[text()='Settings']";
	private static final String CATALOG_ID_XPATH = "//span[contains(@data-testid,'engineHeader')]";
	private static final String PENDING_REQUEST_ACCEPT_XPATH = "//*[name()='svg'][@data-testid='CheckIcon']";
	private static final String PENDING_REQUEST_REJECT_XPATH = "//*[name()='svg'][@data-testid='CloseIcon']";
	private static final String SEETING_OPTION_XPATH = "//div[@aria-label='{option}']";
	private static final String RIGHT_SIDE_OPEN_PAGE_XPATH = "//div[contains(@class,'flexlayout__tab_button_top')][.//div[normalize-space()='{pageName}']]";
	private static final String SETTING_SECTION_XPATH = "//h4[normalize-space()='{section}']";
	private static final String PUBLISH_ENABLE_TOGGLE_XPATH = "//div//p[normalize-space()='Enable the publishing of the portal.']/following::span[contains(@class,'Mui-checked')]//input[@type='checkbox']";
	private static final String CLICK_ON_PUBLISH_PORTAL_BUTTON_XPATH = "//button//span[normalize-space()='Publish']";
	private static final String SETTING_PAGE_APP_OPTION_XPATH = "//span[normalize-space()='{buttonName}']";
	private static final String GENERAL_SETTING_SECTION_XPATH = "//p[normalize-space()='{section}']";

	public static boolean canViewOverview(Page page) {
		return page.getByTestId(VIEW_OVERVIEW_TAB_XPATH).isVisible();
	}

	public static boolean canViewUsage(Page page) {
		return page.getByTestId(VIEW_USAGE_TAB_XPATH).isVisible();
	}

	public static boolean canViewSMSSDetails(Page page) {
		return page.getByTestId(VIEW_SMSS_TAB_XPATH).isVisible();
	}

	public static boolean canViewEditSMSS(Page page) {
		return page.locator(VIEW_EDIT_SMSS_BUTTON_XPATH).isVisible();
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
		Locator settingOption = page.getByTestId(CLICK_ON_SETTINGS_DATATESTID);
		AICorePageUtils.waitFor(settingOption);
		if (!settingOption.getAttribute("class").contains("flexlayout__border_button--selected")) {
			settingOption.click();
		}
	}

	public static boolean userCanSeeDeleteCatalog(Page page) {
		Locator deleteOption = page.locator(CLICK_ON_DELETE_BUTTON_XPATH);
		return deleteOption.isVisible();
	}

	public static boolean userCanSeeMember(Page page) {
		return page.locator(CLICK_ON_MEMBER_XPATH).isVisible();
	}

	public static boolean UserCanSeeGeneral(Page page) {
		return page.locator(CIICK_ON_GENERAL_XPATH).isVisible();
	}

	public static boolean userCanSeeApps(Page page) {
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

	public static boolean canSeeEditOption(Page page, String action) {
		Locator editButton = page.locator(SEE_EDIT_OPTION_XPATH);
		AICorePageUtils.waitFor(editButton);
		switch (action) {
		case "Enable":
			page.waitForTimeout(1000);
			return editButton.isEnabled();
		case "Disable":
			String ariaDisabled = editButton.getAttribute("aria-disabled");
			String classAttr = editButton.getAttribute("class");

			boolean isDisabled = ("true".equalsIgnoreCase(ariaDisabled))
					|| (classAttr != null && classAttr.contains("Mui-disabled"));
			return isDisabled;
		default:
			throw new IllegalArgumentException("Invalid action");
		}
	}

	public static boolean canSeeSettingOption(Page page) {
		return page.locator(APP_SETTING_OPTION_XPATH).isVisible();
	}

	public static String editorUserSeeToastMessageText(Page page) {
		page.getByTestId(EDITOR_SEE_TOASTER_MESSAGE_DATATESTID)
				.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		String toasterMessage = page.getByTestId(EDITOR_SEE_TOASTER_MESSAGE_DATATESTID).innerText();
		page.locator(CLICK_ON_CANCEL_BUTTON_XPATH).click();
		return toasterMessage;
	}

	public static void getCatalogAndCopyId(Page page) {
		Locator id = page.locator(CATALOG_ID_XPATH);
		id.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(60000));
		String copiedId = id.innerText();
		String catalogTypeText = page.innerText(CATALOG_TYPE_XPATH);
		String catalogType = catalogTypeText.trim().split("\\s+")[0];
		TestResourceTrackerHelper.getInstance().setCatalogId(catalogType, copiedId);
	}

	// as per new UI of setting page
	public static void clickOnMemberSettingOption(Page page) {
		page.locator(CLICK_ON_MEMBER_XPATH).click();
	}

	public static void clickOnGeneralSettingOption(Page page) {
		page.locator(CIICK_ON_GENERAL_XPATH).click();
	}

	// 🔹 Common reusable method

	public static void searchUser(Page page, String role, boolean useDocker) {
		Locator searchIcon = page.locator(CLICK_ON_SEARCH_ICON_XPATH);
		if (searchIcon.isVisible()) {
			searchIcon.click();
		}
		// Get username from config file (same as addMember)
		String username = ConfigUtils.getValue(role.toLowerCase() + "_username").split("@")[0];
		if (useDocker) {
			username = username + " lastname";
		}

		// Fill the search input
		page.getByPlaceholder(SEARCH_MEMBER_PLACEHOLDER_TEXT).fill(username);
		page.waitForTimeout(1000);

		// Optional: click the matching result if needed
		try {
			if (useDocker) {
				page.getByTitle("Name: " + username).click();
			} else {
				page.locator("//div[contains(text(),'" + username + "')]").first().click();
			}
		} catch (Exception e) {
			logger.info("User not clickable or not found: " + username);
		}
	}

	public static void clickOnPendingRequestTab(Page page) {
		page.getByTestId("pending-requests-count").click();
	}

	public static void acceptPendingRequest(Page page) {
		Locator acceptButton = page.locator(PENDING_REQUEST_ACCEPT_XPATH);
		AICorePageUtils.waitFor(acceptButton);
		acceptButton.click();
	}

	public static void rejectPendingRequest(Page page) {
		Locator rejectButton = page.locator(PENDING_REQUEST_REJECT_XPATH);
		AICorePageUtils.waitFor(rejectButton);
		rejectButton.click();
	}

	public static void clickOnSettingsOption(Page page, String option) {
		page.locator(SEETING_OPTION_XPATH.replace("{option}", option)).click();
	}

	public static boolean userSeeThePageOpenOnRightSidePanel(Page page, String pageName) {
		Locator rightSidePage = page.locator(RIGHT_SIDE_OPEN_PAGE_XPATH.replace("{pageName}", pageName));
		AICorePageUtils.waitFor(rightSidePage);
		return rightSidePage.isVisible();
	}

	public static boolean userCanSeeSectionUnderSetting(Page page, String section) {
		Locator sectionLocator = page.locator(SETTING_SECTION_XPATH.replace("{section}", section));
		if(!sectionLocator.isVisible()) {
			sectionLocator = page.locator("//h6[normalize-space()='" + section + "']");
		}
		return sectionLocator.isVisible();
	}

	public static boolean isPortalToggleInExpectedState(Page page, String action) {
		Locator enableToggle = page.locator(PUBLISH_ENABLE_TOGGLE_XPATH);
		AICorePageUtils.waitFor(enableToggle);
		boolean shouldEnable = action.equalsIgnoreCase("enable");
		boolean isCurrentlyEnabled = enableToggle.isVisible();
		if (isCurrentlyEnabled != shouldEnable) {
			enableToggle.click();
		}
		return enableToggle.isVisible() == shouldEnable;
	}

	public static boolean clickOnPublishPortalButton(Page page) {
		Locator publishButton = page.locator(CLICK_ON_PUBLISH_PORTAL_BUTTON_XPATH);
		if (publishButton.isEnabled()) {
			publishButton.click();
			return true;
		} else {
			return false;
		}

	}

	public static void clickOnAppSettingsOption(Page page, String buttonName) {
		page.locator(SETTING_PAGE_APP_OPTION_XPATH.replace("{buttonName}", buttonName)).click();
	}

	public static boolean userCanSeeSectionUnderGeneralSetting(Page page, String sectionName) {
		Locator sectionLocator = page.locator(GENERAL_SETTING_SECTION_XPATH.replace("{section}", sectionName));
		return sectionLocator.isVisible();
	}
}
