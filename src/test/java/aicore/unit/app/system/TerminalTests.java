package aicore.unit.app.system;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import aicore.pages.home.HomePageUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.utils.AbstractE2ETest;
import aicore.utils.TerminalPageUtils;
import aicore.utils.page.app.DragAndDropBlocksPageUtils;

public class TerminalTests extends AbstractE2ETest {

	@BeforeAll
	public static void createApp() {
		login(page, UserType.NATIVE);
		HomePageUtils.navigateToHomePage(page);
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenAppLibrary(page);
		HomePageUtils.clickOnSystemApp(page);
		DragAndDropBlocksPageUtils.clickOnTerminalCard(page);
		TerminalPageUtils.verifyUserIsOnTerminalPage(page);
	}
	
	@Test
	void testPixel() {
		String language = "Pixel";
		TerminalPageUtils.runCommand(page, "Hello");
		String output = TerminalPageUtils.getActualPixelOutput(page, language);
		Assertions.assertEquals("Hello", output, "either Output is not correct or Command is not working as expected");

	}
	
	@Test
	void testPyton() {
		String language = "Python";
		TerminalPageUtils.changeToLanguage(page, language);
		TerminalPageUtils.runCommand(page, "1+1");

		String output = TerminalPageUtils.getActualPixelOutput(page, language);
		Assertions.assertEquals("2", output, "either Output is not correct or Command is not working as expected");

	}
}
