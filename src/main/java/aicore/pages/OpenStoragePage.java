package aicore.pages;

import com.microsoft.playwright.Page;

import aicore.pages.storage.AddStorageFormUtils;
import aicore.utils.StoragePageUtils;

public class OpenStoragePage extends AbstractAddCatalogPageBase {

	// private Page page;
	private String timestamp;

	public OpenStoragePage(Page page, String timestamp) {
		this.page = page;
		this.timestamp = timestamp;
	}

	public void clickOnAddStorageButton() {
		StoragePageUtils.clickOnAddStorageButton(page);
	}

	public void selectStorage(String storageName) {
		AddStorageFormUtils.selectStorage(page, storageName);
	}

	public boolean fieldUnderSection(String storageType, String section, String field) {
		return AddStorageFormUtils.fieldUnderSection(page, storageType, section, field);
	}

	public boolean isFieldMandatory(String storageType, String field) {
		return AddStorageFormUtils.isFieldMandatory(page, storageType, field);
	}

	public void fillCatalogCreationForm(String storageType, String fieldName, String fieldValue) {
		AddStorageFormUtils.fillCatalogCreationForm(page, storageType, fieldName, fieldValue, timestamp);
	}

	public void enterCatalogName(String catalogName) {
		AddStorageFormUtils.enterCatalogName(page, catalogName);
	}

	public void enterRegionName(String regionName) {
		AddStorageFormUtils.enterRegionName(page, regionName);
	}

	public void enterBucket(String bucket) {
		AddStorageFormUtils.enterBucket(page, bucket);
	}

	public void enterAccessKey(String accessKey) {
		AddStorageFormUtils.enterAccessKey(page, accessKey);
	}

	public void enterSecretKey(String secretKey) {
		AddStorageFormUtils.enterSecretKey(page, secretKey);
	}

	public void searchStorage(String storageName) {
		StoragePageUtils.searchStorage(page, storageName);
	}

	public boolean validateConnectButtonEnabled() {
		return StoragePageUtils.validateConnectButtonEnabled(page);
	}

	public void clickOnConnectButton() {
		StoragePageUtils.clickOnConnectButton(page);
	}

	public String verifyStorageCreatedToastMessage(String toastMessage) {
		return StoragePageUtils.verifyStorageCreatedToastMessage(page, toastMessage);
	}

	public String verifyStorageTitle(String storageTitle) {
		return StoragePageUtils.verifyStorageTitle(page, storageTitle);
	}

	public void clickOnSMSSTab() {
		StoragePageUtils.clickOnSMSSTab(page);
	}

	public String verifyNameFiledInSMSS() {
		return StoragePageUtils.verifyNameFiledInSMSS(page);
	}

	public String verifyS3RegionFiledInSMSS() {
		return StoragePageUtils.verifyS3RegionFiledInSMSS(page);
	}

	public String verifyS3BucketFiledInSMSS() {
		return StoragePageUtils.verifyS3BucketFiledInSMSS(page);
	}

	public String verifyS3AccessKeyFiledInSMSS() {
		return StoragePageUtils.verifyS3AccessKeyFiledInSMSS(page);
	}

	public void verifyAsteriskMarkOnFields(String fieldLabels) {
		StoragePageUtils.verifyAsteriskMarkOnFields(page, fieldLabels);
	}

	public boolean verifyConnectButtonDisabled() {
		return StoragePageUtils.verifyConnectButtonDisabled(page);
	}

	public boolean verifyFieldIsVisible(String fieldname) {
		return AddStorageFormUtils.verifyFieldIsVisible(page, fieldname);
	}

	public void enterValuesInField(String fieldname, String fieldValue) {
		AddStorageFormUtils.enterValuesInField(page, fieldname, fieldValue, timestamp);
	}

	public void viewStorageIDalongwithCopyIcon() {
		StoragePageUtils.viewStorageIDalongwithCopyIcon(page);
	}

	public void clickCopyIcon() {
		StoragePageUtils.clickCopyIcon(page);
	}

	public void verifyToastMessage(String toastMessage) {
		StoragePageUtils.verifyToastMessage(page, toastMessage);
	}

	public void verifyStorageDescription(String storageDescription) {
		StoragePageUtils.verifyToastMessage(page, storageDescription);
	}

	public void verifyEmbeddedTags(String tag, String toastMessage) {
		StoragePageUtils.verifyEmbeddedTags(page, tag, toastMessage);
	}

	public void verifyPublishedByInfo() {
		StoragePageUtils.verifyPublishedByInfo(page);
	}

	public boolean verifyChangeAccessButton() {
		return StoragePageUtils.verifyChangeAccessButton(page);
	}

	public String verifyExpectedTime() {
		return StoragePageUtils.verifyExpectedTime(page);
	}

	public boolean verifyActualTime(String time) {
		return StoragePageUtils.verifyActualTime(page, time);
	}

	public void clickOnCancelButton() {
		StoragePageUtils.clickOnCancelButton(page);
	}

	public void clickOnSettingsTab() {
		StoragePageUtils.clickOnSettingsTab(page);
	}

	public void enterLocalPathPrefix(String path) {
		StoragePageUtils.enterLocalPathPrefix(page, path);
	}

	public void clickOnDeleteButton() {
		StoragePageUtils.clickOnDeleteButton(page);
	}

	public void clickOnDeleteConfirmationButton() {
		StoragePageUtils.clickOnDeleteConfirmationButton(page);
	}

	public String verifyDeleteToastMessage() {
		return StoragePageUtils.verifyDeleteToastMessage(page);
	}

	public void clickOnCreatedStorage(String storageName) {
		StoragePageUtils.clickOnCreatedStorage(page, storageName);
	}

	public void clickOnDiscoverableStoragesButton() {
		StoragePageUtils.clickOnDiscoverableStoragesButton(page);
	}

	public void clickOnButton(String buttonName) {
		StoragePageUtils.clickOnButton(page, buttonName);
	}

	public void createStorage(String storageType, String catalogName, String region, String bucket) {
		AddStorageFormUtils.createStorage(page, storageType, catalogName, region, bucket);
	}
}
