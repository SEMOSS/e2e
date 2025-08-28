package aicore.framework;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class Resource {

    private String url;

    private Page page;
    private BrowserContext context;
    private Browser browser;
    private Playwright playwright;
    private String scenarioName;

    private int step = 0;
    private int scenarioNumberOfFeatureFile = 0;
    private String feature = "";
    private int featureNumber = 0;

    private int resourceNumber;

    public Resource(String url, int resourceNumber) {
        this.url = url;
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

    public void setContext(BrowserContext context) {
        this.context = context;
    }

    public Page getPage() {
        return this.page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public int getStep() {
        return this.step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int getScenarioNumberOfFeatureFile() {
        return this.scenarioNumberOfFeatureFile;
    }

    public void setScenarioNumberOfFeatureFile(int scenarioNumberOfFeatureFile) {
        this.scenarioNumberOfFeatureFile = scenarioNumberOfFeatureFile;
    }

    public String getFeature() {
        return this.feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public int getFeatureNumber() {
        return this.featureNumber;
    }

    public void setFeatureNumber(int featureNumber) {
        this.featureNumber = featureNumber;
    }

    public void resetScenarioNumberOfFeatureFile() {
        this.scenarioNumberOfFeatureFile = 0;
    }

    public void incrementScenarioNumberOfFeatureFile() {
        this.scenarioNumberOfFeatureFile++;
    }

    public void incrementFeatureNumber() {
        this.featureNumber++;
    }

    public void resetStep() {
        this.step = 0;
    }

    public int getResourceNumber() {
        return resourceNumber;
    }
}
