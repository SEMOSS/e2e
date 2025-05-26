package aicore.pages;

import com.microsoft.playwright.Page;

import aicore.utils.AddDatabaseToCatalogPageUtils;

public class AddDatabaseToCatalogPage {
    private Page page;

    public AddDatabaseToCatalogPage(Page page) {
        this.page = page;
    }

    public void clickOnAddDatabaseButton() {
        AddDatabaseToCatalogPageUtils.clickAddDatabaseButton(page);
    }

    public void selectDatabaseType(String dbType) {
        AddDatabaseToCatalogPageUtils.selectDatabaseType(page, dbType);
    }

    public String uploadDatabaseFile(String fileName) {
        return AddDatabaseToCatalogPageUtils.uploadDatabaseFile(page, fileName);
    }

    public void clickOnCreateDatabaseButton() {
        AddDatabaseToCatalogPageUtils.clickCreateDatabaseButton(page);
    }

    public String verifyDatabaseNameInCatalog(String dbName) {
        return AddDatabaseToCatalogPageUtils.verifyDatabaseNameInCatalog(page, dbName);
    }

}
