package aicore.framework;

import aicore.utils.CommonUtils;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

/**
 * Resource represents per-thread test resources (Playwright Page/Context and counters).
 *
 * Thread-safety model:
 * - Resource is intended to be used by a single thread (one Resource per test/thread).
 * - To use safely in concurrent execution, obtain instances via {@link ResourceManager#getResource()}.
 * - Internal mutable counters are implemented with AtomicInteger; string fields that require
 *   visibility across threads are declared volatile.
 */
public class Resource {

    private volatile String url;

    // Playwright artifacts - Page and BrowserContext are not safe to share between threads.
    // Resource instances should own their own BrowserContext/Page.
    private volatile Page page;
    private volatile BrowserContext context;
    private volatile Browser browser;

    // Shared browser/playwright are handled by ResourceManager, not stored as mutable here.
    private volatile Playwright playwright;

    private volatile String scenarioName;

    // Atomic counters for safe concurrent increments if accessed from other threads (defensive).
    private final java.util.concurrent.atomic.AtomicInteger step = new java.util.concurrent.atomic.AtomicInteger(0);
    private final java.util.concurrent.atomic.AtomicInteger scenarioNumberOfFeatureFile = new java.util.concurrent.atomic.AtomicInteger(0);
    private volatile String feature = "";
    private final java.util.concurrent.atomic.AtomicInteger featureNumber = new java.util.concurrent.atomic.AtomicInteger(0);

    private final int resourceNumber;

    private String timestamp = null;

    public Resource(String url, int resourceNumber) {
        this.url = url;
        this.resourceNumber = resourceNumber;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getScenarioName() {
        return this.scenarioName;
    }

    public void setScenarioName(String scenarioName) {
        this.scenarioName = scenarioName;
    }

    public Playwright getPlaywright() {
        return this.playwright;
    }

    public void setPlaywright(Playwright playwright) {
        this.playwright = playwright;
    }

    public Browser getBrowser() {
        return this.browser;
    }

    public void setBrowser(Browser browser) {
        this.browser = browser;
    }

    public BrowserContext getContext() {
        return this.context;
    }

    /**
     * Set the BrowserContext for this Resource. Should only be called by the owning thread or
     * ResourceManager when initializing per-thread context.
     */
    public synchronized void setContext(BrowserContext context) {
        this.context = context;
    }

    public Page getPage() {
        return this.page;
    }

    /**
     * Set the Page for this Resource. Should only be called by the owning thread.
     */
    public synchronized void setPage(Page page) {
        this.page = page;
    }

    public int getStep() {
        return this.step.get();
    }

    public void setStep(int step) {
        this.step.set(step);
    }

    public int getScenarioNumberOfFeatureFile() {
        return this.scenarioNumberOfFeatureFile.get();
    }

    public void setScenarioNumberOfFeatureFile(int scenarioNumberOfFeatureFile) {
        this.scenarioNumberOfFeatureFile.set(scenarioNumberOfFeatureFile);
    }

    public String getFeature() {
        return this.feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public int getFeatureNumber() {
        return this.featureNumber.get();
    }

    public void setFeatureNumber(int featureNumber) {
        this.featureNumber.set(featureNumber);
    }

    public void resetScenarioNumberOfFeatureFile() {
        this.scenarioNumberOfFeatureFile.set(0);
    }

    public void incrementScenarioNumberOfFeatureFile() {
        this.scenarioNumberOfFeatureFile.incrementAndGet();
    }

    public void incrementFeatureNumber() {
        this.featureNumber.incrementAndGet();
    }

    public void resetStep() {
        this.step.set(0);
    }

    public int getResourceNumber() {
        return this.resourceNumber;
    }

    /**
     * Cleanup browser context and page owned by this Resource. Safe to call from any thread but
     * intended to be called by the owning thread during teardown.
     */
    public synchronized void closeContext() {
        try {
            if (page != null) {
                try { page.close(); } catch (Exception ignore) {}
                page = null;
            }
        } finally {
            if (context != null) {
                try { context.close(); } catch (Exception ignore) {}
                context = null;
            }
        }
  
    public String getTimestamp() {
        return this.timestamp;
    }

    public void resetTimestamp() {
        this.timestamp = CommonUtils.getTimeStampName();

    }
}
