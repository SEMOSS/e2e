
package aicore.steps;

import aicore.hooks.SetupHooks;
import aicore.pages.OpenAppLibraryPage;
import aicore.pages.TerminalPage;
import aicore.utils.CommonUtils;
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
		terminalPage.runPixel(pixelCommand);
	}

	@Then("User sees {string} in the output")
	public void user_sees_in_the_output(String expectedPixelOutput) {
		String ActualOutput = terminalPage.getActualPixelOutput();
		Assertions.assertEquals(expectedPixelOutput, ActualOutput,
				"Output is not correct or Pixel Command is not working");
	}

	@Then("User sees Filename in the output as {string}")
	public void user_sees_filename_in_the_output_as(String expectedFileName) {
		String actualFileName = terminalPage.getActualFileName();
		if(expectedFileName.contains("/")) {
			String [] expectedFileNamePath = expectedFileName.split("/");
			int fileNameIndex = expectedFileNamePath.length-1;
			Assertions.assertEquals(expectedFileNamePath[fileNameIndex], actualFileName,"Name of file is not visible in the list");
		}else {
			Assertions.assertEquals(actualFileName, expectedFileName, "Name of file is not visible in the list");
		}
	}

	@Then("User sees Filesize in the output as {string}")
	public void user_sees_filesize_in_the_output_as(String filesize) {
		double ActualFilesize = terminalPage.getActualFileSize();
		int expectedFilesize = Integer.parseInt(filesize);		
		Assertions.assertTrue(ActualFilesize == expectedFilesize ,"Upload date is not present or either not same");
	}

	@Then("User sees Last modified date in the output")
	public void user_sees_last_modified_date_in_the_output() {
		String actualDate = terminalPage.getActualFileDate();
		String expectedDate = CommonUtils.getTodayDateFormatted();
		Assertions.assertEquals(actualDate, expectedDate, "Upload date is not present or either not same");
	}
}