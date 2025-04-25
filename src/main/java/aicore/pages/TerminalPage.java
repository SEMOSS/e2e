package aicore.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

import aicore.utils.CommonUtils;

public class TerminalPage {
	private Page page;

	private static final String OUTPUT_XPATH = "(//div[contains(@class, 'ace_layer')]//div[contains(@class, 'ace_line')][last()-2]//span[contains(@class, 'ace_constant')])";
	private static final String LOADING_SCREEN_XPATH = "//h5[contains(text(),'Loading')]";
	private static final String FILESIZE_XPATH = "//div[contains(@class, 'ace_layer')]//div[contains(@class, 'ace_line')][last()-2]//span[contains(@class,'ace_constant')]";
	private static final String FILENAME_XPATH = "//div[contains(@class, 'ace_layer')]//div[contains(@class, 'ace_line')][last()-2]//span[contains(@class,'ace_string')][2]";
	private static final String FILEDATE_XPATH = "//div[contains(@class, 'ace_layer')]//div[contains(@class, 'ace_line')][last()-2]//span[contains(@class,'ace_string')][last()]";

	public TerminalPage(Page page) {
		this.page = page;
	}

	public void runPixel(String pixelCommand) {
		page.getByRole(AriaRole.TEXTBOX).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		if (pixelCommand.contains("{VECTOR_ID}")) {
			String copiedId = (String) page.evaluate("()=>navigator.clipboard.readText()");
			pixelCommand = pixelCommand.replace("{VECTOR_ID}", copiedId);
		} 
		page.getByRole(AriaRole.TEXTBOX).fill(pixelCommand);
		page.keyboard().press("Enter");
	}

	public String getActualPixelOutput() {
		page.locator(OUTPUT_XPATH).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		String actualOutput = page.locator(OUTPUT_XPATH).textContent().trim();
		return actualOutput;
	}

	public void waitForLoadingScreennToDisappear() {
		page.locator(LOADING_SCREEN_XPATH).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
	}

	public double getActualFileSize() {
		page.locator(FILESIZE_XPATH).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		String fileSize = page.locator(FILESIZE_XPATH).textContent();
		double sizeOfFile = Double.parseDouble(fileSize);
		int actualFileSize = (int) Math.round(sizeOfFile);
		return actualFileSize;
	}

	public String getActualFileName() {
		page.locator(FILENAME_XPATH).isVisible();
		String fileName = page.locator(FILENAME_XPATH).textContent();
		String nameOfFile = fileName.replace("\"", "");
		return nameOfFile;
	}

	public String getActualFileDate() {
		page.locator(FILEDATE_XPATH).isVisible();
		String fileDate = page.locator(FILEDATE_XPATH).textContent();
		String dateOfFile = fileDate.replace("\"", "");
		String[] actualFileDate = CommonUtils.splitStringBySpace(dateOfFile);
		return actualFileDate[0];
	}
}