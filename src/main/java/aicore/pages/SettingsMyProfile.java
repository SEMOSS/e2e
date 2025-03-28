package aicore.pages;

import com.microsoft.playwright.Page;

public class SettingsMyProfile {
	private Page page;
	private static final String OPEN_SETTINGS_ICON_XPATH = "//a[@aria-label='Navigate to settings']";
	private static final String MY_PROFILE_SECTION_TITLE_XPATH = "//h6[text()='{sectionText}']";
	private static final String PRIVACY_CENTER_XPATH = "//span[text()='Privacy Center']";

	public SettingsMyProfile(Page page) {
		this.page = page;
	}

	public void openSettingsIcon() {
		page.click(OPEN_SETTINGS_ICON_XPATH);
	}

	public void clickOnMyProfileCard() {
		page.getByText("My profile").click();
	}

	public boolean isSectionVisible(String sectionText) {
		page.waitForSelector(MY_PROFILE_SECTION_TITLE_XPATH.replace("{sectionText}", sectionText));
		return page.isVisible(MY_PROFILE_SECTION_TITLE_XPATH.replace("{sectionText}", sectionText));

	}

	public String verifyPrivacyCenter() {
		String actualTextMessage = page.textContent(PRIVACY_CENTER_XPATH);
		return actualTextMessage;
	}

}
