package aicore.steps;

import java.io.IOException;

import aicore.hooks.SetupHooks;
import aicore.pages.CaptureScreenShotPage;
import aicore.utils.FolderUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class captureScreenShotSteps {

    private CaptureScreenShotPage captureScreenShotPage;
    private String currentFolder;
    private String subFolder;

    public captureScreenShotSteps() {
        captureScreenShotPage = new CaptureScreenShotPage(SetupHooks.getPage());

    }

    @Given("User captures documentation screenshot for {string}")
    public void initializeBaselineFolder(String subFolderName) {
        this.subFolder = subFolderName;
        this.currentFolder = FolderUtils.getCurrentBaselineFolder(subFolderName);
    }

    @And("User captures a {string} and highlights the {string}")
    public void user_Captures_Screenshot_and_highlights_the(String elementType, String elementName) throws IOException {
       //here
    	String fullPath = currentFolder;
        captureScreenShotPage.captureScreenshot(elementType, elementName, fullPath);
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
