package aicore.utils.page.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

import aicore.utils.AICorePageUtils;
import aicore.utils.CommonUtils;

public class AppPageUtils {

	private static final Logger logger = LogManager.getLogger(AppPageUtils.class);

	public static final String CREATE_NEW_APP_DATA_TEST_ID = "home-create-app-btn";
	public static final String APP_CARD_XPATH = "//p[text()='{appName}']";
	public static final String OPEN_APP_LINK_XPATH = "//p[text()='{appName}']/ancestor::div[contains(@class,'MuiCardHeader-root')]/following-sibling::div//a";
	public static final String APP_SEARCH_TEXTBOX_XPATH = "//input[contains(@class,'MuiInputBase-input MuiOutlinedInput-input ') and @placeholder='Search']";
	public static final String MORE_VERTICAL_OPTIONS_ICON_XPATH = "//p[text()='{appName}']/ancestor::div[contains(@class,'MuiCardHeader-root')]/following-sibling::div[contains(@class,'MuiCardActions-root')]//*[name()='svg' and @data-testid='MoreVertIcon']";
	public static final String MORE_VERTICAL_OPTION_XPATH = "//li[@value='{optionValue}']";
	public static final String ID_COPY_TOAST_MESSAGE_XPATH = "//div[text()='Succesfully copied to clipboard']";

	public static void clickOnCreateNewAppButton(Page page) {
		page.getByTestId(CREATE_NEW_APP_DATA_TEST_ID).click();
	}

	public static void searchApp(Page page, String appName, String timestamp) {
		page.getByLabel("Search").click();
		page.getByLabel("Search").fill(appName + " " + timestamp);
	}

	public static void clickOnAppCard(Page page, String appName, String timestamp) {
		String expectedAppName = appName + " " + timestamp;
		Locator appCard = page.locator((APP_CARD_XPATH.replace("{appName}", expectedAppName)));
		AICorePageUtils.waitFor(appCard);
		Locator anchor = page.locator(OPEN_APP_LINK_XPATH.replace("{appName}", expectedAppName));
		CommonUtils.removeTargetAttribute(anchor);
		appCard.click();
	}

	public static void clickOnMoreVertIcon(Page page, String appName) {
		Locator iconLocator = page.locator(MORE_VERTICAL_OPTIONS_ICON_XPATH.replace("{appName}", appName));
		iconLocator.scrollIntoViewIfNeeded();
		iconLocator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		iconLocator.click();
	}

	public static void clickOnOption(Page page, String optionName) {
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
		Locator optionLocator = page.locator(MORE_VERTICAL_OPTION_XPATH.replace("{optionValue}", optionValue));
		optionLocator.isVisible();
		optionLocator.click();
	}

	public static String getAppIdCopiedToastMessage(Page page) {
		page.locator(ID_COPY_TOAST_MESSAGE_XPATH).isVisible();
		return page.locator(ID_COPY_TOAST_MESSAGE_XPATH).textContent().trim();
	}

}
