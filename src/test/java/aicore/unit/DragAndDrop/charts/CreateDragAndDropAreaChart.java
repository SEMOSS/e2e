package aicore.unit.DragAndDrop.charts;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.microsoft.playwright.Page;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.CommonUtils;
import aicore.utils.annotations.PWPage;
import aicore.utils.page.app.CreateAppPopupUtils;
import aicore.utils.page.app.DragAndDropBlocksPageUtils;
import aicore.utils.page.app.TemplateCreationUtils;

public class CreateDragAndDropAreaChart extends AbstractPlaywrightTestBase{
	private int initialChartCount;
	private String appName;

	
	@BeforeEach
	void setup(@PWPage Page page) {
		loginNativeAdmin(page);
		appName = TemplateCreationUtils.createDragAndDropApp(page, "Drag and Drop");
		verifyAppCreated(page);
		verifyWelcomePage(page);
		DragAndDropBlocksPageUtils.clickOnBlocksOption(page);
	}
	@AfterEach
	void tearDown(@PWPage Page page) {
		CommonUtils.navigateAndDeleteApp(page, appName);
	    logout(page);
	}
	
	private void verifyAppCreated(Page page) {
	    String appName = CreateAppPopupUtils.userFetchAppName(page);
	    Assertions.assertFalse(appName.isEmpty(), "Fetched App Name is Empty");
	}
	
	private void verifyWelcomePage(Page page) {
	    Assertions.assertTrue(
	            DragAndDropBlocksPageUtils.verifyPage1IsVisible(page),
	            "Page is not visible");

	    Assertions.assertTrue(
	            DragAndDropBlocksPageUtils.verifyWelcomeTextboxIsVisible(page),
	            "Welcome text box is not visible");

	    Assertions.assertEquals(
	            "Welcome to the UI Builder! Drag and drop blocks to use in your app.",
	            DragAndDropBlocksPageUtils.verifyWelcomeText(page),
	            "Mismatch between the expected and actual welcome message");
	}
	
	private void verifyDeleteIconVisible(Page page, boolean shouldBeVisible) {
	    boolean canSeeDelete = DragAndDropBlocksPageUtils.canSeeDeleteIcon(page);

	    if (shouldBeVisible) {
	        Assertions.assertTrue(canSeeDelete, "User cannot view the Delete icon");
	    } else {
	        Assertions.assertFalse(canSeeDelete, "User should not view the Delete icon");
	    }
	}
	
	private void deleteChart(Page page, String chartName) {
	    initialChartCount = DragAndDropBlocksPageUtils.getInitialcount(page, chartName);

	    Assertions.assertTrue(
	            DragAndDropBlocksPageUtils.canSeeDeleteIcon(page),
	            "Delete icon is not visible before attempting deletion");

	    DragAndDropBlocksPageUtils.clickOnDeleteIcon(page);
	}
	
	private void verifyChartRemoved(Page page, String chartName) {
	    Assertions.assertTrue(
	            DragAndDropBlocksPageUtils.chartIsRemoved(page, initialChartCount, chartName),
	            "Expected chart was not removed after deletion");
	}
	
	private void addAreaChart(Page page) {
	    DragAndDropBlocksPageUtils.mouseHoverOnBlock(page, "Area Chart");
	    DragAndDropBlocksPageUtils.blockDropPosition(page, "Area Chart");
	    DragAndDropBlocksPageUtils.clickOnDroppedBlock(page, "Area Chart");
	}
	
	@Test
    public void DragAndDropDataAreaChartDeleteIcon_test(@PWPage Page page) {
		
		addAreaChart(page);
		verifyDeleteIconVisible(page, true);
		deleteChart(page, "Area Chart");
		verifyChartRemoved(page, "Area Chart");
	}

}
