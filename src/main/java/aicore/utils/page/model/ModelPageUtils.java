package aicore.utils.page.model;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

import aicore.utils.HomePageUtils;

public class ModelPageUtils {

	private static final String SELECT_OPENAI_XPATH = "//p[text()='{OpenAIModelName}']";
	private static final String SELECT_MODEL_XPATH = "//p[text()='{ModelName}']";
	private static final String CATALOG_NAME_DATA_TESTID = "importForm-textField-NAME";
	private static final String OPEN_AI_KEY_DATA_TESTID = "importForm-textField-OPEN_AI_KEY";
	private static final String VARIABLE_NAME_DATA_TESTID = "importForm-textField-VAR_NAME";
	private static final String CREATE_MODEL_BUTTON_XPATH = "//button[@type='submit']";
	private static final String MODEL_TOAST_MESSAGE = "Successfully added LLM to catalog";
	// SMSS field
	private static final String SMSS_TAB_XPATH = "//button[text()='SMSS']";
	private static final String NAME_SMSS_PROPERTIES_XPATH = "//div[@class='view-line']//span[@class='mtk1'][starts-with(text(), 'NAME')]";
	private static final String VAR_NAME_SMSS_PROPERTIES_XPATH = "//div[@class='view-line']//span[@class='mtk1'][starts-with(text(), 'VAR_NAME')]";
	private static final String SMSS_PROPERTIES_FIELDS_COMMON_XPATH = "//div[@class='view-line']//span[@class='mtk1'][starts-with(text(), '{fieldName}')]";

	public static void clickAddModelButton(Page page) {
		page.getByTestId("engine-catalog-add-btn").isVisible();
		page.getByTestId("engine-catalog-add-btn").click();
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
		page.click(CREATE_MODEL_BUTTON_XPATH);
	}

	public static String modelCreationToastMessage(Page page) {
		Locator toastMessage = page.getByRole(AriaRole.ALERT)
				.filter(new Locator.FilterOptions().setHasText(MODEL_TOAST_MESSAGE));
		return toastMessage.textContent().trim();
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
		page.getByTestId("engine-catalog-add-btn").click();
		page.click(SELECT_MODEL_XPATH.replace("{ModelName}", modelName));
		page.getByTestId(CATALOG_NAME_DATA_TESTID).fill(catalogName);
		page.getByTestId(OPEN_AI_KEY_DATA_TESTID).fill(openAIKey);
		page.getByTestId(VARIABLE_NAME_DATA_TESTID).fill(varName);
		Locator createButton = page.locator(CREATE_MODEL_BUTTON_XPATH);
		createButton.scrollIntoViewIfNeeded();
		createButton.click();
	}

}
