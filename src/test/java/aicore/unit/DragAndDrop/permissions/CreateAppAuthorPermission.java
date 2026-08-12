package aicore.unit.DragAndDrop.permissions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.microsoft.playwright.Page;
import aicore.base.GenericSetupUtils;
import aicore.pages.model.SettingsModelPageUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.AddFunctionPageUtils;
import aicore.utils.CatlogAccessPageUtility;
import aicore.utils.CommonUtils;
import aicore.utils.annotations.PWPage;
import aicore.utils.page.app.CreateAppPopupUtils;
import aicore.utils.page.app.DragAndDropBlocksPageUtils;
import aicore.utils.page.app.TemplateCreationUtils;

public class CreateAppAuthorPermission extends AbstractPlaywrightTestBase {
	
	private String appName;

	@BeforeEach
	void setup(@PWPage Page page) {
		loginNativeAdmin(page);
		appName = TemplateCreationUtils.createDragAndDropApp(page, "Drag and Drop");
		verifyAppCreated(page);
		verifyWelcomePage(page);
		CatlogAccessPageUtility.clickOnSettings(page);
		AddFunctionPageUtils.clickOnAccessControl(page);
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
	
	private void verifyAppCreated(Page page) {
	    String appName = CreateAppPopupUtils.userFetchAppName(page);
	    Assertions.assertFalse(appName.isEmpty(), "Fetched App Name is Empty");
	}
	

	private void verifyPrivateToggleEnabled(Page page, String role) {
	    boolean privateToggleVisible = CatlogAccessPageUtility.userCanSeeAndEnablePrivateToggle(page);

	    Assertions.assertTrue(
	            privateToggleVisible,
	            role + " user cannot see the Private Toggle enabled."
	    );
	}
	
	private void verifyNonDiscoverableToggleEnabled(Page page, String role) {
	    boolean nonDiscoverableToggleVisible =
	            CatlogAccessPageUtility.userCanSeeAndEnableNonDiscoverableToggle(page);

	    Assertions.assertTrue(
	            nonDiscoverableToggleVisible,
	            role + " user cannot see the Non-Discoverable toggle enabled."
	    );
	}
	
	private void verifyDeleteCatalogOptionVisible(Page page, String role) {
	    boolean deleteCatalogOptionVisible =
	            CatlogAccessPageUtility.userCanSeeDeleteCatalog(page);

	    Assertions.assertTrue(
	            deleteCatalogOptionVisible,
	            role + " user cannot view the Delete Catalog option."
	    );
	}
	
	@Test
    public void AddDeleteEditorAndReadUser_test (@PWPage Page page) {
		
		String Editor = "Editor";
		String Read = "Read";

		SettingsModelPageUtils.clickOnAddMembersButton(page);
		SettingsModelPageUtils.addMember(page, Editor, GenericSetupUtils.useDocker());
		CatlogAccessPageUtility.searchUserBasedOnRole(page, Editor);
		SettingsModelPageUtils.deleteAddedMember(page, Editor);
		SettingsModelPageUtils.clickOnAddMembersButton(page);
		SettingsModelPageUtils.addMember(page, Read, GenericSetupUtils.useDocker());
		CatlogAccessPageUtility.searchUserBasedOnRole(page, "Viewer");
		SettingsModelPageUtils.deleteAddedMember(page, "Viewer");
	}
	
	@Test
    public void ActionsOnPrivateToggleButton_test (@PWPage Page page) {
		
		verifyPrivateToggleEnabled(page, "Author");
		CatlogAccessPageUtility.setToggleStateForPrivate(page);
		CatlogAccessPageUtility.setToggleStateForPrivate(page);
	
	}

	@Test
    public void ActionsOnNonDiscoverableToggleButton_test (@PWPage Page page) {
		
		AddFunctionPageUtils.clickOnAccessControl(page);
		verifyNonDiscoverableToggleEnabled(page, "Author");
		CatlogAccessPageUtility.setToggleStateForNonDiscovrable(page);
		CatlogAccessPageUtility.setToggleStateForNonDiscovrable(page);

	}
	
	@Test
    public void DeleteApp_test (@PWPage Page page) {
		
		verifyDeleteCatalogOptionVisible(page, "Author");
	}

}
