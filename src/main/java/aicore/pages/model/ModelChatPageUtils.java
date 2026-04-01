package aicore.pages.model;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;

import aicore.utils.AICorePageUtils;

public class ModelChatPageUtils {
	private static final String MODEL_ID_TESTID = "engineHeader-Model-id";
	private static final String MODEL_NAME_TESTID = "Title";
	
	// Chat field
	private static final String CHAT_TAB_XPATH = "//button[text()='Chat']";
	private static final String CHAT_TITLE_XPATH = "//div//*[text()='{title}']";
	private static final String CHAT_TEMPERATURE_XPATH = "//span[text()='Temperature']/parent::div/following-sibling::p[contains(text(),'Current')]";
	private static final String CHAT_MAX_TOKENS_XPATH = "//div//span[contains(text(),'Max Tokens')]/parent::div/parent::div//input";
	private static final String CHAT_INPUT_XPATH = "//div//textarea[@name='prompt']";
	private static final String CHAT_SEND_BUTTON_XPATH = "//button[@aria-label='Send message']";
	private static final String EMPTY_CHAT_WINDOW_XPATH = "//div//span[contains(text(),'Ask a question to start a conversation')]";
	private static final String CHAT_CLEAR_ALL_BUTTON_XPATH = "//button[text()='Clear Chat']";
	private static final String CHAT_RESPONSE_XPATH = "//div[@class='flex items-end gap-2 px-4 py-2']";
	private static final String CHAT_OUTPUT_XPATH = "//div[@class='prose prose-slate dark:prose-invert max-w-none']";
	private static final String CHAT_MODEL_ID_XPATH = "//div//*[text()='Model Information']//following-sibling::p//*[contains(text(),'Model ID')]/..";
	private static final String CHAT_MODEL_NAME_XPATH = "//div//*[text()='Model Information']//following-sibling::p//*[contains(text(),'Model Name')]/..";

	
	public static void clickOnChatTab(Page page) {
		Locator chatTab = page.locator(CHAT_TAB_XPATH);
		AICorePageUtils.waitFor(chatTab);
		chatTab.click();
		page.waitForTimeout(2000);
	}

	public static void verifyChatSectionDisplayed(Page page, String title) {
		Locator chatTabTitle = page.locator(CHAT_TITLE_XPATH.replace("{title}", title));
		AICorePageUtils.waitFor(chatTabTitle);
		if (!chatTabTitle.isVisible()) {
			throw new RuntimeException("Chat tab title is not displayed");
		}
	}

	public static void verifyTemperatureValue(Page page, String temperatureValue) {
		Locator temperatureField = page.locator(CHAT_TEMPERATURE_XPATH);
		AICorePageUtils.waitFor(temperatureField);
		String actualTemperatureValue = temperatureField.textContent().trim().split(" ")[1];
		if (!actualTemperatureValue.equalsIgnoreCase(temperatureValue)) {
			throw new PlaywrightException("Temperature value is not matching. Expected: " + temperatureValue
					+ " but found: " + actualTemperatureValue);
		}
	}

	public static void verifyMaxTokensValue(Page page, String maxTokensValue) {
		Locator maxTokensField = page.locator(CHAT_MAX_TOKENS_XPATH);
		AICorePageUtils.waitFor(maxTokensField);
		String actualMaxTokensValue = maxTokensField.getAttribute("value");
		if (!actualMaxTokensValue.equalsIgnoreCase(maxTokensValue)) {
			throw new PlaywrightException("Max tokens value is not matching. Expected: " + maxTokensValue
					+ " but found: " + actualMaxTokensValue);
		}
	}

	public static void verifyInputTextboxPlaceholder(Page page, String defaultValue) {
		Locator inputField = page.locator(CHAT_INPUT_XPATH);
		AICorePageUtils.waitFor(inputField);
		String placeholderInputValue = inputField.getAttribute("placeholder").trim();
		if (!placeholderInputValue.equalsIgnoreCase(defaultValue)) {
			throw new PlaywrightException("Input textbox placeholder is not matching. Expected: " + defaultValue
					+ " but found: " + placeholderInputValue);
		}
	}

	public static void verifyAndActivateSendButton(Page page, String inputText) {
		Locator inputField = page.locator(CHAT_INPUT_XPATH);
		Locator sendButton = page.locator(CHAT_SEND_BUTTON_XPATH);
		AICorePageUtils.waitFor(inputField);
		AICorePageUtils.waitFor(sendButton);
		inputField.fill(inputText);
		if (!sendButton.isEnabled()) {
			throw new PlaywrightException("Send button is not enabled");
		}
	}

	public static void clickOnSendButton(Page page) {
		Locator sendButton = page.locator(CHAT_SEND_BUTTON_XPATH);
		AICorePageUtils.waitFor(sendButton);
		if (!sendButton.isEnabled()) {
			throw new PlaywrightException("Send button is not enabled");
		} else {
			sendButton.click();
		}
	}

	public static void clickOnClearAllButton(Page page) {
		Locator clearAllButton = page.locator(CHAT_CLEAR_ALL_BUTTON_XPATH);
		AICorePageUtils.waitFor(clearAllButton);
		if (!clearAllButton.isEnabled()) {
			throw new PlaywrightException("Clear all button is not enabled");
		} else {
			clearAllButton.click();
		}
	}

	public static void verifyChatWindowCleared(Page page) {
		Locator emptyChatWindow = page.locator(EMPTY_CHAT_WINDOW_XPATH);
		AICorePageUtils.waitFor(emptyChatWindow);
		if (!emptyChatWindow.isVisible()) {
			throw new PlaywrightException("Chat window is not cleared after clicking on Clear all button");
		}
	}

	public static void verifyLoaderDisplayed(Page page) {
		page.waitForTimeout(2000); // wait for response to be generated
		Locator response = page.locator(CHAT_RESPONSE_XPATH);
		AICorePageUtils.waitFor(response);
		if (!response.isVisible()) {
			throw new PlaywrightException("Response is not generated after submitting the query");
		}
	}

	public static void verifyResponseGeneratedInChatWindow(Page page) {
		Locator Output = page.locator(CHAT_OUTPUT_XPATH);
		AICorePageUtils.waitFor(Output);
		String outputText = Output.textContent().trim();
		if (!(outputText.length() > 0)) {
			throw new PlaywrightException("Output is not generated after submitting the query");
		}
	}
	
	public static void verifyModelIDAndNameDisplayed(Page page) {
		Locator modelID = page.getByTestId(MODEL_ID_TESTID);
		Locator modelName = page.getByTestId(MODEL_NAME_TESTID);
		AICorePageUtils.waitFor(modelID);
		AICorePageUtils.waitFor(modelName);
		String modelIDText = modelID.textContent().trim();
		String modelNameText = modelName.textContent().trim();
		Locator chatModelID = page.locator(CHAT_MODEL_ID_XPATH);
		Locator chatModelName = page.locator(CHAT_MODEL_NAME_XPATH);
		AICorePageUtils.waitFor(chatModelID);
		AICorePageUtils.waitFor(chatModelName);
		String chatModelIDText = chatModelID.textContent().trim().split(" ")[2];
		String chatModelNameText = chatModelName.textContent().trim().split(" ")[2];
		if (!modelIDText.equalsIgnoreCase(chatModelIDText)) {
			throw new PlaywrightException("Model ID in Chat section does not match with Model Information section"
					+ modelIDText + " " + chatModelIDText);
		}
		if (!modelNameText.equalsIgnoreCase(chatModelNameText)) {
			throw new PlaywrightException("Model Name in Chat section does not match with Model Information section"
					+ modelNameText + " " + chatModelNameText);
		}

	}

}
