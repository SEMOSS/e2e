package aicore.utils.page.model;

import java.util.ArrayList;
import java.util.List;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

import aicore.utils.CommonUtils;

public class EditModelPageUtils {
	
	private static final String MODEL_CATALOG_SEARCH_TEXTBOX_XPATH = "//input[@placeholder='Search']";
	private static final String SEARCHED_MODEL_XPATH = "//div[@class='css-q5m5ti']//p[text()='{modelName}']";
	private static final String EDIT_BUTTON_XPATH = "//button[contains(@class, 'MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary MuiButton-sizeMedium MuiButton-containedSizeMedium ')]";
	private static final String TAG_TEXTBOX = "Tag";
	private static final String SUBMIT_BUTTON_XPATH = "//span[text()='Submit']";
	private static final String EDIT_SUCCESS_TOAST_MESSAGE = "Successfully set the new metadata values for the engine";
	private static final String DETAILS_TEXTBOX_XPATH = "//textarea[@class='inputarea monaco-mouse-cursor-text']";
	private static final String DESCRIPTION_TEXTBOX_LABEL = "Description";
	private static final String DOMAIN_TEXTBOX_LABEL = "Domain";
	private static final String DATA_CLASSIFICATION_TEXTBOX_XPATH = "(//input[@aria-autocomplete='list'])[3]";
	private static final String DATA_RESTRICTIONS_TEXTBOX_XPATH = "(//input[@aria-autocomplete='list'])[4]";
	private static final String DESCRIPTION_TEXT_XPATH = "//div[@class='css-1xfr4eb']//h6";
	private static final String MODEL_TAGS_XPATH = "//div[@class='css-fm4r4t']//span";
	private static final String DETAILS_UNDER_OVERVIEW_XPATH = "//div[h6/h6[text()='Details']]/following-sibling::div[contains(@class,'MuiStack-root')]";
	private static final String TAGS_UNDER_OVERVIEW_XPATH = "//div[h6/h6[contains(text(), 'Tag')]]/following-sibling::div";
	private static final String DOMAIN_TEXTS_UNDER_OVERVIEW_XPATH = "//div[h6/h6[contains(text(), 'Domain')]]/following-sibling::div";
	private static final String DATA_CLASSIFICATION_OPTIONS_UNDER_OVERVIEW_XPATH = "//div[h6/h6[contains(text(), 'Data classification')]]/following-sibling::div";
	private static final String DATA_RESTRICTIONS_OPTIONS_UNDER_OVERVIEW_XPATH = "//div[h6/h6[contains(text(), 'Data restrictions')]]/following-sibling::div";
	private static final String MODEL_CARD_XPATH = "//p[contains(text(),'{modelName}')]";

	public static void searchModelCatalog(Page page, String modelName, String timestamp) {
		page.locator(MODEL_CATALOG_SEARCH_TEXTBOX_XPATH).click();
		page.locator(MODEL_CATALOG_SEARCH_TEXTBOX_XPATH).fill(modelName + timestamp);
	}

	public static void selectModelFromSearchOptions(Page page, String modelName, String timestamp) {
		page.locator((SEARCHED_MODEL_XPATH.replace("{modelName}", modelName + timestamp))).isVisible();
		page.locator(SEARCHED_MODEL_XPATH.replace("{modelName}", modelName + timestamp)).click();
	}

	public static void addedModelCard(Page page, String modelName) {
		page.locator(MODEL_CARD_XPATH.replace("{modelName}", modelName)).isVisible();
		page.locator(MODEL_CARD_XPATH.replace("{modelName}", modelName)).click();
	}

	public static boolean verifyModelIsDisplayedOnCatalogPage(Page page, String modelName, String timestamp) {
		String modelNameWithTimestamp = SEARCHED_MODEL_XPATH.replace("{modelName}", modelName + timestamp);
		page.waitForSelector(modelNameWithTimestamp, new Page.WaitForSelectorOptions().setTimeout(10000));
		boolean isModelVisible = page.isVisible(modelNameWithTimestamp);
		return isModelVisible;
	}

	public static void clickOnEditButton(Page page) {
		page.click(EDIT_BUTTON_XPATH);
	}

	public static void enterDetails(Page page, String detailsText) {
		page.fill(DETAILS_TEXTBOX_XPATH, detailsText);
	}

	public static void enterDescription(Page page, String descriptionText) {
		page.getByLabel(DESCRIPTION_TEXTBOX_LABEL).fill(descriptionText);
	}

	public static void enterTagName(Page page, String tagName) {
		page.getByLabel(TAG_TEXTBOX).click();
		page.getByLabel(TAG_TEXTBOX).fill(tagName);
		page.getByLabel(TAG_TEXTBOX).press("Enter");
	}

	public static void enterDomainName(Page page, String domainName) {
		page.getByLabel(DOMAIN_TEXTBOX_LABEL).fill(domainName);
		page.getByLabel(DOMAIN_TEXTBOX_LABEL).press("Enter");
	}

	public static void selectDataClassificationOption(Page page, String option) {
		page.click(DATA_CLASSIFICATION_TEXTBOX_XPATH);
		page.fill(DATA_CLASSIFICATION_TEXTBOX_XPATH, option);
		page.locator(DATA_CLASSIFICATION_TEXTBOX_XPATH).press("ArrowDown");
		page.locator(DATA_CLASSIFICATION_TEXTBOX_XPATH).press("Enter");
	}

	public static void selectDataRestrictionsOption(Page page, String option) {
		page.click(DATA_RESTRICTIONS_TEXTBOX_XPATH);
		page.fill(DATA_RESTRICTIONS_TEXTBOX_XPATH, option);
		page.locator(DATA_RESTRICTIONS_TEXTBOX_XPATH).press("ArrowDown");
		page.locator(DATA_RESTRICTIONS_TEXTBOX_XPATH).press("Enter");
	}

	public static void clickOnSubmit(Page page) {
		page.click(SUBMIT_BUTTON_XPATH);
	}

	public static String verifyEditSuccessfullToastMessage(Page page) {
		Locator toastMessage = page.getByRole(AriaRole.ALERT)
				.filter(new Locator.FilterOptions().setHasText(EDIT_SUCCESS_TOAST_MESSAGE));
		return toastMessage.textContent().trim();
	}

	public static void waitForEditSuccessToastMessageToDisappear(Page page) {
		page.locator(EDIT_SUCCESS_TOAST_MESSAGE)
				.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
	}

	public static String verifyDescriptionText(Page page) {
		String descriptionText = page.textContent(DESCRIPTION_TEXT_XPATH).trim();
		return descriptionText;
	}

	public static List<String> verifyTagNames(Page page) {
		List<String> tags = new ArrayList<String>();
		List<String> tagsText = page.locator(MODEL_TAGS_XPATH).allInnerTexts();
		CommonUtils.extractOverviewSectionValues(tags, tagsText);
		return tags;
	}

	public static String verifyDetailsTextUnderOverview(Page page) {
		Locator shadowElement = page.locator(DETAILS_UNDER_OVERVIEW_XPATH).locator("p");
		shadowElement.waitFor();
		String text = shadowElement.innerText().trim();
		return text;
	}

	public static List<String> verifyTagNamesUnderOverview(Page page) {
		List<String> tags = new ArrayList<String>();
		List<String> tagsText = page.locator(TAGS_UNDER_OVERVIEW_XPATH).allInnerTexts();
		CommonUtils.extractOverviewSectionValues(tags, tagsText);
		return tags;
	}

	public static List<String> verifyDomainValuesUnderOverview(Page page) {
		List<String> domains = new ArrayList<String>();
		List<String> domainText = page.locator(DOMAIN_TEXTS_UNDER_OVERVIEW_XPATH).allInnerTexts();
		CommonUtils.extractOverviewSectionValues(domains, domainText);
		return domains;
	}

	public static List<String> verifyDataClassificationOptionsUnderOverview(Page page) {
		List<String> dataClassificationOptions = new ArrayList<String>();
		List<String> dataClassificationOptionsText = page.locator(DATA_CLASSIFICATION_OPTIONS_UNDER_OVERVIEW_XPATH)
				.allInnerTexts();
		CommonUtils.extractOverviewSectionValues(dataClassificationOptions, dataClassificationOptionsText);
		return dataClassificationOptions;
	}

	public static List<String> verifyDataRestrictionOptionsUnderOverview(Page page) {
		List<String> dataRestrictionOptions = new ArrayList<String>();
		List<String> dataRestrictionOptionsText = page.locator(DATA_RESTRICTIONS_OPTIONS_UNDER_OVERVIEW_XPATH)
				.allInnerTexts();
		CommonUtils.extractOverviewSectionValues(dataRestrictionOptions, dataRestrictionOptionsText);
		return dataRestrictionOptions;
	}
}
