package aicore.steps;

import org.junit.jupiter.api.Assertions;

import aicore.hooks.SetupHooks;
import aicore.pages.AddDatabasePage;
import aicore.pages.HomePage;
import aicore.pages.ViewUsagePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddDatabaseSteps {
    private AddDatabasePage addDatabaseToCatalogPage;
    private HomePage homePage;
    private ViewUsagePage viewUsagePage;

    public AddDatabaseSteps() {
        homePage = new HomePage(SetupHooks.getPage());
        addDatabaseToCatalogPage = new AddDatabasePage(SetupHooks.getPage());
        viewUsagePage = new ViewUsagePage(SetupHooks.getPage());
    }

    @Given("User clicks on Open Database")
    public void user_clicks_to_open_database() {
        homePage.clickOnOpenDatabase();
    }

    @When("User clicks on Add Database")
    public void user_clicks_on_add_database() {
        addDatabaseToCatalogPage.clickOnAddDatabaseButton();
    }

    @Then("User selects database {string}")
    public void user_selects_database(String dbType) {
        addDatabaseToCatalogPage.selectDatabaseType(dbType);
    }

    @And("User uploads database file {string}")
    public void user_uploads_database_file(String fileName) {
        String uploadedFileName = addDatabaseToCatalogPage.uploadDatabaseFile(fileName);
        if (fileName.contains("/")) {
            String[] ActualFileName = fileName.split("/");
            int fileNameIndex = ActualFileName.length - 1;
            Assertions.assertEquals(ActualFileName[fileNameIndex], uploadedFileName,
                    "Database Document file is not uploaded successfully");
        } else {
            Assertions.assertEquals(fileName, uploadedFileName, "Database Document file is not uploaded successfully");
        }

    }

    @And("User clicks on Create Database button")
    public void user_clicks_on_create_database_button() {
        addDatabaseToCatalogPage.clickOnCreateDatabaseButton();
    }

    @And("User sees the database name {string} in the database catalog")
    public void user_sees_the_database_name_in_the_database_catalog(String dbName) {
        String databaseNameInCatalog = addDatabaseToCatalogPage.verifyDatabaseNameInCatalog(dbName);
        Assertions.assertEquals(dbName, databaseNameInCatalog,
                "Database name is not visible in the database catalog");
    }

    @And("User clicks on the database name {string} in the database catalog")
    public void user_clicks_the_database_name_in_the_database_catalog(String dbName) {
        addDatabaseToCatalogPage.clickOnDatabaseNameInCatalog(dbName);
    }

    @When("User clicks on Usage tab for Database")
    public void user_clicks_on_usage_tab_for_database() {
        viewUsagePage.clickOnUsageTab();
    }

    @Then("User sees an example of {string} with example code for Database")
    public void user_sees_an_example_of_with_example_code_for_database(String usageType) {
        viewUsagePage.verifyExample(usageType);
    }

    @When("User clicks on MetaData tab")
    public void user_clicks_on_metadata_tab() {
        addDatabaseToCatalogPage.clickOnMetaDataTab();
    }

    @Then("User sees the table in the metadata tab")
    public void user_sees_the_table_in_the_metadata_tab() {
        addDatabaseToCatalogPage.verifyMetaData();
    }
}