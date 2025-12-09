package aicore.utils.page.app;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;

import aicore.framework.ConfigUtils;
import aicore.utils.AICorePageUtils;
import aicore.utils.CommonUtils;

public class DragAndDropBlocksPageUtils {

	private static final Logger logger = LogManager.getLogger(DragAndDropBlocksPageUtils.class);

	private static final String PAGE_1_ID = "#page-1";
	private static final String PAGE_SELECTION_XPATH = "//div[@class='flexlayout__tab_button_content workspace_layout' and text()='page-1']";
	private static final String BLOCK_SEARCH_BOX_XPATH = "//*[@data-testid='TuneIcon']/../../../..//input[@placeholder='Search']";
	private static final String WELCOME_TEXT_BLOCK_TEXT = "Welcome to the UI Builder! Drag and drop blocks to use in your app.";
	private static final String EDIT_BUTTON_XPATH = "//a[span[text()='Edit']]";
	public static final String PREVIEW_APP_BUTTON_DATA_TEST_ID = "PlayArrowIcon";
	public static final String SHARE_APP_BUTTON_DATA_TEST_ID = "ShareRoundedIcon";
	public static final String SAVE_APP_BUTTON_DATA_TEST_ID = "SaveOutlinedIcon";
	public static final String SHOW_BUTTON_XPATH = "//a[span[text()='Show']]";
	private static final String TERMINAL_XPATH = "//p[contains(text(),'Terminal')]";
	public static final String BROWSE_TEMPLATES_XPATH = "text=Start build with a template";
	private static final String SAVE_APP_BUTTON_NAME = "Save App (ctrl/command + s)";

	// Blocks section
	private static final String BLOCKS_OPTION_XPATH = "//div[contains(@class,'flexlayout__border_button')][@title='Blocks']";
	private static final String LINK_BLOCK_DATA_TESTID = "blockMenuCardContent-card-Link";
	private static final String HEADING_1_BLOCK_DATA_TESTID = "blockMenuCardContent-card-Text-(h1)";
	private static final String HEADING_2_BLOCK_DATA_TESTID = "blockMenuCardContent-card-Text-(h2)";
	private static final String HEADING_3_BLOCK_DATA_TESTID = "blockMenuCardContent-card-Text-(h3)";
	private static final String HEADING_4_BLOCK_DATA_TESTID = "blockMenuCardContent-card-Text-(h4)";
	private static final String HEADING_5_BLOCK_DATA_TESTID = "blockMenuCardContent-card-Text-(h5)";
	private static final String HEADING_6_BLOCK_DATA_TESTID = "blockMenuCardContent-card-Text-(h6)";
	private static final String TEXT_BLOCK_DATA_TESTID = "blockMenuCardContent-card-Text";
	private static final String LOGS_BLOCK_DATA_TESTID = "blockMenuCardContent-card-Logs";
	private static final String MARKDOWN_BLOCK_DATA_TESTID = "blockMenuCardContent-card-Markdown";
	private static final String LINE_CHART_BLOCK_DATA_TESTID = "blockMenuCardContent-card-Line-Chart";
	private static final String SCATTER_PLOT_BLOCK_DATA_TESTID = "blockMenuCardContent-card-Scatter-Plot";
	private static final String BAR_CHART_BLOCK_DATA_TESTID = "blockMenuCardContent-card-Bar-Chart";
	private static final String BAR_CHART_STACKED_BLOCK_DATA_TESTID = "blockMenuCardContent-card-Bar-Chart---Stacked";
	private static final String PIE_CHART_BLOCK_DATA_TESTID = "blockMenuCardContent-card-Pie-Chart";
	private static final String GANTT_CHART_BLOCK_DATA_TESTID = "blockMenuCardContent-card-Gantt-Chart";
	private static final String DENDROGRAM_CHART_DATA_TESTID = "blockMenuCardContent-card-Dendrogram-Chart";
	private static final String MERMAID_CHART_DATA_TESTID = "blockMenuCardContent-card-Mermaid-Chart";
	private static final String WORLD_MAP_CHART_DATA_TESTID = "blockMenuCardContent-card-World-Map-Chart";
	private static final String ACCORDION_BLOCK_DATA_TESTID = "blockMenuCardContent-card-Accordion";
	private static final String HEADING_BLOCK_HELLO_WORLD_XPATH = "//h1[text()='Hello world']";
	private static final String MENU_OPTION_XPATH = "//button[contains(@class,'MuiButtonBase-root MuiIconButton-root MuiIconButton-edgeStart')]";
	private static final String MENU_CLOSED_ICON_XPATH = "//button[@aria-label='menu']//*[local-name()='svg' and @data-testid='MenuIcon']";
	private static final String APP_LOGO_ON_EDIT_PAGE_XPATH = "//h6[text()='{appName}']";
	private static final String LOGS_BLOCK_ON_PAGE_XPATH = "//div[contains(@data-block,'logs')]//span[text()='{logsText}']";
	private static final String INPUT_BLOCK_DATA_TESTID = "blockMenuCardContent-card-Input";
	private static final String DATA_GRID_DATA_TESTID = "blockMenuCardContent-card-Data-Grid";
	private static final String COLUMN_HEADERS_XPATH = "//div[contains(@class,'MuiDataGrid-columnHeaderTitleContainerContent')]//div";
	private static final String DELETE_COLUMN_BUTTON_XPATH = "//div[span[@title='{columnName}']]//following-sibling::div";
	private static final String DATA_GRID_ROWS_COUNT_XPATH = "//div[contains(@role,'rowgroup')]//div[contains(@role,'row')]";
	private static final String DATA_GRID_INFO_XPATH = ".MuiTablePagination-displayedRows";
	private static final String PAGINATION_DROP_DOWN_XPATH = "//*[text()='Rows per page:']/parent::div//following-sibling::div//div[@aria-haspopup='listbox']";

	// drag and dropped blocks on page
	private static final String CHART_XPATH = "//div[@class='echarts-for-react ']";
	private static final String DROPPED_TEXT_BLOCK_XPATH = "//p[text()='Hello world']";
	private static final String DROPPED_LINK_BLOCK_XPATH = "//a[text()='Insert text']";
	private static final String DROPPED_MARKDOWN_BLOCK_XPATH = "//p[strong[text()='Hello world']]";
	private static final String DROPPED_LOGS_BLOCK_XPATH = "//div[text()='Attach Query']";
	private static final String DROPPED_INPUT_BLOCK_XPATH = "//label[text()='Example Input']";
	private static final String DROPPED_DATA_GRID_BLOCK_XPATH = "//div[text()='No rows']";
	private static final String DROPPED_AREA_CHART_XPATH = "//div[@class='vega-embed']";
	private static final String DROPPED_MERMAID_CHART_XPATH = "//pre[@class='mermaid']";
	private static final String DROPPED_ACCORDION_BLOCK_XPATH = "//div[@data-block='accordion--1']";

	// Area Chart
	private static final String AREA_CHART_DATA_TESTID = "blockMenuCardContent-card-Area-Chart";
	private static final String DUPLICATE_ICON_XPATH = "//button[@aria-label='Duplicate']";
	private static final String DELETE_ICON_XPATH = "//*[name()='svg'][@data-testid='DeleteOutlineIcon']";
	private static final String CLICK_ON_AREA_CHART_VIEW_OPTIONS = "//div[@aria-label='Vega visualization']";
	private static final String DUPLICATE_TOOLTIP_MESSAGE_XPATH = "//div[contains(@class, 'MuiTooltip-tooltip') and text()='Duplicate']";
	private static final String DELETE_TOOLTIP_MESSAGE_XPATH = "//div[contains(@class, 'MuiTooltip-tooltip') and text()='Delete']";
	private static final String CHART_COUNT_ON_PAGE_XPATH = "//canvas[@class='marks']";

	// Bookmark app
	private static final String APP_BOOKMARK_XPATH = "//button[@type='button']//*[name()='svg'][@data-testid='BookmarkBorderIcon']";
	private static final String APP_UNBOOKMARK_XPATH = "//button[@type='button']//*[name()='svg'][@data-testid='BookmarkIcon']";
	private static final String APP_BOOKMARK_SECTION_TEXT = "Bookmarked";
	private static final String CATALOG_SEE_ON_BOOKMARKSECTIONXPATH = "//h6[normalize-space(text())='Bookmarked']/following-sibling::div[@class='css-uncsel']";
	// App section
	private static final String APP_DISPALY_APP_SECTION = "//div[contains(@data-testid,'appTileCard')]//a[@rel='noopener noreferrer']";
	private static final String APP_DISCOVRABLE_SECTION_DATATESTID = "appCatalogPage-discoverable-btn";
	private static final String CREATED_APP_DISPLAY_DISCOVEABLE_SECTION_XPATH = "//div[contains(@data-testid,'appTileCard')]";
	private static final String APP_SYSTEM_SECTION_DATATESTID = "appCatalogPage-systemApps-btn";
	private static final String APP_DISPLAY_IN_SYSTEM_SECTION_DATATESTID = "appTileCard-{appName}-tile";

	// Bar Chart tool
	private static final String CONDITIONAL_XPATH = "//span[text()='Conditional']";
	private static final String BARCHART_ISVISIBLE_XPATH = "//div[@class='echarts-for-react ']";
	private static final String COLOR_PALETTE_XPATH = "//span[text()='Color Palette']";
	private static final String ADD_CUSTOME_COLOR_PALETTE_XPATH = "//span[text()='+ Add Custom Color Palette']";
	private static final String COLOR_PALETTE_ICON_XPATH = "//*[name()='svg'][@data-testid='FormatColorFillIcon']";
	private static final String SELECT_RED_COLOR_XPATH = "//div[@title='#D0021B']";
	private static final String SELECT_BLUE_COLOR_XPATH = "//div[@title='#4A90E2']";
	private static final String COLOR_CHECK_ICON_XPATH = "//*[name()='svg'][@data-testid='CheckIcon']";
	private static final String ADD_COLOR_XPATH = "//span[text()='Add']";
	private static final String ADDED_COLOR_PALETTE_XPATH = "//div[normalize-space()='MyPalette']";
	private static final String LEGEND_OPTION_XPATH = "//span[text()='Legend']";
	private static final String LEGEND_OPTION_CHECKBOX_XPATH = "//p[normalize-space()='Show Legend']/preceding-sibling::span//input[@type='checkbox']";
	private static final String EDIT_AXIS_OPTION_XPATH = "//span[text()='Edit {axis}']";
	public static final String VALUE_LABELS_DROPDOWN_XPATH = "//li[normalize-space()='{valueLabelPosition}']";
	private static final String SHOW_AXIS_TITLE_XPATH = "//p[normalize-space(.)='Show Axis Title']/preceding-sibling::span//input[@type='checkbox']";
	private static final String AXIS_TITLE_XPATH = "//p[contains(text(),'Set Axis Title')]/parent::div//input[@type='text']";
	private static final String AXIS_FONT_SIZE_XPATH = "//p[contains(text(),'Edit Axis Title Font Size')]/parent::div//input[@type='number']";
	private static final String AXIS_GAP_XPATH = "//p[contains(text(),'Axis Gap')]/parent::div//input[@type='number']";
	private static final String AXIS_LABEL_FONT_SIZE_XPATH = "//p[contains(text(),'Edit Label Font Size:')]/parent::div//input[@type='number']";
	private static final String AXIS_SHOW_LABELS_XPATH = "//p[normalize-space(.)='Show {axis} Labels']/preceding-sibling::span//input[@type='checkbox']";
	private static final String AXIS_ROATATE_VALUE_XPATH = "//p[normalize-space(.)='Rotate {axis} Values:']/following::input[@type='range']";
	private static final String AXIS_LINE_TICK_XPATH = "//p[normalize-space(.)='Show {axis} Line Ticks']/preceding-sibling::span//input[@type='checkbox']";
	private static final String AXIS_ZOOM_XPATH = "//p[normalize-space(.)='Show / Hide {axis} Zoom']/preceding-sibling::span//input[@type='checkbox']";
	private static final String SHOW_VALUE_LABEL_XPATH = "//p[normalize-space()='Show Value Labels']/preceding-sibling::span//input[@type='checkbox']";
	private static final String SHOW_VALUE_LABEL_POSITION_XPATH = "//div[@id='label-position']";
	private static final String SHOW_VALUE_LABEL_SELECT_ALIGNMENT_XPATH = "//div[@id='alignment-label']";
	private static final String SHOW_VALUE_LABEL_FONT_XPATH = "//div[@id='font']";
	private static final String SHOW_VALUE_LABEL_FONT_SIZE_XPATH = "//input[@id='font-size']";
	private static final String SHOW_VALUE_LABEL_FONT_WEIGHT_XPATH = "//div[@id='font-weight']";
	private static final String SHOW_VALUE_LABEL_SELECT_COLOR_XPATH = "//button[@aria-label='select colour']";
	private static final String SHOW_VALUE_LABEL_CHOOSE_COLOR_XPATH = "//div[@title='#000000']";
	private static final String BAR_STYLE_CHHOSE_COLOR_XPATH = "//div[@title='#4A90E2']";
	private static final String SHOW_ROATATE_VALUE_LABEL_XPATH = "//input[@id='rotate-label']";
	private static final String CHART_TOOL_NAME_XPATH = "//span[text()='{toolName}']";
	private static final String BAR_STYLE_WIDTH_OPTION_XPATH = "//label[normalize-space()='Bar Width']/following::input[@aria-orientation='horizontal']";
	private static final String CHART_TITLE_SELECT_ALIGNMENT_XPATH = "//div[@id='alignment']";
	private static final String CHART_TITLE_SELECT_FONT_FAMILY_XPATH = "//div[@id='font-family']";
	private static final String CHART_TITLE_NAME_XPATH = "//input[@id='name']";
	private static final String CHART_TITLE_SIZE_XPATH = "//input[@id='size']";
	private static final String CHART_SHOW_TITLE_XPATH = "//span[@title='Show Title']//input";
	private static final String RESIZING_HEIGHT_XPATH = "//p[normalize-space()='Height']/ancestor::div[contains(@class,'base-setting-section')]//input[@type='text']";
	private static final String RESIZING_WIDTH_XPATH = "//p[normalize-space()='Width']/ancestor::div[contains(@class,'base-setting-section')]//input[@type='text']";
	private static final String LEFT_PANEL_TAB_DATATESTID = "workspace-{tabName}";
	private static final String MARKDOWN_BLOCK_XPATH = "//strong[text()='Hello world']";
	private static final String BLOCK_SETTINGS_XPATH = "//div[@class='flexlayout__border_button_content workspace_layout' and text()='Block Settings']/parent::div";

	public static boolean verifyPage1IsVisible(Page page) {
		Locator element = page.locator(PAGE_1_ID);
		element.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		return element.isVisible();
	}

	public static boolean verifyWelcomeTextboxIsVisible(Page page) {
		boolean isWelcomeTextboxVisible = page.getByText(WELCOME_TEXT_BLOCK_TEXT).isVisible();
		return isWelcomeTextboxVisible;
	}

	public static String verifyWelcomeText(Page page) {
		String actualWelcomeText = page.getByText(WELCOME_TEXT_BLOCK_TEXT).textContent().trim();
		return actualWelcomeText;
	}

	public static void navigateToHomePageFromAppEditPage(Page page) {
		String appNameWithLogo = ConfigUtils.getValue("applicationName");
		if (page.locator(MENU_CLOSED_ICON_XPATH).isVisible()) {
			page.locator(MENU_OPTION_XPATH).click();
		}
		page.locator(APP_LOGO_ON_EDIT_PAGE_XPATH.replace("{appName}", appNameWithLogo)).click();
	}

	public static void clickOnEditButton(Page page) {
		page.locator(EDIT_BUTTON_XPATH).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		page.locator(EDIT_BUTTON_XPATH).click();
	}

	public static void clickOnBlocksOption(Page page) {
		Locator blocksOption = page.locator(BLOCKS_OPTION_XPATH);
		if (!blocksOption.getAttribute("class").contains("flexlayout__border_button--selected")) {
			blocksOption.click();
		}
	}

	public static void closeBlocksOption(Page page) {
		Locator blocksOption = page.locator(BLOCKS_OPTION_XPATH);
		if (blocksOption.getAttribute("class").contains("flexlayout__border_button--selected")) {
			blocksOption.click();
		}
	}

	public static void blockDropPosition(Page page) {
		Locator targetBox = page.getByText(WELCOME_TEXT_BLOCK_TEXT);
		CommonUtils.moveMouseToCenterWithMargin(page, targetBox, 0, 10);
		page.mouse().up();
	}

	public static void clickOnDroppedBlock(Page page, String blockName) {
		Locator DroppedBlockLocator = null;
		switch (blockName) {
		case "Text (h1)":
		case "Text (h2)":
		case "Text (h3)":
		case "Text (h4)":
		case "Text (h5)":
		case "Text (h6)":
			DroppedBlockLocator = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Hello world"));
			break;
		case "Text":
			DroppedBlockLocator = page.locator(DROPPED_TEXT_BLOCK_XPATH);
			break;
		case "Link":
			DroppedBlockLocator = page.locator(DROPPED_LINK_BLOCK_XPATH);
			break;
		case "Markdown":
			DroppedBlockLocator = page.locator(DROPPED_MARKDOWN_BLOCK_XPATH);
			break;
		case "Logs":
			DroppedBlockLocator = page.locator(DROPPED_LOGS_BLOCK_XPATH);
			break;
		case "Input":
			DroppedBlockLocator = page.locator(DROPPED_INPUT_BLOCK_XPATH);
			break;
		case "Scatter Plot":
		case "Line Chart":
		case "Bar Chart":
		case "Bar Chart - Stacked":
		case "Pie Chart":
		case "Gantt Chart":
		case "Dendrogram Chart":
		case "World Map Chart":
			DroppedBlockLocator = page.locator(CHART_XPATH);
			break;
		case "Data Grid":
			DroppedBlockLocator = page.locator(DROPPED_DATA_GRID_BLOCK_XPATH);
			break;
		case "Area Chart":
			DroppedBlockLocator = page.locator(DROPPED_AREA_CHART_XPATH);
			break;
		case "Mermaid Chart":
			DroppedBlockLocator = page.locator(DROPPED_MERMAID_CHART_XPATH);
			break;
		case "Accordion":
			DroppedBlockLocator = page.locator(DROPPED_ACCORDION_BLOCK_XPATH);
			break;
		default:
			logger.error("Invalid block name: " + blockName);
			throw new IllegalArgumentException("Invalid block name: " + blockName);
		}
		AICorePageUtils.waitFor(DroppedBlockLocator);
		if (!blockName.equals("Link")) {
			DroppedBlockLocator.click();
		}
	}

	public static void mouseHoverOnBlock(Page page, String blockName) {
		boolean isValidBlock = true;
		Locator blockLocator = null;
		switch (blockName) {
		case "Text (h1)":
			blockLocator = page.getByTestId(HEADING_1_BLOCK_DATA_TESTID);
			break;
		case "Text (h2)":
			blockLocator = page.getByTestId(HEADING_2_BLOCK_DATA_TESTID);
			break;
		case "Text (h3)":
			blockLocator = page.getByTestId(HEADING_3_BLOCK_DATA_TESTID);
			break;
		case "Text (h4)":
			blockLocator = page.getByTestId(HEADING_4_BLOCK_DATA_TESTID);
			break;
		case "Text (h5)":
			blockLocator = page.getByTestId(HEADING_5_BLOCK_DATA_TESTID);
			break;
		case "Text (h6)":
			blockLocator = page.getByTestId(HEADING_6_BLOCK_DATA_TESTID);
			break;
		case "Text":
			blockLocator = page.getByTestId(TEXT_BLOCK_DATA_TESTID);
			break;
		case "Link":
			blockLocator = page.getByTestId(LINK_BLOCK_DATA_TESTID);
			break;
		case "Markdown":
			blockLocator = page.getByTestId(MARKDOWN_BLOCK_DATA_TESTID);
			break;
		case "Logs":
			blockLocator = page.getByTestId(LOGS_BLOCK_DATA_TESTID);
			break;
		case "Scatter Plot":
			blockLocator = page.getByTestId(SCATTER_PLOT_BLOCK_DATA_TESTID);
			break;
		case "Line Chart":
			blockLocator = page.getByTestId(LINE_CHART_BLOCK_DATA_TESTID);
			break;
		case "Bar Chart":
			blockLocator = page.getByTestId(BAR_CHART_BLOCK_DATA_TESTID);
			break;
		case "Bar Chart - Stacked":
			blockLocator = page.getByTestId(BAR_CHART_STACKED_BLOCK_DATA_TESTID);
			break;
		case "Pie Chart":
			blockLocator = page.getByTestId(PIE_CHART_BLOCK_DATA_TESTID);
			break;
		case "Gantt Chart":
			blockLocator = page.getByTestId(GANTT_CHART_BLOCK_DATA_TESTID);
			break;
		case "Area Chart":
			blockLocator = page.getByTestId(AREA_CHART_DATA_TESTID);
			break;
		case "Data Grid":
			blockLocator = page.getByTestId(DATA_GRID_DATA_TESTID);
			break;
		case "Input":
			blockLocator = page.getByTestId(INPUT_BLOCK_DATA_TESTID);
			break;
		case "Dendrogram Chart":
			blockLocator = page.getByTestId(DENDROGRAM_CHART_DATA_TESTID);
			break;
		case "Mermaid Chart":
			blockLocator = page.getByTestId(MERMAID_CHART_DATA_TESTID);
			break;
		case "World Map Chart":
			blockLocator = page.getByTestId(WORLD_MAP_CHART_DATA_TESTID);
			break;
		case "Accordion":
			blockLocator = page.getByTestId(ACCORDION_BLOCK_DATA_TESTID);
			break;
		default:
			isValidBlock = false;
			logger.error("Invalid block name: " + blockName);
			throw new IllegalArgumentException("Invalid block name: " + blockName);
		}
		blockLocator.scrollIntoViewIfNeeded();
		blockLocator.isVisible();
		blockLocator.hover();
		if (isValidBlock) {
			page.mouse().down();
		}
	}

	public static String verifyHeadingBlockTextMessage(Page page) {
		String headingBlockTextMessage = page.locator(HEADING_BLOCK_HELLO_WORLD_XPATH).textContent().trim();
		return headingBlockTextMessage;
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
			textBlockLocator = page.locator("//div[contains(@data-block,'markdown')]");
			break;
		case "Logs":
			textBlockLocator = page.locator(LOGS_BLOCK_ON_PAGE_XPATH.replace("{logsText}", blockText));
			break;
		default:
			textBlockLocator = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName(blockText));
			break;
		}
		AICorePageUtils.waitFor(textBlockLocator);
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
		page.goBack();
		page.waitForLoadState(LoadState.LOAD);
	}

	public static void selectPage(Page page, String pageName) {
		page.locator(PAGE_SELECTION_XPATH.replace("{pageName}", pageName)).first().click();
	}

	public static void searchBlock(Page page, String blockName) {
		page.locator(BLOCK_SEARCH_BOX_XPATH).fill(blockName);
	}

	public static void clickOnTerminalCard(Page page) {
		page.locator(TERMINAL_XPATH).isVisible();
		page.locator(TERMINAL_XPATH).click();
	}

	public static void takeChartScreenshot(Page page, String actualImagePath, String chart) {
		Locator chartLocator;
		switch (chart) {
		case "Mermaid Chart":
			chartLocator = page.locator(DROPPED_MERMAID_CHART_XPATH);
			break;
		case "Color Pallette Tool":
			chartLocator = page.locator(CHART_XPATH);
			break;
		case "Legend Chart Tool":
			chartLocator = page.locator(CHART_XPATH);
			break;

		case "Edit X-Axix Tool":
			chartLocator = page.locator(CHART_XPATH);
			break;

		default:
			chartLocator = page.locator(CHART_XPATH);
		}
		Path path = Paths.get(actualImagePath);
		page.waitForTimeout(4000);
		chartLocator.screenshot(new Locator.ScreenshotOptions().setPath(path));
	}

	// Duplicate and delete Area Chart
	public static void clickOnAreaChartTOViewOptions(Page page) {
		page.locator(CLICK_ON_AREA_CHART_VIEW_OPTIONS).click();
	}

	public static int getInitialcount(Page page, String blockName) {
		Locator droppedBlockLocator;
		switch (blockName) {
		case "Scatter Plot":
		case "Line Chart":
		case "Bar Chart":
		case "Bar Chart - Stacked":
		case "Pie Chart":
		case "Gantt Chart":
		case "Dendrogram Chart":
		case "World Map Chart":
			droppedBlockLocator = page.locator(CHART_XPATH);
			break;
		case "Data Grid":
			droppedBlockLocator = page.locator(DROPPED_DATA_GRID_BLOCK_XPATH);
			break;
		case "Area Chart":
			droppedBlockLocator = page.locator(DROPPED_AREA_CHART_XPATH);
			break;
		case "Mermaid Chart":
			droppedBlockLocator = page.locator(DROPPED_MERMAID_CHART_XPATH);
			break;
		default:
			logger.error("Invalid block name: " + blockName);
			throw new IllegalArgumentException("Invalid block name: " + blockName);
		}
		return droppedBlockLocator.count();
	}

	public static boolean canSeeDuplicateIcon(Page page) {
		return page.locator(DUPLICATE_ICON_XPATH).isVisible();
	}

	public static void clickOnDuplicateIcon(Page page) {
		Locator duplicateIcon = page.locator(DUPLICATE_ICON_XPATH);
//		CommonUtils.moveMouseToCenter(page, duplicateIcon, 0);
		duplicateIcon.click();
	}

	public static boolean duplicatedChartIsVisiable(Page page, int previousCount, String blockName) {
		Locator droppedBlockLocator;
		switch (blockName) {
		case "Scatter Plot":
		case "Line Chart":
		case "Bar Chart":
		case "Bar Chart - Stacked":
		case "Pie Chart":
		case "Gantt Chart":
		case "Dendrogram Chart":
		case "World Map Chart":
			droppedBlockLocator = page.locator(CHART_XPATH);
			break;
		case "Data Grid":
			droppedBlockLocator = page.locator(DROPPED_DATA_GRID_BLOCK_XPATH);
			break;
		case "Area Chart":
			droppedBlockLocator = page.locator(DROPPED_AREA_CHART_XPATH);
			break;
		case "Mermaid Chart":
			droppedBlockLocator = page.locator(DROPPED_MERMAID_CHART_XPATH);
			break;
		default:
			logger.error("Invalid block name: " + blockName);
			throw new IllegalArgumentException("Invalid block name: " + blockName);
		}
		// Wait until chart count increases
		page.waitForCondition(() -> droppedBlockLocator.count() == previousCount + 1);
		// Scroll the newly added chart into view (last one)
		if (droppedBlockLocator.count() > 0) {
			droppedBlockLocator.last().scrollIntoViewIfNeeded();
		}
		// Re-check the updated count
		int updatedChartCount = droppedBlockLocator.count();
		return updatedChartCount == previousCount + 1;
	}

	public static boolean canSeeDeleteIcon(Page page) {
		return page.locator(DELETE_ICON_XPATH).isVisible();
	}

	public static void clickOnDeleteIcon(Page page) {
		page.locator(DELETE_ICON_XPATH).click();
	}

	public static boolean chartIsRemoved(Page page, int previousCount, String chartName) {
		Locator chartLocator;
		switch (chartName) {
		case "Scatter Plot":
		case "Line Chart":
		case "Bar Chart":
		case "Bar Chart - Stacked":
		case "Pie Chart":
		case "Gantt Chart":
		case "Dendrogram Chart":
		case "World Map Chart":
			chartLocator = page.locator(CHART_XPATH);
			break;
		case "Data Grid":
			chartLocator = page.locator(DROPPED_DATA_GRID_BLOCK_XPATH);
			break;
		case "Area Chart":
			chartLocator = page.locator(DROPPED_AREA_CHART_XPATH);
			break;
		case "Mermaid Chart":
			chartLocator = page.locator(DROPPED_MERMAID_CHART_XPATH);
			break;
		default:
			logger.error("Invalid block name: " + chartName);
			throw new IllegalArgumentException("Invalid block name: " + chartName);
		}
		// Wait until chart count decrease
		page.waitForCondition(() -> chartLocator.count() == previousCount - 1);
		int updatedChartCount = chartLocator.count(); // Count charts again
		return updatedChartCount == previousCount - 1;
	}

	public static void hoverOnDuplicateIcon(Page page) {
		page.locator(DUPLICATE_ICON_XPATH).hover();
	}

	public static boolean checkTooltipMessageOfDuplicate(Page page, String expectedresult) {
		String actualResult = page.locator(DUPLICATE_TOOLTIP_MESSAGE_XPATH).textContent();
		return actualResult != null && actualResult.contains(expectedresult);
	}

	public static void hoverOnDeleteIcon(Page page) {
		page.locator(DELETE_ICON_XPATH).hover();
	}

	public static boolean checkTooltipMessageOfDeleteIcon(Page page, String expectedresult) {
		String actualResult = page.locator(DELETE_TOOLTIP_MESSAGE_XPATH).textContent();
		return actualResult != null && actualResult.contains(expectedresult);
	}

	public static void clickOnDuplicateIconMultipleTimes(int count, Page page) {
		for (int i = 0; i < count; i++) {
			Locator duplicateIcon = page.locator(DUPLICATE_ICON_XPATH); // Step 1: Click on Duplicate icon
			duplicateIcon.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
			duplicateIcon.click();
			page.waitForTimeout(500); // Step 2: Wait for UI to new chart
			if (i < count - 1) {
				Locator firstChart = page.locator(CHART_COUNT_ON_PAGE_XPATH).first();

				firstChart.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
				firstChart.click();
				page.waitForTimeout(300); // slight wait for UI response
			}
		}
	}

	public static int countCheck(Page page) {
		return page.locator(CHART_COUNT_ON_PAGE_XPATH).count();
	}

	public static int waitForChartCount(Page page, int expectedCount) {
		Locator charts = page.locator(CHART_COUNT_ON_PAGE_XPATH);
		int retries = 20; // retry for 10 seconds (20 * 500ms)
		for (int i = 0; i < retries; i++) {
			int currentCount = charts.count();
			if (currentCount == expectedCount) {
				return currentCount;
			}
			page.waitForTimeout(500);
		}
		int finalCount = charts.count();
		throw new RuntimeException("Expected " + expectedCount + " charts, but found " + finalCount);
	}

	public static void clickOnSyncChangesButton(Page page) {
		page.getByTestId("SyncIcon").click();
	}

	public static List<String> checkDataGridColumnNamesOnUI(Page page) {
		Locator columnNames = page.locator(COLUMN_HEADERS_XPATH);
		columnNames.last().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		return columnNames.allTextContents();
	}

	public static void removeColumnFromDataGrid(Page page, String columnName) {
		page.locator(DELETE_COLUMN_BUTTON_XPATH.replace("{columnName}", columnName)).isVisible();
		page.locator(DELETE_COLUMN_BUTTON_XPATH.replace("{columnName}", columnName)).click();
	}

	public static void validatePaginationForRowsPerPageOptions(Page page, List<String> rowsPerPageOptions) {
		for (String rowsPerPage : rowsPerPageOptions) {
			page.locator(PAGINATION_DROP_DOWN_XPATH).click();
			page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(rowsPerPage).setExact(true)).click();
			int actualRowsCount = page.locator(DATA_GRID_ROWS_COUNT_XPATH).count();
			if (actualRowsCount > Integer.parseInt(rowsPerPage)) {
				throw new AssertionError(
						"Displayed record rows " + actualRowsCount + " exceed selected rows per page " + rowsPerPage);
			}
			String displayedRowsText = page.locator(DATA_GRID_INFO_XPATH).textContent().trim();
			String[] parts = displayedRowsText.split("of");
			String secondValue = parts[1].trim();
			int totalRows = Integer.parseInt(secondValue);
			// Calculate expected total pages
			int expectedPages = (int) Math.ceil((double) totalRows / Integer.parseInt(rowsPerPage));

			// Check forward navigation
			for (int i = 1; i < expectedPages; i++) {
				page.getByLabel("Go to previous page").isVisible();
				page.getByLabel("Go to next page").isEnabled();
				page.getByLabel("Go to next page").click();
			}
			// Check backward navigation
			for (int i = 1; i < expectedPages; i++) {
				page.getByLabel("Go to previous page").isVisible();
				page.getByLabel("Go to previous page").isEnabled();
				page.getByLabel("Go to previous page").click();
			}
		}
	}

	// bookmarksection
	public static void clickBookmarkIcon(Page page, String appName) {
		Locator bookmarkIcon = page.locator(APP_BOOKMARK_XPATH).first();
		AICorePageUtils.waitFor(bookmarkIcon);
		bookmarkIcon.scrollIntoViewIfNeeded();
		bookmarkIcon.click();
	}

	public static boolean isBookmarkedSectionVisible(Page page) {
		Locator bookmarkSection = page.getByRole(AriaRole.HEADING,
				new Page.GetByRoleOptions().setName(APP_BOOKMARK_SECTION_TEXT));
		AICorePageUtils.waitFor(bookmarkSection);
		return bookmarkSection.isVisible();
	}

	public static boolean bookmarkAppSeeOnTheBookmarkSection(Page page) {
		Locator bookmarkedSection = page.locator(CATALOG_SEE_ON_BOOKMARKSECTIONXPATH).first();
		AICorePageUtils.waitFor(bookmarkedSection);
		return bookmarkedSection.isVisible();
	}

	public static void clickOnUnbookmarkforApp(Page page, String appName) {
		Locator unbookmarkIcon = page.locator(APP_UNBOOKMARK_XPATH).first();
		AICorePageUtils.waitFor(unbookmarkIcon);
		unbookmarkIcon.scrollIntoViewIfNeeded();
		unbookmarkIcon.click();
	}

	public static boolean isAppRemovedFromBookmarkSection(Page page, String appName) {
		Locator bookmarkedApp = page.locator(CATALOG_SEE_ON_BOOKMARKSECTIONXPATH).first();
		return bookmarkedApp.isHidden();
	}

	public static boolean isBookmarkedSectionNotVisible(Page page) {
		Locator bookmarkSection = page.getByText(APP_BOOKMARK_SECTION_TEXT);
		return bookmarkSection.isHidden();
	}

	// created app display in all apps section
	public static boolean isAppDisplayedInAllAppsSection(Page page, String appName) {
		Locator appInAllAppsSection = page.locator(APP_DISPALY_APP_SECTION);
		AICorePageUtils.waitFor(appInAllAppsSection);
		return appInAllAppsSection.isVisible();
	}

	public static void clickOnDiscovrableApps(Page page) {
		Locator discovrableApp = page.getByTestId(APP_DISCOVRABLE_SECTION_DATATESTID);
		discovrableApp.isVisible();
		discovrableApp.click();
	}

	public static boolean createdAppDisplayInDiscoverableApp(Page page, String appName) {
		Locator appDispalyInDiscoverable = page.locator(CREATED_APP_DISPLAY_DISCOVEABLE_SECTION_XPATH);
		return appDispalyInDiscoverable.isVisible();
	}

	public static void clickOnSystemApps(Page page) {
		page.getByTestId(APP_SYSTEM_SECTION_DATATESTID).click();
	}

	public static boolean isAppDisplayedInSystemAppsSection(Page page, String appName) {
		return page.getByTestId(APP_DISPLAY_IN_SYSTEM_SECTION_DATATESTID.replace("{appName}", appName)).isVisible();
	}

	// bar chart tool
	public static void clickOnToolTab(Page page) {
		page.locator("//button[normalize-space()='Tools']").click();
	}

	public static void applyConditional(Page page, String value) {
		page.getByTestId("selectInputSettings-Show-Block-e-chart--1-select").click();
		page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(value)).click();
	}

	public static boolean isBarChartVisible(Page page) {
		page.waitForTimeout(500);
		return page.locator(BARCHART_ISVISIBLE_XPATH).isVisible();
	}

	public static boolean performAddColor(Page page) {
		page.locator(ADD_CUSTOME_COLOR_PALETTE_XPATH).click();
		page.getByPlaceholder("Enter Palette Name").fill("MyPalette");
		page.locator(COLOR_PALETTE_ICON_XPATH).click();
		page.locator(SELECT_RED_COLOR_XPATH).click();
		page.locator(COLOR_CHECK_ICON_XPATH).click();
		page.locator(SELECT_BLUE_COLOR_XPATH).click();
		page.locator(COLOR_CHECK_ICON_XPATH).click();
		page.locator(ADD_COLOR_XPATH).click();
		return page.locator(ADDED_COLOR_PALETTE_XPATH).first().isVisible();
	}

	public static void performCheckColor(Page page) {
		page.locator(ADDED_COLOR_PALETTE_XPATH).first().click();

	}

	public static void clickOnLegendOptionAndTurnOnTheToggle(Page page) {
		page.locator(LEGEND_OPTION_XPATH).click();
		page.locator(LEGEND_OPTION_CHECKBOX_XPATH).check();
	}

	public static void clickOnEditXAxisOption(Page page, String axis) {
		page.locator(EDIT_AXIS_OPTION_XPATH.replace("{axis}", axis)).click();
	}

	public static void updateToolSettings(Page page, String valueName, String values) {
		String axisName = valueName.replace(" ", "");
		String axisNameWithDash = valueName.replace(" ", "-");
		for (String pair : values.split(", ")) {
			if (!pair.contains("=")) {
				throw new AssertionError("Invalid parameter: " + pair);
			}
			String[] kv = pair.split("=", 2);
			String key = kv[0];
			String value = kv[1].trim();
			switch (key) {
			case "Show Axis Title":
				boolean shouldShowTitle = Boolean.parseBoolean(value);
				boolean currentTitle = page.locator(SHOW_AXIS_TITLE_XPATH).isChecked();
				if (currentTitle != shouldShowTitle) {
					page.locator(SHOW_AXIS_TITLE_XPATH).click();
				}
				break;

			case "Set Axis Title":
				page.locator(AXIS_TITLE_XPATH).fill(value);
				break;

			case "Edit Axis Title Font Size":
				page.locator(AXIS_FONT_SIZE_XPATH).fill(value);
				break;

			case "Axis Gap":
				page.locator(AXIS_GAP_XPATH).fill(value);
				break;

			case "Edit Label Font Size":
				page.locator(AXIS_LABEL_FONT_SIZE_XPATH).fill(value);
				break;

			case "Show XAxis Labels":
			case "Show YAxis Labels":
				boolean shouldShow = Boolean.parseBoolean(value);
				boolean current = page.locator(AXIS_SHOW_LABELS_XPATH.replace("{axis}", axisName)).isChecked();
				if (current != shouldShow) {
					page.locator(AXIS_SHOW_LABELS_XPATH.replace("{axis}", axisName)).click();
				}
				break;

			case "Rotate X-Axis Values":
			case "Rotate Y-Axis Values":
				page.locator(AXIS_ROATATE_VALUE_XPATH.replace("{axis}", axisNameWithDash)).fill(value);
				break;

			case "Show XAxis Line Ticks":
			case "Show YAxis Line Ticks":
				boolean ticks = Boolean.parseBoolean(value);
				boolean currentTicks = page.locator(AXIS_LINE_TICK_XPATH.replace("{axis}", axisName)).isChecked();
				if (currentTicks != ticks) {
					page.locator(AXIS_LINE_TICK_XPATH.replace("{axis}", axisName)).click();
				}
				break;

			case "Show / Hide X-Axis Zoom":
			case "Show / Hide Y-Axis Zoom":
				boolean zoom = Boolean.parseBoolean(value);
				boolean currentZoom = page.locator(AXIS_ZOOM_XPATH.replace("{axis}", axisNameWithDash)).isChecked();
				if (currentZoom != zoom) {
					page.locator(AXIS_ZOOM_XPATH.replace("{axis}", axisNameWithDash)).click();
				}
				break;
			case "Show Title":
				boolean shouldShowTitle1 = Boolean.parseBoolean(value);
				boolean currentTitle1 = page.locator(CHART_SHOW_TITLE_XPATH).isChecked();
				if (currentTitle1 != shouldShowTitle1) {
					page.locator(CHART_SHOW_TITLE_XPATH).click();
				}
				break;
			case "Title Name":
				page.locator(CHART_TITLE_NAME_XPATH).fill(value);
				break;
			case "Text Size":
				page.locator(CHART_TITLE_SIZE_XPATH).fill(value);
				break;
			case "Select Font Family":
				page.locator(CHART_TITLE_SELECT_FONT_FAMILY_XPATH).click();
				page.locator(VALUE_LABELS_DROPDOWN_XPATH.replace("{valueLabelPosition}", value)).click();
				break;

			case "Select Font Weight":
				page.locator(SHOW_VALUE_LABEL_FONT_WEIGHT_XPATH).click();
				page.locator(VALUE_LABELS_DROPDOWN_XPATH.replace("{valueLabelPosition}", value)).click();
				break;
			case "Select Colour":
				page.locator(SHOW_VALUE_LABEL_SELECT_COLOR_XPATH).click();
				page.locator(SHOW_VALUE_LABEL_CHOOSE_COLOR_XPATH).click();
				page.keyboard().press("Escape");
				break;
			case "Select Alignment":
				page.locator(CHART_TITLE_SELECT_ALIGNMENT_XPATH).click();
				page.locator(VALUE_LABELS_DROPDOWN_XPATH.replace("{valueLabelPosition}", value)).click();
				break;
			case "Height":
				page.locator(RESIZING_HEIGHT_XPATH).fill(value);
				break;
			case "Width":
				page.locator(RESIZING_WIDTH_XPATH).fill(value);
				break;

			default:
				throw new AssertionError("Unknown Value setting: " + key);
			}
		}

	}

	public static void turnOnValueLabelToggle(Page page) {
		Locator valueLabelToggle = page.locator(SHOW_VALUE_LABEL_XPATH);
		if (!valueLabelToggle.isChecked()) {
			valueLabelToggle.click();
		}
	}

	public static void updateValueLabelSettings(Page page, String values) {
		for (String pair : values.split(", ")) {
			if (!pair.contains("=")) {
				throw new AssertionError("Invalid parameter: " + pair);
			}
			String[] kv = pair.split("=", 2);
			String key = kv[0];
			String value = kv[1].trim();
			switch (key) {
			case "Position":
				page.locator(SHOW_VALUE_LABEL_POSITION_XPATH).click();
				page.locator(VALUE_LABELS_DROPDOWN_XPATH.replace("{valueLabelPosition}", value)).click();
				break;
			case "Rotate Label":
				page.locator(SHOW_ROATATE_VALUE_LABEL_XPATH).fill(value);
				break;
			case "Select Alignment":
				page.locator(SHOW_VALUE_LABEL_SELECT_ALIGNMENT_XPATH).click();
				page.locator(VALUE_LABELS_DROPDOWN_XPATH.replace("{valueLabelPosition}", value)).click();
				break;
			case "Select Font":
				page.locator(SHOW_VALUE_LABEL_FONT_XPATH).click();
				page.locator(VALUE_LABELS_DROPDOWN_XPATH.replace("{valueLabelPosition}", value)).click();
				break;
			case "Select Font Size ":
				page.locator(SHOW_VALUE_LABEL_FONT_SIZE_XPATH).fill(value);
				break;
			case "Select Font Weight":
				page.locator(SHOW_VALUE_LABEL_FONT_WEIGHT_XPATH).click();
				page.locator(VALUE_LABELS_DROPDOWN_XPATH.replace("{valueLabelPosition}", value)).click();
				break;
			case "Select Colour":
				page.locator(SHOW_VALUE_LABEL_SELECT_COLOR_XPATH).click();
				page.locator(SHOW_VALUE_LABEL_CHOOSE_COLOR_XPATH).click();
				page.keyboard().press("Escape");
				break;
			default:
				throw new AssertionError("Unknown Value Label setting: " + key);
			}
		}

	}

	public static void clickOnToolOption(Page page, String toolName) {
		page.locator(CHART_TOOL_NAME_XPATH.replace("{toolName}", toolName)).click();

	}

	public static void updateBarStyle(Page page, String barStyleValue) {
		for (String pair : barStyleValue.split(", ")) {
			if (!pair.contains("=")) {
				throw new AssertionError("Invalid parameter: " + pair);
			}
			String[] kv = pair.split("=", 2);
			String key = kv[0];
			String value = kv[1].trim();
			switch (key) {
			case "Bar Width":
				page.locator(BAR_STYLE_WIDTH_OPTION_XPATH).fill(value);
				break;
			case "Select Colour":
				page.locator(SHOW_VALUE_LABEL_SELECT_COLOR_XPATH).click();
				page.locator(BAR_STYLE_CHHOSE_COLOR_XPATH).click();
				page.keyboard().press("Escape");
				break;
			default:
				throw new AssertionError("Unknown Bar Style setting: " + key);
			}

		}
	}

	public static void clickOnTabInLeftPanel(Page page, String tabName) {
		page.getByTestId(LEFT_PANEL_TAB_DATATESTID.replace("{tabName}", tabName)).first().click();
	}

	public static void clickOnMarkdownContainerToSelectIt(Page page) {
		page.locator(MARKDOWN_BLOCK_XPATH).click();
	}
	
	public static void clickOnBlockSettingsOption(Page page) {
		page.locator(BLOCK_SETTINGS_XPATH).click();
	}
}
