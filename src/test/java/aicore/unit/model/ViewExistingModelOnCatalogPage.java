package aicore.unit.model;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import aicore.pages.base.EditMetadataPageUtils;
import aicore.pages.home.HomePageUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.pages.model.AddModelFormUtils;
import aicore.pages.model.EditModelPageUtils;
import aicore.pages.model.SettingsModelPageUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.AddFunctionPageUtils;
import aicore.utils.CatalogFilterPageUtils;
import aicore.utils.CommonUtils;
import aicore.utils.StoragePageUtils;
import aicore.utils.TestResourceTrackerHelper;
import aicore.utils.annotations.PWPage;
import aicore.utils.page.model.ModelPageUtils;

public class ViewExistingModelOnCatalogPage extends AbstractPlaywrightTestBase {
	private String modelCatalogName;
	
	@BeforeEach
	void setup(@PWPage Page page) {
		loginNativeAdmin(page);	
		openModelPage(page);
	    clickAddModelButton(page);
	    AddModelFormUtils.selectModelType(page, "OpenAI");
	    AddModelFormUtils.selectModel(page, "GPT-4.1");	
		modelCatalogName = "GPT Model" + CommonUtils.getTimeStampName();
		AddModelFormUtils.enterCatalogName(page, modelCatalogName);
		AddModelFormUtils.enterOpenAIKey(page, "Test@1234");
		AddModelFormUtils.clickOnCreateModelButton(page);
		clickOnCopyCatalogId(page);
		verifyModelTitle(page, modelCatalogName);

		
	}	
	@AfterEach
	void tearDown(@PWPage Page page) {
	    CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_MODEL, modelCatalogName);
	    logout(page);
	}
	
	private void clickAddModelButton(Page page) {
	    Locator addModelButton = page.getByTestId("engineIndex-add-Model-btn");
	    addModelButton.waitFor(
	            new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
	    addModelButton.click();
	}
	
	private void openModelPage(Page page) {
		
	    HomePageUtils.navigateToHomePage(page);
	    MainMenuUtils.openMainMenu(page);
	    MainMenuUtils.clickOnOpenModel(page);
	}
	
    private void clickOnCopyCatalogId(Page page) {
	        Locator copyCatalogIdButton = page.getByTestId("engineHeader-copy-Model-id-btn");
	        copyCatalogIdButton.waitFor(
	                new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
	        copyCatalogIdButton.click();
	    }
	
	
	private void verifyModelTitle(Page page, String modelTitle) {
	    String actualModelTitle = ModelPageUtils.verifyModelTitle(page, modelTitle);
	    String expectedModelTitle = ModelPageUtils.getExpectedCatalogTitle(modelTitle);
	    Assertions.assertEquals(
	            expectedModelTitle,
	            actualModelTitle,
	            "Model title does not match");
	}
	
	private void addTags(Page page, String tags) {
    String[] tagNames = tags.split(",\\s*");

    for (String tagName : tagNames) {
        EditMetadataPageUtils.enterTagName(page, tagName.trim());
    	}
    
	}
	private void enterDomains(Page page, String domainNames) {
	    String[] domains = domainNames.split(",\\s*");

	    for (String domain : domains) {
	        EditMetadataPageUtils.enterDomainName(page, domain.trim());
	    }
	}

	private void verifyModelDisplayed(Page page, String modelName) {
	    String catalogName = modelName.equals("GPT Model") ? modelCatalogName : modelName;
	    boolean isModelDisplayed = EditModelPageUtils.verifyModelIsDisplayedOnCatalogPage(page, catalogName);
	    Assertions.assertTrue(isModelDisplayed, "Model is not displayed: " + catalogName);
	}
	
	private void applyModelFiltersAndVerify(Page page) {
	    List<Map<String, String>> filters = List.of(
	            Map.of("FILTER_CATEGORY", "Domain", "FILTER_VALUE", "SAP, AI"),
	            Map.of("FILTER_CATEGORY", "Tag", "FILTER_VALUE", "embeddings, Test1"),
	            Map.of("FILTER_CATEGORY", "Data Classification", "FILTER_VALUE", "IP"),
	            Map.of("FILTER_CATEGORY", "Data Restrictions", "FILTER_VALUE", "IP ALLOWED"));

	    for (Map<String, String> filter : filters) {
	        String[] filterValues = filter.get("FILTER_VALUE").split(",\\s*");

	        for (String filterValue : filterValues) {
	            CatalogFilterPageUtils.selectFilterValue(page, filterValue.trim());
	        }
	    }
	}
	
	private void verifyCatalogInBookmarkedSection(Page page, String catalogName) {
	    String actualCatalogName = catalogName.equals("GPT Model")
	            ? modelCatalogName
	            : catalogName;

	    boolean isCatalogDisplayed =
	            CatalogFilterPageUtils.verifyCatalogDisplayedUnderBookmarkedSection(
	                    page,
	                    actualCatalogName);

	    Assertions.assertTrue(
	            isCatalogDisplayed,
	            actualCatalogName + " is not displayed in the Bookmarked section");
	}
	
	private void selectDataRestrictions(Page page, String restrictions) {
	    String[] options = restrictions.split(",\\s*");

	    for (String option : options) {
	        EditMetadataPageUtils.selectDataRestrictionsOption(
	                page,
	                option.trim());
	    }
	}

	private void selectDataClassifications(Page page, String dataClassificationOptions) {
	    String[] options = dataClassificationOptions.split(",\\s*");

	    for (String option : options) {
	        EditMetadataPageUtils.selectDataClassificationOption(
	                page,
	                option.trim());
	    }
	}
	
	private void verifyEngineAccessStatusOnTooltip(Page page, String expectedStatus) {
	    EditModelPageUtils.mouseHoverOnEngineAccessStatusIcon(page);
	    String actualStatus = EditModelPageUtils.getEngineAccessStatusTooltipText(page, expectedStatus);
	    Assertions.assertEquals(expectedStatus, actualStatus, "Incorrect status");
	}
	
	private void verifyTagNamesDisplayedOnCard(Page page, String expectedTags, String catalogName) {
	    List<String> expectedTagList = Arrays.asList(expectedTags.split(",\\s*"));
	    List<String> actualTagList = EditModelPageUtils.verifyTagNamesDisplayedOnCard(page, catalogName);
	    Assertions.assertEquals(expectedTagList, actualTagList);
	}
	
	private void verifyIconsVisibleOnCatalogCard(Page page, List<String> icons) {
	    for (String icon : icons) {
	        boolean isIconVisible = EditModelPageUtils.isIconVisibleOnCatalogCard(page, icon);
	        Assertions.assertTrue(isIconVisible, "Icon '" + icon + "' is not visible on the catalog card");
	    }
	}
	
	private void verifyDeleteConfirmationEngineName(Page page, String expectedEngineName) {
	    String actualEngineName = EditModelPageUtils.getDeleteConfirmationEngineName(page);
	    Assertions.assertEquals(expectedEngineName, actualEngineName, "Incorrect engine name");
	}
	
	private void verifyDeleteToastMessage(Page page, String expectedToastMessage) {
	    String actualMessage = page.locator("//li[@data-type='success']").first().textContent().trim();
	    Assertions.assertEquals(expectedToastMessage, actualMessage, "Delete message doesn't match");
	}
	
	@Test
    public void ViewAndValidateFilterFunctionality_test(@PWPage Page page){
	
		EditMetadataPageUtils.clickEditIcon(page);
		addTags(page, "embeddings, Test1");
		enterDomains(page, "SAP, AI");
		selectDataClassifications(page, "IP, PHI");
		selectDataRestrictions(page, "IP ALLOWED, PHI ALLOWED");
		EditMetadataPageUtils.clickOnSubmit(page);
		openModelPage(page);
		EditModelPageUtils.searchModelCatalog(page, modelCatalogName);
		verifyModelDisplayed(page, modelCatalogName);
		applyModelFiltersAndVerify(page);
		CatalogFilterPageUtils.clickOnBookmark(page, modelCatalogName);
		verifyCatalogInBookmarkedSection(page, modelCatalogName);
		CatalogFilterPageUtils.clickOnUnbookmark(page, modelCatalogName);
		}

	@Test
    public void ValidateaccessOfModelCatalog_test(@PWPage Page page){
		openModelPage(page);
		EditModelPageUtils.searchModelCatalog(page, modelCatalogName);
		verifyModelDisplayed(page, modelCatalogName);
		verifyEngineAccessStatusOnTooltip(page, "Private");
		EditModelPageUtils.searchModelCatalog(page, modelCatalogName);
		EditModelPageUtils.selectModelFromSearchOptions(page, modelCatalogName);
		AddFunctionPageUtils.clickOnAccessControl(page);
		SettingsModelPageUtils.clickOnMakeCatalogPublicButton(page, "Model");
		openModelPage(page);
		EditModelPageUtils.searchModelCatalog(page, modelCatalogName);
		verifyModelDisplayed(page, modelCatalogName);
		verifyEngineAccessStatusOnTooltip(page, "Global");		
	}

	@Test
    public void ValidateContentofCatalogCard_test(@PWPage Page page){
		
		EditModelPageUtils.getCatalogID(page);
		EditMetadataPageUtils.clickEditIcon(page);
		addTags(page, "embeddings, Test1");
		EditMetadataPageUtils.clickOnSubmit(page);
		openModelPage(page);
		EditModelPageUtils.searchModelCatalog(page, modelCatalogName);
		verifyModelDisplayed(page, modelCatalogName);
		EditModelPageUtils.validateIDisDisplayedOnCatalogCard(page);
		verifyTagNamesDisplayedOnCard(page, "embeddings, Test1", "Model");
		verifyTagNamesDisplayedOnCard(page, "embeddings, Test1", "Model");
		verifyIconsVisibleOnCatalogCard(page, List.of("lock", "bookmark", "view logs dashboard", "delete"));	
	}
	
	
	@Test
    public void DeletedAndValidateContentofCatalogCard_test(@PWPage Page page){
		
		EditModelPageUtils.getCatalogID(page);
		openModelPage(page);
		EditModelPageUtils.searchModelCatalog(page, modelCatalogName);
		verifyModelDisplayed(page, modelCatalogName);
		EditModelPageUtils.clickOnCatalogCardOption(page, "Delete Engine");
		EditModelPageUtils.getDeleteConfirmationMessage(page);
		verifyDeleteConfirmationEngineName(page, modelCatalogName);
		EditModelPageUtils.isEngineIdVisibleOnDeleteConfirmation(page);
		EditModelPageUtils.isButtonVisibleOnDeleteConfirmation(page, "Cancel");
		EditModelPageUtils.isButtonVisibleOnDeleteConfirmation(page, "Delete");
		StoragePageUtils.clickOnButton(page, "Delete");
		verifyDeleteToastMessage(page, "Successfully deleted " + modelCatalogName);
	}
	
	
}
	
