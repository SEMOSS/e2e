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

	public void closeBlocksOption() {
		DragAndDropBlocksPageUtils.closeBlocksOption(page);
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

	public void searchBlock(String blockName) {
		DragAndDropBlocksPageUtils.searchBlock(page, blockName);
	}

	public void clickOnTerminalCard() {
		DragAndDropBlocksPageUtils.clickOnTerminalCard(page);
	}

	public void takeChartScreenshot(String actualImagePath, String chart) {
		DragAndDropBlocksPageUtils.takeChartScreenshot(page, actualImagePath, chart);
	}

	// Area Chart
	public void clickOnAreaChartTOViewOptions() {
		DragAndDropBlocksPageUtils.clickOnAreaChartTOViewOptions(page);
	}

	public boolean canSeeDuplicateIcon() {
		return DragAndDropBlocksPageUtils.canSeeDuplicateIcon(page);
	}

	public int getInitialCount(String blockName) {
		return DragAndDropBlocksPageUtils.getInitialcount(page, blockName);
	}

	public void clickOnDuplicateIcon() {
		DragAndDropBlocksPageUtils.clickOnDuplicateIcon(page);
	}

	public boolean duplicatedChartIsVisiable(int expectedcount, String blockName) {
		return DragAndDropBlocksPageUtils.duplicatedChartIsVisiable(page, expectedcount, blockName);
	}

	public boolean canSeeDeleteIcon() {
		return DragAndDropBlocksPageUtils.canSeeDeleteIcon(page);
	}

	public void clickOnDeleteIcon() {
		DragAndDropBlocksPageUtils.clickOnDeleteIcon(page);
	}

	public boolean chartIsRemoved(int count, String chartName) {
		return DragAndDropBlocksPageUtils.chartIsRemoved(page, count, chartName);
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

	public int waitForChartCount(int expectedCount) {
		return DragAndDropBlocksPageUtils.waitForChartCount(page, expectedCount);
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

	// bar charts tools
	public void clickOnToolTab() {
		DragAndDropBlocksPageUtils.clickOnToolTab(page);
	}

	public void applyConditional(String value) {
		DragAndDropBlocksPageUtils.applyConditional(page, value);
	}

	public boolean isBarChartVisible() {
		return DragAndDropBlocksPageUtils.isBarChartVisible(page);
	}

	public boolean performAddColor() {
		return DragAndDropBlocksPageUtils.performAddColor(page);
	}

	public void performCheckColor() {
		DragAndDropBlocksPageUtils.performCheckColor(page);
	}

	public void clickOnLegendOptionAndTurnOnTheToggle() {
		DragAndDropBlocksPageUtils.clickOnLegendOptionAndTurnOnTheToggle(page);
	}

	public void clickOnEditXAxisOption(String axis) {
		DragAndDropBlocksPageUtils.clickOnEditXAxisOption(page, axis);
	}

	public void updateToolSettings(String axis, String xAxisSettings) {
		DragAndDropBlocksPageUtils.updateToolSettings(page, axis, xAxisSettings);
	}

	public void turnOnValueLabelToggle() {
		DragAndDropBlocksPageUtils.turnOnValueLabelToggle(page);
	}

	public void updateValueLabelSettings(String valueLabelSettings) {
		DragAndDropBlocksPageUtils.updateValueLabelSettings(page, valueLabelSettings);
	}

	public void clickOnToolOption(String toolName) {
		DragAndDropBlocksPageUtils.clickOnToolOption(page, toolName);
	}

	public void updateBarStyle(String barStyleValue) {
		DragAndDropBlocksPageUtils.updateBarStyle(page, barStyleValue);
	}

	public void clickOnTabInLeftPanel(String tabName) {
		DragAndDropBlocksPageUtils.clickOnTabInLeftPanel(page, tabName);
	}

	public void clickOnMarkdownContainerToSelectIt() {
		DragAndDropBlocksPageUtils.clickOnMarkdownContainerToSelectIt(page);
	}
	public void clickOnBlockSettingsOption() {
		DragAndDropBlocksPageUtils.clickOnBlockSettingsOption(page);
	}
}