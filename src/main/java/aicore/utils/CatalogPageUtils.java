package aicore.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.qameta.allure.Step;


public class CatalogPageUtils {
    
    private static final Logger logger = LogManager.getLogger(CatalogPageUtils.class);
    
    private static final String METADATA_TAB_DATA_TESTID = "engineLayout-Metadata-tab";
    
    @Step("Click on Metadata tab")
    public static void clickOnMetadataTab(Page page) {
        logger.info("Clicking on Metadata tab");
        Locator locator = page.getByTestId(METADATA_TAB_DATA_TESTID);
        AICorePageUtils.waitFor(locator);
        locator.isVisible();
        locator.click();
        logger.info("Successfully clicked on Metadata tab");
    }
    
    @Step("Check if Metadata tab is visible")
    public static boolean isMetadataTabVisible(Page page) {
        logger.info("Checking if Metadata tab is visible");
        Locator locator = page.getByTestId(METADATA_TAB_DATA_TESTID);
        boolean isVisible = locator.isVisible();
        return isVisible;
    }
    
}

