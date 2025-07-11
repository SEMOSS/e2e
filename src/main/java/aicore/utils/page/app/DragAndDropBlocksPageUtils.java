package aicore.utils.page.app;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

import aicore.utils.CommonUtils;
import aicore.utils.ConfigUtils;

public class DragAndDropBlocksPageUtils {

	private static final Logger logger = LogManager.getLogger(DragAndDropBlocksPageUtils.class);

	private static final String PAGE_1_ID = "#page-1";
	private static final String PAGE_SELECTION_XPATH = "//div[@class='flexlayout__tab_button_content' and text()='{pageName}']";
	private static final String WELCOME_TEXT_BLOCK_XPATH = "//div[@id='page-1']//p[@data-block='welcome-text-block']";
	private static final String EDIT_BUTTON_XPATH = "//a[span[text()='Edit']]";
	public static final String PREVIEW_APP_BUTTON_DATA_TEST_ID = "PlayArrowIcon";
	public static final String SHARE_APP_BUTTON_DATA_TEST_ID = "ShareRoundedIcon";
	public static final String SAVE_APP_BUTTON_DATA_TEST_ID = "SaveOutlinedIcon";
	public static final String SHOW_BUTTON_XPATH = "//a[span[text()='Show']]";
	private static final String TERMINAL_XPATH = "//p[contains(text(),'Terminal')]";
	public static final String BROWSE_TEMPLATES_XPATH = "text=Start build with a template";
	private static final String SAVE_APP_BUTTON_NAME = "Save App (ctrl/command + s)";

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
	private static final String PIE_CHART_BLOCK_XPATH = "//div[@aria-label='Show proportions of a whole']";
	private static final String GANTT_CHART_BLOCK_XPATH = "//div[@aria-label='Gannt chart for task management']";
	private static final String HEADING_BLOCK_HELLO_WORLD_XPATH = "//h1[text()='Hello world']";
	private static final String MENU_OPTION_XPATH = "//button[contains(@class,'MuiButtonBase-root MuiIconButton-root MuiIconButton-edgeStart')]";
	private static final String MENU_CLOSED_ICON_XPATH = "//button[@aria-label='menu']//*[local-name()='svg' and @data-testid='MenuIcon']";
	private static final String APP_LOGO_ON_EDIT_PAGE_XPATH = "//h6[text()='{appName}']";
	private static final String LOGS_BLOCK_ON_PAGE_XPATH = "//div[contains(@data-block,'logs')]//span[text()='{logsText}']";
	private static final String CHART_XPATH = "//div[@class='echarts-for-react ']";
	private static final String DATA_GRID_XPATH = "//div[@aria-label='Organize and display known data in a tabular format']";
	private static final String COLUMN_HEADERS_XPATH = "//div[contains(@class,'MuiDataGrid-columnHeaderTitleContainerContent')]//div";
	private static final String DELETE_COLUMN_BUTTON_XPATH = "//div[span[@title='{columnName}']]//following-sibling::div";
	private static final String DATA_GRID_ROWS_COUNT_XPATH = "//div[contains(@role,'rowgroup')]//div[contains(@role,'row')]";
	private static final String DATA_GRID_INFO_XPATH = ".MuiTablePagination-displayedRows";
	private static final String PAGINATION_DROP_DOWN_XPATH = "//*[text()='Rows per page:']/parent::div//following-sibling::div//div[@aria-haspopup='listbox']";

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

	public static void blockDropPosition(Page page) {
		Locator targetBox = page.locator(WELCOME_TEXT_BLOCK_XPATH);
		CommonUtils.moveMouseToCenterWithMargin(page, targetBox, 0, 10);
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
			case "Pie Chart":
				page.locator(PIE_CHART_BLOCK_XPATH).scrollIntoViewIfNeeded();
				page.locator(PIE_CHART_BLOCK_XPATH).isVisible();
				page.locator(PIE_CHART_BLOCK_XPATH).hover();
				break;
			case "Gantt Chart":
				page.locator(GANTT_CHART_BLOCK_XPATH).scrollIntoViewIfNeeded();
				page.locator(GANTT_CHART_BLOCK_XPATH).isVisible();
				page.locator(GANTT_CHART_BLOCK_XPATH).hover();
				break;
			case "Data Grid":
				page.locator(DATA_GRID_XPATH).scrollIntoViewIfNeeded();
				page.locator(DATA_GRID_XPATH).isVisible();
				page.locator(DATA_GRID_XPATH).hover();
				break;
			default:
				isValidBlock = false;
				logger.error("Invalid block name: " + blockName);
				throw new IllegalArgumentException("Invalid block name: " + blockName);
		}
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

	public static void selectPage(Page page, String pageName) {
		page.locator(PAGE_SELECTION_XPATH.replace("{pageName}", pageName)).first().click();
	}

	public static void clickOnTerminalCard(Page page) {
		page.locator(TERMINAL_XPATH).isVisible();
		page.locator(TERMINAL_XPATH).click();
	}

	public static void takeChartScreenshot(Page page, String actualImagePath) {
		Locator chart = page.locator(CHART_XPATH);
		Path path = Paths.get(actualImagePath);
		page.waitForTimeout(2000);
		chart.screenshot(new Locator.ScreenshotOptions().setPath(path));
	}

	public static void clickOnSyncChangesButton(Page page) {
		page.getByTestId("SyncIcon").click();
	}

	public static List<String> checkDataGridColumnNamesOnUI(Page page) {
		Locator columnNames = page.locator(COLUMN_HEADERS_XPATH);
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
}
