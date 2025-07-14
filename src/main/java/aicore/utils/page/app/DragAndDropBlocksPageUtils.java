package aicore.utils.page.app;

import java.nio.file.Path;
import java.nio.file.Paths;

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

	// Area Chart
	private static final String AREA_CHART_XPATH = "//div[@aria-label='Show trends over time with cumulative data']";
	private static final String DUPLICATE_ICON_XPATH = "//*[name()='svg'][@data-testid='ContentCopyIcon']";
	private static final String DELETE_ICON_XPATH = "//*[name()='svg'][@data-testid='DeleteOutlineIcon']";
	private static final String CLICK_ON_AREA_CHART_VIEW_OPTIONS = "//div[@aria-label='Vega visualization']";
	private static final String AREA_CHART_COUNT_XPATH = "//canvas[@class='marks']";

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
		case "Area Chart":
			page.locator(AREA_CHART_XPATH).scrollIntoViewIfNeeded();
			page.locator(AREA_CHART_XPATH).isVisible();
			page.locator(AREA_CHART_XPATH).hover();
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

	// Area Chart
	public static void clickOnAreaChartTOViewOptions(Page page) {
		page.locator(CLICK_ON_AREA_CHART_VIEW_OPTIONS).click();
	}

	public static boolean CanseeDuplicateIcon(Page page) {
		return page.locator(DUPLICATE_ICON_XPATH).isVisible();
	}

	// declare the variable for get the inatial count
	private static int initialChartCount;

	public static void clickOnDuplicateIcon(Page page) {
		initialChartCount = page.locator(AREA_CHART_COUNT_XPATH).count();
		System.out.println("Intitial Count : " + initialChartCount);
		page.locator(DUPLICATE_ICON_XPATH).click();
	}

	public static boolean duplicatedChartIsVisiable(Page page, int expectedCount) {

		System.out.println(initialChartCount);
		// Wait for the new chart to be added (max 5 seconds)
		page.waitForCondition(() -> page.locator(AREA_CHART_COUNT_XPATH).count() == initialChartCount + 1);

		// Count charts again
		int updatedChartCount = page.locator(AREA_CHART_COUNT_XPATH).count();
		System.out.println("updarted count: " + updatedChartCount);
		return updatedChartCount == initialChartCount + 1;

	}

	public static boolean CanseeDeleteIcon(Page page) {
		return page.locator(DELETE_ICON_XPATH).isVisible();
	}

	public static void clickOnDeleteIcon(Page page) {
		initialChartCount = page.locator(AREA_CHART_COUNT_XPATH).count();
		System.out.println("Intitial Count : " + initialChartCount);
		page.locator(DELETE_ICON_XPATH).click();
	}

	public static boolean areaChartIsRemoved(Page page) {

		// Wait for the new chart to be added (max 5 seconds)
		page.waitForCondition(() -> page.locator(AREA_CHART_COUNT_XPATH).count() == initialChartCount - 1);
		// Count charts again
		int updatedChartCount = page.locator(AREA_CHART_COUNT_XPATH).count();
		System.out.println("updarted count: " + updatedChartCount);
		return updatedChartCount == initialChartCount - 1;
	}

	public static void hoverOnDuplicateIcon(Page page) {
		page.locator(DUPLICATE_ICON_XPATH).hover();
	}

	public static boolean checkTooltipMessageOfDuplicate(Page page, String expectedresult) {
		String actualResult = page.locator("//div[contains(@class, 'MuiTooltip-tooltip') and text()='Duplicate']")
				.textContent();
		return actualResult != null && actualResult.contains(expectedresult);
	}

	public static void hoverOnDeleteIcon(Page page) {
		page.locator(DELETE_ICON_XPATH).hover();
	}

	public static boolean checkTooltipMessageOfDeleteIcon(Page page, String expectedresult) {
		String actualResult = page.locator("//div[contains(@class, 'MuiTooltip-tooltip') and text()='Delete']")
				.textContent();
		return actualResult != null && actualResult.contains(expectedresult);
	}

	public static void clickOnDuplicateIconMultipleTimes(int count, Page page) {
		for (int i = 0; i < count; i++) {
			// Step 1: Click on Duplicate icon
			Locator duplicateIcon = page.locator(DUPLICATE_ICON_XPATH); // replace with actual locator
			duplicateIcon.waitFor(new Locator.WaitForOptions().setTimeout(5000));
			duplicateIcon.click();

			// Step 2: Wait a bit for UI to render new chart
			page.waitForTimeout(500);

			// Step 3: If not last iteration, click on Area Chart again to reactivate
			// Duplicate icon
			if (i < count - 1) {
				Locator firstChart = page.locator("//canvas[@class='marks']").first();
				firstChart.waitFor(new Locator.WaitForOptions().setTimeout(5000));
				firstChart.click();
				page.waitForTimeout(300); // slight wait for UI response
			}
		}

	}

	public static int CountCheck(Page page) {
		return page.locator("//canvas[@class='marks']").count();
	}

//
	public static void firstAreachart(Page page) {
		Locator firstChart = page.locator("//canvas[@class='marks']").first();
		firstChart.waitFor(new Locator.WaitForOptions().setTimeout(5000));
		firstChart.click();

	}

	public static void userClickOnSchema(Page page) {
		page.locator("//div[@class='view-lines monaco-mouse-cursor-text']").click();

		// Click directly on the '$schema' keyword
		Locator schemaKey = page.locator("//span[@class='mtk20' and contains(text(),\"$schema\")]");
		schemaKey.click();

		// Slight pause to ensure focus
		page.waitForTimeout(500);

		page.keyboard().press("Home");
		// Select the $schema key and value using keyboard
		for (int i = 0; i < 61; i++) {
			page.keyboard().press("Shift+ArrowRight"); // Extend selection character-by-character
		}

		// Delete the selected text
		page.keyboard().press("Backspace");

		// Optionally remove a trailing comma or fix formatting
		// page.keyboard().press("Backspace");

		page.pause();
	}

}
