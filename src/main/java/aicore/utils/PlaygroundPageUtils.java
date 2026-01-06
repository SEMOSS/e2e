package aicore.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;

public class PlaygroundPageUtils {

    private static final String PLAYGROUND_APP_BUTTON_XPATH = "//*[text()='Experiment in our Playground™']/../../..//a";
    private static final String PROMPT_THE_MODEL_BUTTON_LABEL = "Prompt the Model";
    private static final String CONFIGURATION_MENU_XPATH = "//form";
    private static final String ADD_MCP_TOOL_XPATH = "//div[text()='MCPs']/following-sibling::button";
    private static final String ADD_KNOWLEDGE_TOOL_XPATH = "//div[text()='Knowledge']/following-sibling::button";
    private static final String SAVE_BUTTON_XPATH = "//button[text()='Save']";
    private static final String MCP_TOOL_XPATH = "//p[contains(text(),'{MCP}')]";
    private static final String CREATE_WORKSPACE_XPATH = "//button[text()='Create a Workspace']";
    private static final String CARD_TITLE_XPATH = "//div[@data-slot='card-title']";
    private static final String WORKSPACE_PROFILE_XPATH = "//span[@data-slot='tooltip-trigger']//button//span";
    private static final String SEARCH_WORKSPACE_XPATH = "//div/div[@role='group']//input[@placeholder='Search']";
    private static final String WORKSPACE_NAME_INPUT_XPATH = "workspaceForm-textField-name";
    private static final String WORKSPACE_DESCRIPTION_INPUT_XPATH = "workspaceForm-description-txt";
    private static final String WORKSPACE_CREATE_XPATH = "workspaceForm-submit-btn";
    private static final String WORKSPACE_MENU_XPATH = "//div/button[@aria-haspopup='menu']";
    private static final String WORKSPACE_NEW_CHAT_XPATH = "//div[@data-slot='card-title' and text()='{workspaceName}']/../../following-sibling::div//button";
    private static final String NO_WORKSPACE_PRESENT_XPATH = "//*[text()='No results found']";
    private static final String WORKSPACE_MENU_OPTION_XPATH = "//*[contains(@role, 'menuitem') and text()='{optionName}']";
    private static final String SIDEBAR_TOGGLE_XPATH = "//button[@data-slot='sidebar-trigger']";
    private static final String NO_PROMPT_EXIST_XPATH = "//div[@data-slot='sidebar-group']//*[text()='No rooms found']";
    private static final String PROMPT_XPATH = "//div[@data-slot='sidebar-group']//*[text()='{prompt}']";
    private static final String PROMPT_DELETE_BUTTON_XPATH = "//div[@data-slot='sidebar-group']//*[text()='{prompt}']//following-sibling::button";
    private static final String SIDEBAR_XPATH = "//div[@data-slot='sidebar-group']";
    private static final String RESPONSE_XPATH = "//div//span[text()='Llama3-70B-Instruct']";
    private static final String LOADING_SPINNER_XPATH = "//button[@aria-label='Prompt the Model']//*[@aria-label='Loading']";
    private static final String MCP_SEARCH_BAR_XPATH = "//label[text()='Available Tools']//..//..//input[@placeholder='Search']";
    private static final String MCP_CHECKBOX_XPATH = "//p[contains(text(),'{MCP}')]//../../button";
    private static final String KNOWLEDGE_CHECKBOX_XPATH = "//*[contains(text(),'{KNOWLEDGE}')]//../../button";
    private static final String KNOWLEDGE_CHECKBOX_STATUS_XPATH = "//*[contains(text(),'{KNOWLEDGE}')]//../../button[@role='checkbox']//span";
    private static final String MCP_ADDED_MODEL_XPATH = "//*[contains(text(),'Selected Tools')]//..//span";
    private static final String KNOWLEDGE_LIST_XPATH = "//div[text()='Knowledge']//../..//div/span";
    private static final String TEMPERATURE_INPUT_XPATH = "//label[contains(text(),'Temperature')]";
    private static final String MAX_TOKEN_INPUT_XPATH = "//*[text()='Max Token']//../../input";
    private static final String INSTRUCTION_INPUT_XPATH = "//*[text()='Instructions']//../../textarea";
    private static final String KNOWLEDGE_DELETE_XPATH = "//div[text()='Knowledge']//../..//span[contains(text(),'{KNOWLEDGE}')]//following-sibling::button";
    private static final String MCP_LIST_XPATH = "//div[text()='MCPs']//../..//span[contains(text(),'{MCP}')]";
    private static final String MCP_DELETED_LIST_XPATH = "//div[text()='MCPs']//../..//span[contains(text(),'Play')]/..";
    private static final String KNOWLEDGE_LIST_MESSAGE_XPATH = "//div[text()='Knowledge']//../..//span";
    private static final String MCP_LIST_MESSAGE_XPATH = "//div[text()='MCPs']//../..//span";
    private static final String MCP_DELETE_XPATH = "//div[text()='MCPs']//../..//span[contains(text(),'{MCP}')]//following-sibling::button";
    private static final String MODEL_CATALOG_DROPDOWN = "//div//label[text()='Model']//following-sibling::button//span";
    private static final String MODEL_CATALOG_DROPDOWN_CHECKED = "//div[@role='group']//div//span[contains(text(),'{catalogName}')]/../../*[1]";
    private static final String MODEL_CATALOG_SEARCH_INPUT = "//div/div/input[@placeholder='Search']";
    private static final String KNOWLEDGE_CATALOG_SEARCH_INPUT = "//div/div/input[@placeholder='Search']";
    private static final String MODEL_ITEM_BY_NAME = "//div[@data-slot='command-group']//div//div//div//span[contains(text(),'{modelName}')]";
    private static final String MODEL_CHECKBOX_BY_NAME = ".//div[contains(@class,'model-item') and normalize-space(text())='{MODEL}']//input[@type='checkbox']";

    public static void clickOnPlaygroundAppButton(Page page) {
        Locator anchor = page.locator(PLAYGROUND_APP_BUTTON_XPATH);
        CommonUtils.removeTargetAttribute(anchor);
        anchor.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public static void verifyButtons(Page page, String buttonName) {
        page.getByLabel(buttonName).isVisible();
        page.getByLabel(buttonName).isEnabled();
    }

    public static void verifyTextareaPlaceholder(Page page, String placeholder) {
        Locator textarea = page.locator("textarea");
        String actualPlaceholder = textarea.getAttribute("placeholder");
        if (!actualPlaceholder.equals(placeholder)) {
            throw new AssertionError("Expected placeholder: " + placeholder + ", but found: " + actualPlaceholder);
        }
    }
    public static void clickOnWorkspaceButton(Page page) {
        Locator workspaceButton = page.locator("//li//a[@aria-label ='Workspace']");
        if (workspaceButton.isEnabled()) {
            workspaceButton.click();
        } else {
            throw new AssertionError("The 'Workspace' button is disabled and cannot be clicked.");
        }
    }

    public static void clickOnCreateNewWorkspaceButton(Page page) {
        Locator createNewWorkspaceButton = page.locator(CREATE_WORKSPACE_XPATH);
        if (createNewWorkspaceButton.isEnabled()) {
            createNewWorkspaceButton.click();
        } else {
            throw new AssertionError("The 'Create New Workspace' button is disabled and cannot be clicked.");
        }
    }
    public static void clickOnNewCreateWorkspaceButton(Page page) {
        Locator createNewWorkspaceButton = page.getByTestId(WORKSPACE_CREATE_XPATH);
        if (createNewWorkspaceButton.isEnabled()) {
            createNewWorkspaceButton.click();
        } else {
            throw new AssertionError("The 'Create New Workspace' button is disabled and cannot be clicked on the form");
        }
    }

    public static void verifyWorkspaceCreatedWithDescription(Page page, String workspaceName, String description) {
        Locator createdWorkspace = page.locator("//div[text()='" + workspaceName + "']");
        AICorePageUtils.waitFor(createdWorkspace);
        if (!createdWorkspace.isVisible()) {
            throw new AssertionError("Workspace '" + workspaceName + "' is not created.");
        }
        Locator workspaceDescription = page.locator("//div[text()='" + workspaceName + "']");
        if (!workspaceDescription.isVisible()) {
            throw new AssertionError("Expected description: " + description + ", but found: " + workspaceDescription.textContent());
        }
    }

    public static void selectWorkspaceFromList(Page page, String workspaceName) {
        Locator workspace = page.locator("//div[text()='" + workspaceName + "']");
        if (workspace.isEnabled()) {
            workspace.click();
        } else {
            throw new AssertionError("The workspace '" + workspaceName + "' is disabled and cannot be selected.");
        }
    }
    public static void selectWorkspaceChatFromList(Page page, String workspaceName) {
        Locator workspace = page.locator(WORKSPACE_NEW_CHAT_XPATH.replace("{workspaceName}", workspaceName));
        if (workspace.isEnabled()) {
            workspace.click();
        } else {
            throw new AssertionError("The workspace '" + workspaceName + "' is disabled and cannot be selected.");
        }
    }

    public static void verifyWorkspaceSelectedInList(Page page, String workspaceName) {
        Locator selectedWorkspace = page.locator("//div[text()='" + workspaceName + "']");
        AICorePageUtils.waitFor(selectedWorkspace);
        if (!selectedWorkspace.isVisible()) {
            throw new AssertionError("Workspace '" + workspaceName + "' is not selected in the list.");
        }
    }

    public static void clickOnDeleteWorkspaceButton(Page page) {
        Locator WorkspaceMenuButton = page.locator(WORKSPACE_MENU_XPATH);
        if (WorkspaceMenuButton.isEnabled()) {
            WorkspaceMenuButton.click();
        } else {
            throw new AssertionError("The 'Workspaces menu' button is disabled and cannot be clicked.");
        }
        Locator deleteWorkspaceButton = page.locator(WORKSPACE_MENU_OPTION_XPATH.replace("{optionName}", "Delete"));
        if (deleteWorkspaceButton.isEnabled()) {
            deleteWorkspaceButton.click();
        } else {
            throw new AssertionError("The 'Delete Workspace' button is disabled and cannot be clicked.");
        }
    }
    public static void clickOnEditWorkspaceButton(Page page) {
        Locator WorkspaceMenuButton = page.locator(WORKSPACE_MENU_XPATH);
        if (WorkspaceMenuButton.isEnabled()) {
            WorkspaceMenuButton.click();
        } else {
            throw new AssertionError("The 'Workspaces menu' button is disabled and cannot be clicked.");
        }
        Locator editWorkspaceButton = page.locator(WORKSPACE_MENU_OPTION_XPATH.replace("{optionName}", "Edit"));
        if (editWorkspaceButton.isEnabled()) {
            editWorkspaceButton.click();
        } else {
            throw new AssertionError("The 'Edit Workspace' button is disabled and cannot be clicked.");
        }
    }

    public static void verifyWorkspaceDeleted(Page page) {
        Locator noWorkspaceExist = page.locator(NO_WORKSPACE_PRESENT_XPATH);
        AICorePageUtils.waitFor(noWorkspaceExist);
        if (!noWorkspaceExist.isVisible()) {
            throw new AssertionError("Workspace is still present in the list.");
        }
    }
  
    public static void enterWorkspaceName(Page page, String workspaceName) {
        Locator createNewWorkspaceButton = page.getByTestId(WORKSPACE_NAME_INPUT_XPATH);
        if (createNewWorkspaceButton.isEnabled()) {
            createNewWorkspaceButton.fill(workspaceName);
        } else {
            throw new AssertionError("The 'Workspace Name' input field is disabled and cannot be filled.");
        }
    }
    public static void enterWorkspaceDescription(Page page, String workspaceDescription) {
        Locator createNewWorkspaceButton = page.getByTestId(WORKSPACE_DESCRIPTION_INPUT_XPATH);
        if (createNewWorkspaceButton.isEnabled()) {
            createNewWorkspaceButton.fill(workspaceDescription);
        } else {
            throw new AssertionError("The 'Workspace Description' input field is disabled and cannot be filled.");
        }
    }

    public static void searchWorkspace(Page page, String workspaceName) {
        Locator workspaceSearchBox = page.locator(SEARCH_WORKSPACE_XPATH);
        if (workspaceSearchBox.isEnabled()) {
            workspaceSearchBox.fill(workspaceName);
            page.waitForTimeout(500);
        } else {
            throw new AssertionError("The 'Workspace Search' input field is disabled and cannot be filled.");
        }
    }

    public static void verifyWorkspaceDisplayedInSearchResults(Page page, String workspaceName) {
        Locator searchedWorkspace = page.locator(CARD_TITLE_XPATH);
        AICorePageUtils.waitFor(searchedWorkspace);
        String workspaceTitle = searchedWorkspace.textContent();
        if (!workspaceTitle.equals(workspaceName)) {
            throw new AssertionError("Search function failed. Expected workspace: " + workspaceName + ", but found: " + workspaceTitle);
        }
    }

    public static void verifyWorkspaceSelection(Page page, String workspaceName) {
        Locator workspaceProfile = page.locator(WORKSPACE_PROFILE_XPATH);
        AICorePageUtils.waitFor(workspaceProfile);
        String workspaceProfileName = workspaceProfile.textContent();
        if (!workspaceProfileName.equals(workspaceName)) {
            throw new AssertionError("did not select the correct workspace. Expected workspace: " + workspaceName + ", but found: " + workspaceProfileName);
        }
    }

    public static void enterPromptInTextarea(Page page, String prompt) {
        Locator textarea = page.locator("textarea");
        textarea.fill(prompt);
    }

    public static void clickOnOpenConfigurationMenuButton(Page page, String buttonName) {
        Locator button = page.getByLabel(buttonName);
        if (button.isEnabled()) {
            button.click();
        } else {
            throw new AssertionError("The button '" + buttonName + "' is disabled and cannot be clicked.");
        }
    }

    public static void verifyModelCatalogDropdownPresent(Page page, String modelName) {
        Locator dropdown = page.locator(MODEL_CATALOG_DROPDOWN);
        AICorePageUtils.waitFor(dropdown);
        String dropdownText = dropdown.textContent();
        if (!dropdown.isVisible() && (dropdownText == null || dropdownText.isEmpty())) {
            throw new AssertionError("Model catalog text not visible");
        }
    }

    public static void clickOnModelCatalogDropdown(Page page) {
        Locator dropdown = page.locator(MODEL_CATALOG_DROPDOWN);
        AICorePageUtils.waitFor(dropdown);
        try {
            dropdown.click();
        } catch (Exception e) {
            dropdown.click(new Locator.ClickOptions().setForce(true));
        }
    }

    public static void verifyKnowledgeIsChecked(Page page, String knowledgeName) {
            Locator checkbox = page.locator(KNOWLEDGE_CHECKBOX_STATUS_XPATH.replace("{KNOWLEDGE}", knowledgeName));
            AICorePageUtils.waitFor(checkbox);
            String checkboxState = checkbox.getAttribute("data-state");
            if (!checkboxState.equals("checked")) {
                throw new AssertionError("Knowledge with name'" + knowledgeName + "' is not checked in catalog ");
            }
    }
    public static void verifyModelIsChecked(Page page, String modelName) {
        Locator dropdown = page.locator(MODEL_CATALOG_DROPDOWN);
        AICorePageUtils.waitFor(dropdown);
        String dropdownText = dropdown.textContent();
        if (!(modelName.equals("default")) && dropdownText.contains(modelName)) {
            Locator checkbox = page.locator(MODEL_CATALOG_DROPDOWN_CHECKED.replace("{catalogName}", modelName));
            AICorePageUtils.waitFor(checkbox);
            if (!checkbox.isVisible()) {
                throw new AssertionError("Model with partial name'" + modelName + "' is not checked in dropdown");
            }
        }
    }

    public static void searchModelInSearchbox(Page page, String modelName) {
        Locator input = page.locator(MODEL_CATALOG_SEARCH_INPUT);
        AICorePageUtils.waitFor(input);
        input.fill(modelName);
        page.waitForTimeout(300);
    }

    public static void searchKnowledgeInSearchbox(Page page, String knowledgeName) {
        Locator input = page.locator(KNOWLEDGE_CATALOG_SEARCH_INPUT);
        AICorePageUtils.waitFor(input);
        input.fill(knowledgeName);
        page.waitForTimeout(300);
    }

    public static void verifyModelVisibleInDropdown(Page page, String modelName) {
        Locator model = page.locator(MODEL_ITEM_BY_NAME.replace("{modelName}", modelName));
        AICorePageUtils.waitFor(model);
        if (!model.isVisible()) {
            throw new AssertionError("Model '" + modelName + "' not visible in dropdown");
        }
    }

    public static void verifyInstructionsSectionIsDisplayed(Page page) {
        Locator maxTokenSection = page.locator(INSTRUCTION_INPUT_XPATH);
        AICorePageUtils.waitFor(maxTokenSection);
        String placeholder = maxTokenSection.getAttribute("placeholder");
        if (placeholder == null || placeholder.isEmpty()|| !placeholder.contains("Update Instructions")) {
            throw new AssertionError("Instructions section is not visible");
        }
        
    }
    public static void verifyMaxTokenSectionIsDisplayed(Page page) {
        Locator maxTokenSection = page.locator(MAX_TOKEN_INPUT_XPATH);
        AICorePageUtils.waitFor(maxTokenSection);
        String placeholder = maxTokenSection.getAttribute("placeholder");
        if (placeholder == null || placeholder.isEmpty()|| !placeholder.contains("Update token length")) {
            throw new AssertionError("Max Token section is not visible");
        }
        
    }
    public static void verifyTemperatureSectionIsDisplayed(Page page, String temperature) {
        Locator temperatureSection = page.locator(TEMPERATURE_INPUT_XPATH);
        AICorePageUtils.waitFor(temperatureSection);
        String textContent = temperatureSection.textContent();
        if (textContent == null || textContent.isEmpty()|| !textContent.contains(temperature)) {
            throw new AssertionError("Temperature section is not visible");
        }
        
    }

    public static void selectModelFromDropdown(Page page, String modelName) {
        Locator model = page.locator(MODEL_ITEM_BY_NAME.replace("{modelName}", modelName));
        AICorePageUtils.waitFor(model);
        try {
            model.click();
        } catch (Exception e) {
            model.click(new Locator.ClickOptions().setForce(true));
        }
        page.waitForTimeout(300);
    }

    public static void verifyConfigurationMenuIsOpened(Page page) {
        Locator configMenu = page.locator(CONFIGURATION_MENU_XPATH);
        if (!configMenu.isVisible()) {
            throw new AssertionError("Expected the Configuration Menu to be opened, but it is not visible.");
        }
    }

    public static void clickOnSidebarToggleButton(Page page) {
        Locator toggleButton = page.locator(SIDEBAR_TOGGLE_XPATH);
        if (!toggleButton.isVisible()) {
            throw new AssertionError("Sidebar toggle button is not visible.");
        }
        toggleButton.click();
    }

    public static void verifyPromptPresentInSidebarHistory(Page page, String prompt) {
        Locator promptLocator = page.locator("//div[@data-slot='sidebar-group']//*[text()='" + prompt + "']");
        AICorePageUtils.waitFor(promptLocator);
        if (!promptLocator.isVisible()) {
            throw new AssertionError("Prompt '" + prompt + "' is not present in the sidebar history.");
        }
    }

    public static void hoverOverSidebarHistoryItem(Page page, String prompt) {
        Locator promptLocator = page.locator(PROMPT_XPATH.replace("{prompt}", prompt));
        AICorePageUtils.waitFor(promptLocator);
        if (!promptLocator.isVisible()) {
            throw new AssertionError("Prompt '" + prompt + "' is not present in the sidebar history.");
        }
        promptLocator.hover();
    }

    public static void clickDeleteIconForSidebarHistoryItem(Page page, String prompt) {
        Locator deleteIconLocator = page.locator(PROMPT_DELETE_BUTTON_XPATH.replace("{prompt}", prompt));
        AICorePageUtils.waitFor(deleteIconLocator);
        if (!deleteIconLocator.isVisible()) {
            throw new AssertionError("Delete icon for prompt '" + prompt + "' is not visible in the sidebar history.");
        }
        deleteIconLocator.click();
    }

    public static void verifyPromptNotPresentInSidebarHistory(Page page, String prompt) {
        Locator promptLocator = page.locator(NO_PROMPT_EXIST_XPATH);
        AICorePageUtils.waitFor(promptLocator);
        if (!promptLocator.isVisible()) {
            throw new AssertionError("Prompt '" + prompt + "' is still present in the sidebar history.");
        }
    }

    public static void verifySidebarState(Page page, boolean state) {
        Locator sidebar = page.locator(SIDEBAR_XPATH);
        AICorePageUtils.waitFor(sidebar);
        boolean isVisible = sidebar.isVisible();
        if (state && !isVisible) {
            throw new AssertionError("Expected sidebar to be visible, but it is not.");
        } else if (!state && isVisible) {
            throw new AssertionError("Expected sidebar to be hidden, but it is visible.");
        }
    }

    public static void waitForModelResponse(Page page) {
        Locator loadingSpinner = page.locator(LOADING_SPINNER_XPATH);
        // Wait for loading spinner to disappear (become hidden)
        long timeout = System.currentTimeMillis() + 12000; // 12 second timeout
        while (loadingSpinner.isVisible() && System.currentTimeMillis() < timeout) {
            page.waitForTimeout(500);
        }
        
        if (System.currentTimeMillis() >= timeout) {
            throw new AssertionError("Timeout waiting for loading spinner to disappear. Response loading took too long.");
        }
        
    }

    public static void verifyModelResponseDisplayed(Page page) {
        Locator responseLocator = page.locator(RESPONSE_XPATH);
        if (!responseLocator.isVisible()) {
            throw new AssertionError("Model response/output is not generated.");
        }
    }

    public static void clickOnMCPDropdown(Page page) {
        Locator addMcpToolLocator = page.locator(ADD_MCP_TOOL_XPATH);
        if (!addMcpToolLocator.isVisible()) {
            throw new AssertionError("Add MCP Tool button is not visible.");
        }
        addMcpToolLocator.click();
    }
    public static void clickOnKnowledgeDropdown(Page page) {
        Locator addKnowledgeToolLocator = page.locator(ADD_KNOWLEDGE_TOOL_XPATH);
        if (!addKnowledgeToolLocator.isVisible()) {
            throw new AssertionError("Add Knowledge Tool button is not visible.");
        }
        addKnowledgeToolLocator.click();
    }

    public static void saveAddedKnowledgeList(Page page) {
        Locator SaveMcpToolLocator = page.locator(SAVE_BUTTON_XPATH);
        if (!SaveMcpToolLocator.isVisible()) {
            throw new AssertionError("Save Knowledge button is not visible.");
        }
        SaveMcpToolLocator.click();
    }
    public static void saveAddedMCPList(Page page) {
        Locator SaveMcpToolLocator = page.locator(SAVE_BUTTON_XPATH);
        if (!SaveMcpToolLocator.isVisible()) {
            throw new AssertionError("Save MCP button is not visible.");
        }
        SaveMcpToolLocator.click();
    }

    public static void clickOverifyMCPAppVisibleInAvailableTools(Page page, String modelName) {
        Locator MCPToolLocator = page.locator(MCP_CHECKBOX_XPATH.replace("{MCP}", modelName)).first();
        String beforeState = MCPToolLocator.getAttribute("data-state");
        AICorePageUtils.waitFor(MCPToolLocator);
        if (!MCPToolLocator.isVisible()) {
            throw new AssertionError("MCP Tool is not visible.");
        }
        if (beforeState.equals("checked")) {
            throw new AssertionError("MCP Tool '" + modelName + "' is already selected.");
        }
        MCPToolLocator.click();
        // wait for 600 ms to reflect the state change
        page.waitForTimeout(600);
        String afterState = MCPToolLocator.getAttribute("data-state");
        if (!afterState.equals("checked")) {
            throw new AssertionError("MCP Tool '" + modelName + "' is not selected.");
        }
    }
    public static void clickVerifyKnowledgeVisibleInAvailableTools(Page page, String knowledgeName) {
        Locator KnowledgeToolLocator = page.locator(KNOWLEDGE_CHECKBOX_XPATH.replace("{KNOWLEDGE}", knowledgeName)).first();
        String beforeState = KnowledgeToolLocator.getAttribute("data-state");
        AICorePageUtils.waitFor(KnowledgeToolLocator);
        if (!KnowledgeToolLocator.isVisible()) {
            throw new AssertionError("Knowledge Tool is not visible.");
        }
        if (beforeState.equals("checked")) {
            throw new AssertionError("Knowledge Tool '" + knowledgeName + "' is already selected.");
        }
        KnowledgeToolLocator.click();
        // wait for 600 ms to reflect the state change
        page.waitForTimeout(600);
        String afterState = KnowledgeToolLocator.getAttribute("data-state");
        if (!afterState.equals("checked")) {
            throw new AssertionError("Knowledge Tool '" + knowledgeName + "' is not selected.");
        }
    }

    public static void deleteAddedMCPModelMCPSection(Page page, String modelName) {
        Locator MCPListToolLocator = page.locator(MCP_DELETED_LIST_XPATH.replace("{MCP}", modelName));
        Locator MCPToolLocator = page.locator(MCP_DELETE_XPATH.replace("{MCP}", modelName));
        if (MCPListToolLocator.isVisible()) {
            MCPListToolLocator.hover();
            MCPToolLocator.click();
            // wait for 600 ms to reflect the deletion
            page.waitForTimeout(600);

        }
    }
    public static void deleteAddedKnowledgeModelKnowledgeSection(Page page, String knowledgeName) {
        Locator knowledgeListToolLocator = page.locator(KNOWLEDGE_LIST_XPATH);
        Locator knowledgeToolLocator = page.locator(KNOWLEDGE_DELETE_XPATH.replace("{KNOWLEDGE}", knowledgeName));
        if (knowledgeListToolLocator.isVisible()) {
            knowledgeListToolLocator.hover();
            knowledgeToolLocator.click();
            // wait for 600 ms to reflect the deletion
            page.waitForTimeout(600);

        }
    }

    public static void verifyMCPModelRemovedMCPSection(Page page, String modelName) {
        Locator MCPListToolMessage = page.locator(MCP_LIST_MESSAGE_XPATH);
        AICorePageUtils.waitFor(MCPListToolMessage);
        String expectedMessage = "No MCPs added";
        String actualMessage = MCPListToolMessage.textContent();
        if (!actualMessage.equals(expectedMessage)) {
            throw new AssertionError("Expected message: '" + expectedMessage + "', but found: '" + actualMessage + "'");
        }

    }
    public static void verifyKnowledgeRemovedKnowledgeSection(Page page, String knowledgeName) {
        Locator knowledgeListToolMessage = page.locator(KNOWLEDGE_LIST_MESSAGE_XPATH);
        AICorePageUtils.waitFor(knowledgeListToolMessage);
        String expectedMessage = "No Knowledge Found";
        String actualMessage = knowledgeListToolMessage.textContent();
        if (!actualMessage.equals(expectedMessage)) {
            throw new AssertionError("Expected message: '" + expectedMessage + "', but found: '" + actualMessage + "'");
        }

    }

    public static void searchAndSelectMCPModel(Page page, String modelName) {
        Locator MCPToolLocator = page.locator(MCP_TOOL_XPATH.replace("{MCP}", modelName)).first();
        AICorePageUtils.waitFor(MCPToolLocator);
        String MCPName = MCPToolLocator.textContent();
        Locator MCPSearchBarLocator = page.locator(MCP_SEARCH_BAR_XPATH);
        AICorePageUtils.waitFor(MCPSearchBarLocator);
        MCPSearchBarLocator.fill(MCPName);
        page.waitForTimeout(600);
        Locator newMCPToolLocator = page.locator(MCP_CHECKBOX_XPATH.replace("{MCP}", modelName)).first();
        String beforeState = newMCPToolLocator.getAttribute("data-state");
        AICorePageUtils.waitFor(newMCPToolLocator);
        if (!newMCPToolLocator.isVisible()) {
            throw new AssertionError("MCP Tool is not visible.");
        }
        if (beforeState.equals("checked")) {
            throw new AssertionError("MCP Tool '" + modelName + "' is already selected.");
        }
        newMCPToolLocator.click();
        // wait for 600 ms to reflect the state change
        page.waitForTimeout(600);
        String afterState = newMCPToolLocator.getAttribute("data-state");
        if (!afterState.equals("checked")) {
            throw new AssertionError("MCP Tool '" + modelName + "' is not selected.");
        }
    }

    public static void verifyAddedMCPAppSelectedList(Page page, String modelName) {
        Locator MCPListToolLocator = page.locator(MCP_ADDED_MODEL_XPATH.replace("{MCP}", modelName)).first();
        AICorePageUtils.waitFor(MCPListToolLocator);
        if (!MCPListToolLocator.isVisible()) {
            throw new AssertionError("MCP Tool is not added/visible in the selected list.");
        }
    }

    public static void verifyAddedKnowledgeModelKnowledgeSection(Page page, String knowledgeName) {
        Locator knowledgeListToolLocator = page.locator(KNOWLEDGE_LIST_XPATH);
        AICorePageUtils.waitFor(knowledgeListToolLocator);
        if (!knowledgeListToolLocator.isVisible() && !knowledgeListToolLocator.textContent().contains(knowledgeName)) {
            throw new AssertionError("Knowledge Tool is not added/visible in the side list.");
        }
    }
    public static void verifyAddedMCPModelMCPSection(Page page, String modelName) {
        Locator MCPListToolLocator = page.locator(MCP_LIST_XPATH.replace("{MCP}", modelName)).first();
        AICorePageUtils.waitFor(MCPListToolLocator);
        if (!MCPListToolLocator.isVisible()) {
            throw new AssertionError("MCP Tool is not added/visible in the side list.");
        }
    }

    public static void verifyButtonIsEnabled(Page page, String buttonName) {
        Locator button = page.getByLabel(buttonName);
        if (!button.isEnabled()) {
            throw new AssertionError("Expected the button '" + buttonName + "' to be enabled, but it is disabled.");
        }
    }

    public static void verifyButtonIsDisabled(Page page, String buttonName) {
        Locator button = page.getByLabel(buttonName);
        if (button.isEnabled()) {
            throw new AssertionError("Expected the button '" + buttonName + "' to be disabled, but it is enabled.");
        }
    }
}
