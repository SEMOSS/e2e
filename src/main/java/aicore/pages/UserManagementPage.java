package aicore.pages;

import com.microsoft.playwright.Page;

public class UserManagementPage {

	private Page page;
	private static final String ADMIN_ON_OFF_BUTTON_XPATH = "[data-testid='AdminPanelSettingsOutlinedIcon']";
	private static final String MEMBER_SETTING_PAGE_XPATH = "//div[contains(@class, 'MuiGrid-grid')]/div/div/div/span[text()='Member Settings']";
	private static final String ADMIN_ON_BUTTON_XPATH = "//span[text()='Admin on']";
	private static final String ADD_MEMBER_XPATH = "[data-testid='AddIcon']";
	private static final String ADD_MEMBER_TYPE_XPATH = "//label[text()='Type']/parent::div";
	private static final String ADD_MEMBER_TYPE_NATIVE_XPATH = "//li[text()='Native']";
	private static final String ADD_MEMBER_USERID_XPATH = "//label[text()='User Id']";
	private static final String ADD_MEMBER_USERNAME_XPATH = "//label[text()='Username']/following-sibling::div/input";
	private static final String ADD_MEMBER_NAME_XPATH = "//label[text()='Name']/following-sibling::div/input";
	private static final String ADD_MEMBER_EMAIL_XPATH = "//label[text()='Email']/following-sibling::div/input";
	private static final String ADD_MEMBER_PHONE_NUMBER_XPATH = "//label[text()='Phone Number']/following-sibling::div/input";
	private static final String ADD_MEMBER_EXTENSION_XPATH = "//label[text()='Extension']/following-sibling::div/input";
	private static final String ADD_MEMBER_TYPE_SAVE_XPATH = "//span[text()='Save']";
	private static final String ADD_MEMBER_TOAST_MESSAGE_XPATH = "//div[text()='Successfully added user']";

	public UserManagementPage(Page page) {
		this.page = page;
	}

	public boolean checkAdminButton() {
		return page.locator(ADMIN_ON_OFF_BUTTON_XPATH).isVisible();
	}

	public void clickAdminButton() {
		page.locator(ADMIN_ON_OFF_BUTTON_XPATH).click();
	}

	public boolean checkMemberSettingPageTile() {
		return page.locator(MEMBER_SETTING_PAGE_XPATH).isVisible();
	}

	public void clickMemberSettingPageTile() {
		page.locator(MEMBER_SETTING_PAGE_XPATH).click();
	}

	public void checkAddMemberButton() {
		page.locator(ADD_MEMBER_XPATH).isVisible();
	}

	public void checkAdminOnButton() {
		page.locator(ADMIN_ON_BUTTON_XPATH).isVisible();
	}

	public void checkAddUserButton() {
		page.locator(ADD_MEMBER_XPATH).isVisible();
	}

	public void clickAddUserButton() {
		page.locator(ADD_MEMBER_XPATH).click();
	}

	public void clickTypeDropdown() {
		page.locator(ADD_MEMBER_TYPE_XPATH).isVisible();
		page.locator(ADD_MEMBER_TYPE_XPATH).click();
	}

	public void clickNativeDropdownValue() {
		page.locator(ADD_MEMBER_TYPE_NATIVE_XPATH).isVisible();
		page.locator(ADD_MEMBER_TYPE_NATIVE_XPATH).click();
	}

	public void fillUserId(String UserId) throws InterruptedException {
		page.locator(ADD_MEMBER_USERID_XPATH).isVisible();
		page.locator(ADD_MEMBER_USERID_XPATH).fill(UserId);
	}

	public void fillUsername(String Username) throws InterruptedException {
		page.locator(ADD_MEMBER_USERNAME_XPATH).isVisible();
		page.locator(ADD_MEMBER_USERNAME_XPATH).fill(Username);
	}

	public void fillName(String Name) throws InterruptedException {
		page.locator(ADD_MEMBER_NAME_XPATH).isVisible();
		page.locator(ADD_MEMBER_NAME_XPATH).fill(Name);
	}

	public void fillEmail(String Email) {
		page.locator(ADD_MEMBER_EMAIL_XPATH).isVisible();
		page.locator(ADD_MEMBER_EMAIL_XPATH).fill(Email);
	}

	public void fillPhoneNumber(String Number) {
		page.locator(ADD_MEMBER_PHONE_NUMBER_XPATH).isVisible();
		page.locator(ADD_MEMBER_PHONE_NUMBER_XPATH).fill(Number);
	}

	public void fillExtension(String Extension) {
		page.locator(ADD_MEMBER_EXTENSION_XPATH).isVisible();
		page.locator(ADD_MEMBER_EXTENSION_XPATH).fill(Extension);
	}

	public void clickSaveButton() {
		page.locator(ADD_MEMBER_TYPE_SAVE_XPATH).click();
	}

	public String userCreationToastMessage() {
		String toastMessage = page.textContent(ADD_MEMBER_TOAST_MESSAGE_XPATH).trim();
		return toastMessage;
	}

}
