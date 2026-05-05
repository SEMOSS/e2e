package aicore.utils;

import java.util.concurrent.locks.ReentrantLock;

import com.microsoft.playwright.Page;

import aicore.pages.function.FunctionAccessSettingsUtils;
import aicore.pages.home.MainMenuUtils;

public class AbstractFunctionTestBase extends AbstractPlaywrightTestBase {
	
	/* we need to lock the upload capabilities because multiple tests
	* may fail trying to upload/tear down the zip file while another thread 
	* is using the function for a test simultaneously
	* 
	* Now one thread must lock and unlock this capability before another test can proceed.
	* 
	* The lock is shared among all threads.
	* 
	* Try catch to ensure the lock is released and no deadlocks occur
	*/ 
	private static final ReentrantLock FUNCTION_ENGINE_LOCK = new ReentrantLock();

	protected void createFunctionFromZip(Page page) {
		FUNCTION_ENGINE_LOCK.lock();
		try {
			MainMenuUtils.openMainMenu(page);
			MainMenuUtils.clickOnOpenFunction(page);
			AddFunctionPageUtils.deleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_FUNCTION,
					TestResources.WEATHER_FUNC_NAME);
			AddFunctionPageUtils.clickOnAddFunctionButton(page);
			CatalogCreationFromZipUtil.clickOnFileUploadIcon(page);
			FunctionTestUtils.userUploadsFile(page, TestResources.WEATHER_FUNC_ZIP);
			CatalogCreationFromZipUtil.clickOnUploadButton(page, "Upload");
			CatlogAccessPageUtility.getCatalogAndCopyId(page);
			FunctionTestUtils.verifyUserSeesSuccessToastMessage(page, "Successfully Created Function Database");
			FunctionTestUtils.userCanSeeCatalogTitle(page, TestResources.WEATHER_FUNC_NAME);
		} catch (Exception e) {
			// ensure lock is released if setup fails
			FUNCTION_ENGINE_LOCK.unlock();
			throw e;
		}
	}

	protected void deleteZipFunction(Page page) {
		try {
			// delete after test
			MainMenuUtils.openMainMenu(page);
			MainMenuUtils.clickOnOpenFunction(page);
			FunctionTestUtils.userSearchesForAndLocatesFunction(page, TestResources.WEATHER_FUNC_NAME);
			AddFunctionPageUtils.clickOnFunctionNameInCatalog(page, "WeatherFunctionTest", null);
			AddFunctionPageUtils.clickOnAccessControl(page);
			/// following 2 steps are to delete function
			FunctionAccessSettingsUtils.clickOnDeleteButton(page);
			FunctionAccessSettingsUtils.clickOnDeleteConfirmationButton(page);
			/// confirm
			FunctionAccessSettingsUtils.verifyDeleteToastMessage(page, "Successfully deleted Function");
		} finally {
			// release lock so next thread can proceed
			FUNCTION_ENGINE_LOCK.unlock();
		}
	}

	/*
	 * For the infrequent scenario where a test needs to manually delete the zip function 
	 * from the test, this method will proved the caller the ability 
	 * to unlock the reentrant lock after running a block of code.
	 */
	protected void releaseFunctionZipLock(Runnable action) {
		try {
			action.run();
		} finally {
			// release lock so next thread can proceed
			FUNCTION_ENGINE_LOCK.unlock();
		}
	}
}
