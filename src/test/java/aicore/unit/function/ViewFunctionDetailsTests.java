package aicore.unit.function;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;

import aicore.pages.ViewFunctionPage;
import aicore.pages.function.GeneralFunctionPage;
import aicore.pages.home.MainMenuUtils;
import aicore.pages.model.EditModelPageUtils;
import aicore.pages.model.SettingsModelPageUtils;
import aicore.utils.AICorePageUtils;
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
import aicore.utils.page.model.ModelPageUtils;

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
	void testViewOverviewTabDetails(@PWPage Page page) throws InterruptedException {
		String timestamp = CommonUtils.getTimeStampName();
		FunctionTestUtils.userCanSeeCatalogTitle(page, "WeatherFunctionTest");
		AddFunctionPageUtils.clickOnAccessControl(page);
		SettingsModelPageUtils.clickOnAddMembersButton(page);
		SettingsModelPageUtils.addMember(page, "read", false);
		logout(page);
		loginReadOnly(page);
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenFunction(page);
		AddFunctionPageUtils.clickOnFunctionNameInCatalog(page, "WeatherFunctionTest", timestamp);
		AddFunctionPageUtils.verifyChangeAccessButton(page, "Change Access");
		logout(page);
		loginNativeAdmin(page);
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenFunction(page);
		AddFunctionPageUtils.verifyFunctionNameInCatalog(page, "WeatherFunctionTest", timestamp);
		AddFunctionPageUtils.clickOnFunctionNameInCatalog(page, "WeatherFunctionTest", timestamp);
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
		CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_FUNCTION, TestResources.WEATHER_FUNC_NAME);
	}

	@Test
	@DisplayName("Validate the available tool and their input parameter after MCP Generation for Function")
	public void testValidateToolsAfterMCPGeneration(@PWPage Page page) throws IOException {
		String toastMessage = "MCP generated";
		FunctionTestUtils.userCanSeeCatalogTitle(page, "WeatherFunctionTest");
		AICorePageUtils.clickOnTabButton(page, "MCP Usage");
		EditModelPageUtils.clickOnGenerateMCPButtonFromMCPUsageTab(page);
		String actualMessage = ModelPageUtils.modelCreationToastMessage(page, toastMessage);
		Assertions.assertEquals(actualMessage, toastMessage, "Generate MCP creation failed");
		Map<String, List<String>> toolData = new HashMap<>();
		toolData.put("Execute Function Engine", Arrays.asList("engine", "map"));
		toolData.forEach((toolName, parameters) -> {
			boolean isToolPresent = EditModelPageUtils.verifyToolsInGeneratedMCP(page, toolName);
			Assertions.assertTrue(isToolPresent, "Tool not displayed: " + toolName);
			boolean isParamPresent = EditModelPageUtils.verifyInputParameters(page, toolName, parameters);
			Assertions.assertTrue(isParamPresent, "Parameters not correct for: " + toolName);
		});
	}

}
