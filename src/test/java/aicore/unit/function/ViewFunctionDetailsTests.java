package aicore.unit.function;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import aicore.base.GenericSetupUtils;
import aicore.hooks.SetupHooks;
import aicore.pages.ViewFunctionPage;
import aicore.pages.home.MainMenuUtils;
import aicore.pages.model.EditModelPageUtils;
import aicore.pages.model.SettingsModelPageUtils;
import aicore.utils.AICorePageUtils;
import aicore.utils.AbstractE2ETest;
import aicore.utils.AddFunctionPageUtils;
import aicore.utils.CatalogCreationFromZipUtil;
import aicore.utils.CatlogAccessPageUtility;
import aicore.utils.CommonUtils;
import aicore.utils.FunctionTestUtils;
import aicore.utils.TestResourceTrackerHelper;
import aicore.utils.page.model.ModelPageUtils;

public class ViewFunctionDetailsTests extends AbstractE2ETest {
	private static final Logger logger = LogManager.getLogger(ViewFunctionDetailsTests.class);

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
		AddFunctionPageUtils.deleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_FUNCTION,
				"WeatherFunctionTest");
		AddFunctionPageUtils.clickOnAddFunctionButton(page);
		CatalogCreationFromZipUtil.clickOnFileUploadIcon(page);
		FunctionTestUtils.userUploadsFile(page, "Function/weatherFunctionTest.zip");
		CatalogCreationFromZipUtil.clickOnUploadButton(page, "Upload");
		CatlogAccessPageUtility.getCatalogAndCopyId(page);
		FunctionTestUtils.verifyUserSeesSuccessToastMessage(page, "Successfully Created Function Database");
		FunctionTestUtils.userCanSeeCatalogTitle(page, "WeatherFunctionTest");
	}

	@Test
	void testViewOverviewTabDetails() throws InterruptedException {
		String timestamp = CommonUtils.getTimeStampName();
		FunctionTestUtils.userCanSeeCatalogTitle(page, "WeatherFunctionTest");
		AddFunctionPageUtils.clickOnAccessControl(page);
		SettingsModelPageUtils.clickOnAddMembersButton(page);
		SettingsModelPageUtils.addMember(page, "read", GenericSetupUtils.useDocker());
		logout(page);
		login(page, UserType.READER);
		MainMenuUtils.openMainMenu(SetupHooks.getPage());
		MainMenuUtils.clickOnOpenFunction(SetupHooks.getPage());
		AddFunctionPageUtils.clickOnFunctionNameInCatalog(page, "WeatherFunctionTest", timestamp);
		ViewFunctionPage.verifyChangeAccessButton(page, "Change Access");
		logout(page);
		login(page, UserType.NATIVE);
		MainMenuUtils.openMainMenu(SetupHooks.getPage());
		MainMenuUtils.clickOnOpenFunction(SetupHooks.getPage());
		AddFunctionPageUtils.verifyFunctionNameInCatalog(page, "WeatherFunctionTest", timestamp);
		AddFunctionPageUtils.clickOnFunctionNameInCatalog(page, "WeatherFunctionTest", timestamp);
	}

	@Test
	void testViewUsageDetailsInUsageTabForSelectedFunction() {
		FunctionTestUtils.userCanSeeCatalogTitle(page, "WeatherFunctionTest");
		ViewFunctionPage viewFunction = new ViewFunctionPage(page);
		viewFunction.clickUsageTab("Usage");
		assertTrue(viewFunction.verifyUsageInstructionsSection("How to use in Pixel"));
		assertTrue(viewFunction.verifyUsageInstructionsSection("How to use in Python"));
		assertTrue(viewFunction.verifyUsageInstructionsSection("How to use in Java"));
		CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_FUNCTION,
				"WeatherFunctionTest");
	}

	@Test
	@DisplayName("Validate the available tool and their input parameter after MCP Generation for Function")
	public void testValidateToolsAfterMCPGeneration() throws IOException {
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
