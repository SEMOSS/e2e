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
}
