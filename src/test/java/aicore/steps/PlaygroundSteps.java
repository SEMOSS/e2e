package aicore.steps;

import java.util.List;

import org.junit.platform.commons.function.Try;

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