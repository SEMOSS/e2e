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
}