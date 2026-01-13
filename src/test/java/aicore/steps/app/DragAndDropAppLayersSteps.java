package aicore.steps.app;

import org.junit.jupiter.api.Assertions;

import aicore.hooks.SetupHooks;
import aicore.pages.app.LayersPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DragAndDropAppLayersSteps {
	private LayersPage layerPage;

	public DragAndDropAppLayersSteps() {
		layerPage = new LayersPage(SetupHooks.getPage());
	}

	@And("User click on the {string} tab in the left panel")
	public void user_click_on_the_tab_in_the_left_panel(String tabName) {
		layerPage.clickOnTabInLeftPanel(tabName);
	}

	@Then("User should see {string} in the Pages section of the left pane")
	public void user_should_see_in_the_pages_section_of_the_left_pane(String pageName) {
		boolean isPagePresent = layerPage.isPagePresent(pageName);
		Assertions.assertTrue(isPagePresent, "Expected page is not present in the Pages section");
	}

	@And("User click on the Add new page icon to add a new page")
	public void user_click_on_the_add_new_page_icon_to_add_a_new_page() {
		layerPage.clickOnAddNewPageIcon();
	}

	@Then("User should be on {string} page")
	public void user_should_be_on_page(String pageName) {
		boolean isUserOnPage = layerPage.isUserOnPage(pageName);
		Assertions.assertTrue(isUserOnPage, "Expected user is not on the correct page");
	}

	@When("User clicks on {string} in the left pane")
	public void user_clicks_on_in_the_left_pane(String pageName) {
		layerPage.clickOnPageInLeftPane(pageName);
	}

	@When("User drags the {string} block and drops it {string} the {string} block")
	public void user_drags_the_block_and_drops_it_on_the_block(String blockName, String position,
			String containerName) {
		layerPage.mouseHoverOnLayer(blockName);
		layerPage.layerDropPosition(containerName, position);
	}

	@When("User moves the {string} block {string} the {string} block")
	public void user_moves_the_block_the_block(String sourceLayerName, String position, String targetLayerName) {
		layerPage.reorderLayerPosition(sourceLayerName, position, targetLayerName);
	}

	@Then("User should see {string} block appear {string} the {string} block on the page")
	public void user_should_see_block_appear_the_block_on_the_page(String reorderedLayer, String position, String baseLayer) {
		boolean isLayerPositionCorrect = layerPage.verifyLayerPosition(reorderedLayer, position, baseLayer);
		Assertions.assertTrue(isLayerPositionCorrect, "Expected layer position is not correct for " + reorderedLayer);
	}

}
