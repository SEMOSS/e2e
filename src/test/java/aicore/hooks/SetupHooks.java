package aicore.hooks;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

import aicore.framework.ResourcePool;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;

import aicore.base.GenericSetupUtils;
import aicore.utils.CaptureScreenShotUtils;
import aicore.utils.CommonUtils;
import aicore.framework.ConfigUtils;
import aicore.utils.TestResourceTrackerHelper;
import aicore.framework.UrlUtils;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import org.opentest4j.TestAbortedException;

public class SetupHooks {

	private static final Logger logger = LogManager.getLogger(SetupHooks.class);


	@BeforeAll
	public static void beforeAll() throws IOException {
		logger.info("BEFORE ALL");
		GenericSetupUtils.initialize();
		// not sure how this works with parallel?
		// before all runs outside of parallelization but what about in between features?
		//scenarioNumberOfFeatureFile = 0;
	}

	@Before
	public void before(Scenario scenario) throws IOException {
		logger.info("Thread:Scenario {}:{}", Thread.currentThread().getName(), scenario.getName());
		String tempFeature = FilenameUtils.getBaseName(scenario.getUri().toString());
		String feature = ResourcePool.get().getFeature();
		
		// If new feature -> reset | if not -> continue
		if (!tempFeature.equals(feature)) {
			ResourcePool.get().resetTimestamp();
			ResourcePool.get().resetScenarioNumberOfFeatureFile();
			setupFirstScenarioOfFeature(scenario);
			ResourcePool.get().incrementFeatureNumber();
		}

		ResourcePool.get().setFeature(tempFeature);
		ResourcePool.get().incrementScenarioNumberOfFeatureFile();
		ResourcePool.get().resetStep();
	}

	private static void setupFirstScenarioOfFeature(Scenario scenario) throws IOException {
		// Not First scenario, reset page
		if (ResourcePool.get().getFeatureNumber() != 0) {
			try {
				GenericSetupUtils.navigateToHomePage(ResourcePool.get().getPage());
				logoutAndSave();
			} catch (Exception | Error e) {
				logger.error("ATTEMPTING TO LOGOUT AND SAVE", e);
			}
			ResourcePool.get().getPlaywright().close();
		}


		Playwright playwright = Playwright.create();
		ResourcePool.get().setPlaywright(playwright);

		Browser browser = playwright.chromium().launch(GenericSetupUtils.getLaunchOptions());
		ResourcePool.get().setBrowser(browser);

		Browser.NewContextOptions newContextOptions = GenericSetupUtils.getContextOptions().setViewportSize(1280, 720)
				.setDeviceScaleFactor(1)
				.setPermissions(Arrays.asList("clipboard-read", "clipboard-write"))
				.setTimezoneId("America/New_York"); // ensures DPI/zoom consistency;
		BrowserContext context = browser.newContext(newContextOptions);
		ResourcePool.get().setContext(context);

		context.grantPermissions(Arrays.asList("clipboard-read", "clipboard-write"));

		if (Boolean.parseBoolean(ConfigUtils.getValue("use_trace"))) {
			Tracing.StartOptions startOptions = GenericSetupUtils.getStartOptions();
			context.tracing().start(startOptions);
		}

		Page page = context.newPage();

		ResourcePool.get().setPage(page);
		page.setDefaultTimeout(Double.parseDouble(ConfigUtils.getValue("timeout")));

		GenericSetupUtils.setupLoggers(page);

//		if (GenericSetupUtils.useDocker() && RunInfo.isNeedToCreateUser()) {
//			logger.info("Creating users");
//			GenericSetupUtils.createUsers(page);
//		}

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
		int step = ResourcePool.get().getStep();
		//logger.info("BEFORE STEP: {}, {}", scenario.getName(), step++);
		ResourcePool.get().setStep(step);
	}

	@AfterStep
	public void afterStep(Scenario scenario) {
		//logger.info("STEP {}", scenario.isFailed() ? "FAILED" : "PASSED");
	}

	@After
	public void after(Scenario scenario) throws IOException {
		logger.info("AFTER: {}", scenario.getName());
		GenericSetupUtils.navigateToHomePage(ResourcePool.get().getPage());
		Paths.get("img");
		if (Files.exists(Paths.get("img"))) {
			logger.info("Folder img exists");
			logger.info("File is directory: {}", Files.isDirectory(Paths.get("img")));
			try (Stream<Path> stream = Files.list(Paths.get("img"))) {
				stream.forEach(path -> { logger.info("File: {}", path.toAbsolutePath()); });
			}
		}
	}

	@AfterAll
	public static void afterAll() throws IOException {
		logger.info("AFTER ALL");
	}

	private static void logoutAndSave() throws IOException {
		Page page = ResourcePool.get().getPage();
		BrowserContext context = ResourcePool.get().getContext();
		String feature = ResourcePool.get().getFeature();
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
		return ResourcePool.get().getPage();
	}

	public static String getTimestamp() {
		return ResourcePool.get().getTimestamp();
	}

	@After("@DeleteCreatedCatalog")
	public void deleteCatalog(Scenario scenario) {
		Page page = ResourcePool.get().getPage();
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

	@AfterAll
	public static void updateVersion() {
		String version = CaptureScreenShotUtils.version;
		ConfigUtils.setValue("current_version", version);
	}

	@Before("@SkipIfVersionMatch")
	public void compareVersion(Scenario scenario) {
		logger.info("Getting version for app");
		Page page = ResourcePool.get().getPage();
		boolean isVersionMatched = CommonUtils.getVersion(page);
		if (isVersionMatched == true) {
			throw new TestAbortedException("Skipping scenario due to version match.");
		}
	}

	@After("@DeleteTestCatalog")
	public void deleteCatalogResources(Scenario scenario) {
		String scenarioName = scenario.getName();
		Page page = ResourcePool.get().getPage();
		try {
			TestResourceTrackerHelper tracker = TestResourceTrackerHelper.getInstance();
			Map<String, String> catalogMap = tracker.getCatalogType();
			for (Map.Entry<String, String> entry : catalogMap.entrySet()) {
				String type = entry.getKey();
				String id = entry.getValue();
				if (id != null && !id.isBlank()) {
					boolean deleteCatalog = CommonUtils.navigateAndDeleteCatalog(page, type, id);
					if (deleteCatalog) {
						logger.info("Scenario Name: " + scenarioName + " : Catalog deleted successfully. Type: " + type
								+ ", ID: " + id);
					} else {
						logger.warn("Scenario Name: " + scenarioName + " : Failed to Delete catalog: Type: " + type
								+ ", ID = " + id);
					}
				} else {
					logger.warn("Scenario Name: " + scenarioName + " : Catalog ID not available for Type: " + type);
				}
			}
		} finally {
			TestResourceTrackerHelper.getInstance().clearCatalogResources();
		}
	}

	@After("@DeleteCreatedTestApp")
	public void deleteCreatedApp(Scenario scenario) {
		String scenarioName = scenario.getName();
		try {
			String appName = TestResourceTrackerHelper.getInstance().getAppName();
			if (appName != null && !appName.isBlank()) {
				Page page = ResourcePool.get().getPage();
				boolean deleted = CommonUtils.navigateAndDeleteApp(page, appName);
				if (deleted) {
					logger.info("Scenario Name: " + scenarioName + " : App deleted successfully. Name: " + appName);
				} else {
					logger.warn("Scenario Name: " + scenarioName + " : Failed to delete App: " + appName);
				}
			} else {
				logger.warn("Scenario Name: " + scenarioName + " : App name not available for deletion.");
			}
		} finally {
			TestResourceTrackerHelper.getInstance().setAppName(null);
		}
	}


}