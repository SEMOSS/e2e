package aicore.unit.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;

import aicore.pages.home.MainMenuUtils;
import aicore.pages.model.EditModelPageUtils;
import aicore.pages.model.ModelChatPageUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.CommonUtils;
import aicore.utils.ModelTestUtils;
import aicore.utils.TestResourceTrackerHelper;
import aicore.utils.TestResources;
import aicore.utils.annotations.PWPage;
import aicore.utils.annotations.ResourceUploadLock;

public class ModelChatFunctionalityTests extends AbstractPlaywrightTestBase{

	// model catalog and model name
	private String modelName = TestResources.LLAMA3_70B_INSTRUCT_NAME; // "Llama3-70B-Instruct"
	private String fileName = TestResources.LLAMA3_70B_INSTRUCT_ZIP; // "Model/Llama3-70B-Instruct.zip"
    
	@BeforeEach
	void setup(@PWPage Page page) {
		loginNativeAdmin(page);	
		// delete previous model and upload a new model
		// verify model is uploaded correctly.
		ModelTestUtils.uploadModelZip(page, modelName, fileName);
		// navigate to the model page
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenModel(page);
	}

	@AfterEach
	void teardown(@PWPage Page page) {
		loginNativeAdmin(page);
		// Clean up: delete the test model catalog
		assertTrue(CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_MODEL, modelName));
        logout(page);
	}

	// TODO: test fails because of Token limit exceeded for user level : You have used 0 tokens, 
	// but the limit is -1
	@Test
	@ResourceUploadLock(TestResources.LLAMA3_70B_INSTRUCT_ZIP)
	public void testModelChat(@PWPage Page page) {
		EditModelPageUtils.searchModelCatalog(page, modelName);
		EditModelPageUtils.selectModelFromSearchOptions(page, modelName);

		ModelChatPageUtils.clickOnChatTab(page);
		ModelChatPageUtils.verifyChatSectionDisplayed(page, "Chat with the Model");
		ModelChatPageUtils.verifyModelIDAndNameDisplayed(page);
		ModelChatPageUtils.verifyTemperatureValue(page, "0.1");
		ModelChatPageUtils.verifyMaxTokensValue(page, "2000");
		ModelChatPageUtils.verifyInputTextboxPlaceholder(page, "Ask a question… (Enter to send, Shift+Enter for new line)");
		ModelChatPageUtils.verifyAndActivateSendButton(page, "Tell me a joke");
		ModelChatPageUtils.clickOnSendButton(page);
		ModelChatPageUtils.verifyLoaderDisplayed(page);
		ModelChatPageUtils.verifyResponseGeneratedInChatWindow(page);
		ModelChatPageUtils.clickOnClearAllButton(page);
		ModelChatPageUtils.verifyChatWindowCleared(page);
	}
}
