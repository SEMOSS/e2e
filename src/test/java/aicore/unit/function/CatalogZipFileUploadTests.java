package aicore.unit.function;

import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import aicore.pages.home.MainMenuUtils;
import aicore.utils.AbstractE2ETest;
import aicore.utils.AddFunctionPageUtils;
import aicore.utils.CatalogCreationFromZipUtil;
import aicore.utils.CatlogAccessPageUtility;
import aicore.utils.CommonUtils;
import aicore.utils.FunctionTestUtils;
import aicore.utils.TestResourceTrackerHelper;
import aicore.utils.TestResources;
import aicore.utils.TestTags;
import aicore.utils.UploadCatalogUtils;
import aicore.utils.AbstractE2ETest.UserType;


/**
 * Validate zip catalog upload functionality in Files Section for all catalog types
 */
public class CatalogZipFileUploadTests extends AbstractE2ETest {
	
	@BeforeAll
	static void setup() {
		login(page, UserType.NATIVE);
	}
	
	private static final Logger logger = LogManager.getLogger(CatalogZipFileUploadTests.class);

	private static Stream<Arguments> provideInputsForAllCatalogTypes() {
	    return Stream.of(
	    		Arguments.of(TestResourceTrackerHelper.CATALOG_TYPE_MODEL, TestResources.LLAMA3_70B_INSTRUCT_ZIP, TestResources.LLAMA3_70B_INSTRUCT_NAME),
	    		Arguments.of(TestResourceTrackerHelper.CATALOG_TYPE_DATABASE, TestResources.TEST_DATABASE_ZIP, TestResources.TEST_DATABASE_NAME),
	    		Arguments.of(TestResourceTrackerHelper.CATALOG_TYPE_FUNCTION, TestResources.WEATHER_FUNC_ZIP, TestResources.WEATHER_FUNC_NAME),
	    		Arguments.of(TestResourceTrackerHelper.CATALOG_TYPE_STORAGE, TestResources.LOCAL_MINIO_ZIP, TestResources.LOCAL_MINIO_NAME),
	    		Arguments.of(TestResourceTrackerHelper.CATALOG_TYPE_VECTOR, TestResources.TEST_VECTOR_ZIP, TestResources.TEST_VECTOR_NAME),
	    		Arguments.of(TestResourceTrackerHelper.CATALOG_TYPE_GUARDRAIL, TestResources.GLINER_ZIP, TestResources.GLINER_NAME)
	    		);
	}
	
	/// TODO: THIS TEST IS FAILING BOTH HERE AND IN THE CUCUMBER FEATURE FILE. IT NEEDS TO BE FIXED AND THE CUCUMBER FILE
	/// DELETED
	@ParameterizedTest
	@MethodSource("provideInputsForAllCatalogTypes")
	@Tag(TestTags.BROKEN)
	void testValidateZipUploadFunctionalityForAllCatalogTypes(String catalog, String fileName, String catalogName) {
		MainMenuUtils.openMainMenu(page);
		CatalogCreationFromZipUtil.openCatalog(page, catalog);
		AddFunctionPageUtils.deleteCatalog(page, catalog, catalogName);
		CatalogCreationFromZipUtil.clickOnAddCatalogButton(page, catalog);		
		CatalogCreationFromZipUtil.clickOnFileUploadIcon(page);
		FunctionTestUtils.userUploadsFile(page, fileName);
		CatalogCreationFromZipUtil.clickOnUploadButton(page, "Upload");
		CatlogAccessPageUtility.getCatalogAndCopyId(page);
		AddFunctionPageUtils.clickOnFileTab(page);
		UploadCatalogUtils.clickOnCreateAtIconOnFileSection(page);
		UploadCatalogUtils.selectAction(page, "Upload Files");
		FunctionTestUtils.userUploadsFile(page, "ModelZIP.zip");
		UploadCatalogUtils.clickOnUploadButtonToCreateCodeApp(page, "Upload");
		FunctionTestUtils.verifyUserCanSeeFolder(page, "ModelZIP.zip");
		UploadCatalogUtils.clickOnCreateAtIconOnFileSection(page);
		UploadCatalogUtils.selectAction(page, "New Directory");
		UploadCatalogUtils.enterFolderName(page, "TestFolder");
		UploadCatalogUtils.clickOnCreateButton(page);
		FunctionTestUtils.verifyUserCanSeeFolder(page, "TestFolder");
		CatalogCreationFromZipUtil.userClicksOnItemInFilesList(page, "TestFolder");
		UploadCatalogUtils.clickOnCreateAtIconOnFileSection(page);
		UploadCatalogUtils.selectAction(page, "New Directory");
		UploadCatalogUtils.enterFolderName(page, "SubFolder");
		UploadCatalogUtils.clickOnCreateButton(page);
		UploadCatalogUtils.userCanSeeFolderUnderParentFolder(page, "SubFolder", "TestFolder");
		UploadCatalogUtils.clickOnCreateAtIconOnFileSection(page);
		UploadCatalogUtils.selectAction(page, "New File");
		UploadCatalogUtils.enterFileName(page, "SubFile");
		UploadCatalogUtils.clickOnCreateButton(page);
		UploadCatalogUtils.userCanSeeFileUnderParentFolder(page, "SubFile", "TestFolder");
		UploadCatalogUtils.clickOnCreateAtIconOnFileSection(page);
		UploadCatalogUtils.selectAction(page, "New File");
		UploadCatalogUtils.enterFileName(page, "TestFile");
		UploadCatalogUtils.clickOnCreateButton(page);
		FunctionTestUtils.verifyUserCanSeeFolder(page, "TestFile");
		CatalogCreationFromZipUtil.userClicksOnItemInFilesList(page, "TestFile");
		UploadCatalogUtils.userEditFileWithSomeContentAs(page, "dummydata");
		CommonUtils.navigateAndDeleteCatalog(page, catalog, catalogName);
	}
}
