package aicore.pages.database;
import com.microsoft.playwright.Page;
import aicore.pages.home.MainMenuUtils;
import aicore.utils.CatalogCreationFromZipUtil;
import aicore.utils.CatlogAccessPageUtility;

public final class DataBaseCreationUtils {

    private DataBaseCreationUtils() {
    }
    public static String createTestDatabase(Page page) {

        MainMenuUtils.openMainMenu(page);
        MainMenuUtils.clickOnOpenDatabase(page);

        AddDatabaseFormUtils.clickAddDatabaseButton(page);
        CatalogCreationFromZipUtil.clickOnFileUploadIcon(page);
        String uploadedFileName =
                CatalogCreationFromZipUtil.uploadFile(page, "Database/TestDatabase.zip");

        CatalogCreationFromZipUtil.clickOnUploadButton(page, "Upload");
        page.waitForURL(url -> !url.contains("/engine/database/new"));
        page.waitForTimeout(5000);
        CatlogAccessPageUtility.getCatalogAndCopyId(page);
        return uploadedFileName;
    }
}