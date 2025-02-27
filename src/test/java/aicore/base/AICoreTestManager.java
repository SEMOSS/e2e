package aicore.base;

import java.io.IOException;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import aicore.utils.ConfigUtils;

public class AICoreTestManager {

	private static Page page;
	private static BrowserContext context;
	private static Browser browser;
	private static Playwright playwright;

	static {
		if (page == null) {
			playwright = Playwright.create();
			LaunchOptions lp = new LaunchOptions();
			lp.setChannel(ConfigUtils.getValue("browserType"));
			lp.setHeadless(Boolean.parseBoolean(ConfigUtils.getValue("headless")));
			browser = playwright.chromium().launch(lp);
			context = browser.newContext();
			page = context.newPage();
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
