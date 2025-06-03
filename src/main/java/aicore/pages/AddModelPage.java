package aicore.pages;

import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.util.List;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

import aicore.utils.page.model.EditModelPageUtils;
import aicore.utils.page.model.ModelPageUtils;
import aicore.utils.page.model.SettingsModelPageUtils;

public class AddModelPage {

	private Page page;
	private String timestamp;

	private static final String ADD_FILE_XPATH = "//input[@type='file']";
	private static final String ADD_FILE_NAME_XPATH = "//span[@title='{fileName}']";
	private static final String DELETE_TOAST_MESSAGE_XPATH = "//div[text()='Successfully deleted Model']";

	public AddModelPage(Page page, String timestamp) {
		this.page = page;
		this.timestamp = timestamp;
	}

	public void clickAddModelButton() {
		ModelPageUtils.clickAddModelButton(page);
	}

	public void selectModel(String modelName) {
		ModelPageUtils.selectModel(page, modelName);
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
		String relativePath = "src" + pathSeparator + "test" + pathSeparator + "resources" + pathSeparator + "data"
				+ pathSeparator;
		if (fileName.contains("/")) {
			fileName.replace("/", pathSeparator);
		}
		fileInput.setInputFiles(Paths.get(relativePath + fileName));
		if (fileName.contains("/")) {
			String[] ActualFileName = fileName.split("/");
			int fileNameIndex = ActualFileName.length - 1;
			Locator uploadedFileName = page
					.locator(ADD_FILE_NAME_XPATH.replace("{fileName}", ActualFileName[fileNameIndex]));
			String uploadedFileNameValue = uploadedFileName.textContent();
			return uploadedFileNameValue;
		} else {
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
	// Edit model
	public void searchModelCatalog(String modelName) {
		EditModelPageUtils.searchModelCatalog(page, modelName, timestamp);
	}

	public void selectModelFromSearchOptions(String modelName) {
		EditModelPageUtils.selectModelFromSearchOptions(page, modelName, timestamp);
	}

	public boolean verifyModelIsDisplayedOnCatalogPage(String modelName) {
		return EditModelPageUtils.verifyModelIsDisplayedOnCatalogPage(page, modelName, timestamp);
	}

	public void clickOnEditButton() {
		EditModelPageUtils.clickOnEditButton(page);
	}

	public void enterDetails(String detailsText) {
		EditModelPageUtils.enterDetails(page, detailsText);
	}

	public void enterDescription(String descriptionText) {
		EditModelPageUtils.enterDescription(page, descriptionText);
	}

	public void enterTagName(String tagName) {
		EditModelPageUtils.enterTagName(page, tagName);
	}

	public void enterDomainName(String domainName) {
		EditModelPageUtils.enterDomainName(page, domainName);
	}

	public void selectDataClassificationOption(String option) {
		EditModelPageUtils.selectDataClassificationOption(page, option);
	}

	public void selectDataRestrictionsOption(String option) {
		EditModelPageUtils.selectDataRestrictionsOption(page, option);
	}

	public void clickOnSubmit() {
		EditModelPageUtils.clickOnSubmit(page);
	}

	public String verifyEditSuccessfullToastMessage() {
		return EditModelPageUtils.verifyEditSuccessfullToastMessage(page);
	}

	public void waitForEditSuccessToastMessageToDisappear() {
		EditModelPageUtils.waitForEditSuccessToastMessageToDisappear(page);
	}

	public String verifyDescriptionText() {
		return EditModelPageUtils.verifyDescriptionText(page);
	}

	public List<String> verifyTagNames() {
		return EditModelPageUtils.verifyTagNames(page);
	}

	public String verifyDetailsTextUnderOverview() {
		return EditModelPageUtils.verifyDetailsTextUnderOverview(page);
	}

	public List<String> verifyTagNamesUnderOverview() {
		return EditModelPageUtils.verifyTagNamesUnderOverview(page);
	}

	public List<String> verifyDomainValuesUnderOverview() {
		return EditModelPageUtils.verifyDomainValuesUnderOverview(page);
	}

	public List<String> verifyDataClassificationOptionsUnderOverview() {
		return EditModelPageUtils.verifyDataClassificationOptionsUnderOverview(page);
	}

	public List<String> verifyDataRestrictionOptionsUnderOverview() {
		return EditModelPageUtils.verifyDataRestrictionOptionsUnderOverview(page);
	}

	// Methods used for settings

	public void clickOnSettingsTab() {
		SettingsModelPageUtils.clickOnSettingsTab(page);
	}

	public boolean verifyMakePublicSectionIsVisible(String title) {
		return SettingsModelPageUtils.verifyMakePublicSectionIsVisible(page, title);
	}

	public String verifyMakePublicSectionTextMessage() {
		return SettingsModelPageUtils.verifyMakePublicSectionTextMessage(page);
	}

	public boolean verifyMakePublicToggleButtonIsVisible() {
		return SettingsModelPageUtils.verifyMakePublicToggleButtonIsVisible(page);
	}

	public boolean verifyMakeDiscoverableSectionIsVisible(String title) {
		return SettingsModelPageUtils.verifyMakeDiscoverableSectionIsVisible(page, title);
	}

	public String verifyMakeDiscoverableSectionTextMessage() {
		return SettingsModelPageUtils.verifyMakeDiscoverableSectionTextMessage(page);
	}

	public boolean verifyMakeDiscoverableToggleButtonIsVisible() {
		return SettingsModelPageUtils.verifyMakeDiscoverableToggleButtonIsVisible(page);
	}

	public boolean verifyDeleteSectionIsVisible(String title) {
		return SettingsModelPageUtils.verifyDeleteSectionIsVisible(page, title);
	}

	public String verifyDeleteSectionTextMessage() {
		return SettingsModelPageUtils.verifyDeleteSectionTextMessage(page);
	}

	public boolean verifyDeleteButtonIsVisible() {
		return SettingsModelPageUtils.verifyDeleteButtonIsVisible(page);
	}

	public boolean verifyPendingRequestsSectionIsVisible() {
		return SettingsModelPageUtils.verifyPendingRequestsSectionIsVisible(page);
	}

	public String verifyPendingRequestsSectionTextMessage() {
		return SettingsModelPageUtils.verifyPendingRequestsSectionTextMessage(page);
	}

	public boolean verifyMembersSectionIsVisible() {
		return SettingsModelPageUtils.verifyMembersSectionIsVisible(page);
	}

	public boolean verifySearchMembersSearchBoxIsVisible() {
		return SettingsModelPageUtils.verifySearchMembersSearchBoxIsVisible(page);
	}

	public boolean verifyAddMembersButtonIsVisible() {
		return SettingsModelPageUtils.verifyAddMembersButtonIsVisible(page);
	}

	public boolean verifyRowsPerPageDropdownIsVisible() {
		return SettingsModelPageUtils.verifyRowsPerPageDropdownIsVisible(page);
	}

	public List<String> verifyRowsPerPageDropdownOptions() {
		return SettingsModelPageUtils.verifyRowsPerPageDropdownOptions(page);
	}

	public void clickOnEditSMSSButton() {
		SettingsModelPageUtils.clickOnEditSMSSButton(page);
	}

	public void clickOnUpdateSMSSButton() {
		SettingsModelPageUtils.clickOnUpdateSMSSButton(page);
	}

	public void editSMSSFieldValues(String fieldName, String newValue) {
		SettingsModelPageUtils.editSMSSFieldValues(page, fieldName, newValue);
	}

	public void pageReload() {
		SettingsModelPageUtils.pageReload(page);
	}

	public boolean verifyGroupIsVisible(String groupName) {
		return SettingsModelPageUtils.verifyGroupIsVisible(page, groupName);
	}

	public boolean VerifyModelIsVisible(String groupName, String modelName) {
		return SettingsModelPageUtils.VerifyModelIsVisible(page, groupName, modelName);
	}

	public boolean verifyTileIsVisible(String tileName) {
		return SettingsModelPageUtils.verifyTileIsVisible(page, tileName);
	}

	public void clickOnSearchBox(String string) {
		SettingsModelPageUtils.clickOnSearchBox(page, string);
	}

	public void clickOnAccessControl() {
		SettingsModelPageUtils.clickOnAccessControl(page);
	}

	public void clickOnAddMembersButton() {
		SettingsModelPageUtils.clickOnAddMembersButton(page);
	}

	public void addMember(String role, boolean useDocker) throws InterruptedException {
		SettingsModelPageUtils.addMember(page, role, useDocker);
	}

	public void clickOnDeleteButton() {
		SettingsModelPageUtils.clickOnDeleteButton(page);
	}

	public boolean isDeleteSuccessful() {
		return SettingsModelPageUtils.isDeleteSuccessful(page);
	}

	public boolean isPermissionErrorDisplayed() {
		return SettingsModelPageUtils.isPermissionErrorDisplayed(page);
	}

	public boolean isAddMemberButtonVisible() {
		return SettingsModelPageUtils.isAddMemberButtonVisible(page);
	}

	public void deleteAddedMember(String role) {
		SettingsModelPageUtils.deleteAddedMember(page, role);
	}

	public void addedModelCard(String modelName) {
		EditModelPageUtils.addedModelCard(page, modelName);
	}

	public String verifyDeleteToastMessage() {
		// TODO use AICorePageUtils.verifyToastMessage
		page.locator(DELETE_TOAST_MESSAGE_XPATH)
				.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		String toastMessage = page.locator(DELETE_TOAST_MESSAGE_XPATH).textContent();
		return toastMessage;
	}

	public void clickOnUsageTab() {
		SettingsModelPageUtils.clickOnUsageTab(page);
	}

	public String copyModelID() {
		return SettingsModelPageUtils.copyModelID(page);
	}

	public String getFullSectionCodeByHeading(String headingText) {
		return SettingsModelPageUtils.getFullSectionCodeByHeading(page, headingText);
	}
}
