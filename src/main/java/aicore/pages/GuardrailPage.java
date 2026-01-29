package aicore.pages;

import com.microsoft.playwright.Page;

import aicore.utils.GuardrailPageUtils;

public class GuardrailPage {
	private Page page;

	public GuardrailPage(Page page) {
		this.page = page;
	}

	public void clickOnAddGuardrailButton() {
		GuardrailPageUtils.clickOnAddGuardrailButton(page);
	}

	public void enterCatalogName(String catalogName) {
		GuardrailPageUtils.enterCatalogName(page, catalogName);
	}

	public void enterNerLabels(String label) {
		GuardrailPageUtils.enterNerLabels(page, label);
	}

	public void enterDefaultThreshold(String threshold) {
		GuardrailPageUtils.enterDefaultThreshold(page, threshold);
	}

	public String verifyGuardrailTitle(String guardrailTitle) {
		return GuardrailPageUtils.verifyGuardrailTitle(page, guardrailTitle);
	}
}
