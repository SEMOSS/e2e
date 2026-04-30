package aicore.utils.settings;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import aicore.utils.AICorePageUtils;
import io.qameta.allure.Step;

public class PersonalAccessTokenUtils {

	// access key table
	private static final String GENERATED_KEY_XPATH = "//td[contains(text(),'{keyName}')]";
	private static final String GENERATED_DESCRIPTION_XPATH = "//td[text()='{description}']";
	private static final String DELETE_BUTTON_XPATH = "//td[text()='{KeyName}']/following-sibling::td//button[@title='Delete']";

	/////////////////// Methods to create token

	@Step("Click on New Key Button")
	public static void clickNewKeyButton(Page page) {
		Locator locator = page.getByTestId("myProfilePage-new-key-btn");
		AICorePageUtils.waitFor(locator);
		locator.click();
	}

	@Step("Enter key name: {keyName}")
	public static void enterKeyName(Page page, String keyName) {
		Locator locator = page.getByTestId("myProfilePage-generate-key-name-txt");
		AICorePageUtils.waitFor(locator);
		locator.click();
		locator.fill(keyName);
	}

	@Step("Enter key description: {description}")
	public static void enterDescription(Page page, String description) {
		Locator locator = page.getByTestId("myProfilePage-generate-key-description-txt");
		AICorePageUtils.waitFor(locator);
		locator.click();
		locator.fill(description);
	}

	@Step("Click on Generate Button")
	public static void clickGenerateButton(Page page) {
		Locator locator = page.getByTestId("myProfilePage-generate-btn");
		AICorePageUtils.waitFor(locator);
		locator.click();
	}

	/////////////////// methods to read generated token
	@Step("Click on copy Access Key")
	public static String copyAccessKey(Page page) {
		Locator locator = page.getByTestId("myProfilePage-created-access-copy-btn");
		AICorePageUtils.waitFor(locator);
		locator.click();
		return AICorePageUtils.readStringFromClipboard(page).toString().trim();
	}
	
	@Step("Click on copy Secret Key")
	public static String copySecretKey(Page page) {
		Locator locator = page.getByTestId("myProfilePage-secret-key-copy-btn");
		AICorePageUtils.waitFor(locator);
		locator.click();
		return AICorePageUtils.readStringFromClipboard(page).toString().trim();
	}

	@Step("Copy Secret Key")
	public static String getJavasScriptExample(Page page) {
		showJavaScriptExample(page);
		Locator locator = page.getByTestId("myProfilePage-js-sdk-copy-btn");
		AICorePageUtils.waitFor(locator);
		locator.click();
		return AICorePageUtils.readStringFromClipboard(page).toString().trim();
	}

	@Step("Click on show JavaScript toggle example")
	private static void showJavaScriptExample(Page page) {
		Locator locator = page.getByTestId("myProfilePage-js-toggle-btn");
		AICorePageUtils.waitFor(locator);
		locator.click();
	}

	@Step("Copy Python example")
	public static String getPyExample(Page page) {
		showPythonExample(page);
		Locator locator = page.getByTestId("myProfilePage-py-sdk-copy-btn");
		AICorePageUtils.waitFor(locator);
		locator.click();
		return AICorePageUtils.readStringFromClipboard(page).toString().trim();
	}

	@Step("Click on show Python toggle example")
	private static void showPythonExample(Page page) {
		Locator locator = page.getByTestId("myProfilePage-py-toggle-btn");
		AICorePageUtils.waitFor(locator);
		locator.click();
	}

	@Step("Click on close button")
	public static void clickOnCloseBtn(Page page) {
		Locator closeButton = page.locator("button[data-slot='dialog-close']");
		closeButton.click();
	}
	
	////////// ACCESS KEY TABLE VIEW

	@Step("Validate keyName is in the table: {keyName}")
	public static String validateGeneratedKey(Page page, String keyName) {
		String generatedKeyName = page.textContent(GENERATED_KEY_XPATH.replace("{keyName}", keyName))
				.trim();
		return generatedKeyName;
	}

	@Step("Validate description is in the table: {description}")
	public static String validateDescriptionName(Page page, String description) {
		String generatedDescription = page
				.textContent(GENERATED_DESCRIPTION_XPATH.replace("{description}", description)).trim();
		return generatedDescription;
	}
	
	@Step("Click on delete button for key: {keyName}")
	public static void clickOnDeleteIcon(Page page, String keyName) {
		String deleteButtonXpath = DELETE_BUTTON_XPATH.replace("{KeyName}", keyName);
		page.waitForSelector(deleteButtonXpath);
		page.click(deleteButtonXpath);
	}
}
