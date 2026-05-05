package aicore.unit.function;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;

import aicore.pages.function.FunctionAccessSettingsUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.pages.model.SettingsModelPageUtils;
import aicore.utils.AbstractE2ETest;
import aicore.utils.AbstractFunctionTestBase;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.AddFunctionPageUtils;
import aicore.utils.CatalogCreationFromZipUtil;
import aicore.utils.CatlogAccessPageUtility;
import aicore.utils.FunctionTestUtils;
import aicore.utils.PWPage;
import aicore.utils.RequestAccessPopupUtils;
import aicore.utils.StoragePageUtils;
import aicore.utils.TestResourceTrackerHelper;


public class AddFunctionFromZipTests extends AbstractFunctionTestBase{
	private static final Logger logger = LogManager.getLogger(AddFunctionFromZipTests.class);
	
	@BeforeEach
	void setup(@PWPage Page page) {
		logger.info("BEFORE ALL: creating function");
		loginNativeAdmin(page);
		createFunctionFromZip(page);
	}
	
	@AfterEach
	void tearDown(@PWPage Page page) {
		logger.info("AFTER ALL: Deleting function");
		deleteZipFunction(page);
		logout(page);
	}
	
	@Test
	void testValidateChangeAccessPopup(@PWPage Page page) throws InterruptedException {
		logger.info("TESTING: testValidateChangeAccessPopup");
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenFunction(page); 

		// if there are multiple functions, ours may be not visible without scrolling or searching
		// this will let us filter out the list of functions so we can 'see' ours
		FunctionTestUtils.userSearchesForAndLocatesFunction(page, "WeatherFunctionTest");
		AddFunctionPageUtils.clickOnFunctionNameInCatalog(page, "WeatherFunctionTest", null);// no timestamp
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
	void testChangeAccessRequest(@PWPage Page page) throws InterruptedException {
		logger.info("TESTING: testChangeAccessRequest");
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenFunction(page); 

		// if there are multiple functions, ours may be not visible without scrolling or searching
		// this will let us filter out the list of functions so we can 'see' ours
		FunctionTestUtils.userSearchesForAndLocatesFunction(page, "WeatherFunctionTest");
		AddFunctionPageUtils.clickOnFunctionNameInCatalog(page, "WeatherFunctionTest", null);// no timestamp
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
