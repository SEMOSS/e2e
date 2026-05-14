package aicore.unit.storage;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;

import aicore.pages.home.MainMenuUtils;
import aicore.pages.storage.AddStorageFormUtils;
import aicore.utils.page.app.AppPageUtils;

import aicore.utils.annotations.PWPage;

import com.microsoft.playwright.Page;
//import com.microsoft.playwright.Locator;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.CommonUtils;
import aicore.utils.TestResourceTrackerHelper;
import aicore.utils.annotations.PWPage;

public class StoragePageFiltersTests extends AbstractPlaywrightTestBase {
	
//    Given User created '1' storage with the 'Amazon S3' storage, catalog name 'Ascending Amazon S3 Storage', Region 'India', Bucket 'BucketTest'
	private static String storageCount = "1";
	private static String storageType = "Amazon S3";

	String ascCatalogName = "Ascending Amazon S3 Storage";
	String descCatalogName = "Descending Amazon S3 Storage";
	
	private static String region = "India";
	private static String bucket = "BucketTest";	
	
	@BeforeEach
	void setup(@PWPage Page page) {
		loginNativeAdmin(page);
	}	
	@AfterEach
	void tearDown(@PWPage Page page) {
		logout(page);
	}
	
	@Test
	public void storagePageFiltersAscending_test(@PWPage Page page) {
		
		for (int i = 1; i <= Integer.parseInt(storageCount); i++) {
			String fullCatalogName = ascCatalogName + System.currentTimeMillis();
			AddStorageFormUtils.createStorage(page, storageType, fullCatalogName, region, bucket);
				
			MainMenuUtils.openMainMenu(page);
			MainMenuUtils.clickOnOpenStorage(page);
			AppPageUtils.clickOnFilterButton(page, "Ascending");
				
			boolean isSortedInAscendingOrder = AppPageUtils.verifySortedInAscendingOrder(page);
			Assertions.assertTrue(isSortedInAscendingOrder, fullCatalogName + " are not sorted in ascending order");

			AppPageUtils.selectSortByOption(page, "Date Created");
			AppPageUtils.clickOnFilterButton(page, "Ascending");
				 
			boolean isSortedByDateCreatedAsc = AppPageUtils.verifySortedByDateCreated(page, true);
			Assertions.assertTrue(isSortedByDateCreatedAsc, fullCatalogName + " are not sorted by date created in ascending order");

			assertTrue(CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_STORAGE, fullCatalogName));
		}
	}
	
	@Test
	public void storagePageFiltersDescending_test(@PWPage Page page) {
		
		for (int i = 1; i <= Integer.parseInt(storageCount); i++) {
			String fullCatalogName = descCatalogName + System.currentTimeMillis();
			AddStorageFormUtils.createStorage(page, storageType, fullCatalogName, region, bucket);
				
			MainMenuUtils.openMainMenu(page);
			MainMenuUtils.clickOnOpenStorage(page);
			AppPageUtils.clickOnFilterButton(page, "Descending");
				
			boolean isSortedInDescendingOrder = AppPageUtils.verifySortedInDescendingOrder(page);
			Assertions.assertTrue(isSortedInDescendingOrder, fullCatalogName + " are not sorted in descending order");

			AppPageUtils.selectSortByOption(page, "Date Created");
			AppPageUtils.clickOnFilterButton(page, "Descending");
				 
			boolean isSortedByDateCreatedDesc = AppPageUtils.verifySortedByDateCreated(page, false);
			Assertions.assertTrue(isSortedByDateCreatedDesc, fullCatalogName + " are not sorted by date created in descending order");

			assertTrue(CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_STORAGE, fullCatalogName));
		}
	}
}
