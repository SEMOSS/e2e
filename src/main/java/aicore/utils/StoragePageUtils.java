package aicore.utils;

import com.microsoft.playwright.Page;

public class StoragePageUtils {

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

	public static void clickOnAddStorageButton(Page page) {
		page.click(ADD_STORAGE_BUTTON_XPATH);
	}
	
	public static void selectStorage(Page page, String storageName) {
		page.click(STORAGE_XPATH.replace("{Storage}", storageName));
	}
	
	public static void enterCatalogName(Page page, String catalogName) {
		page.fill(CATALOG_NAME_TEXTBOX_ID, catalogName);
	}
	
	public static void enterRegionName(Page page, String regionName) {
		page.fill(REGION_TEXTBOX_ID, regionName);
	}
	
	public static void enterBucket(Page page, String bucket) {
		page.fill(BUCKET_TEXTBOX_ID, bucket);
	}
	
	public static void enterAccessKey(Page page, String accessKey) {
		page.fill(ACCESS_KEY_TEXTBOX_ID, accessKey);
	}
	
	public static void enterSecretKey(Page page) {
		page.fill(SECRET_KEY_TEXTBOX_ID, "Test@123");
	}
	
	public static void clickOnCreateStorageButton(Page page) {
		page.click(CREATE_STORAGE_BUTTON);
	}
	
	public static String verifyStorageCreatedToastMessage(Page page) {
		String toastMessage = page.textContent(STORAGE_CREATE_SUCCESS_TOAST_MESSAGE_XPATH).trim();
		return toastMessage;
	}
	
	public static String verifyStorageTitle(Page page, String storageTitle) {
		String actualtitle = page.textContent(STORAGE_TITLE_XPATH.replace("{title}", storageTitle)).trim();
		return actualtitle;
	}
	
	public static void clickOnSMSSTab(Page page) {
		page.click(SMSS_TAB_XPATH);
	}
	
	public static String verifyNameFiledInSMSS(Page page) {
		String name = page.textContent(NAME_SMSS_PROPERTIES_XPATH).trim();
		return name;
	}
	
	public static String verifyS3RegionFiledInSMSS(Page page) {
		String s3Region = page.textContent(S3_REGION_SMSS_PROPERTIES_XPATH).trim();
		return s3Region;
	}
	
	public static String verifyS3BucketFiledInSMSS(Page page) {
		String s3Bucket = page.textContent(S3_BUCKET_SMSS_PROPERTIES_XPATH).trim();
		return s3Bucket;
	}
	
	public static String verifyS3AccessKeyFiledInSMSS(Page page) {
		String s3AccessKey = page.textContent(S3_ACCESS_KEY_SMSS_PROPERTIES_XPATH).trim();
		return s3AccessKey;
	}
}
