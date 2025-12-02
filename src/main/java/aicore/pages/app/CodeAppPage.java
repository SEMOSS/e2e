package aicore.pages.app;

import com.microsoft.playwright.Page;

import aicore.utils.page.app.CodeAppPageUtils;

public class CodeAppPage {
    private Page page;

	public CodeAppPage(Page page) {
		this.page = page;
	}

    public void clickOnFileUploadButton() {
        CodeAppPageUtils.clickOnFileUploadButton(page);
	}

    public void clickOnUnzipCheckbox() {
        CodeAppPageUtils.clickOnUnzipCheckbox(page);
    }

    public void clickOnPublishButton() {
        CodeAppPageUtils.clickOnPublishButton(page);
    }
}
