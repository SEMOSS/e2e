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
import aicore.utils.annotations.PWPage;
import aicore.utils.page.app.AppPageUtils;
import aicore.utils.page.app.CreateAppPopupUtils;
import aicore.utils.page.app.DragAndDropBlocksPageUtils;
import aicore.utils.page.app.TemplateCreationUtils;

public class CreateAppReadUserPermission extends AbstractPlaywrightTestBase {
	
	private String Read = "Read";

	@BeforeEach
	void setup(@PWPage Page page) {
		loginNativeAdmin(page);
		String appName = TemplateCreationUtils.createDragAndDropApp(page, "Drag and Drop");
		verifyAppCreated(page);
		verifyWelcomePage(page);
		CatlogAccessPageUtility.clickOnSettings(page);
		AddFunctionPageUtils.clickOnAccessControl(page);
		SettingsModelPageUtils.clickOnAddMembersButton(page);
		SettingsModelPageUtils.addMember(page, Read, GenericSetupUtils.useDocker());
	    logout(page);
	    loginReadOnly(page);
		openAppLibrary(page);
		AppPageUtils.searchApp(page, appName, "");
		AppPageUtils.clickOnAppCard(page, appName, "");
		

		System.out.println("After opening app:");
		System.out.println("URL: " + page.url());
		System.out.println("Title: " + page.title());
		
		page.pause();
	   
	}
	

	@AfterEach
	void tearDown(@PWPage Page page) {
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
	
	
	private void verifyEditOption(Page page, String role, String action) {
	    boolean editOption = CatlogAccessPageUtility.canSeeEditOption(page, action);

	    Assertions.assertTrue(
	            editOption,
	            "For " + role + " user, Edit Option is " + action
	    );
	}

	
	
	private void verifySettingsVisibility(Page page, String role, String action) {
	    boolean canSeeSettings = CatlogAccessPageUtility.canSeeSettingOption(page);

	    if (action.equalsIgnoreCase("view")) {
	        Assertions.assertTrue(
	                canSeeSettings,
	                role + " user cannot view the Settings option"
	        );
	    } else if (action.equalsIgnoreCase("not view")) {
	        Assertions.assertFalse(
	                canSeeSettings,
	                role + " user should not view the Settings option"
	        );
	    } else {
	        Assertions.fail("Invalid action: " + action);
	    }
	}
	
	private void verifyPrivateToggleNotVisible(Page page, String role) {
	    Assertions.assertFalse(
	    		CatlogAccessPageUtility.userCanSeeAndEnablePrivateToggle(page),
	            role + " user should not see Make Private Toggle"
	    );
	}
	
	
	private void verifyNonDiscoverableToggleNotVisible(Page page, String role) {
	    Assertions.assertFalse(
	    		CatlogAccessPageUtility.userCanSeeAndEnableNonDiscoverableToggle(page),
	            role + " user should not see Make Non-Discoverable Toggle"
	    );
	}
	
	private void verifyDeleteCatalogOptionNotVisible(Page page, String role) {
	    Assertions.assertFalse(
	    		CatlogAccessPageUtility.userCanSeeDeleteCatalog(page),
	            role + " user should not view Delete Catalog Option"
	    );
	}
	
	@Test
    public void ReadButtonDisabled_test (@PWPage Page page) {
		
		System.out.println("Test: ");
		verifyEditOption(page, "Read", "Disable");
		System.out.println("Step 1");

		verifyWelcomePage(page);
		System.out.println("Step 1");

		verifySettingsVisibility(page, "Read", "not view");
		System.out.println("Step 2");

		verifyPrivateToggleNotVisible(page, "Read");
		System.out.println("Step 3");

		verifyNonDiscoverableToggleNotVisible(page, "Read");
		System.out.println("Step 4");

		verifyDeleteCatalogOptionNotVisible(page, "Read");
		System.out.println("Step 5");

	    logout(page);
	    loginAuthor(page);
		
		
	}
	
	
	
	
	
	
	
	
}
