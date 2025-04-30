package aicore.utils;

import java.util.List;

import com.microsoft.playwright.Keyboard;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

public class SettingsPageUtils {

	///// SETTING ADMIN QUERY PAGE
	private static final String DATABASE_DROPDOWN_NAME = "Database";
	private static final String QUERY_TEXTBOX_XPATH = "//div[contains(@class,'MuiFormControl-root')]//label[text()='Enter query to run on database']";
	private static final String DATABASE_TABLE_HEADER_XPATH = "//table[contains(@class,'MuiTable-root')]//thead//th";
	private static final String QUERY_EXECUTED_TOAST_MESSAGE = "Successfully submitted query";
	private static final String EXECUTE_QUERY_BUTTON_TEXT = "Run";

	public static void selectDatabase(Page page, String databaseName) {
		Locator databaseDropdown = page.getByRole(AriaRole.BUTTON,
				new Page.GetByRoleOptions().setName(DATABASE_DROPDOWN_NAME));
		databaseDropdown.isVisible();
		databaseDropdown.click();
		Locator databaseDropdownListOptions = page.getByRole(AriaRole.OPTION,
				new Page.GetByRoleOptions().setName(databaseName));
		databaseDropdownListOptions.click();
	}

	public static void enterQuery(Page page, String query) {
		page.locator(QUERY_TEXTBOX_XPATH).fill(query);
	}

	public static void clickOnExecuteQueryButton(Page page) {
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(EXECUTE_QUERY_BUTTON_TEXT)).click();
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(EXECUTE_QUERY_BUTTON_TEXT)).click();
	}

	public static int getTableHeaderCount(Page page) {
		return page.locator(DATABASE_TABLE_HEADER_XPATH).count();
	}

	public static List<String> getTableHeaderNames(Page page) {
		return page.locator(DATABASE_TABLE_HEADER_XPATH).allInnerTexts();
	}

	public static String verifyQueryExecutedToastMessage(Page page) {
		Locator toastMessage = page.getByRole(AriaRole.ALERT)
				.filter(new Locator.FilterOptions().setHasText(QUERY_EXECUTED_TOAST_MESSAGE));
		toastMessage.isVisible();
		return toastMessage.textContent().trim();
	}
	
	///// SETTING PAGE
	private static final String ADMIN_ON_OFF_BUTTON_XPATH = "[data-testid='AdminPanelSettingsOutlinedIcon']";
	private static final String ADMIN_BUTTON_OFF_XPATH = "//*[local-name()='svg'][contains(@class,'MuiSvgIcon-colorDisabled')]";
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
	
	public static boolean checkAdminButton(Page page) {
		return page.locator(ADMIN_ON_OFF_BUTTON_XPATH).isVisible();
	}

	public static void clickOnAdminButton(Page page) {
		if (page.locator(ADMIN_BUTTON_OFF_XPATH).isVisible()) {
			page.locator(ADMIN_ON_OFF_BUTTON_XPATH).click();
		}
	}

	public static void checkCardVisible(Page page, String cardName) {
		page.locator(CARD_XPATH.replace("{cardName}", cardName))
				.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
	}

	public static void clickOnCard(Page page, String cardName) {
		page.locator(CARD_XPATH.replace("{cardName}", cardName)).click();
	}

	public static void checkAddUserButton(Page page) {
		page.locator(ADD_MEMBER_XPATH).isVisible();

	}

	public static void checkAdminOnButton(Page page) {
		page.locator(ADMIN_ON_BUTTON_XPATH).isVisible();
	}

	public static int checkCountOfUsers(Page page) {
		page.locator(MEMBER_COUNT_XPATH).isVisible();
		String countOfUser = page.locator(MEMBER_COUNT_XPATH).textContent();
		String[] numberOfUser = CommonUtils.splitStringBySpace(countOfUser);
		int totalUser = Integer.parseInt(numberOfUser[0]);
		return totalUser;

	}

	public static int countOfPages(Page page) {
		int userCount = checkCountOfUsers(page);
		String rowsCount = page.locator(ROWS_PER_PAGE_XPATH).textContent();
		int numberOfRows = Integer.parseInt(rowsCount);
		int totalpages = (int) Math.ceil((double) userCount / numberOfRows);
		return totalpages;
	}

	public static void checkForwardButton(Page page) {
		for (int i = 1; i < countOfPages(page); i++) {
			page.locator(NEXT_PAGE_XPATH).isVisible();
			page.locator(NEXT_PAGE_XPATH).isEnabled();
			page.locator(NEXT_PAGE_XPATH).click();
		}
	}

	public static void checkBackwardButton(Page page) {
		for (int i = 1; i < countOfPages(page); i++) {
			page.locator(PREVIOUS_PAGE_XPATH).isVisible();
			page.locator(PREVIOUS_PAGE_XPATH).isVisible();
			page.locator(PREVIOUS_PAGE_XPATH).click();
		}
	}

	public static int checkRecordsOnPage(Page page) {
		int rowsCount = page.locator(TOTAL_ROWS_XPATH).count();
		return rowsCount;
	}

	public static void clickNumberOfRows(Page page, String rowsPerPageValue) {
		page.locator(ROWS_PER_PAGE_XPATH).click();
		page.locator(ROWS_FILTER_UNIT_VALUE_XPATH.replace("{unitValue}", rowsPerPageValue)).isVisible();
		page.locator(ROWS_FILTER_UNIT_VALUE_XPATH.replace("{unitValue}", rowsPerPageValue)).click();
	}
	
	public static void clickOnSearchButton(Page page) {
		page.locator(SEARCH_BUTTON_XPATH).isVisible();
		page.locator(SEARCH_BUTTON_XPATH).click();

	}

	public static void clickOnSearchBox(Page page) {
		page.locator(SEARCH_BAR_XPATH).isVisible();
		page.locator(SEARCH_BAR_XPATH).click();

	}

	public static void enterUsername(Page page, String username) {
		page.keyboard().type(username, new Keyboard.TypeOptions().setDelay(200));
		page.waitForTimeout(1500);
	}

	public static String checkUsername(Page page, String username) {
		return page.locator(USERLIST_XPATH.replace("{userName}", username)).textContent();

	}
	
	///// SETTING MY PROFILE PAGE
	private static final String OPEN_SETTINGS_ICON_XPATH = "Settings-icon";
	private static final String MY_PROFILE_SECTION_TITLE_XPATH = "//h6[text()='{sectionText}']";
	private static final String PRIVACY_CENTER_XPATH = "//span[text()='Privacy Center']";
	private static final String DESCRIPTION_FIELD_BUTTON_XPATH = "//label[text()='Description']/following-sibling::div//input";
	private static final String KEY_COPY_BUTTON_XPATH = "//div[h6[text()='{KeyName}']]//button[contains(@class, 'MuiButton-outlined') and .//span[text()='Copy']]";
	private static final String SECTION_ARROW_DOWN_XPATH = "//div[h6[text()='{sectionName}']]//*[name()='svg']";
	private static final String SECTION_COPY_BUTTON_XPATH = "//div[h6[text()='{sectionName}']]/following-sibling::div//span[text()='Copy']";
	private static final String CANCEL_BUTTON_XPATH = "//button[contains(@class, 'MuiButtonBase-root') and .//span[normalize-space()='Close']]";
	private static final String DELETE_BUTTON_XPATH = "//td[text()='{KeyName}']/following-sibling::td//button[@title='Delete']";
	private static final String DELETE_KEY_TOAST_MESSAGE_XPATH = "//div[contains(@class, 'MuiAlert-message')]";
	private static final String GENERATED_KEY_XPATH = "//td[contains(text(),'{keyName}')]";
	private static final String GENERATED_DESCRIPTION_XPATH = "//td[text()='{description}']";
	

	public static void openSettingsIcon(Page page) {
		page.getByTestId(OPEN_SETTINGS_ICON_XPATH).click();;
	}

	public static void clickOnMyProfileCard(Page page) {
		page.getByText("My profile").click();
	}

	public static boolean isSectionVisible(Page page, String sectionText) {
		page.waitForSelector(MY_PROFILE_SECTION_TITLE_XPATH.replace("{sectionText}", sectionText));
		return page.isVisible(MY_PROFILE_SECTION_TITLE_XPATH.replace("{sectionText}", sectionText));
	}

	public static String verifyPrivacyCenter(Page page) {
		String actualTextMessage = page.textContent(PRIVACY_CENTER_XPATH);
		return actualTextMessage;
	}

	public static void clickNewKeyButton(Page page) {
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("New Key")).click();

	}

	public static void enterKeyName(Page page, String keyName, String timestamp) {
		String uniqueKeyName = keyName + timestamp;
		page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Name")).click();
		page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Name")).fill(uniqueKeyName);
	}

	public static void enterDescription(Page page, String description, String timestamp) {
		String uniqueDescription = description + timestamp;
		page.click(DESCRIPTION_FIELD_BUTTON_XPATH);
		page.fill(DESCRIPTION_FIELD_BUTTON_XPATH, uniqueDescription);

	}

	public static void clickGenerateButton(Page page) {
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Generate")).click();
	}

	public static boolean isAccessKeyPopupVisible(Page page) {
		Locator accessKeyPopup = page.locator(".access-key-popup");
		return accessKeyPopup.isVisible();
	}

	public static String copyAccessKey(Page page, String KeyName) {
		page.click(KEY_COPY_BUTTON_XPATH.replace("{KeyName}", KeyName));
		return page.evaluate("navigator.clipboard.readText()").toString().trim();
	}

	public static String extractExampleSectionContent(Page page, String sectionName) {
		page.click(SECTION_ARROW_DOWN_XPATH.replace("{sectionName}", sectionName));
		page.click(SECTION_COPY_BUTTON_XPATH.replace("{sectionName}", sectionName));
		return page.evaluate("navigator.clipboard.readText()").toString().trim();
	}

	public static void clickOnCancelButton(Page page) {
		page.click(CANCEL_BUTTON_XPATH);
	}

	public static void clickOnDeleteIcon(Page page, String KeyName, String timestamp) {
		String deleteButtonXpath = DELETE_BUTTON_XPATH.replace("{KeyName}", KeyName + timestamp);
		page.waitForSelector(deleteButtonXpath);
		page.click(deleteButtonXpath);
	}

	public static String deleteKeyToastMessage(Page page) {
		String toastMessage = page.textContent(DELETE_KEY_TOAST_MESSAGE_XPATH).trim();
		return toastMessage;
	}

	public static String getExpectedAccessKeyTitle(Page page, String keyName, String timestamp) {
		String expTitle = keyName + timestamp;
		return expTitle;
	}

	public static String validateGeneratedKey(Page page, String keyName, String timestamp) {
		String generatedKeyName = page.textContent(GENERATED_KEY_XPATH.replace("{keyName}", keyName + timestamp))
				.trim();
		return generatedKeyName;
	}

	public static String validateDescriptionName(Page page, String description, String timestamp) {
		String generatedDescription = page
				.textContent(GENERATED_DESCRIPTION_XPATH.replace("{description}", description + timestamp)).trim();
		return generatedDescription;
	}

	public static String getExpectedDescriptionName(Page page, String description, String timestamp) {
		String expDescription = description + timestamp;
		return expDescription;
	}
}
