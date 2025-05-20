package aicore.pages;

import com.microsoft.playwright.Page;

import aicore.utils.StoragePageUtils;

public class OpenStoragePage {

	private Page page;
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

	public void clickOnUsageTab() {
		StoragePageUtils.clickOnUsageTab(page);
	}

	public void verifyExampleOfStorage(String example) {
		StoragePageUtils.verifyExampleOfStorage(page, example);
	}

}
