package aicore.unit.function;

import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import aicore.utils.page.app.CodeAppPageUtils;


/**
 * Validate zip catalog upload functionality in Files Section for all catalog types
 */
public class CatalogZipFileUploadTests extends AbstractE2ETest {
	
	private static final Logger logger = LogManager.getLogger(CatalogZipFileUploadTests.class);

	private static Stream<Arguments> provideInputsForAllCatalogTypes() {
	    return Stream.of(
	    		Arguments.of(TestResourceTrackerHelper.CATALOG_TYPE_MODEL, "Model/Llama3-70B-Instruct.zip", "Llama3-70B-Instruct"),
	    		Arguments.of(TestResourceTrackerHelper.CATALOG_TYPE_DATABASE, "Database/TestDatabase.zip", "TestDatabase"),
	    		Arguments.of(TestResourceTrackerHelper.CATALOG_TYPE_FUNCTION, "Function/weatherFunctionTest.zip", "WeatherFunctionTest"),
	    		Arguments.of(TestResourceTrackerHelper.CATALOG_TYPE_STORAGE, "Storage/Localminio.zip", "localminio"),
	    		Arguments.of(TestResourceTrackerHelper.CATALOG_TYPE_VECTOR, "VectorDatabase/TestVector.zip", "TestVector"),
	    		Arguments.of(TestResourceTrackerHelper.CATALOG_TYPE_GUARDRAIL, "Guardrail/Gliner.zip", "Gliner")
	    		);
	}
	
	/// TODO: THIS TEST IS FAILING BOTH HERE AND IN THE CUCUMBER FEATURE FILE. IT NEEDS TO BE FIXED AND THE CUCUMBER FILE
	/// DELETED
	@ParameterizedTest
	@MethodSource("provideInputsForAllCatalogTypes")
	void testValidateZipUploadFunctionalityForAllCatalogTypes(String catalog, String fileName, String catalogName) {
		login(page, UserType.NATIVE); // TODO find a way to only login once at the beginning of the set of tests
		MainMenuUtils.openMainMenu(page);
		CatalogCreationFromZipUtil.openCatalog(page, catalog);
		AddFunctionPageUtils.deleteCatalog(page, catalog, catalogName);
		CatalogCreationFromZipUtil.clickOnAddCatalogButton(page, catalog);		
		CatalogCreationFromZipUtil.clickOnFileUploadIcon(page);
		FunctionTestUtils.userUploadsFile(page, fileName);
		CatalogCreationFromZipUtil.clickOnUploadButton(page, "Upload");
		CatlogAccessPageUtility.getCatalogAndCopyId(page);
		AddFunctionPageUtils.clickOnFileTab(page);
		CodeAppPageUtils.clickOnCreateAtIconOnFileSection(page);
		CodeAppPageUtils.selectAction(page, "Upload Files");
		FunctionTestUtils.userUploadsFile(page, "ModelZIP.zip");
		CodeAppPageUtils.clickOnUploadButtonToCreateCodeApp(page, "Upload");
		FunctionTestUtils.verifyUserCanSeeFolder(page, "ModelZIP.zip");
		CodeAppPageUtils.clickOnCreateAtIconOnFileSection(page);
		CodeAppPageUtils.selectAction(page, "New Directory");
		CodeAppPageUtils.enterFolderName(page, "TestFolder");
		CodeAppPageUtils.clickOnCreateButton(page);
		FunctionTestUtils.verifyUserCanSeeFolder(page, "TestFolder");
		CodeAppPageUtils.userSelectTheFolder(page, "TestFolder");
		CodeAppPageUtils.clickOnCreateAtIconOnFileSection(page);
		CodeAppPageUtils.selectAction(page, "New Directory");
		CodeAppPageUtils.enterFolderName(page, "SubFolder");
		CodeAppPageUtils.clickOnCreateButton(page);
		CodeAppPageUtils.userCanSeeFolderUnderParentFolder(page, "SubFolder", "TestFolder");
		CodeAppPageUtils.clickOnCreateAtIconOnFileSection(page);
		CodeAppPageUtils.selectAction(page, "New File");
		CodeAppPageUtils.enterFileName(page, "SubFile");
		CodeAppPageUtils.clickOnCreateButton(page);
		CodeAppPageUtils.userCanSeeFileUnderParentFolder(page, "SubFile", "TestFolder");
		CodeAppPageUtils.clickOnCreateAtIconOnFileSection(page);
		CodeAppPageUtils.selectAction(page, "New File");
		CodeAppPageUtils.enterFileName(page, "TestFile");
		CodeAppPageUtils.clickOnCreateButton(page);
		FunctionTestUtils.verifyUserCanSeeFile(page, "TestFile");
		CodeAppPageUtils.userSelectTheFile(page, "TestFile");
		CodeAppPageUtils.userEditFileWithSomeContentAs(page, "dummydata");
		CommonUtils.navigateAndDeleteCatalog(page, catalog, catalogName);
	}
}
