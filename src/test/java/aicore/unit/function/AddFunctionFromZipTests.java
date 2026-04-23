package aicore.unit.function;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import aicore.base.GenericSetupUtils;
import aicore.hooks.SetupHooks;
import aicore.pages.EmbedDocumentPage;
import aicore.pages.function.FunctionAccessSettingsUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.pages.model.SettingsModelPageUtils;
import aicore.utils.AbstractE2ETest;
import aicore.utils.AddFunctionPageUtils;
import aicore.utils.CatalogCreationFromZipUtil;
import aicore.utils.CatlogAccessPageUtility;
import aicore.utils.FunctionTestUtils;
import aicore.utils.RequestAccessPopupUtils;
import aicore.utils.StoragePageUtils;
import aicore.utils.TestResourceTrackerHelper;
import aicore.utils.TestTags;
import aicore.utils.AbstractE2ETest.UserType;

public class AddFunctionFromZipTests extends AbstractE2ETest {
	private static final Logger logger = LogManager.getLogger(AddFunctionFromZipTests.class);
	
	@BeforeAll
	static void setup() {
		login(page, UserType.NATIVE);
	}
	
	@BeforeEach
	void createFunctionUsingZip() {
		logger.info("BEFORE ALL: creating function");
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenFunction(page); 
		// this checks & removes existing function that may collide with name
		AddFunctionPageUtils.deleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_FUNCTION, "WeatherFunctionTest");
		AddFunctionPageUtils.clickOnAddFunctionButton(page);
		CatalogCreationFromZipUtil.clickOnFileUploadIcon(page);
		FunctionTestUtils.userUploadsFile(page, "Function/weatherFunctionTest.zip");
		CatalogCreationFromZipUtil.clickOnUploadButton(page, "Upload");
		CatlogAccessPageUtility.getCatalogAndCopyId(page);
		FunctionTestUtils.verifyUserSeesSuccessToastMessage(page, "Successfully Created Function Database");
		FunctionTestUtils.userCanSeeCatalogTitle(page, "WeatherFunctionTest");
	}
	
	@Test
	void testValidateChangeAccessPopup() throws InterruptedException {
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
		login(page, UserType.EDITOR);
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
		login(page, UserType.NATIVE);
	}
	
	@Test
	void testChangeAccessRequest() throws InterruptedException {
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
		login(page, UserType.EDITOR);
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenFunction(page); 
		AddFunctionPageUtils.searchFunctionCatalog(page, "WeatherFunctionTest");
		AddFunctionPageUtils.selectFunctionFromSearchOptions(page, "WeatherFunctionTest");
		FunctionAccessSettingsUtils.clickOnChangeAccessTab(page);
		RequestAccessPopupUtils.selectAccessType(page, "author");
		RequestAccessPopupUtils.enterComment(SetupHooks.getPage(), "Access Request");
		RequestAccessPopupUtils.clickOnRequestButton(page);
		FunctionTestUtils.verifyUserSeesSuccessfulRequestToastMessage(page, "Successfully requested access to engine");
		logout(page);
		login(page, UserType.NATIVE);
	}
	
	@AfterEach
	void deleteFunction() {
		logger.info("AFTER ALL: Deleting function");
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenFunction(page); 
		FunctionTestUtils.userSearchesForAndLocatesFunction(page, "WeatherFunctionTest");
		AddFunctionPageUtils.clickOnFunctionNameInCatalog(page, "WeatherFunctionTest", null);
		AddFunctionPageUtils.clickOnAccessControl(page);
		/// following 2 steps are to delete function
		FunctionAccessSettingsUtils.clickOnDeleteButton(page);
		FunctionAccessSettingsUtils.clickOnDeleteConfirmationButton(page);
		/// confirm
		FunctionAccessSettingsUtils.verifyDeleteToastMessage(page, "Successfully deleted Function");
	}
}
