package aicore.utils;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import com.microsoft.playwright.Page;

import aicore.base.GenericSetupUtils;
import aicore.framework.AICoreTestConstants;
import aicore.framework.ConfigUtils;
import aicore.framework.ResourcePool;
import aicore.pages.home.HomePageUtils;

/**
 * Superclass to initialize and shutdown e2e env for JUnit tests
 */
public class AbstractE2ETest {
	
	private static final Logger logger = LogManager.getLogger(AbstractE2ETest.class);

	// `page` is a proxy shared across test classes, but each worker thread resolves
	// its own actual Playwright Page instance via the thread-local CURRENT_PAGE.
	// This allows class-level parallel execution while preserving sequential order
	// within ordered dependent test classes.
	private static final ThreadLocal<Page> CURRENT_PAGE = new ThreadLocal<>();
	private static final ThreadLocal<Boolean> ALREADY_LOGGED_IN = ThreadLocal.withInitial(() -> false);
	protected static final Page page = createPageProxy();
	static String adminUser;
	static String adminPassword;

	static String adminUser2;
	static String adminPassword2;

	static String authorUser;
	static String authorPassword ;

	static String editorUser ;
	static String editorPassword ;

	static String readUser;
	static String readPassword;
	
	public enum UserType {
        NATIVE,
        ADMIN,
        AUTHOR,
        EDITOR,
        READER
    }

	private static Page createPageProxy() {
		InvocationHandler handler = (proxy, method, args) -> {
			Page current = CURRENT_PAGE.get();
			if (current == null) {
				throw new IllegalStateException("No Playwright Page available for current thread");
			}
			return method.invoke(current, args);
		};
		return (Page) Proxy.newProxyInstance(Page.class.getClassLoader(), new Class<?>[] { Page.class }, handler);
	}
	
	@BeforeAll
	public static void beforeAll() {
		logger.info("Initializing setup for ALL tests");
		try {
			GenericSetupUtils.initialize();
		} catch (IOException e) {
			fail(e.getMessage());
		}
		/// sets up a Resource with a Playwright instance, Browser, and Browser context
		CURRENT_PAGE.set(GenericSetupUtils.setupPlaywright());
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
		ALREADY_LOGGED_IN.set(false);
	}
	
	public static void login(Page page, UserType userType) {
		if (!ALREADY_LOGGED_IN.get()) {
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
			ALREADY_LOGGED_IN.set(true);
		} else {
			logger.warn("Tried to login as: " + userType + " while already logged in. Unsuccessful attempt");
		}
	}
	
	public static void logout(Page page) {
		if (ALREADY_LOGGED_IN.get()) {
			GenericSetupUtils.logout(page);
			ALREADY_LOGGED_IN.set(false);
		} else {
			logger.warn("Tried to logout while not logged in. Unsuccessful attempt");
		}
	}
	
	@AfterEach
	public void afterEach() {
		logger.info("Navigating home");
		HomePageUtils.navigateToHomePage(page);
	}
	
	@AfterAll
	public static void afterAll() throws IOException {
		logger.info("Cleaning up after ALL tests");
		Page currentPage = CURRENT_PAGE.get();
		if (currentPage != null) {
			try {
				GenericSetupUtils.logout(currentPage);
			} catch (Throwable t) {
				logger.warn("Logout during teardown failed", t);
			}
			ALREADY_LOGGED_IN.set(false);
			ResourcePool.get().getContext().close();
			
			// TODO modify this to use the 'fail' logic in SetupHooks.java
			if (GenericSetupUtils.useVideo()) {
				Path og = currentPage.video().path();
				Files.deleteIfExists(og);
			}
			currentPage.close();
		} else {
			logger.warn("No Playwright Page available for current thread during afterAll cleanup");
		}
		CURRENT_PAGE.remove();
		ALREADY_LOGGED_IN.remove();
	}
}
