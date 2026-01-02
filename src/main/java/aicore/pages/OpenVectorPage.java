package aicore.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

import aicore.framework.ConfigUtils;
import aicore.utils.AICorePageUtils;
import aicore.utils.CommonUtils;

public class OpenVectorPage extends AbstractAddCatalogPageBase {

	private String timestamp;
	private static final String ADD_VECTOR_BUTTON_XPATH = "//button/span[text()='Add ']";
	private static final String CONNECTIONS_XPATH = "//div[@class='css-axw7ok']//p[text()='{Connections}']";
	private static final String CATALOG_NAME_TEXTBOX_ID = "//*[@data-testid='vector-form-input-NAME']//input";
	private static final String EMBEDDER_DROPDOWN_XPATH = "(//div[contains(@class ,'MuiSelect-select MuiSelect-outlined MuiInputBase-input MuiOutlinedInput-input')])[1]";
	private static final String EMBEDDER_DROPDOWN_OPTIONS_LIST_XPATH = "//li[text()='{modelName}']";
	private static final String CHUNKING_STRATEGY_DROPDOWN_XPATH = "(//div[contains(@class,'MuiSelect-select MuiSelect-outlined MuiInputBase-input MuiOutlinedInput-input')])[2]";
	private static final String CHUNKING_STRATEGY_DROPDOWN_OPTIONS_LIST_XPATH = "//ul[contains(@class,'MuiList-root MuiList-padding MuiMenu-list')]//li[text()='{strategyName}']";
	private static final String CONTENT_LENGTH_ID = "//*[@data-testid='vector-form-input-CONTENT_LENGTH']//input";
	private static final String CONTENT_OVERLAP_ID = "//*[@data-testid='vector-form-input-CONTENT_OVERLAP']//input";
	private static final String HOST_NAME_ID = "//*[@data-testid='vector-form-input-HOSTNAME']//input";
	private static final String API_KEY_ID = "//*[@data-testid='vector-form-input-API_KEY']//input";
	private static final String NAME_SPACE_ID = "//*[@data-testid='vector-form-input-NAMESPACE']//input";
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
	private static final String VECTOR_CARD_XPATH = "//p[text()='{catalogName}']";
	private static final String DELETE_TOAST_MESSAGE_XPATH = "//div[text()='Successfully deleted Vector']";
	private static final String VECTOR_ID = "//*[@data-testid=\"ContentCopyOutlinedIcon\"]/../..";
	private static final String COPY_VECTOR_ID = "ContentCopyOutlinedIcon";
	private static final String COPIED_TOAST_DATA_TESTID = "notification-success-message";
	private static final String VECTOR_DESCRIPTION_XPATH = "//*[@id='home__content']//div//h6[contains(@class,'MuiTypography-subtitle1')]";
	private static final String VECTOR_TAGS_XPATH = "//h6[text()='Tag']/../../..//div//div//span[text()='{tagName}']";
	private static final String UPDATED_BY_XPATH = "//*[@id='home__content']//p[contains(text(),'Updated by ')]";
	private static final String UPDATED_AT_XPATH = "//*[@id='home__content']//p[contains(text(),'at ')]";
	private static final String DISCOVERABLE_VECTORS_XPATH = "//button[text()='Discoverable Vectors']";

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
		page.locator(EMBEDDER_DROPDOWN_OPTIONS_LIST_XPATH.replace("{modelName}", modelName)).click();
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
		page.locator(CREATE_VECTOR_BUTTON_XPATH).scrollIntoViewIfNeeded();
		page.locator(CREATE_VECTOR_BUTTON_XPATH).click();
	}

	public String verifyVectorCreatedToastMessage() {
		String toastMessage = page.textContent(VECTOR_CREATED_SUCCESS_TOAST_MESSAGE_XPATH).trim();
		return toastMessage;
	}

	public void verifyVectorTitle(String vectorTitle) {
		Locator vector = page.locator("h4:has-text('" + vectorTitle + "')");
		vector.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		if (!vector.isVisible()) {
			throw new AssertionError("Vector title '" + vectorTitle + "' is not visible on the page.");
		}
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

	public String verifyToastMessage() {
		Locator alert = page.getByTestId("notification-success-alert");
		return AICorePageUtils.verifySuccessToastMessage(page, alert);
	}

	public void copyVectorId() {
		page.getByTestId(COPY_VECTOR_ID).isVisible();
		page.getByTestId(COPY_VECTOR_ID).click();
	}

	public String copiedSuccessToastMessage() {
		page.getByTestId(COPIED_TOAST_DATA_TESTID)
				.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		String toastMessage = page.getByTestId(COPIED_TOAST_DATA_TESTID).textContent();
		return toastMessage;
	}

	public void verifyVectorDescription() {
		page.locator(VECTOR_DESCRIPTION_XPATH).isVisible();
		String vectorDescription = page.locator(VECTOR_DESCRIPTION_XPATH).textContent().trim();
		if (vectorDescription == null || vectorDescription.isEmpty()) {
			throw new AssertionError("Vector description is not available or empty.");
		}
	}

	public void verifyChangeAccessButton() {
		if (!page.getByText("Change Access").isVisible()) {
			throw new AssertionError("Change Access button is not visible on the Vector page.");
		}
	}

	public void verifyCurrentUrlContainsVectorId() {
		page.locator(VECTOR_ID).isVisible();
		String vectorIdText = page.locator(VECTOR_ID).textContent().trim();
		String currentUrl = page.url();
		if (!currentUrl.contains(vectorIdText)) {
			throw new AssertionError("Vector ID is not present or wrong in Vector catalog: " + vectorIdText);
		}
	}

	public void verifyVectorTags(String tagName) {
		page.locator(VECTOR_TAGS_XPATH.replace("{tagName}", tagName)).isVisible();
		if (!page.locator((VECTOR_TAGS_XPATH.replace("{tagName}", tagName))).isVisible()) {
			throw new AssertionError("Tag " + tagName + " is not visible on the Vector page.");
		}
	}

	public void verifyUpdatedBy(String role) {
		String username = ConfigUtils.getValue(role.toLowerCase() + "_username");
		page.locator(UPDATED_BY_XPATH).isVisible();
		String updatedByText = page.locator(UPDATED_BY_XPATH).textContent().trim();
		if (!updatedByText.contains(username)) {
			throw new AssertionError("Updated By does not contain the expected username: " + username);
		}
	}

	public void verifyUpdatedAt() {
		page.locator(UPDATED_AT_XPATH).isVisible();
		String updatedAtText = page.locator(UPDATED_AT_XPATH).textContent().trim();
		String[] updatedDate = CommonUtils.splitStringBySpace(updatedAtText, 1);
		String currentDate = CommonUtils.getTodayDateFormatted();
		if (!updatedDate[1].equals(currentDate)) {
			throw new AssertionError("Updated At does not match the current date: " + currentDate);
		}
	}

	public void clickOnDiscoverableVectorsButton() {
		page.locator(DISCOVERABLE_VECTORS_XPATH).click();
	}

	public void clickOnQnAButton() {
		page.getByTestId("engineLayout-Q&A-tab").click();
	}
}