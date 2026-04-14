package aicore.utils;

import java.util.ArrayList;
import java.util.List;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class AddCatalogPageBaseUtils {
	private static final String SECTION_NAME_XPATH = "//button[text()='{sectionName}']";
	private static final String OPTIONS_UNDER_SECTION_XPATH = "//button[text()='{sectionName}']/following::div//p[text()='{optionName}']";
	private static final String ICONS_XPATH = "//button[text()='{sectionName}']/following::div//p[text()='{optionName}']/parent::div/preceding-sibling::div//img";
	private static final String DATABASE_SECTION_NAME_XPATH = "//button[text()='{sectionName}']";
	private static final String DATABASE_OPTIONS_UNDER_SECTION_XPATH = "//button[text()='{sectionName}']/following::div//p[text()='{optionName}']";
	private static final String DATABASE_OPTIONS_ICONS_XPATH = "//button[text()='{sectionName}']/following::div//p[text()='{optionName}']/parent::div//img";
	private static final String CATALOG_NAME_XPATH = "//h1[contains(text(),'{CatalogName}')]";
	private static final String SEARCH_BAR_DATA_PLACEHOLDERTEXT = "Search";
	// XPath for catalogs without section grouping (e.g., Vector)
	private static final String OPTIONS_WITHOUT_SECTION_XPATH = "//p[text()='{optionName}']";
	private static final String ICONS_WITHOUT_SECTION_XPATH = "//p[text()='{optionName}']/parent::div/preceding-sibling::div//img";
	// TODO need data-testid for catalog description
	private static final String CATALOG_DESCRIPTION_XPATH = "//div[normalize-space(text())='{CatalogDescription}']";
	private static final String CATALOG_ID_XPATH = "//button[@aria-label='{CatalogID}']/parent::div//span";
	private static final String COPY_ID_ICON_XPATH = "//button[contains(@data-testid,'engineHeader-copy')]";
	private static final String COPY_TOAST_MESSAGE_XPATH = "//div[text()='{ToastMessage}']";
	private static final String EDIT_BUTTON_XPATH = "//button[text()='Edit']";
	private static final String SUBMIT_BUTTON_DATATESTID = "editEngineDetails-submit-btn";
	private static final String CLOSE_BUTTON_XPATH = "//button[text()='Cancel']";
	private static final String EDIT_SUCCESS_TOAST_MESSAGE = "//div[text()='Successfully set the new metadata values for the engine']";
	private static final String MODEL_TAGS_XPATH = "//div[@class='css-fm4r4t']//span";
	private static final String EDIT_TAG_XPATH = "editEngineDetails-Tag-autocomplete";

	// View Database Type on Connect To database page
	private static final String SEARCH_INPUT_DATATESTID = "search-box";

	public static void clickOnSection(Page page, String catalog, String sectionName) {
		switch (catalog) {
		case "database":
			page.locator(DATABASE_SECTION_NAME_XPATH.replace("{sectionName}", sectionName)).click();
			break;
		default:
			page.locator(SECTION_NAME_XPATH.replace("{sectionName}", sectionName)).click();
		}
	}

	public static boolean verifySectionIsVisible(Page page, String catalog, String sectionName) {
		boolean isSectionVisible;
		switch (catalog.toLowerCase()) {
		case "database":
			page.locator(DATABASE_SECTION_NAME_XPATH.replace("{sectionName}", sectionName)).click();
			isSectionVisible = page.isVisible(DATABASE_SECTION_NAME_XPATH.replace("{sectionName}", sectionName));
			break;
		default:
			page.locator(SECTION_NAME_XPATH.replace("{sectionName}", sectionName)).click();
			isSectionVisible = page.isVisible(SECTION_NAME_XPATH.replace("{sectionName}", sectionName));
		}
		return isSectionVisible;
	}

	public static boolean verifyOptionIsVisible(Page page, String catalog, String sectionName, String optionName) {
		boolean isOptionVisible;
		switch (catalog.toLowerCase()) {
		case "database":
			isOptionVisible = page.isVisible(DATABASE_OPTIONS_UNDER_SECTION_XPATH.replace("{sectionName}", sectionName)
					.replace("{optionName}", optionName));
			break;
		default:
			isOptionVisible = page.isVisible(OPTIONS_UNDER_SECTION_XPATH.replace("{sectionName}", sectionName)
					.replace("{optionName}", optionName));
		}
		return isOptionVisible;
	}

	public static Locator getIconByLabel(Page page, String catalog, String sectionName, String optionName) {
		switch (catalog.toLowerCase()) {
		case "database":
			return page.locator(DATABASE_OPTIONS_ICONS_XPATH.replace("{sectionName}", sectionName)
					.replace("{optionName}", optionName));
		default:
			return page.locator(ICONS_XPATH.replace("{sectionName}", sectionName).replace("{optionName}", optionName));
		}

	}

	public static boolean isIconVisible(Page page, String catalog, String sectionName, String optionName) {
		switch (catalog.toLowerCase()) {
		case "database":
			return page.locator(DATABASE_OPTIONS_ICONS_XPATH.replace("{sectionName}", sectionName)
					.replace("{optionName}", optionName)).isVisible();
		default:
			return page.locator(ICONS_XPATH.replace("{sectionName}", sectionName).replace("{optionName}", optionName))
					.isVisible();
		}
	}

	public static boolean isSearchBarPresent(Page page) {
		Locator searchBar = page.getByPlaceholder(SEARCH_BAR_DATA_PLACEHOLDERTEXT);
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
		Locator enterTag = page.getByTestId(EDIT_TAG_XPATH);
		enterTag.scrollIntoViewIfNeeded();
		enterTag.click();
		enterTag.fill(tagName);
		enterTag.press("Enter");
	}

	public static void clickOnSubmit(Page page) {
		Locator submitButton = page.getByTestId(SUBMIT_BUTTON_DATATESTID);
		submitButton.scrollIntoViewIfNeeded();
		submitButton.click();
	}

	public static void clickOnClose(Page page) {
		Locator closeButton = page.locator(CLOSE_BUTTON_XPATH);
		closeButton.scrollIntoViewIfNeeded();
		closeButton.click();
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
		Locator alert = page.locator(EDIT_SUCCESS_TOAST_MESSAGE);
		return AICorePageUtils.verifySuccessToastMessage(page, alert);
	}

	// Overloaded methods for catalogs without section/group names
	public static boolean verifyOptionIsVisible(Page page, String catalog, String optionName) {
		switch (catalog.toLowerCase()) {
		case "database":
			throw new UnsupportedOperationException(
					"Use verifyOptionIsVisible(page, catalog, sectionName, optionName) for grouped catalogs like database");
		default:
			return page.isVisible(OPTIONS_WITHOUT_SECTION_XPATH.replace("{optionName}", optionName));
		}
	}

	public static Locator getIconByLabel(Page page, String catalog, String optionName) {
		switch (catalog.toLowerCase()) {
		case "database":
			throw new UnsupportedOperationException(
					"Use getIconByLabel(page, catalog, sectionName, optionName) for grouped catalogs like database");
		default:
			return page.locator(ICONS_WITHOUT_SECTION_XPATH.replace("{optionName}", optionName));
		}
	}

	public static boolean isIconVisible(Page page, String catalog, String optionName) {
		switch (catalog.toLowerCase()) {
		case "database":
			throw new UnsupportedOperationException(
					"Use isIconVisible(page, catalog, sectionName, optionName) for grouped catalogs like database");
		default:
			return page.locator(ICONS_WITHOUT_SECTION_XPATH.replace("{optionName}", optionName))
					.isVisible();
		}
	}

	// View Database Type on Connect To database page
	public static void searchDatabaseType(Page page, String section, String databaseType) {
		page.getByTestId(SEARCH_INPUT_DATATESTID)
				.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		page.getByTestId(SEARCH_INPUT_DATATESTID).click();
		page.getByTestId(SEARCH_INPUT_DATATESTID).fill(databaseType); // Enter search term
	}
}