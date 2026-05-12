package aicore.unit.storage;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import aicore.hooks.SetupHooks;
import aicore.pages.home.HomePageUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.pages.storage.AddStorageFormUtils;
import aicore.utils.AddCatalogPageBaseUtils;
//import aicore.utils.AddFunctionPageUtils;
import aicore.utils.CatlogAccessPageUtility;
import aicore.utils.CommonUtils;
import aicore.utils.StoragePageUtils;
//import aicore.utils.AbstractE2ETest;

import com.microsoft.playwright.Page;
//import com.microsoft.playwright.Locator;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.annotations.PWPage;
import aicore.utils.TestResourceTrackerHelper;


public class AddLocalFileSystemStorageTests extends AbstractPlaywrightTestBase {

	private static String storageName = "Local File System";
	private static String timestamp = CommonUtils.getTimeStampName();
	private static String catalogName = "Local File System Storage" + timestamp;
	private static String pathPrefix = "local_storage";
	private static String toastMessageAdd = "Successfully added new storage to catalog";
	private static String toastMessageDelete = "Successfully deleted Storage";
	
	@BeforeEach
	void setup(@PWPage Page page) {
		loginNativeAdmin(page);
	}	
	@AfterEach
	void tearDown(@PWPage Page page) {
		logout(page);
	}

	@Test
	public void addLocalFileSystemStorage_test(@PWPage Page page) throws Exception {
		
//		login(page, UserType.NATIVE);
		
		HomePageUtils.navigateToHomePage(page);
//		MainMenuUtils.openMainMenu(SetupHooks.getPage());
		MainMenuUtils.openMainMenu(page);
//		MainMenuUtils.clickOnOpenStorage(SetupHooks.getPage());
		MainMenuUtils.clickOnOpenStorage(page);
		StoragePageUtils.clickOnAddStorageButton(page);
		AddStorageFormUtils.selectStorage(page, storageName);
		
		AddStorageFormUtils.enterCatalogName(page, catalogName);
		
		StoragePageUtils.enterLocalPathPrefix(page, pathPrefix);
		StoragePageUtils.clickOnConnectButton(page);
		
		System.out.println("checkpoint # 1");
		
		String actualMessage = StoragePageUtils.verifyStorageCreatedToastMessage(page, toastMessageAdd);
		
		System.out.println("checkpoint # 2");
		
		String expectedMessage = toastMessageAdd;
		Assertions.assertEquals(actualMessage, expectedMessage, "Storage creation is failed");
		
		CatlogAccessPageUtility.getCatalogAndCopyId(page);
		
		AddCatalogPageBaseUtils.verifyCatalogName(page, catalogName);
				
//		HomePageUtils.navigateToHomePage(page);
////		MainMenuUtils.openMainMenu(SetupHooks.getPage());
//		MainMenuUtils.openMainMenu(page);
////		MainMenuUtils.clickOnOpenStorage(SetupHooks.getPage());
//		MainMenuUtils.clickOnOpenStorage(page);
//		
//		StoragePageUtils.searchStorage(page, catalogName);
//		StoragePageUtils.clickOnCreatedStorage(page, catalogName);
//		AddFunctionPageUtils.clickOnAccessControl(page);
//		StoragePageUtils.clickOnDeleteButton(page);
//		StoragePageUtils.clickOnDeleteConfirmationButton(page);
//		
//		actualMessage = StoragePageUtils.verifyDeleteToastMessage(page);
//		expectedMessage = toastMessageDelete;
//		Assertions.assertEquals(actualMessage, expectedMessage, "Delete Message is not matching with expected");

		// CommonUtils.navigateAndDeleteCatalog() provides concise functionality of the code commented out above
		assertTrue(CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_STORAGE, catalogName));
		
	}
	
	
	
	
	
}
