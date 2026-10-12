package aicore.unit.function.addNewFunction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;

import aicore.pages.function.AddFunctionFormUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.AddFunctionPageUtils;
import aicore.utils.CatlogAccessPageUtility;
import aicore.utils.CommonUtils;
import aicore.utils.FunctionTestUtils;
import aicore.utils.TestResourceTrackerHelper;
import aicore.utils.TestResources;
import aicore.utils.annotations.PWPage;

public class GoogleOCR_TestCases extends AbstractPlaywrightTestBase {

	@BeforeEach
	void setup(@PWPage Page page) {
		loginNativeAdmin(page);
	}

	@AfterEach
	void tearDown(@PWPage Page page) {
		logout(page);
	}

	@Test
	@DisplayName("TC01_Verify connection of Google OCR function")
	void testConnectGoogleOCRFunction(@PWPage Page page) {
		String timestamp = CommonUtils.getTimeStampName();
		String catalogName = TestResources.FUNC_GOOGLE_OCR_CATALOG;
		String formFields = "Catalog Name=" + catalogName + ", " + TestResources.FUNC_GOOGLE_OCR_FORM_FIELDS;
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenFunction(page);
		AddFunctionPageUtils.clickOnAddFunctionButton(page);
		AddFunctionFormUtils.selectFunction(page, TestResources.FUNC_GOOGLE_OCR);
		FunctionTestUtils.verifyUserFilledFunctionCreationFormFields(page, formFields, timestamp);
		AddFunctionFormUtils.uploadFile(page, TestResources.DUMMY_PDF);
		boolean isConnectEnabled = AddFunctionFormUtils.validateConnectButtonEnabled(page);
		Assertions.assertTrue(isConnectEnabled, "'Connect' button is not enabled after filling all required fields");
		AddFunctionFormUtils.clickOnConnectButton(page);

		CatlogAccessPageUtility.getCatalogAndCopyId(page);
		FunctionTestUtils.verifyUserSeesSuccessToastMessage(page, "Successfully added function database to catalog");

		CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_FUNCTION, catalogName + timestamp);
	}
}
