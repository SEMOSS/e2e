package aicore.utils;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import aicore.hooks.SetupHooks;
import aicore.pages.CatalogCreationFromZipPage;
import aicore.pages.function.AddFunctionFormUtils;
import aicore.pages.function.FunctionAccessSettingsUtils;

public class FunctionTestUtils {
	public static void verifyFunctionCreationFormWithSelectFields(Page page, List<Map<String, String>> formFields){
		for (Map<String, String> row : formFields) {
			String sectionName = row.get("SECTION_NAME");
			String fieldsValue = row.get("FIELDS");
			if (sectionName == null || sectionName.trim().isEmpty() || fieldsValue == null
					|| fieldsValue.trim().isEmpty()) {
				continue;
			}
			String[] fields = fieldsValue.split(", ");
			for (String field : fields) {
				boolean isFieldVisible = AddFunctionPageUtils.fieldUnderSection(page, sectionName, field);
				Assertions.assertTrue(isFieldVisible, field + " is not visible under " + sectionName + " section");
			}
		}
	}
	
	
	public static void verifyFunctionCreationFormWithMandatoryFields(Page page, String mandatoryFields){
		String[] fields = mandatoryFields.split(", ");
		for (String field : fields) {
			boolean isFieldMandatory = AddFunctionPageUtils.isFieldMandatory(page, field);
			Assertions.assertTrue(isFieldMandatory, field + " is not mandatory field");
		}
	}
	
	public static void verifyUserFilledFunctionCreationFormFields(Page page, String formFields, String timestamp){
		String[] fields = formFields.split(", ");
		for (String field : fields) {
			if (!field.contains("=")) {
				continue;
			}
			String[] keyValue = field.split("=", 2);
			String fieldName = keyValue[0].trim();
			String fieldValue = keyValue[1].trim();
			AddFunctionPageUtils.fillFunctionCreationForm(page, fieldName, fieldValue, timestamp);
		}
	}
	
	public static void verifyUploadedFunctionFile(Page page, String fileName){
		String uploadedFileName = CatalogCreationFromZipUtil.uploadFile(page, fileName);
		if (fileName.contains("/")) {
			String[] ActualFileName = fileName.split("/");
			int fileNameIndex = ActualFileName.length - 1;
			Assertions.assertEquals(ActualFileName[fileNameIndex], uploadedFileName,
					"file is not uploaded successfully");
		} else {
			Assertions.assertEquals(fileName, uploadedFileName, "file is not uploaded successfully");
		}
	}
	
	public static void verifyAsteriskOnRequiredFields(Page page, String requiredFields){
		String[] fields = requiredFields.split(",");
		for (String field : fields) {
			boolean isFieldMandatory = AddFunctionFormUtils.isFieldMandatory(page, field);
			Assertions.assertTrue(isFieldMandatory, field + " is not mandatory field");
		}
	}
	
	public static void verifyUserSeesSuccessToastMessage(Page page, String toastMessage) {
		String actualMessage = AddFunctionPageUtils.verifySuccessToastMessage(page, toastMessage);
		Assertions.assertEquals(toastMessage, actualMessage, "Toaster is not matching with expected");
		AddFunctionPageUtils.closeToastMessage(page);
	}
	
	public static void verifyUserSeesDeletedToastMessage(Page page, String toastMessage) {
		String expectedMessage = FunctionAccessSettingsUtils.verifyDeleteToastMessage(page, toastMessage);
		String actualMessage = toastMessage;
		Assertions.assertEquals(actualMessage, expectedMessage, "Delete Message is not matching with expected");
	}
	
	public static void verifyUserSeesSuccessfulRequestToastMessage(Page page, String toastMessage) {
		String toastText = RequestAccessPopupUtils.verifyRequestSuccessToastVisible(page, toastMessage);
		Assertions.assertTrue(toastText != null && toastText.contains(toastMessage),
				"Expected toast message to contain: '" + toastMessage + "' but got: '" + toastText + "'");
	}
	
	public static void userUploadsFile(Page page, String fileName) {
		String uploadedFileName = CatalogCreationFromZipUtil.uploadFile(page, fileName);
		if (fileName.contains("/")) {
			String[] ActualFileName = fileName.split("/");
			int fileNameIndex = ActualFileName.length - 1;
			Assertions.assertEquals(ActualFileName[fileNameIndex], uploadedFileName,
					"file is not uploaded successfully");
		} else {
			Assertions.assertEquals(fileName, uploadedFileName, "file is not uploaded successfully");
		}
	}
	
	public static void userCanSeeCatalogTitle(Page page, String dbName) {
		boolean isTitleVisible = AddDatabasePageUtils.verifyDatabaseTitle(page, dbName);
		Assertions.assertTrue(isTitleVisible, "Database title is not visible");
	}
	
	public static void userSearchesForAndLocatesFunction(Page page, String catalogName) {
		Locator catalogLocator = AddFunctionPageUtils.searchForAndLocateCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_FUNCTION, catalogName);
		assertTrue( catalogLocator.isVisible() );
	}
	
	
	public static void verifyPopupWithSelectOptions(Page page, String expectedTitle, List<String> options){//user_should_see_the_popup_with_following_options(String expectedTitle,
//			io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
		Assertions.assertTrue(RequestAccessPopupUtils.isPopupVisible(SetupHooks.getPage()), expectedTitle + " popup is not visible");
		for (String option : options) {
			Assertions.assertTrue(RequestAccessPopupUtils.isOptionVisible(SetupHooks.getPage(), option),
					option + " is not visible in Change Access popup");
		}
	}
}
