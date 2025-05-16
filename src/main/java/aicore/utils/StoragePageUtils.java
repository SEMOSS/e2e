package aicore.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class StoragePageUtils {

	private static final String ADD_STORAGE_BUTTON_XPATH = "[aria-label=\"Navigate to import Storage\"]";
	private static final String STORAGE_XPATH = "//div[@class='css-axw7ok']//p[text()='{Storage}']";
	private static final String CREATE_STORAGE_BUTTON = "//span[text()='Create storage']";
	private static final String STORAGE_CREATE_SUCCESS_TOAST_MESSAGE_XPATH = "//div[text()='Successfully added to catalog storage']";
	private static final String STORAGE_TITLE_XPATH = "//h4[text()='{title}']";
	private static final String SMSS_TAB_XPATH = "//button[text()='SMSS']";
	private static final String NAME_SMSS_PROPERTIES_XPATH = "//div[@class='view-line']//span[@class='mtk1'][starts-with(text(), 'NAME')]";
	private static final String S3_REGION_SMSS_PROPERTIES_XPATH = "//div[@class='view-line']//span[@class='mtk1'][starts-with(text(), 'S3_REGION')]";
	private static final String S3_BUCKET_SMSS_PROPERTIES_XPATH = "//div[@class='view-line']//span[@class='mtk1'][starts-with(text(), 'S3_BUCKET')]";
	private static final String S3_ACCESS_KEY_SMSS_PROPERTIES_XPATH = "//div[@class='view-line']//span[@class='mtk1'][starts-with(text(), 'S3_ACCESS_KEY')]";
	private static final String CATALOG_NAME_TEXTBOX_DATATESTID = "importForm-textField-NAME";
	private static final String REGION_TEXTBOX_DATATESTID = "importForm-textField-S3_REGION";
	private static final String BUCKET_TEXTBOX_DATATESTID = "importForm-textField-S3_BUCKET";
	private static final String ACCESS_KEY_TEXTBOX_DATATESTID = "importForm-textField-S3_ACCESS_KEY";
	private static final String SECRET_KEY_TEXTBOX_DATATESTID = "importForm-textField-S3_SECRET_KEY";

	public static void clickOnAddStorageButton(Page page) {
		page.click(ADD_STORAGE_BUTTON_XPATH);
	}

	public static void selectStorage(Page page, String storageName) {
		page.click(STORAGE_XPATH.replace("{Storage}", storageName));
	}

	public static void enterCatalogName(Page page, String catalogName) {
		page.getByTestId(CATALOG_NAME_TEXTBOX_DATATESTID).fill(catalogName);
	}

	public static void enterRegionName(Page page, String regionName) {
		page.getByTestId(REGION_TEXTBOX_DATATESTID).fill(regionName);
	}

	public static void enterBucket(Page page, String bucket) {
		page.getByTestId(BUCKET_TEXTBOX_DATATESTID).fill(bucket);
	}

	public static void enterAccessKey(Page page, String accessKey) {
		page.getByTestId(ACCESS_KEY_TEXTBOX_DATATESTID).fill(accessKey);
	}

	public static void enterSecretKey(Page page, String secreteKey) {
		page.getByTestId(SECRET_KEY_TEXTBOX_DATATESTID).fill(secreteKey);
	}

	public static void clickOnCreateStorageButton(Page page) {
		page.locator(CREATE_STORAGE_BUTTON).isVisible();
		page.locator(CREATE_STORAGE_BUTTON).click();
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

	public static void verifyAsteriskMarkOnFields(Page page, String fieldLabels) {
		String[] labels = fieldLabels.split(",");
		for (String label : labels) {
			String asteriskSelector = "//label[text()='%s']/span[text()='*']".replace("%s", label.trim());
			if (!page.locator(asteriskSelector).isVisible()
					|| !page.locator(asteriskSelector).textContent().contains("*")) {
				throw new AssertionError(
						"Asterisk mark is not visible or does not contain '*' for the field: " + label.trim());
			}
		}
	}

	public static boolean verifyMissingInputField(Page page, String fieldName) {
		Locator missingFieldParent = null;
		switch (fieldName) {
		case "Catalog Name":
			missingFieldParent = page.getByTestId(CATALOG_NAME_TEXTBOX_DATATESTID).locator("..");
			break;
		case "Region":
			missingFieldParent = page.getByTestId(REGION_TEXTBOX_DATATESTID).locator("..");
			break;
		case "Bucket":
			missingFieldParent = page.getByTestId(BUCKET_TEXTBOX_DATATESTID).locator("..");
			break;

		default:
			System.out.println("Invalid Field name" + fieldName);
		}
		missingFieldParent.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		String missingFieldClass = missingFieldParent.getAttribute("class");
		return missingFieldClass.contains("Mui-focused");
	}

	public static boolean verifyFieldIsVisible(Page page, String fieldName) {
		Locator fieldLocator = null;
		switch (fieldName) {
		case "Catalog Name":
			fieldLocator = page.getByTestId(CATALOG_NAME_TEXTBOX_DATATESTID);
			break;
		case "Region":
			fieldLocator = page.getByTestId(REGION_TEXTBOX_DATATESTID);
			break;
		case "Bucket":
			fieldLocator = page.getByTestId(BUCKET_TEXTBOX_DATATESTID);
			break;
		case "Access key":
			fieldLocator = page.getByTestId(ACCESS_KEY_TEXTBOX_DATATESTID);
			break;
		case "Secret key":
			fieldLocator = page.getByTestId(SECRET_KEY_TEXTBOX_DATATESTID);
			break;
		case "Create Storage button":
			fieldLocator = page.locator(CREATE_STORAGE_BUTTON);
			break;
		default:
			System.out.println("Invalid Field name" + fieldName);
			return false;
		}
		return fieldLocator != null && fieldLocator.isVisible();
	}

	public static void enterValuesInField(Page page, String fieldName, String fieldValue, String timestamp) {
		Locator fieldLocator = null;
		switch (fieldName) {
		case "Catalog Name":
			fieldLocator = page.getByTestId(CATALOG_NAME_TEXTBOX_DATATESTID);
			if (fieldValue != null && !fieldValue.trim().isEmpty()) {
				fieldValue = fieldValue + "" + timestamp;
			}
			break;
		case "Region":
			fieldLocator = page.getByTestId(REGION_TEXTBOX_DATATESTID);
			break;
		case "Bucket":
			fieldLocator = page.getByTestId(BUCKET_TEXTBOX_DATATESTID);
			break;
		case "Access key":
			fieldLocator = page.getByTestId(ACCESS_KEY_TEXTBOX_DATATESTID);
			break;
		case "Secret key":
			fieldLocator = page.getByTestId(SECRET_KEY_TEXTBOX_DATATESTID);
			break;
		default:
			System.out.println("Invalid Field name" + fieldName);
		}
		fieldLocator.fill(fieldValue);
	}
}
