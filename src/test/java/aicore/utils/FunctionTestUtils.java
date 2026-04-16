package aicore.utils;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;

import com.microsoft.playwright.Page;

import aicore.hooks.SetupHooks;
import aicore.pages.CatalogCreationFromZipPage;

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
}
