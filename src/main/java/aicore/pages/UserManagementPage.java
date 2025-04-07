package aicore.pages;

import com.microsoft.playwright.Page;

public class UserManagementPage {

	private Page page;
	private static final String ADMIN_ON_OFF_BUTTON_XPATH = "[data-testid='AdminPanelSettingsOutlinedIcon']";
	private static final String MEMBER_SETTING_PAGE_XPATH = "//div[contains(@class, 'MuiGrid-grid')]/div/div/div/span[text()='Member Settings']";
	private static final String ADMIN_ON_BUTTON_XPATH = "//span[text()='Admin on']";
	private static final String ADD_MEMBER_XPATH = "[data-testid='AddIcon']";
	private static final String ADD_MEMBER_TYPE_XPATH = "//label[text()='Type']/parent::div";
	private static final String ADD_MEMBER_TYPE_NATIVE_XPATH = "//li[text()='NATIVE']";
	private static final String ADD_MEMBER_USERID_XPATH = "//label[text()='User Id']";
	private static final String ADD_MEMBER_NAME_XPATH = "//label[text()='Name']/following-sibling::div/input";
	private static final String ADD_MEMBER_EMAIL_XPATH = "//label[text()='Email']/following-sibling::div/input";
	private static final String ADD_MEMBER_PHONE_NUMBER_XPATH = "//label[text()='Phone Number']/following-sibling::div/input";
	private static final String ADD_MEMBER_EXTENSION_XPATH = "//label[text()='Extension']/following-sibling::div/input";
	private static final String ADD_MEMBER_TYPE_SAVE_XPATH = "//span[text()='Save']";
	private static final String ADD_MEMBER_TOAST_MESSAGE_XPATH = "//div[text()='Successfully added user']";
	private static final String EDIT_ICON_XPATH = "[data-testid='EditIcon']";
	private static final String MODEL_DROPDOWN_XPATH = "//div[text()='None']";
	private static final String TOKEN_VALUE_XPATH = "//li[text()='{dropdown_value}']";
	private static final String MAX_TOKEN_VALUE_XPATH = "//label[text()='Max Tokens']";
	private static final String FREQUENCY_DROPDOWN_XPATH = "//label[text()='Frequency']//parent::div//div";
	private static final String WEEKELY_VALUE_XPATH = "//li[text()='{dropdown_option}']";
	private static final String MODEL_LIMIT_XPATH = "//td[text()='{limitValue}']";
	private static final String SEARCH_BUTTON_XPATH = "[placeholder=\"Search Users\"]";
	private static final String SELECT_ALL_BUTTON_XPATH = "//label[@class=\"MuiFormControlLabel-root MuiFormControlLabel-labelPlacementEnd css-1o4vkg1-MuiFormControlLabel-root\"]/parent::th//input";
	private static final String DELETE_MEMBER_TOAST_MESSAGE_XPATH = "//div[text()='Successfully deleted users']";
	private static final String DELETE_SELECTED_BUTTON_XPATH = "//span[text()='Delete Selected']";
	private static final String SEARCH_ICON_XPATH = "[data-testid=\"SearchIcon\"]";

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

	public void clickOnEditUser() {
		page.locator(EDIT_ICON_XPATH).first().isVisible();
		page.locator(EDIT_ICON_XPATH).first().click();
	}

	public void clickModelUserDropdown() {
		page.locator(MODEL_DROPDOWN_XPATH).isVisible();
		page.locator(MODEL_DROPDOWN_XPATH).click();
	}

	public void selectTokenValueDropdown(String dropdownOption) {
		page.locator(TOKEN_VALUE_XPATH.replace("{dropdown_value}", dropdownOption)).isVisible();
		page.locator(TOKEN_VALUE_XPATH.replace("{dropdown_value}", dropdownOption)).click();
	}

	public void fillMaxTokensValue(String value) {
		page.locator(MAX_TOKEN_VALUE_XPATH).isVisible();
		page.locator(MAX_TOKEN_VALUE_XPATH).fill(value);
	}

	public void clickFrequencyDropdown() {
		page.locator(FREQUENCY_DROPDOWN_XPATH).first().isVisible();
		page.locator(FREQUENCY_DROPDOWN_XPATH).first().click();

	}

	public void selectWeeklyValueDropdown(String dropdownValue) {
		page.locator(WEEKELY_VALUE_XPATH.replace("{dropdown_option}", dropdownValue)).isVisible();
		page.locator(WEEKELY_VALUE_XPATH.replace("{dropdown_option}", dropdownValue)).click();
	}

	public String getModelLimitValue(String limit) {
		page.locator(MODEL_LIMIT_XPATH.replace("{limitValue}", limit)).isVisible();
		return page.locator(MODEL_LIMIT_XPATH.replace("{limitValue}", limit)).textContent();
	}

	public void checkSearchButton() {
		page.isVisible(SEARCH_ICON_XPATH);
		page.click(SEARCH_ICON_XPATH);
	}

	public void searchUser() {
		page.fill(SEARCH_BUTTON_XPATH, "UserId");
	}

	public void clickSelectAllButton() {
		page.isVisible(SELECT_ALL_BUTTON_XPATH);
		page.click(SELECT_ALL_BUTTON_XPATH);
	}

	public void clickDeleteSelectedButton() {
		page.isVisible(DELETE_SELECTED_BUTTON_XPATH);
		page.click(DELETE_SELECTED_BUTTON_XPATH);
	}

	public String userDeletionToastMessage() {
		String toastMessage = page.textContent(DELETE_MEMBER_TOAST_MESSAGE_XPATH).trim();
		return toastMessage;
	}

}
