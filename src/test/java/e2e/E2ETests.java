package e2e;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Browser.NewContextOptions;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;

public class E2ETests {

	private static boolean pingSuccessful = true;

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
	private static boolean headless = true;
	private static double slowmo = 0.0;
	private static double timeout = 5000.0;
	private static LaunchOptions lo = null;

	// Registration
	private static boolean registered = false;

	// for debugging. don't have logging yet :/
	private static String apiStringEndpoint = null;

	private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
	private static String folderDateTime = LocalDateTime.now().format(dtf);

	@BeforeAll
	static void launchBrowser() throws Exception {
		if (initialize) {
			loadTestProps();
			lo = new LaunchOptions();
			lo.setHeadless(headless);
			lo.setSlowMo(slowmo);

			pingSuccessful = pingServer();

			Path p = Paths.get("videos");
			if (Files.isDirectory(p)) {
				FileUtils.cleanDirectory(p.toFile());
			}
			initialize = false;
		}

		if (!pingSuccessful) {
			fail("Cannot run tests since we cannot reach server at " + apiStringEndpoint);
		}

		playwright = Playwright.create();

		browser = playwright.chromium().launch(lo);
	}

	private static boolean pingServer() throws InterruptedException {
		int i = 0;
		boolean successful = false;
		apiStringEndpoint = getApi("/api/config");
		while (i < 10) {
			try {
				HttpURLConnection conn = (HttpURLConnection) new URL(apiStringEndpoint).openConnection();
				conn.setRequestMethod("GET");
				conn.setConnectTimeout(1000);
				int code = conn.getResponseCode();
				if (code == 200) {
					successful = true;
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			i++;
			Thread.sleep(500);
		}
		return successful;
	}

	@AfterAll
	static void closeBrowser() {
		playwright.close();
	}

	@BeforeEach
	void createContextAndPage(TestInfo ti) {
		String name = ti.getDisplayName();
		Path path = Paths.get("videos", folderDateTime, name);
		NewContextOptions co = new Browser.NewContextOptions();
		co.setRecordVideoDir(path);
		co.setViewportSize(1280, 720);
		context = browser.newContext(co);
		context.setDefaultTimeout(timeout);
		page = context.newPage();

		if (!Utility.getRegistered()) {
			nativeRegister();
			registered = true;
		}
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

	protected static String getApi(String ending) {
		return api + ending;
	}

	private void nativeRegister() {
		page.navigate(getApi("/setAdmin"));
		page.locator("#user-id").click();
		page.locator("#user-id").fill("user1");
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();
		page.navigate(getUrl("/#!/login"));
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Reject")).click();
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Register Now")).click();
		page.getByPlaceholder("First Name").click();
		page.getByPlaceholder("First Name").fill("user");
		page.getByPlaceholder("First Name").press("Tab");
		page.getByPlaceholder("Last Name").fill("one");
		page.getByPlaceholder("Last Name").press("Tab");
		page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Username")).fill("user1");
		page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Username")).press("Tab");
		page.getByPlaceholder("Email").fill("user1@deloitte.com");
		page.getByPlaceholder("Email").press("Tab");
		page.getByPlaceholder("Phone Number").press("Tab");
		page.getByPlaceholder("Phone Extension").press("Tab");
		page.getByPlaceholder("Country Code: +").press("Tab");
		page.keyboard().type("TestTest8*");
		page.keyboard().press("Tab");
		page.keyboard().type("TestTest8*");
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Register")).click();
//		page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Username")).click();
//		page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Username")).fill("user1");
//		page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Username")).press("Tab");
//		page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password")).fill("TestTest8*");
//		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Log In").setExact(true)).click();
//		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("")).click();
	}

}
