package aicore.unit.model;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;

import aicore.base.GenericSetupUtils;
import aicore.framework.AICoreTestConstants;
import aicore.framework.ConfigUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.pages.model.AddModelFormUtils;
import aicore.utils.CatlogAccessPageUtility;
import aicore.utils.CommonUtils;
import aicore.utils.page.model.ModelPageUtils;

public class AddModelTests {

	@Test
	public void testAddModel() throws IOException {
		GenericSetupUtils.initialize();
		Page page = GenericSetupUtils.setupPlaywright();
		String nativeUser = ConfigUtils.getValue(AICoreTestConstants.NATIVE_USERNAME);
		String nativePassword = ConfigUtils.getValue(AICoreTestConstants.NATIVE_PASSWORD);
		GenericSetupUtils.login(page, nativeUser, nativePassword);
		
		String timestamp = CommonUtils.getTimeStampName();
		String modelCatalogName = "Model" + timestamp;
		String modelType ="OpenAI";
		String modelName ="GPT-4.1";
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

		// validate the model exists
		CatlogAccessPageUtility.getCatalogAndCopyId(page);
		ModelPageUtils.verifyModelTitle(page, modelCatalogName);

		// delete db
		CommonUtils.navigateAndDeleteCatalog(page, "Model", modelCatalogName);

	}
}
