package aicore.pages.model;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import aicore.utils.AICorePageUtils;

public class AddModelFormUtils {

	private static final String SELECT_OPENAI_XPATH = "//p[text()='{OpenAIModelName}']";
	private static final String OPTIONS_TAB_DATA_TESTID = "connect-to-{tabName}-tab";

	private static final String SELECT_TYPE_FOR_MODEL_DATA_TESTID = "importForm-MODEL_TYPE-select";
	private static final String MODEL_TYPE_DATATESTID = "//*[@data-testid=\"connect-to-{modelType}-tab\"]";
	private static final String SELECT_MODEL_XPATH = "//p[text()='{ModelName}']";
	private static final String CATALOG_NAME_DATA_TESTID = "model-import-form-input-NAME";
	private static final String OPEN_AI_KEY_DATA_TESTID = "model-import-form-input-OPEN_AI_KEY";
	private static final String CREATE_MODEL_BUTTON_XPATH = "//button[@type='submit']";

	private static final String MAX_TOKENS_DATA_TESTID = "importForm-MAX_TOKENS-textField";
	private static final String MAX_INPUT_TOKENS_DATA_TESTID = "importForm-MAX_INPUT_TOKENS-textField";

	private static final String ENTER_DEPLOYMENT_NAME_DATA_TESTID = "importForm-MODEL-textField";
	private static final String ENTER_THE_VERSION_DATA_TESTID = "importForm-API_VERSION-textField";

	// aws options
	private static final String AWS_REGION_DATA_TESTID = "importForm-AWS_REGION-textField";
	private static final String AWS_ACCESS_KEY_DATA_TESTID = "importForm-AWS_ACCESS_KEY-textField";
	private static final String AWS_SECRET_KEY_DATA_TESTID = "importForm-AWS_SECRET_KEY-textField";

	// settings
	private static final String INIT_SCRIPT_DATA_TESTID = "importForm-INIT_MODEL_ENGINE-textField";
	private static final String GCP_REGION_DATA_TESTID = "importForm-GCP_REGION-textField";
	private static final String ENTER_ENDPOINT_DATA_TESTID = "importForm-ENDPOINT-textField";

	// chat info
	private static final String SELECT_CHAT_TYPE_FOR_MODEL_DATA_TESTID = "importForm-CHAT_TYPE-select";
	private static final String SELECT_KEEP_CONVERSATION_HISTORY_FOR_MODEL_DATA_TESTID = "importForm-KEEP_CONVERSATION_HISTORY-select";
	private static final String SELECT_RECORD_QUESTIONS_AND_RESPONSES_FOR_MODEL_DATA_TESTID = "importForm-KEEP_INPUT_OUTPUT-select";

	// create buttons
	private static final String CREATE_MODEL_BUTTON_DATA_TESTID = "importForm-submit-btn";
	private static final String SELECT_ADD_MODEL_OPTION_DATA_TESTID = "{option}-upload-file-button";

	// fields
	private static final String MANDATORY_FIELD_TEXT_DATA_TESTID = "importForm-{fieldName}-textField";
	private static final String MANDATORY_FIELD_SELECT_DATA_TESTID = "importForm-{fieldName}-select";
	private static final String SELECT_DROPDOWN_VALUE_XPATH = "//div[normalize-space()='{fieldValue}']";
	private static final String CONNECT_BUTTON_DATA_TESTID = "model-importForm-connect-button";
	private static final String BUTTON_FIELD_UNDER_SECTION_XPATH = "//*[text()='{section}']/parent::div/following-sibling::div//div//button[@data-testid='model-import-form-input-{field}']";
	private static final String INPUT_FIELD_UNDER_SECTION_XPATH = "//*[text()='{section}']/parent::div/following-sibling::div//div//input[@data-testid='model-import-form-input-{field}']";
	private static final String MANDATORY_INPUT_FIELDS_XPATH = "//input[@data-testid='model-import-form-input-{field}']/../label//span[text()='*']";
	private static final String MANDATORY_BUTTON_FIELDS_XPATH = "//button[@data-testid='model-import-form-input-{field}']/../label//span[text()='*']";
	private static final String FORM_FIELDS_XPATH = "model-import-form-input-{field}";

	public static void selectOpenAi(Page page, String aiModelName) {
		page.click(SELECT_OPENAI_XPATH.replace("{OpenAIModelName}", aiModelName));
	}

	public static void clickOnGroupTab(Page page, String tabName) {
		String tab = tabName.replace(" ", "-");
		Locator tabLocator = page.getByTestId(OPTIONS_TAB_DATA_TESTID.replace("{tabName}", tab));
		AICorePageUtils.waitFor(tabLocator);
		tabLocator.click();
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

	public static void selectModelType(Page page, String modelType) {
		page.locator(MODEL_TYPE_DATATESTID.replace("{modelType}", modelType)).click();
	}

	public static void selectModel(Page page, String modelName) {
		Locator modelCard = page.locator(SELECT_MODEL_XPATH.replace("{ModelName}", modelName)).first();
		AICorePageUtils.waitFor(modelCard);
		modelCard.click();
	}

	public static void enterCatalogName(Page page, String catalogName) {
		Locator catalogNameField = page.getByTestId(CATALOG_NAME_DATA_TESTID);
		catalogNameField.fill(catalogName);
	}

	public static void enterOpenAIKey(Page page, String openAIKey) {
		Locator openAIKeyField = page.getByTestId(OPEN_AI_KEY_DATA_TESTID);
		openAIKeyField.fill(openAIKey);
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

	private static String[] getFieldInfo(String field) {
		String fieldNameForDataTestId;
		switch (field) {
		case "Catalog Name":
			fieldNameForDataTestId = "Name";
			break;
		case "Max Tokens (Max Completion Tokens)":
			fieldNameForDataTestId = "Max Tokens";
			break;
		case "Record Questions and Responses":
			fieldNameForDataTestId = "Keep Input Output";
			break;
		case "Init Script":
			fieldNameForDataTestId = "Init model engine";
			break;
		case "OpenAI API Key":
		case "Azure Open AI Key":
		case "Perplexity API Key":
			fieldNameForDataTestId = "Open AI Key";
			break;
		case "Model ID":
		case "Model Name":
		case "Model (Deployment Name)":
			fieldNameForDataTestId = "Model";
			break;
		case "Region":
			fieldNameForDataTestId = "Aws Region";
			break;
		case "AWS Secret Access Key":
			fieldNameForDataTestId = "AWS Secret Key";
			break;
		case "Max Completion Tokens":
			fieldNameForDataTestId = "MAX Tokens";
			break;
		case "Completion Type":
			fieldNameForDataTestId = "Chat Type";
			break;
		case "Azure Endpoint":
			fieldNameForDataTestId = "Endpoint";
			break;
		default:
			fieldNameForDataTestId = field;
		}
		String fieldType;
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
		case "Model (Deployment Name)":
		case "Open AI Key":
		case "OPEN AI Key":
		case "OpenAI API Key":
		case "API Key":
		case "AWS Access Key":
		case "AWS Secret Access Key":
		case "Perplexity API Key":
		case "Azure Open AI Key":
		case "Max Input Tokens":
		case "Max Tokens":
		case "Max Completion Tokens":
		case "Context Window":
		case "Max Tokens (Max Completion Tokens)":
		case "Endpoint":
		case "Azure Endpoint":
		case "Provider":
			fieldType = "Input";
			break;
		case "Chat Type":
		case "Record Questions and Responses":
		case "Keep Conversation History":
		case "Deployment Type":
		case "Completion Type":
		case "Type":
			fieldType = "Dropdown";
			break;
		default:
			throw new IllegalArgumentException("Invalid field: " + field);
		}
		return new String[] { fieldNameForDataTestId, fieldType };
	}

	public static boolean fieldUnderSection(Page page, String section, String field) {
		String[] info = getFieldInfo(field);
		String actualField = info[0];
		String fieldType = info[1];
		String fieldName = actualField.replace(" ", "_").toUpperCase();
		Locator fieldLocator;
		if ("Input".equals(fieldType)) {
			fieldLocator = page.locator(
					INPUT_FIELD_UNDER_SECTION_XPATH.replace("{section}", section).replace("{field}", fieldName));
		} else if ("Dropdown".equals(fieldType)) {
			fieldLocator = page.locator(
					BUTTON_FIELD_UNDER_SECTION_XPATH.replace("{section}", section).replace("{field}", fieldName));
		} else {
			throw new IllegalArgumentException("Invalid field type: " + fieldType);
		}
		fieldLocator.scrollIntoViewIfNeeded();
		return fieldLocator.isVisible();
	}

	public static boolean isFieldMandatory(Page page, String field) {
		String[] info = getFieldInfo(field);
		String actualField = info[0];
		String fieldType = info[1];
		String fieldName = actualField.replace(" ", "_").toUpperCase();
		Locator fieldLocator;
		if ("Input".equals(fieldType)) {
			fieldLocator = page.locator(MANDATORY_INPUT_FIELDS_XPATH.replace("{field}", fieldName));
		} else if ("Dropdown".equals(fieldType)) {
			fieldLocator = page.locator(MANDATORY_BUTTON_FIELDS_XPATH.replace("{field}", fieldName));
		} else {
			throw new IllegalArgumentException("Invalid field type: " + fieldType);
		}
		fieldLocator.first().scrollIntoViewIfNeeded();
		return fieldLocator.first().isVisible();
	}

	public static void fillCatalogCreationForm(Page page, String field, String fieldValue, String timestamp) {
		String[] info = getFieldInfo(field);
		String actualField = info[0];
		String fieldType = info[1];
		String fieldName = actualField.replace(" ", "_").toUpperCase();
		Locator fieldLocator = page.getByTestId(FORM_FIELDS_XPATH.replace("{field}", fieldName));
		fieldLocator.scrollIntoViewIfNeeded();
		switch (fieldType) {
		case "Input":
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

	public static void clickOnCreateModelButton(Page page) {
		Locator createButtonLocator = page.locator(CREATE_MODEL_BUTTON_XPATH);
		AICorePageUtils.waitFor(createButtonLocator);
		createButtonLocator.scrollIntoViewIfNeeded();
		createButtonLocator.click();
	}

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

	public static void enterEndpoint(Page page, String endpoint) {
		Locator endpointField = page.getByTestId(ENTER_ENDPOINT_DATA_TESTID);
		String currentValue = endpointField.inputValue().trim();
		if (currentValue.isEmpty()) {
			endpointField.fill(endpoint);
		}
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

}
