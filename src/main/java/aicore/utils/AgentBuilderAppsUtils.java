package aicore.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class AgentBuilderAppsUtils {
	private static final String SELECT_llM_XPATH = "//input[@id='model-autocomplete' and @type='text' and contains(@class, 'MuiAutocomplete-input')]";
	private static final String ENTER_NAME_XPATH = "//div[contains(@class, 'MuiInputBase-root')]//legend/span[text()='Name']/ancestor::div[contains(@class, 'MuiInputBase-root')]//input";
	private static final String PROMPT_CONTEXT_XPATH = "//textarea[@rows='6' and contains(@class, 'MuiOutlinedInput-input') and @placeholder and not(@readonly)]";
	private static final String SET_INPUT_XPATH = "//div[contains(@class, 'MuiPaper-root')]//button[normalize-space(text())='{setInput}']";
	private static final String SET_INPUT_BUTTON_XPATH = "//button[span[text()='Set Input']]";
	private static final String PREVIEW_BUTTON_XPATH = "//button[span[normalize-space(text())='Preview']]";
	private static final String INPUT_TYPE_XPATH = "//div[contains(@class, 'MuiInputBase-root') and contains(@class, 'MuiAutocomplete-inputRoot')]/input[@id='input-token-autocomplete']";
	private static final String CREATE_APP_BUTTON_XPATH = "//button[normalize-space(.)='Create App']";
private static final String INPUT_TYPE_FOR_INPUT_XPATH = "//span[text()='{INPUT_TEXT}']/ancestor::div[contains(@class,'MuiGrid-item')]/following-sibling::div//label[text()='Input Type']/ancestor::div[contains(@class,'MuiFormControl-root')]//input";
	public static void enterName(Page page, String appName, String timestamp) {
		page.locator(ENTER_NAME_XPATH).fill(appName + " " + timestamp);
	}

	public static void clickOnSelectLLM(Page page, String modelTitle) {
		page.fill(SELECT_llM_XPATH, modelTitle);
		page.locator(SELECT_llM_XPATH).press("ArrowDown");
		page.locator(SELECT_llM_XPATH).press("Enter");
	}

	public static void fillPrompt(Page page, String promptContext) {
		page.locator(PROMPT_CONTEXT_XPATH).click();
		page.locator(PROMPT_CONTEXT_XPATH).fill(promptContext);
	}

	public static void setInput(Page page, String setInput) {
		Locator setInputValue = page.locator(SET_INPUT_XPATH.replace("{setInput}", setInput));
		setInputValue.hover();
		setInputValue.click();
		Locator setInputButton = page.locator(SET_INPUT_BUTTON_XPATH);
		setInputButton.hover();
		setInputButton.click();
	}

	public static void selectInputType(Page page, String inputType) {
		Locator selectInputTypeButton = page.locator(INPUT_TYPE_XPATH);
		selectInputTypeButton.click();
		selectInputTypeButton.pressSequentially(inputType);
		selectInputTypeButton.press("ArrowDown");
		selectInputTypeButton.press("Enter");
	}

	public static void clickOnPreviewButton(Page page) {
		page.locator(PREVIEW_BUTTON_XPATH).click();
	}

	public static void clickOnCreateAppButton(Page page) {
		page.locator(CREATE_APP_BUTTON_XPATH).click();
	}
	public static void setInputInPrompt(Page page, String setInput) {
          // Split input string into individual words
    String[] words = setInput.trim().split("\\s+");
    for (String word : words) {

        String wordXPath = SET_INPUT_XPATH.replace("{setInput}", word);
        Locator wordLocator = page.locator(wordXPath);
		page.evaluate("document.body.style.zoom='1.4'");
		wordLocator.hover();
            wordLocator.click();
			page.evaluate("document.body.style.zoom='1.0'");
	}

		Locator setInputButton = page.locator(SET_INPUT_BUTTON_XPATH);
		setInputButton.hover();
		setInputButton.click();
	}


    public static void selectInputTypeForInput(Page page, String inputType, String setInput) {
		String cleanedSetInput = setInput.replaceAll("[^a-zA-Z0-9 ]", "");
		String inputText = "{ } " + cleanedSetInput;
       Locator selectInputType = page.locator(INPUT_TYPE_FOR_INPUT_XPATH.replace("{INPUT_TEXT}", inputText));
		selectInputType.click();
		selectInputType.pressSequentially(inputType);
		selectInputType.press("ArrowDown");
		selectInputType.press("Enter");
    }
	
}
