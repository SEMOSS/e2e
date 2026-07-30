package aicore.unit.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;

import aicore.pages.base.EditMetadataPageUtils;
import aicore.pages.function.FunctionAccessSettingsUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.pages.model.EditModelPageUtils;
import aicore.pages.model.SettingsModelPageUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.AddCatalogPageBaseUtils;
import aicore.utils.AddFunctionPageUtils;
import aicore.utils.CatalogFilterPageUtils;
import aicore.utils.CommonUtils;
import aicore.utils.ModelTestUtils;
import aicore.utils.StoragePageUtils;
import aicore.utils.TestResourceTrackerHelper;
import aicore.utils.annotations.PWPage;

public class ViewExistingModelonCatalogPage extends AbstractPlaywrightTestBase {
    
    private String modelCatalogName = null;
	private boolean modelCatalogDeletedByTest = false;

	@BeforeEach
    public void setup(@PWPage Page page) {
        // login with admin user before tests
		loginNativeAdmin(page);

        // setup models
        String timestamp = CommonUtils.getTimeStampName();
		modelCatalogName = "GPT Model" + timestamp;
		String modelType = "OpenAI";
		String modelName = "GPT-4.1";
		String openAIKey = "Test@1234";

        // add model 
        ModelTestUtils.addModel(page, modelType, modelName, modelCatalogName, openAIKey);

        // need to return back to main engine page
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenModel(page);
		EditModelPageUtils.searchModelCatalog(page, modelCatalogName);
		EditModelPageUtils.selectModelFromSearchOptions(page, modelCatalogName);
		modelCatalogDeletedByTest = false;
    }

    @AfterEach
    public void tearDown(@PWPage Page page) {
        loginNativeAdmin(page);
		if (!modelCatalogDeletedByTest) {
			// Clean up: delete the test model catalog when not already deleted in test
			assertTrue(CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_MODEL, modelCatalogName));
		}
        logout(page);
    }

    @Test
    public void testFiltersFunctionalityMyFunctionsTab(@PWPage Page page) {
        MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenModel(page);
		EditModelPageUtils.searchModelCatalog(page, modelCatalogName);
		EditModelPageUtils.selectModelFromSearchOptions(page, modelCatalogName);
		EditMetadataPageUtils.clickEditIcon(page);

        String tagNames = "embeddings, Test1";
		String[] tagsArray = tagNames.split(", ");
		for (String tag : tagsArray) {
			EditMetadataPageUtils.enterTagName(page, tag);
        }
         
        String domainNames = "SAP, AI";
		String[] allDomainNames = domainNames.split(", ");
		for (String domainName : allDomainNames) {
			EditMetadataPageUtils.enterDomainName(page, domainName);
		}

		String dataClassificationOptions = "IP, PHI";
		String[] classificationOptions = dataClassificationOptions.split(", ");
		for (String option : classificationOptions) {
			EditMetadataPageUtils.selectDataClassificationOption(page, option);
		}

		String dataRestrictionOptions = "IP ALLOWED, PHI ALLOWED";
		String[] restrictionsOptions = dataRestrictionOptions.split(", ");
		for (String option : restrictionsOptions) {
			EditMetadataPageUtils.selectDataRestrictionsOption(page, option);
		}

		// save edit changes
		EditMetadataPageUtils.clickOnSubmit(page);
        // verify success edit message
        String expectedToastMessage = "Successfully set the new metadata values for the engine";
        String actualToastMessage = AddCatalogPageBaseUtils.verifyEditSuccessfullToastMessage(page);
		Assertions.assertEquals(actualToastMessage, expectedToastMessage);

        MainMenuUtils.openMainMenu(page);
        MainMenuUtils.clickOnOpenModel(page);
		EditModelPageUtils.searchModelCatalog(page, modelCatalogName);
		// EditModelPageUtils.selectModelFromSearchOptions(page, modelCatalogName);
        EditModelPageUtils.verifyModelIsDisplayedOnCatalogPage(page, modelCatalogName);

        final String FILTER_CATEGORY_NAME = "FILTER_CATEGORY";
		final String FILTER_VALUE_NAME = "FILTER_VALUE";
		List<Map<String, String>> rows = List.of(
				Map.of(FILTER_CATEGORY_NAME, "Tag", FILTER_VALUE_NAME, "embeddings, Test1"),
				Map.of(FILTER_CATEGORY_NAME, "Domain", FILTER_VALUE_NAME, "SAP, AI"),
				Map.of(FILTER_CATEGORY_NAME, "Data Classification", FILTER_VALUE_NAME, "IP"),
				Map.of(FILTER_CATEGORY_NAME, "Data Restrictions", FILTER_VALUE_NAME, "IP ALLOWED"));
		validateCatalogFilters(modelCatalogName, FILTER_CATEGORY_NAME, FILTER_VALUE_NAME, rows, page);

		CatalogFilterPageUtils.clickOnBookmark(page, modelCatalogName);
		boolean isCatalogDisplayedUnderBookmarkedSection = CatalogFilterPageUtils
				.verifyCatalogDisplayedUnderBookmarkedSection(page, modelCatalogName);
		Assertions.assertTrue(isCatalogDisplayedUnderBookmarkedSection,
				modelCatalogName + " " + "not displayed under bookmarked section");
		CatalogFilterPageUtils.clickOnUnbookmark(page, modelCatalogName);
        
    }

	@Test
	public void testValidateAccessStatusOfCreatedModelCatalog(@PWPage Page page) {
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenModel(page);
		EditModelPageUtils.searchModelCatalog(page, modelCatalogName);
		boolean isModelVisible = EditModelPageUtils.verifyModelIsDisplayedOnCatalogPage(page, modelCatalogName);
		Assertions.assertTrue(isModelVisible, "Model is not visible on model catalog page");

		EditModelPageUtils.mouseHoverOnEngineAccessStatusIcon(page);
		String expectedStatus = "Private";
		String actualStatus = EditModelPageUtils.getEngineAccessStatusTooltipText(page, expectedStatus);
		Assertions.assertEquals(expectedStatus, actualStatus, "Incorrect status");

		EditModelPageUtils.selectModelFromSearchOptions(page, modelCatalogName);
		AddFunctionPageUtils.clickOnAccessControl(page);
		SettingsModelPageUtils.clickOnMakeCatalogPublicButton(page, "Model");

		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenModel(page);
		EditModelPageUtils.searchModelCatalog(page, modelCatalogName);
		isModelVisible = EditModelPageUtils.verifyModelIsDisplayedOnCatalogPage(page, modelCatalogName);
		Assertions.assertTrue(isModelVisible, "Model is not visible on model catalog page");

		EditModelPageUtils.mouseHoverOnEngineAccessStatusIcon(page);
		expectedStatus = "Global";
		actualStatus = EditModelPageUtils.getEngineAccessStatusTooltipText(page, expectedStatus);
		Assertions.assertEquals(expectedStatus, actualStatus, "Incorrect status");
	}

	@Test
	public void testValidateContentOfCreatedModelCatalogCard(@PWPage Page page) {
		String id = EditModelPageUtils.getCatalogID(page);
		Assertions.assertNotNull(id, "Catalog ID should not be null");

		EditMetadataPageUtils.clickEditIcon(page);
		String expectedTags = "embeddings, Test1";
		String[] tagArray = expectedTags.split(", ");
		for (String tag : tagArray) {
			EditMetadataPageUtils.enterTagName(page, tag);
		}
		EditMetadataPageUtils.clickOnSubmit(page);

		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenModel(page);
		EditModelPageUtils.searchModelCatalog(page, modelCatalogName);
		boolean isModelVisible = EditModelPageUtils.verifyModelIsDisplayedOnCatalogPage(page, modelCatalogName);
		Assertions.assertTrue(isModelVisible, "Model is not visible on model catalog page");

		boolean isIdVisible = EditModelPageUtils.validateIDisDisplayedOnCatalogCard(page);
		Assertions.assertTrue(isIdVisible, "Catalog ID is not visible on the catalog card");

		List<String> actualTagList = EditModelPageUtils.verifyTagNamesDisplayedOnCard(page, "Model");
		List<String> expectedTagList = Arrays.asList(tagArray);
		Assertions.assertEquals(expectedTagList, actualTagList);

		boolean isCreatedDateVisible = EditModelPageUtils.isCreatedDateVisibleOnCard(page);
		Assertions.assertTrue(isCreatedDateVisible, "Catalog created date is not visible on the catalog card");

		List<String> icons = List.of("lock", "bookmark", "view logs dashboard", "delete");
		for (String icon : icons) {
			boolean isIconVisible = EditModelPageUtils.isIconVisibleOnCatalogCard(page, icon);
			Assertions.assertTrue(isIconVisible, "Icon '" + icon + "' is not visible on the catalog card");
		}
	}

	@Test
	public void testDeleteModelCatalogFromDashboardAndValidateDeleteConfirmationPopUp(@PWPage Page page) {
		String id = EditModelPageUtils.getCatalogID(page);
		Assertions.assertNotNull(id, "Catalog ID should not be null");

		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenModel(page);
		EditModelPageUtils.searchModelCatalog(page, modelCatalogName);
		boolean isModelVisible = EditModelPageUtils.verifyModelIsDisplayedOnCatalogPage(page, modelCatalogName);
		Assertions.assertTrue(isModelVisible, "Model is not visible on model catalog page");

		EditModelPageUtils.clickOnCatalogCardOption(page, "Delete Engine");
		String expectedMessage = "Are you sure you want to delete this engine?";
		String actualMessage = EditModelPageUtils.getDeleteConfirmationMessage(page);
		Assertions.assertEquals(expectedMessage, actualMessage, "Incorrect confirmation message");

		String actualEngineName = EditModelPageUtils.getDeleteConfirmationEngineName(page);
		Assertions.assertEquals(modelCatalogName, actualEngineName, "Incorrect engine name");

		Boolean isEngineIdVisible = EditModelPageUtils.isEngineIdVisibleOnDeleteConfirmation(page);
		Assertions.assertTrue(isEngineIdVisible, "Engine ID is not visible on the delete confirmation pop-up");

		boolean isCancelButtonVisible = EditModelPageUtils.isButtonVisibleOnDeleteConfirmation(page, "Cancel");
		Assertions.assertTrue(isCancelButtonVisible,
				"Button 'Cancel' is not visible on the delete confirmation pop-up");

		boolean isDeleteButtonVisible = EditModelPageUtils.isButtonVisibleOnDeleteConfirmation(page, "Delete");
		Assertions.assertTrue(isDeleteButtonVisible,
				"Button 'Delete' is not visible on the delete confirmation pop-up");

		StoragePageUtils.clickOnButton(page, "Delete");
		String expectedToastMessage = "Successfully deleted " + modelCatalogName;
		actualMessage = FunctionAccessSettingsUtils.verifyDeleteToastMessage(page, expectedToastMessage);
		Assertions.assertEquals(expectedToastMessage, actualMessage, "Delete message doesn't match");
		modelCatalogDeletedByTest = true;
	}

	private void validateCatalogFilters(final String catalogName, final String filterCategoryName,
			final String filterValueName, List<Map<String, String>> rows, Page page) {
		for (Map<String, String> row : rows) {
			row.get(filterCategoryName);
			String filterValues = row.get(filterValueName);

			String[] filterValuesArray = filterValues.split(", ");
			for (String filterValue : filterValuesArray) {
				CatalogFilterPageUtils.selectFilterValue(page, filterValue);
				CatalogFilterPageUtils.selectFilterValue(page, filterValue);
				boolean isCatalogVisible = CatalogFilterPageUtils.verifyCatalogIsVisibleOnCatalogPage(page, catalogName);
				Assertions.assertTrue(isCatalogVisible,
						"Catalog is not present for " + "'" + filterValue + "'" + " filter value");
				// To de-select selected filter we again call this method
				CatalogFilterPageUtils.selectFilterValue(page, filterValue);

			}
		}
	}

    




}
