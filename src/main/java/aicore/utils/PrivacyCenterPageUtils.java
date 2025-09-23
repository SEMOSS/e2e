package aicore.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

public class PrivacyCenterPageUtils {
	private static final String PRIVACY_CENTER_BUTTON_DATA_TESTID = "settingsLayout-privacy-btn";
	private static final String CLOSE_ICON_DATA_TESTID = "CloseIcon";
	private static final String PRIVACY_POPUP_XPATH = "//div[@role='dialog']";
	private static final String COOKIE_POLICY_LINK_XPATH = "//a[text()='Cookie policy']";
	private static final String CLOSE_BUTTON_XPATH = "//span[text()='Close']";
//	private static final String COOKIES_FIELD_XPATH = "//div[@class='cookie-details'][.//p[strong[text()='Name:'] and contains(.,'{cookieName}')]]//p[strong[text()='{fieldName}:']]";
//	private static final String COOKIES_FIELD_XPATH = "//div[@class='cookie-details'][.//p[strong[text()='Name:'] and text()='Name: {cookieName}']]//p[strong[text()='{fieldName}:']]";
	private static final String COOKIES_FIELD_XPATH = "//div[@class='cookie-details'][.//p[strong[text()='Name:'] and text()=' {cookieName}']]//p[strong[text()='{fieldName}:']]";

	public static void clickOnPrivacyCenterButton(Page page) {
		page.getByTestId(PRIVACY_CENTER_BUTTON_DATA_TESTID).click();
	}

	public static boolean isPrivacyPopupVisible(Page page) {
		return page.locator(PRIVACY_POPUP_XPATH).isVisible();
	}

	public static void waitForPopupClose(Page page) {
		page.locator(PRIVACY_POPUP_XPATH).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
	}

	public static void clickOnCloseIcon(Page page) {
		page.getByTestId(CLOSE_ICON_DATA_TESTID).click();
	}

	public static boolean isElemnetVisible(Page page, String element, String title) {
		Locator elementLocator;
		switch (element) {
		case "Popup":
			elementLocator = page.locator(PRIVACY_POPUP_XPATH);
			break;
		case "Cookie policy link":
			elementLocator = page.locator(COOKIE_POLICY_LINK_XPATH);
			break;
		case "Popup name":
		case "Title":
		case "Section1":
		case "Section2":
		case "Sub-section1":
		case "Sub-section2":
			elementLocator = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName(title));
			break;
		case "Close icon":
			elementLocator = page.getByTestId(CLOSE_ICON_DATA_TESTID);
			break;
		case "Close button":
			elementLocator = page.locator(CLOSE_BUTTON_XPATH);
			break;
		default:
			throw new IllegalArgumentException("Unknown element: " + element);
		}
		AICorePageUtils.waitFor(elementLocator);
		elementLocator.scrollIntoViewIfNeeded();
		return elementLocator.isVisible();
	}

	public static String getCookieFieldValue(Page page, String cookieName, String fieldName) {
		Locator fieldXpath;
		switch (fieldName.toLowerCase()) {
		case "name":
		case "host":
		case "duration":
		case "type":
		case "category":
		case "description":
			fieldXpath = page
					.locator(COOKIES_FIELD_XPATH.replace("{cookieName}", cookieName).replace("{fieldName}", fieldName));
			break;
		default:
			throw new IllegalArgumentException("Unknown field: " + fieldName);
		}
		return fieldXpath.innerText().replace(fieldName + ":", "").trim();
	}

	public static Page clickOnCookiePolicyLink(Page page) {
		return page.waitForPopup(() -> {
			Locator link = page.locator(COOKIE_POLICY_LINK_XPATH);
			AICorePageUtils.waitFor(link);
			link.click();
		});
	}
}
