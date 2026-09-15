package aicore.utils.reporting;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestPlan;

/**
 * Auto-generates the Allure HTML report after ANY test run finishes -
 * whether that's the full Cucumber suite, a single JUnit5 class, or a subset
 * selected via -Dtest. Registered via ServiceLoader
 * (META-INF/services/org.junit.platform.launcher.TestExecutionListener), so
 * it fires regardless of how tests are launched (IDE, mvn/mvnw, or the JUnit
 * Platform Console launcher directly - no Maven required for this step).
 *
 * Uses the standalone Allure commandline ("allure" on PATH) so it works the
 * same way locally and in CI. If the CLI isn't installed, this logs a
 * warning and does nothing further - it never fails the build/test run.
 *
 * Locally, the report is also auto-opened in the default browser after the
 * run finishes (pass or fail). Under CI (detected via the CI/GITHUB_ACTIONS
 * env vars, which GitHub Actions sets automatically) the report is still
 * generated but the browser-open step is skipped, since there's no display
 * to open it on - no CI-specific code change needed to add CI support later,
 * only wiring the actual pipeline steps (install CLI, upload artifact).
 */
public class AllureReportListener implements TestExecutionListener {
	private static final Logger logger = LogManager.getLogger(AllureReportListener.class);

	private static final Path RESULTS_DIR = Paths.get("target", "allure-results");
	private static final Path REPORT_DIR = Paths.get("target", "site", "allure-report");

	@Override
	public void testPlanExecutionStarted(TestPlan testPlan) {
		// keep local disk usage bounded before each run
		ArtifactCleanup.cleanOlderThan(Paths.get("target", "test-artifacts"), 1);
		ArtifactCleanup.cleanOlderThan(RESULTS_DIR, 1);
		ArtifactCleanup.cleanOlderThan(REPORT_DIR, 1);
	}

	@Override
	public void testPlanExecutionFinished(TestPlan testPlan) {
		if (!java.nio.file.Files.isDirectory(RESULTS_DIR)) {
			logger.info("No allure-results found at {}, skipping report generation", RESULTS_DIR);
			return;
		}
		if (!generateReport()) {
			return;
		}
		if (isRunningInCi()) {
			logger.info("CI environment detected - skipping browser auto-open. Report available at {}",
					REPORT_DIR.toAbsolutePath());
			return;
		}
		openReport();
	}

	/**
	 * Retries a couple of times on failure: this repo lives in a OneDrive-
	 * synced folder, and OneDrive can transiently lock a just-written
	 * allure-results JSON file at the exact moment "generate" runs right
	 * after the test finishes, causing a spurious non-zero exit code even
	 * though the same command succeeds a moment later.
	 */
	private static boolean generateReport() {
		final int maxAttempts = 3;
		for (int attempt = 1; attempt <= maxAttempts; attempt++) {
			try {
				ProcessBuilder pb = new ProcessBuilder(allureCommand(), "generate", "--clean", "-o",
						REPORT_DIR.toString(), RESULTS_DIR.toString());
				pb.redirectErrorStream(true);
				fixJavaHome(pb);
				Process process = pb.start();
				String output = new String(process.getInputStream().readAllBytes());
				boolean finished = process.waitFor(60, TimeUnit.SECONDS);
				if (!finished) {
					process.destroyForcibly();
					logger.warn("Allure report generation timed out after 60s");
					return false;
				}
				if (process.exitValue() == 0) {
					logger.info("Allure report generated at {}", REPORT_DIR.toAbsolutePath());
					return true;
				}
				logger.warn("Allure report generation exited with code {} (attempt {}/{}): {}",
						process.exitValue(), attempt, maxAttempts, output.strip());
			} catch (IOException e) {
				logger.warn("Allure CLI not found on PATH ({}). Skipping auto report generation. "
						+ "Install locally with `npm install -g allure-commandline`, or run `mvn allure:report`/`mvnw allure:report` instead.",
						e.getMessage());
				return false;
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				logger.warn("Allure report generation interrupted: {}", e.getMessage());
				return false;
			}
			if (attempt < maxAttempts) {
				sleepQuietly(750);
			}
		}
		logger.warn("Allure report generation failed after {} attempts", maxAttempts);
		return false;
	}

	private static void sleepQuietly(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * Fire-and-forget: "allure open" starts its own local web server and
	 * launches the default browser, then keeps serving in the background so
	 * the tab keeps working. We deliberately don't waitFor() it - it isn't
	 * meant to exit on its own.
	 */
	private static void openReport() {
		try {
			ProcessBuilder pb = new ProcessBuilder(allureCommand(), "open", REPORT_DIR.toString());
			pb.redirectOutput(ProcessBuilder.Redirect.DISCARD);
			pb.redirectError(ProcessBuilder.Redirect.DISCARD);
			fixJavaHome(pb);
			pb.start();
			logger.info("Opening Allure report in the default browser...");
		} catch (IOException e) {
			logger.warn("Could not auto-open Allure report: {}", e.getMessage());
		}
	}

	private static boolean isRunningInCi() {
		return System.getenv("CI") != null || System.getenv("GITHUB_ACTIONS") != null;
	}

	/**
	 * The bundled "allure.cmd"/"allure" script shells out to whatever
	 * JAVA_HOME is set in the *inherited process environment*, not
	 * necessarily the JDK actually running these tests. On dev machines that
	 * env var can be stale (pointing at an uninstalled/rotated JDK version),
	 * which makes the Allure CLI fail with "JAVA_HOME is set to an invalid
	 * directory" even though a perfectly valid JDK is right here running
	 * this JVM. Always override the child process's JAVA_HOME with
	 * `java.home` from the current JVM - it is guaranteed to exist since
	 * we're actively executing on it - so report generation/opening never
	 * depends on the ambient environment being correctly configured.
	 */
	private static void fixJavaHome(ProcessBuilder pb) {
		String currentJavaHome = System.getProperty("java.home");
		if (currentJavaHome != null && java.nio.file.Files.isDirectory(Paths.get(currentJavaHome))) {
			pb.environment().put("JAVA_HOME", currentJavaHome);
		}
	}

	/**
	 * Resolves the allure executable without relying on the current
	 * process's PATH being up to date. IDEs (Eclipse/IntelliJ) started
	 * before "allure" was added to PATH keep their original environment for
	 * the whole session, so a bare "allure"/"allure.cmd" lookup would fail
	 * until the IDE is restarted. Instead, check well-known npm global
	 * install locations directly first, and only fall back to a PATH-based
	 * lookup (works for terminals/CI that already have it on PATH).
	 */
	private static String allureCommand() {
		boolean windows = System.getProperty("os.name", "").toLowerCase().contains("win");
		String exeName = windows ? "allure.cmd" : "allure";

		String appData = System.getenv("APPDATA");
		if (windows && appData != null) {
			Path npmGlobalWin = Paths.get(appData, "npm", exeName);
			if (java.nio.file.Files.isRegularFile(npmGlobalWin)) {
				return npmGlobalWin.toString();
			}
		}
		String home = System.getProperty("user.home");
		if (home != null) {
			Path npmGlobalUnix = Paths.get(home, ".npm-global", "bin", exeName);
			if (java.nio.file.Files.isRegularFile(npmGlobalUnix)) {
				return npmGlobalUnix.toString();
			}
			Path nvmDefault = Paths.get(home, "AppData", "Roaming", "npm", exeName);
			if (java.nio.file.Files.isRegularFile(nvmDefault)) {
				return nvmDefault.toString();
			}
		}
		// fall back to PATH lookup
		return exeName;
	}
}
