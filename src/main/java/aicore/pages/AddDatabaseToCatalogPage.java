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

	public boolean verifyDatabaseIsVisbileInCatalog(String dbName) {
		return AddDatabaseToCatalogPageUtils.verifyDatabaseIsVisbileInCatalog(page, dbName);
	}

	public void clickOnDatabaseNameInCatalog(String dbName) {
		AddDatabaseToCatalogPageUtils.clickOnDatabaseNameInCatalog(page, dbName);
	}

	public void clickOnCopyID() {
		AddDatabaseToCatalogPageUtils.clickOnCopyID(page);
	}

	public String verifyCopyIdSuccessToastMessage(String toastMessage) {
		return AddDatabaseToCatalogPageUtils.verifyCopyIdSuccessToastMessage(page, toastMessage);
	}

	public void searchFilterValue(String filterValue) {
		AddDatabaseToCatalogPageUtils.searchFilterValue(page, filterValue);
	}

	public void selectFilterValue(String filterCategory, String filterValue) {
		AddDatabaseToCatalogPageUtils.selectFilterValue(page, filterCategory, filterValue);
	}
}
