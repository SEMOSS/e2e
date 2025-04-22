
package aicore.steps;

import aicore.hooks.SetupHooks;
import aicore.pages.HomePage;
import aicore.pages.OpenAppLibraryPage;
import aicore.pages.TerminalPage;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;

public class RunPixelSteps {

	private HomePage homePage;
	private OpenAppLibraryPage openAppLibraryPage;
	private TerminalPage terminalPage;

	public RunPixelSteps() {
		homePage = new HomePage(SetupHooks.getPage());
		openAppLibraryPage = new OpenAppLibraryPage(SetupHooks.getPage(), AddModelSteps.timestamp);
		terminalPage = new TerminalPage(SetupHooks.getPage());

	}

	@Then("User clicks on Open App Library")
	public void user_clicks_on_open_app_library() {
		homePage.clickOnOpenAppLibrary();
	}

	@Then("User clicks on System Apps tab")
	public void user_clicks_on_System_Apps_tab() {
		openAppLibraryPage.clickOnSystemApps();
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
		Assertions.assertTrue(commandOutput == expectedOutput);
	}
}