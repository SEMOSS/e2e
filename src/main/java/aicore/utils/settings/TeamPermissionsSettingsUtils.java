package aicore.utils.settings;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import aicore.utils.AICorePageUtils;

public class TeamPermissionsSettingsUtils {

    private static final String SELECT_TYPE_DROPDOWN_XPATH = "//legend/span[text()='Type*']/ancestor::div[contains(@class,'MuiInputBase-root')]//div[@role='combobox']";
    private static final String TEAM_NAME_XPATH = "//legend/span[text()='Name*']/ancestor::div[contains(@class,'MuiInputBase-root')]//input";
    private static final String DESCRIPTION_XPATH = "//legend/span[normalize-space()='Description']/ancestor::div[contains(@class,'MuiInputBase-root')]//input";
    private static final String ADD_BUTTON_XPATH = "//button[.//span[normalize-space()='{buttonName}']]";
    private static final String NAME_XPATH = "//p[normalize-space()='{Name}']";
    private static final String GENERATED_DESCRIPTION_XPATH = "//p[normalize-space()='{description}']";

    public static void selectTypeFromDropdown(Page page, String type) {
        Locator selectTypeFromDropdown = page.locator(SELECT_TYPE_DROPDOWN_XPATH);
        AICorePageUtils.waitFor(selectTypeFromDropdown);
        selectTypeFromDropdown.click();
        page.waitForTimeout(300);
        page.getByText(type).click();
    }

    public static void fillTeamName(Page page, String value) {
        page.locator(TEAM_NAME_XPATH).isVisible();
        page.locator(TEAM_NAME_XPATH).fill(value);
    }

    public static void enterDescription(Page page, String description) {
        page.locator(DESCRIPTION_XPATH).isVisible();
        page.locator(DESCRIPTION_XPATH).fill(description);
    }

    public static void clickOnAddButton(Page page, String button) {
        page.click(ADD_BUTTON_XPATH.replace("{buttonName}", button));
    }

    public static String verifyName(Page page, String name) {
        Locator actualName = page.locator(NAME_XPATH.replace("{Name}", name));
        AICorePageUtils.waitFor(actualName);
        actualName.scrollIntoViewIfNeeded();
        return actualName.textContent().trim();
    }

    public static String validateDescription(Page page, String description) {
        Locator actualDescription = page.locator(GENERATED_DESCRIPTION_XPATH.replace("{description}", description));
        AICorePageUtils.waitFor(actualDescription);
        actualDescription.scrollIntoViewIfNeeded();
        return actualDescription.textContent().trim();
    }

}
