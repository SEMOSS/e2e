package aicore.unit.storage;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import aicore.hooks.SetupHooks;
import aicore.pages.home.HomePageUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.pages.storage.AddStorageFormUtils;
import aicore.utils.AddCatalogPageBaseUtils;
import aicore.utils.CatlogAccessPageUtility;
import aicore.utils.CommonUtils;
import aicore.utils.StoragePageUtils;
import aicore.utils.TestResourceTrackerHelper;
import io.cucumber.datatable.DataTable;

import aicore.utils.AbstractE2ETest;

import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.annotations.PWPage;
import com.microsoft.playwright.Page;

public class ViewAndValidateStorageCreationFormTests extends AbstractPlaywrightTestBase {
	
//	private static String storageName = "<FUNCTION_NAME>";
	private static String storageName = "Amazon S3";
//	private static String fieldNames = "<FIELD_NAMES>";
	private static String fieldNames = "Catalog Name, Region, Bucket, Access key, Secret key";
//	private static String requiredFields = "<REQUIRED_FIELDS>";
	private static String requiredFields = "Catalog Name, Region, Bucket";
	private static String timestamp = CommonUtils.getTimeStampName();
			
			
//	| GROUP        | STORAGE_OPTIONS                                                                                                                                                        |
//	| Storage      | Amazon S3, CEPH, Dreamhost, Dropbox, Google Cloud, Google Drive, Local File System, Microsoft Azure Blob Storage, Microsoft OneDrive, MinIO, Network File System, SFTP |

	private List<List<String>> raw = Arrays.asList(
				Arrays.asList("FIELD_NAME", "FIELD_VALUE"),
				Arrays.asList("Catalog Name", "Amazon S3 Storage"),
				Arrays.asList("Region", "India"),
				Arrays.asList("Bucket", "BucketTest"),
				Arrays.asList("Access key", "Test123"),
				Arrays.asList("Secret key", "Test123")
			);
	
	private List<List<String>> complete = Arrays.asList(
			Arrays.asList("FIELD_NAME", "FIELD_VALUE"),
			Arrays.asList("Catalog Name", "Amazon S3 Storage"),
			Arrays.asList("Region", "India"),
			Arrays.asList("Bucket", "BucketTest")
		);
	
	private List<List<String>> missingCatalogNameValue = Arrays.asList(
			Arrays.asList("FIELD_NAME", "FIELD_VALUE"),
			Arrays.asList("Catalog Name", ""),
			Arrays.asList("Region", "India"),
			Arrays.asList("Bucket", "BucketTest")
		);
	
	private List<List<String>> missingRegionValue = Arrays.asList(
			Arrays.asList("FIELD_NAME", "FIELD_VALUE"),
			Arrays.asList("Catalog Name", "Amazon S3 Storage"),
			Arrays.asList("Region", ""),
			Arrays.asList("Bucket", "BucketTest")
		);
	
	private List<List<String>> missingBucketValue = Arrays.asList(
			Arrays.asList("FIELD_NAME", "FIELD_VALUE"),
			Arrays.asList("Catalog Name", "Amazon S3 Storage"),
			Arrays.asList("Region", "India"),
			Arrays.asList("Bucket", "")
		);
	
	@BeforeEach
	void setup(@PWPage Page page) {
		loginNativeAdmin(page);
	}	
	@AfterEach
	void tearDown(@PWPage Page page) {
		// DeleteTestCatalog, should delete it since we created it by clicking on the connect button in the test method
		logout(page);
	}
	
	@Test
	public void viewAndValidateStorageCreationForm_test(@PWPage Page page) {
		
		HomePageUtils.navigateToHomePage(page);
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenStorage(page);
		StoragePageUtils.clickOnAddStorageButton(page);
		
		AddStorageFormUtils.selectStorage(page, storageName);
		
		String[] fields = fieldNames.split(", ");
		for (String field : fields) {
			boolean isFieldVisible = AddStorageFormUtils.verifyFieldIsVisible(page, field);
			Assertions.assertTrue(isFieldVisible, "Field is not visible on form: " + field);
		}
		
//		String requiredFieldLabels = "<REQUIRED_FIELDS>";
		String requiredFieldLabels = "Catalog Name,Region,Bucket";
//		When User enters value in below fields
//	      | FIELD_NAME   | FIELD_VALUE       |
//	      | Catalog Name | Amazon S3 Storage |
//	      | Region       | India             |
//	      | Bucket       | BucketTest        |
//	      | Access key   | Test123           |
//	      | Secret key   | Test123           |
//
//	    Examples: 
//	      | FUNCTION_NAME | FIELD_NAMES                                          | REQUIRED_FIELDS              |
//	      | Amazon S3     | Catalog Name, Region, Bucket, Access key, Secret key | Catalog Name, Region, Bucket |
		StoragePageUtils.verifyAsteriskMarkOnFields(page, requiredFieldLabels);
		
		List<Map<String, String>> rows = convertToMaps(missingCatalogNameValue);
		for (Map<String, String> row : rows) {
			String fieldName = row.get("FIELD_NAME");
			String fieldValue = row.get("FIELD_VALUE");
			fieldValue = fieldValue != null ? fieldValue.trim() : "";
			AddStorageFormUtils.enterValuesInField(page, fieldName, fieldValue, timestamp);
		}
		boolean isButtonDisabled = StoragePageUtils.verifyConnectButtonDisabled(page);
		Assertions.assertTrue(isButtonDisabled, "Create Storage button is not disabled");
		
		rows = convertToMaps(missingRegionValue);
		for (Map<String, String> row : rows) {
			String fieldName = row.get("FIELD_NAME");
			String fieldValue = row.get("FIELD_VALUE");
			fieldValue = fieldValue != null ? fieldValue.trim() : "";
			AddStorageFormUtils.enterValuesInField(page, fieldName, fieldValue, timestamp);
		}
		isButtonDisabled = StoragePageUtils.verifyConnectButtonDisabled(page);
		Assertions.assertTrue(isButtonDisabled, "Create Storage button is not disabled");
		
		rows = convertToMaps(missingBucketValue);
		for (Map<String, String> row : rows) {
			String fieldName = row.get("FIELD_NAME");
			String fieldValue = row.get("FIELD_VALUE");
			fieldValue = fieldValue != null ? fieldValue.trim() : "";
			AddStorageFormUtils.enterValuesInField(page, fieldName, fieldValue, timestamp);
		}
		isButtonDisabled = StoragePageUtils.verifyConnectButtonDisabled(page);
		Assertions.assertTrue(isButtonDisabled, "Create Storage button is not disabled");
		
		rows = convertToMaps(complete);
		for (Map<String, String> row : rows) {
			String fieldName = row.get("FIELD_NAME");
			String fieldValue = row.get("FIELD_VALUE");
			fieldValue = fieldValue != null ? fieldValue.trim() : "";
			AddStorageFormUtils.enterValuesInField(page, fieldName, fieldValue, timestamp);
		}
		boolean isButtonEnabled = StoragePageUtils.validateConnectButtonEnabled(page);
		Assertions.assertTrue(isButtonEnabled, "'Connect' button is not enabled");
		
		
		
		rows = convertToMaps(raw);
		for (Map<String, String> row : rows) {
			String fieldName = row.get("FIELD_NAME");
			String fieldValue = row.get("FIELD_VALUE");
			fieldValue = fieldValue != null ? fieldValue.trim() : "";
			AddStorageFormUtils.enterValuesInField(page, fieldName, fieldValue, timestamp);
		}
		StoragePageUtils.clickOnConnectButton(page);
		
		String expectedMessage = "Successfully added new storage to catalog";
		String actualMessage = StoragePageUtils.verifyStorageCreatedToastMessage(page, expectedMessage);
		Assertions.assertEquals(actualMessage, expectedMessage, "Storage creation is failed");
		CatlogAccessPageUtility.getCatalogAndCopyId(page);
		
		String catalogName = "Amazon S3 Storage" + timestamp;
		AddCatalogPageBaseUtils.verifyCatalogName(page, catalogName);
		
		assertTrue(CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_STORAGE, catalogName));
		
	}
	
	private List<Map<String, String>> convertToMaps(List<List<String>> ls) {
		List<Map<String, String>> maps = new ArrayList<Map<String, String>>();
		String fieldName = ls.get(0).get(0);
		String fieldValue = ls.get(0).get(1);
		for(int i = 1; i < ls.size(); i++) {
			maps.add(Map.of(fieldName, ls.get(i).get(0), fieldValue, ls.get(i).get(1)));
		}
		return maps;
	}

}
