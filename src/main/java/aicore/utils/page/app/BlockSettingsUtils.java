package aicore.utils.page.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import aicore.utils.CommonUtils;
import aicore.utils.waitLayer.Waits;

public class BlockSettingsUtils {

	private static final Logger logger = LogManager.getLogger(BlockSettingsUtils.class);

	private static final String APP_SETTINGS_DATA_TEST_ID = "MenuIcon";
	private static final String BLOCK_SETTINGS_XPATH = "//div[@class='flexlayout__border_button_content' and text()='Block Settings']/parent::div";
	public static final String PERMISSION_SETTINGS_DATA_TEST_ID = "SettingsIcon";
	private static final String DESTINATION_TEXTBOX_XPATH = "//p[text()='Destination']/parent::div/following-sibling::div//div[contains(@class,'MuiInputBase-root')]//input[@type='text']";
	private static final String TEXT_TEXTBOX_XPATH = "//p[text()='Text']/parent::div/following-sibling::div//div[contains(@class,'MuiInputBase-root')]//input[@type='text']";
	private static final String FONT_LIST_XPATH = "//p[text()='Font']/parent::div/following-sibling::div//div[contains(@class,'MuiInputBase-root')]//input[@type='text']";
	private static final String COLOR_BOX_XPATH = "//input[@type='color']";
	private static final String MARKDOWN_TEXTBOX_XPATH = "//p[text()='Markdown']/parent::div/following-sibling::div//div[contains(@class,'MuiInputBase-root')]//input[@type='text']";
	private static final String QUERY_DROPDOWN_XPATH = "//span[@data-slot='select-value' and text()='Query']/ancestor::button[@role='combobox']";

	private static final String DATA_TAB_XPATH = "//button[normalize-space()='Data']";
	private static final String DRAG_COLUMN_NAME_XPATH = "//div[@data-rbd-draggable-id='{columnName}']";
	private static final String DROP_FIELD_XPATH = "//span[normalize-space()= '{fieldName}']/parent::div/following-sibling::div";
	private static final String SEARCH_FRAME_PLACEHOLDER = "Select frame";
	private static final String SELECT_FRAME_IN_NOTEBOOK_XPATH = "//button[contains(@class,'items-center')]//span[text()='Select Frame']";
	private static final String DROPPED_COLUMN_IN_FIELD_XPATH = "//span[contains(normalize-space(), '{fieldName}')]/parent::div/following-sibling::div[contains(@id,'{columnName}')]";
	private static final String OPTION_XPATH = "//p[text()='{optionName}']/../following-sibling::div//button";

	public static void clickOnBlockSettingsOption(Page page) {
		logger.info("Starting: clickOnBlockSettingsOption");
		Locator blockSettingsOption = page.locator(BLOCK_SETTINGS_XPATH);
		Waits.waitForElementVisible(blockSettingsOption);
		if (!blockSettingsOption.getAttribute("class").contains("flexlayout__border_button--selected")) {
			blockSettingsOption.click();
			page.waitForTimeout(1000);
		}
		logger.info("Completed: clickOnBlockSettingsOption");
	}

	public static Locator clickOnAppSettingsOption(Page page) {
		logger.info("Starting: clickOnAppSettingsOption");
		Locator locator = page.getByTestId(APP_SETTINGS_DATA_TEST_ID);
		Waits.waitForElementVisible(locator);
		if (!page.getByTestId("MenuOpenIcon").isVisible()) {
			if (!locator.getAttribute("class").contains("flexlayout__border_button--selected")) {
				locator.click();
			}
		} else {
			Locator openIcon = page.getByTestId("MenuOpenIcon");
			Waits.waitForElementVisible(openIcon);
			logger.info("Completed: clickOnAppSettingsOption - settings already open");
			return openIcon;
		}
		logger.info("Completed: clickOnAppSettingsOption");
		return locator;
	}

	public static Locator clickOnPermissionSettingsOption(Page page) {
		logger.info("Starting: clickOnPermissionSettingsOption");
		Locator locator = page.getByTestId(PERMISSION_SETTINGS_DATA_TEST_ID);
		Waits.waitForElementClickable(locator);
		locator.click();
		logger.info("Completed: clickOnPermissionSettingsOption");
		return locator;
	}

	public static void userSelectsTheAppearanceTab(Page page) {
		logger.info("Starting: userSelectsTheAppearanceTab");
		Locator appearanceTab = page.getByText("Appearance");
		Waits.waitForElementClickable(appearanceTab);
		appearanceTab.click();
		logger.info("Completed: userSelectsTheAppearanceTab");
	}

	public static void enterDestination(Page page, String destination) {
		logger.info("Starting: enterDestination with destination '{}'", destination);
		Locator loc = page.locator(DESTINATION_TEXTBOX_XPATH);
		Waits.waitForElementVisible(loc);
		loc.click();
		loc.fill(destination);
		logger.info("Completed: enterDestination with destination '{}'", destination);
	}

	public static void enterText(Page page, String text) {
		logger.info("Starting: enterText with text '{}'", text);
		Locator loc = page.locator(TEXT_TEXTBOX_XPATH);
		Waits.waitForElementVisible(loc);
		loc.click();
		loc.fill(text);
		logger.info("Completed: enterText with text '{}'", text);
	}

	public static void enterMarkdown(Page page, String markdown) {
		logger.info("Starting: enterMarkdown with markdown '{}'", markdown);
		Locator loc = page.locator(MARKDOWN_TEXTBOX_XPATH);
		Waits.waitForElementVisible(loc);
		loc.fill(markdown);
		logger.info("Completed: enterMarkdown with markdown '{}'", markdown);
	}

	public static void selectTextStyle(Page page, String textStyles) {
		logger.info("Starting: selectTextStyle with textStyles '{}'", textStyles);
		String[] textStyle = textStyles.split(", ");
		for (String style : textStyle) {
			Locator styleButton = page.getByRole(AriaRole.BUTTON,
					new Page.GetByRoleOptions().setName(style.trim()).setExact(true));
			Waits.waitForElementClickable(styleButton);
			styleButton.click();
		}
		logger.info("Completed: selectTextStyle with textStyles '{}'", textStyles);
	}

	public static void selectTextFont(Page page, String fontName) {
		logger.info("Starting: selectTextFont with fontName '{}'", fontName);
		Locator fontList = page.locator(FONT_LIST_XPATH);
		Waits.waitForElementVisible(fontList);
		fontList.click();
		fontList.fill(fontName);
		fontList.press("ArrowDown");
		fontList.press("Enter");
		logger.info("Completed: selectTextFont with fontName '{}'", fontName);
	}

	public static void selectTextColor(Page page, String hexColor) {
		logger.info("Starting: selectTextColor with hexColor '{}'", hexColor);
		Locator colorBox = page.locator(COLOR_BOX_XPATH);
		Waits.waitForElementVisible(colorBox);
		colorBox.fill(hexColor);
		logger.info("Completed: selectTextColor with hexColor '{}'", hexColor);
	}

	public static void selectTextAlign(Page page, String textAlign) {
		logger.info("Starting: selectTextAlign with textAlign '{}'", textAlign);
		Locator alignButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(textAlign));
		Waits.waitForElementClickable(alignButton);
		alignButton.click();
		logger.info("Completed: selectTextAlign with textAlign '{}'", textAlign);
	}

	

	public static void selectQueryFromList(Page page, String queryName) {
	    logger.info("Starting: selectQueryFromList with queryName '{}'", queryName);
	    Locator queryDropdown = page.locator(QUERY_DROPDOWN_XPATH);
	    Waits.waitForElementVisible(queryDropdown);
	    queryDropdown.click();
	    Locator option = page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(queryName));
	    Waits.waitForElementClickable(option);
	    option.click();
	    logger.info("Completed: selectQueryFromList with queryName '{}'", queryName);
	}


	public static void clickOnDataTab(Page page) {
		logger.info("Starting: clickOnDataTab");
		Locator dataTab = page.locator(DATA_TAB_XPATH);
		Waits.waitForElementClickable(dataTab);
		dataTab.click();
		logger.info("Completed: clickOnDataTab");
	}

	public static void selectFrame(Page page, String frameId) {
		logger.info("Starting: selectFrame with frameId '{}'", frameId);
		Locator trigger = page.locator("span[data-slot='select-value']")
				.filter(new Locator.FilterOptions().setHasText("Select frame"))
				.locator("xpath=ancestor::button | ancestor::*[@data-slot='select-trigger']")
				.first();
		if (trigger.count() == 0) {
			trigger = page.locator("span[data-slot='select-value']:has-text('Select frame')").first();
		}
		Waits.waitForElementVisible(trigger);
		trigger.scrollIntoViewIfNeeded();
		trigger.click();
		page.waitForTimeout(500);
		Locator option = page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(frameId));
		if (option.count() == 0) {
			option = page.locator("[role='option']").filter(new Locator.FilterOptions().setHasText(frameId));
		}
		Waits.waitForElementVisible(option.first(), Waits.SHORT_TIMEOUT);
		option.first().click();
		page.waitForTimeout(800);
		logger.info("Completed: selectFrame with frameId '{}'", frameId);
	}

	public static void dragColumnToTargetField(Page page, String columnName, String targetField) {
		logger.info("Starting: dragColumnToTargetField with columnName '{}' and targetField '{}'", columnName,
				targetField);
		Locator sourceLocator = page.locator(DRAG_COLUMN_NAME_XPATH.replace("{columnName}", columnName));
		Waits.waitForElementVisible(sourceLocator);
		sourceLocator.evaluate("el => el.scrollIntoView({ block: 'center', behavior: 'instant' })");
		page.waitForTimeout(300);
		sourceLocator.hover();
		CommonUtils.moveMouseToCenter(page, sourceLocator, 0);
		page.waitForTimeout(200);
		page.mouse().down();
		page.waitForTimeout(300);
		Locator targetLocator = page.locator(DROP_FIELD_XPATH.replace("{fieldName}", targetField)).first();
		Waits.waitForElementVisible(targetLocator);
		targetLocator.evaluate("el => el.scrollIntoView({ block: 'center', behavior: 'instant' })");
		page.waitForTimeout(300);
		CommonUtils.moveMouseToCenter(page, sourceLocator, 0);
		targetLocator.scrollIntoViewIfNeeded();
		CommonUtils.moveMouseToCenter(page, targetLocator, 20);
		targetLocator.hover();
		page.waitForTimeout(300);
		page.mouse().up();
		page.waitForTimeout(800);
		logger.info("Completed: dragColumnToTargetField with columnName '{}' and targetField '{}'", columnName,
				targetField);
	}

	public static boolean verifyColumnDroppedInCorrectField(Page page, String columnName, String targetField) {
		logger.info("Starting: verifyColumnDroppedInCorrectField with columnName '{}' and targetField '{}'",
				columnName, targetField);
		Locator tag = page.locator(
				DROPPED_COLUMN_IN_FIELD_XPATH.replace("{fieldName}", targetField).replace("{columnName}", columnName));
		page.waitForTimeout(1000);
		boolean isVisible = tag.isVisible();
		logger.info("Completed: verifyColumnDroppedInCorrectField - result: {}", isVisible);
		return isVisible;
	}

	public static void enterValueInGraphTD(Page page, String value) {
		logger.info("Starting: enterValueInGraphTD with value '{}'", value);
		Locator editor = page.locator(".monaco-editor");
		Waits.waitForElementVisible(editor);
		editor.click();
		// Locator inputArea = page.locator(".monaco-editor textarea.inputarea");
		// inputArea.click();
		// inputArea.press("Control+End");
		// inputArea.pressSequentially(value);
		page.keyboard().press("Control+End");
		page.keyboard().type(value);
		logger.info("Completed: enterValueInGraphTD with value '{}'", value);
	}

	public static void closeBlockSettings(Page page) {
		logger.info("Starting: closeBlockSettings");
		Locator blockSettingsOption = page.locator(BLOCK_SETTINGS_XPATH);
		Waits.waitForPageLoad(page);
		Waits.waitForElementVisible(blockSettingsOption);
		blockSettingsOption.click();
		logger.info("Completed: closeBlockSettings");
	}

	public static void clickOnOption(Page page, String option) {
		logger.info("Starting: clickOnOption with option '{}'", option);
		Locator optionLocator = page.locator(OPTION_XPATH.replace("{optionName}", option));
		Waits.waitForElementClickable(optionLocator);
		optionLocator.click();
		logger.info("Completed: clickOnOption with option '{}'", option);
	}
}
