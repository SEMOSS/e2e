package aicore.unit.app.codeApp;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;

import aicore.base.GenericSetupUtils;
import aicore.pages.model.SettingsModelPageUtils;
import aicore.pages.model.settings.ModelAccessSettingsUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.AddFunctionPageUtils;
import aicore.utils.CatalogCreationFromZipUtil;
import aicore.utils.CatlogAccessPageUtility;
import aicore.utils.CommonUtils;
import aicore.utils.annotations.PWPage;
import aicore.utils.page.app.TemplateCreationUtils;
import aicore.utils.settings.JobPageUtils;

public class CodeAppSettingsTests extends AbstractPlaywrightTestBase {

	private String testAppName = "";

	@BeforeEach
	void setup(@PWPage Page page) {
		loginAdmin(page);
		testAppName = TemplateCreationUtils.createCodeApp(page);
	}

	@AfterEach
	void tearDown(@PWPage Page page) {
		CommonUtils.navigateAndDeleteApp(page, testAppName);
		logout(page);
	}
	
	
	private void assertSectionVisible(Page page, String sectionName) {
		Assertions.assertTrue(CatlogAccessPageUtility.userCanSeeSectionUnderSetting(page, sectionName),
				sectionName + " section is not visible");
	}

	private void assertToastMessage(Page page, String expectedToast) {
		String actualToast = AddFunctionPageUtils.verifySuccessToastMessage(page, expectedToast);
		Assertions.assertEquals(expectedToast, actualToast, "Toaster is not matching with expected");
		AddFunctionPageUtils.closeToastMessage(page);
	}

	private void addThenRemoveMember(Page page, String role) {
		SettingsModelPageUtils.clickOnAddMembersButton(page);
		SettingsModelPageUtils.addMember(page, role, GenericSetupUtils.useDocker());
		CatlogAccessPageUtility.searchUser(page, role, GenericSetupUtils.useDocker());
		SettingsModelPageUtils.deleteAddedMember(page, role);
	}

	private void assertToggleToastMatches(Page page, Runnable toggleAction, String expectedWord) {
		toggleAction.run();
		String message = CatlogAccessPageUtility.getToasterMessage(page);
		Assertions.assertTrue(message.toLowerCase().matches("successfully made .* " + expectedWord),
				"Expected pattern: 'Successfully made .* " + expectedWord + "', but got: " + message);
	}

	@Test
	void testAccessControlTabValidateMemberOptionForCodeApp(@PWPage Page page) {
		CatlogAccessPageUtility.clickOnSettings(page);
		AddFunctionPageUtils.clickOnAccessControl(page);

		assertSectionVisible(page, "Access Settings");
		assertSectionVisible(page, "Pending Requests");

		addThenRemoveMember(page, "Editor");
		addThenRemoveMember(page, "Read");
	}

	@Test
	void testSettingsTabValidateAppsOptionForCodeApp(@PWPage Page page) {
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
		String fileToUpload = "dummy-pdf.pdf";
		String uploadedFileName = CatalogCreationFromZipUtil.uploadFile(page, fileToUpload);
		Assertions.assertEquals(fileToUpload, uploadedFileName, "file is not uploaded successfully");

		CatlogAccessPageUtility.clickOnAppSettingsOption(page, "Update");
	}

	@Test
	void testAccessControlTabValidateGeneralOptionForCodeApp(@PWPage Page page) {
		CatlogAccessPageUtility.clickOnSettings(page);
		AddFunctionPageUtils.clickOnAccessControl(page);

		assertToggleToastMatches(page, () -> CatlogAccessPageUtility.setToggleStateForPrivate(page), "public");
		assertToggleToastMatches(page, () -> CatlogAccessPageUtility.setToggleStateForPrivate(page), "private");
		assertToggleToastMatches(page, () -> CatlogAccessPageUtility.setToggleStateForNonDiscovrable(page),
				"discoverable");
		assertToggleToastMatches(page, () -> CatlogAccessPageUtility.setToggleStateForNonDiscovrable(page),
				"undiscoverable");

		Assertions.assertTrue(CatlogAccessPageUtility.userCanSeeSectionUnderGeneralSetting(page, "Delete Project"),
				"Delete Project section is not visible");

		ModelAccessSettingsUtils.clickOnDeleteButton(page);
		Assertions.assertTrue(SettingsModelPageUtils.isDeleteSuccessful(page),
				"Admin user should be able to delete the catalog, but permission error appeared.");
	}

	@Test
	void testMcpUsageTabSectionsAndCopyButtons(@PWPage Page page) {
		CatlogAccessPageUtility.clickOnSettings(page);
		JobPageUtils.clickOnTab(page, "MCP Usage");

		assertSectionVisible(page, "Available Tools");

		List<String> sections = List.of(
				"VS Code (MCP Integration)",
				"Claude Desktop (MCP Server Connection)",
				"Claude with custom backend and MCP (Best for AI Tooling)",
				"OpenAI Codex / CLI Tools (MCP Connection)",
				"Terminal Command (npx mcp-remote)",
				"cURL Command (Manual MCP JSON-RPC Request)",
				"JavaScript (Node.js — fetch / axios)",
				"Python (requests)");

		String expectedToast = "Successfully copied to clipboard";
		for (String section : sections) {
			assertSectionVisible(page, section);

			CatlogAccessPageUtility.clickOnCopyButtonForSection(page, section);
			String actualToast = AddFunctionPageUtils.verifySuccessToastMessage(page, expectedToast);
			Assertions.assertEquals(expectedToast, actualToast,
					"Toast message mismatch for section: " + section);
			AddFunctionPageUtils.closeToastMessage(page);
		}
	}


}

