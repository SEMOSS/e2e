package aicore.pages;

import com.microsoft.playwright.Page;
import aicore.utils.VerifyVectorDatabasePageUtils;

public class VerifyVectorDatabasePage {
    private Page page;

    public VerifyVectorDatabasePage(Page page) {
        this.page = page;
    }

    public void selectVectorDatabaseFromConnectionTypes(String dbType) {
        VerifyVectorDatabasePageUtils.selectVectorDatabaseFromConnectionTypes(page, dbType);
    }

    public boolean verifyFieldUnderSection(String sectionName, String fieldName) {
        return VerifyVectorDatabasePageUtils.verifyFieldUnderSection(page, sectionName, fieldName);
    }

    public boolean isVectorDatabaseFieldMandatory(String fieldName) {
        return VerifyVectorDatabasePageUtils.isVectorDatabaseFieldMandatory(page, fieldName);
    }
}
