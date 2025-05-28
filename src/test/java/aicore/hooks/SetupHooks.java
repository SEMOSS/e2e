package aicore.hooks;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;

import aicore.base.GenericSetupUtils;
import aicore.base.RunInfo;
import aicore.utils.CommonUtils;
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
	private static int scenarioNumberOfFeatureFile = 0;
	private static String feature = "";
	private static int featureNumber = 0;

	@BeforeAll
	public static void beforeAll() throws IOException {
		logger.info("BEFORE ALL");
		GenericSetupUtils.initialize();
		scenarioNumberOfFeatureFile = 0;

		playwright = Playwright.create();
		browser = playwright.chromium().launch(GenericSetupUtils.getLaunchOptions());

	}

	@Before
	public void before(Scenario scenario) throws IOException {
		logger.info("BEFORE: {}", scenario.getName());
		String tempFeature = FilenameUtils.getBaseName(scenario.getUri().toString());
		if (!tempFeature.equals(feature)) {
			scenarioNumberOfFeatureFile = 0;
			setupFirstScenarioOfFeature(scenario);
			featureNumber++;
		}
		feature = tempFeature;
		scenarioNumberOfFeatureFile++;
		step = 0;
	}

	private static void setupFirstScenarioOfFeature(Scenario scenario) throws IOException {
		if (featureNumber != 0) {
			GenericSetupUtils.navigateToHomePage(page);
			logoutAndSave();
		}
		Browser.NewContextOptions newContextOptions = GenericSetupUtils.getContextOptions();
		context = browser.newContext(newContextOptions);

		context.grantPermissions(Arrays.asList("clipboard-read", "clipboard-write"));

		Tracing.StartOptions startOptions = GenericSetupUtils.getStartOptions();
		context.tracing().start(startOptions);

		page = context.newPage();
		page.setDefaultTimeout(Double.parseDouble(ConfigUtils.getValue("timeout")));

		GenericSetupUtils.setupLoggers(page);

		if (GenericSetupUtils.useDocker() && RunInfo.isNeedToCreateUser()) {
			logger.info("Creating users");
			GenericSetupUtils.createUsers(page);
		}

		logger.info("BEFORE - logging in and starting test: {}", scenario.getName());

		String sourceTagName = scenario.getSourceTagNames().stream().findFirst().orElse("");

		// Use switch to handle different sourceTagNames
		switch (sourceTagName) {
		case "@LoginWithMS":
			String MsUsername = ConfigUtils.getValue("ms_username");
			String MsPassword = ConfigUtils.getValue("ms_password");
			GenericSetupUtils.loginWithMSuser(page, MsUsername, MsPassword);
			break;

		case "@LoginWithAdmin":
			String adminUser = ConfigUtils.getValue("admin_username");
			String adminPassword = ConfigUtils.getValue("admin_password");
			GenericSetupUtils.login(page, adminUser, adminPassword);
			break;

		case "@LoginWithAuthor":
			String authorUser = ConfigUtils.getValue("author_username");
			String authorPassword = ConfigUtils.getValue("author_password");
			GenericSetupUtils.login(page, authorUser, authorPassword);
			break;

		case "@LoginWithEditor":
			String nativeEditorUser = ConfigUtils.getValue("editor_username");
			String nativeEditorPassword = ConfigUtils.getValue("editor_password");
			GenericSetupUtils.login(page, nativeEditorUser, nativeEditorPassword);
			break;

		case "@LoginWithReadOnly":
			String nativeReadUser = ConfigUtils.getValue("read_username");
			String nativeReadPassword = ConfigUtils.getValue("read_password");
			GenericSetupUtils.login(page, nativeReadUser, nativeReadPassword);
			break;

		default:
			String nativeUser = ConfigUtils.getValue("native_username");
			String nativePassword = ConfigUtils.getValue("native_password");
			GenericSetupUtils.login(page, nativeUser, nativePassword);
			break;
		}
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
		logger.info("AFTER: {}", scenario.getName());
		GenericSetupUtils.navigateToHomePage(page);
	}

	@AfterAll
	public static void afterAll() throws IOException {
		logger.info("AFTER ALL");
		GenericSetupUtils.navigateToHomePage(page);
		logoutAndSave();
		playwright.close();
	}

	private static void logoutAndSave() throws IOException {
		try {
			page.navigate(UrlUtils.getUrl("#/"));
			GenericSetupUtils.logout(page);
		} catch (Throwable t) {
			logger.error("Could not logout with throwable message: {}", t.getMessage());
		}

		if (GenericSetupUtils.useTrace()) {
			logger.info("Using trace. Saving trace video");
			Tracing.StopOptions so = new Tracing.StopOptions();
			String filename = feature + ".zip";
			Path dir = Paths.get("traces", "features");
			if (!Files.exists(dir)) {
				logger.info("Creating directories");
				Files.createDirectories(dir);
			}
			Path traceFile = Paths.get("traces", "features", filename);
			logger.info("Setting path: {}", traceFile);
			so.setPath(traceFile);
			page.context().tracing().stop(so);
		}

		context.close();
		Path og = null;
		if (GenericSetupUtils.useVideo()) {
			og = page.video().path();
		}

		page.close();

		if (GenericSetupUtils.useVideo()) {
			Path newPath = Paths.get("videos", "features", feature + ".webm");
			Path newDir = Paths.get("videos", "features");
			if (!Files.exists(newDir)) {
				Files.createDirectories(newDir);
			}
			Files.move(og, newPath);
		}

	}

	public static Page getPage() {
		return page;
	}

	@After("@DeleteCreatedCatalog")
	public void deleteCatalog(Scenario scenario) {
		final String ACCESS_CONTROL_XPATH = "//button[text()='Access Control']";
		final String DELETE_BUTTON_XPATH = "//span[text()='Delete']";
		final String CONFIRMATION_POPUP_DELETE_BUTTON_XPATH = "//div[contains(@class,'MuiDialog-paperWidthSm')]//div//button[contains(@class,'MuiButton-containedSizeMedium')]";
		final String DELETE_TOAST_MESSAGE_XPATH = "//div[contains(text(),'Successfully deleted')]";
		boolean isDeleted = CommonUtils.deleteCatalog(page, ACCESS_CONTROL_XPATH, DELETE_BUTTON_XPATH,
				CONFIRMATION_POPUP_DELETE_BUTTON_XPATH, DELETE_TOAST_MESSAGE_XPATH);

		if (isDeleted) {
			logger.info("Catalog deleted successfully after scenario", scenario.getName());
		} else {
			logger.warn("Failed to delete catalog after scenario", scenario.getName());
		}
	}

}