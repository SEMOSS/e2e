package aicore.unit.model;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import aicore.base.GenericSetupUtils;
import aicore.pages.function.FunctionAccessSettingsUtils;
import aicore.pages.home.HomePageUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.pages.model.AddModelFormUtils;
import aicore.pages.model.EditModelPageUtils;
import aicore.pages.model.SettingsModelPageUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.AddFunctionPageUtils;
import aicore.utils.CommonUtils;
import aicore.utils.RequestAccessPopupUtils;
import aicore.utils.TestResourceTrackerHelper;
import aicore.utils.annotations.PWPage;
import aicore.utils.page.model.ModelPageUtils;


public class ModelCatalogAccessControlTest extends AbstractPlaywrightTestBase {
	private String modelCatalogName;
	
	@BeforeEach
	void setup(@PWPage Page page) {
		loginNativeAdmin(page);	
		openModelPage(page);
	    clickAddModelButton(page);
	    AddModelFormUtils.selectModelType(page, "OpenAI");
	    AddModelFormUtils.selectModel(page, "GPT-4.1");	
		modelCatalogName = "GPT Model" + CommonUtils.getTimeStampName();
		AddModelFormUtils.enterCatalogName(page, modelCatalogName);
		AddModelFormUtils.enterOpenAIKey(page, "Test@1234");
		AddModelFormUtils.clickOnCreateModelButton(page);
		clickOnCopyCatalogId(page);
		verifyModelTitle(page, modelCatalogName);
		AddFunctionPageUtils.clickOnAccessControl(page);
		FunctionAccessSettingsUtils.clickOnMakeDiscoverableButton(page, "Model");
	    logout(page);
	    loginEditor(page);
		openModelPage(page);
		SettingsModelPageUtils.clickOnDiscoverableModelsButton(page);
		EditModelPageUtils.searchModelCatalog(page, modelCatalogName);
		EditModelPageUtils.selectModelFromSearchOptions(page, modelCatalogName);
		EditModelPageUtils.clickOnRequestAccessButtonOfDiscoverableCatalog(page);
		RequestAccessPopupUtils.selectAccessType(page, "author");
		RequestAccessPopupUtils.enterComment(page, "Access Request");
		RequestAccessPopupUtils.clickOnRequestButton(page);
	    logout(page);
		loginNativeAdmin(page);	
		openModelPage(page);
		EditModelPageUtils.searchModelCatalog(page, modelCatalogName);
		EditModelPageUtils.selectModelFromSearchOptions(page, modelCatalogName);
		AddFunctionPageUtils.clickOnAccessControl(page);
		SettingsModelPageUtils.getPendingRequestCountText(page);
	    
	}	
	
	@AfterEach
	void tearDown(@PWPage Page page) {
	    CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_MODEL, modelCatalogName);
	    logout(page);
	}
	
	private void openModelPage(Page page) {
		
	    HomePageUtils.navigateToHomePage(page);
	    MainMenuUtils.openMainMenu(page);
	    MainMenuUtils.clickOnOpenModel(page);
	}
	
	
	private void clickAddModelButton(Page page) {
	    Locator addModelButton = page.getByTestId("engineIndex-add-Model-btn");

	    addModelButton.waitFor(
	            new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
	    addModelButton.click();
	}
	
    private void clickOnCopyCatalogId(Page page) {
	        Locator copyCatalogIdButton = page.getByTestId("engineHeader-copy-Model-id-btn");

	        copyCatalogIdButton.waitFor(
	                new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
	        copyCatalogIdButton.click();
	    }
	
	
	private void verifyModelTitle(Page page, String modelTitle) {
	    String actualModelTitle = ModelPageUtils.verifyModelTitle(page, modelTitle);
	    String expectedModelTitle = ModelPageUtils.getExpectedCatalogTitle(modelTitle);

	    Assertions.assertEquals(
	            expectedModelTitle,
	            actualModelTitle,
	            "Model title does not match");
	}
	
	
	private void verifyUserDisplayedInMembersList(Page page, String role, String permissionGranted) {
	    boolean isUserDisplayed = SettingsModelPageUtils.isUserDisplayedInListAfterRequestAction(page, role,
	            permissionGranted, GenericSetupUtils.useDocker());
	    Assertions.assertTrue(isUserDisplayed, "User is not displayed in the members list with the provided permission");
	}
	
	
	private void verifyUserNotDisplayedInMembersList(Page page, String role, String permissionGranted) {
	    boolean isUserDisplayed = SettingsModelPageUtils.isUserDisplayedInListAfterRequestAction(page, role,
	            permissionGranted, GenericSetupUtils.useDocker());
	    Assertions.assertFalse(isUserDisplayed, "User is displayed in the members list with the requested permission");
	}
	
	
	@Test
    public void AcceptAndValidateUserInMemberList_test(@PWPage Page page){
	
		SettingsModelPageUtils.clickOnPendingRequestsExpandButton(page);
		SettingsModelPageUtils.performActionOnPendingRequest(page, "Approve");
		ModelPageUtils.modelCreationToastMessage(page, "Successfully approved user permissions");
		SettingsModelPageUtils.pageReload(page);
		verifyUserDisplayedInMembersList(page, "editor", "author");		
		
	}
	
	@Test
	public void RejectAndValidateUserNotInMemberList_test(@PWPage Page page){
	
	
		SettingsModelPageUtils.clickOnPendingRequestsExpandButton(page);
		SettingsModelPageUtils.performActionOnPendingRequest(page, "Reject");
		ModelPageUtils.modelCreationToastMessage(page, "Successfully denied user permissions");
		SettingsModelPageUtils.pageReload(page);
		verifyUserNotDisplayedInMembersList(page, "editor", "author");

	}
	

	@Test
	public void ChangeRequestAcessAndAccept_test(@PWPage Page page){
		
		SettingsModelPageUtils.clickOnPendingRequestsExpandButton(page);
		SettingsModelPageUtils.changeRequestedAccessRole(page, "read");
		SettingsModelPageUtils.performActionOnPendingRequest(page, "Approve");
		ModelPageUtils.modelCreationToastMessage(page, "Successfully approved user permissions");
		SettingsModelPageUtils.pageReload(page);
		verifyUserDisplayedInMembersList(page, "editor", "read");
	}
	
}
	
