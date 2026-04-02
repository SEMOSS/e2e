package aicore.pages.model;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;

import aicore.utils.AICorePageUtils;

public class ModelSMSSPageUtils {
	
	// SMSS field
	private static final String NAME_SMSS_PROPERTIES_XPATH = "//div[@class='view-line']//span[@class='mtk1'][starts-with(text(), 'NAME')]";
	private static final String VAR_NAME_SMSS_PROPERTIES_XPATH = "//div[@class='view-line']//span[@class='mtk1'][starts-with(text(), 'VAR_NAME')]";
	private static final String SMSS_PROPERTIES_FIELDS_COMMON_XPATH = "//div[@class='view-line']//span[@class='mtk1'] [starts-with(normalize-space(string(.)), '{fieldName}')]";
	private static final String ENDPOINT_SMSSPROPERTIES_XPATH = "//div[@class='view-line']//span[@class='mtk1'] [starts-with(normalize-space(string(.)), 'ENDPOINT')]/parent::*";
	private static final String INIT_MODEL_ENGINE_SMSSPROPERTIES_XPATH = "//div[@class='view-line']//span[contains(text(),'INIT_MODEL_ENGINE')]";

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

}
