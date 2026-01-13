package aicore.pages.app;

import com.microsoft.playwright.Page;

import aicore.utils.page.app.LayersPageUtils;

public class LayersPage {
	private Page page;

	public LayersPage(Page page) {
		this.page = page;
	}

	public void clickOnTabInLeftPanel(String tabName) {
		LayersPageUtils.clickOnTabInLeftPanel(page, tabName);
	}

	public void clickOnAddNewPageIcon() {
		LayersPageUtils.clickOnAddNewPageIcon(page);
	}

	public boolean isPagePresent(String pageName) {
		return LayersPageUtils.isPagePresent(page, pageName);
	}

	public boolean isUserOnPage(String pageName) {
		return LayersPageUtils.isUserOnPage(page, pageName);
	}

	public void clickOnPageInLeftPane(String pageName) {
		LayersPageUtils.clickOnPageInLeftPane(page, pageName);
	}

	public void layerDropPosition(String blockName, String position) {
		LayersPageUtils.layerDropPosition(page, blockName, position);
	}

	public void mouseHoverOnLayer(String blockName) {
		LayersPageUtils.mouseHoverOnLayer(page, blockName);
	}
	public void reorderLayerPosition(String sourceLayerName, String position, String targetLayerName) {
		LayersPageUtils.reorderLayerPosition(page, sourceLayerName, position, targetLayerName);
	}
	public boolean verifyLayerPosition(String reorderedLayer, String position, String baseLayer) {
		return LayersPageUtils.verifyLayerPosition(page, reorderedLayer, position, baseLayer);
	}
}
