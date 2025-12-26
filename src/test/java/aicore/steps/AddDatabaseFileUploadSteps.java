package aicore.steps;

import aicore.hooks.SetupHooks;
import aicore.pages.AddDatabaseFileUploadPage;
import io.cucumber.java.en.And;

public class AddDatabaseFileUploadSteps {
 
	private AddDatabaseFileUploadPage addDatabaseCSVFileUploadPage;
    private String timestamp;

	public AddDatabaseFileUploadSteps() {
        addDatabaseCSVFileUploadPage = new AddDatabaseFileUploadPage(SetupHooks.getPage());
        timestamp = SetupHooks.getTimestamp();
	}
    
    @And("User clicks on the {string} tab from options")
    public void user_clicks_on_the_tab_from_options(String tabName) {
       addDatabaseCSVFileUploadPage.selectTab(tabName);
    }

    @And("User selects {string} file type")
    public void user_selects_file_type(String fileType) {
       addDatabaseCSVFileUploadPage.selectFileType(fileType);
    }

    @And("User enters {string} as Database Name")
    public void user_enters_database_name(String dbName) {
       addDatabaseCSVFileUploadPage.enterDatabaseName(dbName+"_"+timestamp);
    }

    @And("User selects {string} as database type")
    public void user_selects_database_type(String dbType) {
       addDatabaseCSVFileUploadPage.selectDatabaseType(dbType);
    }

    @And("User selects {string} as Metamodel type")
    public void user_selects_metamodel_type(String metaModelType) {
       addDatabaseCSVFileUploadPage.selectMetamodelType(metaModelType);
    }

    @And("User sees the colunm names with edit button and delete button")
    public void user_checks_the_columns_are_editable() {
       addDatabaseCSVFileUploadPage.checkColumnsAreEditable();
    }
}