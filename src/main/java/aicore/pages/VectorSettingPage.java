package aicore.pages;

import java.util.Vector;

import com.microsoft.playwright.Page;

import aicore.utils.VectorSettingPageUtils;

public class VectorSettingPage {
    private Page page;

    public VectorSettingPage(Page page) {
        this.page = page;
    }

    public void verifyVectorSettingTitle(String title) {
       VectorSettingPageUtils.verifyVectorSettingTitle(page, title);
    }

    public void verifyVectorCards() {
        VectorSettingPageUtils.verifyVectorCards(page);
    }

    public void isSearchBarPresent() {
     VectorSettingPageUtils.isSearchBarPresent(page);
    }
    
    public void searchForVector(String vectorName) {
        VectorSettingPageUtils.searchForVector(page, vectorName);
    }
    public void verifySearchedVector(String vectorName) {
        VectorSettingPageUtils.verifySearchedVector(page, vectorName);
    }

    public void clickOnVectorCardByName(String catalogName) {
        VectorSettingPageUtils.clickOnVectorCardByName(page, catalogName);
    }
}
