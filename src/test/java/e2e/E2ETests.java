package e2e;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class E2ETests {

	// Shared between all tests in this class.
	protected static Playwright playwright;
	protected static Browser browser;

	// New instance for each test method.
	protected BrowserContext context;
	protected Page page;

	// URL prefix
	private static String urlPrefix = null;
	private static String api = null;
	
	// setup specific
	private static boolean initialize = true;
	private static boolean headless = false;
	private static double slowmo = 0.0;
	private static double timeout = 5000.0;
	private static LaunchOptions lo = null;

	@BeforeAll
	static void launchBrowser() throws IOException {
		if (initialize) {
			loadTestProps();
			lo = new LaunchOptions();
			lo.setHeadless(headless);
			lo.setSlowMo(slowmo);
			initialize = false;
		}

		playwright = Playwright.create();

		browser = playwright.chromium().launch(lo);
	}

	@AfterAll
	static void closeBrowser() {
		playwright.close();
	}

	@BeforeEach
	void createContextAndPage() {
		context = browser.newContext();
		context.setDefaultTimeout(timeout);
		page = context.newPage();
	}

	@AfterEach
	void closeContext() {
		context.close();
	}

	static Properties loadTestProps() throws IOException {
		Properties props = new Properties();

		try (InputStream is = Files.newInputStream(Paths.get("test.props"))) {
			props.load(is);
			urlPrefix = props.get("URL_PREFIX").toString();
			headless = Boolean.valueOf(props.get("HEADLESS").toString());
			slowmo = Double.valueOf(props.get("SLOWMO").toString());
			timeout = Double.valueOf(props.get("TIMEOUT").toString());
			api = String.valueOf(props.get("API")).toString();
		}

		return props;
	}
	
	protected String getUrlPrefix() {
		return urlPrefix;
	}
	
	protected String getUrl(String ending) {
		return urlPrefix + ending;
	}
	
	protected String getApi(String ending) {
		return api + ending;
	}

}
