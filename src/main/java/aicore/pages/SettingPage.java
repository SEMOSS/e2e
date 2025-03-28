package aicore.pages;

import com.microsoft.playwright.Page;

import aicore.utils.CommonUtils;

public class SettingPage {

	private Page page;
	private static final String ADMIN_ON_OFF_BUTTON_XPATH = "[data-testid='AdminPanelSettingsOutlinedIcon']";
	private static final String CARD_XPATH = "//div[contains(@class,'MuiCardHeader-content')]/span[text()='{cardName}']";
	private static final String ADMIN_ON_BUTTON_XPATH = "//span[text()='Admin on']";
	private static final String ADD_MEMBER_XPATH = "[data-testid='AddIcon']";
	private static final String MEMBER_COUNT_XPATH = "//div[@class='css-1lxwves']//span";

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

	public void checkAddUserButton() {
		page.locator(ADD_MEMBER_XPATH).isVisible();

	}

	public void checkAdminOnButton() {
		page.locator(ADMIN_ON_BUTTON_XPATH).isVisible();
	}

	public int checkCountOfUsers() {
		page.locator(MEMBER_COUNT_XPATH).isVisible();
		String countOfUser = page.locator(MEMBER_COUNT_XPATH).textContent();
		String[] numberOfUser = CommonUtils.splitStringBySpace(countOfUser);
		int totalUser = Integer.parseInt(numberOfUser[0]);
		return totalUser;

	}

}
