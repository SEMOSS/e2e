package aicore.pages.guardrail;

import com.microsoft.playwright.Page;

public class AddGuardrailFormUtils {
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

}
