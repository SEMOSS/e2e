
package aicore.steps;

import aicore.hooks.SetupHooks;
import aicore.pages.HomePage;
import aicore.pages.OpenAppLibraryPage;
import aicore.pages.TerminalPage;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;

public class TerminalSteps {

	private OpenAppLibraryPage openAppLibraryPage;
	private TerminalPage terminalPage;

	public TerminalSteps() {
		openAppLibraryPage = new OpenAppLibraryPage(SetupHooks.getPage(), AddModelSteps.timestamp);
		terminalPage = new TerminalPage(SetupHooks.getPage());

	}

	@Then("User clicks on Terminal card")
	public void user_clicks_on_Terminal_card() {
		openAppLibraryPage.clickOnTerminalCard();
	}

	@Then("User run pixel command {string}")
	public void user_run_pixel_command(String pixelCommand) {
		terminalPage.terminalInputBox(pixelCommand);
	}

	@Then("User sees {string} in the output")
	public void user_sees_in_the_output(String pixelOutput) {
		String Output = terminalPage.validateOutput(pixelOutput);
		int commandOutput = Integer.parseInt(Output);
		int expectedOutput = Integer.parseInt(pixelOutput);
		Assertions.assertTrue(commandOutput == expectedOutput, "Output is not correct or Pixel Command is not working");
	}
}