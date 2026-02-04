package aicore.pages;

import com.microsoft.playwright.Page;

import aicore.utils.AddDatabaseFileUploadUtils;

public class AddDatabaseFileUploadPage {
    private Page page;

public AddDatabaseFileUploadPage(Page page) {
		this.page = page;
	}


    public void selectTab(String tabName) {
        AddDatabaseFileUploadUtils.selectTab(page, tabName);
    }

    public void selectFileType(String fileType) {
        AddDatabaseFileUploadUtils.selectFileType(page, fileType);
    }

    public void selectDatabaseType(String dbType) {
        AddDatabaseFileUploadUtils.selectDatabaseType(page, dbType);
    }

    public void selectMetamodelType(String metaModelType) {
        AddDatabaseFileUploadUtils.selectMetamodelType(page, metaModelType);
    }

    public void enterDatabaseName(String dbName) {
        AddDatabaseFileUploadUtils.enterDatabaseName(page, dbName);
    }

    public void checkColumnsAreEditable() {
         AddDatabaseFileUploadUtils.checkColumnsAreEditable(page);
    }

    public boolean verifyTableName(String tableName) {
        return AddDatabaseFileUploadUtils.verifyTableName(page, tableName);
    }
    
    public void verifyFullScreenBtn() {
        AddDatabaseFileUploadUtils.verifyFullScreenBtn(page);
    }

    public void verifySelectTableBtn() {
        AddDatabaseFileUploadUtils.verifySelectTableBtn(page);
    }

    public void verifyResetbtn() {
        AddDatabaseFileUploadUtils.verifyResetbtn(page);
    }

    public void verifyCreateRealtionshipBtn(String parentTable, String childTable) {
        AddDatabaseFileUploadUtils.verifyCreateRealtionshipBtn(page, parentTable, childTable);
    }

    public void verifySaveBtn() {
        AddDatabaseFileUploadUtils.verifySaveBtn(page);
    }

    public void verifyCancelBtn() {
        AddDatabaseFileUploadUtils.verifyCancelBtn(page);
    }
}