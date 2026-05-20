package aicore.unit.storage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;

import aicore.hooks.SetupHooks;
import aicore.pages.home.HomePageUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.pages.storage.AddStorageFormUtils;
import aicore.utils.StoragePageUtils;
import aicore.utils.AbstractE2ETest.UserType;
import aicore.utils.annotations.PWPage;
import io.cucumber.datatable.DataTable;
import aicore.utils.AbstractE2ETest;
import aicore.utils.CommonUtils;

import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.annotations.PWPage;
import com.microsoft.playwright.Page;



public class AddAllStorageTypesTests extends AbstractPlaywrightTestBase {
	
	// table format:
    //    "| STORAGE                      | STORAGE_TYPE | S1_NAME | S1_FIELDS    | S2_NAME     | S2_FIELDS                      | S3_NAME  | S3_FIELDS                           | MANDATORY_FIELDS                                                                 | FORM_FIELDS                                                                                                                                   |"
	List<String> data = List.of(
	      "| Amazon S3                    | S3           | General | Catalog Name | Credentials | Access Key, Secret Key         | Settings | Region, Bucket                      | Catalog Name, Region, Bucket                                                     | Catalog Name=Amazon-S3, Access Key=Test123, Secret Key=Test@123, Region=Asia, Bucket=s3                                                       |",
	      "| CEPH                         | CEPH         | General | Catalog Name | Credentials | Access Key, Secret Key         | Settings | Endpoint, Root Bucket Path          | Catalog Name, Access Key, Secret Key, Endpoint                                   | Catalog Name=CEPH, Access Key=Test123, Secret Key=Test@123, Endpoint=https://www.google.com, Root Bucket Path=/data                           |",
	      "| Dropbox                      | S3           | General | Catalog Name | Credentials | S3 Access Key, S3 Secret Key   | Settings | S3 Endpoint, S3 Region              | Catalog Name, S3 Endpoint, S3 Region, S3 Access Key, S3 Secret Key               | Catalog Name=Dropbox, S3 Region=Asia, S3 Endpoint=https://www.google.com, S3 Access Key=Test123, S3 Secret Key=Test@123                       |",
	      "| Google Cloud                 | GCS          | General | Catalog Name | Credentials | Service Account File           | Settings | Region, Bucket                      | Catalog Name, Region, Service Account File                                       | Catalog Name=Google-Cloud, Region=Asia, Bucket=GCS, Service Account File=/data                                                                |",
	      "| Local File System            | LOCAL        | General | Catalog Name |             |                                | Settings | Local Path Prefix                   | Catalog Name, Local Path Prefix                                                  | Catalog Name=Local-File-System, Local Path Prefix=/data                                                                                       |",
	      "| Microsoft Azure Blob Storage | Azure        | General | Catalog Name | Credentials | Primary Key, Connection String | Settings | Account Name, Generate Dynamic SAS  | Catalog Name, Account Name, Generate Dynamic SAS, Primary Key, Connection String | Catalog Name=Microsoft-Azure-Blob, Account Name=Azure storage, Generate Dynamic SAS=True, Primary Key=Test123, Connection String=ABC          |",
	      "| MinIO                        | MINIO        | General | Catalog Name | Credentials | Access Key, Secret Key         | Settings | Region, Endpoint, Root Bucket Path  | Catalog Name, Region, Endpoint, Access Key, Secret Key                           | Catalog Name=Minio, Region=Asia, Endpoint=https://www.google.com, Root Bucket Path=/data/test-bucket, Access Key=Test123, Secret Key=Test@123 |",
	      "| Network File System          | NFS          | General | Catalog Name | Credentials | Username, Password             | Settings | Network Domain, Network Path Prefix | Catalog Name, Network Domain, Username, Password                                 | Catalog Name=Network-File-System, Network Domain=test.local, Network Path Prefix=data, Username=user1, Password=pass@123                      |",
	      "| SFTP                         | SFTP         | General | Catalog Name | Credentials | Username, Password             | Settings | Host, Port                          | Catalog Name, Host, Port, Username                                               | Catalog Name=SFTP, Host=localhost, Port=8888, Username=user1, Password=pass@123                                                               |"
	 );
	
	List<String> format = List.of("STORAGE", "STORAGE_TYPE", "S1_NAME", "S1_FIELDS", "S2_NAME", "S2_FIELDS", "S3_NAME", "S3_FIELDS", "MANDATORY_FIELDS", "FORM_FIELDS");
	 
	record Storage(String storageName, String storageType, String s1Name, String s1Fields, String s2Name, String s2Fields, String s3Name, String s3Fields, String mandatoryFields, String formFields) {}
	
	public Storage parseRow(String row) {
		String[] rawParts = row.split("\\|");
		for(int i = 0; i < rawParts.length; i++) { // get rid of whitespace for the values
			rawParts[i] = rawParts[i].trim();
		}
		// discard rawParts[0] due to leading '|' character in the row
		Storage store = new Storage(rawParts[1], rawParts[2], rawParts[3], rawParts[4], rawParts[5], rawParts[6], rawParts[7], rawParts[8], rawParts[9], rawParts[10]);
		return store;
	}

	private static String timestamp = CommonUtils.getTimeStampName();
	
	//private final String storageOptions = "Amazon S3, CEPH, Dreamhost, Dropbox, Google Cloud, Google Drive, Local File System, Microsoft Azure Blob Storage, Microsoft OneDrive, MinIO, Network File System, SFTP";

	
//	| SECTION_NAME | FIELDS      |
//	| <S1_NAME>    | <S1_FIELDS> |
//	| <S2_NAME>    | <S2_FIELDS> |
//	| <S3_NAME>    | <S3_FIELDS> |
	
//	private List<List<String>> raw = Arrays.asList(
//		    Arrays.asList("SECTION_NAME", "FIELDS"),
//		    Arrays.asList("<S1_NAME>", "<S1_FIELDS>"),
//		    Arrays.asList("<S2_NAME>", "<S2_FIELDS>"),
//		    Arrays.asList("<S3_NAME>", "<S3_FIELDS>")
//		);
//	
	
//	private DataTable dataTable = DataTable.create(raw);	
	
	
	@BeforeEach
	void setup(@PWPage Page page) {
		loginNativeAdmin(page);
	}	
	@AfterEach
	void tearDown(@PWPage Page page) {
		logout(page);
	}
	
	@Test
	public void addAllStorageTypes_test(@PWPage Page page) throws IOException {
		
		for(String row : data) {
			
			Storage store = parseRow(row);
			
			HomePageUtils.navigateToHomePage(page);
			
			MainMenuUtils.openMainMenu(page);
			MainMenuUtils.clickOnOpenStorage(page);
			StoragePageUtils.clickOnAddStorageButton(page);
			
			String storageName = store.storageName();
			String storageType = store.storageType();
			AddStorageFormUtils.selectStorage(page, storageName);
			
			// User can see '<STORAGE_TYPE>' storage creation form with following sections with fields:
			// | SECTION_NAME | FIELDS      |
			// | <S1_NAME>    | <S1_FIELDS> |
			// | <S2_NAME>    | <S2_FIELDS> |
			// | <S3_NAME>    | <S3_FIELDS> |

			String sectionName = store.s1Name();
			String fieldsValue = store.s1Fields();
			checker(page, sectionName, fieldsValue, storageName, storageType);
			
			sectionName = store.s2Name();
			fieldsValue = store.s2Fields();
			checker(page, sectionName, fieldsValue, storageName, storageType);
			
			sectionName = store.s3Name();
			fieldsValue = store.s3Fields();
			checker(page, sectionName, fieldsValue, storageName, storageType);
						
			// User can see '<STORAGE_TYPE>' storage creation form with following mandatory fields
			// | <MANDATORY_FIELDS> |
			String[] mandatoryFields = store.mandatoryFields().split(", ");
			for (String field : mandatoryFields) {
				boolean isFieldMandatory = AddStorageFormUtils.isFieldMandatory(page, storageType, field);
				Assertions.assertTrue(isFieldMandatory, field + " is not mandatory field");
			}
			
			// User fills the '<STORAGE_TYPE>' storage creation form with:
			// | <FORM_FIELDS> |
			String[] formFields = store.formFields().split(", ");
			for (String field : formFields) {
				if (!field.contains("=")) {
					continue;
				}
				String[] keyValue = field.split("=", 2);
				String fieldName = keyValue[0].trim();
				String fieldValue = keyValue[1].trim();
				AddStorageFormUtils.fillCatalogCreationForm(page, storageType, fieldName, fieldValue, timestamp);
			}
			
		    // User can see 'Connect' button becomes enabled to create storage
			boolean isButtonEnabled = StoragePageUtils.validateConnectButtonEnabled(page);
			Assertions.assertTrue(isButtonEnabled, "'Connect' button is not enabled");
			
			// test doesn't actually create the storage since it doesn't click the connect button,
			// so we don't have to delete the storages in the teardown cleanup method
		}
	}
	
	private void checker(@PWPage Page page, String sectionName, String fieldsValue, String storageName, String storageType) {
		if (sectionName != null && !(sectionName.trim().isEmpty()) && fieldsValue != null
				&& !(fieldsValue.trim().isEmpty())) {
			String[] fields = fieldsValue.split(", ");
			for (String field : fields) {
				boolean isFieldVisible = AddStorageFormUtils.fieldUnderSection(page, storageType, sectionName, field);
				Assertions.assertTrue(isFieldVisible, field + " is not visible under " + sectionName + " section in " + storageName + " storage, " + storageType + " type.");
			}
		}
	}
	
}
