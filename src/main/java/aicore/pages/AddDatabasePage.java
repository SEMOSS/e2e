package aicore.pages;

import java.nio.file.Path;

import com.microsoft.playwright.Page;

import aicore.utils.AddDatabasePageUtils;

public class AddDatabasePage extends AbstractAddCatalogPageBase {

	public AddDatabasePage(Page page) {
		this.page = page;
	}

	public void clickOnAddDatabaseButton() {
		AddDatabasePageUtils.clickAddDatabaseButton(page);
	}

	public void selectDatabaseType(String dbType) {
		AddDatabasePageUtils.selectDatabaseType(page, dbType);
	}

	public void selectDatabaseFromConnectionTypes(String dbType) {
		AddDatabasePageUtils.selectDatabaseFromConnectionTypes(page, dbType);
	}

	public void enterCatalogName(String catalogName) {
		AddDatabasePageUtils.enterCatalogName(page, catalogName);
	}

	public void uploadHostFile(String hostNameFilePath) {
		AddDatabasePageUtils.enterHostName(page, hostNameFilePath);
	}

	public void clickOnApplyButton() {
		AddDatabasePageUtils.clickOnApplyButton(page);
	}

	public void clickApplyDatabaseButton() {
		AddDatabasePageUtils.clickApplyDatabaseButton(page);
	}

	public boolean verifyDatabaseTitle(String dbName) {
		return AddDatabasePageUtils.verifyDatabaseTitle(page, dbName);
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

	public boolean verifyDatabaseIsVisibleInCatalog(String dbName) {
		return AddDatabasePageUtils.verifyDatabaseIsVisibleInCatalog(page, dbName);
	}

	public boolean clickOnDatabaseNameInCatalogAndCopyID(String dbName) {
		return AddDatabasePageUtils.clickOnDatabaseNameInCatalogAndCopyID(page, dbName);
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

	public void verifyDatabaseName(String databaseName) {
		AddDatabasePageUtils.verifyDatabaseName(page, databaseName);
	}

	public void verifyDatabaseID() {
		AddDatabasePageUtils.verifyDatabaseID(page);
	}

	public void verifyDatabaseDescription(String databaseDescription) {
		AddDatabasePageUtils.verifyDatabaseDescription(page, databaseDescription);
	}

	public Path clickOnExportButton() throws Exception {
		return AddDatabasePageUtils.clickOnExportButton(page);
	}

	public void clickOnEditButton() {
		AddDatabasePageUtils.clickOnEditButton(page);
	}

	public void searchDatabaseCatalog(String catalogName) {
		AddDatabasePageUtils.searchDatabaseCatalog(page, catalogName);
	}

	public void selectDatabaseFromSearchOptions(String catalogName) {
		AddDatabasePageUtils.selectDatabaseFromSearchOptions(page, catalogName);
	}

	public void clickDatabase(String databaseName) {
		AddDatabasePageUtils.clickDatabase(page, databaseName);
	}

	public void clickOnMetaDataTab() {
		AddDatabasePageUtils.clickOnMetadataTab(page);
	}

	public void verifyMetaData() {
		AddDatabasePageUtils.verifyMetaData(page);
	}
}
