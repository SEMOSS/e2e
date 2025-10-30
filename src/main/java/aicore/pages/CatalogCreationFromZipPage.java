package aicore.pages;

import com.microsoft.playwright.Page;

import aicore.utils.CatalogCreationFromZipUtil;

public class CatalogCreationFromZipPage {
	private Page page;

	public CatalogCreationFromZipPage(Page page) {
		this.page = page;
	}

	public void openCatalog(String catalogName) {
		CatalogCreationFromZipUtil.openCatalog(page, catalogName);
	}

	public void clickOnAddCatalogButton(String catalogName) {
		CatalogCreationFromZipUtil.clickOnAddCatalogButton(page, catalogName);
	}

	public void selectAddCatalogOption(String option) {
		CatalogCreationFromZipUtil.selectAddCatalogOption(page, option);
		;
	}

	public String uploadFile(String fileName) {
		return CatalogCreationFromZipUtil.uploadFile(page, fileName);
	}

	public void clickOnCreateCatalogButton() {
		CatalogCreationFromZipUtil.clickOnCreateCatalogButton(page);
	}
}
