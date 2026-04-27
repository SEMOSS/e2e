package aicore.utils.page.app;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

import aicore.utils.AICorePageUtils;
import aicore.utils.CommonUtils;

public class AppPageUtils {

	private static final Logger logger = LogManager.getLogger(AppPageUtils.class);

	public static final String CREATE_NEW_APP_DATA_TEST_ID = "appCatalogPage-create-new-app-btn";
	public static final String APP_CARD_XPATH = "//h3[text()='{appName}']";
	public static final String APP_DESCRIPTION_XPATH = "//p[text()='{description}']";
	public static final String OPEN_APP_LINK_XPATH = "//button[text()='{buttonName}']";
	public static final String APP_SEARCH_TEXTBOX_XPATH = "//input[contains(@class,'MuiInputBase-input MuiOutlinedInput-input ') and @placeholder='Search']";
	public static final String MORE_VERTICAL_OPTIONS_ICON_XPATH = "//button[@aria-label='More options']";
	public static final String MORE_VERTICAL_OPTION_XPATH = "//li[@value='{optionValue}']";
	public static final String ID_COPY_TOAST_MESSAGE_XPATH = "//li[@data-type='success']";
	private static final String SELECT_FILTER_VALUE_XPATH = "//h6[text()='{filterCategory}']/parent::button/following-sibling::div//span[text()='{filterValue}']";
	private static final String INFO_BUTTON_XPATH = "//a[text()='{buttonName}']";
	private static final String APP_BOOKMARK_ICON_XPATH = "//button[@aria-label='Add bookmark']";
	private static final String PUBLISHED_DATE_XPATH = "//p[text()='{publishedDate}']";
	private static final String LAST_EDITED_DATE_XPATH = "//p[text()='{lastEditedDate}']";
	private static final String MORE_VERTICAL_OPTIONS_XPATH = "//div[text()='{optionName}']";
	private static final String FILTER_OPTION_XPATH = "//button[@data-slot='popover-trigger']";
	private static final String APPS_NAME_XPATH = "//button//h3 | //img/../..//div[1]//p";
	private static final String SORT_BY_DROPDOWN_XPATH = "[aria-label='Sort By']";
	private static final String SORT_BY_OPTION_XPATH = "//div[@role='option']//span[text()='{optionName}']";
	private static final String CARDS_VIEW_OPTIONS_XPATH = "//button[@aria-label='{view}']";
	private static final String COPY_ID_XPATH = "//button[@aria-label='{icon}']";
	private static final String DATA_CLASSIFICATION_CHECKBOX_XPATH = "//span[text()='{option}']";
	private static final String APP_SETTINGS_SUBMIT_TESTID = "save";
	private static final String APP_LIST_VIEW  = "Open app in new tab";
	private static final String APP_GRID_VIEW = "Open";
	private static final String DATE_CREATED_XPATH = "//button[@title='Private engine']/../../../div[1]//div[2]//span[contains(text(),'2026')]";

	public static void clickOnCreateNewAppButton(Page page) {
		page.getByTestId(CREATE_NEW_APP_DATA_TEST_ID).click();
	}

	public static void searchApp(Page page, String appName, String timestamp) {
		page.getByLabel("Search apps").click();
		page.getByLabel("Search apps").fill(appName + " " + timestamp);
		page.waitForTimeout(500);
	}

	public static void selectAppCardsView(Page page, String view) {
		page.locator(CARDS_VIEW_OPTIONS_XPATH.replace("{view}", view)).click();
	}

	public static void clickOnEditButtoninSettings(Page page) {
		page.getByTestId("appDetail-edit-btn").click();			
	}

	public static void enterTagNameinAppSettings(Page page, String tagName) {
		Locator enterTag = page.getByTestId("tags");
		enterTag.scrollIntoViewIfNeeded();
		enterTag.click();
		enterTag.fill(tagName);
		enterTag.press("Enter");
	}

	public static void enterDomainNameinAppSettings(Page page, String domainName) {
		Locator enterDomain = page.getByPlaceholder("Press enter to add domain");
		enterDomain.scrollIntoViewIfNeeded();
		enterDomain.fill(domainName);
		enterDomain.press("Enter");
	}

	public static void selectDataClassificationOptioninAppSettings(Page page, String option) {
		Locator selectCheckbox = page.locator(DATA_CLASSIFICATION_CHECKBOX_XPATH.replace("{option}", option));
		selectCheckbox.scrollIntoViewIfNeeded();
		selectCheckbox.click();
	}

	public static void selectDataRestrictionsOptioninAppSettings(Page page, String option) {
		Locator selectCheckbox = page.locator(DATA_CLASSIFICATION_CHECKBOX_XPATH.replace("{option}", option));
		selectCheckbox.scrollIntoViewIfNeeded();
		selectCheckbox.click();
	}

	public static void clickOnSubmitButtoninAppSettings(Page page) {
		Locator submitButton = page.getByTestId(APP_SETTINGS_SUBMIT_TESTID);
		submitButton.scrollIntoViewIfNeeded();
		submitButton.click();
	}

	public static void searchAppId(Page page, String appId) {
		page.getByLabel("Search apps").click();
		page.getByLabel("Search apps").fill(appId);
	}

	public static void clickOnAppCard(Page page, String appName, String timestamp) {
		String expectedAppName = appName + " " + timestamp;
		Locator appCard = page.locator((APP_CARD_XPATH.replace("{appName}", expectedAppName)));
		AICorePageUtils.waitFor(appCard);
		appCard.click();
	}

	public static void clickOnMoreVertIcon(Page page, String appName, String timestamp) {
		page.waitForTimeout(200);
		Locator appCard = page.locator(APP_CARD_XPATH.replace("{appName}", appName));
		AICorePageUtils.waitFor(appCard);
		appCard.scrollIntoViewIfNeeded();
		Locator iconLocator = page.locator(MORE_VERTICAL_OPTIONS_ICON_XPATH);
		iconLocator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		iconLocator.click();
	}

	public static String clickOnOption(Page page, String optionName) {
		Locator optionLocator = page.locator(MORE_VERTICAL_OPTIONS_XPATH.replace("{optionName}", optionName));
		optionLocator.isVisible();
		optionLocator.click();
		if (optionName.equals("Copy App ID")) {
			return CommonUtils.readCopiedTextFromClipboard(page);
		}
		return null;
	}

	public static String getCopiedId(Page page, String icon) {
		page.locator(COPY_ID_XPATH.replace("{icon}", icon)).click();
		return CommonUtils.readCopiedTextFromClipboard(page);
	}

	public static String getAppIdCopiedToastMessage(Page page) {
		page.locator(ID_COPY_TOAST_MESSAGE_XPATH).isVisible();
		return page.locator(ID_COPY_TOAST_MESSAGE_XPATH).textContent().trim();
	}

	public static void enterCloneAppName(Page page, String appName, String timestamp) {
		page.getByLabel("Name").click();
		page.getByLabel("Name").fill(appName + " " + timestamp);
	}

	public static void enterCloneAppDescription(Page page, String appDescription) {
		page.getByLabel("Description").click();
		page.getByLabel("Description").fill(appDescription);
	}

	public static void clickOnButton(Page page, String buttonName) {
		AICorePageUtils.clickOnButton(page, buttonName);
	}

	public static boolean isAppDisplayedOnPage(Page page, String appName, String timestamp) {
		String expectedAppName = appName + " " + timestamp;
		Locator appCard = page.locator((APP_CARD_XPATH.replace("{appName}", expectedAppName)));
		AICorePageUtils.waitFor(appCard);
		return appCard.isVisible();
	}

	public static boolean isContentVisibleOnAppCard(Page page, String contentName, String contentValue,
			String timestamp) {
		// Expected formatted date (e.g., "October 22, 2025")
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);
		String expectedDate = LocalDate.now().format(formatter);
		Locator locator = null;
		switch (contentName) {
		case "App Name":
			locator = page.locator(APP_CARD_XPATH.replace("{appName}", contentValue + " " + timestamp));
			break;
		case "App Description":
			locator = page.locator(APP_DESCRIPTION_XPATH.replace("{description}", contentValue));
			break;
		case "Open App button":
			locator = page.locator(OPEN_APP_LINK_XPATH.replace("{buttonName}", contentValue));
			break;
		case "Info button":
			locator = page.locator(INFO_BUTTON_XPATH.replace("{buttonName}", contentValue));
			break;
		case "More Vert Icon":
			locator = page.locator(MORE_VERTICAL_OPTIONS_ICON_XPATH);
			break;
		case "Bookmark Icon":
			locator = page.locator(APP_BOOKMARK_ICON_XPATH);
			break;
		case "Published date":
			locator = page.locator(
					PUBLISHED_DATE_XPATH.replace("{publishedDate}", contentValue.replace("{date}", expectedDate)));
			break;
		case "Last Edited date":
			locator = page.locator(
					LAST_EDITED_DATE_XPATH.replace("{lastEditedDate}", contentValue.replace("{date}", expectedDate)));
			break;
		default:
			logger.error("Invalid option name: " + contentName);
			throw new IllegalArgumentException("Invalid option name: " + contentName);
		}
		locator.scrollIntoViewIfNeeded();
		return locator.isVisible();
	}

	public static boolean isAppNotDisplayedOnPage(Page page, String appName, String timestamp) {
		String expectedAppName = appName + " " + timestamp;
		Locator appCard = page.locator(APP_CARD_XPATH.replace("{appName}", expectedAppName));
		return !appCard.isVisible();
	}

	public static void clickOnFilterOption(Page page) {
		page.locator(FILTER_OPTION_XPATH).click();
	}

	public static void searchFilterValueOnAppPage(Page page, String filterValue) {
		page.getByPlaceholder("Search by...").fill(filterValue);
	}

	public static void selectFilterValueOnAppPage(Page page, String filterCategory, String filterValue) {
		Locator filterValueLocator = page.locator(SELECT_FILTER_VALUE_XPATH.replace("{filterCategory}", filterCategory)
				.replace("{filterValue}", filterValue));
		filterValueLocator.waitFor();
		filterValueLocator.click();
	}

	public static void clickOnInfoButton(Page page, String buttonName) {
		page.locator(INFO_BUTTON_XPATH.replace("{buttonName}", buttonName)).click();
	}

	public static void clickOnDiscoverableAppsButton(Page page) {
		page.getByTestId("appCatalogPage-discoverable-btn").click();
	}

	public static void clickOnFilterButton(Page page, String filterName) {
		page.getByTitle(filterName + " Order").click();
	}

	public static boolean verifySortedInAscendingOrder(Page page) {
		Locator appNamesLocator = page.locator(APPS_NAME_XPATH);
		int appCount = appNamesLocator.count();
		String previousAppName = "";
		for (int i = 0; i < appCount; i++) {
			String currentAppName = appNamesLocator.nth(i).textContent().trim();
			if (currentAppName.compareToIgnoreCase(previousAppName) < 0) {
				return false;
			}
			previousAppName = currentAppName;
		}
		return true;
	}

	public static boolean verifySortedInDescendingOrder(Page page) {
		Locator appNamesLocator = page.locator(APPS_NAME_XPATH);
		int appCount = appNamesLocator.count();
		String previousAppName = null;
		for (int i = 0; i < appCount; i++) {
			String currentAppName = appNamesLocator.nth(i).textContent().trim();
			if (previousAppName != null && currentAppName.compareToIgnoreCase(previousAppName) > 0) {
				return false;
			}
			previousAppName = currentAppName;
		}
		return true;
	}

	public static boolean verifyAppsSortedByDateLastEdited(Page page) {
		Locator lastEditedDatesLocator = page.locator(LAST_EDITED_DATE_XPATH.replace("{lastEditedDate}", ""));
		int appCount = lastEditedDatesLocator.count();
		String previousDateStr = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);
		for (int i = 0; i < appCount; i++) {
			String currentDateStr = lastEditedDatesLocator.nth(i).textContent().trim();
			currentDateStr = currentDateStr.replace("Last Edited: ", "");
			if (previousDateStr != null) {
				LocalDate previousDate = LocalDate.parse(previousDateStr, formatter);
				LocalDate currentDate = LocalDate.parse(currentDateStr, formatter);
				if (currentDate.isAfter(previousDate)) {
					return false;
				}
			}
			previousDateStr = currentDateStr;
		}
		return true;
	}

	public static boolean verifyAppsSortedByUpdatedAgo(Page page) {
		return verifyAppsSortedByUpdatedAgo(page, false);
	}

	/**
	 * Verifies that the apps are sorted by the 'Updated ... ago' format
	 * (descending: most recent first). Handles 'Updated today', 'Updated X days
	 * ago', 'Updated X month(s) ago'
	 */
	public static boolean verifyAppsSortedByUpdatedAgo(Page page, boolean descending) {
		Locator dateLocators = page.locator("//p[contains(text(),'Updated')]");
		int appCount = dateLocators.count();
		int previousDaysAgo = descending ? Integer.MIN_VALUE : Integer.MAX_VALUE;
		for (int i = 0; i < appCount; i++) {
			String dateText = dateLocators.nth(i).textContent().trim().toLowerCase(Locale.ENGLISH);
			int daysAgo = parseUpdatedAgoToDays(dateText);
			if (descending) {
				if (daysAgo < previousDaysAgo) {
					return false;
				}
			} else {
				if (daysAgo > previousDaysAgo) {
					return false;
				}
			}
			previousDaysAgo = daysAgo;
		}
		return true;
	}

	private static int parseUpdatedAgoToDays(String dateText) {
		if (dateText.contains("today")) {
			return 0;
		} else if (dateText.contains("day")) {
			return extractNumber(dateText);
		} else if (dateText.contains("month")) {
			return extractNumber(dateText) * 30; // Approximate a month as 30 days
		}
		return Integer.MAX_VALUE; // fallback for unknown format
	}

	private static int extractNumber(String text) {
		java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("\\d+").matcher(text);
		if (matcher.find()) {
			return Integer.parseInt(matcher.group());
		}
		return 0; // Default if no number found
	}

	public static void selectSortByOption(Page page, String optionName) {
		Locator sortDropdown = page.locator(SORT_BY_DROPDOWN_XPATH);
		sortDropdown.click();
		Locator optionLocator = page.locator(SORT_BY_OPTION_XPATH.replace("{optionName}", optionName));
		optionLocator.waitFor();
		optionLocator.click();
	}

	public static void clickOnViewFilterButton(Page page, String view) {
		page.getByTitle(view).click();
	}

	public static boolean verifyAppsInTheGridView(Page page) {
		int appInTheGridView =page.getByText(APP_GRID_VIEW).count();
		return appInTheGridView > 0;
	}

	public static boolean verifyAppsInTheListView(Page page) {
		int appInTheListView =page.getByLabel(APP_LIST_VIEW).count();
		return appInTheListView > 0;
	}

	public static boolean verifySortedByDateCreated(Page page, boolean ascending) {
        Locator dateCreatedLocator = page.locator(DATE_CREATED_XPATH);
        int appCount = dateCreatedLocator.count();
        String previousDateStr = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH);

        for (int i = 0; i < appCount; i++) {
            String currentDateStr = dateCreatedLocator.nth(i).textContent().trim();
            if (previousDateStr != null) {
                LocalDate previousDate = LocalDate.parse(previousDateStr, formatter);
                LocalDate currentDate = LocalDate.parse(currentDateStr, formatter);
                if (ascending) {
                    if (currentDate.isBefore(previousDate)) {
                        return false;
                    }
                } else {
                    if (currentDate.isAfter(previousDate)) {
                        return false;
                    }
                }
            }
            previousDateStr = currentDateStr;
        }
        return true;
    }
}
