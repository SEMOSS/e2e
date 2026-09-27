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

public class AWSPolly_TestCases extends AbstractPlaywrightTestBase {

	@BeforeEach
	void setup(@PWPage Page page) {
		loginNativeAdmin(page);
	}

	@AfterEach
	void tearDown(@PWPage Page page) {
		logout(page);
	}

	@Test
	void testConnectAWSPollyFunction(@PWPage Page page) {
		String timestamp = CommonUtils.getTimeStampName();
		String catalogName = "AWS-Polly";
		String formFields = "Catalog Name=" + catalogName
				+ ", Access Key=Test123, Secret Key=Test@123, Region=Asia";
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenFunction(page);
		AddFunctionPageUtils.clickOnAddFunctionButton(page);
		AddFunctionFormUtils.selectFunction(page, "AWS Polly");
		FunctionTestUtils.verifyUserFilledFunctionCreationFormFields(page, formFields, timestamp);
		boolean isConnectEnabled = AddFunctionFormUtils.validateConnectButtonEnabled(page);
		Assertions.assertTrue(isConnectEnabled, "'Connect' button is not enabled after filling all required fields");
		AddFunctionFormUtils.clickOnConnectButton(page);

		CatlogAccessPageUtility.getCatalogAndCopyId(page);
		FunctionTestUtils.verifyUserSeesSuccessToastMessage(page, "Successfully added function database to catalog");

		CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_FUNCTION, catalogName + timestamp);
	}
}
