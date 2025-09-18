package aicore.pages;

import com.microsoft.playwright.Page;

import aicore.utils.PrivacyCenterPageUtils;

public class PrivacyCenterPage {
	private Page page;

	public PrivacyCenterPage(Page page) {
		this.page = page;
	}

	public void clickOnPrivacyCenterButton() {
		PrivacyCenterPageUtils.clickOnPrivacyCenterButton(page);
	}

	public boolean isPrivacyPopupVisible() {
		return PrivacyCenterPageUtils.isPrivacyPopupVisible(page);
	}

	public void clickOnCloseIcon() {
		PrivacyCenterPageUtils.clickOnCloseIcon(page);
	}

	public boolean isElemnetVisible(String element, String title) {
		return PrivacyCenterPageUtils.isElemnetVisible(page, element, title);
	}
}
