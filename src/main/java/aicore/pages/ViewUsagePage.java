package aicore.pages;

import com.microsoft.playwright.Page;

import aicore.utils.ViewUsagePageUtils;

public class ViewUsagePage {

    private Page page;

    public ViewUsagePage(Page page) {
        this.page = page;
    }

    public void clickOnUsageTab() {
        ViewUsagePageUtils.clickOnUsageTab(page);
    }

    public void verifyExample(String example) {
        ViewUsagePageUtils.verifyExample(page, example);
    }
}