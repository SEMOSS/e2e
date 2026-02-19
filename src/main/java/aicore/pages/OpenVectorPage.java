package aicore.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

import aicore.framework.ConfigUtils;
import aicore.utils.AICorePageUtils;
import aicore.utils.CommonUtils;

public class OpenVectorPage extends AbstractAddCatalogPageBase {

	private String timestamp;
	private static final String ADD_VECTOR_BUTTON_XPATH = "//button[text()='Add ']";
	private static final String CONNECTIONS_XPATH = "//div[@class='css-axw7ok']//p[text()='{Connections}']";
	private static final String CATALOG_NAME_TEXTBOX_ID = "//input[@data-testid='vector-form-input-NAME']";
	private static final String EMBEDDER_DROPDOWN_DATATESTID = "vector-form-input-EMBEDDER_ENGINE_ID";
	private static final String EMBEDDER_DROPDOWN_OPTIONS_LIST_XPATH = "//select//option[text()='{modelName}']";
	private static final String CHUNKING_STRATEGY_DROPDOWN_DATATESTID = "vector-form-input-CHUNKING_STRATEGY";
	private static final String CHUNKING_STRATEGY_DROPDOWN_OPTIONS_LIST_XPATH = "//option[text()='{strategyName}']";
	private static final String CONTENT_LENGTH_ID = "//input[@data-testid='vector-form-input-CONTENT_LENGTH']";
	private static final String CONTENT_OVERLAP_ID = "//input[@data-testid='vector-form-input-CONTENT_OVERLAP']";
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
	private static final String COPY_VECTOR_ID = "engineHeader-copy-Vector-id-btn";
	private static final String COPIED_TOAST_DATA_XPATH = "//div[text()='ID copied to clipboard']";
	private static final String VECTOR_DESCRIPTION_XPATH = "//*[@id='home__content']//div//h6[contains(@class,'MuiTypography-subtitle1')]";
	private static final String VECTOR_TAGS_XPATH = "vector-form-input-TAGS";
	private static final String UPDATED_BY_XPATH = "//*[@id='home__content']//p[contains(text(),'Updated by ')]";
	private static final String UPDATED_AT_XPATH = "//*[@id='home__content']//p[contains(text(),'at ')]";
	private static final String DISCOVERABLE_VECTORS_XPATH = "//button[normalize-space()='Discoverable Vectors']";
	private static final String TOASTER_MESSAGE_XPATH = "//div[text()='{toasterMessage}']";
	private static final String Q_A_TAB_XPATH = "//button[text()='Q&A']";
	private static final String ADJUST_CONFIGURATIONS_PANEL_XPATH = "//span[text()='{panelName}']/parent::div[contains(@class,'flex items-center')]";
	private static final String SELECT_MODEL_DROPDOWN_XPATH = "//p[normalize-space()='Select Model:']/following::button[@data-slot='select-trigger']";
	private static final String SLIDER_XPATH = "//div[//p[normalize-space()='{sliderName}']] /following-sibling::div//span[@data-slot='slider']";
	private static final String HOVER_ON_ICON_XPATH = "//div//p[normalize-space()='{iconName}']/following::button[@data-slot='tooltip-trigger']";
	private static final String GENRATED_ANSWER_XPATH = "//div[@class='mt-4 flex flex-col gap-2']//div[text()='Policy Extraction Response:']";
	private static final String CLICK_ON_QA_TAB_DATATESTID = "engineLayout-Q&A-tab";
	private static final String SLIDER_TOOLTIP_XPATH = "//div[@data-slot='tooltip-content' and @data-state='delayed-open']";
	private static final String Q_A_HEADER_TITLE_DATATESTID = "engineQa-title";
	private static final String INPUT_BOX_DATATESTID = "engineQa-question-input";
	private static final String GENERATE_BUTTON_DATATESTID = "engineQa-generate-btn";
	private static final String SLIDER_BAR_XPATH = "//p[text()='{sliderName}']/following::span[@role='slider']";

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

	public void enterVectorTag(String vTag) {
		Locator tagInput = page.getByTestId(VECTOR_TAGS_XPATH);
		tagInput.fill(vTag);
		tagInput.press("Enter");
	}

	public void selectModelfromEmbedderDropdown(String modelName) {
		page.getByTestId(EMBEDDER_DROPDOWN_DATATESTID).click();
		page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(modelName)).click();
	}

	public void selectStrategyfromChunkingStrategyDropdown(String strategyName) {
		page.getByTestId(CHUNKING_STRATEGY_DROPDOWN_DATATESTID).click();
		page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(strategyName)).click();
	}

	public void enterContentLength(String contentLengthValue) {
		Locator contentLength = page.locator(CONTENT_LENGTH_ID);
		if (contentLength.isVisible()) {
			contentLength.fill("");
			contentLength.fill(contentLengthValue);
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
		Locator vector = page.getByTestId("Title");
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

	public boolean verifyToastMessage(String expectedToastMessage) {
		Locator alert = page.locator(TOASTER_MESSAGE_XPATH.replace("{toasterMessage}", expectedToastMessage));
		String actualToastMessage = page
				.locator(TOASTER_MESSAGE_XPATH.replace("{toasterMessage}", expectedToastMessage)).textContent().trim();
		return actualToastMessage.equals(expectedToastMessage);
		// return AICorePageUtils.verifySuccessToastMessage(page, alert);
	}

	public void copyVectorId() {
		page.getByTestId(COPY_VECTOR_ID).isVisible();
		page.getByTestId(COPY_VECTOR_ID).click();
	}

	public String copiedSuccessToastMessage() {
		page.locator(COPIED_TOAST_DATA_XPATH);
		String toastMessage = page.locator(COPIED_TOAST_DATA_XPATH).textContent();
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
		page.getByTestId(COPY_VECTOR_ID).isVisible();
		String vectorIdText = page.getByTestId(COPY_VECTOR_ID).textContent().trim();
		String currentUrl = page.url();
		if (!currentUrl.contains(vectorIdText)) {
			throw new AssertionError("Vector ID is not present or wrong in Vector catalog: " + vectorIdText);
		}
	}

	public void verifyVectorTags(String tagName) {
		page.locator(VECTOR_TAGS_XPATH.replace("{tagName}", tagName)).first().isVisible();
		if (!page.locator((VECTOR_TAGS_XPATH.replace("{tagName}", tagName))).first().isVisible()) {
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
		page.getByTestId(CLICK_ON_QA_TAB_DATATESTID).click();
	}

	public boolean verifyQandATabIsDisplayed() {
		return page.locator(Q_A_TAB_XPATH).isVisible();
	}

	public boolean verifyPanelIsVisible(String panelName) {
		return page.locator(ADJUST_CONFIGURATIONS_PANEL_XPATH.replace("{panelName}", panelName)).isVisible();
	}

	public boolean verifyDropdownIsPresent(String dropdownName) {
		return page.locator(SELECT_MODEL_DROPDOWN_XPATH).isVisible();
	}

	public boolean verifySliderIsVisible(String sliderName) {
		return page.locator(SLIDER_XPATH.replace("{sliderName}", sliderName)).first().isVisible();
	}

	public boolean verifyTooltipOnHover(String optionName, String expectedTooltip) {
		Locator hoverIcon = page.locator(HOVER_ON_ICON_XPATH.replace("{iconName}", optionName)).first();
		hoverIcon.hover();
		Locator tooltip = page.locator(SLIDER_TOOLTIP_XPATH);
		tooltip.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		String actualTooltip = tooltip.innerText();
		actualTooltip = actualTooltip.replaceAll("\\s+", " ").trim();
		expectedTooltip = expectedTooltip.replaceAll("\\s+", " ").trim();
		return actualTooltip.contains(expectedTooltip);
	}

	public boolean verifyQandAHeaderIsDisplayed() {
		return page.getByTestId(Q_A_HEADER_TITLE_DATATESTID).isVisible();
	}

	public boolean verifyQuestionInputBoxIsVisible() {
		return page.getByTestId(INPUT_BOX_DATATESTID).isVisible();
	}

	public boolean verifyButtonIsEnabled() {
		return page.getByTestId(GENERATE_BUTTON_DATATESTID).isEnabled();
	}

	public void clickOnQandATab() {
		page.getByTestId(CLICK_ON_QA_TAB_DATATESTID).click();
	}

	public void clickOnSelectModelDropdown() {
		page.locator(SELECT_MODEL_DROPDOWN_XPATH).click();
	}

	public void selectModelFromDropdown(String modelName) {
		page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(modelName)).click();
	}

	public boolean verifySelectedModelInDropdown(String modelName) {
		String selectedModel = page.locator(SELECT_MODEL_DROPDOWN_XPATH).textContent().trim();
		return selectedModel.equals(modelName);
	}

	public void adjustSlider(String sliderName, String target) {
		double targetValue = Double.parseDouble(target);
		Locator slider = page.locator(SLIDER_BAR_XPATH.replace("{sliderName}", sliderName)).first();
		slider.focus();
		double current = Double.parseDouble(slider.getAttribute("aria-valuenow"));
		while (current != targetValue) {
			if (current < targetValue) {
				slider.press("ArrowRight");
			} else {
				slider.press("ArrowLeft");
			}
			current = Double.parseDouble(slider.getAttribute("aria-valuenow"));
		}
	}

	public String getCurrentSliderValue(String value, String sliderName) {
		Locator slidervalue = page.locator(SLIDER_BAR_XPATH.replace("{sliderName}", sliderName)).first();
		AICorePageUtils.waitFor(slidervalue);
		return slidervalue.getAttribute("aria-valuenow");
	}

	public void enterQuestionInInputBox(String question) {
		page.getByTestId(INPUT_BOX_DATATESTID).fill(question);
	}

	public void clickOnGenerateAnswerButton() {
		page.getByTestId(GENERATE_BUTTON_DATATESTID).click();
	}

	public boolean verifyAnswerIsDisplayed() {
		Locator answerBox = page.locator(GENRATED_ANSWER_XPATH);
		AICorePageUtils.waitFor(answerBox);
		return answerBox.isVisible();
	}
}