package aicore.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;

public class PlaygroundPageUtils {

    private static final String PLAYGROUND_APP_BUTTON_XPATH = "//span[text()='Experiment in our Playground™']/../../..//a";
    private static final String PROMPT_THE_MODEL_BUTTON_LABEL = "Prompt the Model";
    private static final String CONFIGURATION_MENU_XPATH = "//form";
    private static final String ADD_MCP_TOOL_XPATH = "//div[text()='MCPs']/following-sibling::button";
    private static final String SAVE_MCP_TOOL_XPATH = "//button[text()='Save']";
    private static final String MCP_TOOL_XPATH = "//p[contains(text(),'{MCP}')]";
    private static final String SIDEBAR_TOGGLE_XPATH = "//button[@data-slot='sidebar-trigger']";
    private static final String NO_PROMPT_EXIST_XPATH = "//div[@data-slot='sidebar-group']//*[text()='No rooms found']";
    private static final String PROMPT_XPATH = "//div[@data-slot='sidebar-group']//*[text()='{prompt}']";
    private static final String PROMPT_DELETE_BUTTON_XPATH = "//div[@data-slot='sidebar-group']//*[text()='{prompt}']//following-sibling::button";
    private static final String SIDEBAR_XPATH = "//div[@data-slot='sidebar-group']";
    private static final String RESPONSE_XPATH = "//div//span[text()='Llama3-70B-Instruct']";
    private static final String LOADING_SPINNER_XPATH = "//button[@aria-label='Prompt the Model']//*[@aria-label='Loading']";
    private static final String MCP_SEARCH_BAR_XPATH = "//label[text()='Available Tools']//..//..//input[@placeholder='Search']";
    private static final String MCP_CHECKBOX_XPATH = "//p[contains(text(),'{MCP}')]//../../button";
    private static final String MCP_ADDED_MODEL_XPATH = "//*[contains(text(),'Selected Tools')]//..//span";
    private static final String MCP_LIST_XPATH = "//div[text()='MCPs']//../..//span[contains(text(),'{MCP}')]";
    private static final String MCP_DELETED_LIST_XPATH = "//div[text()='MCPs']//../..//span[contains(text(),'Play')]/..";
    private static final String MCP_LIST_MESSAGE_XPATH = "//div[text()='MCPs']//../..//span";
    private static final String MCP_DELETE_XPATH = "//div[text()='MCPs']//../..//span[contains(text(),'{MCP}')]//following-sibling::button";
    private static final String MODEL_CATALOG_DROPDOWN = "//div//label[text()='Model']//following-sibling::button//span";
    private static final String MODEL_CATALOG_DROPDOWN_CHECKED = "//div[@role='group']//div//span[contains(text(),'{catalogName}')]/../../*[1]";
    private static final String MODEL_CATALOG_SEARCH_INPUT = "//div/div/input[@placeholder='Search']";
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

    public static void verifyModelVisibleInDropdown(Page page, String modelName) {
        Locator model = page.locator(MODEL_ITEM_BY_NAME.replace("{modelName}", modelName));
        AICorePageUtils.waitFor(model);
        if (!model.isVisible()) {
            throw new AssertionError("Model '" + modelName + "' not visible in dropdown");
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

    public static void saveAddedMCPList(Page page) {
        Locator SaveMcpToolLocator = page.locator(SAVE_MCP_TOOL_XPATH);
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

    public static void verifyMCPModelRemovedMCPSection(Page page, String modelName) {
        Locator MCPListToolMessage = page.locator(MCP_LIST_MESSAGE_XPATH);
        AICorePageUtils.waitFor(MCPListToolMessage);
        String expectedMessage = "No MCPs added";
        String actualMessage = MCPListToolMessage.textContent();
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
