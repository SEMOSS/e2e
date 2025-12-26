package aicore.utils;

import java.util.ArrayList;
import java.util.List;

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
	private static final String BLOCK_ELEMENT_XPATH = "//*[contains(@data-block,'{blockName}')]/..";
	private static final String BUTTON_TYPE_XPATH = "//button[@type='{buttonType}']";
	private static final String APP_TYPE_TAB_XPATH = "//p[text()='{appTypeName}']/../../../../../../.. | //h6[text()='{appTypeName}']/../..";
	private static final String USE_TEMPLATE_TAB_XPATH = "//p[text()='{templateName}']/../../../../../following-sibling::div//button";
	private static final String DATATESTID_NAME = "{dataTestIdName}";
	private static final String DATATESTID_LAYER_NAME = "//*[contains(@data-id, '{layerName}')]/..";
	private static final String BLOCK_TITLE_NAME = "//*[@title='{blockTitle}']";
	private static final String BLOCK_SETTING_ELEMENT_XPATH = "//p[text()='{blockName}']/../../../..";
	private static final String SECTION_XPATH = "//h6[normalize-space()='{sectionName}']/ancestor::div[contains(@class,'MuiGrid-item')]//ul[@role='tree']";

	public static List<Locator> captureButtonScreenshot(Page page, String buttonName) {
		Locator locator = page.locator(CTA_ELEMENT_XPATH.replace("{ButtonName}", buttonName));
		List<Locator> locators = new ArrayList<>();
		int count = locator.count();
		for (int i = 0; i < count; i++) {
			Locator current = locator.nth(i);
			locators.add(current);
		}
		// Fallback: aria-label
		if (locators.isEmpty()) {
			Locator alt = page.locator("//*[contains(@aria-label,'" + buttonName + "')]");
			int altCount = alt.count();
			for (int i = 0; i < altCount; i++) {
				locators.add(alt.nth(i));
			}
		}
		return locators;
	}

	public static List<Locator> captureTileScreenshot(Page page, String tileName) {
		Locator locator = page.locator("//span[contains(normalize-space(),'" + tileName + "')]/../../..");
		List<Locator> locators = new ArrayList<>();
		int count = locator.count();
		for (int i = 0; i < count; i++) {
			locators.add(locator.nth(i));
		}
		// Fallback: paragraph match
		if (locators.isEmpty()) {
			Locator alt = page.locator("//p[contains(normalize-space(),'" + tileName + "')]");
			int altCount = alt.count();
			for (int i = 0; i < altCount; i++) {
				locators.add(alt.nth(i));
			}
		}
		return locators;
	}

	public static List<Locator> captureListItemScreenshot(Page page, String buttonName) {
		Locator locator = page.locator(LIST_ITEM_ELEMENT_XPATH.replace("{Element}", buttonName));
		List<Locator> locators = new ArrayList<>();
		int count = locator.count();
		for (int i = 0; i < count; i++) {
			locators.add(locator.nth(i));
		}
		return locators;
	}

	public static List<Locator> captureHeadingScreenshot(Page page, String headingName) {
		Locator locator = page.locator("//h6[contains(normalize-space(),'" + headingName + "')]");
		List<Locator> locators = new ArrayList<>();
		int count = locator.count();
		for (int i = 0; i < count; i++) {
			locators.add(locator.nth(i));
		}
		return locators;
	}

	public static List<Locator> captureSearchElementScreenshot(Page page, String buttonName) {
		Locator locator = page.locator(SEARCH_ELEMENT_XPATH.replace("{ButtonName}", buttonName));
		List<Locator> locators = new ArrayList<>();
		int count = locator.count();
		for (int i = 0; i < count; i++) {
			locators.add(locator.nth(i));
		}
		// Fallback: placeholder
		if (locators.isEmpty()) {
			Locator alt = page.locator("//input[contains(@placeholder,'" + buttonName + "')]");
			int altCount = alt.count();
			for (int i = 0; i < altCount; i++) {
				locators.add(alt.nth(i));
			}
		}
		return locators;
	}

	public static List<Locator> captureCopyCTAScreenshot(Page page, String buttonName) {
		Locator locator = page.locator(COPY_ELEMENT_XPATH.replace("{ButtonName}", buttonName));
		List<Locator> locators = new ArrayList<>();
		int count = locator.count();
		for (int i = 0; i < count; i++) {
			locators.add(locator.nth(i));
		}
		return locators;
	}

	public static List<Locator> captureCopyIDScreenshot(Page page, String copyId) {
		Locator locator = page.locator(COPY_ID_TEXT_XPATH.replace("{copyid}", copyId));
		List<Locator> locators = new ArrayList<>();
		int count = locator.count();
		for (int i = 0; i < count; i++) {
			locators.add(locator.nth(i));
		}
		return locators;
	}

	public static List<Locator> captureTabScreenshot(Page page, String elementName) {
		Locator locator = page.getByRole(AriaRole.TAB, new Page.GetByRoleOptions().setName(elementName).setExact(true));
		locator.scrollIntoViewIfNeeded();
		locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		List<Locator> locators = new ArrayList<>();
		int count = locator.count();
		for (int i = 0; i < count; i++) {
			locators.add(locator.nth(i));
		}
		return locators;
	}

	public static List<Locator> captureBlockScreenshot(Page page, String blockName) {
		Locator locator = page.locator(BLOCK_ELEMENT_XPATH.replace("{blockName}", blockName));
		List<Locator> locators = new ArrayList<>();
		int count = locator.count();
		for (int i = 0; i < count; i++) {
			locators.add(locator.nth(i));
		}
		return locators;
	}

	public static List<Locator> captureButtonTypeScreenshot(Page page, String buttonType) {
		Locator locator = page.locator(BUTTON_TYPE_XPATH.replace("{buttonType}", buttonType));
		List<Locator> locators = new ArrayList<>();
		int count = locator.count();
		for (int i = 0; i < count; i++) {
			locators.add(locator.nth(i));
		}
		return locators;
	}

	public static List<Locator> captureAppTypeTab(Page page, String appTypeName) {
		Locator locator = page.locator(APP_TYPE_TAB_XPATH.replace("{appTypeName}", appTypeName));
		List<Locator> locators = new ArrayList<>();
		int count = locator.count();
		for (int i = 0; i < count; i++) {
			locators.add(locator.nth(i));
		}
		return locators;
	}

	public static List<Locator> captureUseTemplate(Page page, String templateName) {
		Locator locator = page.locator(USE_TEMPLATE_TAB_XPATH.replace("{templateName}", templateName));
		List<Locator> locators = new ArrayList<>();
		int count = locator.count();
		for (int i = 0; i < count; i++) {
			locators.add(locator.nth(i));
		}
		return locators;
	}

	public static List<Locator> captureElementThroughtDataTestId(Page page, String dataTestId) {
		Locator locator = page.getByTestId(DATATESTID_NAME.replace("{dataTestIdName}", dataTestId));
		List<Locator> locators = new ArrayList<>();
		int count = locator.count();
		for (int i = 0; i < count; i++) {
			if (locator.nth(i).isVisible()) {
				locators.add(locator.nth(i));
			}
		}
		return locators;
	}
	public static List<Locator> captureElementThroughtDataId(Page page, String dataTestId) {
		Locator locator = page.locator(DATATESTID_LAYER_NAME.replace("{layerName}", dataTestId));
		List<Locator> locators = new ArrayList<>();
		int count = locator.count();
		for (int i = 0; i < count; i++) {
			if (locator.nth(i).isVisible()) {
				locators.add(locator.nth(i));
			}
		}
		return locators;
	}
	public static List<Locator> captureElementThroughTitle(Page page, String blockTitle) {
		Locator locator = page.locator(BLOCK_TITLE_NAME.replace("{blockTitle}", blockTitle));
		List<Locator> locators = new ArrayList<>();
		int count = locator.count();
		for (int i = 0; i < count; i++) {
			if (locator.nth(i).isVisible()) {
				locators.add(locator.nth(i));
			}
		}
		return locators;
	}

	public static List<Locator> captureBlockSettingElementScreenshot(Page page, String blockName) {
		Locator locator = page.locator(BLOCK_SETTING_ELEMENT_XPATH.replace("{blockName}", blockName));
		List<Locator> locators = new ArrayList<>();
		int count = locator.count();
		for (int i = 0; i < count; i++) {
			locators.add(locator.nth(i));
		}
		return locators;
	}

	public static List<Locator> captureSectionScreenshot(Page page, String layerName) {
		Locator locator = page.locator(SECTION_XPATH.replace("{sectionName}", layerName));
		List<Locator> locators = new ArrayList<>();
		int count = locator.count();
		for (int i = 0; i < count; i++) {
			locators.add(locator.nth(i));
		}
		return locators;
	}

}