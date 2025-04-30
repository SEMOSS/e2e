package aicore.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class OpenVectorPage {

	private Page page;
	private String timestamp;

	private static final String ADD_VECTOR_BUTTON_XPATH = "//div[@class=\"MuiStack-root css-6za6r5-MuiStack-root\"]";
	private static final String CONNECTIONS_XPATH = "//div[@class='css-axw7ok']//p[text()='{Connections}']";
	private static final String CATALOG_NAME_TEXTBOX_ID = "#NAME";
	private static final String EMBEDDER_DROPDOWN_XPATH = "(//div[@class=\"MuiSelect-select MuiSelect-outlined MuiInputBase-input MuiOutlinedInput-input css-rre4r0-MuiSelect-select-MuiInputBase-input-MuiOutlinedInput-input\"])[1]";
	private static final String EMBEDDER_DROPDOWN_OPTIONS_LIST_XPATH = "//ul[@class=\"MuiList-root MuiList-padding MuiMenu-list css-6hp17o-MuiList-root-MuiMenu-list\"]//li[text()='{modelName}']";
	private static final String CHUNKING_STRATEGY_DROPDOWN_XPATH = "(//div[@class=\"MuiSelect-select MuiSelect-outlined MuiInputBase-input MuiOutlinedInput-input css-rre4r0-MuiSelect-select-MuiInputBase-input-MuiOutlinedInput-input\"])[2]";
	private static final String CHUNKING_STRATEGY_DROPDOWN_OPTIONS_LIST_XPATH = "//ul[@class=\"MuiList-root MuiList-padding MuiMenu-list css-6hp17o-MuiList-root-MuiMenu-list\"]//li[text()='{strategyName}']";
	private static final String CONTENT_LENGTH_ID = "#CONTENT_LENGTH";
	private static final String CONTENT_OVERLAP_ID = "#CONTENT_OVERLAP";
	private static final String CREATE_VECTOR_BUTTON_XPATH = "//button[@type='submit']";
	private static final String VECTOR_CREATED_SUCCESS_TOAST_MESSAGE_XPATH = "//div[@class=\"MuiAlert-message css-1pxa9xg-MuiAlert-message\"]";
	private static final String VECTOR_TITLE_XPATH = "//h4[@class=\"MuiTypography-root MuiTypography-h4 css-10k44j9-MuiTypography-root\"]";
	private static final String NAME_SMSS_PROPERTIES_XPATH = "//div[@class='view-line']//span[@class='mtk1'][starts-with(text(), 'NAME')]";
	private static final String EMBEDDER_ENGINE_NAME_SMSS_PROPERTIES_XPATH = "//div[@class='view-line']//span[@class='mtk1'][starts-with(text(), 'EMBEDDER_ENGINE_NAME')]";
	private static final String CONTENT_LENGTH_SMSS_PROPERTIES_XPATH = "//div[@class='view-line']//span[@class='mtk1'][starts-with(text(), 'CONTENT_LENGTH')]";
	private static final String CONTENT_OVERLAP_SMSS_PROPERTIES_XPATH = "//div[@class='view-line']//span[@class='mtk1'][starts-with(text(), 'CONTENT_OVERLAP')]";
	private static final String CHUNKING_STRATEGY_SMSS_PROPERTIES_XPATH = "//div[@class='view-line']//span[@class='mtk1'][starts-with(text(), 'CHUNKING_STRATEGY')]";

	public OpenVectorPage(Page page, String timestamp) {
		this.page = page;
		this.timestamp = timestamp;
	}

	public void clickOnAddVectorButton() {
		page.click(ADD_VECTOR_BUTTON_XPATH);
	}

	public void selectConnections(String connectionName) {
		page.click(CONNECTIONS_XPATH.replace("{Connections}", connectionName));
	}

	public void enterVectorCatalogName(String catalogName) {
		page.fill(CATALOG_NAME_TEXTBOX_ID, catalogName + " " + timestamp);
	}

	public void selectModelfromEmbedderDropdown(String modelName) {
		page.click(EMBEDDER_DROPDOWN_XPATH);
		page.click(EMBEDDER_DROPDOWN_OPTIONS_LIST_XPATH.replace("{modelName}", modelName + timestamp));
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

	public void clickOnCreateVectorButton() {
		page.click(CREATE_VECTOR_BUTTON_XPATH);
	}

	public String verifyVectorCreatedToastMessage() {
		String toastMessage = page.textContent(VECTOR_CREATED_SUCCESS_TOAST_MESSAGE_XPATH).trim();
		return toastMessage;
	}

	public void waitForVectorToastMessageToDisappear() {
		page.locator(VECTOR_CREATED_SUCCESS_TOAST_MESSAGE_XPATH)
				.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
	}

	public String verifyVectorTitle() {
		String vectorTitle = page.textContent(VECTOR_TITLE_XPATH).trim();
		return vectorTitle;
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
}
