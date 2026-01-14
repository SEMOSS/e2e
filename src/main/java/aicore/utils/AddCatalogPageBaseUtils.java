package aicore.utils;

import java.util.ArrayList;
import java.util.List;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class AddCatalogPageBaseUtils {
	private static final String SECTION_NAME_XPATH = "//button[text()='{sectionName}']";
	private static final String OPTIONS_UNDER_SECTION_XPATH = "//button[text()='{sectionName}']/following::div//p[text()='{optionName}']";
	private static final String ICONS_XPATH = "//button[text()='{sectionName}']/following::div//p[text()='{optionName}']/parent::div//img";
	private static final String CATALOG_NAME_XPATH = "//h4[text()='{CatalogName}']";
	private static final String SEARCH_BAR_XPATH = "//*[@data-testid='SearchOutlinedIcon']";
	// TODO need data-testid for catalog description
	private static final String CATALOG_DESCRIPTION_XPATH = "//div[normalize-space(text())='{CatalogDescription}']";
	private static final String CATALOG_ID_XPATH = "//button[@aria-label='{CatalogID}']/parent::div";
	private static final String COPY_ID_ICON_XPATH = "[data-testid=\"ContentCopyOutlinedIcon\"]";
	private static final String COPY_TOAST_MESSAGE_XPATH = "//span[text()='{ToastMessage}']";
	private static final String EDIT_BUTTON_XPATH = "//button[contains(@class, 'MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary MuiButton-sizeMedium MuiButton-containedSizeMedium ')]";
	private static final String TAG_TEXTBOX = "Tag";
	private static final String SUBMIT_BUTTON_XPATH = "//span[text()='Submit']";
	private static final String CLOSE_BUTTON_XPATH = "//span[text()='Close']";
	private static final String EDIT_SUCCESS_TOAST_MESSAGE = "Successfully set the new metadata values for the engine";
	private static final String MODEL_TAGS_XPATH = "//div[@class='css-fm4r4t']//span";

	// View Database Type on Connect To database page
	private static final String SEARCH_INPUT_XPATH = "//div[@id='home__content']//input[@placeholder='Search' and @type='text']";

	public static boolean verifySectionIsVisible(Page page, String sectionName) {
		page.locator(SECTION_NAME_XPATH.replace("{sectionName}", sectionName)).click();
		boolean isSectionVisible = page.isVisible(SECTION_NAME_XPATH.replace("{sectionName}", sectionName));
		return isSectionVisible;
	}

	public static boolean verifyOptionIsVisible(Page page, String sectionName, String optionName) {
		boolean isOptionVisible = page.isVisible(
				OPTIONS_UNDER_SECTION_XPATH.replace("{sectionName}", sectionName).replace("{optionName}", optionName));
		return isOptionVisible;
	}

	public static Locator getIconByLabel(Page page, String sectionName, String optionName) {
		return page.locator(ICONS_XPATH.replace("{sectionName}", sectionName).replace("{optionName}", optionName));
	}

	public static boolean isIconVisible(Page page, String sectionName, String optionName) {
		return page.locator(ICONS_XPATH.replace("{sectionName}", sectionName).replace("{optionName}", optionName))
				.isVisible();
	}

	public static boolean isSearchBarPresent(Page page) {
		Locator searchBar = page.locator(SEARCH_BAR_XPATH);
		AICorePageUtils.waitFor(searchBar);
		return searchBar.isVisible();
	}

	public static boolean verifyCatalogName(Page page, String catalogName) {
		Locator locator = page.locator(CATALOG_NAME_XPATH.replace("{CatalogName}", catalogName));
		AICorePageUtils.waitFor(locator);
		return locator.isVisible();
	}

	public static boolean verifyCatalogDescription(Page page, String catalogDescription) {
		return page.locator(CATALOG_DESCRIPTION_XPATH.replace("{CatalogDescription}", catalogDescription)).isVisible();
	}

	public static boolean verifyCatalogID(Page page, String catalogID) {
		Locator locator = page.locator(CATALOG_ID_XPATH.replace("{CatalogID}", catalogID));
		AICorePageUtils.waitFor(locator);
		return locator.isVisible();
	}

	public static boolean checkCopyIcon(Page page) {
		return page.locator(COPY_ID_ICON_XPATH).isVisible();
	}

	public static void clickCopyIcon(Page page) {
		page.locator(COPY_ID_ICON_XPATH).click();
	}

	public static boolean verifyCopyToastMessage(Page page, String toastMessage) {
		Locator toastMessageLocator = page.locator(COPY_TOAST_MESSAGE_XPATH.replace("{ToastMessage}", toastMessage));
		AICorePageUtils.waitFor(toastMessageLocator);
		return toastMessageLocator.isVisible();
	}

	public static boolean checkEditIcon(Page page) {
		return page.locator(EDIT_BUTTON_XPATH).isVisible();
	}

	public static void clickEditIcon(Page page) {
		page.locator(EDIT_BUTTON_XPATH).click();
	}

	public static void enterTagName(Page page, String tagName) {
		page.getByLabel(TAG_TEXTBOX).click();
		page.getByLabel(TAG_TEXTBOX).fill(tagName);
		page.getByLabel(TAG_TEXTBOX).press("Enter");
	}

	public static void clickOnSubmit(Page page) {
		page.click(SUBMIT_BUTTON_XPATH);
	}

	public static void clickOnClose(Page page) {
		page.click(CLOSE_BUTTON_XPATH);
	}

	public static void waitForEditSuccessToastMessageToDisappear(Page page) {
		page.locator(EDIT_SUCCESS_TOAST_MESSAGE).isVisible();
		page.getByTestId("CloseIcon").click();

	}

	public static List<String> verifyTagNames(Page page) {
		List<String> tags = new ArrayList<String>();
		List<String> tagsText = page.locator(MODEL_TAGS_XPATH).allInnerTexts();
		CommonUtils.extractOverviewSectionValues(tags, tagsText);
		return tags;
	}

	public static String verifyEditSuccessfullToastMessage(Page page) {
		Locator alert = page.getByTestId("notification-success-alert");
		return AICorePageUtils.verifySuccessToastMessage(page, alert);
	}

	// View Database Type on Connect To database page
	public static void searchDatabaseType(Page page, String section, String databaseType) {
		page.locator(SECTION_NAME_XPATH.replace("{sectionName}", section)).click();
		page.locator(SEARCH_INPUT_XPATH).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		page.locator(SEARCH_INPUT_XPATH).click();
		page.locator(SEARCH_INPUT_XPATH).fill(databaseType); // Enter search term
	}

}
