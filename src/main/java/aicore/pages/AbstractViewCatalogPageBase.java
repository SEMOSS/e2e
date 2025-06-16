package aicore.pages;

import com.microsoft.playwright.Page;

import aicore.utils.AddCatalogPageBaseUtils;

public abstract class AbstractViewCatalogPageBase {

	protected Page page;

	private static final String FUNCTION_NAME_XPATH = "//h4[text()='{FunctionName}']";
	private static final String FUNCTION_ID_XPATH = "//button[@aria-label=\"copy Function ID\"]/parent::span";
	private static final String FUNCTION_DESCRIPTION_XPATH = "//h6[text()='{FunctionDescription}']";
	private static final String DATE_LAST_UPDATED_XPATH = "//p[text()='{DateLastUpdated}']";

	public boolean isSearchBarPresent() {
		return page.getByPlaceholder("Search").isVisible();
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

	public boolean verifyCatalogName(String catalogName) {
		return AddCatalogPageBaseUtils.verifyCatalogName(page, catalogName);
	}

	public boolean verifyCatalogDescription(String catalogDescription) {
		return AddCatalogPageBaseUtils.verifyCatalogDescription(page, catalogDescription);
	}

	public boolean verifyCatalogID(String catalogID) {
		return AddCatalogPageBaseUtils.verifyCatalogID(page, catalogID);
	}

	public boolean checkCopyIcon() {
		return AddCatalogPageBaseUtils.checkCopyIcon(page);
	}

	public void clickCopyIcon() {
		AddCatalogPageBaseUtils.clickCopyIcon(page);
	}

	public boolean verifyCopyToastMessage(String toastMessage) {
		return AddCatalogPageBaseUtils.verifyCopyToastMessage(page, toastMessage);
	}

	public boolean checkEditIcon() {
		return AddCatalogPageBaseUtils.checkEditIcon(page);
	}

	public void clickEditIcon() {
		AddCatalogPageBaseUtils.clickEditIcon(page);
	}

	public void enterTagName(String tagName) {
		AddCatalogPageBaseUtils.enterTagName(page, tagName);
	}

	public void clickOnSubmit() {
		AddCatalogPageBaseUtils.clickOnSubmit(page);
	}
	
	public void clickOnClose() {
		AddCatalogPageBaseUtils.clickOnClose(page);
	}

	public String verifyEditSuccessfullToastMessage() {
		return AddCatalogPageBaseUtils.verifyEditSuccessfullToastMessage(page);
	}

	public void waitForEditSuccessToastMessageToDisappear() {
		AddCatalogPageBaseUtils.waitForEditSuccessToastMessageToDisappear(page);
	}

}