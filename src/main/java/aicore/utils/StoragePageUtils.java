package aicore.utils;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class StoragePageUtils {

	private static final String ADD_STORAGE_BUTTON_XPATH = "//button[@aria-label='Navigate to import Storage']";
	private static final String STORAGE_TITLE_XPATH = "//h4[text()='{title}']";
	private static final String SMSS_TAB_XPATH = "//button[text()='SMSS']";
	private static final String NAME_SMSS_PROPERTIES_XPATH = "//div[@class='view-line']//span[@class='mtk1'][starts-with(text(), 'NAME')]";
	private static final String S3_REGION_SMSS_PROPERTIES_XPATH = "//div[@class='view-line']//span[@class='mtk1'][starts-with(text(), 'S3_REGION')]";
	private static final String S3_BUCKET_SMSS_PROPERTIES_XPATH = "//div[@class='view-line']//span[@class='mtk1'][starts-with(text(), 'S3_BUCKET')]";
	private static final String S3_ACCESS_KEY_SMSS_PROPERTIES_XPATH = "//div[@class='view-line']//span[@class='mtk1'][starts-with(text(), 'S3_ACCESS_KEY')]";
	private static final String CATALOG_NAME_XPATH = "//div[@data-testid='storage-form-input-NAME']//input[@type='text'] | .//textarea";
	private static final String REGION_TEXTBOX_XPATH = "//div[@data-testid='storage-form-input-S3_REGION']//input[@type='text'] | .//textarea";
	private static final String BUCKET_TEXTBOX_XPATH = "//div[@data-testid='storage-form-input-S3_BUCKET']//input[@type='text'] | .//textarea";
	private static final String ACCESS_KEY_TEXTBOX_XPATH = "//div[@data-testid='storage-form-input-S3_ACCESS_KEY']//input[@type='text'] | .//textarea";
	private static final String SECRET_KEY_TEXTBOX_XPATH = "//div[@data-testid='storage-form-input-S3_SECRET_KEY']//input[@type='password'] | .//textarea";
	private static final String FIELDS_UNDER_SECTION_XPATH = "//h6[text()='{section}']/parent::div/following-sibling::div//div[@data-testid='storage-form-input-{fieldName}']";
	private static final String MANDATORY_FIELDS_XPATH = "//div[@data-testid='storage-form-input-{fieldName}']//span[text()='*']";
	private static final String FIELDS_DATA_TESTID = "storage-form-input-{fieldName}";
	private static final String INPUT_FIELDS_XPATH = "//div[@data-testid='storage-form-input-{fieldName}']//input[@type='text'] | .//textarea";
	private static final String DROPDOWN_FIELDS_XPATH = "//div[@data-testid='storage-form-input-{fieldName}']//*[@role='button' or @aria-haspopup='listbox']";
	private static final String PASSWORD_FIELDS_XPATH = "//div[@data-testid='storage-form-input-{fieldName}']//input[@type='password'] | .//textarea";
	private static final String NUMBER_FIELDS_XPATH = "//div[@data-testid='storage-form-input-{fieldName}']//input[@type='number'] | .//textarea";
	private static final String SELECT_DROPDOWN_VALUE_XPATH = "//li[normalize-space()='{fieldValue}']";
	private static final String CONNECT_BUTTON_DATA_TESTID = "storage-form-submit";
	private static final String VIEW_STORAGE_XPATH = "//button[@aria-label='copy Storage ID']/parent::span";
	private static final String COPY_ID_ICON_XPATH = "[data-testid='ContentCopyOutlinedIcon']";
	private static final String STORAGE_DESCRIPTION_XPATH = "//h6[text()='{StorageDescription}']";
	private static final String OVERVIEW_TAB_XPATH = "//button[text()='Overview']";
	private static final String EDIT_BTN_XPATH = "//span[text()='Edit']";
	private static final String TAGS_XPATH = "//span[text()='Tag']/ancestor::fieldset/parent::div//input";
	private static final String SUBMIT_BTN_XPATH = "//span[text()='Submit']";
	private static final String EMBEDDED_TOAST_MESSAGE_XPATH = "//div[text()='{ToastMessage}']";
	private static final String PUBLISHED_BY_INFO_XPATH = "//p[text()='Published by:']";
	private static final String CHANGE_ACCESS_BUTTON_XPATH = "//span[text()='Change Access']";
	private static final String COPY_TOAST_MESSAGE_XPATH = "//div[text()='{ToastMessage}']";
	private static final String CURRENT_DATE_XPATH = "//p[contains(text(),'{Time}')]";
	private static final String CANCEL_BUTTON_XPATH = "//button[text()='Cancel']";
	private static final String SETTINGS_TAB_XPATH = "//button[text()='Settings']";
	private static final String LOCAL_PATH_PREFIX_XPATH = "//div[@data-testid='storage-form-input-PATH_PREFIX']//input[@type='text'] | .//textarea";
	private static final String DELETE_BUTTON_XPATH = "//span[text()='Delete']";
	private static final String CONFIRMATION_POPUP_XPATH = "//div[contains(@class,'MuiDialog-paperWidthSm')]";
	private static final String CONFIRMATION_POPUP_DELETE_BUTTON_XPATH = "//div[contains(@class,'MuiDialog-paperWidthSm')]//div//button[contains(@class,'MuiButton-containedSizeMedium')]";
	private static final String DELETE_TOAST_MESSAGE = "Successfully deleted Storage";
	private static final String STORAGE_CARD_XPATH = "//p[contains(text(),'{catalogName}')]";
	private static final String DISCOVERABLE_STORAGES_XPATH = "engineIndexPage-Storages-discoverable-switch";
	private static final String BUTTON_XPATH = "//button[text()='{buttonName}']";

	public static void clickOnAddStorageButton(Page page) {
		page.click(ADD_STORAGE_BUTTON_XPATH);
	}

	public static void selectStorage(Page page, String storageName) {
		Locator locator = page.getByText(storageName).first();
		locator.click();
		page.waitForLoadState();
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

	public static boolean fieldUnderSection(Page page, String storageType, String section, String field) {
		String fieldName = getFieldNameForTestId(storageType, field);
		Locator fieldLocator = page
				.locator(FIELDS_UNDER_SECTION_XPATH.replace("{section}", section).replace("{fieldName}", fieldName));
		fieldLocator.scrollIntoViewIfNeeded();
		return fieldLocator.isVisible();
	}

	public static boolean isFieldMandatory(Page page, String storageType, String field) {
		String fieldName = getFieldNameForTestId(storageType, field);
		Locator fieldLocator = page.locator(MANDATORY_FIELDS_XPATH.replace("{fieldName}", fieldName));
		fieldLocator.first().scrollIntoViewIfNeeded();
		return fieldLocator.first().isVisible();
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

	public static void enterCatalogName(Page page, String catalogName) {
		page.locator(CATALOG_NAME_XPATH).fill(catalogName);
	}

	public static void enterRegionName(Page page, String regionName) {
		page.locator(REGION_TEXTBOX_XPATH).fill(regionName);
	}

	public static void enterBucket(Page page, String bucket) {
		page.locator(BUCKET_TEXTBOX_XPATH).fill(bucket);
	}

	public static void enterAccessKey(Page page, String accessKey) {
		page.locator(ACCESS_KEY_TEXTBOX_XPATH).fill(accessKey);
	}

	public static void enterSecretKey(Page page, String secreteKey) {
		page.locator(SECRET_KEY_TEXTBOX_XPATH).fill(secreteKey);
	}

	public static boolean validateConnectButtonEnabled(Page page) {
		Locator connectButton = page.getByTestId(CONNECT_BUTTON_DATA_TESTID);
		connectButton.scrollIntoViewIfNeeded();
		return connectButton.isEnabled();
	}

	public static void clickOnConnectButton(Page page) {
		Locator connectButton = page.getByTestId(CONNECT_BUTTON_DATA_TESTID);
		connectButton.scrollIntoViewIfNeeded();
		connectButton.click();
	}

	public static String verifyStorageCreatedToastMessage(Page page) {
		Locator alert = page.getByTestId("notification-success-alert");
		return AICorePageUtils.verifySuccessToastMessage(page, alert);
	}

	public static String verifyStorageTitle(Page page, String storageTitle, String timestamp) {
		String actualtitle = page.textContent(STORAGE_TITLE_XPATH.replace("{title}", storageTitle + " " + timestamp))
				.trim();
		return actualtitle;
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

	public static boolean verifyConnectButtonDisabled(Page page) {
		return page.getByTestId(CONNECT_BUTTON_DATA_TESTID).isDisabled();
	}

	public static boolean verifyFieldIsVisible(Page page, String fieldName) {
		Locator fieldLocator = null;
		switch (fieldName) {
		case "Catalog Name":
			fieldLocator = page.locator(CATALOG_NAME_XPATH);
			break;
		case "Region":
			fieldLocator = page.locator(REGION_TEXTBOX_XPATH);
			break;
		case "Bucket":
			fieldLocator = page.locator(BUCKET_TEXTBOX_XPATH);
			break;
		case "Access key":
			fieldLocator = page.locator(ACCESS_KEY_TEXTBOX_XPATH);
			break;
		case "Secret key":
			fieldLocator = page.locator(SECRET_KEY_TEXTBOX_XPATH);
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
			fieldLocator = page.locator(CATALOG_NAME_XPATH);
			if (fieldValue != null && !fieldValue.trim().isEmpty()) {
				fieldValue = fieldValue + "" + timestamp;
			}
			break;
		case "Region":
			fieldLocator = page.locator(REGION_TEXTBOX_XPATH);
			break;
		case "Bucket":
			fieldLocator = page.locator(BUCKET_TEXTBOX_XPATH);
			break;
		case "Access key":
			fieldLocator = page.locator(ACCESS_KEY_TEXTBOX_XPATH);
			break;
		case "Secret key":
			fieldLocator = page.locator(SECRET_KEY_TEXTBOX_XPATH);
			break;
		default:
			System.out.println("Invalid Field name" + fieldName);
		}
		fieldLocator.fill(fieldValue);
	}

	public static void viewStorageIDalongwithCopyIcon(Page page) {
		page.locator(VIEW_STORAGE_XPATH).isVisible();
		page.locator(COPY_ID_ICON_XPATH).isVisible();
	}

	public static void clickCopyIcon(Page page) {
		page.locator(COPY_ID_ICON_XPATH).click();
	}

	public static void verifyToastMessage(Page page, String toastMessage) {
		page.getByText(COPY_TOAST_MESSAGE_XPATH.replace("{ToastMessage}", toastMessage)).isVisible();
	}

	public static void verifyStorageDescription(Page page, String storageDescription) {
		page.getByText(STORAGE_DESCRIPTION_XPATH.replace("{StorageDescription}", storageDescription)).isVisible();
	}

	public static void verifyEmbeddedTags(Page page, String tag, String toastMessage) {
		page.getByText(OVERVIEW_TAB_XPATH).isVisible();
		page.locator(EDIT_BTN_XPATH).click();
		page.locator(TAGS_XPATH).fill(tag);
		page.keyboard().press("Enter");
		page.locator(SUBMIT_BTN_XPATH).click();
		page.getByText(EMBEDDED_TOAST_MESSAGE_XPATH.replace("{ToastMessage}", toastMessage)).isVisible();
	}

	public static String verifyExpectedTime(Page page) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		return ZonedDateTime.now(ZoneId.of("UTC")).format(formatter);
	}

	public static boolean verifyActualTime(Page page, String time) {
		return page.locator(CURRENT_DATE_XPATH.replace("{Time}", time)).isVisible();
	}

	public static void verifyPublishedByInfo(Page page) {
		page.getByText(PUBLISHED_BY_INFO_XPATH).isVisible();
	}

	public static boolean verifyChangeAccessButton(Page page) {
		return page.getByText(CHANGE_ACCESS_BUTTON_XPATH).isVisible();
	}

	public static void clickOnCancelButton(Page page) {
		page.click(CANCEL_BUTTON_XPATH);
	}

	public static void clickOnSettingsTab(Page page) {
		page.click(SETTINGS_TAB_XPATH);
	}

	public static void enterLocalPathPrefix(Page page, String path) {
		page.locator(LOCAL_PATH_PREFIX_XPATH).fill(path);
	}

	public static void searchStorage(Page page, String storageName) {
		page.getByTestId("search-bar").click();
		page.getByTestId("search-bar").fill(storageName);
	}

	public static void clickOnCreatedStorage(Page page, String storageName) {
		page.locator(STORAGE_CARD_XPATH.replace("{catalogName}", storageName)).isVisible();
		page.locator(STORAGE_CARD_XPATH.replace("{catalogName}", storageName)).click();
	}

	public static void clickOnDeleteButton(Page page) {
		page.locator(DELETE_BUTTON_XPATH).isVisible();
		page.locator(DELETE_BUTTON_XPATH).click();
	}

	public static void clickOnDeleteConfirmationButton(Page page) {
		page.locator(CONFIRMATION_POPUP_XPATH)
				.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		page.locator(CONFIRMATION_POPUP_DELETE_BUTTON_XPATH).isVisible();
		page.locator(CONFIRMATION_POPUP_DELETE_BUTTON_XPATH).click();
	}

	public static String verifyDeleteToastMessage(Page page) {
		page.getByText(DELETE_TOAST_MESSAGE)
				.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		String toastMessage = page.getByText(DELETE_TOAST_MESSAGE).textContent();
		return toastMessage;
	}

	public static void clickOnDiscoverableStoragesButton(Page page) {
		page.getByTestId(DISCOVERABLE_STORAGES_XPATH).click();
	}

	public static void clickOnButton(Page page, String buttonName) {
		page.click(BUTTON_XPATH.replace("{buttonName}", buttonName));
	}
}