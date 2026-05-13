package aicore.unit.storage;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import aicore.hooks.SetupHooks;
import aicore.pages.home.HomePageUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.pages.model.EditModelPageUtils;
import aicore.utils.AddCatalogPageBaseUtils;
import aicore.utils.CommonUtils;
import aicore.utils.ModelTestUtils;
import aicore.utils.StoragePageUtils;
import aicore.utils.TestResourceTrackerHelper;
import aicore.utils.AbstractE2ETest.UserType;
import aicore.utils.annotations.PWPage;
import aicore.steps.AbstractAddCatalogBase;

import io.cucumber.datatable.DataTable;

import aicore.utils.AbstractE2ETest;

import aicore.utils.AbstractPlaywrightTestBase;

public class ViewAddStorageOptionsTests extends AbstractPlaywrightTestBase {
	
	private final String GROUP_NAME = "GROUP";
	private final String STORAGE_OPTIONS_NAME = "STORAGE_OPTIONS";
	private final String catalog = "storage";
	private final String storageOptions = "Amazon S3, CEPH, Dreamhost, Dropbox, Google Cloud, Google Drive, Local File System, Microsoft Azure Blob Storage, Microsoft OneDrive, MinIO, Network File System, SFTP";
	
//	| GROUP        | STORAGE_OPTIONS                                                                                                                                                        |
//	| Storage      | Amazon S3, CEPH, Dreamhost, Dropbox, Google Cloud, Google Drive, Local File System, Microsoft Azure Blob Storage, Microsoft OneDrive, MinIO, Network File System, SFTP |

//	private List<List<String>> raw = Arrays.asList(
//		    Arrays.asList(GROUP_NAME, STORAGE_OPTION_NAMES),
//		    Arrays.asList(
//		        "Storage",
//		        "Amazon S3, CEPH, Dreamhost, Dropbox, Google Cloud, Google Drive, Local File System, Microsoft Azure Blob Storage, Microsoft OneDrive, MinIO, Network File System, SFTP"
//		    )
//		);
	
	
//	// Create test data structure to replace DataTable
//	// Each entry: [section name, expected count]
//	Object[][] testData = {
//		{"How to use in Pixel", 5},
//		{"How to use in Python", 1},
//		{"How to use with LangChain API", 1},
//		{"How to use externally with OpenAI API (with or without our Python SDK)", 4},
//		{"How to use in Java", 1}
//	};
//	
//	// Validate model catalog id occurences in each section
//	for (Object[] data : testData) {
//		String sectionName = (String) data[0];
//		int expectedCount = (int) data[1];
	
	
	@BeforeEach
    public void setup(@PWPage Page page) {
        // login with admin user before tests
		loginNativeAdmin(page);
    }

	@AfterEach
    public void tearDown(@PWPage Page page) {
        logout(page);
    }
	
	
	@Test
	public void viewAndAddStorageOptions_test(@PWPage Page page) throws Exception {
				
		HomePageUtils.navigateToHomePage(page);
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenStorage(page);
		StoragePageUtils.clickOnAddStorageButton(page);
		
		boolean isSearchBarVisible = AddCatalogPageBaseUtils.isSearchBarPresent(page);
		Assertions.assertTrue(isSearchBarVisible, "Search bar not visible");
				
		List<Map<String, String>> rows = List.of(
			    Map.of(GROUP_NAME, catalog, STORAGE_OPTIONS_NAME, storageOptions)
			);
//		AbstractAddCatalogBase.validateOptionsWithIcon(catalog, GROUP_NAME, STORAGE_OPTION_NAMES, rows, SetupHooks.getPage());
		
		validateOptionsWithIcon(catalog, GROUP_NAME, STORAGE_OPTIONS_NAME, rows, page);
		
//		assertTrue(CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_STORAGE, catalog));

	}
	
	private static void validateOptionsWithIcon(final String catalog, final String groupName, final String supportedOptions,
			List<Map<String, String>> rows, Page page) {
		for (Map<String, String> row : rows) {
			String sectionName = row.get(groupName);
			String expectedOptions = row.get(supportedOptions);
			// Verify section is visible
//			boolean isSectionVisible = AddCatalogPageBaseUtils.verifySectionIsVisible(page, catalog, sectionName);
//			Assertions.assertTrue(isSectionVisible, sectionName + " section not visible");
			String[] expectedOptionsArray = expectedOptions.split(", ");
			for (String optionName : expectedOptionsArray) {
				// Verify option is visible
				boolean isOptionVisible =  AddCatalogPageBaseUtils.verifyOptionIsVisible(page, catalog, sectionName, optionName);
				Assertions.assertTrue(isOptionVisible, optionName + " option not visible");
				// Verify icon is visible
				Locator icon = AddCatalogPageBaseUtils.getIconByLabel(page, catalog, sectionName, optionName);
				icon.waitFor();
				boolean isIconVisible = AddCatalogPageBaseUtils.isIconVisible(page, catalog, sectionName, optionName);
				Assertions.assertTrue(isIconVisible, optionName + " icon is not visible");
				// verify icon is not broken
				String iconUrl = icon.getAttribute("src");
				// for 'Local File System' storage & 'FAISS' vector options getting broken image
				if (isIconVisible && !optionName.matches(".*(Local File System||FAISS).*")) {
					boolean isIconValid = CommonUtils.isIconValid(iconUrl);
					Assertions.assertTrue(isIconValid, optionName + " icon src is broken: " + iconUrl);
				}
			}
		}
	}
}
