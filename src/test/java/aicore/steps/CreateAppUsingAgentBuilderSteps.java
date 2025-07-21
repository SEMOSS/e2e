package aicore.steps;

import aicore.hooks.SetupHooks;
import aicore.pages.CreateAppUsingAgentBuilder;
import aicore.pages.HomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class CreateAppUsingAgentBuilderSteps {
	private HomePage homePage;
	private CreateAppUsingAgentBuilder createAppAgentBuilder;

	public CreateAppUsingAgentBuilderSteps() {
		this.homePage = new HomePage(SetupHooks.getPage());
		this.createAppAgentBuilder = new CreateAppUsingAgentBuilder(SetupHooks.getPage());
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
	@When("User selects {string} to set input in prompt")
public void user_selects_to_set_input_in_prompt(String string) {
	createAppAgentBuilder.setInputInPrompt(string);
}
@When("User selects InputType as {string} for {string}")
public void user_selects_input_type_as_for(String string, String string2) {
	createAppAgentBuilder.selectInputTypeForInput(string, string2);
}


}
