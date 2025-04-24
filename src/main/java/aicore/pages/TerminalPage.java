package aicore.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

public class TerminalPage {
	private Page page;

	private static final String OUTPUT_XPATH = "(//div[contains(@class, 'ace_layer')]//div[contains(@class, 'ace_line')][last()-2]//span[contains(@class, 'ace_constant')])";

	public TerminalPage(Page page) {
		this.page = page;
	}

	public void runPixel(String pixelCommand) {
		page.getByRole(AriaRole.TEXTBOX).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		page.getByRole(AriaRole.TEXTBOX).fill(pixelCommand);
		page.keyboard().press("Enter");
	}

	public String getActualPixelOutput() {
		page.locator(OUTPUT_XPATH).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		String actualOutput = page.locator(OUTPUT_XPATH).textContent().trim();
		return actualOutput;

	}
}