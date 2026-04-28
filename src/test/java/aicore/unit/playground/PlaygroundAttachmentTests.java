package aicore.unit.playground;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import aicore.pages.home.HomePageUtils;
import aicore.utils.AbstractE2ETest;
import aicore.utils.PlaygroundPageUtils;
import aicore.utils.TestResources;

public class PlaygroundAttachmentTests extends AbstractE2ETest {

	@ParameterizedTest(name = "Playground upload attachments: {0}")
	@ValueSource(strings = { TestResources.EMPLOYEE_XLSX, TestResources.IMAGE_PNG, TestResources.MCP_ZIP,
			TestResources.TEST_TXT, TestResources.CUCUMBERREPORT_HTML, TestResources.DUMMY_PDF })
	void testUploadAttachments(String fileName) {
		login(page, UserType.NATIVE);
		HomePageUtils.navigateToHomePage(page);
		HomePageUtils.clickOnBuildButton(page);
		PlaygroundPageUtils.clickOnPlaygroundAppButton(page);
		PlaygroundPageUtils.closeTourButton(page);
		PlaygroundPageUtils.clickOnAddFilesButton(page);
		String optionName = "Add Documents";
		boolean isOptionDisplay = PlaygroundPageUtils.selectOption(page, optionName);
		Assertions.assertTrue(isOptionDisplay, "Option '" + optionName + "' is not visible in the Option settings.");

		PlaygroundPageUtils.uploadFileInPlaygrounds(page, fileName);
		String fileType = fileName.contains(".") ? fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase() : "";
		String actualFileName = PlaygroundPageUtils.verifyUploadedFile(page, fileType);
		
		String expectedFileName = fileName;
		if (fileName.contains("/")) {
			expectedFileName = fileName.substring(fileName.lastIndexOf("/") + 1);
		} 
		
		Assertions.assertTrue(actualFileName != null && actualFileName.toLowerCase().contains(expectedFileName.toLowerCase()),
				"File upload verification failed. Expected File: " + expectedFileName + " Actual File from UI: "
						+ actualFileName);
	}
}
