package aicore.utils.page.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Mouse;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.BoundingBox;

import aicore.utils.AICorePageUtils;
import aicore.utils.CommonUtils;


public class LayersPageUtils {
	private static final Logger logger = LogManager.getLogger(DragAndDropBlocksPageUtils.class);

	private static final String LEFT_PANEL_TAB_DATATESTID = "workspace-{tabName}-image";
	private static final String ADD_NEW_PAGE_ICON_XPATH = "//button[contains(@class,'layers-menu__add-layer-button')]";
	private static final String LEFT_PANE_PAGE_XPATH = "//div[text()='page-1']";
	private static final String SELECTED_PAGE_XPATH = "//div[@id='{pageName}']";
	private static final String LAYER_DROPDOWN_BLOCK_XPATH = "//li[@role='treeitem']//div//div//span//*";
	private static final String LAYER_BLOCK_INSIDE_XPATH = " //li[@role='treeitem']//*[normalize-space(.)='{layerName}']";
	private static final String LAYER_BLOCK_OUTSIDE_XPATH = " //div[@data-id='{layerName}']/..";
	private static final String LAYER_BLOCK_XPATH = "//div[contains(@data-id,'{layerName}')]//div[@role='button']";
	private static final String LAYER_BLOCK_ABOVE_XPATH = "//div[contains(@data-id,'{baseLayer}')]/preceding-sibling::div[contains(@data-id,'{reorderedLayer}')]";
	private static final String LAYER_BLOCK_BELOW_XPATH = "//div[contains(@data-id,'{baseLayer}')]/following-sibling::div[contains(@data-id,'{reorderedLayer}')]";
	private static final String LAYER_MORE_VERT_ICON_XPATH = "//div[contains(@data-id,'{layerName}')]//button[@aria-label='more']";
//	private static final String DELETE_LAYER_XPATH = "//div[text()='Delete']";
	private static final String DUPLICATE_LAYER_XPATH = "//div[text()='Duplicate']";
//	private static final String CONTAINER_OUTSIDE_DROP_ZONE = "//div[contains(@style,'position: absolute') and contains(@style,'height: 8px')]";
	private static final String DELETE_LAYER_MENU_ITEM_XPATH =
	        "//*[@role='menuitem' and contains(.,'Delete')]";
	
	
	
	public static void clickOnTabInLeftPanel(Page page, String tabName) {
		page.getByTestId(LEFT_PANEL_TAB_DATATESTID.replace("{tabName}", tabName)).first().click();
	}

	public static void clickOnAddNewPageIcon(Page page) {
		page.locator(ADD_NEW_PAGE_ICON_XPATH).click();
	}

	public static boolean isPagePresent(Page page, String pageName) {
		return page.locator(LEFT_PANE_PAGE_XPATH.replace("{pageName}", pageName)).first().isVisible();
	}

	public static boolean isUserOnPage(Page page, String pageName) {
		return page.locator(SELECTED_PAGE_XPATH.replace("{pageName}", pageName)).isVisible();
	}

	public static void clickOnPageInLeftPane(Page page, String pageName) {
		page.locator(LEFT_PANE_PAGE_XPATH.replace("{pageName}", pageName)).first().click();
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

	    Locator sourceLayer = page
	            .locator(LAYER_BLOCK_XPATH.replace("{layerName}", sourceLayerName.toLowerCase()))
	            .last();

	    Locator targetLayer = page
	            .locator(LAYER_BLOCK_XPATH.replace("{layerName}", targetLayerName.toLowerCase()))
	            .first();

	    
	    Locator expandArrow = page.locator(
	    	    "//div[@data-id='container--1']//button[@aria-label='Expand']"
	    	).first();

	 if (sourceLayerName.equalsIgnoreCase("Text") && expandArrow.isVisible()) {
	     expandArrow.click(new Locator.ClickOptions().setForce(true));
	     page.waitForTimeout(1000);
	 }
	 
		 AICorePageUtils.waitFor(sourceLayer);
		 AICorePageUtils.waitFor(targetLayer);

	    if (sourceLayer.count() == 0) {
	        throw new IllegalArgumentException("Source layer not found: " + sourceLayerName);
	    }

	    if (targetLayer.count() == 0) {
	        throw new IllegalArgumentException("Target layer not found: " + targetLayerName);
	    }


	    sourceLayer.scrollIntoViewIfNeeded();
	    targetLayer.scrollIntoViewIfNeeded();

	    BoundingBox source = sourceLayer.boundingBox();
	    BoundingBox target = targetLayer.boundingBox();

	    page.mouse().move(
	            source.x + source.width / 2,
	            source.y + source.height / 2);

	    page.mouse().down();

	    page.waitForTimeout(200);

	    double dropY;
	    switch (position.toLowerCase()) {

	    case "above":
	        dropY = target.y - 5;
	        break;

	    case "below":
	        dropY = target.y + target.height + 5;
	        break;

	    case "inside":
	        dropY = target.y + (target.height / 2);
	        break;
	    case "outside":
	        dropY = target.y - 5;
	        break;
	    default:
	        throw new IllegalArgumentException("Invalid position: " + position);
	}
	    page.mouse().move(
	            target.x + (target.width / 2),
	            dropY,
	            new Mouse.MoveOptions().setSteps(40));

	    page.waitForTimeout(300);

	    page.mouse().up();

	    page.waitForTimeout(500);
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
		case "inside" -> {
		    Locator container = page.locator("[data-block='" + baseLayer + "--1']");
		    yield container.locator("[data-block='" + reorderedLayer + "--1']");
		}
		case "outside" -> page
        .locator(LAYER_BLOCK_XPATH.replace("{layerName}", reorderedLayer))
        .first();
		default -> throw new IllegalArgumentException("Invalid position: " + position);
		};
		AICorePageUtils.waitFor(blockLocator);
		return blockLocator.isVisible();
	}

	public static void deleteLayer(Page page, String layerName) {

	    Locator moreButton = page.locator(
	            LAYER_MORE_VERT_ICON_XPATH.replace("{layerName}", layerName.toLowerCase())
	    ).first();

	    AICorePageUtils.waitFor(moreButton);

	    moreButton.click();

	    Locator deleteButton = page.locator(DELETE_LAYER_MENU_ITEM_XPATH);

	    AICorePageUtils.waitFor(deleteButton);

	    deleteButton.click();
	}

	public static void duplicateLayer(Page page, String layerName) {
		Locator moreVertIcon = page.locator(LAYER_MORE_VERT_ICON_XPATH.replace("{layerName}", layerName.toLowerCase()))
				.first();
		AICorePageUtils.waitFor(moreVertIcon);
		for (int i = 1; i <= 5; i++) {
			moreVertIcon.click();
			Locator duplicate = page.locator(DUPLICATE_LAYER_XPATH);
			AICorePageUtils.waitFor(duplicate);
			if (duplicate.isVisible()) {
				duplicate.click();
				break;
			}
		}
	}

	public static boolean isLayerDeleted(Page page, String layerName) {
		return page.locator(LAYER_MORE_VERT_ICON_XPATH.replace("link", layerName.toLowerCase())).first().isVisible();
	}

	public static boolean isLayerDuplicated(Page page, String layerName) {
		return page.locator(LAYER_BLOCK_XPATH.replace("{layerName}", layerName.toLowerCase())).count() > 1;
	}

}
