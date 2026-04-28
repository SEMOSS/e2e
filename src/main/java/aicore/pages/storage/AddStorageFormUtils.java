package aicore.pages.storage;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import aicore.pages.home.MainMenuUtils;
import aicore.utils.CatlogAccessPageUtility;
import aicore.utils.StoragePageUtils;

public class AddStorageFormUtils {
	
	private static final String CATALOG_NAME_DATA_TESTID = "storage-form-input-NAME";
	private static final String REGION_TEXTBOX_DATA_TESTID = "storage-form-input-S3_REGION";
	private static final String BUCKET_TEXTBOX_DATA_TESTID = "storage-form-input-S3_BUCKET";
	private static final String ACCESS_KEY_TEXTBOX_DATA_TESTID = "storage-form-input-S3_ACCESS_KEY";
	private static final String SECRET_KEY_TEXTBOX_DATA_TESTID = "storage-form-input-S3_SECRET_KEY";
	

	private static final String MANDATORY_FIELDS_XPATH = "//div[@data-testid='storage-form-field-{fieldName}']//span[text()='*']";
	private static final String FIELDS_DATA_TESTID = "storage-form-input-{fieldName}";
	private static final String FIELDS_UNDER_SECTION_XPATH = "//h4[normalize-space()='{sectionName}']/ancestor::div//input[@data-testid='storage-form-input-{fieldName}']";
	private static final String INPUT_FIELDS_XPATH = "//input[@data-testid='storage-form-input-{fieldName}']";
	private static final String DROPDOWN_FIELDS_XPATH = "//div[@data-testid='storage-form-input-{fieldName}']//*[@role='button' or @aria-haspopup='listbox']";
	private static final String PASSWORD_FIELDS_XPATH = "//div[@data-testid='storage-form-input-{fieldName}']//input[@type='password'] | .//textarea";
	private static final String NUMBER_FIELDS_XPATH = "//div[@data-testid='storage-form-input-{fieldName}']//input[@type='number'] | .//textarea";
	private static final String SELECT_DROPDOWN_VALUE_XPATH = "//li[normalize-space()='{fieldValue}']";
	
	public static void selectStorage(Page page, String storageName) {
		Locator locator = page.getByText(storageName).first();
		locator.click();
		page.waitForLoadState();
	}
	
	public static void enterCatalogName(Page page, String catalogName) {
		page.getByTestId(CATALOG_NAME_DATA_TESTID).fill(catalogName);
	}

	public static void enterRegionName(Page page, String regionName) {
		page.getByTestId(REGION_TEXTBOX_DATA_TESTID).fill(regionName);
	}

	public static void enterBucket(Page page, String bucket) {
		page.getByTestId(BUCKET_TEXTBOX_DATA_TESTID).fill(bucket);
	}

	public static void enterAccessKey(Page page, String accessKey) {
		page.getByTestId(ACCESS_KEY_TEXTBOX_DATA_TESTID).fill(accessKey);
	}

	public static void enterSecretKey(Page page, String secreteKey) {
		page.getByTestId(SECRET_KEY_TEXTBOX_DATA_TESTID).fill(secreteKey);
	}
	
	public static boolean verifyFieldIsVisible(Page page, String fieldName) {
		Locator fieldLocator = null;
		switch (fieldName) {
		case "Catalog Name":
			fieldLocator = page.getByTestId(CATALOG_NAME_DATA_TESTID);
			break;
		case "Region":
			fieldLocator = page.getByTestId(REGION_TEXTBOX_DATA_TESTID);
			break;
		case "Bucket":
			fieldLocator = page.getByTestId(BUCKET_TEXTBOX_DATA_TESTID);
			break;
		case "Access key":
			fieldLocator = page.getByTestId(ACCESS_KEY_TEXTBOX_DATA_TESTID);
			break;
		case "Secret key":
			fieldLocator = page.getByTestId(SECRET_KEY_TEXTBOX_DATA_TESTID);
			break;
		default:
			System.out.println("Invalid Field name" + fieldName);
			return false;
		}
		fieldLocator.scrollIntoViewIfNeeded();
		return fieldLocator != null && fieldLocator.isVisible();
	}

	public static void enterValuesInField(Page page, String fieldName, String fieldValue, String timestamp) {
		Locator fieldLocator = null;
		switch (fieldName) {
		case "Catalog Name":
			fieldLocator = page.getByTestId(CATALOG_NAME_DATA_TESTID);
			if (fieldValue != null && !fieldValue.trim().isEmpty()) {
				fieldValue = fieldValue + "" + timestamp;
			}
			break;
		case "Region":
			fieldLocator = page.getByTestId(REGION_TEXTBOX_DATA_TESTID);
			break;
		case "Bucket":
			fieldLocator = page.getByTestId(BUCKET_TEXTBOX_DATA_TESTID);
			break;
		case "Access key":
			fieldLocator = page.getByTestId(ACCESS_KEY_TEXTBOX_DATA_TESTID);
			break;
		case "Secret key":
			fieldLocator = page.getByTestId(SECRET_KEY_TEXTBOX_DATA_TESTID);
			break;
		default:
			System.out.println("Invalid Field name" + fieldName);
		}
		fieldLocator.fill(fieldValue);
	}
	
	public static boolean fieldUnderSection(Page page, String storageType, String section, String field) {
		String fieldName = getFieldNameForTestId(storageType, field);
		Locator fieldLocator = page.locator(
				FIELDS_UNDER_SECTION_XPATH.replace("{sectionName}", section).replace("{fieldName}", fieldName));
		fieldLocator.scrollIntoViewIfNeeded();
		return fieldLocator.isVisible();
	}

	public static void fillCatalogCreationForm(Page page, String storageType, String field, String fieldValue,
			String timestamp) {
		String fieldName = getFieldNameForTestId(storageType, field);
		Locator fieldContainer = page.getByTestId(FIELDS_DATA_TESTID.replace("{fieldName}", fieldName));
		fieldContainer.scrollIntoViewIfNeeded();
		Locator dropdownField = page.locator(DROPDOWN_FIELDS_XPATH.replace("{fieldName}", fieldName));
		Locator inputField = page.locator(INPUT_FIELDS_XPATH.replace("{fieldName}", fieldName));
		Locator passwordField = page.locator(PASSWORD_FIELDS_XPATH.replace("{fieldName}", fieldName));
		Locator numberField = page.locator(NUMBER_FIELDS_XPATH.replace("{fieldName}", fieldName));
		if (dropdownField.count() > 0) {
			dropdownField.first().click();
			Locator dropdownOption = page.locator(SELECT_DROPDOWN_VALUE_XPATH.replace("{fieldValue}", fieldValue));
			dropdownOption.click();
		} else if (passwordField.count() > 0) {
			passwordField.first().fill(fieldValue);
		} else if (numberField.count() > 0) {
			numberField.first().fill(fieldValue);
		} else {
			if (field.contains("Catalog Name")) {
				fieldValue = fieldValue + timestamp;
			}
			inputField.first().fill(fieldValue);
		}
	}
	
	public static boolean isFieldMandatory(Page page, String storageType, String field) {
		String fieldName = getFieldNameForTestId(storageType, field);
		Locator fieldLocator = page.locator(MANDATORY_FIELDS_XPATH.replace("{fieldName}", fieldName));
		fieldLocator.first().scrollIntoViewIfNeeded();
		return fieldLocator.first().isVisible();
	}
	
	private static String getFieldNameForTestId(String storageType, String field) {
		storageType = storageType.trim().toUpperCase();
		field = field.trim().replaceAll("\\s+", " ");
		String fieldNamesForDataTestid = switch (storageType) {
		case "S3" -> switch (field) {
		case "Catalog Name" -> "Name";
		case "Region" -> "S3_REGION";
		case "Bucket" -> "S3_BUCKET";
		case "Access Key" -> "S3 Access Key";
		case "Secret Key" -> "S3 Secret Key";
		default -> field;
		};
		case "CEPH" -> switch (field) {
		case "Catalog Name" -> "Name";
		case "Endpoint" -> "CEPH Endpoint";
		case "Root Bucket Path" -> "CEPH Bucket";
		case "Access Key" -> "CEPH Access Key";
		case "Secret Key" -> "CEPH Secret Key";
		default -> field;
		};
		case "GCS" -> switch (field) {
		case "Catalog Name" -> "Name";
		case "Region" -> "GCS Region";
		case "Bucket" -> "GCS Bucket";
		case "Service Account File" -> "GCS Service Account File";
		default -> field;
		};
		case "LOCAL" -> switch (field) {
		case "Catalog Name" -> "Name";
		case "Local Path Prefix" -> "Path Prefix";
		default -> field;
		};
		case "AZURE" -> switch (field) {
		case "Catalog Name" -> "Name";
		case "Account Name" -> "Az Account Name";
		case "Generate Dynamic SAS" -> "Az Generate Dynamic SAS";
		case "Primary Key" -> "Az Primary Key";
		case "Connection String" -> "Az Conn String";
		default -> field;
		};
		case "MINIO" -> switch (field) {
		case "Catalog Name" -> "Name";
		case "Region" -> "MINIO Region";
		case "Endpoint" -> "MINIO Endpoint";
		case "Root Bucket Path" -> "MINIO Bucket";
		case "Access Key" -> "MINIO Access Key";
		case "Secret Key" -> "MINIO Secret Key";
		default -> field;
		};
		case "NFS" -> switch (field) {
		case "Catalog Name" -> "Name";
		case "Network Path Prefix" -> "Path Prefix";
		default -> field;
		};
		case "SFTP" -> switch (field) {
		case "Catalog Name" -> "Name";
		case "Host" -> "Hostname";
		default -> field;
		};
		default -> throw new IllegalArgumentException("Invalid storage type: " + storageType);
		};
		return fieldNamesForDataTestid.replace(" ", "_").toUpperCase();
	}

	public static void createStorage(Page page, String storageType, String catalogName, String region, String bucket) {
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenStorage(page);
		StoragePageUtils.clickOnAddStorageButton(page);
		selectStorage(page, storageType);
		enterCatalogName(page, catalogName);
		enterRegionName(page, region);
		enterBucket(page, bucket);
		StoragePageUtils.clickOnConnectButton(page);
		CatlogAccessPageUtility.getCatalogAndCopyId(page);
}
}