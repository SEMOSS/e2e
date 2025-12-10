package aicore.utils.page.app;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

import aicore.utils.AICorePageUtils;
import aicore.utils.CommonUtils;

public class AppPageUtils {

	private static final Logger logger = LogManager.getLogger(AppPageUtils.class);

	public static final String CREATE_NEW_APP_DATA_TEST_ID = "appCatalogPage-create-new-app-btn";
	public static final String APP_CARD_XPATH = "//p[text()='{appName}']";
	public static final String APP_DESCRIPTION_XPATH = "//span[text()='{description}']";
	public static final String OPEN_APP_LINK_XPATH = "//p[text()='{buttonName}']";
	public static final String APP_SEARCH_TEXTBOX_XPATH = "//input[contains(@class,'MuiInputBase-input MuiOutlinedInput-input ') and @placeholder='Search']";
	public static final String MORE_VERTICAL_OPTIONS_ICON_DATA_TESTID = "MoreVertIcon";
	public static final String MORE_VERTICAL_OPTION_XPATH = "//li[@value='{optionValue}']";
	public static final String ID_COPY_TOAST_MESSAGE_DATATESTID = "notification-success-message";
	public static final String MAKE_PUBLIC_BUTTON_XPATH = "//span[contains(@class,'MuiSwitch-root MuiSwitch')]//input[@type='checkbox']";
	public static final String DELETE_APP_CONFIRMATION_BUTTON_XPATH = "//button//span[text()='{name}']";
	private static final String SELECT_FILTER_VALUE_XPATH = "//h6[text()='{filterCategory}']/ancestor::li/following-sibling::div//p[text()='{filterValue}']";
	private static final String VIEW_DETAILS_BUTTON_XPATH = "//p[text()='{buttonName}']";
	private static final String APP_BOOKMARK_ICON_XPATH = "//button[@type='button']//*[name()='svg'][@data-testid='BookmarkBorderIcon']";
	private static final String PUBLISHED_DATE_XPATH = "//p[text()='{publishedDate}']";
	private static final String LAST_EDITED_DATE_XPATH = "//p[text()='{lastEditedDate}']";

	public static void clickOnCreateNewAppButton(Page page) {
		page.getByTestId(CREATE_NEW_APP_DATA_TEST_ID).click();
	}

	public static void searchApp(Page page, String appName, String timestamp) {
		page.getByLabel("Search").click();
		page.getByLabel("Search").fill(appName + " " + timestamp);
		page.waitForTimeout(500);
	}

	public static void searchAppId(Page page, String appId) {
		page.getByLabel("Search").click();
		page.getByLabel("Search").fill(appId);
	}

	public static void clickOnAppCard(Page page, String appName, String timestamp) {
		String expectedAppName = appName + " " + timestamp;
		Locator appCard = page.locator((APP_CARD_XPATH.replace("{appName}", expectedAppName)));
		AICorePageUtils.waitFor(appCard);
		// Locator anchor = page.locator(OPEN_APP_LINK_XPATH.replace("{appName}", expectedAppName));
		// CommonUtils.removeTargetAttribute(anchor);
		appCard.click();
	}

	public static void clickOnMoreVertIcon(Page page, String appName) {
		page.waitForTimeout(200);
		Locator appCard = page.locator((APP_CARD_XPATH.replace("{appName}", appName)));
		AICorePageUtils.waitFor(appCard);
		appCard.scrollIntoViewIfNeeded();
		Locator iconLocator = page.getByTestId(MORE_VERTICAL_OPTIONS_ICON_DATA_TESTID);
		iconLocator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		iconLocator.click();
	}

	public static String clickOnOption(Page page, String optionName) {
		String optionValue = null;
		switch (optionName) {
		case "Copy App ID":
			optionValue = "copy";
			break;
		case "Clone This App":
			optionValue = "clone";
			break;
		case "Delete App":
			optionValue = "delete";
			break;
		default:
			logger.error("Invalid option name: " + optionName);
			throw new IllegalArgumentException("Invalid option name: " + optionName);
		}
		Locator optionLocator = page.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName(optionValue));
		optionLocator.isVisible();
		optionLocator.click();
		if (optionName.equals("Copy App ID")) {
			return CommonUtils.readCopiedTextFromClipboard(page);
		}
		return null;
	}

	public static String getAppIdCopiedToastMessage(Page page) {
		// page.locator(ID_COPY_TOAST_MESSAGE_XPATH).isVisible();
		// return page.locator(ID_COPY_TOAST_MESSAGE_XPATH).textContent().trim();
		page.getByTestId(ID_COPY_TOAST_MESSAGE_DATATESTID).isVisible();
		return page.getByTestId(ID_COPY_TOAST_MESSAGE_DATATESTID).textContent().trim();
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

	public static void MakeAppPublic(Page page) {
		page.locator(MAKE_PUBLIC_BUTTON_XPATH).isVisible();
		page.locator(MAKE_PUBLIC_BUTTON_XPATH).click();
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
		case "View Details button":
			locator = page.locator(VIEW_DETAILS_BUTTON_XPATH.replace("{buttonName}", contentValue));
			break;
		case "More Vert Icon":
			locator = page.getByTestId(MORE_VERTICAL_OPTIONS_ICON_DATA_TESTID);
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

	public static void getAppDescription(Page page, String appName) {

	}

	public static void clickOnDeleteButton(Page page, String buttonName) {
		Locator deleteButton = page.locator(DELETE_APP_CONFIRMATION_BUTTON_XPATH.replace("{name}", buttonName));
		deleteButton.isVisible();
		deleteButton.click();
	}

	public static boolean isAppNotDisplayedOnPage(Page page, String appName, String timestamp) {
		String expectedAppName = appName + " " + timestamp;
		Locator appCard = page.locator((APP_CARD_XPATH.replace("{appName}", expectedAppName)));
		return !appCard.isVisible();
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

	public static void clickOnViewDetails(Page page, String buttonName) {
		page.locator(VIEW_DETAILS_BUTTON_XPATH.replace("{buttonName}", buttonName)).click();
	}

	public static void clickOnAccessControlButton(Page page) {
		AICorePageUtils.clickOnTabButton(page, "Access Control");
	}
}
