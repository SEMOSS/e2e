package aicore.base;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import aicore.utils.ConfigUtils;
import aicore.utils.UrlUtils;

public class AICoreTestManager {

	private static final Logger logger = LogManager.getLogger(AICoreTestManager.class);

	private static Page page;
	private static BrowserContext context;
	private static Browser browser;
	private static Playwright playwright;

	static {
		try {
			logger.info("Initializing Playwright");
			GenericSetupUtils.initialize();
		} catch (IOException e) {
			logger.error(e);
			throw new RuntimeException(e);
		}

		if (page == null) {
			playwright = Playwright.create();
			LaunchOptions lp = GenericSetupUtils.getLaunchOptions();
			browser = playwright.chromium().launch(lp);
			context = browser.newContext(GenericSetupUtils.getContextOptions());
			context.setDefaultTimeout(Double.parseDouble(ConfigUtils.getValue("timeout")));

			if (GenericSetupUtils.useTrace()) {
				context.tracing().start(GenericSetupUtils.getStartOptions());
			}
			newPage();
			GenericSetupUtils.setupLoggers(page);

			if (GenericSetupUtils.useDocker() && RunInfo.isNeedToCreateUser()) {
				//if testing we don't need to add default users
				if (!GenericSetupUtils.testOnDocker()) {
					logger.info("Setting up users");
					//GenericSetupUtils.createUsers();
					logger.info("Done setting up users" );
				}
			}
		}
	}

	private AICoreTestManager() {
	}

	public static Page getPage() {
		return page;
	}
	
	public static Page newPage() {
		page = context.newPage();
		return page;
	}

	public static void launchApp() throws IOException {
		String baseUrl = UrlUtils.getUrl("#/login");
		logger.info("Navigating to {}", baseUrl);
		page.navigate(baseUrl);
	}

	public static void close() {
		logger.info("Closing Playwright");
		page.close();
		context.close();
		browser.close();
		playwright.close();
	}
}
