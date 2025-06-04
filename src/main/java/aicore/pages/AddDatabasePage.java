package aicore.pages;

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

}
