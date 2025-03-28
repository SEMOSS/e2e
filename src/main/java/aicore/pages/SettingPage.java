package aicore.pages;

import com.microsoft.playwright.Page;

public class SettingPage {
	private Page page;
	private static final String ADMIN_ON_OFF_BUTTON_XPATH = "[data-testid='AdminPanelSettingsOutlinedIcon']";
	private static final String CARD_XPATH = "//div[contains(@class,'MuiCardHeader-content')]/span[text()='{cardName}']";

	public SettingPage(Page page) {
		this.page = page;
	}

	public boolean checkAdminButton() {
		return page.locator(ADMIN_ON_OFF_BUTTON_XPATH).isVisible();
	}

	public void clickOnAdminButton() {
		page.locator(ADMIN_ON_OFF_BUTTON_XPATH).click();
	}

	public void checkCardVisible(String cardName) {
		page.locator(CARD_XPATH.replace("{cardName}", cardName)).isVisible();

	}

	public void clickOnCard(String cardName) {
		page.locator(CARD_XPATH.replace("{cardName}", cardName)).click();
	}
}
