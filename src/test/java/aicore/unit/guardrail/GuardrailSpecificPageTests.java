package aicore.unit.guardrail;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import aicore.pages.guardrail.AddGuardrailFormUtils;
import aicore.utils.AbstractE2ETest;
import aicore.utils.CommonUtils;
import aicore.utils.TestResourceTrackerHelper;

public class GuardrailSpecificPageTests extends AbstractE2ETest {

	private static String GUARDRAIL_ID = null;

	@BeforeAll
	public static void setupBeforeAll() throws IOException {
		login(page, UserType.NATIVE);

		String timestamp = CommonUtils.getTimeStampName();
		String modelNameType = "Gliner";
		String catalogName = "guardrail" + timestamp;
		String nerLabel = "label";
		String threshold = "1";
		GUARDRAIL_ID = AddGuardrailFormUtils.createGuardrail(page, modelNameType, catalogName, nerLabel, threshold);

	}

	@AfterAll
	static void deleteGuardrail() {
		CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_GUARDRAIL, GUARDRAIL_ID);
	}
}
