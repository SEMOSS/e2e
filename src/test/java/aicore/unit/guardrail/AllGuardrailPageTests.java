package aicore.unit.guardrail;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import aicore.pages.base.EditMetadataPageUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.pages.model.EditModelPageUtils;
import aicore.utils.AbstractE2ETest;
import aicore.utils.AddDatabasePageUtils;
import aicore.utils.CommonUtils;
import aicore.utils.GuardrailPageUtils;
import aicore.utils.GuardrailTestUtils;
import aicore.utils.TestResourceTrackerHelper;

public class AllGuardrailPageTests extends AbstractE2ETest {
	private static String GUARDRAIL_ID = null;
	private static String GUARDRAIL_NAME = null;

	
	@BeforeAll
	public static void setupAddGuardrail() throws IOException {
		login(page, UserType.NATIVE);
		
		// add guardrail
		String timestamp = CommonUtils.getTimeStampName();
		GUARDRAIL_NAME = "guardrail" + timestamp;
		GUARDRAIL_ID = GuardrailTestUtils.createGlinerGuardrail(page, GUARDRAIL_NAME);
		
//		// edit db metadata for filter tests
		EditMetadataPageUtils.clickEditIcon(page);
		EditMetadataPageUtils.enterDetails(page, "Gliner guardrail");
		EditMetadataPageUtils.enterDescription(page, "Test Gliner guardrail catalog");
		// tags
		EditMetadataPageUtils.enterTagName(page, "embeddings");
		EditMetadataPageUtils.enterTagName(page, "Test1");
		EditMetadataPageUtils.enterTagName(page, "Test2");
		EditMetadataPageUtils.enterTagName(page, "Test3");

		// domains
		EditMetadataPageUtils.enterDomainName(page, "SAP");
		EditMetadataPageUtils.enterDomainName(page, "AI");
		EditMetadataPageUtils.enterDomainName(page, "Finance");

		EditMetadataPageUtils.selectDataClassificationOption(page, "IP");
		EditMetadataPageUtils.selectDataClassificationOption(page, "PHI");
		EditMetadataPageUtils.selectDataClassificationOption(page, "PII");
		EditMetadataPageUtils.selectDataClassificationOption(page, "PUBLIC");
		EditMetadataPageUtils.selectDataRestrictionsOption(page, "IP ALLOWED");
		EditMetadataPageUtils.selectDataRestrictionsOption(page, "PHI ALLOWED");
		EditMetadataPageUtils.selectDataRestrictionsOption(page, "FOUO ALLOWED");

		EditMetadataPageUtils.clickOnSubmit(page);
	}
	
	@Test
	void testCatalogCard() {
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnGuardrail(page);
		GuardrailPageUtils.searchGuardrailCatalog(page, GUARDRAIL_NAME);
		
		// validate search
		String databaseNameInCatalog = AddDatabasePageUtils.verifyDatabaseNameInCatalog(page, GUARDRAIL_NAME);
		boolean databaseNameFlag = databaseNameInCatalog.contains(GUARDRAIL_NAME);
		Assertions.assertTrue(databaseNameFlag, "Database name is not visible in the database catalog");

		// test id on card
		boolean isIdVisible = EditModelPageUtils.validateIDisDisplayedOnCatalogCard(page, GUARDRAIL_NAME);
		Assertions.assertTrue(isIdVisible, "Catalog ID is not visible on the catalog card");

		// validate tags on card
		String[] tagArray = "embeddings, Test1".split(", ");
		List<String> actualTagList = EditModelPageUtils.verifyTagNamesDisplayedOnCard(page, "Database");
		List<String> expectedTagList = Arrays.asList(tagArray);
		Assertions.assertEquals(expectedTagList, actualTagList);

		assertTrue(EditModelPageUtils.isCreatedDateVisibleOnCard(page));

		//TODO make tests to test the behavior of clicking on these icons
		List<String> icons = List.of("lock", "bookmark", "view logs dashboard", "delete");
		for (String icon : icons) {
			boolean isIconVisible = EditModelPageUtils.isIconVisibleOnCatalogCard(page, icon);
			Assertions.assertTrue(isIconVisible, "Icon '" + icon + "' is not visible on the catalog card");
		}
	}
	
	@AfterAll
	static void cleanUp() {
		login(page, UserType.NATIVE);
		boolean deleteDb = CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_GUARDRAIL, GUARDRAIL_ID);
		assertTrue(deleteDb);
	}


}
