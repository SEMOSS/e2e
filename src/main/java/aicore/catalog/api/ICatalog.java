package aicore.catalog.api;

import com.microsoft.playwright.Page;

public interface ICatalog {
	
	public static final String CATALOG_TYPE_DATABASE = "Database";
	public static final String CATALOG_TYPE_STORAGE = "Storage";
	public static final String CATALOG_TYPE_MODEL = "Model";
	public static final String CATALOG_TYPE_VECTOR = "Vector";
	public static final String CATALOG_TYPE_FUNCTION = "Function";
	public static final String CATALOG_TYPE_GUARDRAIL = "Guardrail";
	public static final String CATALOG_TYPE_APP = "App";
	
	enum CATALOG_TYPE {
		DATABASE(CATALOG_TYPE_DATABASE), 
		STORAGE(CATALOG_TYPE_STORAGE), 
		MODEL(CATALOG_TYPE_MODEL),
		VECTOR(CATALOG_TYPE_VECTOR), 
		FUNCTION(CATALOG_TYPE_FUNCTION), 
		GUARDRAIL(CATALOG_TYPE_GUARDRAIL), 
		APP(CATALOG_TYPE_APP);
		
		private final String type;
		private CATALOG_TYPE(String type) {
			this.type = type;
		}
		
		public String getType() {
			return this.type;
		}
	}
	
//	/** MAIN CATALOG PAGE OPERATIONS */
//	void clickCreateOrAddNewCatalog(Page page, CATALOG_TYPE catalogType);
//	
//	void deleteCatalogIfExists(Page page, CATALOG_TYPE catalogType, String catalogName);
//	
//	void searchForCatalog(Page page, CATALOG_TYPE catalogType, String catalogName);
//	
//	boolean selectCatalog(Page page, CATALOG_TYPE catalogType, String catalogName);
//
//	/** CATALOG SETTINGS PAGE OPERATIONS */
//	void navigateToCatalogSetting(Page page);
//	
//	void viewOverviewTab(Page page);
//	
//	void viewDependenciesTab(Page page);
//	
//	void viewMCPUsageTab(Page page);
//	
//	void viewCommitsTab(Page page);
//	
//	void viewSettingsTab(Page page);
//	
//	void viewAccessControlTab(Page page);
//	
//	void viewFilesTab(Page page);
//	
//	void viewSMSSTab(Page page);
//	
//	void exportCatalog(Page page);
//	
//	void editCatalog(Page page);
//	
//	void openCatalog(Page page);
//	
//	/** SECONDARY CATALOG PAGE OPERATIONS */
//	void filterCatalogPage(Page page);
//	
//	void searchByFilter(Page page);
//	
//	void toggleBetweenCatalogListTabs(Page page);
//	
//	void toggleSearchAscendingDescending(Page page);
//	
//	/** CREATE OR ADD CATALOG PAGE OPERATIONS */
//	void searchForCatalogTypes(Page page);
//	
//	void uploadCatalog(Page page);
//	
//	void clickOnCatalogTypeToAdd(Page page);
}
