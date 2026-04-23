package aicore.pages.guardrail;

import java.util.ArrayList;
import java.util.List;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import aicore.pages.home.MainMenuUtils;
import aicore.pages.model.AddModelFormUtils;
import aicore.utils.AICorePageUtils;
import aicore.utils.CatlogAccessPageUtility;
import aicore.utils.CommonUtils;
import aicore.utils.GuardrailPageUtils;

public class AddGuardrailFormUtils {
	public static List<String> createdGuardrailIds = new ArrayList<>();
	private static final String CATALOG_NAME_FIELD_DATATESTID = "guardrail-form-input-MODEL_NAME";
	private static final String NER_LABELS_FIELD_DATATESTID = "guardrail-form-input-NER_LABELS";
	// Settings
	private static final String DEFAULT_THRESHOLD_FIELD_DATATESTID = "guardrail-form-input-DEFAULT_THRESHOLD";

	public static void enterCatalogName(Page page, String catalogName) {
		page.getByTestId(CATALOG_NAME_FIELD_DATATESTID).fill(catalogName);
	}

	/**
	 * User for Gliner
	 * 
	 * @param page
	 * @param label
	 */
	public static void enterNerLabels(Page page, String label) {
		page.getByTestId(NER_LABELS_FIELD_DATATESTID).fill(label);
		page.getByTestId(NER_LABELS_FIELD_DATATESTID).press("Enter");
	}

	//////////////////////////// Settings
	public static void enterDefaultThreshold(Page page, String threshold) {
		page.getByTestId(DEFAULT_THRESHOLD_FIELD_DATATESTID).fill(threshold);
	}

	public static void createGuardrail(Page page, String guardrailType, String catalogName, String nerLabels, String defaultThreshold) {
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnGuardrail(page);
		GuardrailPageUtils.clickOnAddGuardrailButton(page);
		AddModelFormUtils.selectModel(page, guardrailType);
		enterCatalogName(page, catalogName);
		enterNerLabels(page, nerLabels);
		enterDefaultThreshold(page, defaultThreshold);
		AddModelFormUtils.clickOnCreateModelButton(page);
		CatlogAccessPageUtility.getCatalogAndCopyId(page);
	}

}
