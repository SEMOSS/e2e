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

	private static final String ADD_FILE_XPATH = "//input[@type='file']";
	private static final String ADD_FILE_NAME_XPATH = "//span[@title='{fileName}']";
	private static final String DELETE_TOAST_MESSAGE_XPATH = "//div[text()='Successfully deleted Model']";

	public AddModelToCatalogPage(Page page, String timestamp) {
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
		ModelPageUtils.addedModelCard(page, modelName);
	}

	public String verifyDeleteToastMessage() {
		// TODO use AICorePageUtils.verifyToastMessage
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
