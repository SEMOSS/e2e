package aicore.pages.model;

import java.util.ArrayList;
import java.util.List;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

import aicore.utils.CommonUtils;

public class EditModelPageUtils {

	private static final String SEARCHED_MODEL_XPATH = "//p[text()='{modelName}']";
	private static final String EDIT_BUTTON_XPATH = "//button[contains(@class, 'MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary MuiButton-sizeMedium MuiButton-containedSizeMedium ')]";
	private static final String TAG_TEXTBOX = "Tag";
	private static final String SUBMIT_BUTTON_XPATH = "//span[text()='Submit']";
	private static final String EDIT_SUCCESS_TOAST_MESSAGE = "Successfully set the new metadata values for the engine";
	private static final String DETAILS_TEXTBOX_XPATH = "//*[@class='view-lines monaco-mouse-cursor-text']//div";
	private static final String DESCRIPTION_TEXTBOX_LABEL = "Description";
	private static final String DOMAIN_TEXTBOX_LABEL = "Domain";
	private static final String DATA_CLASSIFICATION_CHECKBOX_XPATH = "//label[@for='data classification-opt-{option}']//button";
	private static final String DATA_RESTRICTIONS_CHECKBOX_XPATH = "//label[@for='data restrictions-opt-{option}']//button";
	private static final String DESCRIPTION_TEXT_DATA_TESTID = "Description";
	private static final String MODEL_TAGS_XPATH = "//h4[text()='Tag']/parent::section//div//span";
	private static final String DETAILS_UNDER_OVERVIEW_DATA_TESTID = "engine-overview-markdown";
	private static final String TAGS_UNDER_OVERVIEW_XPATH = "//h4[contains(text(), 'Tag')]/parent::section/child::div";
	private static final String DOMAIN_TEXTS_UNDER_OVERVIEW_XPATH = "//h4[contains(text(), 'Domain')]/parent::section//span";
	private static final String DATA_CLASSIFICATION_OPTIONS_UNDER_OVERVIEW_XPATH = "//h4[contains(text(), 'Data Classification')]/parent::section//div";
	private static final String DATA_RESTRICTIONS_OPTIONS_UNDER_OVERVIEW_XPATH = "//h4[contains(text(), 'Data Restrictions')]/parent::section//div";
	private static final String MODEL_CARD_XPATH = "//p[contains(text(),'{modelName}')]";
	private static final String DOMAIN_TEXTBOX_DATATESTID = "editEngineDetails-Domain-autocomplete";
	private static final String DATA_RESTRICTION_SELECT_XPATH = "//button[@id='data restrictions']";
	private static final String DATA_CLASSIFICATION_SELECT_XAPTH = "//button[@id='data classification']";

	public static void searchModelCatalog(Page page, String modelName) {
		page.getByTestId("search-bar").click();
		page.getByTestId("search-bar").fill(modelName);
	}

	public static void selectModelFromSearchOptions(Page page, String modelName) {
		page.locator((SEARCHED_MODEL_XPATH.replace("{modelName}", modelName))).isVisible();
		page.locator(SEARCHED_MODEL_XPATH.replace("{modelName}", modelName)).click();
	}

	public static void addedModelCard(Page page, String modelName) {
		page.locator(MODEL_CARD_XPATH.replace("{modelName}", modelName)).isVisible();
		page.locator(MODEL_CARD_XPATH.replace("{modelName}", modelName)).click();
	}

	public static boolean verifyModelIsDisplayedOnCatalogPage(Page page, String modelName) {
		String modelNameWithTimestamp = SEARCHED_MODEL_XPATH.replace("{modelName}", modelName);
		page.waitForSelector(modelNameWithTimestamp, new Page.WaitForSelectorOptions().setTimeout(10000));
		boolean isModelVisible = page.isVisible(modelNameWithTimestamp);
		return isModelVisible;
	}

	public static void clickOnEditButton(Page page) {
		page.click(EDIT_BUTTON_XPATH);
	}

	public static void enterDetails(Page page, String detailsText) {
		page.locator(DETAILS_TEXTBOX_XPATH).click();
		page.keyboard().type(detailsText);
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
		Locator enterDomain = page.getByTestId(DOMAIN_TEXTBOX_DATATESTID);
		enterDomain.scrollIntoViewIfNeeded();
		enterDomain.fill(domainName);
		enterDomain.press("Enter");
	}

	public static void selectDataClassificationOption(Page page, String option) {
		Locator selectDataclassification = page.locator(DATA_CLASSIFICATION_SELECT_XAPTH);
		selectDataclassification.scrollIntoViewIfNeeded();
		selectDataclassification.click();
		Locator selectCheckbox = page.locator(DATA_CLASSIFICATION_CHECKBOX_XPATH.replace("{option}", option));
		selectCheckbox.scrollIntoViewIfNeeded();
		selectCheckbox.click();
		selectDataclassification.click();
	}

	public static void selectDataRestrictionsOption(Page page, String option) {
		Locator selectRestriction = page.locator(DATA_RESTRICTION_SELECT_XPATH);
		selectRestriction.scrollIntoViewIfNeeded();
		selectRestriction.click();
		Locator selectCheckbox = page.locator(DATA_RESTRICTIONS_CHECKBOX_XPATH.replace("{option}", option));
		selectCheckbox.scrollIntoViewIfNeeded();
		selectCheckbox.click();
		selectRestriction.click();
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
		String descriptionText = page.getByTestId(DESCRIPTION_TEXT_DATA_TESTID).textContent().trim();
		return descriptionText;
	}

	public static List<String> verifyTagNames(Page page) {
		List<String> tags = new ArrayList<String>();
		List<String> tagsText = page.locator(MODEL_TAGS_XPATH).allInnerTexts();
		CommonUtils.extractOverviewSectionValues(tags, tagsText);
		return tags;
	}

	public static String verifyDetailsTextUnderOverview(Page page) {
		Locator shadowElement = page.getByTestId(DETAILS_UNDER_OVERVIEW_DATA_TESTID);
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