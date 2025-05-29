package aicore.utils;

import java.nio.file.FileSystems;
import java.nio.file.Paths;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class AddDatabaseToCatalogPageUtils {

    private static final String ADD_DATABASE_BUTTON = "Navigate to import Database";
    private static final String ADD_FILE_XPATH = "//input[@type='file']";
    private static final String ADD_FILE_NAME_XPATH = "//span[@title='{fileName}']";
    private static final String CREATE_DATABASE_BUTTON = "Create database";

    public static void clickAddDatabaseButton(Page page) {
        page.getByLabel(ADD_DATABASE_BUTTON).isVisible();
        page.getByLabel(ADD_DATABASE_BUTTON).click();
    }

    public static void selectDatabaseType(Page page, String dbType) {
        page.getByText(dbType).isVisible();
        page.getByText(dbType).click();
    }

    public static String uploadDatabaseFile(Page page, String fileName) {
        String pathSeparator = FileSystems.getDefault().getSeparator();
        Locator fileInput = page.locator(ADD_FILE_XPATH);
        String relativePath = "src" + pathSeparator + "test" + pathSeparator + "resources" + pathSeparator + "data"
                + pathSeparator;
        if (fileName.contains("/")) {
            fileName.replace("/", pathSeparator);
        }
        fileInput.setInputFiles(Paths.get(relativePath + fileName));
        if (fileName.contains("/")) {
            String[] ActualFileName = fileName.split("/");
            int fileNameIndex = ActualFileName.length - 1;
            Locator uploadedFileName = page
                    .locator(ADD_FILE_NAME_XPATH.replace("{fileName}", ActualFileName[fileNameIndex]));
            String uploadedFileNameValue = uploadedFileName.textContent();
            return uploadedFileNameValue;
        } else {
            Locator uploadedFileName = page.locator(ADD_FILE_NAME_XPATH.replace("{fileName}", fileName));
            String uploadedFileNameValue = uploadedFileName.textContent();
            return uploadedFileNameValue;
        }
    }

    public static void clickCreateDatabaseButton(Page page) {
        page.getByText(CREATE_DATABASE_BUTTON).isVisible();
        page.getByText(CREATE_DATABASE_BUTTON).click();
    }

    public static String verifyDatabaseNameInCatalog(Page page, String dbName) {
        page.getByText(dbName).isVisible();
        String databaseNameInCatalog = page.getByText(dbName).textContent();
        return databaseNameInCatalog;
    }

    public static void clickOnDatabaseNameInCatalog(Page page, String dbName) {
        page.getByText(dbName).click();
    }
}