package aicore.base;

import java.io.IOException;
import java.nio.file.Path;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Browser.NewContextOptions;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import aicore.utils.ConfigUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class AICoreTestBase {
	private static final Logger LOGGER = LogManager.getLogger(AICoreTestBase.class);

	public static Page page;
	private BrowserContext context;
	private Browser browser;
	private Playwright playwright;

	private static boolean initialize = true;

	public AICoreTestBase() throws IOException {
		LOGGER.info("Starting AICoreTestBase");
		if (initialize) {
			initialize = false;
			GenericSetupUtils.initialize();
		}

		this.playwright = Playwright.create();
		LaunchOptions lp = GenericSetupUtils.getLaunchOptions();
		this.browser = playwright.chromium().launch(lp);

		NewContextOptions co = GenericSetupUtils.getContextOptions();
		this.context = browser.newContext(co);

		if (Boolean.parseBoolean(ConfigUtils.getValue("use_trace"))) {
			this.context.tracing().start(GenericSetupUtils.getStartOptions());
		}
		page = context.newPage();

		GenericSetupUtils.setupLoggers(page);
	}

	@Before
	public void launchApp() throws IOException {
		page.navigate(ConfigUtils.getValue("baseUrl"));
	}

	@After
	public void tearDown(Scenario scenario) throws IOException { // Clean up after tests are done
		Path p = null;
		if (Boolean.parseBoolean(ConfigUtils.getValue("use_video"))) {
			p = page.video().path();
		}
		
		
		page.close();
		GenericTeardownUtils.saveTrace(scenario, context);
		context.close();
		GenericTeardownUtils.saveVideo(scenario, p);
		browser.close();
		playwright.close();
	}
}
