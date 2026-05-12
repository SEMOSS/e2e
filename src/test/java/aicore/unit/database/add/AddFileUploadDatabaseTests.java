package aicore.unit.database.add;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;

import aicore.pages.database.AddDatabaseFormUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.utils.AICorePageUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.AddDatabaseFileUploadUtils;
import aicore.utils.AddDatabasePageUtils;
import aicore.utils.CatalogCreationFromZipUtil;
import aicore.utils.CatlogAccessPageUtility;
import aicore.utils.CommonUtils;
import aicore.utils.DatabaseTestUtils;
import aicore.utils.TestResourceTrackerHelper;
import aicore.utils.TestResources;
import aicore.utils.annotations.PWPage;

public class AddFileUploadDatabaseTests extends AbstractPlaywrightTestBase {

	@BeforeEach
	void setup(@PWPage Page page) {
		loginNativeAdmin(page);
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenDatabase(page);

		// testing add file upload db
		AddDatabaseFormUtils.clickAddDatabaseButton(page);
		String tabName = "file uploads";
		AddDatabaseFileUploadUtils.selectTab(page, tabName);
	}	
	
	@AfterEach
	void tearDown(@PWPage Page page) {
		logout(page);
	}
	////////////////// UPLOAD as flat table

	@Test
	public void testAddExcelFlatTable(@PWPage Page page) throws IOException {
		String timestamp = CommonUtils.getTimeStampName();
		String fileType = "Excel";
		String dbName = "Excel db" + timestamp;
		String dbType = "h2";
		String metaModelType = "asFlatTable";
		String fileName = "Database/Database.xlsx";

		// db options
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

		// delete db
		CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_DATABASE, dbID);
	}

	@Test
	public void testAddCSVFlatTable(@PWPage Page page) throws IOException {
		String timestamp = CommonUtils.getTimeStampName();
		String dbName = "CSV db" + timestamp;
		String fileName = TestResources.DIABETES_CSV;
		String dbType = "h2";
		String metaModelType = "asFlatTable";
		String dbID = DatabaseTestUtils.addFlatCsv(page, dbName, fileName, dbType, metaModelType);

		// delete db
		CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_DATABASE, dbID);
	}

	@Test
	public void testAddTSVFlatTable(@PWPage Page page) throws IOException {
		String timestamp = CommonUtils.getTimeStampName();
		String fileType = "TSV";
		String dbName = "TSV db" + timestamp;
		String dbType = "rdf";
		String metaModelType = "asFlatTable";
		String fileName = "Database/Employee.tsv";

		// db options
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

		// delete db
		CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_DATABASE, dbID);
	}

	//////////////// METAMODEL FROM SCRATCH
// TODO verify this metamodel option is still valid
//	@Test
//	public void testAddCSVFromScratch(@PWPage Page page) throws IOException {
//		String timestamp = CommonUtils.getTimeStampName();
//		String fileType = "CSV";
//		String dbName = "CSV db" + timestamp;
//		String dbType = "rdf";
//		String metaModelType = "fromScratch";
//		String fileName = "Database/diabetes.csv";
//
//		// db options
//		AddDatabaseFileUploadUtils.selectFileType(page, fileType);
//		AddDatabaseFileUploadUtils.enterDatabaseName(page, dbName);
//		AddDatabaseFileUploadUtils.selectDatabaseType(page, dbType);
//		AddDatabaseFileUploadUtils.selectMetamodelType(page, metaModelType);
//		CatalogCreationFromZipUtil.uploadFile(page, fileName);
//		AddDatabaseFormUtils.clickOnConnectButton(page);
//
//		// validate the db created
//		AddDatabaseFileUploadUtils.checkColumnsAreEditable(page);
//		AICorePageUtils.clickOnButton(page, "Import");
//		boolean isTitleVisible = AddDatabasePageUtils.verifyDatabaseTitle(page, dbName);
//		Assertions.assertTrue(isTitleVisible, "Database title is not visible");
//		String dbID = CatlogAccessPageUtility.getCatalogAndCopyId(page);
//
//		// delete db
//		CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_DATABASE, dbID);
//	}
//
//	@Test
//	public void testAddTSVFromScratch(@PWPage Page page) throws IOException {
//		String timestamp = CommonUtils.getTimeStampName();
//		String fileType = "TSV";
//		String dbName = "TSV db" + timestamp;
//		String dbType = "rdf";
//		String metaModelType = "fromScratch";
//		String fileName = "Database/Employee.tsv";
//
//		// db options
//		AddDatabaseFileUploadUtils.selectFileType(page, fileType);
//		AddDatabaseFileUploadUtils.enterDatabaseName(page, dbName);
//		AddDatabaseFileUploadUtils.selectDatabaseType(page, dbType);
//		AddDatabaseFileUploadUtils.selectMetamodelType(page, metaModelType);
//		CatalogCreationFromZipUtil.uploadFile(page, fileName);
//		AddDatabaseFormUtils.clickOnConnectButton(page);
//
//		// validate the db created
//		AddDatabaseFileUploadUtils.checkColumnsAreEditable(page);
//		AICorePageUtils.clickOnButton(page, "Import");
//		boolean isTitleVisible = AddDatabasePageUtils.verifyDatabaseTitle(page, dbName);
//		Assertions.assertTrue(isTitleVisible, "Database title is not visible");
//		String dbID = CatlogAccessPageUtility.getCatalogAndCopyId(page);
//
//		// delete db
//		CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_DATABASE, dbID);
//	}

	//////////////// SUGGESTED METAMODEL
	@Test
	public void testAddTSVSuggestedMetamodel(@PWPage Page page) throws IOException {
		String timestamp = CommonUtils.getTimeStampName();
		String fileType = "TSV";
		String dbName = "TSV db" + timestamp;
		String dbType = "rdf";//"h2";
		String metaModelType = "asSuggestedMetaModel";
		String fileName = "Database/Employee.tsv";

		// db options
		AddDatabaseFileUploadUtils.selectFileType(page, fileType);
		AddDatabaseFileUploadUtils.enterDatabaseName(page, dbName);
		AddDatabaseFileUploadUtils.selectDatabaseType(page, dbType);
		AddDatabaseFileUploadUtils.selectMetamodelType(page, metaModelType);
		CatalogCreationFromZipUtil.uploadFile(page, fileName);
		AddDatabaseFormUtils.clickOnConnectButton(page);
		// additional suggested metamodel steps
		AddDatabaseFileUploadUtils.checkColumnsAreEditable(page);
		AddDatabasePageUtils.clickOnSaveButton(page);

		// validate the db created
		boolean isTitleVisible = AddDatabasePageUtils.verifyDatabaseTitle(page, dbName);
		Assertions.assertTrue(isTitleVisible, "Database title is not visible");
		String dbID = CatlogAccessPageUtility.getCatalogAndCopyId(page);

		// delete db
		CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_DATABASE, dbID);
	}

	@Test
	public void testAddCSVSuggestedMetamodel(@PWPage Page page) throws IOException {
		String timestamp = CommonUtils.getTimeStampName();
		String fileType = "CSV";
		String dbName = "CSV db" + timestamp;
		String dbType = "rdf";//"h2";
		String metaModelType = "asSuggestedMetaModel";
		String fileName = "Database/diabetes.csv";

		// db options
		AddDatabaseFileUploadUtils.selectFileType(page, fileType);
		AddDatabaseFileUploadUtils.enterDatabaseName(page, dbName);
		AddDatabaseFileUploadUtils.selectDatabaseType(page, dbType);
		AddDatabaseFileUploadUtils.selectMetamodelType(page, metaModelType);
		CatalogCreationFromZipUtil.uploadFile(page, fileName);
		AddDatabaseFormUtils.clickOnConnectButton(page);
		// additional suggested metamodel steps
		// TODO need to have better validation on the column types and names in this
		// step
		AddDatabaseFileUploadUtils.checkColumnsAreEditable(page);
		AddDatabasePageUtils.clickOnSaveButton(page);

		// validate the db created
		boolean isTitleVisible = AddDatabasePageUtils.verifyDatabaseTitle(page, dbName);
		Assertions.assertTrue(isTitleVisible, "Database title is not visible");
		String dbID = CatlogAccessPageUtility.getCatalogAndCopyId(page);

		// delete db
		CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_DATABASE, dbID);
	}

	@Test
	public void testAddTSVSuggestedMetamodelEditRelationship(@PWPage Page page) throws IOException {
		String timestamp = CommonUtils.getTimeStampName();
		String fileType = "TSV";
		String dbName = "TSV db" + timestamp;
		String dbType = "rdf";
		String metaModelType = "asSuggestedMetaModel";
		String fileName = "Database/Employee.tsv";

		// db options
		AddDatabaseFileUploadUtils.selectFileType(page, fileType);
		AddDatabaseFileUploadUtils.enterDatabaseName(page, dbName);
		AddDatabaseFileUploadUtils.selectDatabaseType(page, dbType);
		AddDatabaseFileUploadUtils.selectMetamodelType(page, metaModelType);
		CatalogCreationFromZipUtil.uploadFile(page, fileName);
		AddDatabaseFormUtils.clickOnConnectButton(page);

		String tableName = "Employee";
		// additional suggested metamodel steps
		AddDatabaseFileUploadUtils.verifyTableName(page, tableName);
		AddDatabaseFileUploadUtils.verifyFullScreenBtn(page);
		AddDatabaseFileUploadUtils.verifySelectTableBtn(page);
		AddDatabaseFileUploadUtils.verifyResetBtn(page);
		String parentTable = "JOB ID";
		String childTable = "EMPLOYEE ID";
		AddDatabaseFileUploadUtils.verifyCreateRealtionshipBtn(page, parentTable, childTable);
		AddDatabaseFileUploadUtils.verifyAddBtnForCreateConnection(page);
		AddDatabaseFileUploadUtils.verifySaveBtnForCreateRelationship(page);
		AddDatabaseFileUploadUtils.verifyCancelBtn(page);
		AddDatabaseFileUploadUtils.verifySaveBtn(page);
		AddDatabasePageUtils.clickOnSaveButton(page);

		// validate the db created
		boolean isTitleVisible = AddDatabasePageUtils.verifyDatabaseTitle(page, dbName);
		Assertions.assertTrue(isTitleVisible, "Database title is not visible");
		String dbID = CatlogAccessPageUtility.getCatalogAndCopyId(page);

		// delete db
		CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_DATABASE, dbID);
	}

	/////////////////// Upload multiple files
	/// TODO verify this metamodel type is still valid
//	@Test
//	public void testAddMultiCSVFromScratch(@PWPage Page page) throws IOException {
//		String timestamp = CommonUtils.getTimeStampName();
//		String fileType = "CSV";
//		String dbName = "CSV db" + timestamp;
//		String dbType = "rdf";
//		String metaModelType = "fromScratch";
//		String fileName = "Database/diabetes.csv";
//		String fileName2 = "Database/diabetes2.csv";
//
//		// db options
//		AddDatabaseFileUploadUtils.selectFileType(page, fileType);
//		AddDatabaseFileUploadUtils.enterDatabaseName(page, dbName);
//		AddDatabaseFileUploadUtils.selectDatabaseType(page, dbType);
//		AddDatabaseFileUploadUtils.selectMetamodelType(page, metaModelType);
//		CatalogCreationFromZipUtil.uploadFile(page, fileName);
//		CatalogCreationFromZipUtil.uploadFile(page, fileName2);
//		AddDatabaseFormUtils.clickOnConnectButton(page);
//
//		// metamodel steps
//		// TODO need to have better validation on the column types and names in this
//		// step
//		AddDatabaseFileUploadUtils.checkColumnsAreEditable(page);
//		Assertions.assertTrue(AddDatabaseFileUploadUtils.verifyTableName(page, "diabetes"),
//				"diabates table was not added");
//		Assertions.assertTrue(AddDatabaseFileUploadUtils.verifyTableName(page, "diabetes2"),
//				"diabates2 table was not added");
//
//		AICorePageUtils.clickOnButton(page, "Import");
//
//		// validate the db created
//		boolean isTitleVisible = AddDatabasePageUtils.verifyDatabaseTitle(page, dbName);
//		Assertions.assertTrue(isTitleVisible, "Database title is not visible");
//		String dbID = CatlogAccessPageUtility.getCatalogAndCopyId(page);
//
//		// delete db
//		CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_DATABASE, dbID);
//	}
//
//	@Test
//	public void testAddMultiTSVFromScratch(@PWPage Page page) throws IOException {
//		String timestamp = CommonUtils.getTimeStampName();
//		String fileType = "TSV";
//		String dbName = "TSV db" + timestamp;
//		String dbType = "rdf";
//		String metaModelType = "fromScratch";
//		String fileName = "Database/Employee.tsv";
//		String fileName2 = "Database/Employee2.tsv";
//
//		// db options
//		AddDatabaseFileUploadUtils.selectFileType(page, fileType);
//		AddDatabaseFileUploadUtils.enterDatabaseName(page, dbName);
//		AddDatabaseFileUploadUtils.selectDatabaseType(page, dbType);
//		AddDatabaseFileUploadUtils.selectMetamodelType(page, metaModelType);
//		CatalogCreationFromZipUtil.uploadFile(page, fileName);
//		CatalogCreationFromZipUtil.uploadFile(page, fileName2);
//		AddDatabaseFormUtils.clickOnConnectButton(page);
//
//		// metamodel steps
//		// TODO need to have better validation on the column types and names in this
//		// step
//		AddDatabaseFileUploadUtils.checkColumnsAreEditable(page);
//		AICorePageUtils.clickOnButton(page, "Import");
//
//		// validate the db created
//		boolean isTitleVisible = AddDatabasePageUtils.verifyDatabaseTitle(page, dbName);
//		Assertions.assertTrue(isTitleVisible, "Database title is not visible");
//		String dbID = CatlogAccessPageUtility.getCatalogAndCopyId(page);
//
//		// delete db
//		CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_DATABASE, dbID);
//	}

}
