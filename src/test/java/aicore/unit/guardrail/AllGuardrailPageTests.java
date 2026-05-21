package aicore.unit.guardrail;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.microsoft.playwright.Page;

import aicore.pages.AddFunctionToCatalogPage;
import aicore.pages.AddModelPage;
import aicore.pages.base.EditMetadataPageUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.pages.model.EditModelPageUtils;
import aicore.pages.model.SettingsModelPageUtils;
import aicore.steps.CatalogFilterSteps;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.AddDatabasePageUtils;
import aicore.utils.AddFunctionPageUtils;
import aicore.utils.CommonUtils;
import aicore.utils.GuardrailPageUtils;
import aicore.utils.GuardrailTestUtils;
import aicore.utils.TestResourceTrackerHelper;
import aicore.utils.TestResources;
import aicore.utils.annotations.PWPage;
import aicore.utils.annotations.ResourceUploadLock;

public class AllGuardrailPageTests extends AbstractPlaywrightTestBase {
	private String GUARDRAIL_ID = null;
	private String GUARDRAIL_NAME = null;
	private AddModelPage openModelPage;

	
	@BeforeEach
	@ResourceUploadLock(TestResources.GLINER_ZIP)
	public void setupAddGuardrail(@PWPage Page page) throws IOException {
		loginNativeAdmin(page);
		
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
	@ResourceUploadLock(TestResources.GLINER_ZIP)
	void testCatalogCard(@PWPage Page page) {
		openGuardrailsAndValidateSearch(page);

		// test id on card
		boolean isIdVisible = EditModelPageUtils.validateIDisDisplayedOnCatalogCard(page, GUARDRAIL_NAME);
		Assertions.assertTrue(isIdVisible, "Catalog ID is not visible on the catalog card");

		// validate tags on card
		String[] tagArray = "embeddings, Test1, Test2".split(", ");
		List<String> actualTagList = EditModelPageUtils.verifyTagNamesDisplayedOnCard(page, "GUARDRAIL");
		List<String> expectedTagList = Arrays.asList(tagArray);
		Assertions.assertEquals(expectedTagList, actualTagList);

		assertTrue(EditModelPageUtils.isCreatedDateVisibleOnCard(page));

		//TODO make tests to test the behavior of clicking on these icons
		//Checks if contents of catalog card is correct
		List<String> icons = List.of("lock", "bookmark", "view logs dashboard", "delete");
		for (String icon : icons) {
			boolean isIconVisible = EditModelPageUtils.isIconVisibleOnCatalogCard(page, icon);
			Assertions.assertTrue(isIconVisible, "Icon '" + icon + "' is not visible on the catalog card");
		}
	}
	
	//test filters
	@ParameterizedTest(name = "{index} => {0} = {1}")
	@MethodSource("filterData")
	@ResourceUploadLock(TestResources.GLINER_ZIP)
	void testGuardRailFilters(String filterCategory, String filterValue, @PWPage Page page) {
		openGuardrailsAndValidateSearch(page);
		
		List<Map<String, String>> mapList = List
				.of(Map.of("FILTER_CATEGORY", filterCategory, "FILTER_VALUE", filterValue));

		CatalogFilterSteps.validateCatalogFilters(GUARDRAIL_NAME, "FILTER_CATEGORY", "FILTER_VALUE", mapList,
				page);
		
	}
	
	//test privacy check
	@Test
	void testGuardRailStatus(@PWPage Page page) {
		openGuardrailsAndValidateSearch(page);
		EditModelPageUtils.mouseHoverOnEngineAccessStatusIcon(page);
		String actualStatus = EditModelPageUtils.getEngineAccessStatusTooltipText(page, "Private");
		Assertions.assertEquals("Private", actualStatus, "Incorrect status");
		GuardrailPageUtils.selectTheGuardrailCatalog(page, GUARDRAIL_NAME);
		AddFunctionPageUtils.clickOnAccessControl(page);
		SettingsModelPageUtils.clickOnMakeCatalogPublicButton(page,"Guardrail");
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnGuardrail(page);
		GuardrailPageUtils.searchGuardrailCatalog(page, GUARDRAIL_NAME);
		
		EditModelPageUtils.mouseHoverOnEngineAccessStatusIcon(page);
		actualStatus = EditModelPageUtils.getEngineAccessStatusTooltipText(page,"Global");
		Assertions.assertEquals("Global", actualStatus, "Incorrect status");
		
		
	}
	
	
	@AfterEach
	@ResourceUploadLock(TestResources.GLINER_ZIP )
	void cleanUp(@PWPage Page page) {
		loginNativeAdmin(page);
		boolean deleteDb = CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_GUARDRAIL, GUARDRAIL_ID);
		assertTrue(deleteDb);
		logout(page);
	}
	
	private void openGuardrailsAndValidateSearch(@PWPage Page page) {
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnGuardrail(page);
		GuardrailPageUtils.searchGuardrailCatalog(page, GUARDRAIL_NAME);

		String databaseNameInCatalog = AddDatabasePageUtils.verifyDatabaseNameInCatalog(page, GUARDRAIL_NAME);
		Assertions.assertTrue(databaseNameInCatalog.contains(GUARDRAIL_NAME),
				"Database name is not visible in the database catalog");
	}
	
	private static Stream<Arguments> filterData() {
		return Stream.of(Arguments.of("Tag", "embeddings, Test1"), 
					Arguments.of("Domain", "SAP, AI"),
					Arguments.of("Data Classification", "IP, PHI, PII, PUBLIC"), 
					Arguments.of("Data Restrictions", "IP ALLOWED, PHI ALLOWED, FOUO ALLOWED")
		);
	}



}
