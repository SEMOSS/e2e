package aicore.unit.DragAndDrop.layers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.microsoft.playwright.Page;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.annotations.PWPage;
import aicore.utils.page.app.CreateAppPopupUtils;
import aicore.utils.page.app.DragAndDropBlocksPageUtils;
import aicore.utils.page.app.LayersPageUtils;
import aicore.utils.page.app.TemplateCreationUtils;

public class CreateAndValidateLayers extends AbstractPlaywrightTestBase {
		
	@BeforeEach
	void setup(@PWPage Page page) {
		loginNativeAdmin(page);
		TemplateCreationUtils.createDragAndDropApp(page, "Drag and Drop");
		verifyAppCreated(page);
	}
	
	@AfterEach
	void tearDown(@PWPage Page page) {
	    logout(page);
	}
	
	private void verifyAppCreated(Page page) {
	    String appName = CreateAppPopupUtils.userFetchAppName(page);
	    Assertions.assertFalse(appName.isEmpty(), "Fetched App Name is Empty");
	}
	
	private void verifyPagePresent(Page page, String pageName) {
	    Assertions.assertTrue(
	            LayersPageUtils.isPagePresent(page, pageName),
	            "Expected page '" + pageName + "' is not present in the Pages section.");
	}
	
	private void verifyUserOnPage(Page page, String pageName) {
	    Assertions.assertTrue(
	            LayersPageUtils.isUserOnPage(page, pageName),
	            "Expected user is not on page '" + pageName + "'.");
	}
	
	private void verifyLayerDuplicated(Page page, String layerName) {
	    Assertions.assertTrue(
	            LayersPageUtils.isLayerDuplicated(page, layerName),
	            "Expected layer '" + layerName + "' to be duplicated.");
	}
	
	private void verifyLayerDeleted(Page page, String layerName) {
	    Assertions.assertFalse(
	            LayersPageUtils.isLayerDeleted(page, layerName),
	            "Expected layer '" + layerName + "' to be deleted.");
	}
	
	private void verifyLayerPosition(
	        Page page,
	        String reorderedLayer,
	        String position,
	        String baseLayer) {

	    Assertions.assertTrue(
	            LayersPageUtils.verifyLayerPosition(
	                    page,
	                    reorderedLayer,
	                    position,
	                    baseLayer),
	            "Expected '" + reorderedLayer + "' to appear "
	                    + position + " '" + baseLayer + "'.");
	}
	
	@Test
    public void CreateAndValidateMultiplePages_test(@PWPage Page page) {
		LayersPageUtils.clickOnTabInLeftPanel(page, "Layers");
		verifyPagePresent(page, "/page-1");
		LayersPageUtils.clickOnAddNewPageIcon(page);
		verifyPagePresent(page, "/page-2");
		LayersPageUtils.clickOnAddNewPageIcon(page);
		verifyPagePresent(page, "/page-3");
		DragAndDropBlocksPageUtils.selectPage(page, "page-1");
		verifyUserOnPage(page, "page-1");
		DragAndDropBlocksPageUtils.selectPage(page, "page-2");
		verifyUserOnPage(page, "page-2");
		DragAndDropBlocksPageUtils.selectPage(page, "page-3");
		verifyUserOnPage(page, "page-3");
	}
	
	@Test
    public void ReordeLayers_test(@PWPage Page page) {
		
		DragAndDropBlocksPageUtils.clickOnBlocksOption(page);
	    DragAndDropBlocksPageUtils.mouseHoverOnBlock(page, "Scatter Plot");
	    DragAndDropBlocksPageUtils.blockDropPosition(page, "Scatter Plot");
	    DragAndDropBlocksPageUtils.mouseHoverOnBlock(page, "Logs");
	    DragAndDropBlocksPageUtils.blockDropPosition(page, "Logs");
	    DragAndDropBlocksPageUtils.mouseHoverOnBlock(page, "Link");
	    DragAndDropBlocksPageUtils.blockDropPosition(page, "Link");
		LayersPageUtils.clickOnTabInLeftPanel(page, "Layers");
		LayersPageUtils.reorderLayerPosition(page, "E-chart", "above", "Link");
		verifyLayerPosition(page, "E-chart", "above", "Link");
		LayersPageUtils.reorderLayerPosition(page, "Link", "inside", "Container");
		verifyLayerPosition(page, "Link", "inside", "Container");	
		LayersPageUtils.reorderLayerPosition(page, "Text", "outside", "Container");
		verifyLayerPosition(page, "Text", "outside", "Container");
		DragAndDropBlocksPageUtils.clickOnSaveAppButton(page);
	}
	
	@Test
    public void DeleteDuplicateLayers_test(@PWPage Page page) {
		
		DragAndDropBlocksPageUtils.clickOnBlocksOption(page);
	    DragAndDropBlocksPageUtils.mouseHoverOnBlock(page, "Scatter Plot");
	    DragAndDropBlocksPageUtils.blockDropPosition(page, "Scatter Plot");
		LayersPageUtils.clickOnTabInLeftPanel(page, "Layers");
		LayersPageUtils.duplicateLayer(page, "E-chart");
		verifyLayerDuplicated(page, "E-chart");
		LayersPageUtils.deleteLayer(page, "Container");
		verifyLayerDeleted(page, "Container");
		DragAndDropBlocksPageUtils.clickOnSaveAppButton(page);

		
		


		
		
		
		
	}
		

}
