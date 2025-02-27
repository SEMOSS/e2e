package aicore.pages;

import com.microsoft.playwright.Page;

public class AddModelToCatalogPage {

	private Page page;
	private String timestamp;

	private static final String ADD_MODEL_BUTTON_XPATH = "//button[@aria-label='Navigate to import Model']";
	private static final String SELECT_OPENAI_XPATH = "//p[text()='{OpenAIModelName}']";
	private static final String CATALOG_NAME_XPATH = "//label[@id='NAME-label']";
	private static final String OPEN_AI_KEY_XPATH = "//input[@id='OPEN_AI_KEY']";
	private static final String VARIABLE_NAME_ID = "#VAR_NAME";
	private static final String CREATE_MODEL_BUTTON_XPATH = "//button[@type='submit']";
	private static final String MODEL_TOAST_MESSAGE_XPATH = "//div[@class='MuiAlert-message css-1xsto0d']";
	private static final String CRAETED_MODEL_XPATH = "//h4[@class='MuiTypography-root MuiTypography-h4 css-grm9aw']";
	private static final String SMSS_TAB_XPATH = "//button[text()='SMSS']";
	private static final String NAME_SMSS_PROPERTIES_XPATH = "//div[@class='view-line']//span[@class='mtk1'][starts-with(text(), 'NAME')]";
	private static final String VAR_NAME_SMSS_PROPERTIES_XPATH = "//div[@class='view-line']//span[@class='mtk1'][starts-with(text(), 'VAR_NAME')]";
	private static final String EDIT_BUTTON_XPATH = "//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary MuiButton-sizeMedium MuiButton-containedSizeMedium MuiButton-root MuiButton-contained MuiButton-containedPrimary MuiButton-sizeMedium MuiButton-containedSizeMedium css-bmsw1d']";
	private static final String TAG_NAME_AFTER_ADDING_XPATH = "//span[@class='MuiChip-label MuiChip-labelMedium css-9iedg7'][1]";
	private static final String TAG_TEXTBOX = "Tag";
	private static final String SUBMIT_BUTTON_XPATH = "//span[text()='Submit']";

	public AddModelToCatalogPage(Page page, String timestamp) {
		this.page = page;
		this.timestamp = timestamp;
	}

	public void addModelButton() {
		page.click(ADD_MODEL_BUTTON_XPATH);
	}

	public void selectOpenAi(String aiModelName) {
		page.click(SELECT_OPENAI_XPATH.replace("{OpenAIModelName}", aiModelName));
	}

	public void enterCatalogName(String CatalogName) {
		String uniqueCatalogName = CatalogName + timestamp;
		page.fill(CATALOG_NAME_XPATH, uniqueCatalogName);
	}

	public void enterOpenAIKey(String openAIKey) {
		page.fill(OPEN_AI_KEY_XPATH, openAIKey);
	}

	public void enterVariableName(String varName) {
		page.fill(VARIABLE_NAME_ID, varName);
	}

	public void createModel() {
		page.click(CREATE_MODEL_BUTTON_XPATH);
	}

	public String modelCreationToastMessage() {
		String toastMessage = page.textContent(MODEL_TOAST_MESSAGE_XPATH).trim();
		return toastMessage;
	}

	public String verifyModelTitle() {
		String modelTitle = page.textContent(CRAETED_MODEL_XPATH).trim();
		return modelTitle;
	}

	public void smssTab() {
		page.click(SMSS_TAB_XPATH);
	}

	public String getExpectedCatalogTitle(String modelTitle) {
		String expTitle = modelTitle + timestamp;
		return expTitle;
	}

	public String verifyNameInSMSS() {
		String nameInSMSSProperties = page.textContent(NAME_SMSS_PROPERTIES_XPATH);
		return nameInSMSSProperties;
	}

	public String verifyVarNameInSMSS() {
		String varNameInSMSSProperties = page.textContent(VAR_NAME_SMSS_PROPERTIES_XPATH);
		return varNameInSMSSProperties;
	}

	public void clickOnEditButton() {
		page.click(EDIT_BUTTON_XPATH);
		page.waitForTimeout(1000);
	}

	public void enterTagName(String tagName) {
		page.getByLabel(TAG_TEXTBOX).click();
		page.getByLabel(TAG_TEXTBOX).fill("embeddings");
		page.getByLabel(TAG_TEXTBOX).press("Enter");
	}

	public void clickOnSubmit() {
		page.click(SUBMIT_BUTTON_XPATH);
	}

	public String verifyTagNameafteradding() {
		String modelTagName = page.textContent(TAG_NAME_AFTER_ADDING_XPATH);
		return modelTagName;
	}
}
