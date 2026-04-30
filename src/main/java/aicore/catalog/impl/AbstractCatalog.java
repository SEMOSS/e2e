package aicore.catalog.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import aicore.catalog.api.ICatalog;
import aicore.pages.base.AbstractBasePage;
import aicore.pages.function.FunctionAccessSettingsUtils;
import aicore.utils.AICorePageUtils;

public abstract class AbstractCatalog extends AbstractBasePage implements ICatalog {
	private static final Logger logger = LogManager.getLogger(AbstractCatalog.class);

	// TODO should move to separate file if this list starts to become too large
	/** create or add new catalog item constants */
	private static final String CATALOG_TYPE_SELECTOR_TAG = "{catalog_type}";
	private static final String CATALOG_NAME_SELECTOR_TAG = "{catalog_name}";
	private static final String ENGINE_NEW_CATALOG_TEST_ID = "engineIndex-add-" + CATALOG_TYPE_SELECTOR_TAG + "-btn";
	private static final String APP_NEW_CATALOG_TEST_ID = "appCatalogPage-create-new-app-btn";

	/** SEARCH */
	private static final String ENGINE_SEARCH_BAR_TESTID = "search-bar";
	private static final String APP_SEARCH_BAR_ARIA_LABEL = "Search apps"; /// app catalog search bar currently does not
																			/// have a test id
	private static final String ENGINE_SEARCHED_CARD_TEST_ID = "genericEngineCards-" + CATALOG_TYPE_SELECTOR_TAG + "-"
			+ CATALOG_NAME_SELECTOR_TAG;
	private static final String APP_SEARCHED_CARD_TEST_ID = "appTileCard-" + CATALOG_NAME_SELECTOR_TAG + "-row";

	/** settings page tabs */
	private static final String ENGINE_SETTINGS_ACCESS_CONTROL_TAB_TST_ID = "engineLayout-Access Control-tab";
	private static final String APP_SETTINGS_ACCESS_CONTROL_TAB_TEXT = "Access Control";
	private static final String ACCESS_SETTINGS_DELETE_BUTTON_TEST_ID = "//button[contains(@data-testid,'-delete-btn')]";
	private static final String DELETE_CONFIRMATION_POPUP_XPATH = "//div[@data-slot='dialog-content']";
	private static final String ACCESS_SETTINGS_CONFIRM_DELETE_BUTTON_TEST_ID = "//button[contains(@data-testid,'confirmDelete-btn')]";

	@Override
	public void clickCreateOrAddNewCatalog(Page page, CATALOG_TYPE catalogType) {
		Locator locator = null;
		switch (catalogType) {
		case CATALOG_TYPE.APP:
			locator = page.getByTestId(APP_NEW_CATALOG_TEST_ID);
			break;
		case CATALOG_TYPE.DATABASE:
		case CATALOG_TYPE.STORAGE:
		case CATALOG_TYPE.MODEL:
		case CATALOG_TYPE.VECTOR:
		case CATALOG_TYPE.FUNCTION:
		case CATALOG_TYPE.GUARDRAIL:
			locator = page
					.getByTestId(ENGINE_NEW_CATALOG_TEST_ID.replace(CATALOG_TYPE_SELECTOR_TAG, catalogType.getType()));
			break;
		}
		waitAndClick(locator);
		logger.info("Clicked on Add " + catalogType.getType() + " Button");
	}

	@Override
	public void deleteCatalogIfExists(Page page, CATALOG_TYPE catalogType, String catalogName) {
		// step 1. search for the catalog
		searchForCatalog(page, catalogType, catalogName);
		// step 2. click on the catalog
		if (selectCatalog(page, catalogType, catalogName)) { // if catalog exists, we click and continue, otherwise we are done deleting
		// step 3. click on the access control tab
		clickOnAccessControl(page, catalogType);
		// step 4. click delete button
		Locator locator = page.locator(ACCESS_SETTINGS_DELETE_BUTTON_TEST_ID);
		waitAndClick(locator);
		// step 5. click on the delete confirmation button
		Locator confirmPopup = page.locator(DELETE_CONFIRMATION_POPUP_XPATH);
		AICorePageUtils.waitFor(confirmPopup); /// wait for the confirmation popup to show before looking for
												/// confirm-delete button
		Locator confirmDeleteLocator = page.locator(ACCESS_SETTINGS_CONFIRM_DELETE_BUTTON_TEST_ID);
		waitAndClick(confirmDeleteLocator);
		}
	}

	@Override
	public void searchForCatalog(Page page, CATALOG_TYPE catalogType, String catalogName) {
		Locator searchBar = null;
		switch (catalogType) {
		case CATALOG_TYPE.APP:
			searchBar = page.getByLabel(APP_SEARCH_BAR_ARIA_LABEL);
			break;
		case CATALOG_TYPE.DATABASE:
		case CATALOG_TYPE.STORAGE:
		case CATALOG_TYPE.MODEL:
		case CATALOG_TYPE.VECTOR:
		case CATALOG_TYPE.FUNCTION:
		case CATALOG_TYPE.GUARDRAIL:
			searchBar = page.getByTestId(ENGINE_SEARCH_BAR_TESTID);
			break;
		}

		waitAndFill(searchBar, catalogName);
	}

	@Override
	public boolean selectCatalog(Page page, CATALOG_TYPE catalogType, String catalogName) {
		Locator catalogLocator = null;
		switch (catalogType) {
		case CATALOG_TYPE.APP:
			String formattedCatalogName = catalogName.replace(" ", "-");
			catalogLocator = page
					.getByTestId(APP_SEARCHED_CARD_TEST_ID.replace(CATALOG_NAME_SELECTOR_TAG, formattedCatalogName));
			break;
		case CATALOG_TYPE.DATABASE:
		case CATALOG_TYPE.STORAGE:
		case CATALOG_TYPE.MODEL:
		case CATALOG_TYPE.VECTOR:
		case CATALOG_TYPE.FUNCTION:
		case CATALOG_TYPE.GUARDRAIL:
			catalogLocator = page.getByTestId(
					ENGINE_SEARCHED_CARD_TEST_ID.replace(CATALOG_TYPE_SELECTOR_TAG, catalogType.getType().toUpperCase())
							.replace(CATALOG_NAME_SELECTOR_TAG, catalogName));
			break;
		}
		if (catalogLocator.isVisible()) {
			waitAndClick(catalogLocator);
			return true;
		}
		return false;
	}

	@Override
	public void navigateToCatalogSetting(Page page) {
		// TODO Auto-generated method stub

	}

	@Override
	public void viewOverviewTab(Page page) {
		// TODO Auto-generated method stub

	}

	@Override
	public void viewDependenciesTab(Page page) {
		// TODO Auto-generated method stub

	}

	@Override
	public void viewMCPUsageTab(Page page) {
		// TODO Auto-generated method stub

	}

	@Override
	public void viewCommitsTab(Page page) {
		// TODO Auto-generated method stub

	}

	@Override
	public void viewSettingsTab(Page page) {
		// TODO Auto-generated method stub

	}

	@Override
	public void viewAccessControlTab(Page page) {
		// TODO Auto-generated method stub

	}

	public static void clickOnAccessControl(Page page, CATALOG_TYPE catalogType) {
		Locator btn = null;
		switch (catalogType) {
		case CATALOG_TYPE.APP:
			btn = page.getByRole(AriaRole.BUTTON,
					new Page.GetByRoleOptions().setName(APP_SETTINGS_ACCESS_CONTROL_TAB_TEXT));
			break;
		case CATALOG_TYPE.DATABASE:
		case CATALOG_TYPE.STORAGE:
		case CATALOG_TYPE.MODEL:
		case CATALOG_TYPE.VECTOR:
		case CATALOG_TYPE.FUNCTION:
		case CATALOG_TYPE.GUARDRAIL:
			btn = page.getByTestId(ENGINE_SETTINGS_ACCESS_CONTROL_TAB_TST_ID);
			break;
		}
		waitAndClick(btn);
	}

	@Override
	public void viewFilesTab(Page page) {
		// TODO Auto-generated method stub

	}

	@Override
	public void viewSMSSTab(Page page) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exportCatalog(Page page) {
		// TODO Auto-generated method stub

	}

	@Override
	public void editCatalog(Page page) {
		// TODO Auto-generated method stub

	}

	@Override
	public void openCatalog(Page page) {
		// TODO Auto-generated method stub

	}

	@Override
	public void filterCatalogPage(Page page) {
		// TODO Auto-generated method stub

	}

	@Override
	public void searchByFilter(Page page) {
		// TODO Auto-generated method stub

	}

	@Override
	public void toggleBetweenCatalogListTabs(Page page) {
		// TODO Auto-generated method stub

	}

	@Override
	public void toggleSearchAscendingDescending(Page page) {
		// TODO Auto-generated method stub

	}

	@Override
	public void searchForCatalogTypes(Page page) {
		// TODO Auto-generated method stub

	}

	@Override
	public void uploadCatalog(Page page) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clickOnCatalogTypeToAdd(Page page) {
		// TODO Auto-generated method stub

	}

}
