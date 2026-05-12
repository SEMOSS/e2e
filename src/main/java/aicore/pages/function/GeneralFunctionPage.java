package aicore.pages.function;

import com.microsoft.playwright.Page;

import aicore.catalog.impl.AbstractEngineCatalogPage;

public class GeneralFunctionPage {
	
	public static void clickAddFunctionButton(Page page) {
		AbstractEngineCatalogPage.clickCreateOrAddNewCatalog(page, AbstractEngineCatalogPage.CATALOG_TYPE.FUNCTION);
	}
	
	public static void deleteFunctionIfExists(Page page, String functionName) {
		AbstractEngineCatalogPage.deleteCatalogIfExists(page, AbstractEngineCatalogPage.CATALOG_TYPE.FUNCTION, functionName);
	}

	public static void searchForFunction(Page page, String functionName) {
		AbstractEngineCatalogPage.searchForCatalog(page, AbstractEngineCatalogPage.CATALOG_TYPE.FUNCTION, functionName);
	}
	
	public static void clickOnFunction(Page page, String functionName) {
		AbstractEngineCatalogPage.selectCatalog(page, AbstractEngineCatalogPage.CATALOG_TYPE.FUNCTION, functionName);
	}
	
}
