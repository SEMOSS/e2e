
package aicore.steps;

import org.junit.jupiter.api.Assertions;

import aicore.hooks.SetupHooks;
import aicore.pages.OpenAppLibraryPage;
import aicore.pages.TerminalPage;
import aicore.utils.CommonUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

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
		terminalPage.runCommand(pixelCommand);
	}

	@Then("User sees {string} output {string}")
	public void user_sees_in_the_output(String language, String expectedPixelOutput) {
		String ActualOutput = terminalPage.getActualPixelOutput(language);
		Assertions.assertEquals(expectedPixelOutput, ActualOutput,
				"either Output is not correct or Command is not working as expected");
	}

	@Then("User sees Filename in the output as {string}")
	public void user_sees_filename_in_the_output_as(String expectedFileName) {
		String actualFileName = terminalPage.getActualFileName();
		if (expectedFileName.contains("/")) {
			String[] expectedFileNamePath = expectedFileName.split("/");
			int fileNameIndex = expectedFileNamePath.length - 1;
			Assertions.assertEquals(expectedFileNamePath[fileNameIndex], actualFileName,
					"Name of file is not visible in the list");
		} else {
			Assertions.assertEquals(actualFileName, expectedFileName, "Name of file is not visible in the list");
		}
	}

	@Then("User sees Filesize in the output as {string}")
	public void user_sees_filesize_in_the_output_as(String filesize) {
		double ActualFilesize = terminalPage.getActualFileSize();
		int expectedFilesize = Integer.parseInt(filesize);
		Assertions.assertTrue(ActualFilesize == expectedFilesize, "Upload date is not present or either not same");
	}

	@Then("User sees Last modified date in the output")
	public void user_sees_last_modified_date_in_the_output() {
		String actualDate = terminalPage.getActualFileDate();
		String expectedDate = CommonUtils.getTodayDateFormatted();
		Assertions.assertEquals(actualDate, expectedDate, "Upload date is not present or either not same");
	}

	@Given("User is on Terminal page")
	public void user_is_on_terminal_page() {
		terminalPage.verifyUserIsOnTerminalPage();
	}

	@When("User run python command {string}")
	public void user_run_python_command(String pixelCommand) {
		terminalPage.runCommand(pixelCommand);
	}

	@And("User change the language to {string}")
	public void user_changes_the_language_to(String language) {
		terminalPage.changeToLanguage(language);
	}

}