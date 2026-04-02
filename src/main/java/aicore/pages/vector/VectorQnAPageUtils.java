package aicore.pages.vector;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

import aicore.utils.AICorePageUtils;

public class VectorQnAPageUtils {
	private static final String SELECT_MODEL_DROPDOWN_XPATH = "//p[normalize-space()='Select Model:']/following::button[@data-slot='select-trigger']";
	private static final String SLIDER_XPATH = "//div[//p[normalize-space()='{sliderName}']] /following-sibling::div//span[@data-slot='slider']";
	private static final String SLIDER_TOOLTIP_XPATH = "//div[@data-slot='tooltip-content' and @data-state='delayed-open']";
	private static final String Q_A_HEADER_TITLE_DATATESTID = "engineQa-title";
	private static final String INPUT_BOX_DATATESTID = "engineQa-question-input";
	private static final String HOVER_ON_ICON_XPATH = "//div//p[normalize-space()='{iconName}']/following::button[@data-slot='tooltip-trigger']";

	private static final String GENRATED_ANSWER_XPATH = "//div[@class='mt-4 flex flex-col gap-2']//div[text()='Policy Extraction Response:']";
	private static final String GENERATE_BUTTON_DATATESTID = "engineQa-generate-btn";

	public static void clickOnSelectModelDropdown(Page page) {
		page.locator(SELECT_MODEL_DROPDOWN_XPATH).click();
	}

	public static boolean verifySelectedModelInDropdown(Page page, String modelName) {
		String selectedModel = page.locator(SELECT_MODEL_DROPDOWN_XPATH).textContent().trim();
		return selectedModel.equals(modelName);
	}

	public static boolean verifyDropdownIsPresent(Page page, String dropdownName) {
		return page.locator(SELECT_MODEL_DROPDOWN_XPATH).isVisible();
	}

	public static boolean verifySliderIsVisible(Page page, String sliderName) {
		return page.locator(SLIDER_XPATH.replace("{sliderName}", sliderName)).first().isVisible();
	}

	public static boolean verifyTooltipOnHover(Page page, String optionName, String expectedTooltip) {
		Locator hoverIcon = page.locator(HOVER_ON_ICON_XPATH.replace("{iconName}", optionName)).first();
		hoverIcon.hover();
		Locator tooltip = page.locator(SLIDER_TOOLTIP_XPATH).first();
		tooltip.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		String actualTooltip = tooltip.innerText();
		actualTooltip = actualTooltip.replaceAll("\\s+", " ").trim();
		expectedTooltip = expectedTooltip.replaceAll("\\s+", " ").trim();
		return actualTooltip.contains(expectedTooltip);
	}

	public static boolean verifyQandAHeaderIsDisplayed(Page page) {
		return page.getByTestId(Q_A_HEADER_TITLE_DATATESTID).isVisible();
	}

	public static boolean verifyQuestionInputBoxIsVisible(Page page) {
		return page.getByTestId(INPUT_BOX_DATATESTID).isVisible();
	}

	public static void enterQuestionInInputBox(Page page, String question) {
		page.getByTestId(INPUT_BOX_DATATESTID).fill(question);
	}

	public static boolean verifyButtonIsEnabled(Page page) {
		return page.getByTestId(GENERATE_BUTTON_DATATESTID).isEnabled();
	}

	public static void clickOnGenerateAnswerButton(Page page) {
		page.getByTestId(GENERATE_BUTTON_DATATESTID).click();
	}

	public static boolean verifyAnswerIsDisplayed(Page page) {
		Locator answerBox = page.locator(GENRATED_ANSWER_XPATH);
		AICorePageUtils.waitFor(answerBox);
		return answerBox.isVisible();
	}
}
