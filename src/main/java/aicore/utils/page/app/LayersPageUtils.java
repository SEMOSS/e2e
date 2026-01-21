package aicore.utils.page.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import aicore.utils.AICorePageUtils;
import aicore.utils.CommonUtils;

public class LayersPageUtils {
	private static final Logger logger = LogManager.getLogger(DragAndDropBlocksPageUtils.class);

	private static final String LEFT_PANEL_TAB_DATATESTID = "workspace-{tabName}";
	private static final String ADD_NEW_PAGE_ICON_XPATH = "//button/*[@data-testid='AddIcon']";
	private static final String LEFT_PANE_PAGE_XPATH = "//h6[text()='{pageName}']";
	private static final String SELECTED_PAGE_XPATH = "//div[@id='{pageName}']";
	private static final String LAYER_DROPDOWN_BLOCK_XPATH = "//li[@role='treeitem']//div//div//span//*";
	private static final String LAYER_BLOCK_INSIDE_XPATH = " //li[@role='treeitem']//*[normalize-space(.)='{layerName}']";
	private static final String LAYER_BLOCK_OUTSIDE_XPATH = " //div[@data-id='{layerName}']/..";
	private static final String LAYER_BLOCK_XPATH = "//div[contains(@data-id,'{layerName}')]";
	private static final String INSIDE_CONTAINER_LAYER_BLOCK_XPATH = "//div[contains(@data-id,'{baseLayer}')]//div[contains(@data-id,'{reorderedLayer}')]";
	private static final String LAYER_BLOCK_ABOVE_XPATH = "//div[contains(@data-id,'{baseLayer}')]/preceding-sibling::div[contains(@data-id,'{reorderedLayer}')]";
	private static final String LAYER_BLOCK_BELOW_XPATH = "//div[contains(@data-id,'{baseLayer}')]/following-sibling::div[contains(@data-id,'{reorderedLayer}')]";
	private static final String CONTAINER_EXPAND_ARROW_DATA_TESTID = "ChevronRightIcon";
	private static final String LAYER_MORE_VERT_ICON_XPATH = "//div[contains(@data-id,'link')]//button";
	private static final String DELETE_LAYER_XPATH = "//li[@value='delete']";
	private static final String DUPLICATE_LAYER_XPATH = "//li[@value='duplicate']";

	public static void clickOnTabInLeftPanel(Page page, String tabName) {
		page.getByTestId(LEFT_PANEL_TAB_DATATESTID.replace("{tabName}", tabName)).first().click();
	}

	public static void clickOnAddNewPageIcon(Page page) {
		page.locator(ADD_NEW_PAGE_ICON_XPATH).click();
	}

	public static boolean isPagePresent(Page page, String pageName) {
		return page.locator(LEFT_PANE_PAGE_XPATH.replace("{pageName}", pageName)).isVisible();
	}

	public static boolean isUserOnPage(Page page, String pageName) {
		return page.locator(SELECTED_PAGE_XPATH.replace("{pageName}", pageName)).isVisible();
	}

	public static void clickOnPageInLeftPane(Page page, String pageName) {
		page.locator(LEFT_PANE_PAGE_XPATH.replace("{pageName}", pageName)).click();
	}

	public static void mouseHoverOnLayer(Page page, String layerTargetName) {
		Locator layerDropDownLocator = page.locator(LAYER_DROPDOWN_BLOCK_XPATH).first();
		Locator blockLocator = page.locator(LAYER_BLOCK_INSIDE_XPATH.replace("{layerName}", layerTargetName));
		layerDropDownLocator.click();
		if (blockLocator == null) {
			logger.error("Invalid layer name: " + layerTargetName);
			throw new IllegalArgumentException("Invalid layer name: " + layerTargetName);
		}
		blockLocator.scrollIntoViewIfNeeded();
		blockLocator.isVisible();
		blockLocator.hover();
		page.mouse().down();

	}

	public static void layerDropPosition(Page page, String layerName, String position) {
		if (position.equalsIgnoreCase("inside")) {
			Locator targetBox = page.locator(LAYER_BLOCK_INSIDE_XPATH.replace("{layerName}", layerName));
			CommonUtils.moveMouseToCenterWithMargin(page, targetBox, 10, 10);
		} else if (position.equalsIgnoreCase("outside")) {
			Locator targetBox = page.locator(LAYER_BLOCK_OUTSIDE_XPATH.replace("{layerName}", layerName));
			CommonUtils.moveMouseToCenterWithMargin(page, targetBox, 20, 10);
		}
		page.mouse().up();
	}

	public static void reorderLayerPosition(Page page, String sourceLayerName, String position,
			String targetLayerName) {
		page.waitForTimeout(500);
		Locator sourceLayer = page.locator(LAYER_BLOCK_XPATH.replace("{layerName}", sourceLayerName.toLowerCase()))
				.first();
		Locator targetLayer = page.locator(LAYER_BLOCK_XPATH.replace("{layerName}", targetLayerName.toLowerCase()))
				.first();
		Locator expandArrow = page.getByTestId(CONTAINER_EXPAND_ARROW_DATA_TESTID).first();
		if (expandArrow.isVisible()) {
			expandArrow.click(new Locator.ClickOptions().setForce(true));
		}
		if (sourceLayer == null) {
			logger.error("Invalid source layer name: " + sourceLayerName);
			throw new IllegalArgumentException("Invalid source layer name: " + sourceLayerName);
		}
		sourceLayer.scrollIntoViewIfNeeded();
		AICorePageUtils.waitFor(sourceLayer);
		sourceLayer.hover();
		page.mouse().down();
		if (targetLayer == null) {
			logger.error("Invalid target layer name: " + targetLayerName);
			throw new IllegalArgumentException("Invalid target layer name: " + targetLayerName);
		}
		AICorePageUtils.waitFor(targetLayer);
		if (position.equalsIgnoreCase("above") || targetLayerName.equalsIgnoreCase("Container")) {
			CommonUtils.moveMouseToCenterWithMargin(page, targetLayer, -5, 30);
		} else {
			CommonUtils.moveMouseToCenterWithMargin(page, targetLayer, 5, 30);
		}
		page.mouse().up();
	}

	public static boolean verifyLayerPosition(Page page, String reorderedLayerName, String position,
			String baseLayerName) {
		String reorderedLayer = reorderedLayerName.toLowerCase();
		String baseLayer = baseLayerName.toLowerCase();
		Locator blockLocator = switch (position.toLowerCase()) {
		case "above" -> page.locator(
				LAYER_BLOCK_ABOVE_XPATH.replace("{baseLayer}", baseLayer).replace("{reorderedLayer}", reorderedLayer));
		case "below" -> page.locator(
				LAYER_BLOCK_BELOW_XPATH.replace("{baseLayer}", baseLayer).replace("{reorderedLayer}", reorderedLayer));
		case "inside" -> page.locator(INSIDE_CONTAINER_LAYER_BLOCK_XPATH.replace("{baseLayer}", baseLayer)
				.replace("{reorderedLayer}", reorderedLayer));
		case "outside" -> page.locator(LAYER_BLOCK_XPATH.replace("{layerName}", reorderedLayer));
		default -> throw new IllegalArgumentException("Invalid position: " + position);
		};
		AICorePageUtils.waitFor(blockLocator);
		return blockLocator.isVisible();
	}

	public static void deleteLayer(Page page, String layerName) {
		Locator moreVertIcon = page.locator(LAYER_MORE_VERT_ICON_XPATH.replace("link", layerName.toLowerCase()))
				.first();
		AICorePageUtils.waitFor(moreVertIcon);
		moreVertIcon.click();
		Locator delete = page.locator(DELETE_LAYER_XPATH);
		AICorePageUtils.waitFor(delete);
		delete.click();
	}

	public static void duplicateLayer(Page page, String layerName) {
		Locator moreVertIcon = page.locator(LAYER_MORE_VERT_ICON_XPATH.replace("link", layerName.toLowerCase()))
				.first();
		AICorePageUtils.waitFor(moreVertIcon);
		moreVertIcon.click();
		Locator duplicate = page.locator(DUPLICATE_LAYER_XPATH);
		AICorePageUtils.waitFor(duplicate);
		duplicate.click();
	}

	public static boolean isLayerDeleted(Page page, String layerName) {
		return page.locator(LAYER_MORE_VERT_ICON_XPATH.replace("link", layerName.toLowerCase())).first().isVisible();
	}

	public static boolean isLayerDuplicated(Page page, String layerName) {
		return page.locator(LAYER_BLOCK_XPATH.replace("{layerName}", layerName.toLowerCase())).count() > 1;
	}
}
