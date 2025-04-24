package aicore.dev;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.Tracing;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;

import aicore.base.GenericSetupUtils;
import aicore.utils.ConfigUtils;
import aicore.utils.RestCaller;
import aicore.utils.UrlUtils;

/**
 * Creating main method to test all models in playground
 */

public class PlaygroundTests {

	public static void main(String[] args) throws IOException {
		Page page = setupPlaywright();
		
		String adminUser = ConfigUtils.getValue("admin_username");
		String adminPassword = ConfigUtils.getValue("admin_password");

		String cookie = login(page, adminUser, adminPassword);

		/*
		List<Map<String, Object>> engines = RestCaller.getModelEngines(cookie);
		
		grantAccessForAllEngines(page, engines, adminUser);
		*/	
			
		/*
		// import playground v4
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
		page.getByLabel("Description").fill("Playground v04");
		page.getByText("Next").click();
		*/
		
		// navigate to playground v4
		page.getByTestId("LibraryBooksOutlinedIcon").click();
		page.getByPlaceholder("Search", new Page.GetByPlaceholderOptions().setExact(true)).click();
		page.getByPlaceholder("Search", new Page.GetByPlaceholderOptions().setExact(true)).fill("play");
		
		page.getByText("Playground v04").click();
		// page.locator("div[role='combobox'][aria-haspopup='listbox']").click();
		
		// Wait for the iframe to be available and get the iframe element using the class attribute
        FrameLocator iframe = page.frameLocator("iframe.css-2n02x3");
        
        // Locate the dropdown element within the iframe and click to open it
        Locator dropdown = iframe.locator("div[role='combobox']");
        dropdown.click();

        // Wait for the dropdown options to be visible
        Locator options = iframe.locator("ul[role='listbox'] li[role='option']");
        options.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

        // Get the count of option elements
        int count = options.count();

        // Iterate through each option element and click it
        for (int i = 0; i < count; i++) {
            options.nth(i).click();
            // Optionally, add a delay between clicks if needed
            page.waitForTimeout(500); // 500 milliseconds delay
            
            iframe.locator("textarea[placeholder='Ask a question...']").click();
    		iframe.locator("textarea[placeholder='Ask a question...']").fill("how to tie my shoes");
    		iframe.locator("[data-testid='SendRoundedIcon']").click();
    		
    		page.waitForTimeout(5000); // 500 milliseconds delay

            // Re-open the dropdown if needed
            if (i < count - 1) {
                dropdown.click();
                options.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            }
        }

        /*
        // Locate an element within the iframe
        Locator elementInIframe = iframe.locator("div[role='combobox']");
        // "//h3[text()='Welcome']"
        
        boolean x = elementInIframe.isVisible();
        System.out.println(x);
        
		// write out test cases for each playground test

	}

	private static void grantAccessForAllEngines(Page page, List<Map<String, Object>> engines, String userId) {
		page.getByTestId("Settings-icon").click();
		// click on model settings
		page.getByText("Model Settings").click();
		// Toggle to Admin On
		page.getByText("Admin Off").click();

		// Iterate through each item and click on it
		for (Map<String, Object> engineInfo : engines) {
			String engineId = engineInfo.get("engine_id").toString();
			String modelPage = UrlUtils.getUrl("#/settings/model/" + engineId);
			page.navigate(modelPage);
			page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add Members")).click();

			Locator dialog = page.getByRole(AriaRole.DIALOG);

			page.getByPlaceholder("Search Users").click();
			page.getByPlaceholder("Search Users").fill(userId);

			// Selector for the text within the modal
			String adminUserText = "Name: "+userId+" lastname";
			if (page.getByTitle(adminUserText).isVisible()) {
				page.getByTitle(adminUserText).click();
				dialog.locator("input[type='radio'][value='Author']").click();
				page.getByText("Save").click();
			} else {
				page.getByText("Cancel").click();
			}
		}
	}

	private static String login(Page page, String adminUser, String adminPassword) {
		// user in on home page after login
		// going to login
		page.navigate(UrlUtils.getUrl("#/login"));
		page.getByTestId("CloseIcon").click();
		page.getByLabel("Username").click();
		page.getByLabel("Username").fill(adminUser);
		page.getByLabel("Username").press("Tab");
		page.locator("input[type=\"password\"]").fill(adminPassword);
		/*
		Response response = page.waitForResponse(UrlUtils.getApi("api/auth/login"),
				() -> page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click());
		*/
		Response response = page.waitForResponse(UrlUtils.getApi("api/auth/login"),
                () -> page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login").setExact(true)).first().click());
		assertEquals(200, response.status());

		String cookie = response.allHeaders().get("set-cookie").split("; ")[0];
		Map<String, String> newMap = new HashMap<>();
		newMap.put("cookie", cookie);
		page.setExtraHTTPHeaders(newMap);
		page.reload();
		page.waitForLoadState(LoadState.DOMCONTENTLOADED);
		// page.waitForLoadState(LoadState.NETWORKIDLE);
		page.waitForLoadState(LoadState.LOAD);
		GenericSetupUtils.navigateToHomePage(page);
		return cookie;
	}

	private static Page setupPlaywright() {
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
		return page;
	}

}
