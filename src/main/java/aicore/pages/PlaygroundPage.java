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
    public void clickOnMCPDropdown() {
        PlaygroundPageUtils.clickOnMCPDropdown(page);
    }

    public void saveAddedMCPList() {
        PlaygroundPageUtils.saveAddedMCPList(page);
    }

    public void clickOverifyMCPAppVisibleInAvailableTools(String modelName) {
        PlaygroundPageUtils.clickOverifyMCPAppVisibleInAvailableTools(page, modelName);

    }

    public void verifyAddedMCPAppSelectedList(String modelName) {
        PlaygroundPageUtils.verifyAddedMCPAppSelectedList(page, modelName);
    }

    public void verifyAddedMCPModelMCPSection(String modelName) {
        PlaygroundPageUtils.verifyAddedMCPModelMCPSection(page, modelName);
    }

    public void deleteAddedMCPModelMCPSection(String modelName) {
        PlaygroundPageUtils.deleteAddedMCPModelMCPSection(page, modelName);
    }

    public void verifyMCPModelRemovedMCPSection(String modelName) {
        PlaygroundPageUtils.verifyMCPModelRemovedMCPSection(page, modelName);
    }

    public void searchAndSelectMCPModel(String modelName) {
        PlaygroundPageUtils.searchAndSelectMCPModel(page, modelName);
    }

    public void verifyModelCatalogDropdownPresent(String modelName) {
        PlaygroundPageUtils.verifyModelCatalogDropdownPresent(page, modelName);
    }

    public void clickOnModelCatalogDropdown() {
        PlaygroundPageUtils.clickOnModelCatalogDropdown(page);
    }

    public void verifyModelIsChecked(String modelName) {
        PlaygroundPageUtils.verifyModelIsChecked(page, modelName);
    }

    public void searchModelInSearchbox(String modelName) {
        PlaygroundPageUtils.searchModelInSearchbox(page, modelName);
    }

    public void verifyModelVisibleInDropdown(String modelName) {
        PlaygroundPageUtils.verifyModelVisibleInDropdown(page, modelName);
    }

    public void selectModelFromDropdown(String modelName) {
        PlaygroundPageUtils.selectModelFromDropdown(page, modelName);
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
