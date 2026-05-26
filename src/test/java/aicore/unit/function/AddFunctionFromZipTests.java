package aicore.unit.function;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;

import aicore.pages.function.FunctionAccessSettingsUtils;
import aicore.pages.function.GeneralFunctionPage;
import aicore.pages.home.MainMenuUtils;
import aicore.pages.model.SettingsModelPageUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.AddFunctionPageUtils;
import aicore.utils.CatalogCreationFromZipUtil;
import aicore.utils.CatlogAccessPageUtility;
import aicore.utils.CommonUtils;
import aicore.utils.FunctionTestUtils;
import aicore.utils.RequestAccessPopupUtils;
import aicore.utils.StoragePageUtils;
import aicore.utils.TestResourceTrackerHelper;
import aicore.utils.TestResources;
import aicore.utils.annotations.PWPage;
import aicore.utils.annotations.ResourceUploadLock;


public class AddFunctionFromZipTests extends AbstractPlaywrightTestBase {
	private static final Logger logger = LogManager.getLogger(AddFunctionFromZipTests.class);
	
	@BeforeEach
	void setup(@PWPage Page page) {
		logger.info("BEFORE ALL: creating function");
		loginNativeAdmin(page);
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenFunction(page);
		GeneralFunctionPage.deleteFunctionIfExists(page, TestResources.WEATHER_FUNC_NAME);
		AddFunctionPageUtils.clickOnAddFunctionButton(page);
		CatalogCreationFromZipUtil.clickOnFileUploadIcon(page);
		FunctionTestUtils.userUploadsFile(page, TestResources.WEATHER_FUNC_ZIP);
		CatalogCreationFromZipUtil.clickOnUploadButton(page, "Upload");
		CatlogAccessPageUtility.getCatalogAndCopyId(page);
		FunctionTestUtils.verifyUserSeesSuccessToastMessage(page, "Successfully Created Function Database");
		FunctionTestUtils.userCanSeeCatalogTitle(page, TestResources.WEATHER_FUNC_NAME);
	}
	
	@AfterEach
	void tearDown(@PWPage Page page) {
		logger.info("AFTER ALL: Deleting function");
		CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_FUNCTION, TestResources.WEATHER_FUNC_NAME);
		logout(page);
	}
	
	@Test
	@ResourceUploadLock(TestResources.WEATHER_FUNC_ZIP)
	void testValidateChangeAccessPopup(@PWPage Page page) throws InterruptedException {
		logger.info("TESTING: testValidateChangeAccessPopup");
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenFunction(page); 
		// if there are multiple functions, ours may be not visible without scrolling or searching
		// this will let us filter out the list of functions so we can 'see' ours
		GeneralFunctionPage.searchForFunction(page, TestResources.WEATHER_FUNC_NAME);
		GeneralFunctionPage.clickOnFunction(page, TestResources.WEATHER_FUNC_NAME);
		SettingsModelPageUtils.clickOnAccessControl(page);
		FunctionAccessSettingsUtils.clickOnAddMembersForFunction(page);
		FunctionAccessSettingsUtils.searchAndAddMemberForFunction(page, "Editor");
		logout(page);
		loginEditor(page);
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenFunction(page); 
		AddFunctionPageUtils.searchFunctionCatalog(page, "WeatherFunctionTest");
		AddFunctionPageUtils.selectFunctionFromSearchOptions(page, "WeatherFunctionTest");
		FunctionAccessSettingsUtils.clickOnChangeAccessTab(page);
		List<String> popupOptions = List.of("Author", "Editor", "Read-Only", "Comment Box", "Cancel Button", "Request Button");
		FunctionTestUtils.verifyPopupWithSelectOptions(page, "Change Access", popupOptions);
		StoragePageUtils.clickOnCancelButton(page);
		logout(page);
		// log back in as admin for subsequent tests
		loginNativeAdmin(page);
	}
	
	@Test
	@ResourceUploadLock(TestResources.WEATHER_FUNC_ZIP)
	void testChangeAccessRequest(@PWPage Page page) throws InterruptedException {
		logger.info("TESTING: testChangeAccessRequest");
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenFunction(page); 

		// if there are multiple functions, ours may be not visible without scrolling or searching
		// this will let us filter out the list of functions so we can 'see' ours
		GeneralFunctionPage.searchForFunction(page, TestResources.WEATHER_FUNC_NAME);
		GeneralFunctionPage.clickOnFunction(page, TestResources.WEATHER_FUNC_NAME);
		SettingsModelPageUtils.clickOnAccessControl(page);
		FunctionAccessSettingsUtils.clickOnAddMembersForFunction(page);
		FunctionAccessSettingsUtils.searchAndAddMemberForFunction(page, "Editor");
		logout(page);
		loginEditor(page);
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenFunction(page); 
		AddFunctionPageUtils.searchFunctionCatalog(page, "WeatherFunctionTest");
		AddFunctionPageUtils.selectFunctionFromSearchOptions(page, "WeatherFunctionTest");
		FunctionAccessSettingsUtils.clickOnChangeAccessTab(page);
		RequestAccessPopupUtils.selectAccessType(page, "author");
		RequestAccessPopupUtils.enterComment(page, "Access Request");
		RequestAccessPopupUtils.clickOnRequestButton(page);
		FunctionTestUtils.verifyUserSeesSuccessfulRequestToastMessage(page, "Successfully requested access to engine");
		logout(page);
		loginNativeAdmin(page);
	}
}
