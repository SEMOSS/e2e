package aicore.utils;

import java.util.ArrayList;
import java.util.List;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

public class ModelPageUtils {

	private static final String MODEL_GROUP_XPATH = "//div[text()='{groupName}']";
	private static final String MODELS_UNDER_GROUP_XPATH = "//div[text()='{groupName}']/following-sibling::div//p[text()='{modelName}']";
	private static final String SELECT_OPENAI_XPATH = "//p[text()='{OpenAIModelName}']";
	private static final String CATALOG_NAME_XPATH = "//label[@id='NAME-label']";
	private static final String OPEN_AI_KEY_XPATH = "//input[@id='OPEN_AI_KEY']";
	private static final String VARIABLE_NAME_ID = "#VAR_NAME";
	private static final String CREATE_MODEL_BUTTON_XPATH = "//button[@type='submit']";
	private static final String MODEL_TOAST_MESSAGE = "Successfully added LLM to catalog";
	private static final String MODEL_CATALOG_SEARCH_TEXTBOX_XPATH = "//input[@placeholder='Search']";
	private static final String SEARCHED_MODEL_XPATH = "//div[@class='css-q5m5ti']//p[text()='{modelName}']";
	// SMSS field
	private static final String SMSS_TAB_XPATH = "//button[text()='SMSS']";
	private static final String NAME_SMSS_PROPERTIES_XPATH = "//div[@class='view-line']//span[@class='mtk1'][starts-with(text(), 'NAME')]";
	private static final String VAR_NAME_SMSS_PROPERTIES_XPATH = "//div[@class='view-line']//span[@class='mtk1'][starts-with(text(), 'VAR_NAME')]";
	private static final String SMSS_PROPERTIES_FIELDS_COMMON_XPATH = "//div[@class='view-line']//span[@class='mtk1'][starts-with(text(), '{fieldName}')]";
	private static final String EDIT_SMSS_BUTTON_XPATH = "//span[text()='Edit SMSS']";
	private static final String UPDATE_SMSS_BUTTON_XPATH = "//span[text()='Update SMSS']";
	// Settings field
	private static final String SETTINGS_TAB_XPATH = "//button[text()='Access Control']";
	private static final String MAKE_PUBLIC_SECTION_TITLE_XPATH = "(//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-4 css-1udb513'])[1]//p[text()='{title}']";
	private static final String MAKE_PUBLIC_SECTION_TEXT_MESSAGE_XPATH = "(//div[contains(@class,'MuiGrid-root MuiGrid-item MuiGrid-grid')])[1]//p[contains(@class,'MuiTypography-root MuiTypography-body2')]";
	private static final String MAKE_PUBLIC_TOGGLE_BUTTON_XPATH = "(//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-4 css-1udb513'])[1]//span[@class='MuiSwitch-track css-1ju1kxc']";
	private static final String MAKE_DISCOVERABLE_SECTION_TITLE_XPATH = "(//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-4 css-1udb513'])[2]//p[text()='{title}']";
	private static final String MAKE_DISCOVERABLE_SECTION_TEXT_MESSAGE_XPATH = "(//div[contains(@class,'MuiGrid-root MuiGrid-item MuiGrid-grid')])[2]//p[contains(@class,'MuiTypography-root MuiTypography-body2')]";
	private static final String MAKE_DISCOVERABLE_TOGGLE_BUTTON_XPATH = "(//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-4 css-1udb513'])[2]//span[@class='MuiSwitch-track css-1ju1kxc']";
	private static final String DELETE_SECTION_TITLE_XPATH = "(//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-4 css-1udb513'])[3]//p[text()='{title}']";
	private static final String DELETE_SECTION_TEXT_MESSAGE_XPATH = "(//div[contains(@class,'MuiGrid-root MuiGrid-item MuiGrid-grid')])[3]//p[contains(@class,'MuiTypography-root MuiTypography-body2')]";
	private static final String DELETE_BUTTON_XPATH = "(//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-4 css-1udb513'])[3]//div[@class='MuiAlert-action css-1mzcepu']";
	private static final String PENDING_REQUESTS_SECTION_TITLE_XPATH = "//div[@class='css-aplv3o']//div[@class='css-163ryps']";
	private static final String PENDING_REQUESTS_SECTION_TEXT_MESSAGE_XPATH = "//div[h6[text()='Pending Requests']]/following-sibling::div//p[contains(text(),'0 pending requests')]";
	private static final String MEMBER_SECTION_TITLE_XPATH = "//div[@class='css-1hk1ec8']//div[@class='css-163ryps']";
	private static final String MEMBER_SEARCH_ICON_XPATH = "//*[name()='svg'][@data-testid='SearchIcon']";
	private static final String SEARCH_MEMBERS_SEARCHBOX_XPATH = "//div[contains(@class,'MuiInputBase-root MuiOutlinedInput-root MuiInputBase-colorPrimary')]";
	private static final String ADD_MEMBERS_BUTTON_XPATH = "//div[@class='css-gm8qym']";
	private static final String ROWS_PER_PAGE_DROPDOWN_XPATH = "//div[@class='MuiInputBase-root MuiInputBase-colorPrimary css-1tsucmk']";
	private static final String ROWS_PER_PAGE_DROPDOWN_OPTIONS_LIST_XPATH = "//ul[contains(@class,'MuiList-root MuiList-padding MuiMenu-list')]//li";
	private static final String ADD_MEMBER_XPATH = "//input[@placeholder='Search users' and @type='text' and @role='combobox']";
	private static final String RADIO_BUTTON_XPATH = "//span[div[contains(text(),'{role}')]]/ancestor::div[contains(@class, 'MuiCardHeader-root')]//input[@type='radio']";
	private static final String SAVE_BUTTON_XPATH = "//button[contains(@class, 'MuiButton-containedPrimary') and .//span[text()='Save']]";
	private static final String DELETE_SUCCESS_TOAST_XPATH = "//div[contains(@class, 'MuiAlert-message')]";
	private static final String DELETE_PERMISSION_ERROR_TOAST_XPATH = "//div[contains(@class, 'MuiAlert-message') and contains(text(), 'does not exist or user does not have permissions')]";
	private static final String MEMBER_ADDED_SUCCESS_TOAST_MESSAGE_XPATH = "//div[contains(@class, 'MuiAlert-message') and contains(text(), 'Successfully added member permissions')]";
	private static final String MEMBER_ADDED_SUCCESS_TOAST_MESSAGE_CLOSE_ICON_XPATH = "//button[@aria-label='Close']";
	private static final String ADDED_MEMBER_DELETE_ICON_XPATH = "td:has(button svg[data-testid='EditIcon']) button svg[data-testid='DeleteIcon']";
	private static final String CONFIRM_BUTTON_XPATH = "//button[span[text()='Confirm']]";
	// Edit model
	private static final String EDIT_BUTTON_XPATH = "//button[contains(@class, 'MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary MuiButton-sizeMedium MuiButton-containedSizeMedium ')]";
	private static final String TAG_TEXTBOX = "Tag";
	private static final String SUBMIT_BUTTON_XPATH = "//span[text()='Submit']";
	private static final String EDIT_SUCCESS_TOAST_MESSAGE = "Successfully set the new metadata values for the engine";
	private static final String DETAILS_TEXTBOX_XPATH = "//textarea[@class='inputarea monaco-mouse-cursor-text']";
	private static final String DESCRIPTION_TEXTBOX_LABEL = "Description";
	private static final String DOMAIN_TEXTBOX_LABEL = "Domain";
	private static final String DATA_CLASSIFICATION_TEXTBOX_XPATH = "(//input[@aria-autocomplete='list'])[3]";
	private static final String DATA_RESTRICTIONS_TEXTBOX_XPATH = "(//input[@aria-autocomplete='list'])[4]";
	private static final String DESCRIPTION_TEXT_XPATH = "//div[@class='css-1xfr4eb']//h6";
	private static final String MODEL_TAGS_XPATH = "//div[@class='css-fm4r4t']//span";
	private static final String DETAILS_UNDER_OVERVIEW_XPATH = "//div[h6/h6[text()='Details']]/following-sibling::div[contains(@class,'MuiStack-root')]";
	private static final String TAGS_UNDER_OVERVIEW_XPATH = "//div[h6/h6[contains(text(), 'Tag')]]/following-sibling::div";
	private static final String DOMAIN_TEXTS_UNDER_OVERVIEW_XPATH = "//div[h6/h6[contains(text(), 'Domain')]]/following-sibling::div";
	private static final String DATA_CLASSIFICATION_OPTIONS_UNDER_OVERVIEW_XPATH = "//div[h6/h6[contains(text(), 'Data classification')]]/following-sibling::div";
	private static final String DATA_RESTRICTIONS_OPTIONS_UNDER_OVERVIEW_XPATH = "//div[h6/h6[contains(text(), 'Data restrictions')]]/following-sibling::div";

	// Usage
	private static final String USAGE_TAB_XPATH = "//button[text()='Usage']";
	private static final String MODEL_ID_COPY_OPTION = "//button[@aria-label='copy Model ID']";
	private static final String USAGE_CODE_SECTION_XPATH = "//h6[text()='{sectionName}']/following-sibling::pre";
	private static final String TILE_XPATH = "//div[contains(@class,'MuiCardHeader-content')]/span[contains(text(),'{tileName}')]";

	public void addModelButton(Page page) {
		page.getByLabel("Navigate to import Model").click();
	}

	public static void selectOpenAi(Page page, String aiModelName) {
		page.click(SELECT_OPENAI_XPATH.replace("{OpenAIModelName}", aiModelName));
	}

	public static void enterCatalogName(Page page, String CatalogName, String timestamp) {
		String uniqueCatalogName = CatalogName + timestamp;
		page.fill(CATALOG_NAME_XPATH, uniqueCatalogName);
	}

	public static void enterOpenAIKey(Page page, String openAIKey) {
		page.fill(OPEN_AI_KEY_XPATH, openAIKey);
	}

	public static void enterVariableName(Page page, String varName) {
		page.fill(VARIABLE_NAME_ID, varName);
	}

	public static void createModel(Page page) {
		page.click(CREATE_MODEL_BUTTON_XPATH);
	}

	public static String modelCreationToastMessage(Page page) {
		Locator toastMessage = page.getByRole(AriaRole.ALERT)
				.filter(new Locator.FilterOptions().setHasText(MODEL_TOAST_MESSAGE));
		return toastMessage.textContent().trim();
	}

	public static void waitForModelCreationToastMessageDisappear(Page page) {
		page.getByRole(AriaRole.ALERT).filter(new Locator.FilterOptions().setHasText(MODEL_TOAST_MESSAGE))
				.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
	}

	public static String verifyModelTitle(Page page, String modelTitle, String timestamp) {
		Locator actualmodelTitle = page.getByRole(AriaRole.HEADING,
				new Page.GetByRoleOptions().setName(modelTitle + timestamp));
		actualmodelTitle.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		return actualmodelTitle.textContent().trim();
	}

	public static void clickOnSMSSTab(Page page) {
		page.click(SMSS_TAB_XPATH);
	}

	public static String getExpectedCatalogTitle(String modelTitle, String timestamp) {
		String expTitle = modelTitle + timestamp;
		return expTitle;
	}

	public static String verifyNameInSMSS(Page page) {
		String nameInSMSSProperties = page.textContent(NAME_SMSS_PROPERTIES_XPATH);
		return nameInSMSSProperties;
	}

	public static String verifyVarNameInSMSS(Page page) {
		String varNameInSMSSProperties = page.textContent(VAR_NAME_SMSS_PROPERTIES_XPATH);
		return varNameInSMSSProperties;
	}

	public static String verifyKeepConversationHistoryValueInSMSS(Page page, String fieldName) {
		String varNameInSMSSProperties = page
				.textContent(SMSS_PROPERTIES_FIELDS_COMMON_XPATH.replace("{fieldName}", fieldName));
		return varNameInSMSSProperties;
	}
//Edit model

	public static void searchModelCatalog(Page page, String modelName, String timestamp) {
		page.locator(MODEL_CATALOG_SEARCH_TEXTBOX_XPATH).click();
		page.locator(MODEL_CATALOG_SEARCH_TEXTBOX_XPATH).fill(modelName + timestamp);
	}

	public static void selectModelFromSearchOptions(Page page, String modelName, String timestamp) {
		page.locator((SEARCHED_MODEL_XPATH.replace("{modelName}", modelName + timestamp))).isVisible();
		page.locator(SEARCHED_MODEL_XPATH.replace("{modelName}", modelName + timestamp)).click();
	}

	public static boolean verifyModelIsDisplayedOnCatalogPage(Page page, String modelName, String timestamp) {
		String modelNameWithTimestamp = SEARCHED_MODEL_XPATH.replace("{modelName}", modelName + timestamp);
		page.waitForSelector(modelNameWithTimestamp, new Page.WaitForSelectorOptions().setTimeout(10000));
		boolean isModelVisible = page.isVisible(modelNameWithTimestamp);
		return isModelVisible;
	}

	public static void clickOnEditButton(Page page) {
		page.click(EDIT_BUTTON_XPATH);
	}

	public static void enterDetails(Page page, String detailsText) {
		page.fill(DETAILS_TEXTBOX_XPATH, detailsText);
	}

	public static void enterDescription(Page page, String descriptionText) {
		page.getByLabel(DESCRIPTION_TEXTBOX_LABEL).fill(descriptionText);
	}

	public static void enterTagName(Page page, String tagName) {
		page.getByLabel(TAG_TEXTBOX).click();
		page.getByLabel(TAG_TEXTBOX).fill(tagName);
		page.getByLabel(TAG_TEXTBOX).press("Enter");
	}

	public static void enterDomainName(Page page, String domainName) {
		page.getByLabel(DOMAIN_TEXTBOX_LABEL).fill(domainName);
		page.getByLabel(DOMAIN_TEXTBOX_LABEL).press("Enter");
	}

	public static void selectDataClassificationOption(Page page, String option) {
		page.click(DATA_CLASSIFICATION_TEXTBOX_XPATH);
		page.fill(DATA_CLASSIFICATION_TEXTBOX_XPATH, option);
		page.locator(DATA_CLASSIFICATION_TEXTBOX_XPATH).press("ArrowDown");
		page.locator(DATA_CLASSIFICATION_TEXTBOX_XPATH).press("Enter");
	}

	public static void selectDataRestrictionsOption(Page page, String option) {
		page.click(DATA_RESTRICTIONS_TEXTBOX_XPATH);
		page.fill(DATA_RESTRICTIONS_TEXTBOX_XPATH, option);
		page.locator(DATA_RESTRICTIONS_TEXTBOX_XPATH).press("ArrowDown");
		page.locator(DATA_RESTRICTIONS_TEXTBOX_XPATH).press("Enter");
	}

	public static void clickOnSubmit(Page page) {
		page.click(SUBMIT_BUTTON_XPATH);
	}

	public static String verifyEditSuccessfullToastMessage(Page page) {
		Locator toastMessage = page.getByRole(AriaRole.ALERT)
				.filter(new Locator.FilterOptions().setHasText(EDIT_SUCCESS_TOAST_MESSAGE));
		return toastMessage.textContent().trim();
	}

	public static void waitForEditSuccessToastMessageToDisappear(Page page) {
		page.locator(EDIT_SUCCESS_TOAST_MESSAGE)
				.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
	}

	public static String verifyDescriptionText(Page page) {

		String descriptionText = page.textContent(DESCRIPTION_TEXT_XPATH).trim();
		return descriptionText;
	}

	public static List<String> verifyTagNames(Page page) {
		List<String> tags = new ArrayList<String>();
		List<String> tagsText = page.locator(MODEL_TAGS_XPATH).allInnerTexts();
		CommonUtils.extractOverviewSectionValues(tags, tagsText);
		return tags;
	}

	public static String verifyDetailsTextUnderOverview(Page page) {
		Locator shadowElement = page.locator(DETAILS_UNDER_OVERVIEW_XPATH).locator("p");
		shadowElement.waitFor();
		String text = shadowElement.innerText().trim();
		return text;
	}

	public static List<String> verifyTagNamesUnderOverview(Page page) {
		List<String> tags = new ArrayList<String>();
		List<String> tagsText = page.locator(TAGS_UNDER_OVERVIEW_XPATH).allInnerTexts();
		CommonUtils.extractOverviewSectionValues(tags, tagsText);
		return tags;
	}

	public static List<String> verifyDomainValuesUnderOverview(Page page) {
		List<String> domains = new ArrayList<String>();
		List<String> domainText = page.locator(DOMAIN_TEXTS_UNDER_OVERVIEW_XPATH).allInnerTexts();
		CommonUtils.extractOverviewSectionValues(domains, domainText);
		return domains;
	}

	public static List<String> verifyDataClassificationOptionsUnderOverview(Page page) {
		List<String> dataClassificationOptions = new ArrayList<String>();
		List<String> dataClassificationOptionsText = page.locator(DATA_CLASSIFICATION_OPTIONS_UNDER_OVERVIEW_XPATH)
				.allInnerTexts();
		CommonUtils.extractOverviewSectionValues(dataClassificationOptions, dataClassificationOptionsText);
		return dataClassificationOptions;
	}

	public static List<String> verifyDataRestrictionOptionsUnderOverview(Page page) {
		List<String> dataRestrictionOptions = new ArrayList<String>();
		List<String> dataRestrictionOptionsText = page.locator(DATA_RESTRICTIONS_OPTIONS_UNDER_OVERVIEW_XPATH)
				.allInnerTexts();
		CommonUtils.extractOverviewSectionValues(dataRestrictionOptions, dataRestrictionOptionsText);
		return dataRestrictionOptions;
	}

	// Methods used for settings

	public static void clickOnSettingsTab(Page page) {
		page.click(SETTINGS_TAB_XPATH);
	}

	public static boolean verifyMakePublicSectionIsVisible(Page page, String title) {
		boolean isMakePublicSectionVisible = page.isVisible(MAKE_PUBLIC_SECTION_TITLE_XPATH.replace("{title}", title));
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
		boolean isMakeDiscoverableSectionVisible = page
				.isVisible(MAKE_DISCOVERABLE_SECTION_TITLE_XPATH.replace("{title}", title));
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
		boolean isDeleteSectionVisible = page.isVisible(DELETE_SECTION_TITLE_XPATH.replace("{title}", title));
		return isDeleteSectionVisible;
	}

	public static String verifyDeleteSectionTextMessage(Page page) {
		String actualTextMessage = page.textContent(DELETE_SECTION_TEXT_MESSAGE_XPATH);
		return actualTextMessage;
	}

	public static boolean verifyDeleteButtonIsVisible(Page page) {
		boolean isDeleteButtonVisible = page.isVisible(DELETE_BUTTON_XPATH);
		return isDeleteButtonVisible;
	}

	public static boolean verifyPendingRequestsSectionIsVisible(Page page) {
		boolean isPendingRequestsSectionVisible = page.isVisible(PENDING_REQUESTS_SECTION_TITLE_XPATH);
		return isPendingRequestsSectionVisible;
	}

	public static String verifyPendingRequestsSectionTextMessage(Page page) {
		String actualTextMessage = page.textContent(PENDING_REQUESTS_SECTION_TEXT_MESSAGE_XPATH).trim();
		return actualTextMessage;
	}

	public static boolean verifyMembersSectionIsVisible(Page page) {
		boolean isMembersSectionVisible = page.isVisible(MEMBER_SECTION_TITLE_XPATH);
		return isMembersSectionVisible;
	}

	public static boolean verifySearchMembersSearchBoxIsVisible(Page page) {
		page.click(MEMBER_SEARCH_ICON_XPATH);
		boolean isSearchMembersTextBoxVisible = page.isVisible(SEARCH_MEMBERS_SEARCHBOX_XPATH);
		return isSearchMembersTextBoxVisible;
	}

	public static boolean verifyAddMembersButtonIsVisible(Page page) {
		boolean isAddMembersButtonVisible = page.isVisible(ADD_MEMBERS_BUTTON_XPATH);
		return isAddMembersButtonVisible;
	}

	public static boolean verifyRowsPerPageDropdownIsVisible(Page page) {
		page.locator(ROWS_PER_PAGE_DROPDOWN_XPATH).scrollIntoViewIfNeeded();
		boolean isRowsPerPageDropdownVisible = page.isVisible(ROWS_PER_PAGE_DROPDOWN_XPATH);
		return isRowsPerPageDropdownVisible;
	}

	public static List<String> verifyRowsPerPageDropdownOptions(Page page) {
		page.click(ROWS_PER_PAGE_DROPDOWN_XPATH);
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

	public static boolean verifyGroupIsVisible(Page page, String groupName) {
		boolean isGroupVisible = page.isVisible(MODEL_GROUP_XPATH.replace("{groupName}", groupName));
		return isGroupVisible;
	}

	public static boolean VerifyModelIsVisible(Page page, String groupName, String modelName) {
		boolean isModelVisible = page.isVisible(
				MODELS_UNDER_GROUP_XPATH.replace("{groupName}", groupName).replace("{modelName}", modelName));
		return isModelVisible;
	}

	public static boolean verifyTileIsVisible(Page page, String tileName) {
		boolean isTileVisible = page.isVisible(TILE_XPATH.replace("{tileName}", tileName));
		return isTileVisible;
	}

	public static void clickOnSearchBox(Page page, String string) {
		page.getByPlaceholder("Search").isVisible();
		page.getByPlaceholder("Search").click();
		page.getByPlaceholder("Search").fill(string);
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
			Locator addMemberSearchbar = page.locator(ADD_MEMBER_XPATH);
			addMemberSearchbar.click();
			addMemberSearchbar.fill(username);
			page.waitForTimeout(2000);
//			addMemberSearchbar.press("ArrowDown");
//			addMemberSearchbar.press("Enter");
			page.getByTitle(username).click();
			page.click(RADIO_BUTTON_XPATH.replace("{role}", role));
			page.click(SAVE_BUTTON_XPATH);
		}

		// THESE ELEMENTS REMOVED ON A SEMOSS UPDATE ON May 12, 2025
//		page.click(MEMBER_ADDED_SUCCESS_TOAST_MESSAGE_CLOSE_ICON_XPATH);
//		page.locator(MEMBER_ADDED_SUCCESS_TOAST_MESSAGE_XPATH)
//				.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
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
		page.click(ADDED_MEMBER_DELETE_ICON_XPATH.replace("{role}", role));
		page.click(CONFIRM_BUTTON_XPATH);
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

	//////////// MODEL PERMISSIONS - AUTHOR

	private static final String VIEW_OVERVIEW_TAB_XPATH = "//button[contains(@class, 'MuiTab-root') and text()='Overview']";
	private static final String VIEW_USAGE_TAB_XPATH = "//button[contains(@class, 'MuiTab-root') and text()='Usage']";
	private static final String VIEW_SMSS_TAB_XPATH = "//button[contains(@class, 'MuiTab-root') and text()='SMSS']";
	private static final String VIEW_EDIT_SMSS_BUTTON_XPATH = "//span[text()='Edit SMSS']";
	private static final String VIEW_ACCESSCONTROL_TAB_XPATH = "//button[contains(@class, 'MuiTab-root') and text()='Access Control']";

	public static boolean canViewOverview(Page page) {
		return page.isVisible(VIEW_OVERVIEW_TAB_XPATH);
	}

	public static boolean canViewUsage(Page page) {
		return page.isVisible(VIEW_USAGE_TAB_XPATH);
	}

	public static boolean canViewSMSSDetails(Page page) {
		return page.isVisible(VIEW_SMSS_TAB_XPATH);
	}

	public static boolean canViewEditSMSS(Page page) {
		return page.isVisible(VIEW_EDIT_SMSS_BUTTON_XPATH);
	}

	public static boolean canViewAccessControl(Page page) {
		return page.isVisible(VIEW_ACCESSCONTROL_TAB_XPATH);
	}
}
