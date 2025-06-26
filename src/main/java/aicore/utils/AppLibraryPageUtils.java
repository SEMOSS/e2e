package aicore.utils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Mouse;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.BoundingBox;
import com.microsoft.playwright.options.WaitForSelectorState;

public class AppLibraryPageUtils {
	
	public static final String CREATE_NEW_APP_BUTTON_XPATH = "//button[span[text()='Create New App']]";
	private static final String GET_STARTED_BUTTON_IN_DRAG_AND_DROP_XPATH = "//div[h6[text()='Drag and Drop']]/following-sibling::div/button[span[text()='Get Started']]";
	private static final String NAME_TEXTBOX_XPATH = "//div[contains(@class,'MuiFormControl-root MuiFormControl-fullWidth')]//label[text()='Name']";
	private static final String DESCRIPTION_TEXTBOX_XPATH = "//div[contains(@class,'MuiFormControl-root MuiTextField-root')]//label[text()='Description']";
	private static final String TAG_TEXTBOX_XPATH = "//input[contains(@placeholder,'to add tag') and @role='combobox']";
	private static final String CREATE_BUTTON_XPATH = "//button[span[text()='Create']]";
	private static final String PAGE_1_ID = "#page-1";
	private static final String PAGE_SELECTION_XPATH = "//div[@class='flexlayout__tab_button_content' and text()='{pageName}']";
	private static final String WELCOME_TEXT_BLOCK_XPATH = "//div[@id='page-1']//p[@data-block='welcome-text-block']";
	private static final String APP_SEARCH_TEXTBOX_XPATH = "//input[contains(@class,'MuiInputBase-input MuiOutlinedInput-input ') and @placeholder='Search']";
	private static final String SEARCHED_APP_XPATH = "//a[contains(@class,'MuiTypography-root MuiTypography-inherit')]//p[text()='{appName}']";
	private static final String EDIT_BUTTON_XPATH = "//a[span[text()='Edit']]";
	public static final String PREVIEW_APP_BUTTON_DATA_TEST_ID = "PlayArrowIcon";

	private static final String TERMINAL_XPATH = "//p[contains(text(),'Terminal')]";
	public static final String BROWSE_TEMPLATES_XPATH = "text=Browse Templates";

	// Blocks section
	private static final String BLOCKS_OPTION_XPATH = "//div[@class='flexlayout__border_button_content' and text()='Blocks']/parent::div";
	private static final String LINK_BLOCK_XPATH = "//div[@aria-label='Access a webpage through a clickable URL']";
	private static final String HEADING_1_BLOCK_XPATH = "//div[@aria-label='Display Text in header 1']";
	private static final String HEADING_2_BLOCK_XPATH = "//div[@aria-label='Display Text in header 2']";
	private static final String HEADING_3_BLOCK_XPATH = "//div[@aria-label='Display Text in header 3']";
	private static final String HEADING_4_BLOCK_XPATH = "//div[@aria-label='Display Text in header 4']";
	private static final String HEADING_5_BLOCK_XPATH = "//div[@aria-label='Display Text in header 5']";
	private static final String HEADING_6_BLOCK_XPATH = "//div[@aria-label='Display Text in header 6']";
	private static final String TEXT_BLOCK_XPATH = "//div[@aria-label='Show text in a regular paragraph style']";
	private static final String LOGS_BLOCK_XPATH = "//div[@aria-label='Show logs from the notebook']";
	private static final String MARKDOWN_BLOCK_XPATH = "//div[@aria-label='Show text in markdown format']";
	private static final String LINE_CHART_BLOCK_XPATH = "//div[text()='Line Chart']/parent::p/following-sibling::div[div[@aria-label='Show relationships between two variables']]";
	private static final String SCATTER_PLOT_BLOCK_XPATH = "//div[text()='Scatter Plot']/parent::p/following-sibling::div[div[@aria-label='Show relationships between two variables']]";
	private static final String BAR_CHART_BLOCK_XPATH = "//div[text()='Bar Chart']/parent::p/following-sibling::div[div[@aria-label='Compare cumulative totals and individual segments across categories']]";
	private static final String BAR_CHART_STACKED_BLOCK_XPATH = "//div[text()='Bar Chart - Stacked']/parent::p/following-sibling::div[div[@aria-label='Compare cumulative totals and individual segments across categories']]";
	private static final String HEADING_BLOCK_HELLO_WORLD_XPATH = "//h1[text()='Hello world']";
	private static final String MENU_OPTION_XPATH = "//button[contains(@class,'MuiButtonBase-root MuiIconButton-root MuiIconButton-edgeStart')]";
	private static final String MENU_CLOSED_ICON_XPATH = "//button[@aria-label='menu']//*[local-name()='svg' and @data-testid='MenuIcon']";
	private static final String APP_LOGO_ON_EDIT_PAGE_XPATH = "//h6[text()='{appName}']";
	private static final String LOGS_BLOCK_ON_PAGE_XPATH = "//div[contains(@data-block,'logs')]//span[text()='{logsText}']";
	private static final String CHART_XPATH = "//div[@class='echarts-for-react ']";
	// Block settings for Text elements
	private static final String BLOCK_SETTINGS_XPATH = "//div[@class='flexlayout__border_button_content' and text()='Block Settings']/parent::div";
	private static final String DESTINATION_TEXTBOX_XPATH = "//p[text()='Destination']/parent::div/following-sibling::div//div[contains(@class,'MuiInputBase-root')]//input[@type='text']";
	private static final String TEXT_TEXTBOX_XPATH = "//p[text()='Text']/parent::div/following-sibling::div//div[contains(@class,'MuiInputBase-root')]//input[@type='text']";
	private static final String FONT_LIST_XPATH = "//p[text()='Font']/parent::div/following-sibling::div//div[contains(@class,'MuiInputBase-root')]//input[@type='text']";
	private static final String COLOR_BOX_XPATH = "//input[@type='color']";
	private static final String MARKDOWN_TEXTBOX_XPATH = "//p[text()='Markdown']/parent::div/following-sibling::div//div[contains(@class,'MuiInputBase-root')]//input[@type='text']";
	private static final String QUERY_DROPDOWN_XPATH = "//input[@placeholder='Query']";
	private static final String SAVE_APP_BUTTON_NAME = "Save App (ctrl/command + s)";

	// Block settings for charts
	private static final String DATA_TAB_XPATH = "//button[normalize-space()='Data']";
	private static final String DRAG_COLUMN_NAME_XPATH = "//div[@data-rbd-draggable-id='{columnName}']";
	private static final String DROP_FIELD_XPATH = "//span[contains(normalize-space(), '{fieldName}')]/parent::div/following-sibling::div";
	private static final String SEARCH_FRAME_PLACEHOLDER = "Select frame";

	// Notebook section
	private static final String NOTEBOOK_OPTION_XPATH = "//div[@class='flexlayout__border_button_content' and text()='Notebooks']";
	private static final String CREATE_NEW_NOTEBOOK_DATA_TESTID = "NoteAddOutlinedIcon";
	private static final String QUERY_SUBMIT_BUTTON_XPATH = "//span[text()='Submit']";
	private static final String NOTEBOOK_QUERY_ID_LABEL = "Id";
	private static final String CODE_ENTER_TEXTAREA = ".monaco-editor textarea.inputarea";
	private static final String QUERY_CODE_RUN_OUTPUT_XPATH = "//div[contains(@id,'notebook-cell-actions')]/child::div/span[text()='{codeOutput}']";
	private static final String IMPORT_DATA_OPTIONS_XPATH = "//li[@value='{optionName}']";
	private static final String SELECT_DATABASE_DROPDOWN_XPATH = "//label[text()='Select Database']/following-sibling::div//div[@role='button']";
	private static final String SELECT_ALL_COLUMNS_XPATH = "(//tbody//tr)[1]//input[@type='checkbox']";
	private static final String LIST_OF_COLUMN_NAMES_XPATH = "//table[contains(@class, 'MuiTable-root')]//tbody//tr[position()>1]//td[2]";
	private static final String IMPORT_BUTTON_XPATH = "//span[text()='Import']";
	private static final String FRAME_CSS = "input[value*='FRAME_']";
	private static final String DELETE_CELL_DATA_TESTID = "DeleteIcon";

	public static void clickOnCreateNewAppButton(Page page) {
		page.locator(CREATE_NEW_APP_BUTTON_XPATH).click();
	}

	public static void clickOnGetStartedButtonInDragAndDrop(Page page, String appType) {
		page.locator(GET_STARTED_BUTTON_IN_DRAG_AND_DROP_XPATH.replace("{appType}", appType)).click();
	}

	public static String enterAppName(Page page, String appName, String timestamp) {
		String appNameTesting = appName + " " + timestamp;
		page.locator(NAME_TEXTBOX_XPATH).fill(appNameTesting);
		return appNameTesting;
	}

	public static void enterAppDescription(Page page, String appDescription) {
		page.locator(DESCRIPTION_TEXTBOX_XPATH).fill(appDescription);
	}

	public static void enterTags(Page page, String tagName) {
		page.locator(TAG_TEXTBOX_XPATH).fill(tagName);
		page.keyboard().press("Enter");
	}

	public static void clickOnCreateButton(Page page) {
		page.locator(CREATE_BUTTON_XPATH).click();
	}

	public static boolean verifyPage1IsVisible(Page page) {
		Locator element = page.locator(PAGE_1_ID);
		element.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		return element.isVisible();
	}

	public static boolean verifyWelcomeTextboxIsVisible(Page page) {
		boolean isWelcomeTextboxVisible = page.locator(WELCOME_TEXT_BLOCK_XPATH).isVisible();
		return isWelcomeTextboxVisible;
	}

	public static String verifyWelcomeText(Page page) {
		String actualWelcomeText = page.locator(WELCOME_TEXT_BLOCK_XPATH).textContent().trim();
		return actualWelcomeText;
	}

	public static void navigateToHomePageFromAppEditPage(Page page) {
		String appNameWithLogo = ConfigUtils.getValue("applicationName");
		if (page.locator(MENU_CLOSED_ICON_XPATH).isVisible()) {
			page.locator(MENU_OPTION_XPATH).click();
		}
		page.locator(APP_LOGO_ON_EDIT_PAGE_XPATH.replace("{appName}", appNameWithLogo)).click();
	}

	public static void searchApp(Page page, String appName, String timestamp) {
		page.locator(APP_SEARCH_TEXTBOX_XPATH).fill(appName + " " + timestamp);
	}

	public static void clickOnSearchedApp(Page page, String appName, String timestamp) {
		page.locator(SEARCHED_APP_XPATH.replace("{appName}", appName + " " + timestamp)).click();
	}

	public static void clickOnEditButton(Page page) {
		page.locator(EDIT_BUTTON_XPATH).click();
	}

	public static void clickOnBlocksOption(Page page) {
		Locator blocksOption = page.locator(BLOCKS_OPTION_XPATH);
		if (!blocksOption.getAttribute("class").contains("flexlayout__border_button--selected")) {
			blocksOption.click();
		}
	}

	public static void blockDropPosition(Page page) {
		Locator targetBox = page.locator(WELCOME_TEXT_BLOCK_XPATH);
		moveMouseToCenterWithMargin(page, targetBox, 0, 10);
		page.mouse().up();
	}

	public static void mouseHoverOnBlock(Page page, String blockName) {
		boolean isValidBlock = true;
		switch (blockName) {
		case "Text (h1)":
			page.locator(HEADING_1_BLOCK_XPATH).isVisible();
			page.locator(HEADING_1_BLOCK_XPATH).hover();
			break;
		case "Text (h2)":
			page.locator(HEADING_2_BLOCK_XPATH).isVisible();
			page.locator(HEADING_2_BLOCK_XPATH).hover();
			break;
		case "Text (h3)":
			page.locator(HEADING_3_BLOCK_XPATH).isVisible();
			page.locator(HEADING_3_BLOCK_XPATH).hover();
			break;
		case "Text (h4)":
			page.locator(HEADING_4_BLOCK_XPATH).isVisible();
			page.locator(HEADING_4_BLOCK_XPATH).hover();
			break;
		case "Text (h5)":
			page.locator(HEADING_5_BLOCK_XPATH).isVisible();
			page.locator(HEADING_5_BLOCK_XPATH).hover();
			break;
		case "Text (h6)":
			page.locator(HEADING_6_BLOCK_XPATH).isVisible();
			page.locator(HEADING_6_BLOCK_XPATH).hover();
			break;
		case "Text":
			page.locator(TEXT_BLOCK_XPATH).isVisible();
			page.locator(TEXT_BLOCK_XPATH).hover();
			break;
		case "Link":
			page.locator(LINK_BLOCK_XPATH).isVisible();
			page.locator(LINK_BLOCK_XPATH).hover();
			break;
		case "Markdown":
			page.locator(MARKDOWN_BLOCK_XPATH).isVisible();
			page.locator(MARKDOWN_BLOCK_XPATH).hover();
			break;
		case "Logs":
			page.locator(LOGS_BLOCK_XPATH).isVisible();
			page.locator(LOGS_BLOCK_XPATH).hover();
			break;
		case "Scatter Plot":
			page.locator(SCATTER_PLOT_BLOCK_XPATH).scrollIntoViewIfNeeded();
			page.locator(SCATTER_PLOT_BLOCK_XPATH).isVisible();
			page.locator(SCATTER_PLOT_BLOCK_XPATH).hover();
			break;
		case "Line Chart":
			page.locator(LINE_CHART_BLOCK_XPATH).scrollIntoViewIfNeeded();
			page.locator(LINE_CHART_BLOCK_XPATH).isVisible();
			page.locator(LINE_CHART_BLOCK_XPATH).hover();
			break;
		case "Bar Chart":
			page.locator(BAR_CHART_BLOCK_XPATH).scrollIntoViewIfNeeded();
			page.locator(BAR_CHART_BLOCK_XPATH).isVisible();
			page.locator(BAR_CHART_BLOCK_XPATH).hover();
			break;
		case "Bar Chart - Stacked":
			page.locator(BAR_CHART_STACKED_BLOCK_XPATH).scrollIntoViewIfNeeded();
			page.locator(BAR_CHART_STACKED_BLOCK_XPATH).isVisible();
			page.locator(BAR_CHART_STACKED_BLOCK_XPATH).hover();
			break;
		default:
			isValidBlock = false;
			System.out.println("Invalid block name: " + blockName);
		}
		if (isValidBlock) {
			page.mouse().down();
		}
	}

	public static String verifyHeadingBlockTextMessage(Page page) {
		String headingBlockTextMessage = page.locator(HEADING_BLOCK_HELLO_WORLD_XPATH).textContent().trim();
		return headingBlockTextMessage;
	}

	public static void clickOnBlockSettingsOption(Page page) {
		Locator blockSettingsOption = page.locator(BLOCK_SETTINGS_XPATH);
		if (!blockSettingsOption.getAttribute("class").contains("flexlayout__border_button--selected")) {
			blockSettingsOption.click();
		}
	}

	public static void userSelectsTheAppearanceTab(Page page) {
		page.getByText("Appearance").click();
	}

	public static void enterDestination(Page page, String destination) {
		page.locator(DESTINATION_TEXTBOX_XPATH).fill(destination);
	}

	public static void enterText(Page page, String text) {
		page.locator(TEXT_TEXTBOX_XPATH).fill(text);
	}

	public static void enterMarkdown(Page page, String markdown) {
		page.locator(MARKDOWN_TEXTBOX_XPATH).fill(markdown);
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

	public static void clickOnSaveAppButton(Page page) {
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(SAVE_APP_BUTTON_NAME).setExact(true))
				.click();
	}

	public static Locator textSectionDragAndDroppedBlockLocator(Page page, String blockName, String blockText) {
		Locator textBlockLocator = null;
		switch (blockName) {
		case "Link":
			textBlockLocator = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(blockText));
			break;
		case "Text":
			textBlockLocator = page.locator("p", new Page.LocatorOptions().setHasText(blockText));
			break;
		case "Markdown":
			textBlockLocator = page.locator("p", new Page.LocatorOptions().setHasText(blockText));
			break;
		case "Logs":
			textBlockLocator = page.locator(LOGS_BLOCK_ON_PAGE_XPATH.replace("{logsText}", blockText));
			break;
		default:
			textBlockLocator = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName(blockText));
			break;
		}
		return textBlockLocator;
	}

	public static String getBlockText(Page page, String blockName, String blockText) {
		textSectionDragAndDroppedBlockLocator(page, blockName, blockText)
				.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		return textSectionDragAndDroppedBlockLocator(page, blockName, blockText).textContent().trim();
	}

	public static String getBlockTextFont(Page page, String blockName, String blockText) {
		return textSectionDragAndDroppedBlockLocator(page, blockName, blockText)
				.evaluate("el => el.style.fontFamily || getComputedStyle(el).fontFamily").toString()
				.replaceAll("^\"|\"$", "");
	}

	public static String getBlockTextStyle(Page page, String blockName, String blockText) {
		return textSectionDragAndDroppedBlockLocator(page, blockName, blockText).evaluate("el => el.style.fontWeight")
				.toString();
	}

	public static String getBlockTextColor(Page page, String blockName, String blockText) {
		return textSectionDragAndDroppedBlockLocator(page, blockName, blockText)
				.evaluate("el => getComputedStyle(el).color").toString().trim();
	}

	public static String getBlockTextAlign(Page page, String blockName, String blockText) {
		return textSectionDragAndDroppedBlockLocator(page, blockName, blockText)
				.evaluate("el => getComputedStyle(el).textAlign").toString();
	}

	public static void clickOnLink(Page page, String blockText) {
		Locator link = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(blockText));
		link.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		link.click();
	}

	public static String getDestinationUrl(Page page, String url) {
		page.waitForURL(url);
		return page.url();
	}

	public static void navigateToPreviosPage(Page page) {
		page.goBack(new Page.GoBackOptions().setTimeout(5000));
	}

// Notebook section
	public static void clickOnNotebooksOption(Page page) {
		page.locator(NOTEBOOK_OPTION_XPATH).click();
	}

	public static void clickOnCreateNewNotebook(Page page) {
		page.getByTestId(CREATE_NEW_NOTEBOOK_DATA_TESTID).click();
	}

	public static void enterQueryName(Page page, String queryName) {
		page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName(NOTEBOOK_QUERY_ID_LABEL)).fill(queryName);
	}

	public static void clickOnQuerySubmitButton(Page page) {
		page.locator(QUERY_SUBMIT_BUTTON_XPATH).click();
	}

	public static void selectPage(Page page, String pageName) {
		page.locator(PAGE_SELECTION_XPATH.replace("{pageName}", pageName)).first().click();
	}

	public static void enterCodeInQuery(Page page, String code) {
		page.locator(CODE_ENTER_TEXTAREA).fill(code);
	}

	public static void clickOnRunAllButton(Page page) {
		page.getByTestId("ArrowDownwardIcon").click();
	}

	public static void selectQueryFromList(Page page, String queryName) {
		page.locator(QUERY_DROPDOWN_XPATH).fill(queryName);
		page.locator(QUERY_DROPDOWN_XPATH).press("ArrowDown");
		page.locator(QUERY_DROPDOWN_XPATH).press("Enter");
	}

	public static String getCodeOutput(Page page, String codeOutput) {
		Locator outputResult = page.locator(QUERY_CODE_RUN_OUTPUT_XPATH.replace("{codeOutput}", codeOutput));
		outputResult.waitFor(new Locator.WaitForOptions().setTimeout(10000));
		return outputResult.textContent().trim();
	}

	public static void clickOnTerminalCard(Page page) {
		page.locator(TERMINAL_XPATH).isVisible();
		page.locator(TERMINAL_XPATH).click();
	}

	public static void mouseHoverOnNotebookHiddenOptions(Page page) {
		Locator hiddenOptions = page.locator(CODE_ENTER_TEXTAREA);
		moveMouseToCenterWithMargin(page, hiddenOptions, 80, 10);
	}

	public static void clickOnHiddenNotebookOption(Page page, String optionName) {
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(optionName)).click();
	}

	public static void selectDataImportOption(Page page, String optionName) {
		page.locator(IMPORT_DATA_OPTIONS_XPATH.replace("{optionName}", optionName)).click();
	}

	public static void selectDatabaseFromDropdown(Page page, String databaseName) {
		page.locator(SELECT_DATABASE_DROPDOWN_XPATH).click();
		page.waitForTimeout(300);
		page.getByText(databaseName).click();
	}

	public static void selectAllColumns(Page page) {
		page.locator(SELECT_ALL_COLUMNS_XPATH).click();
	}

	public static void clickOnImportButton(Page page) {
		page.locator(IMPORT_BUTTON_XPATH).scrollIntoViewIfNeeded();
		page.locator(IMPORT_BUTTON_XPATH).click();
	}

	public static void deleteFirstCell(Page page) {
		page.getByTestId(DELETE_CELL_DATA_TESTID).first().click();
	}

	public static void clickOnRunCellButton(Page page) {
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Run cell")).click();
		page.getByTestId("CheckCircleIcon")
				.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
	}

	public static String getFrameID(Page page) {
		return page.locator(FRAME_CSS).inputValue().trim();
	}

	// chart block settings

	public static void clickOnDataTab(Page page) {
		page.locator(DATA_TAB_XPATH).click();
	}

	public static void selectFrame(Page page, String frameId) {
		Locator selectFrame = page.getByPlaceholder(SEARCH_FRAME_PLACEHOLDER);
		selectFrame.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		selectFrame.click();
		selectFrame.fill(frameId);
		selectFrame.press("ArrowDown");
		selectFrame.press("Enter");
	}

	public static void dragColumnToTargetField(Page page, String columnName, String targetField) {
		// scroll to column
		Locator sourceLocator = page.locator(DRAG_COLUMN_NAME_XPATH.replace("{columnName}", columnName));
		sourceLocator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		sourceLocator.scrollIntoViewIfNeeded();
		// Grab column
		sourceLocator.hover();
		moveMouseToCenter(page, sourceLocator, 0);
		page.mouse().down();
		page.waitForTimeout(300);
		// scroll to target filed
		Locator targetLocator = page.locator(DROP_FIELD_XPATH.replace("{fieldName}", targetField)).first();
		targetLocator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		targetLocator.scrollIntoViewIfNeeded();
		page.waitForTimeout(300);
		// refresh drag coordinates after scrolling
		moveMouseToCenter(page, sourceLocator, 0);
		// drop column to target filed--
		moveMouseToCenter(page, targetLocator, 15);
		page.mouse().up();
		page.waitForTimeout(300);
	}

	public static void takeChartScreenshot(Page page, String actualImagePath) {
		Locator chart = page.locator(CHART_XPATH);
		Path path = Paths.get(actualImagePath);
		page.waitForTimeout(2000);
		chart.screenshot(new Locator.ScreenshotOptions().setPath(path));
	}

	public static List<String> checkColumnNamesOnUI(Page page) {
		Locator columnNames = page.locator(LIST_OF_COLUMN_NAMES_XPATH);
		return columnNames.allTextContents();
	}

	private static void moveMouseToCenter(Page page, Locator locator, int steps) {
		BoundingBox box = locator.boundingBox();
		page.mouse().move(box.x + (box.width / 2), (box.y + box.height / 2), new Mouse.MoveOptions().setSteps(steps));
	}

	private static void moveMouseToCenterWithMargin(Page page, Locator locator, int margin, int steps) {
		BoundingBox box = locator.boundingBox();
		page.mouse().move(box.x + (box.width / 2), (box.y + box.height + margin),
				new Mouse.MoveOptions().setSteps(steps));
	}
}
