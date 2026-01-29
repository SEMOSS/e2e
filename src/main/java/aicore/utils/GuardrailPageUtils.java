package aicore.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class GuardrailPageUtils {
	private static final String ADD_GUARDRAIL_BUTTON_DATA_TESTID = "engineIndex-add-Guardrail-btn";
	private static final String CATALOG_NAME_FIELD_XPATH = "//div[@data-testid='guardrail-form-input-MODEL_NAME']//input";
	private static final String NER_LABELS_FIELD_XPATH = "//div[@data-testid='guardrail-form-input-NER_LABELS']//input";
	private static final String DEFAULT_THRESHOLD_FIELD_XPATH = "//div[@data-testid='guardrail-form-input-DEFAULT_THRESHOLD']//input";

	public static void clickOnAddGuardrailButton(Page page) {
		page.getByTestId(ADD_GUARDRAIL_BUTTON_DATA_TESTID).click();
	}

	public static void enterCatalogName(Page page, String catalogName) {
		page.locator(CATALOG_NAME_FIELD_XPATH).fill(catalogName);
	}

	public static void enterNerLabels(Page page, String label) {
		page.locator(NER_LABELS_FIELD_XPATH).fill(label);
		page.locator(NER_LABELS_FIELD_XPATH).press("Enter");
	}

	public static void enterDefaultThreshold(Page page, String threshold) {
		page.locator(DEFAULT_THRESHOLD_FIELD_XPATH).fill(threshold);
	}

	public static String verifyGuardrailTitle(Page page, String guardrailTitle) {
		Locator actualGuardrailTitle = page.getByRole(AriaRole.HEADING,
				new Page.GetByRoleOptions().setName(guardrailTitle));
		AICorePageUtils.waitFor(actualGuardrailTitle);
		return actualGuardrailTitle.textContent().trim();
	}
}
