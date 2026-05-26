package aicore.unit.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;

import aicore.pages.home.MainMenuUtils;
import aicore.pages.model.EditModelPageUtils;
import aicore.pages.model.SettingsModelPageUtils;
import aicore.pages.model.settings.ModelAccessSettingsUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.CommonUtils;
import aicore.utils.ModelTestUtils;
import aicore.utils.TestResourceTrackerHelper;
import aicore.utils.annotations.PWPage;

public class ModelSettingsTests extends AbstractPlaywrightTestBase {
    private String modelCatalogName = null;

	@BeforeEach
    public void setup(@PWPage Page page) {
        // login with admin user before tests
		loginNativeAdmin(page);

        // setup models
        String timestamp = CommonUtils.getTimeStampName();
		modelCatalogName = "Model" + timestamp;
		String modelType = "OpenAI";
		String modelName = "GPT-4.1";
		String openAIKey = "Test@1234";

        // add model 
        ModelTestUtils.addModel(page, modelType, modelName, modelCatalogName, openAIKey);

        // need to return back to main engine page
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenModel(page);
		EditModelPageUtils.searchModelCatalog(page, modelCatalogName);
		EditModelPageUtils.selectModelFromSearchOptions(page, modelCatalogName);
    }

	@AfterEach
    public void tearDown(@PWPage Page page) {
        loginNativeAdmin(page);
		// Clean up: delete the test model catalog
		assertTrue(CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_MODEL, modelCatalogName));
        logout(page);
    }

    @Test
    public void testModelSettings(@PWPage Page page) {
        SettingsModelPageUtils.clickOnSettingsTab(page);
        // verify Private section
        assertTrue(ModelAccessSettingsUtils.verifyMakePublicSectionIsVisible(page,"Private"));
        String actualTextMessage = ModelAccessSettingsUtils.verifyMakePublicSectionTextMessage(page);
        assertEquals(actualTextMessage, "No one outside of the specified member group can access");
        assertTrue(ModelAccessSettingsUtils.verifyMakePublicToggleButtonIsVisible(page));
        // verify Non Discoverable Section
        assertTrue(ModelAccessSettingsUtils.verifyMakePublicSectionIsVisible(page,"Non Discoverable"));
        actualTextMessage = ModelAccessSettingsUtils.verifyMakeDiscoverableSectionTextMessage(page);
        assertEquals(actualTextMessage, "Users cannot discover Model, view its details, or request access when it is non-discoverable.");
        assertTrue(ModelAccessSettingsUtils.verifyMakeDiscoverableToggleButtonIsVisible(page));
        // verify Delete Model section
        assertTrue(ModelAccessSettingsUtils.verifyMakePublicSectionIsVisible(page,"Delete Model"));
        actualTextMessage = ModelAccessSettingsUtils.verifyDeleteSectionTextMessage(page);
        assertEquals(actualTextMessage, "Delete Model from catalog.");
        assertTrue(ModelAccessSettingsUtils.verifyDeleteButtonIsVisible(page));
        // Pending Requests section
        assertTrue(SettingsModelPageUtils.verifyPendingRequestsSectionIsVisible(page));
        actualTextMessage = SettingsModelPageUtils.verifyPendingRequestsSectionTextMessage(page);
        String extractedText = actualTextMessage.replaceAll("\\d+", "").trim();
        boolean isValidText = extractedText.equals("pending request")
				|| extractedText.equals("pending requests");
        assertTrue(isValidText);
        // Members Permissions section
        assertTrue(SettingsModelPageUtils.verifyMembersSectionIsVisible(page));
        assertTrue(SettingsModelPageUtils.verifySearchMembersSearchBoxIsVisible(page));
        assertTrue(SettingsModelPageUtils.verifyAddMembersButtonIsVisible(page));
        assertTrue(SettingsModelPageUtils.verifyRowsPerPageDropdownIsVisible(page));
    }
}
