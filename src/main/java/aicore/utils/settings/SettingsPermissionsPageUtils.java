package aicore.utils.settings;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import aicore.framework.ConfigUtils;

public class SettingsPermissionsPageUtils {
	private static final String CATALOG_SETTINGS_CARD_DATA_TESTID = "settingsIndexPage-{cardName}-card";
	private static final String CATALOG_SEARCHBOX_PLACEHOLDER = "Search";
	private static final String PROJECT_SEARCHBOX_PLACEHOLDER = "Project";
	private static final String CATALOG_CARD_XPATH = "//p[text()='{catalogName}']";
	private static final String APP_CARD_XPATH = "//span[text()='{appName}']";
	private static final String MEMBER_SEARCH_ICON_XPATH = "//h6[text()='Permissions']/parent::div/following-sibling::div//*[@data-testid='SearchIcon']";
	private static final String SEARCH_MEMBER_SEARCHBOX_XPATH = "//div[@data-testid='membersTables-searchMembers-searchBar}']//input";
	private static final String ACCESS_RADIO_BUTTON_XPATH = "//input[@type='radio' and @value='{role}']";
	private static final String USER_DELETE_ICON_XPATH = "//tr[.//input[@type='radio' and @value='{role}' and @checked]]//*[@data-testid='DeleteIcon']";
	private static final String DELETE_USER_CONFIRM_BUTTON_XPATH = "//span[text()='Confirm']";
	private static final String USER_EDIT_ICON_XPATH = "//tr[.//input[@type='radio' and @value='{role}' and @checked]]//*[@data-testid='EditIcon']";
	private static final String EDIT_MEMBER_ACCESS_BUTTON_XPATH = "//div[contains(@class,'MuiFormGroup-root MuiRadioGroup')]//input[@type='radio' and @value='{role}']";
	private static final String EDIT_MEMBER_UPDATE_BUTTON_XPATH = "//span[text()='Update']";
	private static final String USER_PERMISSION_DATE_XPATH = "//tr[.//input[@type='radio' and @value='{role}' and @checked]]//td[4]";

	public static void selectCard(Page page, String cardName) {
		String card = cardName.replace(" ", "-");
		Locator cardLocator = page.getByTestId(CATALOG_SETTINGS_CARD_DATA_TESTID.replace("{cardName}", card));
		cardLocator.isVisible();
		cardLocator.click();
	}

	public static void searchCatalog(Page page, String catalogName) {
		Locator searchApp = page.getByPlaceholder(PROJECT_SEARCHBOX_PLACEHOLDER);
		if (searchApp.isVisible()) {
			searchApp.fill(catalogName);
		} else {
			page.getByPlaceholder(CATALOG_SEARCHBOX_PLACEHOLDER).nth(1).fill(catalogName);
		}
	}

	public static void clickOnCatalogCard(Page page, String catalogName) {
		Locator catalogCardLocator = page.locator(CATALOG_CARD_XPATH.replace("{catalogName}", catalogName));
		Locator appCardLocator = page.locator(APP_CARD_XPATH.replace("{appName}", catalogName));
		if (appCardLocator.count() > 0 && appCardLocator.isVisible()) {
			appCardLocator.click();
		} else if (catalogCardLocator.count() > 0 && catalogCardLocator.isVisible()) {
			catalogCardLocator.click();
		} else {
			throw new RuntimeException("No visible card found for catalog/app: " + catalogName);
		}
	}

	public static String getUserNameFromConfig(Page page, String userType) {
		String userName = "";
		switch (userType.toLowerCase()) {
		case "author":
			userName = ConfigUtils.getValue("author_username");
			break;
		case "editor":
			userName = ConfigUtils.getValue("editor_username");
			break;
		case "read":
		case "read-only":
			userName = ConfigUtils.getValue("read_username");
			break;
		default:
			throw new IllegalArgumentException("Unknown user type: " + userType);
		}
		return userName;
	}

	public static boolean validateUserPermissions(Page page, String expectedRole) {
		Locator accessRadioButton = page.locator(ACCESS_RADIO_BUTTON_XPATH.replace("{role}", expectedRole));
		boolean isSelected = accessRadioButton.isChecked();
		return isSelected;
	}

	public static void searchUserInUserSearchbar(Page page, String userType) {
		String userName = getUserNameFromConfig(page, userType);
		Locator searchIcon = page.locator(MEMBER_SEARCH_ICON_XPATH);
		if (searchIcon.isVisible()) {
			searchIcon.click();
		}
		Locator searchBox = page.locator(SEARCH_MEMBER_SEARCHBOX_XPATH);
		searchBox.clear();
		searchBox.fill(userName);
		page.waitForTimeout(1000);
	}

	public static void changeUserRole(Page page, String currentRole, String newRole) {
		Locator editIcon = page.locator(USER_EDIT_ICON_XPATH.replace("{role}", currentRole));
		editIcon.isVisible();
		editIcon.click();
		page.locator(EDIT_MEMBER_ACCESS_BUTTON_XPATH.replace("{role}", newRole)).click();
		page.locator(EDIT_MEMBER_UPDATE_BUTTON_XPATH).click();
		page.waitForTimeout(500);
	}

	public static void deleteUserFromMembersList(Page page, String role) {
		Locator deleteIcon = page.locator(USER_DELETE_ICON_XPATH.replace("{role}", role));
		deleteIcon.click();
		Locator confirmDeleteButton = page.locator(DELETE_USER_CONFIRM_BUTTON_XPATH);
		if (confirmDeleteButton.isVisible()) {
			confirmDeleteButton.click();
		}
	}

	public static boolean verifyUserIsExists(Page page, String role) {
		Locator accessRadioButton = page.locator(ACCESS_RADIO_BUTTON_XPATH.replace("{role}", role));
		return accessRadioButton.isVisible();
	}

	public static boolean isIconDisabled(Page page, String role, String iconType) {
		Locator iconLocator;
		if (iconType.equalsIgnoreCase("delete")) {
			iconLocator = page.locator(USER_DELETE_ICON_XPATH.replace("{role}", role));
		} else if (iconType.equalsIgnoreCase("edit")) {
			iconLocator = page.locator(USER_EDIT_ICON_XPATH.replace("{role}", role));
		} else {
			throw new IllegalArgumentException("Unknown icon type: " + iconType);
		}
		return iconLocator.isDisabled();
	}

	public static String getCurrentUtcTime() {
		return LocalDateTime.now(ZoneOffset.UTC).withNano(0).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}

	public static String getUserPermissionDate(Page page, String role) {
		return page.locator(USER_PERMISSION_DATE_XPATH.replace("{role}", role)).innerText();
	}
}
