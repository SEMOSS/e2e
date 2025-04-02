package aicore.hooks;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;

import aicore.base.GenericSetupUtils;
import aicore.base.RunInfo;
import aicore.utils.ConfigUtils;
import aicore.utils.UrlUtils;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;

public class SetupHooks {

	private static final Logger logger = LogManager.getLogger(SetupHooks.class);

	private static Page page;
	private static BrowserContext context;
	private static Browser browser;
	private static Playwright playwright;

	private static int step = 0;

	@BeforeAll
	public static void beforeAll() throws IOException {
		// logger.info("BEFORE ALL: {}", scenario.getName());
		GenericSetupUtils.initialize();

		playwright = Playwright.create();
		browser = playwright.chromium().launch(GenericSetupUtils.getLaunchOptions());
	}

	@Before
	public void before(Scenario scenario) {
		logger.info("BEFORE: {}", scenario.getName());
		step = 0;

		Browser.NewContextOptions newContextOptions = GenericSetupUtils.getContextOptions();
		context = browser.newContext(newContextOptions);
		context.grantPermissions(Arrays.asList("clipboard-read", "clipboard-write"));
		Tracing.StartOptions startOptions = GenericSetupUtils.getStartOptions();
		context.tracing().start(startOptions);

		page = context.newPage();
		page.setDefaultTimeout(Double.parseDouble(ConfigUtils.getValue("timeout")));

		GenericSetupUtils.setupLoggers(page);

		if (GenericSetupUtils.useDocker() && RunInfo.isNeedToCreateUser()) {
			GenericSetupUtils.createUsers(page);
		}

		logger.info("BEFORE - logging in and starting test: {}", scenario.getName());
		String adminUser = ConfigUtils.getValue("native_username");
		String adminPassword = ConfigUtils.getValue("native_password");
		GenericSetupUtils.login(page, adminUser, adminPassword);
	}

	@BeforeStep
	public void beforeStep(Scenario scenario) {
		logger.info("BEFORE STEP: {}, {}", scenario.getName(), step++);
	}

	@AfterStep
	public void afterStep(Scenario scenario) {
		logger.info("STEP {}", scenario.isFailed() ? "FAILED" : "PASSED");
	}

	@After
	public void after(Scenario scenario) throws IOException {
		page.navigate(UrlUtils.getUrl("#/"));
		GenericSetupUtils.logout(page);
		logger.info("AFTER: {}", scenario.getName());
		String scenarioName = scenario.getName();

		if (GenericSetupUtils.useTrace()) {
			Tracing.StopOptions so = new Tracing.StopOptions();
			String filename = scenarioName + ".zip";
			Path dir = Paths.get("traces", "features");
			if (!Files.exists(dir)) {
				Files.createDirectories(dir);
			}
			Path traceFile = Paths.get("traces", "features", filename);
			so.setPath(traceFile);
			page.context().tracing().stop(so);
		}

		context.close();

		if (GenericSetupUtils.useVideo()) {
			Path og = page.video().path();
			Path newPath = Paths.get("videos", "features", scenarioName + ".webm");
			Path newDir = Paths.get("videos", "features");
			if (!Files.exists(newDir)) {
				Files.createDirectories(newDir);
			}
			Files.move(og, newPath);
		}

		page.close();
	}

	@AfterAll
	public static void afterAll() {
		logger.info("AFTER ALL");
		playwright.close();
	}

	public static Page getPage() {
		return page;
	}
}
