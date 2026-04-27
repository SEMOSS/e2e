package aicore.pages.function;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import aicore.framework.ConfigUtils;
import aicore.pages.base.AbstractBasePage;
import aicore.utils.AICorePageUtils;

public class FunctionAccessSettingsUtils extends AbstractBasePage{
	private static final String DELETE_BUTTON_XPATH = "//button[contains(@data-testid,'-delete-btn')]";
	private static final String CONFIRMATION_POPUP_XPATH = "//div[@data-slot='dialog-content']";
	private static final String CONFIRMATION_POPUP_DELETE_BUTTON_XPATH = "//button[contains(@data-testid,'confirmDelete-btn')]";
	private static final String DELETE_TOAST_MESSAGE = "//div[text()='{toastMessage}']";
	// locate add members button by label until a test-id is added
	private static final String ADD_MEMBERS_BUTTON_LABEL = "Add Members";
	private static final String ADD_MEMBERS_MODAL_SEARCH_BAR_PLACEHOLDER = "Search by name or email...";
	private static final String ADD_MEMBERS_MODAL_INVITE_BUTTON_TEXT = "Invite";
	private static final String CHANGE_ACCESS_XPATH = "//button[text()='Change Access']";

	
	private static final String MAKE_DISCOVERABLE_BUTTON_DATATESTID = "settingsTiles-{catalogName}-makeDiscoverable-switch";
	
	public static void clickOnDeleteButton(Page page) {
		Locator locator = page.locator(DELETE_BUTTON_XPATH);
		waitAndClick(locator);
	}

	public static void clickOnDeleteConfirmationButton(Page page) {
		Locator confirmPopup = page.locator(CONFIRMATION_POPUP_XPATH);
		AICorePageUtils.waitFor(confirmPopup); /// wait for the confirmation popup to show before looking for confirm delete button
		Locator confirmDeleteLocator = page.locator(CONFIRMATION_POPUP_DELETE_BUTTON_XPATH);
		waitAndClick(confirmDeleteLocator);
	}

	public static String verifyDeleteToastMessage(Page page, String toastMessage) {
//		Locator locator = page.locator(DELETE_TOAST_MESSAGE.replace("{toastMessage}", toastMessage)).first();
//		return locator.textContent();
		return page.locator(DELETE_TOAST_MESSAGE.replace("{toastMessage}", toastMessage)).first().textContent();
	}
	
	public static void clickOnMakeDiscoverableButton(Page page, String catalogName) {
		Locator makeDiscoverableButton = page
				.getByTestId(MAKE_DISCOVERABLE_BUTTON_DATATESTID.replace("{catalogName}", catalogName));
		waitAndClick(makeDiscoverableButton);
	}
	
	public static void clickOnAddMembersForFunction(Page page) {
		Locator locator = page.getByText(ADD_MEMBERS_BUTTON_LABEL);
		waitAndClick(locator);
	}
	
	public static void searchAndAddMemberForFunction(Page page, String role) {
		String username = ConfigUtils.getValue(role.toUpperCase() + "_USERNAME").split("@")[0];
		Locator locator = page.getByPlaceholder(ADD_MEMBERS_MODAL_SEARCH_BAR_PLACEHOLDER);
		waitAndFill(locator, username);
		
		Locator memberButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(username));
		waitAndClick(memberButton);
		
		Locator inviteButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(ADD_MEMBERS_MODAL_INVITE_BUTTON_TEXT));
		waitAndClick(inviteButton);
	}
	
	public static void clickOnChangeAccessTab(Page page) {
		Locator locator = page.locator(CHANGE_ACCESS_XPATH);
		waitAndClick(locator);
	}
}
