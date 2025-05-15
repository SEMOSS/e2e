package aicore.pages;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.util.List;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

import aicore.utils.ModelPageUtils;

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
	private static final String USAGE_CODE_SECTION_XPATH = "//h6[text()='{sectionName}']/following-sibling::pre";
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
  
	public void selectOpenAi(String aiModelName) {
		ModelPageUtils.selectOpenAi(page, aiModelName);
	}

	public void enterCatalogName(String CatalogName) {
		ModelPageUtils.enterCatalogName(page, CatalogName, timestamp);
	}

	public void enterOpenAIKey(String openAIKey) {
		ModelPageUtils.enterOpenAIKey(page, openAIKey);
	}

	public void enterVariableName(String varName) {
		ModelPageUtils.enterVariableName(page, varName);
	}

	public void createModel() {
		ModelPageUtils.createModel(page);
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
		return ModelPageUtils.modelCreationToastMessage(page);
	}

	public void waitForModelCreationToastMessageDisappear() {
		ModelPageUtils.waitForModelCreationToastMessageDisappear(page);
	}

	public String verifyModelTitle(String modelTitle) {
		return ModelPageUtils.verifyModelTitle(page, modelTitle, timestamp);
	}

	public void clickOnSMSSTab() {
		ModelPageUtils.clickOnSMSSTab(page);
	}

	public String getExpectedCatalogTitle(String modelTitle) {
		return ModelPageUtils.getExpectedCatalogTitle(modelTitle, timestamp);
	}

	public String verifyNameInSMSS() {
		return ModelPageUtils.verifyNameInSMSS(page);
	}

	public String verifyVarNameInSMSS() {
		return ModelPageUtils.verifyVarNameInSMSS(page);
	}

	public String verifyKeepConversationHistoryValueInSMSS(String fieldName) {
		return ModelPageUtils.verifyKeepConversationHistoryValueInSMSS(page, fieldName);
	}
//Edit model

	public void searchModelCatalog(String modelName) {
		ModelPageUtils.searchModelCatalog(page, modelName, timestamp);
	}

	public void selectModelFromSearchOptions(String modelName) {
		ModelPageUtils.selectModelFromSearchOptions(page, modelName, timestamp);
	}

	public boolean verifyModelIsDisplayedOnCatalogPage(String modelName) {
		return ModelPageUtils.verifyModelIsDisplayedOnCatalogPage(page, modelName, timestamp);
	}

	public void clickOnEditButton() {
		ModelPageUtils.clickOnEditButton(page);
	}

	public void enterDetails(String detailsText) {
		ModelPageUtils.enterDetails(page, detailsText);
	}

	public void enterDescription(String descriptionText) {
		ModelPageUtils.enterDescription(page, descriptionText);
	}

	public void enterTagName(String tagName) {
		ModelPageUtils.enterTagName(page, tagName);
	}

	public void enterDomainName(String domainName) {
		ModelPageUtils.enterDomainName(page, domainName);
	}

	public void selectDataClassificationOption(String option) {
		ModelPageUtils.selectDataClassificationOption(page, option);
	}

	public void selectDataRestrictionsOption(String option) {
		ModelPageUtils.selectDataRestrictionsOption(page, option);
	}

	public void clickOnSubmit() {
		ModelPageUtils.clickOnSubmit(page);
	}

	public String verifyEditSuccessfullToastMessage() {
		return ModelPageUtils.verifyEditSuccessfullToastMessage(page);
	}

	public void waitForEditSuccessToastMessageToDisappear() {
		ModelPageUtils.waitForEditSuccessToastMessageToDisappear(page);
	}

	public String verifyDescriptionText() {
		return ModelPageUtils.verifyDescriptionText(page);
	}

	public List<String> verifyTagNames() {
		return ModelPageUtils.verifyTagNames(page);
	}

	public String verifyDetailsTextUnderOverview() {
		return ModelPageUtils.verifyDetailsTextUnderOverview(page);
	}

	public List<String> verifyTagNamesUnderOverview() {
		return ModelPageUtils.verifyTagNamesUnderOverview(page);
	}

	public List<String> verifyDomainValuesUnderOverview() {
		return ModelPageUtils.verifyDomainValuesUnderOverview(page);
	}

	public List<String> verifyDataClassificationOptionsUnderOverview() {
		return ModelPageUtils.verifyDataClassificationOptionsUnderOverview(page);
	}

	public List<String> verifyDataRestrictionOptionsUnderOverview() {
		return ModelPageUtils.verifyDataRestrictionOptionsUnderOverview(page);
	}

	// Methods used for settings

	public void clickOnSettingsTab() {
		ModelPageUtils.clickOnSettingsTab(page);
	}

	public boolean verifyMakePublicSectionIsVisible(String title) {
		return ModelPageUtils.verifyMakePublicSectionIsVisible(page, title);
	}

	public String verifyMakePublicSectionTextMessage() {
		return ModelPageUtils.verifyMakePublicSectionTextMessage(page);
	}

	public boolean verifyMakePublicToggleButtonIsVisible() {
		return ModelPageUtils.verifyMakePublicToggleButtonIsVisible(page);
	}

	public boolean verifyMakeDiscoverableSectionIsVisible(String title) {
		return ModelPageUtils.verifyMakeDiscoverableSectionIsVisible(page, title);
	}

	public String verifyMakeDiscoverableSectionTextMessage() {
		return ModelPageUtils.verifyMakeDiscoverableSectionTextMessage(page);
	}

	public boolean verifyMakeDiscoverableToggleButtonIsVisible() {
		return ModelPageUtils.verifyMakeDiscoverableToggleButtonIsVisible(page);
	}

	public boolean verifyDeleteSectionIsVisible(String title) {
		return ModelPageUtils.verifyDeleteSectionIsVisible(page, title);
	}

	public String verifyDeleteSectionTextMessage() {
		return ModelPageUtils.verifyDeleteSectionTextMessage(page);
	}

	public boolean verifyDeleteButtonIsVisible() {
		return ModelPageUtils.verifyDeleteButtonIsVisible(page);
	}

	public boolean verifyPendingRequestsSectionIsVisible() {
		return ModelPageUtils.verifyPendingRequestsSectionIsVisible(page);
	}

	public String verifyPendingRequestsSectionTextMessage() {
		return ModelPageUtils.verifyPendingRequestsSectionTextMessage(page);
	}

	public boolean verifyMembersSectionIsVisible() {
		return ModelPageUtils.verifyMembersSectionIsVisible(page);
	}

	public boolean verifySearchMembersSearchBoxIsVisible() {
		return ModelPageUtils.verifySearchMembersSearchBoxIsVisible(page);
	}

	public boolean verifyAddMembersButtonIsVisible() {
		return ModelPageUtils.verifyAddMembersButtonIsVisible(page);
	}

	public boolean verifyRowsPerPageDropdownIsVisible() {
		return ModelPageUtils.verifyRowsPerPageDropdownIsVisible(page);
	}

	public List<String> verifyRowsPerPageDropdownOptions() {
		return ModelPageUtils.verifyRowsPerPageDropdownOptions(page);
	}

	public void clickOnEditSMSSButton() {
		ModelPageUtils.clickOnEditSMSSButton(page);
	}

	public void clickOnUpdateSMSSButton() {
		ModelPageUtils.clickOnUpdateSMSSButton(page);
	}

	public void editSMSSFieldValues(String fieldName, String newValue) {
		ModelPageUtils.editSMSSFieldValues(page, fieldName, newValue);
	}

	public void pageReload() {
		ModelPageUtils.pageReload(page);
	}

	public boolean verifyGroupIsVisible(String groupName) {
		return ModelPageUtils.verifyGroupIsVisible(page, groupName);
	}

	public boolean VerifyModelIsVisible(String groupName, String modelName) {
		return ModelPageUtils.VerifyModelIsVisible(page, groupName, modelName);
	}

	public boolean verifyTileIsVisible(String tileName) {
		return ModelPageUtils.verifyTileIsVisible(page, tileName);
	}

	public void clickOnSearchBox(String string) {
		ModelPageUtils.clickOnSearchBox(page, string);
	}

	public void clickOnAccessControl() {
		ModelPageUtils.clickOnAccessControl(page);
	}

	public void clickOnAddMembersButton() {
		ModelPageUtils.clickOnAddMembersButton(page);
	}

	public void addMember(String role, boolean useDocker) throws InterruptedException {
		ModelPageUtils.addMember(page, role, useDocker);
	}

	public void clickOnDeleteButton() {
		ModelPageUtils.clickOnDeleteButton(page);
	}

	public boolean isDeleteSuccessful() {
		return ModelPageUtils.isDeleteSuccessful(page);
	}

	public boolean isPermissionErrorDisplayed() {
		return ModelPageUtils.isPermissionErrorDisplayed(page);
	}

	public boolean isAddMemberButtonVisible() {
		return ModelPageUtils.isAddMemberButtonVisible(page);
	}

	public void deleteAddedMember(String role) {
		ModelPageUtils.deleteAddedMember(page, role);
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
		ModelPageUtils.clickOnUsageTab(page);
	}

	public String copyModelID() {
		return ModelPageUtils.copyModelID(page);
	}

	public String getFullSectionCodeByHeading(String headingText) {
		return ModelPageUtils.getFullSectionCodeByHeading(page, headingText);
	}
}
