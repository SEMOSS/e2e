package aicore.utils.extensions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Browser.NewContextOptions;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.Tracing;
import com.microsoft.playwright.Tracing.StartOptions;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;

import aicore.base.DockerUtils;
import aicore.framework.AICoreTestConstants;
import aicore.framework.ConfigUtils;
import aicore.framework.HttpLogger;
import aicore.framework.JunitUrlUtils;
import aicore.pages.home.HomePageUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.utils.annotations.PWPage;

/*
 * This is a JUnit extension that tests can use to instantiate a set of playwright, browser, browsercontext, and page objects
 * for each test class. This is necessary for parallel execution of test classes.
 * 
 *  Injects the reference to page and PlaywrightExtension class instance to methods that declare a parameter type
 *  for these objects.
 *  
 *  Methods that require a reference to the Page object must also use th 'PWPage' tag:
 *  
 *  void exampleMethod(@PWPage page){...}
 *  
 *  for this class to inject the page.
 *  
 *  If a test method uses a MethodSource to inject more parameters, the objects injected by this class must be last
 *  parameter(s) declared in the test method signature to ensure proper parameter resolution.
 */
public class PlaywrightExtension implements BeforeAllCallback, AfterAllCallback, BeforeEachCallback, AfterEachCallback, ParameterResolver {
	private static final Logger logger = LogManager.getLogger(PlaywrightExtension.class);

    private static final AtomicBoolean firstRun = new AtomicBoolean(true);
	private Playwright playwright;
	private Browser browser;

// Key names for the ExtensionContext.Store
	private static final String BROWSER_KEY = "BROWSER";
	private static final String CONTEXT_KEY = "CONTEXT";
	private static final String PAGE_KEY = "PAGE";

	private static boolean useDocker = false;
	private static boolean useVideo = false;
	private static boolean useTrace = false;

	String adminUser;
	String adminPassword;

	String adminUser2;
	String adminPassword2;

	String authorUser;
	String authorPassword;

	String editorUser;
	String editorPassword;

	String readUser;
	String readPassword;
	boolean alreadyLoggedIn;

	public static enum UserType {
		NATIVE, ADMIN, AUTHOR, EDITOR, READER
	}
	
	public static boolean isFirstRun() {
        return firstRun.compareAndSet(true, false);
    }

	@Override
	public void beforeAll(ExtensionContext context) {
		try {
			if (isFirstRun()) {
			doInit();
			}
		} catch (IOException e) {
			fail("Init failed: " + e.getMessage());
		}

		// set up once and that's it
		adminUser = ConfigUtils.getValue(AICoreTestConstants.NATIVE_USERNAME);
		adminPassword = ConfigUtils.getValue(AICoreTestConstants.NATIVE_PASSWORD);

		adminUser2 = ConfigUtils.getValue(AICoreTestConstants.ADMIN_USERNAME);
		adminPassword2 = ConfigUtils.getValue(AICoreTestConstants.ADMIN_PASSWORD);

		authorUser = ConfigUtils.getValue(AICoreTestConstants.AUTHOR_USERNAME);
		authorPassword = ConfigUtils.getValue(AICoreTestConstants.AUTHOR_PASSWORD);

		editorUser = ConfigUtils.getValue(AICoreTestConstants.EDITOR_USERNAME);
		editorPassword = ConfigUtils.getValue(AICoreTestConstants.EDITOR_PASSWORD);

		readUser = ConfigUtils.getValue(AICoreTestConstants.READ_USERNAME);
		readPassword = ConfigUtils.getValue(AICoreTestConstants.READ_PASSWORD);
		alreadyLoggedIn = false;

		playwright = Playwright.create();
		browser = playwright.chromium().launch(getLaunchOptions());
	}
	
	public static void doInit() throws IOException {
		logCheck();
		loadUrl();
		useDocker = Boolean.parseBoolean(ConfigUtils.getValue(AICoreTestConstants.USE_DOCKER));
		useVideo = Boolean.parseBoolean(ConfigUtils.getValue(AICoreTestConstants.USE_VIDEO));
		useTrace = Boolean.parseBoolean(ConfigUtils.getValue(AICoreTestConstants.USE_TRACE));
		logger.info("docker: {}, videos: {}, traces: {}", useDocker, useVideo, useTrace);

		// shouldn't matter if this is docker or not, should still just ping server
		// you're trying
		// to connect to
		DockerUtils.startUpJunit();

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
	}

	@Override
	public void afterAll(ExtensionContext context) {
		if (browser    != null) browser.close();
		if (playwright != null) playwright.close();
	}

	@Override
	public void beforeEach(ExtensionContext context) {
		Browser.NewContextOptions newContextOptions = getContextOptions().setViewportSize(1280, 720)
				.setDeviceScaleFactor(1).setPermissions(Arrays.asList("clipboard-read", "clipboard-write"))
				.setTimezoneId("America/New_York"); // ensures DPI/zoom consistency;
		BrowserContext browserContext = browser.newContext(newContextOptions);
		browserContext.grantPermissions(Arrays.asList("clipboard-read", "clipboard-write"));

		// setup tracing
//		if (Boolean.parseBoolean(ConfigUtils.getValue(AICoreTestConstants.USE_TRACE))) {
//			Tracing.StartOptions startOptions = GenericSetupUtils.getStartOptions();
//			browserContext.tracing().start(startOptions);
//		}

		Page page = browserContext.newPage();
		page.setDefaultTimeout(Double.parseDouble(ConfigUtils.getValue(AICoreTestConstants.TIMEOUT)));
		setupLoggers(page);

		ExtensionContext.Store store = context.getStore(ExtensionContext.Namespace.create(getClass(), context));
		store.put(CONTEXT_KEY, browserContext);
		store.put(PAGE_KEY, page);
	}
	
	public static LaunchOptions getLaunchOptions() {
		LaunchOptions lp = new LaunchOptions();
		lp.setChannel(ConfigUtils.getValue(AICoreTestConstants.BROWSER_TYPE));
		lp.setHeadless(Boolean.parseBoolean(ConfigUtils.getValue(AICoreTestConstants.HEADLESS)));
		lp.setSlowMo(Double.parseDouble(ConfigUtils.getValue(AICoreTestConstants.SLOMO)));
		lp.setTimeout(Double.parseDouble(ConfigUtils.getValue(AICoreTestConstants.TIMEOUT)));
		return lp;
	}

	public static NewContextOptions getContextOptions() {
		NewContextOptions co = new Browser.NewContextOptions();
		if (Boolean.parseBoolean(ConfigUtils.getValue(AICoreTestConstants.USE_VIDEO))) {
			co.setRecordVideoDir(Paths.get("videos"));
			co.setRecordVideoSize(1920, 1080);
			co.setViewportSize(1920, 1080);
		}

		if (Boolean.parseBoolean(ConfigUtils.getValue(AICoreTestConstants.USE_STATE))) {
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

	@Override
	public void afterEach(ExtensionContext context) {
		ExtensionContext.Store store = context.getStore(ExtensionContext.Namespace.create(getClass(), context));
		Page page = store.remove(PAGE_KEY, Page.class);
		BrowserContext ctx = store.remove(CONTEXT_KEY, BrowserContext.class);
		Browser browser = store.remove(BROWSER_KEY, Browser.class);

//		try {
//			if (GenericSetupUtils.useVideo() && page != null) {
//				Path og = page.video().path();
//				Files.deleteIfExists(og);
//			}
//		} catch (Exception e) {
//			logger.warn("Failed to clean up video: {}", e.getMessage());
//		}
		if (page != null)
			page.close();
		if (ctx != null)
			ctx.close();
		if (browser != null)
			browser.close();
	}

// ParameterResolver: support injection of Page, BrowserContext, Browser
	@Override
	public boolean supportsParameter(ParameterContext paramCtx, ExtensionContext extCtx) {
		Class<?> type = paramCtx.getParameter().getType();
		if (paramCtx.isAnnotated(PWPage.class) && type == Page.class) {
			return true;
		} 
		if (type == PlaywrightExtension.class) {
			return true;
		}
		return false;
	}

	@Override
	public Object resolveParameter(ParameterContext paramCtx, ExtensionContext extCtx) {
		Class<?> type = paramCtx.getParameter().getType();
		ExtensionContext.Store store = extCtx.getStore(ExtensionContext.Namespace.create(getClass(), extCtx));
		if (type == Page.class) {
			return store.get(PAGE_KEY, Page.class);
		}
		 if (type == PlaywrightExtension.class) {
		      return this;
		    }
		throw new ParameterResolutionException("Unsupported parameter type: " + type);
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
	
	private static void loadUrl() {
		String environmentUrls = ConfigUtils.getValue(AICoreTestConstants.URLS);
		logger.info("URLS: {}", environmentUrls);
		String[] arr = environmentUrls.split(",");
		/// we should only have a single URL
		String s = arr[0].trim();
		if (!s.endsWith("/")) {
			s = s + "/";
		}

		JunitUrlUtils.setURL(s);
	}

	public void login(Page page, UserType userType) {
		if (!alreadyLoggedIn) {
			logger.info("Logging in as: " + userType);
			switch (userType) {
			case NATIVE:
				login(page, adminUser, adminPassword);
				break;
			case ADMIN:
				login(page, adminUser2, adminPassword2);
				break;
			case AUTHOR:
				login(page, authorUser, authorPassword);
				break;
			case EDITOR:
				login(page, editorUser, editorPassword);
				break;
			case READER:
				login(page, readUser, readPassword);
				break;
			default:
				throw new AssertionError(
						"Could not login in user type. Needs to be one of Native, Admin, Author, Editor, or Reader");
			}
			alreadyLoggedIn = true;
		} else {
			logger.warn("Tried to login as: " + userType + " while already logged in. Unsuccessful attempt");
		}
	}
	
	public String login(Page page, String user, String password) {
		// going to login
		String url = JunitUrlUtils.getUrl("#/login");
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
		HomePageUtils.navigateToHomePage(page, JunitUrlUtils.getUrl("#"));
		return "Login Successful";
	}

	public void logout(Page page) {
		if (alreadyLoggedIn) {
			MainMenuUtils.openMainMenu(page);
			MainMenuUtils.clickOnUserAccountButton(page);
			MainMenuUtils.logout(page);

			String loginPage = JunitUrlUtils.getUrl("#/login");
			page.waitForURL(loginPage);
			assertEquals(loginPage, page.url());
			alreadyLoggedIn = false;
		} else {
			logger.warn("Tried to logout while not logged in. Unsuccessful attempt");
		}
	}

}
