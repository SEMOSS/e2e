package aicore.utils;

import com.microsoft.playwright.*;

import aicore.base.GenericSetupUtils;
import aicore.framework.AICoreTestConstants;
import aicore.framework.ConfigUtils;
import aicore.framework.UrlUtils;
import aicore.utils.AbstractE2ETest.UserType;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.*;

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

	private Playwright playwright;
	private Browser browser;

// Key names for the ExtensionContext.Store
	private static final String BROWSER_KEY = "BROWSER";
	private static final String CONTEXT_KEY = "CONTEXT";
	private static final String PAGE_KEY = "PAGE";

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

	@Override
	public void beforeAll(ExtensionContext context) {
		try {
//			if (RunInfo.isFirstRun()) {
			loadUrl();
			GenericSetupUtils.initialize();
//			}
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
		browser = playwright.chromium().launch(GenericSetupUtils.getLaunchOptions());
	}

	@Override
	public void afterAll(ExtensionContext context) {
		if (browser    != null) browser.close();
		if (playwright != null) playwright.close();
	}

	@Override
	public void beforeEach(ExtensionContext context) {
		Browser.NewContextOptions newContextOptions = GenericSetupUtils.getContextOptions().setViewportSize(1280, 720)
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
		GenericSetupUtils.setupLoggers(page);

		ExtensionContext.Store store = context.getStore(ExtensionContext.Namespace.create(getClass(), context));
		store.put(CONTEXT_KEY, browserContext);
		store.put(PAGE_KEY, page);
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

}
