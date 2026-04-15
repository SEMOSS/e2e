package aicore.app.model;

import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;

import aicore.base.GenericSetupUtils;
import aicore.framework.AICoreTestConstants;
import aicore.framework.ConfigUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.pages.model.AddModelFormUtils;
import aicore.utils.CommonUtils;
import aicore.utils.page.model.ModelPageUtils;

public class ModelSpecificPageTests {
	
	private String modelCatalogName = null;
	private Page page = GenericSetupUtils.setupPlaywright();


	@BeforeAll
	public void setup() throws IOException {
		GenericSetupUtils.initialize();
		String nativeUser = ConfigUtils.getValue(AICoreTestConstants.NATIVE_USERNAME);
		String nativePassword = ConfigUtils.getValue(AICoreTestConstants.NATIVE_PASSWORD);
		GenericSetupUtils.login(page, nativeUser, nativePassword);

		String timestamp = CommonUtils.getTimeStampName();
		modelCatalogName = "Model" + timestamp;
		String modelType = "OpenAI";
		String modelName = "GPT-4.1";
		String openAIKey = "Test@1234";

		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenModel(page);

		// model form options
		ModelPageUtils.clickAddModelButton(page);
		AddModelFormUtils.selectModelType(page, modelType);
		AddModelFormUtils.selectModel(page, modelName);
		AddModelFormUtils.enterCatalogName(page, modelCatalogName);
		AddModelFormUtils.enterOpenAIKey(page, openAIKey);
		AddModelFormUtils.clickOnCreateModelButton(page);
	}
	
	@Test
	public void testViewSMSS() {
		ModelPageUtils.verifyModelTitle(page, modelCatalogName);
		ModelPageUtils.clickOnSMSSTab(page);
	}
	
	
}
