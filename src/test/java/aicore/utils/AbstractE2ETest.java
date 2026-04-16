package aicore.utils;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.microsoft.playwright.Page;

import aicore.base.GenericSetupUtils;
import aicore.framework.AICoreTestConstants;
import aicore.framework.ConfigUtils;
import aicore.pages.home.HomePageUtils;

/**
 * Superclass to initialize and shutdown e2e env for JUnit tests
 */
public class AbstractE2ETest {
	
	private static final Logger logger = LogManager.getLogger(AbstractE2ETest.class);

	protected static Page page;
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
	static boolean alreadLoggedIn;
	
	public enum UserType {
        NATIVE,
        ADMIN,
        AUTHOR,
        EDITOR,
        READER
    }
	
	@BeforeAll
	public static void beforeAll() {
		logger.info("Initializing setup for ALL tests");
		try {
			GenericSetupUtils.initialize();
		} catch (IOException e) {
			fail(e.getMessage());
		}
		page = GenericSetupUtils.setupPlaywright();
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
		alreadLoggedIn = false;
	}
	
	public static void login(Page page, UserType userType) {
		if (!alreadLoggedIn) {
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
			alreadLoggedIn = true;
		} else {
			logger.warn("Tried to login as: " + userType + " while already logged in. Unsuccessful attempt");
		}
	}
	
	public static void logout(Page page) {
		if (alreadLoggedIn) {
			GenericSetupUtils.logout(page);
			alreadLoggedIn = false;
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
	public static void afterAll() {
		logger.info("Cleaning up after ALL tests");
		GenericSetupUtils.logout(page);
		alreadLoggedIn = false;
		page.close();
	}
}
