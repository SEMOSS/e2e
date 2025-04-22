package aicore.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class TerminalPage {
	private Page page;

	private static final String INPUT_BOX_XPATH = "[role='textbox']";
	private static final String OUTPUT_XPATH = "//span[contains(text(),'{OutputValue}')]";

	public TerminalPage(Page page) {
		this.page = page;
	}

	public void terminalInputBox(String pixelCommand) {
		page.locator(INPUT_BOX_XPATH).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		page.locator(INPUT_BOX_XPATH).isVisible();
		String PixelCommand = pixelCommand;
		if (PixelCommand.contains("{Vector Id")) {
			page.locator(INPUT_BOX_XPATH).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
			page.locator(INPUT_BOX_XPATH).isVisible();
			String CopiedId = (String) page.evaluate("()=>navigator.clipboard.readText()");
			String Command = PixelCommand.replace("{Vector Id}", CopiedId);
			page.locator(INPUT_BOX_XPATH).fill(Command);
			page.keyboard().press("Enter");
		} else {
			page.locator(INPUT_BOX_XPATH).fill(pixelCommand);
			page.keyboard().press("Enter");
		}
	}

	public String validateOutput(String pixelOutput) {
		page.locator(OUTPUT_XPATH.replace("{OutputValue}", pixelOutput))
				.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		page.locator(OUTPUT_XPATH.replace("{OutputValue}", pixelOutput)).isVisible();
		String actualOutput = page.locator(OUTPUT_XPATH.replace("{OutputValue}", pixelOutput)).textContent();
		return actualOutput;

	}
}