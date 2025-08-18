package aicore.pages;

import com.microsoft.playwright.Page;

import aicore.utils.AddFunctionPageUtils;

public class AddFunctionToCatalogPage extends AbstractAddCatalogPageBase {

	private final String timestamp;

	public AddFunctionToCatalogPage(Page page, String timestamp) {
		this.page = page;
		this.timestamp = timestamp;
	}

	public boolean verifyFunctionIsVisibleInCatalog(String functionName) {
		return AddFunctionPageUtils.verifyFunctionIsVisibleInCatalog(page, functionName);
	}

	public void searchFilterValue(String filterValue) {
		AddFunctionPageUtils.searchFilterValue(page, filterValue);
	}

	public void selectFilterValue(String filterCategory, String filterValue) {
		AddFunctionPageUtils.selectFilterValue(page, filterCategory, filterValue);
	}

	public void clickOnSettings() {
		AddFunctionPageUtils.clickOnSettings(page);
	}
	public void clickOnMakeDiscoverableButton(String caltalogName) {
		AddFunctionPageUtils.clickOnMakeDiscoverableButton(page, caltalogName);
	}
	public void clickOnDiscoverableFunctionsbutton() {
		AddFunctionPageUtils.clickOnDiscoverableFunctionsbutton(page);
	}

	public void clickOnAddFunctionButton() {
		AddFunctionPageUtils.clickOnAddFunctionButton(page);
	}

	public void selectFunction(String functionType) {
		AddFunctionPageUtils.selectFunction(page, functionType);

	}

	public void enterCatalogName(String catalogName) {
		AddFunctionPageUtils.enterCatalogName(page, catalogName, timestamp);
	}

	public void enterUrl(String url) {
		AddFunctionPageUtils.enterUrl(page, url);
	}

	public void selectHttpMethod(String httpMethod) {
		AddFunctionPageUtils.selectHttpMethod(page, httpMethod);
	}

	public void selectPostBodyMessage(String postBodyMessage) {
		AddFunctionPageUtils.selectPostBodyMessage(page, postBodyMessage);
	}

	public void verifyAsteriskMarkOnFields(String fieldLabels) {
		AddFunctionPageUtils.verifyAsteriskMarkOnFields(page, fieldLabels);
	}

	public void enterHeaders(String headers) {
		AddFunctionPageUtils.enterHeaders(page, headers);
	}

	public void enterFunctionParameters(String functionParameters) {
		AddFunctionPageUtils.enterFunctionParameters(page, functionParameters);
	}

	public void enterFunctionName(String functionName) {
		AddFunctionPageUtils.enterFunctionName(page, functionName);
	}

	public void enterFunctionDescription(String functionDescription) {
		AddFunctionPageUtils.enterFunctionDescription(page, functionDescription);
	}

	public boolean verifyCreateFunctionButtonDisabled() {
		return AddFunctionPageUtils.verifyCreateFunctionButtonDisabled(page);
	}

	public void selectFunctionType(String functionType) {
		AddFunctionPageUtils.selectFunctionType(page, functionType);
	}

	public void enterFunctionRequiredParameters(String functionRequiredParameters) {
		AddFunctionPageUtils.enterFunctionRequiredParameters(page, functionRequiredParameters);
	}

	public void checkCreateFunctionButton() {
		AddFunctionPageUtils.checkCreateFunctionButton(page);
	}

	public void clickOnCreateFunctionButton() {
		AddFunctionPageUtils.clickOnCreateFunctionButton(page);
	}

	public String enterFilePath(String fileName) {
		return AddFunctionPageUtils.enterFilePath(page, fileName);
	}

	public String verifyFunctionNameInCatalog(String catalogName) {
		return AddFunctionPageUtils.verifyFunctionNameInCatalog(page, catalogName, timestamp);

	}

	public void clickOnFunctionNameInCatalog(String functionName) {
		AddFunctionPageUtils.clickOnFunctionNameInCatalog(page, functionName, timestamp);
	}

	public void clickOnAccessControl() {
		AddFunctionPageUtils.clickOnAccessControl(page);
	}

	public void clickOnDeleteButton() {
		AddFunctionPageUtils.clickOnDeleteButton(page);
	}

	public void clickOnDeleteConfirmationButton() {
		AddFunctionPageUtils.clickOnDeleteConfirmationButton(page);
	}

	public String verifyDeleteToastMessage() {
		return AddFunctionPageUtils.verifyDeleteToastMessage(page);
	}

	public String verifySuccessToastMessage(String Toast_message) {
		AddFunctionPageUtils.verifySuccessToastMessage(page, Toast_message);
		return Toast_message;
	}

	public boolean verifyMissingInputField() {
		return AddFunctionPageUtils.verifyMissingInputField(page);
	}

	public void searchFunctionCatalog(String catalogName) {
		AddFunctionPageUtils.searchFunctionCatalog(page, catalogName);
	}

	public void selectFunctionFromSearchOptions(String catalogName) {
		AddFunctionPageUtils.selectFunctionFromSearchOptions(page, catalogName);
	}
}
