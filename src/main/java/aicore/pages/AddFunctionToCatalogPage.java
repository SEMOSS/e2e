package aicore.pages;

import com.microsoft.playwright.Page;

import aicore.pages.function.AddFunctionFormUtils;
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
		AddFunctionFormUtils.selectFunction(page, functionType);
	}

	public boolean fieldUnderSection(String section, String field) {
		return AddFunctionFormUtils.fieldUnderSection(page, section, field);
	}

	public boolean isFieldMandatory(String field) {
		return AddFunctionFormUtils.isFieldMandatory(page, field);
	}

	public void fillFunctionCreationForm(String fieldName, String fieldValue) {
		AddFunctionFormUtils.fillFunctionCreationForm(page, fieldName, fieldValue, timestamp);
	}

	public void enterCatalogName(String catalogName) {
		AddFunctionFormUtils.enterCatalogName(page, catalogName, timestamp);
	}

	public void enterUrl(String url) {
		AddFunctionFormUtils.enterUrl(page, url);
	}

	public void selectHttpMethod(String httpMethod) {
		AddFunctionFormUtils.selectHttpMethod(page, httpMethod);
	}

	public void selectPostBodyMessage(String postBodyMessage) {
		AddFunctionFormUtils.selectPostBodyMessage(page, postBodyMessage);
	}

	public void enterHeaders(String headers) {
		AddFunctionFormUtils.enterHeaders(page, headers);
	}

	public void enterFunctionParameters(String functionParameters) {
		AddFunctionFormUtils.enterFunctionParameters(page, functionParameters);
	}

	public void enterFunctionName(String functionName) {
		AddFunctionFormUtils.enterFunctionName(page, functionName);
	}

	public void enterFunctionDescription(String functionDescription) {
		AddFunctionFormUtils.enterFunctionDescription(page, functionDescription);
	}

	public boolean verifyCreateFunctionButtonDisabled() {
		return AddFunctionFormUtils.verifyCreateFunctionButtonDisabled(page);
	}

	public void selectFunctionType(String functionType) {
		AddFunctionFormUtils.selectFunctionType(page, functionType);
	}

	public void enterFunctionRequiredParameters(String functionRequiredParameters) {
		AddFunctionFormUtils.enterFunctionRequiredParameters(page, functionRequiredParameters);
	}

	public void checkCreateFunctionButton() {
		AddFunctionFormUtils.checkCreateFunctionButton(page);
	}

	public boolean validateConnectButtonEnabled() {
		return AddFunctionFormUtils.validateConnectButtonEnabled(page);
	}

	public void clickOnConnectButton() {
		AddFunctionFormUtils.clickOnConnectButton(page);
	}

	public String verifyFunctionNameInCatalog(String catalogName) {
		return AddFunctionPageUtils.verifyFunctionNameInCatalog(page, catalogName, timestamp);
	}

	public void clickOnFunctionNameInCatalog(String functionName) {
		AddFunctionPageUtils.clickOnFunctionNameInCatalog(page, functionName, timestamp);
	}

	public void clickOnFileTab() {
		AddFunctionPageUtils.clickOnFileTab(page);
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

	public String verifyDeleteToastMessage(String toastMessage) {
		return AddFunctionPageUtils.verifyDeleteToastMessage(page, toastMessage);
	}

	public String verifySuccessToastMessage(String toastMessage) {
		return AddFunctionPageUtils.verifySuccessToastMessage(page, toastMessage);
	}

	public boolean verifyMissingInputField() {
		return AddFunctionFormUtils.verifyMissingInputField(page);
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
