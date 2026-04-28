package aicore.pages.base;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class EditMetadataPageUtils {
	private static final String EDIT_BUTTON_XPATH = "//button[text()='Edit']";
	private static final String SUBMIT_BUTTON_DATATESTID = "editEngineDetails-submit-btn";
	private static final String CLOSE_BUTTON_DATATESTID = "editEngineDetails-close-btn";


	// form options
	private static final String DETAILS_TEXTBOX_XPATH = "//*[@class='view-lines monaco-mouse-cursor-text']//div";
	private static final String DESCRIPTION_TEXTBOX_LABEL = "Description";
	private static final String EDIT_TAG_XPATH = "editEngineDetails-Tag-autocomplete";
	private static final String DOMAIN_TEXTBOX_DATATESTID = "editEngineDetails-Domain-autocomplete";
	private static final String DATA_CLASSIFICATION_CHECKBOX_XPATH = "//label[@for='data classification-opt-{option}']//button";
	private static final String DATA_RESTRICTIONS_CHECKBOX_XPATH = "//label[@for='data restrictions-opt-{option}']//button";
	private static final String DATA_RESTRICTION_SELECT_XPATH = "//button[@id='data restrictions']";
	private static final String DATA_CLASSIFICATION_SELECT_XAPTH = "//button[@id='data classification']";

	public static void clickEditIcon(Page page) {
		page.locator(EDIT_BUTTON_XPATH).click();
	}

	public static boolean checkEditIcon(Page page) {
		return page.locator(EDIT_BUTTON_XPATH).isVisible();
	}

	public static void enterDetails(Page page, String detailsText) {
		page.locator(DETAILS_TEXTBOX_XPATH).click();
		page.keyboard().type(detailsText);
	}

	public static void enterDescription(Page page, String descriptionText) {
		page.getByLabel(DESCRIPTION_TEXTBOX_LABEL).fill(descriptionText);
	}

	public static void enterTagName(Page page, String tagName) {
		Locator enterTag = page.getByTestId(EDIT_TAG_XPATH);
		enterTag.scrollIntoViewIfNeeded();
		enterTag.click();
		enterTag.fill(tagName);
		enterTag.press("Enter");
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
		Locator submitButton = page.getByTestId(SUBMIT_BUTTON_DATATESTID);
		submitButton.scrollIntoViewIfNeeded();
		submitButton.click();
	}
	
	public static void clickOnClose(Page page) {
		Locator closeButton =page.getByTestId(CLOSE_BUTTON_DATATESTID);
		closeButton.scrollIntoViewIfNeeded();
		closeButton.click();
	}
}
