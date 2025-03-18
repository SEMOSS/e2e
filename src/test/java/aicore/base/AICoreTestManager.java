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

public class AICoreTestManager {

	private static final Logger LOGGER = LogManager.getLogger(AICoreTestManager.class);

	private static Page page;
	private static BrowserContext context;
	private static Browser browser;
	private static Playwright playwright;

	static {
		try {
			GenericSetupUtils.initialize();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		if (page == null) {
			playwright = Playwright.create();
			LaunchOptions lp = GenericSetupUtils.getLaunchOptions();
			browser = playwright.chromium().launch(lp);
			context = browser.newContext(GenericSetupUtils.getContextOptions());

			if (Boolean.valueOf(ConfigUtils.getValue("use_trace"))) {
				context.tracing().start(GenericSetupUtils.getStartOptions());
			}
			page = context.newPage();
			GenericSetupUtils.setupLoggers(page);
		}
	}

	private AICoreTestManager() {
	}

	public static Page getPage() {
		return page;
	}

	public static void launchApp() throws IOException {
		page.navigate(ConfigUtils.getValue("baseUrl"));
	}

	public static void close() {
		page.close();
		context.close();
		browser.close();
		playwright.close();
	}
}
