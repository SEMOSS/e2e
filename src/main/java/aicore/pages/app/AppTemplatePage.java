package aicore.pages.app;

import com.microsoft.playwright.Page;

import aicore.utils.page.app.AppTemplatePageUtils;

public class AppTemplatePage {

    private Page page;

    public AppTemplatePage(Page page) {
        this.page = page;
    }

    public void verifyPageWithTitle(String title) {
        AppTemplatePageUtils.verifyPageWithTitle(title,page);
    }

    public void verifyDescription(String description) {
        AppTemplatePageUtils.verifyDescription(description,page);
    }

    public void verifyInputFieldWithLabel(String label) {
        AppTemplatePageUtils.verifyInputFieldWithLabel(label,page);
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

     public void verifyAppPageTitle(String title) {
        AppTemplatePageUtils.verifyAppPageTitle(title,page);
    }
     public void clickOnAppPageTitle(String title) {
        AppTemplatePageUtils.clickOnAppPageTitle(title,page);
    }
    public void changeAppPageTitle(String oldTitle, String newTitle) {
        AppTemplatePageUtils.changeAppPageTitle(oldTitle,newTitle,page);
    }

     public void verifyAppPageSubTitle(String title) {
        AppTemplatePageUtils.verifyAppPageSubTitle(title,page);
    }
 }