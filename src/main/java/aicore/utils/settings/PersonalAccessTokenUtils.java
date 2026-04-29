package aicore.utils.settings;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import aicore.utils.AICorePageUtils;

public class PersonalAccessTokenUtils {

	// access key table
	private static final String GENERATED_KEY_XPATH = "//td[contains(text(),'{keyName}')]";
	private static final String GENERATED_DESCRIPTION_XPATH = "//td[text()='{description}']";
	private static final String DELETE_BUTTON_XPATH = "//td[text()='{KeyName}']/following-sibling::td//button[@title='Delete']";

	/////////////////// Methods to create token

	public static void clickNewKeyButton(Page page) {
		Locator locator = page.getByTestId("myProfilePage-new-key-btn");
		AICorePageUtils.waitFor(locator);
		locator.click();
	}

	public static void enterKeyName(Page page, String keyName) {
		Locator locator = page.getByTestId("myProfilePage-generate-key-name-txt");
		AICorePageUtils.waitFor(locator);
		locator.click();
		locator.fill(keyName);
	}

	public static void enterDescription(Page page, String description) {
		Locator locator = page.getByTestId("myProfilePage-generate-key-description-txt");
		AICorePageUtils.waitFor(locator);
		locator.click();
		locator.fill(description);
	}

	public static void clickGenerateButton(Page page) {
		Locator locator = page.getByTestId("myProfilePage-generate-btn");
		AICorePageUtils.waitFor(locator);
		locator.click();
	}

	/////////////////// methods to read generated token

	public static String copyAccessKey(Page page) {
		Locator locator = page.getByTestId("myProfilePage-created-access-copy-btn");
		AICorePageUtils.waitFor(locator);
		locator.click();
		return AICorePageUtils.readStringFromClipboard(page).toString().trim();
	}

	public static String copySecretKey(Page page) {
		Locator locator = page.getByTestId("myProfilePage-secret-key-copy-btn");
		AICorePageUtils.waitFor(locator);
		locator.click();
		return AICorePageUtils.readStringFromClipboard(page).toString().trim();
	}

	public static String getJavasScriptExample(Page page) {
		showJavaScriptExample(page);
		Locator locator = page.getByTestId("myProfilePage-js-sdk-copy-btn");
		AICorePageUtils.waitFor(locator);
		locator.click();
		return AICorePageUtils.readStringFromClipboard(page).toString().trim();
	}

	private static void showJavaScriptExample(Page page) {
		Locator locator = page.getByTestId("myProfilePage-js-toggle-btn");
		AICorePageUtils.waitFor(locator);
		locator.click();
	}

	public static String getPyExample(Page page) {
		showPythonExample(page);
		Locator locator = page.getByTestId("myProfilePage-py-sdk-copy-btn");
		AICorePageUtils.waitFor(locator);
		locator.click();
		return AICorePageUtils.readStringFromClipboard(page).toString().trim();
	}

	private static void showPythonExample(Page page) {
		Locator locator = page.getByTestId("myProfilePage-py-toggle-btn");
		AICorePageUtils.waitFor(locator);
		locator.click();
	}

	public static void clickOnCloseBtn(Page page) {
		// TODO need data test id on FE
//		Locator closeButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Close"));
//		closeButton.click();
		
		Locator closeButton = page.locator("button[data-slot='dialog-close']");
		closeButton.click();
	}
	
	////////// ACCESS KEY TABLE VIEW

	public static String validateGeneratedKey(Page page, String keyName) {
		String generatedKeyName = page.textContent(GENERATED_KEY_XPATH.replace("{keyName}", keyName))
				.trim();
		return generatedKeyName;
	}

	public static String validateDescriptionName(Page page, String description) {
		String generatedDescription = page
				.textContent(GENERATED_DESCRIPTION_XPATH.replace("{description}", description)).trim();
		return generatedDescription;
	}
	
	public static void clickOnDeleteIcon(Page page, String keyName) {
		String deleteButtonXpath = DELETE_BUTTON_XPATH.replace("{KeyName}", keyName);
		page.waitForSelector(deleteButtonXpath);
		page.click(deleteButtonXpath);
	}
}
