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
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.AddFunctionPageUtils;
import aicore.utils.CommonUtils;
import aicore.utils.ModelTestUtils;
import aicore.utils.TestResourceTrackerHelper;
import aicore.utils.annotations.PWPage;
import aicore.utils.page.model.ModelPageUtils;

public class ModelCatalogReadOnlyPermissionsTests extends AbstractPlaywrightTestBase {

	private String modelCatalogName = null;
	private String modelCatalogId = null;

	@BeforeEach
	public void setup(@PWPage Page page) throws Exception {
		loginNativeAdmin(page);

		String timestamp = CommonUtils.getTimeStampName();
		modelCatalogName = "Catalog" + timestamp;
		ModelTestUtils.addModel(page, "OpenAI", "GPT-4.1", modelCatalogName, "Test@1234");

		modelCatalogId = EditModelPageUtils.getCatalogID(page);
		String actualTitle = ModelPageUtils.verifyModelTitle(page, modelCatalogName);
		Assertions.assertEquals(modelCatalogName, actualTitle, "Model title is not correct");

		AddFunctionPageUtils.clickOnAccessControl(page);
		SettingsModelPageUtils.clickOnAddMembersButton(page);
		SettingsModelPageUtils.addMember(page, "Read", GenericSetupUtils.useDocker());

		logout(page);
		loginReadOnly(page);
		openModelCatalog(page, modelCatalogName);

		actualTitle = ModelPageUtils.verifyModelTitle(page, modelCatalogName);
		Assertions.assertEquals(modelCatalogName, actualTitle, "Model title is not correct");

		CatalogPermissionsPage permissionsPage = new CatalogPermissionsPage(page);
		Assertions.assertTrue(permissionsPage.canViewOverview(), "Read user cannot view the Overview tab");
	}

	@AfterEach
	public void tearDown(@PWPage Page page) {
		try {
			logout(page);
		} catch (Exception e) {
			// Ignore logout errors and continue with admin login for cleanup.
		}
		loginNativeAdmin(page);
		boolean isDeleted = false;
		if (modelCatalogId != null && !modelCatalogId.isBlank()) {
			isDeleted = CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_MODEL,
					modelCatalogId);
		}
		if (!isDeleted && modelCatalogName != null && !modelCatalogName.isBlank()) {
			isDeleted = CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_MODEL,
					modelCatalogName);
		}
		assertTrue(isDeleted, "Failed to delete model catalog during teardown. Name: " + modelCatalogName + ", ID: "
				+ modelCatalogId);
		logout(page);
	}

	@Test
	public void testReadCanViewUsage(@PWPage Page page) {
		CatalogPermissionsPage permissionsPage = new CatalogPermissionsPage(page);
		Assertions.assertTrue(permissionsPage.canViewUsage(), "Read user cannot view the Usage tab");
	}

	@Test
	public void testReadCannotViewSMSSDetails(@PWPage Page page) {
		CatalogPermissionsPage permissionsPage = new CatalogPermissionsPage(page);
		Assertions.assertFalse(permissionsPage.canViewSMSSDetails(), "Read user should not view the SMSS tab");
	}

	@Test
	public void testReadCannotViewEditSMSS(@PWPage Page page) {
		CatalogPermissionsPage permissionsPage = new CatalogPermissionsPage(page);
		Assertions.assertFalse(permissionsPage.canViewSMSSDetails(), "Read user should not view the SMSS tab");
		Assertions.assertFalse(permissionsPage.canViewEditSMSS(), "Read user should not view the Edit SMSS button");
	}

	@Test
	public void testReadCannotViewAccessControl(@PWPage Page page) {
		CatalogPermissionsPage permissionsPage = new CatalogPermissionsPage(page);
		Assertions.assertFalse(permissionsPage.canViewAccessControl(), "Read user should not view the Access Control tab");
	}

	@Test
	public void testReadCannotSeeMemberSetting(@PWPage Page page) {
		CatalogPermissionsPage permissionsPage = new CatalogPermissionsPage(page);
		Assertions.assertFalse(permissionsPage.canViewAccessControl(), "Read user should not view the Access Control tab");
		Assertions.assertFalse(SettingsModelPageUtils.isAddMemberButtonVisible(page),
				"Read user should not be able to see the Add Member button");
	}

	@Test
	public void testReadCannotDeleteModel(@PWPage Page page) {
		CatalogPermissionsPage permissionsPage = new CatalogPermissionsPage(page);
		Assertions.assertFalse(permissionsPage.canViewAccessControl(), "Read user should not view the Access Control tab");
	}

	private void openModelCatalog(Page page, String catalogName) {
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenModel(page);
		EditModelPageUtils.searchModelCatalog(page, catalogName);
		EditModelPageUtils.selectModelFromSearchOptions(page, catalogName);
	}
}
