package aicore.pages;

import com.microsoft.playwright.Page;

import aicore.utils.TerminalPageUtils;

public class TerminalPage {
	private Page page;

	public TerminalPage(Page page) {
		this.page = page;
	}

	public void runCommand(String pixelCommand) {
		TerminalPageUtils.runCommand(page, pixelCommand);
	}

	public void verifyUserIsOnTerminalPage() {
		TerminalPageUtils.verifyUserIsOnTerminalPage(page);
	}

	public String getActualPixelOutput(String language) {
		return TerminalPageUtils.getActualPixelOutput(page, language);
	}

	public void waitForLoadingScreennToDisappear() {
		TerminalPageUtils.waitForLoadingScreennToDisappear(page);
	}

	public double getActualFileSize() {
		return TerminalPageUtils.getActualFileSize(page);
	}

	public String getActualFileName() {
		return TerminalPageUtils.getActualFileName(page);
	}

	public String getActualFileDate() {
		return TerminalPageUtils.getActualFileDate(page);
	}

	public void changeToLanguage(String language) {
		TerminalPageUtils.changeToLanguage(page, language);
	}
}