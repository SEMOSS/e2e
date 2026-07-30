package aicore.unit.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;

import aicore.base.GenericSetupUtils;
import aicore.pages.function.FunctionAccessSettingsUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.pages.model.EditModelPageUtils;
import aicore.pages.model.SettingsModelPageUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.AddFunctionPageUtils;
import aicore.utils.CommonUtils;
import aicore.utils.ModelTestUtils;
import aicore.utils.RequestAccessPopupUtils;
import aicore.utils.TestResourceTrackerHelper;
import aicore.utils.annotations.PWPage;
import aicore.utils.page.model.ModelPageUtils;

public class ModelCatalogAccessControlTest extends AbstractPlaywrightTestBase {

	private String modelCatalogName = null;

	@BeforeEach
	public void setup(@PWPage Page page) {
		loginNativeAdmin(page);

		String timestamp = CommonUtils.getTimeStampName();
		modelCatalogName = "Model" + timestamp;
		String modelType = "OpenAI";
		String modelName = "GPT-4.1";
		String openAIKey = "Test@1234";

		ModelTestUtils.addModel(page, modelType, modelName, modelCatalogName, openAIKey);

		String modelId = EditModelPageUtils.getCatalogID(page);
		Assertions.assertNotNull(modelId, "Catalog ID should not be null");

		String modelTitle = ModelPageUtils.verifyModelTitle(page, modelCatalogName);
		Assertions.assertEquals(modelCatalogName, modelTitle, "Model title is not correct");

		AddFunctionPageUtils.clickOnAccessControl(page);
		FunctionAccessSettingsUtils.clickOnMakeDiscoverableButton(page, "Model");

		logout(page);
		loginEditor(page);

		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenModel(page);
		SettingsModelPageUtils.clickOnDiscoverableModelsButton(page);
		EditModelPageUtils.searchModelCatalog(page, modelCatalogName);
		EditModelPageUtils.selectModelFromSearchOptions(page, modelCatalogName);
		EditModelPageUtils.clickOnRequestAccessButtonOfDiscoverableCatalog(page);
		RequestAccessPopupUtils.selectAccessType(page, "author");
		RequestAccessPopupUtils.enterComment(page, "Access Request");
		RequestAccessPopupUtils.clickOnRequestButton(page);

		logout(page);
		loginNativeAdmin(page);

		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenModel(page);
		EditModelPageUtils.searchModelCatalog(page, modelCatalogName);
		EditModelPageUtils.selectModelFromSearchOptions(page, modelCatalogName);
		AddFunctionPageUtils.clickOnAccessControl(page);
		String pendingRequestsText = SettingsModelPageUtils.getPendingRequestCountText(page);
		Assertions.assertEquals("1 pending request", pendingRequestsText, "Pending request text not correct");
	}

	@AfterEach
	public void tearDown(@PWPage Page page) {
		loginNativeAdmin(page);
		if (modelCatalogName != null) {
			assertTrue(CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_MODEL,
					modelCatalogName));
		}
		logout(page);
	}

	@Test
	public void testAcceptRequestAndValidateUserAddedInMemberList(@PWPage Page page) {
		SettingsModelPageUtils.clickOnPendingRequestsExpandButton(page);
		SettingsModelPageUtils.performActionOnPendingRequest(page, "Approve");

		String actualMessage = ModelPageUtils.modelCreationToastMessage(page,
				"Successfully approved user permissions");
		Assertions.assertEquals("Successfully approved user permissions", actualMessage,
				"Approval toast message not correct");

		SettingsModelPageUtils.pageReload(page);
		AddFunctionPageUtils.clickOnAccessControl(page);
		boolean isUserDisplayed = SettingsModelPageUtils.isUserDisplayedInListAfterRequestAction(page, "editor",
				"author", GenericSetupUtils.useDocker());
		Assertions.assertTrue(isUserDisplayed,
				"User is not displayed in the members list with the provided permission");
	}

	@Test
	public void testRejectRequestAndValidateUserNotAddedInMemberList(@PWPage Page page) {
		SettingsModelPageUtils.clickOnPendingRequestsExpandButton(page);
		SettingsModelPageUtils.performActionOnPendingRequest(page, "Reject");

		String actualMessage = ModelPageUtils.modelCreationToastMessage(page, "Successfully denied user permissions");
		Assertions.assertEquals("Successfully denied user permissions", actualMessage, "Toast message not correct");

		SettingsModelPageUtils.pageReload(page);
		AddFunctionPageUtils.clickOnAccessControl(page);
		boolean isUserDisplayed = SettingsModelPageUtils.isUserDisplayedInListAfterRequestAction(page, "editor",
				"author", GenericSetupUtils.useDocker());
		Assertions.assertFalse(isUserDisplayed,
				"User is displayed in the members list with the provided permission");
	}

	@Test
	public void testChangeRequestedAccessAndApprove(@PWPage Page page) {
		SettingsModelPageUtils.clickOnPendingRequestsExpandButton(page);
		SettingsModelPageUtils.changeRequestedAccessRole(page, "read");
		SettingsModelPageUtils.performActionOnPendingRequest(page, "Approve");

		String actualMessage = ModelPageUtils.modelCreationToastMessage(page,
				"Successfully approved user permissions");
		Assertions.assertEquals("Successfully approved user permissions", actualMessage,
				"Approval toast message not correct");

		SettingsModelPageUtils.pageReload(page);
		AddFunctionPageUtils.clickOnAccessControl(page);
		boolean isUserDisplayed = SettingsModelPageUtils.isUserDisplayedInListAfterRequestAction(page, "editor",
				"read", GenericSetupUtils.useDocker());
		Assertions.assertTrue(isUserDisplayed,
				"User is not displayed in the members list with the provided permission");
	}
}
