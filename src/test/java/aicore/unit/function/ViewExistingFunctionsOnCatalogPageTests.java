package aicore.unit.function;

import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import aicore.pages.function.FunctionAccessSettingsUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.pages.model.EditModelPageUtils;
import aicore.pages.model.SettingsModelPageUtils;
import aicore.utils.AbstractE2ETest;
import aicore.utils.AddFunctionPageUtils;
import aicore.utils.CatalogCreationFromZipUtil;
import aicore.utils.CatlogAccessPageUtility;
import aicore.utils.CommonUtils;
import aicore.utils.FunctionTestUtils;
import aicore.utils.StoragePageUtils;
import aicore.utils.TestResourceTrackerHelper;
import aicore.utils.TestTags;

public class ViewExistingFunctionsOnCatalogPageTests extends AbstractE2ETest {
	private static final Logger logger = LogManager.getLogger(ViewExistingFunctionsOnCatalogPageTests.class);
	
	@BeforeAll
	void setup() {
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
	
	private static Stream<Arguments> provideOptionsToFilterFunctionality() {
	    return Stream.of(
	    		Arguments.of("Data Classification", "CONFIDENTIAL", "IP"),
	    		Arguments.of("Data Restrictions", "CONFIDENTIAL ALLOWED", "IP ALLOWED")
	    		);
	}
	
	@ParameterizedTest
	@MethodSource("provideOptionsToFilterFunctionality")
	void testFiltersFunctionalityMyFunctionsTab(String filterCategoryName, String filterValues) {
		String timestamp = CommonUtils.getTimeStampName();
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenFunction(page); 
		FunctionTestUtils.verifyUserSeesFunctionInCatalog(page, "WeatherFunctionTest", timestamp);
		FunctionTestUtils.validateFunctionFilters(page, "WeatherFunctionTest", filterCategoryName, filterValues);
		CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_FUNCTION, "WeatherFunctionTest");
	}

	
	private static Stream<Arguments> provideOptionsToDiscoverableFilterFunctionality() {
	    return Stream.of(
	    		Arguments.of("Data Classification", "IP"),
	    		Arguments.of("Data Restrictions", "IP ALLOWED")
	    		);
	}
	
	@ParameterizedTest
	@MethodSource("provideOptionsToDiscoverableFilterFunctionality")
	void testFilterFunctionalityDiscoverableFunctionsTab(String filterCategoryName, String filterValues) {
		String timestamp = CommonUtils.getTimeStampName();
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenFunction(page); 
		FunctionTestUtils.verifyUserSeesFunctionInCatalog(page, "WeatherFunctionTest", timestamp);
		AddFunctionPageUtils.clickOnFunctionNameInCatalog(page, "WeatherFunctionTest", timestamp);
		AddFunctionPageUtils.clickOnAccessControl(page);
		FunctionAccessSettingsUtils.clickOnMakeDiscoverableButton(page, TestResourceTrackerHelper.CATALOG_TYPE_FUNCTION);
		logout(page);
		login(page, UserType.EDITOR);
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenFunction(page);
		AddFunctionPageUtils.clickOnDiscoverableFunctionsButton(page);
		FunctionTestUtils.verifyUserSeesFunctionInCatalog(page, "WeatherFunctionTest", timestamp);
		FunctionTestUtils.validateFunctionFilters(page, "WeatherFunctionTest", filterCategoryName, filterValues);
		logout(page);
		login(page, UserType.NATIVE);
		CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_FUNCTION, "WeatherFunctionTest");
	}

	@Test
	void testValidateAccessStatusOfCreatedFunction() {
		String timestamp = CommonUtils.getTimeStampName();
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenFunction(page); 
		AddFunctionPageUtils.searchFunctionCatalog(page, "WeatherFunctionTest");
		FunctionTestUtils.verifyUserSeesFunctionInCatalog(page, "WeatherFunctionTest", timestamp);
		EditModelPageUtils.mouseHoverOnEngineAccessStatusIcon(page);
		FunctionTestUtils.userSeesFunctionStatusOnTooltip(page, "Private");
		AddFunctionPageUtils.selectFunctionFromSearchOptions(page, "WeatherFunctionTest");
		AddFunctionPageUtils.clickOnAccessControl(page);
		FunctionAccessSettingsUtils.clickOnMakeFunctionPublicButton(page);
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenFunction(page); 
		AddFunctionPageUtils.searchFunctionCatalog(page, "WeatherFunctionTest");
		FunctionTestUtils.verifyUserSeesFunctionInCatalog(page, "WeatherFunctionTest", timestamp);
		EditModelPageUtils.mouseHoverOnEngineAccessStatusIcon(page);
		FunctionTestUtils.userSeesFunctionStatusOnTooltip(page, "Global");
		CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_FUNCTION, "WeatherFunctionTest");
	}
	
	private static Stream<Arguments> provideContentToFunctionCatalogCard() {
	    return Stream.of(
	    		Arguments.of("lock", "bookmark", "view logs dashboard", "delete")
	    		);
	}

	@ParameterizedTest
	@MethodSource("provideContentToFunctionCatalogCard")
	void testValidateContentOfCreatedFunctionCatalogCard(String iconStr) {
		String timestamp = CommonUtils.getTimeStampName();
		FunctionTestUtils.userGetsCatalogID(page);
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenFunction(page); 
		AddFunctionPageUtils.searchFunctionCatalog(page, "WeatherFunctionTest");
		FunctionTestUtils.verifyUserSeesFunctionInCatalog(page, "WeatherFunctionTest", timestamp);
		FunctionTestUtils.userShouldSeeCatalogID(page);
		// This element on the Function card seems to have been removed
//		FunctionTestUtils.verifyUserSeesTagsOnCard(page, "embeddings, Test1", TestResourceTrackerHelper.CATALOG_TYPE_FUNCTION);
		FunctionTestUtils.verifyUserSeesCreatedDateOnCatalogCard(page);
		FunctionTestUtils.verifyUsersSeesIconsOnContentCard(page, iconStr);
		CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_FUNCTION, "WeatherFunctionTest");
	}

	@Test
	void testDeleteFunctionFromDashboardAndValidateDeletionPopup() {
		String timestamp = CommonUtils.getTimeStampName();
		FunctionTestUtils.userGetsCatalogID(page);
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenFunction(page); 
		AddFunctionPageUtils.searchFunctionCatalog(page, "WeatherFunctionTest");
		FunctionTestUtils.verifyUserSeesFunctionInCatalog(page, "WeatherFunctionTest", timestamp);
		EditModelPageUtils.clickOnCatalogCardOption(page, "Delete Engine");
		FunctionTestUtils.verifyUserSeesDeleteConfirmationPopupWithMessage(page, "Are you sure you want to delete this engine?");
		FunctionTestUtils.verifyFunctionNameIsOnDeletionConfirmationPopup(page, "WeatherFunctionTest");
		FunctionTestUtils.verifyUsersSeesEngineIdOnDeleteConfirmationPopup(page); 
		FunctionTestUtils.verifUserSeesButtonOnDeleteConfirmationPopup(page, "Cancel");
		FunctionTestUtils.verifUserSeesButtonOnDeleteConfirmationPopup(page, "Delete");
		StoragePageUtils.clickOnButton(page, "Delete");
		FunctionTestUtils.verifyUserSeesDeletedToastMessage(page, "Successfully deleted WeatherFunctionTest");
	}
}
