package aicore.steps;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import aicore.hooks.SetupHooks;
import aicore.pages.CaptureScreenShotPage;
import aicore.utils.CaptureScreenShotUtils;
import aicore.utils.FolderUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class captureScreenShotSteps {

    private CaptureScreenShotPage captureScreenShotPage;
    private String currentFolder;
    private String subFolder;
    private final Page page;

    public captureScreenShotSteps() {
        captureScreenShotPage = new CaptureScreenShotPage(SetupHooks.getPage());
        this.page = SetupHooks.getPage();
    }

    @Given("User captures documentation screenshot for {string}")
    public void initializeBaselineFolder(String subFolderName) {
        this.subFolder = subFolderName;
        this.currentFolder = FolderUtils.getCurrentBaselineFolder(subFolderName);
    }
    
    @And("User captures a {string} and highlights the {string}")
    public void user_Captures_Screenshot_and_highlights_the(String elementTypes, String elementNames) throws IOException {
        String fullPath = currentFolder;

        String[] types = elementTypes.split(",");
        String[] names = elementNames.split(",");

        // Trim for robustness
        for (int i = 0; i < types.length; i++) types[i] = types[i].trim();
        for (int i = 0; i < names.length; i++) names[i] = names[i].trim();

        List<Locator> allLocators = new ArrayList<>();
        for (int i = 0; i < types.length; i++) {
            String type = types[i];
            String name = (i < names.length) ? names[i] : "";
            List<Locator> locators = captureScreenShotPage.getLocatorsForTypeAndName(type, name);
            allLocators.addAll(locators);
        }
        Path path = Paths.get(fullPath, String.join("_", names) + ".png");
        CaptureScreenShotUtils.captureScreenshot(page, allLocators, path);      
    }

    @And("User captures a {string} and highlights the {string} with name {string}")
    public void user_Captures_Screenshot_and_highlights_the_with_name(String elementTypes, String elementNames,String fileName) throws IOException {
        String fullPath = currentFolder;

        String[] types = elementTypes.split(",");
        String[] names = elementNames.split(",");

        // Trim for robustness
        for (int i = 0; i < types.length; i++) types[i] = types[i].trim();
        for (int i = 0; i < names.length; i++) names[i] = names[i].trim();

        List<Locator> allLocators = new ArrayList<>();
        for (int i = 0; i < types.length; i++) {
            String type = types[i];
            String name = (i < names.length) ? names[i] : "";
            List<Locator> locators = captureScreenShotPage.getLocatorsForTypeAndName(type, name);
            allLocators.addAll(locators);
        }
        Path path = Paths.get(fullPath, String.join("_", fileName) + ".png");
        CaptureScreenShotUtils.captureScreenshot(page, allLocators, path);      
    }
    
    @Then("User completes screenshot capture and triggers comparison for {string}")
    public void user_completes_screenshot_capture_and_triggers_comparison(String catalogName) throws IOException, Exception {
        captureScreenShotPage.compareAndStoreResultsIfReady(catalogName);
    }
    @And("User captures screenshot for {string}")
    public void user_captures_screenshot_for(String folderName) throws IOException {
        String fullPath = currentFolder;
        captureScreenShotPage.capturePageScreenshot(folderName, fullPath);
    }

     @And("User captures screenshot for form {string}")
    public void user_captures_screenshot_for_form(String formName) throws IOException {
        String fullPath = currentFolder;
        captureScreenShotPage.captureFormScreenshot(formName, fullPath);
    }

}
