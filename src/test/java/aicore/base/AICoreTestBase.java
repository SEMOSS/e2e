package aicore.base;

import java.io.IOException;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import aicore.utils.ConfigUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class AICoreTestBase {
  
	public Page page;
	private BrowserContext context ; 
	private Browser browser; 
	private Playwright playwright; 
	
	public AICoreTestBase(){
		this.playwright = Playwright.create();
		LaunchOptions lp = new LaunchOptions();
		lp.setChannel(ConfigUtils.getValue("browserType"));
		lp.setHeadless(Boolean.parseBoolean(ConfigUtils.getValue("headless")));
		this.browser = playwright.chromium().launch(lp);
		this.context = browser.newContext();
		page = context.newPage();
	}

	@Before
	public void launchApp() throws IOException {
		page.navigate(ConfigUtils.getValue("baseUrl"));
	}

	@After
	public void tearDown() { // Clean up after tests are done
		page.close();
		context.close();
		browser.close();
		playwright.close();
	}
}
