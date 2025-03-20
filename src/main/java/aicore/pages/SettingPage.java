package aicore.pages;

import com.microsoft.playwright.Page;

public class SettingPage {

	private Page page;

	private static final String ADMIN_ON_OFF_BUTTON_XPATH = "[data-testid='AdminPanelSettingsOutlinedIcon']";
	private static final String MEMBER_SETTING_PAGE_XPATH = "//div[contains(@class, 'MuiGrid-grid')]/div/div/div/span[text()='Member Settings']";
	private static final String ADMIN_ON_BUTTON_XPATH = "//span[text()='Admin on']";
	private static final String ADD_MEMBER_XPATH = "[data-testid='AddIcon']";
	private static final String MEMBER_COUNT_XPATH = "//div[@class='css-1lxwves']//span";

	public SettingPage(Page page) {
		this.page = page;
	}

	public boolean checkAdminButton() {
//	page.pause();
		return page.isVisible(ADMIN_ON_OFF_BUTTON_XPATH);
	}

	public void callAdminButton() {
		page.click(ADMIN_ON_OFF_BUTTON_XPATH);
	}

	public boolean checkMemberSettingPageTile() {

		return page.isVisible(MEMBER_SETTING_PAGE_XPATH);

	}

	public void callMemberSettingPageTile() {
		page.click(MEMBER_SETTING_PAGE_XPATH);
	}

	public void checkAddMemberButton() {
		page.isVisible(ADD_MEMBER_XPATH);

	}

	public void checkAdminOnButton() {
		page.isVisible(ADMIN_ON_BUTTON_XPATH);
	}

	public void checkCountOfUsers() {
		page.isVisible(MEMBER_COUNT_XPATH);
	}
}
