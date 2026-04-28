package aicore.unit.guardrail;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import aicore.hooks.SetupHooks;
import aicore.pages.guardrail.AddGuardrailFormUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.utils.AbstractE2ETest;
import aicore.utils.CommonUtils;
import aicore.utils.TestResourceTrackerHelper;
import aicore.utils.page.app.AppPageUtils;

public class AllGuardrailSortTests extends AbstractE2ETest {
	private static String ABC_GUARDRAIL_ID = null;
	private static String XYZ_GUARDRAIL_ID = null;

	@BeforeAll
	public static void setupBeforeAll() throws IOException {
		// login with native user before tests
		login(page, UserType.NATIVE);

		// inputs
		String timestamp = CommonUtils.getTimeStampName();
		String modelNameType = "Gliner";
		String catalogName = "abc guardrail" + timestamp;
		String nerLabel = "label";
		String threshold = "1";
		ABC_GUARDRAIL_ID = AddGuardrailFormUtils.createGuardrail(page, modelNameType, catalogName, nerLabel, threshold);

		// inputs
		String timestamp2 = CommonUtils.getTimeStampName();
		String catalogName2 = "xyz guardrail" + timestamp2;
		XYZ_GUARDRAIL_ID = AddGuardrailFormUtils.createGuardrail(page, modelNameType, catalogName2, nerLabel,
				threshold);
	}

	@BeforeEach
	public void setup() throws IOException {
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnGuardrail(SetupHooks.getPage());
	}
	
	@Test
	void testNameSort() {
		AppPageUtils.clickOnFilterButton(page, "Ascending");
		boolean isSortedInAscendingOrder = AppPageUtils.verifySortedInAscendingOrder(SetupHooks.getPage());
		Assertions.assertTrue(isSortedInAscendingOrder, "Guardrails are not sorted in ascending order");

		AppPageUtils.clickOnFilterButton(page, "Descending");
		boolean isSortedInDescendingOrder = AppPageUtils.verifySortedInDescendingOrder(SetupHooks.getPage());
		Assertions.assertTrue(isSortedInDescendingOrder, "Guardrails are not sorted in descending order");
	}
	
	@Test
	void testDateCreatedSort() {
		AppPageUtils.selectSortByOption(page, "Date Created");
		AppPageUtils.clickOnFilterButton(page, "Ascending");
		boolean isSortedByDateCreatedAsc = AppPageUtils.verifySortedByDateCreated(SetupHooks.getPage(), true);
		Assertions.assertTrue(isSortedByDateCreatedAsc,  "Guardrails are not sorted by date created in ascending order");

		AppPageUtils.clickOnFilterButton(page, "Descending");
		boolean isSortedByDateCreatedDesc = AppPageUtils.verifySortedByDateCreated(SetupHooks.getPage(), false);
		Assertions.assertTrue(isSortedByDateCreatedDesc,   "Guardrails are not sorted by date created in descending order");
	}

	@AfterAll
	static void deleteGuardrail() {
		CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_GUARDRAIL, ABC_GUARDRAIL_ID);
		CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_GUARDRAIL, XYZ_GUARDRAIL_ID);

	}

}
