package aicore.pages;

import com.microsoft.playwright.Page;

public class ViewFunctionPage {

	private Page page;

	private static final String FUNCTION_NAME_XPATH = "//h4[text()='{FunctionName}']";
	private static final String FUNCTION_ID_XPATH = "//button[@aria-label=\"copy Function ID\"]/parent::span";
	private static final String FUNCTION_DESCRIPTION_XPATH = "//h6[text()='{FunctionDescription}']";
	private static final String DATE_LAST_UPDATED_XPATH = "//p[text()='{DateLastUpdated}']";
	private static final String MARKUP_FUNCTION_XPATH = "//div[text()='{MarkupFunction}']";
	private static final String CHANGE_ACCESS_BUTTON_XPATH = "//span[text()='{ChangeAccessButton}']";
	private static final String USAGE_TAB_XPATH = "//button[text()='{UsageTab}']";
	private static final String USAGE_INSTRUCTIONS_SECTION_XPATH = "//h6[text()='{UsageInstructionsSection}']";

	public ViewFunctionPage(Page page) {
		this.page = page;
	}

	public void verifyFunctionName(String functionName) {
		page.getByText(FUNCTION_NAME_XPATH.replace("{FunctionName}", functionName)).isVisible();
	}

	public void verifyFunctionID() {
		page.getByText(FUNCTION_ID_XPATH).isVisible();
	}

	public void verifyFunctionDescription(String functionDescription) {
		page.getByText(FUNCTION_DESCRIPTION_XPATH.replace("{FunctionDescription}", functionDescription)).isVisible();
	}

	public void verifyDateLastUpdated(String dateLastUpdated) {
		page.getByText(DATE_LAST_UPDATED_XPATH.replace("{DateLastUpdated}", dateLastUpdated)).isVisible();
	}

	public void verifyMarkupFunction(String markupFunction) {
		page.getByText(MARKUP_FUNCTION_XPATH.replace("{MarkupFunction}", markupFunction)).isVisible();
	}

	public void verifyChangeAccessButton(String changeAccessButton) {
		page.getByText(CHANGE_ACCESS_BUTTON_XPATH.replace("{ChangeAccessButton}", changeAccessButton)).isVisible();
	}

	public void clickUsageTab(String usageTab) {
		page.locator(USAGE_TAB_XPATH.replace("{UsageTab}", usageTab)).isVisible();
		page.locator(USAGE_TAB_XPATH.replace("{UsageTab}", usageTab)).click();
	}

	public void verifyUsageInstructionsSection(String usageInstructionsSection) {
		page.getByText(USAGE_INSTRUCTIONS_SECTION_XPATH.replace("{UsageInstructionsSection}", usageInstructionsSection))
				.isVisible();
	}

}
