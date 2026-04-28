package aicore.unit.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import aicore.pages.home.MainMenuUtils;
import aicore.pages.model.EditModelPageUtils;
import aicore.pages.model.ModelChatPageUtils;
import aicore.utils.AbstractE2ETest;
import aicore.utils.ModelTestUtils;
import aicore.utils.TestResources;

public class ModelChatFunctionalityTests extends AbstractE2ETest{
    
	// TODO: test is broken model chat is failling with NATIVE user: 
	// 'Prompt is larger than the token limit, please shorten/break it into multiple prompts'
	// upoloadModelZip fails for Admin user
	@Test
	@DisplayName("Validate Chat section of Model")
	public void testModelChat() {

		// setup
		// login with native user before tests
		// login(page, UserType.ADMIN);
		login(page, UserType.NATIVE);

		// model catalog and model name
		String modelName = TestResources.LLAMA3_70B_INSTRUCT_NAME; // "Llama3-70B-Instruct"
		String fileName = TestResources.LLAMA3_70B_INSTRUCT_ZIP; // "Model/Llama3-70B-Instruct.zip"

		// delete previous model and upload a new model
		// verify model is uploaded correctly.
		ModelTestUtils.uploadModelZip(page, modelName, fileName);

		// navigate to the model page
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenModel(page);
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
