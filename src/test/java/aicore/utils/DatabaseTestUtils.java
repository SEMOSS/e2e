package aicore.utils;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.io.FilenameUtils;
import org.junit.jupiter.api.Assertions;

import com.microsoft.playwright.Page;

import aicore.hooks.SetupHooks;
import aicore.pages.database.AddDatabaseFormUtils;
import aicore.pages.home.HomePageUtils;
import aicore.pages.home.MainMenuUtils;

public class DatabaseTestUtils {

	public static String uploadDatabaseZip(Page page, String dbName, String fileName) {
		// delete db before
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenDatabase(page);
		AddFunctionPageUtils.deleteCatalog(page, "Database", dbName);

		// click on add db
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenDatabase(page);
		AddDatabaseFormUtils.clickAddDatabaseButton(page);
		assertTrue(AddCatalogPageBaseUtils.isSearchBarPresent(page));

		CatalogCreationFromZipUtil.clickOnFileUploadIcon(page);
		CatalogCreationFromZipUtil.uploadFile(page, fileName);
		CatalogCreationFromZipUtil.clickOnUploadButton(page, "Upload");

		return getDatabaseID(page, dbName);
	}
	
	public static String addFlatCsv(Page page, String dbName, String fileName, String dbType, String metaModelType) {
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenDatabase(page);
		AddDatabaseFormUtils.clickAddDatabaseButton(page);
		String tabName = "file uploads";
		AddDatabaseFileUploadUtils.selectTab(page, tabName);
		
		String fileType = FilenameUtils.getExtension(fileName).toUpperCase();
		AddDatabaseFileUploadUtils.selectFileType(page, fileType);
		AddDatabaseFileUploadUtils.enterDatabaseName(page, dbName);
		AddDatabaseFileUploadUtils.selectDatabaseType(page, dbType);
		AddDatabaseFileUploadUtils.selectMetamodelType(page, metaModelType);
		CatalogCreationFromZipUtil.uploadFile(page, fileName);
		AddDatabaseFormUtils.clickOnConnectButton(page);

		// validate the db created
		AddDatabaseFileUploadUtils.checkColumnsAreEditable(page);
		AICorePageUtils.clickOnButton(page, "Import");
		boolean isTitleVisible = AddDatabasePageUtils.verifyDatabaseTitle(page, dbName);
		Assertions.assertTrue(isTitleVisible, "Database title is not visible");
		String dbID = CatlogAccessPageUtility.getCatalogAndCopyId(page);
		return dbID;
	}
	
	public static String getDatabaseID(Page page, String dbName) {
		HomePageUtils.navigateToHomePage(page);
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenDatabase(page);
		AddDatabasePageUtils.searchDatabaseCatalog(page, dbName);
		AddDatabasePageUtils.clickOnDatabaseNameInCatalog(page, dbName);
		boolean isTitleVisible = AddDatabasePageUtils.verifyDatabaseTitle(page, dbName);
		Assertions.assertTrue(isTitleVisible, "Database title is not visible");
		String dbID = CatlogAccessPageUtility.getCatalogAndCopyId(page);
		return dbID;
	}

}
