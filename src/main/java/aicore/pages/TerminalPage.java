package aicore.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

public class TerminalPage {
	private Page page;

	private static final String OUTPUT_XPATH = "//span[contains(text(),'{OutputValue}')]";

	public TerminalPage(Page page) {
		this.page = page;
	}

	public void terminalInputBox(String pixelCommand) {
		page.getByRole(AriaRole.TEXTBOX).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		page.getByRole(AriaRole.TEXTBOX).isVisible();
		page.getByRole(AriaRole.TEXTBOX).fill(pixelCommand);
		page.keyboard().press("Enter");
	}

	public String validateOutput(String pixelOutput) {
		page.locator(OUTPUT_XPATH.replace("{OutputValue}", pixelOutput))
				.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		page.locator(OUTPUT_XPATH.replace("{OutputValue}", pixelOutput)).isVisible();
		String actualOutput = page.locator(OUTPUT_XPATH.replace("{OutputValue}", pixelOutput)).textContent();
		return actualOutput;

	}
}