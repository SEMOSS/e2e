package aicore.utils.waitLayer;

import java.util.function.Predicate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;

public class Waits {
    
    private static final Logger logger = LogManager.getLogger(Waits.class);
    
    // Default timeout values
    public static final int DEFAULT_TIMEOUT = 30000; // 30 seconds
    public static final int SHORT_TIMEOUT = 10000;   // 10 seconds
    public static final int LONG_TIMEOUT = 60000;    // 60 seconds
    

    public static void waitForElementVisible(Locator locator) {
        waitForElementVisible(locator, DEFAULT_TIMEOUT);
    }
    
    public static void waitForElementVisible(Locator locator, int timeout) {
        logger.debug("Waiting for element to be visible: {}", locator);
        try {
            locator.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE)
                .setTimeout(timeout));
            logger.debug("Element is visible: {}", locator);
        } catch (PlaywrightException e) {
            logger.error("Element not visible after {}ms: {}", timeout, locator);
            throw e;
        }
    }
    
  
    public static void waitForElementAttached(Locator locator) {
        waitForElementAttached(locator, DEFAULT_TIMEOUT);
    }
    
    public static void waitForElementAttached(Locator locator, int timeout) {
        logger.debug("Waiting for element to be attached: {}", locator);
        try {
            locator.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.ATTACHED)
                .setTimeout(timeout));
            logger.debug("Element is attached: {}", locator);
        } catch (PlaywrightException e) {
            logger.error("Element not attached after {}ms: {}", timeout, locator);
            throw e;
        }
    }
    
  
    public static void waitForElementEnabled(Locator locator) {
        waitForElementEnabled(locator, DEFAULT_TIMEOUT);
    }
    
    public static void waitForElementEnabled(Locator locator, int timeout) {
        logger.debug("Waiting for element to be enabled: {}", locator);
        try {
            locator.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE)
                .setTimeout(timeout));
            
            boolean isDisabled = locator.isDisabled();
            if (isDisabled) {
                logger.warn("Element is disabled: {}", locator);
                throw new PlaywrightException("Element is disabled");
            }
            logger.debug("Element is enabled: {}", locator);
        } catch (PlaywrightException e) {
            logger.error("Element not enabled after {}ms: {}", timeout, locator);
            throw e;
        }
    }
    
  
    public static void waitForElementClickable(Locator locator) {
        waitForElementClickable(locator, DEFAULT_TIMEOUT);
    }
    
    public static void waitForElementClickable(Locator locator, int timeout) {
        logger.debug("Waiting for element to be clickable: {}", locator);
        waitForElementVisible(locator, timeout);
        waitForElementEnabled(locator, timeout);
        logger.debug("Element is clickable: {}", locator);
    }
    
   
    public static void waitForElementHidden(Locator locator) {
        waitForElementHidden(locator, DEFAULT_TIMEOUT);
    }
    
    public static void waitForElementHidden(Locator locator, int timeout) {
        logger.debug("Waiting for element to be hidden: {}", locator);
        try {
            locator.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.HIDDEN)
                .setTimeout(timeout));
            logger.debug("Element is hidden: {}", locator);
        } catch (PlaywrightException e) {
            logger.error("Element not hidden after {}ms: {}", timeout, locator);
            throw e;
        }
    }
    
  
    public static void waitForElementText(Locator locator, String expectedText) {
        waitForElementText(locator, expectedText, DEFAULT_TIMEOUT);
    }
    
    public static void waitForElementText(Locator locator, String expectedText, int timeout) {
        logger.debug("Waiting for element text to contain '{}': {}", expectedText, locator);
        long startTime = System.currentTimeMillis();
        boolean textFound = false;
        
        while (System.currentTimeMillis() - startTime < timeout) {
            try {
                String actualText = locator.textContent();
                if (actualText != null && actualText.contains(expectedText)) {
                    textFound = true;
                    break;
                }
                Thread.sleep(500);
            } catch (Exception e) {
                logger.warn("Error while checking element text: {}", e.getMessage());
            }
        }
        
        if (!textFound) {
            throw new PlaywrightException("Element text did not contain '" + expectedText + "' within " + timeout + "ms");
        }
        logger.debug("Element contains expected text: {}", expectedText);
    }
    
   
    public static void waitForPageLoad(Page page) {
        waitForPageLoad(page, DEFAULT_TIMEOUT);
    }
    
    public static void waitForPageLoad(Page page, int timeout) {
        logger.debug("Waiting for page to load...");
        try {
            page.waitForLoadState(LoadState.NETWORKIDLE, new Page.WaitForLoadStateOptions()
                .setTimeout((double) timeout));
            page.waitForLoadState(LoadState.DOMCONTENTLOADED, new Page.WaitForLoadStateOptions()
                .setTimeout((double) timeout));
            logger.debug("Page loaded successfully");
        } catch (PlaywrightException e) {
            logger.error("Page failed to load within {}ms", timeout);
            throw e;
        }
    }
    
    
    public static void waitForCondition(Page page, Predicate<Page> condition) {
        waitForCondition(page, condition, DEFAULT_TIMEOUT);
    }
    
    public static void waitForCondition(Page page, Predicate<Page> condition, int timeout) {
        logger.debug("Waiting for condition to be true...");
        long startTime = System.currentTimeMillis();
        
        while (System.currentTimeMillis() - startTime < timeout) {
            try {
                if (condition.test(page)) {
                    logger.debug("Condition met");
                    return;
                }
                Thread.sleep(500);
            } catch (Exception e) {
                logger.warn("Error while waiting for condition: {}", e.getMessage());
            }
        }
        
        throw new PlaywrightException("Condition not met within " + timeout + "ms");
    }
    
   
    public static void waitAndClick(Locator locator) {
        waitAndClick(locator, DEFAULT_TIMEOUT);
    }
    
    public static void waitAndClick(Locator locator, int timeout) {
        logger.debug("Waiting and clicking element: {}", locator);
        waitForElementClickable(locator, timeout);
        
        try {
            locator.click(new Locator.ClickOptions()
                .setTimeout(timeout)
                .setForce(false));
            logger.debug("Clicked element: {}", locator);
        } catch (PlaywrightException e) {
            logger.error("Failed to click element: {}", e.getMessage());
            throw e;
        }
    }
    
    
    public static Locator waitAndGetElement(Locator locator) {
        return waitAndGetElement(locator, DEFAULT_TIMEOUT);
    }
    
    public static Locator waitAndGetElement(Locator locator, int timeout) {
        logger.debug("Waiting for element and returning: {}", locator);
        waitForElementVisible(locator, timeout);
        return locator;
    }
    
    
    public static void waitForElementAttribute(Locator locator, String attributeName, String expectedValue) {
        waitForElementAttribute(locator, attributeName, expectedValue, DEFAULT_TIMEOUT);
    }
    
    public static void waitForElementAttribute(Locator locator, String attributeName, String expectedValue, int timeout) {
        logger.debug("Waiting for element attribute '{}' to contain '{}': {}", attributeName, expectedValue, locator);
        long startTime = System.currentTimeMillis();
        boolean attributeFound = false;
        
        while (System.currentTimeMillis() - startTime < timeout) {
            try {
                String actualValue = locator.getAttribute(attributeName);
                if (actualValue != null && actualValue.equals(expectedValue)) {
                    attributeFound = true;
                    break;
                }
                Thread.sleep(500);
            } catch (Exception e) {
                logger.warn("Error while checking element attribute: {}", e.getMessage());
            }
        }
        
        if (!attributeFound) {
            throw new PlaywrightException("Element attribute '" + attributeName + 
                "' did not equal '" + expectedValue + "' within " + timeout + "ms");
        }
        logger.debug("Element attribute matches expected value: {}", expectedValue);
    }
}
