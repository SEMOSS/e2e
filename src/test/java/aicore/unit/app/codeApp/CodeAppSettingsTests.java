package aicore.unit.app.codeApp;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;

import aicore.base.GenericSetupUtils;
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
import aicore.utils.settings.JobPageUtils;

public class CodeAppSettingsTests extends AbstractPlaywrightTestBase {

		private String testAppName = "";

		@BeforeEach
		void setup(@PWPage Page page) {
			String timestamp = CommonUtils.getTimeStampName();
			testAppName = "Code app " + timestamp;

			loginAdmin(page);

			MainMenuUtils.openMainMenu(page);
			MainMenuUtils.clickOnOpenAppLibrary(page);
			AppPageUtils.clickOnCreateNewAppButton(page);
			CreateAppPopupUtils.clickOnGetStartedButton(page, "Develop in code");
			CreateAppPopupUtils.enterAppName(page, testAppName);
			CreateAppPopupUtils.enterAppDescription(page, "Created by automation script");
			CreateAppPopupUtils.enterTags(page, "MCP");
			CreateAppPopupUtils.clickOnCreateButton(page);
			String fetchName = CreateAppPopupUtils.userFetchAppName(page);
			Assertions.assertFalse(fetchName.isEmpty(), "Fetched App Name is Empty");
		}

		@AfterEach
		void tearDown(@PWPage Page page) {
			CommonUtils.navigateAndDeleteApp(page, testAppName);
			logout(page);
		}

		
		@Test
		void testAccessControlTabValidateMemberOptionForCodeApp(@PWPage Page page) throws InterruptedException {
			CatlogAccessPageUtility.clickOnSettings(page);
			AddFunctionPageUtils.clickOnAccessControl(page);

			boolean isAccessSettingsVisible = CatlogAccessPageUtility.userCanSeeSectionUnderSetting(page, "Access Settings");
			Assertions.assertTrue(isAccessSettingsVisible, "Access Settings section is not visible");

			boolean isPendingRequestsVisible = CatlogAccessPageUtility.userCanSeeSectionUnderSetting(page, "Pending Requests");
			Assertions.assertTrue(isPendingRequestsVisible, "Pending Requests section is not visible");

			SettingsModelPageUtils.clickOnAddMembersButton(page);
			SettingsModelPageUtils.addMember(page, "Editor", GenericSetupUtils.useDocker());
			CatlogAccessPageUtility.searchUser(page, "Editor", GenericSetupUtils.useDocker());
			SettingsModelPageUtils.deleteAddedMember(page, "Editor");

			SettingsModelPageUtils.clickOnAddMembersButton(page);
			SettingsModelPageUtils.addMember(page, "Read", GenericSetupUtils.useDocker());
			CatlogAccessPageUtility.searchUser(page, "Read", GenericSetupUtils.useDocker());
			SettingsModelPageUtils.deleteAddedMember(page, "Read");
		}

		
		@Test
		void testSettingsTabValidateAppsOptionForCodeApp(@PWPage Page page) throws Exception {
			CatlogAccessPageUtility.clickOnSettings(page);
			CatlogAccessPageUtility.clickOnTab(page, "Settings");

			boolean isPortalsVisible = CatlogAccessPageUtility.userCanSeeSectionUnderSetting(page, "Portals");
			Assertions.assertTrue(isPortalsVisible, "Portals section is not visible");

			boolean isPortalEnabled = CatlogAccessPageUtility.isPortalToggleInExpectedState(page, "enable");
			Assertions.assertTrue(isPortalEnabled, "Failed to enable the Publish Portal toggle");

			boolean isPublishButtonEnabled = CatlogAccessPageUtility.clickOnPublishPortalButton(page);
			Assertions.assertTrue(isPublishButtonEnabled, "Publish Portal button is not enabled");

			String expectedPublishToast = "Successfully published";
			String actualPublishToast = AddFunctionPageUtils.verifySuccessToastMessage(page, expectedPublishToast);
			Assertions.assertEquals(expectedPublishToast, actualPublishToast, "Toaster is not matching with expected");
			AddFunctionPageUtils.closeToastMessage(page);

			boolean isReactorsVisible = CatlogAccessPageUtility.userCanSeeSectionUnderSetting(page, "Reactors");
			Assertions.assertTrue(isReactorsVisible, "Reactors section is not visible");

			CatlogAccessPageUtility.clickOnAppSettingsOption(page, "Compile Changes on This Instance");
			String expectedCompileToast = "Successfully compiled";
			String actualCompileToast = AddFunctionPageUtils.verifySuccessToastMessage(page, expectedCompileToast);
			Assertions.assertEquals(expectedCompileToast, actualCompileToast, "Toaster is not matching with expected");
			AddFunctionPageUtils.closeToastMessage(page);

			CatlogAccessPageUtility.clickOnAppSettingsOption(page, "Deploy and Persist Changes");
			String expectedDeployToast = "Successfully compiled and deployed";
			String actualDeployToast = AddFunctionPageUtils.verifySuccessToastMessage(page, expectedDeployToast);
			Assertions.assertEquals(expectedDeployToast, actualDeployToast, "Toaster is not matching with expected");
			AddFunctionPageUtils.closeToastMessage(page);

			boolean isUpdateProjectVisible = CatlogAccessPageUtility.userCanSeeSectionUnderSetting(page, "Update Project");
			Assertions.assertTrue(isUpdateProjectVisible, "Update Project section is not visible");

			String fileToUpload = "dummy-pdf.pdf";
			String uploadedFileName = CatalogCreationFromZipUtil.uploadFile(page, fileToUpload);
			Assertions.assertEquals(fileToUpload, uploadedFileName, "file is not uploaded successfully");

			CatlogAccessPageUtility.clickOnAppSettingsOption(page, "Update");
		}

		
		@Test
		void testAccessControlTabValidateGeneralOptionForCodeApp(@PWPage Page page) {
			CatlogAccessPageUtility.clickOnSettings(page);
			AddFunctionPageUtils.clickOnAccessControl(page);

			CatlogAccessPageUtility.setToggleStateForPrivate(page); // turn OFF Private
			String publicToast = CatlogAccessPageUtility.getToasterMessage(page);
			Assertions.assertTrue(publicToast.toLowerCase().matches("successfully made .* public"),
					"Expected pattern: 'Successfully made .* public', but got: " + publicToast);

			CatlogAccessPageUtility.setToggleStateForPrivate(page); // turn ON Private
			String privateToast = CatlogAccessPageUtility.getToasterMessage(page);
			Assertions.assertTrue(privateToast.toLowerCase().matches("successfully made .* private"),
					"Expected pattern: 'Successfully made .* private', but got: " + privateToast);

			CatlogAccessPageUtility.setToggleStateForNonDiscovrable(page); // turn OFF Non Discoverable
			String discoverableToast = CatlogAccessPageUtility.getToasterMessage(page);
			Assertions.assertTrue(discoverableToast.toLowerCase().matches("successfully made .* discoverable"),
					"Expected pattern: 'Successfully made .* discoverable', but got: " + discoverableToast);

			CatlogAccessPageUtility.setToggleStateForNonDiscovrable(page); // turn ON Non Discoverable
			String undiscoverableToast = CatlogAccessPageUtility.getToasterMessage(page);
			Assertions.assertTrue(undiscoverableToast.toLowerCase().matches("successfully made .* undiscoverable"),
					"Expected pattern: 'Successfully made .* undiscoverable', but got: " + undiscoverableToast);

			boolean isDeleteSectionVisible = CatlogAccessPageUtility.userCanSeeSectionUnderGeneralSetting(page,
					"Delete Project");
			Assertions.assertTrue(isDeleteSectionVisible, "Delete Project section is not visible");

			ModelAccessSettingsUtils.clickOnDeleteButton(page);
			boolean isDeleteSuccessful = SettingsModelPageUtils.isDeleteSuccessful(page);
			Assertions.assertTrue(isDeleteSuccessful,
					"Admin user should be able to delete the catalog, but permission error appeared.");
		}

		
		@Test
		void testMcpUsageTabSectionsAndCopyButtons(@PWPage Page page) {
			CatlogAccessPageUtility.clickOnSettings(page);
			JobPageUtils.clickOnTab(page, "MCP Usage");

			boolean isAvailableToolsVisible = CatlogAccessPageUtility.userCanSeeSectionUnderSetting(page, "Available Tools");
			Assertions.assertTrue(isAvailableToolsVisible, "Available Tools section is not visible");

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
				boolean isSectionVisible = CatlogAccessPageUtility.userCanSeeSectionUnderSetting(page, section);
				Assertions.assertTrue(isSectionVisible, section + " section is not visible");

				CatlogAccessPageUtility.clickOnCopyButtonForSection(page, section);
				String actualToast = AddFunctionPageUtils.verifySuccessToastMessage(page, expectedToast);
				Assertions.assertEquals(expectedToast, actualToast,
						"Toast message mismatch for section: " + section);
				AddFunctionPageUtils.closeToastMessage(page);
			}
		}
	}

