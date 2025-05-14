package aicore.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import aicore.utils.AddFunctionToCatalogPageUtils;

public class AddFunctionToCatalogPage {

    private Page page;
    private String timestamp;
    private static final String FUNCTION_SECTION_NAME_XPATH = "//div[text()='{sectionName}']";
    private static final String DATABASE_OPTIONS_UNDER_SECTION_XPATH = "//div[text()='{sectionName}']/following-sibling::div//p[text()='{optionName}']";
    private static final String ICONS_XPATH = "//p[text()='{optionName}']/parent::div//img";

    public AddFunctionToCatalogPage(Page page, String timestamp) {
        this.page = page;
        this.timestamp = timestamp;
    }

    public boolean isSearchBarPresent() {
        return page.getByPlaceholder("Search").isVisible();
    }

    public boolean verifySectionIsVisible(String sectionName) {
        boolean isSectionVisible = page.isVisible(FUNCTION_SECTION_NAME_XPATH.replace("{sectionName}", sectionName));
        return isSectionVisible;
    }

    public boolean VerifyDatabaseOptionIsVisible(String sectionName, String databaseOptionName) {
        boolean isOptionVisible = page.isVisible(DATABASE_OPTIONS_UNDER_SECTION_XPATH
                .replace("{sectionName}", sectionName).replace("{optionName}", databaseOptionName));
        return isOptionVisible;
    }

    public Locator getIconByLabel(String optionName) {
        return page.locator(ICONS_XPATH.replace("{optionName}", optionName));
    }

    public boolean isIconVisible(String optionName) {
        return page.locator(ICONS_XPATH.replace("{optionName}", optionName)).isVisible();
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
