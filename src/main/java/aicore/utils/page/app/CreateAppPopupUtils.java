package aicore.utils.page.app;

import com.microsoft.playwright.Page;

public class CreateAppPopupUtils {
	private static final String GET_STARTED_BUTTON_IN_DRAG_AND_DROP_XPATH = "//div[h6[text()='{appType}']]/following-sibling::div/button[span[text()='Get started with our tools']]";
	public static final String NAME_TEXTBOX_XPATH = "//div[contains(@class,'MuiFormControl-root MuiFormControl-fullWidth')]//label[text()='Name']";
	private static final String DESCRIPTION_TEXTBOX_XPATH = "//div[contains(@class,'MuiFormControl-root MuiTextField-root')]//label[text()='Description']";
	private static final String TAG_TEXTBOX_XPATH = "//input[contains(@placeholder,'to add tag') and @role='combobox']";
	private static final String CREATE_BUTTON_XPATH = "//button[span[text()='Create']]";

	public static void clickOnGetStartedButton(Page page, String appType) {
		if (appType.toLowerCase().contains("agent")) {
			page.getByTestId("new-app-agent-btn").click();
		} else if (appType.toLowerCase().contains("drag and drop")) {
			page.getByTestId("new-app-drag-btn").click();
		} else {
			page.locator(GET_STARTED_BUTTON_IN_DRAG_AND_DROP_XPATH.replace("{appType}", appType)).click();
		}
	}

	public static String enterAppName(Page page, String appName, String timestamp) {
		String appNameTesting = appName + " " + timestamp;
		page.locator(NAME_TEXTBOX_XPATH).fill(appNameTesting);
		return appNameTesting;
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
}
