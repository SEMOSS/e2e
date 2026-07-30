package aicore.unit.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;

import aicore.base.GenericSetupUtils;
import aicore.pages.CatalogPermissionsPage;
import aicore.pages.home.MainMenuUtils;
import aicore.pages.model.EditModelPageUtils;
import aicore.pages.model.SettingsModelPageUtils;
import aicore.pages.model.settings.ModelAccessSettingsUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.AddFunctionPageUtils;
import aicore.utils.CommonUtils;
import aicore.utils.ModelTestUtils;
import aicore.utils.TestResourceTrackerHelper;
import aicore.utils.annotations.PWPage;
import aicore.utils.page.model.ModelPageUtils;

public class ModelCatalogEditorPermissionsTests extends AbstractPlaywrightTestBase {

	private String modelCatalogName = null;
	private String modelCatalogId = null;
	private boolean modelCatalogDeletedByTest = false;

	@BeforeEach
	public void setup(@PWPage Page page) throws Exception {
		loginNativeAdmin(page);

		String timestamp = CommonUtils.getTimeStampName();
		modelCatalogName = "Catalog" + timestamp;
		ModelTestUtils.addModel(page, "OpenAI", "GPT-4.1", modelCatalogName, "Test@1234");

		String actualTitle = ModelPageUtils.verifyModelTitle(page, modelCatalogName);
		Assertions.assertEquals(modelCatalogName, actualTitle, "Model title is not correct");
		modelCatalogId = EditModelPageUtils.getCatalogID(page);

		AddFunctionPageUtils.clickOnAccessControl(page);
		SettingsModelPageUtils.clickOnAddMembersButton(page);
		SettingsModelPageUtils.addMember(page, "Editor", GenericSetupUtils.useDocker());

		logout(page);
		loginEditor(page);
		openModelCatalog(page, modelCatalogName);

		actualTitle = ModelPageUtils.verifyModelTitle(page, modelCatalogName);
		Assertions.assertEquals(modelCatalogName, actualTitle, "Model title is not correct");

		CatalogPermissionsPage permissionsPage = new CatalogPermissionsPage(page);
		Assertions.assertTrue(permissionsPage.canViewOverview(), "Editor user cannot view the Overview tab");
		modelCatalogDeletedByTest = false;
	}

	@AfterEach
	public void tearDown(@PWPage Page page) {
		try {
			logout(page);
		} catch (Exception e) {
			// Ignore logout errors and continue with admin login for cleanup.
		}
		loginNativeAdmin(page);
		if (!modelCatalogDeletedByTest) {
			boolean isDeleted = false;
			if (modelCatalogId != null && !modelCatalogId.isBlank()) {
				isDeleted = CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_MODEL,
						modelCatalogId);
			}
			if (!isDeleted && modelCatalogName != null && !modelCatalogName.isBlank()) {
				isDeleted = CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_MODEL,
						modelCatalogName);
			}
			assertTrue(isDeleted,
					"Failed to delete model catalog during teardown. Name: " + modelCatalogName + ", ID: "
							+ modelCatalogId);
		}
		logout(page);
	}

	@Test
	public void testEditorCanViewUsage(@PWPage Page page) {
		CatalogPermissionsPage permissionsPage = new CatalogPermissionsPage(page);
		Assertions.assertTrue(permissionsPage.canViewUsage(), "Editor user cannot view the Usage tab");
	}

	@Test
	public void testEditorCannotViewSMSSDetails(@PWPage Page page) {
		CatalogPermissionsPage permissionsPage = new CatalogPermissionsPage(page);
		Assertions.assertFalse(permissionsPage.canViewSMSSDetails(), "Editor user should not view the SMSS tab");
	}

	@Test
	public void testEditorCannotViewEditSMSS(@PWPage Page page) {
		CatalogPermissionsPage permissionsPage = new CatalogPermissionsPage(page);
		Assertions.assertFalse(permissionsPage.canViewSMSSDetails(), "Editor user should not view the SMSS tab");
		Assertions.assertFalse(permissionsPage.canViewEditSMSS(), "Editor user should not view the Edit SMSS button");
	}

	@Test
	public void testEditorCanViewAccessControl(@PWPage Page page) {
		CatalogPermissionsPage permissionsPage = new CatalogPermissionsPage(page);
		Assertions.assertTrue(permissionsPage.canViewAccessControl(), "Editor user cannot view the Access Control tab");
	}

	@Test
	public void testEditorCanSeeMemberSetting(@PWPage Page page) {
		AddFunctionPageUtils.clickOnAccessControl(page);
		Assertions.assertTrue(SettingsModelPageUtils.isAddMemberButtonVisible(page),
				"Editor user should be able to see the Add Member button");
	}

	@Test
	public void testEditorCanAddReadMember(@PWPage Page page) throws Exception {
		AddFunctionPageUtils.clickOnAccessControl(page);
		SettingsModelPageUtils.clickOnAddMembersButton(page);
		SettingsModelPageUtils.addMember(page, "Read", GenericSetupUtils.useDocker());

		logout(page);
		loginReadOnly(page);
		openModelCatalog(page, modelCatalogName);
		String actualTitle = ModelPageUtils.verifyModelTitle(page, modelCatalogName);
		Assertions.assertEquals(modelCatalogName, actualTitle, "Model title is not correct");
	}

	@Test
	public void testEditorCanDeleteReadMember(@PWPage Page page) throws Exception {
		AddFunctionPageUtils.clickOnAccessControl(page);
		SettingsModelPageUtils.clickOnAddMembersButton(page);
		SettingsModelPageUtils.addMember(page, "Read", GenericSetupUtils.useDocker());

		CatalogPermissionsPage permissionsPage = new CatalogPermissionsPage(page);
		permissionsPage.searchUserBasedOnRole("Read", GenericSetupUtils.useDocker());
		SettingsModelPageUtils.deleteAddedMember(page, "Read");

		boolean isUserDisplayed = SettingsModelPageUtils.isUserDisplayedInListAfterRequestAction(page, "read", "read",
				GenericSetupUtils.useDocker());
		Assertions.assertFalse(isUserDisplayed, "Read user is still visible in members list after deletion");
	}

	@Test
	public void testAuthorCanDeleteModelFromEditorScenario(@PWPage Page page) {
		logout(page);
		loginNativeAdmin(page);
		openModelCatalog(page, modelCatalogName);
		AddFunctionPageUtils.clickOnAccessControl(page);
		ModelAccessSettingsUtils.clickOnDeleteButton(page);
		Assertions.assertTrue(SettingsModelPageUtils.isDeleteSuccessful(page),
				"Author should be able to delete the catalog");
		modelCatalogDeletedByTest = true;
	}

	private void openModelCatalog(Page page, String catalogName) {
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenModel(page);
		EditModelPageUtils.searchModelCatalog(page, catalogName);
		EditModelPageUtils.selectModelFromSearchOptions(page, catalogName);
	}
}
