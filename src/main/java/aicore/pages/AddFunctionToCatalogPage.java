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

	public boolean fieldUnderSection(String section, String field) {
		return AddFunctionPageUtils.fieldUnderSection(page, section, field);
	}

	public boolean isFieldMandatory(String field) {
		return AddFunctionPageUtils.isFieldMandatory(page, field);
	}

	public void fillModelCreationForm(String fieldName, String fieldValue) {
		AddFunctionPageUtils.fillCatalogCreationForm(page, fieldName, fieldValue, timestamp);
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
//
//	public void verifyAsteriskMarkOnFields(String fieldLabels) {
//		AddFunctionPageUtils.verifyAsteriskMarkOnFields(page, fieldLabels);
//	}

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

	public boolean validateConnectButtonEnabled() {
		return AddFunctionPageUtils.validateConnectButtonEnabled(page);
	}

	public void clickOnConnectButton() {
		AddFunctionPageUtils.clickOnConnectButton(page);
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

	public String verifySuccessToastMessage() {
		return AddFunctionPageUtils.verifySuccessToastMessage(page);
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

	public void deleteCatalog(String catalog, String catalogName) {
		AddFunctionPageUtils.deleteCatalog(page, catalog, catalogName);
	}
	public void closeToastMessage() {
		AddFunctionPageUtils.closeToastMessage(page);
	}
}
