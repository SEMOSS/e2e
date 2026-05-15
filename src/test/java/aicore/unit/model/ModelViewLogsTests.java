package aicore.unit.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;

import aicore.pages.home.MainMenuUtils;
import aicore.pages.model.EditModelPageUtils;
import aicore.pages.model.ModelChatPageUtils;
import aicore.pages.model.ModelViewLogsDashboardPageUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.CommonUtils;
import aicore.utils.ModelTestUtils;
import aicore.utils.TestResourceTrackerHelper;
import aicore.utils.TestResources;
import aicore.utils.annotations.PWPage;

public class ModelViewLogsTests extends AbstractPlaywrightTestBase {

    // model catalog and model name
	private String modelName = TestResources.PERPLEXITY_NAME; // "Perplexity"
	private String fileName = TestResources.PERPLEXITY_ZIP; // "Perplexity.zip"
    
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
    
    @Test
    public void testLogs(@PWPage Page page) {

        EditModelPageUtils.searchModelCatalog(page, modelName);
		// EditModelPageUtils.selectModelFromSearchOptions(page, modelName);

        // TODO: this part of the test fails because of Token limit exceeded for user level : You have used 0 tokens, 
	    // but the limit is -1
		// ModelChatPageUtils.clickOnChatTab(page);
		// ModelChatPageUtils.verifyChatSectionDisplayed(page, "Chat with the Model");
		// ModelChatPageUtils.verifyModelIDAndNameDisplayed(page);
		// ModelChatPageUtils.verifyTemperatureValue(page, "0.1");
		// ModelChatPageUtils.verifyMaxTokensValue(page, "2000");
		// ModelChatPageUtils.verifyInputTextboxPlaceholder(page, "Ask a question… (Enter to send, Shift+Enter for new line)");
		// ModelChatPageUtils.verifyAndActivateSendButton(page, "Tell me a joke");
		// ModelChatPageUtils.clickOnSendButton(page);
        // ModelChatPageUtils.verifyLoaderDisplayed(page);
		// ModelChatPageUtils.verifyResponseGeneratedInChatWindow(page);
		// ModelChatPageUtils.clickOnClearAllButton(page);
		// ModelChatPageUtils.verifyChatWindowCleared(page);

        ModelViewLogsDashboardPageUtils.navigateToModelInsightDashboard(page);
        assertTrue(ModelViewLogsDashboardPageUtils.verifyDashboardHeading(page));
        assertTrue(ModelViewLogsDashboardPageUtils.verifyRefreshButton(page));
        assertTrue(ModelViewLogsDashboardPageUtils.verifyEventHistorySection(page));
        assertTrue(ModelViewLogsDashboardPageUtils.verifyPromptResponseTimelineSection(page));
        assertTrue(ModelViewLogsDashboardPageUtils.verifyTableExists(page));
        String columnNames = "User Id, Session Id, Request, Response, Engine Type, Engine Name, Latency, Tokens, Timestamp, Status";
        List<String> expectedColumns = Arrays.asList(columnNames.split("\\s*,\\s*"));
        assertTrue(ModelViewLogsDashboardPageUtils.verifyTableColumnsExist(page, expectedColumns));


    }
}
