package aicore.unit.guardrail.add;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import aicore.pages.guardrail.AddGuardrailFormUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.pages.model.AddModelFormUtils;
import aicore.utils.AbstractE2ETest;
import aicore.utils.CatlogAccessPageUtility;
import aicore.utils.CommonUtils;
import aicore.utils.GuardrailPageUtils;
import aicore.utils.TestResourceTrackerHelper;

public class AddGuardrailTests extends AbstractE2ETest {
	@BeforeAll
	public void setup() throws IOException {
		// login with native user before tests
		login(page, UserType.NATIVE);
	}

	@Test
	void testGlinerGuardrail() {
		// inputs
		String timestamp = CommonUtils.getTimeStampName();
		String modelNameType = "Gliner";
		String catalogName = "Gliner guardrail" + timestamp;
		String nerLabel = "label";
		String threshold = "1";

		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnGuardrail(page);
		GuardrailPageUtils.clickOnAddGuardrailButton(page);
		AddModelFormUtils.selectModel(page, modelNameType);
		AddGuardrailFormUtils.enterCatalogName(page, catalogName);
		AddGuardrailFormUtils.enterNerLabels(page, nerLabel);
		AddGuardrailFormUtils.enterDefaultThreshold(page, threshold);
		AddGuardrailFormUtils.clickOnConnectButton(page);

		// get guardrail id
		String id = CatlogAccessPageUtility.getCatalogAndCopyId(page);

		// TODO toast message
//		String toastMessage = "Successfully added new guardrail to catalog";
//		ModelPageUtils.modelCreationToastMessage(page, toastMessage);

		String actualGuardrailTitle = GuardrailPageUtils.verifyGuardrailTitle(page, catalogName);
		Assertions.assertEquals(catalogName, actualGuardrailTitle, "Guardrail title does not match");

		CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_GUARDRAIL, id);
		
		// TODO validate inputs in SMSS file
	}

	@Test
	void testDetoxifyGuardrail() {
		// inputs
		String timestamp = CommonUtils.getTimeStampName();
		String modelNameType = "Detoxify";
		String catalogName = "detoxify guardrail" + timestamp;
		String threshold = "1";

		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnGuardrail(page);
		GuardrailPageUtils.clickOnAddGuardrailButton(page);
		AddModelFormUtils.selectModel(page, modelNameType);
		AddGuardrailFormUtils.enterCatalogName(page, catalogName);
		AddGuardrailFormUtils.enterDefaultThreshold(page, threshold);
		AddGuardrailFormUtils.clickOnConnectButton(page);

		// get guardrail id
		String id = CatlogAccessPageUtility.getCatalogAndCopyId(page);

		// TODO toast message
//		String toastMessage = "Successfully added new guardrail to catalog";
//		ModelPageUtils.modelCreationToastMessage(page, toastMessage);

		String actualGuardrailTitle = GuardrailPageUtils.verifyGuardrailTitle(page, catalogName);
		Assertions.assertEquals(catalogName, actualGuardrailTitle, "Guardrail title does not match");

		CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_GUARDRAIL, id);
		
		// TODO validate inputs in SMSS file
	}

}
