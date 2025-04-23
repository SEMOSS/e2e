package aicore.dev;

import java.util.Arrays;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;

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
		// run once
//		GenericSetupUtils.createUsers(page);
		String adminUser = ConfigUtils.getValue("admin_username");
		String adminPassword = ConfigUtils.getValue("admin_password");
		
		// user in on home page after login
		GenericSetupUtils.login(page, adminUser, adminPassword);
		
		// TODO go to settings page
		
		// click on model settings
		
		// assign user to every model
		
		
		
		// navigate to playground v4
		
		// write out test cases for each playground test
		
	
	}

}
