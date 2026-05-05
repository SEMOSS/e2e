package aicore.utils;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;

import com.microsoft.playwright.Page;

/*
 * Provides an abstract class for tests to inherit the Playwright extension class and access the user type enum and login/logout 
 * operations
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(PlaywrightExtension.class)
public class AbstractPlaywrightTestBase {

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

	private PlaywrightExtension getExtension() {
		// JUnit will have injected the extension instance as a TestInstance
		// post-processor.
		// You can look it up via JUnit's ExtensionContext if needed, but usually helper
		// methods accept Page and call static utility methods instead.
		throw new UnsupportedOperationException("not needed in static helper pattern");
	}
}
