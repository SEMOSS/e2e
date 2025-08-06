package aicore.base;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import aicore.utils.AICorePageUtils;
import aicore.utils.ConfigUtils;
import aicore.utils.UrlUtils;
import e2e.HttpLogger;

public class GenericSetupUtils {

	private static final Logger logger = LogManager.getLogger(GenericSetupUtils.class);
	private static boolean useDocker = false;
	private static boolean useVideo = false;
	private static boolean useTrace = false;

	public static void initialize() throws IOException {
		if (RunInfo.isFirstRun()) {
			doInit();
		}
	}

	private static void doInit() throws IOException {
		logCheck();

		useDocker = Boolean.parseBoolean(ConfigUtils.getValue("use_docker"));
		useVideo = Boolean.parseBoolean(ConfigUtils.getValue("use_video"));
		useTrace = Boolean.parseBoolean(ConfigUtils.getValue("use_trace"));
		logger.info("docker: {}, videos: {}, traces: {}", useDocker, useVideo, useTrace);

		if (useDocker) {
			DockerUtils.startup();
		}

		if (useVideo) {
			Path p = Paths.get("videos");
			logger.info("Videos will be saved to: {}", p.toString());
			if (Files.isDirectory(p)) {
				logger.info("Cleaning directory: {}", p.toString());
				FileUtils.cleanDirectory(p.toFile());
			}
		}

		if (useTrace) {
			Path trace = Paths.get("traces");
			logger.info("Traces will be saved to: {}", trace);
			if (Files.isDirectory(trace)) {
				logger.info("Cleaning directory: {}", trace.toString());
				FileUtils.cleanDirectory(trace.toFile());
			}
		}
	}

	public static void logCheck() {
		logger.info("Log check");
		logger.info("INFO");
		logger.debug("DEBUG");
		logger.warn("WARN");
		logger.error("ERROR");
		logger.fatal("FATAL");
		logger.info("Log check end");
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
	public static void createUsers(Page page) {
		// setup admin user
		String adminUser = ConfigUtils.getValue("native_username");
		String adminPassword = ConfigUtils.getValue("native_password");

		setupInitialAdmin(page, adminUser);

		// test admin user login
		registerUser(page, adminUser, adminPassword);

		String adminUser2 = ConfigUtils.getValue("admin_username");
		String adminPassword2 = ConfigUtils.getValue("admin_password");
		registerUser(page, adminUser2, adminPassword2);

		// login and make admin and admin and logout
		login(page, adminUser, adminPassword);
		makeAdminUserAdmin(page);
		logout(page);

		String authorUser = ConfigUtils.getValue("author_username");
		String authorPassword = ConfigUtils.getValue("author_password");
		registerUser(page, authorUser, authorPassword);

		String editorUser = ConfigUtils.getValue("editor_username");
		String editorPassword = ConfigUtils.getValue("editor_password");
		registerUser(page, editorUser, editorPassword);

		String readUser = ConfigUtils.getValue("read_username");
		String readPassword = ConfigUtils.getValue("read_password");
		registerUser(page, readUser, readPassword);
	}

	private static void makeAdminUserAdmin(Page page) {
		page.getByLabel("Navigate to settings").click();
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Admin Off")).click();
		page.getByText("Member Settings").click();
		try {
			page.getByRole(AriaRole.ROW, new Page.GetByRoleOptions().setName("A admin lastname")).getByLabel("Admin")
					.check();
		} catch (Throwable t) {
			logger.warn("This works, but throws anyway, just catch it and move on: {}", t.getMessage());
		}
	}

	public static void logout(Page page) {
		// going to logout
//		Locator menuOpen = page.getByTestId("CloseIcon");
//		if (!menuOpen.isVisible()) {
//			Locator locator = page.getByTestId("MenuRoundedIcon");
//			locator.click();
//			menuOpen.click();
//		}
		Locator isMenuOpen = page
				.locator("//a[@aria-label='Go Home']/parent::div//*[name()='svg'][@data-testid='CloseIcon']");
		// page.waitForTimeout(1000);
		if (isMenuOpen.isVisible()) {
			isMenuOpen.click();
		}
		Locator locator = page.getByTestId("MenuRoundedIcon");
		AICorePageUtils.waitFor(locator);
		locator.click();

		page.getByTestId("AccountCircleRoundedIcon").click();
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Logout")).click();

		page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Welcome!")).click();
		String loginPage = UrlUtils.getUrl("#/login");
		page.waitForURL(loginPage);
		assertEquals(loginPage, page.url());
	}

	public static String login(Page page, String user, String password) {
		// going to login
		page.navigate(UrlUtils.getUrl("#/login"));
		page.getByLabel("Username").click();
		page.getByLabel("Username").fill(user);
		page.getByLabel("Username").press("Tab");
		page.locator("input[type=\"password\"]").fill(password);
		Response response = page.waitForResponse(UrlUtils.getApi("api/auth/login"),
				() -> page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click());

		assertEquals(200, response.status());

		String cookie = response.allHeaders().get("set-cookie").split("; ")[0];
		Map<String, String> newMap = new HashMap<>();
		newMap.put("cookie", cookie);
		page.setExtraHTTPHeaders(newMap);
		page.reload();
		page.waitForLoadState(LoadState.DOMCONTENTLOADED);
		page.waitForLoadState(LoadState.NETWORKIDLE);
		page.waitForLoadState(LoadState.LOAD);
		navigateToHomePage(page);
		return cookie;
	}

	public static void navigateToHomePage(Page page) {
		String homePage = UrlUtils.getUrl("#");
		page.navigate(homePage);
		try {
			page.waitForURL(homePage);
		} catch (Throwable t) {
			logger.warn("Waiting for: {}\nCurrent: {}\nContinuing anyway", homePage, page.url());
		}
	}

	public static void loginWithMSuser(Page page, String Username, String Password) {
		page.navigate(UrlUtils.getUrl("#/login"));
		page.locator("//div[@class='MuiStack-root css-bcmwpg']//button").click();
		Page page1 = page.waitForPopup(() -> {
			page.locator("//span[(text()='Deloitte Login')]").click();
		});
		page1.locator("//input[@type='email']").fill(Username);
		page1.locator("#idSIButton9").click();
		page1.locator("input[type=\"password\"]").fill(Password);
		page1.locator("//input[@data-report-event='Signin_Submit']").click();
		page.waitForLoadState(LoadState.DOMCONTENTLOADED);
		page.waitForLoadState(LoadState.NETWORKIDLE);
		page.waitForLoadState(LoadState.LOAD);
		String waitingForUrl = UrlUtils.getUrl("#");
		try {
			page.waitForURL(waitingForUrl);
		} catch (Throwable t) {
			logger.warn("Waiting for: {}\nCurrent: {}\nContinuing anyway", waitingForUrl, page.url());
		}
	}

	private static void setupInitialAdmin(Page page, String userName) {
		page.navigate(UrlUtils.getApi("setAdmin/"));

		logger.info("Page is: {}", page.url());
		assertEquals(UrlUtils.getApi("setAdmin/"), page.url());
		logger.info("Going to fill initial admin.");
		page.locator("#user-id").click();
		String userIdString = userName;
		logger.info("filling user ids with: {}", userIdString);
		page.locator("#user-id").fill(userIdString);
		logger.info("Filled initial admin");
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();
		logger.info("After submitting admin: {}", page.url());
	}

	private static void registerUser(Page page, String userName, String password) {
		page.navigate(UrlUtils.getUrl("#/login"));
		page.waitForURL(UrlUtils.getUrl("#/login"));
		page.reload();
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
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Register")).click();

		// page.waitForLoadState(LoadState.LOAD);
		page.getByRole(AriaRole.ALERT).click();
		assertThat(page.getByRole(AriaRole.ALERT)).containsText("Account registration successful. Log in below.");
		logger.info("Account registration Done");
	}

	public static boolean useDocker() {
		return useDocker;
	}

	public static boolean useVideo() {
		return useVideo;
	}

	public static boolean useTrace() {
		return useTrace;
	}
}
