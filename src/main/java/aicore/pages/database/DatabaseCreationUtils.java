package aicore.pages.database;

import com.microsoft.playwright.Page;

import aicore.pages.home.MainMenuUtils;
import aicore.utils.AddDatabasePageUtils;
import aicore.utils.CatalogCreationFromZipUtil;
import aicore.utils.CatlogAccessPageUtility;
import aicore.utils.CommonUtils;
import aicore.utils.TestResourceTrackerHelper;

public final class DatabaseCreationUtils {
	
	private static final String DATABASE_NAME = "TestDatabase";
	private static final String FILE_PATH = "Database/TestDatabase.zip";
	
	private DatabaseCreationUtils() {
    }
	public static String createTestDatabase(Page page) {
		
		System.out.println("1. Opening Database page...");
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenDatabase(page);
		
		// check if TestDatabase already exists
		boolean isVisible = AddDatabasePageUtils.verifyDatabaseIsVisibleInCatalog(page, DATABASE_NAME);
		if(isVisible) {
			// delete db
			boolean deleted = CommonUtils.navigateAndDeleteCatalog(
                    page,
                    TestResourceTrackerHelper.CATALOG_TYPE_DATABASE,
                    DATABASE_NAME);
			System.out.println("2. Deleting existing database...");
            System.out.println("Database deleted: " + deleted);
    	}
		
		System.out.println("3. Clicking Add Database...");
		AddDatabaseFormUtils.clickAddDatabaseButton(page);
		
		System.out.println("4. Uploading ZIP...");
		CatalogCreationFromZipUtil.clickOnFileUploadIcon(page);

		String uploadedFileName = CatalogCreationFromZipUtil.uploadFile(page, FILE_PATH);
		System.out.println("Uploaded: " + uploadedFileName);

		System.out.println("5. Clicking Upload...");
		CatalogCreationFromZipUtil.clickOnUploadButton(page, "Upload");
//      page.waitForURL(url -> !url.contains("/engine/database/new"));
        System.out.println("After upload URL: " + page.url());
        System.out.println(page.locator("body").innerText());

        System.out.println("6. Copying Catalog ID...");
        System.out.println(page.url());

        page.waitForTimeout(5000);

        System.out.println(page.content());
		System.out.println(page.url());
		System.out.println(page.title());
//      CatlogAccessPageUtility.getCatalogAndCopyId(page);

        System.out.println("Done.");

        return uploadedFileName;
    }
}
