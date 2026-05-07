package aicore.pages.engine;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import aicore.pages.base.AbstractBasePage;
import aicore.utils.AICorePageUtils;

public abstract class AbstractBaseEnginePage extends AbstractBasePage{
	protected final Page page;
//	protected final Locator addButton;
//	protected final Locator deleteButton;
//	protected final Locator searchInput;
//	protected final Locator settingsButton;
	
	/** create or add new catalog item constants */
	protected static final String CATALOG_TYPE_SELECTOR_TAG = "{catalog_type}";
	protected static final String CATALOG_NAME_SELECTOR_TAG = "{catalog_name}";
	protected static final String ENGINE_ADD_CATALOG_TEST_ID = "engineIndex-add-" + CATALOG_TYPE_SELECTOR_TAG + "-btn";
	
	/** search */
	private static final String ENGINE_SEARCH_BAR_TESTID = "search-bar";
	private static final String ENGINE_SEARCHED_CARD_TEST_ID = "genericEngineCards-" + CATALOG_TYPE_SELECTOR_TAG + "-" + CATALOG_NAME_SELECTOR_TAG;
	


	/** settings page tabs */
	protected static final String ACCESS_SETTINGS_DELETE_BUTTON_TEST_ID = "//button[contains(@data-testid,'-delete-btn')]";
	protected static final String DELETE_CONFIRMATION_POPUP_XPATH = "//div[@data-slot='dialog-content']";
	protected static final String ACCESS_SETTINGS_CONFIRM_DELETE_BUTTON_TEST_ID = "//button[contains(@data-testid,'confirmDelete-btn')]";
	
	 public AbstractBaseEnginePage(Page page) {
	        this.page = page;
//	        this.addButton = page.locator("button#add-engine");
//	        this.deleteButton = page.locator("button#delete-engine");
//	        this.searchInput = page.locator("input#search-engine");
//	        this.settingsButton = page.locator("button#engine-settings");
	    }

//	    public void navigateToList() {
//	        page.navigate("/engines/" + getEngineType());
//	    }

		public boolean addEngine(String name) {
			Locator addButton = page.getByTestId(
					ENGINE_SEARCHED_CARD_TEST_ID.replace(CATALOG_TYPE_SELECTOR_TAG, getEngineType().toUpperCase())
							.replace(CATALOG_NAME_SELECTOR_TAG, getEngineName()));

			if (addButton.isVisible()) {
				waitAndClick(addButton);
				return true;
			}
			return false;
		}

	    public void deleteEngineIfExists(String name) {
	    	// step 1. search for the catalog
			searchForCatalog();
			// step 2. click on the catalog
			if (selectCatalog()) { // if catalog exists, we click and continue, otherwise we are done deleting
			// step 3. click on the access control tab
			clickOnAccessControl();
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

		public void searchForCatalog() {
			Locator searchBar = page.getByTestId(ENGINE_SEARCH_BAR_TESTID);
			waitAndFill(searchBar, getEngineName());
		}

		public boolean selectCatalog() {
			Locator catalogLocator = page.getByTestId(
						ENGINE_SEARCHED_CARD_TEST_ID.replace(CATALOG_TYPE_SELECTOR_TAG, getEngineType().toUpperCase())
								.replace(CATALOG_NAME_SELECTOR_TAG, getEngineName()));
			if (catalogLocator.isVisible()) {
				waitAndClick(catalogLocator);
				return true;
			}
			return false;
		}
		
		// TODO should be abstract here so sub -class can implement???
//		public void clickOnAccessControl() {
//			Locator btn = page.getByTestId(ENGINE_SETTINGS_ACCESS_CONTROL_TAB_TST_ID);
//			waitAndClick(btn);
//		}

//	    public void openSettings(String name) {
//	        searchInput.fill(name);
//	        page.locator("tr:has-text('" + name + "') button#settings").click();
//	    }

	    protected abstract String getEngineType();
	    protected abstract String getEngineName();
	    protected abstract void clickOnOpensettings();
	    protected abstract void clickOnAccessControl();
}
