package aicore.unit.function;

import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.microsoft.playwright.Page;

import aicore.pages.function.FunctionAccessSettingsUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.pages.model.EditModelPageUtils;
import aicore.pages.model.SettingsModelPageUtils;
import aicore.utils.AbstractE2ETest;
import aicore.utils.AbstractFunctionTestBase;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.AddFunctionPageUtils;
import aicore.utils.CatalogCreationFromZipUtil;
import aicore.utils.CatlogAccessPageUtility;
import aicore.utils.CommonUtils;
import aicore.utils.FunctionTestUtils;
import aicore.utils.PWPage;
import aicore.utils.StoragePageUtils;
import aicore.utils.TestResourceTrackerHelper;
import aicore.utils.TestTags;

public class ViewExistingFunctionsOnCatalogPageTests extends AbstractFunctionTestBase {
	private static final Logger logger = LogManager.getLogger(ViewExistingFunctionsOnCatalogPageTests.class);
	
	private final String baseFunctionName = "WeatherFunctionTest";

	@BeforeEach
	void setup(@PWPage Page page) {
		logger.info("BEFORE ALL: creating function");
		loginNativeAdmin(page);
		createFunctionFromZip(page);
	}
	
	private static Stream<Arguments> provideOptionsToFilterFunctionality() {
	    return Stream.of(
	    		Arguments.of("Data Classification", "CONFIDENTIAL, IP"),
	    		Arguments.of("Data Restrictions", "CONFIDENTIAL ALLOWED, IP ALLOWED")
	    		);
	}
	
	@ParameterizedTest
	@MethodSource("provideOptionsToFilterFunctionality")
	void testFiltersFunctionalityMyFunctionsTab(String filterCategoryName, String filterValues, @PWPage Page page) {
		String timestamp = CommonUtils.getTimeStampName();
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenFunction(page); 
		FunctionTestUtils.verifyUserSeesFunctionInCatalog(page, baseFunctionName, timestamp);
		FunctionTestUtils.validateFunctionFilters(page, baseFunctionName, filterCategoryName, filterValues);
		releaseFunctionZipLock(() -> CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_FUNCTION, baseFunctionName));
		logout(page);
	}

	
	private static Stream<Arguments> provideOptionsToDiscoverableFilterFunctionality() {
	    return Stream.of(
	    		Arguments.of("Data Classification", "IP"),
	    		Arguments.of("Data Restrictions", "IP ALLOWED")
	    		);
	}
	
	@ParameterizedTest
	@MethodSource("provideOptionsToDiscoverableFilterFunctionality")
	void testFilterFunctionalityDiscoverableFunctionsTab(String filterCategoryName, String filterValues, @PWPage Page page) {
		String timestamp = CommonUtils.getTimeStampName();
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenFunction(page); 
		FunctionTestUtils.verifyUserSeesFunctionInCatalog(page, baseFunctionName, timestamp);
		AddFunctionPageUtils.clickOnFunctionNameInCatalog(page, baseFunctionName, timestamp);
		AddFunctionPageUtils.clickOnAccessControl(page);
		FunctionAccessSettingsUtils.clickOnMakeDiscoverableButton(page, TestResourceTrackerHelper.CATALOG_TYPE_FUNCTION);
		logout(page);
		loginEditor(page);
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenFunction(page);
		AddFunctionPageUtils.clickOnDiscoverableFunctionsButton(page);
		FunctionTestUtils.verifyUserSeesFunctionInCatalog(page, baseFunctionName, timestamp);
		FunctionTestUtils.validateFunctionFilters(page, baseFunctionName, filterCategoryName, filterValues);
		logout(page);
		loginNativeAdmin(page);
		releaseFunctionZipLock(() -> CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_FUNCTION, baseFunctionName));
		logout(page);
	}

	@Test
	void testValidateAccessStatusOfCreatedFunction(@PWPage Page page) {
		String timestamp = CommonUtils.getTimeStampName();
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenFunction(page); 
		AddFunctionPageUtils.searchFunctionCatalog(page, baseFunctionName);
		FunctionTestUtils.verifyUserSeesFunctionInCatalog(page, baseFunctionName, timestamp);
		EditModelPageUtils.mouseHoverOnEngineAccessStatusIcon(page);
		FunctionTestUtils.userSeesFunctionStatusOnTooltip(page, "Private");
		AddFunctionPageUtils.selectFunctionFromSearchOptions(page, baseFunctionName);
		AddFunctionPageUtils.clickOnAccessControl(page);
		FunctionAccessSettingsUtils.clickOnMakeFunctionPublicButton(page);
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenFunction(page); 
		AddFunctionPageUtils.searchFunctionCatalog(page, baseFunctionName);
		FunctionTestUtils.verifyUserSeesFunctionInCatalog(page, baseFunctionName, timestamp);
		EditModelPageUtils.mouseHoverOnEngineAccessStatusIcon(page);
		FunctionTestUtils.userSeesFunctionStatusOnTooltip(page, "Global");
		releaseFunctionZipLock(() -> CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_FUNCTION, baseFunctionName));
		logout(page);
	}
	
	private static Stream<Arguments> provideContentToFunctionCatalogCard() {
	    return Stream.of(
	    		Arguments.of("lock, bookmark, view logs dashboard, delete")
	    		);
	}

	@ParameterizedTest
	@MethodSource("provideContentToFunctionCatalogCard")
	void testValidateContentOfCreatedFunctionCatalogCard(String iconStr, @PWPage Page page) {
		String timestamp = CommonUtils.getTimeStampName();
		FunctionTestUtils.userGetsCatalogID(page);
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenFunction(page); 
		AddFunctionPageUtils.searchFunctionCatalog(page, baseFunctionName);
		FunctionTestUtils.verifyUserSeesFunctionInCatalog(page, baseFunctionName, timestamp);
		FunctionTestUtils.userShouldSeeCatalogID(page);
		// This element on the Function card seems to have been removed
//		FunctionTestUtils.verifyUserSeesTagsOnCard(page, "embeddings, Test1", TestResourceTrackerHelper.CATALOG_TYPE_FUNCTION);
		FunctionTestUtils.verifyUserSeesCreatedDateOnCatalogCard(page);
		FunctionTestUtils.verifyUsersSeesIconsOnContentCard(page, iconStr);
		releaseFunctionZipLock(() -> CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_FUNCTION, baseFunctionName));
		logout(page);
	}

	@Test
	void testDeleteFunctionFromDashboardAndValidateDeletionPopup(@PWPage Page page) {
		releaseFunctionZipLock(() -> {
			String timestamp = CommonUtils.getTimeStampName();
			FunctionTestUtils.userGetsCatalogID(page);
			MainMenuUtils.openMainMenu(page);
			MainMenuUtils.clickOnOpenFunction(page);
			AddFunctionPageUtils.searchFunctionCatalog(page, baseFunctionName);
			FunctionTestUtils.verifyUserSeesFunctionInCatalog(page, baseFunctionName, timestamp);
			EditModelPageUtils.clickOnCatalogCardOption(page, "Delete Engine");
			FunctionTestUtils.verifyUserSeesDeleteConfirmationPopupWithMessage(page,
					"Are you sure you want to delete this engine?");
			FunctionTestUtils.verifyFunctionNameIsOnDeletionConfirmationPopup(page, baseFunctionName);
			FunctionTestUtils.verifyUsersSeesEngineIdOnDeleteConfirmationPopup(page);
			FunctionTestUtils.verifUserSeesButtonOnDeleteConfirmationPopup(page, "Cancel");
			FunctionTestUtils.verifUserSeesButtonOnDeleteConfirmationPopup(page, "Delete");
			StoragePageUtils.clickOnButton(page, "Delete");
			FunctionTestUtils.verifyUserSeesDeletedToastMessage(page, "Successfully deleted " + baseFunctionName);
			logout(page);
		});
	}
}
