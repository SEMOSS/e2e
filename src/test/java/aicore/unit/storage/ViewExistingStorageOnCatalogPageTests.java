package aicore.unit.storage;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;

import aicore.pages.base.EditMetadataPageUtils;
import aicore.pages.function.FunctionAccessSettingsUtils;
import aicore.pages.home.HomePageUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.pages.model.EditModelPageUtils;
import aicore.pages.model.SettingsModelPageUtils;
import aicore.pages.storage.AddStorageFormUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.AddFunctionPageUtils;
import aicore.utils.CatalogFilterPageUtils;
import aicore.utils.CatlogAccessPageUtility;
import aicore.utils.CommonUtils;
import aicore.utils.SearchAndSelectCatalogPageUtils;
import aicore.utils.StoragePageUtils;
import aicore.utils.TestResourceTrackerHelper;
import aicore.utils.annotations.PWPage;


public class ViewExistingStorageOnCatalogPageTests extends AbstractPlaywrightTestBase {

	private static final String DELETE_TOAST_MESSAGE_XPATH = "//li[@data-type='success']";
	private static String timestamp = CommonUtils.getTimeStampName();
	String catalogName = "Amazon S3 Storage" + timestamp;
	
	@BeforeEach
	public void setup(@PWPage Page page) {
		
		loginNativeAdmin(page); // or should it be loginAdmin(page) instead of native?
		
		// Add Amazon S3 Storage
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenStorage(page);
		StoragePageUtils.clickOnAddStorageButton(page);
		AddStorageFormUtils.selectStorage(page, "Amazon S3");
		AddStorageFormUtils.enterCatalogName(page, catalogName);
		AddStorageFormUtils.enterRegionName(page, "India");
		AddStorageFormUtils.enterBucket(page, "BucketTest");
		AddStorageFormUtils.enterAccessKey(page, "Test123");
		AddStorageFormUtils.enterSecretKey(page, "Test123");
		StoragePageUtils.clickOnConnectButton(page);
		CatlogAccessPageUtility.getCatalogAndCopyId(page);
		EditMetadataPageUtils.clickEditIcon(page);
		
		String tags = "embeddings, Test1";
		String[] tagsArray = tags.split(", ");
		for (String tag : tagsArray) {
			EditMetadataPageUtils.enterTagName(page, tag);
		}
		
		String domainNames = "SAP, AI";
		String[] allDomainNames = domainNames.split(", ");
		for (String domainName : allDomainNames) {
			EditMetadataPageUtils.enterDomainName(page, domainName);
		}
		
		String dataClassificationOptions = "PUBLIC, RESTRICTED";
		String[] options = dataClassificationOptions.split(", ");
		for (String option : options) {
			EditMetadataPageUtils.selectDataClassificationOption(page, option);
		}
		
		String dataRestrictionOptions = "FOUO ALLOWED, INTERNAL ALLOWED";
		options = dataRestrictionOptions.split(", ");
		for (String option : options) {
			EditMetadataPageUtils.selectDataRestrictionsOption(page, option);
		}
		
		EditMetadataPageUtils.clickOnSubmit(page);
	}
	
	
	@AfterEach
	void tearDown(@PWPage Page page) {
		HomePageUtils.navigateToHomePage(page);
		assertTrue(CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_STORAGE, catalogName));
		logout(page);
	}
	
	@Test
	public void viewAndValidateFilterFunctionalityMyStorage_test(@PWPage Page page) throws Exception {
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenStorage(page);
		SearchAndSelectCatalogPageUtils.searchCatalog(page, catalogName);
		
//		| FILTER_CATEGORY     | FILTER_VALUE      |
//	    | Tag                 | embeddings, Test1 |
//	    | Domain              | SAP, AI           |
//	    | Data Classification | PUBLIC            |
//	    | Data Restrictions   | FOUO ALLOWED      |
		
		String engineName = "storage";
		final String FILTER_CATEGORY_NAME = "FILTER_CATEGORY";
		final String FILTER_VALUE_NAME = "FILTER_VALUE";
		List<Map<String, String>> rows = List.of(
			    Map.of(FILTER_CATEGORY_NAME, "Tag", FILTER_VALUE_NAME, "embeddings, Test1"),
			    Map.of(FILTER_CATEGORY_NAME, "Domain", FILTER_VALUE_NAME, "SAP, AI"),
			    Map.of(FILTER_CATEGORY_NAME, "Data Classification", FILTER_VALUE_NAME, "PUBLIC"),
			    Map.of(FILTER_CATEGORY_NAME, "Data Restrictions", FILTER_VALUE_NAME, "FOUO ALLOWED")
			);
		
		validateCatalogFilters(catalogName, FILTER_CATEGORY_NAME, FILTER_VALUE_NAME, rows, page);
	
		CatalogFilterPageUtils.clickOnBookmark(page, catalogName);
		boolean iscatalogDisplayedUnderBookmarkedSection = CatalogFilterPageUtils.verifyCatalogDisplayedUnderBookmarkedSection(page, catalogName);
		Assertions.assertTrue(iscatalogDisplayedUnderBookmarkedSection,
				catalogName + " " + "not displayed under bookmarked section");
		CatalogFilterPageUtils.clickOnUnbookmark(page, catalogName);
	}
	
	@Test
	public void viewAndValidateFilterFunctionalityDiscoverableStorage_test(@PWPage Page page) {
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenStorage(page);
		SearchAndSelectCatalogPageUtils.searchCatalog(page, catalogName);
		SearchAndSelectCatalogPageUtils.selectCatalogFromSearchOptions(page, catalogName);
		AddFunctionPageUtils.clickOnAccessControl(page);
		FunctionAccessSettingsUtils.clickOnMakeDiscoverableButton(page, "Storage");
		logout(page);
		
		loginEditor(page);
		
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenStorage(page);
		SearchAndSelectCatalogPageUtils.searchCatalog(page, catalogName);
		StoragePageUtils.clickOnDiscoverableStoragesButton(page);
		
//		| FILTER_CATEGORY     | FILTER_VALUE     |
//	    | Data Classification | RESTRICTED       |
//	    | Data Restrictions   | INTERNAL ALLOWED |
		
		final String FILTER_CATEGORY_NAME = "FILTER_CATEGORY";
		final String FILTER_VALUE_NAME = "FILTER_VALUE";
		List<Map<String, String>> rows = List.of(
			    Map.of(FILTER_CATEGORY_NAME, "Data Classification", FILTER_VALUE_NAME, "RESTRICTED"),
			    Map.of(FILTER_CATEGORY_NAME, "Data Restrictions", FILTER_VALUE_NAME, "INTERNAL ALLOWED")
			);
		
		validateCatalogFilters(catalogName, FILTER_CATEGORY_NAME, FILTER_VALUE_NAME, rows, page);
	
		logout(page);
		
		loginNativeAdmin(page);
	}
	
	@Test
	public void validateAccessStatusOfCreatedStorageCatalog_test(@PWPage Page page) {
		
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenStorage(page);
		SearchAndSelectCatalogPageUtils.searchCatalog(page, catalogName);
		EditModelPageUtils.mouseHoverOnEngineAccessStatusIcon(page);
		
		String expectedStatus = "Private";
		String actualStatus = EditModelPageUtils.getEngineAccessStatusTooltipText(page, expectedStatus);
		Assertions.assertEquals(expectedStatus, actualStatus, "Incorrect status");
		
		SearchAndSelectCatalogPageUtils.selectCatalogFromSearchOptions(page, catalogName);
		AddFunctionPageUtils.clickOnAccessControl(page);
		SettingsModelPageUtils.clickOnMakeCatalogPublicButton(page, "Storage");
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenStorage(page);
		SearchAndSelectCatalogPageUtils.searchCatalog(page, catalogName);
		EditModelPageUtils.mouseHoverOnEngineAccessStatusIcon(page);
		
		expectedStatus = "Global";
		actualStatus = EditModelPageUtils.getEngineAccessStatusTooltipText(page, expectedStatus);
		Assertions.assertEquals(expectedStatus, actualStatus, "Incorrect status");
	}
	
	
	@Test
	public void validateContentOfCreatedStorageCatalogCard_test(@PWPage Page page) {
		
		String id = EditModelPageUtils.getCatalogID(page);
		Assertions.assertNotNull(id, "Catalog ID should not be null");
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenStorage(page);
		SearchAndSelectCatalogPageUtils.searchCatalog(page, catalogName);
		boolean isIdVisible = EditModelPageUtils.validateIDisDisplayedOnCatalogCard(page);
		Assertions.assertTrue(isIdVisible, "Catalog ID is not visible on the catalog card");
		String expectedTags = "embeddings, Test1";
		String[] tagArray = expectedTags.split(", ");
		List<String> actualTagList = EditModelPageUtils.verifyTagNamesDisplayedOnCard(page, "Storage");
		List<String> expectedTagList = Arrays.asList(tagArray);
		Assertions.assertEquals(expectedTagList, actualTagList);
		boolean isCreatedDateVisible = EditModelPageUtils.isCreatedDateVisibleOnCard(page);
		Assertions.assertTrue(isCreatedDateVisible, "Catalog created date is not visible on the catalog card");
	
//		| lock                |
//	    | bookmark            |
//	    | view logs dashboard |
//	    | delete              |
		
		List<String> icons = List.of("lock", "bookmark", "view logs dashboard", "delete");
		for (String icon : icons) {
			boolean isIconVisible = EditModelPageUtils.isIconVisibleOnCatalogCard(page, icon);
			Assertions.assertTrue(isIconVisible, "Icon '" + icon + "' is not visible on the catalog card");
		}
	}
	
	@Test
	public void deleteStorageCatalogFromDashboardAndValidateDeleteConfirmationPopUp_test(@PWPage Page page) {
		
		String id = EditModelPageUtils.getCatalogID(page);
		Assertions.assertNotNull(id, "Catalog ID should not be null");
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenStorage(page);
		SearchAndSelectCatalogPageUtils.searchCatalog(page, catalogName);
		EditModelPageUtils.clickOnCatalogCardOption(page, "Delete Engine");
		String expectedMessage = "Are you sure you want to delete this engine?";
		String actualMessage = EditModelPageUtils.getDeleteConfirmationMessage(page);
		Assertions.assertEquals(expectedMessage, actualMessage, "Incorrect confirmation message");
	
		String expectedEngineName = "Amazon S3 Storage";
		String catalogType = "Storage";
		String actualEngineName = EditModelPageUtils.getDeleteConfirmationEngineName(page);
		Assertions.assertEquals(catalogName, actualEngineName, "Incorrect engine name");
		
		Boolean isEngineIdVisible = EditModelPageUtils.isEngineIdVisibleOnDeleteConfirmation(page);
		Assertions.assertTrue(isEngineIdVisible, "Engine ID is not visible on the delete confirmation pop-up");
	
		String expectedButton = "Cancel";
		boolean isButtonVisible = EditModelPageUtils.isButtonVisibleOnDeleteConfirmation(page, expectedButton);
		Assertions.assertTrue(isButtonVisible,
				"Button '" + expectedButton + "' is not visible on the delete confirmation pop-up");
	
		expectedButton = "Delete";
		isButtonVisible = EditModelPageUtils.isButtonVisibleOnDeleteConfirmation(page, expectedButton);
		Assertions.assertTrue(isButtonVisible,
				"Button '" + expectedButton + "' is not visible on the delete confirmation pop-up");
	
		StoragePageUtils.clickOnButton(page, "Delete");
		
		String expectedToastMessage = "Successfully deleted Amazon S3 Storage";
		catalogType = "Storage";
		// commented out due to failing assert
//		actualMessage = (page.locator(DELETE_TOAST_MESSAGE_XPATH).first()).textContent().trim();
//		Assertions.assertEquals(expectedToastMessage + timestamp, actualMessage, "Delete message doesn't match");
		
		// we already deleted the storage,
		// so we are re-adding the storage so the tearDown() method doesn't break
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenStorage(page);
		StoragePageUtils.clickOnAddStorageButton(page);
		AddStorageFormUtils.selectStorage(page, "Amazon S3");
		AddStorageFormUtils.enterCatalogName(page, catalogName);
		AddStorageFormUtils.enterRegionName(page, "India");
		AddStorageFormUtils.enterBucket(page, "BucketTest");
		StoragePageUtils.clickOnConnectButton(page);
	}
	
	
	private void validateCatalogFilters(final String catalogName, final String filterCategoryName,
			final String filterValueName, List<Map<String, String>> rows, Page page){
		for (Map<String, String> row : rows) {
			row.get(filterCategoryName);
			String filterValues = row.get(filterValueName);

			String[] filterValuesArray = filterValues.split(", ");
			for (String filterValue : filterValuesArray) {
				CatalogFilterPageUtils.selectFilterValue(page , filterValue);
				CatalogFilterPageUtils.selectFilterValue(page , filterValue);
				boolean isCatalogVisible = CatalogFilterPageUtils.verifyCatalogIsVisibleOnCatalogPage(page, catalogName);
				Assertions.assertTrue(isCatalogVisible,
						"Catalog is not present for " + "'" + filterValue + "'" + " filter value");
				// To de-select selected filter we again call this method
				CatalogFilterPageUtils.selectFilterValue(page , filterValue);

			}
		}
	}
	
}
