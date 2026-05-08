package aicore.pages;

import java.util.List;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import aicore.pages.model.AddModelFormUtils;
import aicore.pages.model.EditModelPageUtils;
import aicore.pages.model.ModelChatPageUtils;
import aicore.pages.model.ModelSMSSPageUtils;
import aicore.pages.model.SettingsModelPageUtils;
import aicore.pages.model.UploadModelUtils;
import aicore.utils.page.model.ModelPageUtils;

public class AddModelPage {

	private Page page;
	private String timestamp;

	private static final String DELETE_TOAST_MESSAGE_XPATH = "//li[@data-type='success']";

	public AddModelPage(Page page, String timestamp) {
		this.page = page;
		this.timestamp = timestamp;
	}

	public void clickAddModelButton() {
		ModelPageUtils.clickAddModelButton(page);
	}

	public void selectModelType(String modelType) {
		AddModelFormUtils.selectModelType(page, modelType);
	}

	public void selectOpenAi(String aiModelName) {
		AddModelFormUtils.selectOpenAi(page, aiModelName);
	}

	public void clickOnGroupTab(String tabName) {
		AddModelFormUtils.clickOnGroupTab(page, tabName);
	}

	public boolean fieldUnderSection(String section, String field) {
		return AddModelFormUtils.fieldUnderSection(page, section, field);
	}

	public boolean isFieldMandatory(String field) {
		return AddModelFormUtils.isFieldMandatory(page, field);
	}

	public void fillModelCreationForm(String fieldName, String fieldValue) {
		AddModelFormUtils.fillCatalogCreationForm(page, fieldName, fieldValue, timestamp);
	}

	public boolean validateConnectButtonEnabled() {
		return AddModelFormUtils.validateConnectButtonEnabled(page);
	}

	public void clickOnCreateModelButton(String buttonName) {
		AddModelFormUtils.clickOnCreateModelButton(page, buttonName);
	}

	public void enterCatalogName(String catalogName) {
		AddModelFormUtils.enterCatalogName(page, catalogName + timestamp);
	}

	public void enterOpenAIKey(String openAIKey) {
		AddModelFormUtils.enterOpenAIKey(page, openAIKey);
	}

	public void clickOnCreateModelButton() {
		AddModelFormUtils.clickOnCreateModelButton(page);
	}

	public String modelCreationToastMessage(String toastMessage) {
		return ModelPageUtils.modelCreationToastMessage(page, toastMessage);
	}

	public void closeModelCreationToastMessage() {
		ModelPageUtils.closeModelCreationToastMessage(page);
	}

	public String verifyModelTitle(String modelTitle) {
		return ModelPageUtils.verifyModelTitle(page, modelTitle + timestamp);
	}

	public String getExpectedCatalogTitle(String modelTitle) {
		return ModelPageUtils.getExpectedCatalogTitle(modelTitle + timestamp);
	}

	public void clickOnChatTab() {
		ModelChatPageUtils.clickOnChatTab(page);
	}

	public void verifyChatSectionDisplayed(String title) {
		ModelChatPageUtils.verifyChatSectionDisplayed(page, title);
	}

	public void verifyModelIDAndNameDisplayed() {
		ModelChatPageUtils.verifyModelIDAndNameDisplayed(page);
	}

	public void verifyTemperatureValue(String expectedValue) {
		ModelChatPageUtils.verifyTemperatureValue(page, expectedValue);
	}

	public void verifyMaxTokensValue(String expectedValue) {
		ModelChatPageUtils.verifyMaxTokensValue(page, expectedValue);
	}

	public void verifyInputTextboxPlaceholder(String expectedValue) {
		ModelChatPageUtils.verifyInputTextboxPlaceholder(page, expectedValue);
	}

	public void verifyAndActivateSendButton(String inputText) {
		ModelChatPageUtils.verifyAndActivateSendButton(page, inputText);
	}

	public void clickOnSendButton() {
		ModelChatPageUtils.clickOnSendButton(page);
	}

	public void clickOnClearAllButton() {
		ModelChatPageUtils.clickOnClearAllButton(page);
	}

	public void verifyChatWindowCleared() {
		ModelChatPageUtils.verifyChatWindowCleared(page);
	}

	public void verifyLoaderDisplayed() {
		ModelChatPageUtils.verifyLoaderDisplayed(page);
	}

	public void verifyResponseGeneratedInChatWindow() {
		ModelChatPageUtils.verifyResponseGeneratedInChatWindow(page);
	}

	public void clickOnSMSSTab() {
		ModelPageUtils.clickOnSMSSTab(page);
	}

	public String verifyNameInSMSS() {
		return ModelSMSSPageUtils.verifyNameInSMSS(page);
	}

	public String verifyVarNameInSMSS() {
		return ModelSMSSPageUtils.verifyVarNameInSMSS(page);
	}

	public String verifyKeepConversationHistoryValueInSMSS(String fieldName) {
		return ModelSMSSPageUtils.verifyKeepConversationHistoryValueInSMSS(page, fieldName);
	}

	public void createModel(String modelType, String modelName, String catalogName, String openAIKey) {
		ModelPageUtils.createModel(page, modelType, modelName, catalogName, openAIKey);
	}

	public void deleteCreatedModel() {
		ModelPageUtils.deleteCreatedModels(page);
	}

	// Edit model
	public void searchModelCatalog(String modelName) {
		EditModelPageUtils.searchModelCatalog(page, modelName);
	}

	public void selectModelFromSearchOptions(String modelName) {
		EditModelPageUtils.selectModelFromSearchOptions(page, modelName);
	}

	public boolean verifyModelIsDisplayedOnCatalogPage(String modelName) {
		return EditModelPageUtils.verifyModelIsDisplayedOnCatalogPage(page, modelName);
	}

	public void clickOnEditButton() {
		EditModelPageUtils.clickOnEditButton(page);
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

	public void clickOnOptionsGroupTab(String groupName) {
		SettingsModelPageUtils.clickOnOptionsGroupTab(page, groupName);
	}

	public boolean verifyModelOptionIsVisible(String modelOption) {
		return SettingsModelPageUtils.verifyModelOptionIsVisible(page, modelOption);
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
		Locator toasterMessage = page.locator(DELETE_TOAST_MESSAGE_XPATH).first();
		String toastMessage = toasterMessage.textContent().trim();
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

	public void clickOnDiscoverableModelsButton() {
		SettingsModelPageUtils.clickOnDiscoverableModelsButton(page);
	}

	public void clickOnMakeCatalogPublicButton(String catalogName) {
		SettingsModelPageUtils.clickOnMakeCatalogPublicButton(page, catalogName);
	}

	public void userClickOnCreatedModel() {
		ModelPageUtils.userClickOnCreatedModel(page);

	}

	// Create all Model Types
	public void enterInitScript(String initScript) {
		AddModelFormUtils.enterInitScript(page, initScript);
	}

	public void enterGCPRegion(String gcpRegion) {
		AddModelFormUtils.enterGCPRegion(page, gcpRegion);
	}

	public void selectTypeForModel(String type) {
		AddModelFormUtils.selectTypeForModel(page, type);
	}

	public void enterEndpoint(String endpoint) {
		AddModelFormUtils.enterEndpoint(page, endpoint);
	}

	public String getAllFieldsInSMSSProperties(String fieldName) {
		return ModelSMSSPageUtils.getAllFieldsInSMSSProperties(page, fieldName);
	}

	public void enterDeploymentName(String deploymentName) {
		AddModelFormUtils.enterDeploymentName(page, deploymentName);
	}

	public void enterVersion(String version) {
		AddModelFormUtils.enterTheVersion(page, version);
	}

	public boolean isSubmitButtonEnabled() {
		return ModelPageUtils.isSubmitButtonEnabled(page);
	}

	public void selectChatOption(String option) {
		AddModelFormUtils.selectChatOption(page, option);
	}

	public void selectKeepConversationHistoryOption(String option) {
		AddModelFormUtils.selectKeepConversationHistoryOption(page, option);
	}

	public void selectRecordQuestionsAndResponsesOption(String option) {
		AddModelFormUtils.selectRecordQuestionsAndResponsesOption(page, option);
	}

	public void enterMaxTokens(String maxTokens) {
		AddModelFormUtils.enterMaxTokens(page, maxTokens);
	}

	public void enterMaxInputTokens(String maxInputTokens) {
		AddModelFormUtils.enterMaxInputTokens(page, maxInputTokens);
	}

	public void selectTypeForModel() {
		AddModelFormUtils.selectTypeForModel(page);
	}

	public void enterModelName(String modelName) {
		AddModelFormUtils.enterModelName(page, modelName);
	}

	public void selectModelOption(String model) {
		AddModelFormUtils.selectModelOption(page, model);
	}

	public void enterAWSRegion(String awsRegion) {
		AddModelFormUtils.enterAWSRegion(page, awsRegion);
	}

	public void enterAWSAccessKey(String awsAccessKey) {
		AddModelFormUtils.enterAWSAccessKey(page, awsAccessKey);
	}

	public void enterAWSSecretKey(String awsSecretKey) {
		AddModelFormUtils.enterAWSSecretKey(page, awsSecretKey);
	}

	public void clickOnCreateButton(String buttonName) {
		AddModelFormUtils.clickOnCreateButton(page, buttonName);
	}

	public void selectAddModelOption(String option) {
		AddModelFormUtils.selectAddModelOption(page, option);
	}

	public void clickOnUploadButton(String buttonName) {
		UploadModelUtils.clickOnUploadButton(page, buttonName);
	}

	public void mouseHoverOnEngineAccessStatusIcon() {
		EditModelPageUtils.mouseHoverOnEngineAccessStatusIcon(page);
	}

	public String getEngineAccessStatusTooltipText(String status) {
		return EditModelPageUtils.getEngineAccessStatusTooltipText(page, status);
	}

	public boolean validateIDisDisplayedOnCatalogCard() {
		return EditModelPageUtils.validateIDisDisplayedOnCatalogCard(page);
	}

	public String getCatalogID() {
		return EditModelPageUtils.getCatalogID(page);
	}

	public List<String> verifyTagNamesDisplayedOnCard(String catalog) {
		return EditModelPageUtils.verifyTagNamesDisplayedOnCard(page, catalog);
	}

	public boolean isCreatedDateVisibleOnCard() {
		return EditModelPageUtils.isCreatedDateVisibleOnCard(page);
	}

	public boolean isIconVisibleOnCatalogCard(String iconName) {
		return EditModelPageUtils.isIconVisibleOnCatalogCard(page, iconName);
	}

	public void clickOnCatalogCardOption(String option) {
		EditModelPageUtils.clickOnCatalogCardOption(page, option);
	}

	public String getDeleteConfirmationMessage() {
		return EditModelPageUtils.getDeleteConfirmationMessage(page);
	}

	public String getDeleteConfirmationEngineName() {
		return EditModelPageUtils.getDeleteConfirmationEngineName(page);
	}

	public boolean isEngineIdVisibleOnDeleteConfirmation() {
		return EditModelPageUtils.isEngineIdVisibleOnDeleteConfirmation(page);
	}

	public boolean isButtonVisibleOnDeleteConfirmation(String buttonName) {
		return EditModelPageUtils.isButtonVisibleOnDeleteConfirmation(page, buttonName);
	}

	public String getpendingRequestCountText() {
		return SettingsModelPageUtils.getPendingRequestCountText(page);
	}

	public void clickOnPendingRequestsExpandButton() {
		SettingsModelPageUtils.clickOnPendingRequestsExpandButton(page);
	}

	public void performActionOnPendingRequest(String action) {
		SettingsModelPageUtils.performActionOnPendingRequest(page, action);
	}

	public boolean isUserDisplayedInListAfterRequestAction(String role, String permissionGranted, boolean useDocker) {
		return SettingsModelPageUtils.isUserDisplayedInListAfterRequestAction(page, role, permissionGranted, useDocker);
	}

	public void changeRequestedAccessRole(String newRole) {
		SettingsModelPageUtils.changeRequestedAccessRole(page, newRole);
	}

	public void clickOnGenerateMCPButtonFromMCPUsageTab() {
		EditModelPageUtils.clickOnGenerateMCPButtonFromMCPUsageTab(page);
	}

	public boolean verifyToolsInGeneratedMCP(String toolName) {
		return EditModelPageUtils.verifyToolsInGeneratedMCP(page, toolName);
	}

	public boolean verifyInputParameters(String toolName, List<String> expectedParameters) {
		return EditModelPageUtils.verifyInputParameters(page, toolName, expectedParameters);
	}
}