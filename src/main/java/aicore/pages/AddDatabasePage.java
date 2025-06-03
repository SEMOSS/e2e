package aicore.pages;

import com.microsoft.playwright.Page;

import aicore.utils.AddDatabasePageUtils;

public class AddDatabasePage {
	private Page page;

	public AddDatabasePage(Page page) {
		this.page = page;
	}

	public void clickOnAddDatabaseButton() {
		AddDatabasePageUtils.clickAddDatabaseButton(page);
	}

	public void selectDatabaseType(String dbType) {
		AddDatabasePageUtils.selectDatabaseType(page, dbType);
	}

	public String uploadDatabaseFile(String fileName) {
		return AddDatabasePageUtils.uploadDatabaseFile(page, fileName);
	}

	public void clickOnCreateDatabaseButton() {
		AddDatabasePageUtils.clickCreateDatabaseButton(page);
	}

	public String verifyDatabaseNameInCatalog(String dbName) {
		return AddDatabasePageUtils.verifyDatabaseNameInCatalog(page, dbName);
	}

	public void clickOnDatabaseNameInCatalog(String dbName) {
		AddDatabasePageUtils.clickOnDatabaseNameInCatalog(page, dbName);
	}

	public boolean verifyDatabaseIsVisbileInCatalog(String dbName) {
		return AddDatabasePageUtils.verifyDatabaseIsVisbileInCatalog(page, dbName);
	}

	public void clickOnCopyID(String dbName) {
		AddDatabasePageUtils.clickOnCopyID(page, dbName);
	}

	public String verifyCopyIdSuccessToastMessage(String toastMessage) {
		return AddDatabasePageUtils.verifyCopyIdSuccessToastMessage(page, toastMessage);
	}

	public void searchFilterValue(String filterValue) {
		AddDatabasePageUtils.searchFilterValue(page, filterValue);
	}

	public void selectFilterValue(String filterCategory, String filterValue) {
		AddDatabasePageUtils.selectFilterValue(page, filterCategory, filterValue);
	}

	public void clickOnBookmark(String catalogName) {
		AddDatabasePageUtils.clickOnBookmark(page, catalogName);
	}

	public void clickOnUnbookmark(String catalogName) {
		AddDatabasePageUtils.clickOnUnbookmark(page, catalogName);
	}

	public boolean verifyCatalogDisplayedUnderBookmarkedSection(String catalaogName) {
		return AddDatabasePageUtils.verifyCatalogDisplayedUnderBookmarkedSection(page, catalaogName);
	}

}
