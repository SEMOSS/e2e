package aicore.unit.function;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;

import aicore.pages.ViewFunctionPage;
import aicore.pages.function.GeneralFunctionPage;
import aicore.pages.home.MainMenuUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.AddFunctionPageUtils;
import aicore.utils.CatalogCreationFromZipUtil;
import aicore.utils.CatlogAccessPageUtility;
import aicore.utils.CommonUtils;
import aicore.utils.FunctionTestUtils;
import aicore.utils.TestResourceTrackerHelper;
import aicore.utils.TestResources;
import aicore.utils.TestTags;
import aicore.utils.annotations.PWPage;
import aicore.utils.annotations.ResourceUploadLock;

public class ViewFunctionDetailsTests extends AbstractPlaywrightTestBase {
	private static final Logger logger = LogManager.getLogger(ViewFunctionDetailsTests.class);
	
	@BeforeEach
	void setup(@PWPage Page page) {
		loginNativeAdmin(page);
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenFunction(page);
		GeneralFunctionPage.deleteFunctionIfExists(page, TestResources.WEATHER_FUNC_NAME);
//		AddFunctionPageUtils.deleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_FUNCTION,
//				TestResources.WEATHER_FUNC_NAME);
		AddFunctionPageUtils.clickOnAddFunctionButton(page);
		CatalogCreationFromZipUtil.clickOnFileUploadIcon(page);
		FunctionTestUtils.userUploadsFile(page, TestResources.WEATHER_FUNC_ZIP);
//		acquireFunctionZipLock(()->{
			CatalogCreationFromZipUtil.clickOnUploadButton(page, "Upload");
//		});	
		CatlogAccessPageUtility.getCatalogAndCopyId(page);
		FunctionTestUtils.verifyUserSeesSuccessToastMessage(page, "Successfully Created Function Database");
		FunctionTestUtils.userCanSeeCatalogTitle(page, TestResources.WEATHER_FUNC_NAME);
	}	
	@AfterEach
	void tearDown(@PWPage Page page) {
		logout(page);
	}
	
	@Test
	@Tag(TestTags.BROKEN)
	@ResourceUploadLock(TestResources.WEATHER_FUNC_ZIP)
	void testViewOverviewTabDetails(@PWPage Page page) {
//		releaseFunctionZipLock(() -> 
		CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_FUNCTION, TestResources.WEATHER_FUNC_NAME);
//		); 
		fail("Needs to be implemented");
	/*
	 * Below scenario commented because the bug - https://github.com/SEMOSS/community/issues/587 
	  
	 
    #Scenario: View overview details in "Overview" tab for selected Function
    #Given User can see the Catalog title as 'WeatherFunctionTest'
    #Then User can see 'WeatherFunctionTest' as function name
    #And User can see function ID
    #And User can see 'Please use the Edit button to provide a description for this Function. A description will help others find the Function and understand how to use it. To include more details associated with the Function, edit the markdown located in the Overview section.' as function description
    #And User can see 'N/A' as Date last updated
    #And User can see ' No Markdown available' Markup with Function overview in Overview tab at the bottom of the page.
    #And User clicks on Access Control Tab
    #And User clicks on Add Member button
    #And User adds one user and assigns them as 'read'
    #And User logs out from the application
    #Then User login as "read"
    #And User opens Main Menu
    #And User clicks on Open Function
    #And User clicks on the function name 'WeatherFunctionTest' in the function catalog
    #Then User sees 'Change Access' button
    #And User logs out from the application
    #Then User login as "native"
    #And User opens Main Menu
    #And User clicks on Open Function
    #Then User sees the function name 'WeatherFunctionTest' in the function catalog
    #Then User clicks on the function name 'WeatherFunctionTest' in the function catalog
	  
	 
	 * TODO: This test needs to be created as JUnit once the issue has been resolved
	 */
	}
	
	@Test
	@ResourceUploadLock(TestResources.WEATHER_FUNC_ZIP)
	void testViewUsageDetailsInUsageTabForSelectedFunction(@PWPage Page page) {
		FunctionTestUtils.userCanSeeCatalogTitle(page, TestResources.WEATHER_FUNC_NAME);
		ViewFunctionPage viewFunction = new ViewFunctionPage(page);
		viewFunction.clickUsageTab("Usage");
		assertTrue(viewFunction.verifyUsageInstructionsSection("How to use in Pixel"));
		assertTrue(viewFunction.verifyUsageInstructionsSection("How to use in Python"));
		assertTrue(viewFunction.verifyUsageInstructionsSection("How to use in Java"));
//		releaseFunctionZipLock(() -> 
		CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_FUNCTION, TestResources.WEATHER_FUNC_NAME);
		//);
	}
	
}
