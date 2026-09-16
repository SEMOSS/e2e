package aicore.unit.model.permissions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

import aicore.base.GenericSetupUtils;
import aicore.pages.home.HomePageUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.pages.model.AddModelFormUtils;
import aicore.pages.model.EditModelPageUtils;
import aicore.pages.model.SettingsModelPageUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.CatlogAccessPageUtility;
import aicore.utils.CommonUtils;
import aicore.utils.TestResourceTrackerHelper;
import aicore.utils.annotations.PWPage;
import aicore.utils.page.model.ModelPageUtils;

public class ModelCatalogReadOnlyPermission  extends AbstractPlaywrightTestBase {
	
	private String modelCatalogName;

	@BeforeEach
	void setup(@PWPage Page page) {
		
		loginNativeAdmin(page);	
		openModelPage(page);
	    clickAddModelButton(page);
	    AddModelFormUtils.selectModelType(page, "OpenAI");
	    AddModelFormUtils.selectModel(page, "GPT-4.1");	
		modelCatalogName = "Catalog" + CommonUtils.getTimeStampName();
		AddModelFormUtils.enterCatalogName(page, modelCatalogName);
		AddModelFormUtils.enterOpenAIKey(page, "Test@1234");
		AddModelFormUtils.clickOnCreateModelButton(page);
		ModelPageUtils.verifyModelTitle(page, modelCatalogName + "");
		clickOnCopyCatalogId(page);
		verifyModelTitle(page, modelCatalogName);
		SettingsModelPageUtils.clickOnAccessControl(page);
		SettingsModelPageUtils.clickOnAddMembersButton(page);
		addMember(page, "Read");
	    logout(page);
	    loginReadOnly(page);
		openModelPage(page);
		EditModelPageUtils.searchModelCatalog(page, modelCatalogName);
		EditModelPageUtils.selectModelFromSearchOptions(page, modelCatalogName);
		verifyModelTitle(page, modelCatalogName);
		CatlogAccessPageUtility.canViewOverview(page);
		}
	
	@AfterEach
	void tearDown(@PWPage Page page) {
	    CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_MODEL, modelCatalogName);
	    logout(page);
	}
	
	
    private void addMember(Page page, String role) {
        try {
            SettingsModelPageUtils.addMember(page, role, GenericSetupUtils.useDocker());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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
	

	@Test
    public void ModelCatalogReadViewUsage_test(@PWPage Page page){
		
		CatlogAccessPageUtility.canViewUsage(page);
		logout(page);
		loginNativeAdmin(page);	
		
	}
	

	@Test
    public void ModelCatalogReadSmsDetails_test(@PWPage Page page){
		
		CatlogAccessPageUtility.canViewSMSSDetails(page);
		logout(page);
		loginNativeAdmin(page);	
		
	}
	

	@Test
    public void ModelCatalogReadEditSms_test(@PWPage Page page){
		
		CatlogAccessPageUtility.canViewSMSSDetails(page);
		CatlogAccessPageUtility.canViewEditSMSS(page);
		logout(page);
		loginNativeAdmin(page);	
		
	}
	
	@Test
    public void ModelCatalogReadAccessControl_test(@PWPage Page page){
		
		CatlogAccessPageUtility.canViewAccessControl(page);
		logout(page);
		loginNativeAdmin(page);	
	}
		

	@Test
    public void ModelCatalogReadMemeberSetting_test(@PWPage Page page){
		
		CatlogAccessPageUtility.canViewAccessControl(page);
		SettingsModelPageUtils.isAddMemberButtonVisible(page);
		logout(page);
		loginNativeAdmin(page);	
	}
	


	@Test
    public void ModelCatalogReadDeleteModel_test(@PWPage Page page){
		
		CatlogAccessPageUtility.canViewAccessControl(page);
		logout(page);
		loginNativeAdmin(page);	

	}
		

}
