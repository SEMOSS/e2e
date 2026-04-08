package aicore.pages.app;

import com.microsoft.playwright.Page;

import aicore.utils.AICorePageUtils;

public class AppSettingsPageUtils {

	public static void clickOnAccessControlButton(Page page) {
		AICorePageUtils.clickOnTabButton(page, "Access Control");
	}
}
