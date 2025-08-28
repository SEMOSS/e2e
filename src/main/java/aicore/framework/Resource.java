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

    public Resource(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getScenarioName() {
        return scenarioName;
    }

    public void setScenarioName(String scenarioName) {
        this.scenarioName = scenarioName;
    }

    public Playwright getPlaywright() {
        return playwright;
    }

    public void setPlaywright(Playwright playwright) {
        this.playwright = playwright;
    }

    public Browser getBrowser() {
        return browser;
    }

    public void setBrowser(Browser browser) {
        this.browser = browser;
    }

    public BrowserContext getContext() {
        return context;
    }

    public void setContext(BrowserContext context) {
        this.context = context;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        step = step;
    }

    public int getScenarioNumberOfFeatureFile() {
        return scenarioNumberOfFeatureFile;
    }

    public void setScenarioNumberOfFeatureFile(int scenarioNumberOfFeatureFile) {
        scenarioNumberOfFeatureFile = scenarioNumberOfFeatureFile;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        feature = feature;
    }

    public int getFeatureNumber() {
        return featureNumber;
    }

    public void setFeatureNumber(int featureNumber) {
        featureNumber = featureNumber;
    }

    public void resetScenarioNumberOfFeatureFile() {
        scenarioNumberOfFeatureFile = 0;
    }

    public void incrementScenarioNumberOfFeatureFile() {
        scenarioNumberOfFeatureFile++;
    }

    public void incrementFeatureNumber() {
        featureNumber++;
    }

    public void resetStep() {
        step = 0;
    }
}
