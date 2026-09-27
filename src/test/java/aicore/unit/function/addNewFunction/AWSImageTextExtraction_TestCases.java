package aicore.unit.function.addNewFunction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
import aicore.utils.annotations.PWPage;

public class AWSImageTextExtraction_TestCases extends AbstractPlaywrightTestBase {

	@BeforeEach
	void setup(@PWPage Page page) {
		loginNativeAdmin(page);
	}

	@AfterEach
	void tearDown(@PWPage Page page) {
		logout(page);
	}

	@Test
	void testConnectAWSImageTextExtractionFunction(@PWPage Page page) {
		String timestamp = CommonUtils.getTimeStampName();
		String catalogName = "AWS-Image-Text-Extraction";
		String formFields = "Catalog Name=" + catalogName
				+ ", Access Key=a123456789, Secret Key=test22, Region=us-east-1, S3 Bucket Engine Id=a123456789";
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenFunction(page);
		AddFunctionPageUtils.clickOnAddFunctionButton(page);
		AddFunctionFormUtils.selectFunction(page, "AWS Image Text Extraction");
		FunctionTestUtils.verifyUserFilledFunctionCreationFormFields(page, formFields, timestamp);
		boolean isConnectEnabled = AddFunctionFormUtils.validateConnectButtonEnabled(page);
		Assertions.assertTrue(isConnectEnabled, "'Connect' button is not enabled after filling all required fields");
		AddFunctionFormUtils.clickOnConnectButton(page);

		CatlogAccessPageUtility.getCatalogAndCopyId(page);
		FunctionTestUtils.verifyUserSeesSuccessToastMessage(page, "Successfully added function database to catalog");

		CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_FUNCTION, catalogName + timestamp);
	}
}
