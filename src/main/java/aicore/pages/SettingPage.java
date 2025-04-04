package aicore.pages;

import com.microsoft.playwright.Keyboard;
import com.microsoft.playwright.Page;

import aicore.utils.CommonUtils;

public class SettingPage {

	private Page page;
	private static final String ADMIN_ON_OFF_BUTTON_XPATH = "[data-testid='AdminPanelSettingsOutlinedIcon']";
	private static final String CARD_XPATH = "//div[contains(@class,'MuiCardHeader-content')]/span[text()='{cardName}']";
	private static final String ADMIN_ON_BUTTON_XPATH = "//span[text()='Admin on']";
	private static final String ADD_MEMBER_XPATH = "[data-testid='AddIcon']";
	private static final String MEMBER_COUNT_XPATH = "//div[@class='css-1lxwves']//span";
	private static final String ROWS_PER_PAGE_XPATH = "//div[(@aria-haspopup='listbox')]";
	private static final String ROWS_FILTER_UNIT_VALUE_XPATH = "//li[@data-value='{unitValue}']";
	private static final String TOTAL_ROWS_XPATH = "//tbody[contains(@class, 'MuiTableBody-root')]/tr";
	private static final String NEXT_PAGE_XPATH = "//button[contains(@title,'Go to next page')]";
	private static final String PREVIOUS_PAGE_XPATH = "//button[contains(@title,'Go to previous page')]";
	private static final String SEARCH_BUTTON_XPATH = "[data-testid='SearchIcon']";
	private static final String SEARCH_BAR_XPATH = "//input[contains(@class,'MuiInputBase-input')]";
	private static final String USERLIST_XPATH = "[title='Name: {userName}']";
	private static final String ADD_MEMBER_TYPE_XPATH = "//label[text()='Type']/parent::div";
	private static final String ADD_MEMBER_TYPE_NATIVE_XPATH = "//li[text()='NATIVE']";
	private static final String ADD_MEMBER_USERID_XPATH = "//label[text()='User Id']";
	private static final String ADD_MEMBER_NAME_XPATH = "//label[text()='Name']/following-sibling::div/input";
	private static final String ADD_MEMBER_EMAIL_XPATH = "//label[text()='Email']/following-sibling::div/input";
	private static final String ADD_MEMBER_PHONE_NUMBER_XPATH = "//label[text()='Phone Number']/following-sibling::div/input";
	private static final String ADD_MEMBER_EXTENSION_XPATH = "//label[text()='Extension']/following-sibling::div/input";
	private static final String ADD_MEMBER_TYPE_SAVE_XPATH = "//span[text()='Save']";
	private static final String ADD_MEMBER_TOAST_MESSAGE_XPATH = "//div[text()='Successfully added user']";

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

	public int countOfPages() {
		int userCount = checkCountOfUsers();
		String rowsCount = page.locator(ROWS_PER_PAGE_XPATH).textContent();
		int numberOfRows = Integer.parseInt(rowsCount);
		int totalpages = (int) Math.ceil((double) userCount / numberOfRows);
		return totalpages;
	}

	public void checkForwardButton() {
		for (int i = 1; i < countOfPages(); i++) {
			page.locator(NEXT_PAGE_XPATH).isVisible();
			page.locator(NEXT_PAGE_XPATH).isEnabled();
			page.locator(NEXT_PAGE_XPATH).click();
		}
	}

	public void checkBackwardButton() {
		for (int i = 1; i < countOfPages(); i++) {
			page.locator(PREVIOUS_PAGE_XPATH).isVisible();
			page.locator(PREVIOUS_PAGE_XPATH).isEnabled();
			page.locator(PREVIOUS_PAGE_XPATH).click();
		}
	}

	public int checkRecordsOnPage() {
		int rowsCount = page.locator(TOTAL_ROWS_XPATH).count();
		return rowsCount;
	}

	public void clickNumberOfRows(String rowsPerPageValue) {
		page.locator(ROWS_PER_PAGE_XPATH).click();
		page.locator(ROWS_FILTER_UNIT_VALUE_XPATH.replace("{unitValue}", rowsPerPageValue)).isVisible();
		page.locator(ROWS_FILTER_UNIT_VALUE_XPATH.replace("{unitValue}", rowsPerPageValue)).click();
	}

	public void clickOnSearchButton() {
		page.locator(SEARCH_BUTTON_XPATH).isVisible();
		page.locator(SEARCH_BUTTON_XPATH).click();
	}

	public void clickOnSearchBox() {
		page.locator(SEARCH_BAR_XPATH).isVisible();
		page.locator(SEARCH_BAR_XPATH).click();
	}

	public void enterUsername(String username) {
		page.keyboard().type(username, new Keyboard.TypeOptions().setDelay(200));
		page.waitForTimeout(1000);
	}

	public String checkUsername(String username) {
		return page.locator(USERLIST_XPATH.replace("{userName}", username)).textContent();
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

}
