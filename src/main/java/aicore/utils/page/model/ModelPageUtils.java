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

	public static void waitForModelCreationToastMessageDisappear(Page page) {
		page.getByRole(AriaRole.ALERT).filter(new Locator.FilterOptions().setHasText(MODEL_TOAST_MESSAGE)).isVisible();
		page.getByTestId("CloseIcon").click();
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

}
