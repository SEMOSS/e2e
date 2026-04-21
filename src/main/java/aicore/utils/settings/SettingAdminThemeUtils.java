package aicore.utils.settings;

import com.microsoft.playwright.Page;

public class SettingAdminThemeUtils {
	private static final String PAGE_TILE_XPATH = "//h1[text()='{pageTitle}']";
	private static final String BREADCRUMB_XPATH = "//span[text()='{breadcrumbName}']";
	private static final String SUBTITLE_XPATH = "//p[text()='{subtitle}']";
	private static final String DROPDOWN_XPATH = "//button[@data-slot='select-trigger']";
	private static final String NAME_INPUT_FIELD_PLACEHOLDERTEXT = "Enter name";
	private static final String JSON_EDITOR_SECTION_XPATH = "//div[@data-mode-id='json']";
	private static final String ADMIN_THEME_PAGE_BUTTON_XPATH = "//button[text()='{buttonName}']";
	private static final String PRIVACY_POLICY_LINK_DATA_TESTID = "settingsLayout-privacy-btn";
	private static final String ADMIN_OPTION_XPATH = "//button[text()='Admin On']";
	private static final String THEME_NAME_INPUT_FIELD_PLACEHOLDER_TEXT = "Enter theme name";
	private static final String TOASTER_MESSAGE_XPATH = "//div[text()='{toastMessage}']";

	public static boolean verifyPageTitleIsDisplay(Page page, String pageTitle) {
		return page.locator(PAGE_TILE_XPATH.replace("{pageTitle}", pageTitle)).isVisible();
	}

	public static boolean verifyBreadcrumbIsDisplay(Page page, String breadcrumbName) {
		return page.locator(BREADCRUMB_XPATH.replace("{breadcrumbName}", breadcrumbName)).isVisible();
	}

	public static boolean verifySubtitleIsDisplay(Page page, String subtitle) {
		return page.locator(SUBTITLE_XPATH.replace("{subtitle}", subtitle)).isVisible();
	}

	public static boolean verifyDropdownIsDisplay(Page page) {
		return page.locator(DROPDOWN_XPATH).isVisible();
	}

	public static boolean verifyInputFieldIsDisplay(Page page, String inputFieldName) {
		return page.getByPlaceholder(NAME_INPUT_FIELD_PLACEHOLDERTEXT).isVisible();
	}

	public static boolean verifyJsonEditorSectionIsDisplay(Page page, String editorSectionName) {
		return page.locator(JSON_EDITOR_SECTION_XPATH).first().isVisible();
	}

	public static boolean verifyButtonIsDisplay(Page page, String buttonName) {
		return page.locator(ADMIN_THEME_PAGE_BUTTON_XPATH.replace("{buttonName}", buttonName)).isVisible();
	}

	public static boolean verifyLinkIsDisplay(Page page, String linkName) {
		return page.getByTestId(PRIVACY_POLICY_LINK_DATA_TESTID).isVisible();
	}

	public static boolean verifyAdminOptionIsDisplay(Page page, String optionName) {
		return page.locator(ADMIN_OPTION_XPATH).isVisible();
	}

	public static void enterThemeName(Page page, String themeName) {
		page.getByPlaceholder(THEME_NAME_INPUT_FIELD_PLACEHOLDER_TEXT).fill(themeName);
	}

	public static void clickCreateButton(Page page, String buttonName) {
		page.locator(ADMIN_THEME_PAGE_BUTTON_XPATH.replace("{buttonName}", buttonName)).click();
	}

	public static boolean verifyToastMessage(Page page, String expectedMessage) {
		return page.locator(TOASTER_MESSAGE_XPATH.replace("{toastMessage}", expectedMessage)).first().isVisible();
	}
}