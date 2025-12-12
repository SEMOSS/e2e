package aicore.steps;

import org.junit.jupiter.api.Assertions;

import aicore.hooks.SetupHooks;
import aicore.pages.CreateAppUsingAgentBuilder;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class CreateAppUsingAgentBuilderSteps {
    private final CreateAppUsingAgentBuilder createAppAgentBuilder;
    
    public CreateAppUsingAgentBuilderSteps() {
        this.createAppAgentBuilder = new CreateAppUsingAgentBuilder(SetupHooks.getPage(), SetupHooks.getTimestamp());
    }

    @Given("User enters name as {string}")
    public void user_enters_name_as(String appName) {
        createAppAgentBuilder.enterName(appName);
    }

    @And("User selects LLM as {string}")
    public void user_selects_llm_as(String modelTitle) {
        createAppAgentBuilder.clickOnLLMModels(modelTitle + AddModelSteps.timestamp);
    }

    @When("User fills the prompt as {string}")
    public void user_fills_the_prompt_as(String promptContext) {
        createAppAgentBuilder.fillPrompt(promptContext);
    }

    @When("User selects {string} to set input")
    public void user_selects_to_set_input(String setInput) {
        createAppAgentBuilder.setInput(setInput);
    }

    @When("User selects InputType as {string}")
    public void user_selects_input_type_as(String inputType) {
        createAppAgentBuilder.selectInputType(inputType);
    }

    @When("User clicks on Preview button")
    public void user_clicks_on_preview_button() {
        createAppAgentBuilder.clickOnPreviewButton();
    }

    @When("User clicks on Create App button")
    public void user_clicks_on_create_app_button() {
        createAppAgentBuilder.clickOnCreateAppButton();
    }

    @And("User fetch the app name for agent builder app")
    public void user_fetch_app_name() {
        String fetchName = createAppAgentBuilder.userFetchAppName();
        Assertions.assertFalse(fetchName.isEmpty(), "Fetched App Name is Empty");
    }

    @When("User clicks on {string}")
    public void user_clicks_on(String setInput) {
        createAppAgentBuilder.clickOnPrompt(setInput);
    }

    @When("User selects {string} to set input in prompt")
    public void user_selects_to_set_input_in_prompt(String string) {
        createAppAgentBuilder.setInputInPrompt(string);
    }

    @When("User selects InputType as {string} for {string}")
    public void user_selects_input_type_as_for(String string, String string2) {
        createAppAgentBuilder.selectInputTypeForInput(string, string2);
    }

}
