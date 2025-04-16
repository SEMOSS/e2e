package aicore.pages;

import com.microsoft.playwright.Page;

public class OpenStoragePage {

	private Page page;
	private String timestamp;

	private static final String ADD_STORAGE_BUTTON_XPATH = "//div[@class='MuiStack-root css-1lnp4vl']";
	private static final String STORAGE_XPATH = "//div[@class='css-axw7ok']//p[text()='{Storage}']";
	private static final String CATALOG_NAME_TEXTBOX_ID = "#NAME";
	private static final String REGION_TEXTBOX_ID = "#S3_REGION";
	private static final String BUCKET_TEXTBOX_ID = "#S3_BUCKET";
	private static final String ACCESS_KEY_TEXTBOX_ID = "#S3_ACCESS_KEY";
	private static final String SECRET_KEY_TEXTBOX_ID = "#S3_SECRET_KEY";
	private static final String CREATE_STORAGE_BUTTON = "//span[text()='Create storage']";
	private static final String STORAGE_CREATE_SUCCESS_TOAST_MESSAGE_XPATH = "//div[@class='MuiAlert-message css-1xsto0d']";
	private static final String STORAGE_TITLE_XPATH = "//h4[text()='{title}']";
	private static final String SMSS_TAB_XPATH = "//button[text()='SMSS']";
	private static final String NAME_SMSS_PROPERTIES_XPATH = "//div[@class='view-line']//span[@class='mtk1'][starts-with(text(), 'NAME')]";
	private static final String S3_REGION_SMSS_PROPERTIES_XPATH = "//div[@class='view-line']//span[@class='mtk1'][starts-with(text(), 'S3_REGION')]";
	private static final String S3_BUCKET_SMSS_PROPERTIES_XPATH = "//div[@class='view-line']//span[@class='mtk1'][starts-with(text(), 'S3_BUCKET')]";
	private static final String S3_ACCESS_KEY_SMSS_PROPERTIES_XPATH = "//div[@class='view-line']//span[@class='mtk1'][starts-with(text(), 'S3_ACCESS_KEY')]";

	public OpenStoragePage(Page page, String timestamp) {
		this.page = page;
		this.timestamp = timestamp;
	}

	public void clickOnAddStorageButton() {
		page.click(ADD_STORAGE_BUTTON_XPATH);
	}

	public void selectStorage(String storageName) {
		page.click(STORAGE_XPATH.replace("{Storage}", storageName));
	}

	public void enterCatalogName(String catalogName) {
		page.fill(CATALOG_NAME_TEXTBOX_ID, catalogName + " " + timestamp);
	}

	public void enterRegionName(String regionName) {
		page.fill(REGION_TEXTBOX_ID, regionName);
	}
	
	public void enterBucket(String bucket) {
		page.fill(BUCKET_TEXTBOX_ID, bucket);
	}

	public void enterAccessKey(String accessKey) {
		page.fill(ACCESS_KEY_TEXTBOX_ID, accessKey);
	}

	public void enterSecretKey() {
		page.fill(SECRET_KEY_TEXTBOX_ID, "Test@123");
	}

	public void clickOnCreateStorageButton() {
		page.click(CREATE_STORAGE_BUTTON);
	}

	public String verifyStorageCreatedToastMessage() {
		String toastMessage = page.textContent(STORAGE_CREATE_SUCCESS_TOAST_MESSAGE_XPATH).trim();
		return toastMessage;
	}

	public String verifyStorageTitle(String storageTitle) {
		String actualtitle = page.textContent(STORAGE_TITLE_XPATH.replace("{title}", storageTitle + " " + timestamp))
				.trim();
		return actualtitle;
	}

	public String getExpectedStorageTitle(String title) {
		String expectedTitle = title + " " + timestamp;
		return expectedTitle;
	}

	public void clickOnSMSSTab() {
		page.click(SMSS_TAB_XPATH);
	}

	public String verifyNameFiledInSMSS() {
		String name = page.textContent(NAME_SMSS_PROPERTIES_XPATH).trim();
		return name;
	}

	public String verifyS3RegionFiledInSMSS() {
		String s3Region = page.textContent(S3_REGION_SMSS_PROPERTIES_XPATH).trim();
		return s3Region;
	}
	
	public String verifyS3BucketFiledInSMSS() {
		String s3Bucket = page.textContent(S3_BUCKET_SMSS_PROPERTIES_XPATH).trim();
		return s3Bucket;
	}

	public String verifyS3AccessKeyFiledInSMSS() {
		String s3AccessKey = page.textContent(S3_ACCESS_KEY_SMSS_PROPERTIES_XPATH).trim();
		return s3AccessKey;
	}

}
