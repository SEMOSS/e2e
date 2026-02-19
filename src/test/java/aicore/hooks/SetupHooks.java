package aicore.hooks;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;

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
import aicore.framework.Resource;
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
		// Do not create browser/playwright here - setup is done per-thread when a feature starts
	}

	@Before
	public void before(Scenario scenario) throws IOException {
		logger.info("Thread: {}", Thread.currentThread().getName());
        logger.info("Scenario: {}", scenario.getName());
		String tempFeature = FilenameUtils.getBaseName(scenario.getUri().toString());
		String feature = ResourcePool.get().getFeature();
        String scenarioName = scenario.getName();
        
		// If new feature OR resource not initialized -> logout previous, reset and login
		Resource res = ResourcePool.get();
		if (res.getPage() == null || !tempFeature.equals(feature)) {
			logger.info("New feature or uninitialized resource detected: {} (current feature: {})", tempFeature, feature);
			// Logout from previous feature if exists and we have an active page
			if (feature != null && res.getPage() != null) {
				try {
					GenericSetupUtils.logout(res.getPage());
				} catch (Exception e) {
					logger.warn("Failed to logout from previous feature: {}", e.getMessage());
				}
			}

			// If there is no page, create it; if feature changed and there is a page, recreate context
			if (res.getPage() == null) {
				setupFirstScenarioOfFeature();
				try {
					GenericSetupUtils.navigateToHomePage(res.getPage());
				} catch (Throwable t) {
					logger.warn("Navigation after setup failed: {}", t.getMessage());
				}
			} else {
				// Feature changed while a page already exists -> close context and reinitialize
				res.closeContext();
				setupFirstScenarioOfFeature();
			}

			performLoginBasedOnTags(scenario);
			res.resetTimestamp();
			res.resetScenarioNumberOfFeatureFile();
			res.incrementFeatureNumber();
		}

		ResourcePool.get().setFeature(tempFeature);
        ResourcePool.get().setScenarioName(scenarioName);
		ResourcePool.get().incrementScenarioNumberOfFeatureFile();
		ResourcePool.get().resetStep();
        ResourcePool.get().setFailed(false);
	}

	private static void setupFirstScenarioOfFeature() throws IOException {

		Resource res = ResourcePool.get();

		// If browser/playwright not created for this resource, create them once and reuse across features
		if (res.getBrowser() == null || res.getPlaywright() == null) {
			logger.info("Creating playwright/browser for resource {} with url {}", res.getResourceNumber(), res.getUrl());
			Playwright playwright = Playwright.create();
			res.setPlaywright(playwright);

			Browser browser = playwright.chromium().launch(GenericSetupUtils.getLaunchOptions());
			res.setBrowser(browser);
		} else {
			logger.info("Reusing existing browser for resource {}", res.getResourceNumber());
		}

		// Always create a fresh context + page per feature to isolate state, closing prior context if present
		res.closeContext();

		Browser.NewContextOptions newContextOptions = GenericSetupUtils.getContextOptions().setViewportSize(1280, 720)
				.setDeviceScaleFactor(1)
				.setPermissions(Arrays.asList("clipboard-read", "clipboard-write"))
				.setTimezoneId("America/New_York"); // ensures DPI/zoom consistency;
		BrowserContext context = res.getBrowser().newContext(newContextOptions);
		res.setContext(context);

		context.grantPermissions(Arrays.asList("clipboard-read", "clipboard-write"));

		if (Boolean.parseBoolean(ConfigUtils.getValue("use_trace"))) {
			Tracing.StartOptions startOptions = GenericSetupUtils.getStartOptions();
			context.tracing().start(startOptions);
		}

		Page page = context.newPage();

		res.setPage(page);
		page.setDefaultTimeout(Double.parseDouble(ConfigUtils.getValue("timeout")));

		GenericSetupUtils.setupLoggers(page);
	}

//		if (GenericSetupUtils.useDocker() && RunInfo.isNeedToCreateUser()) {
//			logger.info("Creating users");
//			GenericSetupUtils.createUsers(page);
//		}
private static void performLoginBasedOnTags(Scenario scenario) {
		Page page = ResourcePool.get().getPage();
		logger.info("BEFORE - logging in and starting test: {}", scenario.getName());

		String sourceTagName = scenario.getSourceTagNames().stream().findFirst().orElse("");
		performLogin(sourceTagName, page);
	}

	private static void performLogin(String sourceTagName, Page page) {
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
        ResourcePool.get().setFailed(scenario.isFailed());
		GenericSetupUtils.navigateToHomePage(ResourcePool.get().getPage());
		
		int currentScenarioCount = ResourcePool.get().getScenarioNumberOfFeatureFile();
		int totalScenarioCount = 0;
		try {
			Path featurePath = null;
			// Prefer using the URI directly to avoid Windows leading-slash issues ("/D:/...")
			try {
				featurePath = Paths.get(scenario.getUri());
			} catch (IllegalArgumentException ex) {
				String p = scenario.getUri().getPath();
				if (p != null && p.startsWith("/") && p.length() > 2 && p.charAt(2) == ':') {
					// Strip leading slash for Windows absolute paths like "/D:/..."
					p = p.substring(1);
				}
				featurePath = Paths.get(p);
			}
			if (Files.exists(featurePath)) {
				totalScenarioCount = (int) Files.readAllLines(featurePath).stream()
					.filter(line -> line.trim().startsWith("Scenario:"))
					.count();
			} else {
				logger.warn("Feature file not found when counting scenarios: {}", featurePath);
			}
		} catch (IOException e) {
			logger.warn("Unable to read feature file to count scenarios: {}", e.getMessage());
		}
		if (currentScenarioCount == totalScenarioCount && totalScenarioCount > 0) {
			logger.info("Last scenario of the feature: {}. Logging out and closing context.", ResourcePool.get().getFeature());
			logoutAndSave();
		}
	}

	@AfterAll
	public static void afterAll() throws IOException {
		logger.info("AFTER ALL - cleaning up current resource context only");
		// Only close the context (page + context) for this thread's resource. Do NOT
		// close the browser or Playwright here because multiple workers run in the
		// same JVM and other workers may still be using their browsers.
		try {
			ResourcePool.get().closeContext();
		} catch (Exception e) {
			logger.warn("Error closing context in AfterAll: {}", e.getMessage());
		}
	}

	private static void logoutAndSave() throws IOException {
        logger.info("logging out and saving");
		Page page = ResourcePool.get().getPage();
		BrowserContext context = ResourcePool.get().getContext();
		String feature = ResourcePool.get().getFeature();
		
        String scenarioName = ResourcePool.get().getScenarioName();
		try {
			page.navigate(UrlUtils.getUrl("#/"));
			GenericSetupUtils.logout(page);
		} catch (Exception e) {
			logger.warn("Failed to logout: {}", e.getMessage());
		}

        boolean failed = ResourcePool.get().isFailed();

        logger.info("saving trace and videos");
		if (GenericSetupUtils.useTrace() && failed) {
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
		page.close();

		if (GenericSetupUtils.useVideo()) {
            logger.info("saving video");
            // Find the actual video file created by Playwright
            Optional<Path> videoFile = findRecentVideoFile(Paths.get(System.getProperty("user.dir")));
            
            if (videoFile.isPresent()) {
                Path sourceVideo = videoFile.get();
                logger.info("Found video file: {}", sourceVideo);
                
                if (failed) {
                    // Rename video for failed scenarios
                    String scenarioNameSafe = makeScenarioNameFileSafe(scenarioName);
                    Path targetDir = Paths.get("videos", "features", feature);
                    if (!Files.exists(targetDir)) {
                        Files.createDirectories(targetDir);
                    }

                    // Determine target path with collision avoidance
                    Path newPath = Paths.get(targetDir.toString(), scenarioNameSafe + ".webm");
                    int counter = 1;
                    while (Files.exists(newPath) && counter < 1000) {
                        newPath = Paths.get(targetDir.toString(), scenarioNameSafe + "_" + counter + ".webm");
                        counter++;
                    }

                    try {
                        Files.move(sourceVideo, newPath);
                        logger.info("Video successfully renamed and moved to: {}", newPath);
                    } catch (IOException moveEx) {
                        logger.warn("Failed to move video from {} to {}: {}", sourceVideo, newPath, moveEx.getMessage());
                        // If move fails, try to copy and delete as fallback
                        try {
                            Files.copy(sourceVideo, newPath);
                            Files.deleteIfExists(sourceVideo);
                            logger.info("Video copied to destination as fallback: {}", newPath);
                        } catch (IOException copyEx) {
                            logger.error("Failed to copy video as fallback: {}", copyEx.getMessage());
                        }
                    }
                } else {
                    // Delete video for passed scenarios
                    try {
                        Files.deleteIfExists(sourceVideo);
                        logger.info("Video deleted for passing scenario: {}", scenarioName);
                    } catch (IOException delEx) {
                        logger.warn("Failed to delete video for passing scenario {}: {}", scenarioName, delEx.getMessage());
                    }
                }
            } else {
                logger.warn("No video file found for scenario: {}", scenarioName);
            }
		}
	}

	private static Optional<Path> findRecentVideoFile(Path searchDir) {
		try {
			long cutoffTime = System.currentTimeMillis() - (5 * 60 * 1000); // Last 5 minutes
			return Files.walk(searchDir, 3)
					.filter(Files::isRegularFile)
					.filter(p -> p.toString().endsWith(".webm"))
					.filter(p -> {
						try {
							return Files.getLastModifiedTime(p).toMillis() > cutoffTime;
						} catch (IOException e) {
							return false;
						}
					})
					.max(Comparator.comparingLong(p -> {
						try {
							return Files.getLastModifiedTime(p).toMillis();
						} catch (IOException e) {
							return 0;
						}
					}));
		} catch (IOException e) {
			logger.warn("Error searching for video files: {}", e.getMessage());
			return Optional.empty();
		}
	}

    public static String makeScenarioNameFileSafe(String scenarioName) {
        return scenarioName.trim().replaceAll("[^a-zA-Z0-9._-]", "_");
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