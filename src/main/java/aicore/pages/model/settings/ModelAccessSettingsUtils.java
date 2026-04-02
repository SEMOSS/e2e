package aicore.pages.model.settings;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ModelAccessSettingsUtils {
	private static final String MAKE_PUBLIC_SECTION_TEXT_MESSAGE_XPATH = "//p[text()='Private']/following-sibling::p";
	private static final String MAKE_PUBLIC_TOGGLE_BUTTON_DATA_TESTID = "settingsTiles-make-Model-public-private-switch";
	private static final String MAKE_DISCOVERABLE_SECTION_TEXT_MESSAGE_XPATH = "//p[text()='Non Discoverable']/following-sibling::p";
	private static final String MAKE_DISCOVERABLE_TOGGLE_BUTTON_DATA_TESTID = "settingsTiles-Model-makeDiscoverable-switch";
	private static final String TILE_SECTION_TITLE_XPATH = "//p[text()='{title}']";

	// delete ids
	private static final String DELETE_SECTION_TEXT_MESSAGE_XPATH = "//p[text()='Delete Model']/following-sibling::p";
	private static final String DELETE_BUTTON_DATA_TESTID = "settingsTiles-Model-delete-btn";
	private static final String DELETE_CATALOG_BUTTON_XPATH = "//button[contains(@data-testid,'-delete-btn')]";
	private static final String CONFIRMATION_POPUP_DELETE_BUTTON_XPATH = "//button[contains(@data-testid,'confirmDelete-btn')]";

	public static boolean verifyDeleteButtonIsVisible(Page page) {
		return page.getByTestId(DELETE_BUTTON_DATA_TESTID).isVisible();
	}

	public static String verifyMakeDiscoverableSectionTextMessage(Page page) {
		String actualTextMessage = page.textContent(MAKE_DISCOVERABLE_SECTION_TEXT_MESSAGE_XPATH);
		return actualTextMessage;
	}

	public static boolean verifyMakeDiscoverableToggleButtonIsVisible(Page page) {
		return page.getByTestId(MAKE_DISCOVERABLE_TOGGLE_BUTTON_DATA_TESTID).isVisible();
	}

	public static String verifyMakePublicSectionTextMessage(Page page) {
		String actualTextMessage = page.textContent(MAKE_PUBLIC_SECTION_TEXT_MESSAGE_XPATH);
		return actualTextMessage;
	}

	public static boolean verifyMakePublicToggleButtonIsVisible(Page page) {
		return page.getByTestId(MAKE_PUBLIC_TOGGLE_BUTTON_DATA_TESTID).isVisible();
	}

	public static boolean verifyMakePublicSectionIsVisible(Page page, String title) {
		boolean isMakePublicSectionVisible = page.isVisible(TILE_SECTION_TITLE_XPATH.replace("{title}", title));
		return isMakePublicSectionVisible;
	}

	public static boolean verifyMakeDiscoverableSectionIsVisible(Page page, String title) {
		boolean isMakeDiscoverableSectionVisible = page.isVisible(TILE_SECTION_TITLE_XPATH.replace("{title}", title));
		return isMakeDiscoverableSectionVisible;
	}

	public static boolean verifyDeleteSectionIsVisible(Page page, String title) {
		boolean isDeleteSectionVisible = page.isVisible(TILE_SECTION_TITLE_XPATH.replace("{title}", title));
		return isDeleteSectionVisible;
	}

	public static String verifyDeleteSectionTextMessage(Page page) {
		String actualTextMessage = page.textContent(DELETE_SECTION_TEXT_MESSAGE_XPATH).trim();
		return actualTextMessage;
	}

	public static void clickOnDeleteButton(Page page) {
		Locator deleteButton = page.locator(DELETE_CATALOG_BUTTON_XPATH);
		deleteButton.click();
		page.locator(CONFIRMATION_POPUP_DELETE_BUTTON_XPATH).click();
	}
}
