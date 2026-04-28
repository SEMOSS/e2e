package aicore.unit.model;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import aicore.pages.home.MainMenuUtils;
import aicore.pages.model.AddModelFormUtils;
import aicore.pages.model.EditModelPageUtils;
import aicore.pages.model.ModelSMSSPageUtils;
import aicore.pages.model.SettingsModelPageUtils;
import aicore.utils.AICorePageUtils;
import aicore.utils.AbstractE2ETest;
import aicore.utils.AddCatalogPageBaseUtils;
import aicore.utils.CommonUtils;
import aicore.utils.TestResourceTrackerHelper;
import aicore.utils.page.model.ModelPageUtils;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AddModelPageTests extends AbstractE2ETest {
	
	private static String modelCatalogName = null;

	@BeforeAll
	public static void setup() throws IOException {
		// login with native user before tests
		login(page, UserType.NATIVE);

		String timestamp = CommonUtils.getTimeStampName();
		modelCatalogName = "Model" + timestamp;
		String modelType = "OpenAI";
		String modelName = "GPT-4.1";
		String openAIKey = "Test@1234";

		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenModel(page);

		// model form options
		ModelPageUtils.clickAddModelButton(page);
		AddModelFormUtils.selectModelType(page, modelType);
		AddModelFormUtils.selectModel(page, modelName);
		AddModelFormUtils.enterCatalogName(page, modelCatalogName);
		AddModelFormUtils.enterOpenAIKey(page, openAIKey);
		AddModelFormUtils.clickOnCreateModelButton(page);
	}
	
	@BeforeEach
	public void navigateToModelPage() {
		// Ensure we're on the model details page before each test
		// This prevents tests from failing due to previous test navigation
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenModel(page);
		EditModelPageUtils.searchModelCatalog(page, modelCatalogName);
		EditModelPageUtils.selectModelFromSearchOptions(page, modelCatalogName);
		
		// Wait for page to stabilize
		page.waitForLoadState();
		page.waitForTimeout(500); // Small buffer for any animations
	}
	
	@AfterAll
	public static void teardown() {
		// Clean up: delete the test model catalog
		if (modelCatalogName != null && page != null) {
			try {
				CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_MODEL, modelCatalogName);
			} catch (Exception e) {
				System.err.println("Failed to delete model catalog: " + e.getMessage());
			}
		}
	}
	
	@Order(1)  // Run FIRST - read-only test
	@Test
	@Tag("model")
	@DisplayName("Verify SMSS properties are displayed correctly for a model")
	public void testViewSMSS() {
		ModelPageUtils.verifyModelTitle(page, modelCatalogName);
		ModelPageUtils.clickOnSMSSTab(page);
		String fullModelNameSmss = ModelSMSSPageUtils.verifyNameInSMSS(page);
		String actualModelName = CommonUtils.splitTrimValue(fullModelNameSmss, "NAME");
		String expectedNameProps = ModelPageUtils.getExpectedCatalogTitle(modelCatalogName);
		Assertions.assertEquals(actualModelName, expectedNameProps, "Model name is not matching");
		String fullModelVarNameSmss = ModelSMSSPageUtils.verifyVarNameInSMSS(page);
		String actualVarName = CommonUtils.splitTrimValue(fullModelVarNameSmss, "VAR_NAME");
		Assertions.assertEquals(actualVarName, "myModel", "Var name is not matching");
	}
	
	@Test
	@Order(5)  // Run FIFTH - modifies SMSS properties
	@Tag("model")
	@DisplayName("Edit SMSS properties and verify changes are persisted")
	public void testEditSMSS() {
		ModelPageUtils.verifyModelTitle(page, modelCatalogName);
		ModelPageUtils.clickOnSMSSTab(page);
		SettingsModelPageUtils.clickOnEditSMSSButton(page);
		SettingsModelPageUtils.editSMSSFieldValues(page, "KEEP_CONVERSATION_HISTORY", "True");
		SettingsModelPageUtils.editSMSSFieldValues(page, "VAR_NAME", "New_Name");
		SettingsModelPageUtils.clickOnUpdateSMSSButton(page);
		SettingsModelPageUtils.pageReload(page);
		String fullModelVarNameSmss = ModelSMSSPageUtils.verifyVarNameInSMSS(page);
		String actualVarName = CommonUtils.splitTrimValue(fullModelVarNameSmss, "VAR_NAME");
		Assertions.assertEquals(actualVarName, "New_Name", "Var name is not matching");
		String fullConversationHistory = ModelSMSSPageUtils.verifyKeepConversationHistoryValueInSMSS(page, "KEEP_CONVERSATION_HISTORY");
		actualVarName = CommonUtils.splitTrimValue(fullConversationHistory, "KEEP_CONVERSATION_HISTORY");
		Assertions.assertEquals(actualVarName, "True", "Conversation history setting is not matching");
	}
	
	@Order(2)  // Run SECOND - read-only test
	@Test
	@Tag("model")
	@DisplayName("Add tag to model and verify it appears on the page")
	public void testAddTagToModel() {
		ModelPageUtils.verifyModelTitle(page, modelCatalogName);
		AddCatalogPageBaseUtils.clickEditIcon(page);
		AddCatalogPageBaseUtils.enterTagName(page, "embeddings");
		AddCatalogPageBaseUtils.clickOnSubmit(page);
		List<String> actualTagList = EditModelPageUtils.verifyTagNames(page);
		List<String> expectedTagList = Arrays.asList(new String[] {"embeddings"});
		Assertions.assertEquals(expectedTagList, actualTagList);
	}

	@Order(3)  // Run THIRD - read-only test
	@Test
	@Tag("model")
	@DisplayName("View existing models in Model Catalog")
	public void testViewModels() {
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenModel(page);
		EditModelPageUtils.searchModelCatalog(page, modelCatalogName);
		boolean isModelDisplayed = EditModelPageUtils.verifyModelIsDisplayedOnCatalogPage(page, modelCatalogName);
		Assertions.assertTrue(isModelDisplayed);
	}

	@Order(4)  // Run FOURTH - modifies model details and tags
	@Test
	@Tag("model")
	@DisplayName("Edit model details")
	public void testEditModel() {
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenModel(page);
		EditModelPageUtils.searchModelCatalog(page, modelCatalogName);
		EditModelPageUtils.selectModelFromSearchOptions(page, modelCatalogName);
		AddCatalogPageBaseUtils.clickEditIcon(page);
		
		String detailsText = "GPT-4.1 model";
		EditModelPageUtils.enterDetails(page, detailsText);
		
		String descriptionText = "This is GPT-4.1 test model";
		EditModelPageUtils.enterDescription(page, descriptionText);
		
		String tagNames = "embeddings, Test1";
		String[] tagsArray = tagNames.split(", ");
		for (String tag : tagsArray) {
			AddCatalogPageBaseUtils.enterTagName(page, tag);
		}
		
		String domainNames = "SAP, AI, Finance";
		String[] allDomainNames = domainNames.split(", ");
		for (String domainName : allDomainNames) {
			EditModelPageUtils.enterDomainName(page, domainName);
		}
		
		String dataClassificationOptions = "IP, PHI, PII, PUBLIC";
		String[] classificationOptions = dataClassificationOptions.split(", ");
		for (String option : classificationOptions) {
			EditModelPageUtils.selectDataClassificationOption(page, option);
		}

		String dataRestrictionOptions = "IP ALLOWED, PHI ALLOWED, FOUO ALLOWED";
		String[] restrictionsOptions = dataRestrictionOptions.split(", ");
		for (String option : restrictionsOptions) {
			EditModelPageUtils.selectDataRestrictionsOption(page, option);
		}

		// save edit changes
		AddCatalogPageBaseUtils.clickOnSubmit(page);

		// verify model description
		String actualDescriptionText = EditModelPageUtils.verifyDescriptionText(page);
		Assertions.assertEquals(actualDescriptionText, descriptionText);
		// verify model details
		String actualDetailsText = EditModelPageUtils.verifyDetailsTextUnderOverview(page);
		Assertions.assertEquals(actualDetailsText, detailsText);
		// verify tags
		List<String> expectedTagList = Arrays.asList(tagsArray);
		List<String> actualTagList = EditModelPageUtils.verifyTagNamesUnderOverview(page);
		Assertions.assertEquals(actualTagList, expectedTagList);
		// verify domains
		List<String> expectedDomainList = Arrays.asList(allDomainNames);
		List<String> actualDomainList = EditModelPageUtils.verifyDomainValuesUnderOverview(page);
		Assertions.assertEquals(actualDomainList, expectedDomainList);
		// verify data classification
		List<String> expectedDataClassificationOptionsList = Arrays.asList(classificationOptions);
		List<String> actualDataClassificationOptionsList = EditModelPageUtils.verifyDataClassificationOptionsUnderOverview(page);
		Assertions.assertEquals(actualDataClassificationOptionsList, expectedDataClassificationOptionsList);
		// verfy data restrctions
		List<String> expectedDataRestrictionOptionsList = Arrays.asList(restrictionsOptions);
		List<String> actualDataRestrictionOptionsList = EditModelPageUtils.verifyDataRestrictionOptionsUnderOverview(page);
		Assertions.assertEquals(actualDataRestrictionOptionsList, expectedDataRestrictionOptionsList);
	}

	@Test
	@Order(6)  // Run SIXTH - read-only test, validates model ID
	@Tag("model")
	@DisplayName("Validate model catalog id occurences in usage sections")
	public void testViewModelCatalogId() {
		ModelPageUtils.verifyModelTitle(page, modelCatalogName);
		String modelId = SettingsModelPageUtils.copyModelID(page);
		AICorePageUtils.clickOnTabButton(page, "Usage");

		// Create test data structure to replace DataTable
		// Each entry: [section name, expected count]
		Object[][] testData = {
			{"How to use in Pixel", 5},
			{"How to use in Python", 1},
			{"How to use with LangChain API", 1},
			{"How to use externally with OpenAI API (with or without our Python SDK)", 4},
			{"How to use in Java", 1}
		};

		// Validate model catalog id occurences in each section
		for (Object[] data : testData) {
			String sectionName = (String) data[0];
			int expectedCount = (int) data[1];
			
			String copiedSectionContents = SettingsModelPageUtils.getFullSectionCodeByHeading(page, sectionName);
			int countIdOccurances = CommonUtils.countIdOccurances(copiedSectionContents, modelId);
			
			Assertions.assertEquals(expectedCount, countIdOccurances,
					"Model id count does not match for section '" + sectionName + "'");
		}
	}
}
