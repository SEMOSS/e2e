package aicore.pages.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

import aicore.utils.AICorePageUtils;
import aicore.utils.CommonUtils;

public class EditModelPageUtils {

	private static final String SEARCHED_MODEL_XPATH = "//p[text()='{modelName}']";
	private static final String EDIT_BUTTON_XPATH = "//button[contains(@class, 'MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary MuiButton-sizeMedium MuiButton-containedSizeMedium ')]";
	private static final String TAG_TEXTBOX = "Tag";
	private static final String SUBMIT_BUTTON_XPATH = "//span[text()='Submit']";
	private static final String EDIT_SUCCESS_TOAST_MESSAGE = "Successfully set the new metadata values for the engine";
	private static final String DETAILS_TEXTBOX_XPATH = "//*[@class='view-lines monaco-mouse-cursor-text']//div";
	private static final String DESCRIPTION_TEXTBOX_LABEL = "Description";
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
	private static final String ENGINE_ACCESS_STATUS_ICON_XPATH = "//*[contains(@class,'lucide lucide-lock-keyhole')]";
	private static final String CATALOG_ID_ON_CARD_XPATH = "//p[contains(text(),'{modelId}')]";
	private static final String CATALOG_ID_XPATH = "//span[contains(@data-testid,'engineHeader')]";
	private static final String TAGS_DISPLAYED_ON_CARD_XPATH = "//div[contains(@data-testid,'genericEngineCards-{catalogName}')]//div[@class='flex items-center justify-center']/div/span";
	private static final String DATE_DISPLAYED_ON_CARD_XPATH = "//span[text()='{date}']";
	private static final String ICONS_DISPLAYED_ON_CARD_XPATH = "//button[contains(@title,'{iconName}')]";
	private static String catalogID;
	private static final String DELETE_CONFIRMATION_MESSAGE_XPATH = "//div[@role='dialog']//p[text()='Are you sure you want to delete this engine?']";
	private static final String DELETE_CONFIRMATION_POPUP_ENAGINE_NAME_XAPTH = "//span[text()='Engine Name:']/following-sibling::span";
	private static final String DELETE_CONFIRMATION_POPUP_ENAGINE_ID_XAPTH = "//span[text()='Engine ID:']/following-sibling::span";

	public static void searchModelCatalog(Page page, String modelName) {
		page.getByTestId("search-bar").click();
		page.getByTestId("search-bar").fill(modelName);
		page.waitForTimeout(300);
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
		Locator modelCard = page.locator(SEARCHED_MODEL_XPATH.replace("{modelName}", modelName));
		AICorePageUtils.waitFor(modelCard);
		return modelCard.isVisible();
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

	public static void mouseHoverOnEngineAccessStatusIcon(Page page) {
		Locator lockIcon = page.locator(ENGINE_ACCESS_STATUS_ICON_XPATH).nth(1);
		AICorePageUtils.waitFor(lockIcon);
		lockIcon.hover(new Locator.HoverOptions().setForce(true));
	}

	public static String getEngineAccessStatusTooltipText(Page page, String status) {
		// Added evaluate method because getting tooltip text twice(due to hidden span)
		return page.locator("[data-slot='tooltip-content']").evaluate("el => el.childNodes[0].textContent.trim()")
				.toString();
	}

	public static String getCatalogID(Page page) {
		Locator id = page.locator(CATALOG_ID_XPATH);
		id.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(60000));
		catalogID = id.innerText();
		return catalogID;
	}

	public static boolean validateIDisDisplayedOnCatalogCard(Page page) {
		return validateIDisDisplayedOnCatalogCard(page, catalogID);
	}

	public static boolean validateIDisDisplayedOnCatalogCard(Page page, String catalogId) {
		Locator modelID = page.locator(CATALOG_ID_ON_CARD_XPATH.replace("{modelId}", catalogId));
		return modelID.isVisible();
	}

	public static List<String> verifyTagNamesDisplayedOnCard(Page page, String catalog) {
		List<String> tags = new ArrayList<String>();
		Locator tagsText = page.locator(TAGS_DISPLAYED_ON_CARD_XPATH.replace("{catalogName}", catalog.toUpperCase()));
		List<String> tagList = tagsText.allInnerTexts();
		CommonUtils.extractOverviewSectionValues(tags, tagList);
		return tags;
	}

	public static boolean isCreatedDateVisibleOnCard(Page page) {
		// Get today's date in 'Apr 14 2026' format
		LocalDate today = java.time.LocalDate.now();
		DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("MMM dd yyyy");
		String formattedDate = today.format(formatter);
		formattedDate = formattedDate.replace(" 0", " ");
		Locator date = page.locator(DATE_DISPLAYED_ON_CARD_XPATH.replace("{date}", formattedDate)).nth(1);
		AICorePageUtils.waitFor(date);
		return date.isVisible();
	}

	public static boolean isIconVisibleOnCatalogCard(Page page, String iconName) {
		Locator iconLocator;
		switch (iconName.toLowerCase()) {
		case "lock":
			iconLocator = page.locator(ICONS_DISPLAYED_ON_CARD_XPATH.replace("{iconName}", "Private engine")).nth(1);
			break;
		case "bookmark":
			iconLocator = page.locator(ICONS_DISPLAYED_ON_CARD_XPATH.replace("{iconName}", "Bookmark")).nth(1);
			break;
		case "view logs dashboard":
			iconLocator = page.locator(ICONS_DISPLAYED_ON_CARD_XPATH.replace("{iconName}", "View Logs Dashboard"))
					.nth(1);
			break;
		case "delete":
			iconLocator = page.locator(ICONS_DISPLAYED_ON_CARD_XPATH.replace("{iconName}", "Delete Engine")).nth(1);
			break;
		default:
			throw new IllegalArgumentException("Invalid icon name: " + iconName);
		}
		AICorePageUtils.waitFor(iconLocator);
		return iconLocator.isVisible();
	}

	public static void clickOnCatalogCardOption(Page page, String option) {
		Locator optionLocator = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(option)).first();
		optionLocator.click();
	}

	public static String getDeleteConfirmationMessage(Page page) {
		Locator deleteConfirmationMessage = page.locator(DELETE_CONFIRMATION_MESSAGE_XPATH);
		return deleteConfirmationMessage.textContent().trim();
	}

	public static String getDeleteConfirmationEngineName(Page page) {
		Locator deleteConfirmationEngineName = page.locator(DELETE_CONFIRMATION_POPUP_ENAGINE_NAME_XAPTH).first();
		return deleteConfirmationEngineName.textContent().trim();
	}

	public static boolean isEngineIdVisibleOnDeleteConfirmation(Page page) {
		Locator engineId = page.locator(DELETE_CONFIRMATION_POPUP_ENAGINE_ID_XAPTH);
		if (!engineId.isVisible()) {
			return false;
		}
		String actualEngineId = engineId.textContent().trim();
		return actualEngineId.equals(catalogID);
	}

	public static boolean isButtonVisibleOnDeleteConfirmation(Page page, String buttonName) {
		Locator buttonLocator = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(buttonName));
		return buttonLocator.isVisible();
	}
}