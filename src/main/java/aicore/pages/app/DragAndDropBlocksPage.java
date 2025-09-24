package aicore.pages.app;

import java.util.List;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import aicore.utils.HomePageUtils;
import aicore.utils.page.app.DragAndDropBlocksPageUtils;

public class DragAndDropBlocksPage {

	private Page page;

	public DragAndDropBlocksPage(Page page) {
		this.page = page;
	}

	public boolean verifyPage1IsVisible() {
		return DragAndDropBlocksPageUtils.verifyPage1IsVisible(page);
	}

	public boolean verifyWelcomeTextboxIsVisible() {
		return DragAndDropBlocksPageUtils.verifyWelcomeTextboxIsVisible(page);
	}

	public String verifyWelcomeText() {
		return DragAndDropBlocksPageUtils.verifyWelcomeText(page);
	}

	public void navigatesToHomePage() {
		HomePageUtils.navigateToHomePage(page);
	}

	public void clickOnEditButton() {
		DragAndDropBlocksPageUtils.clickOnEditButton(page);
	}

	public void clickOnBlocksOption() {
		DragAndDropBlocksPageUtils.clickOnBlocksOption(page);
	}

	public void blockDropPosition() {
		DragAndDropBlocksPageUtils.blockDropPosition(page);
	}

	public void clickOnDroppedBlock(String blockName) {
		DragAndDropBlocksPageUtils.clickOnDroppedBlock(page, blockName);
	}

	public void mouseHoverOnBlock(String blockName) {
		DragAndDropBlocksPageUtils.mouseHoverOnBlock(page, blockName);
	}

	public String verifyHeadingBlockTextMessage() {
		return DragAndDropBlocksPageUtils.verifyHeadingBlockTextMessage(page);
	}

	public void clickOnSaveAppButton() {
		DragAndDropBlocksPageUtils.clickOnSaveAppButton(page);
	}

	public Locator textSectionDragAndDroppedBlockLocator(String blockName, String blockText) {
		return DragAndDropBlocksPageUtils.textSectionDragAndDroppedBlockLocator(page, blockName, blockText);
	}

	public String getBlockText(String blockName, String blockText) {
		return DragAndDropBlocksPageUtils.getBlockText(page, blockName, blockText);
	}

	public String getBlockTextFont(String blockName, String blockText) {
		return DragAndDropBlocksPageUtils.getBlockTextFont(page, blockName, blockText);
	}

	public String getBlockTextStyle(String blockName, String blockText) {
		return DragAndDropBlocksPageUtils.getBlockTextStyle(page, blockName, blockText);
	}

	public String getBlockTextColor(String blockName, String blockText) {
		return DragAndDropBlocksPageUtils.getBlockTextColor(page, blockName, blockText);
	}

	public String getBlockTextAlign(String blockName, String blockText) {
		return DragAndDropBlocksPageUtils.getBlockTextAlign(page, blockName, blockText);
	}

	public void clickOnLink(String blockText) {
		DragAndDropBlocksPageUtils.clickOnLink(page, blockText);
	}

	public String getDestinationUrl(String url) {
		return DragAndDropBlocksPageUtils.getDestinationUrl(page, url);
	}

	public void navigateToPreviosPage() {
		DragAndDropBlocksPageUtils.navigateToPreviosPage(page);
	}

	public void selectPage(String pageName) {
		DragAndDropBlocksPageUtils.selectPage(page, pageName);
	}

	public void clickOnTerminalCard() {
		DragAndDropBlocksPageUtils.clickOnTerminalCard(page);
	}

	public void takeChartScreenshot(String actualImagePath) {
		DragAndDropBlocksPageUtils.takeChartScreenshot(page, actualImagePath);
	}

	// Area Chart
	public void clickOnAreaChartTOViewOptions() {
		DragAndDropBlocksPageUtils.clickOnAreaChartTOViewOptions(page);
	}

	public boolean canSeeDuplicateIcon() {
		return DragAndDropBlocksPageUtils.CanseeDuplicateIcon(page);
	}

	public int getInitialCount() {
		return DragAndDropBlocksPageUtils.getInitialcount(page);
	}

	public void clickOnDuplicateIcon() {
		DragAndDropBlocksPageUtils.clickOnDuplicateIcon(page);
	}

	public boolean duplicatedChartIsVisiable(int expectedcount) {
		return DragAndDropBlocksPageUtils.duplicatedChartIsVisiable(page, expectedcount);
	}

	public boolean canSeeDeleteIcon() {
		return DragAndDropBlocksPageUtils.CanseeDeleteIcon(page);
	}

	public void clickOnDeleteIcon() {
		DragAndDropBlocksPageUtils.clickOnDeleteIcon(page);
	}

	public boolean areaChartIsRemoved(int count) {
		return DragAndDropBlocksPageUtils.areaChartIsRemoved(page, count);
	}

	public void hoverOnDuplicateIcon() {
		DragAndDropBlocksPageUtils.hoverOnDuplicateIcon(page);
	}

	public void hoverOnDeleteIcon() {
		DragAndDropBlocksPageUtils.hoverOnDeleteIcon(page);
	}

	public boolean checkTooltipMessageOfDuplicate(String expectedString) {
		return DragAndDropBlocksPageUtils.checkTooltipMessageOfDuplicate(page, expectedString);
	}

	public boolean checkTooltipMessageOfDelete(String expectedString) {
		return DragAndDropBlocksPageUtils.checkTooltipMessageOfDeleteIcon(page, expectedString);
	}

	public void clickOnDuplicateIconMultipleTimes(int count) {
		DragAndDropBlocksPageUtils.clickOnDuplicateIconMultipleTimes(count, page);
	}

	public int countcheck() {
		return DragAndDropBlocksPageUtils.CountCheck(page);
	}

	public void clickOnSyncChangesButton() {
		DragAndDropBlocksPageUtils.clickOnSyncChangesButton(page);
	}

	public List<String> checkDataGridColumnNamesOnUI() {
		return DragAndDropBlocksPageUtils.checkDataGridColumnNamesOnUI(page);
	}

	public void removeColumnFromDataGrid(String columnName) {
		DragAndDropBlocksPageUtils.removeColumnFromDataGrid(page, columnName);
	}

	public void validatePaginationForRowsPerPageOptions(List<String> rowsPerPageOptions) {
		DragAndDropBlocksPageUtils.validatePaginationForRowsPerPageOptions(page, rowsPerPageOptions);
	}

	// bookmarksection
	public void userClickOnBookmarkIcon(String appName) {
		DragAndDropBlocksPageUtils.clickBookmarkIcon(page, appName);
	}

	public void clickOnUnbookmarkforApp(String appName) {
		DragAndDropBlocksPageUtils.clickOnUnbookmarkforApp(page, appName);

	}

	public boolean userSeeBookMarkSection() {
		return DragAndDropBlocksPageUtils.isBookmarkedSectionVisible(page);
	}

	public boolean bookmarkAppSeeOnTheBookmarkSection() {
		return DragAndDropBlocksPageUtils.bookmarkAppSeeOnTheBookmarkSection(page);
	}

	public boolean isAppRemovedFromBookmarkSection(String appName) {
		return DragAndDropBlocksPageUtils.isAppRemovedFromBookmarkSection(page, appName);
	}

	public boolean isBookmarkedSectionNotVisible() {
		return DragAndDropBlocksPageUtils.isBookmarkedSectionNotVisible(page);
	}

	// created app display in all apps section
	public boolean isAppDisplayedInAllAppsSection(String appName) {
		return DragAndDropBlocksPageUtils.isAppDisplayedInAllAppsSection(page, appName);
	}

	public void clickOnDiscovrableApps() {
		DragAndDropBlocksPageUtils.clickOnDiscovrableApps(page);
	}

	public boolean createdAppDisplayInDiscoverableApp(String appName) {
		return DragAndDropBlocksPageUtils.createdAppDisplayInDiscoverableApp(page, appName);
	}

	public void clickOnSystemApps() {
		DragAndDropBlocksPageUtils.clickOnSystemApps(page);
	}

	public boolean isAppDisplayedInSystemAppsSection(String appName) {
		return DragAndDropBlocksPageUtils.isAppDisplayedInSystemAppsSection(page, appName);
	}

}