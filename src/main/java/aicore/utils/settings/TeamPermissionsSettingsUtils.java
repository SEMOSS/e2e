package aicore.utils.settings;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import aicore.framework.ConfigUtils;
import aicore.utils.AICorePageUtils;

public class TeamPermissionsSettingsUtils {

	private static final String SELECT_TYPE_DROPDOWN_XPATH = "//legend/span[text()='Type*']/ancestor::div[contains(@class,'MuiInputBase-root')]//div[@role='combobox']";
	private static final String TEAM_NAME_XPATH = "//legend/span[text()='Name*']/ancestor::div[contains(@class,'MuiInputBase-root')]//input";
	private static final String DESCRIPTION_XPATH = "//legend/span[normalize-space()='Description']/ancestor::div[contains(@class,'MuiInputBase-root')]//input";
	private static final String ADD_BUTTON_XPATH = "//button[.//span[normalize-space()='{buttonName}']]";
	private static final String TEAM_BUTTON_XPATH = "//button//span[contains(text(), 'Add Members')]";
	private static final String LIST_MEMBER_XPATH = "//*[contains(text(), '{Member}')]";
	private static final String MEMBER_CARD_XPATH = "//span[contains(text(),'User ID:')]/div//span";
	private static final String LIST_DROPDOWN = "ArrowDropDownIcon";
	private static final String TOAST_MESSAGE_XPATH = "//div[contains(@class,'MuiAlert-message')]";
	private static final String MEMBER_XPATH = "//div//p[contains(text(),'NATIVE ID: {member}')]";
	private static final String NAME_XPATH = "//p[normalize-space()='{Name}']";
	private static final String GENERATED_DESCRIPTION_XPATH = "//p[normalize-space()='{description}']";
	private static final String SELECT_ENGINE_ROLE_XPATH = "//input[@value='{role}']";
	private static final String SELELCT_THE_ENGINE_DROPDOWN_XPATH = "//label[text()='{selectCatalog}']";
	private static final String CLICK_ON_ADD_CATALOG_TEXT = "{addCatalogName}";

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
		page.reload(); // added because of stale element
	}

	public static void clickOnAddMemberButton(Page page, String button) {
		page.click(ADD_BUTTON_XPATH.replace("{buttonName}", button));
	}

	public static void clickOnAddTeamButton(Page page, String button) {
		page.click(TEAM_BUTTON_XPATH.replace("{buttonName}", button));
	}

	public static void selectMemberFromList(Page page, String role) {
		String username = ConfigUtils.getValue(role.toLowerCase() + "_username").split("@")[0];
		Locator dropdownLocator = page.getByTestId(LIST_DROPDOWN);
		AICorePageUtils.waitFor(dropdownLocator);
		dropdownLocator.click();
		Locator listMember = page.locator(LIST_MEMBER_XPATH.replace("{Member}", username));
		AICorePageUtils.waitFor(listMember);
		listMember.click();

	}

	public static void validateToastMessage(Page page, String expectedMessage) {
		Locator toastMessage = page.locator(TOAST_MESSAGE_XPATH);
		AICorePageUtils.waitFor(toastMessage);
		String actualMessage = toastMessage.textContent().trim();
		if (!actualMessage.equals(expectedMessage)) {
			throw new AssertionError("Expected toast message: " + expectedMessage + ", but got: " + actualMessage);
		}
	}

		public static void checkMemberCard(Page page, String role) {
		String username = ConfigUtils.getValue(role.toLowerCase() + "_username").split("@")[0];
		Locator memberCard = page.locator(MEMBER_CARD_XPATH);
		AICorePageUtils.waitFor(memberCard);
		String actualMember = memberCard.textContent().trim();
		if (!actualMember.equals(username)) {
			throw new AssertionError("Expected member card: " + username + ", but got: " + actualMember);
		}
	}

	public static void checkMemberInList(Page page, String member) {
		Locator memberCard = page.locator(MEMBER_XPATH.replace("{member}", member));
		if (!memberCard.isVisible()) {
			throw new AssertionError("Expected " + member + " not present in the list: ");
		}
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

	// add engine to all catalog with different
	public static void userClickOnCreatedTeamName(Page page, String teamName, String timestamp) {
		page.getByText(teamName + " " + timestamp).click();
	}

	public static void userClickOnAddEngineButton(Page page, String addCatalogName) {
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions()
				.setName(CLICK_ON_ADD_CATALOG_TEXT.replace("{addCatalogName}", addCatalogName))).click();
	}

	public static void userSelectEngineFromList(Page page, String catalogName, String timestamp, String selectCatalog,
			String catalogType) {
		// String catalogId =
		// TestResourceTrackerHelper.getInstance().getCatalogId(catalogType);
		Locator dropdownLocator = page
				.locator(SELELCT_THE_ENGINE_DROPDOWN_XPATH.replace("{selectCatalog}", selectCatalog));
		dropdownLocator.press("Enter");
		page.keyboard().press("Control+V");
		AICorePageUtils.waitFor(dropdownLocator);
		page.getByText(catalogName).click();
	}

	public static void userSelectAppFromList(Page page, String catalogName, String selectCatalog, String catalogType,
			String timestamp) {
		Locator dropdownLocator = page
				.locator(SELELCT_THE_ENGINE_DROPDOWN_XPATH.replace("{selectCatalog}", selectCatalog));
		dropdownLocator.press("Enter");
		dropdownLocator.fill(catalogName + " " + timestamp);
		AICorePageUtils.waitFor(dropdownLocator);
		page.getByText(catalogName).click();
	}

	public static void userSelectEngineAccessRole(Page page, String role) {
		page.locator(SELECT_ENGINE_ROLE_XPATH.replace("{role}", role)).check();
	}

	public static boolean userSeeAddedEngineInTheList(Page page, String catalogName, String role) {
		Locator addedEngine = page.getByRole(AriaRole.ROW, new Page.GetByRoleOptions().setName(catalogName))
				.getByLabel(role);
		addedEngine.check();
		AICorePageUtils.waitFor(addedEngine);
		return addedEngine.isVisible();
	}

}
