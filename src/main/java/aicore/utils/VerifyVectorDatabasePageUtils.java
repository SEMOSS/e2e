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
	private static final String ADVANCED_SECTION_XPATH = "(//button[@data-testid='vector-form-advanced-toggle']//*)[1]";
    private static final String SECTION_FIELD_XPATH = "../following-sibling::div//label[text()='{fieldName}']";
    private static final String MANDATORY_FIELD_XPATH = "//div//label[text()='{fieldName}']//span";

    public static void selectVectorDatabaseFromConnectionTypes(Page page, String dbType) {
        String dbTypeFormatted = dbType.replace(" ", "-");
		Locator option = page.getByTestId(VECTOR_CONNECTIONS_TESTID.replace("{vectorDB}", dbTypeFormatted));
		if (!option.isVisible()) {
			throw new AssertionError("Vector Database connection type '" + dbType + "' is not visible.");
		}
		option.click();
	}

    public static boolean verifyFieldUnderSection(Page page, String sectionName, String fieldName) {
		Locator sectionLocator = page.locator(FORM_SECTION_XPATH.replace("{sectionName}", sectionName));
		if(sectionName.toLowerCase().equals("advanced settings")) {
			if(fieldName.toLowerCase().equals("not available")) {
				return true;
			}
			Locator dropdownLocator = page.locator(ADVANCED_SECTION_XPATH);
	        AICorePageUtils.waitFor(dropdownLocator);
	        String attributeVal = dropdownLocator.getAttribute("data-testid");
			dropdownLocator.scrollIntoViewIfNeeded();
			if (!sectionLocator.isVisible()) {
				throw new AssertionError("Advanced Settings dropdown is not visible.");
			}
			if(attributeVal.equals("ExpandMoreIcon")) {

				dropdownLocator.click();
			}

		}

		if (!sectionLocator.isVisible()){
			throw new AssertionError("Section '" + sectionName + "' is not visible.");
		}
		Locator fieldLocator = sectionLocator.locator(SECTION_FIELD_XPATH.replace("{fieldName}", fieldName));
		return fieldLocator.isVisible();

	}
    
    public static boolean isVectorDatabaseFieldMandatory(Page page, String fieldName) {
		Locator mandatoryField = page.locator(MANDATORY_FIELD_XPATH.replace("{fieldName}", fieldName));
		if (!mandatoryField.textContent().contains("*")) {
			throw new AssertionError("Database connection type '" + fieldName + "' is not showing with * symbol of required field.");
		}
		return mandatoryField.isVisible();
	}
}
