package aicore.utils.page.app;

import com.microsoft.playwright.Page;

public class BISystemAppUtils {

	private static final String WELCOME_POPUP_CLOSE_XPATH = "//div[@class='smss-overlay__wrapper__header__times']";

	
	// catalog options
	private static final String CATALOG_OPTION_ID = "#home-nav__item--catalog";

	
	public static void closeWelcomePopup(Page page) {
		// accepting browser cookies
		try {
			page.getByText("Accept").click();
		} catch (Exception e) {
		}

		// welcome popup
		try {
			boolean popUp = page.locator(WELCOME_POPUP_CLOSE_XPATH).isVisible();
			if (popUp) {
				page.click(WELCOME_POPUP_CLOSE_XPATH);
			}
		} catch (Exception e) {
			// no popup
		}
	}
	
	public static void clickOnCatalogOption(Page page) {
		page.click(CATALOG_OPTION_ID);
	}
}
