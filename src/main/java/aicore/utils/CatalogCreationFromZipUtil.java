package aicore.utils;
import java.nio.file.Paths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import aicore.pages.base.AbstractBasePage;
import aicore.pages.home.MainMenuUtils;
import aicore.utils.waitLayer.Waits;
import io.qameta.allure.Step;

public class CatalogCreationFromZipUtil extends AbstractBasePage {

    private static final Logger logger = LogManager.getLogger(CatalogCreationFromZipUtil.class);

    private static final String CATALOG_MENU_BUTTON_DATA_TESTID = "sidebar-{catalogName}-btn";
    private static final String ADD_CATALOG_BUTTON_DATA_TESTID = "engineIndex-add-{catalog}-btn";
    private static final String UPLOAD_FILE_BUTTON_XPATH = "//button[contains(@data-testid,'upload-submit-button')]";
    private static final String ZIP_UPLOAD_ICON_XPATH = "//button[contains(@data-testid,'-upload-file-button')]";
    private static final String APP_UPLOAD_BUTTON_DATATESTID = "createAppSection-upload-btn";
    private static final String CLICK_ON_NEXT_BUTTON_FOR_UPLOAD_FILE_XPATH = "//button[text()='Next']";
    private static final String CLICK_ON_UPLOAD_BUTTON_FOR_UPLOAD_FILE_XPATH = "//div[@data-slot='dialog-footer']//button[text()='Upload']";
    private static final String SELECT_FOLDER_TYPE_XPATH = "//label[text()='Folder Type']/parent::div//button";

    public static void openCatalog(Page page, String catalogName) {
        logger.info("Starting: openCatalog");
        Locator locator = page.getByTestId(CATALOG_MENU_BUTTON_DATA_TESTID.replace("{catalogName}", catalogName));
        MainMenuUtils.clickOnLocatorAndCLoseMainMenu(page, locator);
        logger.info("Completed: openCatalog");
    }

    public static void clickOnAddCatalogButton(Page page, String catalogName) {
        logger.info("Starting: clickOnAddCatalogButton");
        Locator locator = page.getByTestId(ADD_CATALOG_BUTTON_DATA_TESTID.replace("{catalog}", catalogName));
        locator.click();
        logger.info("Completed: clickOnAddCatalogButton");
    }

    public static void selectAddCatalogOption(Page page, String option) {
        logger.info("Starting: selectAddCatalogOption");
        page.locator(ZIP_UPLOAD_ICON_XPATH).isVisible();
        page.locator(ZIP_UPLOAD_ICON_XPATH).click();
        logger.info("Completed: selectAddCatalogOption");
    }

    public static void clickOnCreateCatalogButton(Page page) {
        logger.info("Starting: clickOnCreateCatalogButton");
        page.locator(UPLOAD_FILE_BUTTON_XPATH).isVisible();
        page.locator(UPLOAD_FILE_BUTTON_XPATH).click();
        logger.info("Completed: clickOnCreateCatalogButton");
    }

    public static void clickOnUploadButton(Page page, String label) {
        logger.info("Starting: clickOnUploadButton");
        Locator buttonLocator = page.locator(UPLOAD_FILE_BUTTON_XPATH);
        buttonLocator.scrollIntoViewIfNeeded();
        buttonLocator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        buttonLocator.click();

        Locator loadingSpinner = page.locator("//span[@role='progressbar']").first();
        if (loadingSpinner.isVisible()) {
            loadingSpinner.waitFor(new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.HIDDEN)
                    .setTimeout(120000));
        }
        logger.info("Completed: clickOnUploadButton");
    }

    // alternative to CodeAppPAgeUtils.userCanSeeFolder
    public static boolean userSeesItemInFilesList(Page page, String itemIdentifier) {
        logger.info("Starting: userSeesItemInFilesList");
        Locator listItem = page.getByTitle(itemIdentifier);
        boolean visible = listItem.isVisible();
        logger.info("Completed: userSeesItemInFilesList - visible: {}", visible);
        return visible;
    }

    // alternative to CodeAppPAgeUtils.userSelectTheFolder
    public static void userClicksOnItemInFilesList(Page page, String itemIdentifier) {
        logger.info("Starting: userClicksOnItemInFilesList");
        Locator listItem = page.getByTitle(itemIdentifier);
        waitAndClick(listItem);
        logger.info("Completed: userClicksOnItemInFilesList");
    }

    public static void clickOnFileUploadIconForAPP(Page page) {
        logger.info("Starting: clickOnFileUploadIconForAPP");
        page.getByTestId(APP_UPLOAD_BUTTON_DATATESTID).click();
        logger.info("Completed: clickOnFileUploadIconForAPP");
    }

    public static void clickOnNextButton(Page page) {
        logger.info("Starting: clickOnNextButton");
        page.locator(CLICK_ON_NEXT_BUTTON_FOR_UPLOAD_FILE_XPATH).click();
        logger.info("Completed: clickOnNextButton");
    }

    public static void selectTheFolderType(Page page) {
        logger.info("Starting: selectTheFolderType");
        Locator selectFolderType = page.locator(SELECT_FOLDER_TYPE_XPATH);
        selectFolderType.click();
        selectFolderType.selectOption("App Zip");
        logger.info("Completed: selectTheFolderType");
    }

    public static void clickOnUploadButton(Page page) {
        logger.info("Starting: clickOnUploadButton");
        page.locator(CLICK_ON_UPLOAD_BUTTON_FOR_UPLOAD_FILE_XPATH).click();
        logger.info("Completed: clickOnUploadButton");
    }

    @Step("Click on File Upload Icon")
    public static void clickOnFileUploadIcon(Page page) {
        logger.info("Starting: clickOnFileUploadIcon");
        logger.info("STEP: Click on File Upload Icon");

        Locator icon = page.locator("button[data-testid*='upload-file-button']");
        Waits.waitForElementVisible(icon, 30000);
        Waits.waitAndClick(icon);

        Locator modal = page.getByTestId("database-zip-upload-modal");
        Waits.waitForElementVisible(modal, 15000);

        logger.info("File upload icon clicked and modal displayed");
        logger.info("Completed: clickOnFileUploadIcon");
    }

    @Step("Upload File: {fileName}")
    public static String uploadFile(Page page, String fileName) {
        logger.info("Starting: uploadFile");
        logger.info("STEP: Upload File: {}", fileName);

        Locator modal = page.getByTestId("database-zip-upload-modal");
        Waits.waitForElementVisible(modal, 15000);

        String relativePath = "src/test/resources/data/";
        String fullPath = relativePath + fileName;

        Locator fileInput = page.locator("input[type='file'][accept='.zip']");
        fileInput.setInputFiles(Paths.get(fullPath));

        page.evaluate("() => {" +
                "const input = document.querySelector('input[type=\"file\"][accept=\".zip\"]');" +
                "if (input) {" +
                "   input.dispatchEvent(new Event('change', { bubbles: true }));" +
                "}" +
                "}");

        page.waitForTimeout(1500);

        Locator uploadButton = page.locator("button[data-testid='database-upload-submit-button']");
        Waits.waitForCondition(
                page,
                p -> {
                    try {
                        return !uploadButton.isDisabled();
                    } catch (Exception e) {
                        return false;
                    }
                },
                30000
        );

        Locator fileTextLocator = page.locator("//div[contains(@class, 'text-center')]/p[contains(@class, 'text-foreground')]");
        Waits.waitForElementVisible(fileTextLocator, 5000);
        String uploadedFileName = fileTextLocator.textContent();

        logger.info("File uploaded successfully: {}", uploadedFileName);
        logger.info("Completed: uploadFile");
        return uploadedFileName;
    }

    @Step("Click on Upload Button")
    public static void clickOnUploadButton_New(Page page) {
        logger.info("Starting: clickOnUploadButton_New");
        logger.info("STEP: Click on Upload Button");

        Waits.waitForPageLoad(page);

        Locator uploadButton = page.locator("button[data-testid='database-upload-submit-button']");
        Waits.waitForCondition(
                page,
                p -> {
                    try {
                        return !uploadButton.isDisabled();
                    } catch (Exception e) {
                        return false;
                    }
                },
                30000);

        Waits.waitForElementVisible(uploadButton, 5000);
        Waits.waitAndClick(uploadButton);

        Locator modal = page.getByTestId("database-zip-upload-modal");
        Waits.waitForElementHidden(modal, 30000);

        logger.info("Upload button clicked and modal closed");
        logger.info("Completed: clickOnUploadButton_New");
    }
}
