package aicore.pages.function;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class FunctionAccessSettingsUtils {
	private static final String DELETE_BUTTON_XPATH = "//button[contains(@data-testid,'-delete-btn')]";
	private static final String CONFIRMATION_POPUP_XPATH = "//div[@data-slot='dialog-content']";
	private static final String CONFIRMATION_POPUP_DELETE_BUTTON_XPATH = "//button[contains(@data-testid,'confirmDelete-btn')]";
	private static final String DELETE_TOAST_MESSAGE = "//div[text()='{toastMessage}']";
	
	private static final String MAKE_DISCOVERABLE_BUTTON_DATATESTID = "settingsTiles-{catalogName}-makeDiscoverable-switch";
	
	public static void clickOnDeleteButton(Page page) {
		page.locator(DELETE_BUTTON_XPATH).isVisible();
		page.locator(DELETE_BUTTON_XPATH).click();
	}

	public static void clickOnDeleteConfirmationButton(Page page) {
		page.locator(CONFIRMATION_POPUP_XPATH)
				.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		page.locator(CONFIRMATION_POPUP_DELETE_BUTTON_XPATH).isVisible();
		page.locator(CONFIRMATION_POPUP_DELETE_BUTTON_XPATH).click();
	}

	public static String verifyDeleteToastMessage(Page page, String toastMessage) {
		return page.locator(DELETE_TOAST_MESSAGE.replace("{toastMessage}", toastMessage)).first().textContent();
	}
	
	public static void clickOnMakeDiscoverableButton(Page page, String catalogName) {
		Locator makeDiscoverableButton = page
				.getByTestId(MAKE_DISCOVERABLE_BUTTON_DATATESTID.replace("{catalogName}", catalogName));
		makeDiscoverableButton.isVisible();
		makeDiscoverableButton.click();
	}
}
