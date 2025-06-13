package aicore.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

import aicore.utils.CommonUtils;

public class EmbedDocumentPage {
	private Page page;

	private static final String TOAST_MESSAGE_XPATH = "//div[text()='Successfully added document']";
	private static final String FILES_XPATH = "//button[text()='Files']";
	private static final String EMBED_DOCUMENT_BUTTON = "AddIcon";
	private static final String EMBED_BUTTON = "//span[text()='Embed']";
	private static final String FILE_NAME_XPATH = "//tr[contains(@class,'MuiTableRow-root')][1]//td[2]";
	private static final String DATE_UPLOADED_XPATH = "//tr[contains(@class,'MuiTableRow-root')][1]//td[3]";
	private static final String FILE_SIZE_XPATH = "//tr[contains(@class,'MuiTableRow-root')][1]//td[4]";
	private static final String DELETE_BUTTON = "DeleteIcon";
	private static final String CHANGE_ACCESS_XPATH = "//span[text()='Change Access']";

	public EmbedDocumentPage(Page page) {
		this.page = page;
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
		String[] date = CommonUtils.splitStringBySpace(dateUploaded, 0);
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

}
