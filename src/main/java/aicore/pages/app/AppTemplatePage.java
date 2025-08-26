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

	public void verifyAppPageSubTitle(String title) {
		AppTemplatePageUtils.verifyAppPageSubTitle(title, page);
	}

	public String userSeePage1() {
		return AppTemplatePageUtils.userSeePage1(page);
	}

	public String userSeeLandingPageBlock() {
		return AppTemplatePageUtils.userSeeLandingPageBlock(page);
	}

	public boolean userSeeTheHyperlink(String hyperlink) {
		return AppTemplatePageUtils.userSeeTheHyperlink(page, hyperlink);
	}

	public void verifyHyperlink(String text, String url) {
		AppTemplatePageUtils.verifyHyperlink(text, url, page);
	}

	public boolean dropChartOnLandingPage() {
		return AppTemplatePageUtils.dropChartOnLandingPage(page);
	}

	public void getBackPage() {
		AppTemplatePageUtils.getBackPage(page);
	}

	public String getCurrentUrl() {
		return AppTemplatePageUtils.getCurrentUrl(page);
	}

	public String userSeeResourceTitle() {
		return AppTemplatePageUtils.userSeeResourceTitle(page);
	}

	public String userSeeAboutTitle() {
		return AppTemplatePageUtils.userSeeAboutTitle(page);
	}
}
