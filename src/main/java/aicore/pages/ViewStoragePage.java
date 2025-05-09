package aicore.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ViewStoragePage {

	private Page page;
	
	private static final String OPEN_STORAGE_XPATH = "//a[@data-testid='Storage-icon']";
	private static final String STATIC_STORAGE_TITLE_XPATH = "//p[text()='Amazon S3 Test Storage']";
	private static final String DYNAMIC_STORAGE_TITLE_XPATH = "//p[text()='{title}']";
	private static final String STORAGE_DESCRIPTION_XPATH = "//p[text()='No description available']";
	private static final String MY_STORAGES_TAB_XPATH = "//button[text()='My Storages']";
	private static final String EDIT_BUTTON_XPATH = "//span[text()='Edit']";   
	private static final String SUBMIT_BUTTON_XPATH = "//span[text()='Submit']";
	private static final String DOMAIN_INPUT_XPATH = "//label[text()='Domain']/following-sibling::div//input";
	private static final String TAG_INPUT_XPATH = "//label[text()='Tag']/following-sibling::div//input";
	private static final String DATA_CLASSIFICATION_INPUT_XPATH = "//label[text()='Data classification']/following-sibling::div//input";
	private static final String DATA_RESTRICTIONS_INPUT_XPATH = "//label[text()='Data restrictions']/following-sibling::div//input";
	private static final String OVERVIEW_FILTER_VALUE_XPATH = "//div[@class='MuiChip-root MuiChip-filled MuiChip-sizeMedium MuiChip-colorDefault MuiChip-filledDefault css-wx0gzd-MuiChip-root']/span[text()='{title}']";
	private static final String FILTER_BY_SECTION_XPATH = "//h6[text()='Filter By']";
	private static final String FILTER_OPTION_TOGGLE_XPATH = "//p[text()='%s']";
	private static final String DISCOVERABLE_STORAGES_TAB_XPATH = "//button[text()='Discoverable Storages']";

	public ViewStoragePage(Page page) {
		this.page = page;
	}
	
	public void openStorageEngine() {
		page.click(OPEN_STORAGE_XPATH);
	}
	
	public boolean isStoragePresent() {
		return page.isVisible(STATIC_STORAGE_TITLE_XPATH);
	}
	
	public void clickMyStoragesTab() {
		page.click(MY_STORAGES_TAB_XPATH);
	}
	
	
	public void clickEditButton() {
		page.click(EDIT_BUTTON_XPATH);
	}
	
	public void clickDiscoverableStoragesTab() {
		page.click(DISCOVERABLE_STORAGES_TAB_XPATH);
	}
	
	public void clickSubmitButton() {
		page.click(SUBMIT_BUTTON_XPATH);
	}
	
	public void clickOnStorage(String storageName) {
		page.locator(DYNAMIC_STORAGE_TITLE_XPATH.replace("{title}", storageName)).click();
	}
	
	public String getStorageTitle(String storageTitle) {
		String actualtitle = page.textContent(DYNAMIC_STORAGE_TITLE_XPATH.replace("{title}", storageTitle)).trim();
		return actualtitle;
	}
	
	public String getStorageDescription() {
		return page.textContent(STORAGE_DESCRIPTION_XPATH);
	}
		
	public void enterTag(String TagName) {
		page.locator(TAG_INPUT_XPATH).click();
		page.locator(TAG_INPUT_XPATH).fill(TagName);
		page.locator(TAG_INPUT_XPATH).press("Enter");
	}

	public boolean isFilterValueVisibleInOverview(String expectedFilter) {
        Locator testTagLocator = page.locator("span.MuiChip-label.MuiChip-labelMedium:has-text('"+expectedFilter+"')").first();
		return  testTagLocator.isVisible();
	}
	
	public void enterDomain(String domainName) {
		page.locator(DOMAIN_INPUT_XPATH).click();
		page.locator(DOMAIN_INPUT_XPATH).fill(domainName);
		page.locator(DOMAIN_INPUT_XPATH).press("Enter");
	}
	
	public void enterAndSelectDataClassification(String dataClassificationName) {
		page.locator(DATA_CLASSIFICATION_INPUT_XPATH).click();
		page.locator(DATA_CLASSIFICATION_INPUT_XPATH).fill(dataClassificationName);
		page.getByText(dataClassificationName).click();
	}
	
	public void enterAndSelectDataRestriction(String dataRestrictionValue) {
		page.locator(DATA_RESTRICTIONS_INPUT_XPATH).click();
		page.locator(DATA_RESTRICTIONS_INPUT_XPATH).fill(dataRestrictionValue);
		page.getByText(dataRestrictionValue).click();
		
	}

	public void expandFilterBySection() {
		page.click(FILTER_BY_SECTION_XPATH);
	}
	
	public void toggleFilter(String filterName) {
		page.click(String.format(FILTER_OPTION_TOGGLE_XPATH, filterName));
		
	}
	
}
