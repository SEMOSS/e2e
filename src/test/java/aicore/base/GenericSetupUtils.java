package aicore.base;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Browser.NewContextOptions;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.Tracing;
import com.microsoft.playwright.Tracing.StartOptions;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;

import aicore.utils.ConfigUtils;
import aicore.utils.UrlUtils;
import e2e.HttpLogger;

public class GenericSetupUtils {

	private static final Logger LOGGER = LogManager.getLogger(GenericSetupUtils.class);
	private static boolean useDocker = false;
	private static boolean testDocker = false;


	public static void initialize() throws IOException {
		if (RunInfo.isFirstRun()) {
			doInit();
		}
	}

	private static void doInit() throws IOException {
		logCheck();

		useDocker = Boolean.parseBoolean(ConfigUtils.getValue("use_docker"));
		boolean useVideo = Boolean.parseBoolean(ConfigUtils.getValue("use_video"));
		boolean useTrace = Boolean.parseBoolean(ConfigUtils.getValue("use_trace"));
		LOGGER.info("docker: {}, videos: {}, traces: {}", useDocker, useVideo, useTrace);
		
		// if you are going to keep docker running and run tests
		testDocker = Boolean.parseBoolean(ConfigUtils.getValue("test_docker"));

		if (useDocker) {
			DockerUtils.startup();
		}

		if (useVideo) {
			Path p = Paths.get("videos");
			LOGGER.info("Videos will be saved to: {}", p.toString());
			if (Files.isDirectory(p)) {
				LOGGER.info("Cleaning directory: {}", p.toString());
				FileUtils.cleanDirectory(p.toFile());
			}
		}

		if (useTrace) {
			Path trace = Paths.get("traces");
			LOGGER.info("Traces will be saved to: {}", trace);
			if (Files.isDirectory(trace)) {
				LOGGER.info("Cleaning directory: {}", trace.toString());
				FileUtils.cleanDirectory(trace.toFile());
			}
		}
	}

	public static void logCheck() {
		LOGGER.info("Log check");
		LOGGER.info("INFO");
		LOGGER.debug("DEBUG");
		LOGGER.warn("WARN");
		LOGGER.error("ERROR");
		LOGGER.fatal("FATAL");
		LOGGER.info("Log check end");
	}

	public static LaunchOptions getLaunchOptions() {
		LaunchOptions lp = new LaunchOptions();
		lp.setChannel(ConfigUtils.getValue("browserType"));
		lp.setHeadless(Boolean.parseBoolean(ConfigUtils.getValue("headless")));
		lp.setSlowMo(Double.parseDouble(ConfigUtils.getValue("slowmo")));
		lp.setTimeout(Double.parseDouble(ConfigUtils.getValue("timeout")));

		return lp;
	}

	public static NewContextOptions getContextOptions() {
		NewContextOptions co = new Browser.NewContextOptions();
		if (Boolean.parseBoolean(ConfigUtils.getValue("use_video"))) {
			co.setRecordVideoDir(Paths.get("videos"));
			co.setRecordVideoSize(1920, 1080);
			co.setViewportSize(1920, 1080);
		}
		
		if (Boolean.parseBoolean(ConfigUtils.getValue("use_state"))) {
			co.setStorageStatePath(Paths.get("state.json"));
		}
		return co;
	}
	
	
	public static StartOptions getStartOptions() {
		StartOptions so = new Tracing.StartOptions();
		so.setScreenshots(true);
		so.setSnapshots(true);
		so.setSources(true);
		return so;
	}

	public static void setupLoggers(Page page) {
		// request handling
		page.onRequest(HttpLogger::logRequest);
		
		// response handling
		page.onResponse(HttpLogger::logResponse);
	}

	// create user and login and logout
	public static void createUsers() {
		// setup admin user
		String adminUser = ConfigUtils.getValue("native_username");
		String adminPassword = ConfigUtils.getValue("native_password");
		Page page = AICoreTestManager.getPage();
		setupInitialAdmin(page, adminUser);

		// test admin user login
		page = AICoreTestManager.newPage();
		registerUser(page, adminUser, adminPassword);
//		login(page, adminUser, adminPassword);
//		logout(page);

		
		// add admin 2
		page = AICoreTestManager.newPage();
		String adminUser2 = ConfigUtils.getValue("admin_username");
		String adminPassword2 = ConfigUtils.getValue("admin_password");
		registerUser(page, adminUser2, adminPassword2);

		
		// add author
		page = AICoreTestManager.newPage();
		String authorUser = ConfigUtils.getValue("author_username");
		String authorPassword = ConfigUtils.getValue("author_password");
		registerUser(page, authorUser, authorPassword);

		page = AICoreTestManager.newPage();
		String editorUser = ConfigUtils.getValue("editor_username");
		String editorPassword = ConfigUtils.getValue("editor_password");
		registerUser(page, editorUser, editorPassword);

		page = AICoreTestManager.newPage();
		String readUser = ConfigUtils.getValue("read_username");
		String readPassword = ConfigUtils.getValue("read_password");
		registerUser(page, readUser, readPassword);
	}


	private static void logout(Page page) {
		// going to logout
		page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^SEMOSS$")))
				.getByRole(AriaRole.BUTTON).click();
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Logout")).click();

		page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Welcome!")).click();
		assertEquals(UrlUtils.getUrl("#/login"), page.url());
	}

	public static void login(Page page, String nativeUsername, String nativePassword) {
		// going to login
		page.navigate(UrlUtils.getUrl("#/login"));
		page.getByLabel("Username").click();
		page.getByLabel("Username").fill(nativeUsername);
		page.getByLabel("Username").press("Tab");
		page.locator("input[type=\"password\"]").fill(nativePassword);
		Response response = page.waitForResponse(UrlUtils.getApi("api/auth/login"), () -> page
				.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login with native")).click());
		assertEquals(200, response.status());
		page.waitForLoadState(LoadState.NETWORKIDLE);
		page.waitForLoadState(LoadState.LOAD);
		page.navigate(UrlUtils.getUrl("#"));
		page.waitForLoadState(LoadState.NETWORKIDLE);
		page.waitForLoadState(LoadState.LOAD);
		page.waitForURL(UrlUtils.getUrl("#"));
	}

	private static void setupInitialAdmin(Page page, String userName) {
		page.navigate(UrlUtils.getApi("setAdmin/"));

		LOGGER.info("Page is: {}", page.url());
		assertEquals(UrlUtils.getApi("setAdmin/"), page.url());
		LOGGER.info("Going to fill initial admin.");
		page.locator("#user-id").click();
		page.locator("#user-id").fill(userName);
		LOGGER.info("Filled initial admin");
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();
		LOGGER.info("After submitting admin: {}", page.url());
	}

	private static void registerUser(Page page, String userName, String password) {
		page.navigate(UrlUtils.getUrl("#/login"));
		page.waitForURL(UrlUtils.getUrl("#/login"));
		page.getByText("Log in below").click();
		assertThat(page.getByRole(AriaRole.PARAGRAPH)).containsText("Log in below");
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Register Now")).click();
		page.getByText("Register below").click();
		assertThat(page.getByRole(AriaRole.PARAGRAPH)).containsText("Register below");

		List<Locator> inputs = page.locator("input[type='text']").all();
		List<Locator> visible = new ArrayList<>();
		for (Locator l : inputs) {
			if (l.isVisible()) {
				visible.add(l);
			}
		}

		visible.get(0).click();
		visible.get(0).fill(userName);

		visible.get(1).click();
		visible.get(1).fill("lastname");

		visible.get(2).click();
		visible.get(2).fill(userName);

		visible.get(3).click();
		visible.get(3).fill(userName + "@deloitte.com");

		List<Locator> passwords = page.locator("input[type='password']").all();
		List<Locator> visiblePasswords = new ArrayList<>();
		for (Locator l : passwords) {
			if (l.isVisible()) {
				visiblePasswords.add(l);
			}
		}
		visiblePasswords.get(0).click();
		visiblePasswords.get(0).fill(password);

		visiblePasswords.get(1).click();
		visiblePasswords.get(1).fill(password);
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Register Account")).click();

		page.waitForLoadState(LoadState.LOAD);
		page.getByRole(AriaRole.ALERT).click();
		assertThat(page.getByRole(AriaRole.ALERT)).containsText("Account registration successful. Log in below.");
		LOGGER.info("Account registration Done");
	}

	public static boolean useDocker() {
		return useDocker;
	}
	
	public static boolean testOnDocker() {
		return testDocker;
	}

}
