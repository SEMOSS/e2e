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
    public void clickOnWorkspaceButton() {
        PlaygroundPageUtils.clickOnWorkspaceButton(page);
    }
    public void clickOnCreateNewWorkspaceButton() {
        PlaygroundPageUtils.clickOnCreateNewWorkspaceButton(page);
    }
    public void enterWorkspaceName(String workspaceName) {
        PlaygroundPageUtils.enterWorkspaceName(page, workspaceName);
    }
    public void clickOnNewCreateWorkspaceButton() {
        PlaygroundPageUtils.clickOnNewCreateWorkspaceButton(page);
    }
    public void verifyWorkspaceCreatedWithDescription(String workspaceName, String description) {
        PlaygroundPageUtils.verifyWorkspaceCreatedWithDescription(page, workspaceName, description);
    }

    public void selectWorkspaceFromList(String workspaceName) {
        PlaygroundPageUtils.selectWorkspaceFromList(page, workspaceName);
    }
    public void selectWorkspaceChatFromList(String workspaceName) {
        PlaygroundPageUtils.selectWorkspaceChatFromList(page, workspaceName);
    }
    public void verifyWorkspaceSelectionInMenu(String workspaceName, String menuName) {
        PlaygroundPageUtils.verifyWorkspaceSelectionInMenu(page, workspaceName, menuName);
    }

    public void verifyWorkspaceSelectedInList(String workspaceName) {
        PlaygroundPageUtils.verifyWorkspaceSelectedInList(page, workspaceName);
    }

    public void clickOnDeleteWorkspaceButton() {
        PlaygroundPageUtils.clickOnDeleteWorkspaceButton(page);
    }

    public void verifyWorkspaceDeleted() {
        PlaygroundPageUtils.verifyWorkspaceDeleted(page);
    }

    public void clickOnEditWorkspaceButton() {
        PlaygroundPageUtils.clickOnEditWorkspaceButton(page);
    }

    public void enterWorkspaceDescription(String workspaceDescription) {
        PlaygroundPageUtils.enterWorkspaceDescription(page, workspaceDescription);
    }

    public void clickOnSaveWorkspaceButton() {
        PlaygroundPageUtils.clickOnNewCreateWorkspaceButton(page);
    }

    public void verifyWorkspaceUpdatedWithDescription(String workspaceName, String description) {
        PlaygroundPageUtils.verifyWorkspaceCreatedWithDescription(page, workspaceName, description);
    }

    public void searchWorkspace(String workspaceName) {
        PlaygroundPageUtils.searchWorkspace(page, workspaceName);
    }

    public void verifyWorkspaceDisplayedInSearchResults(String workspaceName) {
        PlaygroundPageUtils.verifyWorkspaceDisplayedInSearchResults(page, workspaceName);
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
    public void clickOnKnowledgeDropdown() {
        PlaygroundPageUtils.clickOnKnowledgeDropdown(page);
    }

    public void saveAddedKnowledgeList() {
        PlaygroundPageUtils.saveAddedKnowledgeList(page);
    }
    public void saveAddedMCPList() {
        PlaygroundPageUtils.saveAddedMCPList(page);
    }

    public void clickOverifyMCPAppVisibleInAvailableTools(String modelName) {
        PlaygroundPageUtils.clickOverifyMCPAppVisibleInAvailableTools(page, modelName);

    }
    public void clickVerifyKnowledgeAppVisibleInAvailableTools(String knowledgeName) {
        PlaygroundPageUtils.clickVerifyKnowledgeVisibleInAvailableTools(page, knowledgeName);

    }

    public void verifyAddedMCPAppSelectedList(String modelName) {
        PlaygroundPageUtils.verifyAddedMCPAppSelectedList(page, modelName);
    }

    public void verifyAddedKnowledgeModelKnowledgeSection(String knowledgeName) {
        PlaygroundPageUtils.verifyAddedKnowledgeModelKnowledgeSection(page, knowledgeName);
    }
    public void verifyAddedMCPModelMCPSection(String modelName) {
        PlaygroundPageUtils.verifyAddedMCPModelMCPSection(page, modelName);
    }

    public void deleteAddedKnowledgeModelKnowledgeSection(String knowledgeName) {
        PlaygroundPageUtils.deleteAddedKnowledgeModelKnowledgeSection(page, knowledgeName);
    }
    public void deleteAddedMCPModelMCPSection(String modelName) {
        PlaygroundPageUtils.deleteAddedMCPModelMCPSection(page, modelName);
    }

    public void verifyKnowledgeRemovedKnowledgeSection(String knowledgeName) {
        PlaygroundPageUtils.verifyKnowledgeRemovedKnowledgeSection(page, knowledgeName);
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

    public void verifyKnowledgeIsChecked(String knowledgeName) {
        PlaygroundPageUtils.verifyKnowledgeIsChecked(page, knowledgeName);
    }

    public void searchModelInSearchbox(String modelName) {
        PlaygroundPageUtils.searchModelInSearchbox(page, modelName);
    }
    public void searchKnowledgeInSearchbox(String knowledgeName) {
        PlaygroundPageUtils.searchKnowledgeInSearchbox(page, knowledgeName);
    }

    public void verifyModelVisibleInDropdown(String modelName) {
        PlaygroundPageUtils.verifyModelVisibleInDropdown(page, modelName);
    }

    public void verifyTemperatureSectionIsDisplayed(String temperature) {
        PlaygroundPageUtils.verifyTemperatureSectionIsDisplayed(page, temperature);
    }
    public void verifyMaxTokenSectionIsDisplayed() {
        PlaygroundPageUtils.verifyMaxTokenSectionIsDisplayed(page);
    }
    public void verifyInstructionsSectionIsDisplayed() {
        PlaygroundPageUtils.verifyInstructionsSectionIsDisplayed(page);
    }

    public void selectModelFromDropdown(String modelName) {
        PlaygroundPageUtils.selectModelFromDropdown(page, modelName);
    }

    public void clickOnSidebarToggleButton() {
            PlaygroundPageUtils.clickOnSidebarToggleButton(page);
    }

    public void verifyPromptPresentInSidebarHistory(String prompt) {
        PlaygroundPageUtils.verifyPromptPresentInSidebarHistory(page, prompt);
    }

    public void hoverOverSidebarHistoryItem(String prompt) {
        PlaygroundPageUtils.hoverOverSidebarHistoryItem(page, prompt);
    }

    public void clickDeleteIconForSidebarHistoryItem(String prompt) {
        PlaygroundPageUtils.clickDeleteIconForSidebarHistoryItem(page, prompt);
    }

    public void verifyPromptNotPresentInSidebarHistory(String prompt) {
        PlaygroundPageUtils.verifyPromptNotPresentInSidebarHistory(page, prompt);
    }

    public void verifySidebarState(String state) {
         if(state.equals("closed")) {
            PlaygroundPageUtils.verifySidebarState(page,false);
        }else if(state.equals("opened")) {
            PlaygroundPageUtils.verifySidebarState(page,true);
        }
       
    }

    public void waitForModelResponse() {
        PlaygroundPageUtils.waitForModelResponse(page);
    }

    public void verifyModelResponseDisplayed() {
        PlaygroundPageUtils.verifyModelResponseDisplayed(page);
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
