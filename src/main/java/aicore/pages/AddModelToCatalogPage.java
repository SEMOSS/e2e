package aicore.pages;

import com.microsoft.playwright.Page;

import aicore.utils.CommonUtils;

public class AddModelToCatalogPage {

	private Page page;
	private String timestamp;
	private static final String ADD_MODEL_BUTTON_XPATH = "//button[@aria-label='Navigate to import Model']";
	private static final String SELECT_OPENAI_XPATH = "//p[text()='{OpenAIModelName}']";
	private static final String CATLOG_NAME_XPATH = "//label[@id=\"NAME-label\"]";
	private static final String OPEN_AI_KEY_XPATH = "//input[@id=\"OPEN_AI_KEY\"]";
	private static final String VARIABLE_NAME_ID = "#VAR_NAME";
	private static final String CREATE_MODEL_BUTTON_XPATH = "//button[@type=\"submit\"]";
	private static final String MODEL_TOAST_MESSAGE_XPATH = "//div[@class='MuiAlert-message css-1xsto0d']";
	private static final String CRAETED_MODEL_XPATH = "//h4[@class=\"MuiTypography-root MuiTypography-h4 css-grm9aw\"]";
	private static final String SMSS_TAB_XPATH = "//button[@class='MuiButtonBase-root MuiTab-root MuiTab-textColorPrimary css-18jjeyr'][3]";
	private static final String NAME_SMSS_PROPERTIES_XPATH = "//div[@class='view-line']//span[@class='mtk1'][starts-with(text(), 'NAME')]";
	private static final String VAR_NAME_SMSS_PROPERTIES_XPATH = "//div[@class='view-line']//span[@class='mtk1'][starts-with(text(), 'VAR_NAME')]";

	public AddModelToCatalogPage(Page page) {
		this.page = page;
	}

	public void addModelButton() {
		page.click(ADD_MODEL_BUTTON_XPATH);
	}

	public void selectOpenAi(String aiModelName) {
		page.click(SELECT_OPENAI_XPATH.replace("{OpenAIModelName}", aiModelName));
	}

	public void enterCatlogName(String catlogName) {
		timestamp = CommonUtils.getTimeStampName();
		String uniqueCatalogName = catlogName + timestamp;
		page.fill(CATLOG_NAME_XPATH, uniqueCatalogName);
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

	public String getExpectedCatlogTitle(String modelTitle) {
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

}
