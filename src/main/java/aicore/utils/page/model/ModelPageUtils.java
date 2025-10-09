package aicore.utils.page.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
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
		page.getByTestId(OPEN_AI_KEY_DATA_TESTID).fill(openAIKey);
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
		page.getByTestId(INIT_SCRIPT_DATA_TESTID).fill(initScript);
	}

	public static void enterGCPRegion(Page page, String gcpRegion) {
		page.getByTestId(GCP_REGION_DATA_TESTID).fill(gcpRegion);
	}

	public static void selectTypeForModel(Page page, String type) {
		page.getByTestId(SELECT_TYPE_FOR_MODEL_DATA_TESTID).click();
		page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(type)).click();
	}

	public static void enterEndpoint(Page page, String endpoint) {
		page.getByTestId(ENTER_ENDPOINT_DATA_TESTID).fill(endpoint);
	}

	public static String getAllFieldsInSMSSProperties(Page page, String fieldName) {
		String locator = SMSS_PROPERTIES_FIELDS_COMMON_XPATH.replace("{fieldName}", fieldName);
		return page.textContent(locator).trim();
	}

	public static void enterDeploymentName(Page page, String deploymentName) {
		page.getByTestId(ENTER_DEPLOYMENT_NAME_DATA_TESTID).fill(deploymentName);
	}

	public static void enterTheVersion(Page page, String version) {
		page.getByTestId(ENTER_THE_VERSION_DATA_TESTID).fill(version);
	}

	public static boolean areMandatoryFieldFilled(Page page, List<String> fieldNames) {
		// for (String fieldName : fieldNames){
		// String
		// textLocator=page.getByTestId(MANDATORY_FIELD_TEXT_DATA_TESTID.replace("{fieldName}",
		// fieldName)).textContent().trim();
		// String
		// selectLocator=page.getByTestId(MANDATORY_FIELD_SELECT_DATA_TESTID.replace("{fieldName}",
		// fieldName)).textContent().trim();
		// if (textLocator.isEmpty() && selectLocator.isEmpty()) {
		// return false;
		// }
		// }
		// return true;
		for (String fieldName : fieldNames) {
			String type;
			switch (fieldName) {
			case "NAME":
			case "OPEN_AI_KEY":
			case "VAR_NAME":
			case "INIT_MODEL_ENGINE":
			case "MAX_TOKENS":
			case "MAX_INPUT_TOKENS":
				type = "text";
				break;

			case "MODEL_TYPE":
			case "CHAT_TYPE":
			case "KEEP_CONVERSATION_HISTORY":
			case "KEEP_INPUT_OUTPUT":
				type = "select";
				break;

			default:
				throw new RuntimeException("Unknown field type: " + fieldName);
			}

			// Build locator based on field type
			String testId = "importForm-" + fieldName + "-" + (type.equals("text") ? "textField" : "select");
			Locator locator = page.locator("[data-testid='" + testId + "']");

			// Check if field exists
			if (locator.count() == 0) {
				throw new RuntimeException("Field not found in DOM: " + testId);
			}

			// Get value based on type
			String value = type.equals("text") ? locator.inputValue().trim() : locator.textContent().trim();

			// If value is empty → mandatory field not filled
			if (value.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	public static boolean isSubmitButtonEnabled(Page page) {
		return page.getByTestId(SUBMIT_BUTTON_DATA_TESTID).isEnabled();
	}

}
