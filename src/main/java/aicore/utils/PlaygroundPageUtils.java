package aicore.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class PlaygroundPageUtils {
    
    private static final String PLAYGROUND_APP_BUTTON_XPATH = "//span[text()='Experiment in our Playground™']/../../..//a";
    private static final String PROMPT_THE_MODEL_BUTTON_LABEL = "Prompt the Model";
    private static final String CONFIGURATION_MENU_XPATH = "//form";
    private static final String MODEL_CATALOG_DROPDOWN = "//div//label[text()='Model']//following-sibling::button//span";
    private static final String MODEL_CATALOG_DROPDOWN_CHECKED = "//div[@role='group']//div//span[contains(text(),'{catalogName}')]/../../*[1]";
    private static final String MODEL_CATALOG_SEARCH_INPUT = "//div/div/input[@placeholder='Search']";
    private static final String MODEL_ITEM_BY_NAME = "//div[@data-slot='command-group']//div//div//div//span[contains(text(),'{modelName}')]";
    private static final String MODEL_CHECKBOX_BY_NAME = ".//div[contains(@class,'model-item') and normalize-space(text())='{MODEL}']//input[@type='checkbox']";

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
        if(button.isEnabled()) {
            button.click(); 
        }else {
            throw new AssertionError("The button '" + buttonName + "' is disabled and cannot be clicked.");
        }
    }

    public static void verifyModelCatalogDropdownPresent(Page page, String modelName) {
        Locator dropdown = page.locator(MODEL_CATALOG_DROPDOWN);
        AICorePageUtils.waitFor(dropdown);
        String dropdownText = dropdown.textContent();
        if (!dropdown.isVisible() && (dropdownText == null || dropdownText.isEmpty())) {
            throw new AssertionError("Model catalog text not visible");
        }
    }

    public static void clickOnModelCatalogDropdown(Page page) {
        Locator dropdown = page.locator(MODEL_CATALOG_DROPDOWN);
        AICorePageUtils.waitFor(dropdown);
        try {
            dropdown.click();
        } catch (Exception e) {
            dropdown.click(new Locator.ClickOptions().setForce(true));
        }
    }

    public static void verifyModelIsChecked(Page page, String modelName) {
        Locator dropdown = page.locator(MODEL_CATALOG_DROPDOWN);
        AICorePageUtils.waitFor(dropdown);
        String dropdownText = dropdown.textContent();
        if(!(modelName.equals("default")) && dropdownText.contains(modelName)) {
        Locator checkbox = page.locator(MODEL_CATALOG_DROPDOWN_CHECKED.replace("{catalogName}", modelName));
        AICorePageUtils.waitFor(checkbox);
        if (!checkbox.isVisible()){
            throw new AssertionError("Model with partial name'" + modelName + "' is not checked in dropdown");
        }
    }
    }

    public static void searchModelInSearchbox(Page page, String modelName) {
        Locator input = page.locator(MODEL_CATALOG_SEARCH_INPUT);
        AICorePageUtils.waitFor(input);
        input.fill(modelName);
        page.waitForTimeout(300);
    }

    public static void verifyModelVisibleInDropdown(Page page, String modelName) {
        Locator model = page.locator(MODEL_ITEM_BY_NAME.replace("{modelName}", modelName));
        AICorePageUtils.waitFor(model);
        if (!model.isVisible()) {
            throw new AssertionError("Model '" + modelName + "' not visible in dropdown");
        }
    }

    public static void selectModelFromDropdown(Page page, String modelName) {
        Locator model = page.locator(MODEL_ITEM_BY_NAME.replace("{modelName}", modelName));
        AICorePageUtils.waitFor(model);
        try {
            model.click();
        } catch (Exception e) {
            model.click(new Locator.ClickOptions().setForce(true));
        }
        page.waitForTimeout(300);
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
