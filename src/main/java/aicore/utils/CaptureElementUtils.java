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
	private static final String ELEMENT_TEXT_NAME = "//*[text()='{elementText}']";
	private static final String BLOCK_TITLE_NAME = "//*[@title='{blockTitle}']";
	private static final String PROMPT_CONTEXT_NAME = "(//*[text()='{elementName}']//../..)[1]";
	private static final String BLOCK_SETTING_ELEMENT_XPATH = "//p[text()='{blockName}']/../../../..";
	private static final String SECTION_XPATH = "//h6[normalize-space()='{sectionName}']/ancestor::div[contains(@class,'MuiGrid-item')]//ul[@role='tree']";
	private static final String BLOCK_SECTION_XPATH = "//div[normalize-space()='{blockSection}']/ancestor::div[contains(@class,'MuiGrid-item')]";
	private static final String CARD_SECTION_XPATH = "//h3[text()='{elementName}']/ancestor::div[@data-slot='card']";
	private static final String TILE_XPATH = "//span[contains(normalize-space(),'{tileName}')]/../../.. | //h3[contains(normalize-space(),'{tileName}')]/../../..";
	private static final String FULL_SECTION_XPATH = "//div[@class='{sectionName}']";
	private static final String BUTTON_TEXT_XPATH = "//span[normalize-space()='{elementName}']/..";
	private static final String CLASS_ELEMENT_XPATH = "//*[contains(@class,'{className}')]/..";
	private static final String ROLE_ELEMENT_XPATH = "//*[@role='{role}']/../../../..";

	public static List<Locator> getLocatorsForTypeAndName(Page page, String elementType, String elementName) {
		Locator locator = null;
		switch (elementType.toLowerCase()) {
		case "button":
			locator = page.locator(CTA_ELEMENT_XPATH.replace("{ButtonName}", elementName));
			break;
		case "tab":
			locator = page.getByRole(AriaRole.TAB, new Page.GetByRoleOptions().setName(elementName).setExact(true));
			locator.scrollIntoViewIfNeeded();
			locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
			break;
		case "list item":
			locator = page.locator(LIST_ITEM_ELEMENT_XPATH.replace("{Element}", elementName));
			break;
		case "tile":
			locator = page.locator(TILE_XPATH.replace("{tileName}", elementName));
			break;
		case "heading":
			locator = page.locator("//h6[contains(normalize-space(),'" + elementName + "')]");
			break;
		case "searchbar":
			locator = page.locator(SEARCH_ELEMENT_XPATH.replace("{ButtonName}", elementName));
			break;
		case "copycta":
			locator = page.locator(COPY_ELEMENT_XPATH.replace("{ButtonName}", "Copy"));
			break;
		case "copyid":
			locator = page.locator(COPY_ID_TEXT_XPATH.replace("{copyid}", elementName));
			break;
		case "block":
			locator = page.locator(BLOCK_ELEMENT_XPATH.replace("{blockName}", elementName));
			break;
		case "buttontype":
			locator = page.locator(BUTTON_TYPE_XPATH.replace("{buttonType}", elementName));
			break;
		case "apptypetile":
			locator = page.locator(APP_TYPE_TAB_XPATH.replace("{appTypeName}", elementName));
			break;
		case "usetemplatebutton":
			locator = page.locator(USE_TEMPLATE_TAB_XPATH.replace("{templateName}", elementName));
			break;
		case "testidelement":
			locator = page.getByTestId(DATATESTID_NAME.replace("{dataTestIdName}", elementName));
			break;
		case "blocksettingelement":
			locator = page.locator(BLOCK_SETTING_ELEMENT_XPATH.replace("{blockName}", elementName));
			break;
		case "section":
			locator = page.locator(SECTION_XPATH.replace("{sectionName}", elementName));
			break;
		case "layerblock":
			locator = page.locator(DATATESTID_LAYER_NAME.replace("{layerName}", elementName));
			break;
		case "blocktitle":
			locator = page.locator(BLOCK_TITLE_NAME.replace("{blockTitle}", elementName));
			break;
		case "text":
			locator = page.locator(ELEMENT_TEXT_NAME.replace("{elementText}", elementName));
			break;
		case "blocksection":
			locator = page.locator(BLOCK_SECTION_XPATH.replace("{blockSection}", elementName));
			break;
		case "promptcontext":
			locator = page.locator(PROMPT_CONTEXT_NAME.replace("{elementName}", elementName));
			break;
		case "label":
			locator = page.getByLabel(elementName);
			break;
		case "catalogformpage":
			locator = page.getByTestId(elementName).locator("div").first();
			break;
		case "card":
			locator = page.locator(CARD_SECTION_XPATH.replace("{elementName}", elementName));
			break;
		case "fullsection":
			locator = page.locator(FULL_SECTION_XPATH.replace("{sectionName}", elementName));
			break;
		case "buttontext":
			locator = page.locator(BUTTON_TEXT_XPATH.replace("{elementName}", elementName));
			break;
		case "class":
			locator = page.locator(CLASS_ELEMENT_XPATH.replace("{className}", elementName));
			break;
		case "role":
			locator = page.locator(ROLE_ELEMENT_XPATH.replace("{role}", elementName));
			break;
		default:
			throw new IllegalArgumentException("Unsupported element type: " + elementType);
		}

		List<Locator> locators = collectAllLocators(locator);

		// Fallback logic for special cases
		if ("button".equals(elementType.toLowerCase()) && locators.isEmpty()) {
			Locator alt = page.locator("//*[contains(@aria-label,'" + elementName + "')]");
			locators.addAll(collectAllLocators(alt));
		} else if ("tile".equals(elementType.toLowerCase()) && locators.isEmpty()) {
			Locator alt = page.locator("//p[contains(normalize-space(),'" + elementName + "')]");
			locators.addAll(collectAllLocators(alt));
		} else if ("searchbar".equals(elementType.toLowerCase()) && locators.isEmpty()) {
			Locator alt = page.locator("//input[contains(@placeholder,'" + elementName + "')]");
			locators.addAll(collectAllLocators(alt));
		}

		return locators;
	}

	private static List<Locator> collectAllLocators(Locator locator) {
		List<Locator> locators = new ArrayList<>();
		int count = locator.count();
		for (int i = 0; i < count; i++) {
			locators.add(locator.nth(i));
		}
		return locators;
	}
}