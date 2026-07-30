package aicore.unit.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;

import aicore.base.GenericSetupUtils;
import aicore.pages.CatalogPermissionsPage;
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

public class ModelCatalogAuthorPermissionsTests extends AbstractPlaywrightTestBase {

	private String modelCatalogName = null;
	private boolean modelCatalogDeletedByTest = false;

	@BeforeEach
	public void setup(@PWPage Page page) {
		loginNativeAdmin(page);

		String timestamp = CommonUtils.getTimeStampName();
		modelCatalogName = "Model" + timestamp;
		ModelTestUtils.addModel(page, "OpenAI", "GPT-4.1", modelCatalogName, "Test@1234");

		String modelId = EditModelPageUtils.getCatalogID(page);
		Assertions.assertNotNull(modelId, "Catalog ID should not be null");

		String actualTitle = ModelPageUtils.verifyModelTitle(page, modelCatalogName);
		Assertions.assertEquals(modelCatalogName, actualTitle, "Model title is not correct");
		modelCatalogDeletedByTest = false;
	}

	@AfterEach
	public void tearDown(@PWPage Page page) {
		loginNativeAdmin(page);
		if (!modelCatalogDeletedByTest) {
			assertTrue(CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_MODEL,
					modelCatalogName));
		}
		logout(page);
	}

	@Test
	public void testAuthorCanViewOverview(@PWPage Page page) {
		CatalogPermissionsPage permissionsPage = new CatalogPermissionsPage(page);
		Assertions.assertTrue(permissionsPage.canViewOverview(), "Author user cannot view the Overview tab");
	}

	@Test
	public void testAuthorCanViewUsage(@PWPage Page page) {
		CatalogPermissionsPage permissionsPage = new CatalogPermissionsPage(page);
		Assertions.assertTrue(permissionsPage.canViewUsage(), "Author user cannot view the Usage tab");
	}

	@Test
	public void testAuthorCanViewSMSSDetails(@PWPage Page page) {
		CatalogPermissionsPage permissionsPage = new CatalogPermissionsPage(page);
		Assertions.assertTrue(permissionsPage.canViewSMSSDetails(), "Author user cannot view the SMSS tab");
	}

	@Test
	public void testAuthorCanViewEditSMSS(@PWPage Page page) {
		ModelPageUtils.clickOnSMSSTab(page);
		CatalogPermissionsPage permissionsPage = new CatalogPermissionsPage(page);
		Assertions.assertTrue(permissionsPage.canViewEditSMSS(), "Author user cannot view the Edit SMSS button");
	}

	@Test
	public void testAuthorCanViewAccessControl(@PWPage Page page) {
		CatalogPermissionsPage permissionsPage = new CatalogPermissionsPage(page);
		Assertions.assertTrue(permissionsPage.canViewAccessControl(), "Author user cannot view the Access Control tab");
	}

	@Test
	public void testAuthorCanSeeMemberSetting(@PWPage Page page) {
		AddFunctionPageUtils.clickOnAccessControl(page);
		Assertions.assertTrue(SettingsModelPageUtils.isAddMemberButtonVisible(page),
				"Author user should be able to see the Add Member button");
	}

	@Test
	public void testAuthorCanAddEditorMember(@PWPage Page page) throws Exception {
		AddFunctionPageUtils.clickOnAccessControl(page);
		SettingsModelPageUtils.clickOnAddMembersButton(page);
		SettingsModelPageUtils.addMember(page, "Editor", GenericSetupUtils.useDocker());

		boolean isUserDisplayed = SettingsModelPageUtils.isUserDisplayedInListAfterRequestAction(page, "editor",
				"editor", GenericSetupUtils.useDocker());
		Assertions.assertTrue(isUserDisplayed,
				"Editor user is not displayed in the members list with the provided permission");
	}

	@Test
	public void testAuthorCanAddReadMember(@PWPage Page page) throws Exception {
		AddFunctionPageUtils.clickOnAccessControl(page);
		SettingsModelPageUtils.clickOnAddMembersButton(page);
		SettingsModelPageUtils.addMember(page, "Read", GenericSetupUtils.useDocker());

		boolean isUserDisplayed = SettingsModelPageUtils.isUserDisplayedInListAfterRequestAction(page, "read",
				"read", GenericSetupUtils.useDocker());
		Assertions.assertTrue(isUserDisplayed,
				"Read user is not displayed in the members list with the provided permission");
	}

	@Test
	public void testAuthorCanDeleteEditorMember(@PWPage Page page) throws Exception {
		AddFunctionPageUtils.clickOnAccessControl(page);
		SettingsModelPageUtils.clickOnAddMembersButton(page);
		SettingsModelPageUtils.addMember(page, "Editor", GenericSetupUtils.useDocker());

		CatalogPermissionsPage permissionsPage = new CatalogPermissionsPage(page);
		permissionsPage.searchUserBasedOnRole("Editor", GenericSetupUtils.useDocker());
		SettingsModelPageUtils.deleteAddedMember(page, "Editor");

		boolean isUserDisplayed = SettingsModelPageUtils.isUserDisplayedInListAfterRequestAction(page, "editor",
				"editor", GenericSetupUtils.useDocker());
		Assertions.assertFalse(isUserDisplayed, "Editor user is still visible in members list after deletion");
	}

	@Test
	public void testAuthorCanDeleteReadMember(@PWPage Page page) throws Exception {
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
	public void testAuthorCanDeleteModel(@PWPage Page page) {
		AddFunctionPageUtils.clickOnAccessControl(page);
		ModelAccessSettingsUtils.clickOnDeleteButton(page);
		Assertions.assertTrue(SettingsModelPageUtils.isDeleteSuccessful(page),
				"Author should be able to delete the catalog");
		modelCatalogDeletedByTest = true;
	}
}
