package aicore.unit.app.agentBuilder;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;

import aicore.hooks.SetupHooks;
import aicore.pages.home.MainMenuUtils;
import aicore.pages.model.AddModelFormUtils;
import aicore.utils.AICorePageUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.AgentBuilderAppsUtils;
import aicore.utils.CatlogAccessPageUtility;
import aicore.utils.CommonUtils;
import aicore.utils.TestResourceTrackerHelper;
import aicore.utils.TestResources;
import aicore.utils.annotations.PWPage;
import aicore.utils.page.app.AppPageUtils;
import aicore.utils.page.app.CreateAppPopupUtils;
import aicore.utils.page.model.ModelPageUtils;

public class CreateAppUsingAgentBuilderTests extends AbstractPlaywrightTestBase {

	private String testModelName = "";
	private String testAppName = "";
	private String openAiKey = "Test@1234";
	private String successToastMessage = "Successfully added LLM to catalog";
	
	@BeforeEach
	void setup(@PWPage Page page) {
		String timestamp = CommonUtils.getTimeStampName();
		testModelName = "Model " + timestamp; // catalog name
		
		loginNativeAdmin(page);
		
		// create a GPT4.1 model for each test
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenModel(page); 
		ModelPageUtils.clickAddModelButton(page);
		AddModelFormUtils.selectModelType(page, TestResources.MODEL_OPEN_AI);
		AddModelFormUtils.selectModel(page, TestResources.MDL_OPEN_AI_GPT_4_1);
		AddModelFormUtils.enterCatalogName(page, testModelName);
		AddModelFormUtils.enterOpenAIKey(page, openAiKey);
		AddModelFormUtils.clickOnCreateModelButton(page);
		CatlogAccessPageUtility.getCatalogAndCopyId(page);
		String actualToastMessage = ModelPageUtils.modelCreationToastMessage(page, successToastMessage);
		Assertions.assertEquals(actualToastMessage, successToastMessage, "Model creation failed");
		String actualModelTitle = ModelPageUtils.verifyModelTitle(page, testModelName);
		Assertions.assertEquals(actualModelTitle, testModelName);
	}	
	
	@AfterEach
	void tearDown(@PWPage Page page) {
		CommonUtils.navigateAndDeleteApp(page, testAppName);
		CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_MODEL, testModelName);
		logout(page);
	}
	
	@Test
	void testCreateAgentBuilderAppAndNavigateToBlocksPortion(@PWPage Page page) {
		String timestamp = CommonUtils.getTimeStampName();
		testAppName = "Demo App " + timestamp;
		
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenAppLibrary(page);
		AppPageUtils.clickOnCreateNewAppButton(page);
		CreateAppPopupUtils.clickOnGetStartedButton(page, "Construct an agent");
		AgentBuilderAppsUtils.enterName(page, testAppName, null); // no timestamp
		AgentBuilderAppsUtils.clickOnSelectLLM(page, testModelName);
		AgentBuilderAppsUtils.fillPrompt(page, "Create Test cases for the Userstory");
		AICorePageUtils.clickOnButton(page, "Next");
		AgentBuilderAppsUtils.setInput(page, "Userstory");
		AICorePageUtils.clickOnButton(page, "Next");
		AICorePageUtils.clickOnButton(page, "Next");
        AgentBuilderAppsUtils.clickOnPreviewButton(page);
        AgentBuilderAppsUtils.clickOnCreateAppButton(page);
        String fetchName = AgentBuilderAppsUtils.userFetchAppName(page);
        Assertions.assertFalse(fetchName.isEmpty(), "Fetched App Name is Empty");
	}
	
	@Test
	void testCreateAgentbuilderAppUsingGlobalPromptAndNavigateToBlocksPortion(@PWPage Page page) {
		String timestamp = CommonUtils.getTimeStampName();
		testAppName = "Demo App " + timestamp;
		String prompt = "I am planning a 90 day project for a client in Federal Health. The project involves software development, "
				+ "team training, and stakeholder presentations. Can you outline a project plan for me?";
		
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenAppLibrary(page);
		AppPageUtils.clickOnCreateNewAppButton(page);
		CreateAppPopupUtils.clickOnGetStartedButton(page, "Construct an agent");
		AgentBuilderAppsUtils.enterName(page, testAppName, null); // no timestamp
		AgentBuilderAppsUtils.clickOnSelectLLM(page, testModelName);
		AgentBuilderAppsUtils.fillPrompt(page, prompt);
		AICorePageUtils.clickOnButton(page, "Next");
		AgentBuilderAppsUtils.setInputInPrompt(page, "90 day");
		AgentBuilderAppsUtils.setInputInPrompt(page, "Federal Health.");
		AgentBuilderAppsUtils.setInputInPrompt(page, "software development,");
		AgentBuilderAppsUtils.setInputInPrompt(page, "team training,");
		AgentBuilderAppsUtils.setInputInPrompt(page, "stakeholder presentations.");
		AICorePageUtils.clickOnButton(page, "Next");
		// TODO verify user text portions
		AICorePageUtils.clickOnButton(page, "Next");
        AgentBuilderAppsUtils.clickOnPreviewButton(page);
        AgentBuilderAppsUtils.clickOnCreateAppButton(page);
        String fetchName = AgentBuilderAppsUtils.userFetchAppName(page);
        Assertions.assertFalse(fetchName.isBlank(), "Fetched App Name is Empty");
	}
	
}
