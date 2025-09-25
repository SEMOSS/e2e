package aicore.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

public abstract class CaptureElementUtils {

	private static final String CTA_ELEMENT_XPATH = "//span[contains(normalize-space(),'{ButtonName}')]/..  | //button[contains(normalize-space(),'{ButtonName}')] ";
	private static final String LIST_ITEM_ELEMENT_XPATH = "//li[contains(normalize-space(),'{Element}')] | //p[contains(normalize-space(),'{Element}')]/..";
	private static final String SEARCH_ELEMENT_XPATH = "//label[contains(normalize-space(),'{ButtonName}')]";
	private static final String COPY_ELEMENT_XPATH = "(//span[contains(normalize-space(),'{ButtonName}')]/..) | (//button[contains(normalize-space(),'{ButtonName}')]) ";
	private static final String COPY_ID_TEXT_XPATH = "//*[contains(@aria-label,'{copyid}')]/../p";

	public static Locator captureButtonScreenshot(Page page, String buttonName) {
		Locator locator = page.locator(CTA_ELEMENT_XPATH.replace("{ButtonName}", buttonName)).first();
		page.waitForTimeout(500);
		if (!locator.isVisible()) {
			locator = page.locator("//*[contains(@aria-label,'" + buttonName + "')]");
		}
		return locator;
	}

	public static Locator captureTileScreenshot(Page page, String tileName) {
		Locator locator = page.locator("//span[contains(normalize-space(),'" + tileName + "')]/../../..");
		if (!locator.isVisible()) {
			locator = page.locator("//p[contains(normalize-space(),'" + tileName + "')]");
		}
		return locator;
	}

	public static Locator captureListItemScreenshot(Page page, String buttonName) {
		Locator locator = page.locator(LIST_ITEM_ELEMENT_XPATH.replace("{Element}", buttonName));
		return locator;
	}

	public static Locator captureHeadingScreenshot(Page page, String headingName) {
		Locator locator = page.locator("//h6[contains(normalize-space(),'" + headingName + "')]");
		return locator;
	}

	public static Locator captureSearchElementScreenshot(Page page, String buttonName) {
		Locator locator = page.locator(SEARCH_ELEMENT_XPATH.replace("{ButtonName}", buttonName));
		if (!locator.isVisible()) {
			locator = page.locator("//input[contains(@placeholder,'" + buttonName + "')]");
		}
		return locator;
	}

	public static Locator captureCopyCTAScreenshot(Page page, String buttonName) {
		Locator locator = page.locator(COPY_ELEMENT_XPATH.replace("{ButtonName}", buttonName)).nth(1);
		return locator;
	}

	public static Locator captureCopyIDScreenshot(Page page, String copyId) {
		Locator locator = page.locator(COPY_ID_TEXT_XPATH.replace("{copyid}", copyId));
		return locator;
	}

	public static Locator captureTabScreenshot(Page page, String elementName) {
		Locator locator = page.getByRole(AriaRole.TAB, new Page.GetByRoleOptions().setName(elementName).setExact(true));
		locator.scrollIntoViewIfNeeded();
		locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		return locator;
	}
}
