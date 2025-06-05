package aicore.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

import aicore.utils.AICorePageUtils;
import aicore.utils.ConfigUtils;

public class OpenVectorPage extends AbstractAddCatalogPageBase {

	private String timestamp;
	private static final String ADD_VECTOR_BUTTON_XPATH = "//button/span[text()='Add ']";
	private static final String CONNECTIONS_XPATH = "//div[@class='css-axw7ok']//p[text()='{Connections}']";
	private static final String CATALOG_NAME_TEXTBOX_ID = "#NAME";
	private static final String EMBEDDER_DROPDOWN_XPATH = "(//div[contains(@class ,'MuiSelect-select MuiSelect-outlined MuiInputBase-input MuiOutlinedInput-input')])[1]";
	private static final String EMBEDDER_DROPDOWN_OPTIONS_LIST_XPATH = "//ul[contains(@class,'MuiList-root MuiList-padding MuiMenu-list')]//li[text()='{modelName}']";
	private static final String CHUNKING_STRATEGY_DROPDOWN_XPATH = "(//div[contains(@class,'MuiSelect-select MuiSelect-outlined MuiInputBase-input MuiOutlinedInput-input')])[2]";
	private static final String CHUNKING_STRATEGY_DROPDOWN_OPTIONS_LIST_XPATH = "//ul[contains(@class,'MuiList-root MuiList-padding MuiMenu-list')]//li[text()='{strategyName}']";
	private static final String CONTENT_LENGTH_ID = "#CONTENT_LENGTH";
	private static final String CONTENT_OVERLAP_ID = "#CONTENT_OVERLAP";
	private static final String HOST_NAME_ID = "#HOSTNAME";
	private static final String API_KEY_ID = "#API_KEY";
	private static final String NAME_SPACE_ID = "#NAMESPACE";
	private static final String CREATE_VECTOR_BUTTON_XPATH = "//button[@type='submit']";
	private static final String VECTOR_CREATED_SUCCESS_TOAST_MESSAGE_XPATH = "//div[contains(@class,'MuiAlert-message css-')]";
	private static final String NAME_SMSS_PROPERTIES_XPATH = "//div[@class='view-line']//span[@class='mtk1'][starts-with(text(), 'NAME')]";
	private static final String EMBEDDER_ENGINE_NAME_SMSS_PROPERTIES_XPATH = "//div[@class='view-line']//span[@class='mtk1'][starts-with(text(), 'EMBEDDER_ENGINE_NAME')]";
	private static final String CONTENT_LENGTH_SMSS_PROPERTIES_XPATH = "//div[@class='view-line']//span[@class='mtk1'][starts-with(text(), 'CONTENT_LENGTH')]";
	private static final String CONTENT_OVERLAP_SMSS_PROPERTIES_XPATH = "//div[@class='view-line']//span[@class='mtk1'][starts-with(text(), 'CONTENT_OVERLAP')]";
	private static final String CHUNKING_STRATEGY_SMSS_PROPERTIES_XPATH = "//div[@class='view-line']//span[@class='mtk1'][starts-with(text(), 'CHUNKING_STRATEGY')]";
	private static final String ACCESS_CONTROL_XPATH = "//button[text()='Access Control']";
	private static final String DELETE_BUTTON_XPATH = "//span[text()='Delete']";
	private static final String CONFIRMATION_POPUP_XPATH = "//div[contains(@class,'MuiDialog-paperWidthSm')]";
	private static final String CONFIRMATION_POPUP_DELETE_BUTTON_XPATH = "//div[contains(@class,'MuiDialog-paperWidthSm')]//div//button[contains(@class,'MuiButton-containedSizeMedium')]";
	private static final String VECTOR_CARD_XPATH = "//p[contains(text(),'{catalogName}')]";
	private static final String DELETE_TOAST_MESSAGE_XPATH = "//div[text()='Successfully deleted Vector']";
	private static final String COPY_VECTOR_ID = "ContentCopyOutlinedIcon";
	private static final String COPIED_TOAST_MESSAGE_XPATH = "//div[text()='Successfully copied ID']";

	public OpenVectorPage(Page page, String timestamp) {
		this.page = page;
		this.timestamp = timestamp;
	}

	public void clickOnAddVectorButton() {
		page.click(ADD_VECTOR_BUTTON_XPATH);
	}

	public void selectConnections(String connectionName) {
		Locator locator = page.locator("p", new Page.LocatorOptions().setHasText(connectionName));
		locator.click();
	}

	public void enterVectorCatalogName(String vCatalogName) {
		page.fill(CATALOG_NAME_TEXTBOX_ID, vCatalogName);
	}

	public void selectModelfromEmbedderDropdown(String modelName) {
		page.click(EMBEDDER_DROPDOWN_XPATH);
		page.click(EMBEDDER_DROPDOWN_OPTIONS_LIST_XPATH.replace("{modelName}", modelName));
	}

	public void selectStrategyfromChunkingStrategyDropdown(String strategyName) {
		page.click(CHUNKING_STRATEGY_DROPDOWN_XPATH);
		page.click(CHUNKING_STRATEGY_DROPDOWN_OPTIONS_LIST_XPATH.replace("{strategyName}", strategyName));
	}

	public void enterContentLength(String contentLength) {
		if (page.locator(CONTENT_LENGTH_ID).isVisible()) {
			page.fill(CONTENT_LENGTH_ID, contentLength);
		}
	}

	public void enterContentOverlap(String contentOverlap) {
		page.fill(CONTENT_OVERLAP_ID, contentOverlap);
	}

	public void enterHostName() {
		String hostName = ConfigUtils.getValue("pinecone_host_name");
		page.locator(HOST_NAME_ID).isVisible();
		page.locator(HOST_NAME_ID).fill(hostName);
	}

	public void enterApiKey() {
		String apiKey = ConfigUtils.getValue("pinecone_api_key");
		page.locator(API_KEY_ID).isVisible();
		page.locator(API_KEY_ID).fill(apiKey);
	}

	public void enterNameSpace(String namespace) {
		page.locator(NAME_SPACE_ID).isVisible();
		page.locator(NAME_SPACE_ID).fill(namespace);
	}

	public void clickOnCreateVectorButton() {
		page.click(CREATE_VECTOR_BUTTON_XPATH);
	}

	public String verifyVectorCreatedToastMessage() {
		String toastMessage = page.textContent(VECTOR_CREATED_SUCCESS_TOAST_MESSAGE_XPATH).trim();
		return toastMessage;
	}

	public void verifyVectorTitle(String vectorTitle) {
		Locator locator = page.locator("h4:has-text('" + vectorTitle + "')");
		locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
	}

	public String verifyNameFiledInSMSS() {
		String name = page.textContent(NAME_SMSS_PROPERTIES_XPATH).trim();
		return name;
	}

	public String verifyEmbedderEngineNameInSMSS() {
		String name = page.textContent(EMBEDDER_ENGINE_NAME_SMSS_PROPERTIES_XPATH).trim();
		return name;
	}

	public String verifyContentLengthInSMSS() {
		String name = page.textContent(CONTENT_LENGTH_SMSS_PROPERTIES_XPATH).trim();
		return name;
	}

	public String verifyContentOverlapInSMSS() {
		String name = page.textContent(CONTENT_OVERLAP_SMSS_PROPERTIES_XPATH).trim();
		return name;
	}

	public String verifyChunkingStrategyInSMSS() {
		String name = page.textContent(CHUNKING_STRATEGY_SMSS_PROPERTIES_XPATH).trim();
		return name;
	}

	public void clickOnAccessControl() {
		page.locator(ACCESS_CONTROL_XPATH).isVisible();
		page.locator(ACCESS_CONTROL_XPATH).click();
	}

	public void clickOnDeleteButton() {
		page.locator(DELETE_BUTTON_XPATH).isVisible();
		page.locator(DELETE_BUTTON_XPATH).click();
	}

	public void confirmationPopUp() {
		page.locator(CONFIRMATION_POPUP_XPATH)
				.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		page.locator(CONFIRMATION_POPUP_DELETE_BUTTON_XPATH).isVisible();
		page.locator(CONFIRMATION_POPUP_DELETE_BUTTON_XPATH).click();
	}

	public void addedVectorCard(String catalogName) {
		page.locator(VECTOR_CARD_XPATH.replace("{catalogName}", catalogName)).isVisible();
		page.locator(VECTOR_CARD_XPATH.replace("{catalogName}", catalogName)).click();
	}

	public void verifyToastMessage(String expectedToastMessage) {
		AICorePageUtils.verifyToastMessage(page, expectedToastMessage);
	}

	public void copyVectorId() {
		page.getByTestId(COPY_VECTOR_ID).isVisible();
		page.getByTestId(COPY_VECTOR_ID).click();
	}

	public String copiedSuccessToastMessage() {
		page.locator(COPIED_TOAST_MESSAGE_XPATH)
				.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		String toastMessage = page.locator(COPIED_TOAST_MESSAGE_XPATH).textContent();
		return toastMessage;
	}
}
