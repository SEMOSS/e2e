package aicore.pages;

import java.nio.file.Path;
import java.util.List;

import com.microsoft.playwright.Page;

import aicore.utils.AddDatabasePageUtils;

public class AddDatabasePage extends AbstractAddCatalogPageBase {

    public AddDatabasePage(Page page) {
        this.page = page;
    }

    public void clickOnAddDatabaseButton() {
        AddDatabasePageUtils.clickAddDatabaseButton(page);
    }

    public void selectDatabaseType(String dbType) {
        AddDatabasePageUtils.selectDatabaseType(page, dbType);
    }

    public void selectDatabaseFromConnectionTypes(String dbType) {
        AddDatabasePageUtils.selectDatabaseFromConnectionTypes(page, dbType);
    }

    public boolean verifyFieldUnderSection(String sectionName, String fieldName) {
        return AddDatabasePageUtils.verifyFieldUnderSection(page, sectionName, fieldName);
    }

    public boolean isDBFieldMandatory(String fieldName) {
        return AddDatabasePageUtils.isDBFieldMandatory(page, fieldName);
    }

    public void enterCatalogName(String catalogName) {
        AddDatabasePageUtils.enterCatalogName(page, catalogName);
    }

    public void enterHostName(String hostName) {
        AddDatabasePageUtils.enterHostName(page, hostName);
    }

    public void clearPortNumber() {
        AddDatabasePageUtils.clearPortNumber(page);
    }

    public void enterSchemaName(String schemaName) {
        AddDatabasePageUtils.enterSchemaName(page, schemaName);
    }

    public void addUserName(String userName) {
        AddDatabasePageUtils.enterUserName(page, userName);
    }

    public void addJDBCUrl(String jdbcUrl, String dbType) {
        AddDatabasePageUtils.enterJDBCUrl(page, jdbcUrl, dbType);
    }

    public void clickOnApplyButton() {
        AddDatabasePageUtils.clickOnApplyButton(page);
    }

    public void clickApplyDatabaseButton() {
        AddDatabasePageUtils.clickApplyDatabaseButton(page);
    }

    public void clickOnImportDatabaseButton() {
        AddDatabasePageUtils.clickOnImportDatabaseButton(page);
    }

    public boolean verifyDatabaseTitle(String dbName) {
        return AddDatabasePageUtils.verifyDatabaseTitle(page, dbName);
    }

    public void clickOnNextButtonForCreateDatabase() {
        AddDatabasePageUtils.clickOnNextButtonForCreateDatabase(page);
    }

    public String verifyDatabaseNameInCatalog(String dbName) {
        return AddDatabasePageUtils.verifyDatabaseNameInCatalog(page, dbName);
    }

    public void clickOnDatabaseNameInCatalog(String dbName) {
        AddDatabasePageUtils.clickOnDatabaseNameInCatalog(page, dbName);
    }

    public boolean verifyDatabaseIsVisibleInCatalog(String dbName) {
        return AddDatabasePageUtils.verifyDatabaseIsVisibleInCatalog(page, dbName);
    }

    public void clickOnCopyID(String dbName) {
        AddDatabasePageUtils.clickOnCopyID(page, dbName);
    }

    public String verifyCopyIdSuccessToastMessage(String toastMessage) {
        return AddDatabasePageUtils.verifyCopyIdSuccessToastMessage(page, toastMessage);
    }

    public void searchFilterValue(String filterValue) {
        AddDatabasePageUtils.searchFilterValue(page, filterValue);
    }

    public void selectFilterValue(String filterCategory, String filterValue) {
        AddDatabasePageUtils.selectFilterValue(page, filterCategory, filterValue);
    }

    public void verifyDatabaseName(String databaseName) {
        AddDatabasePageUtils.verifyDatabaseName(page, databaseName);
    }

    public void verifyDatabaseID() {
        AddDatabasePageUtils.verifyDatabaseID(page);
    }

    public void verifyDatabaseDescription(String databaseDescription) {
        AddDatabasePageUtils.verifyDatabaseDescription(page, databaseDescription);
    }

    public Path clickOnExportButton() throws Exception {
        return AddDatabasePageUtils.clickOnExportButton(page);
    }

    public void clickOnEditButton() {
        AddDatabasePageUtils.clickOnEditButton(page);
    }

    public void searchDatabaseCatalog(String catalogName) {
        AddDatabasePageUtils.searchDatabaseCatalog(page, catalogName);
    }

    public void selectDatabaseFromSearchOptions(String catalogName) {
        AddDatabasePageUtils.selectDatabaseFromSearchOptions(page, catalogName);
    }

    public void clickDatabase(String databaseName) {
        AddDatabasePageUtils.clickDatabase(page, databaseName);
    }

    public void clickOnMetaDataTab() {
        AddDatabasePageUtils.clickOnMetadataTab(page);
    }

    public void clickOnUsageTab() {
        AddDatabasePageUtils.clickOnMetadataTab(page);
    }

    public void verifyMetaData() {
        AddDatabasePageUtils.verifyMetaData(page);
    }

    public void clickOnRefreshButton() {
        AddDatabasePageUtils.clickOnRefreshButton(page);
    }

    public void selectDatabaseFromDropdown(String dbName) {
        AddDatabasePageUtils.selectDatabaseFromDropdown(page, dbName);
    }

    public void clickOnQueryTab() {
        AddDatabasePageUtils.clickOnQueryTab(page);
    }

    public void enterQuery(String query) {
        AddDatabasePageUtils.enterQuery(page, query);
    }

    public List<String> getQueryResponseTableHeader() {
        return AddDatabasePageUtils.getQueryResponseTableHeader(page);
    }

    public void verifyQueryFieldIsEmpty() {
        AddDatabasePageUtils.verifyQueryFieldIsEmpty(page);
    }

    public boolean verifyAllColumnsAreCollapsed() {
        return AddDatabasePageUtils.verifyAllColumnsAreCollapsed(page);
    }

    public boolean verifyButtonNameChanged(String buttonName) {
        return AddDatabasePageUtils.verifyButtonNameChanged(page, buttonName);
    }

    public List<String> getDataColumns() {
        return AddDatabasePageUtils.getDataColumns(page);
    }

    public void searchDataColumn(String columnName) {
        AddDatabasePageUtils.searchDataColumn(page, columnName);
    }

    public boolean verifySearchedDataColumn(String columnName) {
        return AddDatabasePageUtils.verifySearchedDataColumn(page, columnName);
    }

    public void clickOnRefreshButtonForDataColumns() {
        AddDatabasePageUtils.clickOnRefreshButtonForDataColumns(page);
    }

    public boolean verifyRefreshingTileForDataColumns(String text) {
        return AddDatabasePageUtils.verifyRefreshingTileForDataColumns(page, text);
    }

    public void clickOnExpandTableArrow(String name) {
        AddDatabasePageUtils.clickOnExpandTableArrow(page, name);
    }

    public boolean isSaveButtonDisabled() {
        return AddDatabasePageUtils.isSaveButtonDisabled(page);
    }

    public void clickOnSaveButtonOfMetadataTab() {
        AddDatabasePageUtils.clickOnSaveButtonOfMetadataTab(page);
    }

    public boolean verifyDatabaseCatalogPage() {
        return AddDatabasePageUtils.verifyDatabaseCatalogPage(page);
    }

}
