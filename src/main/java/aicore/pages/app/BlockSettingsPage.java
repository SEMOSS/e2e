package aicore.pages.app;

import com.microsoft.playwright.Page;

import aicore.utils.page.app.BlockSettingsUtils;

public class BlockSettingsPage {
	private Page page;

	public BlockSettingsPage(Page page) {
		this.page = page;
	}

	public void clickOnBlockSettingsOption() {
		BlockSettingsUtils.clickOnBlockSettingsOption(page);
	}

	public void userSelectsTheAppearanceTab() {
		BlockSettingsUtils.userSelectsTheAppearanceTab(page);
	}

	public void enterDestination(String destination) {
		BlockSettingsUtils.enterDestination(page, destination);
	}

	public void enterText(String text) {
		BlockSettingsUtils.enterText(page, text);
	}

	public void enterMarkdown(String markdown) {
		BlockSettingsUtils.enterMarkdown(page, markdown);
	}

	public void selectTextStyle(String textStyles) {
		BlockSettingsUtils.selectTextStyle(page, textStyles);
	}

	public void selectTextFont(String fontName) {
		BlockSettingsUtils.selectTextFont(page, fontName);
	}

	public void selectTextColor(String hexColor) {
		BlockSettingsUtils.selectTextColor(page, hexColor);
	}

	public void selectTextAlign(String textAlign) {
		BlockSettingsUtils.selectTextAlign(page, textAlign);
	}

	public void selectQueryFromList(String queryName) {
		BlockSettingsUtils.selectQueryFromList(page, queryName);
	}

	public void clickOnDataTab() {
		BlockSettingsUtils.clickOnDataTab(page);
	}

	public void selectFrame(String frameId) {
		BlockSettingsUtils.selectFrame(page, frameId);
	}

	public void dragColumnToTargetField(String columnName, String targetField) {
		BlockSettingsUtils.dragColumnToTargetField(page, columnName, targetField);
	}

	public boolean verifyColumnDroppedInCorrectField(String columnName, String targetField) {
		return BlockSettingsUtils.verifyColumnDroppedInCorrectField(page, columnName, targetField);
	}

	public void enterValueInGraphTD(String value) {
		BlockSettingsUtils.enterValueInGraphTD(page, value);
	}

	public void closeBlockSettings() {
		BlockSettingsUtils.closeBlockSettings(page);
	}
}
