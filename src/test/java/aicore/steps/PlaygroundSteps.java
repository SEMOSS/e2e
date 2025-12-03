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

    @When("User enters prompt in the Prompt textarea {string}")
    public void user_Enters_Prompt_In_The_Textarea(String prompt) {
        playgroundPage.enterPromptInTextarea(prompt);
    }

    @And("User clicks on the {string} button")
    public void user_Clicks_On_The_Open_Configuration_Menu_Button(String buttonName) {
        playgroundPage.clickOnOpenConfigurationMenuButton(buttonName);
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

    @When("User searches the {string} configuration tab in the model catalog searchbox")
    public void user_Searches_The_Configuration_Tab_In_The_Model_Catalog_Searchbox(String modelName) {
        playgroundPage.searchModelInSearchbox(modelName);
    }

    @Then("User should see the {string} in the model catalog dropdown")
    public void user_Should_See_The_In_The_Model_Catalog_Dropdown(String modelName) {
        playgroundPage.verifyModelVisibleInDropdown(modelName);
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

    @Then("User sees the Configuration Menu is opened")
    public void user_Sees_The_Configuration_Menu_Is_Opened() {
        playgroundPage.verifyConfigurationMenuIsOpened();
    }
}