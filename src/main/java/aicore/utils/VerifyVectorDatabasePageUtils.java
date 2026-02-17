package aicore.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class VerifyVectorDatabasePageUtils {

	private Page page;

	public VerifyVectorDatabasePageUtils(Page page) {
		this.page = page;
	}

	private static final String VECTOR_CONNECTIONS_TESTID = "importPageContent-connect-to-{vectorDB}-img";
	private static final String FORM_SECTION_XPATH = "//h6[text()='{sectionName}']";
	private static final String ADVANCED_SECTION_XPATH = "//button[@data-testid='vector-form-advanced-toggle']";
	private static final String SECTION_FIELD_XPATH = "//div[//h4[normalize-space()='{sectionName}']]/following-sibling::div//div//label[text()='{fieldName}']";
	private static final String MANDATORY_FIELD_XPATH = "//label[text()='{fieldName}']//span[text()='*']";

	public static void selectVectorDatabaseFromConnectionTypes(Page page, String dbType) {
		String dbTypeFormatted = dbType.replace(" ", "-");
		Locator option = page.getByTestId(VECTOR_CONNECTIONS_TESTID.replace("{vectorDB}", dbTypeFormatted));
		if (!option.isVisible()) {
			throw new Error("Vector Database connection type '" + dbType + "' is not visible.");
		}
		option.click();
	}

	public static boolean verifyFieldUnderSection(Page page, String sectionName, String fieldName) {
		Locator sectionLocator = page.locator(FORM_SECTION_XPATH.replace("{sectionName}", sectionName));
		if (sectionName.equalsIgnoreCase("advanced settings")) {
			if (fieldName.equalsIgnoreCase("not available")) {
				return true;
			}
			Locator dropdownLocator = page.locator("//button[@data-testid='vector-form-advanced-toggle']");
			AICorePageUtils.waitFor(dropdownLocator);
			dropdownLocator.scrollIntoViewIfNeeded();
			if (!dropdownLocator.isVisible()) {
				throw new Error("Advanced Settings dropdown is not visible.");
			}
			String iconState = dropdownLocator.getAttribute("data-state");
			if ("closed".equals(iconState)) {
				dropdownLocator.click();
			}
		}
		Locator fieldLocator = page
				.locator(SECTION_FIELD_XPATH.replace("{sectionName}", sectionName).replace("{fieldName}", fieldName));

		fieldLocator.scrollIntoViewIfNeeded();
		return fieldLocator.isVisible();
	}

	public static boolean isVectorDatabaseFieldMandatory(Page page, String fieldName) {
		Locator mandatoryField = page.locator(MANDATORY_FIELD_XPATH.replace("{fieldName}", fieldName));
		if (!mandatoryField.textContent().contains("*")) {
			throw new Error(
					"Database connection type '" + fieldName + "' is not showing with * symbol of required field.");
		}
		return mandatoryField.isVisible();
	}
}
