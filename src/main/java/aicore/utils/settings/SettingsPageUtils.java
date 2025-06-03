package aicore.utils.settings;

import com.microsoft.playwright.Keyboard;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

import aicore.utils.CommonUtils;

public class SettingsPageUtils {

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
	
}
