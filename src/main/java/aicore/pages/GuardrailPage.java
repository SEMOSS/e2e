package aicore.pages;

import java.nio.file.Path;

import com.microsoft.playwright.Page;

import aicore.pages.guardrail.AddGuardrailFormUtils;
import aicore.utils.GuardrailPageUtils;

public class GuardrailPage {
	private Page page;

	public GuardrailPage(Page page) {
		this.page = page;
	}

	public void enterCatalogName(String catalogName) {
		AddGuardrailFormUtils.enterCatalogName(page, catalogName);
	}

	public void createGuardrail(String guardrailType, String catalogName, String nerLabels, String defaultThreshold) {
		AddGuardrailFormUtils.createGuardrail(page, guardrailType, catalogName, nerLabels, defaultThreshold);
	}

	public String verifyGuardrailTitle(String guardrailTitle) {
		return GuardrailPageUtils.verifyGuardrailTitle(page, guardrailTitle);
	}

	public String verifyToastMessage(String toastMessage) {
		return GuardrailPageUtils.verifyToastMessage(page, toastMessage);
	}

	public Path downloadCatalog() {
		return GuardrailPageUtils.downloadCatalog(page);
	}

	public void searchGuardrailCatalog(String searchText) {
		GuardrailPageUtils.searchGuardrailCatalog(page, searchText);
	}

	public boolean verifySearchedGuardrailCatalogTitle(String guardrailTitle) {
		return GuardrailPageUtils.verifySearchedGuardrailCatalogTitle(page, guardrailTitle);
	}

	public void selectTheGuardrailCatalog(String guardrailTitle) {
		GuardrailPageUtils.selectTheGuardrailCatalog(page, guardrailTitle);
	}

}
