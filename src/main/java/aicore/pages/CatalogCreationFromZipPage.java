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
	}

	public void clickOnFileUploadIcon() {
		CatalogCreationFromZipUtil.clickOnFileUploadIcon(page);
	}

	public void clickOnCreateCatalogButton() {
		CatalogCreationFromZipUtil.clickOnCreateCatalogButton(page);
	}

	public void clickOnUploadButton(String label) {
		CatalogCreationFromZipUtil.clickOnUploadButton(page, label);
	}
}
