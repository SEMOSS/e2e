package aicore.utils.page.app;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import aicore.utils.AICorePageUtils;
import aicore.utils.TestResourceTrackerHelper;

public class CreateAppPopupUtils {
	private static final String CODE_APP_GET_STARTED_BUTTON_DATA_TESTID = "createAppSection-new-app-code-btn-btn";
	public static final String NAME_TEXTBOX_XPATH = "//div[contains(@class,'MuiFormControl-root MuiFormControl-fullWidth')]//label[text()='Name']";
	private static final String DESCRIPTION_TEXTBOX_XPATH = "//div[contains(@class,'MuiFormControl-root MuiTextField-root')]//label[text()='Description']";
	private static final String TAG_TEXTBOX_XPATH = "//input[contains(@placeholder,'to add tag') and @role='combobox']";
	private static final String CREATE_BUTTON_XPATH = "//button[span[text()='Create']]";
	private static final String IFRAME_BUTTON_XPATH = "//button[text()='IFrame']";
	private static final String SELECT_APP_XPATH = "//span[text()='{Select_App}']";
	private static final String USER_FETCH_APP_NAME_XPATH = "//nav[contains(@class,'MuiBreadcrumbs-root')]//li[@class='MuiBreadcrumbs-li']//a[contains(@href,'/view')]//h6[contains(@class,'MuiTypography-subtitle1')]";

	public static void clickOnGetStartedButton(Page page, String appType) {
		if (appType.toLowerCase().contains("agent")) {
			page.getByTestId("createAppSection-new-app-agent-btn-btn").click();
		} else if (appType.toLowerCase().contains("drag and drop")) {
			page.getByTestId("createAppSection-new-app-drag-btn-btn").click();
		} else {
			page.getByTestId(CODE_APP_GET_STARTED_BUTTON_DATA_TESTID).click();
		}
	}

	public static String enterAppName(Page page, String appName, String timestamp) {
		String appNameTesting = appName + " " + timestamp;
		page.locator(NAME_TEXTBOX_XPATH).fill(appNameTesting);
		return appNameTesting;
	}

	public static void selectApp(Page page, String appName, String timestamp) {
		String appNameTesting = appName + " " + timestamp;
		page.getByPlaceholder("Project").click();
		page.getByPlaceholder("Project").fill(appNameTesting);
		page.locator(SELECT_APP_XPATH.replace("{Select_App}", appNameTesting)).click();
	}

	public static void enterAppDescription(Page page, String appDescription) {
		page.locator(DESCRIPTION_TEXTBOX_XPATH).fill(appDescription);
	}

	public static void enterTags(Page page, String tagName) {
		page.locator(TAG_TEXTBOX_XPATH).fill(tagName);
		page.keyboard().press("Enter");
	}

	public static void clickOnCreateButton(Page page) {
		page.locator(CREATE_BUTTON_XPATH).click();
	}

	public static String userFetchAppName(Page page) {
		Locator getAppName = page.locator(USER_FETCH_APP_NAME_XPATH);
		AICorePageUtils.waitFor(getAppName);
		String actualAppName = getAppName.textContent().trim();
		TestResourceTrackerHelper.getInstance().setAppName(actualAppName);
		return actualAppName;
	}

	public static void clickOnUploadButton(Page page) {
		page.getByTestId("createAppSection-upload-btn").click();
	}

	public static void clickOnShareAppButton(Page page) {
		page.getByTestId("ShareRoundedIcon").click();
	}

	public static void clickOnIframeButton(Page page) {
		page.waitForTimeout(500);
		Locator iframeButton = page.locator(IFRAME_BUTTON_XPATH).first();
		iframeButton.click(new Locator.ClickOptions().setForce(true));
	}

	public static void clickOnCloseButton(Page page) {
		page.getByTestId("ClearIcon").nth(1).click(new Locator.ClickOptions().setForce(true));
	}
}
