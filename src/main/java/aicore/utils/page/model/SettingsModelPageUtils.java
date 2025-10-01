package aicore.utils.page.model;

import java.util.List;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

import aicore.framework.ConfigUtils;
import aicore.utils.AICorePageUtils;
import aicore.utils.CommonUtils;

public class SettingsModelPageUtils {

	private static final String MODEL_GROUP_TAB_XPATH = "//div[@role='tablist']//button[text()='{groupTabName}']";
	private static final String MODELS_OPTIONS_XPATH = "//p[text()='{modelOptionName}']";
	private static final String EDIT_SMSS_BUTTON_XPATH = "//span[text()='Edit SMSS']";
	private static final String UPDATE_SMSS_BUTTON_XPATH = "//span[text()='Update SMSS']";
	private static final String SETTINGS_TAB_XPATH = "//button[text()='Access Control']";
	private static final String TILE_SECTION_TITLE_XPATH = "//p[text()='{title}']";
	private static final String MAKE_PUBLIC_SECTION_TEXT_MESSAGE_XPATH = "//p[text()='Private']/following-sibling::p";
	private static final String MAKE_PUBLIC_TOGGLE_BUTTON_XPATH = "//span[@title='Make Model public']";
	private static final String MAKE_DISCOVERABLE_SECTION_TEXT_MESSAGE_XPATH = "//p[text()='Non Discoverable']/following-sibling::p";
	private static final String MAKE_DISCOVERABLE_TOGGLE_BUTTON_XPATH = "//span[@title='Make Model discoverable']";
	private static final String DELETE_SECTION_TEXT_MESSAGE_XPATH = "//p[text()='Delete Model']/following-sibling::p";
	private static final String DELETE_BUTTON_XPATH = "//button//span[text()='Delete']";
	private static final String PENDING_REQUESTS_SECTION_TITLE_XPATH = "//h6[text()='Pending Requests']";
	private static final String PENDING_REQUESTS_SECTION_TEXT_MESSAGE_XPATH = "//div[h6[text()='Pending Requests']]/following-sibling::div//p[contains(text(),'0 pending requests')]";
	private static final String MEMBER_SECTION_TITLE_XPATH = "//h6[text()='Permissions']";
	private static final String MEMBER_SEARCH_ICON_XPATH = "//h6[text()='Permissions']/parent::div/following-sibling::div//*[@data-testid='SearchIcon']";
	private static final String SEARCH_MEMBER_PLACEHOLDER_TEXT = "Search Members";
	private static final String ADD_MEMBERS_BUTTON_XPATH = "//div[text()='Add Members']";
	private static final String ROWS_PER_PAGE_DROPDOWN_XPATH = "//*[name()='svg'][@data-testid='ArrowDropDownIcon']";
	private static final String ROWS_PER_PAGE_DROPDOWN_OPTIONS_LIST_XPATH = "//ul[contains(@class,'MuiList-root MuiList-padding MuiMenu-list')]//li";
	private static final String ADD_MEMBER_XPATH = "//input[@placeholder='Search users' and @type='text' and @role='combobox']";
	private static final String RADIO_BUTTON_XPATH = "//span[div[contains(text(),'{role}')]]/ancestor::div[contains(@class, 'MuiCardHeader-root')]//input[@type='radio']";
	private static final String SAVE_BUTTON_XPATH = "//button[contains(@class, 'MuiButton-containedPrimary') and .//span[text()='Save']]";
	private static final String DELETE_SUCCESS_TOAST_XPATH = "//div[contains(@class, 'MuiAlert-message')]";
	private static final String DELETE_PERMISSION_ERROR_TOAST_XPATH = "//div[contains(@class, 'MuiAlert-message') and contains(text(), 'does not exist or user does not have permissions')]";
	private static final String ADDED_MEMBER_DELETE_ICON_XPATH = "td:has(button svg[data-testid='EditIcon']) button svg[data-testid='DeleteIcon']";
	private static final String CONFIRM_BUTTON_XPATH = "//button[span[text()='Confirm']]";
	private static final String USAGE_TAB_XPATH = "//button[text()='Usage']";
	private static final String MODEL_ID_COPY_OPTION = "//button[@aria-label='copy Model ID']";
	private static final String USAGE_CODE_SECTION_XPATH = "//h6[text()='{sectionName}']/following-sibling::pre";
	private static final String TILE_XPATH = "//div[contains(@class,'MuiCardHeader-content')]/span[contains(text(),'{tileName}')]";
	private static final String SMSS_PROPERTIES_FIELDS_COMMON_XPATH = "//div[@class='view-line']//span[@class='mtk1'][starts-with(text(), '{fieldName}')]";
	private static final String SEARCH_BOX_XPATH = "[id ^='generated-id-'][placeholder='Search']";
	private static final String DISCOVERABLE_MODELS_BUTTON_XPATH = "//button[text()='Discoverable Models']";

	private static final String SEARCH_BUTTON_XPATH = "[placeholder=\"Search Members\"]";
	private static final String SEARCH_ICON_XPATH = "//button[contains(@class,'MuiButtonBase-root MuiIconButton-root')]//*[name()='svg'][@data-testid='SearchIcon']";

	public static void clickOnSettingsTab(Page page) {
		page.click(SETTINGS_TAB_XPATH);
	}

	public static boolean verifyMakePublicSectionIsVisible(Page page, String title) {
		boolean isMakePublicSectionVisible = page.isVisible(TILE_SECTION_TITLE_XPATH.replace("{title}", title));
		return isMakePublicSectionVisible;
	}

	public static String verifyMakePublicSectionTextMessage(Page page) {
		String actualTextMessage = page.textContent(MAKE_PUBLIC_SECTION_TEXT_MESSAGE_XPATH);
		return actualTextMessage;
	}

	public static boolean verifyMakePublicToggleButtonIsVisible(Page page) {
		boolean isMakePublicToggleButtonVisible = page.isVisible(MAKE_PUBLIC_TOGGLE_BUTTON_XPATH);
		return isMakePublicToggleButtonVisible;
	}

	public static boolean verifyMakeDiscoverableSectionIsVisible(Page page, String title) {
		boolean isMakeDiscoverableSectionVisible = page.isVisible(TILE_SECTION_TITLE_XPATH.replace("{title}", title));
		return isMakeDiscoverableSectionVisible;
	}

	public static String verifyMakeDiscoverableSectionTextMessage(Page page) {
		String actualTextMessage = page.textContent(MAKE_DISCOVERABLE_SECTION_TEXT_MESSAGE_XPATH);
		return actualTextMessage;
	}

	public static boolean verifyMakeDiscoverableToggleButtonIsVisible(Page page) {
		boolean isMakeDiscoverableToggleButtonVisible = page.isVisible(MAKE_DISCOVERABLE_TOGGLE_BUTTON_XPATH);
		return isMakeDiscoverableToggleButtonVisible;
	}

	public static boolean verifyDeleteSectionIsVisible(Page page, String title) {
		boolean isDeleteSectionVisible = page.isVisible(TILE_SECTION_TITLE_XPATH.replace("{title}", title));
		return isDeleteSectionVisible;
	}

	public static String verifyDeleteSectionTextMessage(Page page) {
		String actualTextMessage = page.textContent(DELETE_SECTION_TEXT_MESSAGE_XPATH).trim();
		return actualTextMessage;
	}

	public static boolean verifyDeleteButtonIsVisible(Page page) {
		boolean isDeleteButtonVisible = page.isVisible(DELETE_BUTTON_XPATH);
		return isDeleteButtonVisible;
	}

	public static boolean verifyPendingRequestsSectionIsVisible(Page page) {
		Locator pendingRequest = page.locator(PENDING_REQUESTS_SECTION_TITLE_XPATH);
		pendingRequest.scrollIntoViewIfNeeded();
		boolean isPendingRequestsSectionVisible = pendingRequest.isVisible();
		return isPendingRequestsSectionVisible;
	}

	public static String verifyPendingRequestsSectionTextMessage(Page page) {
		Locator pendingRequestText = page.locator(PENDING_REQUESTS_SECTION_TEXT_MESSAGE_XPATH);
		pendingRequestText.scrollIntoViewIfNeeded();
		String actualTextMessage = pendingRequestText.textContent().trim();
		return actualTextMessage;
	}

	public static boolean verifyMembersSectionIsVisible(Page page) {
		boolean isMembersSectionVisible = page.isVisible(MEMBER_SECTION_TITLE_XPATH);
		return isMembersSectionVisible;
	}

	public static boolean verifySearchMembersSearchBoxIsVisible(Page page) {
		page.click(MEMBER_SEARCH_ICON_XPATH);
		boolean isSearchMembersTextBoxVisible = page.getByPlaceholder(SEARCH_MEMBER_PLACEHOLDER_TEXT).isVisible();
		return isSearchMembersTextBoxVisible;
	}

	public static boolean verifyAddMembersButtonIsVisible(Page page) {
		boolean isAddMembersButtonVisible = page.isVisible(ADD_MEMBERS_BUTTON_XPATH);
		return isAddMembersButtonVisible;
	}

	public static boolean verifyRowsPerPageDropdownIsVisible(Page page) {
		Locator ROWS_PER_PAGE_DROPDOWN = page.locator(ROWS_PER_PAGE_DROPDOWN_XPATH);
		AICorePageUtils.waitFor(ROWS_PER_PAGE_DROPDOWN);
		ROWS_PER_PAGE_DROPDOWN.scrollIntoViewIfNeeded();
		boolean isRowsPerPageDropdownVisible = ROWS_PER_PAGE_DROPDOWN.isVisible();
		return isRowsPerPageDropdownVisible;
	}

	public static List<String> verifyRowsPerPageDropdownOptions(Page page) {
		Locator ROWS_PER_PAGE_DROPDOWN = page.locator(ROWS_PER_PAGE_DROPDOWN_XPATH);
		ROWS_PER_PAGE_DROPDOWN.scrollIntoViewIfNeeded();
		ROWS_PER_PAGE_DROPDOWN.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		ROWS_PER_PAGE_DROPDOWN.click();
		page.waitForTimeout(1000);
		List<String> actualOptions = page.locator(ROWS_PER_PAGE_DROPDOWN_OPTIONS_LIST_XPATH).allTextContents();
		return actualOptions;
	}

	public static void clickOnEditSMSSButton(Page page) {
		page.click(EDIT_SMSS_BUTTON_XPATH);
	}

	public static void clickOnUpdateSMSSButton(Page page) {
		page.click(UPDATE_SMSS_BUTTON_XPATH);
	}

	public static void editSMSSFieldValues(Page page, String fieldName, String newValue) {
		String locator = SMSS_PROPERTIES_FIELDS_COMMON_XPATH.replace("{fieldName}", fieldName);
		String fullText = page.textContent(locator);
		String fieldValue = CommonUtils.splitTrimValue(fullText, fieldName);

		if (fieldValue != null) {
			page.click(locator);
			page.press(locator, "End");
			page.locator(locator).press("Shift+Control+ArrowLeft");
			page.keyboard().press("Backspace");
			page.keyboard().type(newValue);
		}
	}

	public static void pageReload(Page page) {
		page.reload();
	}

	public static void clickOnOptionsGroupTab(Page page, String groupName) {
		page.locator(MODEL_GROUP_TAB_XPATH.replace("{groupTabName}", groupName)).isVisible();
		page.locator(MODEL_GROUP_TAB_XPATH.replace("{groupTabName}", groupName)).click();
	}

	public static boolean verifyModelOptionIsVisible(Page page, String modelOption) {
		boolean isModelVisible = page.locator(MODELS_OPTIONS_XPATH.replace("{modelOptionName}", modelOption))
				.isVisible();
		return isModelVisible;
	}

	public static boolean verifyTileIsVisible(Page page, String tileName) {
		boolean isTileVisible = page.isVisible(TILE_XPATH.replace("{tileName}", tileName));
		return isTileVisible;
	}

	public static void clickOnSearchBox(Page page, String string) {
		page.locator(SEARCH_BOX_XPATH).isVisible();
		page.locator(SEARCH_BOX_XPATH).click();
		page.locator(SEARCH_BOX_XPATH).fill(string);
	}

	public static void clickOnAccessControl(Page page) {
		page.click(SETTINGS_TAB_XPATH);
	}

	public static void clickOnAddMembersButton(Page page) {
		page.click(ADD_MEMBERS_BUTTON_XPATH);
	}

	public static void addMember(Page page, String role, boolean useDocker) throws InterruptedException {
		String username = ConfigUtils.getValue(role.toLowerCase() + "_username").split("@")[0];
		if (useDocker) {
			username = username + " lastname";
			// search is by user name first name and lastname
			page.fill(ADD_MEMBER_XPATH, username);
			page.getByTitle("Name: " + username).click();
		} else {
			// page.click(ADD_MEMBER_XPATH);
			page.fill(ADD_MEMBER_XPATH, username);
//			page.getByText(username).click();
			page.waitForTimeout(800);
			page.locator(ADD_MEMBER_XPATH).press("ArrowDown");
			page.locator(ADD_MEMBER_XPATH).press("Enter");
		}
		page.click(RADIO_BUTTON_XPATH.replace("{role}", role));
		page.click(SAVE_BUTTON_XPATH);
		// THESE ELEMENTS REMOVED ON A SEMOSS UPDATE ON May 12, 2025
		Locator alertCloseLocator = page.locator("//button[@aria-label='Close']");
		AICorePageUtils.waitFor(alertCloseLocator);
		alertCloseLocator.click();
		alertCloseLocator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
	}

	public static void clickOnDeleteButton(Page page) {
		Locator deleteButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Delete"));
		deleteButton.click();
		deleteButton.click();
		page.waitForCondition(
				() -> page.isVisible(DELETE_SUCCESS_TOAST_XPATH) || page.isVisible(DELETE_PERMISSION_ERROR_TOAST_XPATH),
				new Page.WaitForConditionOptions().setTimeout(5000));
		// Added cancel button code because pop-up is not closing because of bug
//		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Cancel")).click();
	}

	public static boolean isDeleteSuccessful(Page page) {
		return page.isVisible(DELETE_SUCCESS_TOAST_XPATH);
	}

	public static boolean isPermissionErrorDisplayed(Page page) {
		return page.isVisible(DELETE_PERMISSION_ERROR_TOAST_XPATH);
	}

	public static boolean isAddMemberButtonVisible(Page page) {
		return page.isVisible(ADD_MEMBERS_BUTTON_XPATH);
	}

	public static void deleteAddedMember(Page page, String role) {
		Locator deleteIcon = page.locator(ADDED_MEMBER_DELETE_ICON_XPATH.replace("{role}", role));
		deleteIcon.scrollIntoViewIfNeeded();
		deleteIcon.hover();
		deleteIcon.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		deleteIcon.click();
		page.locator(CONFIRM_BUTTON_XPATH).click();
	}

	public static void clickOnUsageTab(Page page) {
		page.click(USAGE_TAB_XPATH);
	}

	public static String copyModelID(Page page) {
		page.locator(MODEL_ID_COPY_OPTION).click();
		String modelId = null;
		String clipboardText = AICorePageUtils.readStringFromClipboard(page);
		if (clipboardText != null) {
			modelId = clipboardText.trim();
		} else {
			// get current url of the page
			String currentUrl = page.url();
			// Extract the substring after the last slash this will also give us the model
			// id
			int lastSlashIndex = currentUrl.lastIndexOf('/');
			modelId = currentUrl.substring(lastSlashIndex + 1);
		}
		return modelId;
	}

	public static String getFullSectionCodeByHeading(Page page, String headingText) {
		StringBuilder sectionCodeContents = new StringBuilder();
		switch (headingText) {
		case "How to use in Javascript":
			Locator sections = page.locator(USAGE_CODE_SECTION_XPATH.replace("{sectionName}", headingText));
			for (int i = 0; i < sections.count(); i++) {
				Locator subsection = sections.nth(i);
				String codeContent = subsection.textContent().trim();
				sectionCodeContents.append(codeContent).append("\n");
			}
			break;
		default:
			String codeContent = page.locator(USAGE_CODE_SECTION_XPATH.replace("{sectionName}", headingText))
					.textContent().trim();
			sectionCodeContents.append(codeContent).append("\n");
		}
		return sectionCodeContents.toString().trim();
	}

	public static void clickOnDiscoverableModelsButton(Page page) {
		page.click(DISCOVERABLE_MODELS_BUTTON_XPATH);
	}
}
