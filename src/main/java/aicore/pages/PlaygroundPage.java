package aicore.pages;

import com.microsoft.playwright.Page;

import aicore.utils.PlaygroundPageUtils;

public class PlaygroundPage {
     private Page page;

	public PlaygroundPage(Page page) {
		this.page = page;
	}

    public void clickOnPlaygroundAppButton() {
     PlaygroundPageUtils.clickOnPlaygroundAppButton(page);
    }

    public void verifyButtons(String buttonName) {
     PlaygroundPageUtils.verifyButtons(page, buttonName);
    }

    public void verifyTextareaPlaceholder(String placeholder) {
    PlaygroundPageUtils.verifyTextareaPlaceholder(page, placeholder);
    }

    public void enterPromptInTextarea(String prompt) {
        PlaygroundPageUtils.enterPromptInTextarea(page, prompt);
    }

    public void clickOnOpenConfigurationMenuButton(String buttonName) {
        PlaygroundPageUtils.clickOnOpenConfigurationMenuButton(page, buttonName);
    }

    public void verifyConfigurationMenuIsOpened() {
        PlaygroundPageUtils.verifyConfigurationMenuIsOpened(page);
    }

    public void verifyButtonIsEnabled(String buttonName) {
        PlaygroundPageUtils.verifyButtonIsEnabled(page, buttonName);
    }

    public void verifyButtonIsDisabled(String buttonName) {
        PlaygroundPageUtils.verifyButtonIsDisabled(page, buttonName);
    }
}
