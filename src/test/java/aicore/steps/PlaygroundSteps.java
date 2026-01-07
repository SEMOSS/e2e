package aicore.steps;

import java.util.List;

import aicore.hooks.SetupHooks;
import aicore.pages.PlaygroundPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PlaygroundSteps {

    private PlaygroundPage playgroundPage;

    public PlaygroundSteps() {
        this.playgroundPage = new PlaygroundPage(SetupHooks.getPage());
    }

   @And("User clicks on Try it out hyperlink for Experiment in our Playground")
    public void click_On_Try_It_Out_Hyperlink_for_Experiment_In_Our_Playground() {
        playgroundPage.clickOnPlaygroundAppButton();
    }

    @And("User is able to see the following Buttons:")
    public void user_Is_Able_To_SeeTheFollowingButtons(DataTable dataTable) {
        List<String> buttons = dataTable.asList(String.class);
		for (String buttonName : buttons) {
			playgroundPage.verifyButtons(buttonName);
		}
    }

    @And("User sees the Prompt textarea with placeholder {string}")
    public void user_Sees_The_Prompt_Textarea_With_Placeholder(String placeholder) {
       playgroundPage.verifyTextareaPlaceholder(placeholder);
    }

    @When("User click on the workspace button")
    public void user_Clicks_On_Workspace_Button() {
        playgroundPage.clickOnWorkspaceButton();
    }

    @And("User clicks on Create New Workspace button")
    public void user_Clicks_On_Create_New_Workspace_Button() {
        playgroundPage.clickOnCreateNewWorkspaceButton();
    }

    @And("User enters workspace name as {string}")
    public void user_Enters_Workspace_Name_As(String name) {
        playgroundPage.enterWorkspaceName(name);
    }

    @And("User enters workspace description as {string}")
    public void user_Enters_Workspace_Description_As(String desc) {
        playgroundPage.enterWorkspaceDescription(desc);
    }

    @And("User clicks on Create button to create workspace")
    public void user_Clicks_On_Create_Button_To_Create_Workspace() {
        playgroundPage.clickOnNewCreateWorkspaceButton();
    }

    @Then("User verifies that {string} workspace is created with description {string}")
    public void user_Verifies_Workspace_Created_With_Description(String name, String desc) {
        playgroundPage.verifyWorkspaceCreatedWithDescription(name, desc);
    }

    @When("User selects the {string} from the workspace list")
    public void user_Selects_Workspace_From_List(String name) {
        playgroundPage.selectWorkspaceFromList(name);
    }
    @When("User selects the {string} as new chat from the workspace list")
    public void user_Selects_the_as_new_chat_from_the_workspace_list(String workspaceName) {
        playgroundPage.selectWorkspaceChatFromList(workspaceName);
    }
    @When("User verifies that {string} workspace is selected in the workspace chat")
    public void user_verifies_that_workspace_is_selected_in_the_workspace_chat(String workspaceName) {
        playgroundPage.verifyWorkspaceSelection(workspaceName);
    }

    @Then("User verifies that {string} workspace is selected in the workspace list")
    public void user_Verifies_Workspace_Selected_In_List(String name) {
        playgroundPage.verifyWorkspaceSelectedInList(name);
    }

    @And("User clicks on Delete Workspace button")
    public void user_Clicks_On_Delete_Workspace_Button() {
        playgroundPage.clickOnDeleteWorkspaceButton();
    }

    @Then("User verifies that workspace is deleted and not present in the workspace list")
    public void user_Verifies_Workspace_Deleted() {
        playgroundPage.verifyWorkspaceDeleted();
    }

    @And("User clicks on Edit Workspace button")
    public void user_Clicks_On_Edit_Workspace_Button() {
        playgroundPage.clickOnEditWorkspaceButton();
    }

    @And("User updates workspace name to {string}")
    public void user_Updates_Workspace_Name_To(String newName) {
        playgroundPage.enterWorkspaceName(newName);
    }

    @And("User updates workspace description to {string}")
    public void user_Updates_Workspace_Description_To(String newDesc) {
        playgroundPage.enterWorkspaceDescription(newDesc);
    }

    @And("User clicks on Save button to save workspace changes")
    public void user_Clicks_On_Save_Button_To_Save_Workspace_Changes() {
        playgroundPage.clickOnSaveWorkspaceButton();
    }

    @Then("User verifies that {string} workspace is updated with new description {string}")
    public void user_Verifies_Workspace_Updated_With_New_Description(String name, String desc) {
        playgroundPage.verifyWorkspaceUpdatedWithDescription(name, desc);
    }

    @And("User enters {string} in the workspace search box")
    public void user_Enters_In_Workspace_Search_Box(String workspaceName) {
        playgroundPage.searchWorkspace(workspaceName);
    }

    @Then("User verifies that {string} workspace is displayed in the search results")
    public void user_Verifies_Workspace_Displayed_In_Search_Results(String name) {
        playgroundPage.verifyWorkspaceDisplayedInSearchResults(name);
    }

    @When("User enters prompt in the Prompt textarea {string}")
    public void user_Enters_Prompt_In_The_Textarea(String prompt) {
        playgroundPage.enterPromptInTextarea(prompt);
    }

    @And("User clicks on the {string} button")
    public void user_Clicks_On_The_Open_Configuration_Menu_Button(String buttonName) {
        playgroundPage.clickOnOpenConfigurationMenuButton(buttonName);
    }

    @And("User waits for the response from the model")
    public void user_Waits_For_The_Response_From_The_Model() {
        playgroundPage.waitForModelResponse();
    }

    @Then("User verifies that the response from the model is displayed as Prompt")
    public void user_Verifies_That_The_Response_From_The_Model_Is_Displayed_As_Prompt() {
        playgroundPage.verifyModelResponseDisplayed();
    }

    @When("User clicks on the MCP dropdown")
    public void user_Clicks_On_The_MCP_Dropdown() {
        playgroundPage.clickOnMCPDropdown();
    }
    @When("User clicks on the Knowledge dropdown")
    public void user_Clicks_On_The_Knowledge_Dropdown() {
        playgroundPage.clickOnKnowledgeDropdown();
    }

    @And("User should see and select the {string} in the MCP availale tools")
    public void user_Should_See_And_Select_The_In_The_MCP_Availale_Tools(String appName) {
        playgroundPage.clickOverifyMCPAppVisibleInAvailableTools(appName);
    }

    @And("User should see and select the {string} in the Knowledge available tools")
    public void user_Should_See_And_Select_The_In_The_Knowledge_Available_Tools(String knowledgeName) {
        playgroundPage.clickVerifyKnowledgeAppVisibleInAvailableTools(knowledgeName);
    }

    @And("User verify added {string} is updated in selected list")
    public void user_Verify_Added_Is_Updated_In_Selected_List(String appName) {
        playgroundPage.verifyAddedMCPAppSelectedList(appName);
    }

    @When("User saves the added Knowledge list")
    public void user_Saves_The_Added_Knowledge_List() {
        playgroundPage.saveAddedKnowledgeList();
    }

    @When("User saves the added MCP list")
    public void user_Saves_The_Added_MCP_List() {
        playgroundPage.saveAddedMCPList();
    }

    @Then("User verify the added {string} is displayed in MCP section")
    public void user_Verify_The_Added_Is_Displayed_In_MCP_Section(String appName) {
        playgroundPage.verifyAddedMCPModelMCPSection(appName);
    }

    @Then("User verify the added {string} is displayed in Knowledge section")
    public void user_Verify_The_Added_Is_Displayed_In_Knowledge_Section(String knowledgeName) {
        playgroundPage.verifyAddedKnowledgeModelKnowledgeSection(knowledgeName);
    }

    @When("User deletes the added {string} from Knowledge section")
    public void user_Deletes_The_Added_From_Knowledge_Section(String knowledgeName) {
        playgroundPage.deleteAddedKnowledgeModelKnowledgeSection(knowledgeName);
    }
    @When("User deletes the added {string} from MCP section")
    public void user_Deletes_The_Added_From_MCP_Section(String appName) {
        playgroundPage.deleteAddedMCPModelMCPSection(appName);
    }

    @Then("User verify the {string} is removed from Knowledge section")
    public void user_Verify_The_Is_Removed_From_Knowledge_Section(String knowledgeName) {
        playgroundPage.verifyKnowledgeRemovedKnowledgeSection(knowledgeName);
    }
    @Then("User verify the {string} is removed from MCP section")
    public void user_Verify_The_Is_Removed_From_MCP_Section(String appName) {
        playgroundPage.verifyMCPModelRemovedMCPSection(appName);
    }

    @When("User search for {string} in the MCP available tools & selects it")
    public void user_search_for_app_in_mcp_and_select(String appName) {
        playgroundPage.searchAndSelectMCPModel(appName);
    }

     @Then("User verify the model catalog dropdown is present with default model with {string} name")
    public void user_Verify_The_Model_Catalog_Dropdown_Is_Present_With_Default_Model_With(String modelName) {
        playgroundPage.verifyModelCatalogDropdownPresent(modelName);
    }

    @When("User clicks on the Model dropdown")
    public void user_Clicks_On_The_Model_Dropdown() {
        playgroundPage.clickOnModelCatalogDropdown();
    }

    @Then("User verify {string} model should be checked in the model catalog dropdown")
    public void user_Verify_The_Model_Should_Be_Checked_In_The_Model_Catalog_Dropdown(String modelName) {
        playgroundPage.verifyModelIsChecked(modelName);
    }

    @Then("User verify {string} knowledge should be checked in the knowledge catalog dropdown")
    public void user_Verify_Knowledge_Should_Be_Checked_In_The_Knowledge_Catalog_Dropdown(String knowledgeName) {
        playgroundPage.verifyKnowledgeIsChecked(knowledgeName);
    }

    @When("User searches the {string} configuration tab in the model catalog searchbox")
    public void user_Searches_The_Configuration_Tab_In_The_Model_Catalog_Searchbox(String modelName) {
        playgroundPage.searchModelInSearchbox(modelName);
    }
    @When("User searches the {string} configuration tab in the Knowledge catalog searchbox")
    public void user_Searches_The_Configuration_Tab_In_The_Knowledge_Catalog_Searchbox(String knowledgeName) {
        playgroundPage.searchKnowledgeInSearchbox(knowledgeName);
    }

    @Then("User should see the {string} in the model catalog dropdown")
    public void user_Should_See_The_In_The_Model_Catalog_Dropdown(String modelName) {
        playgroundPage.verifyModelVisibleInDropdown(modelName);
    }

    @Then("User Verify the Max Token section is displayed with default value & stepper control")
    public void user_Verify_The_Max_Token_Section_Is_Displayed_With_Default_Value_Stepper_Control() {
        playgroundPage.verifyMaxTokenSectionIsDisplayed();
    }
    
    @Then("User Verify the Instructions section is displayed")
    public void user_Verify_The_Instructions_Section_Is_Displayed() {
        playgroundPage.verifyInstructionsSectionIsDisplayed();
    }

    @Then("User Verify the Temperature section is displayed with default value {string}")
    public void user_Verify_The_Temperature_Section_Is_Displayed_With_Default_Value(String temperature) {
        playgroundPage.verifyTemperatureSectionIsDisplayed(temperature);
    }

    @When("User selects the {string} from the model catalog dropdown")
    public void user_Selects_The_From_The_Model_Catalog_Dropdown(String modelName) {
        playgroundPage.selectModelFromDropdown(modelName);
    }

    @Then("User verifies that the {string} button is {string}")
    public void user_Verifies_That_The_Prompt_The_Model_Button_Is_Enabled(String buttonName, String state) {
        if (state.equals("enabled")) {
            playgroundPage.verifyButtonIsEnabled(buttonName);
        } else {
            playgroundPage.verifyButtonIsDisabled(buttonName);
        }
    }

    @When("User clicks on sidbar toggle button")
    public void user_Clicks_On_Sidebar_Toggle_Button() {
        playgroundPage.clickOnSidebarToggleButton();
    }

    @Then("User verifies that {string} prompt is present in the sidebar history")
    public void user_Verifies_That_Prompt_Is_Present_In_The_Sidebar_History(String prompt) {
        playgroundPage.verifyPromptPresentInSidebarHistory(prompt);
    }

    @When("User hovers over the sidebar history item with prompt {string}")
    public void user_Hovers_Over_The_Sidebar_History_Item_With_Prompt(String prompt) {
        playgroundPage.hoverOverSidebarHistoryItem(prompt);
    }

    @And("User clicks on the delete icon for the sidebar history item with prompt {string}")
    public void user_Clicks_On_The_Delete_Icon_For_The_Sidebar_History_Item_With_Prompt(String prompt) {
        playgroundPage.clickDeleteIconForSidebarHistoryItem(prompt);
    }

    @Then("User verifies that {string} prompt is no longer present in the sidebar history")
    public void user_Verifies_That_Prompt_Is_No_Longer_Present_In_The_Sidebar_History(String prompt) {
        playgroundPage.verifyPromptNotPresentInSidebarHistory(prompt);
    }

    @Then("User verifies that the sidebar is {string}")
    public void user_Verifies_That_The_Sidebar_Is(String state) {
        playgroundPage.verifySidebarState(state);
    }

    @Then("User sees the Configuration Menu is opened")
    public void user_Sees_The_Configuration_Menu_Is_Opened() {
        playgroundPage.verifyConfigurationMenuIsOpened();
    }
}