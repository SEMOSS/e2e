package aicore.pages;

import com.microsoft.playwright.Page;

import aicore.utils.AddFunctionToCatalogPageUtils;

public class AddFunctionToCatalogPage extends AbstractAddCatalogPageBase {

    private final String timestamp;

    public AddFunctionToCatalogPage(Page page, String timestamp) {
        this.page = page;
        this.timestamp = timestamp;
    }

    public boolean verifyFunctionIsVisbileInCatalog(String functionName) {
        return AddFunctionToCatalogPageUtils.verifyFunctionIsVisbileInCatalog(page, functionName);
    }

    public void searchFilterValue(String filterValue) {
        AddFunctionToCatalogPageUtils.searchFilterValue(page, filterValue);
    }

    public void selectFilterValue(String filterCategory, String filterValue) {
        AddFunctionToCatalogPageUtils.selectFilterValue(page, filterCategory, filterValue);
    }

    public void clickOnMakeDiscoverableButton() {
        AddFunctionToCatalogPageUtils.clickOnMakeDiscoverableButton(page);
    }

    public void clickOnDiscoverableFunctionsbutton() {
        AddFunctionToCatalogPageUtils.clickOnDiscoverableFunctionsbutton(page);
    }

    public void clickOnAddFunctionButton() {
        AddFunctionToCatalogPageUtils.clickOnAddFunctionButton(page);
    }

    public void selectFunction(String functionType) {
        AddFunctionToCatalogPageUtils.selectFunction(page, functionType);

    }

    public void enterCatalogName(String catalogName) {
        AddFunctionToCatalogPageUtils.enterCatalogName(page, catalogName, timestamp);

    }

    public void enterUrl(String url) {
        AddFunctionToCatalogPageUtils.enterUrl(page, url);
    }

    public void selectHttpMethod(String httpMethod) {
        AddFunctionToCatalogPageUtils.selectHttpMethod(page, httpMethod);
    }

    public void selectPostBodyMessage(String postBodyMessage) {
        AddFunctionToCatalogPageUtils.selectPostBodyMessage(page, postBodyMessage);
    }

    public void verifyAsteriskMarkOnFields(String fieldLabels) {
        AddFunctionToCatalogPageUtils.verifyAsteriskMarkOnFields(page, fieldLabels);
    }

    public void enterHeaders(String headers) {
        AddFunctionToCatalogPageUtils.enterHeaders(page, headers);
    }

    public void enterFunctionParameters(String functionParameters) {
        AddFunctionToCatalogPageUtils.enterFunctionParameters(page, functionParameters);
    }

    public void enterFunctionName(String functionName) {
        AddFunctionToCatalogPageUtils.enterFunctionName(page, functionName);
    }

    public void enterFunctionDescription(String functionDescription) {
        AddFunctionToCatalogPageUtils.enterFunctionDescription(page, functionDescription);
    }

    public void selectFunctionType(String functionType) {
        AddFunctionToCatalogPageUtils.selectFunctionType(page, functionType);
    }

    public void enterFunctionRequiredParameters(String functionRequiredParameters) {
        AddFunctionToCatalogPageUtils.enterFunctionRequiredParameters(page, functionRequiredParameters);
    }

    public void checkCreateFunctionButton() {
        AddFunctionToCatalogPageUtils.checkCreateFunctionButton(page);
    }

    public void clickOnCreateFunctionButton() {
        AddFunctionToCatalogPageUtils.clickOnCreateFunctionButton(page);
    }

    public String enterFilePath(String fileName) {
        return AddFunctionToCatalogPageUtils.enterFilePath(page, fileName);

    }

    public String verifyFunctionNameInCatalog(String catalogName) {
        return AddFunctionToCatalogPageUtils.verifyFunctionNameInCatalog(page, catalogName, timestamp);

    }

    public void clickOnFunctionNameInCatalog(String functionName) {
        AddFunctionToCatalogPageUtils.clickOnFunctionNameInCatalog(page, functionName, timestamp);
    }

    public void clickOnAccessControl() {
        AddFunctionToCatalogPageUtils.clickOnAccessControl(page);
    }

    public void clickOnDeleteButton() {
        AddFunctionToCatalogPageUtils.clickOnDeleteButton(page);
    }

    public void clickOnDeleteConfirmationButton() {
        AddFunctionToCatalogPageUtils.clickOnDeleteConfirmationButton(page);
    }

    public String verifyDeleteToastMessage() {
        return AddFunctionToCatalogPageUtils.verifyDeleteToastMessage(page);

    }

    public String verifySuccessToastMessage(String Toast_message) {
        AddFunctionToCatalogPageUtils.verifySuccessToastMessage(page, Toast_message);
        return Toast_message;
    }

    public boolean verifyMissingInputField() {
        return AddFunctionToCatalogPageUtils.verifyMissingInputField(page);
    }

}
