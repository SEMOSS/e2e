package aicore.pages.model;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class UploadModelUtils {
	public static void clickOnUploadButton(Page page, String buttonName) {
		Locator uploadButton = page.locator("//button[text()='Upload']");// page.getByTestId(UPLOAD_BUTTON_DATA_TESTID.replace("{option}",
																			// buttonName));
		uploadButton.isEnabled();
		uploadButton.scrollIntoViewIfNeeded();
		uploadButton.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		uploadButton.click(new Locator.ClickOptions().setForce(true));
		Locator loadingSpinner = page.locator("//span[@role='progressbar']").first();
		if (loadingSpinner.isVisible()) {
			loadingSpinner
					.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN).setTimeout(120000));
		}
	}
}
