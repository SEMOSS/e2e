package aicore.utils.page.app;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import aicore.utils.AICorePageUtils;
import aicore.utils.CommonUtils;

public class AppPageUtils {
	public static final String CREATE_NEW_APP_DATA_TEST_ID = "home-create-app-btn";
	public static final String APP_SEARCH_TEXTBOX_XPATH = "//input[contains(@class,'MuiInputBase-input MuiOutlinedInput-input ') and @placeholder='Search']";

	public static void clickOnCreateNewAppButton(Page page) {
		page.getByTestId(CREATE_NEW_APP_DATA_TEST_ID).click();
	}

	public static void searchApp(Page page, String appName, String timestamp) {
		page.locator(APP_SEARCH_TEXTBOX_XPATH).fill(appName + " " + timestamp);
	}

	public static void clickOnSearchedApp(Page page, String appName, String timestamp) {
		// new search box
		Locator listbox = page.locator("ul.MuiAutocomplete-listbox");
		AICorePageUtils.waitFor(listbox);
		String expectedText = appName + " " + timestamp;
		Locator button = listbox.getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName(expectedText));
		AICorePageUtils.waitFor(button);
		Locator anchor = page.locator("//span[text()='" + expectedText + "']/ancestor::a");
		CommonUtils.removeTargetAttribute(anchor);
		button.click();
	}
}
