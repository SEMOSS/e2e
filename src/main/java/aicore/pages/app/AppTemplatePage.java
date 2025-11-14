package aicore.pages.app;

import com.microsoft.playwright.Page;

import aicore.utils.page.app.AppTemplatePageUtils;

public class AppTemplatePage {

	private Page page;

	public AppTemplatePage(Page page) {
		this.page = page;
	}

	public void verifyPageWithTitle(String title) {
		AppTemplatePageUtils.verifyPageWithTitle(title, page);
	}

	public void verifyDescription(String description) {
		AppTemplatePageUtils.verifyDescription(description, page);
	}

	public void verifyInputFieldWithLabel(String label) {
		AppTemplatePageUtils.verifyInputFieldWithLabel(label, page);
	}

	public void verifySubmitButton() {
		AppTemplatePageUtils.verifySubmitButton(page);
	}

	public void clickPreviewButton() {
		AppTemplatePageUtils.clickPreviewButton(page);
	}

	public void clickOnQuestionBlock() {
		AppTemplatePageUtils.clickOnQuestionBlock(page);
	}

	public void addDescription(String description) {
		AppTemplatePageUtils.addDescription(description, page);
	}

	public void selectTemplateFromList(String templateName) {
		AppTemplatePageUtils.selectTemplateFromList(templateName, page);
	}

	public void clickClosePreviewButton() {
		AppTemplatePageUtils.clickClosePreviewButton(page);
	}

	public void verifyDescriptionInPreview(String description) {
		AppTemplatePageUtils.verifyDescriptionInPreview(description, page);
	}

	public void verifyInputFieldWithLabelInPreview(String label) {
		AppTemplatePageUtils.verifyInputFieldWithLabelInPreview(label, page);
	}

	public void closePreviewWindow() {
		AppTemplatePageUtils.closePreviewWindow(page);
	}

	public void verifySubmitButtonInPreview() {
		AppTemplatePageUtils.verifySubmitButtonInPreview(page);
	}

	public void verifyPageWithTitleInPreview(String title) {
		AppTemplatePageUtils.verifyPageWithTitleInPreview(title, page);
	}

	public void verifyPageWithtitleText(String titleText) {
		AppTemplatePageUtils.verifyPageWithtitleText(titleText, page);
	}

	public void verifyDescriptionBelowTitle(String description) {
		AppTemplatePageUtils.verifyDescriptionBelowTitle(description, page);
	}

	public void verifyHyperlink(String text, String url) {
		AppTemplatePageUtils.verifyHyperlink(text, url, page);
	}

	public String getCurrentUrl() {
		return AppTemplatePageUtils.getCurrentUrl(page);
	}

	public void getBackPage() {
		AppTemplatePageUtils.getBackPage(page);
	}

	public void verifyDescriptionBelowTitleOfBlock(String blockTitle, String description) {
		AppTemplatePageUtils.verifyDescriptionBelowTitleOfBlock(blockTitle, description, page);
	}

	public void verifyHyperlinkText(String text, String blockTitle, String url) {
		AppTemplatePageUtils.verifyHyperlinkText(text, blockTitle, url, page);
	}

	public void clickOnHyperlinkText(String text) {
		AppTemplatePageUtils.clickOnHyperlinkText(text, page);
	}

	public void fillDestinationUrl(String url) {
		AppTemplatePageUtils.fillDestinationUrl(url, page);
	}

	public void clickSaveButtonOfTheApp() {
		AppTemplatePageUtils.clickSaveButtonOfTheApp(page);
	}

	public void verifyAppPageTitle(String title) {
		AppTemplatePageUtils.verifyAppPageTitle(title, page);
	}

	public void clickOnAppPageTitle(String title) {
		AppTemplatePageUtils.clickOnAppPageTitle(title, page);
	}

	public void changeAppPageTitle(String oldTitle, String newTitle) {
		AppTemplatePageUtils.changeAppPageTitle(oldTitle, newTitle, page);
	}

	// MultiPage App
	public void verifyAppPageSubTitle(String title) {
		AppTemplatePageUtils.verifyAppPageSubTitle(title, page);
	}

	public String userSeePage1() {
		return AppTemplatePageUtils.userSeePage1(page);
	}

	public String userSeeTeamplatePageTitle() {
		return AppTemplatePageUtils.userSeeTeamplatePageTitle(page);
	}

	public boolean userSeeTheHyperlink(String hyperlink) {
		return AppTemplatePageUtils.userSeeTheHyperlink(page, hyperlink);
	}

	public boolean dropChartOnPage(String titleOfPage) {
		return AppTemplatePageUtils.dropChartOnPage(page, titleOfPage);
	}

	public String userSeeResourceTitle() {
		return AppTemplatePageUtils.userSeeResourceTitle(page);
	}

	public String userSeeAboutTitle() {
		return AppTemplatePageUtils.userSeeAboutTitle(page);
	}

	// Variable Guide App
	public String userSeeVariableGuideBlocksTitle(String blockTitle) {
		return AppTemplatePageUtils.userSeeVariableGuideBlocksTitle(page, blockTitle);
	}

	public boolean userSeeTheFontStyleAndSizeBlock(String block) {
		return AppTemplatePageUtils.userSeeTheFontStyleAndSizeBlock(page, block);
	}

	public void selectFontStyle(String fontName) {
		AppTemplatePageUtils.selectFontStyle(page, fontName);
	}

	public void changeFontSize(String size) {
		AppTemplatePageUtils.changeFontSize(page, size);
	}

	public String getSelectedFont() {
		return AppTemplatePageUtils.getSelectedFont(page);
	}

	public String getFontSize() {
		return AppTemplatePageUtils.getFontSize(page);
	}

	public void clickOSubmitBlock() {
		AppTemplatePageUtils.clickOSubmitBlock(page);
	}

	public void clickOnResponseBlock() {
		AppTemplatePageUtils.clickOnResponseBlock(page);
	}

	// nlp teamplate
	public void selectNotebookFromlist(String notebookName) {
		AppTemplatePageUtils.selectNotebookFromlist(page, notebookName);
	}

	public void selectModelForNLPTemplate(String modelName, String queryName) {
		AppTemplatePageUtils.selectModelForNLPTemplate(page, modelName, queryName);
	}

	public void clickOnFetchDataButton() {
		AppTemplatePageUtils.clickOnFetchDataButton(page);
	}

	public void enterQueryForNLPTemplate(String query) {
		AppTemplatePageUtils.enterQueryForNLPTemplate(page, query);
	}

	public boolean validateAges(String condition, int number) {
		return AppTemplatePageUtils.validateAges(page, condition, number);
	}

	public void verifyAppPageDescription(String descriptionText) {
		AppTemplatePageUtils.verifyAppPageDescription(descriptionText, page);
	}

	public boolean isButtonEnabled(String buttonText) {
		return AppTemplatePageUtils.isButtonEnabled(buttonText, page);
	}

	public boolean verifyCreatedModelsInList() {
		return AppTemplatePageUtils.verifyCreatedModelsInList(page);
	}
}
