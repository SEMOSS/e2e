package aicore.utils;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;

import aicore.base.GenericSetupUtils;
import aicore.framework.AICoreTestConstants;
import aicore.framework.ConfigUtils;
import aicore.framework.Resource;
import aicore.framework.ResourcePool;
import aicore.framework.UrlUtils;
import aicore.pages.home.HomePageUtils;

/**
 * Superclass to initialize and shutdown e2e env for JUnit tests.
 * Uses PER_CLASS lifecycle so each test class gets its own instance
 * with isolated Playwright page/context, enabling parallel execution
 * across test classes.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Execution(ExecutionMode.CONCURRENT)
public class AbstractE2ETest {
	
	private static final Logger logger = LogManager.getLogger(AbstractE2ETest.class);

	private static final Object CONTEXT_CREATION_LOCK = new Object();
	private static Playwright playwright;
	private static Browser browser;
	private BrowserContext context;
	protected Page page;

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
	
	public enum UserType {
        NATIVE,
        ADMIN,
        AUTHOR,
        EDITOR,
        READER
    }
	
	
	@BeforeAll
	void startBrowser() {
		try {
//			if (RunInfo.isFirstRun()) {
				loadUrl();
				GenericSetupUtils.initialize();
//			}
		} catch (IOException e) {
			fail("Init failed: " + e.getMessage());
		}
		// creates these objects for each test class
		playwright = Playwright.create();
		browser = playwright.chromium().launch(GenericSetupUtils.getLaunchOptions());
		
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
	}

	@BeforeEach
	void newContextAndPage() {
		// get browser context options
		Browser.NewContextOptions newContextOptions = GenericSetupUtils.getContextOptions()
				.setViewportSize(1280, 720)
				.setDeviceScaleFactor(1)
				.setPermissions(Arrays.asList("clipboard-read", "clipboard-write"))
				.setTimezoneId("America/New_York"); // ensures DPI/zoom consistency;
		synchronized (CONTEXT_CREATION_LOCK) {
			context = browser.newContext(newContextOptions);
			context.grantPermissions(Arrays.asList("clipboard-read", "clipboard-write"));
		}

		// setup tracing
		if (Boolean.parseBoolean(ConfigUtils.getValue(AICoreTestConstants.USE_TRACE))) {
			Tracing.StartOptions startOptions = GenericSetupUtils.getStartOptions();
			context.tracing().start(startOptions);
		}

		// create page
		page = context.newPage();
		page.setDefaultTimeout(Double.parseDouble(ConfigUtils.getValue(AICoreTestConstants.TIMEOUT)));
		GenericSetupUtils.setupLoggers(page);
//		login(page, UserType.NATIVE);
	}

	@AfterEach
	void tearDown(TestInfo info) throws IOException {
		logger.info("Tearing down test: {}", info.getDisplayName());
		// optional: collect video/trace
		try {
			if (GenericSetupUtils.useVideo() && page != null) {
				Path og = page.video().path();
				Files.deleteIfExists(og);
			}
		} catch (Exception e) {
			logger.warn("Failed to clean up video: {}", e.getMessage());
		}
		logout(page);
		if (page != null)
			page.close();
		if (context != null)
			context.close();
	}

	@AfterAll
	void closeBrowser() {
		
		if (browser != null)
			browser.close();
		if (playwright != null)
			playwright.close();
	}
	
	
	
	
	
	
//	@BeforeAll
//	public void beforeAll() {
//		logger.info("Initializing setup for ALL tests");
//		
//		try {
//			if (RunInfo.isFirstRun()) {
//				/// these only need to be run by the first thread
//				loadUrl();
//				GenericSetupUtils.doInit();
//			}
//		} catch (IOException e) {
//			fail(e.getMessage());
//		}
//	
//		setupPlaywright();
//
//		adminUser = ConfigUtils.getValue(AICoreTestConstants.NATIVE_USERNAME);
//		adminPassword = ConfigUtils.getValue(AICoreTestConstants.NATIVE_PASSWORD);
//		
//		adminUser2 = ConfigUtils.getValue(AICoreTestConstants.ADMIN_USERNAME);
//		adminPassword2 = ConfigUtils.getValue(AICoreTestConstants.ADMIN_PASSWORD);
//
//		authorUser = ConfigUtils.getValue(AICoreTestConstants.AUTHOR_USERNAME);
//		authorPassword = ConfigUtils.getValue(AICoreTestConstants.AUTHOR_PASSWORD);
//
//		editorUser = ConfigUtils.getValue(AICoreTestConstants.EDITOR_USERNAME);
//		editorPassword = ConfigUtils.getValue(AICoreTestConstants.EDITOR_PASSWORD);
//
//		readUser = ConfigUtils.getValue(AICoreTestConstants.READ_USERNAME);
//		readPassword = ConfigUtils.getValue(AICoreTestConstants.READ_PASSWORD);
//		alreadLoggedIn = false;
//	}
//
//	private void setupPlaywright() {
//		// create playwright
//		playwright = Playwright.create();
//		// setup browser
//		browser = playwright.chromium().launch(GenericSetupUtils.getLaunchOptions());
//		// get browser context options
//		Browser.NewContextOptions newContextOptions = GenericSetupUtils.getContextOptions().setViewportSize(1280, 720)
//				.setDeviceScaleFactor(1).setPermissions(Arrays.asList("clipboard-read", "clipboard-write"))
//				.setTimezoneId("America/New_York"); // ensures DPI/zoom consistency;
//		context = browser.newContext(newContextOptions);
//		context.grantPermissions(Arrays.asList("clipboard-read", "clipboard-write"));
//
//		// setup tracing
//		if (Boolean.parseBoolean(ConfigUtils.getValue(AICoreTestConstants.USE_TRACE))) {
//			Tracing.StartOptions startOptions = GenericSetupUtils.getStartOptions();
//			context.tracing().start(startOptions);
//		}
//
//		// create page
//		page = context.newPage();
//		page.setDefaultTimeout(Double.parseDouble(ConfigUtils.getValue(AICoreTestConstants.TIMEOUT)));
//
//		GenericSetupUtils.setupLoggers(page);
//	}
	
	private static void loadUrl() {
		String environmentUrls = ConfigUtils.getValue(AICoreTestConstants.URLS);
		logger.info("URLS: {}", environmentUrls);
		String[] arr = environmentUrls.split(",");
		/// we should only have a single URL
		String s = arr[0].trim();
		if (!s.endsWith("/")) {
			s = s + "/";
		}

		UrlUtils.setURL(s);
	}
	
	public void login(Page page, UserType userType) {
		if (!alreadyLoggedIn) {
			logger.info("Logging in as: " + userType);
			switch (userType) {
			case NATIVE:
				GenericSetupUtils.login(page, adminUser, adminPassword);
				break;
			case ADMIN:
				GenericSetupUtils.login(page, adminUser2, adminPassword2);
				break;
			case AUTHOR:
				GenericSetupUtils.login(page, authorUser, authorPassword);
				break;
			case EDITOR:
				GenericSetupUtils.login(page, editorUser, editorPassword);
				break;
			case READER:
				GenericSetupUtils.login(page, readUser, readPassword);
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
	
	public void logout(Page page) {
		if (alreadyLoggedIn) {
			GenericSetupUtils.logout(page);
			alreadyLoggedIn = false;
		} else {
			logger.warn("Tried to logout while not logged in. Unsuccessful attempt");
		}
	}
	
//	@AfterEach
//	public void afterEach() {
//		if (page != null && !page.isClosed()) {
//			logger.info("Navigating home");
//			try {
//				HomePageUtils.navigateToHomePage(page);
//			} catch (Exception e) {
//				logger.warn("Failed to navigate home after test: {}", e.getMessage());
//			}
//		}
//	}
//	
//	@AfterAll
//	public void afterAll() throws IOException {
//		logger.info("Cleaning up after ALL tests");
//		try {
//			if (page != null && !page.isClosed()) {
//				GenericSetupUtils.logout(page);
//			}
//		} catch (Exception e) {
//			logger.warn("Failed to logout during cleanup: {}", e.getMessage());
//		}
//		alreadLoggedIn = false;
//		
//		// TODO modify this to use the 'fail' logic in SetupHooks.java
//		try {
//			if (GenericSetupUtils.useVideo() && page != null) {
//				Path og = page.video().path();
//				Files.deleteIfExists(og);
//			}
//		} catch (Exception e) {
//			logger.warn("Failed to clean up video: {}", e.getMessage());
//		}
//		
//		try {
//			if (page != null && !page.isClosed()) {
//				page.close();
//			}
//		} catch (Exception e) {
//			logger.warn("Failed to close page: {}", e.getMessage());
//		}
//		
//		try {
//			if (context != null) {
//				context.close();
//			}
//		} catch (Exception e) {
//			logger.warn("Failed to close browser context: {}", e.getMessage());
//		}
//		
//		try {
//			if (browser != null) {
//				browser.close();
//			}
//		} catch (Exception e) {
//			logger.warn("Failed to close browser: {}", e.getMessage());
//		}
//		
//		try {
//			if (playwright != null) {
//				playwright.close();
//			}
//		} catch (Exception e) {
//			logger.warn("Failed to close playwright: {}", e.getMessage());
//		}
//	}
}
