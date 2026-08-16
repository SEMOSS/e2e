package aicore.utils.page.app;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import aicore.pages.home.MainMenuUtils;
import aicore.utils.CommonUtils;
import aicore.utils.TestResourceTrackerHelper;
import aicore.utils.waitLayer.Waits;
import io.qameta.allure.Step;

public class CreateAppPopupUtils {
	private static final Logger logger = LogManager.getLogger(CreateAppPopupUtils.class);

	public static List<String> createdAppNames = new ArrayList<>();

	
    // App Type Cards - Get Started buttons
	private static final String DRAG_AND_DROP_GET_STARTED_XPATH = "//div[@data-slot='card' and .//div[@data-slot='card-title' and normalize-space(.)='Drag and drop blocks']]//button[normalize-space(.)='Get Started']";
	private static final String AGENT_BUILDER_GET_STARTED_XPATH = "//div[@data-slot='card' and .//div[@data-slot='card-title' and normalize-space(.)='Construct an agent']]//button[normalize-space(.)='Get Started']";
	private static final String CODE_APP_GET_STARTED_BUTTON_XPATH = "//div[@data-slot='card' and .//div[@data-slot='card-title' and normalize-space(.)='Develop in code']]//button[normalize-space(.)='Get Started']";

    // New App Modal fields
    public static final String NAME_TEXTBOX_DATATESTID = "newAppModal-textField-name";
    private static final String DESCRIPTION_TEXTBOX_DATATESTID = "newAppModal-description-txt";
    private static final String TAG_TEXTBOX_DATATESTID = "newAppModal-tag-txt";
    private static final String FILE_INPUT_DATATESTID = "newAppModal-file-txt";
    private static final String CREATE_BUTTON_DATATESTID = "newAppModal-create-btn";
    private static final String CANCEL_BUTTON_DATATESTID = "newAppModal-cancel-btn";
    
    // Share/IFrame related
    private static final String IFRAME_BUTTON_XPATH = "//button[text()='IFrame']";
    private static final String SHARE_ICON_DATATESTID = "ShareRoundedIcon";
    private static final String CLOSE_ICON_DATATESTID = "ClearIcon";
    
    // App selection and navigation
    private static final String SELECT_APP_XPATH = "//*[text()='{Select_App}']";
    private static final String USER_FETCH_APP_NAME_XPATH = "//a[contains(@href,'/view')]//div";
    private static final String BREADCRUMB_LINK_XPATH = "//a//div[text()='{appName}']";
    private static final String UPLOAD_BUTTON_DATATESTID = "createAppSection-upload-btn";
    private static final String CREATE_NEW_APP_BTN_DATATESTID = "appCatalogPage-create-new-app-btn";
    
    
    // ==================== ACTIONS - Get Started ====================
	
	@Step("Click on Get Started button for app type: {appType}")
	public static void clickOnGetStartedButton(Page page, String appType) {
	    logger.info("Attempting to click on 'Get Started' button for app type: {}", appType);
	    Waits.waitForPageLoad(page);
		if (appType.toLowerCase().contains("agent")) {
			page.locator(AGENT_BUILDER_GET_STARTED_XPATH).click();
		} else if (appType.toLowerCase().contains("drag and drop")) {
			page.locator(DRAG_AND_DROP_GET_STARTED_XPATH).click();
		} else {
			page.locator(CODE_APP_GET_STARTED_BUTTON_XPATH).click();
		}
        logger.info("Successfully clicked on 'Get Started' button for app type: {}", appType);
	}
	
  // ==================== ACTIONS - Modal Fields ====================
    
    @Step("Enter app name: {appName}")
    public static void enterAppName(Page page, String appName) {
        logger.info("Entering app name: {}", appName);
        Locator nameField = page.getByTestId(NAME_TEXTBOX_DATATESTID);
        Waits.waitForElementVisible(nameField);
        nameField.fill(appName);
        logger.info("App name entered: {}", appName);
    }
    
    @Step("Enter app description: {appDescription}")
    public static void enterAppDescription(Page page, String appDescription) {
        logger.info("Entering app description");
        Locator descriptionField = page.getByTestId(DESCRIPTION_TEXTBOX_DATATESTID);
        Waits.waitForElementVisible(descriptionField);
        descriptionField.fill(appDescription);
        logger.info("App description entered");
    }
    
    @Step("Enter tags: {tags}")
    public static void enterTags(Page page, String tags) {
        logger.info("Entering tags: {}", tags);
        if (tags == null || tags.isEmpty()) {
            logger.warn("No tags provided");
            return;
        }
        
        String[] tagsArray = tags.split(", ");
        for (String tag : tagsArray) {
            enterTag(page, tag.trim());
        }
        logger.info("All tags entered successfully");
    }
    
    @Step("Enter tag: {tag}")
    public static void enterTag(Page page, String tag) {
        logger.info("Entering tag: {}", tag);
        Locator tagField = page.getByTestId(TAG_TEXTBOX_DATATESTID);
        Waits.waitForElementVisible(tagField);
        tagField.fill(tag);
        page.keyboard().press("Enter");
        page.waitForTimeout(300); 
        logger.info("Tag entered: {}", tag);
    }
    
    @Step("Upload image for app")
    public static void uploadImage(Page page, Path imagePath) {
        logger.info("Uploading image: {}", imagePath);
        Locator fileInput = page.getByTestId(FILE_INPUT_DATATESTID);
        Waits.waitForElementAttached(fileInput);
        fileInput.setInputFiles(imagePath);
        logger.info("Image uploaded: {}", imagePath);
    }
    
    // ==================== ACTIONS - Modal Buttons ====================
    
    @Step("Click on Create button")
    public static void clickOnCreateButton(Page page) {
        logger.info("Clicking on Create button");
        Locator createButton = page.getByTestId(CREATE_BUTTON_DATATESTID);
        Waits.waitForElementClickable(createButton);
        createButton.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
        page.waitForTimeout(2000);
        logger.info("Create button clicked successfully");
    }
    
    @Step("Click on Cancel button")
    public static void clickOnCancelButton(Page page) {
        logger.info("Clicking on Cancel button");
        Locator cancelButton = page.getByTestId(CANCEL_BUTTON_DATATESTID);
        Waits.waitForElementClickable(cancelButton);
        cancelButton.click();
        logger.info("Cancel button clicked");
    }
    
    // ==================== ACTIONS - Share & IFrame ====================
    
    @Step("Click on Share App button")
    public static void clickOnShareAppButton(Page page) {
        logger.info("Clicking on Share App button");
        Locator shareButton = page.getByTestId(SHARE_ICON_DATATESTID);
        Waits.waitForElementClickable(shareButton);
        shareButton.click();
        logger.info("Share button clicked");
    }
    
    @Step("Click on IFrame button")
    public static void clickOnIframeButton(Page page) {
        logger.info("Clicking on IFrame button");
        page.waitForTimeout(500);
        Locator iframeButton = page.locator(IFRAME_BUTTON_XPATH).first();
        iframeButton.click(new Locator.ClickOptions().setForce(true));
        logger.info("IFrame button clicked");
    }
    
    @Step("Click on Close button")
    public static void clickOnCloseButton(Page page) {
        logger.info("Clicking on Close button");
        Locator closeButton = page.getByTestId(CLOSE_ICON_DATATESTID).nth(1);
        closeButton.click(new Locator.ClickOptions().setForce(true));
        logger.info("Close button clicked");
    }
    
    @Step("Click on Upload button")
    public static void clickOnUploadButton(Page page) {
        logger.info("Clicking on Upload button");
        Locator uploadButton = page.getByTestId(UPLOAD_BUTTON_DATATESTID);
        Waits.waitForElementClickable(uploadButton);
        uploadButton.click();
        logger.info("Upload button clicked");
    }
    
    // ==================== ACTIONS - App Selection & Navigation ====================
    
    @Step("Select app from dropdown: {appName}")
    public static void selectApp(Page page, String appName, String timestamp) {
        logger.info("Selecting app: {} with timestamp: {}", appName, timestamp);
        String appNameWithTimestamp = appName + " " + timestamp;
        
        Locator projectInput = page.getByPlaceholder("Project");
        Waits.waitForElementVisible(projectInput);
        projectInput.click();
        projectInput.fill(appNameWithTimestamp);
        
        Locator appOption = page.locator(SELECT_APP_XPATH.replace("{Select_App}", appNameWithTimestamp));
        Waits.waitForElementVisible(appOption);
        appOption.click();
        
        logger.info("App selected: {}", appNameWithTimestamp);
    }
    
    @Step("Click on breadcrumb link: {appName}")
    public static void userClickOnBreadcrumbLink(Page page, String appName, String timestamp) {
        logger.info("Clicking on breadcrumb link for app: {}", appName);
        String fullAppName = (timestamp != null && !timestamp.isEmpty()) 
            ? appName + " " + timestamp 
            : appName;
            
        Locator breadcrumbLink = page.locator(BREADCRUMB_LINK_XPATH.replace("{appName}", fullAppName)).first();
        Waits.waitForElementClickable(breadcrumbLink);
        breadcrumbLink.click();
        logger.info("Breadcrumb link clicked: {}", fullAppName);
    }
    
    // ==================== ACTIONS - Bulk Operations ====================
    
    //**Move to TemplateCreationUtils class  **//
    @Step("Create multiple apps")
    public static void createMultipleApps(Page page, String appType, String appName, 
                                         String appDescription, String appTags) {
        logger.info("Creating multiple apps - Starting with: {}", appName);
        MainMenuUtils.openMainMenu(page);
        MainMenuUtils.clickOnOpenAppLibrary(page);
        Locator createNewAppBtn = page.getByTestId(CREATE_NEW_APP_BTN_DATATESTID);
        Waits.waitForElementClickable(createNewAppBtn);
        createNewAppBtn.click();
        clickOnGetStartedButton(page, appType);
        enterAppName(page, appName);
        enterAppDescription(page, appDescription);
        enterTags(page, appTags);
        clickOnCreateButton(page);
        createdAppNames.add(appName);
        logger.info("App created and added to list: {}", appName);
    }
    
    // ==================== HELPER METHODS ====================
    
    
    @Step("Fetch app name from page")
    public static String userFetchAppName(Page page) {
        logger.info("Fetching app name from page");
        page.waitForTimeout(5000);
        
        Locator appNameLocator = page.locator(USER_FETCH_APP_NAME_XPATH);
        Waits.waitForElementVisible(appNameLocator);
        
        String actualAppName = appNameLocator.textContent().trim();
        TestResourceTrackerHelper.getInstance().setAppName(actualAppName);
        
        logger.info("Fetched app name: {}", actualAppName);
        return actualAppName;
    }
    
    
    public static void deleteCreatedApps(Page page) {
        logger.info("Deleting all created apps: {}", createdAppNames);
        for (String appName : createdAppNames) {
            if (appName != null && !appName.isBlank()) {
                CommonUtils.navigateAndDeleteApp(page, appName);
                logger.info("Deleted app: {}", appName);
            }
        }
        createdAppNames.clear();
        logger.info("All created apps deleted successfully");
    }
    
  
   
}
