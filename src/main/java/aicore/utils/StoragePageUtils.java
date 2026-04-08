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
	private static final String LOCAL_PATH_PREFIX_DATATESTID = "storage-form-input-PATH_PREFIX";
	private static final String DELETE_BUTTON_XPATH = "//button[contains(@data-testid,'-delete-btn')]";
	private static final String CONFIRMATION_POPUP_XPATH = "//div[@data-slot='dialog-content']";
	private static final String CONFIRMATION_POPUP_DELETE_BUTTON_XPATH = "//button[contains(@data-testid,'confirmDelete-btn')]";
	private static final String DELETE_TOAST_MESSAGE = "Successfully deleted Storage";
	private static final String STORAGE_CARD_XPATH = "//p[contains(text(),'{catalogName}')]";
	private static final String DISCOVERABLE_STORAGES_XPATH = "engineIndexPage-Storages-discoverable-switch";
	private static final String BUTTON_XPATH = "//button[text()='{buttonName}'] | //span[text()='{buttonName}']";
	private static final String TOAST_MESSAGE_XPATH = "//div[text()='{message}']";

	public static void clickOnAddStorageButton(Page page) {
		page.click(ADD_STORAGE_BUTTON_XPATH);
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

	public static String verifyStorageCreatedToastMessage(Page page, String toastMessage) {
		Locator alert = page.locator(TOAST_MESSAGE_XPATH.replace("{message}", toastMessage));
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
		page.getByTestId(LOCAL_PATH_PREFIX_DATATESTID).fill(path);
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