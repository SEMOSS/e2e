package aicore.pages.app.settings;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class AppAccessControlPageUtils {
	public static final String DELETE_APP_CONFIRMATION_BUTTON_XPATH = "//button[text()='{name}']";
	public static final String MAKE_PUBLIC_BUTTON_XPATH = "//span[contains(@class,'MuiSwitch-root MuiSwitch')]//input[@type='checkbox']";
	private static final String MAKE_DISCOVERABLE_BUTTON_DATATESTID = "settingsTiles-{appName}-makeDiscoverable-switch";

	public static void makeAppPublic(Page page) {
		page.locator(MAKE_PUBLIC_BUTTON_XPATH).isVisible();
		page.locator(MAKE_PUBLIC_BUTTON_XPATH).click();
	}
	
	public static void clickOnMakeDiscoverableButtoninSettings(Page page, String appName) {
		if (appName.contains(" ")) {
			appName = appName.replace(" ", "-");
		}
		Locator makeDiscoverableButton = page
				.getByTestId(MAKE_DISCOVERABLE_BUTTON_DATATESTID.replace("{appName}", appName));
		makeDiscoverableButton.isVisible();
		makeDiscoverableButton.click();
	}
	
	public static void clickOnDeleteButton(Page page, String buttonName) {
		Locator deleteButton = page.locator(DELETE_APP_CONFIRMATION_BUTTON_XPATH.replace("{name}", buttonName));
		deleteButton.isVisible();
		deleteButton.click();
	}

}
