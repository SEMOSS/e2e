package aicore.hooks;

import org.assertj.core.api.SoftAssertions;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;

public class SoftAssertionHooks {
	private static final ThreadLocal<SoftAssertions> softAssertions = ThreadLocal.withInitial(SoftAssertions::new);

	// Get SoftAssertions instance for the current thread
	public static SoftAssertions getSoftAssertions() {
		return softAssertions.get();
	}

	@After
	public void validateAllAssertions(Scenario scenario) {
		try {
			softAssertions.get().assertAll();
		} catch (AssertionError e) {
			scenario.log("Soft Assertions Failed:\n" + e.getMessage());
			throw e;
		} finally {
			softAssertions.remove(); // Cleanup to prevent memory leaks
		}
	}

}
