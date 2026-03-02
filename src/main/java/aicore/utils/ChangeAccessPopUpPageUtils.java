package aicore.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ChangeAccessPopUpPageUtils {
	private static final String AUTHOR_OPTION_XPATH = "//button[@value='OWNER' and @data-slot='radio-group-item']";
	private static final String EDITOR_OPTION_XPATH = "//button[@value='EDIT' and @data-slot='radio-group-item']";
	private static final String READONLY_OPTION_XPATH = "//button[@value='READ_ONLY' and @data-slot='radio-group-item']";
	private static final String COMMENT_BOX_XPATH = "//textarea[not(@aria-hidden) and not(@readonly)]";
	private static final String CANCEL_BUTTON_XPATH = "//button[text()= 'Cancel']";
	private static final String REQUEST_BUTTON_XPATH = "//button[text()= 'Request']";
	private static final String CHANGE_ACCESS_POPUP_XPATH = "//h2[text()='Change Access']";
	private static final String REQUEST_SUCCESS_TOAST_XPATH = "//div[contains(text(),'Successfully requested access to engine')]";

	public static String isChangeAccessPopupVisible(Page page) {
		return page.locator(CHANGE_ACCESS_POPUP_XPATH).textContent();
	}

	public static boolean isPopupVisible(Page page) {
		return page.locator(CHANGE_ACCESS_POPUP_XPATH).isVisible();
	}

	public static boolean isOptionVisible(Page page, String option) {
		return switch (option.toLowerCase()) {
		case "author" -> page.locator(AUTHOR_OPTION_XPATH).isVisible();
		case "editor" -> page.locator(EDITOR_OPTION_XPATH).isVisible();
		case "read-only" -> page.locator(READONLY_OPTION_XPATH).isVisible();
		case "comment box" -> page.locator(COMMENT_BOX_XPATH).isVisible();
		case "cancel button" -> page.locator(CANCEL_BUTTON_XPATH).isVisible();
		case "request button" -> page.locator(REQUEST_BUTTON_XPATH).isVisible();
		default -> false;
		};
	}

	public static void selectAccessType(Page page, String accessType) {
		String xpath;
		switch (accessType.toLowerCase()) {
		case "author":
			xpath = AUTHOR_OPTION_XPATH;
			break;
		case "editor":
			xpath = EDITOR_OPTION_XPATH;
			break;
		case "read-only":
			xpath = READONLY_OPTION_XPATH;
			break;
		default:
			throw new IllegalArgumentException("Invalid access type: " + accessType);
		}
		page.locator(xpath).click();

	}

	public static void enterComment(Page page, String comment) {
		page.locator(COMMENT_BOX_XPATH).fill(comment);
	}

	public static void clickOnRequestButton(Page page) {
		Locator button = page.locator(REQUEST_BUTTON_XPATH);
		AICorePageUtils.waitFor(button);
		button.isEnabled();
		button.click();
	}

	public static String isRequestSuccessToastVisible(Page page) {
		Locator toastMessage = page.locator(REQUEST_SUCCESS_TOAST_XPATH);
		AICorePageUtils.waitFor(toastMessage);
		return toastMessage.innerText().trim();
	}
}
