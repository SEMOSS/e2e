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
		return page.isVisible(ADMIN_ON_OFF_BUTTON_XPATH);
	}

	public void clickOnAdminButton() {
		page.click(ADMIN_ON_OFF_BUTTON_XPATH);
	}

	public void checkCardVisible(String cardName) {

		page.isVisible(CARD_XPATH.replace("{cardName}", cardName));

	}

	public void clickOnCard(String cardName) {
		page.click(CARD_XPATH.replace("{cardName}", cardName));
	}

	public void checkAddUserButton() {
		page.isVisible(ADD_MEMBER_XPATH);

	}

	public void checkAdminOnButton() {
		page.isVisible(ADMIN_ON_BUTTON_XPATH);
	}

	public int checkCountOfUsers() {
		page.isVisible(MEMBER_COUNT_XPATH);
		String countOfUser = page.textContent(MEMBER_COUNT_XPATH);
		String[] numberOfUser = CommonUtils.splitStringBySpace(countOfUser);
		int totalUser = Integer.parseInt(numberOfUser[0]);
		return totalUser;

	}

}
