package aicore.utils;

import java.util.concurrent.locks.ReentrantLock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;

import com.microsoft.playwright.Page;

import aicore.utils.extensions.MultiResourceUploadLockExtension;
import aicore.utils.extensions.PlaywrightExtension;

/*
 * Provides an abstract class for tests to inherit the Playwright extension class and access the user type enum and login/logout 
 * operations
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith({ PlaywrightExtension.class, MultiResourceUploadLockExtension.class})//FunctionZipLockExtension.class })
public class AbstractPlaywrightTestBase {
	private static final Logger logger = LogManager.getLogger(AbstractPlaywrightTestBase.class);

	private PlaywrightExtension extension;

	@BeforeAll
	void captureExtension(PlaywrightExtension ext) {
		this.extension = ext;
	}

	protected void loginNativeAdmin(Page page) {
		extension.login(page, PlaywrightExtension.UserType.NATIVE);
	}

	protected void loginAdmin(Page page) {
		extension.login(page, PlaywrightExtension.UserType.ADMIN);
	}

	protected void loginAuthor(Page page) {
		extension.login(page, PlaywrightExtension.UserType.AUTHOR);
	}

	protected void loginEditor(Page page) {
		extension.login(page, PlaywrightExtension.UserType.EDITOR);
	}

	protected void loginReadOnly(Page page) {
		extension.login(page, PlaywrightExtension.UserType.READER);
	}

	protected void logout(Page page) {
		extension.logout(page);
	}
	
	/**
	 * Needed to lock out any threads looking to test any operation sthat requires the database 
	 * to be uploaded. This is to prevent failures that may occur when one resource modifies the 
	 * upload into a state that causes errors in another resource depending on the same upload
	 * @param action
	 */
	protected void acquireDbLock(ReentrantLock lock, Runnable action) {
		lock.lock();
		logger.info("After acquire lock: " + lock.toString());
		try {
			action.run();
		} catch (Exception e) {
			lock.unlock();
			logger.info("After acquire lock failure: " + lock.toString());
			throw e;
		}
	}
	
	protected void releaseDBLock(ReentrantLock lock, Runnable action) {
		try {
			action.run();
		} finally {
			// release lock so next thread can proceed
			lock.unlock();
			logger.info("After release lock: " + lock.toString());
		}
	}

	private PlaywrightExtension getExtension() {
		// JUnit will have injected the extension instance as a TestInstance
		// post-processor.
		// You can look it up via JUnit's ExtensionContext if needed, but usually helper
		// methods accept Page and call static utility methods instead.
		throw new UnsupportedOperationException("not needed in static helper pattern");
	}
}
