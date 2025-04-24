package aicore.pages;

import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

import aicore.utils.CommonUtils;
import aicore.utils.ConfigUtils;

public class AddModelToCatalogPage {

	private Page page;
	private String timestamp;

	private static final String MODEL_GROUP_XPATH = "//div[text()='{groupName}']";
	private static final String MODELS_UNDER_GROUP_XPATH = "//div[text()='{groupName}']/following-sibling::div//p[text()='{modelName}']";
	private static final String SELECT_OPENAI_XPATH = "//p[text()='{ModelName}']";
	private static final String ADD_FILE_XPATH = "//input[@type='file']";
	private static final String ADD_FILE_NAME_XPATH = "//span[@title='{fileName}']";
	private static final String CATALOG_NAME_XPATH = "//label[@id='NAME-label']";
	private static final String OPEN_AI_KEY_XPATH = "//input[@id='OPEN_AI_KEY']";
	private static final String VARIABLE_NAME_ID = "#VAR_NAME";
	private static final String CREATE_MODEL_BUTTON_XPATH = "//button[@type='submit']";
	private static final String MODEL_CARD_XPATH = "//p[contains(text(),'{modelName}')]";
	private static final String DELETE_TOAST_MESSAGE_XPATH = "//div[text()='Successfully deleted Model']";
	// private static final String MODEL_TOAST_MESSAGE_XPATH =
	// "//div[@class='MuiAlert-message css-1xsto0d']";
	private static final String MODEL_TOAST_MESSAGE = "Successfully added LLM to catalog";
	private static final String CRAETED_MODEL_XPATH = "//h4[@class='MuiTypography-root MuiTypography-h4 css-grm9aw']";
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
	private static final String MODEL_ID_COPY_OPTION_XPATH = "//button[@aria-label='copy Model ID']";
	private static final String USAGE_COMMAND_COPY_OPTION_XPATH = "//div[h6[text()='{commandName}']]//button[contains(@class,'MuiButtonBase-root MuiButton-root MuiButton-outlined')]";
	private static final String TILE_XPATH = "//div[contains(@class,'MuiCardHeader-content')]/span[contains(text(),'{tileName}')]";

	public AddModelToCatalogPage(Page page, String timestamp) {
		this.page = page;
		this.timestamp = timestamp;
	}

	public void addModelButton() {
		page.getByLabel("Navigate to import Model").click();
	}

	public void selectModel(String modelName) {
		page.click(SELECT_OPENAI_XPATH.replace("{ModelName}", modelName));
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

	public String enterFilePath(String fileName) {
		String pathSeparator = FileSystems.getDefault().getSeparator();
		Locator fileInput = page.locator(ADD_FILE_XPATH);
		String relativePath = "src"+pathSeparator+"test"+pathSeparator+"resources"+pathSeparator+"data"+pathSeparator;
		if(fileName.contains("/")) {
			fileName.replace("/", pathSeparator);
		}
		fileInput.setInputFiles(Paths.get(relativePath + fileName));
		if(fileName.contains("/")) {
			String [] ActualFileName = fileName.split("/");
			int fileNameIndex = ActualFileName.length-1;
			Locator uploadedFileName = page.locator(ADD_FILE_NAME_XPATH.replace("{fileName}", ActualFileName[fileNameIndex]));
			String uploadedFileNameValue = uploadedFileName.textContent();
			return uploadedFileNameValue;
		}else {
			Locator uploadedFileName = page.locator(ADD_FILE_NAME_XPATH.replace("{fileName}", fileName));
			String uploadedFileNameValue = uploadedFileName.textContent();
			return uploadedFileNameValue;
		}
		
	}

	public String modelCreationToastMessage() {
		Locator toastMessage = page.getByRole(AriaRole.ALERT)
				.filter(new Locator.FilterOptions().setHasText(MODEL_TOAST_MESSAGE));
		return toastMessage.textContent().trim();
	}

	public void waitForModelCreationToastMessageDisappear() {
		page.getByRole(AriaRole.ALERT).filter(new Locator.FilterOptions().setHasText(MODEL_TOAST_MESSAGE))
				.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
		;
	}

	public String verifyModelTitle(String modelTitle) {
		Locator actualmodelTitle = page.getByRole(AriaRole.HEADING,
				new Page.GetByRoleOptions().setName(modelTitle + timestamp));
		return actualmodelTitle.textContent().trim();
	}

	public void clickOnSMSSTab() {
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

	public String verifyKeepConversationHistoryValueInSMSS(String fieldName) {
		String varNameInSMSSProperties = page
				.textContent(SMSS_PROPERTIES_FIELDS_COMMON_XPATH.replace("{fieldName}", fieldName));
		return varNameInSMSSProperties;
	}
//Edit model

	public void searchModelCatalog(String modelName) {
		page.locator(MODEL_CATALOG_SEARCH_TEXTBOX_XPATH).click();
		page.locator(MODEL_CATALOG_SEARCH_TEXTBOX_XPATH).fill(modelName + timestamp);
	}

	public void selectModelFromSearchOptions(String modelName) {
		page.locator((SEARCHED_MODEL_XPATH.replace("{modelName}", modelName + timestamp))).isVisible();
		page.locator(SEARCHED_MODEL_XPATH.replace("{modelName}", modelName + timestamp)).click();
	}

	public boolean verifyModelIsDisplayedOnCatalogPage(String modelName) {
		String modelNameWithTimestamp = SEARCHED_MODEL_XPATH.replace("{modelName}", modelName + timestamp);
		page.waitForSelector(modelNameWithTimestamp, new Page.WaitForSelectorOptions().setTimeout(10000));
		boolean isModelVisible = page.isVisible(modelNameWithTimestamp);
		return isModelVisible;
	}

	public void clickOnEditButton() {
		page.click(EDIT_BUTTON_XPATH);
	}

	public void enterDetails(String detailsText) {
		page.fill(DETAILS_TEXTBOX_XPATH, detailsText);
	}

	public void enterDescription(String descriptionText) {
		page.getByLabel(DESCRIPTION_TEXTBOX_LABEL).fill(descriptionText);
	}

	public void enterTagName(String tagName) {
		page.getByLabel(TAG_TEXTBOX).click();
		page.getByLabel(TAG_TEXTBOX).fill(tagName);
		page.getByLabel(TAG_TEXTBOX).press("Enter");
	}

	public void enterDomainName(String domainName) {
		page.getByLabel(DOMAIN_TEXTBOX_LABEL).fill(domainName);
		page.getByLabel(DOMAIN_TEXTBOX_LABEL).press("Enter");
	}

	public void selectDataClassificationOption(String option) {
		page.click(DATA_CLASSIFICATION_TEXTBOX_XPATH);
		page.fill(DATA_CLASSIFICATION_TEXTBOX_XPATH, option);
		page.locator(DATA_CLASSIFICATION_TEXTBOX_XPATH).press("ArrowDown");
		page.locator(DATA_CLASSIFICATION_TEXTBOX_XPATH).press("Enter");
	}

	public void selectDataRestrictionsOption(String option) {
		page.click(DATA_RESTRICTIONS_TEXTBOX_XPATH);
		page.fill(DATA_RESTRICTIONS_TEXTBOX_XPATH, option);
		page.locator(DATA_RESTRICTIONS_TEXTBOX_XPATH).press("ArrowDown");
		page.locator(DATA_RESTRICTIONS_TEXTBOX_XPATH).press("Enter");
	}

	public void clickOnSubmit() {
		page.click(SUBMIT_BUTTON_XPATH);
	}

	public String verifyEditSuccessfullToastMessage() {
		Locator toastMessage = page.getByRole(AriaRole.ALERT)
				.filter(new Locator.FilterOptions().setHasText(EDIT_SUCCESS_TOAST_MESSAGE));
		return toastMessage.textContent().trim();
	}

	public void waitForEditSuccessToastMessageToDisappear() {
		page.locator(EDIT_SUCCESS_TOAST_MESSAGE)
				.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
	}

	public String verifyDescriptionText() {

		String descriptionText = page.textContent(DESCRIPTION_TEXT_XPATH).trim();
		return descriptionText;
	}

	public List<String> verifyTagNames() {
		List<String> tags = new ArrayList<String>();
		List<String> tagsText = page.locator(MODEL_TAGS_XPATH).allInnerTexts();
		CommonUtils.extractOverviewSectionValues(tags, tagsText);
		return tags;
	}

	public String verifyDetailsTextUnderOverview() {
		Locator shadowElement = page.locator(DETAILS_UNDER_OVERVIEW_XPATH).locator("p");
		shadowElement.waitFor();
		String text = shadowElement.innerText().trim();
		return text;
	}

	public List<String> verifyTagNamesUnderOverview() {
		List<String> tags = new ArrayList<String>();
		List<String> tagsText = page.locator(TAGS_UNDER_OVERVIEW_XPATH).allInnerTexts();
		CommonUtils.extractOverviewSectionValues(tags, tagsText);
		return tags;
	}

	public List<String> verifyDomainValuesUnderOverview() {
		List<String> domains = new ArrayList<String>();
		List<String> domainText = page.locator(DOMAIN_TEXTS_UNDER_OVERVIEW_XPATH).allInnerTexts();
		CommonUtils.extractOverviewSectionValues(domains, domainText);
		return domains;
	}

	public List<String> verifyDataClassificationOptionsUnderOverview() {
		List<String> dataClassificationOptions = new ArrayList<String>();
		List<String> dataClassificationOptionsText = page.locator(DATA_CLASSIFICATION_OPTIONS_UNDER_OVERVIEW_XPATH)
				.allInnerTexts();
		CommonUtils.extractOverviewSectionValues(dataClassificationOptions, dataClassificationOptionsText);
		return dataClassificationOptions;
	}

	public List<String> verifyDataRestrictionOptionsUnderOverview() {
		List<String> dataRestrictionOptions = new ArrayList<String>();
		List<String> dataRestrictionOptionsText = page.locator(DATA_RESTRICTIONS_OPTIONS_UNDER_OVERVIEW_XPATH)
				.allInnerTexts();
		CommonUtils.extractOverviewSectionValues(dataRestrictionOptions, dataRestrictionOptionsText);
		return dataRestrictionOptions;
	}

	// Methods used for settings

	public void clickOnSettingsTab() {
		page.click(SETTINGS_TAB_XPATH);
	}

	public boolean verifyMakePublicSectionIsVisible(String title) {
		boolean isMakePublicSectionVisible = page.isVisible(MAKE_PUBLIC_SECTION_TITLE_XPATH.replace("{title}", title));
		return isMakePublicSectionVisible;
	}

	public String verifyMakePublicSectionTextMessage() {
		String actualTextMessage = page.textContent(MAKE_PUBLIC_SECTION_TEXT_MESSAGE_XPATH);
		return actualTextMessage;
	}

	public boolean verifyMakePublicToggleButtonIsVisible() {
		boolean isMakePublicToggleButtonVisible = page.isVisible(MAKE_PUBLIC_TOGGLE_BUTTON_XPATH);
		return isMakePublicToggleButtonVisible;
	}

	public boolean verifyMakeDiscoverableSectionIsVisible(String title) {
		boolean isMakeDiscoverableSectionVisible = page
				.isVisible(MAKE_DISCOVERABLE_SECTION_TITLE_XPATH.replace("{title}", title));
		return isMakeDiscoverableSectionVisible;
	}

	public String verifyMakeDiscoverableSectionTextMessage() {
		String actualTextMessage = page.textContent(MAKE_DISCOVERABLE_SECTION_TEXT_MESSAGE_XPATH);
		return actualTextMessage;
	}

	public boolean verifyMakeDiscoverableToggleButtonIsVisible() {
		boolean isMakeDiscoverableToggleButtonVisible = page.isVisible(MAKE_DISCOVERABLE_TOGGLE_BUTTON_XPATH);
		return isMakeDiscoverableToggleButtonVisible;
	}

	public boolean verifyDeleteSectionIsVisible(String title) {
		boolean isDeleteSectionVisible = page.isVisible(DELETE_SECTION_TITLE_XPATH.replace("{title}", title));
		return isDeleteSectionVisible;
	}

	public String verifyDeleteSectionTextMessage() {
		String actualTextMessage = page.textContent(DELETE_SECTION_TEXT_MESSAGE_XPATH);
		return actualTextMessage;
	}

	public boolean verifyDeleteButtonIsVisible() {
		boolean isDeleteButtonVisible = page.isVisible(DELETE_BUTTON_XPATH);
		return isDeleteButtonVisible;
	}

	public boolean verifyPendingRequestsSectionIsVisible() {
		boolean isPendingRequestsSectionVisible = page.isVisible(PENDING_REQUESTS_SECTION_TITLE_XPATH);
		return isPendingRequestsSectionVisible;
	}

	public String verifyPendingRequestsSectionTextMessage() {
		String actualTextMessage = page.textContent(PENDING_REQUESTS_SECTION_TEXT_MESSAGE_XPATH).trim();
		return actualTextMessage;
	}

	public boolean verifyMembersSectionIsVisible() {
		boolean isMembersSectionVisible = page.isVisible(MEMBER_SECTION_TITLE_XPATH);
		return isMembersSectionVisible;
	}

	public boolean verifySearchMembersSearchBoxIsVisible() {
		page.click(MEMBER_SEARCH_ICON_XPATH);
		boolean isSearchMembersTextBoxVisible = page.isVisible(SEARCH_MEMBERS_SEARCHBOX_XPATH);
		return isSearchMembersTextBoxVisible;
	}

	public boolean verifyAddMembersButtonIsVisible() {
		boolean isAddMembersButtonVisible = page.isVisible(ADD_MEMBERS_BUTTON_XPATH);
		return isAddMembersButtonVisible;
	}

	public boolean verifyRowsPerPageDropdownIsVisible() {
		page.locator(ROWS_PER_PAGE_DROPDOWN_XPATH).scrollIntoViewIfNeeded();
		boolean isRowsPerPageDropdownVisible = page.isVisible(ROWS_PER_PAGE_DROPDOWN_XPATH);
		return isRowsPerPageDropdownVisible;
	}

	public List<String> verifyRowsPerPageDropdownOptions() {
		page.click(ROWS_PER_PAGE_DROPDOWN_XPATH);
		List<String> actualOptions = page.locator(ROWS_PER_PAGE_DROPDOWN_OPTIONS_LIST_XPATH).allTextContents();
		return actualOptions;
	}

	public void clickOnEditSMSSButton() {
		page.click(EDIT_SMSS_BUTTON_XPATH);
	}

	public void clickOnUpdateSMSSButton() {
		page.click(UPDATE_SMSS_BUTTON_XPATH);
	}

	public void editSMSSFieldValues(String fieldName, String newValue) {
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

	public void pageReload() {
		page.reload();
	}

	public boolean verifyGroupIsVisible(String groupName) {
		boolean isGroupVisible = page.isVisible(MODEL_GROUP_XPATH.replace("{groupName}", groupName));
		return isGroupVisible;
	}

	public boolean VerifyModelIsVisible(String groupName, String modelName) {
		boolean isModelVisible = page.isVisible(
				MODELS_UNDER_GROUP_XPATH.replace("{groupName}", groupName).replace("{modelName}", modelName));
		return isModelVisible;
	}

	public boolean verifyTileIsVisible(String tileName) {
		boolean isTileVisible = page.isVisible(TILE_XPATH.replace("{tileName}", tileName));
		return isTileVisible;
	}

	public void clickOnSearchBox(String string) {
		page.getByPlaceholder("Search").isVisible();
		page.getByPlaceholder("Search").click();
		page.getByPlaceholder("Search").fill(string);
	}

	public void clickOnAccessControl() {
		page.click(SETTINGS_TAB_XPATH);
	}

	public void clickOnAddMembersButton() {
		page.click(ADD_MEMBERS_BUTTON_XPATH);
	}

	public void addMember(String role) throws InterruptedException {
		String username = ConfigUtils.getValue(role.toLowerCase() + "_username").split("@")[0];
		// search is by user name first name and lastname
		// username = username + " lastname";
		page.fill(ADD_MEMBER_XPATH, username);
//		page.getByTitle("Name: " + username).click();
		page.getByText(username).click();
		page.click(RADIO_BUTTON_XPATH.replace("{role}", role));
		page.click(SAVE_BUTTON_XPATH);
		page.click(MEMBER_ADDED_SUCCESS_TOAST_MESSAGE_CLOSE_ICON_XPATH);
		page.locator(MEMBER_ADDED_SUCCESS_TOAST_MESSAGE_XPATH)
				.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
	}

	public void clickOnDeleteButton() {
		Locator deleteButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Delete"));
		deleteButton.click();
		deleteButton.click();
		page.waitForCondition(
				() -> page.isVisible(DELETE_SUCCESS_TOAST_XPATH) || page.isVisible(DELETE_PERMISSION_ERROR_TOAST_XPATH),
				new Page.WaitForConditionOptions().setTimeout(5000));
		// Added cancel button code because pop-up is not closing because of bug
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Cancel")).click();
	}

	public boolean isDeleteSuccessful() {
		return page.isVisible(DELETE_SUCCESS_TOAST_XPATH);
	}

	public boolean isPermissionErrorDisplayed() {
		return page.isVisible(DELETE_PERMISSION_ERROR_TOAST_XPATH);
	}

	public boolean isAddMemberButtonVisible() {
		return page.isVisible(ADD_MEMBERS_BUTTON_XPATH);
	}

	public void deleteAddedMember(String role) {
		page.click(ADDED_MEMBER_DELETE_ICON_XPATH.replace("{role}", role));
		page.click(CONFIRM_BUTTON_XPATH);
	}

	public void addedModelCard(String modelName) {
		page.locator(MODEL_CARD_XPATH.replace("{modelName}", modelName)).isVisible();
		page.locator(MODEL_CARD_XPATH.replace("{modelName}", modelName)).click();
	}

	public String verifyDeleteToastMessage() {
		page.locator(DELETE_TOAST_MESSAGE_XPATH)
				.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		String toastMessage = page.locator(DELETE_TOAST_MESSAGE_XPATH).textContent();
		return toastMessage;
	}

	public void clickOnUsageTab() {
		page.click(USAGE_TAB_XPATH);
	}

	public String copyModelID() {
		page.locator(MODEL_ID_COPY_OPTION_XPATH).isVisible();
		page.locator(MODEL_ID_COPY_OPTION_XPATH).click();
		page.click(MODEL_ID_COPY_OPTION_XPATH);
		return page.evaluate("navigator.clipboard.readText()").toString().trim();
	}

	public String copyCommand(String commandName) {
		page.locator(USAGE_COMMAND_COPY_OPTION_XPATH.replace("{commandName}", commandName)).scrollIntoViewIfNeeded();
		page.locator(USAGE_COMMAND_COPY_OPTION_XPATH.replace("{commandName}", commandName))
				.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		page.locator(USAGE_COMMAND_COPY_OPTION_XPATH.replace("{commandName}", commandName)).click();
		return page.evaluate("navigator.clipboard.readText()").toString().trim();
	}
}
