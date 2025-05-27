package aicore.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

import aicore.utils.CommonUtils;

public class EmbedDocumentPage extends AbstractSearchAndSelectCatalogPage {
//	private Page page;
	private String timestamp;

	private static final String TOAST_MESSAGE_XPATH = "//div[text()='Successfully added document']";
	private static final String FILES_XPATH = "//button[text()='Files']";
	private static final String EMBED_DOCUMENT_BUTTON = "AddIcon";
	private static final String EMBED_BUTTON = "//span[text()='Embed']";
	private static final String FILE_NAME_XPATH = "//tr[contains(@class,'MuiTableRow-root')][1]//td[2]";
	private static final String DATE_UPLOADED_XPATH = "//tr[contains(@class,'MuiTableRow-root')][1]//td[3]";
	private static final String FILE_SIZE_XPATH = "//tr[contains(@class,'MuiTableRow-root')][1]//td[4]";
	private static final String DELETE_BUTTON = "DeleteIcon";
	private static final String CHANGE_ACCESS_XPATH = "//span[text()='Change Access']";
	private static final String CHANGE_ACCESS_POPUP_XPATH = "//h2[text()='Change Access']";
	private static final String AUTHOR_OPTION_XPATH = "//div[contains(@class,'MuiCardHeader-content')]//div[contains(., 'Author')]/ancestor::div[contains(@class,'MuiCard-root')]//input[@type='radio']";
	private static final String EDITOR_OPTION_XPATH = "//div[contains(@class,'MuiCardHeader-content')]//div[contains(., 'Editor')]/ancestor::div[contains(@class,'MuiCard-root')]//input[@type='radio']";
	private static final String READONLY_OPTION_XPATH = "//div[contains(@class,'MuiCardHeader-content')]//div[contains(., 'Read-Only')]/ancestor::div[contains(@class,'MuiCard-root')]//input[@type='radio']";
	private static final String COMMENT_BOX_XPATH = "//textarea[not(@aria-hidden) and not(@readonly)]";
	private static final String CANCEL_BUTTON_XPATH = "//button[contains(., 'Cancel')]";
	private static final String REQUEST_BUTTON_XPATH = "//button[contains(., 'Request')]";
//	private static final String VECTOR_CATALOG_SEARCH_TEXTBOX_XPATH = "//input[@placeholder='Search']";
//	private static final String SEARCHED_VECTOR_XPATH = "//p[text()='{catalogName}']";
	private static final String REQUEST_SUCCESS_TOAST_XPATH = "//div[contains(@class, 'MuiAlert-message') and contains(text(), 'Successfully requested access to engine')]";

	public EmbedDocumentPage(Page page, String timestamp) {
		this.page = page;
		this.timestamp = timestamp;
	}

	public void clickOnFilesButton() {
		page.locator(FILES_XPATH).isVisible();
		page.locator(FILES_XPATH).click();
	}

	public void clickOnAddEmbedDocument() {
		page.getByTestId(EMBED_DOCUMENT_BUTTON).isVisible();
		page.getByTestId(EMBED_DOCUMENT_BUTTON).click();
	}

	public void clickOnEmbedDocument() {
		page.locator(EMBED_BUTTON).isVisible();
		page.locator(EMBED_BUTTON).click();
	}

	public String verifyToastMessage() {
		page.locator(TOAST_MESSAGE_XPATH).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		String toastMessage = page.locator(TOAST_MESSAGE_XPATH).textContent();
		return toastMessage;
	}

	public String fileNameinList() {
		page.locator(FILE_NAME_XPATH).isVisible();
		String fileName = page.locator(FILE_NAME_XPATH).textContent();
		return fileName;
	}

	public String dateUploaded() {
		page.locator(DATE_UPLOADED_XPATH).isVisible();
		String dateUploaded = page.locator(DATE_UPLOADED_XPATH).textContent();
		String[] date = CommonUtils.splitStringBySpace(dateUploaded);
		return date[0];
	}

	public String fileSize() {
		page.locator(FILE_SIZE_XPATH).isVisible();
		String fileSize = page.locator(FILE_SIZE_XPATH).textContent();
		return fileSize;
	}

	public void deleteButton() {
		page.getByTestId(DELETE_BUTTON).isVisible();
		page.getByTestId(DELETE_BUTTON).isEnabled();
	}

	public void clickOnAccessControlButton() {
		page.locator(CHANGE_ACCESS_XPATH).click();
	}

	public String isChangeAccessPopupVisible() {
		String changeAccessPopUp = page.locator(CHANGE_ACCESS_POPUP_XPATH).textContent();
		return changeAccessPopUp;
	}

	public boolean isPopupVisible() {
		return page.locator(CHANGE_ACCESS_POPUP_XPATH).isVisible();
	}

	public boolean isOptionVisible(String option) {
		switch (option.toLowerCase()) {
		case "author" -> page.locator(AUTHOR_OPTION_XPATH).isVisible();
		case "editor" -> page.locator(EDITOR_OPTION_XPATH).isVisible();
		case "read-only" -> page.locator(READONLY_OPTION_XPATH).isVisible();
		case "comment box" -> page.locator(COMMENT_BOX_XPATH).isVisible();
		case "cancel button" -> page.locator(CANCEL_BUTTON_XPATH).isVisible();
		case "request button" -> page.locator(REQUEST_BUTTON_XPATH).isVisible();
		default -> false;
		}
		;
	}

//	public void searchVectorCatalog(String catalogName) {
//		page.locator(VECTOR_CATALOG_SEARCH_TEXTBOX_XPATH).click();
//		page.locator(VECTOR_CATALOG_SEARCH_TEXTBOX_XPATH).fill(catalogName + timestamp);
//	}
//
//	public void selectVectorFromSearchOptions(String catalogName) {
//		page.locator((SEARCHED_VECTOR_XPATH.replace("{catalogName}", catalogName + timestamp))).isVisible();
//		page.locator(SEARCHED_VECTOR_XPATH.replace("{catalogName}", catalogName + timestamp)).click();
//	}

	public void selectAccessType(String accessType) {
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

	public void enterComment(String comment) {
		page.locator(COMMENT_BOX_XPATH).fill(comment);
	}

	public void clickOnRequestButton() {
		page.locator(REQUEST_BUTTON_XPATH).click();
	}

	public boolean isRequestSuccessToastVisible() {
		return page.locator(REQUEST_SUCCESS_TOAST_XPATH).isVisible();
	}

}
