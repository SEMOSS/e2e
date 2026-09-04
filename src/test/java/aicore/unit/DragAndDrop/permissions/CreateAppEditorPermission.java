package aicore.unit.DragAndDrop.permissions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.microsoft.playwright.Page;
import aicore.base.GenericSetupUtils;
import aicore.pages.home.HomePageUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.pages.model.SettingsModelPageUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.AddFunctionPageUtils;
import aicore.utils.CatlogAccessPageUtility;
import aicore.utils.CommonUtils;
import aicore.utils.annotations.PWPage;
import aicore.utils.page.app.AppPageUtils;
import aicore.utils.page.app.CreateAppPopupUtils;
import aicore.utils.page.app.DragAndDropBlocksPageUtils;
import aicore.utils.page.app.TemplateCreationUtils;

public class CreateAppEditorPermission extends AbstractPlaywrightTestBase{

	
	private String Editor = "Editor";
	private String Read = "Read";
	private String appName;


	@BeforeEach
	void setup(@PWPage Page page) {
		loginNativeAdmin(page);
		appName = TemplateCreationUtils.createDragAndDropApp(page, "Drag and Drop");
		verifyAppCreated(page);
		verifyWelcomePage(page);
		CatlogAccessPageUtility.clickOnSettings(page);
		AddFunctionPageUtils.clickOnAccessControl(page);
		SettingsModelPageUtils.clickOnAddMembersButton(page);
		SettingsModelPageUtils.addMember(page, Editor, GenericSetupUtils.useDocker());
	    logout(page);
		loginEditor(page);
		openAppLibrary(page);
		AppPageUtils.searchApp(page, appName, "");
		AppPageUtils.clickOnAppCard(page, appName, "");
	
	}

	@AfterEach
	void tearDown(@PWPage Page page) {
		CommonUtils.navigateAndDeleteApp(page, appName);
	    logout(page);
	}
	
	private void verifyWelcomePage(Page page) {
	    Assertions.assertTrue(
	            DragAndDropBlocksPageUtils.verifyPage1IsVisible(page),
	            "Page is not visible");

	    Assertions.assertTrue(
	            DragAndDropBlocksPageUtils.verifyWelcomeTextboxIsVisible(page),
	            "Welcome text box is not visible");

	    Assertions.assertEquals(
	            "Welcome to the UI Builder! Drag and drop blocks to use in your app.",
	            DragAndDropBlocksPageUtils.verifyWelcomeText(page),
	            "Mismatch between the expected and actual welcome message");
	}
	
	
	private void openAppLibrary(Page page) {
	    HomePageUtils.navigateToHomePage(page);
	    MainMenuUtils.openMainMenu(page);
	    MainMenuUtils.clickOnOpenAppLibrary(page);
	}
	
	
	private void verifyAppCreated(Page page) {
	    String appName = CreateAppPopupUtils.userFetchAppName(page);
	    Assertions.assertFalse(appName.isEmpty(), "Fetched App Name is Empty");
	}
	
	
	private void verifyPrivateToggleDisabled(Page page, String role) {
	    boolean privateToggleVisible =
	            CatlogAccessPageUtility.userCanSeeAndEnablePrivateToggle(page);

	    Assertions.assertFalse(
	            privateToggleVisible,
	            role + " user should not see the Private Toggle enabled."
	    );
	}
	
	private void verifyNonDiscoverableToggleDisabled(Page page, String role) {
	    boolean nonDiscoverableToggleVisible =
	            CatlogAccessPageUtility.userCanSeeAndEnableNonDiscoverableToggle(page);

	    Assertions.assertFalse(
	            nonDiscoverableToggleVisible,
	            role + " user should not see the Non-Discoverable toggle enabled."
	    );
	}
	
	private void verifyDeleteCatalogOptionNotVisible(Page page, String role) {
	    boolean deleteCatalogOptionVisible =
	            CatlogAccessPageUtility.userCanSeeDeleteCatalog(page);

	    Assertions.assertFalse(
	            deleteCatalogOptionVisible,
	            role + " user should not view the Delete Catalog option."
	    );
	}
	
	@Test
    public void NonDiscoverableAndDeleteToggleEditorUser_test (@PWPage Page page) {
		DragAndDropBlocksPageUtils.clickOnEditButton(page);
		CatlogAccessPageUtility.clickOnSettings(page);
		AddFunctionPageUtils.clickOnAccessControl(page);
		verifyPrivateToggleDisabled(page, Editor);
		verifyNonDiscoverableToggleDisabled(page, Editor);
		verifyDeleteCatalogOptionNotVisible(page, Editor);
	    logout(page);
		loginNativeAdmin(page);

	}
	@Test
    public void AddAndDeleteEditorAndReadUser_test (@PWPage Page page) {
		
		DragAndDropBlocksPageUtils.clickOnEditButton(page);
		CatlogAccessPageUtility.clickOnSettings(page);
		AddFunctionPageUtils.clickOnAccessControl(page);
		SettingsModelPageUtils.clickOnAddMembersButton(page);
		SettingsModelPageUtils.addMember(page, Read, GenericSetupUtils.useDocker());
		CatlogAccessPageUtility.searchUserBasedOnRole(page, "Viewer");
		SettingsModelPageUtils.deleteAddedMember(page, "Viewer");
		logout(page);
		loginAuthor(page);
	    logout(page);
		loginNativeAdmin(page);

	}
		
	
	@Test
    public void DeleteModel_test (@PWPage Page page) {
		DragAndDropBlocksPageUtils.clickOnEditButton(page);
		CatlogAccessPageUtility.clickOnSettings(page);
		AddFunctionPageUtils.clickOnAccessControl(page);
		verifyDeleteCatalogOptionNotVisible(page, Editor);
		logout(page);
		loginAuthor(page);
	    logout(page);
		loginNativeAdmin(page);
	}
	
	
	
	
	
	
	
	
	
	
	
}
