package aicore.unit.DragAndDrop;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;

import aicore.base.GenericSetupUtils;
import aicore.pages.home.HomePageUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.pages.model.SettingsModelPageUtils;
import aicore.pages.model.settings.ModelAccessSettingsUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.AddFunctionPageUtils;
import aicore.utils.CatalogCreationFromZipUtil;
import aicore.utils.CatlogAccessPageUtility;
import aicore.utils.CommonUtils;
import aicore.utils.annotations.PWPage;
import aicore.utils.page.app.AppPageUtils;
import aicore.utils.page.app.CreateAppPopupUtils;
import aicore.utils.page.app.DragAndDropBlocksPageUtils;


public class DragAndDropAppSettingsTests extends AbstractPlaywrightTestBase {

	private static final String APP_NAME = "Test app";

	private String timestamp = "";

	@BeforeEach
	void setup(@PWPage Page page) {
		timestamp = CommonUtils.getTimeStampName();

		loginAdmin(page);

		MainMenuUtils.openMainMenu(page);
		HomePageUtils.navigateToHomePage(page);
		MainMenuUtils.clickOnOpenAppLibrary(page);
		AppPageUtils.clickOnCreateNewAppButton(page);
		CreateAppPopupUtils.clickOnGetStartedButton(page, "Drag and Drop");
		CreateAppPopupUtils.enterAppName(page, APP_NAME + timestamp);
		CreateAppPopupUtils.clickOnCreateButton(page);
		String fetchName = CreateAppPopupUtils.userFetchAppName(page);
		Assertions.assertFalse(fetchName.isEmpty(), "Fetched App Name is Empty");

		boolean isPage1Visible = DragAndDropBlocksPageUtils.verifyPage1IsVisible(page);
		Assertions.assertTrue(isPage1Visible, "Page is not visible");
		boolean isWelcomeTextboxVisible = DragAndDropBlocksPageUtils.verifyWelcomeTextboxIsVisible(page);
		Assertions.assertTrue(isWelcomeTextboxVisible, "Welcome text box not visible");
		String actualWelcomeTextMessage = DragAndDropBlocksPageUtils.verifyWelcomeText(page);
		Assertions.assertEquals("Welcome to the UI Builder! Drag and drop blocks to use in your app.",
				actualWelcomeTextMessage, "Mismatch between the expected and actual message");

		DragAndDropBlocksPageUtils.clickOnBlockSettingsOption(page);
	}

	@AfterEach
	void tearDown(@PWPage Page page) {
		CommonUtils.navigateAndDeleteApp(page, APP_NAME + timestamp);
		logout(page);
	}

	@Test
	@DisplayName("Setting page - Access Control Tab - validate the Member option for drag and drop app")
	void testMemberOptionForDragAndDropApp(@PWPage Page page) throws InterruptedException {
		CatlogAccessPageUtility.clickOnSettings(page);
		AddFunctionPageUtils.clickOnAccessControl(page);

		boolean isAccessSettingsVisible = CatlogAccessPageUtility.userCanSeeSectionUnderSetting(page,
				"Access Settings");
		Assertions.assertTrue(isAccessSettingsVisible, "Access Settings section is not visible");
		boolean isPendingRequestsVisible = CatlogAccessPageUtility.userCanSeeSectionUnderSetting(page,
				"Pending Requests");
		Assertions.assertTrue(isPendingRequestsVisible, "Pending Requests section is not visible");

		SettingsModelPageUtils.clickOnAddMembersButton(page);
		SettingsModelPageUtils.addMember(page, "Editor", GenericSetupUtils.useDocker());
		CatlogAccessPageUtility.searchUserBasedOnRole(page, "Editor");
		SettingsModelPageUtils.deleteAddedMember(page, "Editor");

		SettingsModelPageUtils.clickOnAddMembersButton(page);
		SettingsModelPageUtils.addMember(page, "Read", GenericSetupUtils.useDocker());
		CatlogAccessPageUtility.searchUserBasedOnRole(page, "Read");
		SettingsModelPageUtils.deleteAddedMember(page, "Read");
	}

	@Test
	@DisplayName("Settings page - Setting Tab validate the Apps option for drag and drop app")
	void testAppsOptionForDragAndDropApp(@PWPage Page page) {
		CatlogAccessPageUtility.clickOnSettings(page);
		CatlogAccessPageUtility.clickOnTab(page, "Settings");

		boolean isPortalsVisible = CatlogAccessPageUtility.userCanSeeSectionUnderSetting(page, "Portals");
		Assertions.assertTrue(isPortalsVisible, "Portals section is not visible");

		boolean isPortalEnabled = CatlogAccessPageUtility.isPortalToggleInExpectedState(page, "enable");
		Assertions.assertTrue(isPortalEnabled, "Failed to enable the Publish Portal toggle");

		boolean isPublishButtonEnabled = CatlogAccessPageUtility.clickOnPublishPortalButton(page);
		Assertions.assertTrue(isPublishButtonEnabled, "Publish Portal button is not enabled");
		String publishedToast = CatlogAccessPageUtility.getToastMessage(page, "Successfully published");
		Assertions.assertEquals("Successfully published", publishedToast, "Toaster is not matching with expected");

		boolean isReactorsVisible = CatlogAccessPageUtility.userCanSeeSectionUnderSetting(page, "Reactors");
		Assertions.assertTrue(isReactorsVisible, "Reactors section is not visible");

		CatlogAccessPageUtility.clickOnAppSettingsOption(page, "Compile Changes on This Instance");
		String compiledToast = CatlogAccessPageUtility.getToastMessage(page, "Successfully compiled");
		Assertions.assertEquals("Successfully compiled", compiledToast, "Toaster is not matching with expected");

		CatlogAccessPageUtility.clickOnAppSettingsOption(page, "Deploy and Persist Changes");
		String deployedToast = CatlogAccessPageUtility.getToastMessage(page, "Successfully compiled and deployed");
		Assertions.assertEquals("Successfully compiled and deployed", deployedToast,
				"Toaster is not matching with expected");

		boolean isUpdateProjectVisible = CatlogAccessPageUtility.userCanSeeSectionUnderSetting(page,
				"Update Project");
		Assertions.assertTrue(isUpdateProjectVisible, "Update Project section is not visible");

		CatalogCreationFromZipUtil.uploadFile(page, "dummy-pdf.pdf");
		CatlogAccessPageUtility.clickOnAppSettingsOption(page, "Update");
	}

	
	@Test
	@DisplayName("Setting page - Access Control Tab - validate the General option for drag and drop app")
	void testGeneralOptionForDragAndDropApp(@PWPage Page page) {
		CatlogAccessPageUtility.clickOnSettings(page);
		AddFunctionPageUtils.clickOnAccessControl(page);

		CatlogAccessPageUtility.setToggleStateForPrivate(page);
		String publicMessage = CatlogAccessPageUtility.getToasterMessage(page);
		Assertions.assertTrue(publicMessage.toLowerCase().matches("successfully made .* public".toLowerCase()),
				"Admin user - Expected pattern: Successfully made .* public, but got: " + publicMessage);

		CatlogAccessPageUtility.setToggleStateForPrivate(page);
		String privateMessage = CatlogAccessPageUtility.getToasterMessage(page);
		Assertions.assertTrue(privateMessage.toLowerCase().matches("successfully made .* private".toLowerCase()),
				"Admin user - Expected pattern: Successfully made .* private, but got: " + privateMessage);

		CatlogAccessPageUtility.setToggleStateForNonDiscovrable(page);
		String discoverableMessage = CatlogAccessPageUtility.getToasterMessage(page);
		Assertions.assertTrue(
				discoverableMessage.toLowerCase().matches("successfully made .* discoverable".toLowerCase()),
				"Admin user - Expected pattern: Successfully made .* discoverable, but got: " + discoverableMessage);

		CatlogAccessPageUtility.setToggleStateForNonDiscovrable(page);
		String undiscoverableMessage = CatlogAccessPageUtility.getToasterMessage(page);
		Assertions.assertTrue(
				undiscoverableMessage.toLowerCase().matches("successfully made .* undiscoverable".toLowerCase()),
				"Admin user - Expected pattern: Successfully made .* undiscoverable, but got: " + undiscoverableMessage);

		boolean isDeleteProjectVisible = CatlogAccessPageUtility.userCanSeeSectionUnderGeneralSetting(page,
				"Delete Project");
		Assertions.assertTrue(isDeleteProjectVisible, "Delete Project section is not visible on General setting page");

		ModelAccessSettingsUtils.clickOnDeleteButton(page);
		boolean isDeleteSuccessful = SettingsModelPageUtils.isDeleteSuccessful(page);
		Assertions.assertTrue(isDeleteSuccessful,
				"Admin should be able to delete the catalog, but permission error appeared.");
	}

	@Test
	@DisplayName("Verify the all section are display in MCP Tab along with their code and copy option for drag and drop app")
	void testMcpTabSectionsForDragAndDropApp(@PWPage Page page) {
		CatlogAccessPageUtility.clickOnSettings(page);
		CatlogAccessPageUtility.clickOnTab(page, "MCP Usage");

		boolean isAvailableToolsVisible = CatlogAccessPageUtility.userCanSeeSectionUnderSetting(page,
				"Available Tools");
		Assertions.assertTrue(isAvailableToolsVisible, "Available Tools section is not visible");

		String expectedToast = "Successfully copied to clipboard";
		List<String> sectionNames = Arrays.asList(
				"VS Code (MCP Integration)",
				"Claude Desktop (MCP Server Connection)",
				"Claude with custom backend and MCP (Best for AI Tooling)",
				"OpenAI Codex / CLI Tools (MCP Connection)",
				"Terminal Command (npx mcp-remote)",
				"cURL Command (Manual MCP JSON-RPC Request)",
				"JavaScript (Node.js — fetch / axios)",
				"Python (requests)");

		for (String section : sectionNames) {
			boolean isSectionVisible = CatlogAccessPageUtility.userCanSeeSectionUnderSetting(page, section);
			Assertions.assertTrue(isSectionVisible, section + " section is not visible");
			CatlogAccessPageUtility.clickOnCopyButtonForSection(page, section);
			String actualMessage = AddFunctionPageUtils.verifySuccessToastMessage(page, expectedToast);
			Assertions.assertEquals(expectedToast, actualMessage, "Toast message mismatch for section: " + section
					+ " | Expected: '" + expectedToast + "' but found: '" + actualMessage + "'");
			AddFunctionPageUtils.closeToastMessage(page);
		}
	}
}
