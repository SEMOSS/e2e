package aicore.utils.page.app;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.LoadState;
import aicore.utils.CommonUtils;
import aicore.utils.waitLayer.Waits;
import io.qameta.allure.Step;

public class AppPageUtils {

	    private static final Logger logger = LogManager.getLogger(AppPageUtils.class);
	    
	    // App Catalog - Main elements
	    public static final String CREATE_NEW_APP_DATA_TEST_ID = "appCatalogPage-create-new-app-btn";
	    public static final String APP_CARD_XPATH = "//h3[text()='{appName}']";
	    public static final String APP_DESCRIPTION_XPATH = "//p[text()='{description}']";
	    public static final String OPEN_APP_LINK_XPATH = "//button[text()='{buttonName}']";
	    public static final String APP_SEARCH_TEXTBOX_XPATH = "//input[@placeholder='Search']";
	    public static final String APP_SEARCH_BY_LABEL = "Search apps";
	    
	    // More Options (3 dots menu)
	    public static final String MORE_VERTICAL_OPTIONS_ICON_XPATH = "//button[@aria-label='More options']";
	    public static final String MORE_VERTICAL_OPTION_XPATH = "//li[@value='{optionValue}']";
	    public static final String MORE_VERTICAL_OPTIONS_XPATH = "//div[text()='{optionName}']";
	    public static final String ID_COPY_TOAST_MESSAGE_XPATH = "//li[@data-type='success']";
	    public static final String COPY_ID_XPATH = "//button[@aria-label='{icon}']";
	    
	    // Filter & Sort
	    private static final String FILTER_OPTION_XPATH = "//button[@data-slot='popover-trigger']";
	    private static final String SELECT_FILTER_VALUE_XPATH = "//span[text()='{filterCategory}']/ancestor::button/following-sibling::div//span[text()='{filterValue}']";
	    private static final String SORT_BY_DROPDOWN_XPATH = "[aria-label='Sort By']";
	    private static final String SORT_BY_OPTION_XPATH = "//div[@role='option']//span[text()='{optionName}']";
	    private static final String CARDS_VIEW_OPTIONS_XPATH = "//button[@aria-label='{view}']";
	    private static final String CREATED_BY_ME_FILTER_BUTTON_XPATH = "//label[text()='Created by me']/parent::div//button";
	    
	    // App details & info
	    private static final String INFO_BUTTON_XPATH = "//a[text()='{buttonName}']";
	    private static final String APP_BOOKMARK_ICON_XPATH = "//button[@aria-label='Add bookmark']";
	    private static final String PUBLISHED_DATE_XPATH = "//p[text()='{publishedDate}']";
	    private static final String LAST_EDITED_DATE_XPATH = "//p[text()='{lastEditedDate}']";
	    private static final String APPS_NAME_XPATH = "//button//h3 | //img/../..//div[1]//p";
	    private static final String DATE_CREATED_XPATH = "//button[@title='Private engine']/../../../div[1]//div[2]//span[contains(text(),'2026')]";
	    
	    // App Settings
	    private static final String APP_SETTINGS_SUBMIT_TESTID = "save";
	    private static final String DATA_CLASSIFICATION_CHECKBOX_XPATH = "//span[text()='{option}']";
	    private static final String APP_LIST_VIEW = "Open app in new tab";
	    private static final String APP_GRID_VIEW = "Open";
	    
	    // ==================== CONSTANTS ====================
	    private static final String[] DATA_CLASSIFICATION_ALL_OPTIONS = { "IP", "PHI", "PII", "Public" };
	    private static final String[] DATA_RESTRICTIONS_ALL_OPTIONS = { "IP Allowed", "PHI Allowed", "FOUO Allowed" };
	    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);

	    // ==================== ACTIONS - App Creation ====================

	    @Step("Click on Create New App button")
	    public static void clickOnCreateNewAppButton(Page page) {
	        logger.info("Clicking on 'Create New App' button");
	        Locator createBtn = page.getByTestId(CREATE_NEW_APP_DATA_TEST_ID);
	        Waits.waitForElementClickable(createBtn);
	        createBtn.click();
	        logger.info("Create New App button clicked");
	    }

	    // ==================== ACTIONS - Search ====================

	    @Step("Search for app: {appName}")
	    public static void searchApp(Page page, String appName, String timestamp) {
	        logger.info("Searching for app: {} with timestamp: {}", appName, timestamp);
	        Locator searchBox = page.getByLabel(APP_SEARCH_BY_LABEL);
	        Waits.waitForElementVisible(searchBox);
	        
	        searchBox.click();
	        searchBox.clear();
	        
	        String searchTerm = (timestamp != null && !timestamp.isEmpty()) 
	            ? appName + " " + timestamp 
	            : appName;
	        searchBox.fill(searchTerm);
	        searchBox.press("Enter");
	        
	        page.waitForTimeout(500);
	        logger.info("Search completed for: {}", searchTerm);
	    }

	    @Step("Search by App ID: {appId}")
	    public static void searchAppId(Page page, String appId) {
	        logger.info("Searching by App ID: {}", appId);
	        Locator searchBox = page.getByLabel(APP_SEARCH_BY_LABEL);
	        Waits.waitForElementVisible(searchBox);
	        searchBox.click();
	        searchBox.fill(appId);
	        logger.info("App ID search completed: {}", appId);
	    }

	    // ==================== ACTIONS - App Cards ====================

	    @Step("Click on App Card: {appName}")
	    public static void clickOnAppCard(Page page, String appName, String timestamp) {
	        logger.info("Clicking on app card: {}", appName);
	        String expectedAppName = appName + "" + timestamp;
	        Locator appCard = page.locator(APP_CARD_XPATH.replace("{appName}", expectedAppName));
	        Waits.waitForElementVisible(appCard);
	        appCard.click();
	        logger.info("App card clicked: {}", expectedAppName);
	    }

	    @Step("Verify App is displayed: {appName}")
	    public static boolean isAppDisplayedOnPage(Page page, String appName, String timestamp) {
	        logger.debug("Verifying app is displayed: {}", appName);
	        String expectedAppName = appName + "" + timestamp;
	        Locator appCard = page.locator(APP_CARD_XPATH.replace("{appName}", expectedAppName));
	        try {
	            Waits.waitForElementVisible(appCard, 5000);
	            return appCard.isVisible();
	        } catch (Exception e) {
	            logger.debug("App not displayed: {}", appName);
	            return false;
	        }
	    }

	    @Step("Verify App is NOT displayed: {appName}")
	    public static boolean isAppNotDisplayedOnPage(Page page, String appName, String timestamp) {
	        logger.debug("Verifying app is NOT displayed: {}", appName);
	        String expectedAppName = appName + " " + timestamp;
	        Locator appCard = page.locator(APP_CARD_XPATH.replace("{appName}", expectedAppName));
	        try {
	            return !appCard.isVisible();
	        } catch (Exception e) {
	            return true; 
	        }
	    }

	    // ==================== ACTIONS - More Options Menu ====================

	    @Step("Click on More Options (3 dots) icon")
	    public static void clickOnMoreVertIcon(Page page, String appName, String timestamp) {
	        logger.info("Clicking on More Options icon for app: {}", appName);
	        page.waitForTimeout(200);
	        
	        Locator appCard = page.locator(APP_CARD_XPATH.replace("{appName}", appName));
	        Waits.waitForElementVisible(appCard);
	        appCard.scrollIntoViewIfNeeded();
	        
	        Locator iconLocator = page.locator(MORE_VERTICAL_OPTIONS_ICON_XPATH);
	        Waits.waitForElementVisible(iconLocator);
	        iconLocator.click();
	        logger.info("More Options icon clicked");
	    }

	    @Step("Select option from More Options menu: {optionName}")
	    public static String clickOnOption(Page page, String optionName) {
	        logger.info("Selecting option: {}", optionName);
	        Locator optionLocator = page.locator(MORE_VERTICAL_OPTIONS_XPATH.replace("{optionName}", optionName));
	        Waits.waitForElementVisible(optionLocator);
	        optionLocator.click();
	        
	        if (optionName.equals("Copy App ID")) {
	            String copiedId = CommonUtils.readCopiedTextFromClipboard(page);
	            logger.info("Copied App ID: {}", copiedId);
	            return copiedId;
	        }
	        logger.info("Option selected: {}", optionName);
	        return null;
	    }

	    @Step("Get Copied App ID")
	    public static String getCopiedId(Page page, String icon) {
	        logger.info("Getting copied App ID for icon: {}", icon);
	        Locator copyIcon = page.locator(COPY_ID_XPATH.replace("{icon}", icon));
	        Waits.waitForElementClickable(copyIcon);
	        copyIcon.click();
	        String copiedId = CommonUtils.readCopiedTextFromClipboard(page);
	        logger.info("Retrieved App ID: {}", copiedId);
	        return copiedId;
	    }

	    @Step("Get App ID copy toast message")
	    public static String getAppIdCopiedToastMessage(Page page) {
	        logger.info("Getting App ID copy toast message");
	        Locator toast = page.locator(ID_COPY_TOAST_MESSAGE_XPATH).first();
	        Waits.waitForElementVisible(toast);
	        String message = toast.textContent().trim();
	        logger.info("Toast message: {}", message);
	        return message;
	    }

	    // ==================== ACTIONS - View Options ====================

	    @Step("Select view: {view}")
	    public static void selectAppCardsView(Page page, String view) {
	        logger.info("Selecting view: {}", view);
	        Locator viewButton = page.locator(CARDS_VIEW_OPTIONS_XPATH.replace("{view}", view));
	        Waits.waitForElementClickable(viewButton);
	        viewButton.click();
	        logger.info("View selected: {}", view);
	    }

	    @Step("Verify Grid View is active")
	    public static boolean verifyAppsInTheGridView(Page page) {
	        logger.debug("Verifying Grid View is active");
	        int gridCount = page.getByText(APP_GRID_VIEW).count();
	        return gridCount > 0;
	    }

	    @Step("Verify List View is active")
	    public static boolean verifyAppsInTheListView(Page page) {
	        logger.debug("Verifying List View is active");
	        int listCount = page.getByLabel(APP_LIST_VIEW).count();
	        return listCount > 0;
	    }

	    // ==================== ACTIONS - Filter ====================

	    @Step("Click on Filter option")
	    public static void clickOnFilterOption(Page page) {
	        logger.info("Clicking on Filter option");
	        Locator filterButton = page.locator(FILTER_OPTION_XPATH);
	        Waits.waitForElementClickable(filterButton);
	        filterButton.click();
	        logger.info("Filter option clicked");
	    }

	    @Step("Search filter value: {filterValue}")
	    public static void searchFilterValueOnAppPage(Page page, String filterValue) {
	        logger.info("Searching filter value: {}", filterValue);
	        Locator searchFilter = page.getByPlaceholder("Search filters...");
	        Waits.waitForElementVisible(searchFilter);
	        searchFilter.fill(filterValue);
	        logger.info("Filter search completed: {}", filterValue);
	    }

	    @Step("Select filter value: {filterValue}")
	    public static void selectFilterValueOnAppPage(Page page, String filterCategory, String filterValue) {
	        logger.info("Selecting filter: {} - {}", filterCategory, filterValue);
	        Locator filterLocator = page.locator(
	            SELECT_FILTER_VALUE_XPATH
	                .replace("{filterCategory}", filterCategory)
	                .replace("{filterValue}", filterValue)
	        );
	        Waits.waitForElementVisible(filterLocator);
	        filterLocator.click();
	        logger.info("Filter selected: {}", filterValue);
	    }

	    @Step("Click on Created by Me filter")
	    public static void clickOnCreatedByMeToggleSwitch(Page page) {
	        logger.info("Clicking on Created by Me toggle switch");
	        Locator toggle = page.locator(CREATED_BY_ME_FILTER_BUTTON_XPATH);
	        Waits.waitForElementClickable(toggle);
	        toggle.click();
	        logger.info("Created by Me toggle clicked");
	    }

	    @Step("Click on Filter button: {filterName}")
	    public static void clickOnFilterButton(Page page, String filterName) {
	        logger.info("Clicking on filter button: {}", filterName);
	        Locator filterBtn = page.getByTitle(filterName + " Order");
	        Waits.waitForElementClickable(filterBtn);
	        filterBtn.click();
	        logger.info("Filter button clicked: {}", filterName);
	    }

	    // ==================== ACTIONS - Sort ====================

	    @Step("Select Sort By option: {optionName}")
	    public static void selectSortByOption(Page page, String optionName) {
	        logger.info("Selecting Sort By option: {}", optionName);
	        Locator sortDropdown = page.locator(SORT_BY_DROPDOWN_XPATH);
	        Waits.waitForElementClickable(sortDropdown);
	        sortDropdown.click();
	        
	        Locator optionLocator = page.locator(SORT_BY_OPTION_XPATH.replace("{optionName}", optionName));
	        Waits.waitForElementVisible(optionLocator);
	        optionLocator.click();
	        logger.info("Sort By option selected: {}", optionName);
	    }

	    // ==================== ACTIONS - Discoverable Apps ====================

	    @Step("Click on Discoverable Apps button")
	    public static void clickOnDiscoverableAppsButton(Page page) {
	        logger.info("Clicking on Discoverable Apps button");
	        page.waitForLoadState(LoadState.NETWORKIDLE);
	        Locator discoverableButton = page.getByTestId("appCatalogPage-discoverable-btn");
	        Waits.waitForElementClickable(discoverableButton);
	        discoverableButton.click();
	        logger.info("Discoverable Apps button clicked");
	    }

	    // ==================== ACTIONS - App Settings ====================

	    @Step("Click on Edit button in App Settings")
	    public static void clickOnEditButtoninSettings(Page page) {
	        logger.info("Clicking on Edit button in App Settings");
	        Locator editBtn = page.getByTestId("appDetail-edit-btn");
	        Waits.waitForElementClickable(editBtn);
	        editBtn.click();
	        logger.info("Edit button clicked");
	    }

	    @Step("Enter tag in App Settings: {tagName}")
	    public static void enterTagNameinAppSettings(Page page, String tagName) {
	        logger.info("Entering tag: {}", tagName);
	        Locator tagInput = page.getByTestId("tags");
	        tagInput.scrollIntoViewIfNeeded();
	        Waits.waitForElementVisible(tagInput);
	        tagInput.click();
	        tagInput.fill(tagName);
	        tagInput.press("Enter");
	        logger.info("Tag entered: {}", tagName);
	    }

	    @Step("Enter domain in App Settings: {domainName}")
	    public static void enterDomainNameinAppSettings(Page page, String domainName) {
	        logger.info("Entering domain: {}", domainName);
	        Locator domainInput = page.getByPlaceholder("Press enter to add domain");
	        domainInput.scrollIntoViewIfNeeded();
	        Waits.waitForElementVisible(domainInput);
	        domainInput.fill(domainName);
	        domainInput.press("Enter");
	        logger.info("Domain entered: {}", domainName);
	    }

	    @Step("Select Data Classification option: {option}")
	    public static void selectDataClassificationOptioninAppSettings(Page page, String option) {
	        logger.info("Selecting Data Classification: {}", option);
	        Locator checkbox = page.locator(
	            DATA_CLASSIFICATION_CHECKBOX_XPATH.replace("{option}", normalizeOptionText(option))
	        );
	        try {
	            checkbox.scrollIntoViewIfNeeded();
	            checkbox.click();
	            logger.info("Data Classification selected: {}", option);
	        } catch (PlaywrightException e) {
	            logVisibleOptions(page, "Data Classification", DATA_CLASSIFICATION_ALL_OPTIONS);
	            throw new RuntimeException("Could not select Data Classification option '" + option + "'", e);
	        }
	    }

	    @Step("Select Data Restrictions option: {option}")
	    public static void selectDataRestrictionsOptioninAppSettings(Page page, String option) {
	        logger.info("Selecting Data Restrictions: {}", option);
	        Locator checkbox = page.locator(
	            DATA_CLASSIFICATION_CHECKBOX_XPATH.replace("{option}", normalizeOptionText(option))
	        );
	        try {
	            checkbox.scrollIntoViewIfNeeded();
	            checkbox.click();
	            logger.info("Data Restrictions selected: {}", option);
	        } catch (PlaywrightException e) {
	            logVisibleOptions(page, "Data Restrictions", DATA_RESTRICTIONS_ALL_OPTIONS);
	            throw new RuntimeException("Could not select Data Restrictions option '" + option + "'", e);
	        }
	    }

	    @Step("Click Submit button in App Settings")
	    public static void clickOnSubmitButtoninAppSettings(Page page) {
	        logger.info("Clicking Submit button in App Settings");
	        Locator submitButton = page.getByTestId(APP_SETTINGS_SUBMIT_TESTID);
	        submitButton.scrollIntoViewIfNeeded();
	        Waits.waitForElementClickable(submitButton);
	        submitButton.click();
	        logger.info("Submit button clicked");
	    }

	    // ==================== ACTIONS - Clone App ====================

	    @Step("Enter Clone App Name: {appName}")
	    public static void enterCloneAppName(Page page, String appName, String timestamp) {
	        logger.info("Entering clone app name: {} with timestamp: {}", appName, timestamp);
	        Locator nameField = page.getByLabel("Name");
	        Waits.waitForElementVisible(nameField);
	        nameField.click();
	        nameField.fill(appName + " " + timestamp);
	        logger.info("Clone app name entered");
	    }

	    @Step("Enter Clone App Description: {appDescription}")
	    public static void enterCloneAppDescription(Page page, String appDescription) {
	        logger.info("Entering clone app description");
	        Locator descField = page.getByLabel("Description");
	        Waits.waitForElementVisible(descField);
	        descField.click();
	        descField.fill(appDescription);
	        logger.info("Clone app description entered");
	    }

	    // ==================== HELPER - Verification Methods ====================

	    
	    //** This Method should be delete , have cases are not in our new UI **//
	    @Step("Verify content on App Card: {contentName}")
	    public static boolean isContentVisibleOnAppCard(Page page, String contentName, String contentValue, String timestamp) {
	        logger.debug("Verifying content on app card: {} - {}", contentName, contentValue);
	        
	        String expectedDate = LocalDate.now().format(DATE_FORMATTER);
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
	                locator = page.locator(PUBLISHED_DATE_XPATH.replace("{publishedDate}", contentValue.replace("{date}", expectedDate)));
	                break;
	            case "Last Edited date":
	                locator = page.locator(LAST_EDITED_DATE_XPATH.replace("{lastEditedDate}", contentValue.replace("{date}", expectedDate)));
	                break;
	            default:
	                logger.error("Invalid option name: {}", contentName);
	                throw new IllegalArgumentException("Invalid option name: " + contentName);
	        }
	        
	        locator.scrollIntoViewIfNeeded();
	        boolean isVisible = locator.isVisible();
	        logger.debug("Content visibility: {}", isVisible);
	        return isVisible;
	    }

	    // ==================== HELPER - Sort Verification Methods ====================

	    public static boolean verifySortedInAscendingOrder(Page page) {
	        logger.debug("Verifying apps sorted in ascending order");
	        Locator appNamesLocator = page.locator(APPS_NAME_XPATH);
	        int appCount = appNamesLocator.count();
	        String previousAppName = "";
	        
	        for (int i = 0; i < appCount; i++) {
	            String currentAppName = appNamesLocator.nth(i).textContent().trim();
	            if (currentAppName.compareToIgnoreCase(previousAppName) < 0) {
	                logger.debug("Sorting violation found: {} > {}", previousAppName, currentAppName);
	                return false;
	            }
	            previousAppName = currentAppName;
	        }
	        logger.debug("Apps are sorted in ascending order");
	        return true;
	    }

	    public static boolean verifySortedInDescendingOrder(Page page) {
	        logger.debug("Verifying apps sorted in descending order");
	        Locator appNamesLocator = page.locator(APPS_NAME_XPATH);
	        int appCount = appNamesLocator.count();
	        String previousAppName = null;
	        
	        for (int i = 0; i < appCount; i++) {
	            String currentAppName = appNamesLocator.nth(i).textContent().trim();
	            if (previousAppName != null && currentAppName.compareToIgnoreCase(previousAppName) > 0) {
	                logger.debug("Sorting violation found: {} < {}", previousAppName, currentAppName);
	                return false;
	            }
	            previousAppName = currentAppName;
	        }
	        logger.debug("Apps are sorted in descending order");
	        return true;
	    }

	    public static boolean verifyAppsSortedByDateLastEdited(Page page) {
	        logger.debug("Verifying apps sorted by last edited date");
	        Locator dateLocators = page.locator(LAST_EDITED_DATE_XPATH.replace("{lastEditedDate}", ""));
	        int appCount = dateLocators.count();
	        String previousDateStr = null;
	        
	        for (int i = 0; i < appCount; i++) {
	            String currentDateStr = dateLocators.nth(i).textContent().trim()
	                .replace("Last Edited: ", "");
	            if (previousDateStr != null) {
	                LocalDate previousDate = LocalDate.parse(previousDateStr, DATE_FORMATTER);
	                LocalDate currentDate = LocalDate.parse(currentDateStr, DATE_FORMATTER);
	                if (currentDate.isAfter(previousDate)) {
	                    logger.debug("Date sorting violation: {} > {}", previousDate, currentDate);
	                    return false;
	                }
	            }
	            previousDateStr = currentDateStr;
	        }
	        logger.debug("Apps are sorted by last edited date");
	        return true;
	    }

	    public static boolean verifyAppsSortedByUpdatedAgo(Page page, boolean descending) {
	        logger.debug("Verifying apps sorted by updated ago");
	        Locator dateLocators = page.locator("//p[contains(text(),'Updated')]");
	        int appCount = dateLocators.count();
	        int previousDaysAgo = descending ? Integer.MIN_VALUE : Integer.MAX_VALUE;
	        
	        for (int i = 0; i < appCount; i++) {
	            String dateText = dateLocators.nth(i).textContent().trim().toLowerCase(Locale.ENGLISH);
	            int daysAgo = parseUpdatedAgoToDays(dateText);
	            
	            if (descending) {
	                if (daysAgo < previousDaysAgo) {
	                    logger.debug("Sorting violation in descending order");
	                    return false;
	                }
	            } else {
	                if (daysAgo > previousDaysAgo) {
	                    logger.debug("Sorting violation in ascending order");
	                    return false;
	                }
	            }
	            previousDaysAgo = daysAgo;
	        }
	        logger.debug("Apps are sorted by updated ago");
	        return true;
	    }

	    public static boolean verifySortedByDateCreated(Page page, boolean ascending) {
	        logger.debug("Verifying apps sorted by date created");
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
	                    if (currentDate.isBefore(previousDate)) return false;
	                } else {
	                    if (currentDate.isAfter(previousDate)) return false;
	                }
	            }
	            previousDateStr = currentDateStr;
	        }
	        logger.debug("Apps are sorted by date created");
	        return true;
	    }

	    // ==================== HELPER - Private Methods ====================

	    private static String normalizeOptionText(String option) {
	        return option == null ? null : option.toUpperCase();
	    }

	    private static void logVisibleOptions(Page page, String groupName, String[] allOptions) {
	        StringBuilder visible = new StringBuilder();
	        StringBuilder missing = new StringBuilder();
	        for (String opt : allOptions) {
	            long count = page.locator(
	                DATA_CLASSIFICATION_CHECKBOX_XPATH.replace("{option}", normalizeOptionText(opt))
	            ).count();
	            (count > 0 ? visible : missing).append(opt).append(", ");
	        }
	        logger.warn("{} - Visible: [{}] | Missing: [{}]", groupName, visible, missing);
	    }

	    private static int parseUpdatedAgoToDays(String dateText) {
	        if (dateText.contains("today")) {
	            return 0;
	        } else if (dateText.contains("day")) {
	            return extractNumber(dateText);
	        } else if (dateText.contains("month")) {
	            return extractNumber(dateText) * 30;
	        }
	        return Integer.MAX_VALUE;
	    }

	    private static int extractNumber(String text) {
	        Matcher matcher = Pattern.compile("\\d+").matcher(text);
	        if (matcher.find()) {
	            return Integer.parseInt(matcher.group());
	        }
	        return 0;
	    }
	}

