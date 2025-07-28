package aicore.pages;

import com.microsoft.playwright.Page;

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
		StoragePageUtils.selectStorage(page, storageName);
	}

	public void enterCatalogName(String catalogName) {
		StoragePageUtils.enterCatalogName(page, catalogName);
	}

	public void enterRegionName(String regionName) {
		StoragePageUtils.enterRegionName(page, regionName);
	}

	public void enterBucket(String bucket) {
		StoragePageUtils.enterBucket(page, bucket);
	}

	public void enterAccessKey(String accessKey) {
		StoragePageUtils.enterAccessKey(page, accessKey);
	}

	public void enterSecretKey(String secretKey) {
		StoragePageUtils.enterSecretKey(page, secretKey);
	}

	public void searchStorage(String storageName) {
		StoragePageUtils.searchStorage(page, storageName);
	}

	public void clickOnCreateStorageButton() {
		StoragePageUtils.clickOnCreateStorageButton(page);
	}

	public String verifyStorageCreatedToastMessage() {
		return StoragePageUtils.verifyStorageCreatedToastMessage(page);
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

	public boolean verifyMissingInputField(String fieldName) {
		return StoragePageUtils.verifyMissingInputField(page, fieldName);
	}

	public boolean verifyFieldIsVisible(String fieldname) {
		return StoragePageUtils.verifyFieldIsVisible(page, fieldname);
	}

	public void enterValuesInField(String fieldname, String fieldValue) {
		StoragePageUtils.enterValuesInField(page, fieldname, fieldValue, timestamp);
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

}
