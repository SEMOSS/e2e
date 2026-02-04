package aicore.utils.page.app;

import java.util.ArrayList;
import java.util.List;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import aicore.utils.AICorePageUtils;
import aicore.utils.CommonUtils;
import aicore.utils.HomePageUtils;
import aicore.utils.TestResourceTrackerHelper;

public class CreateAppPopupUtils {
	private static final String CODE_APP_GET_STARTED_BUTTON_XPATH = "createAppSection-new-app-code-btn-btn";
	public static final String NAME_TEXTBOX_XPATH = "//div[contains(@class,'MuiFormControl-root MuiFormControl-fullWidth')]//label[text()='Name']";
	private static final String DESCRIPTION_TEXTBOX_XPATH = "//div[contains(@class,'MuiFormControl-root MuiTextField-root')]//label[text()='Description']";
	private static final String TAG_TEXTBOX_XPATH = "//input[contains(@placeholder,'to add tag') and @role='combobox']";
	private static final String CREATE_BUTTON_XPATH = "//button[span[text()='Create']]";
	private static final String IFRAME_BUTTON_XPATH = "//button[text()='IFrame']";
	private static final String SELECT_APP_XPATH = "//span[text()='{Select_App}']";
	private static final String USER_FETCH_APP_NAME_XPATH = "//nav[contains(@class,'MuiBreadcrumbs-root')]//li[@class='MuiBreadcrumbs-li']//a[contains(@href,'/view')]//div";
	public static List<String> createdAppNames = new ArrayList<>();
	private static final String BREADCRUMB_LINK_XPATH = "//a//div[text()='{appName}']";
	private static final String DRAG_AND_DROP_GET_STARTED_XPATH = "createAppSection-new-app-drag-btn-btn";
	private static final String AGENT_BUILDER_GET_STARTED_XPATH = "createAppSection-new-app-agent-btn-btn";

	public static void clickOnGetStartedButton(Page page, String appType) {
		if (appType.toLowerCase().contains("agent")) {
			page.getByTestId(AGENT_BUILDER_GET_STARTED_XPATH).click();
		} else if (appType.toLowerCase().contains("drag and drop")) {
			page.getByTestId(DRAG_AND_DROP_GET_STARTED_XPATH).click();
		} else {
			page.getByTestId(CODE_APP_GET_STARTED_BUTTON_XPATH).click();
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

	public static void createMultipleApps(Page page, String appType, String appName, String appDescription,
			String appTags) {
		HomePageUtils.openMainMenu(page);
		HomePageUtils.clickOnOpenAppLibrary(page);
		Locator createNewAppBtn = page.getByTestId("appCatalogPage-create-new-app-btn");
		createNewAppBtn.click();
		clickOnGetStartedButton(page, appType);
		page.locator(NAME_TEXTBOX_XPATH).fill(appName);
		enterAppDescription(page, appDescription);
		enterTags(page, appTags);
		clickOnCreateButton(page);
		createdAppNames.add(appName);
	}

	public static void deleteCreatedApps(Page page) {
		for (String appName : createdAppNames) {
			if (appName != null && !appName.isBlank()) {
				CommonUtils.navigateAndDeleteApp(page, appName);
			}
		}
	}

	public static void userClickOnBreadcrumbLink(Page page, String appName, String timestamp) {
		page.locator(BREADCRUMB_LINK_XPATH.replace("{appName}", appName + " " + timestamp)).first().click();
	}
}
