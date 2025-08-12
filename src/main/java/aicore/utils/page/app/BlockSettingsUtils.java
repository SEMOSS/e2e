package aicore.utils.page.app;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import aicore.utils.AICorePageUtils;
import aicore.utils.CommonUtils;

public class BlockSettingsUtils {
	// Block settings for Text elements
	public static final String APP_SETTINGS_DATA_TEST_ID = "MenuIcon";
	private static final String BLOCK_SETTINGS_XPATH = "//div[@class='flexlayout__border_button_content' and text()='Block Settings']/parent::div";
	public static final String PERMISSION_SETTINGS_DATA_TEST_ID = "SettingsIcon";
	private static final String DESTINATION_TEXTBOX_XPATH = "//p[text()='Destination']/parent::div/following-sibling::div//div[contains(@class,'MuiInputBase-root')]//input[@type='text']";
	private static final String TEXT_TEXTBOX_XPATH = "//p[text()='Text']/parent::div/following-sibling::div//div[contains(@class,'MuiInputBase-root')]//input[@type='text']";
	private static final String FONT_LIST_XPATH = "//p[text()='Font']/parent::div/following-sibling::div//div[contains(@class,'MuiInputBase-root')]//input[@type='text']";
	private static final String COLOR_BOX_XPATH = "//input[@type='color']";
	private static final String MARKDOWN_TEXTBOX_XPATH = "//p[text()='Markdown']/parent::div/following-sibling::div//div[contains(@class,'MuiInputBase-root')]//input[@type='text']";
	private static final String QUERY_DROPDOWN_XPATH = "//input[@placeholder='Query']";

	// Block settings for charts
	private static final String DATA_TAB_XPATH = "//button[normalize-space()='Data']";
	private static final String DRAG_COLUMN_NAME_XPATH = "//div[@data-rbd-draggable-id='{columnName}']";
	private static final String DROP_FIELD_XPATH = "//span[normalize-space()= '{fieldName}']/parent::div/following-sibling::div";
	private static final String SEARCH_FRAME_PLACEHOLDER = "Select frame";
	private static final String DROPPED_COLUMN_IN_FIELD_XPATH = "//span[contains(normalize-space(), '{fieldName}')]/parent::div/following-sibling::div[contains(@id,'{columnName}')]";

	public static void clickOnBlockSettingsOption(Page page) {
		Locator blockSettingsOption = page.locator(BLOCK_SETTINGS_XPATH);
		if (!blockSettingsOption.getAttribute("class").contains("flexlayout__border_button--selected")) {
			blockSettingsOption.click();
		}
	}

	public static Locator clickOnAppSettingsOption(Page page) {
		// when settings is not open it uses this dataTestId
		Locator locator = page.getByTestId(APP_SETTINGS_DATA_TEST_ID);
		if (!page.getByTestId("MenuOpenIcon").isVisible()) {
			if (!locator.getAttribute("class").contains("flexlayout__border_button--selected")) {
				locator.click();
			}
		} else {
			// if the settings menu is open it uses this datatestId
			return page.getByTestId("MenuOpenIcon");
		}
		return locator;
	}

	public static Locator clickOnPermissionSettingsOption(Page page) {
		Locator locator = page.getByTestId(PERMISSION_SETTINGS_DATA_TEST_ID);
		locator.click();
		return locator;
	}

	public static void userSelectsTheAppearanceTab(Page page) {
		page.getByText("Appearance").click();
	}

	public static void enterDestination(Page page, String destination) {
		Locator loc = page.locator(DESTINATION_TEXTBOX_XPATH);
		loc.click();
		loc.fill(destination);
	}

	public static void enterText(Page page, String text) {
		Locator loc = page.locator(TEXT_TEXTBOX_XPATH);
		loc.click();
		loc.fill(text);
	}

	public static void enterMarkdown(Page page, String markdown) {
		Locator loc = page.locator(MARKDOWN_TEXTBOX_XPATH);
		loc.fill(markdown);
	}

	public static void selectTextStyle(Page page, String textStyles) {
		String[] textStyle = textStyles.split(", ");
		for (String style : textStyle) {
			page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(style.trim()).setExact(true)).click();
		}
	}

	public static void selectTextFont(Page page, String fontName) {
		page.locator(FONT_LIST_XPATH).click();
		page.locator(FONT_LIST_XPATH).fill(fontName);
		page.locator(FONT_LIST_XPATH).press("ArrowDown");
		page.locator(FONT_LIST_XPATH).press("Enter");
	}

	public static void selectTextColor(Page page, String hexColor) {
		page.locator(COLOR_BOX_XPATH).fill(hexColor);
	}

	public static void selectTextAlign(Page page, String textAlign) {
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(textAlign)).click();
	}

	public static void selectQueryFromList(Page page, String queryName) {
		page.locator(QUERY_DROPDOWN_XPATH).fill(queryName);
		page.locator(QUERY_DROPDOWN_XPATH).press("ArrowDown");
		page.locator(QUERY_DROPDOWN_XPATH).press("Enter");
	}
	// chart block settings

	public static void clickOnDataTab(Page page) {
		page.locator(DATA_TAB_XPATH).click();
	}

	public static void selectFrame(Page page, String frameId) {
		Locator selectFrame = page.getByPlaceholder(SEARCH_FRAME_PLACEHOLDER);
		AICorePageUtils.waitFor(selectFrame);
		selectFrame.click();
		selectFrame.fill(frameId);
		selectFrame.press("ArrowDown");
		selectFrame.press("Enter");
	}

	public static void dragColumnToTargetField(Page page, String columnName, String targetField) {
		// scroll to column
		Locator sourceLocator = page.locator(DRAG_COLUMN_NAME_XPATH.replace("{columnName}", columnName));
		AICorePageUtils.waitFor(sourceLocator);
		sourceLocator.evaluate("el => el.scrollIntoView({ block: 'center', behavior: 'instant' })");
		page.waitForTimeout(300);
		// Grab column
		sourceLocator.hover();
		CommonUtils.moveMouseToCenter(page, sourceLocator, 0);
		page.waitForTimeout(200);
		page.mouse().down();
		page.waitForTimeout(300);
		// scroll to target filed
		Locator targetLocator = page.locator(DROP_FIELD_XPATH.replace("{fieldName}", targetField)).first();
		AICorePageUtils.waitFor(targetLocator);
		targetLocator.evaluate("el => el.scrollIntoView({ block: 'center', behavior: 'instant' })");
		page.waitForTimeout(300);
		// refresh drag coordinates after scrolling
		CommonUtils.moveMouseToCenter(page, sourceLocator, 0);
		// drop column to target filed--
		targetLocator.scrollIntoViewIfNeeded();
		CommonUtils.moveMouseToCenter(page, targetLocator, 20);
		targetLocator.hover();
		page.waitForTimeout(300);
		page.mouse().up();
		page.waitForTimeout(800);
	}

	public static boolean verifyColumnDroppedInCorrectField(Page page, String columnName, String targetField) {
		Locator tag = page.locator(
				DROPPED_COLUMN_IN_FIELD_XPATH.replace("{fieldName}", targetField).replace("{columnName}", columnName));
		page.waitForTimeout(1000);
		return tag.isVisible();
	}

}
