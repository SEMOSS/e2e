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
	private static final String SELECT_MODEL_XPATH = "//p[text()='{ModelName}']";
	private static final String CATALOG_NAME_DATA_TESTID = "importForm-NAME-textField";
	private static final String OPEN_AI_KEY_DATA_TESTID = "importForm-OPEN_AI_KEY-textField";
	private static final String VARIABLE_NAME_DATA_TESTID = "importForm-VAR_NAME-textField";
	private static final String CREATE_MODEL_BUTTON_XPATH = "//button[@type='submit']";
	private static final String MODEL_TOAST_MESSAGE = "Successfully added LLM to catalog";
	// SMSS field
	private static final String SMSS_TAB_XPATH = "//button[text()='SMSS']";
	private static final String NAME_SMSS_PROPERTIES_XPATH = "//div[@class='view-line']//span[@class='mtk1'][starts-with(text(), 'NAME')]";
	private static final String VAR_NAME_SMSS_PROPERTIES_XPATH = "//div[@class='view-line']//span[@class='mtk1'][starts-with(text(), 'VAR_NAME')]";
	private static final String SMSS_PROPERTIES_FIELDS_COMMON_XPATH = "//div[@class='view-line']//span[@class='mtk1'][starts-with(text(), '{fieldName}')]";

	public static List<String> createdModelIds = new ArrayList<>();
	private static final String SEARCH_CATALOG_LABEL = "Search";
	private static final String CLICK_ON_CATALOG_XPATH = "//div[@role='img' and contains(@class,'MuiCardMedia-root')]";
	private static final String ACCESS_CONTROL_XPATH = "//button[text()='Access Control']";
	static final String STORAGE_SETTING_XPATH = "//button[text()='Settings']";
	private static final String DELETE_BUTTON_XPATH = "//span[text()='Delete']";
	private static final String CONFIRMATION_POPUP_DELETE_BUTTON_XPATH = "//div[contains(@class,'MuiDialog-paperWidthSm')]//div//button[contains(@class,'MuiButton-containedSizeMedium')]";
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
	private static final String ENDPOINT_SMSSPROPERTIES_XPATH = "//div[@class='view-line']//span[@class='mtk1 detected-link']";
	private static final String INIT_MODEL_ENGINE_SMSSPROPERTIES_XPATH = "//div[@class='view-line']//span[contains(text(),'INIT_MODEL_ENGINE')]";
	private static final String AWS_REGION_DATA_TESTID = "importForm-AWS_REGION-textField";
	private static final String AWS_ACCESS_KEY_DATA_TESTID = "importForm-AWS_ACCESS_KEY-textField";
	private static final String AWS_SECRET_KEY_DATA_TESTID = "importForm-AWS_SECRET_KEY-textField";
	private static final String CREATE_MODEL_BUTTON_DATA_TESTID="importForm-submit-btn";

	public static void clickAddModelButton(Page page) {
		page.getByTestId("engineIndex-add-Model-btn").isVisible();
		page.getByTestId("engineIndex-add-Model-btn").click();
	}

	public static void selectModel(Page page, String modelName) {
		page.click(SELECT_MODEL_XPATH.replace("{ModelName}", modelName));
	}

	public static void selectOpenAi(Page page, String aiModelName) {
		page.click(SELECT_OPENAI_XPATH.replace("{OpenAIModelName}", aiModelName));
	}

	public static void enterCatalogName(Page page, String catalogName) {
		page.getByTestId(CATALOG_NAME_DATA_TESTID).fill(catalogName);
	}

	public static void enterOpenAIKey(Page page, String openAIKey) {
		Locator openAIKeyField = page.getByTestId(OPEN_AI_KEY_DATA_TESTID);
		String currentValue = openAIKeyField.inputValue().trim();
		if (currentValue.isEmpty()) {
			openAIKeyField.fill(openAIKey);
		}
	}

	public static void enterVariableName(Page page, String varName) {
		page.getByTestId(VARIABLE_NAME_DATA_TESTID).fill(varName);
	}

	public static void clickOnCreateModelButton(Page page) {
		Locator createButtonLocator = page.locator(CREATE_MODEL_BUTTON_XPATH);
		AICorePageUtils.waitFor(createButtonLocator);
		createButtonLocator.scrollIntoViewIfNeeded();
		createButtonLocator.click();
	}

	public static String modelCreationToastMessage(Page page) {
		Locator alert = page.getByTestId("notification-success-alert");
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

	public static void clickOnSMSSTab(Page page) {
		page.click(SMSS_TAB_XPATH);
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

	public static void createModel(Page page, String modelName, String catalogName, String openAIKey, String varName) {
		HomePageUtils.openMainMenu(page);
		HomePageUtils.clickOnOpenModel(page);
		page.getByTestId("engineIndex-add-Model-btn").click();
		page.click(SELECT_MODEL_XPATH.replace("{ModelName}", modelName));
		page.getByTestId(CATALOG_NAME_DATA_TESTID).fill(catalogName);
		page.getByTestId(OPEN_AI_KEY_DATA_TESTID).fill(openAIKey);
		page.getByTestId(VARIABLE_NAME_DATA_TESTID).fill(varName);
		Locator createButton = page.locator(CREATE_MODEL_BUTTON_XPATH);
		createButton.scrollIntoViewIfNeeded();
		createButton.click();
		Locator copyId = page.getByTestId("ContentCopyOutlinedIcon");
		AICorePageUtils.waitFor(copyId);
		copyId.click();
		String modelId = CommonUtils.readCopiedTextFromClipboard(page);
		createdModelIds.add(modelId);
	}

	public static void userClickOnCreatedModel(Page page) {
		page.getByLabel(SEARCH_CATALOG_LABEL).fill("Model 2");
		page.locator(CLICK_ON_CREATED_MODEL_XPATH).first().click();

	}

	// created multiple model deleted
	public static void deleteCreatedModels(Page page) {
		for (String modelId : createdModelIds) {
			try {
				HomePageUtils.openMainMenu(page);
				HomePageUtils.clickOnOpenModel(page);
				page.getByLabel(SEARCH_CATALOG_LABEL).fill(modelId);
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
		Locator locator;
		switch (fieldName) {
		case "ENDPOINT":
			locator = page.locator(ENDPOINT_SMSSPROPERTIES_XPATH);
			break;
		case "INIT_MODEL_ENGINE":
			// Custom handling for partial text match
			locator = page.locator(INIT_MODEL_ENGINE_SMSSPROPERTIES_XPATH);
			break;
		default:
			locator = page.locator(SMSS_PROPERTIES_FIELDS_COMMON_XPATH.replace("{fieldName}", fieldName));
			break;
		}
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

	public static boolean areMandatoryFieldFilled(Page page, String fieldName) {

		Locator locator;
		// Map field types correctly
		switch (fieldName) {
		case "MODEL_TYPE":
		case "CHAT_TYPE":
		case "KEEP_CONVERSATION_HISTORY":
		case "KEEP_INPUT_OUTPUT":
			locator = page.getByTestId("importForm-" + fieldName + "-select");
			break;
		default:
			locator = page.getByTestId("importForm-" + fieldName + "-textField");
			break;
		}

		try {
			locator.waitFor(new Locator.WaitForOptions().setTimeout(10000));
			String value = "";

			if (locator.isVisible()) {
				try {
					value = locator.inputValue().trim();
				} catch (PlaywrightException e) {
					value = locator.textContent().trim();
				}
			}
			return !value.isEmpty();
		} catch (Exception e) {
			logger.warn("Field not found or empty: " + fieldName);
			return false;
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

}
