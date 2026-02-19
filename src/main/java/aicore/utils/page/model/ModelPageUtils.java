package aicore.utils.page.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

import aicore.utils.AICorePageUtils;
import aicore.utils.CommonUtils;
import aicore.utils.HomePageUtils;

public class ModelPageUtils {

	private static final Logger logger = LogManager.getLogger(ModelPageUtils.class);
	private static final String SELECT_OPENAI_XPATH = "//p[text()='{OpenAIModelName}']";
	private static final String OPTIONS_TAB_DATA_TESTID = "connect-to-{tabName}-tab";
	private static final String SELECT_MODEL_XPATH = "//p[text()='{ModelName}']";
	private static final String CATALOG_NAME_DATA_TESTID = "importForm-Catalog-Name-textField";
	private static final String OPEN_AI_KEY_DATA_TESTID = "model-importForm-Open-AI-Key-password";
	private static final String VARIABLE_NAME_DATA_TESTID = "importForm-VAR_NAME-textField";
	private static final String CREATE_MODEL_BUTTON_XPATH = "//button[@type='submit']";
	private static final String MODEL_TOAST_MESSAGE = "Successfully added LLM to catalog";
	private static final String TEXT_FIELDS_UNDER_SECTION_XPATH = "//*[text()='{section}']/parent::div/following-sibling::div//div//input[@data-testid='importForm-{field}-textField']";
	private static final String DROPDOWN_FIELDS_UNDER_SECTION_XPATH = "//*[text()='{section}']/parent::div/following-sibling::div//div//button[@data-testid='model-importForm-{field}-select']";
	private static final String CREDENTIAL_FIELDS_UNDER_SECTION_XPATH = "//*[text()='{section}']/parent::div/following-sibling::div//div//input[@data-testid='model-importForm-{field}-password']";
	private static final String NUMBER_FIELDS_UNDER_SECTION_XPATH = "//*[text()='{section}']/parent::div/following-sibling::div//div//input[@data-testid='model-importForm-{field}']";
	private static final String URL_FIELDS_UNDER_SECTION_XPATH = "//h6[text()='{section}']/parent::div/following-sibling::div//div//input[@data-testid='model-importForm-{field}-url']";
	private static final String MANDATORY_TEXT_FIELDS_XPATH = "//input[@data-testid='importForm-{field}-textField']/../label//span[text()='*']";
	private static final String MANDATORY_DROPDOWN_FIELDS_XPATH = "//button[@data-testid='model-importForm-{field}-select']/../label//span[text()='*']";
	private static final String MANDATORY_CREDENTIAL_FIELDS_XPATH = "//input[@data-testid='model-importForm-{field}-password']/../label//span[text()='*']";
	private static final String MANDATORY_NUMBER_FIELDS_XPATH = "//input[@data-testid='model-importForm-{field}']/../label//span[text()='*']";
	private static final String MANDATORY_URL_FIELDS_XPATH = "//input[@data-testid='model-importForm-{field}-url']/../label//span[text()='*']";
	private static final String TEXT_FIELDS_DATA_TESTID = "importForm-{field}-textField";
	private static final String DROPDOWN_FIELDS_DATA_TESTID = "model-importForm-{field}-select";
	private static final String CREDENTIAL_FIELDS_DATA_TESTID = "model-importForm-{field}-password";
	private static final String NUMBER_FIELDS_DATA_TESTID = "model-importForm-{field}";
	private static final String URL_FIELDS_DATA_TESTID = "model-importForm-{field}-url";
	private static final String SELECT_DROPDOWN_VALUE_XPATH = "//div[normalize-space()='{fieldValue}']";
	private static final String CONNECT_BUTTON_DATA_TESTID = "model-importForm-connect-button";
	private static final String MODEL_TOAST_MESSAGE_TESTID = "//div[text()='{toastMessage}']";
	private static final String MODEL_ID_TESTID = "engineHeader-Model-id";
	private static final String MODEL_NAME_TESTID = "Title";

	// Chat field
	private static final String CHAT_TAB_XPATH = "//button[text()='Chat']";
	private static final String CHAT_TITLE_XPATH = "//div//*[text()='{title}']";
	private static final String CHAT_TEMPERATURE_XPATH = "//div//p[text()='Temperature']/../../span[contains(text(),'Current')]";
	private static final String CHAT_MAX_TOKENS_XPATH = "//div//p[contains(text(),'Max Tokens')]/../../div//input";
	private static final String CHAT_INPUT_XPATH = "//div//textarea[@name='prompt']";
	private static final String CHAT_SEND_BUTTON_XPATH = "//button[@aria-label='Send message']";
	private static final String EMPTY_CHAT_WINDOW_XPATH = "//div//span[contains(text(),'Start Conversation by typing a message')]";
	private static final String CHAT_CLEAR_ALL_BUTTON_XPATH = "//button[text()='Clear Chat']";
	private static final String CHAT_RESPONSE_XPATH = "//div//*[text()='Response']";
	private static final String CHAT_OUTPUT_XPATH = "//div//*[text()='Response']/../div//p[1]";
	private static final String CHAT_MODEL_ID_XPATH = "//div//*[text()='Model Information']//following-sibling::p//*[contains(text(),'Model ID')]/..";
	private static final String CHAT_MODEL_NAME_XPATH = "//div//*[text()='Model Information']//following-sibling::p//*[contains(text(),'Model Name')]/..";

	// SMSS field
	private static final String SMSS_TAB_XPATH = "engineLayout-SMSS-tab";
	private static final String NAME_SMSS_PROPERTIES_XPATH = "//div[@class='view-line']//span[@class='mtk1'][starts-with(text(), 'NAME')]";
	private static final String VAR_NAME_SMSS_PROPERTIES_XPATH = "//div[@class='view-line']//span[@class='mtk1'][starts-with(text(), 'VAR_NAME')]";
	private static final String SMSS_PROPERTIES_FIELDS_COMMON_XPATH = "//div[@class='view-line']//span[@class='mtk1'] [starts-with(normalize-space(string(.)), '{fieldName}')]";

	public static List<String> createdModelIds = new ArrayList<>();
	private static final String SEARCH_CATALOG_DATA_TESTID = "search-bar";
	private static final String CLICK_ON_CATALOG_XPATH = "//div[@data-slot='card']";
	private static final String ACCESS_CONTROL_XPATH = "//button[text()='Access Control']";
	static final String STORAGE_SETTING_XPATH = "//button[text()='Settings']";
	private static final String DELETE_BUTTON_XPATH = "//button[contains(@data-testid,'-delete-btn')]";
	private static final String CONFIRMATION_POPUP_DELETE_BUTTON_XPATH = "//button[contains(@data-testid,'confirmDelete-btn')]";
	private static final String CLICK_ON_CREATED_MODEL_XPATH = "//div[contains(@data-testid,'genericEngineCards')]";
	private static final String INIT_SCRIPT_DATA_TESTID = "importForm-INIT_MODEL_ENGINE-textField";
	private static final String GCP_REGION_DATA_TESTID = "importForm-GCP_REGION-textField";
	private static final String SELECT_TYPE_FOR_MODEL_DATA_TESTID = "importForm-MODEL_TYPE-select";
	private static final String ENTER_ENDPOINT_DATA_TESTID = "importForm-ENDPOINT-textField";
	private static final String ENTER_DEPLOYMENT_NAME_DATA_TESTID = "importForm-MODEL-textField";
	private static final String ENTER_THE_VERSION_DATA_TESTID = "importForm-API_VERSION-textField";
	private static final String MANDATORY_FIELD_TEXT_DATA_TESTID = "importForm-{fieldName}-textField";
	private static final String MANDATORY_FIELD_SELECT_DATA_TESTID = "importForm-{fieldName}-select";
	private static final String SUBMIT_BUTTON_DATA_TESTID = "importForm-submit-btn";
	private static final String SELECT_CHAT_TYPE_FOR_MODEL_DATA_TESTID = "importForm-CHAT_TYPE-select";
	private static final String SELECT_KEEP_CONVERSATION_HISTORY_FOR_MODEL_DATA_TESTID = "importForm-KEEP_CONVERSATION_HISTORY-select";
	private static final String SELECT_RECORD_QUESTIONS_AND_RESPONSES_FOR_MODEL_DATA_TESTID = "importForm-KEEP_INPUT_OUTPUT-select";
	private static final String MAX_TOKENS_DATA_TESTID = "importForm-MAX_TOKENS-textField";
	private static final String MAX_INPUT_TOKENS_DATA_TESTID = "importForm-MAX_INPUT_TOKENS-textField";
	private static final String ENDPOINT_SMSSPROPERTIES_XPATH = "//div[@class='view-line']//span[@class='mtk1'] [starts-with(normalize-space(string(.)), 'ENDPOINT')]/parent::*";
	private static final String INIT_MODEL_ENGINE_SMSSPROPERTIES_XPATH = "//div[@class='view-line']//span[contains(text(),'INIT_MODEL_ENGINE')]";
	private static final String AWS_REGION_DATA_TESTID = "importForm-AWS_REGION-textField";
	private static final String AWS_ACCESS_KEY_DATA_TESTID = "importForm-AWS_ACCESS_KEY-textField";
	private static final String AWS_SECRET_KEY_DATA_TESTID = "importForm-AWS_SECRET_KEY-textField";
	private static final String CREATE_MODEL_BUTTON_DATA_TESTID = "importForm-submit-btn";
	private static final String MODEL_TYPE_DATATESTID = "//*[@data-testid=\"connect-to-{modelType}-tab\"]";
	private static final String SELECT_ADD_MODEL_OPTION_DATA_TESTID = "{option}-upload-file-button";
	private static final String UPLOAD_BUTTON_DATA_TESTID = "{option}-upload-submit-button";

	public static void clickAddModelButton(Page page) {
		page.getByTestId("engineIndex-add-Model-btn").isVisible();
		page.getByTestId("engineIndex-add-Model-btn").click();
	}

	public static void selectModelType(Page page, String modelType) {
		page.locator(MODEL_TYPE_DATATESTID.replace("{modelType}", modelType)).click();
	}

	public static void selectModel(Page page, String modelName) {
		Locator modelCard = page.locator(SELECT_MODEL_XPATH.replace("{ModelName}", modelName)).first();
		AICorePageUtils.waitFor(modelCard);
		modelCard.click();
	}

	public static void selectOpenAi(Page page, String aiModelName) {
		page.click(SELECT_OPENAI_XPATH.replace("{OpenAIModelName}", aiModelName));
	}

	public static void clickOnGroupTab(Page page, String tabName) {
		String tab = tabName.replace(" ", "-");
		Locator tabLocator = page.getByTestId(OPTIONS_TAB_DATA_TESTID.replace("{tabName}", tab));
		AICorePageUtils.waitFor(tabLocator);
		tabLocator.click();
	}

	public static boolean fieldUnderSection(Page page, String section, String field) {
		Locator fieldLocator = null;
		String fieldName = field.replace(" ", "-");
		switch (field) {
		case "Catalog Name":
		case "Model":
		case "Init Script":
		case "Tag":
		case "Project":
		case "GCP Region":
		case "Service Account Credentials":
		case "Model ID":
		case "Region":
		case "Model Name":
		case "API Version":
			fieldLocator = page.locator(
					TEXT_FIELDS_UNDER_SECTION_XPATH.replace("{section}", section).replace("{field}", fieldName));
			break;
		case "Chat Type":
		case "Record Questions and Responses":
		case "Keep Conversation History":
		case "Deployment Type":
		case "Completion Type":
		case "Type":
			fieldLocator = page.locator(
					DROPDOWN_FIELDS_UNDER_SECTION_XPATH.replace("{section}", section).replace("{field}", fieldName));
			break;
		case "Open AI Key":
		case "OPEN AI Key":
		case "OpenAI API Key":
		case "AWS Access Key ID":
		case "AWS Secret Access Key":
		case "Azure Open AI Key":
			fieldLocator = page.locator(
					CREDENTIAL_FIELDS_UNDER_SECTION_XPATH.replace("{section}", section).replace("{field}", fieldName));
			break;
		case "Max Input Tokens":
		case "Max Tokens":
		case "Max Completion Tokens":
		case "Context Window":
		case "Max Tokens (Max Completion Tokens)":
			fieldLocator = page.locator(
					NUMBER_FIELDS_UNDER_SECTION_XPATH.replace("{section}", section).replace("{field}", fieldName));
			break;
		case "Endpoint":
		case "Azure Endpoint":
			Locator urlField = page.locator(
					URL_FIELDS_UNDER_SECTION_XPATH.replace("{section}", section).replace("{field}", fieldName));
			Locator textField = page.locator(
					TEXT_FIELDS_UNDER_SECTION_XPATH.replace("{section}", section).replace("{field}", fieldName));
			if (urlField.count() > 0) {
				fieldLocator = urlField;
			} else {
				fieldLocator = textField;
			}
			break;
		default:
			throw new IllegalArgumentException("Invalid field provided for section fields: " + field);
		}
		fieldLocator.scrollIntoViewIfNeeded();
		return fieldLocator.isVisible();
	}

	public static boolean isFieldMandatory(Page page, String field) {
		Locator fieldLocator = null;
		String fieldName = field.replace(" ", "-");
		switch (field) {
		case "Catalog Name":
		case "Model":
		case "Init Script":
		case "Tag":
		case "Project":
		case "GCP Region":
		case "Service Account Credentials":
		case "Model ID":
		case "Region":
		case "Model Name":
		case "API Version":
			fieldLocator = page.locator(MANDATORY_TEXT_FIELDS_XPATH.replace("{field}", fieldName));
			break;
		case "Chat Type":
		case "Record Questions and Responses":
		case "Keep Conversation History":
		case "Deployment Type":
		case "Completion Type":
		case "Type":
			fieldLocator = page.locator(MANDATORY_DROPDOWN_FIELDS_XPATH.replace("{field}", fieldName));
			break;
		case "Open AI Key":
		case "OPEN AI Key":
		case "OpenAI API Key":
		case "AWS Access Key ID":
		case "AWS Secret Access Key":
		case "Azure Open AI Key":
			fieldLocator = page.locator(MANDATORY_CREDENTIAL_FIELDS_XPATH.replace("{field}", fieldName));
			break;
		case "Max Input Tokens":
		case "Max Tokens":
		case "Max Completion Tokens":
		case "Context Window":
		case "Max Tokens (Max Completion Tokens)":
			fieldLocator = page.locator(MANDATORY_NUMBER_FIELDS_XPATH.replace("{field}", fieldName));
			break;
		case "Endpoint":
		case "Azure Endpoint":
			Locator urlField = page.locator(MANDATORY_URL_FIELDS_XPATH.replace("{field}", fieldName));
			Locator textField = page.locator(MANDATORY_TEXT_FIELDS_XPATH.replace("{field}", fieldName));
			if (urlField.count() > 0) {
				fieldLocator = urlField;
			} else {
				fieldLocator = textField;
			}
			break;
		default:
			throw new IllegalArgumentException("Invalid mandatory field provided: " + field);
		}
		fieldLocator.first().scrollIntoViewIfNeeded();
		return fieldLocator.first().isVisible();
	}

	public static void fillCatalogCreationForm(Page page, String field, String fieldValue, String timestamp) {
		Locator fieldLocator = null;
		String fieldName = field.replace(" ", "-");
		String fieldType = "";
		switch (field) {
		case "Catalog Name":
		case "Model":
		case "Init Script":
		case "Tag":
		case "Project":
		case "GCP Region":
		case "Service Account Credentials":
		case "Model ID":
		case "Region":
		case "Model Name":
		case "API Version":
			fieldLocator = page.getByTestId(TEXT_FIELDS_DATA_TESTID.replace("{field}", fieldName));
			fieldType = "Text";
			break;
		case "Chat Type":
		case "Record Questions and Responses":
		case "Keep Conversation History":
		case "Deployment Type":
		case "Completion Type":
		case "Type":
			fieldLocator = page.getByTestId(DROPDOWN_FIELDS_DATA_TESTID.replace("{field}", fieldName));
			fieldType = "Dropdown";
			break;
		case "Open AI Key":
		case "OPEN AI Key":
		case "OpenAI API Key":
		case "AWS Access Key ID":
		case "AWS Secret Access Key":
		case "Azure Open AI Key":
			fieldLocator = page.getByTestId(CREDENTIAL_FIELDS_DATA_TESTID.replace("{field}", fieldName));
			fieldType = "Credential";
			break;
		case "Max Input Tokens":
		case "Max Tokens":
		case "Max Completion Tokens":
		case "Context Window":
		case "Max Tokens (Max Completion Tokens)":
			fieldLocator = page.getByTestId(NUMBER_FIELDS_DATA_TESTID.replace("{field}", fieldName));
			fieldType = "Number";
			break;
		case "Endpoint":
		case "Azure Endpoint":
			Locator urlField = page.getByTestId(URL_FIELDS_DATA_TESTID.replace("{field}", fieldName));
			Locator textField = page.getByTestId(TEXT_FIELDS_DATA_TESTID.replace("{field}", fieldName));
			if (urlField.count() > 0) {
				fieldLocator = urlField;
			} else {
				fieldLocator = textField;
			}
			fieldType = "Url";
			break;
		default:
			throw new IllegalArgumentException("Invalid field: " + field);
		}
		fieldLocator.scrollIntoViewIfNeeded();
		switch (fieldType) {
		case "Text":
		case "Credential":
		case "Number":
		case "Url":
			if (field.equalsIgnoreCase("Catalog Name")) {
				fieldLocator.fill(fieldValue + timestamp);
			} else {
				fieldLocator.fill(fieldValue);
			}
			break;
		case "Dropdown":
			fieldLocator.click();
			page.locator(SELECT_DROPDOWN_VALUE_XPATH.replace("{fieldValue}", fieldValue)).click();
			break;
		default:
			throw new IllegalArgumentException("Invalid field type");
		}
	}

	public static boolean validateConnectButtonEnabled(Page page) {
		Locator connectButton = page.getByTestId(CONNECT_BUTTON_DATA_TESTID);
		connectButton.scrollIntoViewIfNeeded();
		return connectButton.isEnabled();
	}

	public static void clickOnCreateModelButton(Page page, String buttonName) {
		Locator createButton = page.getByTestId(CONNECT_BUTTON_DATA_TESTID);
		createButton.scrollIntoViewIfNeeded();
		createButton.click();
	}

	public static void enterCatalogName(Page page, String catalogName) {
		Locator catalogNameField = page.getByTestId(CATALOG_NAME_DATA_TESTID);
//		Locator catalogNameInput = catalogNameField.locator("input");
		catalogNameField.fill(catalogName);
	}

	public static void enterOpenAIKey(Page page, String openAIKey) {
		Locator openAIKeyField = page.getByTestId(OPEN_AI_KEY_DATA_TESTID);
//		Locator openAIKeyInput = openAIKeyField.locator("input");
		openAIKeyField.fill(openAIKey);
	}

	public static void clickOnCreateModelButton(Page page) {
		Locator createButtonLocator = page.locator(CREATE_MODEL_BUTTON_XPATH);
		AICorePageUtils.waitFor(createButtonLocator);
		createButtonLocator.scrollIntoViewIfNeeded();
		createButtonLocator.click();
	}

	public static String modelCreationToastMessage(Page page, String toastMessage) {
		Locator alert = page.locator(MODEL_TOAST_MESSAGE_TESTID.replace("{toastMessage}", toastMessage));
		return AICorePageUtils.verifySuccessToastMessage(page, alert);
	}

	public static void closeModelCreationToastMessage(Page page) {
		AICorePageUtils.closeToastMessage(page);
	}

	public static String verifyModelTitle(Page page, String modelTitle) {
		Locator actualmodelTitle = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName(modelTitle));
		actualmodelTitle.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		return actualmodelTitle.textContent().trim();
	}

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

	public static void clickOnSMSSTab(Page page) {
		page.getByTestId(SMSS_TAB_XPATH).click();
		page.waitForTimeout(2000);
	}

	public static String getExpectedCatalogTitle(String expTitle) {
		return expTitle;
	}

	public static String verifyNameInSMSS(Page page) {
		String nameInSMSSProperties = page.textContent(NAME_SMSS_PROPERTIES_XPATH);
		return nameInSMSSProperties;
	}

	public static String verifyVarNameInSMSS(Page page) {
		String varNameInSMSSProperties = page.textContent(VAR_NAME_SMSS_PROPERTIES_XPATH);
		return varNameInSMSSProperties;
	}

	public static String verifyKeepConversationHistoryValueInSMSS(Page page, String fieldName) {
		String varNameInSMSSProperties = page
				.textContent(SMSS_PROPERTIES_FIELDS_COMMON_XPATH.replace("{fieldName}", fieldName));
		return varNameInSMSSProperties;
	}

	public static void createModel(Page page, String modelType, String modelName, String catalogName,
			String openAIKey) {
		HomePageUtils.openMainMenu(page);
		HomePageUtils.clickOnOpenModel(page);
		page.getByTestId("engineIndex-add-Model-btn").click();
		selectModelType(page, modelType);
		selectModel(page, modelName);
		enterCatalogName(page, catalogName);
		enterOpenAIKey(page, openAIKey);
		Locator createButton = page.locator(CREATE_MODEL_BUTTON_XPATH);
		createButton.scrollIntoViewIfNeeded();
		createButton.click();
		Locator copyId = page.getByTestId("engineHeader-copy-Model-id-btn");
		AICorePageUtils.waitFor(copyId);
		copyId.click();
		String modelId = CommonUtils.readCopiedTextFromClipboard(page);
		createdModelIds.add(modelId);
	}

	public static void userClickOnCreatedModel(Page page) {
		page.getByTestId(SEARCH_CATALOG_DATA_TESTID).fill("Model 2");
		page.locator(CLICK_ON_CREATED_MODEL_XPATH).first().click();

	}

	// created multiple model deleted
	public static void deleteCreatedModels(Page page) {
		for (String modelId : createdModelIds) {
			try {
				HomePageUtils.openMainMenu(page);
				HomePageUtils.clickOnOpenModel(page);
				page.getByTestId(SEARCH_CATALOG_DATA_TESTID).fill(modelId);
				page.waitForTimeout(500);
				page.locator(CLICK_ON_CATALOG_XPATH).click();
				page.locator(ACCESS_CONTROL_XPATH).click();
				page.locator(DELETE_BUTTON_XPATH).click();
				page.locator(CONFIRMATION_POPUP_DELETE_BUTTON_XPATH).click();

			} catch (Exception e) {
				logger.warn("Model ID not found or already deleted: " + modelId);
			}
		}
		createdModelIds.clear();
	}

	// Create all Model Types
	public static void enterInitScript(Page page, String initScript) {
		Locator initScriptField = page.getByTestId(INIT_SCRIPT_DATA_TESTID);
		String currentValue = initScriptField.inputValue().trim();
		if (currentValue.isEmpty()) {
			initScriptField.fill(initScript);
		}
	}

	public static void enterGCPRegion(Page page, String gcpRegion) {
		Locator gcpRegionField = page.getByTestId(GCP_REGION_DATA_TESTID);
		String currentValue = gcpRegionField.inputValue().trim();
		if (currentValue.isEmpty()) {
			gcpRegionField.fill(gcpRegion);
		}

	}

	public static void selectTypeForModel(Page page, String type) {
		Locator typeField = page.getByTestId(SELECT_TYPE_FOR_MODEL_DATA_TESTID);
		String currentValue = typeField.textContent().trim();
		if (currentValue.isEmpty()) {
			currentValue = typeField.inputValue().trim();
		}
		if (!currentValue.equalsIgnoreCase(type)) {
			typeField.click();
			page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(type)).click();
		}
	}

	public static void enterEndpoint(Page page, String endpoint) {
		Locator endpointField = page.getByTestId(ENTER_ENDPOINT_DATA_TESTID);
		String currentValue = endpointField.inputValue().trim();
		if (currentValue.isEmpty()) {
			endpointField.fill(endpoint);
		}
	}

	public static String getAllFieldsInSMSSProperties(Page page, String fieldName) {
		Locator locator = null;
		switch (fieldName) {
		case "ENDPOINT":
			locator = page.locator(ENDPOINT_SMSSPROPERTIES_XPATH);
			break;
		case "INIT_MODEL_ENGINE":
			// Custom handling for partial text match
			locator = page.locator(INIT_MODEL_ENGINE_SMSSPROPERTIES_XPATH);
			break;
		case "MODEL":
			Locator allModels = page.locator(SMSS_PROPERTIES_FIELDS_COMMON_XPATH.replace("{fieldName}", fieldName));
			int count = allModels.count();
			for (int i = 0; i < count; i++) {
				String text = allModels.nth(i).textContent().replace('\u00A0', ' ').trim();
				if (text.startsWith("MODEL ") && !text.startsWith("MODEL_")) {
					locator = allModels.nth(i);
					break;
				}
			}
			break;
		default:
			locator = page.locator(SMSS_PROPERTIES_FIELDS_COMMON_XPATH.replace("{fieldName}", fieldName));
			break;
		}
		AICorePageUtils.waitFor(locator);
		locator.scrollIntoViewIfNeeded();
		String value = null;
		try {
			value = locator.innerText().trim();
		} catch (PlaywrightException e) {
			value = locator.textContent().trim();
		}
		return value;
	}

	public static void enterDeploymentName(Page page, String deploymentName) {
		Locator deploymentNameField = page.getByTestId(ENTER_DEPLOYMENT_NAME_DATA_TESTID);
		String currentValue = deploymentNameField.inputValue().trim();
		if (currentValue.isEmpty()) {
			deploymentNameField.fill(deploymentName);
		}
	}

	public static void enterTheVersion(Page page, String version) {
		Locator versionField = page.getByTestId(ENTER_THE_VERSION_DATA_TESTID);
		String currentValue = versionField.inputValue().trim();
		if (currentValue.isEmpty()) {
			versionField.fill(version);
		}
	}

	public static boolean isSubmitButtonEnabled(Page page) {
		return page.getByTestId(SUBMIT_BUTTON_DATA_TESTID).isEnabled();
	}

	public static void selectOption(Page page, String option) {
		Locator typeField = page.getByTestId(SELECT_TYPE_FOR_MODEL_DATA_TESTID);
		String currentValue = typeField.textContent().trim();
		if (currentValue.isEmpty()) {
			currentValue = typeField.inputValue().trim();
		}
		if (!currentValue.equalsIgnoreCase(option)) {
			typeField.click();
			page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(option)).click();
		}
	}

	public static void selectChatOption(Page page, String option) {
		Locator typeField = page.getByTestId(SELECT_CHAT_TYPE_FOR_MODEL_DATA_TESTID);
		String currentValue = typeField.textContent().trim();
		if (currentValue.isEmpty()) {
			currentValue = typeField.inputValue().trim();
		}
		if (!currentValue.equalsIgnoreCase(option)) {
			typeField.click();
			page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(option)).click();
		}
	}

	public static void selectKeepConversationHistoryOption(Page page, String option) {
		Locator typeField = page.getByTestId(SELECT_KEEP_CONVERSATION_HISTORY_FOR_MODEL_DATA_TESTID);
		String currentValue = typeField.textContent().trim();
		if (currentValue.isEmpty()) {
			currentValue = typeField.inputValue().trim();
		}
		if (!currentValue.equalsIgnoreCase(option)) {
			typeField.click();
			page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(option)).click();
		}
	}

	public static void selectRecordQuestionsAndResponsesOption(Page page, String option) {
		Locator typeField = page.getByTestId(SELECT_RECORD_QUESTIONS_AND_RESPONSES_FOR_MODEL_DATA_TESTID);
		String currentValue = typeField.textContent().trim();
		if (currentValue.isEmpty()) {
			currentValue = typeField.inputValue().trim();
		}
		if (!currentValue.equalsIgnoreCase(option)) {
			typeField.click();
			page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(option)).click();
		}
	}

	public static void enterMaxTokens(Page page, String maxTokens) {
		Locator maxTokensField = page.getByTestId(MAX_TOKENS_DATA_TESTID);
		String currentValue = maxTokensField.inputValue().trim();
		if (currentValue.isEmpty()) {
			maxTokensField.fill(maxTokens);
		}
	}

	public static void enterMaxInputTokens(Page page, String maxInputTokens) {
		Locator maxInputTokensField = page.getByTestId(MAX_INPUT_TOKENS_DATA_TESTID);
		String currentValue = maxInputTokensField.inputValue().trim();
		if (currentValue.isEmpty()) {
			maxInputTokensField.fill(maxInputTokens);
		}
	}

	public static void selectTypeForModel(Page page) {
		Locator typeField = page.getByTestId(SELECT_TYPE_FOR_MODEL_DATA_TESTID);
		String currentValue = typeField.textContent().trim();
		if (currentValue.isEmpty()) {
			currentValue = typeField.inputValue().trim();
		}
		if (currentValue.equalsIgnoreCase("Open AI")) {
			typeField.click();
			page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName("Open AI")).click();
		}
	}

	public static void enterModelName(Page page, String modelName) {
		Locator modelNameField = page.getByTestId(MANDATORY_FIELD_TEXT_DATA_TESTID.replace("{fieldName}", "MODEL"));
		String currentValue = modelNameField.inputValue().trim();
		if (currentValue.isEmpty()) {
			modelNameField.fill(modelName);
		}
	}

	public static void selectModelOption(Page page, String model) {
		Locator modelField = page.getByTestId(MANDATORY_FIELD_SELECT_DATA_TESTID.replace("{fieldName}", "MODEL"));
		String currentValue = modelField.textContent().trim();
		if (currentValue.isEmpty()) {
			currentValue = modelField.inputValue().trim();
		}
		if (!currentValue.equalsIgnoreCase(model)) {
			modelField.click();
			page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(model)).click();
		}
	}

	public static void enterAWSRegion(Page page, String awsRegion) {
		Locator awsRegionField = page.getByTestId(AWS_REGION_DATA_TESTID);
		String currentValue = awsRegionField.inputValue().trim();
		if (currentValue.isEmpty()) {
			awsRegionField.fill(awsRegion);
		}
	}

	public static void enterAWSAccessKey(Page page, String awsAccessKey) {
		Locator awsAccessKeyField = page.getByTestId(AWS_ACCESS_KEY_DATA_TESTID);
		String currentValue = awsAccessKeyField.inputValue().trim();
		if (currentValue.isEmpty()) {
			awsAccessKeyField.fill(awsAccessKey);
		}
	}

	public static void enterAWSSecretKey(Page page, String awsSecretKey) {
		Locator awsSecretKeyField = page.getByTestId(AWS_SECRET_KEY_DATA_TESTID);
		String currentValue = awsSecretKeyField.inputValue().trim();
		if (currentValue.isEmpty()) {
			awsSecretKeyField.fill(awsSecretKey);
		}
	}

	public static void clickOnCreateButton(Page page, String buttonName) {
		Locator createButtonLocator = page.getByTestId(CREATE_MODEL_BUTTON_DATA_TESTID);
		AICorePageUtils.waitFor(createButtonLocator);
		createButtonLocator.scrollIntoViewIfNeeded();
		createButtonLocator.click();
	}

	public static void selectAddModelOption(Page page, String option) {
		Locator addModelOption = page.getByTestId(SELECT_ADD_MODEL_OPTION_DATA_TESTID.replace("{option}", option));
		AICorePageUtils.waitFor(addModelOption);
		addModelOption.click();
	}

	public static void clickOnUploadButton(Page page, String buttonName) {
		Locator uploadButton = page.locator("//button[text()='Upload']");// page.getByTestId(UPLOAD_BUTTON_DATA_TESTID.replace("{option}",
																			// buttonName));
		uploadButton.isEnabled();
		uploadButton.scrollIntoViewIfNeeded();
		uploadButton.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		uploadButton.click(new Locator.ClickOptions().setForce(true));
		Locator loadingSpinner = page.locator("//span[@role='progressbar']").first();
		if (loadingSpinner.isVisible()) {
			loadingSpinner
					.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN).setTimeout(120000));
		}
	}
}
