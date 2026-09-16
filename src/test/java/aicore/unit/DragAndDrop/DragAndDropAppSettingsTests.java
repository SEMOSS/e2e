package aicore.unit.DragAndDrop;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;

import aicore.base.GenericSetupUtils;
import aicore.pages.model.SettingsModelPageUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.AddFunctionPageUtils;
import aicore.utils.CatalogCreationFromZipUtil;
import aicore.utils.CatlogAccessPageUtility;
import aicore.utils.CommonUtils;
import aicore.utils.annotations.PWPage;
import aicore.utils.page.app.DragAndDropBlocksPageUtils;
import aicore.utils.page.app.TemplateCreationUtils;

public class DragAndDropAppSettingsTests extends AbstractPlaywrightTestBase {
	private static final Logger logger = LogManager.getLogger(DragAndDropAppSettingsTests.class);
	private String appName = "";

	@BeforeEach
	void setup(@PWPage Page page) {
		loginAdmin(page);
		
		logger.info("BEFORE ALL: creating App");

		appName = TemplateCreationUtils.createDragAndDropApp(page, "Drag and Drop");

		Assertions.assertTrue(DragAndDropBlocksPageUtils.verifyPage1IsVisible(page), "Page is not visible");
		Assertions.assertTrue(DragAndDropBlocksPageUtils.verifyWelcomeTextboxIsVisible(page),
				"Welcome text box not visible");
		Assertions.assertEquals("Welcome to the UI Builder! Drag and drop blocks to use in your app.",
				DragAndDropBlocksPageUtils.verifyWelcomeText(page), "Mismatch between the expected and actual message");

		// BlockSettingsUtils.closeBlockSettings(page);

		
		DragAndDropBlocksPageUtils.clickOnBlockSettingsOption(page);

	}

	@AfterEach
	void tearDown(@PWPage Page page) {
		logger.info("After ALL: Delete App");
		CommonUtils.navigateAndDeleteApp(page, appName);
		logout(page);
	}
	
	private void assertSectionVisible(Page page, String sectionName) {
		Assertions.assertTrue(CatlogAccessPageUtility.userCanSeeSectionUnderSetting(page, sectionName),
				sectionName + " section is not visible");
	}

	private void assertToastMessage(Page page, String expectedToast) {
		Assertions.assertEquals(expectedToast, CatlogAccessPageUtility.getToastMessage(page, expectedToast),
				"Toaster is not matching with expected");
	}

	private void addThenRemoveMember(Page page, String role) {
		SettingsModelPageUtils.clickOnAddMembersButton(page);
		SettingsModelPageUtils.addMember(page, role, GenericSetupUtils.useDocker());
		CatlogAccessPageUtility.searchUserBasedOnRole(page, role);
		SettingsModelPageUtils.deleteAddedMember(page, role);
	}
	
	

	private void assertToggleToastMatches(Page page, Runnable toggleAction, String expectedWord) {
		toggleAction.run();
		String message = CatlogAccessPageUtility.getToasterMessage(page);
		Assertions.assertTrue(message.toLowerCase().matches("successfully made .* " + expectedWord),
				"Expected pattern: Successfully made .* " + expectedWord + ", but got: " + message);
	}

	@Test
	@DisplayName("TC01_Setting page - Access Control Tab - validate the Member option for drag and drop app")
	void testMemberOptionForDragAndDropApp(@PWPage Page page) {
		
		CatlogAccessPageUtility.clickOnSettings(page);
		AddFunctionPageUtils.clickOnAccessControl(page);

		assertSectionVisible(page, "Access Settings");
		assertSectionVisible(page, "Pending Requests");

		addThenRemoveMember(page, "Editor");
		addThenRemoveMember(page, "Read");
	}
	
	

	@Test
	@DisplayName("TC02_Settings page - Setting Tab validate the Apps option for drag and drop app")
	void testAppsOptionForDragAndDropApp(@PWPage Page page) {
		CatlogAccessPageUtility.clickOnSettings(page);
		CatlogAccessPageUtility.clickOnTab(page, "Settings");

		assertSectionVisible(page, "Portals");
		Assertions.assertTrue(CatlogAccessPageUtility.isPortalToggleInExpectedState(page, "enable"),
				"Failed to enable the Publish Portal toggle");
		Assertions.assertTrue(CatlogAccessPageUtility.clickOnPublishPortalButton(page),
				"Publish Portal button is not enabled");
		assertToastMessage(page, "Successfully published");

		assertSectionVisible(page, "Reactors");
		CatlogAccessPageUtility.clickOnAppSettingsOption(page, "Compile Changes on This Instance");
		assertToastMessage(page, "Successfully compiled");

		CatlogAccessPageUtility.clickOnAppSettingsOption(page, "Deploy and Persist Changes");
		assertToastMessage(page, "Successfully compiled and deployed");

		assertSectionVisible(page, "Update Project");
		//review and add to test data 
		CatalogCreationFromZipUtil.uploadFile(page, "dummy-pdf.pdf");
		CatlogAccessPageUtility.clickOnAppSettingsOption(page, "Update");
	}

	@Test
	@DisplayName("TC03_Setting page - Access Control Tab - validate the General option for drag and drop app")
	void testGeneralOptionForDragAndDropApp(@PWPage Page page) {
		CatlogAccessPageUtility.clickOnSettings(page);
		AddFunctionPageUtils.clickOnAccessControl(page);

		assertToggleToastMatches(page, () -> CatlogAccessPageUtility.setToggleStateForPrivate(page), "public");
		assertToggleToastMatches(page, () -> CatlogAccessPageUtility.setToggleStateForPrivate(page), "private");
		assertToggleToastMatches(page, () -> CatlogAccessPageUtility.setToggleStateForNonDiscovrable(page),
				"discoverable");
		assertToggleToastMatches(page, () -> CatlogAccessPageUtility.setToggleStateForNonDiscovrable(page),
				"undiscoverable");

		Assertions.assertTrue(CatlogAccessPageUtility.userCanSeeSectionUnderGeneralSetting(page, "Delete Project"),
				"Delete Project section is not visible on General setting page");

		// Is Already in aftermethod
		//ModelAccessSettingsUtils.clickOnDeleteButton(page);
		//Assertions.assertTrue(SettingsModelPageUtils.isDeleteSuccessful(page),
				//"Admin should be able to delete the catalog, but permission error appeared.");
	}

	@Test
	@DisplayName("TC04_Verify the all section are display in MCP Tab along with their code and copy option for drag and drop app")
	void testMcpTabSectionsForDragAndDropApp(@PWPage Page page) {
		CatlogAccessPageUtility.clickOnSettings(page);
		CatlogAccessPageUtility.clickOnTab(page, "MCP Usage");

		assertSectionVisible(page, "Available Tools");

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
			assertSectionVisible(page, section);
			CatlogAccessPageUtility.clickOnCopyButtonForSection(page, section);
			String actualMessage = AddFunctionPageUtils.verifySuccessToastMessage(page, expectedToast);
			Assertions.assertEquals(expectedToast, actualMessage, "Toast message mismatch for section: " + section
					+ " | Expected: '" + expectedToast + "' but found: '" + actualMessage + "'");
			AddFunctionPageUtils.closeToastMessage(page);
		}
	}


}
