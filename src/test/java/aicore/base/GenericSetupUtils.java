package aicore.base;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

import aicore.framework.ConfigUtils;
import aicore.framework.Resource;
import aicore.framework.ResourcePool;
import aicore.framework.UrlUtils;
import aicore.utils.AICorePageUtils;
import e2e.HttpLogger;

public class GenericSetupUtils {

	private static final Logger logger = LogManager.getLogger(GenericSetupUtils.class);
	private static boolean useDocker = false;
	private static boolean useVideo = false;
	private static boolean useTrace = false;
	private static final String SEMOSS_MENU_DATA_TESID = "MenuRoundedIcon";
//	private static final String SEMOSS_OPEN_MEN_DATA_XPATH = "//a[@aria-label='Go Home']/parent::div//*[@data-testid='CloseIcon']";
	private static final String SEMOSS_OPEN_MEN_DATA_TESTID = "MenuOpenRoundedIcon";

	public static void initialize() throws IOException {
		if (RunInfo.isFirstRun()) {
			doInit();
		}
	}

	private static void doInit() throws IOException {
		logCheck();

		loadEnv();
		loadUrls();

		useDocker = Boolean.parseBoolean(ConfigUtils.getValue("use_docker"));
		useVideo = Boolean.parseBoolean(ConfigUtils.getValue("use_video"));
		useTrace = Boolean.parseBoolean(ConfigUtils.getValue("use_trace"));
		logger.info("docker: {}, videos: {}, traces: {}", useDocker, useVideo, useTrace);

		// shouldn't matter if this is docker or not, should still just ping server
		// you're trying
		// to connect to
		DockerUtils.startup();

		if (useVideo) {
			Path p = Paths.get("videos");
			logger.info("Videos will be saved to: {}", p.toString());
			if (Files.isDirectory(p)) {
				logger.info("Cleaning directory: {}", p.toString());
				FileUtils.deleteDirectory(p.toFile());
				Files.createDirectory(p);
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

		initializeResources();
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

	private static void loadEnv() throws IOException {

		Path file = Paths.get(".env");
		Map<String, String> projectEnvironment = getEnvironment(file);

		if (Files.exists(Paths.get(".env.local"))) {
			projectEnvironment.putAll(getEnvironment(Paths.get(".env.local")));
		}

		RunInfo.setEnvVariables(projectEnvironment);
	}

	private static Map<String, String> getEnvironment(Path file) throws IOException {
		return Files.readAllLines(file).stream().map(String::trim).filter(s -> !s.isEmpty())
				.filter(s -> !s.startsWith("#")).filter(s -> s.contains("=")).map(s -> s.split("=", 2))
				.collect(Collectors.toMap(s -> s[0].trim(), s -> s.length > 1 ? s[1].trim() : ""));
	}

	private static void loadUrls() {
		String environmentUrls = RunInfo.getEnvVariables().get("URLS");
		List<String> urls = new ArrayList<>();
		int parallelCount = RunInfo.getParallelism();
		// Check to see if urls were set manually. If so, use them
		if (environmentUrls != null && !environmentUrls.isEmpty()) {
			logger.info("Environment urls were set manually: {}", environmentUrls);
			String[] arr = environmentUrls.split(",");
			for (int i = 0; i < parallelCount; i++) {
				String s = arr[i].trim();
				if (!s.endsWith("/")) {
					s = s + "/";
				}
				urls.add(s);
			}
		} else {
			// Generate URLS based off of how docker containers are generated
			String urlStart = "http://e2e-semoss-";
			String urlEnd = ":8080/";
			for (int i = 1; i <= parallelCount; i++) {
				String url = urlStart + i + urlEnd;
				urls.add(url);
			}
		}

		RunInfo.setURLS(urls);
	}

	private static void initializeResources() {
		int parallelCount = RunInfo.getParallelism();
		List<String> urls = RunInfo.getUrls();
		List<Resource> resources = new ArrayList<>();
		for (int i = 0; i < parallelCount; i++) {
			String url = urls.get(i);
			Resource r = new Resource(url, i);
			resources.add(r);
		}

		ResourcePool.init(resources);
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

	public static void reset() {

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
//		Locator isMenuOpen = page.locator(SEMOSS_OPEN_MEN_DATA_XPATH);
		Locator isMenuOpen = page.getByTestId(SEMOSS_OPEN_MEN_DATA_TESTID);
		page.waitForTimeout(300);
		if (isMenuOpen.isVisible()) {
//			isMenuOpen.click();
			isMenuOpen.dblclick();
		}
		Locator locator = page.getByTestId(SEMOSS_MENU_DATA_TESID);
		AICorePageUtils.waitFor(locator);
		locator.click();
		page.getByTestId("SettingsIcon").click();
		// close menu
		Locator menuOpen = page.getByTestId(SEMOSS_OPEN_MEN_DATA_TESTID);
		if (menuOpen.isVisible()) {
//			menuOpen.click();
			menuOpen.dblclick();
		}
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
//		Locator isMenuOpen = page.locator(SEMOSS_OPEN_MEN_DATA_XPATH);
		Locator isMenuOpen = page.getByTestId(SEMOSS_OPEN_MEN_DATA_TESTID);
		if (isMenuOpen.isVisible()) {
			// isMenuOpen.click();
			isMenuOpen.dblclick();
		}
		Locator locator = page.getByTestId(SEMOSS_MENU_DATA_TESID);
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
		String url = UrlUtils.getUrl("#/login");
		page.navigate(url);
		Locator acceptBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Accept"));
		if (acceptBtn.isVisible()) {
			try {
				acceptBtn.click();
			} catch (Error | Exception e) {
				logger.info("Failed to click Accept button: " + e.getMessage());
			}
		}
// Added page reload because after adding a new user in app and logging in with that user, the page needs to be refreshed.
		page.reload();
		page.waitForLoadState(LoadState.NETWORKIDLE);
		page.getByTestId("loginPage-textField-username").click();
		page.getByTestId("loginPage-textField-username").fill(user);
		page.getByTestId("loginPage-textField-password").click();
		page.getByTestId("loginPage-textField-password").fill(password);
// Commented below code due to the set-cookie header removed from api/auth/login.

//		Response response = page.waitForResponse(UrlUtils.getApi("api/auth/login"),
//				() -> page.getByTestId("loginPage-button-login").click());
//
//		assertEquals(200, response.status());
//		String cookie = response.allHeaders().get("set-cookie").split("; ")[0];
//		Map<String, String> newMap = new HashMap<>();
//		newMap.put("cookie", cookie);
//		page.setExtraHTTPHeaders(newMap);
//		page.reload();
//		page.waitForLoadState(LoadState.DOMCONTENTLOADED);
//		page.waitForLoadState(LoadState.NETWORKIDLE);
//		page.waitForLoadState(LoadState.LOAD);
//		navigateToHomePage(page);
//		return cookie;
		Response response = page.waitForResponse(
				resp -> resp.url().contains("/api/auth/login") && resp.request().method().equals("POST"),
				() -> page.getByTestId("loginPage-button-login").click());
		assertEquals(200, response.status());
		page.waitForLoadState(LoadState.NETWORKIDLE);
		navigateToHomePage(page);
		return "Login Successful";
	}

	public static void navigateToHomePage(Page page) {
		String homePage = UrlUtils.getUrl("#/");
		try {
			logger.info("Navigating to : {}\nCurrent: {}\nContinuing anyway", homePage, page.url());
			page.navigate(homePage);
			page.waitForURL(homePage);
		} catch (Throwable t) {
			logger.warn("Waiting for: {}\nCurrent: {}\nContinuing anyway", homePage, page.url());
		}
	}

	public static void loginWithMSuser(Page page, String Username, String Password) {
		String url = UrlUtils.getUrl("#/login");
		page.navigate(url);
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
		String url = UrlUtils.getUrl("#/login");
		page.navigate(url);
		page.waitForURL(url);
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
