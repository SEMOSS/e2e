package aicore.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class PlaygroundPageUtils {
    
    private static final String PLAYGROUND_APP_BUTTON_XPATH = "//span[text()='Experiment in our Playground™']/../../..//a";
    private static final String PROMPT_THE_MODEL_BUTTON_LABEL = "Prompt the Model";
    private static final String CONFIGURATION_MENU_XPATH = "//form";

    public static void clickOnPlaygroundAppButton(Page page) {
        Locator anchor = page.locator(PLAYGROUND_APP_BUTTON_XPATH);
        CommonUtils.removeTargetAttribute(anchor);
        anchor.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public static void verifyButtons(Page page, String buttonName) {
        page.getByLabel(buttonName).isVisible();
        page.getByLabel(buttonName).isEnabled();
    }

    public static void verifyTextareaPlaceholder(Page page, String placeholder) {
        Locator textarea = page.locator("textarea");
        String actualPlaceholder = textarea.getAttribute("placeholder");
        if (!actualPlaceholder.equals(placeholder)) {
            throw new AssertionError("Expected placeholder: " + placeholder + ", but found: " + actualPlaceholder);
        }
    }

    public static void enterPromptInTextarea(Page page, String prompt) {
        Locator textarea = page.locator("textarea");
        textarea.fill(prompt);
    }

    public static void clickOnOpenConfigurationMenuButton(Page page, String buttonName) {
        Locator button = page.getByLabel(buttonName);
        button.click();
    }

    public static void verifyConfigurationMenuIsOpened(Page page) {
        Locator configMenu = page.locator(CONFIGURATION_MENU_XPATH);
        if (!configMenu.isVisible()) {
            throw new AssertionError("Expected the Configuration Menu to be opened, but it is not visible.");
        }
    }

    public static void verifyButtonIsEnabled(Page page, String buttonName) {
        Locator button = page.getByLabel(buttonName);
        if (!button.isEnabled()) {
            throw new AssertionError("Expected the button '" + buttonName + "' to be enabled, but it is disabled.");
        }
    }

    public static void verifyButtonIsDisabled(Page page, String buttonName) {
        Locator button = page.getByLabel(buttonName);
        if (button.isEnabled()) {
            throw new AssertionError("Expected the button '" + buttonName + "' to be disabled, but it is enabled.");
        }
    }
}
