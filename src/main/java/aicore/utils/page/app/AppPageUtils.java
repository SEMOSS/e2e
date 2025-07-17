package aicore.utils.page.app;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import aicore.utils.AICorePageUtils;
import aicore.utils.CommonUtils;

public class AppPageUtils {

	public static final String CREATE_NEW_APP_DATA_TEST_ID = "home-create-app-btn";
	public static final String APP_CARD_XPATH = "//p[text()='{appName}']";
	public static final String OPEN_APP_LINK_XPATH = "//p[text()='{appName}']/ancestor::div[contains(@class,'MuiCardHeader-root')]/following-sibling::div//a";

	public static void clickOnCreateNewAppButton(Page page) {
		page.getByTestId(CREATE_NEW_APP_DATA_TEST_ID).click();
	}

	public static void searchApp(Page page, String appName, String timestamp) {
		page.getByLabel("Search").click();
		page.getByLabel("Search").fill(appName + " " + timestamp);
	}

	public static void clickOnAppCard(Page page, String appName, String timestamp) {
		String expectedAppName = appName + " " + timestamp;
		Locator appCard = page.locator((APP_CARD_XPATH.replace("{appName}", expectedAppName)));
		AICorePageUtils.waitFor(appCard);
		Locator anchor = page.locator(OPEN_APP_LINK_XPATH.replace("{appName}", expectedAppName));
		CommonUtils.removeTargetAttribute(anchor);
		appCard.click();
	}
}
