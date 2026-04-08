package aicore.utils.page.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

import aicore.pages.home.MainMenuUtils;
import aicore.pages.model.AddModelFormUtils;
import aicore.utils.AICorePageUtils;
import aicore.utils.CommonUtils;

public class ModelPageUtils {

	private static final Logger logger = LogManager.getLogger(ModelPageUtils.class);

	private static final String MODEL_TOAST_MESSAGE_TESTID = "//div[text()='{toastMessage}']";

	private static final String VARIABLE_NAME_DATA_TESTID = "importForm-VAR_NAME-textField";
	private static final String MODEL_TOAST_MESSAGE = "Successfully added LLM to catalog";
	private static final String SUBMIT_BUTTON_DATA_TESTID = "importForm-submit-btn";
	private static final String SMSS_TAB_XPATH = "engineLayout-SMSS-tab";

	public static List<String> createdModelIds = new ArrayList<>();
	private static final String SEARCH_CATALOG_DATA_TESTID = "search-bar";
	private static final String CLICK_ON_CATALOG_XPATH = "//div[@data-slot='card']";
	private static final String ACCESS_CONTROL_XPATH = "//button[text()='Access Control']";
	static final String STORAGE_SETTING_XPATH = "//button[text()='Settings']";
	private static final String DELETE_BUTTON_XPATH = "//button[contains(@data-testid,'-delete-btn')]";
	private static final String CONFIRMATION_POPUP_DELETE_BUTTON_XPATH = "//button[contains(@data-testid,'confirmDelete-btn')]";
	private static final String CLICK_ON_CREATED_MODEL_XPATH = "//div[contains(@data-testid,'genericEngineCards')]";
	
	
	public static void clickAddModelButton(Page page) {
		page.getByTestId("engineIndex-add-Model-btn").isVisible();
		page.getByTestId("engineIndex-add-Model-btn").click();
	}
	
	public static void clickOnSMSSTab(Page page) {
		page.getByTestId(SMSS_TAB_XPATH).click();
		page.waitForTimeout(2000);
	}
	
	public static void createModel(Page page, String modelType, String modelName, String catalogName,
			String openAIKey) {
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenModel(page);
		page.getByTestId("engineIndex-add-Model-btn").click();
		AddModelFormUtils.selectModelType(page, modelType);
		AddModelFormUtils.selectModel(page, modelName);
		AddModelFormUtils.enterCatalogName(page, catalogName);
		AddModelFormUtils.enterOpenAIKey(page, openAIKey);
		AddModelFormUtils.clickOnCreateModelButton(page);
		Locator copyId = page.getByTestId("engineHeader-copy-Model-id-btn");
		AICorePageUtils.waitFor(copyId);
		copyId.click();
		String modelId = CommonUtils.readCopiedTextFromClipboard(page);
		createdModelIds.add(modelId);
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




	public static String getExpectedCatalogTitle(String expTitle) {
		return expTitle;
	}

	public static void userClickOnCreatedModel(Page page) {
		page.getByTestId(SEARCH_CATALOG_DATA_TESTID).fill("Model 2");
		page.locator(CLICK_ON_CREATED_MODEL_XPATH).first().click();

	}

	// created multiple model deleted
	public static void deleteCreatedModels(Page page) {
		for (String modelId : createdModelIds) {
			try {
				MainMenuUtils.openMainMenu(page);
				MainMenuUtils.clickOnOpenModel(page);
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

	public static boolean isSubmitButtonEnabled(Page page) {
		return page.getByTestId(SUBMIT_BUTTON_DATA_TESTID).isEnabled();
	}

}
