package aicore.app.function;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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

public class AddFunctionFromZipTests extends AbstractE2ETest {
	private static final Logger logger = LogManager.getLogger(AddFunctionFromZipTests.class);
	
	@BeforeEach
	void createFunctionUsingZip() {
		logger.info("BEFORE ALL: creating function");
		login(page, UserType.NATIVE); //TODO find a way to only login once at the beginning of the set of tests

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
		login(page, UserType.NATIVE); //TODO find a way to only login once at the beginning of the set of tests
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenFunction(page); 

		// if there are multiple functions, ours may be not visible without scrolling or searching
		// this will let us filter out the list of functions so we can 'see' ours
		FunctionTestUtils.userSearchesForAndLocatesFunction(page, "WeatherFunctionTest");
		AddFunctionPageUtils.clickOnFunctionNameInCatalog(page, "WeatherFunctionTest", null);// no timestamp
		SettingsModelPageUtils.clickOnAccessControl(page);
		SettingsModelPageUtils.clickOnAddMembersButton(page);
		SettingsModelPageUtils.addMember(page, "Editor", GenericSetupUtils.useDocker());
		logout(page);
		login(page, UserType.EDITOR);
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenFunction(page); 
		AddFunctionPageUtils.searchFunctionCatalog(page, "WeatherFunctionTest");
		AddFunctionPageUtils.selectFunctionFromSearchOptions(page, "WeatherFunctionTest");
		/// TODO we can to re-organize these non-page specific actions into a single source to be extended by the different pages
		new EmbedDocumentPage(page).clickOnAccessControlButton();
		List<String> popupOptions = List.of("Author", "Editor", "Read-Only", "Comment Box", "Cancel Button", "Request Button");
		FunctionTestUtils.verifyPopupWithSelectOptions(page, "Change Access", popupOptions);
		StoragePageUtils.clickOnCancelButton(page);
		logout(page);
	}
	
	@Test
	void testChangeAccessRequest() throws InterruptedException {
		logger.info("TESTING: testChangeAccessRequest");
		login(page, UserType.NATIVE); //TODO find a way to only login once at the beginning of the set of tests
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenFunction(page); 

		// if there are multiple functions, ours may be not visible without scrolling or searching
		// this will let us filter out the list of functions so we can 'see' ours
		FunctionTestUtils.userSearchesForAndLocatesFunction(page, "WeatherFunctionTest");
		AddFunctionPageUtils.clickOnFunctionNameInCatalog(page, "WeatherFunctionTest", null);// no timestamp
		SettingsModelPageUtils.clickOnAccessControl(page);
		SettingsModelPageUtils.clickOnAddMembersButton(page);
		SettingsModelPageUtils.addMember(page, "Editor", GenericSetupUtils.useDocker());
		logout(page);
		login(page, UserType.EDITOR);
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenFunction(page); 
		AddFunctionPageUtils.searchFunctionCatalog(page, "WeatherFunctionTest");
		AddFunctionPageUtils.selectFunctionFromSearchOptions(page, "WeatherFunctionTest");
		/// TODO we can to re-organize these non-page specific actions into a single source to be extended by the different pages
		new EmbedDocumentPage(page).clickOnAccessControlButton();
		RequestAccessPopupUtils.selectAccessType(page, "author");
		RequestAccessPopupUtils.enterComment(SetupHooks.getPage(), "Access Request");
		RequestAccessPopupUtils.clickOnRequestButton(page);
		FunctionTestUtils.verifyUserSeesSuccessfulRequestToastMessage(page, "Successfully requested access to engine");
		logout(page);
	}
	
	@AfterEach
	void deleteFunction() {
		logger.info("AFTER ALL: Deleting function");
		login(page, UserType.NATIVE); //TODO find a way to only login once at the beginning of the set of tests
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
