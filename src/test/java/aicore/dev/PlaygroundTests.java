package aicore.dev;

import java.nio.file.Paths;
import java.util.Arrays;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

import aicore.base.GenericSetupUtils;
import aicore.utils.ConfigUtils;

/**
 * Creating main method to test all models in playground
 */

public class PlaygroundTests {

	public static void main(String[] args) {
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(GenericSetupUtils.getLaunchOptions());
		
		Browser.NewContextOptions newContextOptions = GenericSetupUtils.getContextOptions();
		BrowserContext context = browser.newContext(newContextOptions);

		context.grantPermissions(Arrays.asList("clipboard-read", "clipboard-write"));

		Tracing.StartOptions startOptions = GenericSetupUtils.getStartOptions();
		context.tracing().start(startOptions);

		Page page = context.newPage();
		page.setDefaultTimeout(Double.parseDouble(ConfigUtils.getValue("timeout")));

		GenericSetupUtils.setupLoggers(page);

		String adminUser = ConfigUtils.getValue("admin_username");
		String adminPassword = ConfigUtils.getValue("admin_password");
		
		// user in on home page after login
		GenericSetupUtils.login(page, adminUser, adminPassword);
		
		/*
		// TODO go to settings page
		page.getByTestId("Settings-icon").click();
		
		
		// click on model settings
		page.getByText("Model Settings").click();
		
		// Toggle to Admin On
		page.getByText("Admin Off").click();
		
		// assign user to every model
		
		// Selector for the container
        String containerSelector = ".MuiGrid-root.MuiGrid-container.MuiGrid-spacing-xs-3.css-1h77wgb";
        // Selector for the items within the container
        String itemSelector = ".MuiGrid-root.MuiGrid-item.MuiGrid-grid-sm-12.MuiGrid-grid-md-6.MuiGrid-grid-lg-4.MuiGrid-grid-xl-3.css-1noz1rq";

        // Get the container element
        ElementHandle container = page.querySelector(containerSelector);

       
        // Get all items within the container
        java.util.List<ElementHandle> items = container.querySelectorAll(itemSelector);

        
        // Iterate through each item and click on it
        for (ElementHandle item : items) {
            item.click();
            page.getByText("Add Members").click();
            
            

            // Locate the modal container
            Locator modal = page.locator(".MuiPaper-root.MuiPaper-elevation.MuiPaper-rounded.MuiPaper-elevation24.MuiDialog-paper.MuiDialog-paperScrollPaper.MuiDialog-paperWidthLg.css-4p7mgu[role='dialog'][aria-labelledby=':rc3:']");
            
            
            page.getByPlaceholder("Search Users").click();
            page.getByPlaceholder("Search Users").fill(adminUser);
            
            // Selector for the text within the modal
            String textSelector = "text=admin lastname";
            
            if (modal.locator(textSelector).isVisible()) {
            	page.getByText("admin lastname").click();
            	page.querySelector("input.PrivateSwitchBase-input.css-1m9pwf3[name=':rs:'][type='radio'][value='Author']").click();
                page.getByText("Save").click();
            } else {
            	page.getByText("Cancel").click();
            }
            
            page.getByText("Model Settings").click();
            
         

        }
       */
		
        /*
        // import playground v4 (only do once)
        
        page.click("//a[@data-tour='nav-app-library']");
        page.getByText("Create New App").click();
        page.getByText("Upload").click();
        
        // Locate the file input element associated with the "Browse" button
        Locator fileInput = page.locator("input[type='file']");

        // Set the file to be uploaded
        fileInput.setInputFiles(Paths.get(ConfigUtils.getValue("playgroundAppFile")));
  
        page.getByText("Next").click();
       
        page.getByLabel("Name").click();
        page.getByLabel("Name").fill("Playground v04");
        
        page.getByLabel("Description").click();
        page.getByLabel("Description").fill("playground");
        
        page.getByText("Next").click();
        page.getByText("Next").click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Upload")).click();
        
        */
        // navigate to playground v4
        page.getByText("Playground v04").click();
        
        // now on Playground app
        
        // click on drop down for models 
        // page.locator("div[role='combobox'][aria-controls=':r1:'][aria-expanded='false'][aria-haspopup='listbox'].MuiSelect-select.MuiSelect-outlined.MuiInputBase-input.MuiOutlinedInput-input.MuiInputBase-inputSizeSmall.css-15unbrw").click();
        
        
        
		
		// write out test cases for each playground test
		
	
	}

}
