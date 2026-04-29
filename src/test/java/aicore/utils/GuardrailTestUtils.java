package aicore.utils;

import com.microsoft.playwright.Page;

import aicore.pages.guardrail.AddGuardrailFormUtils;

public class GuardrailTestUtils {

	public static String createGlinerGuardrail(Page page, String catalogName) {
		String modelNameType = "Gliner";
		String nerLabel = "label";
		String threshold = "1";
		return AddGuardrailFormUtils.createGuardrail(page, modelNameType, catalogName, nerLabel, threshold);
	}
}
