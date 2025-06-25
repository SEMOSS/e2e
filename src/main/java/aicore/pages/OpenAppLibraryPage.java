package aicore.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import aicore.utils.AppLibraryPageUtils;
import aicore.utils.HomePageUtils;

public class OpenAppLibraryPage {

	private Page page;
	private String timestamp;

	public OpenAppLibraryPage(Page page, String timestamp) {
		this.page = page;
		this.timestamp = timestamp;
	}

	public void clickOnCreateNewAppButton() {
		AppLibraryPageUtils.clickOnCreateNewAppButton(page);
	}

	public void clickOnGetStartedButtonInDragAndDrop(String appType) {
		AppLibraryPageUtils.clickOnGetStartedButtonInDragAndDrop(page, appType);
	}

	public void enterAppName(String appName) {
		AppLibraryPageUtils.enterAppName(page, appName, timestamp);
	}

	public void enterAppDescription(String appDescription) {
		AppLibraryPageUtils.enterAppDescription(page, appDescription);
	}

	public void enterTags(String tagName) {
		AppLibraryPageUtils.enterTags(page, tagName);
	}

	public void clickOnCreateButton() {
		AppLibraryPageUtils.clickOnCreateButton(page);
	}

	public boolean verifyPage1IsVisible() {
		return AppLibraryPageUtils.verifyPage1IsVisible(page);
	}

	public boolean verifyWelcomeTextboxIsVisible() {
		return AppLibraryPageUtils.verifyWelcomeTextboxIsVisible(page);
	}

	public String verifyWelcomeText() {
		return AppLibraryPageUtils.verifyWelcomeText(page);
	}

//	public void navigateToHomePageFromAppEditPage() {
//		AppLibraryPageUtils.navigateToHomePageFromAppEditPage(page);
//	}

	public void navigatesToHomePage() {
		HomePageUtils.navigateToHomePage(page);
	}

	public void searchApp(String appName) {
		AppLibraryPageUtils.searchApp(page, appName, timestamp);
	}

	public void clickOnSearchedApp(String appName) {
		AppLibraryPageUtils.clickOnSearchedApp(page, appName, timestamp);
	}

	public void clickOnEditButton() {
		AppLibraryPageUtils.clickOnEditButton(page);
	}

	public void clickOnBlocksOption() {
		AppLibraryPageUtils.clickOnBlocksOption(page);
	}

	public void blockDropPosition() {
		AppLibraryPageUtils.blockDropPosition(page);
	}

	public void mouseHoverOnTextSectionBlock(String blockName) {
		AppLibraryPageUtils.mouseHoverOnTextSectionBlock(page, blockName);
	}

	public String verifyHeadingBlockTextMessage() {
		return AppLibraryPageUtils.verifyHeadingBlockTextMessage(page);
	}

	public void clickOnBlockSettingsOption() {
		AppLibraryPageUtils.clickOnBlockSettingsOption(page);
	}

	public void userSelectsTheAppearanceTab() {
		AppLibraryPageUtils.userSelectsTheAppearanceTab(page);
	}

	public void enterDestination(String destination) {
		AppLibraryPageUtils.enterDestination(page, destination);
	}

	public void enterText(String text) {
		AppLibraryPageUtils.enterText(page, text);
	}

	public void enterMarkdown(String markdown) {
		AppLibraryPageUtils.enterMarkdown(page, markdown);
	}

	public void selectTextStyle(String textStyles) {
		AppLibraryPageUtils.selectTextStyle(page, textStyles);
	}

	public void selectTextFont(String fontName) {
		AppLibraryPageUtils.selectTextFont(page, fontName);
	}

	public void selectTextColor(String hexColor) {
		AppLibraryPageUtils.selectTextColor(page, hexColor);
	}

	public void selectTextAlign(String textAlign) {
		AppLibraryPageUtils.selectTextAlign(page, textAlign);
	}

	public void clickOnSaveAppButton() {
		AppLibraryPageUtils.clickOnSaveAppButton(page);
	}

	public Locator textSectionDragAndDroppedBlockLocator(String blockName, String blockText) {
		return AppLibraryPageUtils.textSectionDragAndDroppedBlockLocator(page, blockName, blockText);
	}

	public String getBlockText(String blockName, String blockText) {
		return AppLibraryPageUtils.getBlockText(page, blockName, blockText);
	}

	public String getBlockTextFont(String blockName, String blockText) {
		return AppLibraryPageUtils.getBlockTextFont(page, blockName, blockText);
	}

	public String getBlockTextStyle(String blockName, String blockText) {
		return AppLibraryPageUtils.getBlockTextStyle(page, blockName, blockText);
	}

	public String getBlockTextColor(String blockName, String blockText) {
		return AppLibraryPageUtils.getBlockTextColor(page, blockName, blockText);
	}

	public String getBlockTextAlign(String blockName, String blockText) {
		return AppLibraryPageUtils.getBlockTextAlign(page, blockName, blockText);
	}

	public void clickOnLink(String blockText) {
		AppLibraryPageUtils.clickOnLink(page, blockText);
	}

	public String getDestinationUrl(String url) {
		return AppLibraryPageUtils.getDestinationUrl(page, url);
	}

	public void navigateToPreviosPage() {
		AppLibraryPageUtils.navigateToPreviosPage(page);
	}

	public void clickOnNotebooksOption() {
		AppLibraryPageUtils.clickOnNotebooksOption(page);
	}

	public void clickOnCreateNewNotebook() {
		AppLibraryPageUtils.clickOnCreateNewNotebook(page);
	}

	public void enterQueryName(String queryName) {
		AppLibraryPageUtils.enterQueryName(page, queryName);
	}

	public void clickOnQuerySubmitButton() {
		AppLibraryPageUtils.clickOnQuerySubmitButton(page);
	}

	public void selectPage(String pageName) {
		AppLibraryPageUtils.selectPage(page, pageName);
	}

	public void enterCodeInQuery(String code) {
		AppLibraryPageUtils.enterCodeInQuery(page, code);
	}

	public void clickOnRunAllButton() {
		AppLibraryPageUtils.clickOnRunAllButton(page);
	}

	public void selectQueryFromList(String queryName) {
		AppLibraryPageUtils.selectQueryFromList(page, queryName);
	}

	public String getCodeOutput(String codeOutput) {
		return AppLibraryPageUtils.getCodeOutput(page, codeOutput);
	}

	public void clickOnTerminalCard() {
		AppLibraryPageUtils.clickOnTerminalCard(page);
	}

}